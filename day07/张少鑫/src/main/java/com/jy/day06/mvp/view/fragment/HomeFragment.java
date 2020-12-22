package com.jy.day06.mvp.view.fragment;

import android.util.Log;
import android.widget.TextView;

import com.jy.day06.R;
import com.jy.day06.base.BaseFragment;
import com.jy.day06.base.BasePresenter;
import com.jy.day06.mvp.presenter.HomeFragPresenter;

import butterknife.BindView;

public class HomeFragment extends BaseFragment {

    private static final String TAG = "HomeFragment";
    @BindView(R.id.tv_show)
    TextView tvShow;

    @Override
    protected BasePresenter createPresenter() {
        return new HomeFragPresenter (this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.home_fragment;
    }

    @Override
    protected void init() {

    }

    @Override
    public void OnSuccess(Object o) {
        String str = (String) o;
        Log.e (TAG, "OnSuccess: 请求成功:" + str);
    }

    @Override
    public void OnFail(String Error) {
        Log.e (TAG, "OnFail: 错误信息:" + Error);
    }
}
