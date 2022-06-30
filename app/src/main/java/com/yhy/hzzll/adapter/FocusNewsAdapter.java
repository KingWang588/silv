package com.yhy.hzzll.adapter;

import java.util.ArrayList;

import com.yhy.hzzll.R;
import com.yhy.hzzll.entity.PAssignmentEntity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class FocusNewsAdapter extends BaseAdapter {

	private final Context context;
	private ArrayList<PAssignmentEntity> mList;

	public FocusNewsAdapter(Context context, ArrayList<PAssignmentEntity> list) {
		this.context = context;
		mList = list;
	}

	@Override
	public int getCount() {
		return mList.size();
	}

	@Override
	public Object getItem(int arg0) {
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View view, ViewGroup arg2) {
		ViewHolder holder = null;
		if (view == null) {
			holder = new ViewHolder();
			view = LayoutInflater.from(context).inflate(
					R.layout.item_focus_news, null);
			holder.tv_content = (TextView) view.findViewById(R.id.tv_content);
			holder.tv_see = (TextView) view.findViewById(R.id.tv_see);
			holder.tv_heart = (TextView) view.findViewById(R.id.tv_heart);
			holder.tv_date = (TextView) view.findViewById(R.id.tv_date);
			view.setTag(holder);
		} else {
			holder = (ViewHolder) view.getTag();
		}
		PAssignmentEntity entity = mList.get(position);
		holder.tv_content.setText(entity.getTitle());
		holder.tv_see.setText(entity.getId());
		holder.tv_heart.setText(entity.getId());
		holder.tv_date.setText(entity.getCtime());
		return view;
	}

	private class ViewHolder {
		private TextView tv_content;
		private TextView tv_see;
		private TextView tv_heart;
		private TextView tv_date;
	}
}
