package com.lego.mydiablo.presenter.fragment;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.lego.mydiablo.data.model.Hero;

public interface HeroTabsView extends MvpView {
    void addCompareFragments(Hero hero, Hero userHero);

    @StateStrategyType(SkipStrategy.class)
    void openPicker();

    @StateStrategyType(SkipStrategy.class)
    void closePicker();

    void setHeroAdapter(Hero hero);

    void showUserProgressBar();

    void hideUserProgressBar();
}
