package com.lego.mydiablo.rest.callback.models.HeroList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Data {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("number")
    @Expose
    private Integer number;
    @SerializedName("timestamp")
    @Expose
    private long timestamp;
    @SerializedName("string")
    @Expose
    private String string;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }
}
