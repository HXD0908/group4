package com.example.android_demo_day7.mvp.callback;

public interface RxObserverCallBack<T> {
    void onSeccessData(T t);
    void onErrorMsg(String msg);
}
