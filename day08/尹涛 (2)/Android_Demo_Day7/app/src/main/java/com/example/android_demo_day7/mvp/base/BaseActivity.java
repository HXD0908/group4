package com.example.android_demo_day7.mvp.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.android_demo_day7.mvp.manager.ContainManager;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 所有的activity都要继承的类
 *
 * @param <P>
 * @param <T>
 */
public abstract class BaseActivity<P extends BasePresenter, T> extends AppCompatActivity implements IBasseView<T> {
    private P mPresenter;
    private Unbinder bind;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int layoutId = getLayoutId();
        if (layoutId != 0)
            setContentView(layoutId);
        bind = ButterKnife.bind(this);
        //将所有的Activity添加到集合里面
        ContainManager.getmManager().addActivity(this);
        //1.创建P层对象并且关联V层
        mPresenter = createPresenter();
        if (mPresenter != null)
            mPresenter.attachView(this);
        //初始化view
        init();
        //初始化数据
        initDate();
    }

    protected abstract P createPresenter();

    //获取P层对象
    protected P getmPresenter() {
        if (mPresenter != null) {
            return mPresenter;
        }
        return null;
    }

    private void initDate() {

    }

    protected abstract void init();

    protected abstract int getLayoutId();

    @Override
    public void onScuccess(T t) {

    }

    @Override
    public void onError(String msg) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (bind != null) {
            bind.unbind();
        }
        if (mPresenter != null) {
            mPresenter.detachView();
            mPresenter = null;
        }
    }
}
