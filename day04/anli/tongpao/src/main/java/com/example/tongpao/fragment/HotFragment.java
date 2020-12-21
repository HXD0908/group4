package com.example.tongpao.fragment;

import com.example.tongpao.R;
import com.example.tongpao.adapter.InfoRvAdapter;
import com.example.tongpao.base.BaseFragment;
import com.example.tongpao.bean.InfoBean;
import com.example.tongpao.interfaces.IFound;
import com.example.tongpao.interfaces.InfoPresenterImpl;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

public class HotFragment extends BaseFragment<IFound.InfoPresenter> implements IFound.InfoView {

    @BindView(R.id.rv)
    RecyclerView rv;

    private int type;
    private InfoRvAdapter infoRvAdapter;
    List<InfoBean.DataBean.ListBean> listBeans;

    public HotFragment(int type) {
        this.type = type;
    }

    public HotFragment() {

    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_hot;
    }

    @Override
    protected void initView() {
        listBeans =new ArrayList<>();
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
        infoRvAdapter = new InfoRvAdapter(listBeans, getActivity());
        rv.setAdapter(infoRvAdapter);
    }

    @Override
    protected IFound.InfoPresenter createPresenter() {
        return new InfoPresenterImpl();
    }

    @Override
    protected void initData() {
        presenter.getInfoDate(type);
    }

    @Override
    public void setInfoReturn(InfoBean infoReturn) {
        listBeans.addAll(infoReturn.getData().getList());
        infoRvAdapter.notifyDataSetChanged();
    }
}