package com.yhy.hzzll.my.activity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.yhy.hzzll.R;
import com.yhy.hzzll.framework.MyData;
import com.yhy.hzzll.mian.activity.BaseActivity;

/**
 * 设置--帮助中心
 * 
 * @author
 * 
 */
public class HelpActivity extends BaseActivity {
	@ViewInject(R.id.wb)
	WebView wb;
	@ViewInject(R.id.tv_title)
	TextView tv_title;


	@ViewInject(R.id.rl_title)
	RelativeLayout rl_title;

	@Override
	protected void onCreate(Bundle arg0) {
		setContentView(R.layout.activity_help);
		super.onCreate(arg0);
		ViewUtils.inject(this);
		init();
	}

	private void init() {
        // 设置 缓存模式
        wb.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        // 开启 DOM storage API 功能
        wb.getSettings().setDomStorageEnabled(true);
        wb.getSettings().setJavaScriptEnabled(true);

		wb.loadUrl(MyData.MOBILE_URL+"helplawyers");
		wb.setWebViewClient(new webViewClient());
		WebChromeClient wvcc = new WebChromeClient() {
			@Override
			public void onReceivedTitle(WebView view, String title) {
				super.onReceivedTitle(view, title);
				tv_title.setText(title);
			}
		};
		// 设置setWebChromeClient对象
		wb.setWebChromeClient(wvcc);
	}

	class webViewClient extends WebViewClient {

		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			view.loadUrl(url);
//			Log.e("url",""+url);
////			if(url.equals("http://mobile.hzzll.com/")){
////				finish();
////			}
			
			
			return true;
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		boolean canBack = wb.canGoBack();
		if (keyCode == KeyEvent.KEYCODE_BACK && canBack) {
			wb.goBack();// 返回前一个页面
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
}
