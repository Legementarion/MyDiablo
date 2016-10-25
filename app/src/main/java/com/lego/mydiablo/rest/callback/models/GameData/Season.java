package com.lego.mydiablo.rest.callback.models.GameData;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Season {
    @SerializedName("current_season")
    @Expose
    private int current_season;

    public int getCurrent_season() {
        return current_season;
    }

    public void setCurrent_season(int current_season) {
        this.current_season = current_season;
    }
}
