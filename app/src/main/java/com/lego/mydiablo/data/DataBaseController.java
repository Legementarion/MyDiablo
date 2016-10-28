package com.lego.mydiablo.data;

import com.lego.mydiablo.data.model.Hero;

import java.util.List;

import rx.Observable;

public interface DataBaseController {

    Observable<List<Hero>> saveToDatabase(List<Hero> itemList);

    void updateDatabase(Hero item);
}
