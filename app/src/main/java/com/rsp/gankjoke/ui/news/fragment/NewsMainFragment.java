package com.rsp.gankjoke.ui.news.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rsp.gankjoke.R;
import com.rsp.gankjoke.adapter.PageAdapter;
import com.rsp.gankjoke.presenent.NewsMainFragmentPresenent;
import com.rsp.gankjoke.presenent.contract.NewsMainFragmentContract;
import com.rsp.gankjoke.ui.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author 小任
 * @date 2016/10/20
 * version 1.0
 * 描述:
 */

public class NewsMainFragment extends BaseFragment<NewsMainFragmentPresenent> implements NewsMainFragmentContract.View {
    @BindView(R.id.tab_gank_main)
    TabLayout tabGankMain;
    @BindView(R.id.vp_gank_main)
    ViewPager vpGankMain;
    private String[] titles = new String[]{"头条","社会","国内","国际","娱乐","体育","军事","科技","财经","时尚"};
    private PageAdapter gankPageAdapter;
    private List<BaseFragment> fragments;

    private NewsListFragment topFragment;
    private NewsListFragment shehuiFragment;
    private NewsListFragment guoneiFragment;
    private NewsListFragment guojiFragment;
    private NewsListFragment yuleFragment;
    private NewsListFragment tiyuFragment;
    private NewsListFragment junshiFragment;
    private NewsListFragment kejiFragment;
    private NewsListFragment caijinFragment;
    private NewsListFragment shishangFragment;
    @Override
    protected void inject() {
        getComponent().inject(this);
    }
    @Override
    public String getTitle() {
        return "新闻";
    }
    @Override
    protected void initEventAndData() {

        initFragment();
        gankPageAdapter = new PageAdapter(getChildFragmentManager(), fragments);
        vpGankMain.setAdapter(gankPageAdapter);
        for (int i = 0; i < titles.length; i++) {
            tabGankMain.addTab(tabGankMain.newTab().setText(titles[i]));
        }
        tabGankMain.setupWithViewPager(vpGankMain);
        for (int i = 0; i < titles.length; i++) {
            tabGankMain.getTabAt(i).setText(titles[i]);

        }
    }
    private void initFragment() {
        topFragment = new NewsListFragment();
        topFragment.setArguments(getBundle(NewsListFragment.TOP));

        shehuiFragment = new NewsListFragment();
        shehuiFragment.setArguments(getBundle(NewsListFragment.SHEHUI));

        guoneiFragment = new NewsListFragment();
        guoneiFragment.setArguments(getBundle(NewsListFragment.GUONEI));

        guojiFragment = new NewsListFragment();
        guojiFragment.setArguments(getBundle(NewsListFragment.GUOJI));

        yuleFragment = new NewsListFragment();
        yuleFragment.setArguments(getBundle(NewsListFragment.YULE));

        tiyuFragment = new NewsListFragment();
        tiyuFragment.setArguments(getBundle(NewsListFragment.TIYU));

        junshiFragment = new NewsListFragment();
        junshiFragment.setArguments(getBundle(NewsListFragment.JUNSHI));

        kejiFragment = new NewsListFragment();
        kejiFragment.setArguments(getBundle(NewsListFragment.KEJI));

        caijinFragment = new NewsListFragment();
        caijinFragment.setArguments(getBundle(NewsListFragment.CAIJING));

        shishangFragment = new NewsListFragment();
        shishangFragment.setArguments(getBundle(NewsListFragment.SHISHANG));

        fragments = new ArrayList<>();
        fragments.add(topFragment);
        fragments.add(shehuiFragment);
        fragments.add(guoneiFragment);
        fragments.add(guojiFragment);
        fragments.add(yuleFragment);
        fragments.add(tiyuFragment);
        fragments.add(junshiFragment);
        fragments.add(kejiFragment);
        fragments.add(caijinFragment);
        fragments.add(shishangFragment);

    }

    private Bundle getBundle(String type) {
        Bundle bundle = new Bundle();
        bundle.putString(NewsListFragment.TYPE, type);
        return bundle;
    }
    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = View.inflate(activity, R.layout.fragment_news_main, null);
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

}
