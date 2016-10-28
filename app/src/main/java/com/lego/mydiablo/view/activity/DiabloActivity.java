package com.lego.mydiablo.view.activity;

import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
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

    @BindView(R.id.start_menu_fragment)
    FrameLayout mContainer;

    private Unbinder mUnbinder;
    private Dialog mAuthDialog;

    public static final String URL = AUTHORIZE_URI + RESPONSE_TYPE + CREDENTIALS + REDIRECTION_URI + REDIRECT_URI;

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private FragmentManager mFragmentManager;

    private boolean doubleBackToExitPressedOnce;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_menu_activity);
        mUnbinder = ButterKnife.bind(this);
        mFragmentManager = getSupportFragmentManager();
        mDiabloPresenter.config(this, mFragmentManager);
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
                view.loadUrl(url);
                mDiabloPresenter.signIn(url);
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
//        mDiabloPresenter.switchFragment(mFragmentManager, null);
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
}
