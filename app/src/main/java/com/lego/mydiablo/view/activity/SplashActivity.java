package com.lego.mydiablo.view.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.PresenterType;
import com.lego.mydiablo.R;
import com.lego.mydiablo.presenter.activity.SplashActivityPresenter;
import com.lego.mydiablo.presenter.activity.SplashActivityView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.lego.mydiablo.utils.HeroUtils.hasConnection;

public class SplashActivity extends MvpAppCompatActivity implements SplashActivityView {

    @BindView(R.id.splash_layout)
    LinearLayout mLinearLayout;
    @BindView(R.id.splash_eu_imageView)
    ImageView mImageViewEU;
    @BindView(R.id.splash_us_imageView)
    ImageView mImageViewUS;
    @BindView(R.id.splash_tw_imageView)
    ImageView mImageViewTW;
    @BindView(R.id.splash_kr_imageView)
    ImageView mImageViewKR;
    @BindView(R.id.splash_eu_textView)
    TextView mTextViewEU;
    @BindView(R.id.splash_us_textView)
    TextView mTextViewUS;
    @BindView(R.id.splash_tw_textView)
    TextView mTextViewTW;
    @BindView(R.id.splash_kr_textView)
    TextView mTextViewKR;

    @BindView(R.id.eu)
    LinearLayout mEU;
    @BindView(R.id.us)
    LinearLayout mUS;
    @BindView(R.id.tw)
    LinearLayout mTW;
    @BindView(R.id.kr)
    LinearLayout mKR;

    private Unbinder mUnbinder;

    @InjectPresenter(type = PresenterType.LOCAL)
    SplashActivityPresenter mSplashActivityPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mUnbinder = ButterKnife.bind(this);
        setUI();
    }

    private void setUI() {
        mLinearLayout.setBackground(null);
        mLinearLayout.setBackgroundColor(getResources().getColor(R.color.background_detail));

        Typeface face = Typeface.createFromAsset(getAssets(),"fonts/blizzard.ttf");
        mTextViewEU.setTypeface(face);
        mTextViewEU.setText("EU");
        mTextViewUS.setTypeface(face);
        mTextViewUS.setText("US");
        mTextViewTW.setTypeface(face);
        mTextViewTW.setText("TW");
        mTextViewKR.setTypeface(face);
        mTextViewKR.setText("KR");
    }

    @OnClick({R.id.eu, R.id.us, R.id.tw, R.id.kr})
    public void regionClick(View view) {
        mSplashActivityPresenter.setRegion(view.getId());
    }

    @Override
    public void startAuth() {
        if (hasConnection(this)) {
            Intent intent = new Intent(this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        } else {
            Toast.makeText(this, R.string.internet_connection, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        mUnbinder.unbind();
        super.onDestroy();
    }


}
