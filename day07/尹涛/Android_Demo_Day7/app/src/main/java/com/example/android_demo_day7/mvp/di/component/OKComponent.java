package com.example.android_demo_day7.mvp.di.component;

import com.example.android_demo_day7.mvp.mvp.model.OKManager;

import javax.inject.Singleton;

import dagger.Component;

//注射器
@Singleton
@Component(modules = OKManager.class)
public interface OKComponent {
    //自定义的方法
    void getSingleApiService()
}
