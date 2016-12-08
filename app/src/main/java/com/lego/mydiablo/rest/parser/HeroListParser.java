package com.lego.mydiablo.rest.parser;

import android.util.Log;

import com.lego.mydiablo.data.RealmDataController;
import com.lego.mydiablo.data.model.DisplayedItemAttribute;
import com.lego.mydiablo.data.model.Hero;
import com.lego.mydiablo.data.model.Item;
import com.lego.mydiablo.data.model.ItemProperty;
import com.lego.mydiablo.data.model.LegendaryPower;
import com.lego.mydiablo.data.model.Rune;
import com.lego.mydiablo.data.model.Skill;
import com.lego.mydiablo.data.model.Socket;
import com.lego.mydiablo.data.model.Stats;
import com.lego.mydiablo.rest.RetrofitRequests;
import com.lego.mydiablo.rest.callback.models.heroes.HeroDetail;
import com.lego.mydiablo.rest.callback.models.heroes.items.ItemDetail;
import com.lego.mydiablo.rest.callback.models.heroes.legendary.Legendary;
import com.lego.mydiablo.rest.callback.models.heroes.skills.HeroSkill;
import com.lego.mydiablo.rest.callback.models.heroes.skills.SkillLists;
import com.lego.mydiablo.rest.callback.models.leaderboard.HeroList;
import com.lego.mydiablo.rest.callback.models.leaderboard.Row;
import com.lego.mydiablo.rest.callback.models.item.Description;
import com.lego.mydiablo.rest.callback.models.item.Gem;
import com.lego.mydiablo.rest.callback.models.item.Property;
import com.lego.mydiablo.rest.callback.models.item.Ranks;
import com.lego.mydiablo.rest.callback.models.item.ResponseItem;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.realm.RealmList;
import retrofit2.Call;
import rx.Observable;
import rx.Observable.OnSubscribe;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.lego.mydiablo.utils.Const.DEFAULT_RANK;
import static com.lego.mydiablo.utils.HeroUtils.castGender;
import static com.lego.mydiablo.utils.Settings.mCurrentLocale;

public class HeroListParser {

    private RetrofitRequests mRetrofitRequests;
    private RealmDataController mRealmDataController;

    public HeroListParser() {
        mRetrofitRequests = RetrofitRequests.getInstance();
        mRealmDataController = RealmDataController.getInstance();
    }

    public Observable<List<Hero>> parseData(HeroList heroList) {
        return Observable.just(parseFromJson(heroList));
    }

    private List<Hero> parseFromJson(HeroList heroList) {
        List<Hero> currentHeroList = new ArrayList<>();
        for (Row row : heroList.getRow()) {
            Hero currentHero = new Hero();
            try {
                if (row.getPlayer().get(0).getData().get(0).getId().equals("HeroBattleTag")) {
                    currentHero.setBattleTag(row.getPlayer().get(0).getData().get(0).getString());    //player data
                    currentHero.setHeroClass(row.getPlayer().get(0).getData().get(2).getString());
                    currentHero.setGender(castGender(row.getPlayer().get(0).getData().get(3).getString()));
                    currentHero.setLevel(row.getPlayer().get(0).getData().get(4).getNumber());
                    if (row.getPlayer().get(0).getData().get(5).getNumber() != null) {
                        currentHero.setParagonLevel(row.getPlayer().get(0).getData().get(5).getNumber());
                    }
                    if (row.getPlayer().get(0).getData().get(6).getId().equals("HeroClanTag")) {
                        currentHero.setClanTag(row.getPlayer().get(0).getData().get(6).getString());
                        currentHero.setClanName(row.getPlayer().get(0).getData().get(7).getString());
                        currentHero.setId(row.getPlayer().get(0).getData().get(8).getNumber());    // if era = 1 and query = barbarian -> Error field doesn't exist
                    } else {
                        currentHero.setId(row.getPlayer().get(0).getData().get(6).getNumber());
                    }
                } else {
                    currentHero.setHeroClass(row.getPlayer().get(0).getData().get(1).getString());
                    currentHero.setLevel(row.getPlayer().get(0).getData().get(3).getNumber());
                    if (row.getPlayer().get(0).getData().get(4).getNumber() != null) {
                        currentHero.setParagonLevel(row.getPlayer().get(0).getData().get(4).getNumber());
                    }
                    if (row.getPlayer().get(0).getData().get(5).getId().equals("HeroClanTag")) {
                        currentHero.setClanTag(row.getPlayer().get(0).getData().get(5).getString());
                        currentHero.setClanName(row.getPlayer().get(0).getData().get(6).getString());
                        currentHero.setId(row.getPlayer().get(0).getData().get(7).getNumber());
                    } else {
                        currentHero.setId(row.getPlayer().get(0).getData().get(5).getNumber());
                    }
                }

                if (heroList.getEra() != 0) {
                    currentHero.setSeasonValue(heroList.getEra());
                } else {
                    currentHero.setSeasonValue(heroList.getSeason());
                }
                currentHero.setRank(row.getData().get(0).getNumber());     //other data
                currentHero.setRiftLevel(row.getData().get(1).getNumber());
                currentHero.setRiftTime(row.getData().get(2).getTimestamp());

                currentHero.setLoadingProgress(false);

            } catch (IndexOutOfBoundsException ex) {
                Log.d("Error", "parseFromJson: array out of range cell " + ex);
            }
            currentHeroList.add(currentHero);
        }
        return currentHeroList;
    }

    public Observable<HeroDetail> getTopHeroDetail(String battleTag, int heroId) {
        return mRetrofitRequests.getHero(battleTag.replace("#", "%23"), heroId, mCurrentLocale);
    }

    public Observable<HeroDetail> setUserHeroToDB(HeroDetail heroDetail) {
        Hero currentUserHero = new Hero();
        currentUserHero.setId(heroDetail.getId());
        currentUserHero.setName(heroDetail.getName());
        currentUserHero.setHeroClass(heroDetail.getHeroClass());
        currentUserHero.setGender(heroDetail.getGender());
        currentUserHero.setHardcore(heroDetail.getHardcore());
        currentUserHero.setSeasonal(heroDetail.getSeasonal());
        currentUserHero.setRank(DEFAULT_RANK);
        currentUserHero.setLoadingProgress(false);
        mRealmDataController.createOrUpdateUserHero(currentUserHero);
        return Observable.just(heroDetail);
    }

    public Hero heroStatParse(Hero newHeroData, HeroDetail hero, List<ResponseItem> items) {
        try {
            newHeroData.setHardcore(hero.getHardcore());
            newHeroData.setSeasonal(hero.getSeasonal());
            newHeroData.setGender(hero.getGender());

            Stats stats = mRealmDataController.getRealm().createObject(Stats.class);
            stats.setLife(hero.getStats().getLife());
            stats.setDamage(hero.getStats().getDamage());
            stats.setToughness(hero.getStats().getToughness());
            stats.setHealing(hero.getStats().getLife());
            stats.setAttackSpeed(hero.getStats().getLife());
            stats.setArmor(hero.getStats().getArmor());
            stats.setStrength(hero.getStats().getStrength());
            stats.setDexterity(hero.getStats().getDexterity());
            stats.setVitality(hero.getStats().getVitality());
            stats.setIntelligence(hero.getStats().getIntelligence());
            stats.setPhysicalResist(hero.getStats().getPhysicalResist());
            stats.setFireResist(hero.getStats().getFireResist());
            stats.setColdResist(hero.getStats().getColdResist());
            stats.setLightningResist(hero.getStats().getLightningResist());
            stats.setPoisonResist(hero.getStats().getPoisonResist());
            stats.setArcaneResist(hero.getStats().getArcaneResist());
            stats.setCritDamage(hero.getStats().getCritDamage());
            stats.setBlockChance(hero.getStats().getBlockChance());
            stats.setBlockAmountMin(hero.getStats().getBlockAmountMin());
            stats.setBlockAmountMax(hero.getStats().getBlockAmountMax());
            stats.setDamageIncrease(hero.getStats().getDamageIncrease());
            stats.setCritChance(hero.getStats().getCritChance());
            stats.setDamageReduction(hero.getStats().getDamageReduction());
            stats.setLifeSteal(hero.getStats().getLifeSteal());
            stats.setLifePerKill(hero.getStats().getLifePerKill());
            stats.setGoldFind(hero.getStats().getGoldFind());
            stats.setMagicFind(hero.getStats().getMagicFind());
            stats.setLifeOnHit(hero.getStats().getLifeOnHit());
            stats.setPrimaryResource(hero.getStats().getPrimaryResource());
            stats.setSecondaryResource(hero.getStats().getSecondaryResource());

            newHeroData.setHeroStats(stats);

            RealmList<LegendaryPower> heroLegendaryPowers = new RealmList<>();
            for (Legendary legendary : hero.getLegendaryPowers()) {
                if (legendary != null) {
                    LegendaryPower legendaryPower = mRealmDataController.getRealm().createObject(LegendaryPower.class);
                    legendaryPower.setId(legendary.getId());
                    legendaryPower.setName(legendary.getName());
                    legendaryPower.setIcon(legendary.getIcon());
                    legendaryPower.setColor(legendary.getDisplayColor());
                    heroLegendaryPowers.add(legendaryPower);
                }
            }
            newHeroData.setHeroPower(heroLegendaryPowers);

            RealmList<Skill> heroSkillsActive = new RealmList<>();
            for (SkillLists skillLists : hero.getSkills().getActive()) {
                if (skillLists.getSkill() != null) {
                    Skill skill = setSkill(skillLists.getSkill());
                    if (skillLists.getRune() != null) {
                        Rune rune = mRealmDataController.getRealm().createObject(Rune.class);
                        rune.setSlug(skillLists.getRune().getSlug());
                        rune.setTitle(skillLists.getRune().getName());
                        rune.setDescription(skillLists.getRune().getDescription());
                        rune.setSimpleDescription(skillLists.getRune().getSimpleDescription());
                        skill.setRune(rune);
                    }
                    heroSkillsActive.add(skill);
                }
            }
            newHeroData.setActiveSkills(heroSkillsActive);

            RealmList<Skill> heroSkillsPassive = new RealmList<>();
            for (SkillLists skillLists : hero.getSkills().getPassive()) {
                if (skillLists.getSkill() != null) {
                    heroSkillsPassive.add(setSkill(skillLists.getSkill()));
                }
            }
            newHeroData.setPassiveSkills(heroSkillsPassive);

            RealmList<Item> heroItems = new RealmList<>();
            for (ResponseItem item : items) {
                heroItems.add(parseItem(item));
            }
            newHeroData.setHeroComplect(heroItems);

            newHeroData.setLoadingProgress(true);
            return newHeroData;

        } catch (NullPointerException ex) {
            Log.d("Hero parse", "heroStatParse: " + hero.getId());
            Log.d("Hero parse", "heroStatParse: " + ex);
            return null;
        }
    }

    private Item parseItem(ResponseItem responseItem) {
        Item item = mRealmDataController.getRealm().createObject(Item.class);
        item.setTitle(responseItem.getName());
        item.setColor(responseItem.getDisplayColor());
        item.setImageUrl(responseItem.getIcon());

        RealmList<DisplayedItemAttribute> displayedItemAttributes = new RealmList<>();
        getDisplayedParam(responseItem.getAttributes().getPrimary(), displayedItemAttributes);
        getDisplayedParam(responseItem.getAttributes().getSecondary(), displayedItemAttributes);
        getDisplayedParam(responseItem.getAttributes().getPassive(), displayedItemAttributes);

        item.setParamDescription(responseItem.getAugmentation());

        RealmList<ItemProperty> calcItemAttributes = new RealmList<>();
        getCalcParam(responseItem.getAttributesRaw(), calcItemAttributes);

        for (Gem gem : responseItem.getGems()) {
            Socket socket = mRealmDataController.getRealm().createObject(Socket.class);
            socket.setTitle(gem.getItem().getName());
            socket.setImageUrl(gem.getItem().getIcon());
            for (Map.Entry<String, List<Description>> entry : gem.getAttributes().entrySet()) {
                getDisplayedParam(entry.getValue(), displayedItemAttributes);
            }
            getCalcParam(gem.getAttributesRaw(), calcItemAttributes);
        }

        RealmList<DisplayedItemAttribute> setStats = new RealmList<>();
        if (responseItem.getSet() != null) {
            for (Ranks rank : responseItem.getSet().getRanks()) {
                for (Map.Entry<String, List<Description>> entry : rank.getAttributes().entrySet()) {
                    getDisplayedParam(entry.getValue(), setStats);
                }
            }
            item.setSetStats(setStats);
            item.setSetName(responseItem.getSet().getName());
        }
        item.setCalcStats(calcItemAttributes);
        item.setDisplayedStats(displayedItemAttributes);
        return item;
    }

    private void getDisplayedParam(List<Description> from, RealmList<DisplayedItemAttribute> to) {
        for (Description description : from) {
            DisplayedItemAttribute displayedItemAttribute = mRealmDataController.getRealm()
                    .createObject(DisplayedItemAttribute.class);
            displayedItemAttribute.setAttribute(description.getText());
            displayedItemAttribute.setColor(description.getColor());
            to.add(displayedItemAttribute);
        }
    }

    private void getCalcParam(Map<String, Property> from, RealmList<ItemProperty> to) {
        for (Map.Entry<String, Property> entry : from.entrySet()) {
            ItemProperty itemProperty = mRealmDataController.getRealm()
                    .createObject(ItemProperty.class);
            itemProperty.setAttribute(entry.getKey());
            itemProperty.setValue(entry.getValue().toString());
            to.add(itemProperty);
        }
    }

    private Skill setSkill(HeroSkill heroSkill) {
        Skill skill = mRealmDataController.getRealm().createObject(Skill.class);
        skill.setSlug(heroSkill.getSlug());
        skill.setTitle(heroSkill.getName());
        skill.setDescription(heroSkill.getDescription());
        skill.setSimpleDescription(heroSkill.getSimpleDescription());
        skill.setImageUrl(heroSkill.getIcon());
        return skill;
    }

    public Observable<Hero> getItemsList(final HeroDetail hero) {
        return getItem(hero)
                .flatMap(this::getBody)
                .toList()
                .compose(applySchedulers())
                .map(items -> mRealmDataController.updateHero(hero, items));
    }

    private <T> Observable.Transformer<T, T> applySchedulers() {
        return observable -> observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private Observable<ResponseItem> getBody(Call<ResponseItem> itemCall) {
        return Observable.create(new OnSubscribe<ResponseItem>() {
            @Override
            public void call(Subscriber<? super ResponseItem> subscriber) {
                if (!subscriber.isUnsubscribed()) {
                    try {
                        subscriber.onNext(itemCall.execute().body());
                        subscriber.onCompleted();
                    } catch (IOException e) {
                        subscriber.onError(e);
                    }
                }
            }
        });
    }

    private Observable<Call<ResponseItem>> getItem(HeroDetail hero) {
        return Observable.create(new OnSubscribe<Call<ResponseItem>>() {
            @Override
            public void call(Subscriber<? super Call<ResponseItem>> subscriber) {
                if (!subscriber.isUnsubscribed()) {
                    try {
                        if (hero.getItems() != null)
                            for (Map.Entry<String, ItemDetail> entry : hero.getItems()
                                    .entrySet()) {
                                ItemDetail value = entry.getValue();
                                checkItem(subscriber, value);
                            }
                        subscriber.onCompleted();
                    } catch (Exception e) {
                        subscriber.onError(e);
                    }
                }
            }
        });
    }

    private void checkItem(Subscriber<? super Call<ResponseItem>> subscriber, ItemDetail itemSup) {
        subscriber.onNext(mRetrofitRequests.getItem(itemSup.getTooltipParams(), mCurrentLocale));
    }

}