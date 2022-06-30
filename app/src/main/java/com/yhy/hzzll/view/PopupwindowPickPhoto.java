package com.yhy.hzzll.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.yhy.hzzll.R;

/**
 * 选择照片
 * 
 * @author Yang
 * 
 */
public class PopupwindowPickPhoto extends PopupWindow {

	private View conentView;

	private TextView tv_album;// 相册
	private TextView tv_picture;// 相机
	private TextView tv_cancel;// 取消

	public interface Click {
		void index(int index);
	}

	public PopupwindowPickPhoto(final Activity context, final Click click) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		conentView = inflater.inflate(R.layout.popupwindow_pickphoto, null);
		int width = context.getResources().getDisplayMetrics().widthPixels;
		// 设置SelectPicPopupWindow的View
		this.setContentView(conentView);
		// 设置SelectPicPopupWindow弹出窗体的宽
		this.setWidth(width);
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
		this.setAnimationStyle(R.style.AnimationPreview);

		// 设置背景颜色变暗
		WindowManager.LayoutParams lp = context.getWindow().getAttributes();
		lp.alpha = 0.9f;
		context.getWindow().setAttributes(lp);
		this.setOnDismissListener(new OnDismissListener() {
			@Override
			public void onDismiss() {
				WindowManager.LayoutParams lp = context.getWindow()
						.getAttributes();
				lp.alpha = 1f;
				context.getWindow().setAttributes(lp);
			}
		});

		tv_album = (TextView) conentView.findViewById(R.id.tv_album);
		tv_picture = (TextView) conentView.findViewById(R.id.tv_picture);
		tv_cancel = (TextView) conentView.findViewById(R.id.tv_cancel);

		tv_album.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				PopupwindowPickPhoto.this.dismiss();
				if (click != null) {
					click.index(1);
				}
			}
		});

		tv_picture.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				PopupwindowPickPhoto.this.dismiss();
				if (click != null) {
					click.index(2);
				}
			}
		});

		tv_cancel.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				PopupwindowPickPhoto.this.dismiss();
			}
		});
	}
}