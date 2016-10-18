package com.lego.mydiablo.rest;

import com.lego.mydiablo.rest.callback.models.HeroDetail.HeroDetail;
import com.lego.mydiablo.rest.callback.models.HeroList.HeroList;
import com.lego.mydiablo.rest.callback.models.Item.Item;
import com.lego.mydiablo.rest.callback.models.SeasonList;

import retrofit2.Call;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Url;
import rx.Observable;

public interface BlizzardApi {

    @FormUrlEncoded
    @POST("/data/d3/season/")
    Call<SeasonList> checkSeasons();

    @GET
    Observable<HeroList> getSeasonHeroBoard(
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
