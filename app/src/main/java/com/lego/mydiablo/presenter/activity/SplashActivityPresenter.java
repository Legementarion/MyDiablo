package com.lego.mydiablo.presenter.activity;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.lego.mydiablo.R;

import static com.lego.mydiablo.utils.Settings.mCurrentZone;

@InjectViewState
public class SplashActivityPresenter extends MvpPresenter<SplashActivityView> {

    public void setRegion(int region) {
        switch (region){
            case R.id.eu:
                mCurrentZone = "eu";
                break;
            case R.id.us:
                mCurrentZone = "us";
                break;
            case R.id.tw:
                mCurrentZone = "tw";
                break;
            case R.id.kr:
                mCurrentZone = "kr";
                break;
        }
        getViewState().startAuth();
    }

}
