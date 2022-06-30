package com.yhy.hzzll.my.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yhy.hzzll.R;
import com.yhy.hzzll.my.entity.IncomeEntity;

import java.util.List;

public class MyInvitationAdapter extends BaseAdapter {

    private Context context;
    private List<IncomeEntity.DataBean.ListBean> listBeans;




    public MyInvitationAdapter(Context context, List<IncomeEntity.DataBean.ListBean> listBeans) {
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

        view =  LayoutInflater.from(context).inflate(R.layout.item_invitation, null);
        TextView tv_name = view.findViewById(R.id.tv_name);
        TextView tv_phone = view.findViewById(R.id.tv_phone);
        TextView tv_time = view.findViewById(R.id.tv_time);
        ImageView iv_avatar = view.findViewById(R.id.iv_avatar);


        IncomeEntity.DataBean.ListBean listBean = listBeans.get(i);

        tv_name.setText("用户昵称："+listBean.getNickname());
        tv_phone.setText("用户手机："+listBean.getMobile());
        tv_time.setText("注册时间："+listBean.getCreated_at());
        Glide.with(context).load(listBean.getAvatar()).into(iv_avatar);

        return view;
    }
}
