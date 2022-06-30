package com.yhy.hzzll.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.TextView;

import com.yhy.hzzll.R;

/**
 * 案件管理dialog
 * 
 * @author Yang
 * 
 */
public class CaseManageDialog {
	private Activity activity;

	private OnDelete delete;

	private AlertDialog alertDialog;

	/** 标题 */
	private TextView tv_title;
	/** 内容 */
	private TextView tv_content;
	/** 删除 */
	private TextView tv_delete;
	/** 取消 */
	private TextView tv_cancel;

	public CaseManageDialog(Activity activity) {
		// TODO Auto-generated constructor stub
		this.activity = activity;

	}

	public interface OnDelete {
		void enter();
	}

	public void setOndelete(OnDelete delete) {
		this.delete = delete;
	}

	public void showDialog() {
		alertDialog = new AlertDialog.Builder(activity).create();
		alertDialog.show();
		Window window = alertDialog.getWindow();
		View view = activity.getLayoutInflater().inflate(
				R.layout.dialog_casemanage, null);

		tv_title = (TextView) view.findViewById(R.id.tv_title);
		tv_content = (TextView) view.findViewById(R.id.tv_content);
		tv_delete = (TextView) view.findViewById(R.id.tv_delete);
		tv_cancel = (TextView) view.findViewById(R.id.tv_cancel);

		tv_cancel.setOnClickListener(listener);
		tv_delete.setOnClickListener(listener);
		window.setContentView(view);
	}

	private OnClickListener listener = new OnClickListener() {

		@Override
		public void onClick(View view) {
			switch (view.getId()) {
			case R.id.tv_cancel:
				dismiss();
				break;
			case R.id.tv_delete:
				if (delete != null)
					delete.enter();
				break;
			}
		}
	};

	public void setTitle(String title) {
		if (tv_title != null)
			tv_title.setText(title);
	}

	public void setContent(String content) {
		if (tv_content != null)
			tv_content.setText(content);
	}

	public void dismiss() {
		if (alertDialog.isShowing())
			alertDialog.dismiss();
	}
}
