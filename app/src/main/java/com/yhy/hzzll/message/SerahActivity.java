package com.yhy.hzzll.message;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.yhy.hzzll.R;
import com.yhy.hzzll.mian.activity.BaseActivity;
import com.yhy.hzzll.view.SerachDialog;
import com.yhy.hzzll.view.SerachDialog.Onclick;

public class SerahActivity extends BaseActivity {

	@ViewInject(R.id.tv_clear)
	private TextView tv_clear;

	private SerachDialog dialog;

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_serch);
		super.onCreate(arg0);
		ViewUtils.inject(this);
	}

	@OnClick({ R.id.tv_clear, R.id.tv_back })
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.tv_back:
			finish();
			break;
		case R.id.tv_clear:
			dialog = new SerachDialog(this, new Onclick() {
				@Override
				public void enter() {
					dialog.dismiss();
				}
			});
			break;
		}
	}
}
