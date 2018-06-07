package com.phunguyen.foodforksampleapp;

import android.content.Context;
import android.content.Intent;

public class Extension {
    public static <T> void startScreen(Context context, Class<T> activity) {
        Intent intent = new Intent(context, activity);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
