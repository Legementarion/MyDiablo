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
    private Realm mRealm;

    public static RefWatcher getRefWatcher(Context context) {
        MyDiablo application = (MyDiablo) context.getApplicationContext();
        return application.refWatcher;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        MvpFacade.init();
        refWatcher = LeakCanary.install(this);

        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(this)
                .name(Realm.DEFAULT_REALM_NAME)
                .schemaVersion(0)
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);

        try {
            mRealm = Realm.getInstance(realmConfiguration);
        } catch (RealmMigrationNeededException e) {
            try {
                Realm.deleteRealm(realmConfiguration);
                mRealm = Realm.getInstance(realmConfiguration);
            } catch (Exception ex) {
                throw ex;
                //No Realm file to remove.
            }
        }
    }

}
