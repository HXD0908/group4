package com.example.xun_day04.presenter;

import com.example.xun_day04.bean.ReDainBean;
import com.example.xun_day04.callBack.ReceiverCallBack;
import com.example.xun_day04.fragment.fragmenta.AaFragment;
import com.example.xun_day04.module.MyModule;
import com.example.xun_day04.view.MyView;

public class MyPresenter {
    private MyView myView;
    private final MyModule myModule;

    public MyPresenter(MyView myView) {
        myModule = new MyModule();
        this.myView = myView;
    }

    public void getRe() {
        myModule.getRe(new ReceiverCallBack() {
            @Override
            public void getReDian(ReDainBean reDainBean) {
                myView.getRe(reDainBean);
            }
        });
    }
}
