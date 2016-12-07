package com.lego.mydiablo.view.fragments;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;

import com.lego.mydiablo.R;
import com.lego.mydiablo.data.model.Hero;
import com.lego.mydiablo.presenter.fragment.ItemDetailPresenter;
import com.lego.mydiablo.presenter.fragment.ItemDetailView;
import com.lego.mydiablo.view.adapters.rv.HeroStatsRecyclerAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ItemDetailFragment extends MvpAppCompatFragment implements ItemDetailView {

    @InjectPresenter
    ItemDetailPresenter mItemDetailPresenter;

    public static final String TAG = "ItemDetail";
    private Hero mHero;

    RecyclerView mRecyclerView;
    @BindView(R.id.playerParam)
    ExpandableListView mPlayerExpandableListView;
    @BindView(R.id.coordinator_content)
    CoordinatorLayout mLayout;

    private Unbinder mUnbinder;

    public ItemDetailFragment() {
        // Required empty public constructor
    }

    public static ItemDetailFragment newInstance() {
        return new ItemDetailFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_item_detail, container, false);
        mUnbinder = ButterKnife.bind(this, rootView);

        View footerView = inflater.inflate(R.layout.expandable_list_footer, mPlayerExpandableListView, false);
        View headerView = inflater.inflate(R.layout.expandable_list_header, mPlayerExpandableListView, false);
        mRecyclerView = ButterKnife.findById(footerView, R.id.item_list);

        mPlayerExpandableListView.addFooterView(footerView);
        mPlayerExpandableListView.addHeaderView(headerView);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mPlayerExpandableListView.setNestedScrollingEnabled(true);
        } else {
            CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) mLayout.getLayoutParams();
            params.bottomMargin = 48;
            mLayout.setLayoutParams(params);
        }

        mItemDetailPresenter.setHero(mHero, getContext());

        return rootView;
    }

    public void setHeroInfo(Hero hero) {
        mHero = hero;
    }

    public String getHeroIcon() {
        return mItemDetailPresenter.getIcon();
    }

    @Override
    public void fillData(ExpandableListAdapter expandablePlayerListAdapter) {
        mPlayerExpandableListView.setAdapter(expandablePlayerListAdapter);
    }

    @Override
    public void setupRV(List<String> general) {
        mRecyclerView.setAdapter(new HeroStatsRecyclerAdapter(general, getContext()));
    }

    @Override
    public void onDestroyView() {
        mUnbinder.unbind();
        super.onDestroyView();
    }
}
