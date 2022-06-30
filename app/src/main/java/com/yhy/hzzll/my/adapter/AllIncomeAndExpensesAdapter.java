package com.yhy.hzzll.my.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yhy.hzzll.R;
import com.yhy.hzzll.entity.IncomeAndExpensesEntity;

import java.util.ArrayList;

/**
 * 收支明细
 *
 */
public class AllIncomeAndExpensesAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<IncomeAndExpensesEntity.DataBean.ListBean> list;

    public AllIncomeAndExpensesAdapter(Context context,
                                       ArrayList<IncomeAndExpensesEntity.DataBean.ListBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
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
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.item_income_and_expenses, null);
            holder = new ViewHolder();
            holder.tv_collaboration_description = (TextView) convertView
                    .findViewById(R.id.tv_collaboration_description);
            holder.tv_pay_type = (TextView) convertView
                    .findViewById(R.id.tv_pay_type);
            holder.tv_money = (TextView) convertView
                    .findViewById(R.id.tv_money);
            holder.tv_order_no = (TextView) convertView
                    .findViewById(R.id.tv_order_no);
            holder.tv_date = (TextView) convertView.findViewById(R.id.tv_date);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        IncomeAndExpensesEntity.DataBean.ListBean entity = list.get(position);
        holder.tv_collaboration_description.setText(entity.getType() + "");
        holder.tv_pay_type.setText(entity.getPayment());
        String direct = entity.getDirection();
        String account = entity.getAmount();

        account = account.substring(0,account.length()-3);

        if (direct.equals("收入")) {
            holder.tv_money.setText("+" + account+"元");
            holder.tv_money.setTextColor(context.getResources().getColor(R.color.red));
        } else {
            holder.tv_money.setText("-" + account+"元");
            holder.tv_money.setTextColor(context.getResources().getColor(R.color.textbule));
        }
        holder.tv_order_no.setText(context.getString(
                R.string.label_order_no_txt, entity.getOrder_no() + ""));
        holder.tv_date.setText(entity.getCreated_at() + "");
        return convertView;
    }

    private class ViewHolder {
        private TextView tv_collaboration_description;
        private TextView tv_pay_type;
        private TextView tv_money;
        private TextView tv_order_no;
        private TextView tv_date;
    }
}
