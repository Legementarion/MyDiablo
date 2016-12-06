package com.lego.mydiablo.rest.callback.models.leaderboard;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Row {
    @SerializedName("player")
    @Expose
    private List<HeroModel> player = new ArrayList<>();
    @SerializedName("order")
    @Expose
    private Integer order;
    @SerializedName("data")
    @Expose
    private List<Data> data = new ArrayList<>();


    public List<HeroModel> getPlayer() {
        return player;
    }

    public void setPlayer(List<HeroModel> player) {
        this.player = player;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

}
