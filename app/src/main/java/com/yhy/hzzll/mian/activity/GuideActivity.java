package com.yhy.hzzll.mian.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.yhy.hzzll.R;
import com.yhy.hzzll.framework.MyData;
import com.yhy.hzzll.message.WebviewActivity;
import com.yhy.hzzll.mian.activity.showPop.ShowDialogCallback;
import com.yhy.hzzll.mian.activity.showPop.ShowMsgDialog;
import com.yhy.hzzll.mian.activity.showPop.ShuoMClickableSpan;
import com.yhy.hzzll.utils.PrefsUtils;

public class GuideActivity extends Activity {

    @Override
    protected void onCreate(Bundle arg0) {

        super.onCreate(arg0);
        setContentView(R.layout.guide);
        if (PrefsUtils.getString(getApplicationContext(), PrefsUtils.Agree).equals("agree")) {
            //start();
            showPrivacyDialog();
        }else {
            ShowDialogCallback callback = new ShowDialogCallback() {
                @Override
                public void ok() {
                    PrefsUtils.saveString(getApplicationContext(), PrefsUtils.Agree,"agree");
                    sendBroadcast(new Intent("HzApplication.receiver"));
                    showPrivacyDialog();
                }
                @Override
                public void cancel() {
                    finish();
                }
            };
            ShuoMClickableSpan.Callback click = s -> {
                startActivity(new Intent().putExtra("title", "私律APP隐私保护政策")
                        .putExtra("url", MyData.PRIVACY_STATEMENT)
                        .setClass(GuideActivity.this, WebviewActivity.class));
            };
            new ShowMsgDialog().showMsg(GuideActivity.this,"私律APP隐私保护政策", getString(R.string.pop_private_info_msg),"《私律APP隐私保护政策》",click,callback);
        }

    }

    private void start(){
        new Handler().postDelayed(() -> {
            if (PrefsUtils.getString(getApplicationContext(), PrefsUtils.First).equals("1")) {
                startActivity(new Intent().setClass(GuideActivity.this, MainActivity.class));
                finish();
            } else {
                startActivity(new Intent().setClass(GuideActivity.this, WelcomeActivity.class));
                finish();
            }
        }, 2000);
    }


    private void showPrivacyDialog(){
        ShowDialogCallback callback01 = new ShowDialogCallback() {
            @Override
            public void ok() {
                PrefsUtils.saveString(getApplicationContext(), PrefsUtils.Agree,"agree");
                sendBroadcast(new Intent("HzApplication.receiver"));
                start();
            }
            @Override
            public void cancel() {
                finish();
            }
        };
        ShuoMClickableSpan.Callback click = s -> {
            startActivity(new Intent().putExtra("title", "网易云信隐私政策")
                    .putExtra("url", MyData.PRIVACY_AGREEMENT)
                    .setClass(GuideActivity.this, WebviewActivity.class));
        };
        new ShowMsgDialog().showMsg(this,"网易云信隐私政策", getString(R.string.pop_private_agreement_info_msg),"《网易隐私政策》",click,callback01);

    }
}
