package com.example.mymvp.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by XY.
 * Date: 2020/12/21
 */
public abstract class BaseFragment<P extends BasePresenter, T> extends Fragment implements IBaseView<T> {

    private Unbinder bind;
    private P mPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        int layoutId = getLayoutId();
        View view = null;
        if (layoutId != 0) {
            view = inflater.inflate(layoutId, null);
            bind = ButterKnife.bind(this, view);
        }
        mPresenter = createPresenter();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
        return view;
    }

    protected abstract P createPresenter();

    protected P getmPresenter() {
        if (mPresenter != null) {
            return mPresenter;
        }
        return null;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // 初始化view
        init();
        // 初始化数据
        initData();
    }

    protected void initData() {

    }

    protected abstract void init();

    protected abstract int getLayoutId();

    @Override
    public void onSuccess(T t) {

    }

    @Override
    public void onError(String msg) {

    }

    // 释放资源
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (bind != null) {
            bind.unbind();
        }
        if (mPresenter != null) {
            mPresenter.detachView();
            mPresenter = null;
        }
    }
}
