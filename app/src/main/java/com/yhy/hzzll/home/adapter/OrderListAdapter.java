package com.yhy.hzzll.home.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.netease.nim.uikit.business.session.constant.Extras;
import com.yhy.hzzll.R;
import com.yhy.hzzll.home.entity.OrderListEntity;
import com.yhy.hzzll.message.MyNewMessageActivity;
import com.yhy.hzzll.my.adapter.OrderListsAdapter;
import com.yhy.hzzll.session.SessionHelper;

import java.util.List;

public class OrderListAdapter extends BaseAdapter {

    Context context;
    List<OrderListEntity.DataBean.ListBean> list;

    public OrderListAdapter(Context context, List<OrderListEntity.DataBean.ListBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {

       ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_new_order_list, null);
            holder.tv_order_no = (TextView) convertView.findViewById(R.id.tv_order_no);
            holder.tv_trade_price = (TextView) convertView.findViewById(R.id.tv_trade_price);
            holder.tv_type = (TextView) convertView.findViewById(R.id.tv_type);
            holder.tv_status = (TextView) convertView.findViewById(R.id.tv_status);
            holder.tv_create_time = (TextView) convertView.findViewById(R.id.tv_create_time);
            holder.tv_pursue = (TextView) convertView.findViewById(R.id.tv_pursue);
            holder.linear_pursue = (LinearLayout) convertView.findViewById(R.id.linear_pursue);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }


        OrderListEntity.DataBean.ListBean entity = list.get(i);

        holder.tv_order_no.setText( entity.getOrder_no());
        holder.linear_pursue.setVisibility(View.GONE);

//		String m1 = entity.getAmount();
//		m1 = m1.substring(0,m1.length()-3);

        holder.tv_trade_price.setText(entity.getAmount()+"元");
        holder.tv_type.setText(entity.getType_name());
        holder.tv_create_time.setText(entity.getCreate_time());
        holder.tv_status.setText(entity.getOrder_status());

        return convertView;
    }

    private class ViewHolder {
        private TextView tv_order_no;
        private TextView tv_create_time;
        private TextView tv_trade_price;
        private TextView tv_type;
        private TextView tv_status;
        private TextView tv_pursue;
        private LinearLayout linear_pursue;

    }
}
