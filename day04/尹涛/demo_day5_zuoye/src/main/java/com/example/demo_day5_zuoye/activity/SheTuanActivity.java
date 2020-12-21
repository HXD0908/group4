package com.example.demo_day5_zuoye.activity;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demo_day5_zuoye.R;
import com.example.demo_day5_zuoye.adapter.RrAdapter;
import com.example.demo_day5_zuoye.api.Api;
import com.example.demo_day5_zuoye.bean.ObBean;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class SheTuanActivity extends AppCompatActivity {

    private TextView mTitleTv;
    private RecyclerView mRer;
    private ArrayList<ObBean.DataBean.ListBean> listBeans;
    private RrAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        initView();
        initDate();
    }

    private void initDate() {
        new Retrofit.Builder()
                .baseUrl(Api.subject)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(Api.class)
                .getJie()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ObBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ObBean obBean) {
                        List<ObBean.DataBean.ListBean> list = obBean.getData().getList();
                        listBeans.addAll(list);
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void initView() {
        mTitleTv = (TextView) findViewById(R.id.tv_title);
        mRer = (RecyclerView) findViewById(R.id.rer);
        listBeans = new ArrayList<>();
        mRer.setLayoutManager(new LinearLayoutManager(this));
        mRer.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        adapter = new RrAdapter(listBeans, this);
        mRer.setAdapter(adapter);
    }
}
