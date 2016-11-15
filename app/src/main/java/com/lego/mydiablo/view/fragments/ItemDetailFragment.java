package com.lego.mydiablo.view.fragments;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.lego.mydiablo.R;
import com.lego.mydiablo.data.model.Hero;
import com.lego.mydiablo.presenter.fragment.ItemDetailPresenter;
import com.lego.mydiablo.presenter.fragment.ItemDetailView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ItemDetailFragment extends MvpAppCompatFragment implements ItemDetailView {

    @InjectPresenter
    ItemDetailPresenter mItemDetailPresenter;

    public static final String TAG = "ItemDetail";
    private Hero mHero;

    @BindView(R.id.playerParam)
    ExpandableListView mPlayerExpandableListView;
    @BindView(R.id.item_detail_container)
    LinearLayout mFrameLayout;

    private Unbinder mUnbinder;

    public static ItemDetailFragment newInstance() {
        return new ItemDetailFragment();
    }
    
    public ItemDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_item_detail, container, false);
        mUnbinder = ButterKnife.bind(this, rootView);
        mItemDetailPresenter.setHero(mHero, getContext());
        return rootView;
    }

    public void setHeroInfo(Hero hero){
        mHero = hero;
    }

    @Override
    public void fillData(ExpandableListAdapter expandablePlayerListAdapter) {
        mPlayerExpandableListView.setAdapter(expandablePlayerListAdapter);
    }

    @Override
    public void onDestroyView() {
        mUnbinder.unbind();
        super.onDestroyView();
    }
}
