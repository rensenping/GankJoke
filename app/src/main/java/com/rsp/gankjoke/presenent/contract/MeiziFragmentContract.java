package com.rsp.gankjoke.presenent.contract;

import android.view.View;

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

public interface MeiziFragmentContract {
    interface View extends BaseView {
        void showMeizi(List<GankItem> gankItems);
        void showMoreMeizi(List<GankItem> gankItems);
    }

    interface Presenent extends BasePresenent<View> {
        void loadMeizi();

        void loadMoreMeizi();
    }

}
