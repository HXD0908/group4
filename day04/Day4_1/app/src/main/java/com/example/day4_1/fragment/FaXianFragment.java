package com.example.day4_1.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.day4_1.Apisrevice;
import com.example.day4_1.R;
import com.example.day4_1.activity.PaiHangBangActivity;
import com.example.day4_1.activity.PaoZiActivity;
import com.example.day4_1.activity.SheTuanActivity;
import com.example.day4_1.adapter.MyReMenRecAdapter;
import com.example.day4_1.bean.ReMenBean;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class FaXianFragment extends Fragment implements View.OnClickListener {

    private View view;
    private LinearLayout paozi;
    private LinearLayout shetuan;
    private LinearLayout paihangbang;
    private TextView remenhuodong;
    private TextView gengduohuodong;
    private RelativeLayout rel;
    private RecyclerView rec;
    private LinearLayout zongbuju;
    private ArrayList<ReMenBean.DataBean> list_remen;
    private MyReMenRecAdapter myReMenRecAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_fa_xian, container, false);
        initView();
        initData();
        return view;
    }

    private void initData() {
        new Retrofit.Builder()
                .baseUrl(Apisrevice.URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(Apisrevice.class).getRemenhuati()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ReMenBean>() {
                    @Override
                    public void onSubscribe(Disposable d) { }
                    @Override
                    public void onNext(ReMenBean value) {
                        List<ReMenBean.DataBean> data = value.getData();
                        list_remen.addAll(data);
                        myReMenRecAdapter.notifyDataSetChanged();
                    }
                    @Override
                    public void onError(Throwable e) { }
                    @Override
                    public void onComplete() { }});

    }

    private void initView() {
        paozi = view.findViewById(R.id.paozi);
        shetuan = view.findViewById(R.id.shetuan);
        paihangbang = view.findViewById(R.id.paihangbang);
        remenhuodong = view.findViewById(R.id.remenhuodong);
        gengduohuodong = view.findViewById(R.id.gengduohuodong);
        rel = view.findViewById(R.id.rel);
        rec = view.findViewById(R.id.rec);
        rec = view.findViewById(R.id.rec);
        zongbuju = view.findViewById(R.id.zongbuju);

        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(RecyclerView.HORIZONTAL);
        rec.setLayoutManager(manager);

        list_remen = new ArrayList<>();

        myReMenRecAdapter = new MyReMenRecAdapter(getActivity(),list_remen);

        rec.setAdapter(myReMenRecAdapter);


        paozi.setOnClickListener(this);
        shetuan.setOnClickListener(this);
        paihangbang.setOnClickListener(this);
        gengduohuodong.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.paozi:
                startActivity(new Intent(getActivity(), PaoZiActivity.class));
                break;
            case R.id.shetuan:
                startActivity(new Intent(getActivity(), SheTuanActivity.class));
                break;
            case R.id.paihangbang:
                startActivity(new Intent(getActivity(), PaiHangBangActivity.class));
                break;
            case R.id.gengduohuodong:

                break;


        }
    }
}