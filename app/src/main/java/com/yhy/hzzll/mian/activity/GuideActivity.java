package com.yhy.hzzll.mian.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.netease.nim.uikit.api.NimUIKit;
import com.netease.nimlib.sdk.NIMClient;
import com.yhy.hzzll.R;
import com.yhy.hzzll.contact.ContactHelper;
import com.yhy.hzzll.event.DemoOnlineStateContentProvider;
import com.yhy.hzzll.framework.MyData;
import com.yhy.hzzll.message.WebviewActivity;
import com.yhy.hzzll.mian.activity.showPop.ShowDialogCallback;
import com.yhy.hzzll.mian.activity.showPop.ShowMsgDialog;
import com.yhy.hzzll.mian.activity.showPop.ShuoMClickableSpan;
import com.yhy.hzzll.session.SessionHelper;
import com.yhy.hzzll.utils.PrefsUtils;

public class GuideActivity extends Activity {

    @Override
    protected void onCreate(Bundle arg0) {

        super.onCreate(arg0);
        setContentView(R.layout.guide);
        if (PrefsUtils.getString(getApplicationContext(), PrefsUtils.Agree).equals("agree")) {
            start();
        }else {
            ShowDialogCallback callback = new ShowDialogCallback() {
                @Override
                public void ok() {
                    PrefsUtils.saveString(getApplicationContext(), PrefsUtils.Agree,"agree");
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
                sendBroadcast(new Intent(MyData.HZAPPLICATION_RECEIVER));
                    NIMClient.initSDK();
                    initUiKit();
                PrefsUtils.setIsAgreePrivacyBoolean(GuideActivity.this,true);
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



    private void initUiKit() {

        // 初始化
        NimUIKit.init(this);

        // 可选定制项
        // 注册定位信息提供者类（可选）,如果需要发送地理位置消息，必须提供。
        // demo中使用高德地图实现了该提供者，开发者可以根据自身需求，选用高德，百度，google等任意第三方地图和定位SDK。
//        NimUIKit.setLocationProvider(new NimDemoLocationProvider());

        // 会话窗口的定制: 示例代码可详见demo源码中的SessionHelper类。
        // 1.注册自定义消息附件解析器（可选）
        // 2.注册各种扩展消息类型的显示ViewHolder（可选）
        // 3.设置会话中点击事件响应处理（一般需要）
        SessionHelper.init();

        // 通讯录列表定制：示例代码可详见demo源码中的ContactHelper类。
        // 1.定制通讯录列表中点击事响应处理（一般需要，UIKit 提供默认实现为点击进入聊天界面)
        ContactHelper.init();

        // 在线状态定制初始化。
        NimUIKit.setOnlineStateContentProvider(new DemoOnlineStateContentProvider());
        //设置是否听筒模式
        NimUIKit.setEarPhoneModeEnable(false);
    }

}
