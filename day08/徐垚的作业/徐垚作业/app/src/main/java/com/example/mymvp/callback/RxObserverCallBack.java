package com.example.mymvp.callback;

/**
 * Created by XY.
 * Date: 2020/12/21
 */
public interface RxObserverCallBack<T> {
    void onSuccessData(T t);
    void onErrorMsg(String msg);
}
