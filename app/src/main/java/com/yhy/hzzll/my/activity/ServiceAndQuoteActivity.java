package com.yhy.hzzll.my.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

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
import com.yhy.hzzll.utils.LogUtils;
import com.yhy.hzzll.utils.PrefsUtils;
import com.yhy.hzzll.utils.ToastUtils;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 服务与报价
 *
 * @author zsfzbc
 */
public class ServiceAndQuoteActivity extends BaseActivity {


    @ViewInject(R.id.tv_price)
    TextView tv_price;

    @ViewInject(R.id.tv_tip)
    TextView tv_tip;


    @ViewInject(R.id.cb_o_c_premiumsupport)
    CheckBox cb_o_c_premiumsupport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_service_and_quote);
        super.onCreate(savedInstanceState);
        ViewUtils.inject(this);
        init();
    }

    private void init() {

        if(getIntent().getStringExtra("office").equals("0")){
            cb_o_c_premiumsupport.setChecked(false);
        }else{
            cb_o_c_premiumsupport.setChecked(true);
        }

        if(getIntent().getStringExtra("price").length()==0){
            tv_price.setText("暂无报价");
            tv_tip.setText("点击报价");

        }else{
            tv_price.setText(getIntent().getStringExtra("price")+"元/次");
            tv_tip.setText("点击修改");
        }


        cb_o_c_premiumsupport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              submit2();
            }
        });

    }

    @OnClick({R.id.ic_back, R.id.linear_price})
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.ic_back:
                startActivity(new Intent().putExtra("tab", 4).setClass(getApplicationContext(), MainActivity.class));
                finish();
                break;

            case R.id.linear_price:
                Intent intent = new Intent();
                intent.setClass(context, PremiumSupportActivity.class)
                        .putExtra("office","")
                        .putExtra("price",tv_price.getText().toString());
                startActivityForResult(intent,1216);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1216){
            if(data!=null) {
                if (data.getStringExtra("price").equals("")){
                    tv_price.setText(data.getStringExtra("暂无报价"));
                }else{
                    tv_price.setText(data.getStringExtra("price")+"元/次");
                    tv_tip.setText("点击修改");
                    cb_o_c_premiumsupport.setChecked(true);
//                    if(!cb_o_c_premiumsupport.isChecked()){
//                        submit2();
//                    }
                }
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }


    private void submit2() {

        if (tv_price.getText().toString().equals("暂无报价")||tv_price.getText().toString().equals("")) {
            ToastUtils.getUtils(ServiceAndQuoteActivity.this).show("请先设置金额");
            cb_o_c_premiumsupport.setChecked(false);

            Intent intent = new Intent();
            intent.setClass(context, PremiumSupportActivity.class)
                    .putExtra("office","")
                    .putExtra("price",tv_price.getText().toString());
            startActivityForResult(intent,1216);


        }else{
            RequestParams params = new RequestParams();
            params.addQueryStringParameter("lawyer_id", PrefsUtils.getString(ServiceAndQuoteActivity.this, PrefsUtils.UID));
            LogUtils.logE(PrefsUtils.getString(ServiceAndQuoteActivity.this, PrefsUtils.UID));
            params.addQueryStringParameter("action", "2");

            httpDataUtils.sendProgressPost(MyData.RELEASE_PRICE, params);
            httpDataUtils.setSucessBack(new HttpDataUtils.SucessBack() {
                @Override
                public void sucess(ResponseInfo<String> arg0) {
                    try {
//                        LogUtils.logE("执行了。。。。222" + arg0.result);
                        JSONObject object = new JSONObject(arg0.result);
                        String code = object.optString("code");
                        String msg = object.optString("message");
                        if (code.equals("0")) {
                            ToastUtils.getUtils(ServiceAndQuoteActivity.this).show(msg);

                            if (msg.contains("开启")){
                                cb_o_c_premiumsupport.setChecked(true);
                            }else {
                                cb_o_c_premiumsupport.setChecked(false);
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

    }

}
