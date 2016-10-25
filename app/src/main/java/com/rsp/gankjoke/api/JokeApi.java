package com.rsp.gankjoke.api;

import com.rsp.gankjoke.entity.joke.JokeBean;
import com.rsp.gankjoke.entity.joke.JokeItem;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * @author 小任
 * @date 2016/10/21
 * version 1.0
 * 描述:
 */

public interface JokeApi {
    @GET("randJoke.php")
    Observable<JokeBean<List<JokeItem>>> getJoke(@Query("key") String key);
}
