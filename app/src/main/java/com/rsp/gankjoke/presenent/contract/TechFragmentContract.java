package com.rsp.gankjoke.presenent.contract;

import com.rsp.gankjoke.entity.gank.GankItem;
import com.rsp.gankjoke.presenent.base.BasePresenent;
import com.rsp.gankjoke.presenent.base.BaseView;

import java.util.List;

/**
 * @author 小任
 * @date 2016/10/19
 * version 1.0
 * 描述:
 */

public interface TechFragmentContract {
    interface View extends BaseView {
        void showContent(List<GankItem> data);

        void showMoreContent(List<GankItem> data);
    }

    interface Presenent extends BasePresenent<View> {
        void loadingFirst(String tech);

        void loadingMore(String tech);
    }
}
