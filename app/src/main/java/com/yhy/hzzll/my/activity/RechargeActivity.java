package com.yhy.hzzll.my.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.yhy.hzzll.R;
import com.yhy.hzzll.framework.MyData;
import com.yhy.hzzll.mian.activity.BaseActivity;
import com.yhy.hzzll.utils.HttpDataUtils;
import com.yhy.hzzll.utils.JSONTool;
import com.yhy.hzzll.utils.PrefsUtils;
import com.yhy.hzzll.utils.ToastUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

public class RechargeActivity extends BaseActivity {

//    et_recharge_count

    @ViewInject(R.id.et_recharge_count)
    EditText et_recharge_count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_treasure_recharge);
        ViewUtils.inject(this);
    }


    @OnClick({R.id.ic_back, R.id.tv_pay})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ic_back:

                finish();

                break;
            case R.id.tv_pay:

                if (et_recharge_count.getText().toString().length()==0){
                    ToastUtils.getUtils(RechargeActivity.this).show("请输入需要充值的金额。");
                    return;
                }

                createOrder();

                break;
        }
    }

    private void createOrder() {

        RequestParams params = new RequestParams();
        params.addHeader("Authorization", PrefsUtils.getString(RechargeActivity.this, PrefsUtils.AUTHORIZATION));
        params.addQueryStringParameter("amount", et_recharge_count.getText().toString());
        params.addQueryStringParameter("type","alipay_wap");
        httpDataUtils.sendPost(MyData.PAY_RECHARGE, params);
        httpDataUtils.setSucessBack(new HttpDataUtils.SucessBack() {
            @Override
            public void sucess(ResponseInfo<String> arg0) {

                try {
                    JSONObject object = new JSONObject(arg0.result);
                    String code = JSONTool.getString(object, "code");
                    String msg = JSONTool.getString(object, "message");
                    if (code.equals("0")) { // 成功

                        JSONObject data = object.getJSONObject("data");
                        String order_no = JSONTool.getString(data,"order_no");

                        gotoPay(order_no);

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

    private void gotoPay(String order_no) {

        RequestParams params = new RequestParams();
        params.addHeader("Authorization", PrefsUtils.getString(RechargeActivity.this, PrefsUtils.AUTHORIZATION));
//        params.addQueryStringParameter("amount", et_recharge_count.getText().toString());
        params.addQueryStringParameter("order_no",order_no);
        httpDataUtils.sendProgressPost(MyData.LAWYER_PAY, params);
        httpDataUtils.setSucessBack(new HttpDataUtils.SucessBack() {
            @Override
            public void sucess(ResponseInfo<String> arg0) {

                try {
                    JSONObject object = new JSONObject(arg0.result);
                    String code = JSONTool.getString(object, "code");
                    String msg = JSONTool.getString(object, "message");
                    if (code.equals("0")) { // 成功

                        final String data = JSONTool.getString(object, "data");


                        Runnable payRunnable = new Runnable() {

                            @Override
                            public void run() {
                                PayTask alipay = new PayTask(RechargeActivity.this);
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
                        Toast.makeText(RechargeActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        Toast.makeText(RechargeActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
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
