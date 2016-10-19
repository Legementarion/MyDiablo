package com.lego.mydiablo.rest.callback.models.Item;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@JsonIgnoreProperties(ignoreUnknown=true)
public class AttributesRaw {


    @SerializedName("Strength_Item")
    @Expose
    private Property Strength_Item;
//    @SerializedName("Post_2_1_2_Drop")
//    @Expose
//    private Property Post_2_1_2_Drop;
    @SerializedName("Vitality_Item")
    @Expose
    private Property Vitality_Item;
    @SerializedName("Armor_Item")
    @Expose
    private Property Armor_Item;
    @SerializedName("Resource_Max_Bonus#Fury")
    @Expose
    private Property Resource_Max_Bonus_Fury;
    @SerializedName("Spending_Resource_Heals_Percent#Fury")
    @Expose
    private Property Spending_Resource_Heals_Percent_Fury;
    @SerializedName("Sockets")
    @Expose
    private Property Sockets;
    @SerializedName("Resistance_All")
    @Expose
    private Property Resistance_All;
    @SerializedName("Gold_Find")
    @Expose
    private Property Gold_Find;

    public Property getStrength_Item() {
        return Strength_Item;
    }

    public void setStrength_Item(Property strength_Item) {
        Strength_Item = strength_Item;
    }

    public Property getVitality_Item() {
        return Vitality_Item;
    }

    public void setVitality_Item(Property vitality_Item) {
        Vitality_Item = vitality_Item;
    }

    public Property getArmor_Item() {
        return Armor_Item;
    }

    public void setArmor_Item(Property armor_Item) {
        Armor_Item = armor_Item;
    }

    public Property getResource_Max_Bonus_Fury() {
        return Resource_Max_Bonus_Fury;
    }

    public void setResource_Max_Bonus_Fury(Property resource_Max_Bonus_Fury) {
        Resource_Max_Bonus_Fury = resource_Max_Bonus_Fury;
    }

    public Property getSpending_Resource_Heals_Percent_Fury() {
        return Spending_Resource_Heals_Percent_Fury;
    }

    public void setSpending_Resource_Heals_Percent_Fury(Property spending_Resource_Heals_Percent_Fury) {
        Spending_Resource_Heals_Percent_Fury = spending_Resource_Heals_Percent_Fury;
    }

    public Property getSockets() {
        return Sockets;
    }

    public void setSockets(Property sockets) {
        Sockets = sockets;
    }

    public Property getResistance_All() {
        return Resistance_All;
    }

    public void setResistance_All(Property resistance_All) {
        Resistance_All = resistance_All;
    }

    public Property getGold_Find() {
        return Gold_Find;
    }

    public void setGold_Find(Property gold_Find) {
        Gold_Find = gold_Find;
    }

//    public Property getPost_2_1_2_Drop() {
//        return Post_2_1_2_Drop;
//    }
//
//    public void setPost_2_1_2_Drop(Property post_2_1_2_Drop) {
//        Post_2_1_2_Drop = post_2_1_2_Drop;
//    }
}
