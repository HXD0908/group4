package com.example.jetpack.api;

import com.example.jetpack.bean.FoundBean;
import com.example.jetpack.bean.PicBean;
import com.example.jetpack.bean.TextBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
public interface ApiService {
    String baseUrl = "http://123.56.232.18:8080/";
    @GET("serverdemo/feeds/queryHotFeedsList?pageCount=12&feedType=pics")
    Observable<PicBean> getTu();
    String getBaseUrl = "http://123.56.232.18:8080/";
    @GET("serverdemo/feeds/queryHotFeedsList?pageCount=10&feedType=text&feedId=1578920275")
    Observable<TextBean> getText();
    String getGetBaseUrl = "http://123.56.232.18:8080/";
    @GET("serverdemo//tag/queryTagList")
    Observable<FoundBean> getTab();

}
