package com.lego.mydiablo.rest.callback.models.Item;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Gem {
    @SerializedName("armor")
    @Expose
    private Property armor;
}
