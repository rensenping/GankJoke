package com.rsp.gankjoke.entity.gank;

import java.util.List;

/**
 * @author 小任
 * @date 2016/10/18
 * version 1.0
 * 描述:
 */

public class GankBean<T> {
    private boolean error;

    public T getResults() {
        return results;
    }

    public void setResults(T results) {
        this.results = results;
    }

    private T results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }


}
