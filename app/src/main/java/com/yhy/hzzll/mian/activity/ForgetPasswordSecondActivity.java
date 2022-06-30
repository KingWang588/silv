package com.yhy.hzzll.mian.activity;

import android.content.Intent;
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
import com.yhy.hzzll.utils.HttpDataUtils;
import com.yhy.hzzll.utils.HttpDataUtils.FailBack;
import com.yhy.hzzll.utils.JSONTool;
import com.yhy.hzzll.utils.PrefsUtils;
import com.yhy.hzzll.utils.ToastUtils;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 忘记密码_第二步
 * 
 * @author Yang
 * 
 */
public class ForgetPasswordSecondActivity extends BaseActivity {
	@ViewInject(R.id.tv_phone_number)
	TextView tv_phone_number;
	@ViewInject(R.id.et_new_pwd)
	EditText et_new_pwd;
	@ViewInject(R.id.et_re_pwd)
	EditText et_re_pwd;
	@ViewInject(R.id.et_verfication_code)
	EditText et_verfication_code;
	@ViewInject(R.id.tv_submit)
	TextView tv_submit;
	@ViewInject(R.id.tv_send_verification_code)
	TextView tv_send_verification_code;

	/**
	 * 自定义计时器
	 */
	private TimeCount timer;
	private String accessKey;

	public static final String PHONE_INTENT = "PHONE_INTENT";
	private String phoneNumber;

	@Override
	protected void onCreate(Bundle arg0) {
		setContentView(R.layout.activity_forget_pwd_second);
		super.onCreate(arg0);
		ViewUtils.inject(this);
		init();
	}

	private void init() {

		accessKey = PrefsUtils.getString(getApplicationContext(), PrefsUtils.ACCESSKEY);
		phoneNumber = getIntent().getStringExtra(PHONE_INTENT);
		tv_phone_number.setText(phoneNumber);
	}

	@OnClick({ R.id.tv_send_verification_code, R.id.tv_next })
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.tv_send_verification_code:
			timer = new TimeCount(60000, 1000);// 构造CountDownTimer对象
			getVerify();
			break;
		case R.id.tv_next:
			setNewPassword();
		}
	}


	private void getVerify() {
		RequestParams params = new RequestParams();
		params.addBodyParameter("mobile", tv_phone_number.getText().toString());
		params.addBodyParameter("type", "USER_FORGOT_PASSWORD");
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





	/**
	 * 获取修改验证码
	 */
	private void getCertificationSmsCode(String phone) {
		RequestParams params = new RequestParams();
		params.addHeader("Authorization", accessKey);
		params.addBodyParameter("mobile", phone);
		httpDataUtils.sendProgressPost(MyData.GET_REPWD_MOBILE_CODE, params);
		httpDataUtils.setSucessBack(new HttpDataUtils.SucessBack() {

			@Override
			public void sucess(ResponseInfo<String> arg0) {
				try {
					JSONObject object = new JSONObject(arg0.result);
					String code = JSONTool.getString(object, "code");
					String msg = JSONTool.getString(object, "msg");
					if (code.equals("000000")) { // 成功
						timer.start();// 开始计时
						tv_send_verification_code.setEnabled(false);
					} else {
						timer.cancel();//
						tv_send_verification_code.setEnabled(true);
					}
					ToastUtils.getUtils(getApplicationContext()).show(msg);
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		});
		httpDataUtils.setFailBack(new FailBack() {

			@Override
			public void fail(String msg) {
				timer.cancel();//
				tv_send_verification_code.setEnabled(true);
			}
		});
	}

	/** 用户中心 - 检测验证码是否正确并设置验证码*/
	private void setNewPassword() {
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
		String code = et_verfication_code.getText().toString();
		if (TextUtils.isEmpty(code)) {
			ToastUtils.getUtils(getApplicationContext()).show(getString(R.string.toast_empty_sms));
			return;
		}

		if (newPwd.length()<6||newPwd.length()>12){
			ToastUtils.getUtils(getApplicationContext()).show("请设置6-12位密码");
			return;
		}

		if (!newPwd.equals(reNewPwd)) {
			ToastUtils.getUtils(getApplicationContext()).show(getString(R.string.toast_no_same_pwd));
			return;
		}
		RequestParams params = new RequestParams();

		params.addBodyParameter("mobile", phoneNumber);
		params.addBodyParameter("password", newPwd);
		params.addBodyParameter("confirm_password", newPwd);
		params.addBodyParameter("type", "0");
 		params.addBodyParameter("code", code);
		httpDataUtils.sendProgressPut(MyData.FORGET_PSW, params);
		httpDataUtils.setSucessBack(new HttpDataUtils.SucessBack() {

			@Override
			public void sucess(ResponseInfo<String> arg0) {
				try {
					JSONObject object = new JSONObject(arg0.result);
					String code = object.optString("code");
					String msg = object.optString("message");
					if (code.equals("0")) {
						Intent intent=new Intent(ForgetPasswordSecondActivity.this, LoginActivity.class);
						startActivity(intent);
						finish();
					}
					ToastUtils.getUtils(getApplicationContext()).show(msg);
				} catch (JSONException e) {
					e.printStackTrace();
				}

			}
		});
	}

	/**
	 * 
	 * TODO(这里用一句话描述这个类的作用)倒数计时器
	 * 
	 * @author zcm 2015-6-29 下午4:57:27
	 */
	class TimeCount extends CountDownTimer {
		public TimeCount(long millisInFuture, long countDownInterval) {
			/*
			 * 参数依次为总时长,和计时的时间间隔
			 */
			super(millisInFuture, countDownInterval);
		}

		@Override
		public void onFinish() {
			/*
			 * 计时完毕时触发
			 */
			tv_send_verification_code.setText(getString(R.string.btn_send_verfication_no_time_code_txt));
			// tv_send_verification_code.setBackgroundResource(R.drawable.login_subimt_button);
			tv_send_verification_code.setEnabled(true);
		}

		@Override
		public void onTick(long millisUntilFinished) {
			/*
			 * 计时过程显示
			 */
			tv_send_verification_code.setEnabled(false);
			tv_send_verification_code
					.setText(getString(R.string.btn_send_verfication_code_txt, millisUntilFinished / 1000));
		}
	}
}
