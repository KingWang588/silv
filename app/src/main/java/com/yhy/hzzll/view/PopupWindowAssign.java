package com.yhy.hzzll.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.yhy.hzzll.R;

public class PopupWindowAssign extends PopupWindow {

	public interface onClick {
		void check(int index);
	}

	private onClick click;

	private View conentView;

	public PopupWindowAssign(final Activity context, onClick click) {
		this.click = click;
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		conentView = inflater.inflate(R.layout.popup_assign, null);
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
		tv_title0 = (TextView) conentView.findViewById(R.id.tv_title0);
		tv_title1 = (TextView) conentView.findViewById(R.id.tv_title1);
		tv_title2 = (TextView) conentView.findViewById(R.id.tv_title2);
		tv_title3 = (TextView) conentView.findViewById(R.id.tv_title3);
		tv_title4 = (TextView) conentView.findViewById(R.id.tv_title4);

		tv_title0.setOnClickListener(listener);
		tv_title1.setOnClickListener(listener);
		tv_title2.setOnClickListener(listener);
		tv_title3.setOnClickListener(listener);
		tv_title4.setOnClickListener(listener);
		setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
	}

	private OnClickListener listener = new OnClickListener() {
		@Override
		public void onClick(View view) {
			switch (view.getId()) {
			case R.id.tv_title0:
				click.check(0);
				break;
			case R.id.tv_title1:
				click.check(1);
				break;
			case R.id.tv_title2:
				click.check(2);
				break;
			case R.id.tv_title3:
				click.check(3);
				break;
			case R.id.tv_title4:
				click.check(4);
				break;
			}
		}
	};

	private TextView tv_title0;

	private TextView tv_title1;

	private TextView tv_title2;

	private TextView tv_title3;

	private TextView tv_title4;

	/**
	 * 设置list
	 * 
	 * @param index
	 */
	public void setList(int index) {
		switch (index) {
		case 0:
			tv_title0.setVisibility(View.GONE);
			tv_title1.setVisibility(View.VISIBLE);
			tv_title2.setVisibility(View.VISIBLE);
			tv_title3.setVisibility(View.VISIBLE);
			tv_title4.setVisibility(View.VISIBLE);
			break;
		case 1:
			break;
		case 2:
		case 3:
		case 4:
		}
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
