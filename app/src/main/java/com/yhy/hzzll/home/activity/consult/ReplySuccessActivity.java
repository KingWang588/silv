package com.yhy.hzzll.home.activity.consult;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.yhy.hzzll.R;
import com.yhy.hzzll.entity.ConsultEntity;
import com.yhy.hzzll.framework.MyData;
import com.yhy.hzzll.mian.activity.BaseActivity;
import com.yhy.hzzll.mian.activity.MainActivity;
import com.yhy.hzzll.my.activity.TreasureActivity;
import com.yhy.hzzll.utils.HttpDataUtils;
import com.yhy.hzzll.utils.PrefsUtils;
import com.yhy.hzzll.view.ListViewForScrollview;

import java.util.ArrayList;
import java.util.List;

public class ReplySuccessActivity extends BaseActivity {


    @ViewInject(R.id.tv_num)
    private TextView tv_num;
    @ViewInject(R.id.tv_price)
    private TextView tv_price;

    @ViewInject(R.id.tv_price_hongbao)
    private TextView tv_price_hongbao;

    @ViewInject(R.id.tv_money)
    private TextView tv_money;

    @ViewInject(R.id.tv_caina)
    private TextView tv_caina;

    @ViewInject(R.id.tv_timeout)
    private TextView tv_timeout;

    @ViewInject(R.id.linear_price)
    private LinearLayout linear_price;

    @ViewInject(R.id.lv_newest_consult)
    private ListViewForScrollview lv_newest_consult;


    @ViewInject(R.id.linear_hongbao)
    private LinearLayout linear_hongbao;

    @ViewInject(R.id.linear_1)
    private LinearLayout linear_1;

    List<ConsultEntity.DataBean.ListBean> list;
    NewestAdapter newestAdapter ;


    String is_timeout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reply_success);
        ViewUtils.inject(this);

        is_timeout =  getIntent().getStringExtra("is_timeout");

        init();
    }

    private void init() {

        list = new ArrayList<>();
        newestAdapter = new NewestAdapter(list);
        lv_newest_consult.setAdapter(newestAdapter);

        lv_newest_consult.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(ReplySuccessActivity.this, ConsultDetailsActivity.class).putExtra("id", list.get(position).getId() + ""));
            }
        });

        String num = getIntent().getStringExtra("num");

        tv_num.setText(" "+num+" ");

        if (num.equals("1")){
            tv_price.setText(PrefsUtils.getString(ReplySuccessActivity.this,PrefsUtils.FIRST_REPLY));

            if (PrefsUtils.getString(ReplySuccessActivity.this,PrefsUtils.FIRST_REPLY).length()!=0){
                tv_price_hongbao.setText(PrefsUtils.getString(ReplySuccessActivity.this,PrefsUtils.FIRST_REPLY)+" 元");
            }else{
                tv_price_hongbao.setText("10"+" 元");
            }

            if (is_timeout.equals("1")){

                linear_1.setVisibility(View.GONE);
                tv_timeout.setVisibility(View.VISIBLE);
            }


        }else if(num.equals("2")){
            tv_price.setText(PrefsUtils.getString(ReplySuccessActivity.this,PrefsUtils.SECOND_REPLY));

            if (PrefsUtils.getString(ReplySuccessActivity.this,PrefsUtils.SECOND_REPLY).length()!=0){
                tv_price_hongbao.setText(PrefsUtils.getString(ReplySuccessActivity.this,PrefsUtils.SECOND_REPLY)+" 元");
            }else{
                tv_price_hongbao.setText("6"+" 元");
            }

            if (is_timeout.equals("1")){

                linear_1.setVisibility(View.GONE);
                tv_timeout.setVisibility(View.VISIBLE);
            }

//            tv_price_hongbao.setText(PrefsUtils.getString(ReplySuccessActivity.this,PrefsUtils.SECOND_REPLY)+" 元");
        }else if(num.equals("3")){
            tv_price.setText(PrefsUtils.getString(ReplySuccessActivity.this,PrefsUtils.THIRD_REPLY));

            if (PrefsUtils.getString(ReplySuccessActivity.this,PrefsUtils.THIRD_REPLY).length()!=0){
                tv_price_hongbao.setText(PrefsUtils.getString(ReplySuccessActivity.this,PrefsUtils.THIRD_REPLY)+" 元");
            }else{
                tv_price_hongbao.setText("4"+" 元");
            }

            if (is_timeout.equals("1")){
                linear_1.setVisibility(View.GONE);
                tv_timeout.setVisibility(View.VISIBLE);
            }

//            tv_price_hongbao.setText(PrefsUtils.getString(ReplySuccessActivity.this,PrefsUtils.THIRD_REPLY)+" 元");
        }else {
            tv_caina.setVisibility(View.VISIBLE);
            linear_price.setVisibility(View.GONE);
            tv_money.setVisibility(View.GONE);
            linear_hongbao.setVisibility(View.GONE);
        }

        getNewestConsult();

    }

    private void getNewestConsult() {
        RequestParams params = new RequestParams();
        params.addHeader("Authorization", PrefsUtils.getString(ReplySuccessActivity.this, PrefsUtils.AUTHORIZATION));
        params.addQueryStringParameter("consult_type", "3");
        params.addQueryStringParameter("client", "2");
        params.addQueryStringParameter("page", "1");
        params.addQueryStringParameter("page_num", "5");
        httpDataUtils.sendGet(MyData.CONSULT_LIST, params);
        httpDataUtils.setSucessBack(new HttpDataUtils.SucessBack() {
            @Override
            public void sucess(ResponseInfo<String> arg0) {
                if (httpDataUtils.code(arg0.result)) {
                    Gson gson = new Gson();
                    ConsultEntity entity = gson.fromJson(arg0.result, ConsultEntity.class);
                    list.addAll(entity.getData().getList());
                    newestAdapter.notifyDataSetChanged();
                }
            }
        });
        httpDataUtils.setFailBack(new HttpDataUtils.FailBack() {
            @Override
            public void fail(String msg) {

            }
        });



    }

    @OnClick({R.id.ic_back,R.id.tv_home_page,R.id.tv_money,R.id.tv_consult,R.id.tv_details,R.id.iv_close})
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.iv_close:
                linear_hongbao.setVisibility(View.GONE);
                break;

            case R.id.ic_back:
                finish();
                break;
            case R.id.tv_home_page:
                startActivity(new Intent().putExtra("tab", 0).setClass(getApplicationContext(), MainActivity.class));
                break;
            case R.id.tv_money:
                startActivity(new Intent().setClass(ReplySuccessActivity.this, TreasureActivity.class));
                break;
            case R.id.tv_consult:
                startActivity(new Intent(ReplySuccessActivity.this,ConsultActivity.class));
                break;
            case R.id.tv_details:
                finish();
                break;
        }
    }


    class NewestAdapter extends BaseAdapter{

       List<ConsultEntity.DataBean.ListBean> list;

        public NewestAdapter(List<ConsultEntity.DataBean.ListBean> list) {
            this.list = list;
        }

        @Override
        public int getCount() {
            return list != null ? list.size() : 0;
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            convertView = LayoutInflater.from(context).inflate(R.layout.item_newest_consult, null);

            TextView tv_connect = (TextView) convertView.findViewById(R.id.tv_connect);
            TextView tv_type = (TextView) convertView.findViewById(R.id.tv_type);

            ConsultEntity.DataBean.ListBean listBean = list.get(position);
            if (!listBean.getContent().equals("")){
                tv_connect.setText(listBean.getContent());
            }else{
                tv_connect.setText("[语音问题]");
            }

            tv_type.setText(listBean.getLegal_name());

            return convertView;
        }
    }


}
