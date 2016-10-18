package com.lego.mydiablo.rest.callback.models.HeroDetail.Skills.Active;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Active {
    @SerializedName("skill")
    @Expose
    private Skill skill;
    @SerializedName("rune")
    @Expose
    private Rune rune;

    public Skill getSkill() {
        return skill;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }

    public Rune getRune() {
        return rune;
    }

    public void setRune(Rune rune) {
        this.rune = rune;
    }
}
