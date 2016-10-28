package com.lego.mydiablo.rest;

import com.lego.mydiablo.rest.callback.models.GameData.Era;
import com.lego.mydiablo.rest.callback.models.GameData.Season;
import com.lego.mydiablo.rest.callback.models.HeroDetail.HeroDetail;
import com.lego.mydiablo.rest.callback.models.HeroList.HeroList;
import com.lego.mydiablo.rest.callback.models.Item.Item;

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
    Observable<Item> getItem(
            @Url String url,
            @Query("locale") String locale,
            @Query("apikey") String apikey
    );

}
