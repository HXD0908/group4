package com.example.mymvp.manager;

import android.app.Activity;
import android.content.SharedPreferences;

import com.example.mymvp.base.App;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by XY.
 * Date: 2020/12/21
 */
public class ContainManager {
    private static ContainManager manager;
    // 用来两个类之间传递对象
    private static Map<String, Object> map;
    // 定义一个集合存储所有需要添加到集合的Activity
    private static Set<Activity> set;
    // 全局的sp 整个程序都可以用
    private static SharedPreferences sp;

    private ContainManager() {
        if (map == null) {
            map = new HashMap<String, Object>();
        }
        if (set == null) {
            set = new HashSet<Activity>();
        }
        if (sp == null) {
            sp = App.context().getSharedPreferences("config",
                    App.MODE_PRIVATE);
        }
    }

    public static void save_str(String key, String value) {
        sp.edit().putString(key, value).commit();
    }

    public static String get_str(String key) {
        return sp.getString(key, "");
    }

    public static void save_boolean(String key, Boolean value) {
        sp.edit().putBoolean(key, value).commit();
    }

    public static boolean get_boolean(String key) {
        return sp.getBoolean(key, false);
    }


    public static void addActivity(Activity activity) {
        set.add(activity);
    }

    public static void clearActivity() {
        for (Activity activity : set) {
            if (activity != null) {
                System.exit(0);
            }
        }
    }

    public static void putObj(String key, Object obj) {
        map.put(key, obj);
    }

    public static Object getObj(String key) {
        Object object = map.get(key);
        return object;
    }

    public static synchronized ContainManager getManager() {
        if (manager == null) {
            synchronized (ContainManager.class) {
                if (manager == null) {
                    manager = manager = new ContainManager();
                }
            }
        }
        return manager;
    }
}
