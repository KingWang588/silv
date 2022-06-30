package com.yhy.hzzll.home.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.yhy.hzzll.R;
import com.yhy.hzzll.framework.MyData;
import com.yhy.hzzll.message.WebviewActivity;
import com.yhy.hzzll.mian.activity.BaseActivity;
import com.yhy.hzzll.mian.activity.LoginActivity;
import com.yhy.hzzll.utils.PrefsUtils;

public class ProfessionalServicesActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professional_services);
        ViewUtils.inject(this);
    }

    @OnClick({ R.id.ic_back,R.id.tv_lawcheck,R.id.tv_preservation,R.id.tv_law_and_regulation_database,R.id.tv_litigation_cost_calculator,R.id.tv_the_arbitration_fee_calculator,
            R.id.ic_back,R.id.tv_guarantee, R.id.tv_service, R.id.tv_profeservice, R.id.tv_circulation, R.id.tv_reward})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ic_back:
                finish();
                break;
            case R.id.tv_guarantee:// 保全担保

                if (PrefsUtils.getString(context, PrefsUtils.AUTHORIZATION) == null || PrefsUtils.getString(context, PrefsUtils.AUTHORIZATION).isEmpty()) {
                    startActivity(new Intent().putExtra("activity", ProfessionalServicesActivity.class.toString()).setClass(context, LoginActivity.class));
                    return;
                }

                startActivity(new Intent().setClass(ProfessionalServicesActivity.this, AssureActivity.class));
                break;
            case R.id.tv_service:// 仲裁服务
                startActivity(new Intent().setClass(ProfessionalServicesActivity.this, ArbitrationServiceActivity.class));
                break;
            case R.id.tv_profeservice:// 专业服务
                startActivity(new Intent().setClass(ProfessionalServicesActivity.this, ServiceShopActivity.class));
                break;
            case R.id.tv_circulation:// 债权流转
                startActivity(new Intent().putExtra("title", "0").setClass(ProfessionalServicesActivity.this, AssignmentActivity.class));
                break;
            case R.id.tv_reward:// 资产线索悬赏
                startActivity(new Intent().putExtra("title", "4").setClass(ProfessionalServicesActivity.this, AssignmentActivity.class));
                break;
            case R.id.tv_lawcheck:

                if (PrefsUtils.getString(context, PrefsUtils.AUTHORIZATION) == null || PrefsUtils.getString(context, PrefsUtils.AUTHORIZATION).isEmpty()) {
                    startActivity(new Intent().putExtra("activity", ProfessionalServicesActivity.class.toString()).setClass(context, LoginActivity.class));
                    return;
                }

                startActivity(new Intent().setClass(ProfessionalServicesActivity.this, LawCheckActivity.class));
                break;
//            case R.id.tv_preservation:
//                startActivity(new Intent().setClass(ProfessionalServicesActivity.this, EvidenceSavaActivity.class));
//                break;
            case R.id.tv_law_and_regulation_database:
                startActivity(new Intent().putExtra("title", "法律法规库").putExtra("url", MyData.LAW_AND_REGULATION_DATABASE).setClass(ProfessionalServicesActivity.this, WebviewActivity.class));
                break;
            case R.id.tv_litigation_cost_calculator:
                startActivity(new Intent().putExtra("title", "诉讼费计算器").putExtra("url", MyData.LITIGATION_COST_CALCULATOR+"?app=yes").setClass(ProfessionalServicesActivity.this, WebviewActivity.class));
                break;
            case R.id.tv_the_arbitration_fee_calculator:
                startActivity(new Intent().putExtra("title", "仲裁费计算器").putExtra("url", MyData.THE_ARBITRATION_FEE_CALCULATOR+"?app=yes").setClass(ProfessionalServicesActivity.this, WebviewActivity.class));
                break;
        }
    }



}
