package com.yhy.hzzll.mian.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
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
import com.yhy.hzzll.home.activity.collaborate.LawyerCollaboratDetailsActivity;
import com.yhy.hzzll.home.activity.consult.CommentsAndLikesActivity;
import com.yhy.hzzll.home.activity.consult.ConsultActivity;
import com.yhy.hzzll.home.activity.consult.ConsultDetailsActivity;
import com.yhy.hzzll.home.activity.lawyeroffice.LawyerOfficeActivity;
import com.yhy.hzzll.message.WebviewActivity;
import com.yhy.hzzll.mian.entity.DataUserEntity;
import com.yhy.hzzll.mian.entity.RankEntity;
import com.yhy.hzzll.mian.view.RoleTransitionDialog;
import com.yhy.hzzll.utils.CacheUtils;
import com.yhy.hzzll.utils.ClickFilter;
import com.yhy.hzzll.utils.HttpDataUtils;
import com.yhy.hzzll.utils.HttpDataUtils.FailBack;
import com.yhy.hzzll.utils.JSONTool;
import com.yhy.hzzll.utils.LogUtils;
import com.yhy.hzzll.utils.MyActivityManager;
import com.yhy.hzzll.utils.PrefsUtils;
import com.yhy.hzzll.utils.ToastUtils;
import com.yhy.hzzll.utils.ValidationUtils;
import com.yhy.hzzll.view.DialogLoading;

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

/**
 * 登录
 *
 * @author wangyang
 */
public class LoginActivity extends Activity {

    @ViewInject(R.id.et_id)
    private EditText et_id;

    @ViewInject(R.id.et_password)
    private EditText et_password;

    @ViewInject(R.id.tv_remember)
    private TextView tv_remember;
    @ViewInject(R.id.tv_skip)
    private TextView tv_skip;
    @ViewInject(R.id.tv_version)
    private TextView tv_version;

    @ViewInject(R.id.tv_forget_pwd)
    private TextView tv_forget_pwd;

    @ViewInject(R.id.linear_code)
    private LinearLayout linear_code;

    @ViewInject(R.id.et_code)
    private EditText et_code;

    @ViewInject(R.id.tv_securitycode)
    private TextView tv_securitycode;

    @ViewInject(R.id.tv_login)
    private TextView tv_login;


    private Timer timer;
    private boolean isRemember = false;
    private HttpDataUtils httpDataUtils;
    private String activity;
    private DialogLoading loading;
    HzApplication hzApplication;
    private int login_type = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        timer = new Timer(60000, 1000);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        hzApplication = (HzApplication) getApplication();
        MyActivityManager.getInstance().pushOneActivity(this);
        ViewUtils.inject(this);
        httpDataUtils = new HttpDataUtils(this);
        loading = new DialogLoading();
        if (null != getIntent()) {
            activity = getIntent().getStringExtra("activity");
        }

        if (PrefsUtils.getString(getApplicationContext(), PrefsUtils.ISREMEMBER) != null && PrefsUtils.getString(getApplicationContext(), PrefsUtils.ISREMEMBER).equals("1")) {
            tv_remember.setSelected(true);
            isRemember = true;
            if (PrefsUtils.getString(getApplicationContext(), PrefsUtils.USERNAME) != null && !PrefsUtils.getString(getApplicationContext(), PrefsUtils.USERNAME).isEmpty()) {
                et_id.setText(PrefsUtils.getString(getApplicationContext(), PrefsUtils.USERNAME));
            }
            if (PrefsUtils.getString(getApplicationContext(), PrefsUtils.USERPASSROWD) != null && !PrefsUtils.getString(getApplicationContext(), PrefsUtils.USERPASSROWD).isEmpty()) {
                et_password.setText(PrefsUtils.getString(getApplicationContext(), PrefsUtils.USERPASSROWD));
            }
        }

        if (getIntent().getBooleanExtra("show",false)){
            Dialog dialog = new AlertDialog.Builder(LoginActivity.this)
                    .setTitle("下线通知")
                    .setMessage("您的账号在另外一台设备上登录了，如非本人操作，则密码可能已泄露，建议立即修改密码或联系私律客服。")
                    .setNeutralButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    })
                    .create();

            dialog.show();
        }

        tv_version.setText("私律律师端 V" + getVersion(LoginActivity.this));

        findViewById(R.id.pro).setOnClickListener(view -> {
            startActivity(new Intent().putExtra("title", "私律APP隐私保护政策")
                    .putExtra("url", MyData.PRIVACY_STATEMENT)
                    .setClass(LoginActivity.this, WebviewActivity.class));
        });

    }

    /**
     * 获取版本号
     *
     * @return 当前应用的版本号
     */
    public String getVersion(Context context) {
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            String version = info.versionName;
            return version;
        } catch (Exception e) {
            e.printStackTrace();
            return "2.0.3";
        }
    }

    @OnClick({R.id.tv_register, R.id.btn_login, R.id.tv_remember,
            R.id.tv_forget_pwd, R.id.ic_back, R.id.tv_skip, R.id.tv_securitycode,R.id.tv_login})
    public void onclick(View view) {
        switch (view.getId()) {

            case R.id.tv_login:

                if (login_type==1){

                    tv_login.setText("账号密码登录");
                    login_type=2;
                    et_password.setVisibility(View.GONE);
                    linear_code.setVisibility(View.VISIBLE);
                }else {
                    tv_login.setText("使用验证码登录");
                    login_type=1;
                    et_password.setVisibility(View.VISIBLE);
                    linear_code.setVisibility(View.GONE);
                }

                break;

            case R.id.tv_securitycode:

                if (!ValidationUtils.isMobile(et_id.getText().toString())) {
                    ToastUtils.getUtils(LoginActivity.this).show("请填写正确的手机号！");
                    return;
                }

                getVerify();

                break;
            case R.id.ic_back:
                if (isTaskRoot()) {
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();
                } else {
                    finish();
                }
                break;
            case R.id.tv_register:
                startActivity(new Intent().setClass(this, RegisterFirstActivity.class));
                finish();
                break;
            case R.id.tv_remember:// 记住密码
                if (!isRemember) {
                    tv_remember.setSelected(true);
                } else {
                    tv_remember.setSelected(false);
                }
                isRemember = !isRemember;
                break;
            case R.id.btn_login:
                CheckBox checkBox = findViewById(R.id.check_pro);
                if (!checkBox.isChecked()){
                    ToastUtils.getUtils(getApplicationContext()).show("请先同意《私律隐私政策》！");
                    return;
                }
                if (et_id.getText().toString().isEmpty()) {
                    ToastUtils.getUtils(getApplicationContext()).show("用户名不能为空！");
                    return;
                }

                if (!ValidationUtils.isMobile(et_id.getText().toString())) {
                    ToastUtils.getUtils(LoginActivity.this).show("请填写正确的手机号！");
                    return;
                }

                if (login_type==1){
                    if (et_password.getText().toString().isEmpty()) {
                        ToastUtils.getUtils(getApplicationContext()).show("密码不能为空！");
                        return;
                    }
                }else if (login_type == 2){
                    if (et_code.getText().toString().isEmpty()) {
                        ToastUtils.getUtils(getApplicationContext()).show("验证码不能为空！");
                        return;
                    }
                }

                if (ClickFilter.isFastClick()){
                    Login();
                }

                break;
            case R.id.tv_forget_pwd:

                Intent intent = new Intent(LoginActivity.this, ForgetPasswordFirstActivity.class).putExtra("phone",et_id.getText().toString());
                startActivity(intent);

                break;

            case R.id.tv_skip:

                Intent intent2 = new Intent();
                intent2.setAction("android.intent.action.VIEW");
                Uri content_url = Uri.parse(MyData.USER_SIDE);
                intent2.setData(content_url);
                startActivity(intent2);

                break;

        }
    }


    /**
     * 发送验证码
     */
    private void getVerify() {
        RequestParams params = new RequestParams();
        params.addBodyParameter("mobile", et_id.getText().toString());
        params.addBodyParameter("type", "USER_LOGIN");
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


    private void Login() {

        OkHttpClient okHttpClient = new OkHttpClient();

        RequestBody formBody=null;

        if (login_type==1){
             formBody = new FormBody.Builder()
                    .add("grant_type", "password")
                    .add("client_id", "4")
                    .add("client_secret", "kkRwNTASYx001U2yn8PIYOZxGYO422YQ9mk2NbXg")
                    .add("username", et_id.getText().toString())
                    .add("password", et_password.getText().toString())
                    .add("scope", "")
                    .build();
        }else if (login_type==2){

             formBody = new FormBody.Builder()
                    .add("grant_type", "password")
                    .add("client_id", "4")
                    .add("client_secret", "kkRwNTASYx001U2yn8PIYOZxGYO422YQ9mk2NbXg")
                    .add("username", et_id.getText().toString()+"_"+et_code.getText().toString())
                    .add("password", "smslogin")
                    .add("scope", "")
                    .build();
        }

        Request request = new Request.Builder()
                .url(MyData.NEW_LOGIN)
                .post(formBody)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("loginActivity","登录失败："+e);
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
                                    et_code.setText("");
                                    RoleTransitionDialog dialogTips = new RoleTransitionDialog();
                                    dialogTips.showDialog(LoginActivity.this, new RoleTransitionDialog.Click() {
                                        @Override
                                        public void buy() {
                                            startActivity(new Intent(LoginActivity.this, RoleTransitionActivity.class)
                                                    .putExtra("phone",et_id.getText().toString())
                                                    .putExtra("psw",et_password.getText().toString()));
                                        }
                                    },"");

                                } else if (message.equals("The user credentials were incorrect.")){
                                    ToastUtils.getUtils(LoginActivity.this).show("账号密码错误！");
                                }else {

                                }
                            } else {
                                LogUtils.logE(token_type + "/////" + access_token);
                                PrefsUtils.saveString(LoginActivity.this, PrefsUtils.AUTHORIZATION, token_type + " " + access_token);
                                PrefsUtils.saveString(LoginActivity.this, PrefsUtils.AUTHORIZATION_NO_B, access_token);
                                PrefsUtils.saveString(LoginActivity.this, PrefsUtils.REFRESH_TOKEN, refresh_token );
                                PrefsUtils.saveString(LoginActivity.this, PrefsUtils.REFRESHTIME, System.currentTimeMillis()+"");

                                if (login_type==1){
                                    logininfo();
                                }else if (login_type==2){
                                    logininfo();
                                }

                            }

                        }catch (Exception e){
                            Log.e("loginActivity","登录失败："+e);
                        }

                    }
                });
            }
        });
    }

//    private void oldLogin() {
//        RequestParams params = new RequestParams();
//        params.addBodyParameter("uname", et_id.getText().toString());
//        params.addBodyParameter("uword", et_password.getText().toString());
//        httpDataUtils.sendProgressPost(MyData.LOGIN, params);
//        httpDataUtils.setSucessBack(new HttpDataUtils.SucessBack() {
//
//            @Override
//            public void sucess(ResponseInfo<String> arg0) {
//                try {
//                    JSONObject object = new JSONObject(arg0.result);
//                    String code = object.optString("code");
//                    String msg = object.optString("msg");
//                    String data = object.optString("data");
//
//                    if (code.equals("000000") && msg.equals("登录成功")) {
//                        PrefsUtils.saveString(LoginActivity.this, PrefsUtils.ACCESSKEY, data);
//                        logininfo();
//
//                    } else {
//
//                        PrefsUtils.saveString(LoginActivity.this, PrefsUtils.AUTHORIZATION,"");
//                        PrefsUtils.saveString(LoginActivity.this, PrefsUtils.REFRESH_TOKEN, "" );
//
//
//                        ToastUtils.getUtils(getApplicationContext()).show(msg);
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//        httpDataUtils.setFailBack(new FailBack() {
//
//            @Override
//            public void fail(String msg) {
//                loading.dismissDialog();
//            }
//        });
//    }


    /**
     * 获取登录信息
     */
    private void logininfo() {

        RequestParams params = new RequestParams();
        params.addHeader("Authorization", PrefsUtils.getString(LoginActivity.this, PrefsUtils.AUTHORIZATION));
        httpDataUtils.sendProgressGet(MyData.CHECK_OUT_USER_INFO, params);
        httpDataUtils.setSucessBack(new HttpDataUtils.SucessBack() {
            @Override
            public void sucess(ResponseInfo<String> arg0) {
                try {
                    LogUtils.logE(arg0.result);
                    JSONObject object = new JSONObject(arg0.result);
                    String code = object.optString("code");
                    String msg = object.optString("msg");
                    if (code.equals("0")) {
                        Gson gson = new Gson();
                        try {
                            CacheUtils.dataUserEntity = gson.fromJson(arg0.result, DataUserEntity.class);
                            HzApplication.getInstance().getUserEntityCache().put(Constans.USER_INFO, CacheUtils.dataUserEntity);
                            DataUserEntity userEntity = gson.fromJson(arg0.result, DataUserEntity.class);

                            PrefsUtils.saveString(LoginActivity.this, PrefsUtils.UPHONE, userEntity.getData().getInfo().getMobile());
                            PrefsUtils.saveString(LoginActivity.this, PrefsUtils.UID, userEntity.getData().getInfo().getId() + "");
                            PrefsUtils.saveString(LoginActivity.this, PrefsUtils.ACCID, userEntity.getData().getInfo().getIm_token().getAccid());
                            PrefsUtils.saveString(LoginActivity.this, PrefsUtils.USERTOKEN, userEntity.getData().getInfo().getIm_token().getToken());
                            PrefsUtils.saveString(LoginActivity.this, PrefsUtils.ACCESSKEY, userEntity.getData().getInfo().getOld_user_info().getHash());


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
                                    LogUtils.logE("开启推送失败！");
                                }
                            });


                            PrefsUtils.saveString(LoginActivity.this, PrefsUtils.OLD_UID, userEntity.getData().getInfo().getOld_user_info().getOld_user_id() + "");

                            PrefsUtils.saveString(LoginActivity.this, PrefsUtils.UPHONE, userEntity.getData().getInfo().getMobile());
                            PrefsUtils.saveString(LoginActivity.this, PrefsUtils.UNAME, userEntity.getData().getInfo().getTruename());
                            PrefsUtils.saveString(LoginActivity.this, PrefsUtils.ADDRESS, userEntity.getData().getInfo().getBase_region());
                            PrefsUtils.saveString(LoginActivity.this, PrefsUtils.SPECIAL, userEntity.getData().getInfo().getLawyer_secpical());
                            PrefsUtils.saveString(LoginActivity.this, PrefsUtils.AVATAR, userEntity.getData().getInfo().getAvatar());

                            PrefsUtils.saveString(LoginActivity.this, PrefsUtils.UID, userEntity.getData().getInfo().getId() + "");
                            PrefsUtils.saveString(LoginActivity.this, PrefsUtils.ACCID, userEntity.getData().getInfo().getIm_token().getAccid());
                            PrefsUtils.saveString(LoginActivity.this, PrefsUtils.USERTOKEN, userEntity.getData().getInfo().getIm_token().getToken());
                            PrefsUtils.saveString(LoginActivity.this, PrefsUtils.CONSULT_PRICE, userEntity.getData().getInfo().getOffer_info().getPrice());
                            PrefsUtils.saveString(LoginActivity.this, PrefsUtils.CONSULT_OPEN, userEntity.getData().getInfo().getOffer_info().getExclusive_consult_isprice());
                            PrefsUtils.saveString(LoginActivity.this, PrefsUtils.AUTH_STATUS, userEntity.getData().getInfo().getAuth_status());

                            if (isRemember) {
                                // /==================存储登陆用户 和密码
                                PrefsUtils.saveString(getApplicationContext(), PrefsUtils.USERNAME, et_id.getText().toString().trim());
                                PrefsUtils.saveString(getApplicationContext(), PrefsUtils.USERPASSROWD, et_password.getText().toString());
                                PrefsUtils.saveString(getApplicationContext(), PrefsUtils.ISREMEMBER, "1");
                            } else {
                                PrefsUtils.saveString(getApplicationContext(), PrefsUtils.USERNAME, "");
                                PrefsUtils.saveString(getApplicationContext(), PrefsUtils.USERPASSROWD, "");
                                PrefsUtils.saveString(getApplicationContext(), PrefsUtils.ISREMEMBER, "0");
                            }


                            MANService manService = MANServiceProvider.getService();
                            manService.getMANAnalytics().updateUserAccount(userEntity.getData().getInfo().getTruename(), userEntity.getData().getInfo().getId()+"");
                            LogUtils.logE("埋点成功");

                            doLogin(userEntity.getData().getInfo().getIm_token().getAccid(), userEntity.getData().getInfo().getIm_token().getToken());


//                            if (activity != null && activity.equals(MainActivity.class.toString())) {
//                                int tab = getIntent().getIntExtra("tab", 0);
//                                if (MainActivity.mainactivity != null)
//                                    MainActivity.mainactivity.finish();
//                                startActivity(new Intent().putExtra("tab", tab).setClass(getApplicationContext(), MainActivity.class));
//                                finish();
//                                return;
//                            }
//
//                            if (activity != null && activity.equals(ConsultActivity.class.toString())) {
//                                finish();
//                                return;
//                            }
//
//                            if (activity != null && activity.equals(ConsultDetailsActivity.class.toString())) {
//                                finish();
//                                return;
//                            }
//
//                            if (activity != null && activity.equals(LawyerCollaboratDetailsActivity.class.toString())) {
//                                finish();
//                                return;
//                            }
//
//                            if (activity != null && activity.equals(CommentsAndLikesActivity.class.toString())) {
//                                finish();
//                                return;
//                            }
//
//                            if (activity != null && activity.equals(LawyerOfficeActivity.class.toString())) {
//                                finish();
//                                return;
//                            }
//
//
//                            startActivity(new Intent().putExtra("tab", 0).setClass(getApplicationContext(), MainActivity.class));
//                            finish();




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
        httpDataUtils.setFailBack(new FailBack() {

            @Override
            public void fail(String msg) {
                Log.e("loginActivity",msg);
            }
        });

    }

    private void bind(String id) {
        RequestParams params = new RequestParams();
        params.addHeader("Authorization", PrefsUtils.getString(LoginActivity.this, PrefsUtils.AUTHORIZATION));
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
                        getmoney();
                        DemoCache.setAccount(id);
                        NimUIKit.setAccount(id);

                        if (activity != null && activity.equals(MainActivity.class.toString())) {
                            int tab = getIntent().getIntExtra("tab", 0);
                            if (MainActivity.mainactivity != null)
                                MainActivity.mainactivity.finish();
                            startActivity(new Intent().putExtra("tab", tab).setClass(getApplicationContext(), MainActivity.class));
                            finish();
                            return;
                        }

                        if (activity != null && activity.equals(ConsultActivity.class.toString())) {
                            finish();
                            return;
                        }

                        if (activity != null && activity.equals(ConsultDetailsActivity.class.toString())) {
                            finish();
                            return;
                        }

                        if (activity != null && activity.equals(LawyerCollaboratDetailsActivity.class.toString())) {
                            finish();
                            return;
                        }

                        if (activity != null && activity.equals(CommentsAndLikesActivity.class.toString())) {
                            finish();
                            return;
                        }

                        if (activity != null && activity.equals(LawyerOfficeActivity.class.toString())) {
                            finish();
                            return;
                        }


                        startActivity(new Intent().putExtra("tab", 0).setClass(getApplicationContext(), MainActivity.class));
                        finish();
                    }

                    @Override
                    public void onFailed(int code) {
//                        LogUtils.logE(code + "》》。。。。。。");
                        PrefsUtils.saveString(LoginActivity.this, PrefsUtils.AUTHORIZATION,"");
                        PrefsUtils.saveString(LoginActivity.this, PrefsUtils.REFRESH_TOKEN, "" );

                        ToastUtils.getUtils(getApplicationContext()).show("登录失败。");
                    }

                    @Override
                    public void onException(Throwable exception) {

                    }

                };
        NIMClient.getService(AuthService.class).login(info).setCallback(callback);
    }

    private void getmoney() {
        RequestParams params = new RequestParams();
        HttpDataUtils httpDataUtils = new HttpDataUtils(LoginActivity.this);
        httpDataUtils.sendGet(MyData.CONSULT_RANKING, params);
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
                            RankEntity rankEntity = gson.fromJson(arg0.result, RankEntity.class);
                            PrefsUtils.saveString(LoginActivity.this, PrefsUtils.FIRST_REPLY, rankEntity.getData().get(0).getName());
                            PrefsUtils.saveString(LoginActivity.this, PrefsUtils.SECOND_REPLY, rankEntity.getData().get(1).getName());
                            PrefsUtils.saveString(LoginActivity.this, PrefsUtils.THIRD_REPLY, rankEntity.getData().get(2).getName());

                        } catch (Exception e) {

                        }
                    } else {

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

    private void checkOutUserInfo() {

        RequestParams params = new RequestParams();
        params.addHeader("Authorization", PrefsUtils.getString(getApplicationContext(), PrefsUtils.AUTHORIZATION));
        httpDataUtils.sendProgressGet(MyData.CHECK_OUT_USER_INFO, params);
        httpDataUtils.setSucessBack(new HttpDataUtils.SucessBack() {
            @Override
            public void sucess(ResponseInfo<String> arg0) {

                try {
                    JSONObject object = new JSONObject(arg0.result);
                    String code = JSONTool.getString(object, "code");
                    String msg = JSONTool.getString(object, "message");
                    if (code.equals("0")) { // 成功
                        Gson gson = new Gson();
                        DataUserEntity userEntity = gson.fromJson(object.toString(), DataUserEntity.class);

//                        PrefsUtils.saveString(LoginActivity.this, PrefsUtils.ACCESSKEY, userEntity.getData().getInfo().getOld_user_info().getHash());
                        PrefsUtils.saveString(LoginActivity.this, PrefsUtils.OLD_UID, userEntity.getData().getInfo().getOld_user_info().getOld_user_id() + "");

                        PrefsUtils.saveString(LoginActivity.this, PrefsUtils.UPHONE, userEntity.getData().getInfo().getMobile());
                        PrefsUtils.saveString(LoginActivity.this, PrefsUtils.UNAME, userEntity.getData().getInfo().getTruename());
                        PrefsUtils.saveString(LoginActivity.this, PrefsUtils.ADDRESS, userEntity.getData().getInfo().getBase_region());
                        PrefsUtils.saveString(LoginActivity.this, PrefsUtils.SPECIAL, userEntity.getData().getInfo().getLawyer_secpical());
                        PrefsUtils.saveString(LoginActivity.this, PrefsUtils.AVATAR, userEntity.getData().getInfo().getAvatar());

                        PrefsUtils.saveString(LoginActivity.this, PrefsUtils.UID, userEntity.getData().getInfo().getId() + "");
                        PrefsUtils.saveString(LoginActivity.this, PrefsUtils.ACCID, userEntity.getData().getInfo().getIm_token().getAccid());
                        PrefsUtils.saveString(LoginActivity.this, PrefsUtils.USERTOKEN, userEntity.getData().getInfo().getIm_token().getToken());
                        PrefsUtils.saveString(LoginActivity.this, PrefsUtils.CONSULT_PRICE, userEntity.getData().getInfo().getOffer_info().getPrice());
                        PrefsUtils.saveString(LoginActivity.this, PrefsUtils.CONSULT_OPEN, userEntity.getData().getInfo().getOffer_info().getExclusive_consult_isprice());
                        PrefsUtils.saveString(LoginActivity.this, PrefsUtils.AUTH_STATUS, userEntity.getData().getInfo().getAuth_status());


                        if (isRemember) {

                            PrefsUtils.saveString(LoginActivity.this, PrefsUtils.USERNAME, et_id.getText().toString().trim());
                            PrefsUtils.saveString(LoginActivity.this, PrefsUtils.USERPASSROWD, et_password.getText().toString());
                            PrefsUtils.saveString(LoginActivity.this, PrefsUtils.ISREMEMBER, "1");
                        } else {
                            PrefsUtils.saveString(LoginActivity.this, PrefsUtils.USERNAME, "");
                            PrefsUtils.saveString(LoginActivity.this, PrefsUtils.USERPASSROWD, "");
                            PrefsUtils.saveString(LoginActivity.this, PrefsUtils.ISREMEMBER, "0");
                        }

                        if (activity != null && activity.equals(MainActivity.class.toString())) {
                            int tab = getIntent().getIntExtra("tab", 0);
                            if (MainActivity.mainactivity != null)
                                MainActivity.mainactivity.finish();
                            startActivity(new Intent().putExtra("tab", tab).setClass(LoginActivity.this, MainActivity.class));

                            finish();
                        } else {
                            startActivity(new Intent().putExtra("tab", 0).setClass(LoginActivity.this, MainActivity.class));
                            finish();
                        }


                    } else {
                        ToastUtils.getUtils(LoginActivity.this).show(msg);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * 获取登录信息
     */
//    private void logininfo() {
//        // BusinessEntity、、企业用户实体
//        RequestParams params = new RequestParams();
//        params.addHeader("Authorization", PrefsUtils.getString(LoginActivity.this, PrefsUtils.ACCESSKEY));
//        httpDataUtils.sendGet(MyData.LOGININFO, params);
//        httpDataUtils.setSucessBack(new HttpDataUtils.SucessBack() {
//            @Override
//            public void sucess(ResponseInfo<String> arg0) {
//                loading.dismissDialog();
//                try {
//                    JSONObject object = new JSONObject(arg0.result);
//                    String code = object.optString("code");
//                    String msg = object.optString("msg");
//                    if (code.equals("000000")) {
//                        Log.e("result", object.optString("data"));
//                        Gson gson = new Gson();
//                        CacheUtils.dataUserEntity = gson.fromJson(object.optString("data"), DataUserEntity.class);
//                        if (!CacheUtils.dataUserEntity.getUtype().equals("1")) {
//                            loginOut();
//                            DialogTips dialogTips = new DialogTips();
//                            dialogTips.showDialog(LoginActivity.this, new DialogTips.Click() {
//                                @Override
//                                public void buy() {
//                                    Intent intent = new Intent();
//                                    intent.setAction("android.intent.action.VIEW");
//                                    Uri content_url = Uri.parse("http://mobile.hzzll.com/signin");
//                                    intent.setData(content_url);
//                                    startActivity(intent);
//                                }
//                            });
//                            return;
//                        }
//                        if (isRemember) {
//                            // /==================存储登陆用户 和密码
//                            PrefsUtils.saveString(getApplicationContext(), PrefsUtils.USERNAME, et_id.getText().toString().trim());
//                            PrefsUtils.saveString(getApplicationContext(), PrefsUtils.USERPASSROWD, et_password.getText().toString());
//                            PrefsUtils.saveString(getApplicationContext(), PrefsUtils.ISREMEMBER, "1");
//                        } else {
//                            PrefsUtils.saveString(getApplicationContext(), PrefsUtils.USERNAME, "");
//                            PrefsUtils.saveString(getApplicationContext(), PrefsUtils.USERPASSROWD, "");
//                            PrefsUtils.saveString(getApplicationContext(), PrefsUtils.ISREMEMBER, "0");
//                        }
//
//                        // 记住是否记住密码
//                        PrefsUtils.put(getApplicationContext(), PrefsUtils.ISREMEMBER, isRemember);
//                        // // 存储用户角色ID
//                        // PrefsUtils.saveString(getApplicationContext(),
//                        // PrefsUtils.UTYPE, CacheUtils.entity.getUtype());
//                        // // 存储用户ID
//                        // 将用户信息保存在缓存文件（持久化操作）
//                        HzApplication.getInstance().getUserEntityCache().put(Constans.USER_INFO, CacheUtils.dataUserEntity, 86400);
//                        PrefsUtils.saveString(getApplicationContext(), PrefsUtils.UID, CacheUtils.dataUserEntity.getUserid());
//                        PrefsUtils.saveString(getApplicationContext(), PrefsUtils.UNAME, CacheUtils.dataUserEntity.getVtruename());
//                        PrefsUtils.saveString(getApplicationContext(), PrefsUtils.UCODE, CacheUtils.dataUserEntity.getLawcode());
//                        PrefsUtils.saveString(getApplicationContext(), PrefsUtils.UPHONE, CacheUtils.dataUserEntity.getMobile());
//                        PrefsUtils.saveString(getApplicationContext(), PrefsUtils.PAYWORD, CacheUtils.dataUserEntity.getPayword());
//
//                        // 验证跳转过来的页面
//
//                        if (activity != null && activity.equals(EvidenceSavaActivity.class.toString())) {
//                            finish();
//                            return;
//                        }
//
//                        if (activity != null && activity.equals(MessageDetailChatActivity.class.toString())) {
//                            finish();
//                            return;
//                        }
//
//                        String id = null;
//                        if (getIntent() != null) {
//                            id = getIntent().getStringExtra("id");
//                        }
//
//                        if (id != null && activity != null && activity.equals(LawyerCollaboratDetailsActivity.class.toString())) {
//                            finish();
//                            if (LawyerCollaboratDetailsActivity.lawyerCollaboratDetailsActivity != null)
//                                LawyerCollaboratDetailsActivity.lawyerCollaboratDetailsActivity.finish();
//                            startActivity(new Intent().putExtra("id", id).putExtra("title", "21231313").putExtra("from", "footprint").setClass(getApplicationContext(),
//                                    LawyerCollaboratDetailsActivity.class));
//                            return;
//                        }
//
//                        if (id != null && id.isEmpty()) {
//                            startActivity(new Intent().putExtra("id", id).setClass(getApplicationContext(), LawyerCollaboratDetailsActivity.class));
//                            return;
//                        }
//
//
//                        if (id != null && id.isEmpty() && activity.equals(LawyerCollaboratDetailsActivity.class.toString())) {
//                            startActivity(new Intent().putExtra("id", id).putExtra("title", "121123").putExtra("from", "footprint").setClass(getApplicationContext(), LawyerCollaboratDetailsActivity.class));
//                            return;
//                        }
//                        // 验证跳转过来的页面
//                        if (activity != null && activity.equals(ConsultActivity.class.toString())) {
//                            finish();
//                            return;
//                        }
//                        if (activity != null && activity.equals(LawyerNationalCooperationActivity.class.toString())) {
//                            finish();
//                            return;
//                        }
//                        if (activity != null && activity.equals(MainActivity.class.toString())) {
//                            int tab = getIntent().getIntExtra("tab", 0);
//                            if (MainActivity.mainactivity != null)
//                                MainActivity.mainactivity.finish();
//                            startActivity(new Intent().putExtra("tab", tab).setClass(getApplicationContext(), MainActivity.class));
//                            return;
//                        }
//
//                        if (activity != null && activity.equals(PayActivity.class.toString())) {
//                            finish();
//                            return;
//                        }
//
//                        if (activity != null && activity.equals(ConsultDetailsActivity.class.toString())) {
//                            finish();
//                            return;
//                        }
//
//                        String tag = getIntent().getStringExtra("tag");
//                        if (tag != null && tag.equals("message")) {
//                            finish();
//                            return;
//                        }
//                        startActivity(new Intent().putExtra("utype", CacheUtils.dataUserEntity.getUtype()).setClass(getApplicationContext(), MainActivity.class));
//                        finish();
//                    } else {
//                        ToastUtils.getUtils(getApplicationContext()).show(msg);
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//        httpDataUtils.setFailBack(new FailBack() {
//
//            @Override
//            public void fail(String msg) {
//                loading.dismissDialog();
//            }
//        });
//    }
    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    private void loginOut() {
        RequestParams params = new RequestParams();
        params.addHeader("Authorization", PrefsUtils.getString(getApplicationContext(), PrefsUtils.ACCESSKEY));
        params.addHeader("Accept	", "application/json, text/javascript, */*;");
        httpDataUtils.sendGet(MyData.LOGINOUT, params);
        httpDataUtils.setSucessBack(new HttpDataUtils.SucessBack() {
            @Override
            public void sucess(ResponseInfo<String> arg0) {
                if (httpDataUtils.code(arg0.result)) {
                    // 释放缓存
                    String username = PrefsUtils.getString(LoginActivity.this, PrefsUtils.USERNAME) + "";
                    String userpassword = PrefsUtils.getString(LoginActivity.this, PrefsUtils.USERPASSROWD) + "";
                    String isremember = PrefsUtils.getString(LoginActivity.this, PrefsUtils.ISREMEMBER) + "";
                    PrefsUtils.clear(LoginActivity.this);
                    PrefsUtils.saveString(LoginActivity.this, PrefsUtils.USERNAME, username);
                    PrefsUtils.saveString(LoginActivity.this, PrefsUtils.USERPASSROWD, userpassword);
                    PrefsUtils.saveString(LoginActivity.this, PrefsUtils.ISREMEMBER, isremember);
                    PrefsUtils.saveString(LoginActivity.this, PrefsUtils.First, "1");
                    CacheUtils.dataUserEntity = null;
                    HzApplication
                            .getInstance()
                            .getUserEntityCache()
                            .put(Constans.USER_INFO, CacheUtils.dataUserEntity,
                                    86400);
                } else {
                    httpDataUtils.showMsg(arg0.result);
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
