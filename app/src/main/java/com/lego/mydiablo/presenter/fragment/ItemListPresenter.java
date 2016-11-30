package com.lego.mydiablo.presenter.fragment;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.lego.mydiablo.R;
import com.lego.mydiablo.data.RealmDataController;
import com.lego.mydiablo.data.model.Hero;
import com.lego.mydiablo.events.AuthEvent;
import com.lego.mydiablo.events.FragmentEvent;
import com.lego.mydiablo.logic.Core;
import com.lego.mydiablo.view.adapters.spinners.SeasonAdapter;
import com.lego.mydiablo.view.fragments.MenuFragment;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import rx.Subscriber;

import static com.lego.mydiablo.utils.Const.EMPTY_VALUE;
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

    private String mCurrentSeason = EMPTY_VALUE;
    private String mCurrentClass = EMPTY_VALUE;

    private boolean mEmptyData = true;
    private int mPage;

    @Override
    public void attachView(ItemListView view) {
        super.attachView(view);
        mPage = 0;
    }

    public void configure(Fragment fragment) {
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

    @StateStrategyType(SkipStrategy.class)
    public void loadDataHeroList(String heroClass, String season) {
        if (!mCurrentClass.equals(heroClass) || !mCurrentSeason.equals(season)) {
            mCurrentSeason = season;
            mCurrentClass = heroClass;
            mEmptyData = true;
            if (mMode != null) {
                mPage = 0;
                if (!mRealmDataController.getHeroList(heroClass, season).isEmpty()) {  //get data from db, if db !=null
                    mEmptyData = false;
                    getViewState().setupRecyclerView(mRealmDataController.getHeroList(heroClass, season));
                    mPage = 20;
                } else {
                    mEmptyData = true;
                    load(heroClass, season);
                }
            }
        }
    }

    public void load(String heroClass, String season) {
        mCore.loadHeroList(heroClass, season)
                .doOnSubscribe(() -> {
                    getViewState().updateProgressBar(true);
                    getViewState().blockUI();
                })
                .doAfterTerminate(() -> {
                    getViewState().updateProgressBar(false);
                    getViewState().unBlockUI();
                })
                .subscribe(new Subscriber<List<Hero>>() {
                    @Override
                    public void onCompleted() {
                        unsubscribe();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("Request hero list", "onError: ", e);
                        if (e.getMessage().matches("40[1]{1}.*")) {
                            bus.post(new AuthEvent());
                            Log.e("Request hero list", "onError: regex work fine");
                        } else {
                            // TODO error message
                        }
                    }

                    @Override
                    public void onNext(List<Hero> heroList) {
                        mPage = 20;
                        if (mEmptyData) {
                            getViewState().setupRecyclerView(heroList);
                            mEmptyData = false;
                        } else {
                            getViewState().setNewList(heroList);
                        }
                        loadDetailData(heroList);
                    }
                });
    }

    private void loadDetailData(List<Hero> heroList) {
        for (int i = 0; i < (mItemsPerPage / 4); i++) {
            if (heroList.get(i).getBattleTag() != null && !heroList.get(i).getBattleTag().equals(EMPTY_VALUE)) {
                mCore.loadDetailHeroData(heroList.get(i).getBattleTag(), heroList.get(i).getId())
                        .subscribe(new Subscriber<Hero>() {
                            @Override
                            public void onCompleted() {
                                unsubscribe();
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.e("load Detail Data", "onError: ", e);
                            }

                            @Override
                            public void onNext(Hero hero) {
                                Log.d("Core", "onNext: " + hero.getBattleTag());
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

    public void showProgressDialog() {
        getViewState().showProgress("please wait...");
    }

    public void hideProgressDialog() {
        getViewState().hideProgress();
    }

}
