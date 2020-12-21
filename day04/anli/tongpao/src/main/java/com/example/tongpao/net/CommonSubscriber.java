package com.example.tongpao.net;

import com.example.tongpao.interfaces.IBaseView;

import io.reactivex.subscribers.ResourceSubscriber;

public abstract class CommonSubscriber<T> extends ResourceSubscriber<T> {

    private IBaseView mView;
    private String errorMsg;
    private boolean isShowErrorState = false;

    protected CommonSubscriber(IBaseView view){
        mView = view;
    }

    protected CommonSubscriber(IBaseView view, String emsg){
        mView = view;
        errorMsg = emsg;
    }

    protected CommonSubscriber(IBaseView view, boolean isError){
        mView = view;
        isShowErrorState = isError;
    }

    protected CommonSubscriber(IBaseView view, String emsg, boolean isError){
        mView = view;
        errorMsg = emsg;
        isShowErrorState = isError;
    }

    @Override
    public void onError(Throwable t) {

    }

    @Override
    public void onComplete() {

    }
}
