package com.example.day4_1;

import com.example.day4_1.bean.PaoZiBean;
import com.example.day4_1.bean.ReMenBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface Apisrevice {
    //http://cdwan.cn:7000/tongpao/home/topic_discussed.json
    String URL = "http://cdwan.cn:7000/";

    @GET("tongpao/home/topic_discussed.json")
    Observable<ReMenBean> getRemenhuati();


    //http://cdwan.cn:7000/tongpao/discover/robe.json
    @GET("tongpao/discover/robe.json")
    Observable<PaoZiBean> getPaozi();


}
