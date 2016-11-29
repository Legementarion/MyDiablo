package com.lego.mydiablo.view.fragments;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.PresenterType;
import com.lego.mydiablo.R;
import com.lego.mydiablo.data.model.Hero;
import com.lego.mydiablo.presenter.fragment.ItemListPresenter;
import com.lego.mydiablo.presenter.fragment.ItemListView;
import com.lego.mydiablo.utils.Settings;
import com.lego.mydiablo.view.adapters.ClassAdapter;
import com.lego.mydiablo.view.adapters.PaginateLoadingListItemCreator;
import com.lego.mydiablo.view.adapters.TableItemRecyclerViewAdapter;
import com.paginate.Paginate;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemSelected;
import butterknife.Unbinder;
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;

import static com.lego.mydiablo.utils.Const.START_PROGRESS_VALUE;

public class ItemListFragment extends MvpAppCompatFragment implements ItemListView, Paginate.Callbacks {

    @InjectPresenter(type = PresenterType.GLOBAL)
    ItemListPresenter mItemListPresenter;

    public static final String TAG = "List";

    @BindView(R.id.idClass)
    Spinner mClassSpinner;
    @BindView(R.id.seasonTV)
    TextView mSeasonTextView;
    @BindView(R.id.idSeason)
    Spinner mSeasonSpinner;
    @BindView(R.id.progressBar)
    android.widget.ProgressBar mProgressBar;
    @BindView(R.id.back_button)
    ImageButton mBackBtn;
    @BindView(R.id.item_list)
    RecyclerView mRecyclerView;
    @BindView(R.id.swipe_container)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private TableItemRecyclerViewAdapter mTableItemRecyclerViewAdapter;
    private static ProgressDialog sProgressDialog;
    private Unbinder mUnbinder;
    private Paginate mPaginate;

    private boolean mLoading = false;

    public static ItemListFragment newInstance() {
        return new ItemListFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        mItemListPresenter.configure(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_item_list, container, false);
        mUnbinder = ButterKnife.bind(this, rootView);
        /**Видим кнопку назад, если нет второго фрагмента */
        if (Settings.mTwoPane) {
            mBackBtn.setVisibility(View.GONE);
        } else {
            mBackBtn.setVisibility(View.VISIBLE);
        }


        mSwipeRefreshLayout.setColorSchemeColors(
                Color.RED, Color.GREEN, Color.BLUE, Color.CYAN);
        mSwipeRefreshLayout.setOnRefreshListener(() -> {
            mItemListPresenter.load(mClassSpinner.getSelectedItem().toString(),
                    mSeasonSpinner.getSelectedItem().toString());
        });

        ClassAdapter classAdapter = new ClassAdapter(getContext(), R.layout.spinner, getResources().getStringArray(R.array.hero_class));
        mClassSpinner.setAdapter(classAdapter);
        mClassSpinner.getBackground().setColorFilter(getResources().getColor(R.color.btn_text), PorterDuff.Mode.SRC_ATOP);

        mSeasonSpinner.setAdapter(mItemListPresenter.fillSeasonAdapterArrays(getContext()));
        mSeasonSpinner.getBackground().setColorFilter(getResources().getColor(R.color.btn_text), PorterDuff.Mode.SRC_ATOP);

        Typeface face = Typeface.createFromAsset(getActivity().getAssets(),
                "fonts/blizzard.ttf");
        mSeasonTextView.setTypeface(face);

        setupRecyclerView(Collections.emptyList());
        return rootView;
    }

    @Override
    public void setupRecyclerView(List<Hero> heroList) {
        mRecyclerView.setItemAnimator(new SlideInUpAnimator());
        mTableItemRecyclerViewAdapter = new TableItemRecyclerViewAdapter(heroList, getContext(), mItemListPresenter);
        mRecyclerView.setAdapter(mTableItemRecyclerViewAdapter);

        mPaginate = Paginate.with(mRecyclerView, this)
                .setLoadingTriggerThreshold(2)
                .addLoadingListItem(true)
                .setLoadingListItemCreator(new PaginateLoadingListItemCreator(mRecyclerView, mTableItemRecyclerViewAdapter))
                .setLoadingListItemSpanSizeLookup(() -> 1)
                .build();

    }

    @Override
    public void setNewList(List<Hero> heroList) {
        mTableItemRecyclerViewAdapter.setItems(heroList);
        if (mSwipeRefreshLayout.isRefreshing()) {
            mSwipeRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void updateList(List<Hero> heroList) {
        mTableItemRecyclerViewAdapter.add(heroList);
        mLoading = false;
    }

    @Override
    public void blockUI() {
        mClassSpinner.setEnabled(false);
        mBackBtn.setEnabled(false);
        mSeasonSpinner.setEnabled(false);
        mRecyclerView.setEnabled(false);
    }

    @Override
    public void unBlockUI() {
        mClassSpinner.setEnabled(true);
        mBackBtn.setEnabled(true);
        mSeasonSpinner.setEnabled(true);
        mRecyclerView.setEnabled(true);
    }

    @OnItemSelected({R.id.idSeason, R.id.idClass})
    void onItemSelected() {
        new Handler().postDelayed(() -> {
            if (mClassSpinner != null && mSeasonSpinner != null) {
                mItemListPresenter.loadDataHeroList(mClassSpinner.getSelectedItem().toString(),
                        mSeasonSpinner.getSelectedItem().toString());
            }
        }, 2500);

    }

    @OnClick(R.id.back_button)
    void onBackButtonPress() {
        mItemListPresenter.backPress();
    }

    @Override
    public void updateProgressBar(boolean active) {
        if (mProgressBar.getVisibility() == View.GONE) {
            mProgressBar.setVisibility(View.VISIBLE);
        } else {
            mProgressBar.setProgress(START_PROGRESS_VALUE);
        }
        if (!active) {
            mProgressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void showProgress(String message) {
        sProgressDialog = new ProgressDialog(getContext());
        sProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        sProgressDialog.setMessage(message);
        sProgressDialog.setCancelable(false);
        sProgressDialog.setCanceledOnTouchOutside(false);
        sProgressDialog.show();
    }

    @Override
    public void hideProgress() {
        if (sProgressDialog != null) {
            sProgressDialog.dismiss();
        }
    }

    @Override
    public void onLoadMore() {
        mLoading = true;
        new Handler().postDelayed(this::loadMore, 200);
    }

    private void loadMore() {
        if (mClassSpinner != null && mSeasonSpinner != null) {
            mItemListPresenter.gimmeMore(mClassSpinner.getSelectedItem().toString(), mSeasonSpinner.getSelectedItem().toString());
        }
    }

    @Override
    public boolean isLoading() {
        return mLoading;
    }

    @Override
    public boolean hasLoadedAllItems() {
        return mItemListPresenter.loadedAllItems();
    }

    @Override
    public void onStop() {
        if (mPaginate != null) {
            mPaginate.unbind();
        }
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        mUnbinder.unbind();
        super.onDestroyView();
    }

}
