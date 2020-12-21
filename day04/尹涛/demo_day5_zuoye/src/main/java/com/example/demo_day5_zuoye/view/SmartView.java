package com.example.demo_day5_zuoye.view;

import com.example.demo_day5_zuoye.bean.HotBean;

public interface SmartView {
    void onSeccuss(HotBean hotBean);
    void onFail(String error);
}
