package com.example.tongpao.base;

import com.example.tongpao.interfaces.IBasePresenter;
import com.example.tongpao.interfaces.IBaseView;

import java.lang.ref.WeakReference;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class BasePresenter<V extends IBaseView> implements IBasePresenter<V> {

    private WeakReference<V> weakReference;
    protected V mView;
    CompositeDisposable compositeDisposable;

    @Override
    public void attachView(V view) {
        weakReference = new WeakReference<V>(view);
        mView = weakReference.get();

    }

    public void addSubscribe(Disposable disposable){
        if (compositeDisposable==null){
            compositeDisposable=new CompositeDisposable();
            compositeDisposable.add(disposable);
        }
    }

    public void clearSubscribe(){
        if (compositeDisposable!=null){
            compositeDisposable.clear();
        }
    }

    @Override
    public void unAttachView() {
        mView = null;
        clearSubscribe();
    }
}
