package com.rsp.gankjoke.ui.news.fragment;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mingle.widget.LoadingView;
import com.rsp.gankjoke.R;
import com.rsp.gankjoke.adapter.NewsAdapter;
import com.rsp.gankjoke.adapter.OnItemClickListener;
import com.rsp.gankjoke.entity.news.NewsItem;
import com.rsp.gankjoke.presenent.NewsListPresenent;
import com.rsp.gankjoke.presenent.contract.NewsListContract;
import com.rsp.gankjoke.ui.base.BaseFragment;
import com.rsp.gankjoke.ui.news.activity.NewsDetailsActivity;
import com.rsp.gankjoke.viewholder.ItemNewsHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author 小任
 * @date 2016/10/20
 * version 1.0
 * 描述:
 */

public class NewsListFragment extends BaseFragment<NewsListPresenent> implements NewsListContract.View, OnItemClickListener {
    public static final String TYPE = "type";

    public static final String TOP = "top";
    public static final String SHEHUI = "shehui";
    public static final String GUONEI = "guonei";
    public static final String GUOJI = "guoji";
    public static final String YULE = "yule";
    public static final String TIYU = "tiyu";
    public static final String JUNSHI = "junshi";
    public static final String KEJI = "keji";
    public static final String CAIJING = "caijing";
    public static final String SHISHANG = "shishang";
    @BindView(R.id.rv_tech_content)
    RecyclerView rvTechContent;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    @BindView(R.id.loadView)
    LoadingView loadView;
    private List<NewsItem> mData;
    private NewsAdapter newsAdapter;
    private String type = "top";//新闻类型
    private boolean isLoading = false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        type = arguments.getString(TYPE);
    }

    @Override
    public void showNews(List<NewsItem> newsItems) {
        mData.clear();
        mData.addAll(newsItems);
        newsAdapter.notifyDataSetChanged();
    }


    @Override
    protected void inject() {
        getComponent().inject(this);
    }

    @Override
    protected void initEventAndData() {
        mData = new ArrayList<>();
        newsAdapter = new NewsAdapter(activity, mData);
        newsAdapter.setOnItemClickListener(this);
        rvTechContent.setLayoutManager(new LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false));
        rvTechContent.setAdapter(newsAdapter);

        swipeRefresh.setColorSchemeColors(Color.BLUE,Color.RED,Color.GREEN,Color.YELLOW);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenent.loadingNews(type);
            }
        });
        if (isFirst) {
            mPresenent.loadingNews(type);
        }
    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = View.inflate(activity, R.layout.fragment_news, null);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void startLoading() {
        isLoading = true;
    }

    @Override
    public void loadingError(String msg) {
        isLoading = false;
        if (loadView.getVisibility() != View.GONE) {
            loadView.setVisibility(View.GONE);
        }
    }

    @Override
    public void endLoading() {
        super.endLoading();
        if (loadView.getVisibility() != View.GONE) {
            loadView.setVisibility(View.GONE);
        }
        isLoading = false;
        if (swipeRefresh.isRefreshing()) {
            swipeRefresh.setRefreshing(false);
        }
    }

    @Override
    public void onItemClick(int position, RecyclerView.ViewHolder holder) {
        Intent intent = new Intent(activity, NewsDetailsActivity.class);
        intent.putExtra(NewsDetailsActivity.NEWS_ITEM, mData.get(position));
        ItemNewsHolder itemNewsHolder = (ItemNewsHolder) holder;
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(activity, itemNewsHolder.getNewsImg(), "shareImg");
        startActivity(intent,options.toBundle());
    }
}
