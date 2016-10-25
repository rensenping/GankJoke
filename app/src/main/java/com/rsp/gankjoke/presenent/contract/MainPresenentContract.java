package com.rsp.gankjoke.presenent.contract;

import android.view.View;

import com.rsp.gankjoke.entity.gank.GankItem;
import com.rsp.gankjoke.presenent.base.BasePresenent;
import com.rsp.gankjoke.presenent.base.BaseView;

import java.util.List;

/**
 * @author 小任
 * @date 2016/10/18
 * version 1.0
 * 描述:
 */

public interface MainPresenentContract {
    interface View extends BaseView {
        void startLoadingMeizi(List<GankItem> items);
    }

    interface Presenent extends BasePresenent<View>{
        void loadingMeizi(int page,int pageSize);
    }
}
