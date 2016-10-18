package com.lego.mydiablo.rest.callback.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SeasonList {

    @SerializedName("current_season")
    @Expose
    private Integer currentSeason;
    @SerializedName("service_current_season")
    @Expose
    private Integer serviceCurrentSeason;
    @SerializedName("service_season_state")
    @Expose
    private String serviceSeasonState;
    @SerializedName("last_update_time")
    @Expose
    private String lastUpdateTime;


    public Integer getCurrentSeason() {
        return currentSeason;
    }

    public void setCurrentSeason(Integer currentSeason) {
        this.currentSeason = currentSeason;
    }

    public Integer getServiceCurrentSeason() {
        return serviceCurrentSeason;
    }

    public void setServiceCurrentSeason(Integer serviceCurrentSeason) {
        this.serviceCurrentSeason = serviceCurrentSeason;
    }

    public String getServiceSeasonState() {
        return serviceSeasonState;
    }

    public void setServiceSeasonState(String serviceSeasonState) {
        this.serviceSeasonState = serviceSeasonState;
    }

    public String getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(String lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }
}
