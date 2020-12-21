package com.example.jetpack;

import com.example.jetpack.app.MyApp;
import com.example.jetpack.bean.MyDbBean;
import com.example.jetpack.dao.DaoMaster;
import com.example.jetpack.dao.DaoSession;
import com.example.jetpack.dao.MyDbBeanDao;

import java.util.List;

public class Utils {
    private static Utils utIls;
    private final MyDbBeanDao myDbBeanDao;

    public static Utils getUtIls() {
        if (utIls == null){
            synchronized (Utils.class){
                if (utIls == null ){
                    utIls = new Utils();
                }
            }
        }
        return utIls;
    }

    public Utils() {
        DaoMaster.DevOpenHelper demo = new DaoMaster.DevOpenHelper(MyApp.getApp(), "Demo");
        DaoMaster master = new DaoMaster(demo.getWritableDatabase());
        DaoSession daoSession = master.newSession();
        myDbBeanDao = daoSession.getMyDbBeanDao();
    }
    public long insert(MyDbBean myDbBean){
        long insert = myDbBeanDao.insert(myDbBean);
        return insert;
    }
    public void delete(MyDbBean myDbBean){
        myDbBeanDao.delete(myDbBean);
    }
    public List<MyDbBean> query(){
        List<MyDbBean> list = myDbBeanDao.queryBuilder().list();
        return list;
    }
}
