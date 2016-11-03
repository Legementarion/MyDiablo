package com.lego.mydiablo.rest.callback.models.HeroList;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown=true)
public class HeroList {

    @SerializedName("row")
    @Expose
    private List<Row> row = new ArrayList<>();
    @SerializedName("era")
    @Expose
    private int era;
    @SerializedName("season")
    @Expose
    private int season;

    public List<Row> getRow() {
        return row;
    }

    public void setRow(List<Row> row) {
        this.row = row;
    }

    public int getEra() {
        return era;
    }

    public void setEra(int era) {
        this.era = era;
    }

    public int getSeason() {
        return season;
    }

    public void setSeason(int season) {
        this.season = season;
    }
}
