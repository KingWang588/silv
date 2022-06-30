package com.yhy.hzzll.home.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yhy.hzzll.R;
import com.yhy.hzzll.entity.ConsultEntity;
import com.yhy.hzzll.utils.PrefsUtils;

import java.util.List;

public class ConsultAdapter extends BaseAdapter {

    private Context context;
    private List<ConsultEntity.DataBean.ListBean> list;

    public ConsultAdapter(Context context, List<ConsultEntity.DataBean.ListBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list != null ? list.size() : 0;
    }

    @Override
    public Object getItem(int arg0) {
        return list.get(arg0);
    }

    @Override
    public long getItemId(int arg0) {
        return arg0;
    }

    @Override
    public View getView(int arg0, View view, ViewGroup arg2) {

        ViewHolder holder = null;
//        if (view == null) {
            holder = new ViewHolder();
            view = LayoutInflater.from(context).inflate(R.layout.item_consult, null);
            holder.iv_head = (ImageView) view.findViewById(R.id.iv_head);
            holder.iv_name = (TextView) view.findViewById(R.id.iv_name);
            holder.iv_adress = (TextView) view.findViewById(R.id.iv_adress);
            holder.tv_time = (TextView) view.findViewById(R.id.tv_time);
            holder.iv_content = (TextView) view.findViewById(R.id.iv_content);
            holder.iv_type = (TextView) view.findViewById(R.id.iv_type);
            holder.iv_listen = (TextView) view.findViewById(R.id.iv_listen);
            holder.iv_praise = (TextView) view.findViewById(R.id.iv_praise);
            holder.iv_bounty = (TextView) view.findViewById(R.id.iv_bounty);
            holder.iv_redpacket = (ImageView) view.findViewById(R.id.iv_redpacket);
            holder.i_want = (TextView) view.findViewById(R.id.btn_I_want);
            holder.image_i_want = (ImageView) view.findViewById(R.id.image_i_want);
            holder.linear_i_want = (LinearLayout) view.findViewById(R.id.linear_i_want);

            holder.linear_voice= (LinearLayout) view.findViewById(R.id.linear_voice);
            holder.tv_content_lenth= (TextView) view.findViewById(R.id.tv_content_lenth);



        ConsultEntity.DataBean.ListBean entity = list.get(arg0);

        if (entity.getAvatar()!=null&entity.getAvatar().length()!=0){
            Glide.with(context).load(entity.getAvatar()).into(holder.iv_head);
        }

        holder.iv_name.setText(entity.getNickname());
        if (entity.getBase_region_id()!=null){
            holder.iv_adress.setText(entity.getBase_region_id().getWhole_name());
        }
        holder.tv_time.setText(entity.getDate_time());

        if (entity.getIs_closed() == 1){
            holder.iv_redpacket.setVisibility(View.GONE);
            Resources res = context.getResources();
            Drawable drawable = res.getDrawable(R.drawable.background_i_want);
            holder.linear_i_want.setBackgroundDrawable(drawable);
            holder.image_i_want.setVisibility(View.GONE);
            holder.i_want .setText("问题已经关闭");
            holder.i_want .setTextColor(Color.GRAY);

        }else{
            if (entity.getCount_reply() < 3) {
                holder.iv_redpacket.setVisibility(View.VISIBLE);
            } else {
                holder.iv_redpacket.setVisibility(View.GONE);
            }
        }

        if (PrefsUtils.getString(context,PrefsUtils.UID)!=null){
            if(entity.getCount() !=0 ){
                Resources res = context.getResources(); //resource handle
                Drawable drawable = res.getDrawable(R.drawable.background_i_want);
                holder.linear_i_want.setBackgroundDrawable(drawable);
                holder.image_i_want.setImageResource(R.drawable.voice_1);
                holder.i_want .setText("您已回复");
                holder.i_want .setTextColor(Color.BLACK);

                holder.iv_redpacket.setVisibility(View.GONE);

            }
//            else{
//
//                Resources res = context.getResources(); //resource handle
//                Drawable drawable = res.getDrawable(R.drawable.background_btn_i_want);
//                holder.linear_i_want.setBackgroundDrawable(drawable);
//                holder.image_i_want.setImageResource(R.drawable.voice);
//                holder.i_want .setText("语音解答疑");
//                holder.i_want .setTextColor(context.getResources().getColor(R.color.orangeyellow));
//            }
        }


        if (entity.getContent().length() == 0){
            holder.iv_content.setVisibility(View.GONE);
            holder.linear_voice.setVisibility(View.VISIBLE);
            holder.tv_content_lenth.setText(""+entity.getSpeech_length()+"s");
        }else{
            holder.iv_content.setText(entity.getContent());
        }


        holder.iv_type.setText(entity.getLegal_name());
        holder.iv_listen.setText(entity.getCount_view() + "");
        holder.iv_praise.setText(entity.getCount_follow() + "");
        holder.iv_bounty.setText(entity.getCount_reply() + "");

        return view;
    }

    private class ViewHolder {
        private ImageView iv_head;// 头像
        private TextView iv_name;// 姓名
        private TextView iv_adress;// 地点 日期
        private TextView tv_time;
        private TextView iv_content;// 内容
        private TextView iv_type;// 类型
        private TextView iv_listen;// 浏览
        private TextView iv_praise;// 收藏
        private TextView iv_bounty;// 回复
        private TextView i_want;// 回复
        ImageView image_i_want;
        LinearLayout linear_i_want;
        LinearLayout linear_voice;
        TextView tv_content_lenth;
        private ImageView iv_redpacket;
    }
}
