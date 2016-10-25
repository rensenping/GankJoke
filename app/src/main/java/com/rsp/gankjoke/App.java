package com.rsp.gankjoke;

import android.app.Activity;
import android.app.Application;

import com.rsp.gankjoke.di.component.AppComponent;
import com.rsp.gankjoke.di.component.DaggerAppComponent;
import com.rsp.gankjoke.di.module.AppModule;
import com.squareup.leakcanary.LeakCanary;
import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;

import javax.inject.Inject;

/**
 * @author 小任
 * @date 2016/10/18
 * version 1.0
 * 描述:
 */

public class App extends Application {

    private static AppComponent appComponent;
    @Inject
    public ArrayList<Activity> activityList;

    @Override
    public void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        MobclickAgent.setScenarioType(this, MobclickAgent.EScenarioType.E_UM_NORMAL);
        LeakCanary.install(this);
        appComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
        appComponent.inject(this);

    }

    public static AppComponent getAppComponent() {
        return appComponent;
    }

    public void addActivity(Activity activity) {
        activityList.add(activity);
    }

    public void removeActivity(Activity activity) {
        activityList.remove(activity);
    }

    public void exitApp() {

    }
}
