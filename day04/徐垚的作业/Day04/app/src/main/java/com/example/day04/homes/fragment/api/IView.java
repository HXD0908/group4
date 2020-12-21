package com.example.day04.homes.fragment.api;

import com.ylong.mvp.bean.HotActivityBean;
import com.ylong.mvp.myinterface.Callback;
import com.ylong.mvp.myinterface.IBaseView;

import java.util.List;

/**
 * Created by XY.
 * Date: 2020/12/17
 */
public interface IView {

    interface FindView extends IBaseView {
        void getFindData(HotActivityBean bean);

        void getError(String msg);
    }

    interface FindPresenter {

        void getFindData();
    }

    interface FindModel {
        void getFindData(Callback<HotActivityBean> callBack);

//        void getTextData(Callback<TextBean> callBack);
//
//        void getVideoData(Callback<VideoBean> callBack);
    }
}
