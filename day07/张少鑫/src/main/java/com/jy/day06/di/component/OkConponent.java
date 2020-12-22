package com.jy.day06.di.component;

import com.jy.day06.mvp.model.RxOpretorImpl;
import com.jy.day06.mvp.model.OkManager;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = OkManager.class)
public interface OkConponent {
void getStringApiService(RxOpretorImpl impl);
}
