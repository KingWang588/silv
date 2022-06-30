package com.yhy.hzzll.my.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.yhy.hzzll.R;
import com.yhy.hzzll.framework.MyData;
import com.yhy.hzzll.mian.activity.BaseActivity;
import com.yhy.hzzll.mian.activity.MainActivity;
import com.yhy.hzzll.utils.HttpDataUtils;
import com.yhy.hzzll.utils.PrefsUtils;
import com.yhy.hzzll.utils.ToastUtils;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 设置--意见反馈
 *
 * @author Yang
 */
public class FeedbackActivity extends BaseActivity {

    @ViewInject(R.id.et_description)
    EditText et_description;

    @Override
    protected void onCreate(Bundle arg0) {
        setContentView(R.layout.activity_feedback);
        ViewUtils.inject(this);
        super.onCreate(arg0);
    }

    @OnClick({R.id.tv_submit_feedback, R.id.tv_home})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_home:
                startActivity(new Intent().putExtra("tab", 0).setClass(getApplicationContext(), MainActivity.class));
                break;

            case R.id.tv_submit_feedback:
                feedback();
                break;
        }
    }

    /**
     * 意见反馈
     */
    private void feedback() {

        String summary = et_description.getText().toString();
        if (summary == null || summary.equals("")) {
            ToastUtils.getUtils(getApplicationContext()).show(getString(R.string.toast_please_description));
            return;
        }

        if (summary.length() < 5 || summary.length() > 250) {
            ToastUtils.getUtils(getApplicationContext()).show("请用 5-300 个字描述您的问题。 ");
            return;
        }

        RequestParams params = new RequestParams();
        params.addHeader("Authorization", PrefsUtils.getString(getApplicationContext(), PrefsUtils.AUTHORIZATION));
        params.addBodyParameter("content", summary);
        httpDataUtils.sendProgressPost(MyData.CREATE_FEEDBACK, params);
        httpDataUtils.setSucessBack(new HttpDataUtils.SucessBack() {

            @Override
            public void sucess(ResponseInfo<String> arg0) {
                try {
                    JSONObject object = new JSONObject(arg0.result);
                    String code = object.optString("code");
                    String msg = object.optString("message");
                    ToastUtils.getUtils(getApplicationContext()).show(msg);
                    if (code.equals("0")) {
                        ToastUtils.getUtils(FeedbackActivity.this).show("提交意见反馈成功！");
                        finish();
                    } else {

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
