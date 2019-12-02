package com.bawei.hujintaoweek1.view.fragment;


import android.view.View;
import android.widget.GridView;

import com.bawei.hujintaoweek1.R;
import com.bawei.hujintaoweek1.base.BaseFragment;
import com.bawei.hujintaoweek1.contract.IMyContract;
import com.bawei.hujintaoweek1.model.bean.Shop;
import com.bawei.hujintaoweek1.presenter.MyPresenter;
import com.bawei.hujintaoweek1.view.adapter.MyAdapter;

import java.util.List;

/**
 * 功能:  我的Fragment
 * 作者:  胡锦涛
 * 时间:  2019/12/2 0002 下午 1:57
 */
public class MyFragment extends BaseFragment {


    private GridView gv;

    @Override
    protected void initView(View view) {
        //找控件
        gv = view.findViewById(R.id.gv);
    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_my;
    }

    @Override
    protected void initData() {
        MyPresenter myPresenter = new MyPresenter();
        myPresenter.getMyData(new IMyContract.IViewCallBack() {
            @Override
            public void onSuccess(Shop shop) {
                //获取数据
                List<Shop.DataBean> data = shop.getData();
                //设置适配器
                gv.setAdapter(new MyAdapter(data));
            }

            @Override
            public void onFailure(Throwable e) {

            }
        });
    }
}
