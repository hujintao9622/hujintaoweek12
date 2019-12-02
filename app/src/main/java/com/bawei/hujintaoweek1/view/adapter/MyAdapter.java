package com.bawei.hujintaoweek1.view.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.hujintaoweek1.R;
import com.bawei.hujintaoweek1.model.bean.Shop;
import com.bawei.hujintaoweek1.util.NetUtil;

import java.util.List;

/**
 * 功能:  GrideView适配器
 * 作者:  胡锦涛
 * 时间:  2019/12/2 0002 下午 2:47
 */
public class MyAdapter extends BaseAdapter {
    private List<Shop.DataBean> data;

    public MyAdapter(List<Shop.DataBean> data) {

        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //声明优化类
        ViewHolder holder=null;
        if (convertView==null){
            //创建优化类
            holder=new ViewHolder();
            //加载布局
            convertView=View.inflate(parent.getContext(), R.layout.myitem,null);
            //初始化数据
            holder.img=convertView.findViewById(R.id.my_img);
            holder.name=convertView.findViewById(R.id.my_name);
            //标记
            convertView.setTag(holder);
        }else {
            //复用布局
            holder= (ViewHolder) convertView.getTag();
        }
        //获取对应数据
        Shop.DataBean dataBean = data.get(position);
        holder.name.setText(dataBean.getGoods_name());
        String goods_thumb = dataBean.getGoods_thumb();
        //加载图片
        NetUtil.getInstance().getBitmap(goods_thumb,holder.img);
        return convertView;
    }
    class ViewHolder{
        ImageView img;
        TextView name;
    }
}
