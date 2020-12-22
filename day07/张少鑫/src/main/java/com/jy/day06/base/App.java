package com.jy.day06.base;

import android.app.Application;
import android.content.Context;

public class App extends Application {

    public static Context context;

    public static Context getContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate ();
        context = this;
    }

    public static String getStr(int p) {
        return App.getContext ().getString (p);
    }

}
