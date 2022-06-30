package com.yhy.hzzll.my.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.yhy.hzzll.R;
import com.yhy.hzzll.framework.MyData;
import com.yhy.hzzll.home.activity.newcollaborate.CollaborationPublishActivity;
import com.yhy.hzzll.home.activity.newcollaborate.PickUpCaseActivity;
import com.yhy.hzzll.interfacelib.Connect;
import com.yhy.hzzll.mian.activity.BaseActivity;
import com.yhy.hzzll.mian.activity.NewPayActivity;
import com.yhy.hzzll.my.entity.CollOrderDetialsEntity;
import com.yhy.hzzll.utils.HttpDataUtils;
import com.yhy.hzzll.utils.JSONTool;
import com.yhy.hzzll.utils.PrefsUtils;
import com.yhy.hzzll.utils.ToastUtils;
import com.yhy.hzzll.view.DialogComplaint;
import com.yhy.hzzll.view.DialogCooperateAgreeGiveup;
import com.yhy.hzzll.view.DialogCooperateApplyCollaboration;
import com.yhy.hzzll.view.DialogCooperateCancle;
import com.yhy.hzzll.view.DialogCooperateSurePay;

import org.json.JSONException;
import org.json.JSONObject;

public class CollaborativeOrderDetailsActivity extends BaseActivity {

    String order_no;
    String private_order_no;
    String consult_id;

    @ViewInject(R.id.tv_order_no)
    TextView tv_order_no;

    @ViewInject(R.id.rl_real_pay)
    RelativeLayout rl_real_pay;

    @ViewInject(R.id.tv_create_time)
    TextView tv_create_time;
    @ViewInject(R.id.tv_order_type)
    TextView tv_order_type;

    @ViewInject(R.id.tv_payed_user)
    TextView tv_payed_user;

    @ViewInject(R.id.linear_income)
    LinearLayout linear_income;

    @ViewInject(R.id.linear_dingjin)
    LinearLayout linear_dingjin;


    @ViewInject(R.id.linear_money)
    LinearLayout linear_money;

    @ViewInject(R.id.tv_price)
    TextView tv_price;

    @ViewInject(R.id.tv_status)
    TextView tv_status;

    @ViewInject(R.id.tv_tips)
    TextView tv_tips;

    @ViewInject(R.id.tv_real_money)
    TextView tv_real_money;

    @ViewInject(R.id.tv_complaint)
    TextView tv_complaint;
    @ViewInject(R.id.tv_type_money)
    TextView tv_type_money;

    @ViewInject(R.id.tv_cancle)
    TextView tv_cancle;
    @ViewInject(R.id.btn_go_to_pay)
    Button btn_go_to_pay;

    String is_my = "";
    String is_public = "";

    String order;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collaborative_order_details);
        ViewUtils.inject(this);
        order_no = getIntent().getStringExtra("private_order_no");

        getDetials();

    }


    @Override
    protected void onResume() {

//        getDetials();

        super.onResume();
    }

    private void getDetials() {
        RequestParams params = new RequestParams();
        params.addHeader("Authorization", PrefsUtils.getString(CollaborativeOrderDetailsActivity.this, PrefsUtils.AUTHORIZATION));

        httpDataUtils.sendGet(MyData.COLL_ORDER_DETAILS + order_no, params);
        httpDataUtils.setSucessBack(new HttpDataUtils.SucessBack() {
            @Override
            public void sucess(ResponseInfo<String> arg0) {

                try {
                    JSONObject object = new JSONObject(arg0.result);
                    String code = JSONTool.getString(object, "code");
                    String msg = JSONTool.getString(object, "message");
                    if (code.equals("0")) { // 成功
                        Gson gson = new Gson();
                        CollOrderDetialsEntity entity = gson.fromJson(arg0.result, CollOrderDetialsEntity.class);
                        tv_order_no.setText(entity.getData().getOrder_info().getOrder_no());
                        order = entity.getData().getOrder_info().getPrivate_order_no();
                        tv_create_time.setText(entity.getData().getOrder_info().getData_time());
                        tv_order_type.setText(entity.getData().getOrder_info().getType_name());
                        tv_payed_user.setText(entity.getData().getOrder_info().getNickname());
                        tv_price.setText(entity.getData().getOrder_info().getAmount());
                        String order_status = entity.getData().getOrder_info().getOrder_status();
                        tv_status.setText(order_status);
                        private_order_no = entity.getData().getOrder_info().getPrivate_order_no();
                        consult_id = entity.getData().getOrder_info().getConsult_id() + "";

                        if (entity.getData().getOrder_info().getType_name().equals("聘请律师")){
                            is_my = entity.getData().getOrder_info().getIs_my();
                        }

                        is_public = entity.getData().getOrder_info().getIs_public();

                        if (entity.getData().getOrder_info().getType_name().equals("聘请律师") && is_public.equals("1")) {
                            linear_dingjin.setVisibility(View.VISIBLE);
                        }

                        if (is_my.equals("1") && entity.getData().getOrder_info().getOrder_status().equals("待付定金")) {
                            tv_cancle.setVisibility(View.VISIBLE);
                        }

                        if (is_my.equals("1") && entity.getData().getOrder_info().getOrder_status().equals("待托管")) {
                            tv_cancle.setVisibility(View.VISIBLE);
                        }

                        if (is_my.equals("1")) {
                            tv_type_money.setText("支付信息");


                            if (order_status.equals("待付定金")) {
                                tv_real_money.setText("0.00元");
                            } else if (order_status.equals("已取消")) {


                                linear_income.removeAllViews();

                                View view = View.inflate(CollaborativeOrderDetailsActivity.this, R.layout.item_income_account_date, null);
                                TextView tv_title = (TextView) view.findViewById(R.id.tv_title);
                                TextView tv_amount = (TextView) view.findViewById(R.id.tv_amount);

                                tv_title.setText("支付定金");
                                String m1 = entity.getData().getOrder_info().getAmount_d();
                                tv_amount.setText(m1 + "元");
                                linear_income.addView(view);

                                tv_real_money.setText("50元");

                            } else {
                                linear_income.removeAllViews();
                                for (int i = 0; i < 3; i++) {
                                    View view = View.inflate(CollaborativeOrderDetailsActivity.this, R.layout.item_income_account_date, null);
                                    TextView tv_title = (TextView) view.findViewById(R.id.tv_title);
                                    TextView tv_amount = (TextView) view.findViewById(R.id.tv_amount);

                                    if (i == 0 && entity.getData().getOrder_info().getAmount_d() != null) {
                                        tv_title.setText("支付定金");

                                        String m1 = entity.getData().getOrder_info().getAmount_d();
                                        tv_amount.setText(m1 + "元");
                                        linear_income.addView(view);
                                    }

                                    if (i == 1 && entity.getData().getOrder_info().getAmount_employ() != null) {
                                        tv_title.setText("支付酬金");

                                        String m1 = entity.getData().getOrder_info().getAmount_employ();
                                        tv_amount.setText(m1 + "元");
                                        linear_income.addView(view);
                                    }

                                    if (i == 2 && entity.getData().getOrder_info().getAmount_discount_employ() != null) {
                                        tv_title.setText("优惠卷");

                                        String m1 = entity.getData().getOrder_info().getAmount_discount_employ();
                                        tv_amount.setText(m1 + "元");
                                        linear_income.addView(view);
                                    }

                                }

                                tv_real_money.setText(entity.getData().getOrder_info().getAmount_pay_employ() + "元");
                            }


                        } else {
                            tv_type_money.setText("收益信息");

                            if (entity.getData().getOrder_info().getOrder_status().equals("已完成")) {

                                if (entity.getData().getIncome_lists() != null && entity.getData().getIncome_lists().size() != 0) {

                                    linear_income.removeAllViews();
                                    for (int i = 0; i < entity.getData().getIncome_lists().size(); i++) {
                                        View view = View.inflate(CollaborativeOrderDetailsActivity.this, R.layout.item_income_account_date, null);
                                        TextView tv_title = (TextView) view.findViewById(R.id.tv_title);
                                        TextView tv_amount = (TextView) view.findViewById(R.id.tv_amount);
                                        tv_title.setText(entity.getData().getIncome_lists().get(i).getTitle());

                                        String m1 = entity.getData().getIncome_lists().get(i).getAmount();
                                        tv_amount.setText(m1 + "元");

                                        linear_income.addView(view);
                                    }
                                }

                                rl_real_pay.setVisibility(View.GONE);

//                                tv_real_money.setText(entity.getData().getIncome_total() + "元");
                            } else {
                                linear_money.setVisibility(View.GONE);
                            }
                        }

                        showTips(entity.getData().getOrder_info().getStatus_id(), is_my);

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

    private void showTips(int id, String is_my) {

        if (is_my.equals("1")) {
            tv_tips.setVisibility(View.GONE);
        }

        btn_go_to_pay.setVisibility(View.GONE);


        switch (id) {


            case 1:

                break;
            case 2:

                break;
            case 3:

                break;
            case 4:

                break;
            case 5:

                if (is_my.equals("1")) {
                    tv_complaint.setVisibility(View.VISIBLE);
                } else {
                    tv_complaint.setVisibility(View.GONE);
                }

//                tv_complaint.setVisibility(View.VISIBLE);
                break;
            case 6://已完成

                break;
            case 7:

                break;
            case 8:

                break;
            case 9:

                break;
            case 11:

                btn_go_to_pay.setVisibility(View.VISIBLE);

                break;


        }

    }


    @OnClick({R.id.ic_back, R.id.tv_chack, R.id.tv_complaint, R.id.btn_go_to_pay, R.id.tv_cancle})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_cancle:


                DialogCooperateCancle dialogCooperateSurePay = new DialogCooperateCancle();
                dialogCooperateSurePay.showDialog(CollaborativeOrderDetailsActivity.this, new DialogCooperateCancle.Click() {
                    @Override
                    public void click() {
                        cancleOrder();
                    }
                });

                break;
            case R.id.btn_go_to_pay:

                startActivityForResult(new Intent(CollaborativeOrderDetailsActivity.this, NewPayActivity.class).putExtra("order_no", order_no), 253);

                break;
            case R.id.ic_back:
                finish();
                break;
            case R.id.tv_chack:
                startActivity(new Intent(CollaborativeOrderDetailsActivity.this, PickUpCaseActivity.class)
                        .putExtra("order_no", private_order_no).putExtra("type", tv_order_type.getText().toString()));
                break;

            case R.id.tv_complaint:

                DialogComplaint complaint = new DialogComplaint();
                complaint.showDialog(this, new Connect() {
                    @Override
                    public void text(String text) {

                        if (text.length() < 10) {
                            ToastUtils.getUtils(CollaborativeOrderDetailsActivity.this).show("请输入原因10-240个字");
                            return;
                        } else {
                            complaint(text);
                        }

                    }
                });

                break;

        }
    }

    private void cancleOrder() {
        RequestParams params = new RequestParams();
        params.addHeader("Authorization", PrefsUtils.getString(CollaborativeOrderDetailsActivity.this, PrefsUtils.AUTHORIZATION));
        params.addBodyParameter("order_no", order);
        httpDataUtils.sendDELETE(MyData.DELE_ORDER, params);
        httpDataUtils.setSucessBack(new HttpDataUtils.SucessBack() {
            @Override
            public void sucess(ResponseInfo<String> arg0) {

                try {
                    JSONObject object = new JSONObject(arg0.result);
                    String code = JSONTool.getString(object, "code");
                    String msg = JSONTool.getString(object, "message");
                    if (code.equals("0")) { // 成功

                        tv_cancle.setVisibility(View.GONE);

                        ToastUtils.getUtils(getApplicationContext()).show("订单已取消");
                        getDetials();

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

    private void complaint(String reason) {

        RequestParams params = new RequestParams();
        params.addHeader("Authorization", PrefsUtils.getString(CollaborativeOrderDetailsActivity.this, PrefsUtils.AUTHORIZATION));
        params.addBodyParameter("consult_id", consult_id);
        params.addBodyParameter("order_no", tv_order_no.getText().toString());
        params.addBodyParameter("content", reason);
        httpDataUtils.sendPost(MyData.COMPLAINT, params);
        httpDataUtils.setSucessBack(new HttpDataUtils.SucessBack() {
            @Override
            public void sucess(ResponseInfo<String> arg0) {

                try {
                    JSONObject object = new JSONObject(arg0.result);
                    String code = JSONTool.getString(object, "code");
                    String msg = JSONTool.getString(object, "message");
                    if (code.equals("0")) { // 成功


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

}
