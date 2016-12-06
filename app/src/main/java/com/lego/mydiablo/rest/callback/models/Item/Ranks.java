package com.lego.mydiablo.rest.callback.models.Item;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Ranks {
    @SerializedName("required")
    @Expose
    private String required;
    @SerializedName("attributes")
    @Expose
    private Map<String, List<Description>> attributes = new HashMap<>();
    @SerializedName("attributesRaw")
    @Expose
    private Map<String, Property> attributesRaw = new HashMap<>();

    public String getRequired() {
        return required;
    }

    public void setRequired(String required) {
        this.required = required;
    }

    public Map<String, List<Description>> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, List<Description>> attributes) {
        this.attributes = attributes;
    }

    public Map<String, Property> getAttributesRaw() {
        return attributesRaw;
    }

    public void setAttributesRaw(Map<String, Property> attributesRaw) {
        this.attributesRaw = attributesRaw;
    }

}
