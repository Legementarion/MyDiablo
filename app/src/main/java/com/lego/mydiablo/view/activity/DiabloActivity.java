package com.lego.mydiablo.view.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;

import com.lego.mydiablo.R;
import com.lego.mydiablo.events.FragmentEvent;
import com.lego.mydiablo.rest.AuthRequest;
import com.lego.mydiablo.rest.RetrofitRequests;
import com.lego.mydiablo.rest.callback.models.UserData.AccessToken;
import com.lego.mydiablo.utils.Const;
import com.lego.mydiablo.utils.Settings;
import com.lego.mydiablo.view.fragments.ItemListFragment;
import com.lego.mydiablo.view.fragments.MenuCallBack;
import com.lego.mydiablo.view.fragments.MenuFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.exceptions.RealmMigrationNeededException;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.lego.mydiablo.utils.Const.AUTHORIZE_URI;
import static com.lego.mydiablo.utils.Const.CREDENTIALS;
import static com.lego.mydiablo.utils.Const.REDIRECTION_URI;
import static com.lego.mydiablo.utils.Const.RESPONSE_TYPE;
import static com.lego.mydiablo.utils.Settings.mBattleTag;
import static com.lego.mydiablo.utils.Settings.mDetailActive;
import static com.lego.mydiablo.utils.Settings.mToken;
import static com.lego.mydiablo.utils.Settings.mTwoPane;


public class DiabloActivity extends FragmentActivity implements MenuCallBack {

    @BindView(R.id.start_menu_fragment)
    FrameLayout mContainer;

    private Unbinder mUnbinder;
    private Dialog mAuthDialog;

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private FragmentManager mFragmentManager;
    private MenuFragment mMenuFragment;
    private ItemListFragment mListFragment;

    private Realm mRealm;
    private RetrofitRequests mRetrofitRequests;
    private EventBus bus = EventBus.getDefault();

    private boolean doubleBackToExitPressedOnce;
    private String mAuthCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_menu_activity);
        mUnbinder = ButterKnife.bind(this);
        mMenuFragment = new MenuFragment();
        mListFragment = new ItemListFragment();
        mFragmentManager = getSupportFragmentManager();
        mFragmentManager.beginTransaction().replace(R.id.start_menu_fragment, mMenuFragment).commit();

        config();

        if (hasConnection(this)) {
            if (mToken == null) {
                signIn();
            } else {
                AuthRequest.checkToken(mToken);
            }
        }
    }

    public void config() {
        // Register as a subscriber
        bus.register(this);
        mRetrofitRequests = RetrofitRequests.getInstance();

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Settings.mTwoPane = true;
            mFragmentManager.beginTransaction().replace(R.id.item_list, mListFragment).commit();
        }

        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(this)
                .name(Realm.DEFAULT_REALM_NAME)
                .schemaVersion(0)
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);

        try {
            mRealm = Realm.getInstance(realmConfiguration);
        } catch (RealmMigrationNeededException e) {
            try {
                Realm.deleteRealm(realmConfiguration);
                //Realm file has been deleted.
                mRealm = Realm.getInstance(realmConfiguration);
            } catch (Exception ex) {
                throw ex;
                //No Realm file to remove.
            }
        }
    }

    public void signIn() {
        mAuthDialog = new Dialog(DiabloActivity.this);
        mAuthDialog.setContentView(R.layout.auth_dialog);
        mAuthDialog.setTitle(R.string.Authorization_title);
        String url = AUTHORIZE_URI + RESPONSE_TYPE + CREDENTIALS + REDIRECTION_URI + Const.REDIRECT_URI;
        WebView webView = (WebView) mAuthDialog.findViewById(R.id.wvOauth);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(url);

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                if (url.contains("?code=") || url.contains("&code=")) {
                    Uri uri = Uri.parse(url);
                    mAuthCode = uri.getQueryParameter("code");
                    AuthRequest.getAccessToken(false, mAuthCode)
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribeOn(Schedulers.newThread())
                            .subscribe(new Subscriber<AccessToken>() {
                                @Override
                                public void onCompleted() {
                                    if (!isUnsubscribed())
                                        unsubscribe();
                                }
                                @Override
                                public void onError(Throwable e) {
                                    Log.e("getAccessToken", e.toString());
                                }
                                @Override
                                public void onNext(AccessToken accessToken) {
                                    mToken = accessToken.getAccess_token();
                                    AuthRequest.getBattleTag();
                                    mRetrofitRequests.getEraCount();
                                    mRetrofitRequests.getSeasonCount();
                                }
                            });
                    mAuthDialog.dismiss();
                }
                return true;
            }
        });
        mAuthDialog.show();
        mAuthDialog.setCancelable(false);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
        bus.unregister(this);
    }

    @Override
    public void menuCallBackClick(View view) {
        int i = view.getId();
        if (i == R.id.back_button) {
            switchFragment(mMenuFragment);
        } else {
            if (!Settings.mTwoPane)
                switchFragment(mListFragment);
        }
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }
        this.doubleBackToExitPressedOnce = true;
        Snackbar.make(mContainer, R.string.doubleClick_backBtn, Snackbar.LENGTH_SHORT).show();
        new Handler().postDelayed(() -> doubleBackToExitPressedOnce = false, 2000);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(FragmentEvent event) {
        switchFragment(event.getData());
    }

    private static boolean hasConnection(final Context context) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    private void switchFragment(Fragment fragment) {
        if (mTwoPane) {
            if (mDetailActive) {
                mFragmentManager.beginTransaction().replace(R.id.start_menu_fragment, mMenuFragment).commit();
                mFragmentManager.beginTransaction().replace(R.id.item_list, mListFragment).commit();
            } else {
                mFragmentManager.beginTransaction().replace(R.id.start_menu_fragment, mListFragment).commit();
                mFragmentManager.beginTransaction().replace(R.id.item_list, fragment).commit();
            }
        } else {
            if (fragment == null){
                mFragmentManager.beginTransaction().replace(R.id.start_menu_fragment, mListFragment).commit();
            }else {
                mFragmentManager.beginTransaction().replace(R.id.start_menu_fragment, fragment).commit();
            }
        }
    }

}
