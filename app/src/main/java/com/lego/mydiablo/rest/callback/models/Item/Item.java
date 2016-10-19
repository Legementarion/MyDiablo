package com.lego.mydiablo.rest.callback.models.Item;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Item {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("icon")
    @Expose
    private String icon;
    @SerializedName("displayColor")
    @Expose
    private String displayColor;
    @SerializedName("tooltipParams")
    @Expose
    private String tooltipParams;
    @SerializedName("requiredLevel")
    @Expose
    private Integer requiredLevel;
    @SerializedName("flavorText")
    @Expose
    private String flavorText;
    @SerializedName("typeName")
    @Expose
    private String typeName;
    @SerializedName("damageRange")
    @Expose
    private String damageRange;
    @SerializedName("attributes")
    @Expose
    private HashMap<String,Property> attributes;
    @SerializedName("dps")
    @Expose
    private Property dps;
    @SerializedName("armor")
    @Expose
    private Property armor;
    @SerializedName("gems")
    @Expose
    private List<Gem> gems = new ArrayList<>();
    @SerializedName("setItemsEquipped")
    @Expose
    private List<String> setItemsEquipped = new ArrayList<>();
    @SerializedName("description")
    @Expose
    private Object description;
    @SerializedName("blockChance")
    @Expose
    private String blockChance;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getDisplayColor() {
        return displayColor;
    }

    public void setDisplayColor(String displayColor) {
        this.displayColor = displayColor;
    }

    public String getTooltipParams() {
        return tooltipParams;
    }

    public void setTooltipParams(String tooltipParams) {
        this.tooltipParams = tooltipParams;
    }

    public Integer getRequiredLevel() {
        return requiredLevel;
    }

    public void setRequiredLevel(Integer requiredLevel) {
        this.requiredLevel = requiredLevel;
    }

    public String getFlavorText() {
        return flavorText;
    }

    public void setFlavorText(String flavorText) {
        this.flavorText = flavorText;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getDamageRange() {
        return damageRange;
    }

    public void setDamageRange(String damageRange) {
        this.damageRange = damageRange;
    }

    public Property getDps() {
        return dps;
    }

    public void setDps(Property dps) {
        this.dps = dps;
    }

    public Property getArmor() {
        return armor;
    }

    public void setArmor(Property armor) {
        this.armor = armor;
    }

    public List<Gem> getGems() {
        return gems;
    }

    public void setGems(List<Gem> gems) {
        this.gems = gems;
    }

    public List<String> getSetItemsEquipped() {
        return setItemsEquipped;
    }

    public void setSetItemsEquipped(List<String> setItemsEquipped) {
        this.setItemsEquipped = setItemsEquipped;
    }

    public Object getDescription() {
        return description;
    }

    public void setDescription(Object description) {
        this.description = description;
    }

    public String getBlockChance() {
        return blockChance;
    }

    public void setBlockChance(String blockChance) {
        this.blockChance = blockChance;
    }

    public HashMap<String, Property> getAttributes() {
        return attributes;
    }

    public void setAttributes(HashMap<String, Property> attributes) {
        this.attributes = attributes;
    }
}
