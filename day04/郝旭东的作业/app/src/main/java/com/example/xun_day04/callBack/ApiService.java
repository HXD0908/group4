package com.example.xun_day04.callBack;

import com.example.xun_day04.bean.HomeBean;
import com.example.xun_day04.bean.JinYanBean;
import com.example.xun_day04.bean.QinaDaoBean;
import com.example.xun_day04.bean.ReDainBean;
import com.example.xun_day04.bean.TabBean;
import com.example.xun_day04.bean.ToHaoBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {
    String URL = "http://cdwan.cn:7000/";

    @GET("tongpao//discover/hot_activity.json")
    Observable<ReDainBean> getRe();

    @GET("tongpao//discover/navigation.json")
    Observable<TabBean> getTab();

    @GET("tongpao//discover/news_{page}.json")
    Observable<HomeBean> getHome(@Path("page") int page);

    @GET("/discover/rank_level.json")
    Observable<JinYanBean> getJin();

    @GET("/discover/rank_money.json")
    Observable<ToHaoBean> getTuHao();

    @GET("/discover/rank_sign.json")
    Observable<QinaDaoBean> getQianDao();


}
