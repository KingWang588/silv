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

public class EditAddressActivity extends Activity {

	@ViewInject(R.id.et_address)
	EditText et_adress;
	@ViewInject(R.id.tv_finish)
	TextView tv_finish;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_address);
		ViewUtils.inject(this);

		et_adress.setText(getIntent().getStringExtra("address"));

	}

	@OnClick({R.id.ic_back,R.id.tv_finish})
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.ic_back:
			finish();
			break;
		case R.id.tv_finish:
		updataAddress();
			break;
		}
	}

	private void updataAddress() {
		if(et_adress.getText().toString().equals("")){
			ToastUtils.getUtils(EditAddressActivity.this).show("请输入地址！");
			return;
		}
		
		RequestParams params = new RequestParams();
		HttpDataUtils httpDataUtils = new HttpDataUtils(EditAddressActivity.this);
		params.addHeader("Authorization", PrefsUtils.getString(getApplicationContext(), PrefsUtils.AUTHORIZATION));
		params.addBodyParameter("address", et_adress.getText().toString() + "");
		httpDataUtils.sendProgressPost(MyData.CM_USER_DATA_INFO, params);
		httpDataUtils.setSucessBack(new HttpDataUtils.SucessBack() {

			@Override
			public void sucess(ResponseInfo<String> arg0) {
				try {
					JSONObject object = new JSONObject(arg0.result);
					Log.e(arg0.result);
					String code = object.optString("code");
					String msg = object.optString("msg");
					if (code.equals("0")) {
                         ToastUtils.getUtils(EditAddressActivity.this).show("地址设置成功！");
                         finish();
					} else {

					}
					
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		});
		
	}

}
