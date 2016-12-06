package com.lego.mydiablo.rest.callback.models.heroes.skills;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Skills {
    @SerializedName("active")
    @Expose
    private List<SkillLists> active = new ArrayList<>();
    @SerializedName("passive")
    @Expose
    private List<SkillLists> passive = new ArrayList<>();

    public List<SkillLists> getActive() {
        return active;
    }

    public void setActive(List<SkillLists> skillLists) {
        this.active = skillLists;
    }

    public List<SkillLists> getPassive() {
        return passive;
    }

    public void setPassive(List<SkillLists> passive) {
        this.passive = passive;
    }
}
