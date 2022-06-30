package com.yhy.hzzll.mian.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
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
import com.yhy.hzzll.mian.entity.DataUserEntity;
import com.yhy.hzzll.utils.CacheUtils;
import com.yhy.hzzll.utils.HttpDataUtils;
import com.yhy.hzzll.utils.LogUtils;
import com.yhy.hzzll.utils.PrefsUtils;
import com.yhy.hzzll.utils.ToastUtils;
import com.yhy.hzzll.utils.ValidationUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RoleTransitionActivity extends BaseActivity {

    @ViewInject(R.id.et_user_account)
    private EditText et_user_account;
    @ViewInject(R.id.et_securitycode)
    private EditText et_securitycode;
    @ViewInject(R.id.tv_securitycode)
    private TextView tv_securitycode;

    @ViewInject(R.id.tv_next)
    private TextView tv_next;

    @ViewInject(R.id.tv_login)
    private TextView tv_login;
    HzApplication hzApplication;
    String phone = "";
    String psw = "";
    private Timer timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_role_transition);
        ViewUtils.inject(this);
        hzApplication = (HzApplication) getApplication();
        phone = getIntent().getStringExtra("phone");
        psw = getIntent().getStringExtra("psw");

        et_user_account.setText(phone);
        timer = new Timer(60000, 1000);
    }


    @OnClick({R.id.tv_login, R.id.tv_next, R.id.tv_securitycode})
    public void onclick(View view) {
        switch (view.getId()) {
            case R.id.tv_login:

                finish();

                break;

            case R.id.tv_next:

                if (!ValidationUtils.isMobile(et_user_account.getText().toString())) {
                    ToastUtils.getUtils(RoleTransitionActivity.this).show("请填写正确的手机号！");
                    return;
                }

                if (et_securitycode.getText().toString().length()!=4) {
                    ToastUtils.getUtils(RoleTransitionActivity.this).show("请输入正确的验证码！");
                    return;
                }

                roleReversal();

                break;

            case R.id.tv_securitycode:

                if (!ValidationUtils.isMobile(et_user_account.getText().toString())) {
                    ToastUtils.getUtils(RoleTransitionActivity.this).show("请填写正确的手机号！");
                    return;
                }

                getVerify();

                break;
        }
    }

    private void roleReversal() {
        RequestParams params = new RequestParams();
        params.addBodyParameter("mobile", et_user_account.getText().toString());
        params.addBodyParameter("validate_code", et_securitycode.getText().toString());

        httpDataUtils.sendProgressPost(MyData.ROLE_REVERSAL, params);
        httpDataUtils.setSucessBack(new HttpDataUtils.SucessBack() {
            @Override
            public void sucess(ResponseInfo<String> arg0) {
                if (httpDataUtils.code(arg0.result)) {

                    if(psw.equals("")){
                        finish();
                        ToastUtils.getUtils(RoleTransitionActivity.this).show("您的账号已成功转换为律师，您可以登录私律律师端了。");
                    }else{
                        Login();
                    }

//                    ToastUtils.getUtils(RoleTransitionActivity.this).show(arg0.result);
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


    private void Login() {

        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody formBody = new FormBody.Builder()
                .add("grant_type", "password")
                .add("client_id", "4")
                .add("client_secret", "kkRwNTASYx001U2yn8PIYOZxGYO422YQ9mk2NbXg")
                .add("username", phone)
                .add("password", psw)
                .add("scope", "")
                .build();
        Request request = new Request.Builder()
                .url(MyData.NEW_LOGIN)
                .post(formBody)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                final String res = response.body().string();

                LogUtils.logE(res);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try{

                            JSONObject object = new JSONObject(res);
                            String message = object.optString("message");
                            String token_type = object.optString("token_type");
                            String access_token = object.optString("access_token");
                            String refresh_token = object.optString("refresh_token");

                            if (message != null&!message.equals("")) {
                                if (message.contains("用户")) {
//                                    ToastUtils.getUtils(LoginActivity.this).show("该账号是用户账号，请登陆用户端。");
//                                    RoleTransitionDialog dialogTips = new RoleTransitionDialog();
//                                    dialogTips.showDialog(LoginActivity.this, new RoleTransitionDialog.Click() {
//                                        @Override
//                                        public void buy() {
//                                            startActivity(new Intent(LoginActivity.this, RoleTransitionActivity.class)
//                                                    .putExtra("phone",et_id.getText().toString())
//                                                    .putExtra("psw",et_password.getText().toString()));
//                                        }
//                                    },"");


                                } else if (message.equals("The user credentials were incorrect.")){
//                                    ToastUtils.getUtils(LoginActivity.this).show("账号密码错误！");
                                }else {

                                }
                            } else {
                                LogUtils.logE(token_type + "/////" + access_token);
                                PrefsUtils.saveString(RoleTransitionActivity.this, PrefsUtils.AUTHORIZATION, token_type + " " + access_token);
                                PrefsUtils.saveString(RoleTransitionActivity.this, PrefsUtils.REFRESH_TOKEN, refresh_token );
                                PrefsUtils.saveString(RoleTransitionActivity.this, PrefsUtils.REFRESHTIME, System.currentTimeMillis()+"");
                                oldLogin();
                            }

                        }catch (Exception e){

                        }

                    }
                });
            }
        });
    }


    private void oldLogin() {
        RequestParams params = new RequestParams();
        params.addBodyParameter("uname", phone);
        params.addBodyParameter("uword", psw);
        httpDataUtils.sendProgressPost(MyData.LOGIN, params);
        httpDataUtils.setSucessBack(new HttpDataUtils.SucessBack() {

            @Override
            public void sucess(ResponseInfo<String> arg0) {
                try {
                    JSONObject object = new JSONObject(arg0.result);
                    String code = object.optString("code");
                    String msg = object.optString("msg");
                    String data = object.optString("data");

                    if (code.equals("000000") && msg.equals("登录成功")) {
                        PrefsUtils.saveString(RoleTransitionActivity.this, PrefsUtils.ACCESSKEY, data);
                        logininfo();

                    } else {

                        PrefsUtils.saveString(RoleTransitionActivity.this, PrefsUtils.AUTHORIZATION,"");
                        PrefsUtils.saveString(RoleTransitionActivity.this, PrefsUtils.REFRESH_TOKEN, "" );


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
//                loading.dismissDialog();
            }
        });
    }


    /**
     * 获取登录信息
     */
    private void logininfo() {

        RequestParams params = new RequestParams();
        params.addHeader("Authorization", PrefsUtils.getString(RoleTransitionActivity.this, PrefsUtils.AUTHORIZATION));
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

                            PrefsUtils.saveString(RoleTransitionActivity.this, PrefsUtils.UPHONE, userEntity.getData().getInfo().getMobile());
                            PrefsUtils.saveString(RoleTransitionActivity.this, PrefsUtils.UID, userEntity.getData().getInfo().getId() + "");
                            PrefsUtils.saveString(RoleTransitionActivity.this, PrefsUtils.ACCID, userEntity.getData().getInfo().getIm_token().getAccid());
                            PrefsUtils.saveString(RoleTransitionActivity.this, PrefsUtils.USERTOKEN, userEntity.getData().getInfo().getIm_token().getToken());


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
                            PrefsUtils.saveString(RoleTransitionActivity.this, PrefsUtils.OLD_UID, userEntity.getData().getInfo().getOld_user_info().getOld_user_id() + "");

                            PrefsUtils.saveString(RoleTransitionActivity.this, PrefsUtils.UPHONE, userEntity.getData().getInfo().getMobile());
                            PrefsUtils.saveString(RoleTransitionActivity.this, PrefsUtils.UNAME, userEntity.getData().getInfo().getTruename());
                            PrefsUtils.saveString(RoleTransitionActivity.this, PrefsUtils.ADDRESS, userEntity.getData().getInfo().getBase_region());
                            PrefsUtils.saveString(RoleTransitionActivity.this, PrefsUtils.SPECIAL, userEntity.getData().getInfo().getLawyer_secpical());
                            PrefsUtils.saveString(RoleTransitionActivity.this, PrefsUtils.AVATAR, userEntity.getData().getInfo().getAvatar());

                            PrefsUtils.saveString(RoleTransitionActivity.this, PrefsUtils.UID, userEntity.getData().getInfo().getId() + "");
                            PrefsUtils.saveString(RoleTransitionActivity.this, PrefsUtils.ACCID, userEntity.getData().getInfo().getIm_token().getAccid());
                            PrefsUtils.saveString(RoleTransitionActivity.this, PrefsUtils.USERTOKEN, userEntity.getData().getInfo().getIm_token().getToken());
                            PrefsUtils.saveString(RoleTransitionActivity.this, PrefsUtils.CONSULT_PRICE, userEntity.getData().getInfo().getOffer_info().getPrice());
                            PrefsUtils.saveString(RoleTransitionActivity.this, PrefsUtils.CONSULT_OPEN, userEntity.getData().getInfo().getOffer_info().getExclusive_consult_isprice());
                            PrefsUtils.saveString(RoleTransitionActivity.this, PrefsUtils.AUTH_STATUS, userEntity.getData().getInfo().getAuth_status());

                            MANService manService = MANServiceProvider.getService();
                            manService.getMANAnalytics().updateUserAccount(userEntity.getData().getInfo().getTruename(), userEntity.getData().getInfo().getId()+"");
                            LogUtils.logE("埋点成功");

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


    public void doLogin(final String id, String token) {
        LoginInfo info = new LoginInfo(id, token);
        LogUtils.logE(id + token);

        RequestCallback<LoginInfo> callback =
                new RequestCallback<LoginInfo>() {
                    @Override
                    public void onSuccess(LoginInfo loginInfo) {

                        DemoCache.setAccount(id);
                        NimUIKit.setAccount(id);

                        startActivity(new Intent(RoleTransitionActivity.this,RegisterSecondActivity.class));

                        finish();
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



    private void bind(String id) {
        RequestParams params = new RequestParams();
        params.addHeader("Authorization", PrefsUtils.getString(RoleTransitionActivity.this, PrefsUtils.AUTHORIZATION));
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



    /**
     * 发送验证码
     */
    private void getVerify() {

        RequestParams params = new RequestParams();
        params.addBodyParameter("mobile", et_user_account.getText().toString());
        params.addBodyParameter("type", "USER_SWITCH_LAWYER");
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
