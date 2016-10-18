package com.lego.mydiablo.rest.callback.models.Item;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Property {
    @SerializedName("min")
    @Expose
    private double min;
    @SerializedName("max")
    @Expose
    private double max;

    public double getMin() {
        return min;
    }

    public void setMin(double min) {
        this.min = min;
    }

    public double getMax() {
        return max;
    }

    public void setMax(double max) {
        this.max = max;
    }
}
