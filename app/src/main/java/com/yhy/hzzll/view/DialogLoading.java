package com.yhy.hzzll.view;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.yhy.hzzll.R;

public class DialogLoading {

	private AlertDialog alertDialog;

	private TextView tv_remind;

	public AlertDialog showDialog(Context context) {
		alertDialog = new AlertDialog.Builder(context).create();
		alertDialog.show();
		alertDialog.setCancelable(false);
		alertDialog.setCanceledOnTouchOutside(false);
		// 关键在下面的两行,使用window.setContentView,替换整个对话框窗口的布局
		Window window = alertDialog.getWindow();
		View view = LayoutInflater.from(context).inflate(
				R.layout.dialog_loading, null);
		tv_remind = (TextView) view.findViewById(R.id.tv_remind);
		window.setContentView(view);
		return alertDialog;
	}

	public void setContent(String text) {
		if (tv_remind != null)
			tv_remind.setText(text);
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
