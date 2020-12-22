package com.example.mymvp.mvp.model;

import com.example.mymvp.callback.RxObserverCallBack;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by XY.
 * Date: 2020/12/22
 * <p>
 * 用来封装各种操作符
 */

public class RxOperator {
    // 封装了线程切换
    public static Observable threadtransformer(Observable observable) {
        return observable.subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread());
    }

    // 封装了 ConcatMap操作符  网络嵌套(注册完成之后自动登录)
    public <T> ObservableSource concatMap(Observable<T> registerObservable, Observable<T> loginObservable, RxObserverCallBack<T> callBack) {
        return threadtransformer(registerObservable)
                .doOnNext(o -> callBack.onSuccessData((T) o)).observeOn(Schedulers.io())
                .concatMap(new Function() {
                    @Override
                    public Object apply(Object o) throws Exception {
                        return loginObservable;
                    }
                });
    }
}
