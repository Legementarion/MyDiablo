package com.lego.mydiablo.view.fragments;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ItemDetailFragment extends Fragment {

    public static final String ARG_ITEM_ID = "hero_id";

    @BindView(R.id.playerParam)
    ExpandableListView mPlayerExpandableListView;
    @BindView(R.id.userParam)
    ExpandableListView mUserExpandableListView;
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
        View rootView = inflater.inflate(R.layout.item_detail_fragment, container, false);
        ButterKnife.bind(this, rootView);

        fillData();

        expandablePlayerListTitle = new ArrayList<>(expandablePlayerListDetail.keySet());
        expandablePlayerListAdapter = new ProfileExpandableListAdapter(getContext(), expandablePlayerListTitle, expandablePlayerListDetail);
        mPlayerExpandableListView.setAdapter(expandablePlayerListAdapter);
        return rootView;
    }


    private void fillData() {
        expandablePlayerListDetail = new HashMap<>();
        List<String> general = new ArrayList<>();
        Log.d("ItemDetailFragment", "fillData: " + mHero.getBattleTag());
        general.add(getString(R.string.stat_Life) + " - ololo soon");
//        general.add(getString(R.string.stat_Life) + " - " + mHero.getHeroStats().getLife());
//        general.add(getString(R.string.stat_Damage) + " - " + mHero.getHeroStats().getDamage());
//        general.add(getString(R.string.stat_Toughness) + " - " + mHero.getHeroStats().getToughness());
//        general.add(getString(R.string.stat_Healing) + " - " + mHero.getHeroStats().getHealing());
//        general.add(getString(R.string.stat_AttackSpeed) + " - " + mHero.getHeroStats().getAttackSpeed());
//        general.add(getString(R.string.stat_Armor) + " - " + mHero.getHeroStats().getArmor());
//        general.add(getString(R.string.stat_Strength) + " - " + mHero.getHeroStats().getStrength());
//        general.add(getString(R.string.stat_Dexterity) + " - " + mHero.getHeroStats().getDexterity());
//        general.add(getString(R.string.stat_Vitality) + " - " + mHero.getHeroStats().getVitality());
//        general.add(getString(R.string.stat_Intelligence) + " - " + mHero.getHeroStats().getIntelligence());
//        general.add(getString(R.string.stat_PhysicalResist) + " - " + mHero.getHeroStats().getPhysicalResist());
//        general.add(getString(R.string.stat_FireResist) + " - " + mHero.getHeroStats().getFireResist());
//        general.add(getString(R.string.stat_ColdResist) + " - " + mHero.getHeroStats().getColdResist());
//        general.add(getString(R.string.stat_LightningResist) + " - " + mHero.getHeroStats().getLightningResist());
//        general.add(getString(R.string.stat_PoisonResist) + " - " + mHero.getHeroStats().getPoisonResist());
//        general.add(getString(R.string.stat_ArcaneResist) + " - " + mHero.getHeroStats().getArcaneResist());
//        general.add(getString(R.string.stat_CritDamage) + " - " + mHero.getHeroStats().getCritDamage());
//        general.add(getString(R.string.stat_BlockChance) + " - " + mHero.getHeroStats().getBlockChance());
//        general.add(getString(R.string.stat_BlockAmountMin) + " - " + mHero.getHeroStats().getBlockAmountMin());
//        general.add(getString(R.string.stat_BlockAmountMax) + " - " + mHero.getHeroStats().getBlockAmountMax());
//        general.add(getString(R.string.stat_DamageIncrease) + " - " + mHero.getHeroStats().getDamageIncrease());
//        general.add(getString(R.string.stat_CritChance) + " - " + mHero.getHeroStats().getCritChance());
//        general.add(getString(R.string.stat_DamageReduction) + " - " + mHero.getHeroStats().getDamageReduction());
//        general.add(getString(R.string.stat_LifeSteal) + " - " + mHero.getHeroStats().getLifeSteal());
//        general.add(getString(R.string.stat_LifePerKill) + " - " + mHero.getHeroStats().getLifePerKill());
//        general.add(getString(R.string.stat_LifeOnHit) + " - " + mHero.getHeroStats().getLifeOnHit());
//        general.add(getString(R.string.stat_MagicFind) + " - " + mHero.getHeroStats().getMagicFind());
//        general.add(getString(R.string.stat_GoldFind) + " - " + mHero.getHeroStats().getGoldFind());
//        general.add(getString(R.string.stat_PrimaryResource) + " - " + mHero.getHeroStats().getPrimaryResource());
//        general.add(getString(R.string.stat_SecondaryResource) + " - " + mHero.getHeroStats().getSecondaryResource());

        expandablePlayerListDetail.put(getString(R.string.stats), general);
    }

}
