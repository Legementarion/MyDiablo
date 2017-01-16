package com.lego.mydiablo.data.model;

import io.realm.RealmObject;

public class Stats extends RealmObject {

    private int mLife;
    private double mDamage;
    private double mToughness;
    private double mHealing;
    private double mAttackSpeed;
    private int mArmor;
    private int mStrength;
    private int mDexterity;
    private int mVitality;
    private int mIntelligence;
    private int mPhysicalResist;
    private int mFireResist;
    private int mColdResist;
    private int mLightningResist;
    private int mPoisonResist;
    private int mArcaneResist;
    private double mCritDamage;
    private double mBlockChance;
    private double mBlockAmountMin;
    private double mBlockAmountMax;
    private double mDamageIncrease;
    private double mCritChance;
    private double mDamageReduction;
    private double mLifeSteal;
    private double mLifePerKill;
    private double mGoldFind;
    private double mMagicFind;
    private double mLifeOnHit;
    private int mPrimaryResource;
    private int mSecondaryResource;

    public Stats(){}


    public int getLife() {
        return mLife;
    }

    public void setLife(int life) {
        mLife = life;
    }

    public double getDamage() {
        return mDamage;
    }

    public void setDamage(double damage) {
        mDamage = damage;
    }

    public double getToughness() {
        return mToughness;
    }

    public void setToughness(double toughness) {
        mToughness = toughness;
    }

    public double getHealing() {
        return mHealing;
    }

    public void setHealing(double healing) {
        mHealing = healing;
    }

    public double getAttackSpeed() {
        return mAttackSpeed;
    }

    public void setAttackSpeed(double attackSpeed) {
        mAttackSpeed = attackSpeed;
    }

    public int getArmor() {
        return mArmor;
    }

    public void setArmor(int armor) {
        mArmor = armor;
    }

    public int getStrength() {
        return mStrength;
    }

    public void setStrength(int strength) {
        mStrength = strength;
    }

    public int getDexterity() {
        return mDexterity;
    }

    public void setDexterity(int dexterity) {
        mDexterity = dexterity;
    }

    public int getVitality() {
        return mVitality;
    }

    public void setVitality(int vitality) {
        mVitality = vitality;
    }

    public int getIntelligence() {
        return mIntelligence;
    }

    public void setIntelligence(int intelligence) {
        mIntelligence = intelligence;
    }

    public int getPhysicalResist() {
        return mPhysicalResist;
    }

    public void setPhysicalResist(int physicalResist) {
        mPhysicalResist = physicalResist;
    }

    public int getFireResist() {
        return mFireResist;
    }

    public void setFireResist(int fireResist) {
        mFireResist = fireResist;
    }

    public int getColdResist() {
        return mColdResist;
    }

    public void setColdResist(int coldResist) {
        mColdResist = coldResist;
    }

    public int getLightningResist() {
        return mLightningResist;
    }

    public void setLightningResist(int lightningResist) {
        mLightningResist = lightningResist;
    }

    public int getPoisonResist() {
        return mPoisonResist;
    }

    public void setPoisonResist(int poisonResist) {
        mPoisonResist = poisonResist;
    }

    public int getArcaneResist() {
        return mArcaneResist;
    }

    public void setArcaneResist(int arcaneResist) {
        mArcaneResist = arcaneResist;
    }

    public double getCritDamage() {
        return mCritDamage;
    }

    public void setCritDamage(double critDamage) {
        mCritDamage = critDamage;
    }

    public double getBlockChance() {
        return mBlockChance;
    }

    public void setBlockChance(double blockChance) {
        mBlockChance = blockChance;
    }

    public double getBlockAmountMin() {
        return mBlockAmountMin;
    }

    public void setBlockAmountMin(double blockAmountMin) {
        mBlockAmountMin = blockAmountMin;
    }

    public double getBlockAmountMax() {
        return mBlockAmountMax;
    }

    public void setBlockAmountMax(double blockAmountMax) {
        mBlockAmountMax = blockAmountMax;
    }

    public double getDamageIncrease() {
        return mDamageIncrease;
    }

    public void setDamageIncrease(double damageIncrease) {
        mDamageIncrease = damageIncrease;
    }

    public double getCritChance() {
        return mCritChance;
    }

    public void setCritChance(double critChance) {
        mCritChance = critChance;
    }

    public double getDamageReduction() {
        return mDamageReduction;
    }

    public void setDamageReduction(double damageReduction) {
        mDamageReduction = damageReduction;
    }

    public double getLifeSteal() {
        return mLifeSteal;
    }

    public void setLifeSteal(double lifeSteal) {
        mLifeSteal = lifeSteal;
    }

    public double getLifePerKill() {
        return mLifePerKill;
    }

    public void setLifePerKill(double lifePerKill) {
        mLifePerKill = lifePerKill;
    }

    public double getGoldFind() {
        return mGoldFind;
    }

    public void setGoldFind(double goldFind) {
        mGoldFind = goldFind;
    }

    public double getMagicFind() {
        return mMagicFind;
    }

    public void setMagicFind(double magicFind) {
        mMagicFind = magicFind;
    }

    public double getLifeOnHit() {
        return mLifeOnHit;
    }

    public void setLifeOnHit(double lifeOnHit) {
        mLifeOnHit = lifeOnHit;
    }

    public int getPrimaryResource() {
        return mPrimaryResource;
    }

    public void setPrimaryResource(int primaryResource) {
        mPrimaryResource = primaryResource;
    }

    public int getSecondaryResource() {
        return mSecondaryResource;
    }

    public void setSecondaryResource(int secondaryResource) {
        mSecondaryResource = secondaryResource;
    }
}
