package com.jy.day06.callback;

public interface RxObserverCallBack<T> {
    void OnSuccess(T t);

    void OnFail(String Error);
}
