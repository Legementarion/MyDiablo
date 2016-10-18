package com.lego.mydiablo.rest.callback.models.Item;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
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
    @SerializedName("itemLevel")
    @Expose
    private Integer itemLevel;
    @SerializedName("stackSizeMax")
    @Expose
    private Integer stackSizeMax;
    @SerializedName("bonusAffixes")
    @Expose
    private Integer bonusAffixes;
    @SerializedName("bonusAffixesMax")
    @Expose
    private Integer bonusAffixesMax;
    @SerializedName("accountBound")
    @Expose
    private Boolean accountBound;
    @SerializedName("flavorText")
    @Expose
    private String flavorText;
    @SerializedName("typeName")
    @Expose
    private String typeName;
    @SerializedName("type")
    @Expose
    private Type type;
    @SerializedName("damageRange")
    @Expose
    private String damageRange;
    @SerializedName("slots")
    @Expose
    private List<String> slots = new ArrayList<String>();
    @SerializedName("attributes")
    @Expose
    private Attributes attributes;
    @SerializedName("randomAffixes")
    @Expose
    private List<Object> randomAffixes = new ArrayList<Object>();
    @SerializedName("gems")
    @Expose
    private List<Object> gems = new ArrayList<Object>();
    @SerializedName("socketEffects")
    @Expose
    private List<Object> socketEffects = new ArrayList<Object>();
    @SerializedName("setItemsEquipped")
    @Expose
    private List<String> setItemsEquipped = new ArrayList<String>();
    @SerializedName("craftedBy")
    @Expose
    private List<Object> craftedBy = new ArrayList<Object>();
    @SerializedName("seasonRequiredToDrop")
    @Expose
    private Integer seasonRequiredToDrop;
    @SerializedName("isSeasonRequiredToDrop")
    @Expose
    private Boolean isSeasonRequiredToDrop;
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

    public Integer getItemLevel() {
        return itemLevel;
    }

    public void setItemLevel(Integer itemLevel) {
        this.itemLevel = itemLevel;
    }

    public Integer getStackSizeMax() {
        return stackSizeMax;
    }

    public void setStackSizeMax(Integer stackSizeMax) {
        this.stackSizeMax = stackSizeMax;
    }

    public Integer getBonusAffixes() {
        return bonusAffixes;
    }

    public void setBonusAffixes(Integer bonusAffixes) {
        this.bonusAffixes = bonusAffixes;
    }

    public Integer getBonusAffixesMax() {
        return bonusAffixesMax;
    }

    public void setBonusAffixesMax(Integer bonusAffixesMax) {
        this.bonusAffixesMax = bonusAffixesMax;
    }

    public Boolean getAccountBound() {
        return accountBound;
    }

    public void setAccountBound(Boolean accountBound) {
        this.accountBound = accountBound;
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

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getDamageRange() {
        return damageRange;
    }

    public void setDamageRange(String damageRange) {
        this.damageRange = damageRange;
    }

    public List<String> getSlots() {
        return slots;
    }

    public void setSlots(List<String> slots) {
        this.slots = slots;
    }

    public Attributes getAttributes() {
        return attributes;
    }

    public void setAttributes(Attributes attributes) {
        this.attributes = attributes;
    }

    public List<Object> getRandomAffixes() {
        return randomAffixes;
    }

    public void setRandomAffixes(List<Object> randomAffixes) {
        this.randomAffixes = randomAffixes;
    }

    public List<Object> getGems() {
        return gems;
    }

    public void setGems(List<Object> gems) {
        this.gems = gems;
    }

    public List<Object> getSocketEffects() {
        return socketEffects;
    }

    public void setSocketEffects(List<Object> socketEffects) {
        this.socketEffects = socketEffects;
    }

    public List<String> getSetItemsEquipped() {
        return setItemsEquipped;
    }

    public void setSetItemsEquipped(List<String> setItemsEquipped) {
        this.setItemsEquipped = setItemsEquipped;
    }

    public List<Object> getCraftedBy() {
        return craftedBy;
    }

    public void setCraftedBy(List<Object> craftedBy) {
        this.craftedBy = craftedBy;
    }

    public Integer getSeasonRequiredToDrop() {
        return seasonRequiredToDrop;
    }

    public void setSeasonRequiredToDrop(Integer seasonRequiredToDrop) {
        this.seasonRequiredToDrop = seasonRequiredToDrop;
    }

    public void setSeasonRequiredToDrop(Boolean seasonRequiredToDrop) {
        isSeasonRequiredToDrop = seasonRequiredToDrop;
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
}
