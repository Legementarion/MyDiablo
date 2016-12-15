package com.lego.mydiablo.presenter.fragment;

import android.view.View;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.lego.mydiablo.R;
import com.lego.mydiablo.events.AuthEvent;
import com.lego.mydiablo.events.FragmentEvent;
import com.lego.mydiablo.rest.RetrofitRequests;
import com.lego.mydiablo.view.fragments.ItemListFragment;

import org.greenrobot.eventbus.EventBus;

import static com.lego.mydiablo.utils.Const.ERA;
import static com.lego.mydiablo.utils.Const.HARDCORE;
import static com.lego.mydiablo.utils.Const.EMPTY_VALUE;
import static com.lego.mydiablo.utils.Const.SEASON;
import static com.lego.mydiablo.utils.Settings.mCurrentZone;
import static com.lego.mydiablo.utils.Settings.mHARDCODE;
import static com.lego.mydiablo.utils.Settings.mMode;

@InjectViewState
public class MenuPresenter extends MvpPresenter<MenuView> {

    private EventBus bus = EventBus.getDefault();

    public void pressButton(View view) {
        int sChecked;
        switch (view.getId()) {
            case R.id.bt_normal:
                sChecked = 1;
                mMode = ERA;
                mHARDCODE = EMPTY_VALUE;
                break;
            case R.id.bt_harcore:
                sChecked = 2;
                mMode = ERA;
                mHARDCODE = HARDCORE;
                break;
            case R.id.bt_season:
                sChecked = 3;
                mMode = SEASON;
                mHARDCODE = EMPTY_VALUE;
                break;
            case R.id.bt_season_hardcore:
                sChecked = 4;
                mMode = SEASON;
                mHARDCODE = HARDCORE;
                break;
            default:
                sChecked = 0; //dummy
                break;
        }
        getViewState().updatePressButton(sChecked);
        bus.post(new FragmentEvent(ItemListFragment.newInstance(), ItemListFragment.TAG));
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getViewState().showTab();
    }
}
