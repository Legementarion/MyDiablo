package com.lego.mydiablo.data.model;


import io.realm.RealmObject;

public class Socet extends RealmObject{

    private String mTitle;
    private String mImageUrl;
    private String mParamDescription;

    private int mAttribute;

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

    public int getAttribute() {
        return mAttribute;
    }

    public void setAttribute(int attribute) {
        mAttribute = attribute;
    }
}
