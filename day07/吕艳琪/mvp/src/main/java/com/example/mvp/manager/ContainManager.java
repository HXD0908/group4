package com.example.mvp.manager;

import android.app.Activity;
import android.content.SharedPreferences;

import com.example.mvp.app.MyApp;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ContainManager {
    private volatile static ContainManager mManager;
    //用来两个类之间传递对象
    private static Map<String, Object> mMap;
    //定义一个集合 存储所有需要添加到集合的Activity
    private static Set<Activity> mSet;
    //全局的sp  整个程序都可以用
    private static SharedPreferences mSp;

    private ContainManager() {
        if (mMap == null) {
            mMap = new HashMap<String, Object>();
        }
        if (mSet == null) {
            mSet = new HashSet<Activity>();
        }
        if (mSp == null) {
            mSp = MyApp.context().getSharedPreferences("config", MyApp.MODE_PRIVATE);
        }
    }

    public static void save_str(String key, String value) {
        mSp.edit().putString(key, value).commit();
    }

    public static String get_str(String key) {
        return mSp.getString(key, "");
    }

    public static void save_boolean(String key, Boolean value) {
        mSp.edit().putBoolean(key, value).commit();
    }

    public static boolean get_boolean(String key) {
        return mSp.getBoolean(key, false);
    }

    public static void addActivity(Activity activity) {
        mSet.add(activity);
    }

    public static void clearActivity() {
        for (Activity activity : mSet) {
            if (activity != null) {
                activity.finish();
                System.exit(0);
            }
        }
    }

    public static void putObj(String key, Object obj) {
        mMap.put(key, obj);
    }

    public static Object getObj(String key) {
        Object object = mMap.get(key);
        return object;
    }


    public static synchronized ContainManager getmManager() {
        if (mManager == null) {
            synchronized (ContainManager.class) {
                if (mManager == null) {
                    mManager = new ContainManager();
                }
            }

        }
        return mManager;
    }
}
