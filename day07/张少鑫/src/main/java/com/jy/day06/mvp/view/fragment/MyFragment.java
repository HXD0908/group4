package com.jy.day06.mvp.view.fragment;

import com.jy.day06.R;
import com.jy.day06.base.BaseFragment;
import com.jy.day06.base.BasePresenter;

public class MyFragment extends BaseFragment {
    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.my_fragment;
    }

    @Override
    protected void init() {

    }
}
