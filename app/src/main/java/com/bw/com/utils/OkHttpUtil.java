package com.bw.com.utils;

import android.os.Environment;

import java.io.File;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by ruixuyao on 2018/03/30.
 */

public class OkHttpUtil {
    private static OkHttpUtil util;
    private OkHttpUtil(){

    }
    public static OkHttpUtil getInstance(){
        if(util==null){
            synchronized (OkHttpUtil.class){
                if(util==null){
                    util=new OkHttpUtil();
                }
            }
        }
        return util;
    }
    private static OkHttpClient okHttpClient;
    private static OkHttpClient getOkHttpClient(){
        MyGetInterceptor interceptor = new MyGetInterceptor.Builder().addQueryParam("source", "android").build();
        if (okHttpClient == null) {
            File sdcache = new File(Environment.getExternalStorageDirectory(), "cache");
            int cacheSize = 10 * 1024 * 1024;
            okHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .connectTimeout(15, TimeUnit.SECONDS)
                    .cache(new Cache(sdcache, cacheSize))
                    .build();
        }
        return okHttpClient;
    }
    public  void doGet(String url, Callback callback) {
        //创建OkHttpClient请求对象
        OkHttpClient okHttpClient = getOkHttpClient();
        // OkHttpClient okHttpClient=new OkHttpClient();
        //创建Request
        Request request = new Request.Builder().url(url).build();
        //得到Call对象
        Call call = okHttpClient.newCall(request);
        //执行异步请求
        call.enqueue(callback);
    }
    public void doPost(String url, Map<String, String> params, Callback callback) {
        //创建OkHttpClient请求对象
        OkHttpClient okHttpClient = getOkHttpClient();
        //3.x版本post请求换成FormBody 封装键值对参数
        FormBody.Builder builder = new FormBody.Builder();
        //遍历集合
        for (String key : params.keySet()) {
            builder.add(key, params.get(key));
        }
        //创建Request
        Request request = new Request.Builder().url(url).post(builder.build()).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(callback);
    }
}
