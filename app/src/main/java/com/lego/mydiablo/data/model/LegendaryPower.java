package com.lego.mydiablo.data.model;

import io.realm.RealmObject;

public class LegendaryPower extends RealmObject{

    private String mId;
    private String mName;
    private String mIcon;
    private String mColor;

    public LegendaryPower(){
        //do nothing
    }

    public String getId() {
        return mId;
    }

    public void setId(String mId) {
        this.mId = mId;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public String getIcon() {
        return mIcon;
    }

    public void setIcon(String mIcon) {
        this.mIcon = mIcon;
    }

    public String getColor() {
        return mColor;
    }

    public void setColor(String mColor) {
        this.mColor = mColor;
    }

}
