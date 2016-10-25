package com.rsp.gankjoke.presenent;

import com.rsp.gankjoke.api.GankApi;
import com.rsp.gankjoke.api.GankResultFunc;
import com.rsp.gankjoke.entity.gank.GankItem;
import com.rsp.gankjoke.presenent.base.BaseRxPresenent;
import com.rsp.gankjoke.presenent.contract.MainPresenentContract;

import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.schedulers.Schedulers;

/**
 * @author 小任
 * @date 2016/10/18
 * version 1.0
 * 描述:
 */

public class MainPresenent extends BaseRxPresenent<MainPresenentContract.View> implements MainPresenentContract.Presenent{
    @Inject
    GankApi gankApi;
    @Inject
    public MainPresenent() {

    }

    @Override
    public void loadingMeizi(int page, int pageSize) {
        Subscription subscribe = gankApi.getMeizi(page, pageSize)
                .map(new GankResultFunc<List<GankItem>>())
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        mView.startLoading();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<GankItem>>() {
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
                    public void onNext(List<GankItem> gankItems) {
                        mView.startLoadingMeizi(gankItems);
                    }
                });
        addSubscrebe(subscribe);
    }
}
