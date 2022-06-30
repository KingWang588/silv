package com.yhy.hzzll.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.yhy.hzzll.R;
import com.yhy.hzzll.entity.FocusServiceEntity;
import com.yhy.hzzll.framework.MyData;
import com.yhy.hzzll.utils.ImageLoaderUtils;

public class FocusServiceAdapter extends BaseAdapter {

	private final Context context;
	private List<FocusServiceEntity.list> focusServiceList;

	public FocusServiceAdapter(Context context,
			List<FocusServiceEntity.list> focusServiceList) {
		this.context = context;
		// TODO Auto-generated constructor stub
		this.focusServiceList = focusServiceList;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return focusServiceList == null ? 0 : focusServiceList.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return focusServiceList.get(arg0);
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
					R.layout.item_focus_service, null);
			holder.iv_img = (ImageView) view.findViewById(R.id.iv_img);
			holder.iv_content = (TextView) view.findViewById(R.id.iv_content);
			holder.iv_group = (TextView) view.findViewById(R.id.iv_group);
			holder.iv_add = (TextView) view.findViewById(R.id.iv_add);
			holder.iv_collect = (TextView) view.findViewById(R.id.iv_collect);
			holder.iv_money = (TextView) view.findViewById(R.id.iv_money);

			view.setTag(holder);
		} else {
			holder = (ViewHolder) view.getTag();
		}
		try {
			ImageLoaderUtils.initUtils().display(
					MyData.IP + focusServiceList.get(position).getImgurl(),
					holder.iv_img);
			holder.iv_content.setText(focusServiceList.get(position).getName());
			holder.iv_group.setText(focusServiceList.get(position)
					.getTruename());
			holder.iv_money.setText(focusServiceList.get(position).getPrice()
					+ focusServiceList.get(position).getUnit());
		} catch (Exception e) {
			// TODO: handle exception
		}
		return view;
	}

	private class ViewHolder {
		ImageView iv_img;
		TextView iv_content;
		TextView iv_group;
		TextView iv_add;
		TextView iv_collect;
		TextView iv_money;
	}
}
