package com.yhy.hzzll.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yhy.hzzll.R;
import com.yhy.hzzll.entity.MyCaseEntity;
import com.yhy.hzzll.utils.DateUtils;

import java.util.ArrayList;

/**
 * 我的足迹适配器
 * 
 * @author Yang
 * 
 */
public class MyCaseAdapter extends BaseAdapter {

	private Context context;
	private ArrayList<MyCaseEntity> list;

	public MyCaseAdapter(Context context, ArrayList<MyCaseEntity> list) {
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
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = LayoutInflater.from(context).inflate(R.layout.item_case, null);
			holder.tv_title = (TextView) convertView.findViewById(R.id.tv_title);
			holder.tv_date = (TextView) convertView.findViewById(R.id.tv_date);
			holder.tv_state = (TextView) convertView.findViewById(R.id.tv_state);
			holder.tv_progress = (TextView) convertView.findViewById(R.id.tv_progress);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		MyCaseEntity entity = list.get(position);
		holder.tv_title.setText(entity.getTitle());

		holder.tv_date.setText(DateUtils.getDateToString(Long.parseLong(entity.getCtime()+"000")));
		holder.tv_state.setText(entity.getStatus());
		holder.tv_progress.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (l != null) {
					l.onLookProgress(v, position);
				}
			}
		});
		return convertView;
	}

	private class ViewHolder {
		private TextView tv_title;
		private TextView tv_date;
		private TextView tv_state;
		private TextView tv_progress;
	}

	private OnActionLinsenter l;

	public interface OnActionLinsenter {
		void onLookProgress(View v, int position);
	}

	public void setOnActionLinsenter(OnActionLinsenter l) {
		this.l = l;
	}
}
