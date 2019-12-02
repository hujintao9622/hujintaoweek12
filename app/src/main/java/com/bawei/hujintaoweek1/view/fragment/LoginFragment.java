package com.bawei.hujintaoweek1.view.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.hujintaoweek1.R;
import com.bawei.hujintaoweek1.base.BaseFragment;
import com.bawei.hujintaoweek1.util.NetUtil;

/**
 * 功能:  首页Fragment
 * 作者:  胡锦涛
 * 时间:  2019/12/2 0002 下午 1:57
 */
public class LoginFragment extends BaseFragment {


    private ImageView img;
    private TextView txt;

    @Override
    protected void initView(View view) {
        //找控件
        img = view.findViewById(R.id.login_img);
        txt = view.findViewById(R.id.login_text);
    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_login;
    }

    @Override
    protected void initData() {
        //判断有无网络
        boolean b = NetUtil.getInstance().hasNet(getContext());
        if (b){
            img.setVisibility(View.VISIBLE);
            txt.setVisibility(View.GONE);
        }else {
            img.setVisibility(View.GONE);
            txt.setVisibility(View.VISIBLE);
        }
    }
}
