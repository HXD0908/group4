package com.example.mvp.base;

import android.os.Bundle;
import android.view.View;

import com.example.mvp.manager.ContainManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class IBaseActivity<P extends IBasePresenter,T> extends AppCompatActivity implements IBaseView<T> {

    private Unbinder mBind;
    private P mPresenter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //TODO Activity加载布局
        int layoutId = getLayoutId();
        if (layoutId != 0)
            setContentView(layoutId);
        mBind = ButterKnife.bind(this);
        //TODO 将所有的Activity添加到集合里面
        ContainManager.getmManager().addActivity(this);
        //TODO 创建P层对象并且关联V层
        mPresenter = createPresenter();
        if (mPresenter !=null){
            mPresenter.attachView(this);
        }
        //TODO 初始化View
        init();
        //TODO 初始化数据
        initData();
    }

    protected abstract P createPresenter();

    //TODO 获取P层对象
    protected  P getmPresenter(){
        if (mPresenter != null){
            return mPresenter;
        }
        return null;
    }

    protected void initData() {

    }

    protected abstract int getLayoutId();

    protected abstract void init();

    @Override
    public void OnSuccess(T t) {

    }

    @Override
    public void OnError(String msg) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mBind != null) {
            mBind.unbind();
        }
        if (mPresenter != null){
            mPresenter.detachView();
            mPresenter = null;
        }
    }
}
