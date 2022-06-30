package com.yhy.hzzll.my.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.yhy.hzzll.R;
import com.yhy.hzzll.mian.activity.BaseActivity;
import com.yhy.hzzll.mian.activity.LoginActivity;
import com.yhy.hzzll.mian.entity.DataUserEntity;
import com.yhy.hzzll.framework.Constans;
import com.yhy.hzzll.framework.HzApplication;

/**
 * 账号与安全
 * 
 * @author Yang
 * 
 */
public class AccountSafeActivity extends BaseActivity {
	DataUserEntity userEntity;

	@ViewInject(R.id.tv_set_payword)
	TextView tv_set_payword;

	@Override
	protected void onCreate(Bundle arg0) {
		setContentView(R.layout.activity_accountsafe);
		super.onCreate(arg0);
		ViewUtils.inject(this);
		init();
	}

	private void init() {
		userEntity = (DataUserEntity) HzApplication.getInstance().getUserEntityCache().getAsObject(Constans.USER_INFO);
		if (userEntity == null) {
			startActivity(new Intent().setClass(getApplicationContext(), LoginActivity.class));
			finish();
			return;
		}
//		if (null == userEntity.getPayword() || "".equals(userEntity.getPayword())) {
//			tv_set_payword.setText("设置安全密码");
//		} else {
//			tv_set_payword.setText("修改安全密码");
//		}
	}

	@OnClick({ R.id.rl_revise_password, R.id.rl_revisepay_password })
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.rl_revise_password:
			startActivity(new Intent().setClass(context, RevisePasswordActivity.class));
			break;
		case R.id.rl_revisepay_password:
//			if (null == userEntity.getPayword() || "".equals(userEntity.getPayword())) {
//				startActivity(new Intent().setClass(context, SetupPayPasswordActivity.class));
//			} else {
//				startActivity(new Intent().setClass(context, RevisePayPasswordActivity.class));
//			}
			break;
		}
	}
}
