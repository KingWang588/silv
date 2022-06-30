package com.yhy.hzzll.my.activity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.yhy.hzzll.R;
import com.yhy.hzzll.framework.MyData;
import com.yhy.hzzll.mian.activity.BaseActivity;
import com.yhy.hzzll.mian.entity.DataUserEntity;
import com.yhy.hzzll.utils.HttpDataUtils;
import com.yhy.hzzll.utils.PrefsUtils;
import com.yhy.hzzll.utils.ToastUtils;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 修改登录密码
 * 
 * @author Yang
 * 
 */
public class RevisePasswordActivity extends BaseActivity {

	@ViewInject(R.id.et_new_pwd)
	EditText et_new_pwd;
	@ViewInject(R.id.et_re_pwd)
	EditText et_re_pwd;
	@ViewInject(R.id.tv_phone_number)
	TextView tv_phone_number;
	@ViewInject(R.id.tv_submit)
	TextView tv_submit;

	@ViewInject(R.id.tv_send_verification_code)
	TextView tv_send_verification_code;

	@ViewInject(R.id.et_verfication_code)
	EditText et_verfication_code;

	/**
	 * 自定义计时器
	 */

	private Timer timer;
	private String accessKey;
	private DataUserEntity userEntity;
	private String phone;
	private String is_safe;

	@Override
	protected void onCreate(Bundle arg0) {
		setContentView(R.layout.activity_revise_password);
		super.onCreate(arg0);
		ViewUtils.inject(this);
		init();
	}

	private void init() {
		tv_phone_number.setText(PrefsUtils.getString(RevisePasswordActivity.this,PrefsUtils.UPHONE));
	}

	@OnClick({ R.id.tv_submit,R.id.tv_send_verification_code })
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.tv_submit:
			setPwdword();
			break;
			case R.id.tv_send_verification_code:
				timer = new Timer(60000, 1000);
				getVerify();
				break;
		}
	}

	//修改密码

	private void setPwdword() {

		String account = tv_phone_number.getText().toString();
		if (TextUtils.isEmpty(account)) {
			ToastUtils.getUtils(getApplicationContext()).show(getString(R.string.toast_empty_account));
			return;
		}

		String newPwd = et_new_pwd.getText().toString();
		if (TextUtils.isEmpty(newPwd)) {
			ToastUtils.getUtils(getApplicationContext()).show(getString(R.string.toast_empty_new_pwd));
			return;
		}
		String reNewPwd = et_re_pwd.getText().toString();
		if (TextUtils.isEmpty(reNewPwd)) {
			ToastUtils.getUtils(getApplicationContext()).show(getString(R.string.toast_empty_re_pwd));
			return;
		}
		if (!newPwd.equals(reNewPwd)) {
			ToastUtils.getUtils(getApplicationContext()).show(getString(R.string.toast_no_same_pwd));
			return;
		}

		if (newPwd.length() < 6 || newPwd.length() > 12) {
			ToastUtils.getUtils(getApplicationContext()).show("新密码的长度应在6至12位之间");
			return;
		}

		if (TextUtils.isEmpty(et_verfication_code.getText().toString())) {
			ToastUtils.getUtils(getApplicationContext()).show("请输入验证码");
			return;
		}

		RequestParams params = new RequestParams();
		params.addHeader("Authorization", PrefsUtils.getString(getApplicationContext(), PrefsUtils.AUTHORIZATION));
		params.addBodyParameter("mobile", tv_phone_number.getText().toString());
		params.addBodyParameter("password", newPwd);
		params.addBodyParameter("password_confirmation", newPwd);
		params.addBodyParameter("validate_code", et_verfication_code.getText().toString());
		httpDataUtils.sendProgressPut(MyData.MODIFY_PSW, params);
		httpDataUtils.setSucessBack(new HttpDataUtils.SucessBack() {

			@Override
			public void sucess(ResponseInfo<String> arg0) {
				try {
					JSONObject object = new JSONObject(arg0.result);
					String code = object.optString("code");
					String msg = object.optString("msg");
					if (code.equals("0")) {
						finish();
					}
					ToastUtils.getUtils(getApplicationContext()).show("修改登录密码成功");
				} catch (JSONException e) {
					e.printStackTrace();
				}

			}
		});
	}

	/**
	 * 发送验证码
	 */
	private void getVerify() {
		RequestParams params = new RequestParams();
		params.addBodyParameter("mobile", tv_phone_number.getText().toString());
		params.addBodyParameter("type", "USER_MODIFY_PASSWORD");
		httpDataUtils.sendProgressPost(MyData.GETREGISTERSMSCODE, params);
		httpDataUtils.setSucessBack(new HttpDataUtils.SucessBack() {
			@Override
			public void sucess(ResponseInfo<String> arg0) {
				if (httpDataUtils.code(arg0.result)) {
					tv_send_verification_code.setEnabled(false);
					timer.start();
				} else {
					httpDataUtils.showMsgNew(arg0.result);
				}
			}
		});
	}

	private class Timer extends CountDownTimer {

		public Timer(long millisInFuture, long countDownInterval) {
			super(millisInFuture, countDownInterval);
		}

		@Override
		public void onTick(long millisUntilFinished) {
			tv_send_verification_code.setText((millisUntilFinished / 1000) + "秒后可重发");
		}

		@Override
		public void onFinish() {
			tv_send_verification_code.setEnabled(true);
			tv_send_verification_code.setText("重新获取");
		}
	}

}
