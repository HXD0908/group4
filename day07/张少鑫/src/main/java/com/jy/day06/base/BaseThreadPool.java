package com.jy.day06.base;

import java.util.concurrent.TimeUnit;

public abstract class BaseThreadPool {

    public void executeTask(Runnable runnable) {

    }

    public void executeTimerTask(Runnable runnable, long firststartTime, long interveTime, TimeUnit timeUnit) {

    }

    public void executeTimerTask(Runnable runnable, long dalayTime, TimeUnit timeUnit) {

    }

    public abstract void removeTask();

    public void removeTask(Runnable runnable) {

    }
}
