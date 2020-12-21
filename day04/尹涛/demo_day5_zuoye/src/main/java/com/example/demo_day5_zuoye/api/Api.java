package com.example.demo_day5_zuoye.api;

import com.example.demo_day5_zuoye.bean.HotBean;
import com.example.demo_day5_zuoye.bean.JingBean;
import com.example.demo_day5_zuoye.bean.ObBean;
import com.example.demo_day5_zuoye.bean.QianBean;
import com.example.demo_day5_zuoye.bean.TabBean;
import com.example.demo_day5_zuoye.bean.TuBean;
import com.example.demo_day5_zuoye.bean.VpBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Api {
    String baseUrl = "http://cdwan.cn:7000/";
    @GET("tongpao/discover/hot_activity.json")
    Observable<HotBean> getHot();
    String getBaseUrl = "http://cdwan.cn:7000/";
    @GET("tongpao/discover/navigation.json")
    Observable<TabBean> getTab();
    String getGetBaseUrl = "http://cdwan.cn:7000/";
    @GET("tongpao/discover/news_{path}.json")
    Observable<VpBean> getVp(@Path("path") int type);
    String subject = "http://cdwan.cn:7000/";
    @GET("tongpao/discover/association.json")
    Observable<ObBean> getJie();
    String jing = "http://cdwan.cn:7000/";
    @GET("tongpao/discover/rank_level.json")
    Observable<JingBean> getJing();
    String tu = "http://cdwan.cn:7000/";
    @GET("tongpao/discover/rank_money.json")
    Observable<TuBean> getTu();
    String qian = "http://cdwan.cn:7000/";
    @GET("tongpao/discover/rank_sign.json")
    Observable<QianBean> getQian();
}
