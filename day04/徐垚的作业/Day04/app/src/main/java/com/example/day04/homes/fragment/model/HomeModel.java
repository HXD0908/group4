package com.example.day04.homes.fragment.model;

import com.example.day04.homes.fragment.api.IView;
import com.ylong.mvp.bean.HotActivityBean;
import com.ylong.mvp.base.BaseModel;
import com.ylong.mvp.myinterface.Callback;
import com.ylong.mvp.net.CommonSubscriber;
import com.ylong.mvp.net.HttpManager;
import com.ylong.mvp.utils.RxUtils;

/**
 * Created by XY.
 * Date: 2020/12/18
 */
public class HomeModel extends BaseModel implements IView.FindModel {
    @Override
    public void getFindData(Callback<HotActivityBean> callBack) {
        HttpManager.getInstance().getHttpApi().getFindData()
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<HotActivityBean>(callBack) {
                    @Override
                    public void onNext(HotActivityBean pHotActivityBean) {
                        callBack.success(pHotActivityBean);
                    }
                });
    }
}
