package com.lego.mydiablo.presenter.fragment;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.lego.mydiablo.events.FragmentEvent;
import com.lego.mydiablo.view.fragments.ItemListFragment;

import org.greenrobot.eventbus.EventBus;


@InjectViewState
public class HeroTabsPresenter extends MvpPresenter<HeroTabsView> {

    private EventBus mEventBus = EventBus.getDefault();

    HeroTabsPresenter() {
    }

    public void compare() {
        getViewState().openPicker();
    }

    public void addTab(int userHeroId){
        getViewState().addCompareFragments();
    }

    public void backPress() {
        mEventBus.post(new FragmentEvent(ItemListFragment.newInstance(), ItemListFragment.TAG));
    }

}
