package com.rsp.gankjoke.ui.joke.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mingle.widget.LoadingView;
import com.rsp.gankjoke.R;
import com.rsp.gankjoke.adapter.JokeAdapter;
import com.rsp.gankjoke.entity.joke.JokeItem;
import com.rsp.gankjoke.presenent.JokeFragmentPresenent;
import com.rsp.gankjoke.presenent.contract.JokeFragmentContract;
import com.rsp.gankjoke.ui.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author 小任
 * @date 2016/10/21
 * version 1.0
 * 描述:
 */

public class JokeMainFragment extends BaseFragment<JokeFragmentPresenent> implements JokeFragmentContract.View {

    @BindView(R.id.rcy)
    RecyclerView rcy;
    @BindView(R.id.srl)
    SwipeRefreshLayout srl;
    @BindView(R.id.loadView)
    LoadingView loadView;
    private List<JokeItem> mData;
    private JokeAdapter jokeAdapter;
    private boolean isLoading = false;

    @Override
    public void showJoke(List<JokeItem> jokeItems) {
        mData.clear();
        mData.addAll(jokeItems);
        jokeAdapter.notifyDataSetChanged();
    }

    @Override
    public void showMoreJoke(List<JokeItem> jokeItems) {
        mData.addAll(jokeItems);
        jokeAdapter.notifyDataSetChanged();
    }

    @Override
    protected void inject() {
        getComponent().inject(this);
    }

    @Override
    protected void initEventAndData() {
        mData = new ArrayList<>();
        jokeAdapter = new JokeAdapter(activity, mData);
        srl.setColorSchemeColors(Color.GREEN, Color.RED, Color.BLUE, Color.YELLOW);
        srl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenent.loadJoke();
            }
        });
        rcy.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false));
        rcy.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastVisibleItemPosition = ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastVisibleItemPosition();
                if (recyclerView.getLayoutManager().getItemCount() - 2 == lastVisibleItemPosition && !isLoading) {
                    mPresenent.loadMoreJoke();
                }
            }
        });
        rcy.setAdapter(jokeAdapter);
        if (isFirst) {
            mPresenent.loadJoke();
        }

    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = View.inflate(activity, R.layout.fragment_joke_main, null);
        return rootView;
    }
    @Override
    public String getTitle() {
        return "笑话";
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
        isLoading = false;
        if (loadView.getVisibility() != View.GONE) {
            loadView.setVisibility(View.GONE);
        }
        if (srl.isRefreshing()) {
            srl.setRefreshing(false);
        }
    }


}
