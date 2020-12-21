package com.example.tongpao.interfaces;


import com.example.tongpao.base.BasePresenter;
import com.example.tongpao.bean.FoundTabBean;
import com.example.tongpao.bean.HotActivityBean;
import com.example.tongpao.net.CommonSubscriber;
import com.example.tongpao.net.HttpManager;
import com.example.tongpao.utils.RxUtils;

public class HotactivityPresenter extends BasePresenter<IFound.View> implements IFound.Presenter {
    @Override
    public void getHotactivity() {
        addSubscribe(HttpManager.getInstance()
                .getTongPaoApi()
                .getHotactivity()
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<HotActivityBean>(mView) {
                    @Override
                    public void onNext(HotActivityBean hotActivityBean) {
                        mView.setHotactivity(hotActivityBean);
                    }
                }));
    }

    @Override
    public void getTab() {
        addSubscribe(HttpManager.getInstance()
                .getTongPaoApi()
                .getTab()
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<FoundTabBean>(mView) {
                    @Override
                    public void onNext(FoundTabBean foundTabBean) {
                        mView.setTab(foundTabBean);
                    }
                }));
    }
}
