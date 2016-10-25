package com.rsp.gankjoke.di.component;

import com.rsp.gankjoke.App;
import com.rsp.gankjoke.api.GankApi;
import com.rsp.gankjoke.api.JokeApi;
import com.rsp.gankjoke.api.NewsApi;
import com.rsp.gankjoke.di.module.AppModule;
import com.rsp.gankjoke.util.ACache;

import javax.inject.Singleton;

import dagger.Component;
import dagger.Module;

/**
 * @author 小任
 * @date 2016/10/18
 * version 1.0
 * 描述:
 */
@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
    GankApi getGankApi();

    App getApp();

    ACache getACache();

    NewsApi getNewsApi();

    JokeApi getJokeApi();

   void inject(App app);
}
