package com.ylong.mvp.myinterface;

public interface IBaseView {
    void tips(String tip);
    void loading(int visible);

    void showToast(String msg, int time);

}
