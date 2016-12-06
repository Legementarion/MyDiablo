package com.lego.mydiablo.rest.callback.models.game;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Era {
    @SerializedName("current_era")
    @Expose
    private int current_era;

    public int getCurrent_era() {
        return current_era;
    }

    public void setCurrent_era(int current_era) {
        this.current_era = current_era;
    }
}
