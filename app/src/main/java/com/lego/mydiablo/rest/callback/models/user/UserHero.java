package com.lego.mydiablo.rest.callback.models.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserHero {
    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @JsonProperty("class")
    private String hClass;
    @SerializedName("gender")
    private int gender;
    @SerializedName("level")
    private int level;
    @SerializedName("paragonLevel")
    private int paragon;
    @SerializedName("hardcore")
    private boolean hardcore;
    @SerializedName("seasonal")
    private boolean seasonal;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String gethClass() {
        return hClass;
    }

    public void sethClass(String hClass) {
        this.hClass = hClass;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public boolean isHardcore() {
        return hardcore;
    }

    public void setHardcore(boolean hardcore) {
        this.hardcore = hardcore;
    }

    public boolean isSeasonal() {
        return seasonal;
    }

    public void setSeasonal(boolean seasonal) {
        this.seasonal = seasonal;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getParagon() {
        return paragon;
    }

    public void setParagon(int paragon) {
        this.paragon = paragon;
    }
}
