package com.yhy.hzzll.view;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;

import com.yhy.hzzll.R;
import com.yhy.hzzll.interfacelib.Connect;
import com.yhy.hzzll.utils.ToastUtils;

/**
 * 律师协作详情--申请放弃协作
 * 
 * @author Yang
 * 
 */
public class DialogCooperateApplyCollaboration {

	private AlertDialog alertDialog;

	private View view;

	private EditText ed_remark;

	private TextView tv_right;

	private TextView tv_left;

	public AlertDialog showDialog(final Context context, final Connect connect) {
		alertDialog = new AlertDialog.Builder(context).create();
		alertDialog.show();
		alertDialog.setCancelable(true);
		alertDialog.setCanceledOnTouchOutside(true);
		alertDialog.getWindow().clearFlags(
				WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
		// 关键在下面的两行,使用window.setContentView,替换整个对话框窗口的布局
		Window window = alertDialog.getWindow();
		view = LayoutInflater.from(context).inflate(
				R.layout.dialog_cooperate_applygiveup, null);
		tv_left = (TextView) view.findViewById(R.id.tv_left);
		tv_right = (TextView) view.findViewById(R.id.tv_right);
		ed_remark = (EditText) view.findViewById(R.id.ed_remark);
		tv_left.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				if (alertDialog != null && alertDialog.isShowing()) {
					alertDialog.dismiss();
				}
			}
		});
		tv_right.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				if (!ed_remark.getText().toString().trim().isEmpty()) {
					if (connect != null)
						connect.text(ed_remark.getText().toString());

					if (alertDialog != null && alertDialog.isShowing()) {
//						alertDialog.dismiss();
					}
				} else {
					ToastUtils.getUtils(context).show("请填写放弃理由");
				}
			}
		});
		window.setContentView(view);
		return alertDialog;
	}

	public boolean isShow() {
		if (alertDialog != null) {
			if (alertDialog.isShowing()) {
				return true;
			}
		}
		return false;
	}

	public void dismissDialog() {
		if (alertDialog != null)
			alertDialog.dismiss();
	}
}
