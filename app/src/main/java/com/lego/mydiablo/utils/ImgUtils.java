package com.lego.mydiablo.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.lego.mydiablo.R;

public class ImgUtils {

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
}
