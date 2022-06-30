package com.yhy.hzzll.home.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.yhy.hzzll.R;
import com.yhy.hzzll.message.WebviewActivity;
import com.yhy.hzzll.mian.activity.BaseActivity;

/**
 * 阅读协议
 * 
 * @author Yang
 * 
 */
public class ReadAgreementActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle arg0) {
		setContentView(R.layout.activity_read_agreement);
		super.onCreate(arg0);
		ViewUtils.inject(this);
	}

	@OnClick({ R.id.tv_useagree, R.id.tv_privacy, R.id.tv_law })
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.tv_useagree:// 华债网使用协议
			startActivity(new Intent().putExtra("title", "使用协议")
					.putExtra("url", "http://mobile.hzzll.com/n/0914/m.html")
					.setClass(context, WebviewActivity.class));
			break;
		case R.id.tv_privacy:// 隐私声明
			startActivity(new Intent().putExtra("title", "隐私声明")
					.putExtra("url", "http://mobile.hzzll.com/n/0914/p.html")
					.setClass(context, WebviewActivity.class));
			break;
		case R.id.tv_law:// 法律声明
			startActivity(new Intent().putExtra("title", "法律声明")
					.putExtra("url", "http://mobile.hzzll.com/n/0914/q.html")
					.setClass(context, WebviewActivity.class));
			break;
		}
	}

}
