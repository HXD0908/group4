package com.jy.day04.model.api;

import com.jy.day04.model.bean.FindContentBean;
import com.jy.day04.model.bean.FindMoreBean;
import com.jy.day04.model.bean.FindTitleBean;
import com.jy.day04.model.bean.RanExperBean;
import com.jy.day04.model.bean.RanLocalBean;
import com.jy.day04.model.bean.RanMassBean;
import com.jy.day04.model.bean.RanSignBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface FindApiService {
    String baseUrl = "http://cdwan.cn:7000/tongpao/";

    @GET("discover/hot_activity.json")
    Observable<FindMoreBean> getMore();


    @GET("discover/navigation.json")
    Observable<FindTitleBean> getTitle();

    @GET("discover/news_{path}.json")
    Observable<FindContentBean> getContent(@Path("path") int path);

    // 排行榜 经验榜
    @GET("discover/rank_level.json")
    Observable<RanExperBean> getExper();

    // 排行榜 土豪榜
    @GET("discover/rank_money.json")
    Observable<RanLocalBean> getLocal();

    // 排行榜 签到榜
    @GET("discover/rank_sign.json")
    Observable<RanSignBean> getSign();

    // 社团
    @GET("discover/association.json")
    Observable<RanMassBean> getMass();
}
