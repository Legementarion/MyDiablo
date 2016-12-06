package com.lego.mydiablo.rest.callback.models.Item;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Set {
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("ranks")
    @Expose
    private List<Ranks> ranks = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Ranks> getRanks() {
        return ranks;
    }

    public void setRanks(List<Ranks> ranks) {
        this.ranks = ranks;
    }

}
