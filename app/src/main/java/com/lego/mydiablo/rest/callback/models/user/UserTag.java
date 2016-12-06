package com.lego.mydiablo.rest.callback.models.user;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserTag {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("battletag")
    @Expose
    private String battletag;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBattletag() {
        return battletag;
    }

    public void setBattletag(String battletag) {
        this.battletag = battletag;
    }
}
