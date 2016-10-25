package com.rsp.gankjoke.presenent;

import com.rsp.gankjoke.api.GankApi;
import com.rsp.gankjoke.api.GankResultFunc;
import com.rsp.gankjoke.entity.gank.GankItem;
import com.rsp.gankjoke.presenent.base.BaseRxPresenent;
import com.rsp.gankjoke.presenent.contract.MeiziFragmentContract;

import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.schedulers.Schedulers;

/**
 * @author 小任
 * @date 2016/10/19
 * version 1.0
 * 描述:
 */

public class MeiziFragmentPresenent extends BaseRxPresenent<MeiziFragmentContract.View> implements  MeiziFragmentContract.Presenent{
    @Inject
    public GankApi gankApi;
    private int page = 1;
    @Inject
    public MeiziFragmentPresenent() {

    }
    @Override
    public void loadMeizi() {
        page = 1;
        Subscription subscribe = getGankData();
        addSubscrebe(subscribe);
    }

    private Subscription getGankData() {
        return gankApi.getMeizi(page, 20)
                    .subscribeOn(Schedulers.io())
                    .doOnSubscribe(new Action0() {
                        @Override
                        public void call() {
                            mView.startLoading();
                        }
                    })
                    .map(new GankResultFunc<List<GankItem>>())
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
                            if (page == 1) {
                                mView.showMeizi(gankItems);
                            } else {
                                mView.showMoreMeizi(gankItems);
                            }
                        }
                    });
    }

    @Override
    public void loadMoreMeizi() {
        page++;
        Subscription subscribe = getGankData();
        addSubscrebe(subscribe);
    }
}
