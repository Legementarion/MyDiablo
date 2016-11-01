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
import com.lego.mydiablo.utils.LastFragment;
import com.lego.mydiablo.utils.Settings;
import com.lego.mydiablo.view.fragments.HeroTabsFragment;
import com.lego.mydiablo.view.fragments.ItemListFragment;
import com.lego.mydiablo.view.fragments.MenuFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.lego.mydiablo.utils.LastFragment.DETAIL;
import static com.lego.mydiablo.utils.LastFragment.LIST;
import static com.lego.mydiablo.utils.LastFragment.MENU;
import static com.lego.mydiablo.utils.Settings.mToken;
import static com.lego.mydiablo.utils.Settings.mTwoPane;

@InjectViewState
public class DiabloPresenter extends MvpPresenter<DiabloView> {

    private EventBus bus = EventBus.getDefault();
    private RetrofitRequests mRetrofitRequests;
    private LastFragment mLastFragment;

    DiabloPresenter() {
        bus.register(this);
        mRetrofitRequests = RetrofitRequests.getInstance();
    }

    public boolean hasConnection(final Context context) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    @Override
    public void attachView(DiabloView view) {
        super.attachView(view);
        if (mToken != null) {
            checkSession();
        }
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        if (mToken == null) {
            getViewState().openAuthDialog();
        } else {
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
                        }
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

    public void startConfig(Activity activity) {
        getViewState().showFragment(R.id.main_container, MenuFragment.newInstance(), MenuFragment.TAG);
        mLastFragment = MENU;
        if (activity.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Settings.mTwoPane = true;
            mLastFragment = LIST;
            setTwoScreen();
        }
    }

    public void setTwoScreen() {
        switch (mLastFragment) {
            case MENU:
//                getViewState().showFragment(R.id.additional_container, MenuFragment.newInstance(), MenuFragment.TAG);
//                getViewState().showFragment(R.id.main_container, ItemListFragment.newInstance(), ItemListFragment.TAG);
//                break;
            case LIST:
                getViewState().showFragment(R.id.additional_container, MenuFragment.newInstance(), MenuFragment.TAG);
                getViewState().showFragment(R.id.main_container, ItemListFragment.newInstance(), ItemListFragment.TAG);
                break;
            case DETAIL:
                getViewState().showFragment(R.id.additional_container, ItemListFragment.newInstance(), ItemListFragment.TAG);
                break;
            default:
                break;
        }
    }

    private void switchFragment(Fragment fragment, String tag) {
        switch (tag) {
            case MenuFragment.TAG:
                mLastFragment = MENU;
                break;
            case ItemListFragment.TAG:
                mLastFragment = LIST;
                break;
            case HeroTabsFragment.TAG:
                mLastFragment = DETAIL;
                break;
            default:
                break;
        }
        getViewState().showFragment(R.id.main_container, fragment, fragment.getTag());
        if (mTwoPane) {
            setTwoScreen();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        bus.unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(FragmentEvent event) {
        switchFragment(event.getData(), event.getTag());
    }

}
