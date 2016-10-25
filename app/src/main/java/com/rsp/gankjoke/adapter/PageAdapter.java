package com.rsp.gankjoke.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.rsp.gankjoke.ui.base.BaseFragment;

import java.util.List;

/**
 * @author 小任
 * @date 2016/10/19
 * version 1.0
 * 描述:
 */

public class PageAdapter extends FragmentPagerAdapter {
    private List<BaseFragment> fragments;
    public PageAdapter(FragmentManager fm, List<BaseFragment> fragments) {
        super(fm);
       this.fragments = fragments;
    }
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
