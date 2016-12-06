package com.lego.mydiablo.rest;

import com.lego.mydiablo.rest.callback.models.game.Era;
import com.lego.mydiablo.rest.callback.models.game.Season;
import com.lego.mydiablo.rest.callback.models.heroes.HeroDetail;
import com.lego.mydiablo.rest.callback.models.leaderboard.HeroList;
import com.lego.mydiablo.rest.callback.models.user.UserHeroList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;
import rx.Observable;

interface BlizzardApi {

    @GET("/data/d3/season/")
    Call<Season> checkSeasons(
            @Query("access_token") String token
    );

    @GET("/data/d3/era/")
    Call<Era> checkEras(
            @Query("access_token") String token
    );

    @GET
    Observable<HeroList> getHeroBoard(
            @Url String url,
            @Query("access_token") String token
    );

    @GET
    Observable<HeroDetail> getHero(
            @Url String url,
            @Query("locale") String locale,
            @Query("apikey") String apikey
    );

    @GET
    Observable<UserHeroList> getUserHeroList(
            @Url String url,
            @Query("locale") String locale,
            @Query("apikey") String apikey
    );
}
