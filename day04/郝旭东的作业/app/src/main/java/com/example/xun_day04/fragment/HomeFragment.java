package com.example.xun_day04.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.xun_day04.R;
import com.example.xun_day04.adapter.FragAdapter;
import com.example.xun_day04.adapter.HomeAdapter;
import com.example.xun_day04.bean.HomeBean;
import com.example.xun_day04.bean.TabBean;
import com.example.xun_day04.callBack.ApiService;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private RecyclerView rcy;
    private ArrayList<HomeBean.DataBean.ListBean> homeList;
    private HomeAdapter homeAdapter;
    private int page=1;
    public HomeFragment(int type) {
    }

    public HomeFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        initView(view);
        initData();
        return view;
    }

    private void initView(View view) {
        rcy = view.findViewById(R.id.rcy);
        rcy.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        rcy.setLayoutManager(new LinearLayoutManager(getActivity()));
        homeList = new ArrayList<>();
        homeAdapter = new HomeAdapter(getActivity(), homeList);
        rcy.setAdapter(homeAdapter);
    }

    private void initData() {
        new Retrofit.Builder()
                .baseUrl(ApiService.URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService.class)
                .getHome(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HomeBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(HomeBean homeBean) {
                        List<HomeBean.DataBean.ListBean> list = homeBean.getData().getList();
                        homeList.addAll(list);
                        homeAdapter.notifyDataSetChanged();
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
