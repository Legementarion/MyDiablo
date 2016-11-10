package com.lego.mydiablo.presenter.fragment;

import com.arellomobile.mvp.MvpView;
import com.lego.mydiablo.rest.callback.models.UserData.UserHeroList;

public interface HeroTabsView extends MvpView {
    void addCompareFragments();

    void openPicker();
}
