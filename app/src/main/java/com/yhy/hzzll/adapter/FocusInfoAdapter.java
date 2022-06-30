package com.yhy.hzzll.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yhy.hzzll.R;
import com.yhy.hzzll.entity.FocusInfoEntity;

import java.util.List;

/**
 * 我的关注==信息
 *
 * @author wangyang
 */
public class FocusInfoAdapter extends BaseAdapter {

    private final Context context;
    private List<FocusInfoEntity.DataBean.ListBean> focusInfoList;

    public FocusInfoAdapter(Context context, List<FocusInfoEntity.DataBean.ListBean> focusInfoList) {
        this.context = context;
        this.focusInfoList = focusInfoList;
    }

    @Override
    public int getCount() {
        return focusInfoList == null ? 0 : focusInfoList.size();
    }

    @Override
    public Object getItem(int arg0) {
        return focusInfoList.get(arg0);
    }

    @Override
    public long getItemId(int arg0) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup arg2) {
        ViewHolder holder = null;
        if (view == null) {
            holder = new ViewHolder();
            view = LayoutInflater.from(context).inflate(R.layout.item_focus_info, null);
            holder.iv_head = (ImageView) view.findViewById(R.id.iv_head);
            holder.iv_name = (TextView) view.findViewById(R.id.iv_name);
            holder.iv_date = (TextView) view.findViewById(R.id.iv_date);
            holder.iv_content = (TextView) view.findViewById(R.id.iv_content);
            holder.iv_tag = (TextView) view.findViewById(R.id.iv_tag);

            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }


        FocusInfoEntity.DataBean.ListBean listBean = focusInfoList.get(position);

        holder.iv_date.setText(listBean.getUpdated_at());
        holder.iv_tag.setText("快速咨询");

        if (!listBean.getContent().equals("")) {
            holder.iv_content.setText(listBean.getContent());
        } else {
            holder.iv_content.setText("[语音问题]");
        }

        Glide.with(context).load(listBean.getHead_img()).into(holder.iv_head);
        holder.iv_name.setText(listBean.getNickname());
        return view;
    }

    private class ViewHolder {
        private ImageView iv_head;
        private TextView iv_name;
        private TextView iv_date;
        private TextView iv_content;
        private TextView iv_tag;
    }

}
