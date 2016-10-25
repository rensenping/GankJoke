package com.rsp.gankjoke.api;

import com.rsp.gankjoke.api.exception.GankException;
import com.rsp.gankjoke.entity.gank.GankBean;

import rx.functions.Func1;

/**
 * @author 小任
 * @date 2016/10/19
 * version 1.0
 * 描述:
 */

public class GankResultFunc<T> implements Func1<GankBean<T>,T> {
    @Override
    public T call(GankBean<T> tGankBean) {
        if (tGankBean.isError()) {
            throw new GankException("接口出错");
        }
        return tGankBean.getResults();
    }
}
