package com.jy.day06.base;

public interface IBaseView<T> {
    void OnSuccess(T t);

    void OnFail(String Error);
}
