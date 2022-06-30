package com.yhy.hzzll.my.adapter;

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
import com.yhy.hzzll.entity.OrderListEntity;
import com.yhy.hzzll.message.MyNewMessageActivity;
import com.yhy.hzzll.session.SessionHelper;

import java.util.ArrayList;

/**
 * 我的订单适配器
 */
public class OrderListsAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<OrderListEntity.DataBean.ListBean> list;
    private View.OnClickListener pursue;

    public OrderListsAdapter(Context context, ArrayList<OrderListEntity.DataBean.ListBean> list, View.OnClickListener pursue) {
        this.context = context;
        this.list = list;
        this.pursue = pursue;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
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


        OrderListEntity.DataBean.ListBean entity = list.get(position);

        holder.tv_order_no.setText(entity.getOrder_no());

//		String m1 = entity.getAmount();
//		m1 = m1.substring(0,m1.length()-3);

        holder.tv_trade_price.setText(entity.getAmount() + "元");
        holder.tv_type.setText(entity.getType());
        holder.tv_create_time.setText(entity.getCreate_time());
        holder.tv_status.setText(entity.getStatus());


        if (entity.getStatus() != null) {
            if (entity.getStatus().equals("待回复")) {
                holder.tv_status.setTextColor(Color.RED);
            } else {
                holder.tv_status.setTextColor(Color.BLACK);
            }
        }

        final String accid = entity.getAccid();

        if (entity.getType().equals("专属咨询")) {


            holder.tv_pursue.setVisibility(View.VISIBLE);


            if (entity.getStatus().equals("待回复") || entity.getStatus().equals("进行中")) {
                holder.tv_pursue.setText("去回复");
            } else {
                holder.tv_pursue.setText("查看对话");
            }

            holder.tv_pursue.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.putExtra(Extras.EXTRA_ACCOUNT, accid);
                    intent.putExtra(Extras.EXTRA_CUSTOMIZATION, SessionHelper.getP2pCustomization());
                    intent.setClass(context, MyNewMessageActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    context.startActivity(intent);
                }
            });

        } else {
            if (entity.getCount_pursue() > 0) {
                holder.tv_pursue.setVisibility(View.VISIBLE);
                holder.tv_pursue.setTag(position);
                holder.tv_pursue.setOnClickListener(pursue);
            } else {
                holder.tv_pursue.setOnClickListener(null);
                holder.tv_pursue.setVisibility(View.VISIBLE);
                holder.tv_pursue.setText("查看详情");
            }
        }

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
