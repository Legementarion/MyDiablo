package com.lego.mydiablo.rest.callback.models.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserHeroList {
    @SerializedName("heroes")
    @Expose
    private List<UserHero> heroes = new ArrayList<>();

    public List<UserHero> getHeroes() {
        return heroes;
    }

    public void setHeroes(List<UserHero> heroes) {
        this.heroes = heroes;
    }

}
