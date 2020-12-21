package com.example.demo_day5_zuoye.callback;

import com.example.demo_day5_zuoye.bean.HotBean;

public interface SmartCallBack {
    void onSeccuss(HotBean hotBean);
    void onFail(String error);
}
