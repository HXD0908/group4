package com.jy.day06.base;

import java.lang.ref.WeakReference;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

//1.链接M层和 V层     2.处理业务逻辑    3.添加和断开网络请求开关
public class BasePresenter<V extends IBaseView<T>, T> implements IPresenter<T> {
    private WeakReference<V> vWeakReference;
    protected CompositeDisposable compositeDisposable = new CompositeDisposable ();


    // 1.用弱引用修饰V层 方便GC回收  2.P层关联V层
    protected void attachView(V view) {
        vWeakReference = new WeakReference<V> (view);
    }

    //2.释放V层资源的同时断开网络开关
    protected void dettachView() {
        if (vWeakReference != null) {
            vWeakReference.clear ();
            vWeakReference = null;
        }
        disposable ();
    }


    //添加网络开关
    protected void addDisposable(Disposable disposable) {
        if (compositeDisposable != null) {
            compositeDisposable.add (disposable);
        }
    }


    //断开网络开关
    private void disposable() {
        if (compositeDisposable != null && compositeDisposable.isDisposed ()) {
            compositeDisposable.dispose ();
            compositeDisposable.clear ();
            compositeDisposable = null;
        }
    }


    //TODO 处理业务逻辑
    @Override
    public void start() {

    }

    //TODO 处理业务逻辑
    @Override
    public void start(T... t) {

    }
}
