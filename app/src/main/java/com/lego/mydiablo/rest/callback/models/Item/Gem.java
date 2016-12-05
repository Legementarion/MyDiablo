package com.lego.mydiablo.rest.callback.models.Item;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private Map<String, List<Description>> attributes = new HashMap<>();
    @SerializedName("attributesRaw")
    @Expose
    private Map<String,Property> attributesRaw = new HashMap<>();

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

    public Map<String, List<Description>> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, List<Description>> attributes) {
        this.attributes = attributes;
    }

    public Map<String, Property> getAttributesRaw() {
        return attributesRaw;
    }

    public void setAttributesRaw(Map<String, Property> attributesRaw) {
        this.attributesRaw = attributesRaw;
    }
}
