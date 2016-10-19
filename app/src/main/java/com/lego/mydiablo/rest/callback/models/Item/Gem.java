package com.lego.mydiablo.rest.callback.models.Item;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.List;

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
}
