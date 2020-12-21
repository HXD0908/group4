package com.example.tongpao.net;

import com.example.tongpao.api.TongPaoApi;
import com.example.tongpao.constants.TongPaoConstans;
import com.example.tongpao.utils.DigestUtils;
import com.example.tongpao.utils.SpUtils;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpManager {
    private static HttpManager instance;
    private TongPaoApi tongpaoApi;

    public static HttpManager getInstance() {
        if (instance == null) {
            synchronized (HttpManager.class) {
                if (instance == null) {
                    instance = new HttpManager();
                }
            }
        }
        return instance;
    }

    public HttpManager() {
    }

    private static String[] outHeaderUrl = {"login", "register"};
    private static String signUrl = "cdwan.cn"; //需要签名的域名

    private Retrofit getRetrofit(String url) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .client(getOkHttpClient())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }

    private OkHttpClient getOkHttpClient() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(new LoggingInterceptor())
                .addInterceptor(new HeaderInterceptor())
                .build();
        return okHttpClient;
    }

    static class LoggingInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            return chain.proceed(request);
        }
    }

    static class HeaderInterceptor implements Interceptor {

        public Response intercept(Chain chain) throws IOException {
            String url = chain.request().url().toString();
            String host = chain.request().url().host();
            //判断对应的url接口是否需要添加头信息
            boolean bool = false;
            for (int i = 0; i < outHeaderUrl.length; i++) {
                if (url.indexOf(outHeaderUrl[i]) != -1) {
                    bool = true;
                    break;
                }
            }
            if (signUrl.equals(host) && !bool) {
                String token = SpUtils.getInstance().getString("token");
                //签名处理
                int timestamp = (int) (new Date().getTime() / 1000);
                //随机字符
                String nonce = UUID.randomUUID().toString();
                //保存需要签名验证的参数
                //消息头
                Map<String, String> map = new HashMap<>();
                map.put("timestamp", String.valueOf(timestamp));
                map.put("nonce", nonce);
                map.put("token", token);

                //地址中的参数获取
                HttpUrl.Builder urlBuilder = chain.request().url().newBuilder();
                //获取get请求的地址参数
                Set<String> paramKeys = urlBuilder.build().queryParameterNames();
                for (String key : paramKeys) {
                    String value = urlBuilder.build().queryParameter(key);
                    map.put(key, URLDecoder.decode(value));
                }

                //如果当前是post请求
                if ("POST".equals(chain.request().method())) {
                    //获取当前网络请求中的参数和值
                    FormBody formBody = (FormBody) chain.request().body();
                    if (formBody != null) {
                        for (int i = 0; i < formBody.size(); i++) {
                            map.put(formBody.encodedName(i), URLDecoder.decode(formBody.encodedValue(i)));
                        }
                    }
                }

                //获取需要签名的参数字符串
                String source = DigestUtils.getSignSource(map);
                //通过参数字符串获取对应的签名
                String sign = DigestUtils.Encrypt(source, null);
                //获取请求对象
                Request.Builder builder = chain.request().newBuilder();
                builder.addHeader("token", token);
                builder.addHeader("sign", sign);
                builder.addHeader("timestamp", String.valueOf(timestamp));
                builder.addHeader("nonce", nonce);
                Request request = builder.build();
                Response response = chain.proceed(request);
                //Log.i("response:",response.body().string());
                return response;
            } else {
                return chain.proceed(chain.request());
            }
        }
    }

    public TongPaoApi getTongPaoApi() {
        if (tongpaoApi == null) {
            tongpaoApi = getRetrofit(TongPaoConstans.Base_TpUrl).create(TongPaoApi.class);
        }
        return tongpaoApi;
    }
}
