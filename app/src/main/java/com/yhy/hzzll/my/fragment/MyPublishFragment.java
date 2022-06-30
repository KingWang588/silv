package com.yhy.hzzll.my.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;

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
import com.yhy.hzzll.adapter.MyPublishLawyerAdapter;
import com.yhy.hzzll.entity.MyPublishOfLawyerCollaborationEntity;
import com.yhy.hzzll.framework.MyData;
import com.yhy.hzzll.home.activity.collaborate.LawyerCollaboratDetailsActivity;
import com.yhy.hzzll.mian.fragment.BaseFragment;
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

/**
 * 我发布的
 * 
 * @author zcm
 * 
 */
public class MyPublishFragment extends BaseFragment {
	@ViewInject(R.id.lv)
	PullToRefreshListView listview;
	@ViewInject(R.id.tv_no_data)
	TextView tv_no_data;
	/** 数据分页 */
	private int index = 1;
	/**
	 * 标记是刷新还是加载更多
	 */
	private boolean isPullRefresh;
	private Activity mActivity;
	private MyPublishLawyerAdapter lawyerAdapter;
	private ArrayList<MyPublishOfLawyerCollaborationEntity> list;
	private String accessKey;
	private String uid;
	public HttpDataUtils httpDataUtils;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_my_publish, null);
		ViewUtils.inject(this, view);
		initialize();
		publishViewInit();
		return view;
	}

	private void initialize() {
		httpDataUtils = new HttpDataUtils(mActivity);
		uid = PrefsUtils.getString(mActivity, PrefsUtils.UID);
		accessKey = PrefsUtils.getString(mActivity, PrefsUtils.ACCESSKEY);
		list = new ArrayList<>();
		lawyerAdapter = new MyPublishLawyerAdapter(mActivity, list);
		listview.setMode(Mode.BOTH);
		listview.setEmptyView(tv_no_data);
		listview.setAdapter(lawyerAdapter);
		listview.setOnRefreshListener(new ListViewOnRefreshListener());
		listview.getRefreshableView().setOnItemClickListener(
				new OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						startActivity(new Intent().putExtra("id",
								list.get(arg2-1).getId()).putExtra("title", "00000").putExtra("from", "footprint").setClass(getActivity(),
								LawyerCollaboratDetailsActivity.class));
					}
				});
	}

	class ListViewOnRefreshListener implements OnRefreshListener2<ListView> {

		@Override
		public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
			resetPageIndex();
			isPullRefresh = true;
			getList();

		}

		@Override
		public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
			addPageIndex();
			isPullRefresh = false;
			getList();
		}
	}

	/**
	 * 律师协作---我发布的
	 */
	private void getList() {
		RequestParams params = new RequestParams();
		params.addHeader("Authorization", accessKey);
		params.addQueryStringParameter("page", index + "");
		params.addQueryStringParameter("uid", uid);
		params.addQueryStringParameter("deleted", "0");
		httpDataUtils.sendGet(MyData.LAWERCOOP_LIST, params);
		httpDataUtils.setSucessBack(new HttpDataUtils.SucessBack() {
			@Override
			public void sucess(ResponseInfo<String> arg0) {
				listview.onRefreshComplete();
				if (isPullRefresh) {
					list.clear();
				}
				try {
					JSONObject object = new JSONObject(arg0.result);
					String code = JSONTool.getString(object, "code");
					String msg = JSONTool.getString(object, "msg");
					if (code.equals("000000")) { // 成功
						JSONObject data = JSONTool.getJsonObject(object, "data");
						String pages = JSONTool.getString(data, "pages");
						JSONArray lists = JSONTool.getJsonArray(data, "list");
						Type type = new TypeToken<ArrayList<MyPublishOfLawyerCollaborationEntity>>() {}.getType();
						list.addAll((ArrayList<MyPublishOfLawyerCollaborationEntity>) gson.fromJson(lists.toString(), type));
						lawyerAdapter.notifyDataSetChanged();
					} else {
						ToastUtils.getUtils(mActivity).show(msg);
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}

		});
		httpDataUtils.setFailBack(new FailBack() {

			@Override
			public void fail(String msg) {
				listview.onRefreshComplete();
			}
		});
	}

	private void resetPageIndex() {
		index = 1;
	}

	private void addPageIndex() {
		++index;
	}

	/** 进页面自动刷新 */
	private void publishViewInit() {
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				listview.setRefreshing(true);
			}
		}, 300);
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		mActivity = activity;
	}

//	@OnClick(R.id.tv_input)
//	public void onclick(View view) {
//		switch (view.getId()) {
////		case R.id.tv_input:
////			startActivity(new Intent().setClass(getActivity(),
////					MainActivity.class));
////			getActivity().finish();
////			break;
//
//		}
//	}

}
