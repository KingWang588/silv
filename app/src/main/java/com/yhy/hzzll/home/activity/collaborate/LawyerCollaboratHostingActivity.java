package com.yhy.hzzll.home.activity.collaborate;

import android.os.Bundle;
import android.view.View;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.yhy.hzzll.R;
import com.yhy.hzzll.mian.activity.BaseActivity;

/**
 * 全国律师协作-- 律师详情--去托管
 * 
 * @author Yang
 * 
 */
public class LawyerCollaboratHostingActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_lawyercollaborat_hosting);
		super.onCreate(arg0);
		ViewUtils.inject(this);
	}

	
	@OnClick({ R.id.tv_pay })
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.tv_pay:

			break;
		}
	}
}
