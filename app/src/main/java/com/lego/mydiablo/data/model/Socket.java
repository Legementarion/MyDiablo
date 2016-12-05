package com.lego.mydiablo.data.model;

import io.realm.RealmList;
import io.realm.RealmObject;

public class Socket extends RealmObject{

    private String mTitle;
    private String mImageUrl;
    private String mParamDescription;

    private RealmList<ItemProperty> mAttribute;

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        mImageUrl = imageUrl;
    }

    public String getParamDescription() {
        return mParamDescription;
    }

    public void setParamDescription(String paramDescription) {
        mParamDescription = paramDescription;
    }

    public RealmList<ItemProperty> getAttribute() {
        return mAttribute;
    }

    public void setAttribute(RealmList<ItemProperty> attribute) {
        mAttribute = attribute;
    }

}
