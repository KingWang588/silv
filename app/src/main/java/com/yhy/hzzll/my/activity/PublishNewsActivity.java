package com.yhy.hzzll.my.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.yhy.hzzll.R;
import com.yhy.hzzll.mian.activity.BaseActivity;
import com.yhy.hzzll.framework.MyData;
import com.yhy.hzzll.utils.HttpDataUtils;
import com.yhy.hzzll.utils.PrefsUtils;
import com.yhy.hzzll.utils.ToastUtils;

/**
 * 我的办公室--发表学术文章
 * 
 * @author Yang
 * 
 */
public class PublishNewsActivity extends BaseActivity {

	@ViewInject(R.id.et_title)
	private EditText et_title;

	@ViewInject(R.id.et_info)
	private EditText et_info;

	@ViewInject(R.id.et_keyword)
	private EditText et_keyword;

	@ViewInject(R.id.et_details)
	private EditText et_details;

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_publish_news);
		super.onCreate(arg0);
		ViewUtils.inject(this);
	}

	@OnClick(R.id.tv_save)
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.tv_save:
			if (et_title.getText().toString().trim().isEmpty()) {
				ToastUtils.getUtils(context).show("标题不能为空");
				return;
			}
			if (et_info.getText().toString().trim().isEmpty()) {
				ToastUtils.getUtils(context).show("请输入摘要");
				return;
			}
			if (et_keyword.getText().toString().trim().isEmpty()) {
				ToastUtils.getUtils(context).show("关键字没填");
				return;
			}
			if (et_details.getText().toString().trim().isEmpty()) {
				ToastUtils.getUtils(context).show("学术内容没填");
				return;
			}
			send();
			break;
		default:
			break;
		}
	}

	private void send() {
		RequestParams params = new RequestParams();
		params.addHeader("Authorization", PrefsUtils.getString(context, PrefsUtils.AUTHORIZATION));
		params.addBodyParameter("title", et_title.getText().toString());
		params.addBodyParameter("content", et_details.getText().toString());
		params.addBodyParameter("seo_keyword", et_keyword.getText().toString());
		params.addBodyParameter("seo_description", et_info.getText().toString());

		httpDataUtils.sendProgressPost(MyData.ARTICLECREATE, params);
		httpDataUtils.setSucessBack(new HttpDataUtils.SucessBack() {

			@Override
			public void sucess(ResponseInfo<String> arg0) {
				if (httpDataUtils.code(arg0.result)) {
					httpDataUtils.showMsgNew(arg0.result);
					setResult(0x6002);
					finish();
				} else {
					httpDataUtils.showMsgNew(arg0.result);
				}
			}
		});
		httpDataUtils.setFailBack(new HttpDataUtils.FailBack() {

			@Override
			public void fail(String msg) {
				ToastUtils.getUtils(context).show(msg);
			}
		});

	}
}
