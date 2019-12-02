package com.bawei.hujintaoweek1.view.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.RadioGroup;

import com.bawei.hujintaoweek1.R;
import com.bawei.hujintaoweek1.base.BaseActivity;
import com.bawei.hujintaoweek1.view.fragment.LoginFragment;
import com.bawei.hujintaoweek1.view.fragment.MyFragment;
import com.bawei.hujintaoweek1.view.fragment.NewsFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能:  切换页
 * 作者:  胡锦涛
 * 时间:  2019/12/2 0002 下午 1:57
 */
public class MainActivity extends BaseActivity {

    private ViewPager vp;
    private RadioGroup rg;
    List<Fragment> fragmentList=new ArrayList<>();
    @Override
    protected void initData() {
        //设置数据
        LoginFragment loginFragment = new LoginFragment();
        NewsFragment newsFragment = new NewsFragment();
        MyFragment myFragment = new MyFragment();
        fragmentList.add(loginFragment);
        fragmentList.add(newsFragment);
        fragmentList.add(myFragment);
        //设置适配器
        vp.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return fragmentList.get(position);
            }

            @Override
            public int getCount() {
                return fragmentList.size();
            }
        });
        //默认页面
        rg.check(rg.getChildAt(0).getId());
        vp.setCurrentItem(0);
    }

    @Override
    protected void initView() {
        //找控件
        vp = findViewById(R.id.vp);
        rg = findViewById(R.id.rg);
        //关联ViewPager与RadioGroup
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rb_login:
                        vp.setCurrentItem(0);
                        break;
                    case R.id.rb_news:
                        vp.setCurrentItem(1);
                        break;
                    case R.id.rb_my:
                        vp.setCurrentItem(2);
                        break;
                }
            }
        });
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                rg.check(rg.getChildAt(position).getId());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_main;
    }
    public void jump(){
        vp.setCurrentItem(2);
    }
}
