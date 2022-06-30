package com.yhy.hzzll.my.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.yhy.hzzll.R;
import com.yhy.hzzll.my.entity.InvitetionEntity;

import java.util.List;

/**
 * Created by chengying on 2017/7/21.
 */

public class GridViewAdapter extends BaseAdapter {

    List<InvitetionEntity.DataBean.GeneralBean.ListsBean> listsBeen;
    Context context;

    public GridViewAdapter(List<InvitetionEntity.DataBean.GeneralBean.ListsBean> listsBeen, Context context) {
        this.listsBeen = listsBeen;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listsBeen == null ? 0 : listsBeen.size();
    }

    @Override
    public Object getItem(int position) {
        return listsBeen==null ? null : listsBeen.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        convertView = LayoutInflater.from(context).inflate(R.layout.item_photo,null);
        ImageView iv_photo = (ImageView) convertView.findViewById(R.id.iv_photo);
        Glide.with(context).load(listsBeen.get(position).getAvatar()).into(iv_photo);

        return convertView;
    }
}
