package com.rsp.gankjoke.presenent.base;

/**
 * @author 小任
 * @date 2016/10/18
 * version 1.0
 * 描述: MVP顶层接口
 */

public interface BasePresenent<T extends BaseView> {

    void attachView(T mView);

    void detachView();

}
