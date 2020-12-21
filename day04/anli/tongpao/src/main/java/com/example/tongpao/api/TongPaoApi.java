package com.example.tongpao.api;

import com.example.tongpao.bean.FoundTabBean;
import com.example.tongpao.bean.HotActivityBean;
import com.example.tongpao.bean.HotBean;
import com.example.tongpao.bean.InfoBean;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface TongPaoApi {

    @GET("discover/hot_activity.json")
    Flowable<HotActivityBean> getHotactivity();

    @GET("discover/navigation.json ")
    Flowable<FoundTabBean> getTab();
    //发现热门活动
    @GET("discover/hot_activity.json")
    Flowable<HotBean> getHotDate();
    @GET("discover/navigation.json")
    Flowable<FoundTabBean> getTabData();

    @GET("discover/news_{type}.json")
    Flowable<InfoBean> getInfoDate(@Path("type") int type);
}
