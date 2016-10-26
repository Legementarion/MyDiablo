package com.lego.mydiablo.view.fragments;

import android.animation.Animator;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.SimpleOnPageChangeListener;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lego.mydiablo.events.FragmentEvent;
import com.lego.mydiablo.view.adapters.HeroTabsPagerAdapter;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.squareup.picasso.Picasso;

import com.lego.mydiablo.R;

import org.greenrobot.eventbus.EventBus;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.lego.mydiablo.utils.Settings.mDetailActive;


public class HeroTabsFragment extends Fragment {

    @BindView(R.id.viewpager)
    ViewPager mViewPager;
    @BindView(R.id.tabs)
    SmartTabLayout mTabLayout;
    @BindView(R.id.background_linear_layout)
    LinearLayout mBackgroundLinearLayout;
    @BindView(R.id.background_linear_layout_2)
    LinearLayout mBackgroundLinearLayout2;
    @BindView(R.id.image_background_logo)
    ImageView mImageBackgroundLogo;
    @BindView(R.id.image_back)
    ImageButton mImageBackButton;
    @BindView(R.id.background_tool_bar)
    ImageView mImageBackgroundToolBar;
    @BindView(R.id.animator_icon_relative_layout)
    RelativeLayout mAnimatorIconRelativeLayout;
    @BindView(R.id.app_bar)
    AppBarLayout mAppBarLayout;
    @BindView(R.id.text_view_title_toolbar)
    TextView mTitleTextView;
    @BindView(R.id.toolbar)
    Toolbar mToolBar;
    @BindView(R.id.collapsing_toolbar_layout_news_tabs)
    CollapsingToolbarLayout mCollapsingToolbarLayout;

    private final int PERCENTAGE_TO_ANIMATE_AVATAR = 20;
    private final int PERCENTAGE_TO_INVISIBLE_TAB = 75;
    private final double PERCENTAGE_TRANSPARENT_BACKGROUND_COLOR = 0.75;
    private int mMaxScrollSize;
    private int mPositionViewPage;
    private boolean mHideToolBar = false, mVisibleImageNews = true, mVisibleTab = true;

    private HeroTabsPagerAdapter mAdapter;
    private FragmentEvent event;

    private EventBus bus = EventBus.getDefault();
    private Animation animation;
    private Animator circleRevealAnim;
    private Unbinder mUnbinder;
    private Resources res;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_hero_tabs, container, false);
        mUnbinder = ButterKnife.bind(this, mView);
        setupViewPager();
        res = getResources();
        mTabLayout.setCustomTabView((container1, position, adapter) -> {
            View itemView = inflater.inflate(R.layout.custom_tab_provider, container1, false);
            TextView text = (TextView) itemView.findViewById(R.id.custom_tab_text);
            text.setText(adapter.getPageTitle(position));
            ImageView icon = (ImageView) itemView.findViewById(R.id.custom_tab_icon);
            switch (position) {
                case 0:
                    icon.setImageDrawable(res.getDrawable(R.mipmap.ic_launcher));
                    break;
                case 1:
                    icon.setImageDrawable(res.getDrawable(R.mipmap.ic_launcher));
                    break;
                case 2:
                    icon.setImageDrawable(res.getDrawable(R.mipmap.ic_launcher));
                    break;
                default:
                    throw new IllegalStateException("Invalid position: " + position);
            }

            return itemView;
        });
        mTabLayout.setViewPager(mViewPager);
        mBackgroundLinearLayout.setBackgroundColor(Color.parseColor(addAlphaToColor(mAdapter.getColor(0), PERCENTAGE_TRANSPARENT_BACKGROUND_COLOR)));
        mMaxScrollSize = getResources().getDimensionPixelSize(R.dimen.size_collapsing_toolbar_layout);
        mDetailActive = true;
        setColorCoordinatorLayout(0);
        animation();
        return mView;
    }

    @Override
    public void onDestroyView() {
        mUnbinder.unbind();
        mDetailActive = false;
        super.onDestroyView();
    }

    private final SimpleOnPageChangeListener mOnPageChangeListener = new SimpleOnPageChangeListener() {
        @Override
        public void onPageSelected(int position) {
            mPositionViewPage = position;
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

    @OnClick(R.id.image_back)
    void onButtonPressed(View v) {
        event = new FragmentEvent(null);
        bus.post(event);    //send to diablo activity
    }

    private void animation() {
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
        } else {
            mBackgroundLinearLayout.setBackgroundColor(Color.parseColor(addAlphaToColor(mAdapter.getColor(position), PERCENTAGE_TRANSPARENT_BACKGROUND_COLOR)));
        }
    }

}
