package com.lego.mydiablo.view.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ToggleButton;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.lego.mydiablo.R;
import com.lego.mydiablo.presenter.fragment.MenuPresenter;
import com.lego.mydiablo.presenter.fragment.MenuView;
import com.lego.mydiablo.utils.Settings;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.lego.mydiablo.utils.Const.ERA;
import static com.lego.mydiablo.utils.Const.HARDCORE;
import static com.lego.mydiablo.utils.Const.NO_VALUE;
import static com.lego.mydiablo.utils.Const.SEASON;
import static com.lego.mydiablo.utils.Settings.mHARDCODE;
import static com.lego.mydiablo.utils.Settings.mMode;

/**
 * Created by Lego on 08.03.2016.
 */

public class MenuFragment extends MvpAppCompatFragment implements MenuView {

    @InjectPresenter
    MenuPresenter mMenuPresenter;

    /**Кнопки меню*/
    @BindView(R.id.bt_normal)
    ToggleButton normal;
    @BindView(R.id.bt_harcore)
    ToggleButton hardcore;
    @BindView(R.id.bt_season)
    ToggleButton season;
    @BindView(R.id.bt_season_hardcore)
    ToggleButton seasonHardcore;

    /**Номер кнопки для восстановления при повороте */
    private static int button_CHECKED;
    private static String BUTTON_CHECKED_TAG = "BUTTON_CHECKED_TAG";

    private MenuCallBack menuCallBack;
    private Unbinder unbinder;

    public MenuFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.menu_fragment, container, false);

        /**проверка на количество фрагментов на экране*/
        Settings.mTwoPane = getActivity().findViewById(R.id.item_list) != null;

        unbinder = ButterKnife.bind(this, rootView);
        menuCallBack = (MenuCallBack) getActivity();

        /**Сохранять нажатую кнопку при повороте єкрана */
        if (savedInstanceState != null) {
            button_CHECKED = savedInstanceState.getInt(BUTTON_CHECKED_TAG);
        }
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({ R.id.bt_normal, R.id.bt_harcore, R.id.bt_season, R.id.bt_season_hardcore})
    void pressButton(View view){
        unCheckButton();
        switch (view.getId()){
            case R.id.bt_normal:
                button_CHECKED = 1;
                mMode = ERA;
                mHARDCODE = NO_VALUE;
                normal.setChecked(true);
                break;

            case R.id.bt_harcore:
                button_CHECKED = 2;
                mMode = ERA;
                mHARDCODE = HARDCORE;
                hardcore.setChecked(true);
                break;

            case R.id.bt_season:
                button_CHECKED = 3;
                mMode = SEASON;
                mHARDCODE = NO_VALUE;
                season.setChecked(true);
                break;

            case R.id.bt_season_hardcore:
                button_CHECKED = 4;
                mMode = SEASON;
                mHARDCODE = HARDCORE;
                seasonHardcore.setChecked(true);
                break;
        }
        if (!Settings.mTwoPane) {
            menuCallBack.menuCallBackClick(view);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(BUTTON_CHECKED_TAG, button_CHECKED);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
    }

    /**
     * Визуальное отжатие всех кнопок
     */
    private void unCheckButton() {
        normal.setChecked(false);
        hardcore.setChecked(false);
        season.setChecked(false);
        seasonHardcore.setChecked(false);
    }

    @Override
    public void onResume() {
        super.onResume();
        setButton(button_CHECKED);
    }

    /**
     * При востановлении или пороте єкрана восстанавливаем ранее нажатую кнопку
     * @param check - номер кнопки
     */
    public void setButton(int check) {
        switch (check) {
            case 1:
                normal.setChecked(true);
                break;
            case 2:
                hardcore.setChecked(true);
                break;
            case 3:
                season.setChecked(true);
                break;
            case 4:
                seasonHardcore.setChecked(true);
                break;
            default:
                break;
        }
    }

    @Override
    public void updatePressButton(int check) {
        switch (check) {
            case 1:
                normal.setChecked(true);
                break;
            case 2:
                hardcore.setChecked(true);
                break;
            case 3:
                season.setChecked(true);
                break;
            case 4:
                seasonHardcore.setChecked(true);
                break;
            default:
                break;
        }
    }
}
