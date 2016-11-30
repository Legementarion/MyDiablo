package com.lego.mydiablo.presenter.fragment;

import android.widget.ExpandableListAdapter;

import com.arellomobile.mvp.MvpView;

import java.util.List;

public interface ItemDetailView extends MvpView {
    void fillData(ExpandableListAdapter expandablePlayerListAdapter);

    void setupRV(List<String> general);
}
