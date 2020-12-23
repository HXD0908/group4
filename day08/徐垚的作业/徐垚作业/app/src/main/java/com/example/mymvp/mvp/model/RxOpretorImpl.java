package com.example.mymvp.mvp.model;

import com.example.mymvp.callback.RxObserverCallBack;
import com.example.mymvp.di.component.DaggerOkComponent;
import com.example.mymvp.mvp.model.api.ApiService;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

/**
 * Created by XY.
 * Date: 2020/12/22
 */
public class RxOpretorImpl {
    @Inject
    ApiService apiService;

    public RxOpretorImpl() {
        DaggerOkComponent.create()
                .getSingleApiService(this);
    }

    // TODO 没有参数也没有请求头的 get请求
    public <T> void rxGetRequest(String url, RxObserverCallBack<T> callBack) {
        RxOperator.threadtransformer(apiService.requestGetData(url))
                .subscribe(new RxObserver(callBack));
    }

    // TODO 有参数没有请求头的 get请求
    public <T> void rxGetRequest(String url, Map<String, T> params, RxObserverCallBack<T> callBack) {
        if (params != null && params.size() > 0) { // 有参数的请求
            RxOperator.threadtransformer(apiService.requestGetData(url, params))
                    .subscribe(new RxObserver(callBack));
        } else {  // 没有参数的请求
            rxGetRequest(url, callBack);
        }
    }

    // TODO 有请求头但是没有参数的 get请求
    public <T> void rxGetRequest(String url, HashMap<String, T> headers, RxObserverCallBack<T> callBack) {
        if (headers != null && headers.size() > 0) {
            RxOperator.threadtransformer(apiService.requestGetData(url, headers))
                    .subscribe(new RxObserver(callBack));
        } else {
            rxGetRequest(url, callBack);
        }
    }

    // TODO 既有请求头也有请求参数的 get 请求
    public <T> void rxGetRequest(String url, HashMap<String, T> headers, Map<String, T> params, RxObserverCallBack<T> callBack) {
        // TODO 既有请求头也有请求参数的 get请求
        if (headers != null && headers.size() > 0 && params != null && params.size() > 0) {
            RxOperator.threadtransformer(apiService.requestGetData(url, headers, params))
                    .subscribe(new RxObserver(callBack));
            // TODO 只有请求头没有请求参数的 get请求
        } else if (headers != null && headers.size() > 0 && (params == null || params.size() == 0)) {
            rxGetRequest(url, headers, callBack);
            // TODO 只有参数但是没有请求头的 get请求
        } else if (params != null && params.size() > 0 && (headers == null || headers.size() == 0)) {
            rxGetRequest(url, params, callBack);
        } else {
            // TODO 既没有请求头也没有请求参数的 get请求
            rxGetRequest(url, callBack);
        }
    }

    // 没有参数也没有请求头的 post请求
    public <T> void rxPostRequest(String url,RxObserverCallBack<T> callBack){
        RxOperator.threadtransformer(apiService.requestGetData(url))
                .subscribe(new RxObserver(callBack));
    }
}
