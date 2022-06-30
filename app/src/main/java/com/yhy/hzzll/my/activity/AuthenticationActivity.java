package com.yhy.hzzll.my.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.yhy.hzzll.R;
import com.yhy.hzzll.mian.activity.BaseActivity;
import com.yhy.hzzll.mian.entity.DataUserEntity;

/**
 * 设置--认证信息
 * 
 * @author Yang
 * 
 */
public class AuthenticationActivity extends BaseActivity {
	private DataUserEntity userEntity;
	private int authenticateUserState;
	private boolean isAuthenticatePhone;
	private boolean isAuthenticateEmail;

	@Override
	protected void onCreate(Bundle arg0) {
		setContentView(R.layout.activity_authentication);
		super.onCreate(arg0);
		ViewUtils.inject(this);
		init();
	}

	private void init() {
//		userEntity = (DataUserEntity) HzApplication.getInstance().getUserEntityCache().getAsObject(Constans.USER_INFO);
//		if (userEntity == null) {
//			startActivity(new Intent().setClass(getApplicationContext(), LoginActivity.class));
//			finish();
//			return;
//		}
//		if (userEntity.getVtruename().equals(Constans.CHECK_PENDING)) {
//			authenticateUserState = -1;
//		} else if (userEntity.getVtruename().equals(Constans.UNAUTHERIZED)) {
//			authenticateUserState = 0;
//		} else {
//			authenticateUserState = 1;
//		}
//        isAuthenticatePhone = !(userEntity.getVmobile().equals(Constans.CHECK_PENDING)
//                || userEntity.getVmobile().equals(Constans.UNAUTHERIZED));
//        isAuthenticateEmail = !(userEntity.getVemail().equals(Constans.CHECK_PENDING)
//                || userEntity.getVemail().equals(Constans.UNAUTHERIZED));
	}

	@OnClick({ R.id.rl_realnames, R.id.rl_phonenames, R.id.rl_emailnames })
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.rl_realnames:
			Intent modificationUserIntent = new Intent(AuthenticationActivity.this, AuthenticatRealLawActivity.class);
			if (authenticateUserState == 1) {// 通过认证
				modificationUserIntent.putExtra(AuthenticatRealLawActivity.AUTHENTICATE_USER_STATE_INTENT, 1);
			} else if (authenticateUserState == 0) { // 没有认证
				modificationUserIntent.putExtra(AuthenticatRealLawActivity.AUTHENTICATE_USER_STATE_INTENT, 0);
			} else { // 待审核 -1
				modificationUserIntent.putExtra(AuthenticatRealLawActivity.AUTHENTICATE_USER_STATE_INTENT, -1);
			}
			startActivity(modificationUserIntent);
			break;
		case R.id.rl_phonenames:
			if (isAuthenticatePhone) {// 已经认证跳转到修改
				Intent authenticationPhoneIntent = new Intent(AuthenticationActivity.this,
						AuthenticationPhoneActivity.class);
				startActivity(authenticationPhoneIntent);
			} else { // 没有认证
				Intent modificationPhoneIntent = new Intent(AuthenticationActivity.this,
						ModificationPhoneActivity.class);
				modificationPhoneIntent.putExtra(ModificationPhoneActivity.IS_MODIFY_STATE_INTENT, false);
				startActivity(modificationPhoneIntent);
			}
			break;
		case R.id.rl_emailnames:
			if (isAuthenticateEmail) { // 如果邮箱已经认证
				Intent authenticationEmailIntent = new Intent(AuthenticationActivity.this,
						AuthenticationEmailActivity.class);
				startActivity(authenticationEmailIntent);
			} else { // 没有认证
				Intent modificationEmailintent = new Intent(AuthenticationActivity.this,
						ModificationEmailActivity.class);
				modificationEmailintent.putExtra(ModificationEmailActivity.IS_MODIFY_STATE_INTENT, false);
				startActivity(modificationEmailintent);
			}
			break;
		}
	}
}
