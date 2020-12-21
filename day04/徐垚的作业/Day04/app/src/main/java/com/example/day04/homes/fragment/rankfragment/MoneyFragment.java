package com.example.day04.homes.fragment.rankfragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.day04.R;
import com.example.day04.adapter.MoneyAdapter;
import com.example.day04.homes.fragment.api.IRank;
import com.example.day04.homes.fragment.presenter.RankingPresenter;
import com.ylong.mvp.base.BaseFragment;
import com.ylong.mvp.base.BaseModel;
import com.ylong.mvp.bean.LevelBean;
import com.ylong.mvp.bean.MoneyBean;
import com.ylong.mvp.bean.SignBean;

import java.util.ArrayList;
import java.util.concurrent.locks.Condition;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

public class MoneyFragment extends BaseFragment<RankingPresenter> implements IRank.RankView {

    @BindView(R.id.money_recycler)
    RecyclerView moneyRecycler;

    private ArrayList<MoneyBean.DataBean.TongQianTopBean.ListBean> moneyBeans;
    private MoneyAdapter moneyAdapter;

    @Override
    public int getLayout() {
        return R.layout.fragment_money;
    }

    @Override
    public void initView() {
        moneyBeans = new ArrayList<>();

        moneyAdapter = new MoneyAdapter(getActivity(),moneyBeans);
        moneyRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        moneyRecycler.setAdapter(moneyAdapter);
    }

    @Override
    public RankingPresenter createPersenter() {
        return new RankingPresenter(this);
    }

    @Override
    public void initData() {
        persenter.getMoneyData();
    }

    @Override
    public void getMoneyData(MoneyBean bean) {
        moneyBeans.addAll(bean.getData().getTongQianTop().getList());
        moneyAdapter.notifyDataSetChanged();
    }

    @Override
    public void getSignData(SignBean bean) {

    }

    @Override
    public void getLevelData(LevelBean bean) {

    }

    @Override
    public void showError(String msg) {

    }
}