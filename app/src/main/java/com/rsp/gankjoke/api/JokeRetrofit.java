package com.rsp.gankjoke.api;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author 小任
 * @date 2016/10/21
 * version 1.0
 * 描述:
 */

public class JokeRetrofit {
    public Retrofit getRetrofit() {
        return retrofit;
    }

    private Retrofit retrofit;
    public JokeRetrofit(){
        retrofit = new Retrofit.Builder()
                .baseUrl("http://v.juhe.cn/joke/")
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

}
