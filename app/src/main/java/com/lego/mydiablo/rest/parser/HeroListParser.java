package com.lego.mydiablo.rest.parser;

import android.util.Log;

import com.lego.mydiablo.data.RealmDataController;
import com.lego.mydiablo.data.model.Hero;
import com.lego.mydiablo.data.model.Item;
import com.lego.mydiablo.data.model.LegendaryPower;
import com.lego.mydiablo.data.model.Rune;
import com.lego.mydiablo.data.model.Skill;
import com.lego.mydiablo.data.model.Stats;
import com.lego.mydiablo.rest.RetrofitRequests;
import com.lego.mydiablo.rest.callback.models.HeroDetail.HeroDetail;
import com.lego.mydiablo.rest.callback.models.HeroDetail.Items.ItemDetail;
import com.lego.mydiablo.rest.callback.models.HeroList.HeroList;
import com.lego.mydiablo.rest.callback.models.Item.ResponseItem;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.realm.RealmList;
import retrofit2.Call;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.lego.mydiablo.utils.Const.DEFAULT_RANK;
import static com.lego.mydiablo.utils.ImgUtils.castGender;
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
        for (int i = 0; i < heroList.getRow().size(); i++) {
            Hero currentHero = new Hero();
            try {
                if (heroList.getRow().get(i).getPlayer().get(0).getData().get(0).getId().equals("HeroBattleTag")) {
                    currentHero.setBattleTag(heroList.getRow().get(i).getPlayer().get(0).getData().get(0).getString());    //player data
                    currentHero.setHeroClass(heroList.getRow().get(i).getPlayer().get(0).getData().get(2).getString());
                    currentHero.setGender(castGender(heroList.getRow().get(i).getPlayer().get(0).getData().get(3).getString()));
                    currentHero.setLevel(heroList.getRow().get(i).getPlayer().get(0).getData().get(4).getNumber());
                    if (heroList.getRow().get(i).getPlayer().get(0).getData().get(5).getNumber() != null) {
                        currentHero.setParagonLevel(heroList.getRow().get(i).getPlayer().get(0).getData().get(5).getNumber());
                    }
                    if (heroList.getRow().get(i).getPlayer().get(0).getData().get(6).getId().equals("HeroClanTag")) {
                        currentHero.setClanTag(heroList.getRow().get(i).getPlayer().get(0).getData().get(6).getString());
                        currentHero.setClanName(heroList.getRow().get(i).getPlayer().get(0).getData().get(7).getString());
                        currentHero.setId(heroList.getRow().get(i).getPlayer().get(0).getData().get(8).getNumber());    // if era = 1 and query = barbarian -> Error field doesn't exist
                    } else {
                        currentHero.setId(heroList.getRow().get(i).getPlayer().get(0).getData().get(6).getNumber());
                    }
                } else {
                    currentHero.setHeroClass(heroList.getRow().get(i).getPlayer().get(0).getData().get(1).getString());
                    currentHero.setLevel(heroList.getRow().get(i).getPlayer().get(0).getData().get(3).getNumber());
                    if (heroList.getRow().get(i).getPlayer().get(0).getData().get(4).getNumber() != null) {
                        currentHero.setParagonLevel(heroList.getRow().get(i).getPlayer().get(0).getData().get(4).getNumber());
                    }
                    if (heroList.getRow().get(i).getPlayer().get(0).getData().get(5).getId().equals("HeroClanTag")) {
                        currentHero.setClanTag(heroList.getRow().get(i).getPlayer().get(0).getData().get(5).getString());
                        currentHero.setClanName(heroList.getRow().get(i).getPlayer().get(0).getData().get(6).getString());
                        currentHero.setId(heroList.getRow().get(i).getPlayer().get(0).getData().get(7).getNumber());
                    } else {
                        currentHero.setId(heroList.getRow().get(i).getPlayer().get(0).getData().get(5).getNumber());
                    }
                }

                if (heroList.getEra() != 0) {
                    currentHero.setSeasonValue(heroList.getEra());
                } else {
                    currentHero.setSeasonValue(heroList.getSeason());
                }
                currentHero.setRank(heroList.getRow().get(i).getData().get(0).getNumber());     //other data
                currentHero.setRiftLevel(heroList.getRow().get(i).getData().get(1).getNumber());
                currentHero.setRiftTime(heroList.getRow().get(i).getData().get(2).getTimestamp());

                currentHero.setLoadingProgress(false);

            } catch (IndexOutOfBoundsException ex) {
                Log.d("Error", "parseFromJson: array out of range cell " + i);
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
            for (int i = 0; i < hero.getLegendaryPowers().size(); i++) {
                if (hero.getLegendaryPowers().get(i) != null) {
                    LegendaryPower legendaryPower = mRealmDataController.getRealm().createObject(LegendaryPower.class);
                    legendaryPower.setId(hero.getLegendaryPowers().get(i).getId());
                    legendaryPower.setName(hero.getLegendaryPowers().get(i).getName());
                    legendaryPower.setIcon(hero.getLegendaryPowers().get(i).getIcon());
                    legendaryPower.setColor(hero.getLegendaryPowers().get(i).getDisplayColor());
                    heroLegendaryPowers.add(legendaryPower);
                }
            }
            newHeroData.setHeroPower(heroLegendaryPowers);

            RealmList<Skill> heroSkillsActive = new RealmList<>();
            for (int i = 0; i < hero.getSkills().getActive().size(); i++) {
                if (hero.getSkills().getActive().get(i).getSkill() != null) {
                    Skill skill = setSkill(hero, i);
                    if (hero.getSkills().getActive().get(i).getRune() != null) {
                        Rune rune = mRealmDataController.getRealm().createObject(Rune.class);
                        rune.setSlug(hero.getSkills().getActive().get(i).getRune().getSlug());
                        rune.setTitle(hero.getSkills().getActive().get(i).getRune().getName());
                        rune.setDescription(hero.getSkills().getActive().get(i).getRune().getDescription());
                        rune.setSimpleDescription(hero.getSkills().getActive().get(i).getRune().getSimpleDescription());
                        skill.setRune(rune);
                    }
                    heroSkillsActive.add(skill);
                }
            }

            newHeroData.setActiveSkills(heroSkillsActive);

            RealmList<Skill> heroSkillsPassive = new RealmList<>();
            for (int i = 0; i < hero.getSkills().getPassive().size(); i++) {
                if (hero.getSkills().getPassive().get(i).getSkill() != null) {
                    heroSkillsPassive.add(setSkill(hero, i));
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
            Log.d("Hero parse", "heroStatParse: " + ex.getMessage());
            return null;
        }
    }

    private Item parseItem(ResponseItem responseItem) {
        Item item = mRealmDataController.getRealm().createObject(Item.class);
        item.setTitle(responseItem.getName());
//        item.setType(); TODO PARSE PARAM
        item.setImageUrl(responseItem.getIcon());
        return item;
    }

    private Skill setSkill(HeroDetail hero, int i) {
        Skill skill = mRealmDataController.getRealm().createObject(Skill.class);
        skill.setSlug(hero.getSkills().getActive().get(i).getSkill().getSlug());
        skill.setTitle(hero.getSkills().getActive().get(i).getSkill().getName());
        skill.setDescription(hero.getSkills().getActive().get(i).getSkill().getDescription());
        skill.setSimpleDescription(hero.getSkills().getActive().get(i).getSkill().getSimpleDescription());
        skill.setImageUrl(hero.getSkills().getActive().get(i).getSkill().getIcon());
        return skill;
    }

    public Observable<Hero> getItemsList(final HeroDetail hero) {
        return getItem(hero)
                .flatMap(this::getBody)
                .toList()
                .compose(applySchedulers())
                .map(items -> transform(items, hero));
    }

    private <T> Observable.Transformer<T, T> applySchedulers() {
        return observable -> observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private Hero transform(List<ResponseItem> items, HeroDetail detail) {
        return mRealmDataController.updateHero(detail, items);
    }

    private Observable<ResponseItem> getBody(Call<ResponseItem> itemCall) {
        return Observable.create(new Observable.OnSubscribe<ResponseItem>() {
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
        return Observable.create(new Observable.OnSubscribe<Call<ResponseItem>>() {
            @Override
            public void call(Subscriber<? super Call<ResponseItem>> subscriber) {
                if (!subscriber.isUnsubscribed()) {
                    try {
                        if (hero.getItems() != null) {
                            for (Map.Entry<String, ItemDetail> entry : hero.getItems().entrySet()) {
                                String key = entry.getKey();    //bla bla bla for future
                                ItemDetail value = entry.getValue();
                                checkItem(subscriber, value);
                            }
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
