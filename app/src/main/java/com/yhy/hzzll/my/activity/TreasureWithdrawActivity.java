package com.yhy.hzzll.my.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewStub;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.yhy.hzzll.R;
import com.yhy.hzzll.framework.MyData;
import com.yhy.hzzll.mian.activity.BaseActivity;
import com.yhy.hzzll.mian.entity.DataUserEntity;
import com.yhy.hzzll.utils.HttpDataUtils;
import com.yhy.hzzll.utils.LogUtils;
import com.yhy.hzzll.utils.PrefsUtils;
import com.yhy.hzzll.utils.ToastUtils;
import com.yhy.hzzll.utils.Tools;
import com.yhy.hzzll.view.AdressPopupWindow;
import com.yhy.hzzll.view.BankPopupWindow;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.regex.Pattern;

//import com.pingplusplus.android.Pingpp;

/**
 * 我的财富--提现
 *
 * @author Yang
 */
public class TreasureWithdrawActivity extends BaseActivity {
    private BankPopupWindow popupWindow;

    private ArrayList<String> arrProvinces = new ArrayList<String>();

    @ViewInject(R.id.iv_pay_type_icon)
    ImageView iv_pay_type_icon;
    @ViewInject(R.id.tv_pay_type_title)
    TextView tv_pay_type_title;
    @ViewInject(R.id.tv_balance_of_account)
    TextView tv_balance_of_account;
    @ViewInject(R.id.stub_bank_withdraw_content)
    ViewStub stub_bank_withdraw_content;
    @ViewInject(R.id.stub_third_pay_withdraw_content)
    ViewStub stub_third_pay_withdraw_content;

    EditText et_third_withdrar_account;
    EditText et_third_username;
    EditText et_third_phone;
    EditText et_third_withdraw_count;

    EditText et_bank_cardno;
    EditText et_bank_name;
    EditText et_bank_username;
    EditText et_bank_phone;
    TextView tv_bank_address;
    EditText et_bank_withdraw_count;

    private View cView;
    ImageView iv_wx_is_select;
    ImageView iv_alipay_is_select;
    ImageView iv_bank_card_is_select;

    private String payChannel;
    private DataUserEntity userEntity;

    String accountMoney;

    @Override
    protected void onCreate(Bundle arg0) {
        cView = LayoutInflater.from(this).inflate(R.layout.activity_treasure_withdraw, null);
        setContentView(cView);
        super.onCreate(arg0);
        ViewUtils.inject(this);
        init();
        payChannel = "alipay"; //默认
    }

    private void init() {
        popupWindow = new BankPopupWindow(this);
        RelativeLayout rl_wx_pay_content = (RelativeLayout) popupWindow.conentView.findViewById(R.id.rl_wx_pay_content);
        rl_wx_pay_content.setOnClickListener(new MyOnClickListener());
        iv_wx_is_select = (ImageView) popupWindow.conentView.findViewById(R.id.tv_wx_is_select);
        iv_wx_is_select.setImageResource(R.drawable.icon_point_h);
        RelativeLayout rl_alipay_content = (RelativeLayout) popupWindow.conentView.findViewById(R.id.rl_alipay_content);
        rl_alipay_content.setOnClickListener(new MyOnClickListener());
        iv_alipay_is_select = (ImageView) popupWindow.conentView.findViewById(R.id.tv_alipay_is_select);
        RelativeLayout rl_bank_card_content = (RelativeLayout) popupWindow.conentView
                .findViewById(R.id.rl_bank_card_content);
        rl_bank_card_content.setOnClickListener(new MyOnClickListener());
        iv_bank_card_is_select = (ImageView) popupWindow.conentView.findViewById(R.id.tv_bank_card_is_select);

        stub_bank_withdraw_content.inflate();
        stub_third_pay_withdraw_content.inflate();
        et_third_withdrar_account = (EditText) findViewById(R.id.et_third_withdrar_account);
        et_third_username = (EditText) findViewById(R.id.et_third_username);
        et_third_phone = (EditText) findViewById(R.id.et_third_phone);
        et_third_withdraw_count = (EditText) findViewById(R.id.et_third_withdraw_count);

        et_bank_cardno = (EditText) findViewById(R.id.et_bank_cardno);
        et_bank_name = (EditText) findViewById(R.id.et_bank_name);
        et_bank_username = (EditText) findViewById(R.id.et_bank_username);
        et_bank_phone = (EditText) findViewById(R.id.et_bank_phone);
        tv_bank_address = (TextView) findViewById(R.id.tv_bank_address);
        tv_bank_address.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                final AdressPopupWindow popupWindow = new AdressPopupWindow(TreasureWithdrawActivity.this, arrProvinces, false);
                popupWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);
                popupWindow
                        .setAddresskListener(new AdressPopupWindow.OnAddressCListener() {
                            @Override
                            public void onClick(String province, String city,
                                                String area, int provinceid, int cityid,
                                                int areaid) {
                                tv_bank_address.setText(province + "-" + city + "-" + area);
                                popupWindow.dismiss();
                            }
                        });
            }
        });
        et_bank_withdraw_count = (EditText) findViewById(R.id.et_bank_withdraw_count);

//        getMoney();

        String money = getIntent().getStringExtra("money");
        accountMoney = money;
        tv_balance_of_account.setText(money);

        stub_bank_withdraw_content.setVisibility(View.GONE);
    }


    class MyOnClickListener implements OnClickListener {

        @Override
        public void onClick(View v) {
            iv_wx_is_select.setImageResource(R.drawable.icon_point_n);
            iv_alipay_is_select.setImageResource(R.drawable.icon_point_n);
            iv_bank_card_is_select.setImageResource(R.drawable.icon_point_n);
            switch (v.getId()) {
                case R.id.rl_wx_pay_content:
                    iv_wx_is_select.setImageResource(R.drawable.icon_point_h);
                    setSelection(0);
                    stub_bank_withdraw_content.setVisibility(View.GONE);
                    stub_third_pay_withdraw_content.setVisibility(View.VISIBLE);
                    break;
                case R.id.rl_alipay_content:
                    iv_alipay_is_select.setImageResource(R.drawable.icon_point_h);
                    setSelection(1);
                    stub_bank_withdraw_content.setVisibility(View.GONE);
                    stub_third_pay_withdraw_content.setVisibility(View.VISIBLE);
                    break;
                case R.id.rl_bank_card_content:
                    iv_bank_card_is_select.setImageResource(R.drawable.icon_point_h);
                    setSelection(2);
                    stub_third_pay_withdraw_content.setVisibility(View.GONE);
                    stub_bank_withdraw_content.setVisibility(View.VISIBLE);
                    break;
                // case R.id.rl_add_new_bank:
                // ToastUtils.getUtils(TreasureWithdrawActivity.this).show("微信");
                // setSelection(3);
                // break;
                default:
                    break;
            }
        }
    }

    private void setSelection(int index) {
        switch (index) {
            case 0:
                iv_pay_type_icon.setImageResource(R.drawable.share_weixin);
                tv_pay_type_title.setText("微信");
                payChannel = "wx";
                popupWindow.dismiss();
                // tv_pay_card_number.setVisibility(View.GONE);
                break;
            case 1:
                iv_pay_type_icon.setImageResource(R.drawable.icon_alipay);
                tv_pay_type_title.setText("支付宝");
                payChannel = "alipay";
                popupWindow.dismiss();
                // tv_pay_card_number.setVisibility(View.GONE);
                break;
            case 2:
                iv_pay_type_icon.setImageResource(R.drawable.icon_bank);
                tv_pay_type_title.setText("银行卡");
                payChannel = "bank";
                popupWindow.dismiss();
                // tv_pay_card_number.setVisibility(View.GONE);
                break;
            default:
                break;
        }
    }

    /**
     * 提现 - 发起提现请求
     */
    private void postWithdraw() {
        String rechargePrice = null;
        String name = null;
        String account = null;
        String contact = null;
        String type = null;
        String bank_name = "";
        if ("wx".equals(payChannel)) {
            rechargePrice = et_third_withdraw_count.getText().toString();
            name = et_third_username.getText().toString();
            account = et_third_withdrar_account.getText().toString();
            contact = et_third_phone.getText().toString();
            type = "2";
        } else if ("alipay".equals(payChannel)) {
            rechargePrice = et_third_withdraw_count.getText().toString();
            name = et_third_username.getText().toString();
            account = et_third_withdrar_account.getText().toString();
            contact = et_third_phone.getText().toString();
            type = "1";
        } else {
            rechargePrice = et_bank_withdraw_count.getText().toString();
            name = et_bank_username.getText().toString();
            account = et_bank_cardno.getText().toString();
            contact = et_bank_phone.getText().toString();
            bank_name = tv_bank_address.getText().toString() + et_bank_name.getText().toString();   //开户支行所在地 + 开户支行名称
            type = "bank";
        }

        if (TextUtils.isEmpty(account)) {
            ToastUtils.getUtils(getApplicationContext()).show("提现账户不能为空。");
            return;
        }

        if (TextUtils.isEmpty(rechargePrice)) {
            ToastUtils.getUtils(getApplicationContext()).show(getString(R.string.lable_empty_recharge_price));
            return;
        }
        if (TextUtils.isEmpty(name)) {
            ToastUtils.getUtils(getApplicationContext()).show("提现人姓名不能为空");
            return;
        }

        if (TextUtils.isEmpty(contact)) {
            ToastUtils.getUtils(getApplicationContext()).show("联系方式不能为空");
            return;
        }


        String pattern = "\\w[-\\w.+]*@([A-Za-z0-9][-A-Za-z0-9]+\\.)+[A-Za-z]{2,14}";
        String pattern2 = "(13\\d|14[57]|15[^4,\\D]|17[678]|18\\d)\\d{8}|170[059]\\d{7}";
        if (Pattern.matches(pattern, contact) || Pattern.matches(pattern2, contact)) {

        } else {
            ToastUtils.getUtils(getApplicationContext()).show("请输入正确的联系方式。");
            return;
        }


        double accountPrice = Tools.stringToDouble(accountMoney); // 账户余额
        double price = Tools.stringToDouble(rechargePrice); // 提现金额

        if (price < 50 ) {
            ToastUtils.getUtils(getApplicationContext()).show("提现金额不能小于50");
            return;
        }

        if (price > accountPrice) {
            ToastUtils.getUtils(getApplicationContext()).show(getString(R.string.lable_warn_recharge_price));
            return;
        }

        RequestParams params = new RequestParams();
        params.addHeader("Authorization", PrefsUtils.getString(getApplicationContext(), PrefsUtils.AUTHORIZATION));

        params.addBodyParameter("cash_withdrawal", price + "");
        params.addBodyParameter("username", name);
        params.addBodyParameter("to_account", account);
        params.addBodyParameter("contact", contact);
        params.addBodyParameter("withdraw_type", type);

        LogUtils.logE(type);

        httpDataUtils.sendProgressPost(MyData.WITHDRAW, params);
        httpDataUtils.setSucessBack(new HttpDataUtils.SucessBack() {

            @Override
            public void sucess(ResponseInfo<String> arg0) {
                try {
                    JSONObject object = new JSONObject(arg0.result);
                    String code = object.optString("code");
                    String msg = object.optString("message");
                    if (code.equals("0")) {
                        ToastUtils.getUtils(getApplicationContext()).show("提现申请提交成功，我们会在2个工作日内予以处理");
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

//    /**
//     * onActivityResult 获得支付结果，如果支付成功，服务器会收到ping++ 服务器发送的异步通知。 最终支付成功根据异步通知为准
//     */
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//
//        // 支付页面返回处理
//        if (requestCode == Pingpp.REQUEST_CODE_PAYMENT) {
//            if (resultCode == Activity.RESULT_OK) {
//                String result = data.getExtras().getString("pay_result");
//
//                String errorMsg = data.getExtras().getString("error_msg"); // 错误信息
//                String extraMsg = data.getExtras().getString("extra_msg"); // 错误信息
//                Toast.makeText(context, result + "------" + errorMsg + "-------" + extraMsg, Toast.LENGTH_SHORT).show();
//            }
//        }
//    }

    @OnClick({R.id.ll_chage_bank, R.id.tv_withdraw_submit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_chage_bank:
//                popupWindow.showAtLocation(cView, Gravity.BOTTOM, 0, 0);
                break;
            case R.id.tv_withdraw_submit:
                postWithdraw();
                break;
        }
    }
}
