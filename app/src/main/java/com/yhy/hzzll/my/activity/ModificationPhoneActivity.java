package com.yhy.hzzll.my.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.util.Log;
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
import com.yhy.hzzll.framework.Constans;
import com.yhy.hzzll.framework.HzApplication;
import com.yhy.hzzll.framework.MyData;
import com.yhy.hzzll.mian.activity.BaseActivity;
import com.yhy.hzzll.mian.entity.DataUserEntity;
import com.yhy.hzzll.utils.HttpDataUtils;
import com.yhy.hzzll.utils.HttpDataUtils.FailBack;
import com.yhy.hzzll.utils.JSONTool;
import com.yhy.hzzll.utils.PrefsUtils;
import com.yhy.hzzll.utils.RegularUtils;
import com.yhy.hzzll.utils.ToastUtils;
import com.yhy.hzzll.utils.ValidationUtils;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 认证信息--修改手机号
 *
 * @author Yang
 */
public class ModificationPhoneActivity extends BaseActivity {
    @ViewInject(R.id.tv_phone)
    TextView tv_phone;
    @ViewInject(R.id.et_pwd)
    EditText et_pwd;
    @ViewInject(R.id.et_new_phone)
    EditText et_new_phone;
    @ViewInject(R.id.et_verfication_code)
    EditText et_verfication_code;
    @ViewInject(R.id.tv_send_verification_code)
    TextView tv_send_verification_code;
    @ViewInject(R.id.tv_modification_phone)
    TextView tv_modification_phone;
    @ViewInject(R.id.linear_old_phone_content)
    LinearLayout linear_old_phone_content;
    @ViewInject(R.id.linear_pwd_content)
    LinearLayout linear_pwd_content;
    @ViewInject(R.id.tv_phone_title)
    TextView tv_phone_title;
    @ViewInject(R.id.tv_head_title_phone)
    TextView tv_head_title_phone;

    public static final String IS_MODIFY_STATE_INTENT = "IS_MODIFY_STATE_INTENT";
    public static final String OLD_PHONE_INTENT = "OLD_PHONE_INTENT";

    /**
     * 自定义计时器
     */
    private TimeCount timer;
    private String accessKey;
    private boolean isModifyState;
    private String oldPhone;
    private String uid;

    @Override
    protected void onCreate(Bundle arg0) {
        setContentView(R.layout.activity_modification_phone);
        super.onCreate(arg0);
        ViewUtils.inject(this);
        init();
    }

    private void init() {
//        uid = PrefsUtils.getString(context, PrefsUtils.UID);
//        oldPhone = getIntent().getStringExtra(OLD_PHONE_INTENT);
//        accessKey = PrefsUtils.getString(getApplicationContext(), PrefsUtils.ACCESSKEY);
//        isModifyState = getIntent().getBooleanExtra(IS_MODIFY_STATE_INTENT, false);
//        if (isModifyState) { // 是不是修改状态
//            linear_old_phone_content.setVisibility(View.VISIBLE);
//            linear_pwd_content.setVisibility(View.VISIBLE);
//            tv_phone.setText(oldPhone);
//            tv_modification_phone.setText(R.string.btn_update_phone);
        tv_phone_title.setText(R.string.lable_new_phone_title);
        tv_head_title_phone.setText(R.string.title_head_update_phone);
//        } else {
//            linear_old_phone_content.setVisibility(View.GONE);
//            linear_pwd_content.setVisibility(View.GONE);
//            tv_modification_phone.setText(R.string.btn_confirm_ok);
//            tv_phone_title.setText(R.string.lable_phone_title);
//            tv_head_title_phone.setText(R.string.title_head_certification_phone);
//        }

    }

    @OnClick({R.id.tv_send_verification_code, R.id.tv_modification_phone})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_send_verification_code:
                String newPhone = et_new_phone.getText().toString().trim();
                if (!RegularUtils.isMobile(newPhone)) {
                    ToastUtils.getUtils(getApplicationContext()).show(getString(R.string.toast_please_phone));
                    return;
                }
                timer = new TimeCount(60000, 1000);// 构造CountDownTimer对象
                getVerify();
                break;
            case R.id.tv_modification_phone:
//                if (isModifyState) { // 修改
                lawyerChangeMobile();
//                } else { // 认证
//                    lawyerMobileCertification();
//                }
                break;
        }
    }

    /**
     * 发送验证码
     */
    private void getVerify() {
        RequestParams params = new RequestParams();
        params.addBodyParameter("mobile", et_new_phone.getText().toString());
        params.addBodyParameter("type", "USER_MODIFY_MOBILE");
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
     * 律师手机号认证
     */
    private void lawyerMobileCertification() {
        String mobile = et_new_phone.getText().toString();
        boolean isMobile = RegularUtils.isMobile(mobile);
        if (!isMobile) {
            ToastUtils.getUtils(getApplicationContext()).show(getString(R.string.toast_please_phone));
            return;
        }
        String smsCode = et_verfication_code.getText().toString();
        if (smsCode == null || smsCode.equals("")) {
            ToastUtils.getUtils(getApplicationContext()).show(getString(R.string.toast_please_input_sms_code));
            return;
        }
        RequestParams params = new RequestParams();
        params.addHeader("Authorization", PrefsUtils.getString(getApplicationContext(), PrefsUtils.ACCESSKEY));
        params.addBodyParameter("userid", PrefsUtils.getString(getApplicationContext(), PrefsUtils.UID));
        params.addBodyParameter("mobile", mobile);
        params.addBodyParameter("mobileCode", smsCode);
        httpDataUtils.sendProgressPost(MyData.LAWYER_MOBILE_CERTIFICATION, params);
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
    private void getApproveSmsCode(String newPhone) {
        RequestParams params = new RequestParams();
        params.addHeader("Authorization", accessKey);
        params.addBodyParameter("mobile", newPhone);
        httpDataUtils.sendProgressPost(MyData.GET_APPROVE_SMSCODE, params);
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

    /**
     * 律师业务 - 修改律师手机号码(需登录)
     */
    private void lawyerChangeMobile() {
        String mobile = et_new_phone.getText().toString();

        String smsCode = et_verfication_code.getText().toString();
        if (smsCode == null || smsCode.equals("")) {
            ToastUtils.getUtils(getApplicationContext()).show(getString(R.string.toast_please_input_sms_code));
            return;
        }
        RequestParams params = new RequestParams();
        params.addHeader("Authorization", PrefsUtils.getString(getApplicationContext(), PrefsUtils.AUTHORIZATION));
        params.addBodyParameter("mobile", mobile);
        params.addBodyParameter("validate_code", smsCode);

        httpDataUtils.sendProgressPost(MyData.MODIFY_PHONE, params);
        httpDataUtils.setSucessBack(new HttpDataUtils.SucessBack() {

            @Override
            public void sucess(ResponseInfo<String> arg0) {
                try {
                    JSONObject object = new JSONObject(arg0.result);
                    String code = object.optString("code");
                    String msg = object.optString("msg");
                    if (code.equals("0")) {
                        ToastUtils.getUtils(getApplicationContext()).show("修改手机号成功！");
                        finish();
                    } else {

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
    private void Logininfo() {
        // BusinessEntity、、企业用户实体
        RequestParams params = new RequestParams();
        params.addHeader("Authorization", PrefsUtils.getString(getApplicationContext(), PrefsUtils.ACCESSKEY));
        httpDataUtils.sendProgressGet(MyData.LOGININFO, params);
        httpDataUtils.setSucessBack(new HttpDataUtils.SucessBack() {

            @Override
            public void sucess(ResponseInfo<String> arg0) {
                try {
                    JSONObject object = new JSONObject(arg0.result);
                    String code = object.optString("code");
                    String msg = object.optString("msg");
                    if (code.equals("000000")) {
                        Log.e("result", object.optString("data"));
                        Gson gson = new Gson();
                        DataUserEntity userEntity = gson.fromJson(object.optString("data"), DataUserEntity.class);
                        // 将用户信息保存在缓存文件（持久化操作）
                        HzApplication.getInstance().getUserEntityCache().put(Constans.USER_INFO, userEntity);
//                        PrefsUtils.saveString(getApplicationContext(), PrefsUtils.UID, userEntity.getUserid());
                        startActivity(new Intent().setClass(context, PersonDataLawyerActivity.class));
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

    private void isExistCheckPhone(final String account) {
        if (TextUtils.isEmpty(account)) {
            ToastUtils.getUtils(ModificationPhoneActivity.this).show(getString(R.string.toast_empty_account_phone));
            return;
        }
        if (!ValidationUtils.isMobile(account)) {
            ToastUtils.getUtils(ModificationPhoneActivity.this).show(getString(R.string.toast_validation_phone));
            return;
        }
        RequestParams params = new RequestParams();
        params.addQueryStringParameter("username", account);
        HttpDataUtils httpDataUtils = new HttpDataUtils(ModificationPhoneActivity.this);
        httpDataUtils.sendProgressGet(MyData.CHECK_USERNAME, params);
        httpDataUtils.setSucessBack(new HttpDataUtils.SucessBack() {
            @Override
            public void sucess(ResponseInfo<String> arg0) {
                try {
                    JSONObject object = new JSONObject(arg0.result);
                    String code = object.optString("code");
                    String data = object.optString("data");
                    if (data.equals("0")) {
                        getApproveSmsCode(account);
                    } else {
                        ToastUtils.getUtils(ModificationPhoneActivity.this).show("该账号已存在");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
    }

}
