package com.lego.mydiablo.rest.callback.models.HeroDetail.Skills;


import com.lego.mydiablo.rest.callback.models.HeroDetail.Skills.Active.Active;
import com.lego.mydiablo.rest.callback.models.HeroDetail.Skills.Passive.Passive;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Skills {
    @SerializedName("active")
    @Expose
    private List<Active> active = new ArrayList<>();
    @SerializedName("passive")
    @Expose
    private List<Passive> passive = new ArrayList<>();

    public List<Active> getActive() {
        return active;
    }

    public void setActive(List<Active> active) {
        this.active = active;
    }

    public List<Passive> getPassive() {
        return passive;
    }

    public void setPassive(List<Passive> passive) {
        this.passive = passive;
    }
}
