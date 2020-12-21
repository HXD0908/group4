package com.example.jetpack.app;

import android.app.Application;
import android.content.Context;

public class MyApp extends Application {

    private static MyApp app;

    public static MyApp getApp() {
        return app;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
    }
}
