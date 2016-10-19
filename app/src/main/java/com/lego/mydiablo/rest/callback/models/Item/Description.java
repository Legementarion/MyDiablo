package com.lego.mydiablo.rest.callback.models.Item;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Description {
    @SerializedName("text")
    @Expose
    private String text;
    @SerializedName("color")
    @Expose
    private String color;
    @SerializedName("affixType")
    @Expose
    private String affixType;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getAffixType() {
        return affixType;
    }

    public void setAffixType(String affixType) {
        this.affixType = affixType;
    }

}
