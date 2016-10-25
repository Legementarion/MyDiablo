package com.lego.mydiablo.utils;

/**
 * @author Lego on 10.09.2016.
 */

public class Const {
    public static String CLIENT_ID = "qbsqcqpj8dwcyqs4bc5rsxgwyjx5tkue";
    public static String CLIENT_SECRET = "JcgAFKPQBxrPZGwsx3NfKrnAK2DkS9Ug";

    public static final String BASE_URL = "https://"+Const.ZONE_EU+".battle.net/";
    public static final String MEDIA_URL = "http://media.blizzard.com/";
    public static final String BASE_URL_API = "https://eu.api.battle.net/";
    public static final String AUTHORIZE_URI = "https://eu.battle.net/oauth/authorize";
    public static final String REDIRECT_URI = "https://localhost/";

    public final static String GRANT_TYPE_REFRESH = "refresh_token";
    public final static String CREDENTIALS = "&client_id=" + Const.CLIENT_ID + "&client_secret=" + Const.CLIENT_SECRET;
    public final static String GRANT_TYPE_AUTHORIZE = "authorization_code";
    public final static String REDIRECTION_URI = "&redirect_uri=";
    public final static String RESPONSE_TYPE = "?response_type=code";
    public static final String SENT_TOKEN_TO_SERVER = "sentTokenToServer";
    public static final String REGISTRATION_COMPLETE = "registrationComplete";

    public static final String DATA_PATH = "data/";
    public static final String ACCOUNT_USER = "account/user";
    public static final String D3_PROFILE = "profile/";
    public static final String D3 = "d3/";
    public static final String HERO = "/hero/";
    public static final String ITEM = "item/";
    public static final String ERA = "era/";
    public static final String SEASON = "season/";
    public static final String LEADERBOARD_RIFT = "/leaderboard/rift-";
    public static final String NO_VALUE = "";
    public static final String HARDCORE = "hardcore-";

    public static final String LOCALE_RU = "ru_RU";
    public static final String LOCALE_EN = "en_GB";

    public static final String ZONE_EU = "eu";
    public static final String ZONE_US = "us";
    public static final String ZONE_TW = "tw";
    public static final String ZONE_KR = "kr";


    public static final int SIZE = 1000;
    public static final int START_PROGRESS_VALUE = 50;
}
