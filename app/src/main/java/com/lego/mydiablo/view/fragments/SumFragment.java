package com.lego.mydiablo.view.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.lego.mydiablo.R;
import com.lego.mydiablo.data.model.Hero;
import com.lego.mydiablo.presenter.fragment.SumFragmentPresenter;
import com.lego.mydiablo.presenter.fragment.SumFragmentView;

import java.util.List;

public class SumFragment extends MvpAppCompatFragment implements SumFragmentView{

    @InjectPresenter
    SumFragmentPresenter mSumFragmentPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_item_detail, container, false);
        return mView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public void compare(Hero hero, Hero userHero) {
        mSumFragmentPresenter.calcDifference(hero, userHero);
    }

    @Override
    public void setDiff(List<String> diffList) {

    }
}

