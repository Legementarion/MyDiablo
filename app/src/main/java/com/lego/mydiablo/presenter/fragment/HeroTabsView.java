package com.lego.mydiablo.presenter.fragment;

import com.arellomobile.mvp.MvpView;
import com.lego.mydiablo.data.model.Hero;

public interface HeroTabsView extends MvpView {
    void addCompareFragments(Hero hero, Hero userHero);

    void openPicker();

    void setHeroAdapter(Hero hero);

    void showUserProgressBar();

    void hideUserProgressBar();
}
