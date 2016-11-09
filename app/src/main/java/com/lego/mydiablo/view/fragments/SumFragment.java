package com.lego.mydiablo.view.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.lego.mydiablo.presenter.fragment.SumFragmentPresenter;
import com.lego.mydiablo.presenter.fragment.SumFragmentView;

public class SumFragment extends MvpAppCompatFragment implements SumFragmentView{

    @InjectPresenter
    SumFragmentPresenter mSumFragmentPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}

