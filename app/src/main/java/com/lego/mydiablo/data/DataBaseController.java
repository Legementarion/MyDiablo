package com.lego.mydiablo.data;

import com.lego.mydiablo.data.model.Hero;
import com.lego.mydiablo.rest.callback.models.heroes.HeroDetail;
import com.lego.mydiablo.rest.callback.models.item.ResponseItem;

import java.util.List;

import rx.Observable;

public interface DataBaseController {
    Observable<List<Hero>> saveToDatabase(List<Hero> itemList);

    Observable<Hero> updateDatabase(Hero item);

    Hero updateHero(HeroDetail heroDetail, List<ResponseItem> items);

    void createOrUpdateUserHero(Hero hero);
}
