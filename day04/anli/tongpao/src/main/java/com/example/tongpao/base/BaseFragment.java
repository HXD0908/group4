package com.example.tongpao.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tongpao.interfaces.IBasePresenter;
import com.example.tongpao.interfaces.IBaseView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment<P extends IBasePresenter>  extends Fragment implements IBaseView {

    private Unbinder unbinder;
    protected P presenter;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
        presenter = createPresenter();
        initView();
        if (presenter!=null){
            presenter.attachView(this);
            initData();
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = LayoutInflater.from(container.getContext()).inflate(getLayout(), null);
        return view;
    }


    protected abstract int getLayout();

    protected  abstract void initView();

    protected  abstract  P createPresenter();

    protected abstract void initData();

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (unbinder!=null){
            unbinder.unbind();
        }
        if (presenter!=null){
            presenter.unAttachView();
        }
    }
}
