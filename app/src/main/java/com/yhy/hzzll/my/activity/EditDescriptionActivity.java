package com.yhy.hzzll.my.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.umeng.socialize.utils.Log;
import com.yhy.hzzll.R;
import com.yhy.hzzll.framework.MyData;
import com.yhy.hzzll.utils.HttpDataUtils;
import com.yhy.hzzll.utils.PrefsUtils;
import com.yhy.hzzll.utils.ToastUtils;

import org.json.JSONException;
import org.json.JSONObject;

public class EditDescriptionActivity extends Activity {

	@ViewInject(R.id.et_description)
	EditText et_description;
	@ViewInject(R.id.tv_finish)
	TextView tv_finish;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_description);
		ViewUtils.inject(this);

		if(getIntent().getStringExtra("description")!= null)
		et_description.setText("    "+getIntent().getStringExtra("description"));

	}

	@OnClick({R.id.ic_back,R.id.tv_finish})
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.ic_back:
			finish();
			break;
		case R.id.tv_finish:
			CreateDescription();
			break;
		}
	}

	private void CreateDescription() {
		if(et_description.getText().toString().equals("")){
			ToastUtils.getUtils(EditDescriptionActivity.this).show("请输入律师简介！");
			return;
		}
		
		RequestParams params = new RequestParams();
		HttpDataUtils httpDataUtils = new HttpDataUtils(EditDescriptionActivity.this);
		params.addHeader("Authorization", PrefsUtils.getString(getApplicationContext(), PrefsUtils.AUTHORIZATION));
		params.addBodyParameter("lawyer_intro", et_description.getText().toString() + "");
		httpDataUtils.sendProgressPost(MyData.CM_USER_DATA_INFO, params);
		httpDataUtils.setSucessBack(new HttpDataUtils.SucessBack() {

			@Override
			public void sucess(ResponseInfo<String> arg0) {
				try {
					JSONObject object = new JSONObject(arg0.result);
					Log.e(arg0.result);
					String code = object.optString("code");
					String msg = object.optString("message");
					if (code.equals("0")) {

                         ToastUtils.getUtils(EditDescriptionActivity.this).show("律师简介设置成功！");
                         finish();

					} else {

						 ToastUtils.getUtils(getApplicationContext()).show(msg);

					}

				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		});
	}
}
