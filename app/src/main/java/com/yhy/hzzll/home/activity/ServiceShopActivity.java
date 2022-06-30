package com.yhy.hzzll.home.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
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
import com.umeng.socialize.utils.Log;
import com.yhy.hzzll.R;
import com.yhy.hzzll.framework.MyData;
import com.yhy.hzzll.mian.activity.BaseActivity;
import com.yhy.hzzll.view.DialogLoading;
import com.yhy.hzzll.view.SharePopupWindow;

/**
 * 专业服务
 * 
 * @author Yang
 * 
 */
public class ServiceShopActivity extends BaseActivity {

	@ViewInject(R.id.wb)
	private WebView wb;

	private String shareUrl = "";
	private String shareText = "";
	
	View  view;
	@SuppressLint("SetJavaScriptEnabled")
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
		initialize();
	}

	private void initialize() {
		
		wb.addJavascriptInterface(this, "firstClick");
		
//		wb.getSettings().setJavaScriptEnabled(true);
		wb.loadUrl(MyData.PROFESSIONAL_SERVICES);
		wb.setWebViewClient(new WebViewClient() {

			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				
//				Log.e("+*+*+**+*+*+**+",url+"");
				if(url.equals(MyData.MOBILE_URL+"/")){
					finish();
//					 goToShare();
				}else if(url.equals(MyData.MOBILE_URL+"/n/0914/c.html/")){
					shareUrl =MyData.MOBILE_URL+"/n/0914/c.html#/";
					shareText = "专业服务";
					 goToShare();
				}else if(url.contains("shopdetail?id")){
//					Log.e("详情", url);
                    String[] infos = url.split("=");
//                    Log.e("详情", infos[1]);
					shareUrl =MyData.MOBILE_URL+"/n/0914/c.html#/shopdetail?id="+infos[1];
					shareText = "服务店铺详情";
					 goToShare();
				}						
				else{
					view.loadUrl(url);
				}
				
				return true;
			}
		});
		wb.setWebChromeClient(new WebChromeClient() {

			@Override
			public void onReceivedTitle(WebView view, String title) {
				//
//				if (title.equals("私律 | 用户的私人律师")) {
//					finish();
//				}

				Log.e("title", title + "...");

				super.onReceivedTitle(view, title);

			}
		});

	}

	@Override
	// 设置回退
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if ((keyCode == KeyEvent.KEYCODE_BACK) && wb.canGoBack()) {
			wb.goBack(); // goBack()表示返回WebView的上一页面
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
	
	
	@JavascriptInterface
	 private void goToShare(){
			SharePopupWindow popupWindow = new SharePopupWindow(this, new SharePopupWindow.Click() {
				@Override
				public void Select(SHARE_MEDIA shareStatus) {
					UMImage image = new UMImage(context, R.drawable.app2);
					switch (shareStatus) {
					case WEIXIN:
						Config.OpenEditor = true;
						Config.dialog = new DialogLoading().showDialog(ServiceShopActivity.this);
						new ShareAction(ServiceShopActivity.this).setPlatform(SHARE_MEDIA.WEIXIN)
								.setCallback(umShareListener).withText(shareText).withTitle("私律")
								.withMedia(image)
								// .withMedia(new
								// UMEmoji(context,"http://img.newyx.net/news_img/201306/20/1371714170_1812223777.gif"))
								.withTargetUrl(shareUrl).share();
						break;
					case WEIXIN_CIRCLE:
						// UMImage image = new UMImage(context,
						// "http://www.umeng.com/images/pic/social/integrated_3.png");
						Config.OpenEditor = true;
						Config.dialog = new DialogLoading().showDialog(ServiceShopActivity.this);
						new ShareAction(ServiceShopActivity.this).setPlatform(SHARE_MEDIA.WEIXIN_CIRCLE)
								.setCallback(umShareListener).withTitle("私律")
								.withText(shareText).withMedia(image)
								.withTargetUrl(shareUrl).share();
						break;
					case QQ:
						// UMImage image = new UMImage(context,
						// "http://www.umeng.com/images/pic/social/integrated_3.png");
						Config.OpenEditor = true;
						Config.dialog = new DialogLoading().showDialog(ServiceShopActivity.this);
						new ShareAction(ServiceShopActivity.this).setPlatform(SHARE_MEDIA.QQ)
								.setCallback(umShareListener).withTitle("私律")
								.withText(shareText).withMedia(image)
								// .withMedia(music)
								.withTargetUrl(shareUrl).share();
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
							ActivityCompat.requestPermissions(ServiceShopActivity.this, mPermissionList, 123);
						}
						Config.OpenEditor = true;
						Config.dialog = new DialogLoading().showDialog(ServiceShopActivity.this);
						new ShareAction(ServiceShopActivity.this).setPlatform(SHARE_MEDIA.SINA)
								.setCallback(umShareListener).withText(shareText+shareUrl)
//								 .withTitle("私律").withMedia(image)
								// .withExtra(new
								//
								// UMImage(ConsultDetailsActivity.this,R.drawable.user_head)
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
