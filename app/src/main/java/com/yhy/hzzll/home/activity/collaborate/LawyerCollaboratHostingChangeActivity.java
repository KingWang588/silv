package com.yhy.hzzll.home.activity.collaborate;

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
public class LawyerCollaboratHostingChangeActivity extends BaseActivity {

	public static LawyerCollaboratHostingChangeActivity lawyerCollaboratHostingChangeActivity = null;
	
	@ViewInject(R.id.tv_money)
	private TextView tv_money;

	private String money;

	private String id;

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_lawyercollaborat_hosting_change);
		super.onCreate(arg0);
		ViewUtils.inject(this);
		viewInit();
		lawyerCollaboratHostingChangeActivity=this;
	}

	private void viewInit() {
		if (null != getIntent()) {
			money = getIntent().getStringExtra("money");
			id = getIntent().getStringExtra("id");
			tv_money.setText(money);
		}

	}

    @OnClick({ R.id.tv_pay, R.id.tv_send })
	public void onClick(View view) {
		switch (view.getId()) {
//		case R.id.tv_pay:
//			startActivity(new Intent()
//					.putExtra("activity",
//							LawyerCollaboratHostingChangeActivity.class.toString())
//					.putExtra("price", money).putExtra("id", id)
//					.setClass(context, PayActivity.class));
//			break;
		case R.id.tv_send:
			finish();
			break;
		}
	}

}
