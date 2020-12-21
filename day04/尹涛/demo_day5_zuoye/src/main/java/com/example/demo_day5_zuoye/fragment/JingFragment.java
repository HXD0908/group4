package com.example.demo_day5_zuoye.fragment;

import android.os.Bundle;
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
import com.example.demo_day5_zuoye.adapter.JingRlvAdapter;
import com.example.demo_day5_zuoye.api.Api;
import com.example.demo_day5_zuoye.bean.JingBean;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class JingFragment extends Fragment {
    private RecyclerView mRlv1;
    private ArrayList<JingBean.DataBean.ExpTopBean.ListBean> listBeans;
    private JingRlvAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.jingfragment_layout, container, false);
        initView(inflate);
        initDate();
        return inflate;
    }

    private void initDate() {
        new Retrofit.Builder()
                .baseUrl(Api.jing)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(Api.class)
                .getJing()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<JingBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(JingBean jingBean) {
                        List<JingBean.DataBean.ExpTopBean.ListBean> list = jingBean.getData().getExpTop().getList();
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

    private void initView(@NonNull final View itemView) {
        mRlv1 = (RecyclerView) itemView.findViewById(R.id.rlv1);
        listBeans = new ArrayList<>();
        adapter = new JingRlvAdapter(listBeans, getActivity());
        mRlv1.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
        mRlv1.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRlv1.setAdapter(adapter);
    }
}
