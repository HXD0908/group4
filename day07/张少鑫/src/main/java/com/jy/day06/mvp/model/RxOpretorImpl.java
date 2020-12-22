package com.jy.day06.mvp.model;

import com.jy.day06.callback.RxObserverCallBack;
import com.jy.day06.di.component.DaggerOkConponent;
import com.jy.day06.mvp.model.RxOperator;
import com.jy.day06.mvp.model.api.HomeApiService;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;

import javax.inject.Inject;

import retrofit2.http.HeaderMap;
import retrofit2.http.QueryMap;

public class RxOpretorImpl {
    @Inject
    HomeApiService homeApiService;

    public RxOpretorImpl() {
        DaggerOkConponent.create ().getStringApiService (this);
    }

    // 没有参数也没有请求头的get请求
    public <T> void rxGetRequest(String url, RxObserverCallBack<T> rxObserverCallBack) {
        RxOperator.threadtransforms (homeApiService.requestGetData (url))
                .subscribe (new RxObserver (rxObserverCallBack));
    }

    // 有参数没有请求头的get请求
    public <T> void rxGetRequest(String url, Map<String, T> map, RxObserverCallBack<T> rxObserverCallBack) {
        if (map != null && map.size () > 0) {
            RxOperator.threadtransforms (homeApiService.requestGetData (url, map))
                    .subscribe (new RxObserver (rxObserverCallBack));
        } else {
            rxGetRequest (url, rxObserverCallBack);
        }
    }

    //  有请求头但是没有参数的get请求
    public <T> void rxGetRequest(String url, HashMap<String, T> hashMap, RxObserverCallBack<T> rxObserverCallBack) {
        if (hashMap != null && hashMap.size () > 0) {
            RxOperator.threadtransforms (homeApiService.requestGetData (url, hashMap))
                    .subscribe (new RxObserver (rxObserverCallBack));
        } else {
            rxGetRequest (url, rxObserverCallBack);
        }
    }


    // 既有请求头也有请求参数的get请求
    public <T> void rxGetRequest(String url, HashMap<String, T> headers, Map<String, T> map, RxObserverCallBack<T> rxObserverCallBack) {
        if (headers != null && headers.size () > 0 && map != null && map.size () > 0) {
            RxOperator.threadtransforms (homeApiService.requestGetData (url, headers, map)).subscribe (new RxObserver (rxObserverCallBack));
        } else if (headers != null && headers.size () > 0 && (map == null || map.size () == 0)) {  //没有请求头没有请求参数的get请求
            rxGetRequest (url, headers, rxObserverCallBack);
        } else if (map == null && map.size () > 0 && (headers == null || headers.size () == 0)) {  //  只有参数但是没有请求头的get请求
            rxGetRequest (url, map, rxObserverCallBack);
        } else {
            rxGetRequest (url, rxObserverCallBack);
        }
    }


    // 没有参数也没有请求头的post请求
    public <T> void rxPostRequest(String url, RxObserverCallBack<T> rxObserverCallBack) {
        RxOperator.threadtransforms (homeApiService.requestGetData (url)).subscribe (new RxObserver (rxObserverCallBack));
    }


}
