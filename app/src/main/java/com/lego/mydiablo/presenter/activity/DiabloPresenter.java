package com.lego.mydiablo.presenter.activity;

import android.content.res.Configuration;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.lego.mydiablo.R;
import com.lego.mydiablo.events.AuthEvent;
import com.lego.mydiablo.events.FragmentEvent;
import com.lego.mydiablo.rest.AuthRequest;
import com.lego.mydiablo.rest.callback.models.user.CheckedToken;
import com.lego.mydiablo.utils.LastFragment;
import com.lego.mydiablo.view.activity.DiabloActivity;
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
import static com.lego.mydiablo.utils.Settings.mHeroId;
import static com.lego.mydiablo.utils.Settings.mToken;
import static com.lego.mydiablo.utils.Settings.mTwoPane;

@InjectViewState
public class DiabloPresenter extends MvpPresenter<DiabloView> {

    private EventBus bus = EventBus.getDefault();
    private LastFragment mLastFragment;

    public DiabloPresenter() {
        bus.register(this);
    }

    @Override
    public void attachView(DiabloView view) {
        super.attachView(view);
        if (!TextUtils.isEmpty(mToken)) {
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
//                        getViewState().openAuthDialog();
                        Log.d("Check Session", "onError: " + e);
                    }

                    @Override
                    public void onNext(CheckedToken checkedToken) {
                        if (checkedToken.getError() != null) {
//                            getViewState().openAuthDialog();
                        }
                    }
                });
    }

    public void startConfig(DiabloActivity activity) {
        if (activity.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            mTwoPane = true;
            mLastFragment = LIST;
            setTwoScreen();
        } else {
            getViewState().showFragment(R.id.main_container, MenuFragment.newInstance(), MenuFragment.TAG);
            mLastFragment = MENU;
        }
    }

    public void restoreScreen() {
        getViewState().checkOrientation();
        if (mTwoPane) {
            setTwoScreen();
        } else {
            switch (mLastFragment) {
                case MENU:
                    getViewState().showFragment(R.id.main_container, MenuFragment.newInstance(), MenuFragment.TAG);
                    break;
                case LIST:
                    getViewState().showFragment(R.id.main_container, ItemListFragment.newInstance(), ItemListFragment.TAG);
                    break;
                case DETAIL:
                    getViewState().showFragment(R.id.main_container, HeroTabsFragment.newInstance(mHeroId), HeroTabsFragment.TAG);
                    break;
                default:
                    break;
            }
        }
    }

    private void setTwoScreen() {
        switch (mLastFragment) {
            case MENU:
            case LIST:
                getViewState().showFragment(R.id.additional_container, MenuFragment.newInstance(), MenuFragment.TAG);
                getViewState().showFragment(R.id.main_container, ItemListFragment.newInstance(), ItemListFragment.TAG);
                break;
            case DETAIL:
                getViewState().showFragment(R.id.additional_container, ItemListFragment.newInstance(), ItemListFragment.TAG);
                getViewState().showFragment(R.id.main_container, HeroTabsFragment.newInstance(mHeroId), HeroTabsFragment.TAG);
                break;
            default:
                break;
        }
    }

    private void switchFragment(Fragment fragment, String tag) {
        /**проверка на количество фрагментов на экране */
        getViewState().checkOrientation();
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
        if (mTwoPane) {
            setTwoScreen();
        } else {
            getViewState().showFragment(R.id.main_container, fragment, fragment.getTag());
        }
    }

    @Override
    public void onDestroy() {
        bus.unregister(this);
        super.onDestroy();
    }

    @SuppressWarnings("unused")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onFragmentSwitch(FragmentEvent event) {
        switchFragment(event.getData(), event.getTag());
    }

    @SuppressWarnings("unused")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onAuth(AuthEvent event) {
        checkSession();
    }

}
