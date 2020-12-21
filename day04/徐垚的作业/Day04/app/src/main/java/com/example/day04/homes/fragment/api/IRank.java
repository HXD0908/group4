package com.example.day04.homes.fragment.api;

import com.ylong.mvp.bean.LevelBean;
import com.ylong.mvp.bean.MoneyBean;
import com.ylong.mvp.bean.SignBean;
import com.ylong.mvp.myinterface.Callback;
import com.ylong.mvp.myinterface.IBaseView;

/**
 * Created by XY.
 * Date: 2020/12/18
 */
public interface IRank {
    interface RankView extends IBaseView {
        void getMoneyData(MoneyBean bean);

        void getSignData(SignBean bean);

        void getLevelData(LevelBean bean);

        void showError(String msg);
    }

    interface RankPresenter {
        void getMoneyData();

        void getSignData();

        void getLevelData();
    }

    interface RankModel {
        void getMoneyData(Callback<MoneyBean> callback);

        void getSignData(Callback<SignBean> callback);

        void getLevelData(Callback<LevelBean> callback);
    }
}
