package com.rsp.gankjoke.di.module;

import android.app.Activity;
import android.support.v4.app.Fragment;

import com.rsp.gankjoke.di.annotation.FragmentScope;

import dagger.Module;
import dagger.Provides;

/**
 * @author 小任
 * @date 2016/10/19
 * version 1.0
 * 描述:
 */
@Module
public class FragmentModule {
    private Fragment fragment;

    public FragmentModule(Fragment fragment) {
        this.fragment = fragment;
    }

    @FragmentScope
    @Provides
    public Activity providesActivity() {
        return fragment.getActivity();
    }
}
