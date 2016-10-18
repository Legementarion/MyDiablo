package com.lego.mydiablo.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;


public class PrefUtils {
    /**
     * Save String value
     *
     * @param context context
     * @param key     preference key
     * @param value   preference value
     */
    public static void saveToPrefs(Context context, String key, String value) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        final SharedPreferences.Editor editor = prefs.edit();
        editor.putString(key, value).apply();
    }

    /**
     * Save boolean value
     *
     * @param context context
     * @param key     preference key
     * @param value   preference value
     */
    public static void saveToPrefs(Context context, String key, boolean value) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        final SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(key, value).apply();
    }

    /**
     * Save Long value
     *
     * @param context context
     * @param key     preference key
     * @param value   preference value
     */
    public static void saveToPrefs(Context context, String key, Long value) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        final SharedPreferences.Editor editor = prefs.edit();
        editor.putLong(key, value).apply();
    }

    /**
     * Save Integer value
     *
     * @param context context
     * @param key     preference key
     * @param value   preference value
     */
    public static void saveToPrefs(Context context, String key, Integer value) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        final SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(key, value).apply();
    }

    /**
     * @param context      context
     * @param key          preference key
     * @param defaultValue default preference value
     * @return is key in shared preferences
     */
    public static boolean isInShared(Context context, String key, String defaultValue) {
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        try {
            return sharedPrefs.getString(key, defaultValue) != null;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Remove key from shared preferences
     *
     * @param context context
     * @param key     preference key
     */
    public static void removeFromPrefs(Context context, String key) {
        SharedPreferences sharedPreffs = PreferenceManager.getDefaultSharedPreferences(context);
        final SharedPreferences.Editor editor = sharedPreffs.edit();
        editor.remove(key).apply();
    }

    /**
     * Get Long value from shared preferences
     *
     * @param context      context
     * @param key          preference key
     * @param defaultValue preference default value
     * @return value by key or default value
     */
    public static Long getFromPrefs(Context context, String key, Long defaultValue) {
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        try {
            return sharedPrefs.getLong(key, defaultValue);
        } catch (Exception e) {
            e.printStackTrace();
            return defaultValue;
        }
    }

    /**
     * Get Integer value from shared preferences
     *
     * @param context      context
     * @param key          preference key
     * @param defaultValue preference default value
     * @return value by key or default value
     */
    public static Integer getFromPrefs(Context context, String key, Integer defaultValue) {
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        try {
            return sharedPrefs.getInt(key, defaultValue);
        } catch (Exception e) {
            e.printStackTrace();
            return defaultValue;
        }
    }

    /**
     * Get String value from shared preferences
     *
     * @param context      context
     * @param key          preference key
     * @param defaultValue preference default value
     * @return value by key or default value
     */
    public static String getFromPrefs(Context context, String key, String defaultValue) {
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        try {
            return sharedPrefs.getString(key, defaultValue);
        } catch (Exception e) {
            e.printStackTrace();
            return defaultValue;
        }
    }

    /**
     * Get boolean value from shared preferences
     *
     * @param context      context
     * @param key          preference key
     * @param defaultValue preference default value
     * @return value by key or default value
     */
    public static boolean getFromPrefs(Context context, String key, boolean defaultValue) {
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        try {
            return sharedPrefs.getBoolean(key, defaultValue);
        } catch (Exception e) {
            e.printStackTrace();
            return defaultValue;
        }
    }
}
