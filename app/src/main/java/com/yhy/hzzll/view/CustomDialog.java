package com.yhy.hzzll.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.View;
import android.view.Window;

/**
 * 解答疑dialog
 * 
 * @author Yang
 * 
 */
public class CustomDialog {

	private AlertDialog alertDialog;

	public void showDialog(Activity activity, View view) {
		alertDialog = new AlertDialog.Builder(activity).create();
		alertDialog.show();
		Window window = alertDialog.getWindow();
		window.setContentView(view);
	}

	public void dismiss() {
		if (alertDialog.isShowing())
			alertDialog.dismiss();
	}
}
