package com.bawei.hujintaoweek1.model;

import com.bawei.hujintaoweek1.contract.IMyContract;
import com.bawei.hujintaoweek1.model.bean.Shop;
import com.bawei.hujintaoweek1.util.NetUtil;
import com.google.gson.Gson;

/**
 * 功能:  页面
 * 作者:  胡锦涛
 * 时间:  2019/12/2 0002 下午 2:40
 */
public class MyModel {
    String string="http://blog.zhaoliang5156.cn/api/shop/shop1.json";
    public void getMyData(final IMyContract.IPresenterCallBack iPresenterCallBack){
        //请求数据
        NetUtil.getInstance().getJson(string, new NetUtil.MyCallBack() {
            @Override
            public void onGetJson(String json) {
                //解析
                Shop shop = new Gson().fromJson(json, Shop.class);
                iPresenterCallBack.onSuccess(shop);
            }

            @Override
            public void onfailure(Throwable e) {
                iPresenterCallBack.onFailure(e);
            }
        });
    }
}
