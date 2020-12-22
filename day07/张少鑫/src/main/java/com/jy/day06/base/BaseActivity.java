package com.jy.day06.base;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.jy.day06.manager.ContainManager;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity<P extends BasePresenter, T> extends AppCompatActivity implements IBaseView<T> {

    private Unbinder bind;
    private P Presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);

        // TODO Activity加载布局
        int layoutId = getLayoutId ();
        if (layoutId != 0)
            setContentView (layoutId);

        bind = ButterKnife.bind (this);
        // TODO 将所有的Activity添加到集合里面
        ContainManager.getContainManager ().addActivity (this);
        // TODO 1.创建P层对象并且关联V层
        Presenter = createPresenter ();
        if (Presenter != null) {
            Presenter.attachView (this);
        }
        // TODO 初始化view
        init ();
        // TODO 初始化数据
        initData ();
    }

    protected abstract P createPresenter();

    protected P getPresenter() {
        if (Presenter != null)
            return Presenter;

        return null;
    }


    protected void initData() {

    }

    protected abstract void init();


    protected abstract int getLayoutId();


    @Override
    public void OnSuccess(T t) {

    }

    @Override
    public void OnFail(String Error) {

    }

    // TODO 释放资源
    @Override
    protected void onDestroy() {
        super.onDestroy ();
        if (bind != null)
            bind.unbind ();

        if (Presenter != null)
            Presenter.dettachView ();
        Presenter = null;
    }
}
