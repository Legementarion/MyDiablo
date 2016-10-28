package com.lego.mydiablo.presenter.activity;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.lego.mydiablo.R;
import com.lego.mydiablo.events.FragmentEvent;
import com.lego.mydiablo.rest.AuthRequest;
import com.lego.mydiablo.rest.RetrofitRequests;
import com.lego.mydiablo.rest.callback.models.UserData.AccessToken;
import com.lego.mydiablo.rest.callback.models.UserData.CheckedToken;
import com.lego.mydiablo.utils.Settings;
import com.lego.mydiablo.view.fragments.ItemListFragment;
import com.lego.mydiablo.view.fragments.MenuFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.lego.mydiablo.utils.Settings.mDetailActive;
import static com.lego.mydiablo.utils.Settings.mToken;
import static com.lego.mydiablo.utils.Settings.mTwoPane;

@InjectViewState
public class DiabloPresenter extends MvpPresenter<DiabloView> {

    private EventBus bus = EventBus.getDefault();
    private RetrofitRequests mRetrofitRequests;

    private MenuFragment mMenuFragment;
    private ItemListFragment mListFragment;

    DiabloPresenter() {
        bus.register(this);
        mRetrofitRequests = RetrofitRequests.getInstance();
        Log.d("Track", "onCreate: presenter");
        mMenuFragment = new MenuFragment();
        mListFragment = new ItemListFragment();

    }

    public boolean hasConnection(final Context context) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        if (mToken == null) {
            Log.d("Track", "onFirstViewAttach: " + mToken);
            getViewState().openAuthDialog();
        } else {
            Log.d("Track", "onFirstViewAttach: check session");
            checkSession();
        }
    }

    private void checkSession() {
        AuthRequest.checkToken(mToken).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Subscriber<CheckedToken>() {
                    @Override
                    public void onCompleted() {
                        unsubscribe();
                    }

                    @Override
                    public void onError(Throwable e) {
                        getViewState().openAuthDialog();
                        Log.d("Check Session", "onError: " + e);
                    }

                    @Override
                    public void onNext(CheckedToken checkedToken) {
                        if (checkedToken.getError() != null) {
                            getViewState().openAuthDialog();
                            Log.d("Track", "onNext:  token error ");
                        }
                        Log.d("Track", "onNext:  token pass");
                    }
                });
    }

    public void signIn(String url) {
        String mAuthCode;
        if (url.contains("?code=") || url.contains("&code=")) {
            Uri uri = Uri.parse(url);
            mAuthCode = uri.getQueryParameter("code");
            AuthRequest.getAccessToken(false, mAuthCode)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.newThread())
                    .subscribe(new Subscriber<AccessToken>() {
                        @Override
                        public void onCompleted() {
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
                            getViewState().closeAuthDialog();
                        }
                    });
        }
    }

    public void config(Activity activity, FragmentManager fragmentManager) {
        fragmentManager.beginTransaction().replace(R.id.start_menu_fragment, mMenuFragment).commit();
        if (activity.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Settings.mTwoPane = true;
            fragmentManager.beginTransaction().replace(R.id.start_menu_fragment, mMenuFragment).commit();
            fragmentManager.beginTransaction().replace(R.id.item_list, mListFragment).commit();
        }
    }

    public void switchFragment(FragmentManager fragmentManager, Fragment fragment) {
        if (mTwoPane) {
            if (mDetailActive) {
                fragmentManager.beginTransaction().replace(R.id.start_menu_fragment, mMenuFragment).commit();
                fragmentManager.beginTransaction().replace(R.id.item_list, mListFragment).commit();
            } else {
                fragmentManager.beginTransaction().replace(R.id.start_menu_fragment, mListFragment).commit();
                fragmentManager.beginTransaction().replace(R.id.item_list, fragment).commit();
            }
        } else {
            if (fragment == null) {
                fragmentManager.beginTransaction().replace(R.id.start_menu_fragment, mListFragment).commit();
            } else {
                fragmentManager.beginTransaction().replace(R.id.start_menu_fragment, fragment).commit();
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        bus.unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(FragmentEvent event) {
//        switchFragment(event.getData());
    }

}
