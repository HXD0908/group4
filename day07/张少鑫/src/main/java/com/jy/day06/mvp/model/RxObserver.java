package com.jy.day06.mvp.model;

import android.util.Log;

import androidx.cardview.widget.CardView;

import com.google.gson.JsonParseException;
import com.jy.day06.R;
import com.jy.day06.base.App;
import com.jy.day06.callback.RxObserverCallBack;

import java.io.IOException;

import javax.net.ssl.SSLException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class RxObserver<T> implements Observer {

    private static final String TAG = "RxObserver";
    private final RxObserverCallBack<T> rxCallBack;
    private Disposable disposable;

    public RxObserver(RxObserverCallBack<T> rxCallBack) {
        this.rxCallBack = rxCallBack;
    }

    @Override
    public void onSubscribe(Disposable disposable) {
        this.disposable = disposable;
    }

    @Override
    public void onNext(Object o) {
        if (rxCallBack != null)
            rxCallBack.OnSuccess ((T) o);
    }

    @Override
    public void onError(Throwable e) {
        if (e instanceof IOException) {
            if (rxCallBack != null)
                rxCallBack.OnFail (App.getStr (R.string.connec_exception));
        } else if (e instanceof JsonParseException) {
            if (rxCallBack != null)
                rxCallBack.OnFail (App.getStr (R.string.json_parse_exception));
        } else if (e instanceof SSLException) {
            if (rxCallBack != null)
                rxCallBack.OnFail (App.getStr (R.string.ssl_exception));
        }
    }


    @Override
    public void onComplete() {
        Log.e (TAG, App.getStr (R.string.complete));
    }
}
