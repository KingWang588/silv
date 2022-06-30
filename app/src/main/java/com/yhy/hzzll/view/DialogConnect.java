package com.yhy.hzzll.view;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.TextView;

import com.yhy.hzzll.R;

/**
 * 联系他
 * 
 * @author Yang
 * 
 */
public class DialogConnect {

	private AlertDialog alertDialog;

	private TextView tv_cancel, tv_ok;

	public interface Click {
		void ok();

		void connect();
	}

	public AlertDialog showDialog(Context context, final Click click) {
		alertDialog = new AlertDialog.Builder(context).create();
		alertDialog.show();
		alertDialog.setCancelable(true);
		alertDialog.setCanceledOnTouchOutside(true);
		// 关键在下面的两行,使用window.setContentView,替换整个对话框窗口的布局
		Window window = alertDialog.getWindow();
		View view = LayoutInflater.from(context).inflate(
				R.layout.dialog_connect, null);
		tv_cancel = (TextView) view.findViewById(R.id.tv_ok);
		tv_ok = (TextView) view.findViewById(R.id.tv_connect_he);
		window.setContentView(view);

		tv_cancel.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				dismissDialog();
				if (click != null) {
					click.ok();
				}
			}
		});
		tv_ok.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (click != null) {
					click.connect();
				}
				dismissDialog();
			}
		});
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
