package com.yhy.hzzll.view;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.text.InputType;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.yhy.hzzll.R;
import com.yhy.hzzll.framework.MyData;
import com.yhy.hzzll.utils.HttpDataUtils;
import com.yhy.hzzll.utils.PrefsUtils;
import com.yhy.hzzll.utils.ToastUtils;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 拨号键盘
 * 
 * @author Yang
 * 
 */
public class CallPopupWindow extends PopupWindow {
	public View conentView;
	private Activity context;
	private EditText ed_phone;
	private String text = "";

	private DialogLoading loading;

	private HttpDataUtils httpDataUtils;
	private static final int CALL_PHONE_REQUEST_CODE = 1;
	public CallPopupWindow(final Activity context) {
		this.context = context;
		loading = new DialogLoading();
		httpDataUtils = new HttpDataUtils(context);
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		conentView = inflater.inflate(R.layout.popupwindow_keyboard, null);
		int width = context.getResources().getDisplayMetrics().widthPixels;
		// 设置SelectPicPopupWindow的View
		this.setContentView(conentView);
		// 设置SelectPicPopupWindow弹出窗体的宽
		this.setWidth(width);
		// 设置SelectPicPopupWindow弹出窗体的高
		this.setHeight(LayoutParams.WRAP_CONTENT);
		// 设置SelectPicPopupWindow弹出窗体可点击
		this.setFocusable(true);
		this.setOutsideTouchable(true);
		// 刷新状态
		this.update();
		// 实例化一个ColorDrawable颜色为半透明
		ColorDrawable dw = new ColorDrawable(0000000000);
		// 点back键和其他地方使其消失,设置了这个才能触发OnDismisslistener ，设置其他控件变化等操作
		this.setBackgroundDrawable(dw);
		// mPopupWindow.setAnimationStyle(android.R.style.Animation_Dialog);
		// 设置SelectPicPopupWindow弹出窗体动画效果
		this.setAnimationStyle(R.style.AnimationPreview);

		ImageView iv_close = (ImageView) conentView.findViewById(R.id.iv_close);
		LinearLayout tv_call = (LinearLayout) conentView
				.findViewById(R.id.tv_call);
		ed_phone = (EditText) conentView.findViewById(R.id.ed_phone);
		TextView tv_number1 = (TextView) conentView
				.findViewById(R.id.tv_number1);
		TextView tv_number2 = (TextView) conentView
				.findViewById(R.id.tv_number2);
		TextView tv_number3 = (TextView) conentView
				.findViewById(R.id.tv_number3);
		TextView tv_number4 = (TextView) conentView
				.findViewById(R.id.tv_number4);
		TextView tv_number5 = (TextView) conentView
				.findViewById(R.id.tv_number5);
		TextView tv_number6 = (TextView) conentView
				.findViewById(R.id.tv_number6);
		TextView tv_number7 = (TextView) conentView
				.findViewById(R.id.tv_number7);
		TextView tv_number8 = (TextView) conentView
				.findViewById(R.id.tv_number8);
		TextView tv_number9 = (TextView) conentView
				.findViewById(R.id.tv_number9);
		TextView tv_number0 = (TextView) conentView
				.findViewById(R.id.tv_number0);
		LinearLayout tv_delete = (LinearLayout) conentView
				.findViewById(R.id.tv_delete);

		iv_close.setOnClickListener(listener);
		tv_call.setOnClickListener(listener);
		tv_number1.setOnClickListener(listener);
		tv_number2.setOnClickListener(listener);
		tv_number3.setOnClickListener(listener);
		tv_number4.setOnClickListener(listener);
		tv_number5.setOnClickListener(listener);
		tv_number6.setOnClickListener(listener);
		tv_number7.setOnClickListener(listener);
		tv_number8.setOnClickListener(listener);
		tv_number9.setOnClickListener(listener);
		tv_number0.setOnClickListener(listener);
		tv_delete.setOnClickListener(listener);

	}

	private OnClickListener listener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.iv_close:
				dismiss();
				break;
			case R.id.tv_number1:
				text = text + "1";
				break;
			case R.id.tv_number2:
				text = text + "2";
				break;
			case R.id.tv_number3:
				text = text + "3";
				break;
			case R.id.tv_number4:
				text = text + "4";
				break;
			case R.id.tv_number5:
				text = text + "5";
				break;
			case R.id.tv_number6:
				text = text + "6";
				break;
			case R.id.tv_number7:
				text = text + "7";
				break;
			case R.id.tv_number8:
				text = text + "8";
				break;
			case R.id.tv_number9:
				text = text + "9";
				break;
			case R.id.tv_number0:
				text = text + "0";
				break;
			case R.id.tv_delete:
				if (text.length() != 0)
					text = text.substring(0, text.length() - 1);
				break;
			case R.id.tv_call:
				if (text.isEmpty()) {
					return;
				}
				// Intent intentPhone = new Intent(Intent.ACTION_CALL,
				// Uri.parse("tel:0571-56383200%23" + text));
				// context.startActivity(intentPhone);
				userOpen(text);
				break;
			}
			ed_phone.setText(text);
			ed_phone.setInputType(InputType.TYPE_NULL);
			ed_phone.setSelection(text.length());
		}
	};

	/** 用户开通电话存证 */
	private void userOpen(final String phone) {
		loading.showDialog(context);
		RequestParams params = new RequestParams();
		HttpUtils dataUtils = new HttpUtils();
		params.addBodyParameter("phone",
				PrefsUtils.getString(context, PrefsUtils.UPHONE));
		params.addBodyParameter("password", "123456");
		dataUtils.send(HttpMethod.POST, MyData.USEROPEN, params,
				new RequestCallBack<String>() {
					@Override
					public void onFailure(HttpException arg0, String arg1) {
						ToastUtils.getUtils(context).show(arg1);
						loading.dismissDialog();
					}

					@Override
					public void onSuccess(ResponseInfo<String> arg0) {
						if (httpDataUtils.code(arg0.result)) {
							callingRequest(phone);
						} else {
							try {
								JSONObject object = new JSONObject(arg0.result);
								if (object.optString("code").equals("999999")) {
									callingRequest(phone);
								} else {
									loading.dismissDialog();
									httpDataUtils.showMsg(arg0.result);
								}
							} catch (JSONException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							// try {
							// JSONObject object = new JSONObject(arg0.result);
							// if (object.optString("code").equals("999999")) {
							// JSONObject object2 = new JSONObject(object
							// .optString("data"));
							// String msg = object2.optString("msg");
							// if (msg.equals("该用户已被注册")) {
							// loading.dismissDialog();
							// }
							// } else {
							// httpDataUtils.showMsg(arg0.result);
							// }

						}
					}
				});
	}

	/** 用户拨打存证电话 */
	private void callingRequest(final String oppo) {
		if (oppo.isEmpty()) {
			return;
		}

		RequestParams params = new RequestParams();
		HttpUtils dataUtils = new HttpUtils();
		params.addBodyParameter("uid",
				PrefsUtils.getString(context, PrefsUtils.UID));
		params.addBodyParameter("phone",
				PrefsUtils.getString(context, PrefsUtils.UPHONE));
		params.addBodyParameter("oppo", oppo);
		params.addBodyParameter("time", "1");

		dataUtils.send(HttpMethod.POST, MyData.CALLING, params,
				new RequestCallBack<String>() {
					@Override
					public void onFailure(HttpException arg0, String arg1) {
						loading.dismissDialog();
						ToastUtils.getUtils(context).show(arg1);
					}

					@Override
					public void onSuccess(ResponseInfo<String> arg0) {
						Log.e("arg0222222222", arg0.result);
						loading.dismissDialog();
//						0571-95105856
//						0571-56383200
						if (httpDataUtils.code(arg0.result)) {
//							String to = "0571-95105856" ;
////						+"%23" + oppo;
////							Intent intentPhone = new Intent(Intent.ACTION_CALL,
////									Uri.parse("tel:" + to));
////							context.startActivity(intentPhone);
//							Intent intent = new Intent(Intent.ACTION_DIAL, Uri
//									.parse("tel:" + to));
//							intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//							context.startActivity(intent);

							showDiolog(oppo);
						} else {
							httpDataUtils.showMsg(arg0.result);
						}
					}
				});
	}


	private void showDiolog(String phone) {
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		View view2 = context.getLayoutInflater().inflate(R.layout.view_dialog_delece_consult_reply, null);
		view2.setBackgroundResource(R.drawable.background_view_dialog);
		builder.setView(view2);
		final Dialog dialog = builder.create();

		TextView tv_title = (TextView) view2.findViewById(R.id.tv_1);
		TextView tv_content = (TextView) view2.findViewById(R.id.tv_2);

		tv_title.setText("通话存证");
		tv_content.setText("你要对（"+phone+"）拨号吗？私律会对通话内容进行录音并存证。 ");

		TextView tv_yes = (TextView) view2.findViewById(R.id.tv_yes);
		TextView tv_no = (TextView) view2.findViewById(R.id.tv_no);
		tv_yes.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {

				if (ContextCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE)
						!= PackageManager.PERMISSION_GRANTED) {
					//申请获取联系人权限
					ActivityCompat.requestPermissions(context,new String[]{Manifest.permission.CALL_PHONE},
							CALL_PHONE_REQUEST_CODE);
					return;
				} else{
					String to = "0571-95105856";
////							+ "%23" + oppo;
					Intent intent = new Intent(Intent.ACTION_CALL);
					Uri data = Uri.parse("tel:" + to);
					intent.setData(data);
					context.startActivity(intent);
					dialog.cancel();
				}

			}
		});

		tv_no.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				dialog.cancel();
			}
		});
		dialog.show();
	}

	/**
	 * 显示popupWindow
	 * 
	 * @param parent
	 */
	public void showPopupWindow(View parent) {
		if (!this.isShowing()) {
			// 以下拉方式显示popupwindow
			this.showAtLocation(parent, Gravity.BOTTOM, 0, 0);
		} else {
			this.dismiss();
		}
	}
}
