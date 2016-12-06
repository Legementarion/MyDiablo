package com.lego.mydiablo.data.model;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * @author Lego on 17.05.2016.
 */
public class Item extends RealmObject {

    private String mTitle;
    private String mType;
    private String mColor;
    private String mImageUrl;

    private RealmList<DisplayedItemAttribute> displayedStats;
    private RealmList<ItemProperty> calcStats;

    private String mSetName;
    private RealmList<DisplayedItemAttribute> setStats;

    private RealmList<Socket> mSocket;
    private String mParamDescription;  // augmentation

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

    public RealmList<DisplayedItemAttribute> getDisplayedStats() {
        return displayedStats;
    }

    public void setDisplayedStats(RealmList<DisplayedItemAttribute> displayedStats) {
        this.displayedStats = displayedStats;
    }

    public RealmList<Socket> getSocket() {
        return mSocket;
    }

    public void setSocket(RealmList<Socket> socket) {
        mSocket = socket;
    }

    public String getParamDescription() {
        return mParamDescription;
    }

    public void setParamDescription(String paramDescription) {
        mParamDescription = paramDescription;
    }

    public RealmList<ItemProperty> getCalcStats() {
        return calcStats;
    }

    public void setCalcStats(RealmList<ItemProperty> calcStats) {
        this.calcStats = calcStats;
    }

    public String getColor() {
        return mColor;
    }

    public void setColor(String color) {
        mColor = color;
    }

    public String getSetName() {
        return mSetName;
    }

    public void setSetName(String setName) {
        mSetName = setName;
    }

    public RealmList<DisplayedItemAttribute> getSetStats() {
        return setStats;
    }

    public void setSetStats(RealmList<DisplayedItemAttribute> setStats) {
        this.setStats = setStats;
    }
    
}
