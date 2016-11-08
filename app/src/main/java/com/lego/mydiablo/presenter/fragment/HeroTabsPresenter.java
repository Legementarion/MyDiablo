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

    private EventBus mEventBus = EventBus.getDefault();



}
