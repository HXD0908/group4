package com.example.mvp.base;

import java.lang.ref.WeakReference;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class IBasePresenter<V extends IBaseView<T>, T> implements IPresenter<T> {
    private WeakReference<V> mView;

    protected CompositeDisposable mComposite = new CompositeDisposable();

    //TODO 添加网络开关
    protected void addDisposable(Disposable disposable){
        if (mComposite != null){
            mComposite.add(disposable);
        }
    }

    //TODO 用弱引用修饰V层  方便G回收  P层关联V层
    protected void attachView(V view){
        mView = new WeakReference<V>(view);
    }

    //TODO 释放V层资源的同事 断开网络开关
    protected void detachView(){
        if (mView != null){
            mView.clear();
            mView = null;
        }
        deleteDisposable();
    }

    private void deleteDisposable() {
        if (mComposite != null && !mComposite.isDisposed()){
            mComposite.dispose();
            mComposite.clear();
            mComposite = null;
        }
    }


    @Override
    public void start() {
        //TODO 处理业务逻辑
    }

    @Override
    public void start(T... t) {

    }
}
