package com.rsp.gankjoke.ui.news.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.transition.Transition;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.mingle.widget.LoadingView;
import com.rsp.gankjoke.R;
import com.rsp.gankjoke.entity.news.NewsItem;
import com.rsp.gankjoke.ui.base.BaseControlActivity;
import com.rsp.gankjoke.util.ShareUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author 小任
 * @date 2016/10/20
 * version 1.0
 * 描述:
 */

public class NewsDetailsActivity extends BaseControlActivity {
    public static final String NEWS_ITEM = "item";

    @BindView(R.id.tool_bar)
    Toolbar toolBar;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.wv_detail_content)
    WebView wvTechContent;
    @BindView(R.id.nsv_scroller)
    NestedScrollView nsvScroller;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.headerImg)
    ImageView headerImg;
    @BindView(R.id.ctl)
    CollapsingToolbarLayout ctl;
    @BindView(R.id.loadView)
    LoadingView loadView;
    private NewsItem item;

    @Override
    protected int getLayouId() {
        return R.layout.activity_news_details;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        init();
    }

    @OnClick(R.id.fab)
    public void share(View view) {
        ShareUtil.shareText(this,item.url,"新闻分享");
    }

    private void init() {
        item = getIntent().getParcelableExtra(NEWS_ITEM);
        setToolBar(toolBar, item.title);
        ctl.setExpandedTitleColor(Color.WHITE);
        ctl.setCollapsedTitleTextColor(Color.WHITE);
        WebSettings settings = wvTechContent.getSettings();
        settings.setAppCacheEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setDatabaseEnabled(true);
        settings.setJavaScriptEnabled(true);
        settings.setLoadWithOverviewMode(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setSupportZoom(true);
        wvTechContent.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        wvTechContent.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if (newProgress == 100) {
                    if (loadView.getVisibility() != View.GONE) {
                        loadView.setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                setTitle(title);
            }
        });
        wvTechContent.loadUrl(item.url);
        getWindow().getSharedElementEnterTransition().addListener(new Transition.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {

            }

            @Override
            public void onTransitionEnd(Transition transition) {
                Glide.with(NewsDetailsActivity.this).load(item.thumbnail_pic_s).into(headerImg);
            }

            @Override
            public void onTransitionCancel(Transition transition) {

            }

            @Override
            public void onTransitionPause(Transition transition) {

            }

            @Override
            public void onTransitionResume(Transition transition) {

            }
        });
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && wvTechContent.canGoBack()) {
            wvTechContent.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
