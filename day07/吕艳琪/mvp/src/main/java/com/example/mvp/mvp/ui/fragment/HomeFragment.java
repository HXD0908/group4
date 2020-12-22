package com.example.mvp.mvp.ui.fragment;

import android.util.Log;
import android.widget.TextView;

import com.example.mvp.R;
import com.example.mvp.base.IBaseFragment;
import com.example.mvp.base.IBasePresenter;
import com.example.mvp.mvp.presenter.HomeFrgPresenter;

import butterknife.BindView;

public class HomeFragment extends IBaseFragment {


    @BindView(R.id.homt_frg_tv)
    TextView homtFrgTv;

    @Override
    protected IBasePresenter createPresenter() {
        return new HomeFrgPresenter(this);
    }

    @Override
    protected void init() {

    }

    @Override
    protected void initData() {
        getmPresenter().start();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void OnSuccess(Object o) {
        String string = (String) o;
        Log.e("TAG", string+"================");
    }

    @Override
    public void OnError(String msg) {
        Log.e("TAG", msg+"================");
    }
}
