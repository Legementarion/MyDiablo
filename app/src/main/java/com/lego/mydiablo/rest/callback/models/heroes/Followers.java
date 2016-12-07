package com.lego.mydiablo.rest.callback.models.heroes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.lego.mydiablo.rest.callback.models.heroes.items.ItemDetail;
import com.lego.mydiablo.rest.callback.models.heroes.skills.HeroSkill;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Followers {
    @SerializedName("slug")
    @Expose
    private String slug;
    @SerializedName("items")
    @Expose
    private Map<String, ItemDetail> items = new LinkedHashMap<>();
    @SerializedName("skills")
    @Expose
    private List<HeroSkill> skills = new ArrayList<>();
    @SerializedName("stats")
    @Expose
    private HeroStats stats;

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public Map<String, ItemDetail> getItems() {
        return items;
    }

    public void setItems(
            Map<String, ItemDetail> items) {
        this.items = items;
    }

    public HeroStats getStats() {
        return stats;
    }

    public void setStats(HeroStats stats) {
        this.stats = stats;
    }

    public List<HeroSkill> getSkills() {
        return skills;
    }

    public void setSkills(List<HeroSkill> skills) {
        this.skills = skills;
    }
}
