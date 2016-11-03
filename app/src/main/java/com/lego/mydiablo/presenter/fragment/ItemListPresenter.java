package com.lego.mydiablo.presenter.fragment;

import android.content.Context;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.lego.mydiablo.R;
import com.lego.mydiablo.data.RealmDataController;
import com.lego.mydiablo.data.model.Hero;
import com.lego.mydiablo.events.FragmentEvent;
import com.lego.mydiablo.logic.Core;
import com.lego.mydiablo.view.adapters.SeasonAdapter;
import com.lego.mydiablo.view.fragments.MenuFragment;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import rx.Subscriber;

import static com.lego.mydiablo.utils.Const.SEASON;
import static com.lego.mydiablo.utils.Const.SIZE;
import static com.lego.mydiablo.utils.Settings.mCurrentEraList;
import static com.lego.mydiablo.utils.Settings.mCurrentSeasonList;
import static com.lego.mydiablo.utils.Settings.mItemsPerPage;
import static com.lego.mydiablo.utils.Settings.mMode;

@InjectViewState
public class ItemListPresenter extends MvpPresenter<ItemListView> {

    private Core mCore;
    private RealmDataController mRealmDataController;
    private EventBus bus = EventBus.getDefault();

    private boolean mDoubleQuery = true;
    private boolean mEmptyData = true;
    private int mPage;

    public void configure(Fragment fragment){
        mRealmDataController = RealmDataController.with(fragment);
        mCore = Core.getInstance(mRealmDataController);
    }

    public SeasonAdapter fillSeasonAdapterArrays(Context context) {
        if (mCurrentEraList != null && mCurrentSeasonList != null) {
            if (mMode != null && mMode.equals(SEASON)) {
                return new SeasonAdapter(context, R.layout.spinner, mCurrentSeasonList);
            } else {
                return new SeasonAdapter(context, R.layout.spinner, mCurrentEraList);
            }
        } else {
            return new SeasonAdapter(context, R.layout.spinner, context.getResources().getStringArray(R.array.season));
        }
    }

    public void loadDataHeroList(String heroClass, String season){
        if (mMode != null) {
            if (mDoubleQuery) {
                mPage = 0;
                mDoubleQuery = false;       //delay for api query
                if (mRealmDataController.getHeroList(heroClass, season) != null) {  //get data from db, if db !=null
                    mEmptyData = false;
                    getViewState().setupRecyclerView(mRealmDataController.getHeroList(heroClass, season));
                } else {
                    mEmptyData = true;
                }
                new Handler().postDelayed(() -> mDoubleQuery = true, 2000);
                mCore.doRequest(heroClass, season)
                        .cache()
                        .doOnSubscribe(() -> {getViewState().updateProgressBar(true); getViewState().blockUI();})
                        .doAfterTerminate(() -> {getViewState().updateProgressBar(false); getViewState().unBlockUI();})
                        .subscribe(new Subscriber<List<Hero>>() {
                            @Override
                            public void onCompleted() {
                                unsubscribe();
                            }
                            @Override
                            public void onError(Throwable e) {
                                Log.e("Request hero list", "onError: ", e);
                                if (e.getMessage().matches("40[1-3]{1}.*")) {
                                    Log.e("Request hero list", "onError: regex work fine");
                                }
                            }
                            @Override
                            public void onNext(List<Hero> heroList) {
                                if (mEmptyData) {
                                    getViewState().setupRecyclerView(heroList);
                                    mEmptyData = false;
                                } else {
                                    getViewState().setNewList(heroList);
                                }
                            }
                        });
            }
        }
    }

    public void backPress() {
        bus.post(new FragmentEvent(MenuFragment.newInstance(), MenuFragment.TAG));
    }

    public boolean loadedAllItems() {
        if (mEmptyData) {
            return mPage == 0;
        } else {
            return mPage == SIZE;
        }
    }

    public void gimmeMore(String heroClass, String season) {
        mPage += mItemsPerPage;
        getViewState().updateList(mRealmDataController.getNextHero(heroClass, season, mPage));
    }

}
