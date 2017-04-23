package com.lego.mydiablo.view.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
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
    ConstraintLayout mLayout;
    @BindView(R.id.eu)
    ImageView mImageViewEU;
    @BindView(R.id.us)
    ImageView mImageViewUS;
    @BindView(R.id.tw)
    ImageView mImageViewTW;
    @BindView(R.id.kr)
    ImageView mImageViewKR;
    @BindView(R.id.splash_title)
    TextView mTextViewTitle;
    @BindView(R.id.splash_eu_textView)
    TextView mTextViewEU;
    @BindView(R.id.splash_us_textView)
    TextView mTextViewUS;
    @BindView(R.id.splash_tw_textView)
    TextView mTextViewTW;
    @BindView(R.id.splash_kr_textView)
    TextView mTextViewKR;

    private Unbinder mUnbinder;

    @InjectPresenter(type = PresenterType.LOCAL)
    SplashActivityPresenter mSplashActivityPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        mUnbinder = ButterKnife.bind(this);
        setUI();
    }

    private void setUI() {
        mLayout.setBackground(null);
        mLayout.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.background_detail));
        String[] regionArray = getResources().getStringArray(R.array.region);
        Typeface face = Typeface.createFromAsset(getAssets(), "fonts/blizzard.ttf");
        mTextViewEU.setTypeface(face);
        mTextViewTitle.setTypeface(face);
        mTextViewTitle.setText(R.string.region_choose);
        mTextViewEU.setText(regionArray[0]);
        mTextViewUS.setTypeface(face);
        mTextViewUS.setText(regionArray[1]);
        mTextViewTW.setTypeface(face);
        mTextViewTW.setText(regionArray[2]);
        mTextViewKR.setTypeface(face);
        mTextViewKR.setText(regionArray[3]);
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
