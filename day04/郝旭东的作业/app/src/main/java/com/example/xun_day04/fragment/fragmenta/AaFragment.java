package com.example.xun_day04.fragment.fragmenta;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.xun_day04.R;
import com.example.xun_day04.adapter.FragAdapter;
import com.example.xun_day04.adapter.ReAdapter;
import com.example.xun_day04.bean.ReDainBean;
import com.example.xun_day04.bean.TabBean;
import com.example.xun_day04.callBack.ApiService;
import com.example.xun_day04.fragment.HomeFragment;
import com.example.xun_day04.presenter.MyPresenter;
import com.example.xun_day04.view.MyView;
import com.google.android.material.tabs.TabLayout;

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
public class AaFragment extends Fragment implements MyView {

    private RecyclerView rcy;
    private ArrayList<ReDainBean.DataBean> redianList;
    private ReAdapter reAdapter;
    private MyPresenter myPresenter;
    private TabLayout tab;
    private ViewPager vp;

    public AaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_aa, container, false);
        initView(view);
        initData();
        initVpf();
        return view;
    }

    private void initView(View view) {
        rcy = view.findViewById(R.id.rcy);
        tab = view.findViewById(R.id.tab);
        vp = view.findViewById(R.id.vp);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rcy.setLayoutManager(linearLayoutManager);
        rcy.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        redianList = new ArrayList<>();
        reAdapter = new ReAdapter(getActivity(), redianList);
        rcy.setAdapter(reAdapter);
    }

    private void initVpf() {
        new Retrofit.Builder()
                .baseUrl(ApiService.URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService.class)
                .getTab()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<TabBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(TabBean tabBean) {
                        List<TabBean.DataBean> data = tabBean.getData();
                        ArrayList<Fragment> fragList = new ArrayList<>();
                        for (int i = 0; i < data.size(); i++) {
                            fragList.add(new HomeFragment(data.get(i).getType()));
                        }
                        vp.setAdapter(new FragAdapter(getChildFragmentManager(), fragList));
                        tab.setupWithViewPager(vp);
                        for (int i = 0; i < data.size(); i++) {
                            tab.getTabAt(i).setText(data.get(i).getName());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void initData() {
        myPresenter = new MyPresenter(this);
        myPresenter.getRe();
    }

    @Override
    public void getRe(ReDainBean reDainBean) {
        List<ReDainBean.DataBean> data = reDainBean.getData();
        redianList.addAll(data);
        reAdapter.notifyDataSetChanged();
    }
}
