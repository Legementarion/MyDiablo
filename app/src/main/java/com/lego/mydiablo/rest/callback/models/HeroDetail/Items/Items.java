package com.lego.mydiablo.rest.callback.models.HeroDetail.Items;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Items {
    @SerializedName("head")
    @Expose
    private ItemDetail head;
    @SerializedName("torso")
    @Expose
    private ItemDetail torso;
    @SerializedName("feet")
    @Expose
    private ItemDetail feet;
    @SerializedName("hands")
    @Expose
    private ItemDetail hands;
    @SerializedName("shoulders")
    @Expose
    private ItemDetail shoulders;
    @SerializedName("legs")
    @Expose
    private ItemDetail legs;
    @SerializedName("bracers")
    @Expose
    private ItemDetail bracers;
    @SerializedName("mainHand")
    @Expose
    private ItemDetail mainHand;
    @SerializedName("offHand")
    @Expose
    private ItemDetail offHand;
    @SerializedName("waist")
    @Expose
    private ItemDetail waist;
    @SerializedName("rightFinger")
    @Expose
    private ItemDetail rightFinger;
    @SerializedName("leftFinger")
    @Expose
    private ItemDetail leftFinger;
    @SerializedName("neck")
    @Expose
    private ItemDetail neck;

    public ItemDetail getHead() {
        return head;
    }

    public void setHead(ItemDetail head) {
        this.head = head;
    }

    public ItemDetail getTorso() {
        return torso;
    }

    public void setTorso(ItemDetail torso) {
        this.torso = torso;
    }

    public ItemDetail getFeet() {
        return feet;
    }

    public void setFeet(ItemDetail feet) {
        this.feet = feet;
    }

    public ItemDetail getHands() {
        return hands;
    }

    public void setHands(ItemDetail hands) {
        this.hands = hands;
    }

    public ItemDetail getShoulders() {
        return shoulders;
    }

    public void setShoulders(ItemDetail shoulders) {
        this.shoulders = shoulders;
    }

    public ItemDetail getLegs() {
        return legs;
    }

    public void setLegs(ItemDetail legs) {
        this.legs = legs;
    }

    public ItemDetail getBracers() {
        return bracers;
    }

    public void setBracers(ItemDetail bracers) {
        this.bracers = bracers;
    }

    public ItemDetail getMainHand() {
        return mainHand;
    }

    public void setMainHand(ItemDetail mainHand) {
        this.mainHand = mainHand;
    }

    public ItemDetail getWaist() {
        return waist;
    }

    public void setWaist(ItemDetail waist) {
        this.waist = waist;
    }

    public ItemDetail getRightFinger() {
        return rightFinger;
    }

    public void setRightFinger(ItemDetail rightFinger) {
        this.rightFinger = rightFinger;
    }

    public ItemDetail getLeftFinger() {
        return leftFinger;
    }

    public void setLeftFinger(ItemDetail leftFinger) {
        this.leftFinger = leftFinger;
    }

    public ItemDetail getNeck() {
        return neck;
    }

    public void setNeck(ItemDetail neck) {
        this.neck = neck;
    }

    public ItemDetail getOffHand() {
        return offHand;
    }

    public void setOffHand(ItemDetail offHand) {
        this.offHand = offHand;
    }
}
