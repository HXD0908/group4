package com.example.mymvp.base;

import java.util.concurrent.TimeUnit;

/**
 * Created by XY.
 * Date: 2020/12/21
 */
public abstract class BaseThreadPool {
    public void executeTask(Runnable runnable){

    }

    public void executeTimerTask(Runnable runnable, long fristStartTime, long intervelTime,
                                 TimeUnit timeUnit){

    }

    public void executeTimerTask(Runnable runnable,long delayTime,
                                 TimeUnit timeUnit){

    }

    public abstract void removeTask();

    public void removeTask(Runnable runnable){

    }
}
