package com.yhy.hzzll.adapter;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;

import com.yhy.hzzll.R;
import com.yhy.hzzll.utils.ImageLoaderUtils;

public class PhotoAlbumSingleAdapter extends BaseAdapter {
	private Activity activity;// 上下文
	private List<String> list;

	private List<String> listPath = new ArrayList<String>();

	private LayoutInflater inflater = null;// 导入布局
	private int temp = -1;

	int index = 0;

	public PhotoAlbumSingleAdapter(Activity activity, List<String> allDataList) {
		this.activity = activity;
		this.list = allDataList;
		inflater = LayoutInflater.from(activity);
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {

		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	// listview每显示一行数据,该函数就执行一次
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {// 当第一次加载ListView控件时 convertView为空
			convertView = inflater.inflate(R.layout.item_photo_album, null);// 所以当ListView控件没有滑动时都会执行这条语句
			holder = new ViewHolder();
			holder.mImageView = (ImageView) convertView
					.findViewById(R.id.child_image);
			holder.cb = (CheckBox) convertView
					.findViewById(R.id.child_checkbox);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		// 根据isSelected来设置checkbox的选中状况
		holder.cb.setId(position);// 对checkbox的id进行重新设置为当前的position
		holder.cb.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			// 把上次被选中的checkbox设为false
			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				if (isChecked) {// 实现checkbox的单选功能,同样适用于radiobutton
					index = position;
					if (temp != -1) {
						if (position == temp) {
							return;
						}
						// 找到上次点击的checkbox,并把它设置为false,对重新选择时可以将以前的关掉
						CheckBox tempCheckBox = (CheckBox) activity
								.findViewById(temp);
						if (tempCheckBox != null)
							tempCheckBox.setChecked(false);
					}
					temp = buttonView.getId();// 保存当前选中的checkbox的id值
				} else {
					listPath.clear();
					return;
				}
				listPath.clear();
				listPath.add(list.get(position));
			}
		});
		// System.out.println("temp:"+temp);
		// System.out.println("position:"+position);
		if (position == temp)// 比对position和当前的temp是否一致
		{
			holder.cb.setChecked(true);
		} else {
			holder.cb.setChecked(false);
		}

		String path = list.get(position);
		ImageLoaderUtils.initUtils().display("file://" + path,
				holder.mImageView);
		return convertView;
	}

	static class ViewHolder {
		ImageView mImageView;
		CheckBox cb;
	}

	public List<String> getSelectItems() {
		return listPath;
	}
}