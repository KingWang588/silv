package com.yhy.hzzll.my.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yhy.hzzll.R;
import com.yhy.hzzll.my.entity.MyInvitetationEntity;

import java.util.List;

public class IncomeAdapter extends BaseAdapter {

    private Context context;
    private List<MyInvitetationEntity.DataBean.ListBean> listBeans;




    public IncomeAdapter(Context context, List<MyInvitetationEntity.DataBean.ListBean> listBeans) {
        this.context = context;
        this.listBeans = listBeans;
    }

    @Override
    public int getCount() {
        return listBeans.size();
    }

    @Override
    public Object getItem(int i) {
        return listBeans.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        view =  LayoutInflater.from(context).inflate(R.layout.item_income, null);
        TextView tv_name = view.findViewById(R.id.tv_name);
        TextView tv_phone = view.findViewById(R.id.tv_phone);
        TextView tv_time = view.findViewById(R.id.tv_time);
        TextView tv_type = view.findViewById(R.id.tv_type);
        TextView tv_price = view.findViewById(R.id.tv_price);

        MyInvitetationEntity.DataBean.ListBean listBean = listBeans.get(i);

        tv_name.setText(listBean.getNickname());
        tv_phone.setText(listBean.getMobile());
        tv_time.setText(listBean.getPayment_time());
        tv_type.setText("收益类型："+listBean.getType_name());


        String m3 = listBean.getAmount_pay();
        m3 = m3.substring(0, m3.length() - 3);

        tv_price.setText("收益金额："+m3+"元");

        return view;
    }


}
