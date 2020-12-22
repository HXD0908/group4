package com.example.android_demo_day7.mvp.base;

public interface IBasseView<T> {
    void onScuccess(T t);
    void onError(String msg);
}
