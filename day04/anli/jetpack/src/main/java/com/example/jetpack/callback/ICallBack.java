package com.example.jetpack.callback;

import com.example.jetpack.bean.PicBean;

public interface ICallBack {
    void onSuccess(PicBean picBean);
    void onFaul(String error);
}
