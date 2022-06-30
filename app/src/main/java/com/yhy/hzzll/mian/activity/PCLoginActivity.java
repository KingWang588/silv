package com.yhy.hzzll.mian.activity;

import android.os.Bundle;
import android.view.View;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.yhy.hzzll.R;
import com.yhy.hzzll.entity.IncomeAndExpensesEntity;
import com.yhy.hzzll.framework.MyData;
import com.yhy.hzzll.utils.HttpDataUtils;
import com.yhy.hzzll.utils.JSONTool;
import com.yhy.hzzll.utils.LogUtils;
import com.yhy.hzzll.utils.PrefsUtils;
import com.yhy.hzzll.utils.ToastUtils;

import org.json.JSONException;
import org.json.JSONObject;

public class PCLoginActivity extends BaseActivity {

    String sid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pclogin);
        ViewUtils.inject(this);
        sid = getIntent().getStringExtra("sid");
    }

    @OnClick({R.id.btn_login_pc, R.id.tv_cancle_login})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login_pc:
                gotologin();
                break;
            case R.id.tv_cancle_login:
                finish();
                break;

        }
    }

    private void gotologin() {
        RequestParams params = new RequestParams();
        params.addQueryStringParameter("qruuid", sid);
        params.addQueryStringParameter("user_token", PrefsUtils.getString(PCLoginActivity.this, PrefsUtils.AUTHORIZATION_NO_B));
        params.addQueryStringParameter("user_id", PrefsUtils.getString(PCLoginActivity.this, PrefsUtils.UID));
        httpDataUtils.sendGet(MyData.APP_QCODE_RETURN, params);
        httpDataUtils.setSucessBack(new HttpDataUtils.SucessBack() {

            @Override
            public void sucess(ResponseInfo<String> arg0) {
                try {
                    JSONObject object = new JSONObject(arg0.result);
                    String code = JSONTool.getString(object, "code");
                    String msg = JSONTool.getString(object, "msg");
                    if (code.equals("0")) { // 成功
                        ToastUtils.getUtils(PCLoginActivity.this).show("登录成功！");
                        finish();
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
}
