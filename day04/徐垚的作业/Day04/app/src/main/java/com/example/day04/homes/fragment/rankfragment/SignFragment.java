package com.example.day04.homes.fragment.rankfragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.day04.R;
import com.example.day04.adapter.SignAdapter;
import com.example.day04.homes.fragment.api.IRank;
import com.example.day04.homes.fragment.presenter.RankingPresenter;
import com.ylong.mvp.base.BaseFragment;
import com.ylong.mvp.bean.LevelBean;
import com.ylong.mvp.bean.MoneyBean;
import com.ylong.mvp.bean.SignBean;

import java.util.ArrayList;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

public class SignFragment extends BaseFragment<RankingPresenter> implements IRank.RankView {

    @BindView(R.id.sign_recycler)
    RecyclerView signRecycler;

    private ArrayList<SignBean.DataBean.SignTopBean.ListBean> signBeans;
    private SignAdapter signAdapter;

    @Override
    public int getLayout() {
        return R.layout.fragment_sign;
    }

    @Override
    public void initView() {
        signBeans = new ArrayList<>();
        signAdapter = new SignAdapter(getActivity(), signBeans);
        signRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        signRecycler.setAdapter(signAdapter);
    }

    @Override
    public RankingPresenter createPersenter() {
        return new RankingPresenter(this);
    }

    @Override
    public void initData() {
        persenter.getSignData();
    }

    @Override
    public void getMoneyData(MoneyBean bean) {

    }

    @Override
    public void getSignData(SignBean bean) {
        signBeans.addAll(bean.getData().getSignTop().getList());
        signAdapter.notifyDataSetChanged();
    }

    @Override
    public void getLevelData(LevelBean bean) {

    }

    @Override
    public void showError(String msg) {

    }
}