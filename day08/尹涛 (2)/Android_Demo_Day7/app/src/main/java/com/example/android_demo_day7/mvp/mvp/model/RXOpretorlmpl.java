package com.example.android_demo_day7.mvp.mvp.model;

import com.example.android_demo_day7.mvp.mvp.model.api.ApiService;

import javax.inject.Inject;

import dagger.internal.DaggerCollections;

/**
 * 封装的网络请求操作类
 */
public class RXOpretorlmpl {
    @Inject
    ApiService mApiService;

    public RXOpretorlmpl() {

    }
}
