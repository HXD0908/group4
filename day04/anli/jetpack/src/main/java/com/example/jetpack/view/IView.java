package com.example.jetpack.view;

import com.example.jetpack.bean.PicBean;

public interface IView {
    void onSuccess(PicBean picBean);
    void onFaul(String error);
}
