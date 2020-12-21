package com.example.day4_1.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.day4_1.Apisrevice;
import com.example.day4_1.R;
import com.example.day4_1.adapter.MyJinYanRecAdapter;
import com.example.day4_1.bean.PaoZiBean;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class JYFragment extends Fragment {

    private View view;
    private RecyclerView rec;
    private ArrayList<PaoZiBean.DataBean> list;
    private MyJinYanRecAdapter myPaoZiRecAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_j_y, container, false);
        initView();
        initData();
        return view;
    }

    private void initData() {
        new Retrofit.Builder()
                .baseUrl(Apisrevice.URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(Apisrevice.class).getPaozi()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PaoZiBean>() {
                    @Override
                    public void onSubscribe(Disposable d) { }
                    @Override
                    public void onNext(PaoZiBean value) {
                        List<PaoZiBean.DataBean> data = value.getData();
                        list.addAll(data);
                        myPaoZiRecAdapter.notifyDataSetChanged();

                    }
                    @Override
                    public void onError(Throwable e) { }
                    @Override
                    public void onComplete() { }});
    }

    private void initView() {
        rec = view.findViewById(R.id.rec);

        rec.setLayoutManager(new LinearLayoutManager(getActivity()));

        list = new ArrayList<>();

        myPaoZiRecAdapter = new MyJinYanRecAdapter(getActivity(),list);

        rec.setAdapter(myPaoZiRecAdapter);


    }
}