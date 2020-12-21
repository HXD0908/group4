package com.jy.day04.model;

import com.jy.day04.callback.FindCallBack;
import com.jy.day04.model.api.FindApiService;
import com.jy.day04.model.bean.FindMoreBean;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ImpFindModel implements FindModel {
    @Override
    public void getMore(FindCallBack findCallBack) {
        new Retrofit.Builder ()
                .baseUrl (FindApiService.baseUrl)
                .addConverterFactory (GsonConverterFactory.create ())
                .addCallAdapterFactory (RxJava2CallAdapterFactory.create ())
                .build ()
                .create (FindApiService.class)
                .getMore ()
                .subscribeOn (Schedulers.io ())
                .observeOn (AndroidSchedulers.mainThread ())
                .subscribe (new Observer<FindMoreBean> () {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(FindMoreBean findMoreBean) {
                        findCallBack.OnMoreSuccess (findMoreBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        findCallBack.OnFail (e.getMessage ());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
