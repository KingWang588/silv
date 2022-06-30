package com.yhy.hzzll.my.activity;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.alibaba.sdk.android.man.MANService;
import com.alibaba.sdk.android.man.MANServiceProvider;
import com.alibaba.sdk.android.push.CommonCallback;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.auth.AuthService;
import com.yhy.hzzll.R;
import com.yhy.hzzll.framework.Constans;
import com.yhy.hzzll.framework.HzApplication;
import com.yhy.hzzll.framework.MyData;
import com.yhy.hzzll.message.WebviewActivity;
import com.yhy.hzzll.mian.activity.BaseActivity;
import com.yhy.hzzll.mian.activity.LoginActivity;
import com.yhy.hzzll.mian.activity.RegisterSecondActivity;
import com.yhy.hzzll.mian.activity.RegisterThirdActivity;
import com.yhy.hzzll.mian.entity.DataUserEntity;
import com.yhy.hzzll.my.view.DialogRealname;
import com.yhy.hzzll.my.view.DialogTipRealname;
import com.yhy.hzzll.utils.CacheUtils;
import com.yhy.hzzll.utils.HttpDataUtils;
import com.yhy.hzzll.utils.JSONTool;
import com.yhy.hzzll.utils.LogUtils;
import com.yhy.hzzll.utils.MyActivityManager;
import com.yhy.hzzll.utils.PrefsUtils;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 设置
 *
 * @author Yang
 */
public class SettingActivity extends BaseActivity {

    @ViewInject(R.id.tv_exit)
    private TextView tv_exit;
    DataUserEntity userEntity;
    @ViewInject(R.id.tv_set_payword)
    TextView tv_set_payword;
    @ViewInject(R.id.switch_disturb)
    Switch switchWindow;



    @ViewInject(R.id.tv_rise_in_rank)
    TextView tv_rise_in_rank;

    @ViewInject(R.id.rl_privete)
    View rl_privete;

    @ViewInject(R.id.rl_userdeal)
    View rl_userdeal;

    @Override
    protected void onPause() {
        userEntity = (DataUserEntity) HzApplication.getInstance().getUserEntityCache().getAsObject(Constans.USER_INFO);
        super.onPause();
    }


    @Override
    protected void onResume() {
        userEntity = (DataUserEntity) HzApplication.getInstance().getUserEntityCache().getAsObject(Constans.USER_INFO);
        super.onResume();
    }

    @Override
    protected void onCreate(Bundle arg0) {
        setContentView(R.layout.activity_setting);
        super.onCreate(arg0);
        ViewUtils.inject(this);

        if (PrefsUtils.getString(SettingActivity.this, PrefsUtils.ESCIT_PAYPSW).equals("0")) {
            tv_set_payword.setText("设置安全密码");
        } else {
            tv_set_payword.setText("修改安全密码");
        }

        rl_privete.setOnClickListener(view -> {
            startActivity(new Intent().putExtra("title", "隐私声明")
                    .putExtra("url", MyData.PRIVACY_STATEMENT)
                    .setClass(SettingActivity.this, WebviewActivity.class));
        });

        rl_userdeal.setOnClickListener(view -> {
            startActivity(new Intent().putExtra("title", "用户协议")
                    .putExtra("url", MyData.AGREEMENT_OF_USAGE)
                    .setClass(SettingActivity.this, WebviewActivity.class));
        });

        if (PrefsUtils.getString(SettingActivity.this, PrefsUtils.LAWYER_TYPE).equals("执业律师")) {
            tv_rise_in_rank.setVisibility(View.INVISIBLE);
        } else {
            tv_rise_in_rank.setVisibility(View.VISIBLE);
        }

        if (PrefsUtils.getString(context, PrefsUtils.AUTHORIZATION) == null || PrefsUtils.getString(context, PrefsUtils.AUTHORIZATION).isEmpty()) {
            Resources resources = context.getResources();
            Drawable drawable = resources.getDrawable(R.color.grey);
            tv_exit.setBackgroundDrawable(drawable);
            tv_exit.setTextColor(Color.WHITE);
            tv_exit.setClickable(false);
        }
        final HzApplication hzApplication = (HzApplication) getApplication();
        String swit = PrefsUtils.getString(SettingActivity.this, PrefsUtils.OFF_PUSH);
        if (TextUtils.equals("1",swit)){
            switchWindow.setChecked(true);
        }else{
            switchWindow.setChecked(false);
        }
        switchWindow.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    PrefsUtils.saveString(SettingActivity.this, PrefsUtils.OFF_PUSH,"1");
                    hzApplication.getPushService().turnOnPushChannel(new CommonCallback() {
                        @Override
                        public void onSuccess(String s) {
                            LogUtils.logE("没有登录状态，退送关闭！");
                        }

                        @Override
                        public void onFailed(String s, String s1) {

                        }
                    });

                }else{
                    PrefsUtils.saveString(SettingActivity.this, PrefsUtils.OFF_PUSH,"0");
                    hzApplication.getPushService().turnOffPushChannel(new CommonCallback() {
                        @Override
                        public void onSuccess(String s) {
                            LogUtils.logE("开启推送成功！");
                        }

                        @Override
                        public void onFailed(String s, String s1) {

                        }
                    });
                }
            }
        });
    }

    @OnClick({R.id.rl_persondata, R.id.rl_about, R.id.rl_feedback, R.id.rl_help, R.id.tv_exit, R.id.rl_revise_password, R.id.rl_revisepay_password,
            R.id.rl_realnames, R.id.rl_phonenames, R.id.rl_emailnames, R.id.rl_check})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_realnames:

                if (PrefsUtils.getString(SettingActivity.this, PrefsUtils.AUTHORIZATION) == null || PrefsUtils.getString(SettingActivity.this, PrefsUtils.AUTHORIZATION).isEmpty()) {
                    startActivity(new Intent(SettingActivity.this, LoginActivity.class).putExtra("activity", SettingActivity.class.toString()));
                    return;
                }

                checkAuthInfo();

                break;
            case R.id.rl_phonenames:

                if (PrefsUtils.getString(SettingActivity.this, PrefsUtils.AUTHORIZATION) == null || PrefsUtils.getString(SettingActivity.this, PrefsUtils.AUTHORIZATION).isEmpty()) {
                    startActivity(new Intent(SettingActivity.this, LoginActivity.class).putExtra("activity", SettingActivity.class.toString()));
                    return;
                }

                Intent authenticationPhoneIntent = new Intent(SettingActivity.this, AuthenticationPhoneActivity.class);
                startActivity(authenticationPhoneIntent);

                break;
            case R.id.rl_emailnames:

                if (PrefsUtils.getString(SettingActivity.this, PrefsUtils.AUTHORIZATION) == null || PrefsUtils.getString(SettingActivity.this, PrefsUtils.AUTHORIZATION).isEmpty()) {
                    startActivity(new Intent(SettingActivity.this, LoginActivity.class).putExtra("activity", SettingActivity.class.toString()));
                    return;
                }

                if (!PrefsUtils.getString(SettingActivity.this, PrefsUtils.EMAIl).equals("")) { // 如果邮箱已经认证
                    Intent authenticationEmailIntent = new Intent(SettingActivity.this, AuthenticationEmailActivity.class);
                    startActivity(authenticationEmailIntent);
                } else { // 没有认证
                    Intent modificationEmailintent = new Intent(SettingActivity.this, ModificationEmailActivity.class);
                    modificationEmailintent.putExtra(ModificationEmailActivity.IS_MODIFY_STATE_INTENT, false);
                    startActivity(modificationEmailintent);
                }
                break;

            case R.id.rl_revise_password:

                if (PrefsUtils.getString(SettingActivity.this, PrefsUtils.AUTHORIZATION) == null || PrefsUtils.getString(SettingActivity.this, PrefsUtils.AUTHORIZATION).isEmpty()) {
                    startActivity(new Intent(SettingActivity.this, LoginActivity.class).putExtra("activity", SettingActivity.class.toString()));
                    return;
                }

                startActivity(new Intent().setClass(SettingActivity.this, RevisePasswordActivity.class));
                break;
            case R.id.rl_revisepay_password:

                if (PrefsUtils.getString(SettingActivity.this, PrefsUtils.AUTHORIZATION) == null || PrefsUtils.getString(SettingActivity.this, PrefsUtils.AUTHORIZATION).isEmpty()) {
                    startActivity(new Intent(SettingActivity.this, LoginActivity.class).putExtra("activity", SettingActivity.class.toString()));
                    return;
                }

                startActivity(new Intent().setClass(SettingActivity.this, RevisePayPasswordActivity.class));

                break;
            case R.id.rl_persondata:

                if (PrefsUtils.getString(SettingActivity.this, PrefsUtils.AUTHORIZATION) == null || PrefsUtils.getString(SettingActivity.this, PrefsUtils.AUTHORIZATION).isEmpty()) {
                    Log.e("TAG", "onClick: toLogin" );
                    startActivity(new Intent(SettingActivity.this, LoginActivity.class).putExtra("activity", SettingActivity.class.toString()));
                    return;
                }

                startActivity(new Intent().setClass(SettingActivity.this, PersonDataLawyerActivity.class));

                break;
            case R.id.rl_about:

                startActivity(new Intent().setClass(SettingActivity.this, AboutVersionActivity.class));

                break;
            case R.id.rl_feedback:

                startActivity(new Intent().setClass(SettingActivity.this, FeedbackActivity.class));
                break;

            case R.id.rl_help:

                startActivity(new Intent().setClass(SettingActivity.this, HelpActivity.class));

                break;
            case R.id.tv_exit:// 退出登录
                loginOut();
                break;

        }
    }


    private void checkAuthInfo() {
        RequestParams params = new RequestParams();
        params.addHeader("Authorization", PrefsUtils.getString(SettingActivity.this, PrefsUtils.AUTHORIZATION));
        httpDataUtils.sendGet(MyData.CHECK_USER_AUTH_INFO, params);
        httpDataUtils.setSucessBack(new HttpDataUtils.SucessBack() {
            @Override
            public void sucess(ResponseInfo<String> arg0) {

                try {

                    JSONObject object = new JSONObject(arg0.result);
                    String code = JSONTool.getString(object, "code");
                    String msg = JSONTool.getString(object, "msg");
                    if (code.equals("0")) { // 成功
                        JSONObject data = JSONTool.getJsonObject(object, "data");
                        boolean status = JSONTool.getBoolean(data, "status");
                        String step = JSONTool.getString(data, "step");
                        String name = JSONTool.getString(data, "name");
                        String authcode = JSONTool.getString(data, "code");
                        String reason = JSONTool.getString(data, "reason");

                        PrefsUtils.saveString(SettingActivity.this, PrefsUtils.AUTH_CODE, authcode);

                        if (authcode.equals("NOT_CERTIFIED")) {
                            if (step.equals("1")) { //没有填写认证资料

                                DialogTipRealname dialogTips = new DialogTipRealname();
                                dialogTips.showDialog(SettingActivity.this, new DialogTipRealname.Click() {
                                    @Override
                                    public void buy() {
                                        startActivity(new Intent(SettingActivity.this, RegisterSecondActivity.class));
                                    }
                                });

                            } else if (step.equals("2")) {//没有上传用户资料

                                DialogTipRealname dialogTips = new DialogTipRealname();
                                dialogTips.showDialog(SettingActivity.this, new DialogTipRealname.Click() {
                                    @Override
                                    public void buy() {
                                        startActivity(new Intent(SettingActivity.this, RegisterThirdActivity.class));
                                    }
                                });

                            }
                        } else if (authcode.equals("AUDIT_FAILED")) {

                            DialogRealname dialogTips = new DialogRealname();
                            dialogTips.showDialog(SettingActivity.this, new DialogRealname.Click() {
                                @Override
                                public void buy() {
                                    startActivity(new Intent(SettingActivity.this, RegisterSecondActivity.class));
                                }
                            }, reason);

                        } else {

                            Intent modificationUserIntent = new Intent(SettingActivity.this, AuthenticatRealLawActivity.class);
                            modificationUserIntent.putExtra("authCodeauthCode", authcode);
                            startActivity(modificationUserIntent);
                        }

                    } else {

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
    }


    private void loginOut() {

        NIMClient.getService(AuthService.class).logout();

        String username = PrefsUtils.getString(context, PrefsUtils.USERNAME) + "";
        String userpassword = PrefsUtils.getString(context, PrefsUtils.USERPASSROWD) + "";
        String isremember = PrefsUtils.getString(context, PrefsUtils.ISREMEMBER) + "";

        MANService manService = MANServiceProvider.getService();
        manService.getMANAnalytics().updateUserAccount(PrefsUtils.getString(SettingActivity.this, PrefsUtils.UNAME), PrefsUtils.getString(SettingActivity.this, PrefsUtils.UID));

        PrefsUtils.saveString(context, PrefsUtils.USERNAME, username);
        PrefsUtils.saveString(context, PrefsUtils.USERPASSROWD, userpassword);
        PrefsUtils.saveString(context, PrefsUtils.ISREMEMBER, isremember);
        PrefsUtils.saveString(context, PrefsUtils.UID, "");
        PrefsUtils.saveString(context, PrefsUtils.OLD_UID, "");

        CacheUtils.dataUserEntity = null;
        HzApplication.getInstance().getUserEntityCache().put(Constans.USER_INFO, CacheUtils.dataUserEntity, 86400);
        final HzApplication hzApplication = (HzApplication) getApplication();
        hzApplication.getPushService().unbindAccount(new CommonCallback() {
            @Override
            public void onSuccess(String s) {
                unbind(hzApplication.getPushService().getDeviceId());
            }

            @Override
            public void onFailed(String s, String s1) {
                PrefsUtils.saveString(context, PrefsUtils.AUTHORIZATION, "");
                PrefsUtils.saveString(context, PrefsUtils.REFRESH_TOKEN, "");

                MyActivityManager.getInstance().finishAllActivity();
                Intent intent = new Intent();
                intent.setClass(context, LoginActivity.class);
                startActivity(intent);
//                PrefsUtils.clear(SettingActivity.this);
                finish();
            }
        });
        // 关闭所有在栈里面的activity


    }

    private void unbind(String id) {
        RequestParams params = new RequestParams();
        params.addHeader("Authorization", PrefsUtils.getString(getApplicationContext(), PrefsUtils.AUTHORIZATION));
        params.addHeader("Content-Type", "application/x-www-form-urlencoded");
        params.addQueryStringParameter("device_id", id);
        HttpUtils httpUtils = new HttpUtils();
        httpUtils.send(HttpMethod.DELETE, MyData.DEVICE, params, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                PrefsUtils.saveString(context, PrefsUtils.AUTHORIZATION, "");
                PrefsUtils.saveString(context, PrefsUtils.REFRESH_TOKEN, "");
                PrefsUtils.saveString(context, PrefsUtils.ACCESSKEY, "");
                MyActivityManager.getInstance().finishAllActivity();
                Intent intent = new Intent();
                intent.setClass(context, LoginActivity.class);
                startActivity(intent);
//                PrefsUtils.clear(SettingActivity.this);
                finish();
//                LogUtils.logE(responseInfo.result);
            }

            @Override
            public void onFailure(HttpException e, String s) {

            }
        });
    }
}
