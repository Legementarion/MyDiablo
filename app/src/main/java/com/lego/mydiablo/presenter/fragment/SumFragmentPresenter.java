package com.lego.mydiablo.presenter.fragment;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.lego.mydiablo.data.model.Hero;
import com.lego.mydiablo.logic.Calc;

import java.util.List;

@InjectViewState
public class SumFragmentPresenter extends MvpPresenter<SumFragmentView> {

    public void calcDifference(Hero hero, Hero userHero) {
        getViewState().setDiff(Calc.compareHeroes(hero, userHero));
    }

}
