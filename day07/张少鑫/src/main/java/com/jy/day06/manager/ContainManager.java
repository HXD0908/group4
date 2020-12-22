package com.jy.day06.manager;

import android.app.Activity;
import android.content.SharedPreferences;

import com.jy.day06.base.App;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ContainManager {
    private volatile static ContainManager containManager;

    //用来两个类之间传递对象
    private  Map<String, Object> map;
    //定义一个集合存储所有需要添加到集合的Activity
    private  Set<Activity> set;
    //全局的SP 整个程序都可以用
    private  SharedPreferences sharedPreferences;

    public synchronized static ContainManager getContainManager() {
        if (containManager == null) {
            synchronized (ContainManager.class) {
                if (containManager == null) {
                    containManager = new ContainManager ();
                }
            }
        }
        return containManager;
    }

    private ContainManager() {
        if (map == null) {
            map = new HashMap<String,Object> ();
        }
        if (set == null) {
            set = new HashSet<Activity> ();
        }
        if (sharedPreferences == null) {
            sharedPreferences = App.context.getSharedPreferences ("name", App.MODE_PRIVATE);
        }
    }


    public  void set_str(String key, String value) {
        sharedPreferences.edit ().putString (key, value).commit ();
    }

    public  String get_str(String key) {
        return sharedPreferences.getString (key, "");
    }

    public  void set_boolean(String key, boolean value) {
        sharedPreferences.edit ().putBoolean (key, false).commit ();
    }

    public  boolean get_boolean(String key) {
        return sharedPreferences.getBoolean (key, false);
    }

    public  void addActivity(Activity activity) {
        set.add (activity);
    }

    public  void clearActivity() {
        for (Activity activity : set) {
            if (activity != null) {
                activity.finish ();
                System.exit (0);
            }
        }
    }

    public  void putObject(String key, Object value) {
        map.put (key, value);
    }

    public  Object getObject(String key) {
        return map.get (key);
    }

}
