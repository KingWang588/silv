package com.yhy.hzzll.my.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.yhy.hzzll.R;
import com.yhy.hzzll.framework.MyData;
import com.yhy.hzzll.message.WebviewActivity;
import com.yhy.hzzll.mian.activity.BaseActivity;
import com.yhy.hzzll.utils.HttpDataUtils;
import com.yhy.hzzll.utils.PrefsUtils;
import com.yhy.hzzll.utils.ToastUtils;

import org.json.JSONException;
import org.json.JSONObject;

public class PremiumSupportActivity extends BaseActivity {

    @ViewInject(R.id.et_price)
    EditText et_price;

    @ViewInject(R.id.btn_submit)
    Button btn_submit;

    @ViewInject(R.id.tv_declaration_and_agreement)
    EditText tv_declaration_and_agreement;

    @ViewInject(R.id.cb_declaration_and_agreement)
    CheckBox cb_declaration_and_agreement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_premium_support);
        ViewUtils.inject(this);
        init();
    }

    private void init() {

        if (getIntent().getStringExtra("price").equals("暂无报价")||getIntent().getStringExtra("price").equals("")) {

        } else {
            String price = getIntent().getStringExtra("price");
            price = price.substring(0,price.length()-3);

            et_price.setText(price);
        }

    }

    @OnClick({R.id.ic_back, R.id.btn_submit,R.id.tv_declaration_and_agreement})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_declaration_and_agreement:

                startActivity(new Intent().putExtra("title", "私律专属咨询声明与协议")
                        .putExtra("url", MyData.EXCLUSIVE_CONSULTATION_AGREEMENT)
                        .setClass(PremiumSupportActivity.this, WebviewActivity.class));

                break;

            case R.id.ic_back:

//                Intent mIntent = new Intent();
//                mIntent.putExtra("price", et_price.getText().toString());
//                setResult(RESULT_OK, mIntent);

                finish();

                break;

            case R.id.btn_submit:

                submit();

                break;
        }
    }

    private void submit() {

        RequestParams params = new RequestParams();
        params.addQueryStringParameter("lawyer_id", PrefsUtils.getString(PremiumSupportActivity.this, PrefsUtils.UID));

        int money =0;

        if (et_price.getText().toString().length()!=0){

            try{
                money = Integer.valueOf(et_price.getText().toString());
            }catch (Exception e){
                ToastUtils.getUtils(PremiumSupportActivity.this).show("请输入整数");
            }

        }else{
            ToastUtils.getUtils(PremiumSupportActivity.this).show("请输入金额。");
            return;
        }

        if (money<38||money>99){
            ToastUtils.getUtils(PremiumSupportActivity.this).show("请输入报价金额在38-99之间（整数）。");
            return;
        }

        if (!cb_declaration_and_agreement.isChecked()){
            ToastUtils.getUtils(PremiumSupportActivity.this).show("请查看并同意私律专属咨询声明与协议。");
            return;
        }

        params.addQueryStringParameter("price", et_price.getText().toString());

        if (getIntent().getStringExtra("price").equals("暂无报价")) {
            params.addQueryStringParameter("action", "0");
        } else {
            params.addQueryStringParameter("action", "1");
        }

        httpDataUtils.sendProgressPost(MyData.RELEASE_PRICE, params);
        httpDataUtils.setSucessBack(new HttpDataUtils.SucessBack() {
            @Override
            public void sucess(ResponseInfo<String> arg0) {
                try {

                    JSONObject object = new JSONObject(arg0.result);
                    String code = object.optString("code");
                    String msg = object.optString("message");
                    if (code.equals("0")) {
//                        ToastUtils.getUtils(PremiumSupportActivity.this).show("报价成功！记得开启咨询哦！");

                        Intent mIntent = new Intent();
                        mIntent.putExtra("price", et_price.getText().toString());
                        setResult(RESULT_OK, mIntent);

                        finish();

                    } else {
                        ToastUtils.getUtils(PremiumSupportActivity.this).show(msg);
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
