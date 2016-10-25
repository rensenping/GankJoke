package com.rsp.gankjoke.presenent.base;

import rx.Completable;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * @author 小任
 * @date 2016/10/18
 * version 1.0
 * 描述: 基于RXJava的MVP封装
 */

public class BaseRxPresenent<T extends BaseView> implements BasePresenent<T> {
    protected T mView;
    protected CompositeSubscription mCompositeSubscription;
    protected void unSubscribe() {
        if (mCompositeSubscription != null) {
            mCompositeSubscription.unsubscribe();
        }
    }

    protected void addSubscrebe(Subscription subscription) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(subscription);
    }

    @Override
    public void attachView(T mView) {
        this.mView = mView;
    }

    @Override
    public void detachView() {
        mView = null;
        unSubscribe();
    }
}
