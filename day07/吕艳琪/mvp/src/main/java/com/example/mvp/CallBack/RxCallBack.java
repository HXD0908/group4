package com.example.mvp.CallBack;

public interface RxCallBack<T> {
    void onSuccessData(T t);

    void onErrorMsg(String msg);
}
