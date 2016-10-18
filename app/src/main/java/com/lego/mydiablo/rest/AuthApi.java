package com.lego.mydiablo.rest;

/**
 * @author Lego on 13.05.2016.
 */

import com.lego.mydiablo.rest.callback.AccessToken;
import com.lego.mydiablo.rest.callback.CheckedToken;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;


public interface AuthApi {

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
    Call<CheckedToken> checkToken(
            @Field("token") String token
    );
}
