package com.lego.mydiablo.rest.callback.models.Item;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Gem {
    @SerializedName("item")
    @Expose
    private Params item;
    @SerializedName("isGem")
    @Expose
    private boolean isGem;
    @SerializedName("isJewel")
    @Expose
    private boolean isJewel;
    @SerializedName("attributes")
    @Expose
    private HashMap<String, List<Description>> attributes;
    @SerializedName("attributesRaw")
    @Expose
    private HashMap<String,Property> attributesRaw;

    public Params getItem() {
        return item;
    }

    public void setItem(Params item) {
        this.item = item;
    }

    public boolean isGem() {
        return isGem;
    }

    public void setGem(boolean gem) {
        isGem = gem;
    }

    public boolean isJewel() {
        return isJewel;
    }

    public void setJewel(boolean jewel) {
        isJewel = jewel;
    }

    public HashMap<String, List<Description>> getAttributes() {
        return attributes;
    }

    public void setAttributes(HashMap<String, List<Description>> attributes) {
        this.attributes = attributes;
    }

    public HashMap<String, Property> getAttributesRaw() {
        return attributesRaw;
    }

    public void setAttributesRaw(HashMap<String, Property> attributesRaw) {
        this.attributesRaw = attributesRaw;
    }
}
