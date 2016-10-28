package com.lego.mydiablo.data.model;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Hero extends RealmObject {

    @PrimaryKey
    private int mRank;
    private int id; //hero id
    private String mName;
    private String mBattleTag;
    private String mClass;
    private int mGender;
    private int mLevel;
    private int mParagonLevel;
    private String mClanName;
    private String mClanTag;
    private int mRiftLevel;
    private long mRiftTime;

    private int mSeasonValue;
    private boolean mHardcore;
    private boolean mSeasonal;

    private RealmList<Skill> mActiveSkills = new RealmList<>();
    private RealmList<Skill> mPassiveSkills = new RealmList<>();
    private RealmList<Item> mHeroComplect = new RealmList<>();

    private Stats mHeroStats;
    private RealmList<LegendaryPower> mHeroPower = new RealmList<>();

    public Hero(){
        // Do nothing
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public String getHeroClass() {
        return mClass;
    }

    public void setHeroClass(String mClass) {
        this.mClass = mClass;
    }

    public int getGender() {
        return mGender;
    }

    public void setGender(int mGender) {
        this.mGender = mGender;
    }

    public int getLevel() {
        return mLevel;
    }

    public void setLevel(int mLevel) {
        this.mLevel = mLevel;
    }

    public int getParagonLevel() {
        return mParagonLevel;
    }

    public void setParagonLevel(int mParagonLevel) {
        this.mParagonLevel = mParagonLevel;
    }

    public boolean isHardcore() {
        return mHardcore;
    }

    public void setHardcore(boolean mHardcore) {
        this.mHardcore = mHardcore;
    }

    public boolean isSeasonal() {
        return mSeasonal;
    }

    public void setSeasonal(boolean mSeasonal) {
        this.mSeasonal = mSeasonal;
    }

    public List<Skill> getActiveSkills() {
        return mActiveSkills;
    }

    public void setActiveSkills(RealmList<Skill> mActiveSkills) {
        this.mActiveSkills = mActiveSkills;
    }

    public RealmList<Skill> getPassiveSkills() {
        return mPassiveSkills;
    }

    public void setPassiveSkills(RealmList<Skill> mPassiveSkills) {
        this.mPassiveSkills = mPassiveSkills;
    }

    public RealmList<Item> getHeroComplect() {
        return mHeroComplect;
    }

    public void setHeroComplect(RealmList<Item> mHeroComplect) {
        this.mHeroComplect = mHeroComplect;
    }

    public Stats getHeroStats() {
        return mHeroStats;
    }

    public void setHeroStats(Stats mHeroStats) {
        this.mHeroStats = mHeroStats;
    }


    public RealmList<LegendaryPower> getHeroPower() {
        return mHeroPower;
    }

    public void setHeroPower(RealmList<LegendaryPower> mHeroPower) {
        this.mHeroPower = mHeroPower;
    }

    public String getBattleTag() {
        return mBattleTag;
    }

    public void setBattleTag(String mBattleTag) {
        this.mBattleTag = mBattleTag;
    }

    public String getClanName() {
        return mClanName;
    }

    public void setClanName(String mClanName) {
        this.mClanName = mClanName;
    }

    public String getClanTag() {
        return mClanTag;
    }

    public void setClanTag(String mClanTag) {
        this.mClanTag = mClanTag;
    }

    public int getRank() {
        return mRank;
    }

    public void setRank(int mRank) {
        this.mRank = mRank;
    }

    public int getRiftLevel() {
        return mRiftLevel;
    }

    public void setRiftLevel(int mRiftLevel) {
        this.mRiftLevel = mRiftLevel;
    }

    public long getRiftTime() {
        return mRiftTime;
    }

    public void setRiftTime(long mRiftTime) {
        this.mRiftTime = mRiftTime;
    }

    public int getSeasonValue() {
        return mSeasonValue;
    }

    public void setSeasonValue(int seasonValue) {
        mSeasonValue = seasonValue;
    }
}
