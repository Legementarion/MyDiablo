package com.lego.mydiablo.rest.callback.models.HeroDetail;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.lego.mydiablo.rest.callback.models.HeroDetail.Items.Items;
import com.lego.mydiablo.rest.callback.models.HeroDetail.LegendaryPowers.Legendary;
import com.lego.mydiablo.rest.callback.models.HeroDetail.Skills.Skills;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown=true)
public class HeroDetail {
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("class")
    @Expose
    @JsonProperty("class")
    private String heroClass;
    @SerializedName("gender")
    @Expose
    private int gender;
    @SerializedName("hardcore")
    @Expose
    private boolean hardcore;
    @SerializedName("seasonal")
    @Expose
    private boolean seasonal;
    @SerializedName("skills")
    @Expose
    private Skills skills;
    @SerializedName("items")
    @Expose
    private Items items;
//    @SerializedName("followers")
//    @Expose
//    private Followers followers;
    @SerializedName("legendaryPowers")
    @Expose
    private List<Legendary> legendaryPowers = new ArrayList<>();
    @SerializedName("stats")
    @Expose
    private HeroStats stats;

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

    public boolean getHardcore() {
        return hardcore;
    }

    public void setHardcore(boolean hardcore) {
        this.hardcore = hardcore;
    }

    public boolean getSeasonal() {
        return seasonal;
    }

    public void setSeasonal(boolean seasonal) {
        this.seasonal = seasonal;
    }

    public Skills getSkills() {
        return skills;
    }

    public void setSkills(Skills skills) {
        this.skills = skills;
    }

    public Items getItems() {
        return items;
    }

    public void setItems(Items items) {
        this.items = items;
    }

//    public Followers getFollowers() {
//        return followers;
//    }
//
//    public void setFollowers(Followers followers) {
//        this.followers = followers;
//    }

    public String getHeroClass() {
        return heroClass;
    }

    public void setHeroClass(String heroClass) {
        this.heroClass = heroClass;
    }

    public List<Legendary> getLegendaryPowers() {
        return legendaryPowers;
    }

    public void setLegendaryPowers(List<Legendary> legendaryPowers) {
        this.legendaryPowers = legendaryPowers;
    }

    public HeroStats getStats() {
        return stats;
    }

    public void setStats(HeroStats stats) {
        this.stats = stats;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }
}
