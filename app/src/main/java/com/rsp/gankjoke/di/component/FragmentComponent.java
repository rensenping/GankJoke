package com.rsp.gankjoke.di.component;

import com.rsp.gankjoke.di.annotation.FragmentScope;
import com.rsp.gankjoke.di.module.FragmentModule;
import com.rsp.gankjoke.ui.gank.fragment.GankMainFragment;
import com.rsp.gankjoke.ui.gank.fragment.GirlFragment;
import com.rsp.gankjoke.ui.gank.fragment.TechFragment;
import com.rsp.gankjoke.ui.joke.fragment.JokeMainFragment;
import com.rsp.gankjoke.ui.news.fragment.NewsListFragment;
import com.rsp.gankjoke.ui.news.fragment.NewsMainFragment;

import dagger.Component;

/**
 * @author 小任
 * @date 2016/10/18
 * version 1.0
 * 描述:
 */
@FragmentScope
@Component(dependencies = AppComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {
    void inject(GankMainFragment gankMainFragment);

    void inject(TechFragment techFragment);

    void inject(GirlFragment girlFragment);

    void inject(NewsMainFragment newsMainFragment);
    void inject(NewsListFragment newsListFragment);
    void inject(JokeMainFragment jokeMainFragment);
}
