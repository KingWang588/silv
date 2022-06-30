package com.yhy.hzzll.home.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.yhy.hzzll.R;
import com.yhy.hzzll.framework.MyData;
import com.yhy.hzzll.mian.activity.BaseActivity;
import com.yhy.hzzll.utils.LogUtils;
import com.yhy.hzzll.utils.PrefsUtils;

/**
 * 律查查
 * 
 * @author Yang
 * 
 */

public class LawCheckActivity extends BaseActivity {

	@ViewInject(R.id.wb)
	WebView wb;

	@ViewInject(R.id.tv_title)
	TextView tv_title;
	@ViewInject(R.id.rl_qichacha)
	RelativeLayout rl_qichacha;
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.fragment_lawcheck);
		ViewUtils.inject(this);
		initialize();
	}

	@SuppressLint("JavascriptInterface")
	private void initialize() {
		// 设置 缓存模式
		wb.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
		// 开启 DOM storage API 功能
		wb.getSettings().setDomStorageEnabled(true);
		wb.getSettings().setJavaScriptEnabled(true);
		wb.addJavascriptInterface(this, "token");// 对应js中的test.xxx
		wb.loadData("", "text/html", "UTF-8");
		// wb.loadUrl("file:///android_asset/chacha/index.html?Authorization="
		// + PrefsUtils.getString(getApplicationContext(),
		// PrefsUtils.ACCESSKEY));
//		wb.loadUrl(MyData.MOBILE_URL
//				+ "/n/chacha/index.html#/?_k=scmerq?Authorization="
//				+ PrefsUtils.getString(getApplicationContext(),
//						PrefsUtils.ACCESSKEY));

		LogUtils.logE(MyData.LAW_CHECK+"?app=yes&auth="+PrefsUtils.getString(getApplicationContext(),PrefsUtils.AUTHORIZATION));

		wb.loadUrl(MyData.LAW_CHECK+"?app=yes&auth="+PrefsUtils.getString(getApplicationContext(),PrefsUtils.AUTHORIZATION));
		wb.setWebViewClient(new webViewClient());
//		WebChromeClient wvcc = new WebChromeClient() {
//			@Override
//			public void onReceivedTitle(WebView view, String title) {
//				super.onReceivedTitle(view, title);
////				tv_title.setText(title);
//			}
//		};
		// 设置setWebChromeClient对象
//		wb.setWebChromeClient(wvcc);
	}

//	@JavascriptInterface
//	public String getToken(String msg) {// 对应js中xxx.hello("")
//		Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
//		return PrefsUtils.getString(getApplicationContext(),
//				PrefsUtils.ACCESSKEY);
//	}

	
	class webViewClient extends WebViewClient {

		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			
			Log.e("xxxxxxxxxxxxxxxx", url);

			if(url.equals(MyData.MOBILE_URL+"/")){
				finish();
			}else
//				if (url.contains("link.qichacha.com"))
			{
				view.loadUrl(url);
				rl_qichacha.setVisibility(View.VISIBLE);
			}
			
			return true;
		}
	}

	@OnClick(R.id.ic_back)
	public void onclick(View view) {
		switch (view.getId()) {
		case R.id.ic_back:
//			if ( wb.canGoBack()) {
//				wb.goBack(); // goBack()表示返回WebView的上一页面
//			} else {
				finish();
//			}
			break;
		}
	}

	@Override
	// 设置回退
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if ((keyCode == KeyEvent.KEYCODE_BACK) && wb.canGoBack()) {
			wb.goBack(); // goBack()表示返回WebView的上一页面
		} else {
			finish();
		}
		return true;
	}
}
