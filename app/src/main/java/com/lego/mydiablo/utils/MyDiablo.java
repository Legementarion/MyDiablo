package com.lego.mydiablo.utils;

import android.app.Application;
import android.content.Context;

import com.arellomobile.mvp.MvpFacade;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.exceptions.RealmMigrationNeededException;

public class MyDiablo extends Application {

    private RefWatcher refWatcher;

    @SuppressWarnings("unused")
    public static RefWatcher getRefWatcher(Context context) {
        MyDiablo application = (MyDiablo) context.getApplicationContext();
        return application.refWatcher;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        MvpFacade.init();
        refWatcher = LeakCanary.install(this);
        Realm.init(this);
    }

}
