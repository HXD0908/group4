package com.example.mymvp.mvp.model;

import android.util.Log;

import com.example.mymvp.R;
import com.example.mymvp.base.App;
import com.example.mymvp.callback.RxObserverCallBack;
import com.google.gson.JsonParseException;

import java.io.IOException;

import javax.net.ssl.SSLException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by XY.
 * Date: 2020/12/22
 */
public class RxObserver<T> implements Observer {

    private static final String TAG = "RxObserver";
    private RxObserverCallBack<T> callBack;
    protected Disposable disposable;

    public RxObserver(RxObserverCallBack<T> callBack) {
        this.callBack = callBack;
    }

    @Override
    public void onSubscribe(Disposable disposable) {
        this.disposable = disposable;
    }

    @Override
    public void onNext(Object value) {
        if (callBack != null) {
            callBack.onSuccessData((T) value);
        }
    }

    @Override
    public void onError(Throwable e) {
        if (e instanceof IOException) {
            if (callBack != null) {
                callBack.onErrorMsg(App.getStr(R.string.connex_exception));
            }
        } else if (e instanceof JsonParseException) {
            if (callBack != null) {
                callBack.onErrorMsg(App.getStr(R.string.json_parse_exception));
            }
        } else if (e instanceof SSLException) {
            if (callBack != null) {
                callBack.onErrorMsg(App.getStr(R.string.ssl_exception));
            }
        }
    }

    @Override
    public void onComplete() {
        Log.e(TAG, App.getStr(R.string.complete));
    }
}
