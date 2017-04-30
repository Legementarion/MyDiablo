package com.lego.mydiablo.presenter.fragment;

import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.lego.mydiablo.data.RealmDataController;
import com.lego.mydiablo.data.model.Hero;
import com.lego.mydiablo.events.FragmentEvent;
import com.lego.mydiablo.logic.Core;
import com.lego.mydiablo.view.fragments.ItemListFragment;

import org.greenrobot.eventbus.EventBus;

import rx.Subscriber;

import static com.lego.mydiablo.utils.HeroUtils.castGender;
import static com.lego.mydiablo.utils.Settings.mBattleTag;

@InjectViewState
public class HeroTabsPresenter extends MvpPresenter<HeroTabsView> {

    private Core mCore;

    private Hero mRatingHero;
    private Hero mUserHero;

    private RealmDataController mRealmDataController;
    private EventBus mEventBus = EventBus.getDefault();

    public HeroTabsPresenter() {
    }

    public void getHeroFromDB(Fragment fragment, int rank) {
        mRealmDataController = RealmDataController.with(fragment);
        mCore = Core.getInstance();
        if (rank != 0) {
            mRatingHero = mRealmDataController.getHero(rank);
            getViewState().setHeroAdapter(mRatingHero);
        }
    }

    public void compare(int visibility) {
        if (visibility == View.GONE)
            getViewState().openPicker();
        else
            getViewState().closePicker();
    }

    public void addTab(int userHeroId) {
        mCore.loadUserDetailHeroData(mBattleTag, userHeroId)
                .cache()
                .doOnSubscribe(() -> getViewState().showUserProgressBar())
                .doAfterTerminate(() -> getViewState().hideUserProgressBar())
                .subscribe(new Subscriber<Hero>() {
                    @Override
                    public void onCompleted() {
                        unsubscribe();
                    }

                    @Override
                    public void onError(Throwable e) {
                        getViewState().hideUserProgressBar();
                        Log.d("addTab", "onError: " + e);
                    }

                    @Override
                    public void onNext(Hero hero) {
                        mUserHero = hero;
                        getViewState().addCompareFragments(mRatingHero, mUserHero);
                    }
                });
    }

    public void backPress() {
        mEventBus.post(new FragmentEvent(ItemListFragment.newInstance(), ItemListFragment.TAG));
    }

    public String getHeroIcon() {
        if (mRatingHero != null) {
            return mRatingHero.getHeroClass() + "_" + castGender(mRatingHero.getGender());
        } else {
            return "avatar";
        }
    }

    public String getUserHeroIcon() {
        if (mUserHero != null) {
            return mUserHero.getHeroClass() + "_" + castGender(mRatingHero.getGender());
        } else {
            return "avatar";
        }
    }

    public String getResultIcon() {
        return "avatar";
    }

}
