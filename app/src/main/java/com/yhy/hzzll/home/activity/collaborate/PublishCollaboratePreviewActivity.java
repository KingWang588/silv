package com.yhy.hzzll.home.activity.collaborate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.yhy.hzzll.R;
import com.yhy.hzzll.framework.MyData;
import com.yhy.hzzll.home.activity.ViewPagerExampleActivity;
import com.yhy.hzzll.mian.activity.BaseActivity;
import com.yhy.hzzll.utils.HttpDataUtils;
import com.yhy.hzzll.utils.ImageLoaderUtils;
import com.yhy.hzzll.utils.JSONTool;
import com.yhy.hzzll.utils.LogUtils;
import com.yhy.hzzll.utils.PrefsUtils;
import com.yhy.hzzll.utils.ToastUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import id.zelory.compressor.Compressor;

/**
 * 发布律师协作 预览
 * 
 * @author Yang
 * 
 */
public class PublishCollaboratePreviewActivity extends BaseActivity {

	/** 标题 */
	@ViewInject(R.id.tv_name)
	private TextView tv_name;

	/** 创建日期 */
	@ViewInject(R.id.tv_regdate)
	private TextView tv_regdate;

	/** 协作金额 */
	@ViewInject(R.id.tv_money)
	private TextView tv_money;

	/** 协作类型 */
	@ViewInject(R.id.tv_type)
	private TextView tv_type;
	/** 地区 */
	@ViewInject(R.id.tv_address)
	private TextView tv_address;

	/** 希望完成日期 */
	@ViewInject(R.id.tv_compledate)
	private TextView tv_compledate;

	/** 发布方 */
	@ViewInject(R.id.tv_publisher)
	private TextView tv_publisher;

	/** 发布方电话 */
	@ViewInject(R.id.tv_publish_phone)
	private TextView tv_publish_phone;

	/** 详情 */
	@ViewInject(R.id.tv_details)
	private TextView tv_details;

	/** 附件布局 */
	@ViewInject(R.id.ll_album)
	private LinearLayout ll_album;

	/** 发布按钮 */
	@ViewInject(R.id.tv_publish)
	private TextView tv_publish;

	int index = 0;

	private String hash;
	private String province;
	private String city;
	private String area;
	private String title;
	private String zqlx;
	private String zqdb;
	private String zqprice;
	private String zqcontent;
	private String hopedate;
	private String name;
	private String mobile;

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_publish_collaborate_preview);
		super.onCreate(arg0);
		ViewUtils.inject(this);
		ViewInit();
	}


	/**
	 * 上传文件
	 */
	private void upLoadFile(String filePath, final boolean ispicktrue) {
		String times = System.currentTimeMillis() + "";
		final RequestParams params = new RequestParams();
		params.addHeader("Authorization", PrefsUtils.getString(context, PrefsUtils.ACCESSKEY));
		params.addBodyParameter("name", times);
		params.addBodyParameter("hash", hash);
		params.addBodyParameter("reType", "json");
		try {
			params.addBodyParameter(times, new Compressor(PublishCollaboratePreviewActivity.this).compressToFile(new File(filePath)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		httpDataUtils.sendProgressPost(MyData.FILEUPLOAD, params);
//		if (!loading.isShow()) {
//			loading.showDialog(this);
//		}
		httpDataUtils.setSucessBack(new HttpDataUtils.SucessBack() {
			@Override
			public void sucess(ResponseInfo<String> arg0) {
				JSONObject objet = JSONTool.getJsonObjectOfStr(arg0.result);
				if (httpDataUtils.code(arg0.result)) {
//					JSONArray array = JSONTool.getJsonArray(objet, "data");
//					for (int i = 0; i < array.length(); i++) {
					try {
//							String url = array.getString(i);
//							if (url.contains("file_b")) {

						JSONObject data = objet.getJSONObject("data");
						String url = data.getString("imgurl");
//								url = url.substring(1, url.length());
//						cardImgUrls.add(url);
//						pathArr = pathArr + url + "-";
//						pathArr.substring(0, pathArr.length() - 1);
						if (ispicktrue) {
//							loading.dismissDialog();
						} else {
							if (index == PublishCollaborationActivity.photolist.size()) {
//								loading.dismissDialog();
								index = 0;
								publish();
							}
						}
//							}
					} catch (JSONException e) {
						e.printStackTrace();
					}
//					}
				} else {
					httpDataUtils.showMsg(arg0.result);
				}
			}
		});

		httpDataUtils.setFailBack(new HttpDataUtils.FailBack() {
			@Override
			public void fail(String msg) {
//				loading.dismissDialog();
			}
		});
	}


	private void ViewInit() {
//		loading = new DialogLoading();
		if (getIntent() != null) {
			hash = getIntent().getStringExtra("hash");
			province = getIntent().getStringExtra("province");
			city = getIntent().getStringExtra("city");
			area = getIntent().getStringExtra("area");
			title = getIntent().getStringExtra("title");
			zqlx = getIntent().getStringExtra("zqlx");
			zqdb = getIntent().getStringExtra("zqdb");
			zqprice = getIntent().getStringExtra("zqprice");
			zqcontent = getIntent().getStringExtra("zqcontent");
			hopedate = getIntent().getStringExtra("hopedate");
			name = getIntent().getStringExtra("name");
			mobile = getIntent().getStringExtra("mobile");
			tv_name.setText(title);
			tv_type.setText(getIntent().getStringExtra("type1Name") + "-"
					+ getIntent().getStringExtra("type2Name"));
			tv_money.setText(getIntent().getStringExtra("zqprice"));
			tv_address.setText(getIntent().getStringExtra("provinceName") + "-"
					+ getIntent().getStringExtra("cityName") + "-"
					+ getIntent().getStringExtra("areaName"));
			tv_regdate.setText(new SimpleDateFormat("yyyy-MM-dd")
					.format(new Date()));
			tv_compledate.setText(hopedate);
			tv_details.setText(zqcontent);
			tv_publisher.setText("发布方：" + name);
			tv_publish_phone.setText("联系方式：" + mobile);
			addView();
		} else {
			ToastUtils.getUtils(context).show("操作异常。。。");
			return;
		}
	}

	private void addView() {
		ll_album.removeAllViews();
		final ArrayList<String> urlList = new ArrayList<String>();
		if (PublishCollaborationActivity.photolist.size() != 0) {
			for (int i = 0; i < PublishCollaborationActivity.photolist.size(); i++) {
				urlList.add(PublishCollaborationActivity.photolist.get(i));
			}
		}
		if (PublishCollaborationActivity.photolist.size() != 0) {
			for (int i = 0; i < PublishCollaborationActivity.photolist.size(); i++) {
				View view = getLayoutInflater().inflate(R.layout.item_iamgeview, null);
				final ImageView iv_image = (ImageView) view.findViewById(R.id.iv_image);
				iv_image.setTag(i);
				iv_image.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						Intent intent = new Intent(PublishCollaboratePreviewActivity.this, ViewPagerExampleActivity.class);
						intent.putStringArrayListExtra(ViewPagerExampleActivity.PHOTO_URL_LIST_INTENT, urlList).putExtra("position", (int)iv_image.getTag());
						startActivity(intent);
					}
				});


				ImageLoaderUtils.initUtils().display("file://" + PublishCollaborationActivity.photolist.get(i), iv_image);
				ll_album.addView(view);
			}
		}
	}

	@OnClick(R.id.tv_publish)
	public void Onclick(View view) {
		switch (view.getId()) {
		case R.id.tv_publish:

			if (PublishCollaborationActivity.photolist.size()!=0){
				for (int i = 0; i < PublishCollaborationActivity.photolist.size(); i++) {
					index++;// 记录当前传到哪一张
					upLoadFile(PublishCollaborationActivity.photolist.get(i), false);
				}
			}else{
				publish();
			}
			break;
		}
	}

	private void publish() {
		RequestParams params = new RequestParams();
		params.addHeader("Authorization", PrefsUtils.getString(context, PrefsUtils.ACCESSKEY));
//		Log.e("454545465",PrefsUtils.getString(context, PrefsUtils.ACCESSKEY)+"");
		LogUtils.logE(PrefsUtils.getString(context, PrefsUtils.ACCESSKEY)+"");
		params.addBodyParameter("hash", hash);
		params.addBodyParameter("province", province);
		params.addBodyParameter("city", city);
		params.addBodyParameter("area", area);
		params.addBodyParameter("title", title);
		params.addBodyParameter("zqlx", zqlx);
		params.addBodyParameter("zqdb", zqdb);
		params.addBodyParameter("zqprice", zqprice);
		params.addBodyParameter("zqcontent", zqcontent);
		params.addBodyParameter("hopedate", hopedate);
		params.addBodyParameter("name", name);
		params.addBodyParameter("mobile", mobile);
		httpDataUtils.sendProgressPost(MyData.ADDLAWYERCOOP, params);
		httpDataUtils.setSucessBack(new HttpDataUtils.SucessBack() {
			@Override
			public void sucess(ResponseInfo<String> arg0) {
				if (httpDataUtils.code(arg0.result)) {
					try {
						JSONObject object = new JSONObject(arg0.result);
						String data = object.optString("data");
//						startActivity(new Intent().putExtra("data", data)
//								.putExtra("money", zqprice)
//								.setClass(context, LawyerCollaboratHosting2ChangeActivity.class));

						startActivity(new Intent().putExtra("data", data).setClass(context, PublishCooperateSucessActivity.class));
						finish();

						PublishCollaborationActivity.instance.finish();

					} catch (JSONException e) {
						e.printStackTrace();
					}
				} else {
					httpDataUtils.showMsg(arg0.result);
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
