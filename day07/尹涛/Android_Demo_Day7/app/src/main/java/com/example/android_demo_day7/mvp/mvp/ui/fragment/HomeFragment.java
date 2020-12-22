package com.example.android_demo_day7.mvp.mvp.ui.fragment;

import android.widget.TextView;

import com.example.android_demo_day7.R;
import com.example.android_demo_day7.mvp.base.BaseFragment;
import com.example.android_demo_day7.mvp.base.BasePresenter;

import butterknife.BindView;

public class HomeFragment extends BaseFragment {
    @BindView(R.id.tv_page1)
    TextView tvPage1;

    @Override
    protected BasePresenter createPresenter() {
        return new home
    }

    @Override
    protected void init() {
        getmPresenter().start();
    }

    @Override
    protected void initDate() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.home_fragment;
    }
}
