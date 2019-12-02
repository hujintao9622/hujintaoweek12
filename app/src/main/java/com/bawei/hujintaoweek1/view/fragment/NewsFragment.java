package com.bawei.hujintaoweek1.view.fragment;


import android.view.View;
import android.widget.Button;

import com.bawei.hujintaoweek1.R;
import com.bawei.hujintaoweek1.base.BaseFragment;
import com.bawei.hujintaoweek1.view.activity.MainActivity;

/**
 * 功能:  新闻页Fragment
 * 作者:  胡锦涛
 * 时间:  2019/12/2 0002 下午 1:57
 */
public class NewsFragment extends BaseFragment {


    private Button bt;

    @Override
    protected void initView(View view) {
        //找控件
        bt = view.findViewById(R.id.news_bt);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转
                MainActivity activity = (MainActivity) getActivity();
                activity.jump();
            }
        });
    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_news;
    }

    @Override
    protected void initData() {

    }
}
