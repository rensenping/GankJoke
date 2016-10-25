package com.rsp.gankjoke.ui.gank.fragment;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.mingle.widget.LoadingView;
import com.rsp.gankjoke.R;
import com.rsp.gankjoke.adapter.MeiziAdapter;
import com.rsp.gankjoke.adapter.OnItemClickListener;
import com.rsp.gankjoke.entity.gank.GankItem;
import com.rsp.gankjoke.presenent.MeiziFragmentPresenent;
import com.rsp.gankjoke.presenent.contract.MeiziFragmentContract;
import com.rsp.gankjoke.ui.base.BaseFragment;
import com.rsp.gankjoke.ui.gank.activity.MeiziBigActiviy;
import com.rsp.gankjoke.viewholder.ItemGirlHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author 小任
 * @date 2016/10/19
 * version 1.0
 * 描述:
 */

public class GirlFragment extends BaseFragment<MeiziFragmentPresenent> implements MeiziFragmentContract.View, OnItemClickListener {
    @BindView(R.id.rcy)
    RecyclerView rcy;
    @BindView(R.id.srf)
    SwipeRefreshLayout srf;
    @BindView(R.id.loadView)
    LoadingView loadView;
    private List<GankItem> mData;
    private MeiziAdapter meiziAdapter;
    private boolean isLoading = false;

    @Override
    public void showMeizi(List<GankItem> gankItems) {
        mData.clear();
        mData.addAll(gankItems);
        meiziAdapter.notifyDataSetChanged();

    }

    @Override
    public void showMoreMeizi(List<GankItem> gankItems) {
        mData.addAll(gankItems);
        meiziAdapter.notifyDataSetChanged();
    }

    @Override
    protected void inject() {
        getComponent().inject(this);
    }

    @Override
    protected void initEventAndData() {
        mData = new ArrayList<>();
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        rcy.setLayoutManager(layoutManager);
        meiziAdapter = new MeiziAdapter(activity, mData);
        meiziAdapter.setOnItemClickListener(this);
        rcy.setAdapter(meiziAdapter);
        rcy.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                boolean bottom = ((StaggeredGridLayoutManager) recyclerView.getLayoutManager())
                        .findLastVisibleItemPositions(new int[2])[1] >= recyclerView.getLayoutManager().getItemCount() - 5;
                if (bottom && !isLoading && dy > 0) {
                    isLoading = true;
                    mPresenent.loadMoreMeizi();
                }
            }
        });
        srf.setColorSchemeColors(Color.BLUE, Color.GREEN, Color.YELLOW, Color.RED);
        srf.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                isLoading = true;
                mPresenent.loadMeizi();
            }
        });
        if (isFirst) {
            mPresenent.loadMeizi();
        }
    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = View.inflate(activity, R.layout.fragment_girl, null);
        return rootView;
    }

    @Override
    public void startLoading() {

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
        isLoading = false;
        if (loadView.getVisibility() != View.GONE) {
            loadView.setVisibility(View.GONE);
        }
        if (srf.isRefreshing()) {
            srf.setRefreshing(false);
        }
    }

    @Override
    public void onItemClick(int position, RecyclerView.ViewHolder holder) {
        GankItem gankItem = mData.get(position);
        Intent intent = new Intent(activity, MeiziBigActiviy.class);
        intent.putExtra(MeiziBigActiviy.IMG_PATH, gankItem.url);
        ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation(activity, ((ItemGirlHolder) holder).getMeizi(), "img");
        startActivity(intent, activityOptions.toBundle());
        //startActivity(intent);
    }
}
