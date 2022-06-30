package com.yhy.hzzll.my.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.yhy.hzzll.R;
import com.yhy.hzzll.framework.MyData;
import com.yhy.hzzll.home.activity.newcollaborate.PickUpCaseActivity;
import com.yhy.hzzll.home.adapter.OrderListAdapter;
import com.yhy.hzzll.home.entity.OrderListEntity;
import com.yhy.hzzll.mian.activity.BaseActivity;
import com.yhy.hzzll.utils.HttpDataUtils;
import com.yhy.hzzll.utils.JSONTool;
import com.yhy.hzzll.utils.PrefsUtils;
import com.yhy.hzzll.utils.ToastUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 办公室-我的发布--律师协作
 *
 * @author wangyang
 */
public class MyPLawyerActivity extends BaseActivity  implements PullToRefreshBase.OnRefreshListener2<ListView> {

    @ViewInject(R.id.tv_my_publish)
    TextView tv_my_publish;
    @ViewInject(R.id.tv_my_participate)
    TextView tv_my_participate;
    @ViewInject(R.id.tv_specify_hire)
    TextView tv_specify_hire;

    OrderListAdapter orderListAdapter;

    List<OrderListEntity.DataBean.ListBean> list = new ArrayList<>();
    String type = "0";
    int index  = 1;

    @ViewInject(R.id.lv_case)
     PullToRefreshListView lv_case;

    boolean isPullRefresh;

    @Override
    protected void onCreate(Bundle arg0) {
        setContentView(R.layout.activity_my_plawyer);
        super.onCreate(arg0);
        ViewUtils.inject(this);
        viewInit();

    }

    private void viewInit() {
        tv_my_participate.setSelected(true);

        lv_case.getRefreshableView().setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

//                if (type.equals("2")){
                    startActivity(new Intent(MyPLawyerActivity.this, PickUpCaseActivity.class)
                            .putExtra("order_no", list.get(arg2-1).getOrder_no()));
//                }else {
//                    startActivity(new Intent(MyPLawyerActivity.this,CollaborativeOrderDetailsActivity.class)
//                            .putExtra("private_order_no",list.get(arg2-1).getOrder_no()));
//                }
            }
        });

        orderListAdapter = new OrderListAdapter(context, list);
        lv_case.setAdapter(orderListAdapter);
        lv_case.setMode(PullToRefreshBase.Mode.BOTH);
        lv_case.setOnRefreshListener(this);

        getCaseList();
    }


    private void getCaseList() {

        RequestParams params = new RequestParams();
        params.addHeader("Authorization", PrefsUtils.getString(MyPLawyerActivity.this, PrefsUtils.AUTHORIZATION));

        params.addQueryStringParameter("my_operation",type);
        params.addQueryStringParameter("page", String.valueOf(index));
        params.addBodyParameter("limit", "10");

        httpDataUtils.sendGet(MyData.ORDER_LAWYER_CASE_LIST, params);
        httpDataUtils.setSucessBack(new HttpDataUtils.SucessBack() {
            @Override
            public void sucess(ResponseInfo<String> arg0) {

                Log.e("sendGet", "sucess: " + arg0.result );

                lv_case.onRefreshComplete();

                try {
                    JSONObject object = new JSONObject(arg0.result);
                    String code = JSONTool.getString(object, "code");
                    String msg = JSONTool.getString(object, "message");
                    if (code.equals("0")) { // 成功
                        Gson gson =new Gson();
                        OrderListEntity entity = gson.fromJson(object.toString(),OrderListEntity.class);

                        if (isPullRefresh){
                            list.clear();
                        }

                        list.addAll(entity.getData().getList());
                        orderListAdapter.notifyDataSetChanged();

                    } else {
                        ToastUtils.getUtils(getApplicationContext()).show(msg);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        });
        httpDataUtils.setFailBack(new HttpDataUtils.FailBack() {
            @Override
            public void fail(String msg) {

            }
        });
    }




    @OnClick({R.id.tv_my_publish, R.id.tv_my_participate, R.id.tv_specify_hire})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_my_publish:
                tv_my_publish.setSelected(true);
                tv_my_participate.setSelected(false);
                tv_specify_hire.setSelected(false);

                resetPageIndex();
                type = "1";
                isPullRefresh = true;
                lv_case.setRefreshing();
                getCaseList();


                break;
            case R.id.tv_my_participate:

                tv_my_publish.setSelected(false);
                tv_my_participate.setSelected(true);
                tv_specify_hire.setSelected(false);

                resetPageIndex();
                type = "0";
                isPullRefresh = true;
                lv_case.setRefreshing();
                getCaseList();

                break;

            case R.id.tv_specify_hire:
                tv_my_publish.setSelected(false);
                tv_my_participate.setSelected(false);
                tv_specify_hire.setSelected(true);

                resetPageIndex();
                type = "2";
                isPullRefresh = true;
                lv_case.setRefreshing();
                getCaseList();

                break;
        }
    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
        isPullRefresh = true;
        resetPageIndex();
        getCaseList();
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
        isPullRefresh = false;
        addPageIndex();
        getCaseList();
    }

    private void resetPageIndex() {
        index = 1;
    }

    private void addPageIndex() {
        ++index;
    }

}
