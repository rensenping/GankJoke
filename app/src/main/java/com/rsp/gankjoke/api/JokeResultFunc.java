package com.rsp.gankjoke.api;

import com.rsp.gankjoke.api.exception.JokeException;
import com.rsp.gankjoke.entity.joke.JokeBean;
import com.rsp.gankjoke.entity.joke.JokeItem;

import java.util.List;

import rx.functions.Func1;

/**
 * @author 小任
 * @date 2016/10/21
 * version 1.0
 * 描述:
 */

public class JokeResultFunc<T> implements Func1<JokeBean<T>,T> {
    @Override
    public T call(JokeBean<T> listJokeBean) {
        if (listJokeBean.error_code == 0) {
            return listJokeBean.result;
        }
        throw new JokeException("接口返回错误");
    }
}
