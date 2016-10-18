package com.lego.mydiablo.rest;

import com.lego.mydiablo.rest.callback.models.HeroDetail.HeroDetail;
import com.lego.mydiablo.rest.callback.models.HeroList.HeroList;
import com.lego.mydiablo.rest.callback.models.Item.Item;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.schedulers.Schedulers;

import static com.lego.mydiablo.utils.Const.BASE_URL_API;
import static com.lego.mydiablo.utils.Const.CLIENT_ID;
import static com.lego.mydiablo.utils.Const.D3;
import static com.lego.mydiablo.utils.Const.D3_PROFILE;
import static com.lego.mydiablo.utils.Const.DATA_PATH;
import static com.lego.mydiablo.utils.Const.HERO;
import static com.lego.mydiablo.utils.Const.HERO_BOARD_PATH;
import static com.lego.mydiablo.utils.Const.LEADERBOARD_RIFT;
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
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(rxAdapter)
                .build();
        api = retrofit.create(BlizzardApi.class);
    }


    public Observable<HeroList> getSeasonLeaderTop(final String season, final String heroClass) {
        return api.getSeasonHeroBoard(BASE_URL_API + DATA_PATH + D3 + HERO_BOARD_PATH + season + LEADERBOARD_RIFT + heroClass, mToken);
    }

    public Observable<HeroDetail> getHero(final String battleTag, final int heroId, final String locale) {
        return api.getHero(BASE_URL_API + D3 + D3_PROFILE + battleTag + HERO + heroId, locale, CLIENT_ID);
    }

    public Observable<Item> getItem(final String data, final String locale) {
        return api.getItem(BASE_URL_API + D3 + DATA_PATH + data, locale, CLIENT_ID);
    }
}
