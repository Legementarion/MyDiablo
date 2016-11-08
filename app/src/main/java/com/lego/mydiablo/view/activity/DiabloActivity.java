package com.lego.mydiablo.view.activity;

import android.app.Dialog;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.IdRes;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
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
import static com.lego.mydiablo.utils.Const.REDIRECTION_URI;
import static com.lego.mydiablo.utils.Const.REDIRECT_URI;
import static com.lego.mydiablo.utils.Const.RESPONSE_TYPE;

public class DiabloActivity extends MvpAppCompatActivity implements DiabloView {

    @InjectPresenter(type = PresenterType.WEAK)
    DiabloPresenter mDiabloPresenter;

    @BindView(R.id.main_container)
    FrameLayout mContainer;

    private Unbinder mUnbinder;
    private Dialog mAuthDialog;

    public static final String URL = AUTHORIZE_URI + RESPONSE_TYPE + CREDENTIALS + REDIRECTION_URI + REDIRECT_URI;

    private FragmentManager mFragmentManager;

    private boolean doubleBackToExitPressedOnce;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        mUnbinder = ButterKnife.bind(this);
        mFragmentManager = getSupportFragmentManager();
        mDiabloPresenter.startConfig(this);
    }

    public void prepareSignIn() {
        mAuthDialog = new Dialog(DiabloActivity.this);
        mAuthDialog.setContentView(R.layout.auth_dialog);
        mAuthDialog.setTitle(R.string.Authorization_title);
        WebView webView = (WebView) mAuthDialog.findViewById(R.id.wvOauth);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(URL);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                mDiabloPresenter.signIn(url);
//                view.loadUrl(url);
                return true;
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
        Log.d("Fragment s", "onConfigurationChanged: ");
        super.onConfigurationChanged(newConfig);
        setContentView(R.layout.main_activity);
        mDiabloPresenter.restoreScreen();
    }

}
