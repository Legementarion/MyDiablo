package com.lego.mydiablo.rest.callback.models.Item;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Attributes {

    @SerializedName("primary")
    @Expose
    private List<Description> primary;
    @SerializedName("secondary")
    @Expose
    private List<Description> secondary;
    @SerializedName("passive")
    @Expose
    private List<Description> passive;

    public List<Description> getPrimary() {
        return primary;
    }

    public void setPrimary(List<Description> primary) {
        this.primary = primary;
    }

    public List<Description> getSecondary() {
        return secondary;
    }

    public void setSecondary(List<Description> secondary) {
        this.secondary = secondary;
    }

    public List<Description> getPassive() {
        return passive;
    }

    public void setPassive(List<Description> passive) {
        this.passive = passive;
    }
}
