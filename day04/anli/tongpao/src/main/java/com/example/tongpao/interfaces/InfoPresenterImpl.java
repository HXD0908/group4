package com.example.tongpao.interfaces;

import com.example.tongpao.base.BasePresenter;
import com.example.tongpao.bean.InfoBean;
import com.example.tongpao.net.CommonSubscriber;
import com.example.tongpao.net.HttpManager;
import com.example.tongpao.utils.RxUtils;

public class InfoPresenterImpl extends BasePresenter<IFound.InfoView> implements IFound.InfoPresenter {
    @Override
    public void getInfoDate(int type) {
        addSubscribe(HttpManager.getInstance()
                .getTongPaoApi()
                .getInfoDate(type)
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<InfoBean>(mView) {
                    @Override
                    public void onNext(InfoBean infoBean) {
                        mView.setInfoReturn(infoBean);
                    }
                }));
    }
}
