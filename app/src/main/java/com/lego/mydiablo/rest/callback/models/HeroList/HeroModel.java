package com.lego.mydiablo.rest.callback.models.HeroList;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class HeroModel {
    @SerializedName("key")
    @Expose
    private String key;
    @SerializedName("accountId")
    @Expose
    private Integer accountId;
    @SerializedName("data")
    @Expose
    private List<Datum> data = new ArrayList<Datum>();

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }
}
