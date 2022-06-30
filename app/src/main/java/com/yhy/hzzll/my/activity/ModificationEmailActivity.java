package com.yhy.hzzll.my.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.yhy.hzzll.R;
import com.yhy.hzzll.mian.activity.BaseActivity;
import com.yhy.hzzll.mian.entity.DataUserEntity;
import com.yhy.hzzll.framework.Constans;
import com.yhy.hzzll.framework.HzApplication;
import com.yhy.hzzll.framework.MyData;
import com.yhy.hzzll.utils.HttpDataUtils;
import com.yhy.hzzll.utils.HttpDataUtils.FailBack;
import com.yhy.hzzll.utils.JSONTool;
import com.yhy.hzzll.utils.PrefsUtils;
import com.yhy.hzzll.utils.RegularUtils;
import com.yhy.hzzll.utils.ToastUtils;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 认证信息--修改邮箱
 * 
 * @author Yang
 * 
 */
public class ModificationEmailActivity extends BaseActivity {
	@ViewInject(R.id.tv_email)
	TextView tv_email;
	@ViewInject(R.id.et_pwd)
	EditText et_pwd;
	@ViewInject(R.id.et_new_email)
	EditText et_new_email;
	@ViewInject(R.id.et_verfication_code)
	EditText et_verfication_code;
	@ViewInject(R.id.tv_send_verification_code)
	TextView tv_send_verification_code;
	@ViewInject(R.id.tv_modification_email)
	TextView tv_modification_email;
	@ViewInject(R.id.linear_old_email_content)
	LinearLayout linear_old_email_content;
	@ViewInject(R.id.linear_pwd_content)
	LinearLayout linear_pwd_content;
	@ViewInject(R.id.tv_email_title)
	TextView tv_email_title;
	@ViewInject(R.id.tv_head_title_email)
	TextView tv_head_title_email;

	public static final String OLD_EMAIL_INTENT = "OLD_EMAIL_INTENT";
	public static final String IS_MODIFY_STATE_INTENT = "IS_MODIFY_STATE_INTENT";

	/**
	 * 自定义计时器
	 */
	private TimeCount timer;
	private String accessKey;
	private String oldEmail;
	private String uid;
	/**
	 * 是否是修改状态
	 */
	private boolean isModifyState;

	@Override
	protected void onCreate(Bundle arg0) {
		setContentView(R.layout.activity_modification_email);
		super.onCreate(arg0);
		ViewUtils.inject(this);
		init();
	}

	private void init() {
		tv_email.setEnabled(false);

		isModifyState = getIntent().getBooleanExtra(IS_MODIFY_STATE_INTENT, false);
		if (isModifyState) { // 是不是修改状态

			tv_modification_email.setText(R.string.btn_update_email);
			tv_email_title.setText(R.string.lable_new_email_title);
			tv_head_title_email.setText(R.string.title_head_update_email);
		} else {

			tv_email_title.setText(R.string.lable_email_title);
			tv_head_title_email.setText(R.string.title_head_certification_email);
		}

	}

	@OnClick({ R.id.tv_send_verification_code, R.id.tv_modification_email })
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.tv_send_verification_code:
			String newEmail = et_new_email.getText().toString().trim();
			if (!RegularUtils.isEmail(newEmail)) {
				ToastUtils.getUtils(getApplicationContext()).show(
						getString(R.string.toast_please_email));
				return;
			}
			timer = new TimeCount(60000, 1000);// 构造CountDownTimer对象
			getCertificationEmailCode(newEmail);
			break;
		case R.id.tv_modification_email:

				lawyerChangeEmail();

		}
	}

	/** 律师业务 - 认证律师邮箱(需登录) */
	private void lawyerEmailCertification() {
		String email = et_new_email.getText().toString();
		boolean isEmail = RegularUtils.isEmail(email);
		if (!isEmail) {
			ToastUtils.getUtils(getApplicationContext()).show(
					getString(R.string.toast_please_email));
			return;
		}
		String emailCode = et_verfication_code.getText().toString();
		if (emailCode == null || emailCode.equals("")) {
			ToastUtils.getUtils(getApplicationContext()).show(
					getString(R.string.toast_please_input_email_code));
			return;
		}
		RequestParams params = new RequestParams();
		params.addHeader("Authorization", PrefsUtils.getString(
				getApplicationContext(), PrefsUtils.ACCESSKEY));
		params.addBodyParameter("userid",
				PrefsUtils.getString(getApplicationContext(), PrefsUtils.UID));
		params.addBodyParameter("email", email);
		params.addBodyParameter("emailCode", emailCode);
		httpDataUtils.sendProgressPost(MyData.LAWYER_EMAIL_CERTIFICATION,
				params);
		httpDataUtils.setSucessBack(new HttpDataUtils.SucessBack() {

			@Override
			public void sucess(ResponseInfo<String> arg0) {
				try {
					JSONObject object = new JSONObject(arg0.result);
					String code = object.optString("code");
					String msg = object.optString("msg");
					if (code.equals(Constans.SUCCESS_CODE)) {
						Logininfo();
					} else {

					}
					// ToastUtils.getUtils(getApplicationContext()).show(msg);
				} catch (JSONException e) {
					e.printStackTrace();
				}

			}
		});
	}

	/** 律师业务 - 修改律师邮箱地址(需登录) */
	private void lawyerChangeEmail() {
		final String email = et_new_email.getText().toString();
		boolean isEmail = RegularUtils.isEmail(email);
		if (!isEmail) {
			ToastUtils.getUtils(getApplicationContext()).show(getString(R.string.toast_please_email));
			return;
		}

		String emailCode = et_verfication_code.getText().toString();
		if (emailCode == null || emailCode.equals("")) {
			ToastUtils.getUtils(getApplicationContext()).show(getString(R.string.toast_please_input_email_code));
			return;
		}
		RequestParams params = new RequestParams();
		params.addHeader("Authorization", PrefsUtils.getString(getApplicationContext(), PrefsUtils.AUTHORIZATION));
		params.addBodyParameter("email", email);
		params.addBodyParameter("validate_code", emailCode);

		httpDataUtils.sendProgressPost(MyData.MODIFY_EMAIL, params);
		httpDataUtils.setSucessBack(new HttpDataUtils.SucessBack() {

			@Override
			public void sucess(ResponseInfo<String> arg0) {
				try {
					JSONObject object = new JSONObject(arg0.result);
					String code = object.optString("code");
					String msg = object.optString("msg");
					if (code.equals("0")) {
						PrefsUtils.saveString(ModificationEmailActivity.this, PrefsUtils.EMAIl, email);
						ToastUtils.getUtils(getApplicationContext()).show("邮箱设置成功");
						finish();
					} else {
						ToastUtils.getUtils(getApplicationContext()).show(msg);
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		});

	}

	/**
	 * 获取认证验证码
	 */
	private void getCertificationEmailCode(String email) {
		RequestParams params = new RequestParams();
		// params.addHeader("Authorization", accessKey);
		params.addBodyParameter("email", email);
		params.addBodyParameter("type", "USER_MODIFY_EMAIL");
		httpDataUtils.sendProgressPost(MyData.SEND_EMAIL_CODE, params);
		httpDataUtils.setSucessBack(new HttpDataUtils.SucessBack() {

			@Override
			public void sucess(ResponseInfo<String> arg0) {
				try {
					JSONObject object = new JSONObject(arg0.result);
					String code = JSONTool.getString(object, "code");
					String msg = JSONTool.getString(object, "msg");
					if (code.equals("0")) { // 成功
						timer.start();// 开始计时
						tv_send_verification_code.setEnabled(false);
					} else {
						timer.cancel();//
						tv_send_verification_code.setEnabled(true);
						ToastUtils.getUtils(getApplicationContext()).show(msg);
					}
				 	
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

	/** 获取登录信息 */
	private void Logininfo() {
		// BusinessEntity、、企业用户实体
		RequestParams params = new RequestParams();
		params.addHeader("Authorization", PrefsUtils.getString(
				getApplicationContext(), PrefsUtils.ACCESSKEY));
		httpDataUtils.sendProgressGet(MyData.LOGININFO, params);
		httpDataUtils.setSucessBack(new HttpDataUtils.SucessBack() {

			@Override
			public void sucess(ResponseInfo<String> arg0) {
				try {
					JSONObject object = new JSONObject(arg0.result);
					String code = object.optString("code");
					String msg = object.optString("msg");
					if (code.equals("000000")) {
						Gson gson = new Gson();
						DataUserEntity userEntity = gson.fromJson(
								object.optString("data"), DataUserEntity.class);
						// 将用户信息保存在缓存文件（持久化操作）
						HzApplication.getInstance().getUserEntityCache()
								.put(Constans.USER_INFO, userEntity);
//						PrefsUtils.saveString(getApplicationContext(),
//								PrefsUtils.UID, userEntity.getUserid());
						if (AuthenticationEmailActivity.authenticationEmailActivity != null) {
							AuthenticationEmailActivity.authenticationEmailActivity
									.finish();
						}
						startActivity(new Intent().setClass(context,
								AuthenticationEmailActivity.class));
						finish();
					} else {
						// ToastUtils.getUtils(getApplicationContext()).show(msg);
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
			tv_send_verification_code
					.setText(getString(R.string.btn_send_verfication_no_time_code_txt));
			// tv_send_verification_code.setBackgroundResource(R.drawable.login_subimt_button);
			tv_send_verification_code.setEnabled(true);
		}

		@Override
		public void onTick(long millisUntilFinished) {
			/*
			 * 计时过程显示
			 */
			tv_send_verification_code.setEnabled(false);
			tv_send_verification_code.setText(getString(
					R.string.btn_send_verfication_code_txt,
					millisUntilFinished / 1000));
		}
	}
}
