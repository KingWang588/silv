package com.yhy.hzzll.mian.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.google.gson.Gson;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.yhy.hzzll.R;
import com.yhy.hzzll.framework.MyData;
import com.yhy.hzzll.home.activity.ConsultPaySuccessActivity;
import com.yhy.hzzll.mian.entity.DataUserEntity;
import com.yhy.hzzll.my.activity.RevisePayPasswordActivity;
import com.yhy.hzzll.utils.HttpDataUtils;
import com.yhy.hzzll.utils.JSONTool;
import com.yhy.hzzll.utils.LogUtils;
import com.yhy.hzzll.utils.PrefsUtils;
import com.yhy.hzzll.utils.ToastUtils;
import com.yhy.hzzll.view.DialogPayword;
import com.yhy.hzzll.view.DialogSetupPayword;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

public class NewPayActivity extends BaseActivity {

    @ViewInject(R.id.iv_check)
    ImageView iv_check;

    @ViewInject(R.id.iv_check2)
    ImageView iv_check2;

    @ViewInject(R.id.tv_discount)
    TextView tv_discount;

    @ViewInject(R.id.tv_change)
    TextView tv_change;

    @ViewInject(R.id.tv_price_1)
    TextView tv_price_1;

    boolean select1 = false;
    boolean select2 = false;

    String type = "";//alipay_wap  balance


    String order_no;
    boolean success;
    String pay_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        ViewUtils.inject(this);

        order_no = getIntent().getStringExtra("order_no");
        success = getIntent().getBooleanExtra("success", false);
        LogUtils.logE(order_no);
        tv_price_1.setText(getIntent().getIntExtra("money", 50) + "元");
        getMoney();

    }


    @OnClick({R.id.ic_back, R.id.tv_pay, R.id.iv_check, R.id.iv_check2, R.id.rl_balance, R.id.rl_ali})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_check:
            case R.id.rl_balance:

                if (select1) {

                } else {

                    if (PrefsUtils.getString(NewPayActivity.this, PrefsUtils.ESCIT_PAYPSW).equals("0")) {

                        ToastUtils.getUtils(NewPayActivity.this).show("你还未设置安全密码。");

                        DialogSetupPayword dialogSetupPayword = new DialogSetupPayword();
                        dialogSetupPayword.showDialog(NewPayActivity.this);
                        dialogSetupPayword.setClick(new DialogSetupPayword.Clicks() {
                            @Override
                            public void clicks(int type) {
                                startActivity(new Intent(NewPayActivity.this, RevisePayPasswordActivity.class));
                            }
                        });


                        return;
                    }


                    if (select2) {
                        iv_check2.setImageResource(R.drawable.unchecked);
                        select2 = false;
                    }

                    select1 = true;
                    iv_check.setImageResource(R.drawable.checked);
                    type = "balance";

                }

                break;
            case R.id.iv_check2:
            case R.id.rl_ali:
                if (select2) {

                } else {
                    if (select1) {
                        iv_check.setImageResource(R.drawable.unchecked);
                        select1 = false;
                    }

                    iv_check2.setImageResource(R.drawable.checked);
                    type = "alipay_wap";
                    select2 = true;
                }

                break;
            case R.id.ic_back:

                finish();

                break;
            case R.id.tv_pay:

                if (type.equals("balance")) {

                    DialogPayword dialogPayword = new DialogPayword();
                    dialogPayword.showDialog(NewPayActivity.this);
                    dialogPayword.setCont3(new DialogPayword.Conts3() {
                        @Override
                        public void Money(String money) {
                            pay_password = money;

                            updataOrder();
                        }
                    });


                } else {
                    updataOrder();
                }

                break;
        }
    }


    private void getMoney() {

        RequestParams params = new RequestParams();
        params.addHeader("Authorization", PrefsUtils.getString(NewPayActivity.this, PrefsUtils.AUTHORIZATION));
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

                            DataUserEntity userEntity = gson.fromJson(arg0.result, DataUserEntity.class);
                            String m1 = userEntity.getData().getFinance().getAmt_consult();
                            m1 = m1.substring(0, m1.length() - 3);

                            String m2 = userEntity.getData().getFinance().getAmt_case();
                            m2 = m2.substring(0, m2.length() - 3);

//                            String m3 = userEntity.getData().getFinance().getAmt_freeze();
//                            m3 = m3.substring(0, m3.length() - 3);

                            String m4 = userEntity.getData().getFinance().getAmt_balance();
                            m4 = m4.substring(0, m4.length() - 3);

//                            tv_doc.setText(m1+"元");
//                            tv_working_income.setText(m2+"元");
//                            tv_frozen_money.setText(m3+"元");
//                            tv_balance.setText(m4+"元");
                            tv_discount.setText("可用余额： " + m4 + "元");




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
    }


    private void updataOrder() {

        RequestParams params = new RequestParams();
        params.addHeader("Authorization", PrefsUtils.getString(NewPayActivity.this, PrefsUtils.AUTHORIZATION));
        params.addQueryStringParameter("order_no", order_no);
        params.addQueryStringParameter("type", type);
        httpDataUtils.sendProgressPut(MyData.ORDER_UPDATE, params);
        httpDataUtils.setSucessBack(new HttpDataUtils.SucessBack() {
            @Override
            public void sucess(ResponseInfo<String> arg0) {

                try {
                    JSONObject object = new JSONObject(arg0.result);
                    String code = JSONTool.getString(object, "code");
                    String msg = JSONTool.getString(object, "message");
                    if (code.equals("0")) { // 成功

//                        JSONObject data = object.getJSONObject("data");
//                        String order_no = JSONTool.getString(data,"order_no");

                        gotoPay();

                    } else {
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


    private void gotoPay() {

        RequestParams params = new RequestParams();
        params.addHeader("Authorization", PrefsUtils.getString(NewPayActivity.this, PrefsUtils.AUTHORIZATION));
//        params.addQueryStringParameter("amount", et_recharge_count.getText().toString());
        params.addQueryStringParameter("order_no", order_no);

        if (type.equals("balance")) {
            params.addQueryStringParameter("pay_password", pay_password);
        }

        httpDataUtils.sendProgressPost(MyData.LAWYER_PAY, params);
        httpDataUtils.setSucessBack(new HttpDataUtils.SucessBack() {
            @Override
            public void sucess(ResponseInfo<String> arg0) {

                try {
                    JSONObject object = new JSONObject(arg0.result);
                    String code = JSONTool.getString(object, "code");
                    String msg = JSONTool.getString(object, "message");
                    if (code.equals("0")) { // 成功


                        if (type.equals("balance")) {


                            if (success) {
                                startActivity(new Intent(NewPayActivity.this, ConsultPaySuccessActivity.class).putExtra("order_no", order_no));
                                finish();
                            } else {
                                ToastUtils.getUtils(getApplicationContext()).show("支付成功");
                                setResult(252);
                                finish();
                            }


                        } else {
                            final String data = JSONTool.getString(object, "data");


                            Runnable payRunnable = new Runnable() {

                                @Override
                                public void run() {
                                    PayTask alipay = new PayTask(NewPayActivity.this);
                                    Map<String, String> result = alipay.payV2(data, true);
//                                Log.i("msp", result.toString());

                                    Message msg = new Message();
                                    msg.what = 1;
                                    msg.obj = result;
                                    handler.sendMessage(msg);
                                }
                            };

                            Thread payThread = new Thread(payRunnable);
                            payThread.start();
                        }


                    } else {
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

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            PayResult payResult = new PayResult((Map<String, String>) msg.obj);
            String resultInfo = payResult.getResult();// 同步返回需要验证的信息
            String resultStatus = payResult.getResultStatus();
            // 判断resultStatus 为9000则代表支付成功
            if (TextUtils.equals(resultStatus, "9000")) {
                // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。

                if (success) {
                    startActivity(new Intent(NewPayActivity.this, ConsultPaySuccessActivity.class).putExtra("order_no", order_no));
                    finish();
                } else {
                    Toast.makeText(NewPayActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                    setResult(252);
                    finish();
                }


            } else {
                // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                Toast.makeText(NewPayActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
            }

            super.handleMessage(msg);
        }

    };

//    private static final int SDK_PAY_FLAG = 1;
//    private Handler mHandler = new Handler() {
//        @SuppressWarnings("unused")
//        public void handleMessage(Message msg) {
//            switch (msg.what) {
//                case SDK_PAY_FLAG: {
//                    @SuppressWarnings("unchecked")
//                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
//                    /**
//                     对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
//                     */
//                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
//                    String resultStatus = payResult.getResultStatus();
//                    // 判断resultStatus 为9000则代表支付成功
//                    if (TextUtils.equals(resultStatus, "9000")) {
//                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
//                        Toast.makeText(RechargeActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
//                    } else {
//                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
//                        Toast.makeText(RechargeActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
//                    }
//                    break;
//                }
//
//                default:
//                    break;
//            }
//        };
//    };


    class PayResult {
        private String resultStatus;
        private String result;
        private String memo;

        public PayResult(Map<String, String> rawResult) {
            if (rawResult == null) {
                return;
            }

            for (String key : rawResult.keySet()) {
                if (TextUtils.equals(key, "resultStatus")) {
                    resultStatus = rawResult.get(key);
                } else if (TextUtils.equals(key, "result")) {
                    result = rawResult.get(key);
                } else if (TextUtils.equals(key, "memo")) {
                    memo = rawResult.get(key);
                }
            }
        }

        @Override
        public String toString() {
            return "resultStatus={" + resultStatus + "};memo={" + memo
                    + "};result={" + result + "}";
        }

        /**
         * @return the resultStatus
         */
        public String getResultStatus() {
            return resultStatus;
        }

        /**
         * @return the memo
         */
        public String getMemo() {
            return memo;
        }

        /**
         * @return the result
         */
        public String getResult() {
            return result;
        }

    }
}
