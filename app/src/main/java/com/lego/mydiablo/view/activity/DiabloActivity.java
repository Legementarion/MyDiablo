package com.lego.mydiablo.view.activity;

import android.app.Dialog;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.IdRes;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.PresenterType;
import com.lego.mydiablo.R;

import com.lego.mydiablo.presenter.activity.DiabloPresenter;
import com.lego.mydiablo.presenter.activity.DiabloView;
import com.lego.mydiablo.utils.Settings;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.lego.mydiablo.utils.Const.AUTHORIZE_URI;
import static com.lego.mydiablo.utils.Const.CREDENTIALS;
import static com.lego.mydiablo.utils.Const.HTTP;
import static com.lego.mydiablo.utils.Const.REDIRECTION_URI;
import static com.lego.mydiablo.utils.Const.REDIRECT_URI;
import static com.lego.mydiablo.utils.Const.RESPONSE_TYPE;
import static com.lego.mydiablo.utils.Settings.mCurrentLocale;
import static com.lego.mydiablo.utils.Settings.mCurrentZone;

public class DiabloActivity extends MvpAppCompatActivity implements DiabloView {

    @InjectPresenter(type = PresenterType.WEAK)
    DiabloPresenter mDiabloPresenter;

    @BindView(R.id.main_container)
    FrameLayout mContainer;

    private Unbinder mUnbinder;
    private Dialog mAuthDialog;
    private FragmentManager mFragmentManager;

    private String authUrl;
    private boolean doubleBackToExitPressedOnce;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diablo);
        mUnbinder = ButterKnife.bind(this);
        mCurrentLocale = Resources.getSystem().getConfiguration().locale.toString();
        mFragmentManager = getSupportFragmentManager();
        mDiabloPresenter.startConfig(this);
    }

    public void prepareSignIn() {
        authUrl = HTTP + mCurrentZone + AUTHORIZE_URI + RESPONSE_TYPE + CREDENTIALS + REDIRECTION_URI + REDIRECT_URI;
        Log.d("!!!!!!!!!!!!!", "prepareSignIn: " + authUrl);
        if (mAuthDialog == null) {
            mAuthDialog = new Dialog(DiabloActivity.this);
        }
        mAuthDialog.setContentView(R.layout.dialog_auth);
        mAuthDialog.setTitle(R.string.Authorization_title);
        WebView webView = (WebView) mAuthDialog.findViewById(R.id.wvOauth);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(authUrl);
        webView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return super.shouldOverrideUrlLoading(view, request);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url.matches("^https:\\/\\/localhost\\/.*")) {
                    view.loadUrl("file:///android_asset/fonts/success.html");
                    mDiabloPresenter.signIn(url);
                    mAuthDialog.dismiss();
                } else {
                    view.loadUrl(url);
                }
                return false;
            }
        });
        mAuthDialog.show();
        mAuthDialog.setCancelable(false);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }
        this.doubleBackToExitPressedOnce = true;
        Snackbar.make(mContainer, R.string.doubleClick_backBtn, Snackbar.LENGTH_SHORT).show();
        new Handler().postDelayed(() -> doubleBackToExitPressedOnce = false, 2000);
    }

    @Override
    public void openAuthDialog() {
        if (mDiabloPresenter.hasConnection(this)) {
            prepareSignIn();
        } else {
            Toast.makeText(this, R.string.internet_connection, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void closeAuthDialog() {
        mAuthDialog.dismiss();
    }

    @Override
    public void showFragment(@IdRes int containerViewID, Fragment fragment, String tag) {
        mFragmentManager.beginTransaction()
                .replace(containerViewID, fragment, tag)
                .commit();
    }

    @Override
    public void checkOrientation() {
        Settings.mTwoPane = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        setContentView(R.layout.activity_diablo);
        mDiabloPresenter.restoreScreen();
    }

}
