package com.jy.day06.base;

public interface IPresenter<T> {
    void start();
    void start(T... t);
}
