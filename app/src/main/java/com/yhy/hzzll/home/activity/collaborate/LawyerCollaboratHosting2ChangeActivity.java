package com.yhy.hzzll.home.activity.collaborate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.yhy.hzzll.R;

import com.yhy.hzzll.mian.activity.BaseActivity;

/**
 * 全国律师协作-- 律师详情--去托管--选择托管方式
 * 
 * @author Yang
 * 
 */
public class LawyerCollaboratHosting2ChangeActivity extends BaseActivity {

	public static LawyerCollaboratHosting2ChangeActivity lawyerCollaboratHostingChangeActivity = null;

	@ViewInject(R.id.tv_money)
	private TextView tv_money;

	private String money;

	private String id;

	private String data;

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_lawyercollaborat_hosting_change2);
		super.onCreate(arg0);
		ViewUtils.inject(this);
		viewInit();
		lawyerCollaboratHostingChangeActivity = this;
	}

	private void viewInit() {
		if (null != getIntent()) {
			money = getIntent().getStringExtra("money");
			id = getIntent().getStringExtra("id");
			tv_money.setText(money);
			data = getIntent().getStringExtra("data");
		}

	}

    @OnClick({ R.id.tv_pay, R.id.tv_send })
	public void onClick(View view) {
		switch (view.getId()) {
//		case R.id.tv_pay:
//			startActivity(new Intent()
//					.putExtra(
//							"activity",
//							LawyerCollaboratHostingChangeActivity.class
//									.toString()).putExtra("price", money)
//					.putExtra("id", data).setClass(context, PayActivity.class));
//			break;
		case R.id.tv_send:
			startActivity(new Intent().putExtra("data", data).setClass(context,
					PublishCooperateSucessActivity.class));
			finish();
			break;
		}
	}

}
