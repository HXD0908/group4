package com.example.mymvp.base;

/**
 * Created by XY.
 * Date: 2020/12/21
 */
public interface IBaseView<T> {
    void onSuccess(T t);
    void onError(String msg);
}
