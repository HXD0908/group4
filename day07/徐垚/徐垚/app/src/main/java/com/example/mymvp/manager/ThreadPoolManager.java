package com.example.mymvp.manager;

import com.example.mymvp.base.BaseThreadPool;
import com.example.mymvp.thread.CustomThreadPool;
import com.example.mymvp.thread.SchduleThreadPool;
import com.example.mymvp.thread.SingleThreadPool;

/**
 * Created by XY.
 * Date: 2020/12/21
 */
public class ThreadPoolManager {
    public static final int CUSTOM_THREADPOOL = 0;
    public static final int SINGLE_THREADPOOL = 1;
    public static final int SCHDULE_THREADPOOL = 2;

    public static BaseThreadPool getThreadPool(int type) {
        switch (type) {
            case CUSTOM_THREADPOOL:
                return CustomThreadPool.gtThreadPool();
            case SINGLE_THREADPOOL:
                return SingleThreadPool.getSingleThreaPool();
            case SCHDULE_THREADPOOL:
                return SchduleThreadPool.getmSchduleThreadPool();
        }
        return null;
    }
}
