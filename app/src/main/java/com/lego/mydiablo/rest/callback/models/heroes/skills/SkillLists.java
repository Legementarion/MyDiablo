package com.lego.mydiablo.rest.callback.models.heroes.skills;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SkillLists {
    @SerializedName("skill")
    @Expose
    private HeroSkill skill;
    @SerializedName("rune")
    @Expose
    private Rune rune;

    public HeroSkill getSkill() {
        return skill;
    }

    public void setSkill(HeroSkill skill) {
        this.skill = skill;
    }

    public Rune getRune() {
        return rune;
    }

    public void setRune(Rune rune) {
        this.rune = rune;
    }
}
