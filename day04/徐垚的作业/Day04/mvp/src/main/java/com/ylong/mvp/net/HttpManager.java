package com.ylong.mvp.net;

import com.ylong.mvp.api.HttpApi;
import com.ylong.mvp.api.ImageApi;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 网络请求
 * 整个项目全局使用
 */
public class HttpManager {

    private static HttpManager instance;

    public  static HttpManager getInstance(){
        if(instance == null){
            synchronized(HttpManager.class){
                if(instance == null){
                    instance = new HttpManager();
                }
            }
        }
        return instance;
    }

    private HttpApi httpApi;

    private ImageApi imageApi; //图片下载接口

    private Map<String,Retrofit> map = new HashMap<>();  //retrofit请求对象的对象池

    private Retrofit getRetrofit(String url){
        Retrofit retrofit = new Retrofit.Builder().baseUrl(url)
                .client(getOkHttpClient())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }

    private OkHttpClient getOkHttpClient(){
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60,TimeUnit.SECONDS)
                .addInterceptor(new LoggingInterceptor())
                .addInterceptor(new HeaderInterceptor())
                .build();
        return okHttpClient;
    }

    static class LoggingInterceptor implements Interceptor{

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            return chain.proceed(request);
        }
    }

    static class HeaderInterceptor implements Interceptor{

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request().newBuilder()
                    .addHeader("Authorization","APPCODE 964e16aa1ae944e9828e87b8b9fbd30a")
                    .build();
            return chain.proceed(request);
        }
    }

    /**
     * HttpApi
     * @return
     */
    public HttpApi getHttpApi(){
        if(httpApi ==  null){
            httpApi = getRetrofit(HttpApi.BASE_URL).create(HttpApi.class);
        }
        return httpApi;
    }

    /**
     * 获取图片下载的对象
     * @param baseUrl
     * @return
     */
    public ImageApi getImageApi(String baseUrl){
        Retrofit retrofit = map.get(baseUrl);
        if(retrofit != null){
            imageApi = retrofit.create(ImageApi.class);
        }else{
            retrofit = getRetrofit(baseUrl);
            imageApi = retrofit.create(ImageApi.class);
            map.put(baseUrl,retrofit);
        }
        return imageApi;
    }


}
