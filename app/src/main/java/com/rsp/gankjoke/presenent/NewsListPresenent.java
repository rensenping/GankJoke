package com.rsp.gankjoke.presenent;

import com.rsp.gankjoke.api.NewsApi;
import com.rsp.gankjoke.api.NewsResultFunc;
import com.rsp.gankjoke.entity.news.NewsBean;
import com.rsp.gankjoke.entity.news.NewsItem;
import com.rsp.gankjoke.entity.news.NewsResult;
import com.rsp.gankjoke.presenent.base.BaseRxPresenent;
import com.rsp.gankjoke.presenent.contract.NewsListContract;

import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * @author 小任
 * @date 2016/10/20
 * version 1.0
 * 描述:
 */

public class NewsListPresenent extends BaseRxPresenent<NewsListContract.View> implements  NewsListContract.Presenent{
    @Inject
    NewsApi newsApi;
    @Inject
    public NewsListPresenent() {

    }
    @Override
    public void loadingNews(String type) {
        Subscription subscribe = getNewsData(type);
        addSubscrebe(subscribe);
    }

    private Subscription getNewsData(String type) {
        return newsApi.getNews(type,"bcc6753c2cf50bf64635467cdb3f2e2a")
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        mView.startLoading();
                    }
                })
                .map(new NewsResultFunc<List<NewsItem>>())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<NewsItem>>() {
                    @Override
                    public void onCompleted() {
                        mView.endLoading();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.loadingError(e.getMessage());
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(List<NewsItem> newsItems) {
                        mView.showNews(newsItems);
                    }
                });
    }

}
