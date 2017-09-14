package com.lego.mydiablo.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.lego.mydiablo.R;

public class ImgUtils {

    private ImgUtils() {
    }

    public static Drawable pickImage(Context context, String s) {
        Log.d("IMAGE", "pickImage: " + s);
        switch (s) {
            case "demon hunter":
                return ContextCompat.getDrawable(context, context.getResources().getIdentifier("dh", context.getString(R.string.drawable),
                        context.getPackageName()));

            case "witch doctor":
                return ContextCompat.getDrawable(context, context.getResources().getIdentifier("wd", context.getString(R.string.drawable),
                        context.getPackageName()));

            default:
                return ContextCompat.getDrawable(context, context.getResources().getIdentifier(s, context.getString(R.string.drawable),
                        context.getPackageName()));
        }
    }

    public static Drawable pickImageDash(Context context, String s) {
        Log.d("IMAGE", "pickImageDash: " + s);
        switch (s) {
            case "demon-hunter":
                return ContextCompat.getDrawable(context, context.getResources().getIdentifier("dh", context.getString(R.string.drawable),
                        context.getPackageName()));
            case "witch-doctor":
                return ContextCompat.getDrawable(context, context.getResources().getIdentifier("wd", context.getString(R.string.drawable),
                        context.getPackageName()));
            default:
                return ContextCompat.getDrawable(context, context.getResources().getIdentifier(s, context.getString(R.string.drawable),
                        context.getPackageName()));
        }
    }

    public static Drawable pickHeroIcon(Context context, String s) {
        Log.d("IMAGE", "pickHeroIcon: " + s);
        switch (s) {
            case "demon-hunter_m":
                return ContextCompat.getDrawable(context, context.getResources().getIdentifier("dh_m", context.getString(R.string.drawable),
                        context.getPackageName()));
            case "demon-hunter_f":
                return ContextCompat.getDrawable(context, context.getResources().getIdentifier("dh_f", context.getString(R.string.drawable),
                        context.getPackageName()));
            case "witch-doctor_m":
                return ContextCompat.getDrawable(context, context.getResources().getIdentifier("wd_m", context.getString(R.string.drawable),
                        context.getPackageName()));
            case "witch-doctor_f":
                return ContextCompat.getDrawable(context, context.getResources().getIdentifier("wd_f", context.getString(R.string.drawable),
                        context.getPackageName()));
            default:
                return ContextCompat.getDrawable(context, context.getResources().getIdentifier(s, context.getString(R.string.drawable),
                        context.getPackageName()));
        }
    }

    public static Drawable pickUserHeroIcon(Context context, String s) {
        Log.d("IMAGE", "pickUserHeroIcon: " + s);
        switch (s) {
            case "demon hunter_m":
                return ContextCompat.getDrawable(context, context.getResources().getIdentifier("dh_m", context.getString(R.string.drawable),
                        context.getPackageName()));
            case "demon hunter_f":
                return ContextCompat.getDrawable(context, context.getResources().getIdentifier("dh_f", context.getString(R.string.drawable),
                        context.getPackageName()));
            case "witch doctor_m":
                return ContextCompat.getDrawable(context, context.getResources().getIdentifier("wd_m", context.getString(R.string.drawable),
                        context.getPackageName()));
            case "witch doctor_f":
                return ContextCompat.getDrawable(context, context.getResources().getIdentifier("wd_f", context.getString(R.string.drawable),
                        context.getPackageName()));
            default:
                return ContextCompat.getDrawable(context, context.getResources().getIdentifier(s, context.getString(R.string.drawable),
                        context.getPackageName()));
        }
    }

}
