package com.lego.mydiablo.presenter.activity;

import android.net.Uri;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.lego.mydiablo.rest.AuthRequest;
import com.lego.mydiablo.rest.RetrofitRequests;
import com.lego.mydiablo.rest.callback.models.user.AccessToken;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.lego.mydiablo.utils.Const.AUTHORIZE_URI;
import static com.lego.mydiablo.utils.Const.CREDENTIALS;
import static com.lego.mydiablo.utils.Const.HTTP;
import static com.lego.mydiablo.utils.Const.REDIRECTION_URI;
import static com.lego.mydiablo.utils.Const.REDIRECT_URI;
import static com.lego.mydiablo.utils.Const.RESPONSE_TYPE;
import static com.lego.mydiablo.utils.Settings.mCurrentZone;
import static com.lego.mydiablo.utils.Settings.mToken;

@InjectViewState
public class LoginActivityPresenter extends MvpPresenter<LoginActivityView> {

    private RetrofitRequests mRetrofitRequests;

    public void logIn() {
        String authUrl = HTTP + mCurrentZone + AUTHORIZE_URI + RESPONSE_TYPE + CREDENTIALS + REDIRECTION_URI + REDIRECT_URI;
        WebViewClient webViewClient = new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url.matches("^https://localhost/.*")) {
                    signIn(url);
                    return true;                        //Indicates WebView to NOT load the url
                } else {
                    view.loadUrl(url);
                    return false;                       //Allow WebView to load url
                }
            }
        };
        getViewState().signIn(webViewClient, authUrl);
    }

    private void signIn(String url) {
        String mAuthCode;
        if (url.contains("?code=") || url.contains("&code=")) {
            Uri uri = Uri.parse(url);
            mAuthCode = uri.getQueryParameter("code");
            AuthRequest.getAccessToken(false, mAuthCode)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.newThread())
                    .subscribe(new Subscriber<AccessToken>() {
                        @Override
                        public void onCompleted() {
                            unsubscribe();
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.e("getAccessToken", e.toString());
                        }

                        @Override
                        public void onNext(AccessToken accessToken) {
                            mToken = accessToken.getAccess_token();
                            AuthRequest.getBattleTag();
                            mRetrofitRequests = RetrofitRequests.getInstance();
                            mRetrofitRequests.getEraCount();
                            mRetrofitRequests.getSeasonCount();
                            getViewState().closeAuth();
                        }
                    });
        }
    }

}
