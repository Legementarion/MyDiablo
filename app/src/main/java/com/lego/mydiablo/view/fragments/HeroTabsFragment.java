package com.lego.mydiablo.view.fragments;

import android.animation.Animator;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.SimpleOnPageChangeListener;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lego.mydiablo.view.adapters.HeroTabsPagerAdapter;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.squareup.picasso.Picasso;

import com.lego.mydiablo.R;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;


public class HeroTabsFragment extends Fragment {
    private final int PERCENTAGE_TO_ANIMATE_AVATAR = 20;
    private final int PERCENTAGE_TO_INVISIBLE_TAB = 75;
    private final double PERCENTAGE_TRANSPARENT_BACKGROUND_COLOR = 0.75;
    private int mMaxScrollSize;
    private ViewPager mViewPager;
    private SmartTabLayout mTabLayout;
    private HeroTabsPagerAdapter mAdapter;
    private LinearLayout mBackgroundLinearLayout, mBackgroundLinearLayout2;
    private ImageView mImageAvatar, mImageSearch, mImageNews, mImageBackgroundLogo;
    private ImageView mImageBackgroundToolBar;
    private AppBarLayout mAppBarLayout;
    private Toolbar mToolBar;
    private TextView mTitleTextView;
    private int mPositionViewPage;
    private Animation animation;
    private Boolean mHideToolBar = false, mVisibleImageNews = true, mVisibleTab = true;
    private CollapsingToolbarLayout mCollapsingToolbarLayout;
    private Animator circleRevealAnim;
    private RelativeLayout mAnimatorIconRelativeLayout;
    private LinkedHashMap mapImageURL = new LinkedHashMap<String, String>();


    private final SimpleOnPageChangeListener mOnPageChangeListener = new SimpleOnPageChangeListener() {
        @Override
        public void onPageSelected(int position) {
            mPositionViewPage = position;
            setBackgroundForToolbar(position);

            animationCloseImageNews();
            animation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    animationShowImageNews();
                    setColorCoordinatorLayout(position);

                    animationFillingColor(position);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                }
            });


        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_news_tabs, container, false);

        mViewPager = (ViewPager) mView.findViewById(R.id.viewpager);
        setupViewPager();

        mTabLayout = (SmartTabLayout) mView.findViewById(R.id.tabs);
        mTabLayout.setViewPager(mViewPager);

        mBackgroundLinearLayout = (LinearLayout) mView.findViewById(R.id.background_linear_layout);
        mBackgroundLinearLayout2 = (LinearLayout) mView.findViewById(R.id.background_linear_layout_2);
        mBackgroundLinearLayout.setBackgroundColor(Color.parseColor(addAlphaToColor(mAdapter.getColor(0), PERCENTAGE_TRANSPARENT_BACKGROUND_COLOR)));

        mImageAvatar = (ImageView) mView.findViewById(R.id.image_avatar);

        mImageAvatar.setOnClickListener(v -> showSetting());

        mImageSearch = (ImageView) mView.findViewById(R.id.search_image);

        mImageNews = (ImageView) mView.findViewById(R.id.image_news_tabs);
        mImageBackgroundToolBar = (ImageView) mView.findViewById(R.id.background_tool_bar);
        mAnimatorIconRelativeLayout = (RelativeLayout) mView.findViewById(R.id.animator_icon_relative_layout);
        mImageBackgroundLogo = (ImageView) mView.findViewById(R.id.image_background_logo);

        mToolBar = (Toolbar) mView.findViewById(R.id.toolbar);

        mTitleTextView = (TextView) mView.findViewById(R.id.text_view_title_toolbar);
        mCollapsingToolbarLayout = (CollapsingToolbarLayout) mView.findViewById(R.id.collapsing_toolbar_layout_news_tabs);
        mMaxScrollSize = getResources().getDimensionPixelSize(R.dimen.size_collapsing_toolbar_layout);

        setColorCoordinatorLayout(0);

        mAppBarLayout = (AppBarLayout) mView.findViewById(R.id.app_bar);
        mAppBarLayout.addOnOffsetChangedListener(((appBarLayout, verticalOffset) -> {


            int percentage = (Math.abs(verticalOffset)) * 100 / mMaxScrollSize;

            if (mToolBar.getHeight() - appBarLayout.getHeight() == verticalOffset) {
                mTitleTextView.setText(mAdapter.getPageTitle(mPositionViewPage));

                mHideToolBar = true;
                mVisibleImageNews = false;

            } else {
                if (mHideToolBar) {
                    mTitleTextView.setText("");
                    mHideToolBar = false;
                }
            }

            if (percentage >= PERCENTAGE_TO_ANIMATE_AVATAR && mVisibleImageNews) {
                mVisibleImageNews = false;
                animationCloseImageNews();
                animation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        mAnimatorIconRelativeLayout.setVisibility(View.INVISIBLE);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
            }

            if (percentage <= PERCENTAGE_TO_ANIMATE_AVATAR && !mVisibleImageNews) {
                mVisibleImageNews = true;
                animationShowImageNews();
                animation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        mAnimatorIconRelativeLayout.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
            }
            if (percentage > PERCENTAGE_TO_INVISIBLE_TAB && mVisibleTab) {
                mVisibleTab = false;
                mTabLayout.setVisibility(View.INVISIBLE);
            }

            if (percentage < PERCENTAGE_TO_INVISIBLE_TAB && !mVisibleTab) {
                mVisibleTab = true;
                mTabLayout.setVisibility(View.VISIBLE);
            }
        }));
        return mView;
    }

    private void animationCloseImageNews() {
        animation = AnimationUtils.loadAnimation(getContext(), R.anim.scale_close);
        mAnimatorIconRelativeLayout.startAnimation(animation);
    }

    private void setColorCoordinatorLayout(int position) {
        mCollapsingToolbarLayout.setContentScrimColor(Color.parseColor(mAdapter.getColor(position)));
        mImageBackgroundLogo.setColorFilter(Color.parseColor(mAdapter.getColor(position)));
    }

    private void animationShowImageNews() {
        animation = AnimationUtils.loadAnimation(getContext(), R.anim.scale_show);
        mAnimatorIconRelativeLayout.startAnimation(animation);
    }

    private void showSetting() {
    }

    private void setupViewPager() {
        mAdapter = new HeroTabsPagerAdapter(getChildFragmentManager());
        mViewPager.setAdapter(mAdapter);
        mViewPager.setOffscreenPageLimit(1);
        mViewPager.setCurrentItem(0);
        mViewPager.addOnPageChangeListener(mOnPageChangeListener);
    }

    //alpha from 0.0 to 1.0
    private String addAlphaToColor(String originalColor, double alpha) {
        long alphaFixed = Math.round(alpha * 255);
        String alphaHex = Long.toHexString(alphaFixed);
        if (alphaHex.length() == 1) {
            alphaHex = "0" + alphaHex;
        }
        originalColor = originalColor.replace("#", "#" + alphaHex);

        return originalColor;
    }

    private void animationFillingColor(int position) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            int cx = (int) mBackgroundLinearLayout2.getX() + mBackgroundLinearLayout2.getWidth() / 2;
            int cy = mBackgroundLinearLayout2.getHeight() / 2;
            int finalRadius = Math.max(mBackgroundLinearLayout2.getWidth(), mBackgroundLinearLayout2.getHeight());

            circleRevealAnim = ViewAnimationUtils.createCircularReveal(mBackgroundLinearLayout2, cx, cy, 0, finalRadius);
            circleRevealAnim.setStartDelay(5);
            circleRevealAnim.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {
                    mBackgroundLinearLayout2.setBackgroundColor(Color.parseColor(mAdapter.getColor(position)));
                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    mBackgroundLinearLayout.setBackgroundColor(Color.parseColor(addAlphaToColor(mAdapter.getColor(position), PERCENTAGE_TRANSPARENT_BACKGROUND_COLOR)));
                    mBackgroundLinearLayout2.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.transparent));
                }

                @Override
                public void onAnimationCancel(Animator animation) {
                }

                @Override
                public void onAnimationRepeat(Animator animation) {
                }
            });
            circleRevealAnim.start();
        } else
            mBackgroundLinearLayout.setBackgroundColor(Color.parseColor(addAlphaToColor(mAdapter.getColor(position), PERCENTAGE_TRANSPARENT_BACKGROUND_COLOR)));

    }

    private void setBackgroundForToolbar(int position) {
        Iterator entries = mapImageURL.entrySet().iterator();
        int i = 0;
        while (entries.hasNext()) {
            Map.Entry entry = (Map.Entry) entries.next();
            if (i == position) {
                Picasso.with(getContext())
                        .load((String) entry.getValue())
                        .into(mImageBackgroundToolBar);
                break;
            }
            i++;
        }
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }
}
