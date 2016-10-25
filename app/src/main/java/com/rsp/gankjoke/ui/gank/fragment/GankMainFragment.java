package com.rsp.gankjoke.ui.gank.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rsp.gankjoke.R;
import com.rsp.gankjoke.adapter.PageAdapter;
import com.rsp.gankjoke.presenent.GankMainFragmentPresenent;
import com.rsp.gankjoke.ui.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author 小任
 * @date 2016/10/18
 * version 1.0
 * 描述:
 */

public class GankMainFragment extends BaseFragment<GankMainFragmentPresenent> {


    @BindView(R.id.tab_gank_main)
    TabLayout tabGankMain;
    @BindView(R.id.vp_gank_main)
    ViewPager vpGankMain;
    private String[] titles = new String[]{"Android","iOS","福利"};
    private PageAdapter gankPageAdapter;
    private List<BaseFragment> fragments;
    private TechFragment androidFragment;
    private TechFragment iOSFragment;
    private GirlFragment girlFragment;

    @Override
    protected void inject() {
        getComponent().inject(this);
    }

    @Override
    protected void initEventAndData() {
        fragments = new ArrayList<>();

        Bundle bundle = new Bundle();
        bundle.putString(TechFragment.TECH, TechFragment.TECH_ANDROID);
        androidFragment = new TechFragment();
        androidFragment.setArguments(bundle);

        iOSFragment = new TechFragment();
        bundle = new Bundle();
        bundle.putString(TechFragment.TECH, TechFragment.TECH_IOS);
        iOSFragment.setArguments(bundle);

        girlFragment = new GirlFragment();

        fragments.add(androidFragment);
        fragments.add(iOSFragment);
        fragments.add(girlFragment);

        gankPageAdapter = new PageAdapter(getChildFragmentManager(), fragments);
        vpGankMain.setAdapter(gankPageAdapter);

        tabGankMain.addTab(tabGankMain.newTab().setText(titles[0]));
        tabGankMain.addTab(tabGankMain.newTab().setText(titles[1]));
        tabGankMain.addTab(tabGankMain.newTab().setText(titles[2]));
        tabGankMain.setupWithViewPager(vpGankMain);
        tabGankMain.getTabAt(0).setText(titles[0]);
        tabGankMain.getTabAt(1).setText(titles[1]);
        tabGankMain.getTabAt(2).setText(titles[2]);
    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = View.inflate(activity, R.layout.fragment_gank_main, null);
        return rootView;
    }

    @Override
    public void startLoading() {

    }

    @Override
    public void endLoading() {

    }

    @Override
    public void loadingError(String msg) {

    }

    @Override
    public String getTitle() {
        return "干货";
    }
}
