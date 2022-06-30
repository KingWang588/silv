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
 * 修改安全密码
 * 
 * @author Yang
 * 
 */
public class RevisePayPasswordActivity extends BaseActivity {
	@ViewInject(R.id.et_phone)
	EditText et_phone;
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
	@ViewInject(R.id.tv_title)
	TextView tv_title;


	/**
	 * 自定义计时器
	 */
	private TimeCount timer;
	private String accessKey;
	private DataUserEntity userEntity;
	private String phone;
	private String is_safe;

	@Override
	protected void onCreate(Bundle arg0) {
		setContentView(R.layout.activity_revisepay_password);
		super.onCreate(arg0);
		ViewUtils.inject(this);
		init();
	}

	private void init() {

		if (PrefsUtils.getString(RevisePayPasswordActivity.this,PrefsUtils.ESCIT_PAYPSW).equals("0")) {
			tv_title.setText("设置安全密码");
		} else {
			tv_title.setText("修改安全密码");
		}

		et_phone.setText(PrefsUtils.getString(RevisePayPasswordActivity.this,PrefsUtils.UPHONE));
		et_phone.setFocusable(false);

	}

	@OnClick({ R.id.tv_send_verification_code, R.id.tv_submit })
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.tv_send_verification_code:
			timer = new TimeCount(60000, 1000);// 构造CountDownTimer对象
            getVerify();
			break;
		case R.id.tv_submit:
			setPayword();
		}
	}

	/**
	 * 发送验证码
	 */
	private void getVerify() {
		RequestParams params = new RequestParams();
		params.addBodyParameter("mobile", et_phone.getText().toString());
		params.addBodyParameter("type", "USER_MODIFY_PAY_PASSWORD");
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

	/** 用户中心 - 设置安全密码 */
	private void setPayword() {

		final String newPwd = et_new_pwd.getText().toString();
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
		if (!newPwd.equals(reNewPwd)) {
			ToastUtils.getUtils(getApplicationContext()).show(getString(R.string.toast_no_same_pwd));
			return;
		}
		
		if (newPwd.length() != 6) {
			ToastUtils.getUtils(getApplicationContext()).show("请设置6位纯数字密码！");
			return;
		}

		RequestParams params = new RequestParams();
		params.addHeader("Authorization", PrefsUtils.getString(getApplicationContext(), PrefsUtils.AUTHORIZATION));
		params.addBodyParameter("mobile", PrefsUtils.getString(RevisePayPasswordActivity.this,PrefsUtils.UPHONE));
		params.addBodyParameter("pay_password", newPwd);
		params.addBodyParameter("pay_password_confirmation", newPwd);
		params.addBodyParameter("validate_code", code);
		httpDataUtils.sendProgressPut(MyData.MODIFY_PAY_PSW, params);
		httpDataUtils.setSucessBack(new HttpDataUtils.SucessBack() {

			@Override
			public void sucess(ResponseInfo<String> arg0) {
				try {
					JSONObject object = new JSONObject(arg0.result);
					String code = object.optString("code");
					String msg = object.optString("msg");
					if (code.equals("0")) {
//						userEntity.setPayword(newPwd);
//						HzApplication.getInstance().getUserEntityCache().put(Constans.USER_INFO, userEntity, 86400);
						ToastUtils.getUtils(getApplicationContext()).show("设置安全密码成功。");
						PrefsUtils.saveString(RevisePayPasswordActivity.this,PrefsUtils.ESCIT_PAYPSW,"1");
						finish();
					}

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
