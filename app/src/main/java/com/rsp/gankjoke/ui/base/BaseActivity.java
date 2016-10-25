package com.rsp.gankjoke.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.rsp.gankjoke.presenent.base.BaseRxPresenent;
import com.rsp.gankjoke.presenent.base.BaseView;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * @author 小任
 * @date 2016/10/18
 * version 1.0
 * 描述: 基于MVP+Dagger2的activity基类
 */

public abstract class BaseActivity<T extends BaseRxPresenent> extends BaseControlActivity implements BaseView {
    @Inject
    protected T mPresenent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inject();
        if (mPresenent != null) {
            mPresenent.attachView(this);
        }
        ButterKnife.bind(this);
        initEvent();
    }

    protected abstract void initEvent();
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenent != null) {
            mPresenent.detachView();
        }
    }

    protected abstract void inject();
}
