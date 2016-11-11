package com.lego.mydiablo.view.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.lego.mydiablo.data.model.Hero;
import com.lego.mydiablo.view.fragments.ItemDetailFragment;
import com.lego.mydiablo.view.fragments.SumFragment;

import java.util.ArrayList;
import java.util.List;

public class HeroTabsPagerAdapter extends FragmentPagerAdapter {

    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final List<String> mFragmentTitleList = new ArrayList<>();

    public HeroTabsPagerAdapter(FragmentManager manager) {
        super(manager);
    }

    public void setupAdapter(Hero hero) {
        if (!mFragmentList.isEmpty()){
            mFragmentList.clear();
            mFragmentTitleList.clear();
        }
        addFragment(hero.getBattleTag(), hero);
    }

    public void compare(Hero hero, Hero userHero) {
        if (mFragmentList.size() > 1){
            mFragmentList.remove(2);
            mFragmentList.remove(1);
            mFragmentTitleList.remove(2);
            mFragmentTitleList.remove(1);
        }
        addFragment(userHero.getName(), userHero);
        addSummaryFragment(hero, userHero);
        notifyDataSetChanged();
    }

    private void addFragment(String title, Hero hero) {
        ItemDetailFragment itemDetailFragment = ItemDetailFragment.newInstance();
        itemDetailFragment.setHeroInfo(hero);
        mFragmentTitleList.add(title);
        mFragmentList.add(itemDetailFragment);
        notifyDataSetChanged();
    }

    private void addSummaryFragment(Hero hero, Hero userHero) {
        SumFragment sumFragment = new SumFragment();
        sumFragment.compare(hero, userHero);
        mFragmentTitleList.add("Difference");
        mFragmentList.add(sumFragment);
        notifyDataSetChanged();
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

}
