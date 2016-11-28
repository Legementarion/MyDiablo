package com.lego.mydiablo.view.fragments;

import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.PresenterType;
import com.lego.mydiablo.R;
import com.lego.mydiablo.presenter.fragment.MenuPresenter;
import com.lego.mydiablo.presenter.fragment.MenuView;
import com.lego.mydiablo.view.adapters.RegionAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemSelected;
import butterknife.Unbinder;

public class MenuFragment extends MvpAppCompatFragment implements MenuView {

    @InjectPresenter(type = PresenterType.GLOBAL)
    MenuPresenter mMenuPresenter;

    public static final String TAG = "Menu";
    Animation translate;
    /**
     * Кнопки меню
     */
    @BindView(R.id.bt_normal)
    ToggleButton mNormal;
    @BindView(R.id.bt_harcore)
    ToggleButton mHardcore;
    @BindView(R.id.bt_season)
    ToggleButton mSeason;
    @BindView(R.id.bt_season_hardcore)
    ToggleButton mSeasonHardcore;

    @BindView(R.id.regionTV)
    TextView mRegionTextView;
    @BindView(R.id.idRegion)
    Spinner mRegionsSpinner;
    @BindView(R.id.region_tab)
    View mRegionTab;
    private Unbinder mUnbinder;

    public MenuFragment() {
        //do nothing
    }

    public static MenuFragment newInstance() {
        return new MenuFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_menu, container, false);
        mUnbinder = ButterKnife.bind(this, rootView);

        Typeface face = Typeface.createFromAsset(getActivity().getAssets(),
                "fonts/blizzard.ttf");
        mNormal.setTypeface(face);
        mHardcore.setTypeface(face);
        mSeason.setTypeface(face);
        mSeasonHardcore.setTypeface(face);
        mRegionTextView.setTypeface(face);

        RegionAdapter regionAdapter = new RegionAdapter(getContext(), R.layout.spinner, getResources().getStringArray(R.array.region));
        mRegionsSpinner.setAdapter(regionAdapter);
        mRegionsSpinner.getBackground().setColorFilter(getResources().getColor(R.color.btn_text), PorterDuff.Mode.SRC_ATOP);

        return rootView;
    }

    @Override
    public void onDestroyView() {
        unCheckButton();
        mUnbinder.unbind();
        super.onDestroyView();
    }

    @OnItemSelected(R.id.idRegion)
    void onItemSelected() {
        mMenuPresenter.setRegion(mRegionsSpinner.getSelectedItem().toString());
    }

    @OnClick({R.id.bt_normal, R.id.bt_harcore, R.id.bt_season, R.id.bt_season_hardcore})
    void pressButton(View view) {
        unCheckButton();
        mMenuPresenter.pressButton(view);
    }

    @OnClick(R.id.region_tab_btn)
    void showRegionTab() {
        if (mRegionTab.getVisibility() == View.VISIBLE) {
            translate = AnimationUtils.loadAnimation(getContext(),R.anim.hide_tab);
            mRegionTab.startAnimation(translate);
            mRegionTab.setVisibility(View.GONE);
        }
        else {
            mRegionTab.setVisibility(View.VISIBLE);
            translate = AnimationUtils.loadAnimation(getContext(),R.anim.show_tab);
            mRegionTab.startAnimation(translate);
        }
    }

    /**
     * Визуальное отжатие всех кнопок
     */
    private void unCheckButton() {
        mNormal.setChecked(false);
        mHardcore.setChecked(false);
        mSeason.setChecked(false);
        mSeasonHardcore.setChecked(false);
    }

    /**
     * При востановлении или пороте єкрана восстанавливаем ранее нажатую кнопку
     *
     * @param check - номер кнопки
     */
    @Override
    public void updatePressButton(int check) {
        unCheckButton();
        switch (check) {
            case 1:
                mNormal.setChecked(true);
                break;
            case 2:
                mHardcore.setChecked(true);
                break;
            case 3:
                mSeason.setChecked(true);
                break;
            case 4:
                mSeasonHardcore.setChecked(true);
                break;
            default:
                break;
        }
    }

    @Override
    public void showTab() {
        showRegionTab();
    }

}