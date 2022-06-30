package com.yhy.hzzll.my.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.yhy.hzzll.R;
import com.yhy.hzzll.adapter.FocusInfoAdapter;
import com.yhy.hzzll.entity.FocusInfoEntity;
import com.yhy.hzzll.framework.MyData;
import com.yhy.hzzll.home.activity.consult.ConsultDetailsActivity;
import com.yhy.hzzll.mian.fragment.BaseFragment;
import com.yhy.hzzll.utils.HttpDataUtils;
import com.yhy.hzzll.utils.PrefsUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 我的关注 -- 信息
 * 
 * @author Yang
 * 
 */
public class FocusInfoFragment extends BaseFragment implements
		OnRefreshListener2<ListView> {

	private View view;
	@ViewInject(R.id.lv_focus)
	private ListView lv_focus;
	@ViewInject(R.id.tv_no_data)
	TextView tv_no_data;

	/** 我的关注--信息 适配器 */
	private FocusInfoAdapter infoAdapter;
	/** 关注的信息 */
	private List<FocusInfoEntity.DataBean.ListBean> focusInfoList = new ArrayList<>();

	private int index = 1;

	public HttpDataUtils httpDataUtils;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		httpDataUtils = new HttpDataUtils(getActivity());

	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragment_focus_list, null);
		ViewUtils.inject(this, view);
		viewInit();
		return view;
	}

	@Override
	public void viewInit() {

        infoAdapter = new FocusInfoAdapter(getActivity(), focusInfoList);
        lv_focus.setAdapter(infoAdapter);

		lv_focus.setEmptyView(tv_no_data);

		lv_focus.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

					startActivity(new Intent().putExtra("id", focusInfoList.get(position).getId()+"")
							.putExtra("title", focusInfoList.get(position).getContent()).putExtra("insert",true)
							.setClass(getActivity(), ConsultDetailsActivity.class));

			}
		});

		getInfo();
		super.viewInit();
	}

	/**
	 * 获取信息数据
	 * 
	 * */
	private void getInfo() {

		RequestParams params = new RequestParams();
		params.addHeader("Authorization", PrefsUtils.getString(getActivity(), PrefsUtils.AUTHORIZATION));
		params.addQueryStringParameter("type", "0");
		params.addQueryStringParameter("page", String.valueOf(index));
		httpDataUtils.sendProgressGet(MyData.MY_FOLLOW, params);
		httpDataUtils.setSucessBack(new HttpDataUtils.SucessBack() {
			@Override
			public void sucess(ResponseInfo<String> arg0) {
				if (httpDataUtils.code(arg0.result)) {
					try {
						JSONObject object = new JSONObject(arg0.result);

						focusInfoList.clear();

                        FocusInfoEntity entity = gson.fromJson(object.toString(), FocusInfoEntity.class);
                        focusInfoList.addAll(entity.getData().getList());
                        infoAdapter.notifyDataSetChanged();

					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}


			}
		});

		httpDataUtils.setFailBack(new HttpDataUtils.FailBack() {

			@Override
			public void fail(String msg) {
//				ToastUtils.getUtils(getActivity()).show(msg);
			}
		});
	}



	@Override
	public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
		getInfo();
	}

	@Override
	public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {

	}
}
