package com.lego.mydiablo.presenter.fragment;

import android.content.Context;
import android.util.Log;
import android.widget.ExpandableListAdapter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.lego.mydiablo.R;
import com.lego.mydiablo.data.model.DisplayedItemAttribute;
import com.lego.mydiablo.data.model.Hero;
import com.lego.mydiablo.data.model.Item;
import com.lego.mydiablo.view.adapters.ProfileExpandableListAdapter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

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
    private LinkedHashMap<String, List<String>> expandablePlayerListDetail;
    private List<String> mIcons = new ArrayList<>();
    private List<List<String>> heroItems = new ArrayList<>();

    private Hero mHero;

    public void setHero(Hero hero, Context context) {
        mHero = hero;
        fillData(context);
    }

    public String getIcon() {
        return mHero.getHeroClass() + mHero.getGender();
    }

    private void fillData(Context context) {
        expandablePlayerListDetail = new LinkedHashMap<>();
        fillExpandedList();

        List<String> general = new ArrayList<>();
        general.add(context.getString(R.string.stat_Life) + " - " + mHero.getHeroStats().getLife());
        general.add(context.getString(R.string.stat_PrimaryResource) + " - " + mHero.getHeroStats().getPrimaryResource());
        general.add(context.getString(R.string.stat_SecondaryResource) + " - " + mHero.getHeroStats().getSecondaryResource());

        general.add(context.getString(R.string.stat_Damage) + " - " + mHero.getHeroStats().getDamage());
        general.add(context.getString(R.string.stat_Toughness) + " - " + mHero.getHeroStats().getToughness());
        general.add(context.getString(R.string.stat_Healing) + " - " + mHero.getHeroStats().getHealing());

        general.add(context.getString(R.string.stat_AttackSpeed) + " - " + mHero.getHeroStats().getAttackSpeed());
        general.add(context.getString(R.string.stat_DamageIncrease) + " - " + mHero.getHeroStats().getDamageIncrease());
        general.add(context.getString(R.string.stat_CritDamage) + " - " + mHero.getHeroStats().getCritDamage());
        general.add(context.getString(R.string.stat_CritChance) + " - " + mHero.getHeroStats().getCritChance());
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

        general.add(context.getString(R.string.stat_BlockChance) + " - " + mHero.getHeroStats().getBlockChance());
        general.add(context.getString(R.string.stat_BlockAmountMin) + " - " + mHero.getHeroStats().getBlockAmountMin());
        general.add(context.getString(R.string.stat_BlockAmountMax) + " - " + mHero.getHeroStats().getBlockAmountMax());

        general.add(context.getString(R.string.stat_DamageReduction) + " - " + mHero.getHeroStats().getDamageReduction());
        general.add(context.getString(R.string.stat_LifeSteal) + " - " + mHero.getHeroStats().getLifeSteal());
        general.add(context.getString(R.string.stat_LifePerKill) + " - " + mHero.getHeroStats().getLifePerKill());
        general.add(context.getString(R.string.stat_LifeOnHit) + " - " + mHero.getHeroStats().getLifeOnHit());
        general.add(context.getString(R.string.stat_MagicFind) + " - " + mHero.getHeroStats().getMagicFind());
        general.add(context.getString(R.string.stat_GoldFind) + " - " + mHero.getHeroStats().getGoldFind());

        mIcons.add(MEDIA_URL + D3 + ICONS + ITEMS + LARGE + null + PNG);
        expandablePlayerListTitle = new ArrayList<>(expandablePlayerListDetail.keySet());
        expandablePlayerListAdapter = new ProfileExpandableListAdapter(context, expandablePlayerListTitle, expandablePlayerListDetail, mIcons);

        getViewState().setupRV(general);
        getViewState().fillData(expandablePlayerListAdapter);
    }

    private void fillExpandedList() {
        for (Item item : mHero.getHeroComplect()) {
            List<String> itemStats = new ArrayList<>();
            for (DisplayedItemAttribute displayedItemAttribute : item.getDisplayedStats()) {
                itemStats.add(displayedItemAttribute.getAttribute());
            }
            if (item.getSetName() != null){
                Log.d("ITEM", "fillExpandedList: " + item.getSetName());
            }
            heroItems.add(itemStats);
            mIcons.add(MEDIA_URL + D3 + ICONS + ITEMS + LARGE + item.getImageUrl() + PNG);
        }

        for (int i = 0; i < mHero.getHeroComplect().size(); i++) {
            expandablePlayerListDetail.put(mHero.getHeroComplect().get(i).getTitle(), heroItems.get(i));
        }
    }

}
