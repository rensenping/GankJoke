package com.rsp.gankjoke.presenent;

import com.rsp.gankjoke.api.JokeApi;
import com.rsp.gankjoke.api.JokeResultFunc;
import com.rsp.gankjoke.entity.joke.JokeItem;
import com.rsp.gankjoke.presenent.base.BaseRxPresenent;
import com.rsp.gankjoke.presenent.contract.JokeFragmentContract;

import java.util.List;

import javax.inject.Inject;

import rx.Scheduler;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.schedulers.Schedulers;

/**
 * @author 小任
 * @date 2016/10/21
 * version 1.0
 * 描述:
 */

public class JokeFragmentPresenent extends BaseRxPresenent<JokeFragmentContract.View> implements JokeFragmentContract.Presenent {
    @Inject
    JokeApi jokeApi;
    private int page = 1;
    @Inject
    public JokeFragmentPresenent() {

    }

    @Override
    public void loadJoke() {
        page = 1;
        Subscription subscribe = getJokeData();
        addSubscrebe(subscribe);
    }

    private Subscription getJokeData() {
        return jokeApi.getJoke("686e9d41439afa3264fa1f3909b877b3")
                .map(new JokeResultFunc<List<JokeItem>>())
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        mView.startLoading();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<JokeItem>>() {
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
                    public void onNext(List<JokeItem> jokeItems) {
                        if (page == 1) {
                            mView.showJoke(jokeItems);
                        } else {
                            mView.showMoreJoke(jokeItems);
                        }
                    }
                });
    }

    @Override
    public void loadMoreJoke() {
        page++;
        Subscription subscribe = getJokeData();
        addSubscrebe(subscribe);
    }
}
