package com.yhy.hzzll.mian.activity;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;

import androidx.fragment.app.FragmentActivity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.yhy.hzzll.framework.HzApplication;
import com.yhy.hzzll.message.MessageActivity;
import com.yhy.hzzll.utils.HttpDataUtils;
import com.yhy.hzzll.utils.MyActivityManager;
import com.yhy.hzzll.view.TitleInit;
import com.yhy.hzzll.view.TitleInit.Click;

/**
 * activity 基类
 * 
 * @author wangyang
 * 
 */
public class BaseActivity extends FragmentActivity {
	


	int isFristOpenCaseManagement = 0;
	public Context context;
	public HttpDataUtils httpDataUtils;
	public Gson gson;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		MyActivityManager.getInstance().pushOneActivity(this);
		context = getApplicationContext();
		httpDataUtils = new HttpDataUtils(this);
		gson = new GsonBuilder().serializeNulls().create();
		new TitleInit(this, new Click() {
			@Override
			public void clickRight() {
				startActivity(new Intent().setClass(context,
						MessageActivity.class));
			}

			@Override
			public void clickBack() {
				finish();
			}
		});
	}


	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	protected void onPause() {
		super.onPause();
	}

	@Override
	public void onDestroy() {
		HzApplication.getInstance().setCurrentActivity(null);
		httpDataUtils.httpclientCancel();
		super.onDestroy();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {

		return super.onKeyDown(keyCode, event);
	}

	@Override
	public void onBackPressed() {
		if (getRunningActivityName().equals(MainActivity.class.getName())) {
			Intent intent = new Intent(Intent.ACTION_MAIN);
			intent.addCategory(Intent.CATEGORY_HOME);
			startActivity(intent);
		} else {
			finish();
		}
		return;
	}

	public String getRunningActivityName() {
		ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
		String runningActivity = activityManager.getRunningTasks(1).get(0).topActivity.getClassName();
		return runningActivity;
	}

}
