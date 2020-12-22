package com.example.mymvp.base;

import android.app.Application;
import android.content.Context;

/**
 * Created by XY.
 * Date: 2020/12/21
 */
public class App extends Application {
    // 全局的Context 整个程序都可以用
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    private void init() {
        context = this;
    }

    public static Context context() {
        return context;
    }

    public static String getStr(int p) {
        return App.context().getString(p);
    }
}
