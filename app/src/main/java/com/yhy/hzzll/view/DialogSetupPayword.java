package com.yhy.hzzll.view;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.TextView;

import com.yhy.hzzll.R;


public class DialogSetupPayword {
	private AlertDialog alertDialog;

	private View view;

	Clicks click;

	public interface Clicks {
		void clicks(int type);
	}

	public void setClick(Clicks click) {
		this.click = click;
	}

	public AlertDialog showDialog(Context context) {
		alertDialog = new AlertDialog.Builder(context).create();
		alertDialog.show();
		alertDialog.setCancelable(true);
		alertDialog.setCanceledOnTouchOutside(true);
		// 关键在下面的两行,使用window.setContentView,替换整个对话框窗口的布局
		Window window = alertDialog.getWindow();
		view = LayoutInflater.from(context).inflate(
				R.layout.dialog_setup_payword, null);
		TextView tv_left = (TextView) view.findViewById(R.id.tv_left);
		TextView tv_right = (TextView) view.findViewById(R.id.tv_right);

		tv_left.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				if (click != null)
					click.clicks(0);
				alertDialog.dismiss();
			}
		});

		tv_right.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				if (click != null)
					click.clicks(1);
				alertDialog.dismiss();
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
