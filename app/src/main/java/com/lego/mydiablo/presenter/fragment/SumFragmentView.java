package com.lego.mydiablo.presenter.fragment;

import com.arellomobile.mvp.MvpView;

import java.util.List;

public interface SumFragmentView extends MvpView {
    void setDiff(List<String> diffList);
}
