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
 * 认证信息--邮箱认证
 * 
 * @author Yang
 * 
 */
public class AuthenticationEmailActivity extends BaseActivity {

	public static AuthenticationEmailActivity authenticationEmailActivity = null;

	@ViewInject(R.id.tv_email)
	private TextView tv_email;

	private DataUserEntity userEntity;

	@Override
	protected void onCreate(Bundle arg0) {
		setContentView(R.layout.activity_authentication_email);
		super.onCreate(arg0);
		authenticationEmailActivity = this;
		ViewUtils.inject(this);
		init();
	}

	private void init() {
		tv_email.setText( PrefsUtils.getString(AuthenticationEmailActivity.this,PrefsUtils.EMAIl));
	}

	@OnClick({ R.id.tv_modification_email })
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.tv_modification_email:
			Intent intent = new Intent(getApplicationContext(), ModificationEmailActivity.class);
			intent.putExtra(ModificationEmailActivity.IS_MODIFY_STATE_INTENT, true);
			intent.putExtra(ModificationEmailActivity.OLD_EMAIL_INTENT, PrefsUtils.getString(AuthenticationEmailActivity.this,PrefsUtils.EMAIl));
			startActivity(intent);
			finish();
			break;
		}
	}
}
