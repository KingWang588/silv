package com.yhy.hzzll.view;

import com.yhy.hzzll.R;
import com.yhy.hzzll.utils.PrefsUtils;

import android.R.bool;
import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class UpdataVersionDialog {
	
	private AlertDialog alertDialog;

	private View view;

	public interface Click {
		void click();
	}

	boolean hide;
	Context context;
	String version;
	public AlertDialog showDialog(final Context context,final Click click ,boolean hide,final String version) {
		alertDialog = new AlertDialog.Builder(context).create();
		alertDialog.show();
		alertDialog.setCancelable(true);
		alertDialog.setCanceledOnTouchOutside(false);
		this.hide = hide;
		this.context = context;
		this.version = version;
		// 关键在下面的两行,使用window.setContentView,替换整个对话框窗口的布局
		Window window = alertDialog.getWindow();
		view = LayoutInflater.from(context).inflate(
				R.layout.dialog_updata_version, null);
		
		TextView tv_version = (TextView) view.findViewById(R.id.tv_version);
		
		tv_version.setText("发现有新的版本("+version+")，您是否更新获得更好的用户体验？");
		TextView tv_left = (TextView) view.findViewById(R.id.tv_left);
		TextView tv_right = (TextView) view.findViewById(R.id.tv_right);

		if(hide){
			tv_left.setVisibility(View.GONE);
		}
		
		tv_left.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				PrefsUtils.saveString(context, PrefsUtils.UPDATAORNOT, version);
				alertDialog.dismiss();
			}
		});

		tv_right.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (click != null)
					click.click();
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
