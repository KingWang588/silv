package com.yhy.hzzll.my.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.Nullable;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.yhy.hzzll.R;
import com.yhy.hzzll.entity.IncomeAndExpensesEntity;
import com.yhy.hzzll.framework.MyData;
import com.yhy.hzzll.mian.fragment.BaseFragment;
import com.yhy.hzzll.my.adapter.AllIncomeAndExpensesAdapter;
import com.yhy.hzzll.utils.HttpDataUtils;
import com.yhy.hzzll.utils.HttpDataUtils.FailBack;
import com.yhy.hzzll.utils.JSONTool;
import com.yhy.hzzll.utils.LogUtils;
import com.yhy.hzzll.utils.PrefsUtils;
import com.yhy.hzzll.utils.ToastUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * 收支明细_全部
 *
 * @author zcm
 */
public class AllIncomeAndExpensesFragment extends BaseFragment {
    @ViewInject(R.id.lv)
    PullToRefreshListView listview;

    int index = 1;
    /**
     * 标记是刷新还是加载更多
     */

    private boolean isPullRefresh;
    private Activity mActivity;
    private AllIncomeAndExpensesAdapter mAdapter;
    private ArrayList<IncomeAndExpensesEntity.DataBean.ListBean> list;
    private String accessKey;
    public HttpDataUtils httpDataUtils;

    public static final String START_DATE_INTENT = "START_DATE_INTENT";
    public static final String END_DATE_INTENT = "END_DATE_INTENT";

    private String startDate;
    private String endDate;

    public static AllIncomeAndExpensesFragment newInstance(
            String startTimestamp, String endTimestamp) {
        AllIncomeAndExpensesFragment fragment = new AllIncomeAndExpensesFragment();
        Bundle args = new Bundle();
        args.putString(START_DATE_INTENT, startTimestamp);
        args.putString(END_DATE_INTENT, endTimestamp);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            startDate = getArguments().getString(START_DATE_INTENT);
            endDate = getArguments().getString(END_DATE_INTENT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_income_and_expenses,
                null);
        ViewUtils.inject(this, view);
        initialize();
        publishViewInit();
        return view;
    }

    private void initialize() {
        startDate = mActivity.getIntent().getStringExtra(START_DATE_INTENT);
        endDate = mActivity.getIntent().getStringExtra(END_DATE_INTENT);
        httpDataUtils = new HttpDataUtils(mActivity);
        accessKey = PrefsUtils.getString(mActivity, PrefsUtils.ACCESSKEY);
        list = new ArrayList<>();
        mAdapter = new AllIncomeAndExpensesAdapter(mActivity, list);
        listview.setMode(Mode.BOTH);
        listview.setAdapter(mAdapter);

        listview.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
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

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // 加载完成后停止刷新
                        listview.onRefreshComplete();
                    }
                }, 1000);
            }
        });


    }

    private void resetPageIndex() {
        index = 1;
    }

    private void addPageIndex() {
        ++index;
    }



    /**
     * 全部
     */
    private void getList() {
        RequestParams params = new RequestParams();
        params.addHeader("Authorization", PrefsUtils.getString(getActivity(), PrefsUtils.AUTHORIZATION));
//		params.addQueryStringParameter("uid", PrefsUtils.getString(getActivity(), PrefsUtils.UID));
        params.addQueryStringParameter("start_date", startDate);
        params.addQueryStringParameter("end_date", endDate);
        params.addQueryStringParameter("page", index + "");

        LogUtils.logE(startDate + "fdssssssssssssss" + endDate);
        httpDataUtils.sendGet(MyData.CASHLOG_LIST, params);
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
                    if (code.equals("0")) { // 成功

                        IncomeAndExpensesEntity entity = gson.fromJson(object.toString(), IncomeAndExpensesEntity.class);
                        list.addAll(entity.getData().getList());
                        mAdapter.notifyDataSetChanged();
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

    /**
     * 进页面自动刷新
     */
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
//		case R.id.tv_input:
//			startActivity(new Intent().setClass(getActivity(),
//					MainActivity.class));
//			getActivity().finish();
//			break;
//
//		}
//	}

}
