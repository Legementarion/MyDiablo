package com.lego.mydiablo.presenter.activity;

import android.webkit.WebViewClient;

import com.arellomobile.mvp.MvpView;

public interface LoginActivityView extends MvpView {
    void signIn(WebViewClient webViewClient, String authUrl);

    void closeAuth(int authCode);

    void hideProgress();
}
