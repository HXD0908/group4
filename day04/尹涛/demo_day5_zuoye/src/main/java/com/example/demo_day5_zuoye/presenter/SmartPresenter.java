package com.example.demo_day5_zuoye.presenter;

import com.example.demo_day5_zuoye.bean.HotBean;
import com.example.demo_day5_zuoye.callback.SmartCallBack;
import com.example.demo_day5_zuoye.fragment.FragmentFaXian;
import com.example.demo_day5_zuoye.model.SmartModel;
import com.example.demo_day5_zuoye.view.SmartView;

public class SmartPresenter {
    private final SmartModel smartModel;
    private SmartView smartView;

    public SmartPresenter(SmartView smartView) {
        smartModel = new SmartModel();
        this.smartView = smartView;
    }

    public void getHot() {
        smartModel.getHot(new SmartCallBack() {
            @Override
            public void onSeccuss(HotBean hotBean) {
                smartView.onSeccuss(hotBean);
            }

            @Override
            public void onFail(String error) {
                smartView.onFail(error);
            }
        });
    }
}
