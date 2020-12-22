package com.example.mymvp.manager;

/**
 * Created by XY.
 * Date: 2020/12/21
 */
public class DBManager {
    private volatile static DBManager manager;

    private DBManager() {

    }

    public static synchronized DBManager getManager() {
        if (manager == null) {
            synchronized (DBManager.class) {
                if (manager == null) {
                    manager = new DBManager();
                }
            }
        }
        return manager;
    }
}
