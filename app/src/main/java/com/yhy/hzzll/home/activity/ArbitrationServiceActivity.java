package com.yhy.hzzll.home.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.ValueCallback;
import android.webkit.WebBackForwardList;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.umeng.socialize.utils.Log;
import com.yhy.hzzll.R;
import com.yhy.hzzll.mian.activity.BaseActivity;
import com.yhy.hzzll.framework.MyData;

/**
 * 仲裁服务
 */
public class ArbitrationServiceActivity extends BaseActivity {

	@ViewInject(R.id.wb)
	WebView wb;
	@ViewInject(R.id.tv_title)
	TextView tv_title;

	@ViewInject(R.id.rl_title)
	RelativeLayout rl_title;
	
	@Override
	protected void onCreate(Bundle arg0) {
		setContentView(R.layout.activity_arbitration_service);
		super.onCreate(arg0);
		ViewUtils.inject(this);
		// 设置 缓存模式
		wb.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
		// 开启 DOM storage API 功能
		wb.getSettings().setDomStorageEnabled(true);
		wb.getSettings().setJavaScriptEnabled(true);
		init();
	}

	
	private void init() {
		wb.getSettings().setJavaScriptEnabled(true);
		wb.loadUrl(MyData.ARBITRATION_SERVICE);
		wb.setWebViewClient(new WebViewClient() {
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				Log.e("url*********************************", url);
				view.loadUrl(url);
				return true;
			}
		});

		progressBar = new ProgressDialog(this);
		progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);

		wb.setWebViewClient(new WebViewClient() {
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				// handler.sendEmptyMessage(0);
				view.loadUrl(url);
				return true;
			}
		});
		wb.setWebChromeClient(new WebChromeClient() {

			public void onProgressChanged(WebView view, int progress) {// 载入进度改变而触发
				if (progress == 100) {
					// handler.sendEmptyMessage(1);// 如果全部载入,隐藏进度对话框
				}

				super.onProgressChanged(view, progress);
			}

			@Override
			public void onReceivedTitle(WebView view, String title) {
				if (title.equals("仲裁服务")) {
					rl_title.setVisibility(View.VISIBLE);
				}else{
					rl_title.setVisibility(View.GONE);
				}
				Log.e("title", title + "...");				
				super.onReceivedTitle(view, title);
			}

			// 扩展支持alert事件
			@Override
			public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
				AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
				builder.setTitle("xxx提示").setMessage(message).setPositiveButton("确定", null);
				builder.setCancelable(false);
				builder.setIcon(R.drawable.ic_launcher);
				AlertDialog dialog = builder.create();
				dialog.show();
				result.confirm();
				return true;
			}

			// 扩展浏览器上传文件
			// 3.0++版本
			public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType) {
				openFileChooserImpl(uploadMsg);
			}

			// 3.0--版本
			public void openFileChooser(ValueCallback<Uri> uploadMsg) {
				openFileChooserImpl(uploadMsg);
			}

			public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType, String capture) {
				openFileChooserImpl(uploadMsg);
			}

			// For Android > 5.0
			public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> uploadMsg,
					FileChooserParams fileChooserParams) {
				openFileChooserImplForAndroid5(uploadMsg);
				return true;
			}
		});
	}

	public ValueCallback<Uri> mUploadMessage;
	public ValueCallback<Uri[]> mUploadMessageForAndroid5;

	public final static int FILECHOOSER_RESULTCODE = 1;
	public final static int FILECHOOSER_RESULTCODE_FOR_ANDROID_5 = 2;

	ProgressDialog progressBar;

	private void openFileChooserImpl(ValueCallback<Uri> uploadMsg) {
		mUploadMessage = uploadMsg;
		Intent i = new Intent(Intent.ACTION_GET_CONTENT);
		i.addCategory(Intent.CATEGORY_OPENABLE);
		i.setType("image/*");
		startActivityForResult(Intent.createChooser(i, "File Chooser"), FILECHOOSER_RESULTCODE);
	}

	private void openFileChooserImplForAndroid5(ValueCallback<Uri[]> uploadMsg) {
		mUploadMessageForAndroid5 = uploadMsg;
		Intent contentSelectionIntent = new Intent(Intent.ACTION_GET_CONTENT);
		contentSelectionIntent.addCategory(Intent.CATEGORY_OPENABLE);
		contentSelectionIntent.setType("image/*");

		Intent chooserIntent = new Intent(Intent.ACTION_CHOOSER);
		chooserIntent.putExtra(Intent.EXTRA_INTENT, contentSelectionIntent);
		chooserIntent.putExtra(Intent.EXTRA_TITLE, "Image Chooser");

		startActivityForResult(chooserIntent, FILECHOOSER_RESULTCODE_FOR_ANDROID_5);
	}

	// @Override
	// protected void initTopTitle() {
	// view.setVisibility(View.GONE);
	// }

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (wb.canGoBack() && event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
			// 获取历史列表
			WebBackForwardList mWebBackForwardList = wb.copyBackForwardList();
			// 判断当前历史列表是否最顶端,其实canGoBack已经判断过
			if (mWebBackForwardList.getCurrentIndex() > 0) {
				wb.goBack();
				return true;
			}
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
		if (requestCode == FILECHOOSER_RESULTCODE) {
			if (null == mUploadMessage)
				return;
			Uri result = intent == null || resultCode != RESULT_OK ? null : intent.getData();
			mUploadMessage.onReceiveValue(result);
			mUploadMessage = null;

		} else if (requestCode == FILECHOOSER_RESULTCODE_FOR_ANDROID_5) {
			if (null == mUploadMessageForAndroid5)
				return;
			Uri result = (intent == null || resultCode != RESULT_OK) ? null : intent.getData();
			if (result != null) {
				mUploadMessageForAndroid5.onReceiveValue(new Uri[] { result });
			} else {
				mUploadMessageForAndroid5.onReceiveValue(new Uri[] {});
			}
			mUploadMessageForAndroid5 = null;
		}
	}

	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {// 定义一个Handler，用于处理下载线程与UI间通讯
			if (!Thread.currentThread().isInterrupted()) {
				switch (msg.what) {
				case 0:
					progressBar.show();// 显示进度对话框
					break;
				case 1:
					progressBar.hide();// 隐藏进度对话框，不可使用dismiss()、cancel(),否则再次调用show()时，显示的对话框小圆圈不会动。
					break;
				}
			}

			super.handleMessage(msg);
		}
	};
	
	
	
}
