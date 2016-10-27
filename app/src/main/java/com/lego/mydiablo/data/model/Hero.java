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

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmClass() {
        return mClass;
    }

    public void setmClass(String mClass) {
        this.mClass = mClass;
    }

    public int getmGender() {
        return mGender;
    }

    public void setmGender(int mGender) {
        this.mGender = mGender;
    }

    public int getmLevel() {
        return mLevel;
    }

    public void setmLevel(int mLevel) {
        this.mLevel = mLevel;
    }

    public int getmParagonLevel() {
        return mParagonLevel;
    }

    public void setmParagonLevel(int mParagonLevel) {
        this.mParagonLevel = mParagonLevel;
    }

    public boolean ismHardcore() {
        return mHardcore;
    }

    public void setmHardcore(boolean mHardcore) {
        this.mHardcore = mHardcore;
    }

    public boolean ismSeasonal() {
        return mSeasonal;
    }

    public void setmSeasonal(boolean mSeasonal) {
        this.mSeasonal = mSeasonal;
    }

    public List<Skill> getmActiveSkills() {
        return mActiveSkills;
    }

    public void setmActiveSkills(RealmList<Skill> mActiveSkills) {
        this.mActiveSkills = mActiveSkills;
    }

    public RealmList<Skill> getmPassiveSkills() {
        return mPassiveSkills;
    }

    public void setmPassiveSkills(RealmList<Skill> mPassiveSkills) {
        this.mPassiveSkills = mPassiveSkills;
    }

    public RealmList<Item> getmHeroComplect() {
        return mHeroComplect;
    }

    public void setmHeroComplect(RealmList<Item> mHeroComplect) {
        this.mHeroComplect = mHeroComplect;
    }

    public Stats getmHeroStats() {
        return mHeroStats;
    }

    public void setmHeroStats(Stats mHeroStats) {
        this.mHeroStats = mHeroStats;
    }


    public RealmList<LegendaryPower> getmHeroPower() {
        return mHeroPower;
    }

    public void setmHeroPower(RealmList<LegendaryPower> mHeroPower) {
        this.mHeroPower = mHeroPower;
    }

    public String getmBattleTag() {
        return mBattleTag;
    }

    public void setmBattleTag(String mBattleTag) {
        this.mBattleTag = mBattleTag;
    }

    public String getmClanName() {
        return mClanName;
    }

    public void setmClanName(String mClanName) {
        this.mClanName = mClanName;
    }

    public String getmClanTag() {
        return mClanTag;
    }

    public void setmClanTag(String mClanTag) {
        this.mClanTag = mClanTag;
    }

    public int getmRank() {
        return mRank;
    }

    public void setmRank(int mRank) {
        this.mRank = mRank;
    }

    public int getmRiftLevel() {
        return mRiftLevel;
    }

    public void setmRiftLevel(int mRiftLevel) {
        this.mRiftLevel = mRiftLevel;
    }

    public long getmRiftTime() {
        return mRiftTime;
    }

    public void setmRiftTime(long mRiftTime) {
        this.mRiftTime = mRiftTime;
    }

    public int getSeasonValue() {
        return mSeasonValue;
    }

    public void setSeasonValue(int seasonValue) {
        mSeasonValue = seasonValue;
    }
}
