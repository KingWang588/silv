package com.yhy.hzzll.my.activity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.yhy.hzzll.R;
import com.yhy.hzzll.framework.MyData;
import com.yhy.hzzll.mian.activity.BaseActivity;

/**
 *
 * 设置--关于私律
 *
 */
public class AboutActivity extends BaseActivity {

	@ViewInject(R.id.web)
	private WebView wb;

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_about);
		super.onCreate(arg0);
		ViewUtils.inject(this);
		initialize();
	}



	private void initialize() {
		wb.getSettings().setJavaScriptEnabled(true);
		wb.loadUrl(MyData.ABOUT_SILV);
		wb.setWebViewClient(new webViewClient());
	}

	class webViewClient extends WebViewClient {

		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			view.loadUrl(url);
			return true;
		}
	}
}
