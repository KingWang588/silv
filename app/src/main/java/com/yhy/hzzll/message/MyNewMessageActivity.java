package com.yhy.hzzll.message;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.netease.nim.uikit.business.session.constant.Extras;
import com.netease.nim.uikit.business.session.fragment.MessageFragment;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.NimIntent;
import com.netease.nimlib.sdk.RequestCallback;
import com.netease.nimlib.sdk.msg.constant.SessionTypeEnum;
import com.netease.nimlib.sdk.msg.model.IMMessage;
import com.netease.nimlib.sdk.uinfo.UserService;
import com.netease.nimlib.sdk.uinfo.model.NimUserInfo;
import com.yhy.hzzll.R;
import com.yhy.hzzll.framework.MyData;
import com.yhy.hzzll.home.activity.lawyeroffice.LawyerNationalActivity;
import com.yhy.hzzll.home.activity.newcollaborate.PickUpCaseActivity;
import com.yhy.hzzll.mian.activity.BaseActivity;
import com.yhy.hzzll.my.activity.NewInviteActivity;
import com.yhy.hzzll.my.activity.PersonDataLawyerActivity;
import com.yhy.hzzll.my.activity.QuickConsultingOrderDetailsActivity;
import com.yhy.hzzll.session.SessionHelper;
import com.yhy.hzzll.utils.JSONTool;
import com.yhy.hzzll.utils.LogUtils;
import com.yhy.hzzll.utils.PrefsUtils;
import com.yhy.hzzll.utils.ToastUtils;
import com.yhy.hzzll.view.DialogLoading;
import com.yhy.hzzll.view.LawyerJobPopupWindow;
import com.yhy.hzzll.view.ReportPopupWindow;
import com.yhy.hzzll.widget.TimeTextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class MyNewMessageActivity extends BaseActivity {

    MessageFragment messageFragment;
    FrameLayout fl_message;

    ImageView ic_back;
    TextView tv_name;
    TextView tv_file;
    TextView tv_text_close;
    TextView tv_time;
    TextView tv_report;
    TimeTextView tv_date;
    String account;

    String order_no;

    RelativeLayout rl_order_detial;
    boolean canInput = true;

    String type_id = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_new_message);

        Intent intent = getIntent();
        if (intent != null) {
            if (intent.hasExtra(NimIntent.EXTRA_NOTIFY_CONTENT)) {

                ArrayList<IMMessage> messages = (ArrayList<IMMessage>) intent.getSerializableExtra(NimIntent.EXTRA_NOTIFY_CONTENT);
                if (messages == null || messages.size() > 1) {

                } else {
                    account = messages.get(0).getFromAccount();
                    Log.e("8888866666",account);
                }
            }

            if(intent.getStringExtra(Extras.EXTRA_ACCOUNT)!=null){
                account = getIntent().getStringExtra(Extras.EXTRA_ACCOUNT);
                Log.e("999999999999988888",account);
            }
        }

        initView();
    }

    private void initView() {
        tv_name = (TextView) findViewById(R.id.tv_name);
        ic_back = (ImageView) findViewById(R.id.ic_back);
        tv_file = (TextView) findViewById(R.id.tv_file);
        tv_report = (TextView) findViewById(R.id.tv_report);
        tv_text_close = (TextView) findViewById(R.id.tv_text_close);
        tv_date = (TimeTextView) findViewById(R.id.tv_date);
        tv_time = findViewById(R.id.tv_time);
        rl_order_detial = (RelativeLayout) findViewById(R.id.rl_order_detial);

        List<String> list = new ArrayList<>();
        list.add(account);
        NIMClient.getService(UserService.class).fetchUserInfo(list)
                .setCallback(new RequestCallback<List<NimUserInfo>>() {
                    @Override
                    public void onSuccess(List<NimUserInfo> nimUserInfos) {
                        if (nimUserInfos.size() != 0) {
                            tv_name.setText(nimUserInfos.get(0).getName());

                            String extension = nimUserInfos.get(0).getExtension();

                            LogUtils.logE(extension);

                            JSONObject object = null;
                            try {
                                object = new JSONObject(extension);

                                type_id = JSONTool.getString(object, "type_id");

                                getDateTime();

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                        } else {
                            tv_name.setText("私律用户");
                        }
                    }

                    @Override
                    public void onFailed(int i) {

                    }

                    @Override
                    public void onException(Throwable throwable) {

                    }
                });

//        }

        ic_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tv_file.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MyNewMessageActivity.this, ChatFileActivity.class).putExtra("account", account));

            }
        });
        tv_report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ReportPopupWindow lawyerJobPopupWindow = new ReportPopupWindow(MyNewMessageActivity.this);
                lawyerJobPopupWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);
                lawyerJobPopupWindow.setAddresskListener(new ReportPopupWindow.OnAddressCListener() {
                    @Override
                    public void onClick(String job) {
                        final DialogLoading loading = new DialogLoading();
                        loading.showDialog(MyNewMessageActivity.this);
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                loading.dismissDialog();
                                ToastUtils.getUtils(MyNewMessageActivity.this).show("举报成功，等待工作人员审核！");
                                finish();
                            }
                        },2000);
                    }
                });
            }
        });
        rl_order_detial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (type_id.equals("2")) {

                    startActivity(new Intent(MyNewMessageActivity.this, QuickConsultingOrderDetailsActivity.class).putExtra("order_no", order_no));

                } else {

                    startActivity(new Intent(MyNewMessageActivity.this, PickUpCaseActivity.class)
                            .putExtra("order_no", order_no).putExtra("type", type_id));
                }

            }
        });



    }


    @Override
    protected void onResume() {
//        getDateTime();
        super.onResume();
    }

    private void getDateTime() {
        PrefsUtils.saveString(MyNewMessageActivity.this, PrefsUtils.ORDER_NO, "");
        String url;
        if (type_id.equals("2")) {

            url = MyData.COUNT_DOWN + account + "/" + PrefsUtils.getString(MyNewMessageActivity.this, PrefsUtils.ACCID)+ "?type_id=" + type_id;
            LogUtils.logE(url);
            HttpUtils httpUtils = new HttpUtils();
            httpUtils.send(HttpMethod.GET, url, new RequestCallBack<String>() {
                @Override
                public void onSuccess(ResponseInfo<String> responseInfo) {
                    try {
                        JSONObject object = new JSONObject(responseInfo.result);
                        LogUtils.logE(responseInfo.result);
                        if (object.optString("code").equals("0")) {
                            JSONObject data = JSONTool.getJsonObject(object, "data");
                            boolean is_lawyer = JSONTool.getBoolean(data, "is_lawyer");

                            if (is_lawyer) {
                                rl_order_detial.setVisibility(View.GONE);
                                tv_date.setVisibility(View.GONE);
                                tv_text_close.setVisibility(View.GONE);

                                canInput = true;
                                PrefsUtils.saveString(MyNewMessageActivity.this, PrefsUtils.IS_HAS_REPLY, "2");
                                setDefaultFragment();
                                return;
                            } else {

                                boolean is_has_reply = JSONTool.getBoolean(data, "is_has_reply");
                                boolean is_closed = JSONTool.getBoolean(data, "is_closed");
                                int countdown = JSONTool.getInt(data, "countdown");
                                order_no = JSONTool.getString(data, "order_no");
                                String status_name = JSONTool.getString(data, "status_name");

                                if (is_has_reply) {
                                    PrefsUtils.saveString(MyNewMessageActivity.this, PrefsUtils.ORDER_NO, order_no);
                                    PrefsUtils.saveString(MyNewMessageActivity.this, PrefsUtils.IS_HAS_REPLY, "1");
                                } else {
                                    PrefsUtils.saveString(MyNewMessageActivity.this, PrefsUtils.IS_HAS_REPLY, "0");
                                }

                                if (!is_closed) {

                                    if (status_name.equals("投诉中")) {

                                        canInput = false;
                                        tv_text_close.setText("投诉中");
                                        tv_text_close.setTextColor(Color.RED);
                                        tv_text_close.setVisibility(View.VISIBLE);
                                        rl_order_detial.setVisibility(View.VISIBLE);
                                        tv_date.setVisibility(View.GONE);
                                        tv_time.setVisibility(View.GONE);
                                        setDefaultFragment();

                                    } else if (status_name.equals("待确认")) {

                                        canInput = false;
//                                    tv_text_close.setText("待确认");
//                                    tv_text_close.setTextColor(Color.RED);
                                        tv_text_close.setVisibility(View.GONE);
                                        rl_order_detial.setVisibility(View.VISIBLE);
                                        tv_time.setText("等待用户确认完成：");
                                        tv_time.setVisibility(View.VISIBLE);
                                        tv_date.setVisibility(View.VISIBLE);
                                        setDefaultFragment();

                                        int start_time = JSONTool.getInt(data, "start_time");
                                        int time = start_time + (countdown * 60 * 60);
                                        LogUtils.logE(time + "*****");
                                        timecountdown(time);

                                    } else {
//                                    PrefsUtils.saveString(MyNewMessageActivity.this,PrefsUtils.IS_COMPLAINTING,"0");

                                        if (status_name.equals("待回复")) {
                                            tv_time.setText("等待您的回复：");
                                            tv_time.setVisibility(View.VISIBLE);
                                        }

                                        if (status_name.equals("进行中")) {
                                            tv_time.setText("专属咨询还剩：");
                                            tv_time.setVisibility(View.VISIBLE);
                                        }

                                        int start_time = JSONTool.getInt(data, "start_time");
                                        canInput = true;
                                        rl_order_detial.setVisibility(View.VISIBLE);
                                        tv_date.setVisibility(View.VISIBLE);
                                        tv_text_close.setVisibility(View.GONE);
                                        int time = start_time + (countdown * 60 * 60);
                                        LogUtils.logE(time + "*****");
                                        timecountdown(time);
                                        setDefaultFragment();
                                    }

                                } else {
                                    if (status_name.equals("已取消")) {
                                        canInput = false;
                                        tv_text_close.setText("已取消");
                                        tv_text_close.setTextColor(Color.RED);
                                        tv_text_close.setVisibility(View.VISIBLE);
                                        rl_order_detial.setVisibility(View.VISIBLE);
                                        tv_date.setVisibility(View.GONE);
                                        tv_time.setVisibility(View.GONE);
                                        setDefaultFragment();
                                    } else {
                                        canInput = false;
                                        tv_text_close.setVisibility(View.VISIBLE);
                                        rl_order_detial.setVisibility(View.VISIBLE);
                                        tv_date.setVisibility(View.GONE);
                                        tv_time.setVisibility(View.GONE);
                                        setDefaultFragment();
                                        return;
                                    }
                                }
                            }
                        }
                    } catch (Exception e) {

                        LogUtils.logE(e.toString());
                    }

                    setDefaultFragment();
                }

                @Override
                public void onFailure(HttpException e, String s) {

                }

            });


        } else {
            url = MyData.COUNT_DOWN + account + "/" + PrefsUtils.getString(MyNewMessageActivity.this, PrefsUtils.ACCID) + "?type_id=" + type_id;

            LogUtils.logE(url);

            HttpUtils httpUtils = new HttpUtils();
            httpUtils.send(HttpMethod.GET, url, new RequestCallBack<String>() {
                @Override
                public void onSuccess(ResponseInfo<String> responseInfo) {
                    try {
                        JSONObject object = new JSONObject(responseInfo.result);
                        LogUtils.logE(responseInfo.result);
                        if (object.optString("code").equals("0")) {
                            JSONObject data = JSONTool.getJsonObject(object, "data");
                            boolean is_lawyer = JSONTool.getBoolean(data, "is_lawyer");

                            if (is_lawyer) {
                                rl_order_detial.setVisibility(View.GONE);
                                tv_date.setVisibility(View.GONE);
                                tv_text_close.setVisibility(View.GONE);

                                canInput = true;
                                PrefsUtils.saveString(MyNewMessageActivity.this, PrefsUtils.IS_HAS_REPLY, "2");
                                setDefaultFragment();
                                return;
                            } else {

                                boolean is_has_reply = JSONTool.getBoolean(data, "is_has_reply");
                                boolean is_closed = JSONTool.getBoolean(data, "is_closed");
                                int countdown = JSONTool.getInt(data, "countdown");
                                order_no = JSONTool.getString(data, "order_no");
                                String status_name = JSONTool.getString(data, "status_name");

                                if (is_has_reply) {
                                    PrefsUtils.saveString(MyNewMessageActivity.this, PrefsUtils.ORDER_NO, order_no);
                                    PrefsUtils.saveString(MyNewMessageActivity.this, PrefsUtils.IS_HAS_REPLY, "1");
                                } else {
                                    PrefsUtils.saveString(MyNewMessageActivity.this, PrefsUtils.IS_HAS_REPLY, "0");
                                }

                                if (!is_closed) {

                                    if (status_name.equals("投诉中")) {

                                        canInput = false;
                                        tv_text_close.setText("投诉中");
                                        tv_text_close.setTextColor(Color.RED);
                                        tv_text_close.setVisibility(View.VISIBLE);
                                        rl_order_detial.setVisibility(View.VISIBLE);
                                        tv_date.setVisibility(View.GONE);
                                        tv_time.setVisibility(View.GONE);
                                        setDefaultFragment();

                                    } else if (status_name.equals("待确认")) {

                                        canInput = false;
//                                    tv_text_close.setText("待确认");
//                                    tv_text_close.setTextColor(Color.RED);
                                        tv_text_close.setVisibility(View.GONE);
                                        rl_order_detial.setVisibility(View.VISIBLE);
                                        tv_time.setText("等待用户确认完成：");
                                        tv_time.setVisibility(View.VISIBLE);
                                        tv_date.setVisibility(View.VISIBLE);
                                        setDefaultFragment();

                                        int start_time = JSONTool.getInt(data, "start_time");
                                        int time = start_time + (countdown * 60 * 60);
                                        LogUtils.logE(time + "*****");
                                        timecountdown(time);

                                    } else {
//                                    PrefsUtils.saveString(MyNewMessageActivity.this,PrefsUtils.IS_COMPLAINTING,"0");

                                        if (status_name.equals("待回复")) {
                                            tv_time.setText("等待您的回复：");
                                            tv_time.setVisibility(View.VISIBLE);
                                        }

                                        if (status_name.equals("进行中")) {
                                            tv_time.setText("沟通时间：");
                                            tv_time.setVisibility(View.VISIBLE);
                                        }

                                        int start_time = JSONTool.getInt(data, "start_time");
                                        canInput = true;
                                        rl_order_detial.setVisibility(View.VISIBLE);
                                        tv_date.setVisibility(View.VISIBLE);
                                        tv_text_close.setVisibility(View.GONE);
                                        int time = start_time + (countdown * 60 * 60);
                                        LogUtils.logE(time + "*****");
                                        timecountdown(time);
                                        setDefaultFragment();
                                    }

                                } else {

                                    if (status_name.equals("已完成")) {
                                        canInput = false;
                                        tv_text_close.setText("已完成");
                                        tv_text_close.setTextColor(Color.RED);
                                        tv_text_close.setVisibility(View.VISIBLE);
                                        rl_order_detial.setVisibility(View.VISIBLE);
                                        tv_date.setVisibility(View.GONE);
                                        tv_time.setVisibility(View.GONE);
                                        setDefaultFragment();
                                    } else if (status_name.equals("已取消")) {
                                        canInput = false;
                                        tv_text_close.setText("已取消");
                                        tv_text_close.setTextColor(Color.RED);
                                        tv_text_close.setVisibility(View.VISIBLE);
                                        rl_order_detial.setVisibility(View.VISIBLE);
                                        tv_date.setVisibility(View.GONE);
                                        tv_time.setVisibility(View.GONE);
                                        setDefaultFragment();
                                    } else {
                                        canInput = false;
                                        tv_text_close.setText("用户已选择其他律师");
                                        tv_text_close.setVisibility(View.VISIBLE);
                                        rl_order_detial.setVisibility(View.GONE);
                                        tv_date.setVisibility(View.GONE);
                                        tv_time.setVisibility(View.GONE);
                                        setDefaultFragment();
                                        return;
                                    }
                                }
                            }
                        }
                    } catch (Exception e) {

                        LogUtils.logE(e.toString());
                    }

                    setDefaultFragment();
                }

                @Override
                public void onFailure(HttpException e, String s) {

                }
            });

        }

    }

    private int day;
    private int hour;
    private int minute;
    private int second;

    private void timecountdown(int time) {

        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());// 格式化时间
        String nowtime = date.format(new Date());// 按以上格式 将当前时间转换成字符串
        String date1 = date.format(new Date(new Long(time)));
        daysBetween(nowtime, date.format(transForDate(time)));
        int[] times = {day, hour, minute, second};
        tv_date.setTimes(times);
        if (!tv_date.isRun()) {
            tv_date.run();
        }
    }


    public static Date transForDate(Integer ms) {
        if (ms == null) {
            ms = 0;
        }
        long msl = (long) ms * 1000;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date temp = null;
        if (ms != null) {
            try {
                String str = sdf.format(msl);
                temp = sdf.parse(str);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return temp;
    }


    /**
     * 字符串的日期格式的计算相差多少天
     */
    private boolean daysBetween(String nowdate, String time) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date d1 = df.parse(time);
            Date d2 = df.parse(nowdate);

            long diff = d1.getTime() - d2.getTime();// 这样得到的差值是微秒级别

            int ss = 1000;
            int mi = ss * 60;
            int hh = mi * 60;
            int dd = hh * 24;

            long day = diff / dd;
            long hour = (diff - day * dd) / hh;
            long minute = (diff - day * dd - hour * hh) / mi;
            long second = (diff - day * dd - hour * hh - minute * mi) / ss;

            String strDay = day < 10 ? "0" + day : "" + day; // 天
            String strHour = hour < 10 ? "0" + hour : "" + hour;// 小时
            String strMinute = minute < 10 ? "0" + minute : "" + minute;// 分钟
            String strSecond = second < 10 ? "0" + second : "" + second;// 秒

            this.day = Integer.valueOf(strDay);
            this.hour = Integer.valueOf(strHour);
            this.minute = Integer.valueOf(strMinute);
            this.second = Integer.valueOf(strSecond);

            return d1.getTime() == d2.getTime() || d1.getTime() < d2.getTime();

        } catch (Exception e) {
        }
        return false;
    }

    private void setDefaultFragment() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        Bundle arguments = new Bundle();
        arguments.putSerializable(Extras.EXTRA_TYPE, SessionTypeEnum.P2P);
        arguments.putSerializable(Extras.EXTRA_ACCOUNT, account);
        arguments.putSerializable(Extras.EXTRA_CUSTOMIZATION, SessionHelper.getP2pCustomization());
        arguments.putBoolean("hide", canInput);
        messageFragment = new MessageFragment();
        messageFragment.setArguments(arguments);
        transaction.replace(R.id.fl_message, messageFragment);
        transaction.commit();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (messageFragment != null) {
            LogUtils.logE("========================55656");
            messageFragment.onActivityResult(requestCode, resultCode, data);
        }

//        if (customization != null) {
//            customization.onActivityResult(this, requestCode, resultCode, data);
//        }
    }


}
