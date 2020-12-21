package com.example.jetpack.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jetpack.R;
import com.example.jetpack.Utils;
import com.example.jetpack.adapter.RecycAdapter;
import com.example.jetpack.bean.MyDbBean;

import java.util.ArrayList;
import java.util.List;


public class guanFragment extends Fragment {

    private View view;
    private RecyclerView mRecycle;
    private ArrayList<MyDbBean> list;
    private RecycAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_guan, container, false);
        initView();
        initData();
        return view;
    }

    private void initData() {
        Utils utIls = Utils.getUtIls();
        List<MyDbBean> query = utIls.query();
        list.addAll(query);
        adapter.notifyDataSetChanged();
    }

    private void initView() {
        mRecycle = (RecyclerView) view.findViewById(R.id.recycle);
        list = new ArrayList<>();
        mRecycle.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecycle.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
        adapter = new RecycAdapter(list, getActivity());
        mRecycle.setAdapter(adapter);
    }
}