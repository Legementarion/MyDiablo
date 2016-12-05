package com.lego.mydiablo.data.model;

import io.realm.RealmObject;

public class ItemProperty extends RealmObject{

    private String mAttribute;
    private String mValue;

    public String getAttribute() {
        return mAttribute;
    }

    public void setAttribute(String attribute) {
        mAttribute = attribute;
    }

    public String getValue() {
        return mValue;
    }

    public void setValue(String value) {
        mValue = value;
    }
}
