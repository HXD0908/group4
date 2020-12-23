package com.example.android_demo_day7.mvp.base;

public interface IPresenter<T> {
    void start();
    void start(T... t);
}
