package com.example.demo_day5_zuoye.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demo_day5_zuoye.R;
import com.example.demo_day5_zuoye.adapter.RecAdapter;
import com.example.demo_day5_zuoye.api.Api;
import com.example.demo_day5_zuoye.bean.VpBean;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class VpFragment extends Fragment {

    private final int type;
    private RecyclerView mRec;
    private ArrayList<VpBean.DataBean.ListBean> listBeans;
    private RecAdapter adapter;

    public VpFragment(int type) {
        this.type = type;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.vpfragment_layout, container, false);
        initView(inflate);
        initDate();
        return inflate;
    }

    private void initDate() {
        new Retrofit.Builder()
                .baseUrl(Api.getGetBaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(Api.class)
                .getVp(type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<VpBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(VpBean vpBean) {
                        List<VpBean.DataBean.ListBean> list = vpBean.getData().getList();
                        listBeans.addAll(list);
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("TAG", "onError: "+e.getMessage() );
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void initView(@NonNull final View itemView) {
        mRec = (RecyclerView) itemView.findViewById(R.id.rec);
        listBeans = new ArrayList<>();
        mRec.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
        mRec.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new RecAdapter(listBeans, getActivity());
        mRec.setAdapter(adapter);
    }
}
