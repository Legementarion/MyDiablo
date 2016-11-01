package com.lego.mydiablo.view.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ToggleButton;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.PresenterType;
import com.lego.mydiablo.R;
import com.lego.mydiablo.presenter.fragment.MenuPresenter;
import com.lego.mydiablo.presenter.fragment.MenuView;
import com.lego.mydiablo.utils.Settings;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Lego on 08.03.2016.
 */

public class MenuFragment extends MvpAppCompatFragment implements MenuView {

    @InjectPresenter(type = PresenterType.GLOBAL)
    MenuPresenter mMenuPresenter;

    public static final String TAG = "Menu";

    /**Кнопки меню*/
    @BindView(R.id.bt_normal)
    ToggleButton mNormal;
    @BindView(R.id.bt_harcore)
    ToggleButton mHardcore;
    @BindView(R.id.bt_season)
    ToggleButton mSeason;
    @BindView(R.id.bt_season_hardcore)
    ToggleButton mSeasonHardcore;

    private Unbinder mUnbinder;

    public MenuFragment() {
        //do nothing
    }

    public static MenuFragment newInstance() {
        return new MenuFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.menu_fragment, container, false);
        mUnbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
        unCheckButton();
    }

    @OnClick({ R.id.bt_normal, R.id.bt_harcore, R.id.bt_season, R.id.bt_season_hardcore})
    void pressButton(View view){
        unCheckButton();
        mMenuPresenter.pressButton(view);
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
     * @param check - номер кнопки
     */
    @Override
    public void updatePressButton(int check) {
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

}
