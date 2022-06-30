package com.yhy.hzzll.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
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

public class PhotoAlbumAdapter extends BaseAdapter {

	/**
	 * 用来存储图片的选中情况
	 */
	private HashMap<Integer, Boolean> mSelectMap = new HashMap<Integer, Boolean>();
	private List<String> list;

	protected LayoutInflater mInflater;

	public PhotoAlbumAdapter(Context context, List<String> list) {
		this.list = list;
		mInflater = LayoutInflater.from(context);
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
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		final ViewHolder viewHolder;
		String path = list.get(position);
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.item_photo_album, null);
			viewHolder = new ViewHolder();
			viewHolder.mImageView = (ImageView) convertView
					.findViewById(R.id.child_image);
			viewHolder.mCheckBox = (CheckBox) convertView
					.findViewById(R.id.child_checkbox);

			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		viewHolder.mCheckBox
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {
					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						// 如果是未选中的CheckBox,则添加动画
						if (!mSelectMap.containsKey(position)
								|| !mSelectMap.get(position)) {
							addAnimation(viewHolder.mCheckBox);
						}
						mSelectMap.put(position, isChecked);
					}
				});

		viewHolder.mCheckBox
				.setChecked(mSelectMap.containsKey(position) ? mSelectMap
						.get(position) : false);

		ImageLoaderUtils.initUtils().display("file://" + path,
				viewHolder.mImageView);

		return convertView;
	}

	/**
	 * 给CheckBox加点击动画
	 * 
	 * @param view
	 */
	private void addAnimation(View view) {
		float[] vaules = new float[] { 0.5f, 0.6f, 0.7f, 0.8f, 0.9f, 1.0f,
				1.1f, 1.2f, 1.3f, 1.25f, 1.2f, 1.15f, 1.1f, 1.0f };
		AnimatorSet set = new AnimatorSet();
		set.playTogether(ObjectAnimator.ofFloat(view, "scaleX", vaules),
				ObjectAnimator.ofFloat(view, "scaleY", vaules));
		set.setDuration(150);
		set.start();
	}

	/**
	 * 获取选中的Item的position
	 * 
	 * @return list
	 */
	public List<String> getSelectItems() {
		List<String> list = new ArrayList<String>();
		for (Iterator<Map.Entry<Integer, Boolean>> it = mSelectMap.entrySet()
				.iterator(); it.hasNext();) {
			Map.Entry<Integer, Boolean> entry = it.next();
			if (entry.getValue()) {
				list.add(this.list.get(entry.getKey()));
			}
		}
		return list;
	}

	private static class ViewHolder {
		ImageView mImageView;
		CheckBox mCheckBox;
	}

}
