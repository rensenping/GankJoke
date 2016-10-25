package com.rsp.gankjoke.presenent.contract;

import com.rsp.gankjoke.entity.news.NewsItem;
import com.rsp.gankjoke.presenent.base.BasePresenent;
import com.rsp.gankjoke.presenent.base.BaseView;

import java.util.List;

/**
 * @author 小任
 * @date 2016/10/20
 * version 1.0
 * 描述:
*/

public interface NewsListContract {
    interface View extends BaseView {
        void showNews(List<NewsItem> newsItems);
    }

    interface Presenent extends BasePresenent<View> {
        void loadingNews(String type);
    }
}
