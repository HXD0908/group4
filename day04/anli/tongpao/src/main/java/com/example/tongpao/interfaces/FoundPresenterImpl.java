package com.example.tongpao.interfaces;

import com.example.tongpao.base.BasePresenter;
import com.example.tongpao.bean.FoundTabBean;
import com.example.tongpao.bean.HotBean;
import com.example.tongpao.net.CommonSubscriber;
import com.example.tongpao.net.HttpManager;
import com.example.tongpao.utils.RxUtils;

public class FoundPresenterImpl extends BasePresenter<IFound.View> implements IFound.Presenter {

    @Override
    public void getHotactivity() {
        addSubscribe(HttpManager.getInstance()
                .getTongPaoApi()
                .getHotDate()
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<HotBean>(mView) {
                    @Override
                    public void onNext(HotBean hotBean) {
//                        mView.setHotactivity(hotBean);
                    }
                }));
    }

    @Override
    public void getTab() {
        addSubscribe(HttpManager.getInstance()
                .getTongPaoApi()
                .getTabData()
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<FoundTabBean>(mView) {
                    @Override
                    public void onNext(FoundTabBean foundTabBean) {
                        mView.setTab(foundTabBean);
                    }
                }));
    }
}
