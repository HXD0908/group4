package com.example.day04.homes.fragment.rankfragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.day04.R;
import com.example.day04.adapter.LevelAdapter;
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

public class LevelFragment extends BaseFragment<RankingPresenter> implements IRank.RankView {

    @BindView(R.id.level_recycler)
    RecyclerView levelRecycler;
    private ArrayList<LevelBean.DataBean.ExpTopBean.ListBean> levelBeans;
    private LevelAdapter levelAdapter;

    @Override
    public int getLayout() {
        return R.layout.fragment_level;
    }

    @Override
    public void initView() {
        levelBeans = new ArrayList<>();

        levelAdapter = new LevelAdapter(getActivity(),levelBeans);
        levelRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        levelRecycler.setAdapter(levelAdapter);
    }

    @Override
    public RankingPresenter createPersenter() {
        return new RankingPresenter(this);
    }

    @Override
    public void initData() {
        persenter.getLevelData();
    }

    @Override
    public void getMoneyData(MoneyBean bean) {

    }

    @Override
    public void getSignData(SignBean bean) {

    }

    @Override
    public void getLevelData(LevelBean bean) {
        levelBeans.addAll(bean.getData().getExpTop().getList());
        levelAdapter.notifyDataSetChanged();
    }

    @Override
    public void showError(String msg) {

    }
}