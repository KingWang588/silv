package com.yhy.hzzll.mian.fragment;

/**
 * 注册--填写用户信息
 * 
 * @author wangyang
 * 
 */
public class RegsterFragmentInfo extends BaseFragment {

//	private  Handler handler;
//
//	@ViewInject(R.id.tv_securitycode)
//	private TextView tv_securitycode;
//
//	private HttpDataUtils dataUtils;
//
//	private Timer timer;
//
//	/** 账号 */
//	@ViewInject(R.id.et_phonenumber)
//	private EditText et_phonenumber;
//
//	/** 验证码 */
//	@ViewInject(R.id.et_securitycode)
//	private EditText et_securitycode;
//
//	/** 密码 */
//	@ViewInject(R.id.et_password)
//	private EditText et_password;
//
//	/** 邀请码 */
//	@ViewInject(R.id.et_invitation_code)
//	private EditText et_invitation_code;
//
//	@ViewInject(R.id.cb_read)
//	private CheckBox cb_read;
//
//	@ViewInject(R.id.tv_next)
//	private TextView tv_next;
//
//	@ViewInject(R.id.tv_read_action)
//	MultiActionTextView tv_read_action;
//
//	public RegsterFragmentInfo() {
//
//	}
//
//	public void setHandler(Handler handler) {
//		this.handler = handler;
//	}
//
//	@Override
//	public void onCreate(Bundle savedInstanceState) {
//		dataUtils = new HttpDataUtils(getActivity());
//		timer = new Timer(60000, 1000);
//		super.onCreate(savedInstanceState);
//	}
//
//	@Override
//	public View onCreateView(LayoutInflater inflater,
//			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//		View view = inflater.inflate(R.layout.fragment_register_info, null);
//		ViewUtils.inject(this, view);
//		viewInit();
//		return view;
//	}
//
//	@Override
//	public void viewInit() {
//
//		String text = "阅读并接受《私律使用协议》、《隐私声明》及《法律声明》";
//
//		MultiActionClickableSpan action1 = new MultiActionClickableSpan(6, 12, R.color.textbule, true, false, listener1);
//		MultiActionClickableSpan action2 = new MultiActionClickableSpan(15, 20, R.color.textbule, false, true, listener2);
//		MultiActionClickableSpan action3 = new MultiActionClickableSpan(22, 26, R.color.textbule, false, true, listener3);
//		tv_read_action.setText(text, action1, action2, action3);
//
//
//		cb_read.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//			@Override
//			public void onCheckedChanged(CompoundButton arg0, boolean check) {
//				if (check) {
//					tv_next.setEnabled(true);
//				} else {
//					tv_next.setEnabled(false);
//				}
//			}
//		});
//
//		super.viewInit();
//	}
//
//
//	MultiActionClickableSpan.OnTextClickedListener listener1 = new MultiActionClickableSpan.OnTextClickedListener() {
//		@Override
//		public void onTextClicked(View view, MultiActionClickableSpan span) {
//			startActivity(new Intent().putExtra("title", "使用协议")
////					.putExtra("url", "http://mobile.hzzll.com/n/0914/m.html")
//					.putExtra("url", MyData.AGREEMENT_OF_USAGE)
//					.setClass(getActivity(), WebviewActivity.class));
//		}
//	};
//
//	MultiActionClickableSpan.OnTextClickedListener listener2 = new MultiActionClickableSpan.OnTextClickedListener() {
//		@Override
//		public void onTextClicked(View view, MultiActionClickableSpan span) {
//			startActivity(new Intent().putExtra("title", "隐私声明")
//					.putExtra("url", MyData.PRIVACY_STATEMENT)
//					.setClass(getActivity(), WebviewActivity.class));
//		}
//	};
//
//	MultiActionClickableSpan.OnTextClickedListener listener3 = new MultiActionClickableSpan.OnTextClickedListener() {
//		@Override
//		public void onTextClicked(View view, MultiActionClickableSpan span) {
//			startActivity(new Intent().putExtra("title", "法律声明")
//					.putExtra("url", MyData.LEGAL_DISCLAIMER)
//					.setClass(getActivity(), WebviewActivity.class));
//		}
//	};
//
//	@OnClick({ R.id.tv_next, R.id.tv_securitycode,R.id.tv_read })
//	public void onclick(View view) {
//		switch (view.getId()) {
//			case R.id.tv_next:
//				if (!ValidationUtils.isMobileNO(et_phonenumber.getText().toString())) {
//					ToastUtils.getUtils(getActivity()).show("请填写正确的手机号！");
//					return;
//				}
//				String pass = et_password.getText().toString();
//				if (pass.isEmpty()) {
//					ToastUtils.getUtils(getActivity()).show("请设置密码");
//					return;
//				}
//				if (pass.length() < 6 || pass.length() > 12) {
//					ToastUtils.getUtils(getActivity()).show("请设置6-12位的密码");
//					return;
//				}
//				register();
//				break;
//			case R.id.tv_securitycode:
//
//				if (!ValidationUtils.isMobileNO(et_phonenumber.getText().toString())) {
//					ToastUtils.getUtils(getActivity()).show("请填写正确的手机号！");
//					return;
//				}
//
//				getVerify();
//
//				break;
//			case R.id.tv_read:
//				startActivity(new Intent().setClass(getActivity(), ReadAgreementActivity.class));
//				break;
//		}
//	}
//
//	private void register() {
//		RequestParams params = new RequestParams();
//		params.addBodyParameter("user_type", "0");
//		params.addBodyParameter("occupation", "lawyer");
//		params.addBodyParameter("mobile", et_phonenumber.getText().toString());
//		params.addBodyParameter("password", et_password.getText().toString());
//		params.addBodyParameter("password_confirmation", et_password.getText().toString());
//		params.addBodyParameter("validate_code", et_securitycode.getText().toString());
//		params.addBodyParameter("is_agree","yes");
//
//		if(!et_invitation_code.getText().toString().equals("")){
//			params.addBodyParameter("invite_code", et_invitation_code.getText().toString());
//		}
//
//		dataUtils.sendProgressPost(MyData.REGISTER, params);
//		dataUtils.setSucessBack(new HttpDataUtils.SucessBack() {
//			@Override
//			public void sucess(ResponseInfo<String> arg0) {
//				if (dataUtils.code(arg0.result)) {
//					login();
//				} else {
//					dataUtils.showMsg(arg0.result);
//				}
//			}
//		});
//		dataUtils.setFailBack(new HttpDataUtils.FailBack() {
//			@Override
//			public void fail(String msg) {
//
//			}
//		});
//	}
//
//
//	//注册成功 登录
//
//	private void login() {
//		RequestParams params = new RequestParams();
//		params.addBodyParameter("grant_type", "password");
//		params.addBodyParameter("client_id", "4");
//		params.addBodyParameter("client_secret", "kkRwNTASYx001U2yn8PIYOZxGYO422YQ9mk2NbXg");
//		params.addBodyParameter("username", et_phonenumber.getText().toString());
//		params.addBodyParameter("password", et_password.getText().toString());
//		params.addBodyParameter("scope", "");
//
//		dataUtils.sendPost(MyData.NEW_LOGIN, params);
//		dataUtils.setSucessBack(new HttpDataUtils.SucessBack() {
//			@Override
//			public void sucess(ResponseInfo<String> arg0) {
//
//					try {
//						JSONObject object = new JSONObject(arg0.result);
//						String token_type = object.optString("token_type");
//						String access_token = object.optString("access_token");
//						String refresh_token = object.optString("refresh_token");
//						LogUtils.logE(token_type+"/////"+access_token);
//					    PrefsUtils.saveString(getActivity(), PrefsUtils.AUTHORIZATION,token_type+" "+access_token );
//
//						handler.sendEmptyMessage(1);
//
//					} catch (JSONException e) {
//						e.printStackTrace();
//					}
//
//			}
//		});
//
//		dataUtils.setFailBack(new HttpDataUtils.FailBack(){
//
//			@Override
//			public void fail(String msg) {
//				LogUtils.logE(msg);
//			}
//		});
//
//
//	}
//
//	/**
//	 * 发送验证码
//	 */
//	private void getVerify() {
//		RequestParams params = new RequestParams();
//		params.addBodyParameter("mobile", et_phonenumber.getText().toString());
//		params.addBodyParameter("type", "USER_REGISTER");
//		dataUtils.sendProgressPost(MyData.GETREGISTERSMSCODE, params);
//		dataUtils.setSucessBack(new HttpDataUtils.SucessBack() {
//			@Override
//			public void sucess(ResponseInfo<String> arg0) {
//				if (dataUtils.code(arg0.result)) {
//					tv_securitycode.setEnabled(false);
//					timer.start();
//				} else {
//					dataUtils.showMsg(arg0.result);
//				}
//			}
//		});
//	}
//
//
//	private class Timer extends CountDownTimer {
//
//		public Timer(long millisInFuture, long countDownInterval) {
//			super(millisInFuture, countDownInterval);
//		}
//
//		@Override
//		public void onTick(long millisUntilFinished) {
//			tv_securitycode.setText((millisUntilFinished / 1000) + "秒后可重发");
//		}
//
//		@Override
//		public void onFinish() {
//			tv_securitycode.setEnabled(true);
//			tv_securitycode.setText("重新获取");
//		}
//	}






}
