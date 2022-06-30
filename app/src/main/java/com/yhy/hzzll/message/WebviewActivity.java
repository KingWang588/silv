package com.yhy.hzzll.message;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.umeng.socialize.utils.Log;
import com.yhy.hzzll.R;
import com.yhy.hzzll.framework.MyData;
import com.yhy.hzzll.mian.activity.BaseActivity;

public class WebviewActivity extends BaseActivity {

	@ViewInject(R.id.wb)
	private WebView wb;

	@ViewInject(R.id.rl_title)
	private RelativeLayout rl_title;
	
	@ViewInject(R.id.tv_title)
	private TextView tv_title;

	private String url = "";
	private String title = "";

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_webview);
		super.onCreate(arg0);
		ViewUtils.inject(this);
		
//		 设置 缓存模式
		wb.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
		// 开启 DOM storage API 功能
		wb.getSettings().setDomStorageEnabled(true);
		wb.getSettings().setJavaScriptEnabled(true);

		if (null != getIntent()) {
			title = getIntent().getStringExtra("title");
			url = getIntent().getStringExtra("url");
			initialize();
		}
	}

	private void initialize() {
		
		if("".equals(title)){
			rl_title.setVisibility(View.GONE);
		}else{
			tv_title.setText(title);
		}
		
		wb.getSettings().setJavaScriptEnabled(true);
		wb.loadUrl(url);
		wb.setWebViewClient(new webViewClient());
		// WebChromeClient wvcc = new WebChromeClient() {
		// @Override
		// public void onReceivedTitle(WebView view, String title) {
		// super.onReceivedTitle(view, title);
		// tv_title.setText(title);
		// }
		// };
		// // 设置setWebChromeClient对象
		// wb.setWebChromeClient(wvcc);
	}

	class webViewClient extends WebViewClient {

		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			view.loadUrl(url);
			Log.e("私律微客办公室++++++++++++",url);
			if(url.equals(MyData.MOBILE_URL+"/")){
			finish();
			}
			
			return true;
		}
	}

	@Override
	public void onBackPressed() {
		if (wb.canGoBack()) {
			wb.goBack(); // goBack()表示返回WebView的上一页面
		} else {
			finish();
		}
	}
}
