package com.lego.mydiablo.data.model;

import io.realm.RealmObject;

public class DisplayedItemAttribute extends RealmObject {

    private String mAttribute;
    private String mColor;

    public String getAttribute() {
        return mAttribute;
    }

    public void setAttribute(String attribute) {
        mAttribute = attribute;
    }

    public String getColor() {
        return mColor;
    }

    public void setColor(String color) {
        mColor = color;
    }

}
