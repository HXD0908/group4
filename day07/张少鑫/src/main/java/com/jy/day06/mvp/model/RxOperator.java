package com.jy.day06.mvp.model;

import com.jy.day06.callback.RxObserverCallBack;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * 用来封装各种操作符
 */
public class RxOperator {

    //封装了线程切换
    public static Observable threadtransforms(Observable observable) {
        return observable.observeOn (AndroidSchedulers.mainThread ()).subscribeOn (Schedulers.io ());
    }

    //封装了ConcatMap操作符   网络嵌套(注册完成之后自动登录)

    public <T> ObservableSource concanMap(Observable<T> registerObservable, Observable<T> loginObservable, RxObserverCallBack<T> rxObserverCallBack) {

        return threadtransforms (registerObservable).doOnNext (o -> {
            rxObserverCallBack.OnSuccess ((T) o);
        }).observeOn (Schedulers.io ()).concatMap (o -> {
            return loginObservable;
        });
    }
}
