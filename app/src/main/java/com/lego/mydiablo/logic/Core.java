package com.lego.mydiablo.logic;

import com.lego.mydiablo.data.DataBaseController;
import com.lego.mydiablo.data.model.Hero;
import com.lego.mydiablo.rest.RetrofitRequests;
import com.lego.mydiablo.rest.parser.HeroListParser;

import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.lego.mydiablo.utils.Settings.mItemsPerPage;

/**
 * Created by Lego on 08.03.2016.
 */
public class Core {
    private static Core mCore;
    private RetrofitRequests mServerRequest = RetrofitRequests.getInstance();
    private static DataBaseController mDataBaseController;
    private HeroListParser mParser;

    private Core() {
        mParser = new HeroListParser();
    }

    public static Core getInstance(DataBaseController dataBaseController) {
        mDataBaseController = dataBaseController;
        return mCore == null ? (mCore = new Core()) : mCore;
    }

    public Observable<List<Hero>> doRequest(String heroClass, String heroSeason) {
        return mServerRequest.getEraLeaderTop(heroSeason, heroClass)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .flatMap(heroList -> mParser.parseData(heroList))
                .flatMap(heroes -> mDataBaseController.saveToDatabase(heroes))
                .doOnNext(heroList -> {
                    for (int i = 0; i < mItemsPerPage / 4; i++) {
                        mParser.getTopHeroDetail(heroList.get(i).getBattleTag(), heroList.get(i).getId());
                    }
                }).flatMap(heroList -> mDataBaseController.saveToDatabase(heroList));
    }

}
