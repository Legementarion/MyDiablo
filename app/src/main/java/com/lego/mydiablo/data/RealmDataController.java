package com.lego.mydiablo.data;

import android.app.Activity;
import android.app.Application;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.lego.mydiablo.data.model.Hero;
import com.lego.mydiablo.data.model.Item;
import com.lego.mydiablo.data.model.Skill;
import com.lego.mydiablo.rest.callback.models.HeroDetail.HeroDetail;
import com.lego.mydiablo.rest.callback.models.Item.ResponseItem;
import com.lego.mydiablo.rest.parser.HeroListParser;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;
import rx.Observable;

import static com.lego.mydiablo.utils.Settings.mItemsPerPage;

/**
 * @author Lego on 09.09.2016.
 */

public class RealmDataController implements DataBaseController {
    private static RealmDataController instance;
    private final Realm mRealm;


    private RealmDataController(Application application) {
        mRealm = Realm.getDefaultInstance();
    }

    public static RealmDataController with(Fragment fragment) {
        return instance == null ? (instance = new RealmDataController(fragment.getActivity().getApplication())) : instance;
    }

    public static RealmDataController with(Activity activity) {
        return instance == null ? (instance = new RealmDataController(activity.getApplication())) : instance;
    }

    public static RealmDataController with(Application application) {
        return instance == null ? (instance = new RealmDataController(application)) : instance;
    }

    public static RealmDataController getInstance() {
        return instance;
    }

    public Realm getRealm() {
        return mRealm;
    }


    public Skill getSkill(String param) {
        return mRealm.where(Skill.class)
                .findFirst();
    }

    public Hero getHero(int rank) {
        return mRealm.where(Hero.class).equalTo("mRank", rank)
                .findFirst();
    }

    public List<Hero> getHeroList(String heroClass, String season) {
        RealmResults<Hero> results = mRealm.where(Hero.class).equalTo("mClass", heroClass).equalTo("mSeasonValue", Integer.valueOf(season))
                .findAllSorted("mRank", Sort.ASCENDING);
        if (results.size() < 20) {
            return results;
        } else {
            return supportFillList(results, 0, mItemsPerPage);
        }
    }

    public List<Hero> getNextHero(String heroClass, String season, int size) {
        RealmResults<Hero> results = mRealm.where(Hero.class).equalTo("mClass", heroClass).equalTo("mSeasonValue", Integer.valueOf(season))
                .findAllSorted("mRank", Sort.ASCENDING);
        if (results.isEmpty()) {
            return results;
        } else {
            return supportFillList(results, size - mItemsPerPage, size);
        }
    }

    public Item getItem(String param) {
        return mRealm.where(Item.class)
                .findFirst();
    }

    @Override
    public Observable<List<Hero>> saveToDatabase(List<Hero> itemList) {
        List<Hero> heroList = new ArrayList<>();
        mRealm.executeTransaction(realm -> {
            for (Hero hero : itemList) {
                if (hero != null) {
                    heroList.add(realm.copyToRealmOrUpdate(hero));
                }
            }
        });
        return Observable.just(supportFillList(heroList, 0, mItemsPerPage));
    }

    @Override
    public Observable<Hero> updateDatabase(Hero item) {
        mRealm.executeTransaction(realm -> {
            Hero hero = getHero(item.getRank());
            if (hero != null) {
                realm.copyToRealmOrUpdate(item).getHeroStats().getArmor();
            }
        });
        return Observable.just(item);
    }

    @Override
    public Hero updateHero(HeroDetail heroDetail, List<ResponseItem> items) {
        HeroListParser heroListParser = new HeroListParser();
        List<Hero> heroList = new ArrayList<>();
        mRealm.executeTransaction(realm -> {
            Hero hero = realm.where(Hero.class).equalTo("id", heroDetail.getId()).findFirst();
            if (hero != null) {
                heroList.add(realm.copyToRealmOrUpdate(heroListParser.heroStatParse(hero, heroDetail, items)));
            }
        });
        return heroList.get(0);
    }

    @Override
    public void createOrUpdateUserHero(Hero userHero) {
        mRealm.executeTransaction(realm ->
                realm.copyToRealmOrUpdate(userHero)
        );
    }


    private List<Hero> supportFillList(List<Hero> heroes, int from, int toSize) {
        List<Hero> heroList = new ArrayList<>();
        for (int i = from; i < toSize; i++) {
            heroList.add(heroes.get(i));
        }
        return heroList;
    }


}
