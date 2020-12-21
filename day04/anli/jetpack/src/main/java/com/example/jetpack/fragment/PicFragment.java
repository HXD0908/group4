package com.example.jetpack.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jetpack.R;
import com.example.jetpack.adapter.PicAdapter;
import com.example.jetpack.bean.PicBean;
import com.example.jetpack.presenter.IPresenter;
import com.example.jetpack.view.IView;

import java.util.ArrayList;
import java.util.List;

public class PicFragment extends Fragment implements IView {

    private View view;
    private RecyclerView mRlv;
    private ArrayList<PicBean.DataBeanX.DataBean> dataBeans;
    private PicAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_pic, container, false);
        initView();
        initData();
        return view;
    }

    private void initData() {
        IPresenter iPresenter = new IPresenter(this);
        iPresenter.getDate();
    }

    private void initView() {
        mRlv = (RecyclerView) view.findViewById(R.id.rlv);
        dataBeans = new ArrayList<>();
        adapter = new PicAdapter(dataBeans, getActivity());
        mRlv.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
        mRlv.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRlv.setAdapter(adapter);
    }

    @Override
    public void onSuccess(PicBean picBean) {
        List<PicBean.DataBeanX.DataBean> data = picBean.getData().getData();
        dataBeans.addAll(data);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onFaul(String error) {
        Log.e("TAG", "onFaul: "+error );
    }
}