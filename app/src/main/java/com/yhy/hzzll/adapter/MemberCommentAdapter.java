package com.yhy.hzzll.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yhy.hzzll.R;
import com.yhy.hzzll.entity.MemberCommentEntity;

import java.util.ArrayList;

@SuppressLint("WrongViewCast")
public class MemberCommentAdapter extends BaseAdapter {
	private Context context;
	private ArrayList<MemberCommentEntity.DataBean.ListBean> mList;
	private OnActionListenter listenter;

	public MemberCommentAdapter(Context context, ArrayList<MemberCommentEntity.DataBean.ListBean> list) {
		this.context = context;
		this.mList = list;

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
	public View getView(int position, View convertView, ViewGroup parent) {

		if (convertView == null) {
			holder = new ViewHolder();
			convertView = LayoutInflater.from(context).inflate(R.layout.item_member_comment, null);
			holder.iv_avatar_icon = (ImageView) convertView.findViewById(R.id.iv_avatar);
			holder.ratingbar_reply = (RatingBar)convertView.findViewById(R.id.ratingbar_reply);
			holder.tv_title = (TextView) convertView.findViewById(R.id.tv_username);
			holder.tv_type = (TextView) convertView.findViewById(R.id.tv_type);
			holder.tv_date = (TextView) convertView.findViewById(R.id.tv_date);
			holder.tv_content = (TextView) convertView.findViewById(R.id.tv_content);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		MemberCommentEntity.DataBean.ListBean entity = mList.get(position);
		Glide.with(context).load(entity.getHead_img()).into(holder.iv_avatar_icon);

		holder.ratingbar_reply.setRating(entity.getStart_rate()/2);
		holder.tv_title.setText(entity.getName());
		holder.tv_type.setText(entity.getType());
		holder.tv_date.setText(entity.getUpdated_at());
		holder.tv_content.setText(entity.getContent());
		
		return convertView;
	}

	private class ViewHolder {
		private RatingBar ratingbar_reply;
		private ImageView iv_avatar_icon;
		private TextView tv_title;
		private TextView tv_type;
		private TextView tv_content;
		private TextView tv_date;
	}

	public interface OnActionListenter {
		void onCommentListener(View v, int position);

		void onPraiseListener(View v, int position);
	}
}
