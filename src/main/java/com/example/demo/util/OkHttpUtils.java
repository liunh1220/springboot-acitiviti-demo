package com.example.demo.util;

import com.example.demo.constant.ApplicationConstant;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;

import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2018/5/21.
 */
public class OkHttpUtils {

    public static OkHttpClient.Builder okHttpClientBuilder(ApplicationConstant applicationConstant) {
        return new okhttp3.OkHttpClient.Builder()
                .readTimeout(applicationConstant.okHttpReadTimeout, TimeUnit.MILLISECONDS)
                .connectTimeout(applicationConstant.okHttpConnectTimeout, TimeUnit.MILLISECONDS)
                .writeTimeout(applicationConstant.okHttpWriteTimeout, TimeUnit.MILLISECONDS)
                .retryOnConnectionFailure(true)
                .connectionPool(new ConnectionPool(applicationConstant.okHttpMaxIdle,
                        applicationConstant.okHttpAliveDuration, TimeUnit.SECONDS))
                .addInterceptor(new OkHttpLoggingInterceptor(applicationConstant));
    }



}
