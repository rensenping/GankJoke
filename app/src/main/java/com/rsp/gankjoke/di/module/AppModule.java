package com.rsp.gankjoke.di.module;

import android.app.Activity;

import com.rsp.gankjoke.App;
import com.rsp.gankjoke.api.GankApi;
import com.rsp.gankjoke.api.GankRetrofit;
import com.rsp.gankjoke.api.JokeApi;
import com.rsp.gankjoke.api.JokeRetrofit;
import com.rsp.gankjoke.api.NewsApi;
import com.rsp.gankjoke.api.NewsRetrofit;
import com.rsp.gankjoke.util.ACache;

import java.util.ArrayList;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * @author 小任
 * @date 2016/10/18
 * version 1.0
 * 描述:
 */
@Module
public class AppModule {
    private App app;

    public AppModule(App app) {
        this.app = app;
    }
    @Singleton
    @Provides
    public App proridesApp() {
        return app;
    }
    @Singleton
    @Provides
    public GankRetrofit providesGankRetrofit() {
        return new GankRetrofit();
    }
    @Singleton
    @Provides
    public GankApi providesGankApi(GankRetrofit retrofit) {
        return retrofit.getRetrofit().create(GankApi.class);
    }
    @Singleton
    @Provides
    public ACache providesACache() {
        return ACache.get(app);
    }
    @Singleton
    @Provides
    public ArrayList<Activity> providesAct() {
        return new ArrayList<Activity>();
    }
    @Singleton
    @Provides
    public NewsRetrofit providesNews() {
        return new NewsRetrofit();
    }
    @Singleton
    @Provides
    public NewsApi providesNewApi(NewsRetrofit newsRetrofit) {
        return newsRetrofit.getRetrofit().create(NewsApi.class);
    }
    @Singleton
    @Provides
    public JokeRetrofit providesJokeRetrofit(){
        return new JokeRetrofit();
    }
    @Singleton
    @Provides
    public JokeApi providesJokeApi(JokeRetrofit jokeRetrofit) {
        return jokeRetrofit.getRetrofit().create(JokeApi.class);
    }

}
