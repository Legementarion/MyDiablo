package com.lego.mydiablo.presenter.activity;

import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;

import com.arellomobile.mvp.MvpView;

public interface DiabloView extends MvpView {
    void openAuthDialog();

    void closeAuthDialog();

    void showFragment(@IdRes int containerViewID, Fragment fragment, String tag);
}
