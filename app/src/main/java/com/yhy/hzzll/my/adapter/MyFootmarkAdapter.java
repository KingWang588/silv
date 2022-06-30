package com.yhy.hzzll.my.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yhy.hzzll.R;
import com.yhy.hzzll.entity.Footprint;

import java.util.List;

/**
 * 我的足迹适配器
 * 
 * @author Yang
 * 
 */
public class MyFootmarkAdapter extends BaseAdapter {

	private Context context;
	List<Footprint> list;

	public MyFootmarkAdapter(Context context, List<Footprint> list) {
		this.context = context;
		this.list = list;
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = LayoutInflater.from(context).inflate(R.layout.item_footmark, null);
			holder.tv_content = (TextView) convertView.findViewById(R.id.tv_content);
			holder.tv_type = (TextView) convertView.findViewById(R.id.tv_type);
			holder.tv_date = (TextView) convertView.findViewById(R.id.tv_date);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		Footprint entity = list.get(position);
		if (!entity.getTitle().equals("")){
			holder.tv_content.setText(entity.getTitle());
		}else{
			holder.tv_content.setText("[语音提问]");
		}

		holder.tv_type.setText(entity.getCate_name());
		holder.tv_date.setText(entity.getAddtime());
		return convertView;
	}

	private class ViewHolder {
		private TextView tv_content;
		private TextView tv_type;
		private TextView tv_see;
		private TextView tv_heart;
		private TextView tv_date;
	}
}
