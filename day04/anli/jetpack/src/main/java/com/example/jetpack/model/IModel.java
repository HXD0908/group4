package com.example.jetpack.model;

import com.example.jetpack.api.ApiService;
import com.example.jetpack.bean.PicBean;
import com.example.jetpack.callback.ICallBack;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class IModel {
    public void getTu(final ICallBack iCallBack) {
        new Retrofit.Builder()
                .baseUrl(ApiService.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(ApiService.class)
                .getTu()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PicBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(PicBean picBean) {
                        iCallBack.onSuccess(picBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        iCallBack.onFaul(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
