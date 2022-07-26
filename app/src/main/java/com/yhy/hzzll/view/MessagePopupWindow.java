package com.yhy.hzzll.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.PopupWindow;

import com.yhy.hzzll.R;

public class MessagePopupWindow extends PopupWindow {
	private View conentView;

	public MessagePopupWindow(final Activity context) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		conentView = inflater.inflate(R.layout.popup_message, null);
		int height = context.getResources().getDisplayMetrics().heightPixels;
		int width = context.getResources().getDisplayMetrics().widthPixels;
		// 设置SelectPicPopupWindow的View
		this.setContentView(conentView);
		// 设置SelectPicPopupWindow弹出窗体的宽
		this.setWidth(LayoutParams.WRAP_CONTENT);
		// 设置SelectPicPopupWindow弹出窗体的高
		this.setHeight(LayoutParams.WRAP_CONTENT);
		// 设置SelectPicPopupWindow弹出窗体可点击
		this.setFocusable(true);
		this.setOutsideTouchable(true);
		// 刷新状态
		this.update();
		// 实例化一个ColorDrawable颜色为半透明
		ColorDrawable dw = new ColorDrawable(0000000000);
		// 点back键和其他地方使其消失,设置了这个才能触发OnDismisslistener ，设置其他控件变化等操作
		this.setBackgroundDrawable(dw);
		// mPopupWindow.setAnimationStyle(android.R.style.Animation_Dialog);
		// 设置SelectPicPopupWindow弹出窗体动画效果
//		this.setAnimationStyle(R.style.AnimationPreview);
		// LinearLayout addTaskLayout = (LinearLayout) conentView
		// .findViewById(R.id.add_task_layout);
		// LinearLayout teamMemberLayout = (LinearLayout) conentView
		// .findViewById(R.id.team_member_layout);
		// addTaskLayout.setOnClickListener(new OnClickListener() {
		//
		// @Override
		// public void onClick(View arg0) {
		// SharePopupWindow.this.dismiss();
		// }
		// });
		//
		// teamMemberLayout.setOnClickListener(new OnClickListener() {
		//
		// @Override
		// public void onClick(View v) {
		// SharePopupWindow.this.dismiss();
		// }
		// });
	}

	/**
	 * 显示popupWindow
	 * 
	 * @param parent
	 */
	public void showPopupWindow(View parent) {
		if (!this.isShowing()) {
			// 以下拉方式显示popupwindow
			this.showAtLocation(parent, Gravity.BOTTOM, 0, 0);
		} else {
			this.dismiss();
		}
	}
}
