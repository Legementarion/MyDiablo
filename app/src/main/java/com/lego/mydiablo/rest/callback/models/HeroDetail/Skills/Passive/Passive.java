package com.lego.mydiablo.rest.callback.models.HeroDetail.Skills.Passive;

import com.lego.mydiablo.rest.callback.models.HeroDetail.Skills.Active.Skill;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Passive {
    @SerializedName("skill")
    @Expose
    private Skill skill;

    public Skill getSkill() {
        return skill;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }
}
