package com.example.mymvp.di.component;

import com.example.mymvp.mvp.model.OkManager;
import com.example.mymvp.mvp.model.RxOpretorImpl;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by XY.
 * Date: 2020/12/22
 *
 * 注射器
 */
@Singleton
@Component(modules = OkManager.class)
public interface OkComponent {
    // 自定义的方法
    void getSingleApiService(RxOpretorImpl impl);
}
