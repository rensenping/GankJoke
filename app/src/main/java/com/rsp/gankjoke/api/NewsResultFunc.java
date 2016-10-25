package com.rsp.gankjoke.api;

import com.rsp.gankjoke.api.exception.NewsException;
import com.rsp.gankjoke.entity.news.NewsBean;

import rx.functions.Func1;

/**
 * @author 小任
 * @date 2016/10/20
 * version 1.0
 * 描述:
 */

public class NewsResultFunc<T> implements Func1<NewsBean<T>, T> {

    @Override
    public T call(NewsBean<T> newsBean) {
        if (newsBean.error_code == 0) {
            return newsBean.result.data;
        }
        throw new NewsException("后台接口出错");
    }
}
