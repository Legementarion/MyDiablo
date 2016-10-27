package com.lego.mydiablo.presenter.fragment;

import com.arellomobile.mvp.MvpView;
@FunctionalInterface
public interface MenuView extends MvpView {
    void updatePressButton(int i);
}
