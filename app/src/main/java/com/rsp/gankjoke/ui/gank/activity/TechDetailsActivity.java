package com.rsp.gankjoke.ui.gank.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.mingle.widget.LoadingView;
import com.rsp.gankjoke.R;
import com.rsp.gankjoke.entity.gank.GankItem;
import com.rsp.gankjoke.ui.base.BaseControlActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author 小任
 * @date 2016/10/19
 * version 1.0
 * 描述:
 */

public class TechDetailsActivity extends BaseControlActivity {
    public static final String GANKITEM = "gank";
    @BindView(R.id.tool_bar)
    Toolbar toolBar;
    @BindView(R.id.wv_tech_content)
    WebView wvTechContent;
    @BindView(R.id.loadView)
    LoadingView loadView;
    private GankItem gankItem;

    @Override
    protected int getLayouId() {
        return R.layout.activity_tech_detail;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        gankItem = getIntent().getParcelableExtra(GANKITEM);
        setToolBar(toolBar, gankItem.desc);
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
                if (newProgress ==100) {
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
        wvTechContent.loadUrl(gankItem.url);
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && wvTechContent.canGoBack()) {
            wvTechContent.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        clearWebViewResource();
    }

    //防止webview内存泄露
    private void clearWebViewResource() {
        if (wvTechContent != null) {
            wvTechContent.removeAllViews();
            ((ViewGroup) wvTechContent.getParent()).removeView(wvTechContent);
            wvTechContent.setTag(null);
            wvTechContent.clearHistory();
            wvTechContent.destroy();
            wvTechContent = null;
        }
    }
}
