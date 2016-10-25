package com.rsp.gankjoke.ui.gank.fragment;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mingle.widget.LoadingView;
import com.rsp.gankjoke.R;
import com.rsp.gankjoke.adapter.OnItemClickListener;
import com.rsp.gankjoke.adapter.TechAdapter;
import com.rsp.gankjoke.entity.gank.GankItem;
import com.rsp.gankjoke.presenent.TechFragmentPresenent;
import com.rsp.gankjoke.presenent.contract.TechFragmentContract;
import com.rsp.gankjoke.ui.base.BaseFragment;
import com.rsp.gankjoke.ui.gank.activity.TechDetailsActivity;
import com.rsp.gankjoke.viewholder.ItemTechHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author 小任
 * @date 2016/10/19
 * version 1.0
 * 描述:
 */

public class TechFragment extends BaseFragment<TechFragmentPresenent> implements TechFragmentContract.View, OnItemClickListener {
    @BindView(R.id.rv_tech_content)
    RecyclerView rvTechContent;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    @BindView(R.id.loadView)
    LoadingView loadView;
    private List<GankItem> mData;
    private TechAdapter techAdapter;
    private LinearLayoutManager layoutManager;
    public static final String TECH_ANDROID = "Android";
    public static final String TECH_IOS = "iOS";
    public static final String TECH = "tech";
    private String tech = "Android";
    private boolean isLoadingMore = false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        tech = arguments.getString(TECH);
    }

    @Override
    public void showContent(List<GankItem> data) {
        mData.clear();
        mData.addAll(data);
        techAdapter.notifyDataSetChanged();

    }

    @Override
    public void showMoreContent(List<GankItem> data) {
        mData.addAll(data);
        techAdapter.notifyDataSetChanged();
    }

    @Override
    protected void inject() {
        getComponent().inject(this);
    }

    @Override
    protected void initEventAndData() {
        mData = new ArrayList<>();
        techAdapter = new TechAdapter(activity, mData);
        layoutManager = new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false);
        rvTechContent.setLayoutManager(layoutManager);
        rvTechContent.setAdapter(techAdapter);
        techAdapter.setOnItemClickListener(this);
        rvTechContent.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastVisibleItemPosition = ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastVisibleItemPosition();
                int itemCount = recyclerView.getLayoutManager().getItemCount();
                if (lastVisibleItemPosition >= itemCount - 2 && dy > 0 && !isLoadingMore) {  //还剩2个Item时加载更多

                    mPresenent.loadingMore(tech);
                }
            }
        });
        rvTechContent.setItemAnimator(new DefaultItemAnimator());
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenent.loadingFirst(tech);
            }
        });
        if (isFirst) {
            mPresenent.loadingFirst(tech);
        }
    }


    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = View.inflate(activity, R.layout.fragment_tech, null);
        return rootView;
    }

    @Override
    public void startLoading() {
        isLoadingMore = true;
    }



    @Override
    public void endLoading() {
        super.endLoading();
        if (loadView.getVisibility() != View.GONE) {
            loadView.setVisibility(View.GONE);
        }
        isLoadingMore = false;
        if (swipeRefresh.isRefreshing()) {
            swipeRefresh.setRefreshing(false);
        }
    }
    @Override
    public void loadingError(String msg) {
        //loadingView.stop();
        if (loadView.getVisibility() != View.GONE) {
            loadView.setVisibility(View.GONE);
        }
    }

    @Override
    public void onItemClick(int position, RecyclerView.ViewHolder holder) {
        ItemTechHolder itemTechHolder = (ItemTechHolder) holder;
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(activity, itemTechHolder.getView(), "shareView");
        Intent intent = new Intent(activity, TechDetailsActivity.class);
        intent.putExtra(TechDetailsActivity.GANKITEM, mData.get(position));
        startActivity(intent, options.toBundle());
    }

}
