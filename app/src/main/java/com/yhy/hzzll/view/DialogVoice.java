package com.yhy.hzzll.view;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.yhy.hzzll.R;

public class DialogVoice {

	private AlertDialog alertDialog;

	private ImageView chat_record;

	private TextView tv_tips;

	private TextView tv_time;

	public AlertDialog showDialog(Context context) {
		alertDialog = new AlertDialog.Builder(context).create();
		alertDialog.show();
		alertDialog.setCancelable(false);
		alertDialog.setCanceledOnTouchOutside(false);
		// 关键在下面的两行,使用window.setContentView,替换整个对话框窗口的布局
		Window window = alertDialog.getWindow();
		View view = LayoutInflater.from(context).inflate(R.layout.dialog_voice,
				null);
		chat_record = (ImageView) view.findViewById(R.id.chat_record);
		tv_tips = (TextView) view.findViewById(R.id.tv_tips);
		tv_time = (TextView) view.findViewById(R.id.tv_time);
		window.setContentView(view);
		return alertDialog;
	}

	public void dismissDialog() {
		if (alertDialog != null)
			alertDialog.dismiss();
	}

	public void setDrawable(int drawableId) {
		if (chat_record == null) {
			return;
		}
		chat_record.setImageResource(drawableId);
	}

	public void setTips(String tips) {
		if (tv_tips == null) {
			return;
		}
		tv_tips.setText(tips);
	}

	public void setTime(String time) {
		if (tv_tips == null) {
			return;
		}
		tv_time.setText(time);
	}

}
