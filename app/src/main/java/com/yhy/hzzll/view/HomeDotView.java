package com.yhy.hzzll.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.yhy.hzzll.R;

/**
 * 主页加载多页数据时候显示的dot
 * 
 * @author wangyang
 * 
 */
public class HomeDotView {

	private final Context context;
	private int num;

	public HomeDotView(Context context) {
		this.context = context;
	}

	public void setDotView(LinearLayout layout, int num, int tag) {
		this.num = num;
		layout.removeAllViews();
		if (num != 0 && num > 1) {// 无数据 或者数据小于一页时不显示标记
			for (int i = 0; i < num; i++) {
				View view = LayoutInflater.from(context).inflate(
						R.layout.dot_tab_layout, null);
				ImageView imageView = (ImageView) view
						.findViewById(R.id.dot_view);
				view.setPadding(7, 7, 7, 7);
				imageView.setTag(i);
				if (tag == i) {
					imageView.setSelected(true);
				} else {
					imageView.setSelected(false);
				}
				layout.addView(view);
			}
		} else {
		}
	}

	public int getNum() {
		return num;
	}

}
