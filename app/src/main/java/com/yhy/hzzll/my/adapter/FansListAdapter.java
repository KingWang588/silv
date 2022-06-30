package com.yhy.hzzll.my.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yhy.hzzll.R;
import com.yhy.hzzll.entity.FocusListEntity;

import java.util.List;

public class FansListAdapter extends BaseAdapter {

    private Context context;
    private List<FocusListEntity.DataBean.ListBean> focusVipList;

    public FansListAdapter(Context context, List<FocusListEntity.DataBean.ListBean> focusVipList) {
        this.context = context;
        this.focusVipList = focusVipList;
    }

    @Override
    public int getCount() {
        return focusVipList.size();
    }

    @Override
    public Object getItem(int position) {
        return focusVipList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = View.inflate(context,R.layout.item_fans, null);
        TextView tv_name = (TextView) convertView.findViewById(R.id.tv_name);
        TextView tv_address = (TextView) convertView.findViewById(R.id.tv_address);
        TextView tv_type = (TextView) convertView.findViewById(R.id.tv_type);
        ImageView iv_head = (ImageView) convertView.findViewById(R.id.iv_head);
        LinearLayout linear_office = (LinearLayout)convertView.findViewById(R.id.linear_office);
        Glide.with(context).load(focusVipList.get(position).getHead_img()).into(iv_head);
        tv_name.setText(focusVipList.get(position).getNickname());
        tv_address.setText(focusVipList.get(position).getBase_region());

        if (focusVipList.get(position).getUser_type().equals("1")) {
            linear_office.setVisibility(View.VISIBLE);
            tv_type.setText("律师");
        }else{
            linear_office.setVisibility(View.GONE);
            tv_type.setText("用户");
        }

        return convertView;

    }
}


