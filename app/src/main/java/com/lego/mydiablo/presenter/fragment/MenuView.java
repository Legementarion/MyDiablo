package com.lego.mydiablo.presenter.fragment;

import com.arellomobile.mvp.MvpView;

public interface MenuView extends MvpView {
    void updatePressButton(int i);

    void showTab();

    void blockUI();

    void unBlockUI();

    void setRegionPosition(int region);
}
