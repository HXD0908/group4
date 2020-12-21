package com.ylong.mvp.api;

import com.ylong.mvp.bean.HotActivityBean;
import com.ylong.mvp.bean.LevelBean;
import com.ylong.mvp.bean.MoneyBean;
import com.ylong.mvp.bean.SignBean;

import io.reactivex.Flowable;
import retrofit2.http.GET;

public interface HttpApi {

    String BASE_URL = "http://cdwan.cn:7000/tongpao/";

    @GET("discover/hot_activity.json")
    Flowable<HotActivityBean> getFindData();

    //排行榜--土豪榜 /discover/rank_money.json
    @GET("discover/rank_money.json")
    Flowable<MoneyBean> getMoneyData();

    //排行榜--签到榜 /discover/rank_sign.json
    @GET("discover/rank_sign.json")
    Flowable<SignBean> getSignData();

    // 排行榜--等级榜  /discover/rank_level.json
    @GET("discover/rank_level.json")
    Flowable<LevelBean> getLevelData();
}
