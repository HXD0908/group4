package com.jy.day06.mvp.view.activity;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.view.KeyEvent;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.jy.day06.R;
import com.jy.day06.base.BaseActivity;
import com.jy.day06.base.BasePresenter;
import com.jy.day06.manager.ContainManager;
import com.jy.day06.mvp.view.fragment.FindFragment;
import com.jy.day06.mvp.view.fragment.MyFragment;
import com.jy.day06.mvp.view.fragment.PlusFragment;
import com.jy.day06.mvp.view.fragment.HomeFragment;
import com.jy.day06.mvp.view.fragment.ShoppingFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class HomeActivity extends BaseActivity {

    private static final String TAG = "HomeActivity";
    @BindView(R.id.home_viewpager)
    ViewPager homeViewpager;
    @BindView(R.id.home_tablayout)
    TabLayout homeTablayout;

    private boolean IsExit;
    private Handler handler = new Handler ();

    // TODO 监听返回键
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //TODO  2秒内连续点击两次 退出程序，  如果第二次点击超过2秒了，会Toast提示再按一次退出程序
            if (!IsExit) {
                IsExit = true;
                Toast.makeText (this, "再点击一次退出程序", Toast.LENGTH_SHORT).show ();
                // TODO 2秒之后把变量值改成true
                handler.postDelayed (new Runnable () {
                    @Override
                    public void run() {
                        IsExit = false;
                    }
                }, 2000);

            } else {
                ContainManager.getContainManager ().clearActivity ();
            }
            return false;
        }
        return super.onKeyDown (keyCode, event);
    }


    @Override
    protected void init() {
                List<Fragment> list = new ArrayList<> ();
        list.add (new HomeFragment ());
        list.add (new FindFragment ());
        list.add (new PlusFragment ());
        list.add (new ShoppingFragment ());
        list.add (new MyFragment ());

        homeViewpager.setAdapter (new FragmentPagerAdapter (getSupportFragmentManager ()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return list.get (position);
            }

            @Override
            public int getCount() {
                return list.size ();
            }
        });
        homeTablayout.setupWithViewPager (homeViewpager);
        homeTablayout.getTabAt (0).setText (R.string.homet_title).setIcon (R.drawable.home_tablayout);
        homeTablayout.getTabAt (1).setText (R.string.find_title).setIcon (R.drawable.find_tablayout);
        homeTablayout.getTabAt (2).setIcon (R.drawable.icon_home_plus);
        homeTablayout.getTabAt (3).setText (R.string.shopping_title).setIcon (R.drawable.shopping_tablayout);
        homeTablayout.getTabAt (4).setText (R.string.my_title).setIcon (R.drawable.my_tablayout);
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.home_activity;
    }

}
