package com.example.mymvp.mvp.ui.fragment;

import butterknife.BindView;

import android.util.Log;
import android.widget.TextView;

import com.example.mymvp.R;
import com.example.mymvp.base.BaseFragment;
import com.example.mymvp.base.BasePresenter;
import com.example.mymvp.mvp.presenter.HomeFrgPresenter;

public class HomeFragment extends BaseFragment {

    @BindView(R.id.tv_show)
    TextView tv;

    @Override
    protected BasePresenter createPresenter() {
        return new HomeFrgPresenter(this);
    }

    @Override
    protected void init() {

    }

    @Override
    protected void initData() {
        // 1.V层向P层发送请求数据的命令
        getmPresenter().start();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    // TODO 成功更新UI
    @Override
    public void onSuccess(Object o) {
        String str = (String) o;
        Log.e("TAG","请求成功" + str);
    }

    // TODO 失败更新UI
    @Override
    public void onError(String msg) {
        Log.e("TAG", "请求失败" + msg);
    }
}