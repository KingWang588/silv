package com.yhy.hzzll.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yhy.hzzll.R;
import com.yhy.hzzll.entity.SuccessfulCaseEntity;

import java.util.ArrayList;

@SuppressLint("WrongViewCast")
public class PublishedArtclesAdapter extends BaseAdapter {
	private Context context;
	private ArrayList<SuccessfulCaseEntity.DataBean.ListBean> mList;
	private OnActionListenter listenter;
	private boolean isLookOtherOffice;

	public PublishedArtclesAdapter(Context context, ArrayList<SuccessfulCaseEntity.DataBean.ListBean> list, boolean isLookOtherOffice) {
		this.context = context;
		this.mList = list;
		this.isLookOtherOffice = isLookOtherOffice;
	}

	public void setOnActionListenter(OnActionListenter l) {
		this.listenter = l;
	}

	@Override
	public int getCount() {
		return mList.size();
	}

	@Override
	public Object getItem(int arg0) {
		return mList.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		return 0;
	}

	ViewHolder holder = null;

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {

		if (convertView == null) {
			holder = new ViewHolder();
			convertView = LayoutInflater.from(context).inflate(R.layout.item_successfull, null);
			holder.tv_title = (TextView) convertView.findViewById(R.id.tv_title);
			holder.tv_delete = (TextView) convertView.findViewById(R.id.tv_delete);
			holder.tv_date = (TextView) convertView.findViewById(R.id.tv_date);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		SuccessfulCaseEntity.DataBean.ListBean entity = mList.get(position);

		holder.tv_title.setText(entity.getTitle());
		String data = entity.getCreated_at();
		holder.tv_date.setText(data.substring(0,10));

		holder.tv_delete.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (null != listenter) {
					listenter.onDeleteListener(v, position);
				}
			}
		});

		if (isLookOtherOffice) {
			holder.tv_delete.setVisibility(View.GONE);
		} else {
//			holder.tv_delete.setVisibility(View.VISIBLE);
		}
		return convertView;
	}

	private class ViewHolder {
		private TextView tv_title;
		private TextView tv_delete;
		private TextView tv_date;
	}

	public interface OnActionListenter {
		void onDeleteListener(View v, int position);
	}
}
