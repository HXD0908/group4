package com.example.day04.homes.fragment.presenter;

import com.example.day04.homes.fragment.api.IRank;
import com.example.day04.homes.fragment.model.RankModel;
import com.ylong.mvp.base.BasePersenter;
import com.ylong.mvp.bean.LevelBean;
import com.ylong.mvp.bean.MoneyBean;
import com.ylong.mvp.bean.SignBean;
import com.ylong.mvp.myinterface.Callback;

/**
 * Created by XY.
 * Date: 2020/12/18
 */
public class RankingPresenter extends BasePersenter<IRank.RankView> implements IRank.RankPresenter {

    IRank.RankView view;
    IRank.RankModel model;

    public RankingPresenter(IRank.RankView view) {
        this.view = view;
        model = new RankModel();
    }

    @Override
    public void getMoneyData() {
        model.getMoneyData(new Callback<MoneyBean>() {
            @Override
            public void fail(String msg) {
                view.showError(msg);
            }

            @Override
            public void success(MoneyBean moneyBean) {
                if (view != null) {
                    view.getMoneyData(moneyBean);
                }
            }
        });
    }

    @Override
    public void getSignData() {
        model.getSignData(new Callback<SignBean>() {
            @Override
            public void fail(String msg) {
                view.showError(msg);
            }

            @Override
            public void success(SignBean signBean) {
                if (view != null) {
                    view.getSignData(signBean);
                }
            }
        });
    }

    @Override
    public void getLevelData() {
        model.getLevelData(new Callback<LevelBean>() {
            @Override
            public void fail(String msg) {
                view.showError(msg);
            }

            @Override
            public void success(LevelBean levelBean) {
                if (view != null) {
                    view.getLevelData(levelBean);
                }
            }
        });
    }
}
