package com.example.mvp.mvp.presenter;

import com.example.mvp.CallBack.RxCallBack;
import com.example.mvp.base.IBasePresenter;
import com.example.mvp.mvp.model.RxOpretorImpl;
import com.example.mvp.mvp.model.constants.ApiConstants;
import com.example.mvp.mvp.ui.fragment.HomeFragment;

import java.io.IOException;

import okhttp3.ResponseBody;

public class HomeFrgPresenter extends IBasePresenter {
    private RxOpretorImpl mImpl;
    private HomeFragment mHomeFragment;

    public HomeFrgPresenter(HomeFragment fragment) {
        mImpl = new RxOpretorImpl();
        this.mHomeFragment = fragment;
    }

    @Override
    public void start() {
        //TODO 2.向M层进行数据请求，请求到数据之后将数据返回给V层
        mImpl.rxGetRequest(ApiConstants.BAIDU_LIST, new RxCallBack<Object>() {
            @Override
            public void onSuccessData(Object obj) {
                if (mHomeFragment != null) {
                    ResponseBody responseBody = (ResponseBody) obj;
                    try {
                        String tring = responseBody.string();
                        //TODO gson解析
                       // mHomeFragment.onScuccess(string);
                        mHomeFragment.OnSuccess(tring);
                    } catch (IOException e) {
                        e.printStackTrace();
                        mHomeFragment.OnError(e.getMessage());
                    }
                }
            }

            @Override
            public void onErrorMsg(String msg) {
                if (mHomeFragment != null) {
                    mHomeFragment.OnError(msg);
                }
            }
        });
    }
}
