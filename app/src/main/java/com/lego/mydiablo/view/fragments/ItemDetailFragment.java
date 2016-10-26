package com.lego.mydiablo.view.fragments;


import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.lego.mydiablo.R;
import com.lego.mydiablo.data.RealmDataController;
import com.lego.mydiablo.data.model.Hero;
import com.lego.mydiablo.view.adapters.ProfileExpandableListAdapter;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.lego.mydiablo.utils.Settings.mDetailActive;


public class ItemDetailFragment extends Fragment {

    public static final String ARG_ITEM_ID = "hero_id";

    @BindView(R.id.playerParam)
    ExpandableListView mPlayerExpandableListView;
    @BindView(R.id.userParam)
    ExpandableListView mUserExpandableListView;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.statsCompare)
    RecyclerView mRecyclerView;
    @BindView(R.id.playerName)
    TextView mPlayerName;
    @BindView(R.id.userName)
    TextView mUserName;
    @BindView(R.id.detail_conteiner)
    CoordinatorLayout mFrameLayout;

    private RealmDataController mRealmDataController;

    private Hero mHero;
    private int mHeroId = 0;

    private ImageView mImageViewIcon;

    private ExpandableListAdapter expandablePlayerListAdapter;
    private List<String> expandablePlayerListTitle;
    private HashMap<String, List<String>> expandablePlayerListDetail;

    public static ItemDetailFragment newInstance(int param) {
        ItemDetailFragment fragment = new ItemDetailFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_ITEM_ID, param);
        fragment.setArguments(args);
        return fragment;
    }

    public ItemDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mHeroId = getArguments().getInt(ARG_ITEM_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_item_detail, container, false);
        ButterKnife.bind(this, rootView);

        fab.setOnClickListener(view -> Snackbar.make(view, "Compare", Snackbar.LENGTH_LONG)
                .setAction("Action", null)
                .show());

        mRealmDataController = RealmDataController.with(this);

        if (mHeroId != 0) {
            mHero = mRealmDataController.getHero(mHeroId);
        }
        switchBG(mHero.getmClass(), mHero.getmGender());
        fillData();

        expandablePlayerListTitle = new ArrayList<>(expandablePlayerListDetail.keySet());
        expandablePlayerListAdapter = new ProfileExpandableListAdapter(getContext(), expandablePlayerListTitle, expandablePlayerListDetail);
        mPlayerExpandableListView.setAdapter(expandablePlayerListAdapter);
        return rootView;
    }

    private void switchBG(String s, int i) {
        switch (s) {
            case "barbarian":
                if (i == 0) {
                    mFrameLayout.setBackgroundDrawable(getContext().getResources().getDrawable(R.drawable.barbarian_male_port));
                }else {
                    mFrameLayout.setBackgroundDrawable(getContext().getResources().getDrawable(R.drawable.barbarian_female_port));
                }
                break;
        }
    }

    private void fillData() {
        expandablePlayerListDetail = new HashMap<>();
        List<String> general = new ArrayList<>();
        Log.d("ItemDetailFragment", "fillData: " + mHero.getmBattleTag());
        general.add(getString(R.string.stat_Life) + " - ololo soon");
//        general.add(getString(R.string.stat_Life) + " - " + mHero.getmHeroStats().getLife());
//        general.add(getString(R.string.stat_Damage) + " - " + mHero.getmHeroStats().getDamage());
//        general.add(getString(R.string.stat_Toughness) + " - " + mHero.getmHeroStats().getToughness());
//        general.add(getString(R.string.stat_Healing) + " - " + mHero.getmHeroStats().getHealing());
//        general.add(getString(R.string.stat_AttackSpeed) + " - " + mHero.getmHeroStats().getAttackSpeed());
//        general.add(getString(R.string.stat_Armor) + " - " + mHero.getmHeroStats().getArmor());
//        general.add(getString(R.string.stat_Strength) + " - " + mHero.getmHeroStats().getStrength());
//        general.add(getString(R.string.stat_Dexterity) + " - " + mHero.getmHeroStats().getDexterity());
//        general.add(getString(R.string.stat_Vitality) + " - " + mHero.getmHeroStats().getVitality());
//        general.add(getString(R.string.stat_Intelligence) + " - " + mHero.getmHeroStats().getIntelligence());
//        general.add(getString(R.string.stat_PhysicalResist) + " - " + mHero.getmHeroStats().getPhysicalResist());
//        general.add(getString(R.string.stat_FireResist) + " - " + mHero.getmHeroStats().getFireResist());
//        general.add(getString(R.string.stat_ColdResist) + " - " + mHero.getmHeroStats().getColdResist());
//        general.add(getString(R.string.stat_LightningResist) + " - " + mHero.getmHeroStats().getLightningResist());
//        general.add(getString(R.string.stat_PoisonResist) + " - " + mHero.getmHeroStats().getPoisonResist());
//        general.add(getString(R.string.stat_ArcaneResist) + " - " + mHero.getmHeroStats().getArcaneResist());
//        general.add(getString(R.string.stat_CritDamage) + " - " + mHero.getmHeroStats().getCritDamage());
//        general.add(getString(R.string.stat_BlockChance) + " - " + mHero.getmHeroStats().getBlockChance());
//        general.add(getString(R.string.stat_BlockAmountMin) + " - " + mHero.getmHeroStats().getBlockAmountMin());
//        general.add(getString(R.string.stat_BlockAmountMax) + " - " + mHero.getmHeroStats().getBlockAmountMax());
//        general.add(getString(R.string.stat_DamageIncrease) + " - " + mHero.getmHeroStats().getDamageIncrease());
//        general.add(getString(R.string.stat_CritChance) + " - " + mHero.getmHeroStats().getCritChance());
//        general.add(getString(R.string.stat_DamageReduction) + " - " + mHero.getmHeroStats().getDamageReduction());
//        general.add(getString(R.string.stat_LifeSteal) + " - " + mHero.getmHeroStats().getLifeSteal());
//        general.add(getString(R.string.stat_LifePerKill) + " - " + mHero.getmHeroStats().getLifePerKill());
//        general.add(getString(R.string.stat_LifeOnHit) + " - " + mHero.getmHeroStats().getLifeOnHit());
//        general.add(getString(R.string.stat_MagicFind) + " - " + mHero.getmHeroStats().getMagicFind());
//        general.add(getString(R.string.stat_GoldFind) + " - " + mHero.getmHeroStats().getGoldFind());
//        general.add(getString(R.string.stat_PrimaryResource) + " - " + mHero.getmHeroStats().getPrimaryResource());
//        general.add(getString(R.string.stat_SecondaryResource) + " - " + mHero.getmHeroStats().getSecondaryResource());

        expandablePlayerListDetail.put(getString(R.string.stats), general);
    }

}
