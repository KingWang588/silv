package com.yhy.hzzll.view;

import android.app.AlertDialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import com.yhy.hzzll.R;

/**
 * 律师协作详情--评论内容
 * 
 * @author Yang
 * 
 */
public class DialogPushComment {

	private AlertDialog alertDialog;

	private View view;

	private EditText et_money;

	private Conts cont;

	private String money;

	public interface Conts {
		void Money(String money);
	}

	public void setCont(Conts cont) {
		this.cont = cont;
	}

	public AlertDialog showDialog(Context context) {
		alertDialog = new AlertDialog.Builder(context).create();
		alertDialog.show();
		alertDialog.setCancelable(true);
		alertDialog.setCanceledOnTouchOutside(true);
		alertDialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
		// 关键在下面的两行,使用window.setContentView,替换整个对话框窗口的布局
		Window window = alertDialog.getWindow();
		view = LayoutInflater.from(context).inflate(R.layout.dialog_push_comment, null);
		window.setContentView(view);

		et_money = (EditText) view.findViewById(R.id.et_moneys);
		TextView tv_left = (TextView) view.findViewById(R.id.tv_left);
		TextView tv_right = (TextView) view.findViewById(R.id.tv_right);

		money = et_money.getText().toString();

		Log.e("============et_money", money);
		tv_left.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				alertDialog.dismiss();
			}
		});

		tv_right.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (cont != null)
					cont.Money(et_money.getText().toString());
				alertDialog.dismiss();
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
