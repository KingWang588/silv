package com.yhy.hzzll.home.activity;

import android.Manifest;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.umeng.socialize.Config;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.yhy.hzzll.R;
import com.yhy.hzzll.framework.MyData;
import com.yhy.hzzll.mian.activity.BaseActivity;
import com.yhy.hzzll.view.DialogLoading;
import com.yhy.hzzll.view.SharePopupWindow;

/*
 * @author Yang
 * 
 */
public class AssignmentActivity extends BaseActivity {

	@ViewInject(R.id.wb)
	private WebView wb;
	private String title = "0";
	
	private String shareUrl = "";
	private String shareText = "";
	View view;
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		  view  = getLayoutInflater().inflate(R.layout.activity_serviceshop, null);  
		setContentView(view);
		super.onCreate(arg0);
		ViewUtils.inject(this);
		// 设置 缓存模式
		wb.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
		// 开启 DOM storage API 功能
		wb.getSettings().setDomStorageEnabled(true);
		wb.getSettings().setJavaScriptEnabled(true);
		
		if (null != getIntent()) {
			title = getIntent().getStringExtra("title");
			if (title.equals("0")) {
				initialize(MyData.ASSIGNMENT_OF_DEBT);
			} else if (title.equals("4")) {
				initialize(MyData.ASSET_CLUES_FOR);
			}
		}
	}

	
	private void initialize(String url) {
		wb.getSettings().setJavaScriptEnabled(true);
		wb.loadUrl(url);
		wb.setWebViewClient(new webViewClient());
		
		wb.setWebChromeClient(new WebChromeClient() {

			@Override
			public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
				Log.e("12132131", result + "..."+message+"1214546"+url);
				return super.onJsAlert(view, url, message, result);
			}

			@Override
			public void onReceivedTitle(WebView view, String title) {

				Log.e("title", title + "...");

				super.onReceivedTitle(view, title);

			}
		});
		
		

		
	}

	class webViewClient extends WebViewClient {
		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			
			Log.e("+*+*+**+*+*+**+",url+"");
			
			if(url.equals(MyData.MOBILE_URL+"/")){
				finish();
//				 goToShare();
			}else if(url.equals(MyData.MOBILE_URL+"/n/0914/a.html/")){
				shareUrl ="http://testmobile.hzzll.com/n/0914/a.html#/";
				shareText = "债权转让";
				 goToShare();
			}else if(url.equals(MyData.MOBILE_URL+"/n/0914/b.html/")){
				shareUrl =MyData.MOBILE_URL+"/n/0914/b.html#/";
				shareText = "资产线索悬赏";
				 goToShare();
			}else if(url.equals(MyData.MOBILE_URL+"/n/0914/arz.html/")){
				shareUrl =MyData.MOBILE_URL+"/n/0914/arz.html#/";
				shareText = "债权融资";
				 goToShare();
			}else if(url.equals(MyData.MOBILE_URL+"/n/0914/ahelp.html/")){
				shareUrl =MyData.MOBILE_URL+"/n/0914/ahelp.html#/";
				shareText = "债务危机求助";
				 goToShare();
			}else if(url.equals(MyData.MOBILE_URL+"/n/0914/ayw.html/")){
				shareUrl =MyData.MOBILE_URL+"/n/0914/ayw.html#/";
				shareText = "债权易物";
				 goToShare();
			}else if(url.contains("detail")){
			    String[] infos = url.split("=");
				if(url.contains("a.html")){
					shareUrl =MyData.MOBILE_URL+"/n/0914/a.html#/detail?id="+infos[1];
					shareText = "债权流转详情";
				}else if(url.contains("arz.html")){
					shareUrl =MyData.MOBILE_URL+"/n/0914/arz.html#/detail?id="+infos[1];
					shareText = "债权融资详情";
				}else if(url.contains("ayw.html")){
					shareUrl =MyData.MOBILE_URL+"/n/0914/ayw.html#/detail?id="+infos[1];
					shareText = "债权易物详情";
				}else if(url.contains("ahelp.html")){
					shareUrl =MyData.MOBILE_URL+"/n/0914/ahelp.html#/detail?id="+infos[1];
					shareText = "债务危机求助详情";
				}else if(url.contains("b.html")){
					shareUrl =MyData.MOBILE_URL+"/n/0914/b.html#/detail?id="+infos[1];
					shareText = "资产线索悬赏详情";
				}
				 goToShare();
			}																									
			else{
				view.loadUrl(url);
			}
			return true;
		}		
	}
	
	

	 private void goToShare(){
			
			SharePopupWindow popupWindow = new SharePopupWindow(this, new SharePopupWindow.Click() {
				@Override
				public void Select(SHARE_MEDIA shareStatus) {
					UMImage image = new UMImage(context, R.drawable.app2);
					switch (shareStatus) {
					case WEIXIN:
						Config.OpenEditor = true;
						Config.dialog = new DialogLoading().showDialog(AssignmentActivity.this);
						new ShareAction(AssignmentActivity.this).setPlatform(SHARE_MEDIA.WEIXIN)
								.setCallback(umShareListener).withText(shareText).withTitle("私律")
								.withMedia(image)
								// .withMedia(new
								// UMEmoji(context,"http://img.newyx.net/news_img/201306/20/1371714170_1812223777.gif"))
								.withTargetUrl(shareUrl)
								.share();
						break;
					case WEIXIN_CIRCLE:
						// UMImage image = new UMImage(context,
						// "http://www.umeng.com/images/pic/social/integrated_3.png");
						Config.OpenEditor = true;
						Config.dialog = new DialogLoading().showDialog(AssignmentActivity.this);
						new ShareAction(AssignmentActivity.this).setPlatform(SHARE_MEDIA.WEIXIN_CIRCLE)
								.setCallback(umShareListener).withTitle("私律")
								.withText(shareText).withMedia(image)
								.withTargetUrl(shareUrl)
								.share();
						break;
					case QQ:
						// UMImage image = new UMImage(context,
						// "http://www.umeng.com/images/pic/social/integrated_3.png");
						Config.OpenEditor = true;
						Config.dialog = new DialogLoading().showDialog(AssignmentActivity.this);
						new ShareAction(AssignmentActivity.this).setPlatform(SHARE_MEDIA.QQ)
								.setCallback(umShareListener).withTitle("私律")
								.withText(shareText).withMedia(image)
								// .withMedia(music)
								.withTargetUrl(shareUrl)
								.share();
						break;
					case SINA:
						// UMImage image = new UMImage(context,
						// "http://www.umeng.com/images/pic/social/integrated_3.png");
						if (Build.VERSION.SDK_INT >= 23) {
							String[] mPermissionList = new String[] { Manifest.permission.WRITE_EXTERNAL_STORAGE,
									Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.CALL_PHONE,
									Manifest.permission.READ_LOGS, Manifest.permission.READ_PHONE_STATE,
									Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.SET_DEBUG_APP,
									Manifest.permission.SYSTEM_ALERT_WINDOW, Manifest.permission.GET_ACCOUNTS,
									Manifest.permission.WRITE_APN_SETTINGS };
							ActivityCompat.requestPermissions(AssignmentActivity.this, mPermissionList, 123);
						}
						Config.OpenEditor = true;
						Config.dialog = new DialogLoading().showDialog(AssignmentActivity.this);
						new ShareAction(AssignmentActivity.this).setPlatform(SHARE_MEDIA.SINA)
								.setCallback(umShareListener).withText(shareText + shareUrl)
//								 .withTitle("私律").withMedia(image)
								// .withExtra(new UMImage(ConsultDetailsActivity.this,R.drawable.user_head)
//								 .withTargetUrl(shareUrl)
								.share();
						break;
					default:
						break;
					}
				}
			});
			popupWindow.showPopupWindow(view);
	 }
	
		private UMShareListener umShareListener = new UMShareListener() {
			@Override
			public void onResult(SHARE_MEDIA platform) {
				Log.d("plat", "platform" + platform);
				if (platform.name().equals("WEIXIN_FAVORITE")) {
					Toast.makeText(context, platform + " 收藏成功啦", Toast.LENGTH_SHORT).show();
				} else {
					Toast.makeText(context, platform + " 分享成功啦", Toast.LENGTH_SHORT).show();
				}
			}

			@Override
			public void onError(SHARE_MEDIA platform, Throwable t) {
				Toast.makeText(context, platform + " 分享失败啦", Toast.LENGTH_SHORT).show();
				if (t != null) {
					Log.d("throw", "throw:" + t.getMessage());
				}
			}

			@Override
			public void onCancel(SHARE_MEDIA platform) {
				Toast.makeText(context, platform + " 分享取消了", Toast.LENGTH_SHORT).show();
			}
		};
	
}
