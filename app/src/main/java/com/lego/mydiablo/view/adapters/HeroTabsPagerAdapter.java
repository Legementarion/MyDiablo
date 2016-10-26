package com.lego.mydiablo.view.adapters;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.lego.mydiablo.R;
import com.lego.mydiablo.view.fragments.MenuFragment;


import java.util.ArrayList;
import java.util.List;

public class HeroTabsPagerAdapter extends FragmentPagerAdapter {

    public static final String POSITION_PAGE = "position_page";
    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final List<String> mFragmentTitleList = new ArrayList<>();

    public HeroTabsPagerAdapter(FragmentManager manager) {
        super(manager);
        Bundle bundle = new Bundle();
        bundle.putInt(POSITION_PAGE, 0);
        addFragment("1");
        addFragment("2");

    }

    private void addFragment(String title) {
        mFragmentList.add(new MenuFragment());
        mFragmentTitleList.add(title);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitleList.get(position);
    }

    public String getColor(int position){
        return "#99b342";
    }



}
