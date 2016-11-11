package com.lego.mydiablo.presenter.fragment;

import android.support.v4.app.Fragment;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.lego.mydiablo.data.RealmDataController;
import com.lego.mydiablo.data.model.Hero;

@InjectViewState
public class ItemDetailPresenter extends MvpPresenter<ItemDetailView> {

    private Hero mHero;

    public void setHero(Hero hero){
        mHero = hero;
        getViewState().fillData(mHero);
    }

}
