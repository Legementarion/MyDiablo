package com.lego.mydiablo.view.activity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.lego.mydiablo.R;
import com.lego.mydiablo.presenter.activity.LoginActivityPresenter;
import com.lego.mydiablo.presenter.activity.LoginActivityView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class LoginActivity extends MvpAppCompatActivity implements LoginActivityView {

    @BindView(R.id.wvOauth)
    WebView mWebView;

    private Unbinder mUnbinder;

    @InjectPresenter
    LoginActivityPresenter mLoginActivityPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_auth);
        mUnbinder = ButterKnife.bind(this);
        mLoginActivityPresenter.logIn();
    }

    @Override
    public void signIn(WebViewClient webViewClient, String authUrl) {
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebViewClient(webViewClient);
        mWebView.loadUrl(authUrl);
    }

    @Override
    public void closeAuth(){
        finish();
    }

    @Override
    protected void onDestroy() {
        mUnbinder.unbind();
        super.onDestroy();
    }
}
