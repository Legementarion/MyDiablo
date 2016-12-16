package com.lego.mydiablo.view.fragments;

import android.animation.Animator;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.SimpleOnPageChangeListener;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.PresenterType;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.lego.mydiablo.data.model.Hero;
import com.lego.mydiablo.dialog.PickDialog;
import com.lego.mydiablo.presenter.fragment.HeroTabsPresenter;
import com.lego.mydiablo.presenter.fragment.HeroTabsView;
import com.lego.mydiablo.view.adapters.HeroTabsPagerAdapter;
import com.ogaclejapan.smarttablayout.SmartTabLayout;

import com.lego.mydiablo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.lego.mydiablo.utils.Const.COLOR;
import static com.lego.mydiablo.utils.Const.EMPTY_VALUE;
import static com.lego.mydiablo.utils.ImgUtils.pickHeroIcon;

public class HeroTabsFragment extends MvpAppCompatFragment implements HeroTabsView {

    @InjectPresenter(type = PresenterType.GLOBAL)
    HeroTabsPresenter mHeroTabsPresenter;

    public static final String TAG = "Detail";
    private static final int REQUEST_PICK = 1;

    @BindView(R.id.viewpager)
    ViewPager mViewPager;
    @BindView(R.id.user_info_progressBar)
    ProgressBar mProgressBar;
    @BindView(R.id.tabs)
    SmartTabLayout mTabLayout;
    @BindView(R.id.background_linear_layout)
    LinearLayout mBackgroundLinearLayout;
    @BindView(R.id.background_linear_layout_2)
    LinearLayout mBackgroundLinearLayout2;
    @BindView(R.id.image_background_logo)
    ImageView mImageBackgroundLogo;
    @BindView(R.id.image_logo_tabs)
    ImageView mImageLogo;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    //    @BindView(R.id.image_back)
//    ImageButton mImageBackButton;
    @BindView(R.id.background_tool_bar)
    ImageView mImageBackgroundToolBar;
    @BindView(R.id.animator_icon_relative_layout)
    RelativeLayout mAnimatorIconRelativeLayout;
    @BindView(R.id.app_bar)
    AppBarLayout mAppBarLayout;
    //    @BindView(R.id.text_view_title_toolbar)
//    TextView mTitleTextView;
//    @BindView(R.id.image_title_toolbar)
//    ImageView mImageTitle;
    @BindView(R.id.toolbar)
    Toolbar mToolBar;
    @BindView(R.id.collapsing_toolbar_layout_param_tabs)
    CollapsingToolbarLayout mCollapsingToolbarLayout;

    private static final int PERCENTAGE_TO_ANIMATE_AVATAR = 20;
    private static final int PERCENTAGE_TO_INVISIBLE_TAB = 75;
    private int mMaxScrollSize;
    private int mPositionViewPage;
    private boolean mHideToolBar;
    private boolean mVisibleHeroIcon = true;
    private boolean mVisibleTab = true;

    private HeroTabsPagerAdapter mAdapter;
    private Animation mAnimation;
    private Unbinder mUnbinder;
    private AnimationListener animationListenerInvisible;
    private AnimationListener animationListenerVisible;

    private final SimpleOnPageChangeListener mOnPageChangeListener = new SimpleOnPageChangeListener() {
        @Override
        public void onPageSelected(int position) {
            mPositionViewPage = position;
            animationCloseImageNews();
            mAnimation.setAnimationListener(new AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {     //do nothing
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    animationShowImageNews();
                    setColorCoordinatorLayout();
                    animationFillingColor();
                }

                @Override
                public void onAnimationRepeat(Animation animation) {    //do nothing
                }
            });
        }
    };

    public static HeroTabsFragment newInstance(int rank) {
        Bundle args = new Bundle();
        args.putInt(TAG, rank);
        HeroTabsFragment fragment = new HeroTabsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_hero_tabs, container, false);
        mUnbinder = ButterKnife.bind(this, mView);
        Typeface face = Typeface.createFromAsset(getActivity().getAssets(),
                "fonts/blizzard.ttf");
//        mToolBar.getTitle()..setTypeface(face);
        mToolBar.setNavigationIcon(R.drawable.ic_arrow_back);
        mToolBar.setNavigationOnClickListener(v -> backButton());
        mImageLogo.setImageDrawable(getResources().getDrawable(R.drawable.diablo_logo));

        if (getArguments() != null) {
            setupViewPager(getArguments().getInt(TAG));
            setTabs(inflater);
        }
        mTabLayout.setViewPager(mViewPager);
        mMaxScrollSize = getResources().getDimensionPixelSize(R.dimen.size_collapsing_toolbar_layout);
        setColorCoordinatorLayout();
        animation();
        return mView;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_PICK) {
            mViewPager.setCurrentItem(0);
            mAdapter.removeFragments();
            mViewPager.setAdapter(mAdapter);
            mTabLayout.setViewPager(mViewPager);
            mHeroTabsPresenter.addTab(data.getIntExtra(PickDialog.TAG_PICK_SELECTED, 1));
        }
    }

    @OnClick(R.id.fab)
    public void compareButton(View view) {
        if (mAdapter.getCount() > 1) {
            Snackbar snackbar = Snackbar.make(view, "Compare another one?", Snackbar.LENGTH_LONG)
                    .setAction("YES", view1 ->
                            mHeroTabsPresenter.compare()
                    );
            snackbar.setActionTextColor(Color.RED);
            snackbar.setDuration(Snackbar.LENGTH_INDEFINITE);
            snackbar.show();
        } else {
            mHeroTabsPresenter.compare();
        }
    }

    @Override
    @StateStrategyType(SkipStrategy.class)
    public void openPicker() {
        mAdapter.getPageTitle(0);
        DialogFragment fragment = new PickDialog();
        fragment.setTargetFragment(this, REQUEST_PICK);
        fragment.show(getFragmentManager(), fragment.getClass().getName());
    }

    @Override
    public void setHeroAdapter(Hero hero) {
        mAdapter.setupAdapter(hero);
        mTabLayout.setViewPager(mViewPager);
    }

    @Override
    public void showUserProgressBar() {
        mImageLogo.setVisibility(View.GONE);
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideUserProgressBar() {
        mProgressBar.setVisibility(View.GONE);
        mImageLogo.setVisibility(View.VISIBLE);
    }

    @Override
    public void addCompareFragments(Hero hero, Hero userHero) {
        mAdapter.compare(hero, userHero);
        mTabLayout.setViewPager(mViewPager);
    }

    private void backButton() {
        mHeroTabsPresenter.backPress();
    }

    private void setTabs(LayoutInflater layoutInflater) {
        mTabLayout.setCustomTabView((container1, position, adapter) -> {
            View itemView = layoutInflater.inflate(R.layout.custom_tab_provider, container1, false);
            TextView text = (TextView) itemView.findViewById(R.id.custom_tab_text);
            text.setText(adapter.getPageTitle(position));
            ImageView icon = (ImageView) itemView.findViewById(R.id.custom_tab_icon);
            switch (position) {
                case 0:
                    icon.setImageDrawable(pickHeroIcon(getContext(), mHeroTabsPresenter.getHeroIcon()));
                    break;
                case 1:
                    icon.setImageDrawable(pickHeroIcon(getContext(), mHeroTabsPresenter.getUserHeroIcon()));
                    break;
                case 2:
                    icon.setImageDrawable(pickHeroIcon(getContext(), mHeroTabsPresenter.getResultIcon()));
                    break;
                default:
                    throw new IllegalStateException("Invalid position: " + position);
            }
            return itemView;
        });
    }

    private void animation() {
        initAnimationListener();
        mAppBarLayout.addOnOffsetChangedListener((appBarLayout, verticalOffset) -> {
            int percentage = (Math.abs(verticalOffset)) * 100 / mMaxScrollSize;
            toolBarAnimation(appBarLayout, verticalOffset);
            avatarAnimation(percentage);
            if (percentage > PERCENTAGE_TO_INVISIBLE_TAB && mVisibleTab) {
                mVisibleTab = false;
                mTabLayout.setVisibility(View.INVISIBLE);
            }
            if (percentage < PERCENTAGE_TO_INVISIBLE_TAB && !mVisibleTab) {
                mVisibleTab = true;
                mTabLayout.setVisibility(View.VISIBLE);
            }
        });
    }

    private void avatarAnimation(int percentage) {
        if (percentage >= PERCENTAGE_TO_ANIMATE_AVATAR && mVisibleHeroIcon) {
            mVisibleHeroIcon = false;
            animationCloseImageNews();
            mAnimation.setAnimationListener(animationListenerInvisible);
        }
        if (percentage <= PERCENTAGE_TO_ANIMATE_AVATAR && !mVisibleHeroIcon) {
            mVisibleHeroIcon = true;
            animationShowImageNews();
            mAnimation.setAnimationListener(animationListenerVisible);
        }
    }

    private void toolBarAnimation(AppBarLayout appBarLayout, int verticalOffset) {
        if (mToolBar.getHeight() - appBarLayout.getHeight() == verticalOffset) {
            mCollapsingToolbarLayout.setTitle(mAdapter.getPageTitle(mPositionViewPage));
            switch (mPositionViewPage) {
                case 0:
                    mToolBar.setNavigationIcon(pickHeroIcon(getContext(), mHeroTabsPresenter.getHeroIcon()));
                    break;
                case 1:
                    mToolBar.setNavigationIcon(pickHeroIcon(getContext(), mHeroTabsPresenter.getUserHeroIcon()));
                    break;
                default:
                    mToolBar.setNavigationIcon(pickHeroIcon(getContext(), mHeroTabsPresenter.getResultIcon()));
                    break;
            }
            fab.setVisibility(View.GONE);
            mHideToolBar = true;
            mVisibleHeroIcon = false;
        } else if (mHideToolBar) {
            mCollapsingToolbarLayout.setTitle(EMPTY_VALUE);
            mHideToolBar = false;
            fab.setVisibility(View.VISIBLE);
            mToolBar.setNavigationIcon(R.drawable.ic_arrow_back);
        }
    }

    private void animationCloseImageNews() {
        mAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.scale_close);
        mAnimatorIconRelativeLayout.startAnimation(mAnimation);
    }

    private void setColorCoordinatorLayout() {
        mCollapsingToolbarLayout.setContentScrimColor(Color.parseColor(COLOR));
        mImageBackgroundLogo.setColorFilter(Color.parseColor(COLOR));
    }

    private void animationShowImageNews() {
        mAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.scale_show);
        mAnimatorIconRelativeLayout.startAnimation(mAnimation);
    }

    private void setupViewPager(int rank) {
        mAdapter = new HeroTabsPagerAdapter(getChildFragmentManager());
        mViewPager.setAdapter(mAdapter);
        mViewPager.setOffscreenPageLimit(1);
        mViewPager.setCurrentItem(0);
        mViewPager.addOnPageChangeListener(mOnPageChangeListener);
        if(mHeroTabsPresenter == null) {
            mHeroTabsPresenter = new HeroTabsPresenter();
        }else {
            mHeroTabsPresenter.getHeroFromDB(this, rank);
        }
    }

    private void animationFillingColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            int cx = (int) mBackgroundLinearLayout2.getX() + mBackgroundLinearLayout2.getWidth() / 2;
            int cy = mBackgroundLinearLayout2.getHeight() / 2;
            int finalRadius = Math.max(mBackgroundLinearLayout2.getWidth(), mBackgroundLinearLayout2.getHeight());

            Animator circleRevealAnim = ViewAnimationUtils.createCircularReveal(mBackgroundLinearLayout2, cx, cy, 0, finalRadius);
            circleRevealAnim.setStartDelay(5);
            circleRevealAnim.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {
                    mBackgroundLinearLayout2.setBackgroundColor(Color.parseColor(COLOR));
                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    mBackgroundLinearLayout2.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.transparent));
                }

                @Override
                public void onAnimationCancel(Animator animation) {     // Do nothing
                }

                @Override
                public void onAnimationRepeat(Animator animation) {     // Do nothing
                }
            });
            circleRevealAnim.start();
        }
    }

    @Override
    public void onDestroyView() {
        mUnbinder.unbind();
        super.onDestroyView();
    }

    private void initAnimationListener() {
        animationListenerInvisible = new AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {     //do nothing
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mAnimatorIconRelativeLayout.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {    //do nothing
            }
        };

        animationListenerVisible = new AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {     //do nothing
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mAnimatorIconRelativeLayout.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {    //do nothing
            }
        };
    }

}