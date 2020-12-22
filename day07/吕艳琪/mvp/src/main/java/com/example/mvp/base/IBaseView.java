package com.example.mvp.base;

public interface IBaseView<T> {
    void OnSuccess(T t);

    void OnError(String msg);
}
