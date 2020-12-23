package com.example.mymvp.mvp.model;

import com.example.mymvp.callback.RxObserverCallBack;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by XY.
 * Date: 2020/12/21
 */
public class HomeModel {
    // 3.M层请求到数据之后 , 通过接口回调将数据回传给P层
    public void requestData(RxObserverCallBack callBack) {
        Request request = new Request.Builder()
                .url("https://fanyi.baidu.com/?aldtype=85#en/zh/detach")
                .build();
        new OkHttpClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callBack.onErrorMsg(e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                callBack.onSuccessData(string);
            }
        });
    }
}
