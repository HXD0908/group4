package com.jy.day04.view.activity;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jy.day04.R;
import com.jy.day04.adapter.RanMassAdapter;
import com.jy.day04.model.api.FindApiService;
import com.jy.day04.model.bean.RanMassBean;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RanMassActivity extends AppCompatActivity {
    private static final String TAG = "RanMassActivity";

    private RecyclerView mRecyclerMass;
    private ArrayList<RanMassBean.DataBean.ListBean> listBeans;
    private RanMassAdapter ranMassAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_ran_mass);
        initView ();
        initData();
    }

    private void initData() {
        new Retrofit.Builder ()
                .baseUrl (FindApiService.baseUrl)
                .addConverterFactory (GsonConverterFactory.create ())
                .addCallAdapterFactory (RxJava2CallAdapterFactory.create ())
                .build ()
                .create (FindApiService.class)
                .getMass ()
                .subscribeOn (Schedulers.io ())
                .observeOn (AndroidSchedulers.mainThread ())
                .subscribe (new Observer<RanMassBean> () {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(RanMassBean ranMassBean) {
                        List<RanMassBean.DataBean.ListBean> list = ranMassBean.getData ().getList ();
                        listBeans.addAll (list);
                        ranMassAdapter.notifyDataSetChanged ();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e (TAG, "onError: 错误信息:"+e.getMessage () );
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void initView() {
        mRecyclerMass = (RecyclerView) findViewById (R.id.mass_recycler);
        mRecyclerMass.setLayoutManager (new LinearLayoutManager (this));
        listBeans = new ArrayList<> ();
        ranMassAdapter = new RanMassAdapter (this, listBeans);
        mRecyclerMass.setAdapter (ranMassAdapter);
        mRecyclerMass.addItemDecoration (new DividerItemDecoration (this, DividerItemDecoration.VERTICAL));
    }
}
