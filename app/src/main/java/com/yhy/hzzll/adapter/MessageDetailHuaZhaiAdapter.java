package com.yhy.hzzll.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yhy.hzzll.R;
import com.yhy.hzzll.entity.MessageOfSystemEntity;

import java.util.List;

/**
 * 我的消息
 * 
 * @author Yang
 * 
 */
public class MessageDetailHuaZhaiAdapter extends BaseAdapter {

	private Context context;
	private List<MessageOfSystemEntity.DataBean.ListBean> coll;

	public MessageDetailHuaZhaiAdapter(Context context, List<MessageOfSystemEntity.DataBean.ListBean> coll) {
		this.context = context;
		this.coll = coll;
	}

	@Override
	public int getCount() {
		return coll.size();
	}

	@Override
	public Object getItem(int arg0) {
		return coll.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {

		return arg0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup arg2) {
		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = LayoutInflater.from(context).inflate(R.layout.item_message_detail_huazhai, null);
			holder.tv_msg_title = (TextView) convertView.findViewById(R.id.tv_msg_title);
			holder.tv_date = (TextView) convertView.findViewById(R.id.tv_date);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		MessageOfSystemEntity.DataBean.ListBean msg = coll.get(position);
		holder.tv_msg_title.setText(msg.getContent());
		holder.tv_date.setText(msg.getSend_time());

		return convertView;
	}

	private class ViewHolder {
		private TextView tv_msg_title;
		private TextView tv_date;
	}
}
