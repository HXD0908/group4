package com.ylong.mvp.app;

import android.app.Application;

/**
 * Created by LYL.
 * <p>
 * Date: 2020/12/11
 */
public class BaseApp extends Application {
    public static BaseApp app;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
    }
}
