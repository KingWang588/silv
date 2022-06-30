package com.yhy.hzzll.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.yhy.hzzll.R;
import com.yhy.hzzll.entity.LawyerStyleEntity;
import com.yhy.hzzll.framework.MyData;

import java.util.ArrayList;

@SuppressLint("WrongViewCast")
public class OfficePublishMienAdapter extends BaseAdapter {
	private Context context;
	private ArrayList<LawyerStyleEntity> list = new ArrayList<LawyerStyleEntity>();
	private OnActionListenter listenter;

	public OfficePublishMienAdapter(Context context,
			ArrayList<LawyerStyleEntity> list) {
		this.context = context;
		// this.list = list;
	}

	public void setOnActionListenter(OnActionListenter l) {
		this.listenter = l;
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int arg0) {
		return list.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = LayoutInflater.from(context).inflate(
					R.layout.item_office_lawyercase, null);
			holder.iv_avatar_icon = (ImageView) convertView
					.findViewById(R.id.tv_content);
			holder.tv_title = (TextView) convertView.findViewById(R.id.tv_type);
			holder.tv_action_praise = (TextView) convertView
					.findViewById(R.id.tv_see);
			holder.tv_action_delete = (TextView) convertView
					.findViewById(R.id.tv_heart);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		LawyerStyleEntity entity = list.get(position);
		ImageLoader.getInstance().displayImage(
				MyData.IP + entity.getImgurl().get(0).getB_url(),
				holder.iv_avatar_icon);
		holder.tv_title.setText(entity.getSummary());
		holder.tv_action_praise.setTag(position);
		holder.tv_action_praise.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (null != listenter) {
					listenter.onPraiseListener(v, ((Integer) v.getTag()));
				}
			}
		});

		holder.tv_action_delete.setTag(position);
		holder.tv_action_delete.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (null != listenter) {
					listenter.onDeleteListener(v, ((Integer) v.getTag()));
				}
			}
		});
		return convertView;
	}

	private class ViewHolder {
		private ImageView iv_avatar_icon;
		private TextView tv_title;
		private TextView tv_action_praise;
		private TextView tv_action_delete;
	}

	public interface OnActionListenter {
		void onDeleteListener(View v, int position);

		void onPraiseListener(View v, int position);
	}
}
