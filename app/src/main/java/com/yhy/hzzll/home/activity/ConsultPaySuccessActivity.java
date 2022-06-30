package com.yhy.hzzll.home.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.yhy.hzzll.R;
import com.yhy.hzzll.home.activity.newcollaborate.PickUpCaseActivity;
import com.yhy.hzzll.mian.activity.BaseActivity;
import com.yhy.hzzll.mian.activity.MainActivity;
import com.yhy.hzzll.my.activity.CollaborativeOrderDetailsActivity;


public class ConsultPaySuccessActivity extends BaseActivity {

    private String askId;
    String order_no;

    @Override
    protected void onCreate(Bundle arg0) {
        // TODO Auto-generated method stub
        setContentView(R.layout.activity_consult_paysuccess);
        super.onCreate(arg0);
        ViewUtils.inject(this);
        order_no = getIntent().getStringExtra("order_no");
//		if (null != getIntent()) {
//			askId = getIntent().getStringExtra("askId");
//		}
    }

    @OnClick({R.id.tv_check_service, R.id.tv_check_order,R.id.tv_home})
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.tv_home:
                startActivity(new Intent().putExtra("tab", 0).setClass(ConsultPaySuccessActivity.this, MainActivity.class));
                break;

            case R.id.tv_check_service:

                startActivity(new Intent(ConsultPaySuccessActivity.this, PickUpCaseActivity.class)
                        .putExtra("order_no", order_no).putExtra("type", ""));
                finish();
                break;
            case R.id.tv_check_order:

                startActivity(new Intent(ConsultPaySuccessActivity.this, CollaborativeOrderDetailsActivity.class).putExtra("private_order_no", order_no));
                finish();
                break;


        }
    }

}
