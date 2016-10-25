package com.lego.mydiablo.rest;


import android.util.Log;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lego.mydiablo.rest.callback.models.GameData.Era;
import com.lego.mydiablo.rest.callback.models.GameData.Season;
import com.lego.mydiablo.rest.callback.models.HeroDetail.HeroDetail;
import com.lego.mydiablo.rest.callback.models.HeroList.HeroList;
import com.lego.mydiablo.rest.callback.models.Item.Item;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;
import rx.Observable;
import rx.schedulers.Schedulers;

import static com.lego.mydiablo.utils.Const.BASE_URL_API;
import static com.lego.mydiablo.utils.Const.CLIENT_ID;
import static com.lego.mydiablo.utils.Const.D3;
import static com.lego.mydiablo.utils.Const.D3_PROFILE;
import static com.lego.mydiablo.utils.Const.DATA_PATH;
import static com.lego.mydiablo.utils.Const.HARDCORE;
import static com.lego.mydiablo.utils.Const.HERO;
import static com.lego.mydiablo.utils.Const.LEADERBOARD_RIFT;
import static com.lego.mydiablo.utils.Settings.mCurrentEraList;
import static com.lego.mydiablo.utils.Settings.mCurrentSeasonList;
import static com.lego.mydiablo.utils.Settings.mMode;
import static com.lego.mydiablo.utils.Settings.mToken;

public class RetrofitRequests {

    private static RetrofitRequests instance;
    private BlizzardApi api;

    private RetrofitRequests() {
        create();
    }

    public static RetrofitRequests getInstance() {
        return instance == null ? (instance = new RetrofitRequests()) : instance;
    }

    private void create() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(DeserializationFeature.FAIL_ON_READING_DUP_TREE_KEY);

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        RxJavaCallAdapterFactory rxAdapter = RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io());

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                .addInterceptor(chain -> {
                    Request original = chain.request();
                    Request.Builder requestBuilder = original.newBuilder()
                            .method(original.method(), original.body());
                    Request request = requestBuilder.build();
                    return chain.proceed(request);
                })
                .addInterceptor(logging);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL_API)
                .client(httpClient.build())
                .addConverterFactory(JacksonConverterFactory.create(objectMapper))
                .addCallAdapterFactory(rxAdapter)
                .build();
        api = retrofit.create(BlizzardApi.class);
    }

    public void getSeasonCount() {
        Call<Season> seasonCall = api.checkSeasons(mToken);
        seasonCall.enqueue(new Callback<Season>() {
            @Override
            public void onResponse(Call<Season> call, Response<Season> response) {
                if (response.isSuccessful()) {
                    mCurrentSeasonList = new String[response.body().getCurrent_season()];
                    for (int i = 0; i < response.body().getCurrent_season(); i++) {
                        mCurrentSeasonList[i] = String.valueOf(response.body().getCurrent_season() - i);
                    }
                }
            }

            @Override
            public void onFailure(Call<Season> call, Throwable t) {
                Log.d("Season Error", "onFailure: " + t.getMessage());
            }
        });
    }

    public void getEraCount() {
        Call<Era> eraCall = api.checkEras(mToken);
        eraCall.enqueue(new Callback<Era>() {
            @Override
            public void onResponse(Call<Era> call, Response<Era> response) {
                if (response.isSuccessful()) {
                    mCurrentEraList = new String[response.body().getCurrent_era()];
                    for (int i = 0; i < response.body().getCurrent_era(); i++) {
                        mCurrentEraList[i] = String.valueOf(response.body().getCurrent_era() - i);
                    }
                }
            }

            @Override
            public void onFailure(Call<Era> call, Throwable t) {
                Log.d("Era Error", "onFailure: " + t.getMessage());
            }
        });
    }

    public Observable<HeroList> getEraLeaderTop(final String season, final String heroClass) {
        return api.getHeroBoard(BASE_URL_API + DATA_PATH + D3 + mMode + season + LEADERBOARD_RIFT + HARDCORE + heroClass, mToken);
    }


    public Observable<HeroDetail> getHero(final String battleTag, final int heroId, final String locale) {
        return api.getHero(BASE_URL_API + D3 + D3_PROFILE + battleTag + HERO + heroId, locale, CLIENT_ID);
    }

    public Observable<Item> getItem(final String data, final String locale) {
        return api.getItem(BASE_URL_API + D3 + DATA_PATH + data, locale, CLIENT_ID);
    }

}
