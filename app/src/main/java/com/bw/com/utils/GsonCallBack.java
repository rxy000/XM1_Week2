package com.bw.com.utils;


import android.os.Handler;

import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by ruixuyao on 2018/03/30.
 */

public abstract class GsonCallBack<T> implements Callback {
    private Handler handler=new Handler();
    public abstract void onUi(T t);
    public abstract void onFailed(Call call, IOException e);
    //请求失败
    @Override
    public void onFailure(final Call call, final IOException e) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                onFailed(call,e);
            }
        });
    }
    //请求成功
    @Override
    public void onResponse(Call call, Response response) throws IOException {
        String json=response.body().string();
        Class<T> cls=null;
        Class clz=this.getClass();
        ParameterizedType type= (ParameterizedType) clz.getGenericSuperclass();
        Type[] types=type.getActualTypeArguments();
        cls= (Class<T>) types[0];
        Gson gson=new Gson();
        final T t=gson.fromJson(json,cls);
        handler.post(new Runnable() {
            @Override
            public void run() {
                onUi(t);
            }
        });
    }
}
