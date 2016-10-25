package com.rsp.gankjoke.api;


import com.rsp.gankjoke.entity.gank.GankBean;
import com.rsp.gankjoke.entity.gank.GankItem;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by heyi on 2016/10/16.
 */

public interface GankApi {
    @GET("data/福利/{pageSize}/{page}")
    Observable<GankBean<List<GankItem>>> getMeizi(@Path("page") int page, @Path("pageSize") int pageSize);

    @GET("data/{tech}/{pageSize}/{page}")
    Observable<GankBean<List<GankItem>>> getTech(@Path("tech") String tech, @Path("page") int page, @Path("pageSize") int pageSize);
}
