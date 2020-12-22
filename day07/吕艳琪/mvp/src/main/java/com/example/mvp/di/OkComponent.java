package com.example.mvp.di;

import com.example.mvp.mvp.model.OkManager;
import com.example.mvp.mvp.model.RxOpretorImpl;
import javax.inject.Singleton;
import dagger.Component;

@Singleton
@Component(modules = OkManager.class)
public interface OkComponent {
    void getSingleApiService(RxOpretorImpl impl);
}
