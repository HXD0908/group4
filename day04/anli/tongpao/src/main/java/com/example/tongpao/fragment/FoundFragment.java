package com.example.tongpao.fragment;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.tongpao.R;
import com.example.tongpao.adapter.HotActivityAdapter;
import com.example.tongpao.base.BaseFragment;
import com.example.tongpao.bean.FoundTabBean;
import com.example.tongpao.bean.HotActivityBean;
import com.example.tongpao.interfaces.HotactivityPresenter;
import com.example.tongpao.interfaces.IFound;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import io.reactivex.annotations.NonNull;
import io.reactivex.annotations.Nullable;

public class FoundFragment extends BaseFragment<IFound.Presenter> implements IFound.View {


    @BindView(R.id.find_tool_bar)
    Toolbar findToolBar;
    @BindView(R.id.robe)
    ImageView robe;
    @BindView(R.id.find_lin)
    LinearLayout findLin;
    @BindView(R.id.iv_activity)
    ImageView ivActivity;
    @BindView(R.id.iv_rank)
    ImageView ivRank;
    @BindView(R.id.gengduo)
    TextView gengduo;
    @BindView(R.id.rv_found_hot)
    RecyclerView rvFoundHot;
    @BindView(R.id.found_view_pager)
    ViewPager foundViewPager;
    @BindView(R.id.found_tab_layout)
    TabLayout foundTabLayout;
    private ArrayList<HotActivityBean.DataBean> list;
    private ArrayList<Fragment> fragments;
    private ArrayList<String> tabs;
    private HotActivityAdapter hotActivityAdapter;

    @Override
    protected int getLayout() {
        return R.layout.fragment_found;
    }

    @Override
    protected void initView() {
        list = new ArrayList<>();
        fragments = new ArrayList<>();
        tabs = new ArrayList<>();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvFoundHot.setLayoutManager(linearLayoutManager);
        hotActivityAdapter = new HotActivityAdapter(getActivity(), list);
        rvFoundHot.setAdapter(hotActivityAdapter);
    }

    @Override
    protected IFound.Presenter createPresenter() {
        return new HotactivityPresenter();
    }

    @Override
    protected void initData() {
        presenter.getHotactivity();
        presenter.getTab();
    }

    @Override
    public void setHotactivity(HotActivityBean hotactivity) {
        if (hotactivity.getStatus().getStatusCode() == 100) {
            list.addAll(hotactivity.getData());
            hotActivityAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void setTab(FoundTabBean foundTabBean) {
        if (foundTabBean.getStatus().getStatusCode()==100){

            List<FoundTabBean.DataBean> data = foundTabBean.getData();
            for (int i = 0; i < data.size(); i++) {
                tabs.add(data.get(i).getName());
                fragments.add(new HotFragment(data.get(i).getType()));
            }
            foundViewPager.setAdapter(new FoundViewPage(getChildFragmentManager()));
            foundTabLayout.setupWithViewPager(foundViewPager);
        }
    }
    public class FoundViewPage extends FragmentPagerAdapter {

        public FoundViewPage(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
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
            return tabs.get(position);
        }
    }
}