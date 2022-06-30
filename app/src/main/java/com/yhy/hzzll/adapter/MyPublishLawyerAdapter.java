package com.yhy.hzzll.adapter;

import java.util.ArrayList;

import com.yhy.hzzll.R;
import com.yhy.hzzll.entity.MyPublishOfLawyerCollaborationEntity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MyPublishLawyerAdapter extends BaseAdapter {

	private Context context;
	private ArrayList<MyPublishOfLawyerCollaborationEntity> list;

	public MyPublishLawyerAdapter(Context context,
			ArrayList<MyPublishOfLawyerCollaborationEntity> list) {
		this.context = context;
		this.list = list;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(
					R.layout.item_publish_lawyer, null);
			holder = new ViewHolder();
			holder.tv_collaboration_description = (TextView) convertView
					.findViewById(R.id.tv_collaboration_description);
			holder.tv_price = (TextView) convertView
					.findViewById(R.id.tv_price);
			holder.tv_is_collocation = (TextView) convertView
					.findViewById(R.id.tv_is_collocation);
			holder.tv_date = (TextView) convertView.findViewById(R.id.tv_date);
			holder.tv_collocation_price = (TextView) convertView
					.findViewById(R.id.tv_collocation_price);
			holder.tv_state = (TextView) convertView
					.findViewById(R.id.tv_state);
			holder.tv_num = (TextView) convertView.findViewById(R.id.tv_num);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		MyPublishOfLawyerCollaborationEntity entity = list.get(position);
		holder.tv_collaboration_description.setText(entity.getTitle());
		holder.tv_price.setText(entity.getZqprice());
		String is_pay = entity.getIs_pay();
		if (is_pay.equals("0")) {
			holder.tv_is_collocation.setText("未托管");
			holder.tv_collocation_price.setVisibility(View.GONE);
		} else {
			holder.tv_is_collocation.setText("已托管");
			holder.tv_collocation_price.setVisibility(View.VISIBLE);
			holder.tv_collocation_price.setText(entity.getZqprice());
		}
		holder.tv_date.setText(entity.getHopedate());
		holder.tv_state.setText(entity.getStatus_text());
		holder.tv_num.setText(context.getString(R.string.label_count_coop,
				entity.getCount_coop()));
		return convertView;
	}

	private class ViewHolder {
		private TextView tv_collaboration_description;
		private TextView tv_price;
		private TextView tv_is_collocation;
		private TextView tv_date;
		private TextView tv_collocation_price;
		private TextView tv_state;
		private TextView tv_num;
	}
}
