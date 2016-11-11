package com.lego.mydiablo.presenter.fragment;

import android.support.v4.app.Fragment;
import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.lego.mydiablo.data.RealmDataController;
import com.lego.mydiablo.data.model.Hero;
import com.lego.mydiablo.events.FragmentEvent;
import com.lego.mydiablo.logic.Core;
import com.lego.mydiablo.view.fragments.ItemListFragment;

import org.greenrobot.eventbus.EventBus;

import rx.Subscriber;

import static com.lego.mydiablo.utils.Settings.mBattleTag;

@InjectViewState
public class HeroTabsPresenter extends MvpPresenter<HeroTabsView> {

    private Core mCore;

    private Hero mRatingHero;
    private Hero mUserHero;

    RealmDataController mRealmDataController;
    private EventBus mEventBus = EventBus.getDefault();

    HeroTabsPresenter() {
    }

    public void getHeroFromDB(Fragment fragment, int rank) {
        mRealmDataController = RealmDataController.with(fragment);
        mCore = Core.getInstance();
        if (rank != 0) {
            mRatingHero = mRealmDataController.getHero(rank);
            getViewState().setHeroAdapter(mRatingHero);
        }
    }

    public void compare() {
        getViewState().openPicker();
    }

    public void addTab(int userHeroId) {
        mCore.loadUserDetailHeroData(mBattleTag, userHeroId).subscribe(new Subscriber<Hero>() {
            @Override
            public void onCompleted() {
                unsubscribe();
            }

            @Override
            public void onError(Throwable e) {
                Log.d("addTab", "onError: " + e);
            }

            @Override
            public void onNext(Hero hero) {
                mUserHero = hero;
                Log.d("HeroTabsPresenter", "onNext:  ok");
                getViewState().addCompareFragments(mRatingHero, mUserHero);
            }
        });
    }

    public void backPress() {
        mEventBus.post(new FragmentEvent(ItemListFragment.newInstance(), ItemListFragment.TAG));
    }

}
