package com.lego.mydiablo.rest.callback.models.heroes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.lego.mydiablo.rest.callback.models.heroes.items.ItemDetail;
import com.lego.mydiablo.rest.callback.models.heroes.legendary.Legendary;
import com.lego.mydiablo.rest.callback.models.heroes.skills.Skills;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
    private Map<String, ItemDetail> items = new LinkedHashMap<>();
    @SerializedName("followers")
    @Expose
    private Map<String, Followers> followers = new LinkedHashMap<>();
    @SerializedName("legendaryPowers")
    @Expose
    private List<Legendary> legendaryPowers = new ArrayList<>();
    @SerializedName("stats")
    @Expose
    private HeroStats stats;

    public Map<String, ItemDetail> getItems() {
        return items;
    }

    public void setItems(Map<String, ItemDetail> items) {
        this.items = items;
    }
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

    public Map<String, Followers> getFollowers() {
        return followers;
    }

    public void setFollowers(
            Map<String, Followers> followers) {
        this.followers = followers;
    }
}
