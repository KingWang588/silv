package com.yhy.hzzll.mian.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.sdk.android.man.MANService;
import com.alibaba.sdk.android.man.MANServiceProvider;
import com.alibaba.sdk.android.push.CloudPushService;
import com.alibaba.sdk.android.push.CommonCallback;
import com.google.gson.Gson;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.netease.nim.uikit.api.NimUIKit;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.RequestCallback;
import com.netease.nimlib.sdk.auth.AuthService;
import com.netease.nimlib.sdk.auth.LoginInfo;
import com.yhy.hzzll.R;
import com.yhy.hzzll.framework.Constans;
import com.yhy.hzzll.framework.DemoCache;
import com.yhy.hzzll.framework.HzApplication;
import com.yhy.hzzll.framework.MyData;
import com.yhy.hzzll.home.activity.ReadAgreementActivity;
import com.yhy.hzzll.message.WebviewActivity;
import com.yhy.hzzll.mian.entity.DataUserEntity;
import com.yhy.hzzll.utils.CacheUtils;
import com.yhy.hzzll.utils.HttpDataUtils;
import com.yhy.hzzll.utils.LogUtils;
import com.yhy.hzzll.utils.PrefsUtils;
import com.yhy.hzzll.utils.ToastUtils;
import com.yhy.hzzll.utils.ValidationUtils;

import org.json.JSONException;
import org.json.JSONObject;

import am.widget.multiactiontextview.MultiActionClickableSpan;
import am.widget.multiactiontextview.MultiActionTextView;

public class RegisterFirstActivity extends BaseActivity {

    @ViewInject(R.id.tv_securitycode)
    private TextView tv_securitycode;

    private Timer timer;

    /** 账号 */
    @ViewInject(R.id.et_phonenumber)
    private EditText et_phonenumber;

    /** 验证码 */
    @ViewInject(R.id.et_securitycode)
    private EditText et_securitycode;

    /** 密码 */
    @ViewInject(R.id.et_password)
    private EditText et_password;

    /** 邀请码 */
    @ViewInject(R.id.et_invitation_code)
    private EditText et_invitation_code;

    @ViewInject(R.id.cb_read)
    private CheckBox cb_read;

    @ViewInject(R.id.tv_next)
    private TextView tv_next;

    @ViewInject(R.id.tv_read_action)
    MultiActionTextView tv_read_action;
    HzApplication hzApplication;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_first);
        ViewUtils.inject(this);
        hzApplication = (HzApplication) getApplication();
        viewInit();
    }


    public void viewInit() {
        timer = new Timer(60000, 1000);
        String text = "阅读并接受《私律使用协议》、《隐私声明》及《法律声明》";

        MultiActionClickableSpan action1 = new MultiActionClickableSpan(6, 12, R.color.textbule, true, false, listener1);
        MultiActionClickableSpan action2 = new MultiActionClickableSpan(15, 20, R.color.textbule, true, false, listener2);
        MultiActionClickableSpan action3 = new MultiActionClickableSpan(22, 26, R.color.textbule, true, false, listener3);
        tv_read_action.setText(text, action1, action2, action3);


        cb_read.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton arg0, boolean check) {
                if (check) {
                    tv_next.setEnabled(true);
                } else {
                    tv_next.setEnabled(false);
                }
            }
        });

    }

    MultiActionClickableSpan.OnTextClickedListener listener1 = new MultiActionClickableSpan.OnTextClickedListener() {
        @Override
        public void onTextClicked(View view, MultiActionClickableSpan span) {
            startActivity(new Intent().putExtra("title", "使用协议")
//					.putExtra("url", "http://mobile.hzzll.com/n/0914/m.html")
                    .putExtra("url", MyData.AGREEMENT_OF_USAGE)
                    .setClass(RegisterFirstActivity.this, WebviewActivity.class));
        }
    };

    MultiActionClickableSpan.OnTextClickedListener listener2 = new MultiActionClickableSpan.OnTextClickedListener() {
        @Override
        public void onTextClicked(View view, MultiActionClickableSpan span) {
            startActivity(new Intent().putExtra("title", "隐私声明")
                    .putExtra("url", MyData.PRIVACY_STATEMENT)
                    .setClass(RegisterFirstActivity.this, WebviewActivity.class));
        }
    };

    MultiActionClickableSpan.OnTextClickedListener listener3 = new MultiActionClickableSpan.OnTextClickedListener() {
        @Override
        public void onTextClicked(View view, MultiActionClickableSpan span) {
            startActivity(new Intent().putExtra("title", "法律声明")
                    .putExtra("url", MyData.LEGAL_DISCLAIMER)
                    .setClass(RegisterFirstActivity.this, WebviewActivity.class));
        }
    };

    @OnClick({ R.id.tv_next, R.id.tv_securitycode,R.id.tv_read,R.id.tv_login})
    public void onclick(View view) {
        switch (view.getId()) {
            case R.id.tv_login:
                startActivity(new Intent().putExtra("tab", 4).putExtra("activity", MainActivity.class.toString()).setClass(RegisterFirstActivity.this, LoginActivity.class));
                finish();
                break;
            case R.id.tv_next:
                if (!ValidationUtils.isMobile(et_phonenumber.getText().toString())) {
                    ToastUtils.getUtils(RegisterFirstActivity.this).show("请填写正确的手机号！");
                    return;
                }
                String pass = et_password.getText().toString();
                if (pass.isEmpty()) {
                    ToastUtils.getUtils(RegisterFirstActivity.this).show("请设置密码");
                    return;
                }
                if (pass.length() < 6 || pass.length() > 12) {
                    ToastUtils.getUtils(RegisterFirstActivity.this).show("请设置6-12位的密码");
                    return;
                }
                register();
                break;
            case R.id.tv_securitycode:

                if (!ValidationUtils.isMobile(et_phonenumber.getText().toString())) {
                    ToastUtils.getUtils(RegisterFirstActivity.this).show("请填写正确的手机号！");
                    return;
                }

                getVerify();

                break;
            case R.id.tv_read:
                startActivity(new Intent().setClass(RegisterFirstActivity.this, ReadAgreementActivity.class));
                break;
        }
    }

    private void register() {
        RequestParams params = new RequestParams();
        params.addBodyParameter("user_type", "0");
        params.addBodyParameter("occupation", "lawyer");
        params.addBodyParameter("mobile", et_phonenumber.getText().toString());
        params.addBodyParameter("password", et_password.getText().toString());
        params.addBodyParameter("password_confirmation", et_password.getText().toString());
        params.addBodyParameter("validate_code", et_securitycode.getText().toString());
        params.addBodyParameter("is_agree","yes");
        params.addBodyParameter("register_source","android");

        if(!et_invitation_code.getText().toString().equals("")){
            params.addBodyParameter("invite_code", et_invitation_code.getText().toString());
        }

        httpDataUtils.sendProgressPost(MyData.REGISTER, params);
        httpDataUtils.setSucessBack(new HttpDataUtils.SucessBack() {
            @Override
            public void sucess(ResponseInfo<String> arg0) {
                if (httpDataUtils.code(arg0.result)) {
                    login();
                } else {
                    httpDataUtils.showMsgNew(arg0.result);
                }
            }
        });
        httpDataUtils.setFailBack(new HttpDataUtils.FailBack() {
            @Override
            public void fail(String msg) {

            }
        });
    }


    //注册成功 登录

    private void login() {
        RequestParams params = new RequestParams();
        params.addBodyParameter("grant_type", "password");
        params.addBodyParameter("client_id", "4");
        params.addBodyParameter("client_secret", "kkRwNTASYx001U2yn8PIYOZxGYO422YQ9mk2NbXg");
        params.addBodyParameter("username", et_phonenumber.getText().toString());
        params.addBodyParameter("password", et_password.getText().toString());
        params.addBodyParameter("scope", "");

        httpDataUtils.sendPost(MyData.NEW_LOGIN, params);
        httpDataUtils.setSucessBack(new HttpDataUtils.SucessBack() {
            @Override
            public void sucess(ResponseInfo<String> arg0) {

                try {
                    JSONObject object = new JSONObject(arg0.result);
                    String token_type = object.optString("token_type");
                    String access_token = object.optString("access_token");
                    String refresh_token = object.optString("refresh_token");
                    LogUtils.logE(token_type+"/////"+access_token);
                    PrefsUtils.saveString(RegisterFirstActivity.this, PrefsUtils.AUTHORIZATION,token_type+" "+access_token );
                    PrefsUtils.saveString(RegisterFirstActivity.this, PrefsUtils.REFRESH_TOKEN,refresh_token );

//                    oldLogin();

                    logininfo();

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

        httpDataUtils.setFailBack(new HttpDataUtils.FailBack(){

            @Override
            public void fail(String msg) {
                LogUtils.logE(msg);
            }
        });

    }


    private void oldLogin() {
        RequestParams params = new RequestParams();
        params.addBodyParameter("uname", et_phonenumber.getText().toString());
        params.addBodyParameter("uword", et_password.getText().toString());
        httpDataUtils.sendProgressPost(MyData.LOGIN, params);
        httpDataUtils.setSucessBack(new HttpDataUtils.SucessBack() {

            @Override
            public void sucess(ResponseInfo<String> arg0) {
                try {
                    JSONObject object = new JSONObject(arg0.result);
                    String code = object.optString("code");
                    String msg = object.optString("msg");
                    String data = object.optString("data");
//                    LogUtils.logE("注册完登录是时获取的 ACCESSKEY"+data);

                    if (code.equals("000000") && msg.equals("登录成功")) {
                        PrefsUtils.saveString(RegisterFirstActivity.this, PrefsUtils.ACCESSKEY, data);

                    //   startActivity(new Intent(RegisterFirstActivity.this,RegisterSecondActivity.class));
                    } else {

                        PrefsUtils.saveString(RegisterFirstActivity.this, PrefsUtils.AUTHORIZATION,"");
                        PrefsUtils.saveString(RegisterFirstActivity.this, PrefsUtils.REFRESH_TOKEN, "" );

                        ToastUtils.getUtils(getApplicationContext()).show(msg);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        httpDataUtils.setFailBack(new HttpDataUtils.FailBack() {

            @Override
            public void fail(String msg) {

            }
        });

    }


    /**
     * 获取登录信息
     */
    private void logininfo() {

        RequestParams params = new RequestParams();
        params.addHeader("Authorization", PrefsUtils.getString(RegisterFirstActivity.this, PrefsUtils.AUTHORIZATION));
        httpDataUtils.sendProgressGet(MyData.CHECK_OUT_USER_INFO, params);
        httpDataUtils.setSucessBack(new HttpDataUtils.SucessBack() {
            @Override
            public void sucess(ResponseInfo<String> arg0) {
                try {
                    JSONObject object = new JSONObject(arg0.result);
                    String code = object.optString("code");
                    String msg = object.optString("msg");
                    if (code.equals("0")) {
                        Gson gson = new Gson();
                        try {
                            CacheUtils.dataUserEntity = gson.fromJson(arg0.result, DataUserEntity.class);
                            HzApplication.getInstance().getUserEntityCache().put(Constans.USER_INFO, CacheUtils.dataUserEntity);
                            DataUserEntity userEntity = gson.fromJson(arg0.result, DataUserEntity.class);

                            PrefsUtils.saveString(RegisterFirstActivity.this, PrefsUtils.UPHONE, userEntity.getData().getInfo().getMobile());
                            PrefsUtils.saveString(RegisterFirstActivity.this, PrefsUtils.UID, userEntity.getData().getInfo().getId() + "");
                            PrefsUtils.saveString(RegisterFirstActivity.this, PrefsUtils.ACCID, userEntity.getData().getInfo().getIm_token().getAccid());
                            PrefsUtils.saveString(RegisterFirstActivity.this, PrefsUtils.USERTOKEN, userEntity.getData().getInfo().getIm_token().getToken());


                            final CloudPushService pushService = hzApplication.getPushService();
                            pushService.bindAccount(MyData.SILVZONE + userEntity.getData().getInfo().getId() + "", new CommonCallback() {
                                @Override
                                public void onSuccess(String s) {
                                    Log.e("========>>>>>>", "绑定成功");
                                    bind(pushService.getDeviceId());
                                }

                                @Override
                                public void onFailed(String s, String s1) {
                                    Log.e("========>>>>>>", "绑定失败" + s1);
                                }
                            });

                            pushService.turnOnPushChannel(new CommonCallback() {
                                @Override
                                public void onSuccess(String s) {
                                    LogUtils.logE("开启推送成功！");
                                }

                                @Override
                                public void onFailed(String s, String s1) {

                                }
                            });


//                            PrefsUtils.saveString(LoginActivity.this, PrefsUtils.ACCESSKEY, userEntity.getData().getInfo().getOld_user_info().getHash());
                            PrefsUtils.saveString(RegisterFirstActivity.this, PrefsUtils.OLD_UID, userEntity.getData().getInfo().getOld_user_info().getOld_user_id() + "");

                            PrefsUtils.saveString(RegisterFirstActivity.this, PrefsUtils.UPHONE, userEntity.getData().getInfo().getMobile());
                            PrefsUtils.saveString(RegisterFirstActivity.this, PrefsUtils.UNAME, userEntity.getData().getInfo().getTruename());
                            PrefsUtils.saveString(RegisterFirstActivity.this, PrefsUtils.ADDRESS, userEntity.getData().getInfo().getBase_region());
                            PrefsUtils.saveString(RegisterFirstActivity.this, PrefsUtils.SPECIAL, userEntity.getData().getInfo().getLawyer_secpical());
                            PrefsUtils.saveString(RegisterFirstActivity.this, PrefsUtils.AVATAR, userEntity.getData().getInfo().getAvatar());

                            PrefsUtils.saveString(RegisterFirstActivity.this, PrefsUtils.UID, userEntity.getData().getInfo().getId() + "");
                            PrefsUtils.saveString(RegisterFirstActivity.this, PrefsUtils.ACCID, userEntity.getData().getInfo().getIm_token().getAccid());
                            PrefsUtils.saveString(RegisterFirstActivity.this, PrefsUtils.USERTOKEN, userEntity.getData().getInfo().getIm_token().getToken());
                            PrefsUtils.saveString(RegisterFirstActivity.this, PrefsUtils.CONSULT_PRICE, userEntity.getData().getInfo().getOffer_info().getPrice());
                            PrefsUtils.saveString(RegisterFirstActivity.this, PrefsUtils.CONSULT_OPEN, userEntity.getData().getInfo().getOffer_info().getExclusive_consult_isprice());
                            PrefsUtils.saveString(RegisterFirstActivity.this, PrefsUtils.AUTH_STATUS, userEntity.getData().getInfo().getAuth_status());



                            MANService manService = MANServiceProvider.getService();
                            manService.getMANAnalytics().userRegister(userEntity.getData().getInfo().getTruename());


                            doLogin(userEntity.getData().getInfo().getIm_token().getAccid(), userEntity.getData().getInfo().getIm_token().getToken());

                        } catch (Exception e) {

                        }
                    } else {
                        // ToastUtils.getUtils(getActivity()).show(msg);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        httpDataUtils.setFailBack(new HttpDataUtils.FailBack() {

            @Override
            public void fail(String msg) {
            }
        });

    }

    private void bind(String id) {
        RequestParams params = new RequestParams();
        params.addHeader("Authorization", PrefsUtils.getString(RegisterFirstActivity.this, PrefsUtils.AUTHORIZATION));
        params.addQueryStringParameter("device_id", id);
        params.addQueryStringParameter("device_type", "android");
        HttpDataUtils httpDataUtils = new HttpDataUtils(this);
        httpDataUtils.sendPost(MyData.DEVICE, params);
        httpDataUtils.setSucessBack(new HttpDataUtils.SucessBack() {
            @Override
            public void sucess(ResponseInfo<String> arg0) {

            }
        });
        httpDataUtils.setFailBack(new HttpDataUtils.FailBack() {

            @Override
            public void fail(String msg) {
            }
        });
    }


    public void doLogin(final String id, String token) {
        LoginInfo info = new LoginInfo(id, token);
        LogUtils.logE(id + token);

        RequestCallback<LoginInfo> callback =
                new RequestCallback<LoginInfo>() {
                    @Override
                    public void onSuccess(LoginInfo loginInfo) {

                        DemoCache.setAccount(id);
                        NimUIKit.setAccount(id);

                        startActivity(new Intent(RegisterFirstActivity.this,RegisterSecondActivity.class));
                    }

                    @Override
                    public void onFailed(int code) {
                        LogUtils.logE(code + "》》。。。。。。");
                    }

                    @Override
                    public void onException(Throwable exception) {

                    }

                };
        NIMClient.getService(AuthService.class).login(info).setCallback(callback);
    }

    /**
     * 发送验证码
     */
    private void getVerify() {
        RequestParams params = new RequestParams();
        params.addBodyParameter("mobile", et_phonenumber.getText().toString());
        params.addBodyParameter("type", "USER_REGISTER");
        httpDataUtils.sendProgressPost(MyData.GETREGISTERSMSCODE, params);
        httpDataUtils.setSucessBack(new HttpDataUtils.SucessBack() {
            @Override
            public void sucess(ResponseInfo<String> arg0) {
                if (httpDataUtils.code(arg0.result)) {
                    tv_securitycode.setEnabled(false);
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
            tv_securitycode.setText((millisUntilFinished / 1000) + "秒后可重发");
        }

        @Override
        public void onFinish() {
            tv_securitycode.setEnabled(true);
            tv_securitycode.setText("重新获取");
        }
    }


}
