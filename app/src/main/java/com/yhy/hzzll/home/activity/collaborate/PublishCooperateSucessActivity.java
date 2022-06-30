package com.yhy.hzzll.home.activity.collaborate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.yhy.hzzll.R;
import com.yhy.hzzll.mian.activity.BaseActivity;

/**
 * 发布律师协作成功
 * 
 * @author Yang
 * 
 */
public class PublishCooperateSucessActivity extends BaseActivity {

	private String data;

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_publish_cooperatesucess);
		super.onCreate(arg0);
		ViewUtils.inject(this);
		if (null != getIntent()) {
			data = getIntent().getStringExtra("data");
		}
	}

	@OnClick(R.id.tv_check)
	public void Onclick(View view) {
		switch (view.getId()) {
		case R.id.tv_check:
			startActivity(new Intent().putExtra("id", data).putExtra("title", "000000").putExtra("insert", false).setClass(context, LawyerCollaboratDetailsActivity.class));
			finish();
			break;
		}
	}
}
