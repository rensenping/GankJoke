package com.rsp.gankjoke.presenent.contract;

import com.rsp.gankjoke.entity.joke.JokeItem;
import com.rsp.gankjoke.presenent.base.BasePresenent;
import com.rsp.gankjoke.presenent.base.BaseView;

import java.util.List;

/**
 * @author 小任
 * @date 2016/10/21
 * version 1.0
 * 描述:
 */

public interface JokeFragmentContract {
    interface View extends BaseView {
        void showJoke(List<JokeItem> jokeItems);

        void showMoreJoke(List<JokeItem> jokeItems);
    }

    interface Presenent extends BasePresenent<View> {
        void loadJoke();

        void loadMoreJoke();
    }
}
