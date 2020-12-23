package com.example.mymvp.base;

/**
 * Created by XY.
 * Date: 2020/12/21
 */
public interface IPresenter<T> {
    void start();
    void start(T... t);
}
