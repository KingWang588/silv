package com.yhy.hzzll.my.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.yhy.hzzll.R;
import com.yhy.hzzll.adapter.MyCaseAdapter;
import com.yhy.hzzll.adapter.MyCaseAdapter.OnActionLinsenter;
import com.yhy.hzzll.entity.MyCaseEntity;
import com.yhy.hzzll.framework.Constans;
import com.yhy.hzzll.framework.HzApplication;
import com.yhy.hzzll.framework.MyData;
import com.yhy.hzzll.mian.activity.BaseActivity;
import com.yhy.hzzll.mian.activity.LoginActivity;
import com.yhy.hzzll.mian.entity.DataUserEntity;
import com.yhy.hzzll.utils.HttpDataUtils;
import com.yhy.hzzll.utils.HttpDataUtils.FailBack;
import com.yhy.hzzll.utils.JSONTool;
import com.yhy.hzzll.utils.PrefsUtils;
import com.yhy.hzzll.utils.ToastUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;

//import com.yhy.hzzll.office.activity.CaseManageProgressActivity;

/**
 * 我的关联案件
 * 
 * @author Yang
 * 
 */
public class MyCaseActivity extends BaseActivity implements OnActionLinsenter {

	@ViewInject(R.id.lv_footmark)
	private PullToRefreshListView lv_footmark;
	@ViewInject(R.id.tv_no_data)
	TextView tv_no_data;
	private MyCaseAdapter mAdapter;
	/** 数据分页 */
	private int index = 1;
	/**
	 * 标记是刷新还是加载更多
	 */
	private boolean isPullRefresh;
	private ArrayList<MyCaseEntity> mList;
	private String accessKey;
	DataUserEntity userEntity;

	@Override
	protected void onCreate(Bundle arg0) {
		setContentView(R.layout.activity_mycase);
		super.onCreate(arg0);
		ViewUtils.inject(this);
		viewInit();
	}

	private void viewInit() {
		userEntity = (DataUserEntity) HzApplication.getInstance().getUserEntityCache().getAsObject(Constans.USER_INFO);
		if (userEntity == null) {
			startActivity(new Intent().setClass(getApplicationContext(), LoginActivity.class));
			finish();
			return;
		}
		mList = new ArrayList<MyCaseEntity>();
		accessKey = PrefsUtils.getString(getApplicationContext(), PrefsUtils.ACCESSKEY);
		mAdapter = new MyCaseAdapter(context, mList);
		mAdapter.setOnActionLinsenter(this);
		lv_footmark.setMode(Mode.BOTH);
		lv_footmark.setEmptyView(tv_no_data);
		lv_footmark.setOnRefreshListener(new ListViewOnRefreshListener());
		lv_footmark.setAdapter(mAdapter);
		publishViewInit();
	}

	/** 进页面自动刷新 */
	private void publishViewInit() {
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				lv_footmark.setRefreshing(true);
			}
		}, 300);
	}

	class ListViewOnRefreshListener implements OnRefreshListener2<ListView> {

		@Override
		public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
			resetPageIndex();
			isPullRefresh = true;
			getCreditorAssignmentList();

		}

		@Override
		public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
			addPageIndex();
			isPullRefresh = false;
			getCreditorAssignmentList();

		}
	}

	private void resetPageIndex() {
		index = 1;
	}

	private void addPageIndex() {
		++index;
	}

	/**
	 * 我的关联案件
	 */
	private void getCreditorAssignmentList() {
		RequestParams params = new RequestParams();
		params.addHeader("Authorization", accessKey);
		params.addQueryStringParameter("page", index + "");
		httpDataUtils.sendGet(MyData.GET_CASE, params);
		httpDataUtils.setSucessBack(new HttpDataUtils.SucessBack() {

			@Override
			public void sucess(ResponseInfo<String> arg0) {
				lv_footmark.onRefreshComplete();
				if (isPullRefresh) {
					mList.clear();
				}
				try {
					JSONObject object = new JSONObject(arg0.result);
					String code = JSONTool.getString(object, "code");
					String msg = JSONTool.getString(object, "msg");
					if (code.equals(Constans.SUCCESS_CODE)) { // 成功
						JSONObject data = JSONTool.getJsonObject(object, "data");
						String pages = JSONTool.getString(data, "pages");
						JSONArray list = JSONTool.getJsonArray(data, "list");
						Type type = new TypeToken<ArrayList<MyCaseEntity>>() {
						}.getType();
						mList.addAll((ArrayList<MyCaseEntity>) gson.fromJson(list.toString(), type));
						mAdapter.notifyDataSetChanged();
					} else {
						ToastUtils.getUtils(getApplicationContext()).show(msg);
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}

		});
		httpDataUtils.setFailBack(new FailBack() {

			@Override
			public void fail(String msg) {
				lv_footmark.onRefreshComplete();
			}
		});
	}

	@Override
	public void onLookProgress(View v, int position) {
//		Intent intent = new Intent(this, CaseManageProgressActivity.class);
//		intent.putExtra(CaseManageProgressActivity.CASEID_INTENT, mList.get(position).getId());
//		//intent.putExtra(CaseManageProgressActivity.DELEGATION_NAME_INTENT, list.get(position).get);
//		intent.putExtra(CaseManageProgressActivity.CASE_TITLE_INTENT, mList.get(position).getTitle());
//		intent.putExtra("from","mycase");
//		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//		startActivity(intent);
	}
}
