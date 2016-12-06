package com.lego.mydiablo.rest;

import com.lego.mydiablo.rest.callback.models.item.ResponseItem;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

@FunctionalInterface
interface BlizzardApiSync {

    @GET
    Call<ResponseItem> getItem(
            @Url String url,
            @Query("locale") String locale,
            @Query("apikey") String apikey
    );

}
