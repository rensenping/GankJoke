package com.rsp.gankjoke.api;

import javax.inject.Inject;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by heyi on 2016/10/16.
 */

public class GankRetrofit {

    private Retrofit mRetrofit;
    @Inject
    public GankRetrofit() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl("http://gank.io/api/")
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public Retrofit getRetrofit() {
        return mRetrofit;
    }
}
