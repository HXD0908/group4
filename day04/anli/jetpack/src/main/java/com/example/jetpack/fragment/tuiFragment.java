package com.example.jetpack.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jetpack.R;
import com.example.jetpack.adapter.RAdapter;
import com.example.jetpack.api.ApiService;
import com.example.jetpack.bean.FoundBean;

import java.util.ArrayList;
import java.util.List;

public class tuiFragment extends Fragment {


    private View view;
    private RecyclerView mRcy;
    private ArrayList<FoundBean.DataBeanX.DataBean> dataBeans;
    private RAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_tui, container, false);
        initView();
        return view;
    }

    private void initView() {
        mRcy = (RecyclerView) view.findViewById(R.id.rcy);
        dataBeans = new ArrayList<>();
        mRcy.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
        mRcy.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new RAdapter(dataBeans, getActivity());
        mRcy.setAdapter(adapter);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if (isVisibleToUser){
            initDate();
        }else{
            if (dataBeans!=null){
                dataBeans.clear();
            }
        }
        super.setUserVisibleHint(isVisibleToUser);
    }

    private void initDate() {
        new Retrofit.Builder()
                .baseUrl(ApiService.getGetBaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(ApiService.class)
                .getTab()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<FoundBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(FoundBean tabBean) {
                        List<FoundBean.DataBeanX.DataBean> data = tabBean.getData().getData();
                        dataBeans.addAll(data);
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
}