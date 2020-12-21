package com.example.demo_day5_zuoye.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.demo_day5_zuoye.R;
import com.example.demo_day5_zuoye.activity.PaiActivity;
import com.example.demo_day5_zuoye.activity.SheTuanActivity;
import com.example.demo_day5_zuoye.adapter.RlvAdapter;
import com.example.demo_day5_zuoye.api.Api;
import com.example.demo_day5_zuoye.bean.HotBean;
import com.example.demo_day5_zuoye.bean.TabBean;
import com.example.demo_day5_zuoye.presenter.SmartPresenter;
import com.example.demo_day5_zuoye.view.SmartView;
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

public class FragmentFaXian extends Fragment implements SmartView {
    private LinearLayout mLl;
    private TextView mRemenTv;
    private RecyclerView mRlv;
    private TabLayout mTab;
    private ViewPager mVp;
    private ArrayList<HotBean.DataBean> dataBeans;
    private RlvAdapter adapter;
    private ArrayList<TabBean.DataBean> tabbean;
    private ImageView mTitle1Iv;
    private ImageView mTitle2Iv;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.fragemntfaxain_layout, container, false);
        initView(inflate);
        initDate();
        return inflate;
    }

    private void initDate() {
        SmartPresenter smartPresenter = new SmartPresenter(this);
        smartPresenter.getHot();
        new Retrofit.Builder()
                .baseUrl(Api.getBaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(Api.class)
                .getTab()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<TabBean>() {

                    private ArrayList<Fragment> fragments;

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(TabBean tabBean) {
                        List<TabBean.DataBean> data = tabBean.getData();
                        fragments = new ArrayList<>();
                        tabbean.addAll(data);
                        for (int i = 0; i < tabbean.size(); i++) {
                            fragments.add(new VpFragment(tabbean.get(i).getType()));
                        }
                        mVp.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
                            @Override
                            public Fragment getItem(int position) {
                                return fragments.get(position);
                            }

                            @Override
                            public int getCount() {
                                return fragments.size();
                            }

                            @Nullable
                            @Override
                            public CharSequence getPageTitle(int position) {
                                return tabbean.get(position).getName();
                            }
                        });
                        mTab.setupWithViewPager(mVp);
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
        mLl = (LinearLayout) itemView.findViewById(R.id.ll);
        mTitle1Iv = (ImageView) itemView.findViewById(R.id.iv_title1);
        mTitle2Iv = (ImageView) itemView.findViewById(R.id.iv_title2);
        mRemenTv = (TextView) itemView.findViewById(R.id.tv_remen);
        mRlv = (RecyclerView) itemView.findViewById(R.id.rlv);
        mTab = (TabLayout) itemView.findViewById(R.id.tab);
        mVp = (ViewPager) itemView.findViewById(R.id.vp);
        dataBeans = new ArrayList<>();
        adapter = new RlvAdapter(dataBeans, getActivity());
        mRlv.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        mRlv.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        mRlv.setAdapter(adapter);
        tabbean = new ArrayList<>();
        mTitle1Iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SheTuanActivity.class);
                startActivity(intent);
            }
        });
        mTitle2Iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PaiActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onSeccuss(HotBean hotBean) {
        List<HotBean.DataBean> data = hotBean.getData();
        dataBeans.addAll(data);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onFail(String error) {
        Log.e("TAG", "onFail: " + error);
    }
}
