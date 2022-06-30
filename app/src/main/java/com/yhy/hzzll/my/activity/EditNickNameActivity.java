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

public class EditNickNameActivity extends Activity {

    @ViewInject(R.id.et_nickname)
    EditText et_nickname;
    @ViewInject(R.id.tv_finish)
    TextView tv_finish;
    @ViewInject(R.id.tv_length)
    TextView tv_length;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_nick_name);
        ViewUtils.inject(this);

//		et_nickname.addTextChangedListener(new TextWatcher() {
//			@Override
//			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//			}
//
//			@Override
//			public void onTextChanged(CharSequence s, int start, int before, int count) {
//				int length = s.length();
//				if(length >2&&length<12){
//
//					Resources resources=getBaseContext().getResources();
//					Drawable drawable=resources.getDrawable(R.drawable.backroud_bule);
////					tv_finish.setBackground(getResources().R.drawable.backroud_bule);
//					tv_finish.setClickable(true);
//				}else{
//					tv_finish.setBackground(null);
//					tv_finish.setClickable(false);
//
//				}
//			}
//
//			@Override
//			public void afterTextChanged(Editable s) {
//
//
//
//			}
//		});

    }

    @OnClick({R.id.ic_back, R.id.tv_finish})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ic_back:
                finish();
                break;
            case R.id.tv_finish:
                updataNickName();
                break;
        }
    }


    private void updataNickName() {

        if (et_nickname.getText().toString().equals("")) {
            ToastUtils.getUtils(EditNickNameActivity.this).show("请输入昵称！");
            return;
        }

        RequestParams params = new RequestParams();
        HttpDataUtils httpDataUtils = new HttpDataUtils(EditNickNameActivity.this);
        params.addHeader("Authorization", PrefsUtils.getString(getApplicationContext(), PrefsUtils.AUTHORIZATION));
        params.addBodyParameter("nickname", et_nickname.getText().toString() + "");
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

//                        Map<UserInfoFieldEnum, Object> fields = new HashMap<>(1);
//                        fields.put(UserInfoFieldEnum.Name, et_nickname.getText().toString() + "");
//                        NIMClient.getService(UserService.class).updateUserInfo(fields)
//                                .setCallback(new RequestCallbackWrapper<Void>() {
//                                    @Override
//                                    public void onResult(int i, Void aVoid, Throwable throwable) {
//
//                                    }
//                                });
                        ToastUtils.getUtils(EditNickNameActivity.this).show("昵称设置成功！");
                        finish();
                    } else {

                    }
                    // ToastUtils.getUtils(getApplicationContext()).show(msg);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }


}
