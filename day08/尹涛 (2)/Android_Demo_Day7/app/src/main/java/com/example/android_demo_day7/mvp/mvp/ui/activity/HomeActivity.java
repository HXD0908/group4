package com.example.android_demo_day7.mvp.mvp.ui.activity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.android_demo_day7.R;
import com.example.android_demo_day7.mvp.base.App;
import com.example.android_demo_day7.mvp.base.BaseActivity;
import com.example.android_demo_day7.mvp.base.BasePresenter;
import com.example.android_demo_day7.mvp.engine.adapter.HomeActVpAdapter;
import com.example.android_demo_day7.mvp.manager.ContainManager;
import com.example.android_demo_day7.mvp.manager.ThreadPoolManager;
import com.example.android_demo_day7.mvp.mvp.ui.fragment.CollectFragment;
import com.example.android_demo_day7.mvp.mvp.ui.fragment.HomeFragment;
import com.example.android_demo_day7.mvp.mvp.ui.fragment.MeFragment;
import com.example.android_demo_day7.mvp.mvp.ui.fragment.NavFragment;
import com.example.android_demo_day7.mvp.mvp.ui.fragment.TianFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends BaseActivity {

    @BindView(R.id.vp)
    ViewPager vp;
    @BindView(R.id.tab)
    TabLayout tab;
    private boolean mIsExit;
    private FragmentManager mManager;
    private ArrayList<Fragment> fragments;


    @Override
    protected BasePresenter createPresenter() {
        return null;
    }


    @Override
    protected void init() {
        fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new CollectFragment());
        fragments.add(new TianFragment());
        fragments.add(new NavFragment());
        fragments.add(new MeFragment());
        vp.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        });
        tab.setupWithViewPager(vp);
        tab.getTabAt(0).setText("首页");
        tab.getTabAt(1).setText("首页");
        tab.getTabAt(2).setText("首页");
        tab.getTabAt(3).setText("首页");
        tab.getTabAt(4).setText("首页");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //TODO 2秒内连续点击两次 退出程序,如果第二次点击超过两秒了,会Toast提示再按一次退出程序
            if (!mIsExit) {
                mIsExit = true;
                Toast.makeText(App.context(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                //2秒后吧变量值改成ture
                ThreadPoolManager.getThreadPool(ThreadPoolManager.SCHDULE_THREADPOOL)
                        .executeTimerTask(new Runnable() {
                            @Override
                            public void run() {
                                mIsExit = false;
                            }
                        }, 2, TimeUnit.SECONDS);
            } else {
                ContainManager.getmManager().clearActivtiy();
            }
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }
}
