package com.yhy.hzzll.mian.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.yhy.hzzll.R;
import com.yhy.hzzll.utils.CodeUtils;
import com.yhy.hzzll.utils.ToastUtils;
import com.yhy.hzzll.utils.ValidationUtils;

/**
 * 忘记密码_第一步
 * 
 * 
 * @author Yang
 * 
 */
public class ForgetPasswordFirstActivity extends BaseActivity {
	@ViewInject(R.id.et_user_account)
	EditText et_user_account;
	@ViewInject(R.id.et_verification_code)
	EditText et_verification_code;
	@ViewInject(R.id.iv_verification_code)
	ImageView iv_verification_code;
	@ViewInject(R.id.tv_re_generate_code)
	TextView tv_re_generate_code;
	@ViewInject(R.id.tv_next)
	TextView tv_next;



	@Override
	protected void onCreate(Bundle arg0) {
		setContentView(R.layout.activity_forget_password_first);
		super.onCreate(arg0);
		ViewUtils.inject(this);

		if (ValidationUtils.isMobile(getIntent().getStringExtra("phone"))) {
			et_user_account.setText(getIntent().getStringExtra("phone"));
		}

		init();
	}

	private void init() {
		iv_verification_code.setImageBitmap(CodeUtils.getInstance().createBitmap());
	}

	@OnClick({ R.id.tv_next, R.id.tv_re_generate_code })
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.tv_next:
			isExistCheckPhone();
			break;
		case R.id.tv_re_generate_code:
			iv_verification_code.setImageBitmap(CodeUtils.getInstance().createBitmap());
			break;
		}

	}

	private void isExistCheckPhone() {
		final String account = et_user_account.getText().toString();
		if (TextUtils.isEmpty(account)) {
			ToastUtils.getUtils(getApplicationContext()).show(getString(R.string.toast_empty_account_phone));
			return;
		}
		if (!ValidationUtils.isMobile(account)) {
			ToastUtils.getUtils(getApplicationContext()).show(getString(R.string.toast_validation_phone));
			return;
		}
		if (!CodeUtils.getInstance().equesCode(et_verification_code.getText().toString())) {
			ToastUtils.getUtils(getApplicationContext()).show("验证码错误");
			return;
		}

		Intent intent = new Intent(ForgetPasswordFirstActivity.this,
				ForgetPasswordSecondActivity.class);
		intent.putExtra(ForgetPasswordSecondActivity.PHONE_INTENT, account);
		startActivity(intent);
		finish();

	}

}
