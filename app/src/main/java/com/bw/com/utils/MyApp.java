package com.bw.com.utils;

import android.app.Application;


public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        MyImageLoader.initImagerloader(this);
    }
}
