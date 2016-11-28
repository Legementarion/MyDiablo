package com.lego.mydiablo.logic;

import com.lego.mydiablo.data.DataBaseController;
import com.lego.mydiablo.data.RealmDataController;
import com.lego.mydiablo.data.model.Hero;
import com.lego.mydiablo.rest.RetrofitRequests;
import com.lego.mydiablo.rest.callback.models.UserData.UserHeroList;
import com.lego.mydiablo.rest.parser.HeroListParser;

import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.lego.mydiablo.utils.Settings.mBattleTag;
import static com.lego.mydiablo.utils.Settings.mCurrentLocale;

public class Core {
    private static Core mCore;
    private RetrofitRequests mServerRequest = RetrofitRequests.getInstance();
    private static DataBaseController mDataBaseController;
    private RealmDataController mRealmDataController;
    private HeroListParser mParser;

    private Core() {
        mParser = new HeroListParser();
        mRealmDataController = RealmDataController.getInstance();
    }

    public static Core getInstance(DataBaseController dataBaseController) {
        mDataBaseController = dataBaseController;
        return mCore == null ? (mCore = new Core()) : mCore;
    }

    public static Core getInstance() {
        return mCore == null ? (mCore = new Core()) : mCore;
    }

    public Observable<List<Hero>> loadHeroList(String heroClass, String heroSeason) {
        return mServerRequest.getEraLeaderTop(heroSeason, heroClass)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .flatMap(heroList -> mParser.parseData(heroList))
                .flatMap(heroes -> mDataBaseController.saveToDatabase(heroes));
    }

    public Observable<Hero> loadDetailHeroData(String battleTag, int heroId){
        return mParser.getTopHeroDetail(battleTag, heroId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .flatMap(heroDetail -> mParser.getItemsList(heroDetail));
    }

    public Observable<UserHeroList> loadUserHeroList(){
        return mServerRequest.getUserHeroList(mBattleTag, mCurrentLocale);
    }

    public Observable<Hero> loadUserDetailHeroData(String battleTag, int heroId){
        return mParser.getTopHeroDetail(battleTag, heroId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .flatMap(heroDetail -> mParser.setUserHeroToDB(heroDetail))
                .flatMap(heroDetail -> mParser.getItemsList(heroDetail));
    }

    public boolean checkHeroData(int rank){
        return mRealmDataController.getHero(rank).isLoadingProgress();
    }

}
