package com.lego.mydiablo.presenter.fragment;

import android.widget.ExpandableListAdapter;

import com.arellomobile.mvp.MvpView;

public interface ItemDetailView extends MvpView {
    void fillData(ExpandableListAdapter expandablePlayerListAdapter);
}
