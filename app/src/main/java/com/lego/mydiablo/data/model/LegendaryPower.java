package com.lego.mydiablo.data.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by PlayWeb-11 on 9/29/2016.
 */

public class LegendaryPower extends RealmObject{

    @PrimaryKey
    private String mId;
    private String mName;
    private String mIcon;
    private String mColor;

    public LegendaryPower(){}

    public String getmId() {
        return mId;
    }

    public void setmId(String mId) {
        this.mId = mId;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmIcon() {
        return mIcon;
    }

    public void setmIcon(String mIcon) {
        this.mIcon = mIcon;
    }

    public String getmColor() {
        return mColor;
    }

    public void setmColor(String mColor) {
        this.mColor = mColor;
    }
}
