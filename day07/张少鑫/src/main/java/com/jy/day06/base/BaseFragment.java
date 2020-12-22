package com.jy.day06.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment<P extends BasePresenter, T> extends Fragment implements IBaseView<T> {

    private View inflate;
    private Unbinder bind;
    private P presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        int layoutId = getLayoutId ();

        if (layoutId != 0) {
            inflate = inflater.inflate (layoutId, null);
            bind = ButterKnife.bind (this, inflate);
        }
        presenter = createPresenter ();
        if(presenter!=null){
            presenter.attachView (this);
        }
        return inflate;
    }

    protected abstract P createPresenter();

    protected P getPresenter() {
        if (presenter != null) {
            return presenter;
        }
        return null;
    }

    protected abstract int getLayoutId();


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated (view, savedInstanceState);
        init ();
        initData ();
    }

    public void initData() {

    }

    protected abstract void init();

    @Override
    public void OnSuccess(T t) {

    }

    @Override
    public void OnFail(String Error) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy ();
        bind.unbind ();
        if (presenter != null) {
            presenter.dettachView ();
            presenter = null;
        }
    }
}
