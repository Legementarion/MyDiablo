package com.lego.mydiablo.presenter.fragment;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.widget.ExpandableListAdapter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.lego.mydiablo.R;
import com.lego.mydiablo.data.RealmDataController;
import com.lego.mydiablo.data.model.Hero;
import com.lego.mydiablo.view.adapters.ProfileExpandableListAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.lego.mydiablo.utils.Const.D3;
import static com.lego.mydiablo.utils.Const.ICONS;
import static com.lego.mydiablo.utils.Const.ITEMS;
import static com.lego.mydiablo.utils.Const.LARGE;
import static com.lego.mydiablo.utils.Const.MEDIA_URL;
import static com.lego.mydiablo.utils.Const.PNG;

@InjectViewState
public class ItemDetailPresenter extends MvpPresenter<ItemDetailView> {

    private ExpandableListAdapter expandablePlayerListAdapter;
    private List<String> expandablePlayerListTitle;
    private HashMap<String, List<String>> expandablePlayerListDetail;
    private List<String> mIcons = new ArrayList<>();

    private Hero mHero;

    public void setHero(Hero hero, Context context) {
        mHero = hero;
        fillData(context);

    }

    private void fillData(Context context) {
        expandablePlayerListDetail = new HashMap<>();
        List<String> head = new ArrayList<>();
        head.add("" + mHero.getHeroComplect().get(0).getTitle());
        mIcons.add(MEDIA_URL + D3 + ICONS + ITEMS + LARGE + mHero.getHeroComplect().get(0).getImageUrl() + PNG);
        List<String> torso = new ArrayList<>();
        torso.add("" + mHero.getHeroComplect().get(1).getTitle());
        mIcons.add(MEDIA_URL + D3 + ICONS + ITEMS + LARGE + mHero.getHeroComplect().get(1).getImageUrl() + PNG);
        List<String> feet = new ArrayList<>();
        feet.add("" + mHero.getHeroComplect().get(2).getTitle());
        mIcons.add(MEDIA_URL + D3 + ICONS + ITEMS + LARGE + mHero.getHeroComplect().get(2).getImageUrl() + PNG);
        List<String> hands = new ArrayList<>();
        hands.add("" + mHero.getHeroComplect().get(3).getTitle());
        mIcons.add(MEDIA_URL + D3 + ICONS + ITEMS + LARGE + mHero.getHeroComplect().get(3).getImageUrl() + PNG);
        List<String> shoulders = new ArrayList<>();
        shoulders.add("" + mHero.getHeroComplect().get(4).getTitle());
        mIcons.add(MEDIA_URL + D3 + ICONS + ITEMS + LARGE + mHero.getHeroComplect().get(4).getImageUrl() + PNG);
        List<String> legs = new ArrayList<>();
        legs.add("" + mHero.getHeroComplect().get(5).getTitle());
        mIcons.add(MEDIA_URL + D3 + ICONS + ITEMS + LARGE + mHero.getHeroComplect().get(5).getImageUrl() + PNG);
        List<String> bracers = new ArrayList<>();
        bracers.add("" + mHero.getHeroComplect().get(6).getTitle());
        mIcons.add(MEDIA_URL + D3 + ICONS + ITEMS + LARGE + mHero.getHeroComplect().get(6).getImageUrl() + PNG);
        List<String> mainHand = new ArrayList<>();
        mainHand.add("" + mHero.getHeroComplect().get(7).getTitle());
        mIcons.add(MEDIA_URL + D3 + ICONS + ITEMS + LARGE + mHero.getHeroComplect().get(7).getImageUrl() + PNG);
        List<String> waist = new ArrayList<>();
        waist.add("" + mHero.getHeroComplect().get(8).getTitle());
        mIcons.add(MEDIA_URL + D3 + ICONS + ITEMS + LARGE + mHero.getHeroComplect().get(8).getImageUrl() + PNG);
        List<String> rightFinger = new ArrayList<>();
        rightFinger.add("" + mHero.getHeroComplect().get(9).getTitle());
        mIcons.add(MEDIA_URL + D3 + ICONS + ITEMS + LARGE + mHero.getHeroComplect().get(9).getImageUrl() + PNG);
        List<String> leftFinger = new ArrayList<>();
        leftFinger.add("" + mHero.getHeroComplect().get(10).getTitle());
        mIcons.add(MEDIA_URL + D3 + ICONS + ITEMS + LARGE + mHero.getHeroComplect().get(10).getImageUrl() + PNG);
        List<String> neck = new ArrayList<>();
        neck.add("" + mHero.getHeroComplect().get(11).getTitle());
        mIcons.add(MEDIA_URL + D3 + ICONS + ITEMS + LARGE + mHero.getHeroComplect().get(11).getImageUrl() + PNG);
//        if (mHero.getHeroComplect().)
        List<String> offHand = new ArrayList<>();
//        offHand.add("" + mHero.getHeroComplect().get(12).getTitle());

        List<String> general = new ArrayList<>();
        general.add(context.getString(R.string.stat_Life) + " - " + mHero.getHeroStats().getLife());
        general.add(context.getString(R.string.stat_Damage) + " - " + mHero.getHeroStats().getDamage());
        general.add(context.getString(R.string.stat_Toughness) + " - " + mHero.getHeroStats().getToughness());
        general.add(context.getString(R.string.stat_Healing) + " - " + mHero.getHeroStats().getHealing());
        general.add(context.getString(R.string.stat_AttackSpeed) + " - " + mHero.getHeroStats().getAttackSpeed());
        general.add(context.getString(R.string.stat_Armor) + " - " + mHero.getHeroStats().getArmor());
        general.add(context.getString(R.string.stat_Strength) + " - " + mHero.getHeroStats().getStrength());
        general.add(context.getString(R.string.stat_Dexterity) + " - " + mHero.getHeroStats().getDexterity());
        general.add(context.getString(R.string.stat_Vitality) + " - " + mHero.getHeroStats().getVitality());
        general.add(context.getString(R.string.stat_Intelligence) + " - " + mHero.getHeroStats().getIntelligence());
        general.add(context.getString(R.string.stat_PhysicalResist) + " - " + mHero.getHeroStats().getPhysicalResist());
        general.add(context.getString(R.string.stat_FireResist) + " - " + mHero.getHeroStats().getFireResist());
        general.add(context.getString(R.string.stat_ColdResist) + " - " + mHero.getHeroStats().getColdResist());
        general.add(context.getString(R.string.stat_LightningResist) + " - " + mHero.getHeroStats().getLightningResist());
        general.add(context.getString(R.string.stat_PoisonResist) + " - " + mHero.getHeroStats().getPoisonResist());
        general.add(context.getString(R.string.stat_ArcaneResist) + " - " + mHero.getHeroStats().getArcaneResist());
        general.add(context.getString(R.string.stat_CritDamage) + " - " + mHero.getHeroStats().getCritDamage());
        general.add(context.getString(R.string.stat_BlockChance) + " - " + mHero.getHeroStats().getBlockChance());
        general.add(context.getString(R.string.stat_BlockAmountMin) + " - " + mHero.getHeroStats().getBlockAmountMin());
        general.add(context.getString(R.string.stat_BlockAmountMax) + " - " + mHero.getHeroStats().getBlockAmountMax());
        general.add(context.getString(R.string.stat_DamageIncrease) + " - " + mHero.getHeroStats().getDamageIncrease());
        general.add(context.getString(R.string.stat_CritChance) + " - " + mHero.getHeroStats().getCritChance());
        general.add(context.getString(R.string.stat_DamageReduction) + " - " + mHero.getHeroStats().getDamageReduction());
        general.add(context.getString(R.string.stat_LifeSteal) + " - " + mHero.getHeroStats().getLifeSteal());
        general.add(context.getString(R.string.stat_LifePerKill) + " - " + mHero.getHeroStats().getLifePerKill());
        general.add(context.getString(R.string.stat_LifeOnHit) + " - " + mHero.getHeroStats().getLifeOnHit());
        general.add(context.getString(R.string.stat_MagicFind) + " - " + mHero.getHeroStats().getMagicFind());
        general.add(context.getString(R.string.stat_GoldFind) + " - " + mHero.getHeroStats().getGoldFind());
        general.add(context.getString(R.string.stat_PrimaryResource) + " - " + mHero.getHeroStats().getPrimaryResource());
        general.add(context.getString(R.string.stat_SecondaryResource) + " - " + mHero.getHeroStats().getSecondaryResource());

//        expandablePlayerListDetail.put(context.getString(R.string.stats), general);
        expandablePlayerListDetail.put(context.getString(R.string.head), head);
        expandablePlayerListDetail.put(context.getString(R.string.torso), torso);
        expandablePlayerListDetail.put(context.getString(R.string.feet), feet);
        expandablePlayerListDetail.put(context.getString(R.string.hands), hands);
        expandablePlayerListDetail.put(context.getString(R.string.shoulders), shoulders);
        expandablePlayerListDetail.put(context.getString(R.string.legs), legs);
        expandablePlayerListDetail.put(context.getString(R.string.bracers), bracers);
        expandablePlayerListDetail.put(context.getString(R.string.mainHand), mainHand);
        expandablePlayerListDetail.put(context.getString(R.string.waist), waist);
        expandablePlayerListDetail.put(context.getString(R.string.rightFinger), rightFinger);
        expandablePlayerListDetail.put(context.getString(R.string.leftFinger), leftFinger);
        expandablePlayerListDetail.put(context.getString(R.string.neck), neck);
//        expandablePlayerListDetail.put(context.getString(R.string.offhand), offHand);

        expandablePlayerListTitle = new ArrayList<>(expandablePlayerListDetail.keySet());
        expandablePlayerListAdapter = new ProfileExpandableListAdapter(context, expandablePlayerListTitle, expandablePlayerListDetail, mIcons);

        getViewState().fillData(expandablePlayerListAdapter);
    }

}
