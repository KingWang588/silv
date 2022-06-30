package com.yhy.hzzll.my.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.yhy.hzzll.R;
import com.yhy.hzzll.entity.OrderListEntity;
import com.yhy.hzzll.entity.SortEntity;
import com.yhy.hzzll.framework.MyData;
import com.yhy.hzzll.home.activity.consult.PursueAskActivity;
import com.yhy.hzzll.mian.activity.BaseActivity;
import com.yhy.hzzll.my.adapter.OrderListsAdapter;
import com.yhy.hzzll.utils.HttpDataUtils;
import com.yhy.hzzll.utils.JSONTool;
import com.yhy.hzzll.utils.PrefsUtils;
import com.yhy.hzzll.view.SortView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class OrderManagementActivity extends BaseActivity {

    @ViewInject(R.id.listview)
    private PullToRefreshListView listview;
    @ViewInject(R.id.tv_no_data)
    TextView tv_no_data;


    @ViewInject(R.id.all_line)
    View all_line;
    @ViewInject(R.id.wait_for_reply_line)
    View wait_for_reply_line;
    @ViewInject(R.id.underway_line)
    View underway_line;
    @ViewInject(R.id.finish_line)
    View finish_line;

    @ViewInject(R.id.tv_time)
    TextView tv_time;
    @ViewInject(R.id.tv_type)
    TextView tv_type;
    @ViewInject(R.id.tv_data_statistics)
    TextView tv_data_statistics;
    @ViewInject(R.id.linear_data_statistics)
    LinearLayout linear_data_statistics;


    boolean isShowDataStatistics = false;

    private OrderListsAdapter mAdapter;


    @ViewInject(R.id.tv_num_quick)
    TextView tv_num_quick;
    @ViewInject(R.id.tv_money_quick)
    TextView tv_money_quick;
    @ViewInject(R.id.tv_num)
    TextView tv_num;
    @ViewInject(R.id.tv_money)
    TextView tv_money;


    /**
     * 标记是刷新还是加载更多
     */
    private boolean isPullRefresh;
    private ArrayList<OrderListEntity.DataBean.ListBean> mList;

    String status = "0";
    String time = "0";
    String type = "0";
    private int index = 1;
    String limit = "10";

    @Override
    protected void onCreate(Bundle arg0) {
        setContentView(R.layout.activity_order_management);
        super.onCreate(arg0);
        ViewUtils.inject(this);
        viewInit();
    }


    public void showLine(int a, int b, int c, int d) {
        all_line.setVisibility(a);
        wait_for_reply_line.setVisibility(b);
        underway_line.setVisibility(c);
        finish_line.setVisibility(d);
    }


    // 时间范围(可选值：0-全部，day-天，week-周，month-月，year-年)

    private void sortViewInit(TextView view) {

        List<SortEntity> sortList = new ArrayList<SortEntity>();
        sortList.clear();
        sortList.add(new SortEntity("全部", "0", "a", false));
        sortList.add(new SortEntity("一天", "day", "c", false));
        sortList.add(new SortEntity("一周", "week", "e", false));
        sortList.add(new SortEntity("一个月", "month", "e", false));
        sortList.add(new SortEntity("一年", "year", "e", false));

        SortView sortView = new SortView();
        sortView.setSortViews(OrderManagementActivity.this, view, sortList);
        sortView.setSelects(new SortView.Select() {
            @Override
            public void index(int index) {
                if (index == 0) {
                    time = "0";
                } else if (index == 1) {
                    time = "day";
                } else if (index == 2) {
                    time = "week";
                } else if (index == 3) {
                    time = "month";
                } else if (index == 4) {
                    time = "year";
                }
                handler.sendEmptyMessage(1);
            }
        });
    }


    // 咨询类型(可选值：0-全部，1-快速咨询，2-专属咨询)

    private void sortViewInittype(TextView view) {

        List<SortEntity> sortList = new ArrayList<SortEntity>();
        sortList.clear();
        sortList.add(new SortEntity("全部", "0", "a", false));
        sortList.add(new SortEntity("快速咨询", "day", "c", false));
        sortList.add(new SortEntity("专属咨询", "week", "e", false));
//        sortList.add(new SortEntity("见面咨询", "month", "e", false));
//        sortList.add(new SortEntity("一年", "year", "e", false));

        SortView sortView = new SortView();
        sortView.setSortViews(OrderManagementActivity.this, view, sortList);
        sortView.setSelects(new SortView.Select() {
            @Override
            public void index(int index) {
                if (index == 0) {
                    type = "0";
                } else if (index == 1) {
                    type = "1";
                } else if (index == 2) {
                    type = "2";
                }
                handler.sendEmptyMessage(1);
            }
        });
    }


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            isPullRefresh = true;
            resetPageIndex();
            getOrderList();
            super.handleMessage(msg);
        }
    };

    //订单状态(可选值：0-全部，3-待回复，4-进行中，5-待评价，6-已完成

    @OnClick({R.id.tv_all, R.id.tv_wait_for_reply, R.id.tv_underway, R.id.tv_finish, R.id.tv_type, R.id.tv_time, R.id.tv_data_statistics})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_all:
                showLine(View.VISIBLE, View.GONE, View.GONE, View.GONE);
                status = "0";
                handler.sendEmptyMessage(1);
                break;
            case R.id.tv_wait_for_reply:
                showLine(View.GONE, View.VISIBLE, View.GONE, View.GONE);
                status = "3";
                handler.sendEmptyMessage(1);
                break;
            case R.id.tv_underway:
                showLine(View.GONE, View.GONE, View.VISIBLE, View.GONE);
                status = "4";
                handler.sendEmptyMessage(1);
                break;
            case R.id.tv_finish:
                showLine(View.GONE, View.GONE, View.GONE, View.VISIBLE);
                status = "6";
                handler.sendEmptyMessage(1);
                break;
            case R.id.tv_type:
                sortViewInittype(tv_type);
                break;
            case R.id.tv_time:
                sortViewInit(tv_time);
                break;
            case R.id.tv_data_statistics:

                if (isShowDataStatistics) {
                    linear_data_statistics.setVisibility(View.GONE);
                    isShowDataStatistics = false;

                    Drawable rightDrawable = context.getResources().getDrawable(R.drawable.sort_down);
                    rightDrawable.setBounds(0, 0, rightDrawable.getMinimumWidth(), rightDrawable.getMinimumHeight());
                    tv_data_statistics.setCompoundDrawables(null, null, rightDrawable, null);

                } else {

                    Drawable rightDrawable = context.getResources().getDrawable(R.drawable.icon_arrow_up);
                    rightDrawable.setBounds(0, 0, rightDrawable.getMinimumWidth(), rightDrawable.getMinimumHeight());
                    tv_data_statistics.setCompoundDrawables(null, null, rightDrawable, null);
                    linear_data_statistics.setVisibility(View.VISIBLE);
                    isShowDataStatistics = true;

                    getDataStatistics();

                }


                break;

        }
    }

    View.OnClickListener pursue = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int position = (int) v.getTag();
            startActivity(new Intent(OrderManagementActivity.this, PursueAskActivity.class)
                    .putExtra("reply_id", mList.get(position).getReply_id() + "").putExtra("id", mList.get(position).getConsult_id() + "" + "")
                    .putExtra("message_id", mList.get(position).getMessage_id() + ""));
        }
    };


    private void viewInit() {
        mList = new ArrayList<>();
        mAdapter = new OrderListsAdapter(context, mList, pursue);

//        View headView = LayoutInflater.from(OrderManagementActivity.this).inflate(R.layout.linear_statistics, null, false);
//        ViewUtils.inject(this, headView);
//        listview.getRefreshableView().addHeaderView(headView);

        listview.setEmptyView(tv_no_data);
        listview.setOnItemClickListener(new MyOnItemClickListener());
        listview.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
        listview.setMode(PullToRefreshBase.Mode.BOTH);
        // 刷新
        listview.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                resetPageIndex();
                isPullRefresh = true;
                getOrderList();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                addPageIndex();
                isPullRefresh = false;
                getOrderList();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // 加载完成后停止刷新
                        listview.onRefreshComplete();
                    }
                }, 1000);
            }
        });

        getOrderList();

    }


    private void getDataStatistics() {

        RequestParams params = new RequestParams();
        params.addHeader("Authorization", PrefsUtils.getString(OrderManagementActivity.this, PrefsUtils.AUTHORIZATION));
        httpDataUtils.sendProgressGet(MyData.ORDER_COUNT, params);
        httpDataUtils.setSucessBack(new HttpDataUtils.SucessBack() {

            @Override
            public void sucess(ResponseInfo<String> arg0) {
                if (httpDataUtils.code(arg0.result)) {
                    try {
                        JSONObject object = new JSONObject(arg0.result);
                        JSONObject data = JSONTool.getJsonObject(object, "data");

                        JSONObject quick = JSONTool.getJsonObject(data, "quick");
                        String quick_count = JSONTool.getString(quick, "count");
                        String quick_price = JSONTool.getString(quick, "total");

                        JSONObject exclusive = JSONTool.getJsonObject(data, "exclusive");
                        String exclusive_count = JSONTool.getString(exclusive, "count");
                        String exclusive_price = JSONTool.getString(exclusive, "total");

                        tv_num_quick.setText(quick_count);
                        tv_num.setText(exclusive_count);

                        tv_money_quick.setText(quick_price);
                        tv_money.setText(exclusive_price);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }

            }

        });
        httpDataUtils.setFailBack(new HttpDataUtils.FailBack() {

            @Override
            public void fail(String msg) {
                listview.onRefreshComplete();
            }
        });


    }

    /**
     * 进页面自动刷新
     */
//	private void publishViewInit() {
//		new Handler().postDelayed(new Runnable() {
//			@Override
//			public void run() {
//				listview.setRefreshing(true);
//			}
//		}, 500);
//	}

    class MyOnItemClickListener implements OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            if (position < 1) {

            } else {
                startActivity(new Intent(OrderManagementActivity.this, QuickConsultingOrderDetailsActivity.class).putExtra("order_no", mList.get(position - 1).getOrder_no()));
            }
        }
    }


    private void resetPageIndex() {
        index = 1;
    }

    private void addPageIndex() {
        ++index;
    }

    /**
     * 我的订单列表   order/order-list-0(状态)-day(时间范围)-0(咨询类型)-3(第几页)-5(每页显示的条数)
     * 订单状态(可选值：0-全部，3-待回复，4-进行中，5-待评价，6-已完成
     * 时间范围(可选值：0-全部，day-天，week-周，month-月，year-年)
     * 咨询类型(可选值：0-全部，1-快速咨询，2-专属咨询)
     */
    private void getOrderList() {
        String url = MyData.ORDER_LIST + "-" + status + "-" + time + "-" + type + "-" + index + "-" + limit;
        RequestParams params = new RequestParams();
        params.addHeader("Authorization", PrefsUtils.getString(OrderManagementActivity.this, PrefsUtils.AUTHORIZATION));
        httpDataUtils.sendGet(url, params);
        httpDataUtils.setSucessBack(new HttpDataUtils.SucessBack() {

            @Override
            public void sucess(ResponseInfo<String> arg0) {
                listview.onRefreshComplete();

                if (httpDataUtils.code(arg0.result)) {

                    if (isPullRefresh) {
                        mList.clear();
                    }

                    OrderListEntity entity = gson.fromJson(arg0.result, OrderListEntity.class);
                    mList.addAll(entity.getData().getList());

                    mAdapter.notifyDataSetChanged();

                }

            }

        });
        httpDataUtils.setFailBack(new HttpDataUtils.FailBack() {

            @Override
            public void fail(String msg) {
                listview.onRefreshComplete();
            }
        });
    }

}
