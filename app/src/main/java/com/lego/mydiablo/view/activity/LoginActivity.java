package com.lego.mydiablo.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.PresenterType;
import com.lego.mydiablo.R;
import com.lego.mydiablo.presenter.activity.LoginActivityPresenter;
import com.lego.mydiablo.presenter.activity.LoginActivityView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.lego.mydiablo.R.string.internet_connection;

public class LoginActivity extends MvpAppCompatActivity implements LoginActivityView {

    @BindView(R.id.wvOauth)
    WebView mWebView;
    @BindView(R.id.auth_progressBar)
    ProgressBar mProgressBar;

    private Unbinder mUnbinder;
    public static final String TAG_AUTH = "auth";
    public static final int AUTH_CODE = 200;

    @InjectPresenter(type = PresenterType.LOCAL)
    LoginActivityPresenter mLoginActivityPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
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
    public void closeAuth() {
        Intent intent = new Intent(this, DiabloActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivityForResult(intent, AUTH_CODE);
    }

    @Override
    public void authError() {
        Toast.makeText(this, internet_connection, Toast.LENGTH_SHORT).show();
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, SplashActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }

    @Override
    public void hideProgress() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    protected void onDestroy() {
        mUnbinder.unbind();
        super.onDestroy();
    }
}
