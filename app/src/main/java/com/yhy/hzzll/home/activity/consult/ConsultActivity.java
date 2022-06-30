package com.yhy.hzzll.home.activity.consult;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.yhy.hzzll.R;
import com.yhy.hzzll.home.fragment.AllConsultingFragment;
import com.yhy.hzzll.home.fragment.IAnsweredFragment;
import com.yhy.hzzll.home.view.DialogTipRule;
import com.yhy.hzzll.mian.activity.BaseActivity;
import com.yhy.hzzll.mian.activity.LoginActivity;
import com.yhy.hzzll.utils.PrefsUtils;

/**
 * 解答疑/问答咨询
 *
 * @author Yang
 */
public class ConsultActivity extends BaseActivity {

    @ViewInject(R.id.tv_all)
    private TextView tv_all;

    @ViewInject(R.id.tv_ask)
    private TextView tv_ask;

    @ViewInject(R.id.linear_rule)
    LinearLayout linear_rule;



    View pview;


    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        pview = View.inflate(ConsultActivity.this, R.layout.activity_consult, null);
        setContentView(pview);
        ViewUtils.inject(this);
        tv_all.setSelected(true);
        setDefaultFragment();

//        tv_money.setText("每时每刻28元咨询红包等您来拿，8亿普通百姓都在等您说说话。");

    }

    AllConsultingFragment allConsultingFragment;
    IAnsweredFragment iAnsweredFragment;

    private void setDefaultFragment() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        allConsultingFragment = new AllConsultingFragment();
        transaction.replace(R.id.tv_fragment, allConsultingFragment);
        transaction.commit();
    }

    @OnClick({R.id.tv_all, R.id.tv_ask, R.id.ic_back, R.id.tv_rule, R.id.iv_close,R.id.tv_money})
    public void onClick(View view) {

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();

        switch (view.getId()) {
            case R.id.tv_rule:
            case R.id.tv_money:

                showRule();

                break;
            case R.id.iv_close:

//                tv_money.setVisibility(View.GONE);
                linear_rule.setVisibility(View.GONE);

                break;
            case R.id.ic_back:
                finish();
                break;
            case R.id.tv_all:

                tv_all.setSelected(true);
                tv_ask.setSelected(false);
                if (allConsultingFragment == null) {
                    allConsultingFragment = new AllConsultingFragment();
                }
                transaction.replace(R.id.tv_fragment, allConsultingFragment);
                transaction.commit();
               break;

            case R.id.tv_ask:

                if (PrefsUtils.getString(ConsultActivity.this,PrefsUtils.AUTHORIZATION).equals("")){

                    startActivity(new Intent(ConsultActivity.this, LoginActivity.class).putExtra("activity",ConsultActivity.class.toString()));

                    return;
                }

                tv_ask.setSelected(true);
                tv_all.setSelected(false);
                if (iAnsweredFragment == null) {
                    iAnsweredFragment = new IAnsweredFragment();
                }
                transaction.replace(R.id.tv_fragment, iAnsweredFragment);
                transaction.commit();

                break;
        }
    }

    private void showRule() {

        DialogTipRule dialogTipRule = new DialogTipRule();
        dialogTipRule.showDialog(ConsultActivity.this);

    }

    @Override
    protected void onStart() {
//        tv_money.startScroll();
        super.onStart();
    }


    @Override
    protected void onStop() {
//        tv_money.stopScroll();
        super.onStop();
    }
}
