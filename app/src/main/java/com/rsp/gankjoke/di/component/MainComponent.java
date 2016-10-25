package com.rsp.gankjoke.di.component;

import com.rsp.gankjoke.di.annotation.MainActivityScope;
import com.rsp.gankjoke.ui.MainActivity;
import com.rsp.gankjoke.ui.gank.activity.MeiziBigActiviy;

import dagger.Component;

/**
 * @author 小任
 * @date 2016/10/18
 * version 1.0
 * 描述:
 */
@MainActivityScope
@Component(dependencies = AppComponent.class)
public interface MainComponent {
   void inject(MainActivity mainActivity);

   void inject(MeiziBigActiviy meiziBigActiviy);
}
