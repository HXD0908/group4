package com.example.mvp.mvp.model;

import android.util.Log;

import com.example.mvp.CallBack.RxCallBack;
import com.example.mvp.R;
import com.example.mvp.app.MyApp;

import java.io.IOException;

import javax.net.ssl.SSLException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class RxObserver<T> implements Observer {

    private static final String TAG = "RxObserver";
    private RxCallBack<T> callBack;
    protected Disposable mDisPosable;


    public RxObserver(RxCallBack<T> callBack) {
        this.callBack = callBack;
    }

    @Override
    public void onSubscribe(Disposable disposable) {
        this.mDisPosable = disposable;
    }

    @Override
    public void onNext(Object o) {
        if (callBack != null) {
            callBack.onSuccessData((T) o);
        }
    }

    @Override
    public void onError(Throwable e) {
        if (e instanceof IOException){
            if (callBack !=null){
                callBack.onErrorMsg(MyApp.getStr(R.string.connec_exception));
            }else if (callBack!=null){
                callBack.onErrorMsg(MyApp.getStr(R.string.json_parse_exception));
            }else if (e instanceof SSLException){
                if (callBack!= null){
                    callBack.onErrorMsg(MyApp.getStr(R.string.ssl_exception));
                }
            }
        }
    }

    @Override
    public void onComplete() {
        Log.e(TAG, MyApp.getStr(R.string.complete));

    }
}
