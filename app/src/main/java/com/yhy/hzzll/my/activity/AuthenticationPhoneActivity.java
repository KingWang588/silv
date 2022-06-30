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
import com.yhy.hzzll.mian.entity.DataUserEntity;
import com.yhy.hzzll.utils.PrefsUtils;

/**
 * 认证信息--手机认证
 * 
 * @author Yang
 * 
 */
public class AuthenticationPhoneActivity extends BaseActivity {
	@ViewInject(R.id.et_phone)
	private TextView et_phone;
	@ViewInject(R.id.tv_modification_phone)
	private TextView tv_modification_phone;

	private DataUserEntity userEntity;

	@Override
	protected void onCreate(Bundle arg0) {
		setContentView(R.layout.activity_authentication_phone);
		super.onCreate(arg0);
		ViewUtils.inject(this);
		init();
	}

	private void init() {

		et_phone.setText(PrefsUtils.getString(AuthenticationPhoneActivity.this,PrefsUtils.UPHONE));
	}

	@OnClick({ R.id.tv_modification_phone })
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.tv_modification_phone:
			Intent modificationPhoneIntent = new Intent(getApplicationContext(), ModificationPhoneActivity.class);
			modificationPhoneIntent.putExtra(ModificationPhoneActivity.IS_MODIFY_STATE_INTENT, true);
			modificationPhoneIntent.putExtra(ModificationPhoneActivity.OLD_PHONE_INTENT, et_phone.getText().toString());
			startActivity(modificationPhoneIntent);
			finish();
			break;
		}
	}
}
