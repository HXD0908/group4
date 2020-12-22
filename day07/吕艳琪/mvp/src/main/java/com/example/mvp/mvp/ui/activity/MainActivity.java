package com.example.mvp.mvp.ui.activity;

import android.view.KeyEvent;
import android.widget.Toast;
import com.example.mvp.R;
import com.example.mvp.app.MyApp;
import com.example.mvp.base.IBaseActivity;
import com.example.mvp.base.IBasePresenter;
import com.example.mvp.manager.ContainManager;
import com.example.mvp.manager.ThreadPoolManager;

import java.util.concurrent.TimeUnit;

public class MainActivity extends IBaseActivity {

    private boolean mIsExit;

    @Override
    protected IBasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //TODO  2秒内连续点击两次 退出程序，  如果第二次点击超过2秒了，会Toast提示再按一次退出程序
            if (!mIsExit) {
                mIsExit = true;
                Toast.makeText(MyApp.context(), "再按一次退出程序", Toast.LENGTH_LONG).show();
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
                ContainManager.getmManager().clearActivity();
            }
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }
}
