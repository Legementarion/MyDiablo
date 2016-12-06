package com.lego.mydiablo.rest.callback.models.leaderboard;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Datum {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("string")
    @Expose
    private String string;
    @SerializedName("number")
    @Expose
    private Integer number;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}
