package com.example.mymvp.mvp.ui.activity;

import android.view.KeyEvent;
import android.widget.Toast;

import com.example.mymvp.R;
import com.example.mymvp.base.App;
import com.example.mymvp.base.BaseActivity;
import com.example.mymvp.base.BasePresenter;
import com.example.mymvp.engine.adapter.HomeActVpAdapter;
import com.example.mymvp.manager.ContainManager;
import com.example.mymvp.manager.ThreadPoolManager;
import com.example.mymvp.mvp.ui.fragment.CollectFragment;
import com.example.mymvp.mvp.ui.fragment.HomeFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;

public class HomeActivity extends BaseActivity {
    @BindView(R.id.home_act_vp)
    ViewPager mHomeActVp;
    @BindView(R.id.home_act_tab)
    TabLayout mHomeActTab;
    private boolean mIsExit;
    private FragmentManager mManager;
    private int[] mTabs = {R.string.home_tab_title, R.string.collect_tab_title};

    @Override
    protected void init() {
        mManager = getSupportFragmentManager();
        HomeActVpAdapter homeActVpAdapter = new HomeActVpAdapter(mManager, getFragments(), mTabs);
        mHomeActVp.setAdapter(homeActVpAdapter);
        mHomeActTab.setupWithViewPager(mHomeActVp);
    }

    private List<Fragment> getFragments() {
        List<Fragment> fragments = new ArrayList();
        fragments.add(new HomeFragment());
        fragments.add(new CollectFragment());
        return fragments;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_home;
    }

    //监听返回键
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //TODO  2秒内连续点击两次 退出程序，  如果第二次点击超过2秒了，会Toast提示再按一次退出程序
            if (!mIsExit) {
                mIsExit = true;
                Toast.makeText(App.context(), "再按一次退出程序", Toast.LENGTH_LONG).show();
                //2秒之后把变量值改成true
                ThreadPoolManager.
                        getThreadPool(ThreadPoolManager.SCHDULE_THREADPOOL).
                        executeTimerTask(new Runnable() {
                            @Override
                            public void run() {
                                mIsExit = false;
                            }
                        }, 2, TimeUnit.SECONDS);
            } else {
                ContainManager.getManager().clearActivity();
            }
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }
}