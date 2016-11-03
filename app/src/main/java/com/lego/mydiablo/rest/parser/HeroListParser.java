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

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmList;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.lego.mydiablo.utils.Const.LOCALE_RU;


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

            } catch (IndexOutOfBoundsException ex) {
                Log.d("Error", "parseFromJson: array out of range cell " + i);
            }
            currentHeroList.add(currentHero);
        }
        return currentHeroList;
    }

    public void getTopHeroDetail(String battleTag, int heroId) {
        if (battleTag != null && !battleTag.equals("")) {
            mRetrofitRequests.getHero(battleTag.replace("#", "%23"), heroId, LOCALE_RU)
                    .subscribeOn(Schedulers.io())       //request
                    .observeOn(AndroidSchedulers.mainThread())      //parsing
                    .subscribe(new Subscriber<HeroDetail>() {
                        @Override
                        public void onCompleted() {
                            unsubscribe();
                        }

                        @Override
                        public void onError(Throwable e) {
                            e.printStackTrace();
                            Log.d("Error", "onError: HERO DETAIL " + e);
                        }

                        @Override
                        public void onNext(HeroDetail hero) {
                            heroStatParse(hero, heroId);
                        }
                    });
        }
    }

    private void heroStatParse(HeroDetail hero, int heroId) {
        try {
            Hero currentHero = mRealmDataController.getHero(heroId);

            Hero newHeroData = new Hero();

            newHeroData.setId(currentHero.getId());
            newHeroData.setBattleTag(currentHero.getBattleTag());
            newHeroData.setHeroClass(currentHero.getHeroClass());
            newHeroData.setGender(hero.getGender());
            newHeroData.setLevel(currentHero.getLevel());
            newHeroData.setParagonLevel(currentHero.getParagonLevel());

            if (currentHero.getClanName() != null) {
                newHeroData.setClanName(currentHero.getClanName());
                newHeroData.setClanTag(currentHero.getClanTag());
            }

            newHeroData.setHardcore(hero.getHardcore());
            newHeroData.setSeasonal(hero.getSeasonal());

            Stats stats = new Stats();
            stats.setLife(hero.getStats().getLife());
            stats.setDamage((hero.getStats().getDamage()));
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
                LegendaryPower legendaryPower = new LegendaryPower();
                legendaryPower.setId(hero.getLegendaryPowers().get(i).getId());
                legendaryPower.setName(hero.getLegendaryPowers().get(i).getName());
                legendaryPower.setIcon(hero.getLegendaryPowers().get(i).getIcon());
                legendaryPower.setColor(hero.getLegendaryPowers().get(i).getDisplayColor());
                heroLegendaryPowers.add(legendaryPower);
            }
            newHeroData.setHeroPower(heroLegendaryPowers);

            RealmList<Skill> heroSkillsActive = new RealmList<>();
            for (int i = 0; i < hero.getSkills().getActive().size(); i++) {
                if (hero.getSkills().getActive().get(i).getRune() != null) {
                    Rune rune = new Rune();
                    rune.setSlug(hero.getSkills().getActive().get(i).getRune().getSlug());
                    rune.setTitle(hero.getSkills().getActive().get(i).getRune().getName());
                    rune.setDescription(hero.getSkills().getActive().get(i).getRune().getDescription());
                    rune.setSimpleDescription(hero.getSkills().getActive().get(i).getRune().getSimpleDescription());
                    Skill skill = setSkill(hero, i);
                    skill.setRune(rune);
                    heroSkillsActive.add(skill);
                }
            }
            newHeroData.setActiveSkills(heroSkillsActive);

            RealmList<Skill> heroSkillsPassive = new RealmList<>();
            for (int i = 0; i < hero.getSkills().getPassive().size(); i++) {
                heroSkillsPassive.add(setSkill(hero, i));
            }
            newHeroData.setPassiveSkills(heroSkillsPassive);

            RealmList<Item> heroItems = new RealmList<>();
            heroItems.add(setItem(hero.getItems().getHead()));
            heroItems.add(setItem(hero.getItems().getNeck()));
            heroItems.add(setItem(hero.getItems().getShoulders()));
            heroItems.add(setItem(hero.getItems().getTorso()));
            heroItems.add(setItem(hero.getItems().getBracers()));
            heroItems.add(setItem(hero.getItems().getHands()));
            heroItems.add(setItem(hero.getItems().getWaist()));
            heroItems.add(setItem(hero.getItems().getLegs()));
            heroItems.add(setItem(hero.getItems().getFeet()));
            heroItems.add(setItem(hero.getItems().getLeftFinger()));
            heroItems.add(setItem(hero.getItems().getRightFinger()));
            heroItems.add(setItem(hero.getItems().getMainHand()));

            if (hero.getItems().getOffHand() != null) {
                heroItems.add(setItem(hero.getItems().getOffHand()));
            }
            newHeroData.setHeroComplect(heroItems);

            RealmDataController.getInstance().updateDatabase(newHeroData);

        } catch (NullPointerException ex) {
            ex.printStackTrace();
            Log.d("Hero parse", "heroStatParse: " + hero.getId());
        }
    }

    private Skill setSkill(HeroDetail hero, int i) {
        Skill skill = new Skill();
        skill.setSlug(hero.getSkills().getActive().get(i).getSkill().getSlug());
        skill.setTitle(hero.getSkills().getActive().get(i).getSkill().getName());
        skill.setDescription(hero.getSkills().getActive().get(i).getSkill().getDescription());
        skill.setSimpleDescription(hero.getSkills().getActive().get(i).getSkill().getSimpleDescription());
        skill.setImageUrl(hero.getSkills().getActive().get(i).getSkill().getIcon());
        return skill;
    }

    private Item setItem(ItemDetail itemDetail) {
        Item result_item = new Item();
        if (itemDetail != null) {
            mRetrofitRequests.getItem(itemDetail.getTooltipParams(), LOCALE_RU)
                    .subscribeOn(Schedulers.io())       //request
                    .observeOn(AndroidSchedulers.mainThread())      //parsing
                    .subscribe(new Subscriber<com.lego.mydiablo.rest.callback.models.Item.Item>() {
                        @Override
                        public void onCompleted() {
                            unsubscribe();
                        }

                        @Override
                        public void onError(Throwable e) {
                            e.printStackTrace();
                            Log.d("Error", "onError: Item DETAIL " + e);
                        }

                        @Override
                        public void onNext(com.lego.mydiablo.rest.callback.models.Item.Item item) {
                            result_item.setTitle(item.getName());
                            result_item.setImageUrl(item.getIcon());
                            result_item.setAttribute1(item.getDamageRange());
                            //FIXME item parse
                        }
                    });
        }
        Log.d("Result Item", "onNext: " + result_item.getAttribute1());
        return result_item;
    }
}
