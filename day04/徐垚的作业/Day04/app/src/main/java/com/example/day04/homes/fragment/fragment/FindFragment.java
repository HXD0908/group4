package com.example.day04.homes.fragment.fragment;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;

import android.content.Intent;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.day04.R;
import com.example.day04.adapter.HotRecyclerAdapter;
import com.example.day04.homes.fragment.activity.RankingActivity;
import com.example.day04.homes.fragment.api.IView;
import com.example.day04.homes.fragment.presenter.HomePresenter;
import com.google.android.material.tabs.TabLayout;
import com.ylong.mvp.base.BaseFragment;
import com.ylong.mvp.bean.HotActivityBean;

import java.util.ArrayList;
import java.util.List;

public class FindFragment extends BaseFragment<HomePresenter> implements IView.FindView {

    @BindView(R.id.scroll)
    ScrollView mScrollView;
    @BindView(R.id.title_tab)
    TabLayout titleTab;
    @BindView(R.id.tv_more_talk)
    TextView tvMoreTalk;
    @BindView(R.id.news_recycle)
    RecyclerView newsRecycler;
    @BindView(R.id.content_tab)
    TabLayout contentTab;
    @BindView(R.id.content_vp)
    ViewPager contentVp;
    @BindView(R.id.ll_content_tab)
    TabLayout llContentTab;
    @BindView(R.id.ll_tab)
    LinearLayout llContent;

    private HotRecyclerAdapter adapter;
    private List<HotActivityBean.DataBean> beans;

    @Override
    public int getLayout() {
        return R.layout.fragment_find;
    }

    @Override
    public void initView() {
        persenter.getFindData();

        beans = new ArrayList<>();

        adapter = new HotRecyclerAdapter(getActivity(), beans);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        newsRecycler.setLayoutManager(linearLayoutManager);
        newsRecycler.setAdapter(adapter);
        adapter.notifyDataSetChanged();


        titleTab.addTab(titleTab.newTab().setText("袍子").setIcon(R.drawable.d));
        titleTab.addTab(titleTab.newTab().setText("社团").setIcon(R.drawable.b));
        titleTab.addTab(titleTab.newTab().setText("排行榜").setIcon(R.drawable.c));

        titleTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab pTab) {
                if (pTab.getPosition() == 2) {
                    startActivity(new Intent(getActivity(), RankingActivity.class));
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab pTab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab pTab) {

            }
        });

        //去掉  TabLayout  下划线
        titleTab.setSelectedTabIndicatorHeight(0);

        // 自动吸顶
//        mScrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
//            @Override
//            public void onScrollChange(View pView, int pI, int pI1, int pI2, int pI3) {
//                if (pI1 > newsRecycler.getHeight()) {
//                    llContent.setVisibility(View.VISIBLE);
//                } else {
//                    llContent.setVisibility(View.GONE);
//                }
//            }
//        });
    }

    @Override
    public HomePresenter createPersenter() {
        return new HomePresenter(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void getFindData(HotActivityBean bean) {
        beans.addAll(bean.getData());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void getError(String msg) {

    }
}