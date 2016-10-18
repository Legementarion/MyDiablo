package com.lego.mydiablo.rest.callback.models.HeroList;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class HeroList {

    @SerializedName("row")
    @Expose
    private List<Row> row = new ArrayList<>();
    @SerializedName("era")
    @Expose
    private int era;

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

}
