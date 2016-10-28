package com.lego.mydiablo.rest;

/**
 * @author Lego on 13.05.2016.
 */

import com.lego.mydiablo.rest.callback.models.UserData.AccessToken;
import com.lego.mydiablo.rest.callback.models.UserData.CheckedToken;
import com.lego.mydiablo.rest.callback.models.UserData.UserTag;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Url;
import rx.Observable;

interface AuthApi {

    @FormUrlEncoded
    @POST("/oauth/token")
    Observable<AccessToken> obtainAccessToken(
            @Field("grant_type") String grantType,
            @Field("code") String code,
            @Field("redirect_uri") String redirectUri,
            @Field("scope") String scope
    );

    @FormUrlEncoded
    @POST("/oauth/check_token")
    Observable<CheckedToken> checkToken(
            @Field("token") String token
    );

    @GET
    Call<UserTag> getTag(
            @Url String url,
            @Query("access_token") String token
    );

}
