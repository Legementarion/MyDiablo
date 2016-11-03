package com.lego.mydiablo.presenter.fragment;

import android.support.v4.app.Fragment;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.lego.mydiablo.data.RealmDataController;
import com.lego.mydiablo.data.model.Hero;
import com.lego.mydiablo.logic.Core;

import org.greenrobot.eventbus.EventBus;

@InjectViewState
public class HeroTabsPresenter extends MvpPresenter<HeroTabsView> {

    private Core mCore;
    private RealmDataController mRealmDataController;
    private EventBus mEventBus = EventBus.getDefault();

    private Hero mHero;

    private int mHeroId = 0;

    public void getHeroFromDB(Fragment fragment, int id) {
        mHeroId = id;
        mRealmDataController = RealmDataController.with(fragment);

        if (mHeroId != 0) {
            mHero = mRealmDataController.getHero(mHeroId);
        }
    }
}
