package com.example.mymvp.thread;

import com.example.mymvp.base.BaseThreadPool;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by XY.
 * Date: 2020/12/21
 */
public class CustomThreadPool extends BaseThreadPool {

    private static CustomThreadPool threadPool;
    private final ThreadPoolExecutor executor;

    private CustomThreadPool() {
        executor = new ThreadPoolExecutor(5, 30, 30,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(),
                Executors.defaultThreadFactory());
    }

    public synchronized static CustomThreadPool gtThreadPool() {
        if (threadPool == null) {
            synchronized (CustomThreadPool.class) {
                if (threadPool == null) {
                    threadPool = new CustomThreadPool();
                }
            }
        }
        return threadPool;
    }

    @Override
    public void executeTask(Runnable runnable) {
        super.executeTask(runnable);
        if (executor != null) {
            executor.execute(runnable);
        }
    }

    @Override
    public void removeTask() {

    }

    @Override
    public void removeTask(Runnable runnable) {
        if (executor != null) {
            executor.remove(runnable);
        }
    }
}
