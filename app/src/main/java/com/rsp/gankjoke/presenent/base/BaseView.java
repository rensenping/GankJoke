package com.rsp.gankjoke.presenent.base;

/**
 * @author 小任
 * @date 2016/10/18
 * version 1.0
 * 描述:
 */

public interface BaseView {
    void startLoading();

    void endLoading();

    void loadingError(String msg);
}
