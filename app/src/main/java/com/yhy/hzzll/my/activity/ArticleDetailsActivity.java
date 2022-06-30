package com.yhy.hzzll.my.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.yhy.hzzll.R;
import com.yhy.hzzll.entity.ArticleDetailsEntity;
import com.yhy.hzzll.framework.MyData;
import com.yhy.hzzll.mian.activity.BaseActivity;
import com.yhy.hzzll.utils.HttpDataUtils;
import com.yhy.hzzll.utils.PrefsUtils;
import com.yhy.hzzll.utils.ToastUtils;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 成功案例 ，文章 详情
 * 
 * @author Yang
 * 
 */
public class ArticleDetailsActivity extends BaseActivity {

	private String id;

	@ViewInject(R.id.tv_title)
	private TextView tv_title;

	@ViewInject(R.id.tv_date)
	private TextView tv_date;

	@ViewInject(R.id.tv_auth)
	private TextView tv_auth;

	@ViewInject(R.id.tv_content)
	private WebView tv_content;

	@ViewInject(R.id.tv_img)
	private TextView tv_img;

	@ViewInject(R.id.tv_collect)
	private TextView tv_collect;

	@ViewInject(R.id.tv_share)
	private TextView tv_share;

	String type ;
	
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_article_details);
		super.onCreate(arg0);
		ViewUtils.inject(this);
		if (null != getIntent()) {
			id = getIntent().getStringExtra("id");
			type = getIntent().getStringExtra("type");
		}
		
		Log.e("id", "*+*+*+*+*+"+id);
		getDetails(id);
	}

	private void getDetails(String id) {
		RequestParams params = new RequestParams();
		params.addQueryStringParameter("id", id);
		if(type.equals("article")){
			httpDataUtils.sendProgressGet(MyData.MEMBERARTICLE, params);
		}else{
			httpDataUtils.sendProgressGet(MyData.CASEDETIAL, params);
		}
		
		
		httpDataUtils.setSucessBack(new HttpDataUtils.SucessBack() {
			@Override
			public void sucess(ResponseInfo<String> arg0) {
				if (httpDataUtils.code(arg0.result)) {
					ArticleDetailsEntity entity = gson.fromJson(arg0.result, ArticleDetailsEntity.class);
					tv_title.setText(entity.getData().getTitle() + "");
					tv_date.setText("时间：" + entity.getData().getCreated_at());
//					try {
						tv_auth.setText("作者：" + entity.getData().getAuthor_id());
//					} catch (Exception e) {
//						// TODO: handle exception
//					}
					tv_content.loadDataWithBaseURL(null,
							Html.fromHtml(entity.getData().getContent()) + "",
							"text/html", "UTF-8", null);

				} else {
					httpDataUtils.showMsgNew(arg0.result);
				}
			}
		});
	}
	
	@OnClick({R.id.tv_collect,R.id.tv_share,R.id.tv_delete})
	public void onClick(View view){
		switch (view.getId()) {
		case R.id.tv_collect:
			
			break;
		case R.id.tv_share:
			
			break;
			case R.id.tv_delete:
				showDiolog();
				break;
		}
	}

	private void showDiolog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(ArticleDetailsActivity.this);
//		View view2 = getLayoutInflater().inflate(R.layout.view_dialog_delece_consult_reply, null);
		View view2 = LayoutInflater.from(ArticleDetailsActivity.this).inflate(R.layout.view_dialog_delece_consult_reply, null);
		view2.setBackgroundResource(R.drawable.background_view_dialog);
		builder.setView(view2);
		final Dialog dialog = builder.create();

		TextView tv_title = (TextView) view2.findViewById(R.id.tv_1);
		TextView tv_content = (TextView) view2.findViewById(R.id.tv_2);

		tv_title.setText("删除文章");
		tv_content.setText("删除文章后不可恢复，是否确认删除");

		TextView tv_yes = (TextView) view2.findViewById(R.id.tv_yes);
		TextView tv_no = (TextView) view2.findViewById(R.id.tv_no);
		tv_yes.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				deleteData();
				dialog.cancel();

			}
		});

		tv_no.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				dialog.cancel();
			}
		});
		dialog.show();
	}




	/** 删除 */
	private void deleteData() {
		RequestParams params = new RequestParams();
		params.addHeader("Authorization", PrefsUtils.getString(ArticleDetailsActivity.this, PrefsUtils.AUTHORIZATION));
		params.addBodyParameter("id", id);
		httpDataUtils.sendProgressPost(MyData.DELETE_ARTICLE, params);
		httpDataUtils.setSucessBack(new HttpDataUtils.SucessBack() {

			@Override
			public void sucess(ResponseInfo<String> arg0) {
				try {
					JSONObject object = new JSONObject(arg0.result);
					String code = object.optString("code");
					String msg = object.optString("message");
					if (code.equals("0")) {
						finish();
					}
					ToastUtils.getUtils(ArticleDetailsActivity.this).show(msg);
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		});
	}


}
