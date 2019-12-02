package com.bawei.hujintaoweek1.presenter;

import com.bawei.hujintaoweek1.contract.IMyContract;
import com.bawei.hujintaoweek1.model.MyModel;
import com.bawei.hujintaoweek1.model.bean.Shop;

/**
 * 功能:  页面
 * 作者:  胡锦涛
 * 时间:  2019/12/2 0002 下午 2:39
 */
public class MyPresenter {
    public void getMyData(final IMyContract.IViewCallBack iViewCallBack){
        MyModel myModel = new MyModel();
        myModel.getMyData(new IMyContract.IPresenterCallBack() {
            @Override
            public void onSuccess(Shop shop) {
                iViewCallBack.onSuccess(shop);
            }

            @Override
            public void onFailure(Throwable e) {
                iViewCallBack.onFailure(e);
            }
        });
    }
}
