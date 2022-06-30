package com.yhy.hzzll.mian.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.alibaba.sdk.android.push.CloudPushService;
import com.alibaba.sdk.android.push.CommonCallback;
import com.google.gson.Gson;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
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
import com.yhy.hzzll.my.activity.ServiceAndQuoteActivity;
import com.yhy.hzzll.utils.CacheUtils;
import com.yhy.hzzll.utils.HttpDataUtils;
import com.yhy.hzzll.utils.LogUtils;
import com.yhy.hzzll.utils.PrefsUtils;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterfourthActivity extends BaseActivity {


    HzApplication hzApplication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registerfourth);
        hzApplication = (HzApplication) getApplication();
        ViewUtils.inject(this);
//        logininfo();

    }

    @OnClick({R.id.tv_complete_quote, R.id.tv_home})
    public void onclick(View view) {
        switch (view.getId()) {
            case R.id.tv_home:

                startActivity(new Intent().putExtra("tab", 0).setClass(getApplicationContext(), MainActivity.class));

                break;

            case R.id.tv_complete_quote:
                Intent intent = new Intent();
                intent.setClass(RegisterfourthActivity.this, ServiceAndQuoteActivity.class)
                        .putExtra("office", "0")
                        .putExtra("price", "");
                startActivityForResult(intent, 1216);
                break;
        }
    }

    /**
     * 获取登录信息
     */
    private void logininfo() {

        RequestParams params = new RequestParams();
        params.addHeader("Authorization", PrefsUtils.getString(RegisterfourthActivity.this, PrefsUtils.AUTHORIZATION));
        httpDataUtils.sendGet(MyData.CHECK_OUT_USER_INFO, params);
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

                            PrefsUtils.saveString(RegisterfourthActivity.this, PrefsUtils.ACCESSKEY, userEntity.getData().getInfo().getOld_user_info().getHash());
                            PrefsUtils.saveString(RegisterfourthActivity.this, PrefsUtils.OLD_UID, userEntity.getData().getInfo().getOld_user_info().getOld_user_id() + "");

                            PrefsUtils.saveString(RegisterfourthActivity.this, PrefsUtils.UPHONE, userEntity.getData().getInfo().getMobile());
                            PrefsUtils.saveString(RegisterfourthActivity.this, PrefsUtils.UNAME, userEntity.getData().getInfo().getTruename());
                            PrefsUtils.saveString(RegisterfourthActivity.this, PrefsUtils.ADDRESS, userEntity.getData().getInfo().getBase_region());
                            PrefsUtils.saveString(RegisterfourthActivity.this, PrefsUtils.SPECIAL, userEntity.getData().getInfo().getLawyer_secpical());
                            PrefsUtils.saveString(RegisterfourthActivity.this, PrefsUtils.AVATAR, userEntity.getData().getInfo().getAvatar());

                            PrefsUtils.saveString(RegisterfourthActivity.this, PrefsUtils.UID, userEntity.getData().getInfo().getId() + "");
                            PrefsUtils.saveString(RegisterfourthActivity.this, PrefsUtils.ACCID, userEntity.getData().getInfo().getIm_token().getAccid());
                            PrefsUtils.saveString(RegisterfourthActivity.this, PrefsUtils.USERTOKEN, userEntity.getData().getInfo().getIm_token().getToken());
                            PrefsUtils.saveString(RegisterfourthActivity.this, PrefsUtils.CONSULT_PRICE, userEntity.getData().getInfo().getOffer_info().getPrice());
                            PrefsUtils.saveString(RegisterfourthActivity.this, PrefsUtils.CONSULT_OPEN, userEntity.getData().getInfo().getOffer_info().getExclusive_consult_isprice());


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


                            doLogin(userEntity.getData().getInfo().getIm_token().getAccid(), userEntity.getData().getInfo().getIm_token().getToken());

                        } catch (Exception e) {


                        }
                    } else {
                        // ToastUtils.getUtils(RegisterfourthActivity.this).show(msg);
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
                    }

                    @Override
                    public void onFailed(int code) {
                        LogUtils.logE(code + "》》。。。。。。");
                    }

                    @Override
                    public void onException(Throwable exception) {

                    }
                    // 可以在此保存LoginInfo到本地，下次启动APP做自动登录用
                };
        NIMClient.getService(AuthService.class).login(info).setCallback(callback);
    }

    @Override
    public void onBackPressed() {

        return;
    }

    private void bind(String id) {
        RequestParams params = new RequestParams();
        params.addHeader("Authorization", PrefsUtils.getString(RegisterfourthActivity.this, PrefsUtils.AUTHORIZATION));
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
}
