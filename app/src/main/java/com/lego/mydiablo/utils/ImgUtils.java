package com.lego.mydiablo.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.lego.mydiablo.R;

public class ImgUtils {

    private ImgUtils() {
    }

    public static Drawable pickImage(Context context, String s) {
        switch (s) {
            case "demon hunter":
                return context.getResources().getDrawable(context.getResources().getIdentifier("dh", context.getString(R.string.drawable),
                        context.getPackageName()));

            case "witch doctor":
                return context.getResources().getDrawable(context.getResources().getIdentifier("wd", context.getString(R.string.drawable),
                        context.getPackageName()));

            default:
                return context.getResources().getDrawable(context.getResources().getIdentifier(s, context.getString(R.string.drawable),
                        context.getPackageName()));
        }
    }

    public static Drawable pickImageDash(Context context, String s) {
        switch (s) {
            case "demon-hunter":
                return context.getResources().getDrawable(context.getResources().getIdentifier("dh", context.getString(R.string.drawable),
                        context.getPackageName()));
            case "witch-doctor":
                return context.getResources().getDrawable(context.getResources().getIdentifier("wd", context.getString(R.string.drawable),
                        context.getPackageName()));
            default:
                return context.getResources().getDrawable(context.getResources().getIdentifier(s, context.getString(R.string.drawable),
                        context.getPackageName()));
        }
    }

    public static Drawable pickHeroIcon(Context context, String s) {
        switch (s) {
            case "demon hunter_m":
                return context.getResources().getDrawable(context.getResources().getIdentifier("dh_m", context.getString(R.string.drawable),
                        context.getPackageName()));
            case "demon hunter_f":
                return context.getResources().getDrawable(context.getResources().getIdentifier("dh_f", context.getString(R.string.drawable),
                        context.getPackageName()));
            case "witch doctor_m":
                return context.getResources().getDrawable(context.getResources().getIdentifier("wd_m", context.getString(R.string.drawable),
                        context.getPackageName()));
            case "witch doctor_f":
                return context.getResources().getDrawable(context.getResources().getIdentifier("wd_f", context.getString(R.string.drawable),
                        context.getPackageName()));
            default:
                return context.getResources().getDrawable(context.getResources().getIdentifier(s, context.getString(R.string.drawable),
                        context.getPackageName()));
        }
    }

}
