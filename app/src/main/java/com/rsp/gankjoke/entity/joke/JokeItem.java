package com.rsp.gankjoke.entity.joke;

import com.google.gson.Gson;

/**
 * @author 小任
 * @date 2016/10/21
 * version 1.0
 * 描述:
 */

public class JokeItem {

    /**
     * content : 管管今天和一个同学逛街回来，　　做公交车的时候，等9路时，8路一直过，　　管管就问同学：“为什么8路这么多呢？”　　同学回答：“因为现在是共产党执政时代！”&nbsp;
     * hashId : 66f95f5b958bc9eff40cf376b35d4d3b
     * unixtime : 1477011230
     * updatetime : 2016-10-21 08:53:50
     */

    public String content;
    public String hashId;
    public String unixtime;
    public String updatetime;

    public static JokeItem objectFromData(String str) {

        return new Gson().fromJson(str, JokeItem.class);
    }
}
