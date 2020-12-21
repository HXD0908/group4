package com.example.xun_day04.fragment.fragmentb;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.xun_day04.R;
import com.example.xun_day04.adapter.RecAdapter;
import com.example.xun_day04.bean.JinYanBean;
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
public class HomeAFragment extends Fragment {

    private RecyclerView rcy;
    private ArrayList<JinYanBean.DataBean.ExpTopBean.ListBean> homeList;
    private RecAdapter recAdapter;

    public HomeAFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_a, container, false);
        initView(view);
        initData();
        return view;
    }

    private void initView(View view) {
        rcy = view.findViewById(R.id.rcy);
        rcy.setLayoutManager(new LinearLayoutManager(getActivity()));
        rcy.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        homeList = new ArrayList<>();
        recAdapter = new RecAdapter(getActivity(), homeList);
        rcy.setAdapter(recAdapter);
    }

    private void initData() {
        new Retrofit.Builder()
                .baseUrl(ApiService.URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService.class)
                .getJin()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<JinYanBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(JinYanBean jinYanBean) {
                        List<JinYanBean.DataBean.ExpTopBean.ListBean> list = jinYanBean.getData().getExpTop().getList();
                        homeList.addAll(list);
                        recAdapter.notifyDataSetChanged();
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
