package com.lego.mydiablo.logic;

import android.app.Activity;
import android.app.Application;
import android.os.Handler;
import android.os.Looper;

import com.lego.mydiablo.data.DataBaseController;
import com.lego.mydiablo.data.model.Hero;
import com.lego.mydiablo.rest.RetrofitRequests;
import com.lego.mydiablo.rest.parser.HeroListParser;

import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.lego.mydiablo.utils.Const.SIZE;
import static com.lego.mydiablo.utils.Settings.mItemsPerPage;

/**
 * Created by Lego on 08.03.2016.
 */
public class Core {
    private static Core mCore;
    private RetrofitRequests mServerRequest = RetrofitRequests.getInstance();
    private ProgressBar mProgressBar;
    private static DataBaseController mDataBaseController;
    private HeroListParser mParser;

    private Core(Application application) {
        mParser = new HeroListParser(this);
    }

    public void forCheckState(ProgressBar progressBar) {
        this.mProgressBar = progressBar;
    }

    public static Core getInstance(Activity activity, DataBaseController dataBaseController) {
        mDataBaseController = dataBaseController;
        return mCore == null ? (mCore = new Core(activity.getApplication())) : mCore;
    }

    public Observable<List<Hero>> doRequest(String heroClass, String heroSeason) {
        return mServerRequest.getSeasonLeaderTop(heroSeason, heroClass)
                .flatMap(heroList -> mParser.parseData(heroList))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .flatMap(heroes -> mDataBaseController.saveToDatabase(heroes))
                .doOnNext(heroList -> {
                    for (int i = 0; i < mItemsPerPage / 4; i++) {
                        mParser.getTopHeroDetail(heroList.get(i).getmBattleTag(), heroList.get(i).getId());
                    }
                }).flatMap(heroList -> mDataBaseController.saveToDatabase(heroList));
    }

    public void updateProgressBar(int value) {
        new Handler(Looper.getMainLooper()).post(() -> mProgressBar.updateProgressBar(value, SIZE));    //execute from main thread
    }
}
