package com.rsp.gankjoke.ui.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rsp.gankjoke.App;
import com.rsp.gankjoke.di.component.DaggerFragmentComponent;
import com.rsp.gankjoke.di.component.FragmentComponent;
import com.rsp.gankjoke.di.module.FragmentModule;
import com.rsp.gankjoke.presenent.base.BaseRxPresenent;
import com.rsp.gankjoke.presenent.base.BaseView;
import com.umeng.analytics.MobclickAgent;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * @author 小任
 * @date 2016/10/18
 * version 1.0
 * 描述:
 */

public abstract class BaseFragment<T extends BaseRxPresenent> extends Fragment implements BaseView {
    @Inject
    public Activity activity;
    /**
     * Fragment当前状态是否可见
     */
    protected boolean isVisible;
    /**
     * 是否已被加载过一次，第二次就不再去请求数据了
     */
    protected boolean isFirst = true;
    public View rootView;
    @Inject
    public T mPresenent;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inject();
        if (mPresenent != null) {
            mPresenent.attachView(this);
        }
    }

    protected abstract void inject();

    protected FragmentComponent getComponent() {
        return DaggerFragmentComponent.builder().appComponent(App.getAppComponent()).fragmentModule(new FragmentModule(this)).build();
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenent != null) {
            mPresenent.detachView();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = initView(inflater, container, savedInstanceState);
            ButterKnife.bind(this, rootView);
            initEventAndData();
        }
        return rootView;
    }


    protected abstract void initEventAndData();

    protected abstract View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (rootView != null) {
            ViewGroup parent = (ViewGroup) rootView.getParent();
            if (parent != null) {
                parent.removeView(rootView);
            }
        }
    }

    /**
     * 当前fragment不可见
     */
    protected void onInvisible() {

    }

    /**
     * 当前fragment可见
     */
    protected void onVisible() {

    }

    @Override
    public void endLoading() {
        isFirst = false;
    }

   public String getTitle() {
       return "title";
    }
    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart(this.getClass().getSimpleName()); //统计页面，"MainScreen"为页面名称，可自定义
    }
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd(this.getClass().getSimpleName());
    }
}
