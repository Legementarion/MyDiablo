package com.lego.mydiablo.rest.callback.models.HeroDetail;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HeroStats {
    @SerializedName("life")
    @Expose
    private Integer life;
    @SerializedName("damage")
    @Expose
    private Double damage;
    @SerializedName("toughness")
    @Expose
    private Double toughness;
    @SerializedName("healing")
    @Expose
    private Double healing;
    @SerializedName("attackSpeed")
    @Expose
    private Double attackSpeed;
    @SerializedName("armor")
    @Expose
    private Integer armor;
    @SerializedName("strength")
    @Expose
    private Integer strength;
    @SerializedName("dexterity")
    @Expose
    private Integer dexterity;
    @SerializedName("vitality")
    @Expose
    private Integer vitality;
    @SerializedName("intelligence")
    @Expose
    private Integer intelligence;
    @SerializedName("physicalResist")
    @Expose
    private Integer physicalResist;
    @SerializedName("fireResist")
    @Expose
    private Integer fireResist;
    @SerializedName("coldResist")
    @Expose
    private Integer coldResist;
    @SerializedName("lightningResist")
    @Expose
    private Integer lightningResist;
    @SerializedName("poisonResist")
    @Expose
    private Integer poisonResist;
    @SerializedName("arcaneResist")
    @Expose
    private Integer arcaneResist;
    @SerializedName("critDamage")
    @Expose
    private Double critDamage;
    @SerializedName("blockChance")
    @Expose
    private Double blockChance;
    @SerializedName("blockAmountMin")
    @Expose
    private Integer blockAmountMin;
    @SerializedName("blockAmountMax")
    @Expose
    private Integer blockAmountMax;
    @SerializedName("damageIncrease")
    @Expose
    private Double damageIncrease;
    @SerializedName("critChance")
    @Expose
    private Double critChance;
    @SerializedName("damageReduction")
    @Expose
    private Double damageReduction;
    @SerializedName("thorns")
    @Expose
    private Double thorns;
    @SerializedName("lifeSteal")
    @Expose
    private Double lifeSteal;
    @SerializedName("lifePerKill")
    @Expose
    private Double lifePerKill;
    @SerializedName("goldFind")
    @Expose
    private Double goldFind;
    @SerializedName("magicFind")
    @Expose
    private Double magicFind;
    @SerializedName("lifeOnHit")
    @Expose
    private Double lifeOnHit;
    @SerializedName("primaryResource")
    @Expose
    private Integer primaryResource;
    @SerializedName("secondaryResource")
    @Expose
    private Integer secondaryResource;

    public Integer getLife() {
        return life;
    }

    public void setLife(Integer life) {
        this.life = life;
    }

    public Double getDamage() {
        return damage;
    }

    public void setDamage(Double damage) {
        this.damage = damage;
    }

    public Double getToughness() {
        return toughness;
    }

    public void setToughness(Double toughness) {
        this.toughness = toughness;
    }

    public Double getHealing() {
        return healing;
    }

    public void setHealing(Double healing) {
        this.healing = healing;
    }

    public Double getAttackSpeed() {
        return attackSpeed;
    }

    public void setAttackSpeed(Double attackSpeed) {
        this.attackSpeed = attackSpeed;
    }

    public Integer getArmor() {
        return armor;
    }

    public void setArmor(Integer armor) {
        this.armor = armor;
    }

    public Integer getStrength() {
        return strength;
    }

    public void setStrength(Integer strength) {
        this.strength = strength;
    }

    public Integer getDexterity() {
        return dexterity;
    }

    public void setDexterity(Integer dexterity) {
        this.dexterity = dexterity;
    }

    public Integer getVitality() {
        return vitality;
    }

    public void setVitality(Integer vitality) {
        this.vitality = vitality;
    }

    public Integer getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(Integer intelligence) {
        this.intelligence = intelligence;
    }

    public Integer getPhysicalResist() {
        return physicalResist;
    }

    public void setPhysicalResist(Integer physicalResist) {
        this.physicalResist = physicalResist;
    }

    public Integer getFireResist() {
        return fireResist;
    }

    public void setFireResist(Integer fireResist) {
        this.fireResist = fireResist;
    }

    public Integer getColdResist() {
        return coldResist;
    }

    public void setColdResist(Integer coldResist) {
        this.coldResist = coldResist;
    }

    public Integer getLightningResist() {
        return lightningResist;
    }

    public void setLightningResist(Integer lightningResist) {
        this.lightningResist = lightningResist;
    }

    public Integer getPoisonResist() {
        return poisonResist;
    }

    public void setPoisonResist(Integer poisonResist) {
        this.poisonResist = poisonResist;
    }

    public Integer getArcaneResist() {
        return arcaneResist;
    }

    public void setArcaneResist(Integer arcaneResist) {
        this.arcaneResist = arcaneResist;
    }

    public Double getCritDamage() {
        return critDamage;
    }

    public void setCritDamage(Double critDamage) {
        this.critDamage = critDamage;
    }

    public Double getBlockChance() {
        return blockChance;
    }

    public void setBlockChance(Double blockChance) {
        this.blockChance = blockChance;
    }

    public Integer getBlockAmountMin() {
        return blockAmountMin;
    }

    public void setBlockAmountMin(Integer blockAmountMin) {
        this.blockAmountMin = blockAmountMin;
    }

    public Integer getBlockAmountMax() {
        return blockAmountMax;
    }

    public void setBlockAmountMax(Integer blockAmountMax) {
        this.blockAmountMax = blockAmountMax;
    }

    public Double getDamageIncrease() {
        return damageIncrease;
    }

    public void setDamageIncrease(Double damageIncrease) {
        this.damageIncrease = damageIncrease;
    }

    public Double getCritChance() {
        return critChance;
    }

    public void setCritChance(Double critChance) {
        this.critChance = critChance;
    }

    public Double getDamageReduction() {
        return damageReduction;
    }

    public void setDamageReduction(Double damageReduction) {
        this.damageReduction = damageReduction;
    }

    public Double getThorns() {
        return thorns;
    }

    public void setThorns(Double thorns) {
        this.thorns = thorns;
    }

    public Double getLifeSteal() {
        return lifeSteal;
    }

    public void setLifeSteal(Double lifeSteal) {
        this.lifeSteal = lifeSteal;
    }

    public Double getLifePerKill() {
        return lifePerKill;
    }

    public void setLifePerKill(Double lifePerKill) {
        this.lifePerKill = lifePerKill;
    }

    public Double getGoldFind() {
        return goldFind;
    }

    public void setGoldFind(Double goldFind) {
        this.goldFind = goldFind;
    }

    public Double getMagicFind() {
        return magicFind;
    }

    public void setMagicFind(Double magicFind) {
        this.magicFind = magicFind;
    }

    public Double getLifeOnHit() {
        return lifeOnHit;
    }

    public void setLifeOnHit(Double lifeOnHit) {
        this.lifeOnHit = lifeOnHit;
    }

    public Integer getPrimaryResource() {
        return primaryResource;
    }

    public void setPrimaryResource(Integer primaryResource) {
        this.primaryResource = primaryResource;
    }

    public Integer getSecondaryResource() {
        return secondaryResource;
    }

    public void setSecondaryResource(Integer secondaryResource) {
        this.secondaryResource = secondaryResource;
    }
}
