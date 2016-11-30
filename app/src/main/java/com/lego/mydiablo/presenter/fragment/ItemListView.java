package com.lego.mydiablo.presenter.fragment;

import com.arellomobile.mvp.MvpView;
import com.lego.mydiablo.data.model.Hero;

import java.util.List;

public interface ItemListView extends MvpView {
    void updateProgressBar(boolean state);

    void setupRecyclerView(List<Hero> heroList);

    void setNewList(List<Hero> heroList);

    void updateList(List<Hero> heroList);

    void blockUI();

    void unBlockUI();

    void showProgress(String message);

    void hideProgress();

    void setSpinnerPositions(int heroPos, int seasonPos);
}
