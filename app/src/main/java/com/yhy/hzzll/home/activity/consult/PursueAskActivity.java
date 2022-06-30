package com.yhy.hzzll.home.activity.consult;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.yhy.hzzll.R;
import com.yhy.hzzll.framework.MyData;
import com.yhy.hzzll.home.entity.PursueEntity;
import com.yhy.hzzll.mian.activity.BaseActivity;
import com.yhy.hzzll.utils.HttpDataUtils;
import com.yhy.hzzll.utils.JSONTool;
import com.yhy.hzzll.utils.LogUtils;
import com.yhy.hzzll.utils.PrefsUtils;
import com.yhy.hzzll.utils.ToastUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PursueAskActivity extends BaseActivity {

    @ViewInject(R.id.iv_close)
    private ImageView iv_close;
    @ViewInject(R.id.tv_price)
    private TextView tv_price;
//    @ViewInject(R.id.tv_invite_buy)
//    private TextView tv_invite_buy;

    @ViewInject(R.id.ed_input)
    private EditText ed_input;

    @ViewInject(R.id.lv_pursue)
    private PullToRefreshListView lv_pursue;

//    @ViewInject(R.id.linear_my_service)
//    private LinearLayout linear_my_service;

    PursueAdapter pursueAdapter;
    List<PursueEntity.DataBean.ListBean> listBeanList;

    String reply_id;
    String id;
    String message_id;
    String content;

    boolean show = true;
    boolean edit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pursue_ask);
        ViewUtils.inject(this);

        id = getIntent().getStringExtra("id");
        reply_id = getIntent().getStringExtra("reply_id");
        message_id = getIntent().getStringExtra("message_id");

        lv_pursue.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
        lv_pursue.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                getPursueAsk();
            }
        });

//        if (PrefsUtils.getString(PursueAskActivity.this, PrefsUtils.CONSULT_PRICE).equals("0")) {
//            tv_price.setText("开启咨询");
//            edit = true;
//        } else {
//            tv_price.setText(PrefsUtils.getString(PursueAskActivity.this, PrefsUtils.CONSULT_PRICE));
//        }

        viewInit();
    }

    private void viewInit() {

        listBeanList = new ArrayList<>();
        pursueAdapter = new PursueAdapter(listBeanList);
        lv_pursue.setAdapter(pursueAdapter);
        read();
    }

    private void getPursueAsk() {

        RequestParams params = new RequestParams();
        params.addHeader("Authorization", PrefsUtils.getString(context, PrefsUtils.AUTHORIZATION));
        params.addQueryStringParameter("page_num", "500");
        httpDataUtils.sendProgressGet(MyData.REPLY_REPLY_LIST + reply_id, params);
        httpDataUtils.setSucessBack(new HttpDataUtils.SucessBack() {
            @Override
            public void sucess(ResponseInfo<String> arg0) {
                try {
                    JSONObject object = new JSONObject(arg0.result);
                    String code = JSONTool.getString(object, "code");
                    String msg = JSONTool.getString(object, "msg");
                    if (code.equals("0")) { // 成功
                        lv_pursue.onRefreshComplete();
                        JSONObject data = JSONTool.getJsonObject(object, "data");
                        listBeanList.clear();
                        PursueEntity entity = gson.fromJson(object.toString(), PursueEntity.class);

                        listBeanList.addAll(entity.getData().getList());
                        pursueAdapter.notifyDataSetChanged();

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                lv_pursue.getRefreshableView().setSelection(0);
                            }
                        }, 500);


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
                ToastUtils.getUtils(context).show(msg);
            }
        });
    }

    @OnClick({R.id.ic_back, R.id.tv_send, R.id.tv_details})
    public void onClick(View view) {
        switch (view.getId()) {
//            case R.id.tv_price:
//                if (edit){
//                    startActivity(new Intent().setClass(PursueAskActivity.this, ServiceAndQuoteActivity.class)
//                            .putExtra("office",PrefsUtils.getString(PursueAskActivity.this,PrefsUtils.CONSULT_OPEN))
//                            .putExtra("price",PrefsUtils.getString(PursueAskActivity.this,PrefsUtils.CONSULT_PRICE)));
//                }
//                break;
            case R.id.ic_back:
                finish();
                break;
//            case R.id.iv_close:
//
//                if (show){
//                    linear_my_service.setVisibility(View.GONE);
//                    show = false;
//                }else{
//                    linear_my_service.setVisibility(View.VISIBLE);
//                    show = true;
//                }
//
//                break;
            case R.id.tv_send:
                sendReply();
                break;
//            case R.id.tv_invite_buy:
//
//                sendReplyBuy();
//
//                break;
            case R.id.tv_details:
                startActivity(new Intent(PursueAskActivity.this, ConsultDetailsActivity.class).putExtra("id", id));
                break;

        }
    }

    private void sendReply() {
        RequestParams params = new RequestParams();
        params.addHeader("Authorization", PrefsUtils.getString(PursueAskActivity.this, PrefsUtils.AUTHORIZATION));
        params.addBodyParameter("reply_id", reply_id);
        params.addBodyParameter("content", ed_input.getText().toString());

        httpDataUtils.sendProgressPost(MyData.REPL, params);
        httpDataUtils.setSucessBack(new HttpDataUtils.SucessBack() {
            @Override
            public void sucess(ResponseInfo<String> arg0) {
                JSONObject object = null;
                try {
                    object = new JSONObject(arg0.result);
                    String code = JSONTool.getString(object, "code");
                    if (code.equals("0")) {
                        ed_input.getText().clear();// 情况键盘输入的数据
                        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(ed_input.getWindowToken(), 0);
                        ToastUtils.getUtils(PursueAskActivity.this).show("回复成功！");
                        getPursueAsk();
                    } else {
                        httpDataUtils.showMsgNew(arg0.result);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        httpDataUtils.setFailBack(new HttpDataUtils.FailBack() {
            @Override
            public void fail(String msg) {
                if (msg.equals("Not Found")) {

                }
            }
        });
    }

    private void sendReplyBuy() {
        RequestParams params = new RequestParams();
        params.addHeader("Authorization", PrefsUtils.getString(PursueAskActivity.this, PrefsUtils.AUTHORIZATION));
        String url = MyData.INVITE_BUY + reply_id + "-27";

        httpDataUtils.sendProgressGet(url, params);
        httpDataUtils.setSucessBack(new HttpDataUtils.SucessBack() {
            @Override
            public void sucess(ResponseInfo<String> arg0) {
                JSONObject object = null;
                try {
                    object = new JSONObject(arg0.result);
                    String code = JSONTool.getString(object, "code");
                    if (code.equals("0")) {
                        ToastUtils.getUtils(PursueAskActivity.this).show("邀请成功！");
                        getPursueAsk();

                    } else {
                        httpDataUtils.showMsgNew(arg0.result);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        httpDataUtils.setFailBack(new HttpDataUtils.FailBack() {
            @Override
            public void fail(String msg) {
                if (msg.equals("Not Found")) {

                }
            }
        });
    }


    class PursueAdapter extends BaseAdapter {

        List<PursueEntity.DataBean.ListBean> listBeanList;

        public PursueAdapter(List<PursueEntity.DataBean.ListBean> listBeanList) {
            this.listBeanList = listBeanList;
        }

        @Override
        public int getCount() {
            return listBeanList.size();
        }

        @Override
        public Object getItem(int position) {
            return listBeanList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            convertView = View.inflate(PursueAskActivity.this, R.layout.item_pursue_ask, null);
            ImageView iv_head = (ImageView) convertView.findViewById(R.id.iv_head);
            TextView tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            TextView tv_date = (TextView) convertView.findViewById(R.id.tv_date);
            TextView tv_connect = (TextView) convertView.findViewById(R.id.tv_connect);


            PursueEntity.DataBean.ListBean bean = listBeanList.get(position);

            Glide.with(PursueAskActivity.this).load(bean.getAvatar()).into(iv_head);

            if (bean.getType().equals("职业律师")) {
                tv_name.setText("我");
            } else {
                tv_name.setText(bean.getNickname());
            }

            tv_date.setText(bean.getDate_time());

            tv_connect.setText(bean.getContent());

            return convertView;
        }
    }

    private void read() {
        RequestParams params = new RequestParams();
        params.addHeader("Authorization", PrefsUtils.getString(PursueAskActivity.this, PrefsUtils.AUTHORIZATION));
        params.addHeader("Content-Type", "application/x-www-form-urlencoded");
        LogUtils.logE(message_id);
        params.addQueryStringParameter("message_id", message_id);

        httpDataUtils.sendProgressPut(MyData.MESSAGE_IS_READ, params);
        httpDataUtils.setSucessBack(new HttpDataUtils.SucessBack() {
            @Override
            public void sucess(ResponseInfo<String> arg0) {
                JSONObject object = null;
                try {
                    object = new JSONObject(arg0.result);
                    String code = JSONTool.getString(object, "code");
                    if (code.equals("0")) {
                        getPursueAsk();
                    } else {
                        httpDataUtils.showMsgNew(arg0.result + "gsghajhgjk");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        httpDataUtils.setFailBack(new HttpDataUtils.FailBack() {
            @Override
            public void fail(String msg) {
                if (msg.equals("Not Found")) {

                }
            }
        });
    }
}
