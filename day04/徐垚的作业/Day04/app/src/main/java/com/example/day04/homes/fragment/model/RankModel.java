package com.example.day04.homes.fragment.model;

import com.example.day04.homes.fragment.api.IRank;
import com.ylong.mvp.base.BaseModel;
import com.ylong.mvp.bean.LevelBean;
import com.ylong.mvp.bean.MoneyBean;
import com.ylong.mvp.bean.SignBean;
import com.ylong.mvp.myinterface.Callback;
import com.ylong.mvp.net.CommonSubscriber;
import com.ylong.mvp.net.HttpManager;
import com.ylong.mvp.utils.RxUtils;

/**
 * Created by XY.
 * Date: 2020/12/18
 */
public class RankModel extends BaseModel implements IRank.RankModel {
    @Override
    public void getMoneyData(Callback<MoneyBean> callback) {
        HttpManager.getInstance().getHttpApi().getMoneyData()
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<MoneyBean>(callback) {
                    @Override
                    public void onNext(MoneyBean moneyBean) {
                        callback.success(moneyBean);
                    }
                });
    }

    @Override
    public void getSignData(Callback<SignBean> callback) {
        HttpManager.getInstance().getHttpApi().getSignData()
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<SignBean>(callback) {
                    @Override
                    public void onNext(SignBean signBean) {
                        callback.success(signBean);
                    }
                });
    }

    @Override
    public void getLevelData(Callback<LevelBean> callback) {
        HttpManager.getInstance().getHttpApi().getLevelData()
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<LevelBean>(callback) {
                    @Override
                    public void onNext(LevelBean levelBean) {
                        callback.success(levelBean);
                    }
                });
    }
}
