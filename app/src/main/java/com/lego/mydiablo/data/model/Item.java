package com.lego.mydiablo.data.model;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * @author Lego on 17.05.2016.
 */
public class Item extends RealmObject {

    private String mTitle;
    private String mType;
    private String mImageUrl;

    private int mArmor;
    private int mVitality;
    private int mStrength;

    private RealmList<Socet> mSocet;
    private String mParamDescription;

    private String mAttribute1;
    private String mAttribute2;
    private String mAttribute3;
    private String mAttribute4;
    private String mAttribute5;
    private String mAttribute6;
    private String mAttribute7;
    private String mAttribute8;
    private String mLegendaryAttribute;

    public Item(){
        //do nothing
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        mType = type;
    }

    public String getAttribute1() {
        return mAttribute1;
    }

    public void setAttribute1(String attribute1) {
        mAttribute1 = attribute1;
    }

    public String getAttribute2() {
        return mAttribute2;
    }

    public void setAttribute2(String attribute2) {
        mAttribute2 = attribute2;
    }

    public String getAttribute3() {
        return mAttribute3;
    }

    public void setAttribute3(String attribute3) {
        mAttribute3 = attribute3;
    }

    public String getAttribute4() {
        return mAttribute4;
    }

    public void setAttribute4(String attribute4) {
        mAttribute4 = attribute4;
    }

    public String getAttribute5() {
        return mAttribute5;
    }

    public void setAttribute5(String attribute5) {
        mAttribute5 = attribute5;
    }

    public String getAttribute6() {
        return mAttribute6;
    }

    public void setAttribute6(String attribute6) {
        mAttribute6 = attribute6;
    }

    public String getAttribute7() {
        return mAttribute7;
    }

    public void setAttribute7(String attribute7) {
        mAttribute7 = attribute7;
    }

    public String getAttribute8() {
        return mAttribute8;
    }

    public void setAttribute8(String attribute8) {
        mAttribute8 = attribute8;
    }

    public String getLegendaryAttribute() {
        return mLegendaryAttribute;
    }

    public void setLegendaryAttribute(String legendaryAttribute) {
        mLegendaryAttribute = legendaryAttribute;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        mImageUrl = imageUrl;
    }
}
