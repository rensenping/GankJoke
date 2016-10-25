package com.rsp.gankjoke.api;

import com.rsp.gankjoke.entity.news.NewsBean;
import com.rsp.gankjoke.entity.news.NewsItem;
import com.rsp.gankjoke.entity.news.NewsResult;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * @author 小任
 * @date 2016/10/20
 * version 1.0
 * 描述:
 */

public interface NewsApi {
    @GET("index")
    Observable<NewsBean<List<NewsItem>>> getNews(@Query("type") String tpye, @Query("key") String key);
}
