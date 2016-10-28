package com.lego.mydiablo.presenter.activity;

import com.arellomobile.mvp.MvpView;

public interface DiabloView extends MvpView {
    void openAuthDialog();
    void closeAuthDialog();
}
