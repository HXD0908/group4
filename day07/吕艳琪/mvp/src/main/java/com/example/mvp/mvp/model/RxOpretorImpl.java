package com.example.mvp.mvp.model;

import com.example.mvp.CallBack.RxCallBack;
import com.example.mvp.di.DaggerOkComponent;
import com.example.mvp.mvp.model.api.ApiService;

import java.util.HashMap;
import java.util.Map;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import javax.inject.Inject;

public class RxOpretorImpl {

    @Inject
    ApiService mApiService;

    public RxOpretorImpl(){
        DaggerOkComponent.create()
                .getSingleApiService(this);
    }

    //TODO 没有参数也没有请求头的get请求
    public <T> void rxGetRequest(String url, RxCallBack<T> callBack) {
        RxOperator.threadtransformer(mApiService.requestGetData(url)).
                subscribe(new RxObserver(callBack));
    }



    //有参数没有请求头的get请求
    public <T> void rxGetRequest(String url, Map<String, T> params,
                                 RxCallBack<T> callBack) {
        if (params != null && params.size() > 0) { //有参数的请求
            RxOperator.threadtransformer(mApiService.requestGetData(url, params)).
                    subscribe(new RxObserver(callBack));
        } else {  //没有参数的请求
            rxGetRequest(url, callBack);
        }
    }


    //有请求头但是没有参数的get请求
    public <T> void rxGetRequest(String url, HashMap<String, T> headers,
                                 RxCallBack<T> callBack) {
        if (headers != null && headers.size() > 0) {
            RxOperator.threadtransformer(mApiService.requestGetData(url, headers)).
                    subscribe(new RxObserver(callBack));
        } else {
            rxGetRequest(url, callBack);
        }
    }

    //没有参数也没有请求头的post请求
    public <T> void rxPostRequest(String url, RxCallBack<T> callBack) {
        RxOperator.threadtransformer(mApiService.requestPostData(url)).
                subscribe(new RxObserver(callBack));
    }

}
