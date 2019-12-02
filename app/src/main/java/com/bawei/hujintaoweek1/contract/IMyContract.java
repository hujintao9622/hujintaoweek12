package com.bawei.hujintaoweek1.contract;

import com.bawei.hujintaoweek1.model.bean.Shop;

/**
 * 功能:  接口
 * 作者:  胡锦涛
 * 时间:  2019/12/2 0002 下午 2:42
 */
public interface IMyContract {
    //M层传到P层
    interface IPresenterCallBack{
        void onSuccess(Shop shop);
        void onFailure(Throwable e);
    }
    //P层传到V层
    interface IViewCallBack{
        void onSuccess(Shop shop);
        void onFailure(Throwable e);
    }
}
