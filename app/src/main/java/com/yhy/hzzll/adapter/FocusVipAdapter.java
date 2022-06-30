package com.yhy.hzzll.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.yhy.hzzll.R;
import com.yhy.hzzll.entity.FocusLawyerEntity;
import com.yhy.hzzll.framework.Constans;
import com.yhy.hzzll.framework.MyData;
import com.yhy.hzzll.home.activity.lawyeroffice.LawyerOfficeActivity;
import com.yhy.hzzll.mian.activity.LoginActivity;
import com.yhy.hzzll.my.activity.MyFocusActivity;
import com.yhy.hzzll.utils.HttpDataUtils;
import com.yhy.hzzll.utils.ImageLoaderUtils;
import com.yhy.hzzll.utils.JSONTool;
import com.yhy.hzzll.utils.PrefsUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class FocusVipAdapter extends BaseAdapter {

	private Context context;

	private String[] strData;

	private char[] chaTitle;

	private LayoutInflater layoutInflater;

	private String[] cata;

	private String[] url;

	private HttpDataUtils httpDataUtils;

	private List<FocusLawyerEntity> focusLawyerList;

	private Handler handler;

	public FocusVipAdapter(Context context, String[] strData, char[] chaTitle, String[] cata, String[] url,
			List<FocusLawyerEntity> focusLawyerList, Handler handler) {
		this.context = context;
		this.strData = strData;
		this.chaTitle = chaTitle;
		this.cata = cata;
		this.url = url;
		this.focusLawyerList = focusLawyerList;
		this.handler = handler;
		// layoutInflater = LayoutInflater.from(this.context);
		httpDataUtils = new HttpDataUtils(context);
	}

	@Override
	public int getCount() {
		return strData.length;
	}

	@Override
	public Object getItem(int position) {
		return strData[position];
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	MyTag mytag = null;

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {

		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(R.layout.item_focus_vip, null);
			mytag = new MyTag();
			mytag.text_char = (TextView) convertView.findViewById(R.id.text_char);
			mytag.text_text = (TextView) convertView.findViewById(R.id.text_text);
			mytag.iv_tag = (TextView) convertView.findViewById(R.id.iv_tag);
//			mytag.tv_edit = (TextView) convertView.findViewById(R.id.tv_edit);
			mytag.tv_delete = (TextView) convertView.findViewById(R.id.tv_delete);
			mytag.ll_null_layout = (LinearLayout) convertView.findViewById(R.id.ll_null_layout);
			mytag.iv_img = (ImageView) convertView.findViewById(R.id.iv_img);
			mytag.horizontalScrollView = (HorizontalScrollView) convertView.findViewById(R.id.horizontalScrollView);
			convertView.setTag(mytag);
		} else {
			mytag = (MyTag) convertView.getTag();
		}

		String text = strData[position];
		mytag.text_text.setText(text);

		char cha = chaTitle[position];

		if (position > 0) {
			char cha2 = chaTitle[position - 1];
			if (cha == cha2) {
				mytag.text_char.setVisibility(View.GONE);
			} else {
				mytag.text_char.setText(cha + "");
				mytag.text_char.setVisibility(View.VISIBLE);
			}
		} else {
			mytag.text_char.setText(cha + "");
			mytag.text_char.setVisibility(View.VISIBLE);
		}

		LayoutParams params = new LayoutParams(context.getResources().getDisplayMetrics().widthPixels,
				LayoutParams.MATCH_PARENT);
		mytag.ll_null_layout.setLayoutParams(params);
		String cates = cata[position].substring(1, cata[position].length());
		if (cates.equals("1")) {
			mytag.iv_tag.setText("律师");
		} else if (cates.equals("0")) {
			mytag.iv_tag.setText("用户");
		} else if (cates.equals("2")) {
			mytag.iv_tag.setText("企业");
		} else if (cates.equals("3")) {
			mytag.iv_tag.setText("服务商店");
		} else {
			mytag.iv_tag.setText("律师");
		}

		String u = url[position].substring(1, url[position].length());
		ImageLoaderUtils.initUtils().display(MyData.IP + u, mytag.iv_img);

		mytag.tv_delete.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				for (int i = 0; i < focusLawyerList.size(); i++) {
					if (strData[position].equals(focusLawyerList.get(i).getTruename())) {
						getCollectOrCollect(focusLawyerList.get(i).getNid());
					}
				}
			}
		});

		mytag.ll_null_layout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				for (int i = 0; i < focusLawyerList.size(); i++) {
					if (strData[position].equals(focusLawyerList.get(i).getTruename())) {
						if (focusLawyerList.get(i).getUtype().equals("1")) {
							Intent intent = new Intent(context, LawyerOfficeActivity.class);
							intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
							intent.putExtra(LawyerOfficeActivity.USER_ID_INTENT, focusLawyerList.get(i).getNid())
									.putExtra("title", focusLawyerList.get(i).getNickname()).putExtra("from", "focus");
							context.startActivity(intent);
						}
					}
				}
			}
		});
		return convertView;
	}

	/**
	 * 收藏/取消收藏
	 */
	private void getCollectOrCollect(String uid) {
		RequestParams params = new RequestParams();
		params.addHeader("Authorization", PrefsUtils.getString(context, PrefsUtils.ACCESSKEY));
		params.addQueryStringParameter("cate", "laweroffice");
		params.addQueryStringParameter("nid", uid);
		httpDataUtils.sendProgressGet(MyData.COLLECT_OR_UNCOLLECT, params);
		httpDataUtils.setSucessBack(new HttpDataUtils.SucessBack() {

			@Override
			public void sucess(ResponseInfo<String> arg0) {
				try {
					JSONObject object = new JSONObject(arg0.result);
					String code = JSONTool.getString(object, "code");
					String msg = JSONTool.getString(object, "msg");
					if (code.equals(Constans.SUCCESS_CODE)) { // 成功
						// String data = JSONTool.getString(object, "data");
						handler.sendEmptyMessage(1);
					}
					if (msg.equals("您还未登录，请先登录！")) {
						context.startActivity(new Intent().putExtra("activity", MyFocusActivity.class.toString())
								.setClass(context, LoginActivity.class));
					} else {
						// ToastUtils.getUtils(context).show(msg);
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		});
	}

	public class MyTag {
		ImageView iv_img;
		TextView text_char, text_text, iv_tag, tv_edit, tv_delete;
		LinearLayout ll_null_layout;
		HorizontalScrollView horizontalScrollView;
	}

}
