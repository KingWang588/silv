package com.yhy.hzzll.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.TextView;

import com.yhy.hzzll.R;

/**
 * 搜索页面的dialog
 * 
 * @author Yang
 * 
 */
public class SerachDialog {

	private Activity activity;

	private Onclick onclick;

	private AlertDialog alertDialog;

	private TextView tv_title, tv_content, tv_cancel, tv_enter;

	public SerachDialog(Activity activity, Onclick onclick) {
		// TODO Auto-generated constructor stub
		this.activity = activity;
		this.onclick = onclick;
		showDialog();

	}

	public interface Onclick {
		void enter();
	}

	private void showDialog() {
		alertDialog = new AlertDialog.Builder(activity).create();
		alertDialog.show();
		Window window = alertDialog.getWindow();
		View view = activity.getLayoutInflater().inflate(
				R.layout.dialog_serach, null);
		tv_title = (TextView) view.findViewById(R.id.tv_title);
		tv_content = (TextView) view.findViewById(R.id.tv_content);
		tv_cancel = (TextView) view.findViewById(R.id.tv_cancel);
		tv_enter = (TextView) view.findViewById(R.id.tv_enter);

		tv_cancel.setOnClickListener(listener);
		tv_enter.setOnClickListener(listener);
		window.setContentView(view);
	}

	private OnClickListener listener = new OnClickListener() {
		@Override
		public void onClick(View view) {
			switch (view.getId()) {
			case R.id.tv_cancel:
				dismiss();
				break;
			case R.id.tv_enter:
				onclick.enter();
				break;
			}
		}
	};

	public void setTitle(String title) {

	}

	public void dismiss() {
		if (alertDialog.isShowing())
			alertDialog.dismiss();
	}

}
