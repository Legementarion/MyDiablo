package com.lego.mydiablo.rest.callback.models.UserData;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CheckedToken {
    @SerializedName("exp")
    @Expose
    private Integer exp;
    @SerializedName("user_name")
    @Expose
    private String userName;
    @SerializedName("authorities")
    @Expose
    private List<String> authorities = new ArrayList<String>();
    @SerializedName("client_id")
    @Expose
    private String clientId;
    @SerializedName("error")
    @Expose
    private String error;

    public Integer getExp() {
        return exp;
    }

    public void setExp(Integer exp) {
        this.exp = exp;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<String> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<String> authorities) {
        this.authorities = authorities;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
