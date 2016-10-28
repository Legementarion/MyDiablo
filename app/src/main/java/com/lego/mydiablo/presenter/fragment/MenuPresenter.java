package com.lego.mydiablo.presenter.fragment;

import android.view.View;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.lego.mydiablo.R;

import static com.lego.mydiablo.utils.Const.ERA;
import static com.lego.mydiablo.utils.Const.HARDCORE;
import static com.lego.mydiablo.utils.Const.NO_VALUE;
import static com.lego.mydiablo.utils.Const.SEASON;
import static com.lego.mydiablo.utils.Settings.mHARDCODE;
import static com.lego.mydiablo.utils.Settings.mMode;

@InjectViewState
public class MenuPresenter extends MvpPresenter<MenuView> {

    public void pressButton(View view) {
        int sChecked;
        switch (view.getId()) {
            case R.id.bt_normal:
                sChecked = 1;
                mMode = ERA;
                mHARDCODE = NO_VALUE;
                break;
            case R.id.bt_harcore:
                sChecked = 2;
                mMode = ERA;
                mHARDCODE = HARDCORE;
                break;
            case R.id.bt_season:
                sChecked = 3;
                mMode = SEASON;
                mHARDCODE = NO_VALUE;
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
    }
}
