package com.yhy.hzzll.my.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.netease.nim.uikit.business.robot.parser.elements.group.LinearLayout;
import com.yhy.hzzll.R;
import com.yhy.hzzll.framework.MyData;
import com.yhy.hzzll.home.activity.collaborate.LawyerNationalCooperationActivity;
import com.yhy.hzzll.home.activity.newcollaborate.PickUpCaseActivity;
import com.yhy.hzzll.message.WebviewActivity;
import com.yhy.hzzll.mian.activity.BaseActivity;
import com.yhy.hzzll.mian.activity.RegisterFirstActivity;
import com.yhy.hzzll.utils.HttpDataUtils;
import com.yhy.hzzll.utils.JSONTool;
import com.yhy.hzzll.utils.PrefsUtils;
import com.yhy.hzzll.utils.ToastUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

import am.widget.multiactiontextview.MultiActionClickableSpan;
import am.widget.multiactiontextview.MultiActionTextView;

public class ExclusiveLawyerActivity extends BaseActivity {


    @ViewInject(R.id.not_open)
    View not_open;

    @ViewInject(R.id.close)
    View close;

    @ViewInject(R.id.pending_review)
    View pending_review;
    @ViewInject(R.id.pass)
    View pass;
    @ViewInject(R.id.not_pass)
    View not_pass;

    @ViewInject(R.id.tv_reason)
    TextView tv_reason;

    @ViewInject(R.id.tv_read_action)
    MultiActionTextView tv_read_action;

    @ViewInject(R.id.tv_marriage_insurance)
    TextView tv_marriage_insurance;

    @ViewInject(R.id.tv_credit_insurance)
    TextView tv_credit_insurance;

    @ViewInject(R.id.linear_credit_insurance)
    View linear_credit_insurance;

    @ViewInject(R.id.linear_marriage_insurance)
    View linear_marriage_insurance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exclusive_lawyer);
        ViewUtils.inject(this);





        getStatues();

    }


    MultiActionClickableSpan.OnTextClickedListener listener1 = new MultiActionClickableSpan.OnTextClickedListener() {
        @Override
        public void onTextClicked(View view, MultiActionClickableSpan span) {
            startActivity(new Intent().putExtra("title", "使用协议")
//					.putExtra("url", "http://mobile.hzzll.com/n/0914/m.html")
                    .putExtra("url", MyData.AGREEMENT_OF_USAGE)
                    .setClass(ExclusiveLawyerActivity.this, WebviewActivity.class));
        }
    };

    MultiActionClickableSpan.OnTextClickedListener listener2 = new MultiActionClickableSpan.OnTextClickedListener() {
        @Override
        public void onTextClicked(View view, MultiActionClickableSpan span) {
            startActivity(new Intent().putExtra("title", "隐私声明")
                    .putExtra("url", MyData.PRIVACY_STATEMENT)
                    .setClass(ExclusiveLawyerActivity.this, WebviewActivity.class));
        }
    };



    private void getStatues() {
        RequestParams params = new RequestParams();
        params.addHeader("Authorization", PrefsUtils.getString(ExclusiveLawyerActivity.this, PrefsUtils.AUTHORIZATION));

        httpDataUtils.sendProgressGet(MyData.GET_EXCLUSIVELAWYER_STATUES, params);
        httpDataUtils.setSucessBack(new HttpDataUtils.SucessBack() {

            @Override
            public void sucess(ResponseInfo<String> arg0) {
                Log.e("32",arg0.result);

                try {
                    JSONObject object = new JSONObject(arg0.result);
                    String code = JSONTool.getString(object, "code");
                    String message = JSONTool.getString(object, "message");

                   JSONObject data=  object.getJSONObject("data");

                    if (code.equals("0")) { // 成功

                        String status_id = JSONTool.getString(data,"status_id");


                        if (status_id.equals("1")){
                            pending_review.setVisibility(View.VISIBLE);
                        }else if (status_id.equals("2")){
                            pass.setVisibility(View.VISIBLE);

                            JSONArray array = data.getJSONArray("exclusive_lawyer_secpical");

                            if (array.length()==1){
                                if (array.get(0).equals("婚姻家庭")){
                                    linear_marriage_insurance.setVisibility(View.VISIBLE);
                                    tv_marriage_insurance.setVisibility(View.VISIBLE);
                                }else if (array.get(0).equals("债权债务")){
                                    linear_credit_insurance.setVisibility(View.VISIBLE);
                                    tv_credit_insurance.setVisibility(View.VISIBLE);
                                }
                            }


                            if (array.length()==2){
                                if (array.get(0).equals("婚姻家庭")||array.get(1).equals("婚姻家庭")){
                                    linear_marriage_insurance.setVisibility(View.VISIBLE);
                                    tv_marriage_insurance.setVisibility(View.VISIBLE);
                                }

                                if (array.get(0).equals("债权债务")||array.get(1).equals("债权债务")){
                                    linear_credit_insurance.setVisibility(View.VISIBLE);
                                    tv_credit_insurance.setVisibility(View.VISIBLE);
                                }
                            }





                        }else if (status_id.equals("3")){
                           not_pass.setVisibility(View.VISIBLE);
                            String handle_opinion = JSONTool.getString(data,"handle_opinion");

                            tv_reason.setText(handle_opinion);

                        }else if (status_id.equals("4")){

                            not_open.setVisibility(View.VISIBLE);

                            String text = "阅读并接受《私律使用协议》及《法律声明》";

                            MultiActionClickableSpan action1 = new MultiActionClickableSpan(6, 12, R.color.textbule, true, false, listener1);
                            MultiActionClickableSpan action2 = new MultiActionClickableSpan(16, 19, R.color.textbule, true, false, listener2);
                            tv_read_action.setText(text, action1, action2 );
                        }else if (status_id.equals("0")){
                            close.setVisibility(View.VISIBLE);
                        }

                    } else {

                        not_open.setVisibility(View.VISIBLE);

                        String text = "阅读并接受《私律使用协议》及《法律声明》";

                        MultiActionClickableSpan action1 = new MultiActionClickableSpan(6, 12, R.color.textbule, true, false, listener1);
                        MultiActionClickableSpan action2 = new MultiActionClickableSpan(15, 19, R.color.textbule, true, false, listener2);
                        tv_read_action.setText(text, action1, action2 );


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


    @OnClick({R.id.btn_submit,R.id.btn_more,R.id.ic_back,R.id.btn_money})
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.btn_money:
                Date dt2 = new Date();
                Long time2 = dt2.getTime();
                PrefsUtils.saveString(ExclusiveLawyerActivity.this, PrefsUtils.CLICKLAWCOOP, String.valueOf(time2));
//                tv_notify_lawcoop.setVisibility(View.INVISIBLE);
                startActivity(new Intent().putExtra("tab", 0).setClass(ExclusiveLawyerActivity.this, LawyerNationalCooperationActivity.class));
                break;

            case R.id.ic_back:
                finish();
                break;
            case R.id.btn_more:

//                application();

                not_pass.setVisibility(View.INVISIBLE);
                not_open.setVisibility(View.VISIBLE);

                not_open.setVisibility(View.VISIBLE);

                String text = "阅读并接受《私律使用协议》及《法律声明》";

                MultiActionClickableSpan action1 = new MultiActionClickableSpan(6, 12, R.color.textbule, true, false, listener1);
                MultiActionClickableSpan action2 = new MultiActionClickableSpan(16, 19, R.color.textbule, true, false, listener2);
                tv_read_action.setText(text, action1, action2 );

                break;
                case R.id.btn_submit:
                    application();
                break;
        }
    }


    private void application() {
        RequestParams params = new RequestParams();
        params.addHeader("Authorization", PrefsUtils.getString(ExclusiveLawyerActivity.this, PrefsUtils.AUTHORIZATION));

        httpDataUtils.sendProgressGet(MyData.GET_EXCLUSIVELAWYER_APPLICATION, params);
        httpDataUtils.setSucessBack(new HttpDataUtils.SucessBack() {

            @Override
            public void sucess(ResponseInfo<String> arg0) {
                Log.e("32",arg0.result);

                try {
                    JSONObject object = new JSONObject(arg0.result);
                    String code = JSONTool.getString(object, "code");
                    String message = JSONTool.getString(object, "message");

                    if (code.equals("0")) { // 成功
                        ToastUtils.getUtils(ExclusiveLawyerActivity.this).show("申请成功");
                        finish();
                    } else {
                        ToastUtils.getUtils(ExclusiveLawyerActivity.this).show(message);
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
