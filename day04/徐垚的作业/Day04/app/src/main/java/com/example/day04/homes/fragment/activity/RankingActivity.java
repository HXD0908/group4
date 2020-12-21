package com.example.day04.homes.fragment.activity;

import com.example.day04.R;
import com.example.day04.homes.fragment.api.IRank;
import com.example.day04.homes.fragment.presenter.RankingPresenter;
import com.example.day04.homes.fragment.rankfragment.LevelFragment;
import com.example.day04.homes.fragment.rankfragment.MoneyFragment;
import com.example.day04.homes.fragment.rankfragment.SignFragment;
import com.google.android.material.tabs.TabLayout;
import com.ylong.mvp.base.BaseActivity;
import com.ylong.mvp.bean.LevelBean;
import com.ylong.mvp.bean.MoneyBean;
import com.ylong.mvp.bean.SignBean;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;

public class RankingActivity extends BaseActivity<RankingPresenter> implements IRank.RankView {

    @BindView(R.id.ranking_tab)
    TabLayout rankingTab;
    @BindView(R.id.ranking_vp)
    ViewPager rankingVp;
    private ArrayList<MoneyBean.DataBean.TongQianTopBean.ListBean> moneyBeans;
    private ArrayList<SignBean.DataBean.SignTopBean.ListBean> signBeans;
    private ArrayList<LevelBean.DataBean.ExpTopBean.ListBean> levelBeans;
    private ArrayList<Fragment> fragments;

    @Override
    protected int getLayout() {
        return R.layout.activity_ranking;
    }

    @Override
    protected void initView() {

    }

    public class VpAdapter extends FragmentPagerAdapter {

        public VpAdapter(@NonNull FragmentManager fm) {
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
    }

    @Override
    protected RankingPresenter createPersenter() {
        return new RankingPresenter(this);
    }

    @Override
    protected void initData() {
        initTab();
    }

    @Override
    public void getMoneyData(MoneyBean bean) {

    }

    @Override
    public void getSignData(SignBean bean) {

    }

    @Override
    public void getLevelData(LevelBean bean) {

    }

    private void initTab() {
        moneyBeans = new ArrayList<>();
        signBeans = new ArrayList<>();
        levelBeans = new ArrayList<>();

        fragments = new ArrayList<>();
        fragments.add(new MoneyFragment());
        fragments.add(new SignFragment());
        fragments.add(new LevelFragment());

        ArrayList<String> titles = new ArrayList<>();
        titles.add("土豪榜");
        titles.add("等级榜");
        titles.add("签到榜");

        rankingVp.setAdapter(new VpAdapter(getSupportFragmentManager()));
        rankingTab.setupWithViewPager(rankingVp);

        for (int i = 0; i < titles.size(); i++) {
            rankingTab.getTabAt(i).setText(titles.get(i));
        }
    }

    @Override
    public void showError(String msg) {

    }
}