package com.yhy.hzzll.my.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.yhy.hzzll.R;
import com.yhy.hzzll.adapter.PublishedArtclesAdapter;
import com.yhy.hzzll.adapter.PublishedArtclesAdapter.OnActionListenter;
import com.yhy.hzzll.entity.SuccessfulCaseEntity;
import com.yhy.hzzll.framework.Constans;
import com.yhy.hzzll.framework.MyData;
import com.yhy.hzzll.mian.fragment.BaseFragment;
import com.yhy.hzzll.my.activity.ArticleDetailsActivity;
import com.yhy.hzzll.my.activity.PublishNewsActivity;
import com.yhy.hzzll.utils.HttpDataUtils;
import com.yhy.hzzll.utils.HttpDataUtils.FailBack;
import com.yhy.hzzll.utils.JSONTool;
import com.yhy.hzzll.utils.PrefsUtils;
import com.yhy.hzzll.utils.ToastUtils;
import com.yhy.hzzll.widget.CustomListview;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * 
 * @author 发表文章
 * 
 */
public class PublishedArticlesFragment extends BaseFragment implements
		OnActionListenter {

	@ViewInject(R.id.lv_office)
	CustomListview listview;
	@ViewInject(R.id.ll_publish)
	LinearLayout ll_publish;
	@ViewInject(R.id.tv_no_data)
	TextView tv_no_data;
	private Activity mActivity;
	private PublishedArtclesAdapter mAdapter;
	private ArrayList<SuccessfulCaseEntity.DataBean.ListBean> mList;
	private HttpDataUtils httpDataUtils;

	public static final String IS_LOOK_OTHER_OFFICE_INTENT = "IS_LOOK_OTHER_OFFICE_INTENT";
	private boolean isLookOtherOffice;
	public static final String USER_ID_INTENT = "USER_ID_INTENT";
	private String uid;

	public static PublishedArticlesFragment newInstance(String uid,
			boolean isLookOtherOffice) {
		PublishedArticlesFragment fragment = new PublishedArticlesFragment();
		Bundle args = new Bundle();
		args.putBoolean(IS_LOOK_OTHER_OFFICE_INTENT, isLookOtherOffice);
		args.putString(USER_ID_INTENT, uid);
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (getArguments() != null) {
			isLookOtherOffice = getArguments().getBoolean(
					IS_LOOK_OTHER_OFFICE_INTENT);
			uid = getArguments().getString(USER_ID_INTENT);
		}
	}
	/**
	 * 标记是刷新还是加载更多
	 */
	private boolean isPullRefresh = true;

	//数据分页
	private int index = 1;

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater
				.inflate(R.layout.fragment_published_articles, null);
		ViewUtils.inject(this, view);
		inits();
		return view;
	}

	private void inits() {
		mList = new ArrayList<>();
		mAdapter = new PublishedArtclesAdapter(mActivity, mList, isLookOtherOffice);
		mAdapter.setOnActionListenter(this);
		listview.setAdapter(mAdapter);
		listview.setEmptyView(tv_no_data);
		listview.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				startActivity(new Intent().putExtra("id",
						mList.get(arg2).getId()+"").putExtra("type", "article").setClass(getActivity(),
						ArticleDetailsActivity.class));
			}
		});


//		listview.setMode(PullToRefreshBase.Mode.BOTH);
//		listview.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
//			@Override
//			public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
//				resetPageIndex();
//				isPullRefresh = true;
//				getList();
//			}
//
//			@Override
//			public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
//				addPageIndex();
//				isPullRefresh = false;
//				getList();
//
////				new Handler().postDelayed(new Runnable() {
////					@Override
////					public void run() {
////						// 加载完成后停止刷新
////						listview.onRefreshComplete();
////					}
////				}, 1000);
//			}
//		});



		httpDataUtils = new HttpDataUtils(mActivity);
		if (isLookOtherOffice) {
			ll_publish.setVisibility(View.GONE);
		} else {
			ll_publish.setVisibility(View.VISIBLE);
		}
		getList();
	}

	private void resetPageIndex() {
		index = 1;
	}

	private void addPageIndex() {
		++index;
	}


	@OnClick({ R.id.ll_publish })
	public void onclick(View view) {
		switch (view.getId()) {
//		case R.id.tv_input:
//			startActivity(new Intent().setClass(getActivity(),
//					MainActivity.class));
//			getActivity().finish();
//			break;
		case R.id.ll_publish:
			Intent intent = new Intent(mActivity, PublishNewsActivity.class);
			startActivityForResult(intent, 0x5002);
			break;
		}
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		mActivity = activity;
	}

	/**
	 * 文章列表
	 */
	private void getList() {
		RequestParams params = new RequestParams();
		params.addHeader("Authorization", PrefsUtils.getString(mActivity, PrefsUtils.ACCESSKEY));
		params.addQueryStringParameter("cur_page",index+"");
		params.addQueryStringParameter("author_id", uid);
		httpDataUtils.sendGet(MyData.OFFICEHOMEARTICLE, params);
		httpDataUtils.setSucessBack(new HttpDataUtils.SucessBack() {

			@Override
			public void sucess(ResponseInfo<String> arg0) {

				try {
//					listview.onRefreshComplete();
//					if (isPullRefresh){
//						mList.clear();
//						isPullRefresh = false;
//					}
					mList.clear();
					JSONObject object = new JSONObject(arg0.result);
					String code = JSONTool.getString(object, "code");
					String msg = JSONTool.getString(object, "msg");
					if (code.equals(Constans.SUCCESS_CODE)) { // 成功
						SuccessfulCaseEntity successfulCaseEntity= gson.fromJson(object.toString(),SuccessfulCaseEntity.class);

						mList.addAll(successfulCaseEntity.getData().getList());
						mAdapter.notifyDataSetChanged();
					} else {
						// ToastUtils.getUtils(mActivity).show(msg);
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}

		});
		httpDataUtils.setFailBack(new FailBack() {

			@Override
			public void fail(String msg) {
			}
		});
	}


	private void showDiolog(final int position) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//		View view2 = getLayoutInflater().inflate(R.layout.view_dialog_delece_consult_reply, null);
		View view2 = LayoutInflater.from(getActivity()).inflate(R.layout.view_dialog_delece_consult_reply, null);
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
				deleteData(position);
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
	private void deleteData(final int position) {
		RequestParams params = new RequestParams();
		params.addHeader("Authorization", PrefsUtils.getString(mActivity, PrefsUtils.AUTHORIZATION));
		params.addBodyParameter("id", mList.get(position).getId()+"");
		httpDataUtils.sendProgressPost(MyData.DELETE_ARTICLE, params);
		httpDataUtils.setSucessBack(new HttpDataUtils.SucessBack() {

			@Override
			public void sucess(ResponseInfo<String> arg0) {
				try {
					JSONObject object = new JSONObject(arg0.result);
					String code = object.optString("code");
					String msg = object.optString("message");
					if (code.equals("0")) {
						getList();
					}
					ToastUtils.getUtils(mActivity).show(msg);
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		});
	}

	@Override
	public void onDeleteListener(View v, int position) {
		if (!isLookOtherOffice) {
			showDiolog(position);
		}
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == 0x6002) {
			getList();
		}
	}
}
