package com.lego.mydiablo.presenter.fragment;

import android.support.v4.app.Fragment;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.lego.mydiablo.data.RealmDataController;
import com.lego.mydiablo.data.model.Hero;

@InjectViewState
public class ItemDetailPresenter extends MvpPresenter<ItemDetailView> {

    private RealmDataController mRealmDataController;

    private Hero mHero;
    private int mHeroRank = 0;

    public void getHeroFromDB(Fragment fragment, int rank) {
        mHeroRank = rank;
        mRealmDataController = RealmDataController.with(fragment);

        if (mHeroRank != 0) {
            mHero = mRealmDataController.getHero(mHeroRank);
            getViewState().fillData(mHero);
        }
    }
}
