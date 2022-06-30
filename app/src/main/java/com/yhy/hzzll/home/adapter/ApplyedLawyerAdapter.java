package com.yhy.hzzll.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yhy.hzzll.R;
import com.yhy.hzzll.home.entity.ServiceDetailsEntity;

import java.util.List;


public class ApplyedLawyerAdapter extends BaseAdapter {


    public interface Cont {
        void Entrust(String id,String money);
        void Connect(String id);
    }

    Cont cont;

    public void setCont(Cont cont) {
        this.cont = cont;
    }

    Context context;
    List<ServiceDetailsEntity.DataBean.ServiceInfoBean.ApplicationInfoBean> list;

    public ApplyedLawyerAdapter(Context context, List<ServiceDetailsEntity.DataBean.ServiceInfoBean.ApplicationInfoBean> list) {
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
    public View getView(final int i, View view, ViewGroup viewGroup) {

        view = LayoutInflater.from(context).inflate(R.layout.item_applyed_lawyer,
                null);

        ImageView iv_avatar_l = view.findViewById(R.id.iv_avatar_l);
        TextView tv_name_l = view.findViewById(R.id.tv_name_l);
        TextView tv_address_l = view.findViewById(R.id.tv_address_l);
        TextView tv_specialty_l = view.findViewById(R.id.tv_specialty_l);
        TextView tv_time_l = view.findViewById(R.id.tv_time_l);
        TextView tv_price_l = view.findViewById(R.id.tv_price_l);
        TextView tv_advantage_l = view.findViewById(R.id.tv_advantage_l);
        TextView tv_sure_entrust = view.findViewById(R.id.tv_sure_entrust);
        TextView tv_connect = view.findViewById(R.id.tv_connect);

        if (list.get(i).getAvatar() != null && list.get(i).getAvatar().length() != 0)
            Glide.with(context).load(list.get(i).getAvatar() ).into(iv_avatar_l);

        tv_name_l.setText(list.get(i).getTruename());
        tv_address_l.setText(list.get(i).getBase_region_id().getWhole_name());
        tv_specialty_l.setText(list.get(i).getLawyer_secpical());
        tv_time_l.setText(list.get(i).getCreated_at());

        String amount = list.get(i).getAmount();
        amount = amount.substring(0,amount.length()-3);
        tv_price_l.setText(amount+"元");
        tv_advantage_l.setText(list.get(i).getAdvantage());

        tv_sure_entrust.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cont!=null){
                    cont.Entrust(list.get(i).getId()+"",list.get(i).getAmount());
                }
            }
        });
        tv_connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cont!=null){
                    cont.Connect(list.get(i).getAccid());
                }
            }
        });

        return view;
    }
}
