package com.jy.day04.callback;

import com.jy.day04.model.bean.FindMoreBean;

public interface FindCallBack {

    void OnMoreSuccess(FindMoreBean findMoreBean);

    void OnFail(String Error);
}
