package com.rsp.gankjoke.ui.base;

import android.app.Application;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.rsp.gankjoke.App;
import com.umeng.analytics.MobclickAgent;

import butterknife.ButterKnife;

/**
 * @author 小任
 * @date 2016/10/18
 * version 1.0
 * 描述: 顶层activity
 */

public abstract class BaseControlActivity extends AppCompatActivity {
    protected App app;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        app = (App) getApplication();
        app.addActivity(this);
        setContentView(getLayouId());

    }
    protected void setToolBar(Toolbar toolbar, String title) {
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishAfterTransition();
            }
        });
    }
    protected abstract int getLayouId();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        app.removeActivity(this);
    }
    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }
    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
}
