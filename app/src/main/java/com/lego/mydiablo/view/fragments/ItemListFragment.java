package com.lego.mydiablo.view.fragments;


import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;

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
import butterknife.OnItemSelected;
import butterknife.Unbinder;
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;

import static com.lego.mydiablo.utils.Const.START_PROGRESS_VALUE;

/**
 * An activity representing a list of Items. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class ItemListFragment extends MvpAppCompatFragment implements ItemListView, Paginate.Callbacks {

    @InjectPresenter(type = PresenterType.GLOBAL)
    ItemListPresenter mItemListPresenter;

    public static final String TAG = "List";
    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */

    @BindView(R.id.idClass)
    Spinner mClassSpinner;
    @BindView(R.id.idSeason)
    Spinner mSeasonSpinner;
    @BindView(R.id.progressBar)
    android.widget.ProgressBar mProgressBar;
    @BindView(R.id.back_button)
    Button mBackBtn;
    @BindView(R.id.item_list)
    RecyclerView mRecyclerView;

    private TableItemRecyclerViewAdapter mTableItemRecyclerViewAdapter;

    private boolean mLoading = false;

    private Unbinder mUnbinder;
    private Paginate mPaginate;

    public static ItemListFragment newInstance() {
        return new ItemListFragment();
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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mItemListPresenter.configure(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.item_list_fragment, container, false);
        mUnbinder = ButterKnife.bind(this, rootView);

        /**Видим кнопку назад, если нет второго фрагмента */
        if (Settings.mTwoPane) {
            mBackBtn.setVisibility(View.GONE);
        } else {
            mBackBtn.setVisibility(View.VISIBLE);
        }

        ClassAdapter classAdapter = new ClassAdapter(getContext(), R.layout.spinner, getResources().getStringArray(R.array.hero_class));
        mClassSpinner.setAdapter(classAdapter);
        mClassSpinner.getBackground().setColorFilter(getResources().getColor(R.color.btn_text), PorterDuff.Mode.SRC_ATOP);

        mSeasonSpinner.setAdapter(mItemListPresenter.fillSeasonAdapterArrays(getContext()));
        mSeasonSpinner.getBackground().setColorFilter(getResources().getColor(R.color.btn_text), PorterDuff.Mode.SRC_ATOP);

        setupRecyclerView(Collections.emptyList());
        return rootView;
    }

    @Override
    public void setupRecyclerView(List<Hero> heroList) {
        mRecyclerView.setItemAnimator(new SlideInUpAnimator());
        mTableItemRecyclerViewAdapter = new TableItemRecyclerViewAdapter(heroList, getContext());
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
    }

    @Override
    public void updateList(List<Hero> heroList) {
        mTableItemRecyclerViewAdapter.add(heroList);
    }

    @OnItemSelected({R.id.idSeason, R.id.idClass})
    void onItemSelected() {
        mItemListPresenter.loadDataHeroList(mClassSpinner.getSelectedItem().toString(), mSeasonSpinner.getSelectedItem().toString());
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
    public void onLoadMore() {
        mLoading = true;
        new Handler().postDelayed(this::loadMore, 2000);
    }

    private void loadMore() {
        if (mClassSpinner!=null && mSeasonSpinner !=null) {
            mItemListPresenter.gimmeMore(mClassSpinner.getSelectedItem().toString(), mSeasonSpinner.getSelectedItem().toString());
        }
        mLoading = false;
    }

    @Override
    public boolean isLoading() {
        return mLoading;
    }

    @Override
    public boolean hasLoadedAllItems() {
        return mItemListPresenter.loadedAllItems();
    }
}
