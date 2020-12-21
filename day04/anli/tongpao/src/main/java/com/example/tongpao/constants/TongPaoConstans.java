package com.example.tongpao.constants;

import com.example.tongpao.app.MyApp;

import java.io.File;

public class TongPaoConstans {
    public static final String Base_TpUrl = "http://cdwan.cn:7000/tongpao/";
    //网络缓存的地址
    public static final String PATH_DATA = MyApp.context.getCacheDir().getAbsolutePath() + File.separator + "data";

    public static final String PATH_CACHE = PATH_DATA + "/client";
}
