package com.yhy.hzzll.my.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
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
import com.yhy.hzzll.entity.SortEntity;
import com.yhy.hzzll.framework.Constans;
import com.yhy.hzzll.framework.HzApplication;
import com.yhy.hzzll.framework.MyData;
import com.yhy.hzzll.mian.activity.BaseActivity;
import com.yhy.hzzll.mian.entity.DataUserEntity;
import com.yhy.hzzll.my.adapter.IncomeAdapter;
import com.yhy.hzzll.my.adapter.MyInvitationAdapter;
import com.yhy.hzzll.my.entity.IncomeEntity;
import com.yhy.hzzll.my.entity.MyInvitetationEntity;
import com.yhy.hzzll.utils.CacheUtils;
import com.yhy.hzzll.utils.HttpDataUtils;
import com.yhy.hzzll.utils.LogUtils;
import com.yhy.hzzll.utils.PrefsUtils;
import com.yhy.hzzll.view.SortView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CheckIncomeActivity extends BaseActivity {


    @ViewInject(R.id.listview)
    PullToRefreshListView listview;

    @ViewInject(R.id.all_line)
    View all_line;
    @ViewInject(R.id.wait_for_reply_line)
    View wait_for_reply_line;

    @ViewInject(R.id.tv_type)
    TextView tv_type;

    @ViewInject(R.id.tv_time)
    TextView tv_time;

    @ViewInject(R.id.tv_friend_num)
    TextView tv_friend_num;

    @ViewInject(R.id.tv_price_num)
    TextView tv_price_num;

    @ViewInject(R.id.rl_friend)
    RelativeLayout rl_friend;

    @ViewInject(R.id.linear_income)
    LinearLayout linear_income;





    private boolean isPullRefresh;
    private ArrayList<IncomeEntity.DataBean.ListBean> mmList = new ArrayList<>();;
    private ArrayList<MyInvitetationEntity.DataBean.ListBean> mList = new ArrayList<>();;

    IncomeAdapter incomeAdapter;
    MyInvitationAdapter myInvitationAdapter;

    String type = "1";
    String incometype = "";
    String time = "0";

    String m4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_income);
        ViewUtils.inject(this);
        inteView();
    }

    private void inteView() {

        incomeAdapter = new IncomeAdapter(CheckIncomeActivity.this, mList);
        listview.setAdapter(incomeAdapter);
        listview.setMode(PullToRefreshBase.Mode.BOTH);
        // 刷新
        listview.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                resetPageIndex();
                isPullRefresh = true;
                getData();
//                getincomenum();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // 加载完成后停止刷新
                        listview.onRefreshComplete();
                    }
                }, 1000);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                addPageIndex();
                isPullRefresh = false;
                getData();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // 加载完成后停止刷新
                        listview.onRefreshComplete();
                    }
                }, 1000);
            }
        });

        getincomenum();
    }

    private void getData() {
        listview.setRefreshing();
        if (type.equals("1")){
            getIncome();
//            getincomenum();
        }else {
            getInvitation();
        }
    }

    private void getInvitation() {
        String url = MyData.GET_INVITE_USER+"?type_id="+incometype +"&time="+ time + "&page=" +index;
        myInvitationAdapter = new MyInvitationAdapter(CheckIncomeActivity.this,mmList);
        listview.setAdapter(myInvitationAdapter);

        RequestParams params = new RequestParams();
        params.addHeader("Authorization", PrefsUtils.getString(CheckIncomeActivity.this, PrefsUtils.AUTHORIZATION));

        httpDataUtils.sendGet(url, params);
        httpDataUtils.setSucessBack(new HttpDataUtils.SucessBack() {

            @Override
            public void sucess(ResponseInfo<String> arg0) {
                listview.onRefreshComplete();

                if (httpDataUtils.code(arg0.result)) {

                    if (isPullRefresh) {
                        mmList.clear();
                    }

                    IncomeEntity entity = gson.fromJson(arg0.result, IncomeEntity.class);
                    tv_friend_num.setText(entity.getData().getSum()+"人");
                    mmList.addAll(entity.getData().getList());

                    myInvitationAdapter.notifyDataSetChanged();
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

    //获取收益总额
    private void getincomenum() {

        RequestParams params = new RequestParams();
        params.addHeader("Authorization", PrefsUtils.getString(CheckIncomeActivity.this, PrefsUtils.AUTHORIZATION));
        httpDataUtils.sendGet(MyData.CHECK_OUT_USER_INFO, params);
        httpDataUtils.setSucessBack(new HttpDataUtils.SucessBack() {
            @Override
            public void sucess(ResponseInfo<String> arg0) {

                try {
                    JSONObject object = new JSONObject(arg0.result);
                    String code = object.optString("code");
                    String msg = object.optString("msg");
                    if (code.equals("0")) {
                        Gson gson = new Gson();
                        try {
                            CacheUtils.dataUserEntity = gson.fromJson(arg0.result, DataUserEntity.class);
                            HzApplication.getInstance().getUserEntityCache().put(Constans.USER_INFO, CacheUtils.dataUserEntity);
                            DataUserEntity userEntity = gson.fromJson(arg0.result, DataUserEntity.class);

                            String m3 = userEntity.getData().getFinance().getInvite_income();
                            m3 = m3.substring(0, m3.length() - 3);

                            tv_price_num.setText(m3 + "元");

                            m4 =  userEntity.getData().getFinance().getAmt_balance();
                            m4 = m4.substring(0, m4.length() - 3);

                            getData();

                        } catch (Exception e) {
                            com.lidroid.xutils.util.LogUtils.e("报错了");
                        }
                    } else {

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







    private void getIncome() {

        listview.setAdapter(incomeAdapter);

        String url = MyData.GET_INVITE_INCOME+"?type_id="+incometype +"&time="+ time + "&page=" +index;
        RequestParams params = new RequestParams();
        params.addHeader("Authorization", PrefsUtils.getString(CheckIncomeActivity.this, PrefsUtils.AUTHORIZATION));
//        params.addBodyParameter("type_id",incometype);
//        params.addBodyParameter("time","week");
//        params.addBodyParameter("page","3");
        LogUtils.logE("++++++++++++++++++"+index);
//        params.addBodyParameter("","");
        httpDataUtils.sendGet(url, params);
        httpDataUtils.setSucessBack(new HttpDataUtils.SucessBack() {

            @Override
            public void sucess(ResponseInfo<String> arg0) {
                listview.onRefreshComplete();

                if (httpDataUtils.code(arg0.result)) {

                    if (isPullRefresh) {
                        mList.clear();
                    }

                    MyInvitetationEntity entity = gson.fromJson(arg0.result, MyInvitetationEntity.class);
                    mList.addAll(entity.getData().getList());

                    incomeAdapter.notifyDataSetChanged();
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


    private int index = 1;

    private void resetPageIndex() {
        index = 1;
    }

    private void addPageIndex() {
        ++index;
    }


    public void showLine(int a, int b) {
        all_line.setVisibility(a);
        wait_for_reply_line.setVisibility(b);
    }


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            isPullRefresh = true;
            resetPageIndex();
            getData();
            super.handleMessage(msg);
        }
    };


    @OnClick({R.id.tv_all, R.id.tv_wait_for_reply, R.id.tv_type, R.id.tv_time,R.id.ic_back,R.id.tv_withdraw})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_withdraw:
                startActivity(new Intent().setClass(context, TreasureWithdrawActivity.class).putExtra("money",m4));
                break;
            case R.id.ic_back:
                finish();
                break;
            case R.id.tv_all:
                tv_type.setText("收益类型");
                incometype= "";
                showLine(View.VISIBLE, View.GONE);
                type = "1";
                handler.sendEmptyMessage(1);

                rl_friend.setVisibility(View.GONE);
                linear_income.setVisibility(View.VISIBLE);

                break;

            case R.id.tv_wait_for_reply:
                tv_type.setText("会员类型");
                incometype= "";
                showLine(View.GONE, View.VISIBLE);
                type = "2";
                handler.sendEmptyMessage(1);
                rl_friend.setVisibility(View.VISIBLE);
                linear_income.setVisibility(View.GONE);

                break;

            case R.id.tv_type:
                if (type.equals("1")){
                    sortViewInittype(tv_type);
                }else {
                    sortViewInitMenbertype(tv_type);
                }


                break;

            case R.id.tv_time:
                sortViewInit(tv_time);
                break;
        }
    }


    //会员类型
    private void sortViewInitMenbertype(TextView view) {

        List<SortEntity> sortList = new ArrayList<SortEntity>();
        sortList.clear();
        sortList.add(new SortEntity("会员类型", "0", "a", false));
        sortList.add(new SortEntity("公众用户", "0", "b", false));
        sortList.add(new SortEntity("律师用户", "day", "c", false));
//        sortList.add(new SortEntity("专属咨询", "week", "e", false));

        SortView sortView = new SortView();
        sortView.setSortViews(CheckIncomeActivity.this, view, sortList);
        sortView.setSelects(new SortView.Select() {
            @Override
            public void index(int index) {
                if (index == 0) {
                    incometype = "";
                } else if (index == 1) {
                    incometype = "general";
                } else if (index == 2) {
                    incometype = "lawyer";
                }
                handler.sendEmptyMessage(1);
            }
        });

    }


    // 收益类型(可选值：""-收益类型，14-注册15-快速咨询，16-专属咨询)

    private void sortViewInittype(TextView view) {

        List<SortEntity> sortList = new ArrayList<SortEntity>();
        sortList.clear();
        sortList.add(new SortEntity("收益类型", "0", "a", false));
        sortList.add(new SortEntity("注册", "0", "b", false));
        sortList.add(new SortEntity("快速咨询", "day", "c", false));
        sortList.add(new SortEntity("专属咨询", "week", "e", false));

        SortView sortView = new SortView();
        sortView.setSortViews(CheckIncomeActivity.this, view, sortList);
        sortView.setSelects(new SortView.Select() {
            @Override
            public void index(int index) {
                if (index == 0) {
                    incometype = "";
                } else if (index == 1) {
                    incometype = "14";
                } else if (index == 2) {
                    incometype = "15";
                }else if (index == 3) {
                    incometype = "16";
                }
                handler.sendEmptyMessage(1);
            }
        });
    }



    // 时间范围(可选值：0-全部，day-天，week-周，month-月，year-年)

    private void sortViewInit(TextView view) {

        List<SortEntity> sortList = new ArrayList<SortEntity>();
        sortList.clear();
        sortList.add(new SortEntity("全部", "0", "a", false));
        sortList.add(new SortEntity("一天", "day", "c", false));
        sortList.add(new SortEntity("一周内", "week", "e", false));
        sortList.add(new SortEntity("一月内", "month", "e", false));
        sortList.add(new SortEntity("半年内", "month", "e", false));
        sortList.add(new SortEntity("一年内", "year", "e", false));

        SortView sortView = new SortView();
        sortView.setSortViews(CheckIncomeActivity.this, view, sortList);
        sortView.setSelects(new SortView.Select() {
            @Override
            public void index(int index) {
                if (index == 0) {
                    time = "";
                } else if (index == 1) {
                    time = "day";
                } else if (index == 2) {
                    time = "week";
                } else if (index == 3) {
                    time = "month";
                } else if (index == 4) {
                    time = "half_year";
                }else if (index == 5) {
                    time = "year";
                }
                handler.sendEmptyMessage(1);
            }
        });
    }



}
