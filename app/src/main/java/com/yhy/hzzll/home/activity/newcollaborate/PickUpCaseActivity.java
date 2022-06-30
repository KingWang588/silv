package com.yhy.hzzll.home.activity.newcollaborate;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.FileDownloadListener;
import com.liulishuo.filedownloader.FileDownloader;
import com.liulishuo.filedownloader.util.FileDownloadUtils;
import com.netease.nim.uikit.business.session.constant.Extras;
import com.yhy.hzzll.R;
import com.yhy.hzzll.file.browser.FileBrowserActivity;
import com.yhy.hzzll.framework.MyData;
import com.yhy.hzzll.home.activity.ViewPagerExampleActivity;
import com.yhy.hzzll.home.activity.lawyeroffice.LawyerOfficeActivity;
import com.yhy.hzzll.home.adapter.ApplyedLawyerAdapter;
import com.yhy.hzzll.home.adapter.FileAdapter;
import com.yhy.hzzll.home.adapter.QuestionnaireAdapter;
import com.yhy.hzzll.home.entity.Questionnaire;
import com.yhy.hzzll.home.entity.ServiceDetailsEntity;
import com.yhy.hzzll.interfacelib.Connect;
import com.yhy.hzzll.message.MyNewMessageActivity;
import com.yhy.hzzll.message.WebviewActivity;
import com.yhy.hzzll.mian.activity.BaseActivity;
import com.yhy.hzzll.mian.activity.NewPayActivity;
import com.yhy.hzzll.my.activity.CollaborativeOrderDetailsActivity;
import com.yhy.hzzll.session.SessionHelper;
import com.yhy.hzzll.utils.HttpDataUtils;
import com.yhy.hzzll.utils.ImageLoaderUtils;
import com.yhy.hzzll.utils.JSONTool;
import com.yhy.hzzll.utils.LogUtils;
import com.yhy.hzzll.utils.PrefsUtils;
import com.yhy.hzzll.utils.ToastUtils;
import com.yhy.hzzll.view.DialogCooperateAddmoney;
import com.yhy.hzzll.view.DialogCooperateAgreeGiveup;
import com.yhy.hzzll.view.DialogCooperateApplyCollaboration;
import com.yhy.hzzll.view.DialogCooperateSureComplete;
import com.yhy.hzzll.view.DialogCooperateSurePay;
import com.yhy.hzzll.view.GridViewForScrollView;
import com.yhy.hzzll.view.UploadPopupWindow;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import me.nereo.multi_image_selector.MultiImageSelector;

import static com.yhy.hzzll.utils.OpenFileUtil.openFile;

public class PickUpCaseActivity extends BaseActivity {

    @ViewInject(R.id.lv_question)
    ListView lv_question;

    @ViewInject(R.id.ptrfs)
    PullToRefreshScrollView ptrfs;


//    @ViewInject(R.id.sw_rf)
//    SwipeRefreshLayout sw_rf;


    @ViewInject(R.id.tv_o_c)
    TextView tv_o_c;
    @ViewInject(R.id.tv_o_c_1)
    TextView tv_o_c_1;
    @ViewInject(R.id.tv_o_c_2)
    TextView tv_o_c_2;

    @ViewInject(R.id.tv_status)
    TextView tv_status;

    @ViewInject(R.id.iv_avatar_apply)
    ImageView iv_avatar_apply;

    @ViewInject(R.id.iv_avatar_apply_2)
    ImageView iv_avatar_apply_2;

    @ViewInject(R.id.tv_sure_complete)
    TextView tv_sure_complete;

    @ViewInject(R.id.tv_sure_complete_user)
    TextView tv_sure_complete_user;

    @ViewInject(R.id.tv_msg)
    TextView tv_msg;

    @ViewInject(R.id.tv_tip)
    TextView tv_tip;

    @ViewInject(R.id.tv_order_status)
    TextView tv_order_status;

    @ViewInject(R.id.tv_apl_num)
    TextView tv_apl_num;

    @ViewInject(R.id.tv_order_price)
    TextView tv_order_price;

    @ViewInject(R.id.tv_name)
    TextView tv_name;

    @ViewInject(R.id.tv_my_name)
    TextView tv_my_name;
    @ViewInject(R.id.tv_my_name_1)
    TextView tv_my_name_1;

    @ViewInject(R.id.rl_modify_price_1)
    RelativeLayout rl_modify_price_1;

    @ViewInject(R.id.tv_price_trusteeship_1)
    TextView tv_price_trusteeship_1;

    @ViewInject(R.id.tv_my_advantage)
    TextView tv_my_advantage;
    @ViewInject(R.id.tv_my_advantage_1)
    TextView tv_my_advantage_1;

    @ViewInject(R.id.tv_my_address)
    TextView tv_my_address;
    @ViewInject(R.id.tv_my_address_1)
    TextView tv_my_address_1;

    @ViewInject(R.id.tv_time)
    TextView tv_time;

    @ViewInject(R.id.tv_time_1)
    TextView tv_time_1;

    @ViewInject(R.id.tv_my_specialty)
    TextView tv_my_specialty;
    @ViewInject(R.id.tv_my_specialty_1)
    TextView tv_my_specialty_1;

    @ViewInject(R.id.tv_order_no)
    TextView tv_order_no;

    @ViewInject(R.id.tv_modify_price)
    TextView tv_modify_price;

    @ViewInject(R.id.tv_phone)
    TextView tv_phone;

    @ViewInject(R.id.tv_advantage)
    EditText tv_advantage;

    @ViewInject(R.id.et_intention_price)
    EditText et_intention_price;

    @ViewInject(R.id.et_advantage_1)
    EditText et_advantage_1;

//    @ViewInject(R.id.tv_my_apply)
//    LinearLayout tv_my_apply;

    @ViewInject(R.id.linear_p_and_o)
    RelativeLayout linear_p_and_o;

    @ViewInject(R.id.rl_modify_price)
    RelativeLayout rl_modify_price;

    @ViewInject(R.id.linear_anniu)
    LinearLayout linear_anniu;

    @ViewInject(R.id.linear_evelate)
    LinearLayout linear_evelate;

    @ViewInject(R.id.linear_wait_evleant)
    LinearLayout linear_wait_evleant;

    @ViewInject(R.id.linear_omplaint)
    LinearLayout linear_complaint;
    @ViewInject(R.id.linear_my_apply_1)
    LinearLayout linear_my_apply_1;

    @ViewInject(R.id.linear_anniu_applyed)
    LinearLayout linear_anniu_applyed;


    @ViewInject(R.id.view_apply)
    View view_apply;

    @ViewInject(R.id.view_finish)
    View view_finish;

    @ViewInject(R.id.view_applyed)
    View view_applyed;

    @ViewInject(R.id.linear_coll_publish_wait_trusteeship)
    View linear_coll_publish_wait_trusteeship;

    @ViewInject(R.id.linear_go_to_evaluation)
    LinearLayout linear_go_to_evaluation;  //未评价 布局 发布聘请律师 5

    @ViewInject(R.id.linear_have_evaluation)
    LinearLayout linear_have_evaluation;  //未评价 布局 发布聘请律师 5

    @ViewInject(R.id.linear_no_evaluation)
    LinearLayout linear_no_evaluation;  //未评价 布局 发布聘请律师 5

    @ViewInject(R.id.evelate_rateingbar_coll)
    RatingBar evelate_rateingbar_coll;  //未评价 布局 发布聘请律师 5

    @ViewInject(R.id.evelate_rateingbar)
    RatingBar evelate_rateingbar;

    @ViewInject(R.id.evelate_content_coll)
    TextView evelate_content_coll;

    @ViewInject(R.id.evelate_content)
    TextView evelate_content;

    @ViewInject(R.id.linear_other)
    View linear_other;

    @ViewInject(R.id.line_black) //上传文件 中间的黑线
            View line_black;

    @ViewInject(R.id.linear_coll_publish_select_lawyer)
    View linear_coll_publish_select_lawyer;

    @ViewInject(R.id.linear_waiting_to_pay_the_deposit)
    View linear_waiting_to_pay_the_deposit;

    @ViewInject(R.id.linear_question)
    LinearLayout linear_question;

    @ViewInject(R.id.rl_coll_publish_wait_trusteeship)
    RelativeLayout rl_coll_publish_wait_trusteeship;

    @ViewInject(R.id.linear_demand)
    LinearLayout linear_demand;

    @ViewInject(R.id.linear_wait_money)
    LinearLayout linear_wait_money;

    @ViewInject(R.id.linear_b)
    LinearLayout linear_b;


    @ViewInject(R.id.linear_wait_sure)
    LinearLayout linear_wait_sure;

    @ViewInject(R.id.linear_complaint_result)
    LinearLayout linear_complaint_result;


    @ViewInject(R.id.linear_legal_opinion)
    LinearLayout linear_legal_opinion;

    @ViewInject(R.id.linear_T_E)
    LinearLayout linear_T_E;
    @ViewInject(R.id.linear_no_apply)
    LinearLayout linear_no_apply;


    @ViewInject(R.id.tv_legal_opinion_name)
    TextView tv_legal_opinion_name;

    @ViewInject(R.id.tv_legal_opinion_time)
    TextView tv_legal_opinion_time;

    @ViewInject(R.id.tv_data_time)
    TextView tv_data_time;
    @ViewInject(R.id.tv_1_1)
    TextView tv_1_1;
    @ViewInject(R.id.tv_1_2_1)
    TextView tv_1_2_1;
    @ViewInject(R.id.tv_1_2_2)
    TextView tv_1_2_2;

    @ViewInject(R.id.tv_refuse)
    TextView tv_refuse;


    @ViewInject(R.id.tv_upload_1)
    TextView tv_upload_1;//上传文件按钮   只在进行中显示

    @ViewInject(R.id.tv_upload_2)
    TextView tv_upload_2;//上传文件法律意见书  只在进行中显示

    @ViewInject(R.id.tv_no_legal_opinion)
    TextView tv_no_legal_opinion;

    @ViewInject(R.id.tv_no_file)
    TextView tv_no_file;


    @ViewInject(R.id.linear_pq)
    LinearLayout linear_pq;


    @ViewInject(R.id.linear_linear_legal_opinion__trusteeship)
    LinearLayout linear_linear_legal_opinion__trusteeship;
    @ViewInject(R.id.linear_legal_opinion__trusteeship)
    LinearLayout linear_legal_opinion__trusteeship;

    @ViewInject(R.id.tv_prompt)
    TextView tv_prompt;

    @ViewInject(R.id.tv_legal_opinion_name_t)
    TextView tv_legal_opinion_name_t;

    @ViewInject(R.id.tv_legal_opinion_time_t)
    TextView tv_legal_opinion_time_t;

    @ViewInject(R.id.iv_avatar_1)
    ImageView iv_avatar_1;
    @ViewInject(R.id.iv_avatar)
    ImageView iv_avatar;

    @ViewInject(R.id.gv_file)
    GridViewForScrollView gv_file;

    QuestionnaireAdapter questionnaireAdapter;

    List<Questionnaire.DataBean> list = new ArrayList<>();

    @ViewInject(R.id.tv_price_type)
    TextView tv_price_type;

    @ViewInject(R.id.tv_demed)
    TextView tv_demed;

    @ViewInject(R.id.tv_complaint_result)
    TextView tv_complaint_result;
    @ViewInject(R.id.tv_old_price)
    TextView tv_old_price;


    @ViewInject(R.id.tv_title)
    TextView tv_title;

    @ViewInject(R.id.tv_appleyed_l_num)
    TextView tv_appleyed_l_num;


    @ViewInject(R.id.iv_avatar_trusteeship)
    ImageView iv_avatar_trusteeship;

    @ViewInject(R.id.tv_name_trusteeship)
    TextView tv_name_trusteeship;

    @ViewInject(R.id.tv_address_trusteeship)
    TextView tv_address_trusteeship;

    @ViewInject(R.id.tv_specialty_trusteeship)
    TextView tv_specialty_trusteeship;

    @ViewInject(R.id.tv_time_trusteeship)
    TextView tv_time_trusteeship;

    @ViewInject(R.id.tv_price_trusteeship)
    TextView tv_price_trusteeship;

    @ViewInject(R.id.tv_advantage_trusteeship)
    TextView tv_advantage_trusteeship;

    @ViewInject(R.id.tv_email_trusteeship)
    TextView tv_email_trusteeship;

    @ViewInject(R.id.tv_phone_trusteeship)
    TextView tv_phone_trusteeship;

    @ViewInject(R.id.tv_order)
    TextView tv_order;

    @ViewInject(R.id.tv_rufuse_reason)
    TextView tv_rufuse_reason;

    @ViewInject(R.id.linear_rufuse)
    LinearLayout linear_rufuse;

    @ViewInject(R.id.ll_attachment)
    LinearLayout ll_attachment;

    @ViewInject(R.id.ll_album)
    LinearLayout ll_album;


    @ViewInject(R.id.lv_applyed_lawyer)
    ListView lv_applyed_lawyer;


    @ViewInject(R.id.rl_file)
    RelativeLayout rl_file;

    @ViewInject(R.id.tv_file_type)
    TextView tv_file_type;


    ApplyedLawyerAdapter applyedLawyerAdapter;


    boolean open;
    boolean apply_open = true;

    String case_type;
    String private_order_no;
    String order_no;
    String to;
    int employ_layer_id;


    String publisher_cloud_ID;
    String is_public;

    boolean can_connect = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_up_case);

        ViewUtils.inject(this);

        order_no = getIntent().getStringExtra("order_no");

        Log.e("hjhjkhjkhk", getIntent().getStringExtra("order_no"));
        refresh = true;
//
//         getIntent().getStringExtra("type");
//        if (case_type.equals("婚姻保")) {
//            tv_title.setText("婚姻保");
//        } else if (case_type.equals("债权保")) {
//            tv_title.setText("债权保");
//        } else {
//            tv_title.setText("聘请律师");
//        }

//        sw_rf.setRefreshing(true);
//        getDetail();

//
//        sw_rf.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                getDetail();
//            }
//        });

        ptrfs.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ScrollView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ScrollView> refreshView) {
                getDetail();
            }
        });

    }

    int amount_price = 0;


    private void getDetail() {
        RequestParams params = new RequestParams();
        params.addHeader("Authorization", PrefsUtils.getString(PickUpCaseActivity.this, PrefsUtils.AUTHORIZATION));

        httpDataUtils.sendGet(MyData.ORDER_SERVICE_DETAILS + getIntent().getStringExtra("order_no"), params);
        httpDataUtils.setSucessBack(new HttpDataUtils.SucessBack() {
            @Override
            public void sucess(ResponseInfo<String> arg0) {

                ptrfs.onRefreshComplete();

                try {

                    JSONObject object = new JSONObject(arg0.result);
                    String code = JSONTool.getString(object, "code");
                    String msg = JSONTool.getString(object, "message");
                    if (code.equals("0")) { // 成功
                        Gson gson = new Gson();
                        ServiceDetailsEntity serviceDetailsEntity = gson.fromJson(object.toString(), ServiceDetailsEntity.class);
                        private_order_no = serviceDetailsEntity.getData().getOrder_info().getPrivate_order_no();

                        tv_order_no.setText("订单号： "+serviceDetailsEntity.getData().getOrder_info().getOrder_no());
                        tv_order_status.setText(serviceDetailsEntity.getData().getOrder_info().getOrder_status());

                        if (serviceDetailsEntity.getData().getOrder_info().getOrder_status().equals("已完成")) {
                            can_connect = false;
                        }

                        tv_status.setText(serviceDetailsEntity.getData().getOrder_info().getOrder_status());
                        String amount = serviceDetailsEntity.getData().getOrder_info().getAmount();
                        tv_order_price.setText(amount);

//                        if (amount != null && amount.length() > 3) {
//                            amount = amount.substring(0, amount.length() - 3);
//                            amount = amount.replace(",", "");
//                            amount_price = Integer.valueOf(amount).intValue();
//                        }

                        tv_name.setText(serviceDetailsEntity.getData().getOrder_info().getNickname());
                        tv_apl_num.setText("已有" + serviceDetailsEntity.getData().getOrder_info().getNumber() + "名律师申请");
                        tv_phone.setText(serviceDetailsEntity.getData().getOrder_info().getMobile());
                        to = serviceDetailsEntity.getData().getOrder_info().getMobile();
                        tv_data_time.setText(serviceDetailsEntity.getData().getOrder_info().getData_time());

                        is_public = serviceDetailsEntity.getData().getOrder_info().getIs_public();

                        tv_title.setText(serviceDetailsEntity.getData().getOrder_info().getType_name());
                        case_type = serviceDetailsEntity.getData().getOrder_info().getType_name();
                        publisher_cloud_ID = serviceDetailsEntity.getData().getOrder_info().getAccid();

                        viewinit(serviceDetailsEntity);
                    } else {
                        ToastUtils.getUtils(getApplicationContext()).show(msg);
                        finish();
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

    int file_id;
    int application_id;
    String lawyer_ID;
    boolean first = true;

    private void viewinit(final ServiceDetailsEntity serviceDetailsEntity) {


        if (serviceDetailsEntity.getData().getService_info().getApplication_info() != null && serviceDetailsEntity.getData().getService_info().getApplication_info().get(0) != null)
            file_id = serviceDetailsEntity.getData().getService_info().getApplication_info().get(0).getId();

        if (serviceDetailsEntity.getData().getOrder_info().getType_name().equals("婚姻保") || serviceDetailsEntity.getData().getOrder_info().getType_name().equals("债权保")) {

            linear_question.setVisibility(View.VISIBLE);
            questionnaireAdapter = new QuestionnaireAdapter(list, PickUpCaseActivity.this);
            lv_question.setAdapter(questionnaireAdapter);

            if (first) {
                getdata();
                first = false;
            }

        } else {
            linear_demand.setVisibility(View.VISIBLE);
            tv_demed.setText(serviceDetailsEntity.getData().getOrder_info().getQuestion());

            tv_price_type.setText("意向金额：");
            String amount = serviceDetailsEntity.getData().getOrder_info().getBudget_amount();
            amount = amount.substring(0, amount.length() - 3);
            tv_order_price.setText(amount + " 元");

        }

        attachmentData(serviceDetailsEntity.getData().getFile_detail_info());

        if (serviceDetailsEntity.getData().getOrder_info().getIs_my() != null && serviceDetailsEntity.getData().getOrder_info().getIs_my().equals("1")) {

            //自己发布的聘请律师

            switch (serviceDetailsEntity.getData().getService_info().getService_state()) {

                case "1"://邀请中

                    linear_waiting_to_pay_the_deposit.setVisibility(View.GONE);

                    tv_order_no.setText("委托代理类型：" + serviceDetailsEntity.getData().getOrder_info().getEmploy_type_name());
                    tv_tip.setVisibility(View.VISIBLE);
                    tv_tip.setText("全国律师正在申请，请耐心等待。");
                    linear_p_and_o.setVisibility(View.VISIBLE);

                    break;
                case "2"://24小时无人申请

                    tv_order_no.setText("委托代理类型：" + serviceDetailsEntity.getData().getOrder_info().getEmploy_type_name());
                    tv_price_type.setText("意向金额：");
                    String amount1 = serviceDetailsEntity.getData().getOrder_info().getBudget_amount();
                    tv_order_price.setText(amount1);
                    tv_tip.setVisibility(View.VISIBLE);
                    tv_tip.setText("暂无律师申请。");
                    linear_p_and_o.setVisibility(View.VISIBLE);

                    linear_no_apply.setVisibility(View.VISIBLE);

                    break;

                case "3":

                    tv_order_no.setText("委托代理类型：" + serviceDetailsEntity.getData().getOrder_info().getEmploy_type_name());
//                    tv_price_type.setText("意向金额：");
//                    String amount3 = serviceDetailsEntity.getData().getOrder_info().getBudget_amount();
//                    tv_order_price.setText(amount3);
                    linear_p_and_o.setVisibility(View.VISIBLE);

                    linear_coll_publish_select_lawyer.setVisibility(View.VISIBLE);

                    if (serviceDetailsEntity.getData().getService_info().getApplication_info() != null && serviceDetailsEntity.getData().getService_info().getApplication_info().get(0) != null)
                        tv_appleyed_l_num.setText("申请律师(" + serviceDetailsEntity.getData().getService_info().getApplication_info().size() + "人)");

                    applyedLawyerAdapter = new ApplyedLawyerAdapter(PickUpCaseActivity.this, serviceDetailsEntity.getData().getService_info().getApplication_info());
                    lv_applyed_lawyer.setAdapter(applyedLawyerAdapter);

                    lv_applyed_lawyer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            startActivity(new Intent().putExtra("user_id", serviceDetailsEntity.getData().getService_info().getApplication_info().get(i).getLawyer_id()).putExtra("title", serviceDetailsEntity.getData().getService_info().getApplication_info().get(i).getTruename()).putExtra("insert", true).setClass(context, LawyerOfficeActivity.class));

                        }
                    });

                    applyedLawyerAdapter.setCont(new ApplyedLawyerAdapter.Cont() {
                        @Override
                        public void Entrust(final String id, String money) {

                            money = money.substring(0, money.length() - 6);


                            final int wait_money = Integer.valueOf(money).intValue() - 50;
                            amount_price = Integer.valueOf(money).intValue() - 50;


                            DialogCooperateSurePay dialogCooperateSurePay = new DialogCooperateSurePay();
                            dialogCooperateSurePay.showDialog(PickUpCaseActivity.this, new DialogCooperateAgreeGiveup.Click() {
                                @Override
                                public void click() {
                                    entrust(id);
                                }
                            }, "        该律师报价" + money + "元，您已支付50元保障金，还需支付" + wait_money + "元。");

                        }

                        @Override
                        public void Connect(String id) {
                            Intent intent1 = new Intent();
                            intent1.putExtra(Extras.EXTRA_ACCOUNT, id);
                            intent1.putExtra(Extras.EXTRA_CUSTOMIZATION, SessionHelper.getP2pCustomization());
                            intent1.setClass(PickUpCaseActivity.this, MyNewMessageActivity.class);
                            intent1.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                            startActivity(intent1);
                        }
                    });

                    break;
                case "4"://进行中

                    linear_coll_publish_wait_trusteeship.setVisibility(View.VISIBLE);
                    rl_coll_publish_wait_trusteeship.setVisibility(View.GONE);
                    linear_p_and_o.setVisibility(View.VISIBLE);
                    linear_anniu.setVisibility(View.VISIBLE);
                    linear_T_E.setVisibility(View.VISIBLE);

                    linear_linear_legal_opinion__trusteeship.setVisibility(View.VISIBLE);

                    for (int i = 0; i < serviceDetailsEntity.getData().getService_info().getApplication_info().size(); i++) {

                        if (serviceDetailsEntity.getData().getService_info().getApplication_info().get(i).getIs_select() == 1) {

                            if (serviceDetailsEntity.getData().getService_info().getApplication_info().get(i).getAvatar() != null && serviceDetailsEntity.getData().getService_info().getApplication_info().get(i).getAvatar().length() != 0) {
                                Glide.with(PickUpCaseActivity.this).load(serviceDetailsEntity.getData().getService_info().getApplication_info().get(i).getAvatar()).into(iv_avatar_trusteeship);
                            }

                            tv_name_trusteeship.setText(serviceDetailsEntity.getData().getService_info().getApplication_info().get(i).getTruename());
                            tv_address_trusteeship.setText(serviceDetailsEntity.getData().getService_info().getApplication_info().get(i).getBase_region_id().getWhole_name());
                            tv_specialty_trusteeship.setText(serviceDetailsEntity.getData().getService_info().getApplication_info().get(i).getLawyer_secpical());
                            tv_time_trusteeship.setText(serviceDetailsEntity.getData().getService_info().getApplication_info().get(i).getCreated_at());
                            String amount4 = serviceDetailsEntity.getData().getService_info().getApplication_info().get(i).getAmount();
                            amount4 = amount4.substring(0, amount4.length() - 3);
                            tv_price_trusteeship.setText(amount4 + "元");
                            tv_advantage_trusteeship.setText(serviceDetailsEntity.getData().getService_info().getApplication_info().get(i).getAdvantage());
                            if (serviceDetailsEntity.getData().getService_info().getApplication_info().get(i).getEmail().length() == 0) {
                                tv_email_trusteeship.setText("该律师未设置邮箱");
                            } else {
                                tv_email_trusteeship.setText("邮箱：" + serviceDetailsEntity.getData().getService_info().getApplication_info().get(i).getEmail());
                            }

                            tv_phone_trusteeship.setText("电话：" + serviceDetailsEntity.getData().getService_info().getApplication_info().get(i).getMobile());
                            to = serviceDetailsEntity.getData().getService_info().getApplication_info().get(i).getMobile();

                            if (serviceDetailsEntity.getData().getService_info().getApplication_info().get(i).getLegal_opinion() != null) {

                                if (serviceDetailsEntity.getData().getService_info().getApplication_info().get(i).getLegal_opinion().size() != 0) {
                                    tv_prompt.setVisibility(View.GONE);
                                    linear_legal_opinion__trusteeship.setVisibility(View.VISIBLE);

                                    tv_legal_opinion_name_t.setText(serviceDetailsEntity.getData().getService_info().getApplication_info().get(i).getLegal_opinion().get(0).getFilename());
                                    tv_legal_opinion_time_t.setText(serviceDetailsEntity.getData().getService_info().getApplication_info().get(i).getLegal_opinion().get(0).getCreated_time());

                                    final String filename = serviceDetailsEntity.getData().getService_info().getApplication_info().get(0).getLegal_opinion().get(0).getFilename();
                                    final String savePath = FileDownloadUtils.getDefaultSaveRootPath() + File.separator + "document" + File.separator + filename;
                                    final String url = serviceDetailsEntity.getData().getService_info().getApplication_info().get(0).getLegal_opinion().get(0).getFileurl_full();
                                    final String type = serviceDetailsEntity.getData().getService_info().getApplication_info().get(0).getLegal_opinion().get(0).getExtension();

                                    linear_legal_opinion__trusteeship.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            downloadFile(savePath, url, type);
                                        }
                                    });

                                }

                            }

                        }

                    }


                    break;
                case "5"://待确认
                    linear_coll_publish_wait_trusteeship.setVisibility(View.VISIBLE);
                    rl_coll_publish_wait_trusteeship.setVisibility(View.GONE);

                    linear_p_and_o.setVisibility(View.VISIBLE);
                    linear_anniu.setVisibility(View.VISIBLE);
                    tv_sure_complete_user.setVisibility(View.VISIBLE);
                    linear_T_E.setVisibility(View.VISIBLE);

                    for (int i = 0; i < serviceDetailsEntity.getData().getService_info().getApplication_info().size(); i++) {

                        if (serviceDetailsEntity.getData().getService_info().getApplication_info().get(i).getIs_select() == 1) {

                            employ_layer_id = serviceDetailsEntity.getData().getService_info().getApplication_info().get(i).getId();

                            if (serviceDetailsEntity.getData().getService_info().getApplication_info().get(i).getAvatar() != null && serviceDetailsEntity.getData().getService_info().getApplication_info().get(i).getAvatar().length() != 0) {
                                Glide.with(PickUpCaseActivity.this).load(serviceDetailsEntity.getData().getService_info().getApplication_info().get(i).getAvatar()).into(iv_avatar_trusteeship);
                            }

                            tv_name_trusteeship.setText(serviceDetailsEntity.getData().getService_info().getApplication_info().get(i).getTruename());
                            tv_address_trusteeship.setText(serviceDetailsEntity.getData().getService_info().getApplication_info().get(i).getBase_region_id().getWhole_name());
                            tv_specialty_trusteeship.setText(serviceDetailsEntity.getData().getService_info().getApplication_info().get(i).getLawyer_secpical());
                            tv_time_trusteeship.setText(serviceDetailsEntity.getData().getService_info().getApplication_info().get(i).getCreated_at());
                            String amount = serviceDetailsEntity.getData().getService_info().getApplication_info().get(i).getAmount();
                            amount = amount.substring(0, amount.length() - 3);
                            tv_price_trusteeship.setText(amount + "元");
                            tv_advantage_trusteeship.setText(serviceDetailsEntity.getData().getService_info().getApplication_info().get(i).getAdvantage());
                            if (serviceDetailsEntity.getData().getService_info().getApplication_info().get(i).getEmail().length() == 0) {
                                tv_email_trusteeship.setText("该律师未设置邮箱");
                            } else {
                                tv_email_trusteeship.setText("邮箱：" + serviceDetailsEntity.getData().getService_info().getApplication_info().get(i).getEmail());
                            }

                            tv_phone_trusteeship.setText("电话：" + serviceDetailsEntity.getData().getService_info().getApplication_info().get(i).getMobile());
                            to = serviceDetailsEntity.getData().getService_info().getApplication_info().get(i).getMobile();

                            linear_linear_legal_opinion__trusteeship.setVisibility(View.VISIBLE);

                            if (serviceDetailsEntity.getData().getService_info().getApplication_info().get(i).getLegal_opinion().size() != 0 && serviceDetailsEntity.getData().getService_info().getApplication_info().get(i).getLegal_opinion().get(0) != null) {

                                tv_prompt.setVisibility(View.GONE);
                                linear_legal_opinion__trusteeship.setVisibility(View.VISIBLE);

                                tv_legal_opinion_name_t.setText(serviceDetailsEntity.getData().getService_info().getApplication_info().get(i).getLegal_opinion().get(0).getFilename());
                                tv_legal_opinion_time_t.setText(serviceDetailsEntity.getData().getService_info().getApplication_info().get(i).getLegal_opinion().get(0).getCreated_time());


                                final String filename = serviceDetailsEntity.getData().getService_info().getApplication_info().get(0).getLegal_opinion().get(0).getFilename();
                                final String savePath = FileDownloadUtils.getDefaultSaveRootPath() + File.separator + "document" + File.separator + filename;
                                final String url = serviceDetailsEntity.getData().getService_info().getApplication_info().get(0).getLegal_opinion().get(0).getFileurl_full();
                                final String type = serviceDetailsEntity.getData().getService_info().getApplication_info().get(0).getLegal_opinion().get(0).getExtension();

                                linear_legal_opinion__trusteeship.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        downloadFile(savePath, url, type);
                                    }
                                });

                            }

                        }

                    }

                    break;
                case "6"://投诉中

                    tv_tip.setVisibility(View.VISIBLE);
                    tv_tip.setText("您已发起投诉,请等待客服处理");

                    linear_coll_publish_wait_trusteeship.setVisibility(View.VISIBLE);
                    rl_coll_publish_wait_trusteeship.setVisibility(View.GONE);

                    linear_p_and_o.setVisibility(View.VISIBLE);
                    tv_sure_complete_user.setVisibility(View.GONE);
                    linear_anniu.setVisibility(View.VISIBLE);
                    linear_T_E.setVisibility(View.VISIBLE);
//                    linear_go_to_evaluation.setVisibility(View.VISIBLE);
                    for (int i = 0; i < serviceDetailsEntity.getData().getService_info().getApplication_info().size(); i++) {

                        if (serviceDetailsEntity.getData().getService_info().getApplication_info().get(i).getIs_select() == 1) {

                            application_id = serviceDetailsEntity.getData().getService_info().getApplication_info().get(i).getId();

                            if (serviceDetailsEntity.getData().getService_info().getApplication_info().get(i).getAvatar() != null && serviceDetailsEntity.getData().getService_info().getApplication_info().get(i).getAvatar().length() != 0) {
                                Glide.with(PickUpCaseActivity.this).load(serviceDetailsEntity.getData().getService_info().getApplication_info().get(i).getAvatar()).into(iv_avatar_trusteeship);
                            }

                            tv_name_trusteeship.setText(serviceDetailsEntity.getData().getService_info().getApplication_info().get(i).getTruename());
                            tv_address_trusteeship.setText(serviceDetailsEntity.getData().getService_info().getApplication_info().get(i).getBase_region_id().getWhole_name());
                            tv_specialty_trusteeship.setText(serviceDetailsEntity.getData().getService_info().getApplication_info().get(i).getLawyer_secpical());
                            tv_time_trusteeship.setText(serviceDetailsEntity.getData().getService_info().getApplication_info().get(i).getCreated_at());
                            String amount = serviceDetailsEntity.getData().getService_info().getApplication_info().get(i).getAmount();
                            amount = amount.substring(0, amount.length() - 3);
                            tv_price_trusteeship.setText(amount + "元");
                            tv_advantage_trusteeship.setText(serviceDetailsEntity.getData().getService_info().getApplication_info().get(i).getAdvantage());

                            if (serviceDetailsEntity.getData().getService_info().getApplication_info().get(i).getEmail().length() == 0) {
                                tv_email_trusteeship.setText("该律师未设置邮箱");
                            } else {
                                tv_email_trusteeship.setText("邮箱：" + serviceDetailsEntity.getData().getService_info().getApplication_info().get(i).getEmail());
                            }

                            tv_phone_trusteeship.setText("电话：" + serviceDetailsEntity.getData().getService_info().getApplication_info().get(i).getMobile());
                            to = serviceDetailsEntity.getData().getService_info().getApplication_info().get(i).getMobile();

                            linear_linear_legal_opinion__trusteeship.setVisibility(View.VISIBLE);

                            if (serviceDetailsEntity.getData().getService_info().getApplication_info().get(i).getLegal_opinion().size() != 0 && serviceDetailsEntity.getData().getService_info().getApplication_info().get(i).getLegal_opinion().get(0) != null) {

                                tv_prompt.setVisibility(View.GONE);
                                linear_legal_opinion__trusteeship.setVisibility(View.VISIBLE);

                                tv_legal_opinion_name_t.setText(serviceDetailsEntity.getData().getService_info().getApplication_info().get(i).getLegal_opinion().get(0).getFilename());
                                tv_legal_opinion_time_t.setText(serviceDetailsEntity.getData().getService_info().getApplication_info().get(i).getLegal_opinion().get(0).getCreated_time());

                                final String filename = serviceDetailsEntity.getData().getService_info().getApplication_info().get(0).getLegal_opinion().get(0).getFilename();
                                final String savePath = FileDownloadUtils.getDefaultSaveRootPath() + File.separator + "document" + File.separator + filename;
                                final String url = serviceDetailsEntity.getData().getService_info().getApplication_info().get(0).getLegal_opinion().get(0).getFileurl_full();
                                final String type = serviceDetailsEntity.getData().getService_info().getApplication_info().get(0).getLegal_opinion().get(0).getExtension();

                                linear_legal_opinion__trusteeship.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        downloadFile(savePath, url, type);
                                    }
                                });

                            }

                        }

                    }


                    break;
                case "7"://投诉已完成

                    tv_tip.setVisibility(View.VISIBLE);
                    tv_tip.setText("投诉处理已完成。");
                    view_finish.setVisibility(View.VISIBLE);
                    linear_anniu.setVisibility(View.VISIBLE);
                    tv_sure_complete.setVisibility(View.GONE);
//                    linear_wait_evleant.setVisibility(View.VISIBLE);
                    linear_p_and_o.setVisibility(View.VISIBLE);
                    linear_complaint_result.setVisibility(View.VISIBLE);

                    if (serviceDetailsEntity.getData().getService_info().getComplaint() != null) {
                        linear_complaint_result.setVisibility(View.VISIBLE);
                        tv_complaint_result.setText(serviceDetailsEntity.getData().getService_info().getComplaint().getHandle_opinion());
                    }


                    for (int i = 0; i < serviceDetailsEntity.getData().getService_info().getApplication_info().size(); i++) {

                        if (serviceDetailsEntity.getData().getService_info().getApplication_info().get(i).getIs_select() == 1) {

                            application_id = serviceDetailsEntity.getData().getService_info().getApplication_info().get(i).getId();

                            if (serviceDetailsEntity.getData().getService_info().getApplication_info().get(i).getAvatar() != null && serviceDetailsEntity.getData().getService_info().getApplication_info().get(i).getAvatar().length() != 0) {
                                Glide.with(PickUpCaseActivity.this).load(serviceDetailsEntity.getData().getService_info().getApplication_info().get(i).getAvatar()).into(iv_avatar_trusteeship);
                            }

                            tv_name_trusteeship.setText(serviceDetailsEntity.getData().getService_info().getApplication_info().get(i).getTruename());
                            tv_address_trusteeship.setText(serviceDetailsEntity.getData().getService_info().getApplication_info().get(i).getBase_region_id().getWhole_name());
                            tv_specialty_trusteeship.setText(serviceDetailsEntity.getData().getService_info().getApplication_info().get(i).getLawyer_secpical());
                            tv_time_trusteeship.setText(serviceDetailsEntity.getData().getService_info().getApplication_info().get(i).getCreated_at());
                            String amount = serviceDetailsEntity.getData().getService_info().getApplication_info().get(i).getAmount();
                            amount = amount.substring(0, amount.length() - 3);
                            tv_price_trusteeship.setText(amount + "元");
                            tv_advantage_trusteeship.setText(serviceDetailsEntity.getData().getService_info().getApplication_info().get(i).getAdvantage());

                            if (serviceDetailsEntity.getData().getService_info().getApplication_info().get(i).getEmail().length() == 0) {
                                tv_email_trusteeship.setText("该律师未设置邮箱");
                            } else {
                                tv_email_trusteeship.setText("邮箱：" + serviceDetailsEntity.getData().getService_info().getApplication_info().get(i).getEmail());
                            }

                            tv_phone_trusteeship.setText("电话：" + serviceDetailsEntity.getData().getService_info().getApplication_info().get(i).getMobile());
                            to = serviceDetailsEntity.getData().getService_info().getApplication_info().get(i).getMobile();

                            linear_linear_legal_opinion__trusteeship.setVisibility(View.VISIBLE);

                            if (serviceDetailsEntity.getData().getService_info().getApplication_info().get(i).getLegal_opinion().size() != 0 && serviceDetailsEntity.getData().getService_info().getApplication_info().get(i).getLegal_opinion().get(0) != null) {

                                tv_prompt.setVisibility(View.GONE);
                                linear_legal_opinion__trusteeship.setVisibility(View.VISIBLE);

                                tv_legal_opinion_name_t.setText(serviceDetailsEntity.getData().getService_info().getApplication_info().get(i).getLegal_opinion().get(0).getFilename());
                                tv_legal_opinion_time_t.setText(serviceDetailsEntity.getData().getService_info().getApplication_info().get(i).getLegal_opinion().get(0).getCreated_time());

                                final String filename = serviceDetailsEntity.getData().getService_info().getApplication_info().get(0).getLegal_opinion().get(0).getFilename();
                                final String savePath = FileDownloadUtils.getDefaultSaveRootPath() + File.separator + "document" + File.separator + filename;
                                final String url = serviceDetailsEntity.getData().getService_info().getApplication_info().get(0).getLegal_opinion().get(0).getFileurl_full();
                                final String type = serviceDetailsEntity.getData().getService_info().getApplication_info().get(0).getLegal_opinion().get(0).getExtension();

                                linear_legal_opinion__trusteeship.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        downloadFile(savePath, url, type);
                                    }
                                });

                            }

                        }

                    }


                    break;
                case "8"://已完成未评价


                    linear_coll_publish_wait_trusteeship.setVisibility(View.VISIBLE);
                    rl_coll_publish_wait_trusteeship.setVisibility(View.GONE);

                    linear_p_and_o.setVisibility(View.VISIBLE);
                    tv_sure_complete_user.setVisibility(View.GONE);
                    linear_anniu.setVisibility(View.VISIBLE);
                    linear_T_E.setVisibility(View.VISIBLE);
                    linear_go_to_evaluation.setVisibility(View.VISIBLE);
                    for (int i = 0; i < serviceDetailsEntity.getData().getService_info().getApplication_info().size(); i++) {

                        if (serviceDetailsEntity.getData().getService_info().getApplication_info().get(i).getIs_select() == 1) {

                            application_id = serviceDetailsEntity.getData().getService_info().getApplication_info().get(i).getId();

                            if (serviceDetailsEntity.getData().getService_info().getApplication_info().get(i).getAvatar() != null && serviceDetailsEntity.getData().getService_info().getApplication_info().get(i).getAvatar().length() != 0) {
                                Glide.with(PickUpCaseActivity.this).load(serviceDetailsEntity.getData().getService_info().getApplication_info().get(i).getAvatar()).into(iv_avatar_trusteeship);
                            }

                            tv_name_trusteeship.setText(serviceDetailsEntity.getData().getService_info().getApplication_info().get(i).getTruename());
                            tv_address_trusteeship.setText(serviceDetailsEntity.getData().getService_info().getApplication_info().get(i).getBase_region_id().getWhole_name());
                            tv_specialty_trusteeship.setText(serviceDetailsEntity.getData().getService_info().getApplication_info().get(i).getLawyer_secpical());
                            tv_time_trusteeship.setText(serviceDetailsEntity.getData().getService_info().getApplication_info().get(i).getCreated_at());
                            String amount = serviceDetailsEntity.getData().getService_info().getApplication_info().get(i).getAmount();
                            amount = amount.substring(0, amount.length() - 3);
                            tv_price_trusteeship.setText(amount + "元");
                            tv_advantage_trusteeship.setText(serviceDetailsEntity.getData().getService_info().getApplication_info().get(i).getAdvantage());

                            if (serviceDetailsEntity.getData().getService_info().getApplication_info().get(i).getEmail().length() == 0) {
                                tv_email_trusteeship.setText("该律师未设置邮箱");
                            } else {
                                tv_email_trusteeship.setText("邮箱：" + serviceDetailsEntity.getData().getService_info().getApplication_info().get(i).getEmail());
                            }

                            tv_phone_trusteeship.setText("电话：" + serviceDetailsEntity.getData().getService_info().getApplication_info().get(i).getMobile());
                            to = serviceDetailsEntity.getData().getService_info().getApplication_info().get(i).getMobile();

                            linear_linear_legal_opinion__trusteeship.setVisibility(View.VISIBLE);

                            if (serviceDetailsEntity.getData().getService_info().getApplication_info().get(i).getLegal_opinion().size() != 0 && serviceDetailsEntity.getData().getService_info().getApplication_info().get(i).getLegal_opinion().get(0) != null) {

                                tv_prompt.setVisibility(View.GONE);
                                linear_legal_opinion__trusteeship.setVisibility(View.VISIBLE);

                                tv_legal_opinion_name_t.setText(serviceDetailsEntity.getData().getService_info().getApplication_info().get(i).getLegal_opinion().get(0).getFilename());
                                tv_legal_opinion_time_t.setText(serviceDetailsEntity.getData().getService_info().getApplication_info().get(i).getLegal_opinion().get(0).getCreated_time());

                                final String filename = serviceDetailsEntity.getData().getService_info().getApplication_info().get(0).getLegal_opinion().get(0).getFilename();
                                final String savePath = FileDownloadUtils.getDefaultSaveRootPath() + File.separator + "document" + File.separator + filename;
                                final String url = serviceDetailsEntity.getData().getService_info().getApplication_info().get(0).getLegal_opinion().get(0).getFileurl_full();
                                final String type = serviceDetailsEntity.getData().getService_info().getApplication_info().get(0).getLegal_opinion().get(0).getExtension();

                                linear_legal_opinion__trusteeship.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        downloadFile(savePath, url, type);
                                    }
                                });

                            }

                        }

                    }

                    break;
                case "9"://已完成已评价
                    linear_coll_publish_wait_trusteeship.setVisibility(View.VISIBLE);
                    rl_coll_publish_wait_trusteeship.setVisibility(View.GONE);

                    linear_p_and_o.setVisibility(View.VISIBLE);
                    linear_anniu.setVisibility(View.VISIBLE);
                    linear_T_E.setVisibility(View.VISIBLE);
                    linear_go_to_evaluation.setVisibility(View.VISIBLE);
                    linear_no_evaluation.setVisibility(View.GONE);
                    linear_have_evaluation.setVisibility(View.VISIBLE);

                    evelate_rateingbar_coll.setRating(serviceDetailsEntity.getData().getService_info().getEvaluate().getStart_rate() / 2);
//                    evelate_rateingbar_coll.setNumStars();
                    evelate_content_coll.setText(serviceDetailsEntity.getData().getService_info().getEvaluate().getContent());

                    for (int i = 0; i < serviceDetailsEntity.getData().getService_info().getApplication_info().size(); i++) {

                        if (serviceDetailsEntity.getData().getService_info().getApplication_info().get(i).getIs_select() == 1) {

                            application_id = serviceDetailsEntity.getData().getService_info().getApplication_info().get(i).getId();

                            if (serviceDetailsEntity.getData().getService_info().getApplication_info().get(i).getAvatar() != null && serviceDetailsEntity.getData().getService_info().getApplication_info().get(i).getAvatar().length() != 0) {
                                Glide.with(PickUpCaseActivity.this).load(serviceDetailsEntity.getData().getService_info().getApplication_info().get(i).getAvatar()).into(iv_avatar_trusteeship);
                            }

                            tv_name_trusteeship.setText(serviceDetailsEntity.getData().getService_info().getApplication_info().get(i).getTruename());
                            tv_address_trusteeship.setText(serviceDetailsEntity.getData().getService_info().getApplication_info().get(i).getBase_region_id().getWhole_name());
                            tv_specialty_trusteeship.setText(serviceDetailsEntity.getData().getService_info().getApplication_info().get(i).getLawyer_secpical());
                            tv_time_trusteeship.setText(serviceDetailsEntity.getData().getService_info().getApplication_info().get(i).getCreated_at());
                            String amount = serviceDetailsEntity.getData().getService_info().getApplication_info().get(i).getAmount();
                            amount = amount.substring(0, amount.length() - 3);
                            tv_price_trusteeship.setText(amount + "元");
                            tv_advantage_trusteeship.setText(serviceDetailsEntity.getData().getService_info().getApplication_info().get(i).getAdvantage());
                            if (serviceDetailsEntity.getData().getService_info().getApplication_info().get(i).getEmail().length() == 0) {
                                tv_email_trusteeship.setText("该律师未设置邮箱");
                            } else {
                                tv_email_trusteeship.setText("邮箱：" + serviceDetailsEntity.getData().getService_info().getApplication_info().get(i).getEmail());
                            }

                            tv_phone_trusteeship.setText("电话：" + serviceDetailsEntity.getData().getService_info().getApplication_info().get(i).getMobile());
                            to = serviceDetailsEntity.getData().getService_info().getApplication_info().get(i).getMobile();

                            linear_linear_legal_opinion__trusteeship.setVisibility(View.VISIBLE);

                            if (serviceDetailsEntity.getData().getService_info().getApplication_info().get(i).getLegal_opinion().size() != 0 && serviceDetailsEntity.getData().getService_info().getApplication_info().get(i).getLegal_opinion().get(0) != null) {

                                tv_prompt.setVisibility(View.GONE);
                                linear_legal_opinion__trusteeship.setVisibility(View.VISIBLE);

                                tv_legal_opinion_name_t.setText(serviceDetailsEntity.getData().getService_info().getApplication_info().get(i).getLegal_opinion().get(0).getFilename());
                                tv_legal_opinion_time_t.setText(serviceDetailsEntity.getData().getService_info().getApplication_info().get(i).getLegal_opinion().get(0).getCreated_time());

                                final String filename = serviceDetailsEntity.getData().getService_info().getApplication_info().get(0).getLegal_opinion().get(0).getFilename();
                                final String savePath = FileDownloadUtils.getDefaultSaveRootPath() + File.separator + "document" + File.separator + filename;
                                final String url = serviceDetailsEntity.getData().getService_info().getApplication_info().get(0).getLegal_opinion().get(0).getFileurl_full();
                                final String type = serviceDetailsEntity.getData().getService_info().getApplication_info().get(0).getLegal_opinion().get(0).getExtension();

                                linear_legal_opinion__trusteeship.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        downloadFile(savePath, url, type);
                                    }
                                });

                            }

                        }

                    }

                    break;
                case "10"://待付款

                    linear_coll_publish_wait_trusteeship.setVisibility(View.VISIBLE);
                    rl_coll_publish_wait_trusteeship.setVisibility(View.VISIBLE);

                    linear_p_and_o.setVisibility(View.VISIBLE);

                    for (int i = 0; i < serviceDetailsEntity.getData().getService_info().getApplication_info().size(); i++) {

                        if (serviceDetailsEntity.getData().getService_info().getApplication_info().get(i).getIs_select() == 1) {

                            if (serviceDetailsEntity.getData().getService_info().getApplication_info().get(i).getAvatar() != null && serviceDetailsEntity.getData().getService_info().getApplication_info().get(i).getAvatar().length() != 0) {
                                Glide.with(PickUpCaseActivity.this).load(serviceDetailsEntity.getData().getService_info().getApplication_info().get(i).getAvatar()).into(iv_avatar_trusteeship);
                            }

                            tv_name_trusteeship.setText(serviceDetailsEntity.getData().getService_info().getApplication_info().get(i).getTruename());
                            tv_address_trusteeship.setText(serviceDetailsEntity.getData().getService_info().getApplication_info().get(i).getBase_region_id().getWhole_name());
                            tv_specialty_trusteeship.setText(serviceDetailsEntity.getData().getService_info().getApplication_info().get(i).getLawyer_secpical());
                            tv_time_trusteeship.setText(serviceDetailsEntity.getData().getService_info().getApplication_info().get(i).getCreated_at());

                            String amount5 = serviceDetailsEntity.getData().getService_info().getApplication_info().get(i).getAmount();
                            tv_price_trusteeship.setText(amount5.substring(0, amount5.length() - 3) + "元");
                            amount5 = amount5.substring(0, amount5.length() - 6);
                            amount_price = Integer.valueOf(amount5).intValue() - 50;

                            tv_advantage_trusteeship.setText(serviceDetailsEntity.getData().getService_info().getApplication_info().get(i).getAdvantage());
                            lawyer_ID = serviceDetailsEntity.getData().getService_info().getApplication_info().get(0).getAccid();
                        }

                    }
                    break;


                case "11":

                    linear_p_and_o.setVisibility(View.VISIBLE);
                    linear_waiting_to_pay_the_deposit.setVisibility(View.VISIBLE);

                    break;


                case "13":

                    tv_tip.setVisibility(View.VISIBLE);
                    tv_tip.setText("订单已取消。");
                    linear_p_and_o.setVisibility(View.VISIBLE);
                    linear_waiting_to_pay_the_deposit.setVisibility(View.GONE);
                    linear_coll_publish_wait_trusteeship.setVisibility(View.GONE);
                    rl_coll_publish_wait_trusteeship.setVisibility(View.GONE);

                    break;


            }
        } else {

            switch (serviceDetailsEntity.getData().getService_info().getService_state()) {
                case "1":
                    open = true;
                    lv_question.setVisibility(View.VISIBLE);
                    view_apply.setVisibility(View.VISIBLE);


                    if (serviceDetailsEntity.getData().getOrder_info().getType_name().equals("婚姻保") || serviceDetailsEntity.getData().getOrder_info().getType_name().equals("债权保")) {

                        linear_b.setVisibility(View.VISIBLE);

                        Glide.with(PickUpCaseActivity.this)
                                .load(PrefsUtils.getString(PickUpCaseActivity.this,PrefsUtils.AVATAR))
                                .into(iv_avatar_apply);

                    } else {
                        linear_pq.setVisibility(View.VISIBLE);
                        Glide.with(PickUpCaseActivity.this)
                                .load(PrefsUtils.getString(PickUpCaseActivity.this,PrefsUtils.AVATAR))
                                .into(iv_avatar_apply_2);

                        String amount1 = serviceDetailsEntity.getData().getOrder_info().getBudget_amount();

//                        amount_price = Integer.valueOf(amount.substring(0, amount.length() - 3)).intValue();
                        tv_order_price.setText(amount1.substring(0, amount1.length() - 3) + "元");

                        if (is_public.equals("2")) {
                            tv_1_1.setVisibility(View.GONE);
                            tv_1_2_1.setVisibility(View.VISIBLE);
                            tv_1_2_2.setVisibility(View.VISIBLE);
                            tv_refuse.setVisibility(View.VISIBLE);
                        }

                    }


                    break;

                case "2":
                    open = false;
                    lv_question.setVisibility(View.GONE);
                    view_apply.setVisibility(View.GONE);

                    view_applyed.setVisibility(View.VISIBLE);
                    linear_anniu_applyed.setVisibility(View.VISIBLE);

                    if (serviceDetailsEntity.getData().getService_info().getApplication_info() != null && serviceDetailsEntity.getData().getService_info().getApplication_info().size() != 0) {
                        tv_my_name.setText(serviceDetailsEntity.getData().getService_info().getApplication_info().get(0).getTruename());
                        tv_my_address.setText(serviceDetailsEntity.getData().getService_info().getApplication_info().get(0).getBase_region_id().getWhole_name());
                        tv_my_advantage.setText(serviceDetailsEntity.getData().getService_info().getApplication_info().get(0).getAdvantage());
                        tv_my_specialty.setText(serviceDetailsEntity.getData().getService_info().getApplication_info().get(0).getLawyer_secpical());
                        tv_time.setText(serviceDetailsEntity.getData().getService_info().getApplication_info().get(0).getCreated_at());


                        if (PrefsUtils.getString(PickUpCaseActivity.this, PrefsUtils.AVATAR) != null & PrefsUtils.getString(PickUpCaseActivity.this, PrefsUtils.AVATAR).length() != 0)
                            Glide.with(PickUpCaseActivity.this).load(PrefsUtils.getString(PickUpCaseActivity.this, PrefsUtils.AVATAR)).into(iv_avatar);

                        if (!(case_type.equals("婚姻保") || case_type.equals("债权保"))) {
                            rl_modify_price.setVisibility(View.VISIBLE);
                            String amount = serviceDetailsEntity.getData().getService_info().getApplication_info().get(0).getAmount();
                            amount = amount.substring(0, amount.length() - 3);

                            tv_old_price.setText(amount + "元");
                        }
                    }

                    if (!(case_type.equals("婚姻保") || case_type.equals("债权保"))) {
                        String amount3 = serviceDetailsEntity.getData().getOrder_info().getBudget_amount();
                        tv_order_price.setText(amount3);
                    }


                    break;

                case "3":

                    view_applyed.setVisibility(View.GONE);
                    linear_anniu_applyed.setVisibility(View.GONE);

                    String data_time = serviceDetailsEntity.getData().getOrder_info().getData_time();

                    data_time = data_time.substring(0, 10);
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    try {
                        Date date = simpleDateFormat.parse(data_time);
                        System.out.println(date);
                        Calendar cal = Calendar.getInstance();
                        cal.setTime(date);
                        cal.add(Calendar.DATE, 7);
                        date = cal.getTime();

                        String dateString = simpleDateFormat.format(date);
                        tv_tip.setText("服务已开始，请于" + dateString + "前完成服务");

                    } catch (ParseException px) {
                        px.printStackTrace();
                    }


                    tv_tip.setVisibility(View.VISIBLE);

                    view_finish.setVisibility(View.VISIBLE);
                    linear_anniu.setVisibility(View.VISIBLE);
                    tv_sure_complete.setVisibility(View.VISIBLE);

                    linear_p_and_o.setVisibility(View.VISIBLE);

                    if (serviceDetailsEntity.getData().getService_info().getApplication_info() != null && serviceDetailsEntity.getData().getService_info().getApplication_info().size() != 0) {
                        tv_my_name_1.setText(serviceDetailsEntity.getData().getService_info().getApplication_info().get(0).getTruename());
                        tv_my_address_1.setText(serviceDetailsEntity.getData().getService_info().getApplication_info().get(0).getBase_region_id().getWhole_name());
                        tv_my_advantage_1.setText(serviceDetailsEntity.getData().getService_info().getApplication_info().get(0).getAdvantage());
                        tv_my_specialty_1.setText(serviceDetailsEntity.getData().getService_info().getApplication_info().get(0).getLawyer_secpical());
                        tv_time_1.setText(serviceDetailsEntity.getData().getService_info().getApplication_info().get(0).getCreated_at());


                        if (PrefsUtils.getString(PickUpCaseActivity.this, PrefsUtils.AVATAR) != null & PrefsUtils.getString(PickUpCaseActivity.this, PrefsUtils.AVATAR).length() != 0)
                            Glide.with(PickUpCaseActivity.this).load(PrefsUtils.getString(PickUpCaseActivity.this, PrefsUtils.AVATAR)).into(iv_avatar_1);

                        if (!(case_type.equals("婚姻保") || case_type.equals("债权保"))) {
                            rl_file.setVisibility(View.GONE);
                            line_black.setVisibility(View.GONE);
                            tv_file_type.setText("附件");
                            tv_no_legal_opinion.setText("您还未上传附件");
                        } else {
                            tv_upload_1.setVisibility(View.VISIBLE);
                        }

                        tv_upload_2.setVisibility(View.VISIBLE);

                        if (serviceDetailsEntity.getData().getService_info().getApplication_info().get(0).getWritten_documents() != null & serviceDetailsEntity.getData().getService_info().getApplication_info().get(0).getWritten_documents().size() != 0) {

                            FileAdapter fileAdapter = new FileAdapter(PickUpCaseActivity.this, serviceDetailsEntity.getData().getService_info().getApplication_info().get(0).getWritten_documents());
                            gv_file.setAdapter(fileAdapter);
                            final List<ServiceDetailsEntity.DataBean.ServiceInfoBean.ApplicationInfoBean.WrittenDocumentsBean> written_documents = serviceDetailsEntity.getData().getService_info().getApplication_info().get(0).getWritten_documents();
                            gv_file.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                                    String savePath = FileDownloadUtils.getDefaultSaveRootPath() + File.separator + "document" + File.separator + written_documents.get(i).getFilename();
                                    String url = written_documents.get(i).getFileurl_full();
                                    String type = written_documents.get(i).getExtension();

                                    downloadFile(savePath, url, type);

                                }
                            });
                        }else {
                            tv_no_file.setVisibility(View.VISIBLE);
                        }

                        if (serviceDetailsEntity.getData().getService_info().getApplication_info().get(0).getLegal_opinion() != null & serviceDetailsEntity.getData().getService_info().getApplication_info().get(0).getLegal_opinion().size() != 0) {
                            tv_no_legal_opinion.setVisibility(View.GONE);
                            linear_legal_opinion.setVisibility(View.VISIBLE);
                            tv_legal_opinion_name.setText(serviceDetailsEntity.getData().getService_info().getApplication_info().get(0).getLegal_opinion().get(0).getFilename());
                            tv_legal_opinion_time.setText(serviceDetailsEntity.getData().getService_info().getApplication_info().get(0).getLegal_opinion().get(0).getCreated_time());

                            final String filename = serviceDetailsEntity.getData().getService_info().getApplication_info().get(0).getLegal_opinion().get(0).getFilename();
                            final String savePath = FileDownloadUtils.getDefaultSaveRootPath() + File.separator + "document" + File.separator + filename;
                            final String url = serviceDetailsEntity.getData().getService_info().getApplication_info().get(0).getLegal_opinion().get(0).getFileurl_full();
                            final String type = serviceDetailsEntity.getData().getService_info().getApplication_info().get(0).getLegal_opinion().get(0).getExtension();

                            linear_legal_opinion.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    downloadFile(savePath, url, type);
                                }
                            });
                        } else {
                            linear_legal_opinion.setVisibility(View.GONE);
                            tv_no_legal_opinion.setVisibility(View.VISIBLE);

                        }


                        if (!(case_type.equals("婚姻保") || case_type.equals("债权保"))) {
                            rl_modify_price_1.setVisibility(View.VISIBLE);
                            String amount = serviceDetailsEntity.getData().getService_info().getApplication_info().get(0).getAmount();
                            amount = amount.substring(0, amount.length() - 3);

                            tv_price_trusteeship_1.setText(amount + "元");

                        }


                    }

                    break;

                case "4":// 待确认
                    tv_tip.setVisibility(View.VISIBLE);
                    tv_tip.setText("您服务已完成，正在等待用户确认。");
                    view_finish.setVisibility(View.VISIBLE);
                    linear_anniu.setVisibility(View.VISIBLE);
                    tv_sure_complete.setVisibility(View.GONE);

                    linear_p_and_o.setVisibility(View.VISIBLE);


                    linear_wait_sure.setVisibility(View.VISIBLE);
                    if (serviceDetailsEntity.getData().getService_info().getApplication_info() != null && serviceDetailsEntity.getData().getService_info().getApplication_info().size() != 0) {
                        tv_my_name_1.setText(serviceDetailsEntity.getData().getService_info().getApplication_info().get(0).getTruename());
                        tv_my_address_1.setText(serviceDetailsEntity.getData().getService_info().getApplication_info().get(0).getBase_region_id().getWhole_name());
                        tv_my_advantage_1.setText(serviceDetailsEntity.getData().getService_info().getApplication_info().get(0).getAdvantage());
                        tv_my_specialty_1.setText(serviceDetailsEntity.getData().getService_info().getApplication_info().get(0).getLawyer_secpical());
                        tv_time_1.setText(serviceDetailsEntity.getData().getService_info().getApplication_info().get(0).getCreated_at());


                        if (PrefsUtils.getString(PickUpCaseActivity.this, PrefsUtils.AVATAR) != null & PrefsUtils.getString(PickUpCaseActivity.this, PrefsUtils.AVATAR).length() != 0)
                            Glide.with(PickUpCaseActivity.this).load(PrefsUtils.getString(PickUpCaseActivity.this, PrefsUtils.AVATAR)).into(iv_avatar_1);


                        if (!(case_type.equals("婚姻保") || case_type.equals("债权保"))) {
                            rl_file.setVisibility(View.GONE);

                            line_black.setVisibility(View.GONE);
                            tv_file_type.setText("附件");
                            tv_no_legal_opinion.setText("您还未上传附件");
                        }

                        if (serviceDetailsEntity.getData().getService_info().getApplication_info().get(0).getWritten_documents() != null & serviceDetailsEntity.getData().getService_info().getApplication_info().get(0).getWritten_documents().size() != 0) {

                            FileAdapter fileAdapter = new FileAdapter(PickUpCaseActivity.this, serviceDetailsEntity.getData().getService_info().getApplication_info().get(0).getWritten_documents());
                            gv_file.setAdapter(fileAdapter);
                            final List<ServiceDetailsEntity.DataBean.ServiceInfoBean.ApplicationInfoBean.WrittenDocumentsBean> written_documents = serviceDetailsEntity.getData().getService_info().getApplication_info().get(0).getWritten_documents();

                            gv_file.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                                    String savePath = FileDownloadUtils.getDefaultSaveRootPath() + File.separator + "document" + File.separator + written_documents.get(i).getFilename();
                                    String url = written_documents.get(i).getFileurl_full();
                                    String type = written_documents.get(i).getExtension();

                                    downloadFile(savePath, url, type);

                                }
                            });


                        } else {
                            tv_no_file.setVisibility(View.VISIBLE);
                        }

                        tv_upload_1.setVisibility(View.GONE);
                        tv_upload_2.setVisibility(View.GONE);

                        if (serviceDetailsEntity.getData().getService_info().getApplication_info().get(0).getLegal_opinion() != null & serviceDetailsEntity.getData().getService_info().getApplication_info().get(0).getLegal_opinion().size() != 0) {

                            linear_legal_opinion.setVisibility(View.VISIBLE);
                            tv_legal_opinion_name.setText(serviceDetailsEntity.getData().getService_info().getApplication_info().get(0).getLegal_opinion().get(0).getFilename());
                            tv_legal_opinion_time.setText(serviceDetailsEntity.getData().getService_info().getApplication_info().get(0).getLegal_opinion().get(0).getCreated_time());

                            final String filename = serviceDetailsEntity.getData().getService_info().getApplication_info().get(0).getLegal_opinion().get(0).getFilename();
                            final String savePath = FileDownloadUtils.getDefaultSaveRootPath() + File.separator + "document" + File.separator + filename;
                            final String url = serviceDetailsEntity.getData().getService_info().getApplication_info().get(0).getLegal_opinion().get(0).getFileurl_full();
                            final String type = serviceDetailsEntity.getData().getService_info().getApplication_info().get(0).getLegal_opinion().get(0).getExtension();

                            linear_legal_opinion.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    downloadFile(savePath, url, type);
                                }
                            });

                        } else {
                            tv_no_legal_opinion.setVisibility(View.VISIBLE);
                        }


                        if (!(case_type.equals("婚姻保") || case_type.equals("债权保"))) {
                            rl_modify_price_1.setVisibility(View.VISIBLE);
                            String amount = serviceDetailsEntity.getData().getService_info().getApplication_info().get(0).getAmount();
                            amount = amount.substring(0, amount.length() - 3);
                            tv_price_trusteeship_1.setText(amount + "元");

                            tv_no_file.setVisibility(View.GONE);

                        }


                    }
                    break;


                case "5":
                    linear_wait_evleant.setVisibility(View.GONE);
                    tv_tip.setVisibility(View.VISIBLE);
                    tv_tip.setText("用户已提起投诉。");
                    view_finish.setVisibility(View.VISIBLE);
                    linear_anniu.setVisibility(View.VISIBLE);
                    tv_sure_complete.setVisibility(View.GONE);
                    linear_wait_sure.setVisibility(View.GONE);
                    linear_p_and_o.setVisibility(View.VISIBLE);


                    linear_complaint.setVisibility(View.VISIBLE);
                    if (serviceDetailsEntity.getData().getService_info().getApplication_info() != null && serviceDetailsEntity.getData().getService_info().getApplication_info().size() != 0) {
                        tv_my_name_1.setText(serviceDetailsEntity.getData().getService_info().getApplication_info().get(0).getTruename());
                        tv_my_address_1.setText(serviceDetailsEntity.getData().getService_info().getApplication_info().get(0).getBase_region_id().getWhole_name());
                        tv_my_advantage_1.setText(serviceDetailsEntity.getData().getService_info().getApplication_info().get(0).getAdvantage());
                        tv_my_specialty_1.setText(serviceDetailsEntity.getData().getService_info().getApplication_info().get(0).getLawyer_secpical());
                        tv_time_1.setText(serviceDetailsEntity.getData().getService_info().getApplication_info().get(0).getCreated_at());


                        if (PrefsUtils.getString(PickUpCaseActivity.this, PrefsUtils.AVATAR) != null & PrefsUtils.getString(PickUpCaseActivity.this, PrefsUtils.AVATAR).length() != 0)
                            Glide.with(PickUpCaseActivity.this).load(PrefsUtils.getString(PickUpCaseActivity.this, PrefsUtils.AVATAR)).into(iv_avatar_1);

                        if (!(case_type.equals("婚姻保") || case_type.equals("债权保"))) {
                            rl_file.setVisibility(View.GONE);
                            line_black.setVisibility(View.GONE);
                            tv_file_type.setText("附件");
                            tv_no_legal_opinion.setText("您还未上传附件");
                        }

                        if (serviceDetailsEntity.getData().getService_info().getApplication_info().get(0).getWritten_documents() != null & serviceDetailsEntity.getData().getService_info().getApplication_info().get(0).getWritten_documents().size() != 0) {

                            FileAdapter fileAdapter = new FileAdapter(PickUpCaseActivity.this, serviceDetailsEntity.getData().getService_info().getApplication_info().get(0).getWritten_documents());
                            gv_file.setAdapter(fileAdapter);

                            final List<ServiceDetailsEntity.DataBean.ServiceInfoBean.ApplicationInfoBean.WrittenDocumentsBean> written_documents = serviceDetailsEntity.getData().getService_info().getApplication_info().get(0).getWritten_documents();
                            gv_file.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                                    String savePath = FileDownloadUtils.getDefaultSaveRootPath() + File.separator + "document" + File.separator + written_documents.get(i).getFilename();
                                    String url = written_documents.get(i).getFileurl_full();
                                    String type = written_documents.get(i).getExtension();

                                    downloadFile(savePath, url, type);

                                }
                            });


                        } else {
                            tv_no_file.setVisibility(View.VISIBLE);
                        }

                        if (serviceDetailsEntity.getData().getService_info().getApplication_info().get(0).getLegal_opinion() != null & serviceDetailsEntity.getData().getService_info().getApplication_info().get(0).getLegal_opinion().size() != 0) {

                            linear_legal_opinion.setVisibility(View.VISIBLE);
                            tv_legal_opinion_name.setText(serviceDetailsEntity.getData().getService_info().getApplication_info().get(0).getLegal_opinion().get(0).getFilename());
                            tv_legal_opinion_time.setText(serviceDetailsEntity.getData().getService_info().getApplication_info().get(0).getLegal_opinion().get(0).getCreated_time());

                            final String filename = serviceDetailsEntity.getData().getService_info().getApplication_info().get(0).getLegal_opinion().get(0).getFilename();
                            final String savePath = FileDownloadUtils.getDefaultSaveRootPath() + File.separator + "document" + File.separator + filename;
                            final String url = serviceDetailsEntity.getData().getService_info().getApplication_info().get(0).getLegal_opinion().get(0).getFileurl_full();
                            final String type = serviceDetailsEntity.getData().getService_info().getApplication_info().get(0).getLegal_opinion().get(0).getExtension();

                            linear_legal_opinion.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    downloadFile(savePath, url, type);
                                }
                            });

                        } else {
                            tv_no_legal_opinion.setVisibility(View.VISIBLE);
                        }


                        LogUtils.logE(case_type);

                        if (!(case_type.equals("婚姻保") || case_type.equals("债权保"))) {
                            rl_modify_price_1.setVisibility(View.VISIBLE);
                            String amount = serviceDetailsEntity.getData().getService_info().getApplication_info().get(0).getAmount();
                            amount = amount.substring(0, amount.length() - 3);

                            tv_price_trusteeship_1.setText(amount + "元");

                        }


                    }

                    break;

                case "6": //投诉中 已完成
                    tv_tip.setVisibility(View.VISIBLE);
                    tv_tip.setText("投诉处理已完成。");
                    view_finish.setVisibility(View.VISIBLE);
                    linear_anniu.setVisibility(View.VISIBLE);
                    tv_sure_complete.setVisibility(View.GONE);
//                    linear_wait_evleant.setVisibility(View.VISIBLE);
                    linear_p_and_o.setVisibility(View.VISIBLE);
                    linear_complaint_result.setVisibility(View.VISIBLE);

                    if (serviceDetailsEntity.getData().getService_info().getComplaint() != null) {
                        linear_complaint_result.setVisibility(View.VISIBLE);
                        tv_complaint_result.setText(serviceDetailsEntity.getData().getService_info().getComplaint().getHandle_opinion());
                    }


                    if (serviceDetailsEntity.getData().getService_info().getApplication_info() != null && serviceDetailsEntity.getData().getService_info().getApplication_info().size() != 0) {

                        tv_my_name_1.setText(serviceDetailsEntity.getData().getService_info().getApplication_info().get(0).getTruename());
                        tv_my_address_1.setText(serviceDetailsEntity.getData().getService_info().getApplication_info().get(0).getBase_region_id().getWhole_name());
                        tv_my_advantage_1.setText(serviceDetailsEntity.getData().getService_info().getApplication_info().get(0).getAdvantage());
                        tv_my_specialty_1.setText(serviceDetailsEntity.getData().getService_info().getApplication_info().get(0).getLawyer_secpical());
                        tv_time_1.setText(serviceDetailsEntity.getData().getService_info().getApplication_info().get(0).getCreated_at());


                        if (PrefsUtils.getString(PickUpCaseActivity.this, PrefsUtils.AVATAR) != null & PrefsUtils.getString(PickUpCaseActivity.this, PrefsUtils.AVATAR).length() != 0)
                            Glide.with(PickUpCaseActivity.this).load(PrefsUtils.getString(PickUpCaseActivity.this, PrefsUtils.AVATAR)).into(iv_avatar_1);

                        if (!(case_type.equals("婚姻保") || case_type.equals("债权保"))) {
                            rl_file.setVisibility(View.GONE);
                            line_black.setVisibility(View.GONE);
                            tv_file_type.setText("附件");
                            tv_no_legal_opinion.setText("您还未上传附件");
                        }

                        if (serviceDetailsEntity.getData().getService_info().getApplication_info().get(0).getWritten_documents() != null & serviceDetailsEntity.getData().getService_info().getApplication_info().get(0).getWritten_documents().size() != 0) {

                            FileAdapter fileAdapter = new FileAdapter(PickUpCaseActivity.this, serviceDetailsEntity.getData().getService_info().getApplication_info().get(0).getWritten_documents());
                            gv_file.setAdapter(fileAdapter);

                            final List<ServiceDetailsEntity.DataBean.ServiceInfoBean.ApplicationInfoBean.WrittenDocumentsBean> written_documents = serviceDetailsEntity.getData().getService_info().getApplication_info().get(0).getWritten_documents();
                            gv_file.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                                    String savePath = FileDownloadUtils.getDefaultSaveRootPath() + File.separator + "document" + File.separator + written_documents.get(i).getFilename();
                                    String url = written_documents.get(i).getFileurl_full();
                                    String type = written_documents.get(i).getExtension();

                                    downloadFile(savePath, url, type);

                                }
                            });
                        }else {
                            tv_no_file.setVisibility(View.VISIBLE);
                        }

                        if (serviceDetailsEntity.getData().getService_info().getApplication_info().get(0).getLegal_opinion() != null & serviceDetailsEntity.getData().getService_info().getApplication_info().get(0).getLegal_opinion().size() != 0) {

                            linear_legal_opinion.setVisibility(View.VISIBLE);
                            tv_legal_opinion_name.setText(serviceDetailsEntity.getData().getService_info().getApplication_info().get(0).getLegal_opinion().get(0).getFilename());
                            tv_legal_opinion_time.setText(serviceDetailsEntity.getData().getService_info().getApplication_info().get(0).getLegal_opinion().get(0).getCreated_time());

                            final String filename = serviceDetailsEntity.getData().getService_info().getApplication_info().get(0).getLegal_opinion().get(0).getFilename();
                            final String savePath = FileDownloadUtils.getDefaultSaveRootPath() + File.separator + "document" + File.separator + filename;
                            final String url = serviceDetailsEntity.getData().getService_info().getApplication_info().get(0).getLegal_opinion().get(0).getFileurl_full();
                            final String type = serviceDetailsEntity.getData().getService_info().getApplication_info().get(0).getLegal_opinion().get(0).getExtension();

                            linear_legal_opinion.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    downloadFile(savePath, url, type);
                                }
                            });

                        }

                        if (!(case_type.equals("婚姻保") || case_type.equals("债权保"))) {
                            rl_modify_price_1.setVisibility(View.VISIBLE);
                            String amount = serviceDetailsEntity.getData().getService_info().getApplication_info().get(0).getAmount();
                            amount = amount.substring(0, amount.length() - 3);

                            tv_price_trusteeship_1.setText(amount + "元");

                        }

                    }


                    break;


                case "7": //已完成 未评价
                    linear_wait_sure.setVisibility(View.GONE);

                    tv_tip.setVisibility(View.VISIBLE);
                    tv_tip.setText("您已完成服务。");
                    view_finish.setVisibility(View.VISIBLE);
                    linear_anniu.setVisibility(View.VISIBLE);
                    tv_sure_complete.setVisibility(View.GONE);
                    linear_wait_evleant.setVisibility(View.VISIBLE);
                    linear_p_and_o.setVisibility(View.VISIBLE);

                    if (serviceDetailsEntity.getData().getService_info().getComplaint() != null) {
                        linear_complaint_result.setVisibility(View.VISIBLE);
                        tv_complaint_result.setText(serviceDetailsEntity.getData().getService_info().getComplaint().getHandle_opinion());
                    }


                    if (serviceDetailsEntity.getData().getService_info().getApplication_info() != null && serviceDetailsEntity.getData().getService_info().getApplication_info().size() != 0) {

                        tv_my_name_1.setText(serviceDetailsEntity.getData().getService_info().getApplication_info().get(0).getTruename());
                        tv_my_address_1.setText(serviceDetailsEntity.getData().getService_info().getApplication_info().get(0).getBase_region_id().getWhole_name());
                        tv_my_advantage_1.setText(serviceDetailsEntity.getData().getService_info().getApplication_info().get(0).getAdvantage());
                        tv_my_specialty_1.setText(serviceDetailsEntity.getData().getService_info().getApplication_info().get(0).getLawyer_secpical());
                        tv_time_1.setText(serviceDetailsEntity.getData().getService_info().getApplication_info().get(0).getCreated_at());


                        if (PrefsUtils.getString(PickUpCaseActivity.this, PrefsUtils.AVATAR) != null & PrefsUtils.getString(PickUpCaseActivity.this, PrefsUtils.AVATAR).length() != 0)
                            Glide.with(PickUpCaseActivity.this).load(PrefsUtils.getString(PickUpCaseActivity.this, PrefsUtils.AVATAR)).into(iv_avatar_1);

                        if (!(case_type.equals("婚姻保") || case_type.equals("债权保"))) {
                            rl_file.setVisibility(View.GONE);
                            line_black.setVisibility(View.GONE);
                            tv_file_type.setText("附件");
                            tv_no_legal_opinion.setText("您还未上传附件");
                        }

                        if (serviceDetailsEntity.getData().getService_info().getApplication_info().get(0).getWritten_documents() != null & serviceDetailsEntity.getData().getService_info().getApplication_info().get(0).getWritten_documents().size() != 0) {

                            FileAdapter fileAdapter = new FileAdapter(PickUpCaseActivity.this, serviceDetailsEntity.getData().getService_info().getApplication_info().get(0).getWritten_documents());
                            gv_file.setAdapter(fileAdapter);
                            final List<ServiceDetailsEntity.DataBean.ServiceInfoBean.ApplicationInfoBean.WrittenDocumentsBean> written_documents = serviceDetailsEntity.getData().getService_info().getApplication_info().get(0).getWritten_documents();
                            gv_file.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                                    String savePath = FileDownloadUtils.getDefaultSaveRootPath() + File.separator + "document" + File.separator + written_documents.get(i).getFilename();
                                    String url = written_documents.get(i).getFileurl_full();
                                    String type = written_documents.get(i).getExtension();

                                    downloadFile(savePath, url, type);

                                }
                            });
                        }else {
                            tv_no_file.setVisibility(View.VISIBLE);
                        }

                        if (serviceDetailsEntity.getData().getService_info().getApplication_info().get(0).getLegal_opinion() != null & serviceDetailsEntity.getData().getService_info().getApplication_info().get(0).getLegal_opinion().size() != 0) {

                            linear_legal_opinion.setVisibility(View.VISIBLE);
                            tv_legal_opinion_name.setText(serviceDetailsEntity.getData().getService_info().getApplication_info().get(0).getLegal_opinion().get(0).getFilename());
                            tv_legal_opinion_time.setText(serviceDetailsEntity.getData().getService_info().getApplication_info().get(0).getLegal_opinion().get(0).getCreated_time());

                            final String filename = serviceDetailsEntity.getData().getService_info().getApplication_info().get(0).getLegal_opinion().get(0).getFilename();
                            final String savePath = FileDownloadUtils.getDefaultSaveRootPath() + File.separator + "document" + File.separator + filename;
                            final String url = serviceDetailsEntity.getData().getService_info().getApplication_info().get(0).getLegal_opinion().get(0).getFileurl_full();
                            final String type = serviceDetailsEntity.getData().getService_info().getApplication_info().get(0).getLegal_opinion().get(0).getExtension();

                            linear_legal_opinion.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    downloadFile(savePath, url, type);
                                }
                            });

                        }

                        if (!(case_type.equals("婚姻保") || case_type.equals("债权保"))) {
                            rl_modify_price_1.setVisibility(View.VISIBLE);
                            String amount = serviceDetailsEntity.getData().getService_info().getApplication_info().get(0).getAmount();
                            amount = amount.substring(0, amount.length() - 3);

                            tv_price_trusteeship_1.setText(amount + "元");

                        }

                    }

                    break;

                case "8"://已完成 已评价
                    linear_wait_evleant.setVisibility(View.GONE);
                    linear_wait_sure.setVisibility(View.GONE);
                    tv_tip.setVisibility(View.VISIBLE);
                    tv_tip.setText("您已完成服务。");
                    view_finish.setVisibility(View.VISIBLE);
                    linear_anniu.setVisibility(View.VISIBLE);
                    tv_sure_complete.setVisibility(View.GONE);

                    linear_p_and_o.setVisibility(View.VISIBLE);


                    if (serviceDetailsEntity.getData().getService_info().getComplaint() != null) {
                        linear_complaint_result.setVisibility(View.VISIBLE);
                        tv_complaint_result.setText(serviceDetailsEntity.getData().getService_info().getComplaint().getHandle_opinion());
                    }


                    if (serviceDetailsEntity.getData().getService_info().getApplication_info() != null && serviceDetailsEntity.getData().getService_info().getApplication_info().size() != 0) {

                        tv_my_name_1.setText(serviceDetailsEntity.getData().getService_info().getApplication_info().get(0).getTruename());
                        tv_my_address_1.setText(serviceDetailsEntity.getData().getService_info().getApplication_info().get(0).getBase_region_id().getWhole_name());
                        tv_my_advantage_1.setText(serviceDetailsEntity.getData().getService_info().getApplication_info().get(0).getAdvantage());
                        tv_my_specialty_1.setText(serviceDetailsEntity.getData().getService_info().getApplication_info().get(0).getLawyer_secpical());
                        tv_time_1.setText(serviceDetailsEntity.getData().getService_info().getApplication_info().get(0).getCreated_at());


                        if (PrefsUtils.getString(PickUpCaseActivity.this, PrefsUtils.AVATAR) != null & PrefsUtils.getString(PickUpCaseActivity.this, PrefsUtils.AVATAR).length() != 0)
                            Glide.with(PickUpCaseActivity.this).load(PrefsUtils.getString(PickUpCaseActivity.this, PrefsUtils.AVATAR)).into(iv_avatar_1);

                        if (!(case_type.equals("婚姻保") || case_type.equals("债权保"))) {
                            rl_file.setVisibility(View.GONE);
                            line_black.setVisibility(View.GONE);
                            tv_file_type.setText("附件");
                            tv_no_legal_opinion.setText("您还未上传附件");
                        }

                        if (serviceDetailsEntity.getData().getService_info().getApplication_info().get(0).getWritten_documents() != null & serviceDetailsEntity.getData().getService_info().getApplication_info().get(0).getWritten_documents().size() != 0) {

                            FileAdapter fileAdapter = new FileAdapter(PickUpCaseActivity.this, serviceDetailsEntity.getData().getService_info().getApplication_info().get(0).getWritten_documents());
                            gv_file.setAdapter(fileAdapter);
                            final List<ServiceDetailsEntity.DataBean.ServiceInfoBean.ApplicationInfoBean.WrittenDocumentsBean> written_documents = serviceDetailsEntity.getData().getService_info().getApplication_info().get(0).getWritten_documents();
                            gv_file.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                                    String savePath = FileDownloadUtils.getDefaultSaveRootPath() + File.separator + "document" + File.separator + written_documents.get(i).getFilename();
                                    String url = written_documents.get(i).getFileurl_full();
                                    String type = written_documents.get(i).getExtension();

                                    downloadFile(savePath, url, type);

                                }
                            });
                        }else {
                            tv_no_file.setVisibility(View.VISIBLE);
                        }

                        if (serviceDetailsEntity.getData().getService_info().getApplication_info().get(0).getLegal_opinion() != null & serviceDetailsEntity.getData().getService_info().getApplication_info().get(0).getLegal_opinion().size() != 0) {

                            linear_legal_opinion.setVisibility(View.VISIBLE);
                            tv_legal_opinion_name.setText(serviceDetailsEntity.getData().getService_info().getApplication_info().get(0).getLegal_opinion().get(0).getFilename());
                            tv_legal_opinion_time.setText(serviceDetailsEntity.getData().getService_info().getApplication_info().get(0).getLegal_opinion().get(0).getCreated_time());
                            final String filename = serviceDetailsEntity.getData().getService_info().getApplication_info().get(0).getLegal_opinion().get(0).getFilename();
                            final String savePath = FileDownloadUtils.getDefaultSaveRootPath() + File.separator + "document" + File.separator + filename;
                            final String url = serviceDetailsEntity.getData().getService_info().getApplication_info().get(0).getLegal_opinion().get(0).getFileurl_full();
                            final String type = serviceDetailsEntity.getData().getService_info().getApplication_info().get(0).getLegal_opinion().get(0).getExtension();

                            linear_legal_opinion.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    downloadFile(savePath, url, type);
                                }
                            });

                        }

                        if (serviceDetailsEntity.getData().getService_info().getEvaluate() != null) {
                            linear_evelate.setVisibility(View.VISIBLE);
                            evelate_rateingbar.setRating(serviceDetailsEntity.getData().getService_info().getEvaluate().getStart_rate());
                            evelate_content.setText(serviceDetailsEntity.getData().getService_info().getEvaluate().getContent());
                        }


                        if (!(case_type.equals("婚姻保") || case_type.equals("债权保"))) {
                            rl_modify_price_1.setVisibility(View.VISIBLE);
                            String amount = serviceDetailsEntity.getData().getService_info().getApplication_info().get(0).getAmount();
                            amount = amount.substring(0, amount.length() - 3);

                            tv_price_trusteeship_1.setText(amount + "元");

                        }

                    }

                    break;

                case "9":
                    open = false;
                    lv_question.setVisibility(View.GONE);
                    linear_other.setVisibility(View.VISIBLE);
                    tv_msg.setText("用户已选择其他律师，服务正在进行中。");

                    break;

                case "10":

                    open = false;
                    lv_question.setVisibility(View.GONE);
                    linear_other.setVisibility(View.VISIBLE);
                    tv_msg.setText("用户已选择其他律师，服务已完成。");


                    break;

                case "11":

                    break;

                case "12":

                    view_applyed.setVisibility(View.GONE);
                    linear_anniu_applyed.setVisibility(View.GONE);
                    tv_tip.setVisibility(View.VISIBLE);
                    tv_tip.setText("用户已选择您但尚未托管酬金。");
                    linear_anniu.setVisibility(View.VISIBLE);
                    tv_sure_complete.setVisibility(View.GONE);
                    linear_p_and_o.setVisibility(View.VISIBLE);
                    tv_modify_price.setVisibility(View.GONE);

                    open = false;
                    lv_question.setVisibility(View.GONE);
                    view_apply.setVisibility(View.GONE);

                    view_applyed.setVisibility(View.VISIBLE);
                    linear_wait_money.setVisibility(View.VISIBLE);

                    if (serviceDetailsEntity.getData().getService_info().getApplication_info() != null && serviceDetailsEntity.getData().getService_info().getApplication_info().size() != 0) {

                        tv_my_name.setText(serviceDetailsEntity.getData().getService_info().getApplication_info().get(0).getTruename());
                        tv_my_address.setText(serviceDetailsEntity.getData().getService_info().getApplication_info().get(0).getBase_region_id().getWhole_name());
                        tv_my_advantage.setText(serviceDetailsEntity.getData().getService_info().getApplication_info().get(0).getAdvantage());
                        tv_my_specialty.setText(serviceDetailsEntity.getData().getService_info().getApplication_info().get(0).getLawyer_secpical());
                        tv_time.setText(serviceDetailsEntity.getData().getService_info().getApplication_info().get(0).getCreated_at());

                        if (PrefsUtils.getString(PickUpCaseActivity.this, PrefsUtils.AVATAR) != null & PrefsUtils.getString(PickUpCaseActivity.this, PrefsUtils.AVATAR).length() != 0)
                            Glide.with(PickUpCaseActivity.this).load(PrefsUtils.getString(PickUpCaseActivity.this, PrefsUtils.AVATAR)).into(iv_avatar);

                        if (!(case_type.equals("婚姻保") || case_type.equals("债权保"))) {
                            rl_file.setVisibility(View.GONE);
                            line_black.setVisibility(View.GONE);
                            tv_file_type.setText("附件");
                            tv_no_legal_opinion.setText("您还未上传附件");
                        }

                        if (!(case_type.equals("婚姻保") || case_type.equals("债权保"))) {

                            rl_modify_price_1.setVisibility(View.VISIBLE);
                            String amount = serviceDetailsEntity.getData().getService_info().getApplication_info().get(0).getAmount();
                            amount = amount.substring(0, amount.length() - 3);
                            tv_price_trusteeship_1.setText(amount + "元");
                        }
                    }

                    break;

                case "16":

                    view_applyed.setVisibility(View.GONE);
                    linear_anniu_applyed.setVisibility(View.GONE);

                    linear_rufuse.setVisibility(View.VISIBLE);
                    tv_rufuse_reason.setText("原因：" + serviceDetailsEntity.getData().getService_info().getReason());

                    break;

                case "17":


                    view_applyed.setVisibility(View.GONE);
                    linear_anniu_applyed.setVisibility(View.GONE);

                    linear_other.setVisibility(View.VISIBLE);
                    tv_msg.setText("用户已取消。");

                    break;

            }

        }

    }

    private void downloadFile(final String savePath, String url, final String type) {
        ToastUtils.getUtils(PickUpCaseActivity.this).show("文件加载中");
        FileDownloader.getImpl().create(url)
                .setPath(savePath)
                .setListener(new FileDownloadListener() {
                    @Override
                    protected void pending(BaseDownloadTask task, int soFarBytes, int totalBytes) {

                    }

                    @Override
                    protected void connected(BaseDownloadTask task, String etag, boolean isContinue, int soFarBytes, int totalBytes) {
                    }

                    @Override
                    protected void progress(BaseDownloadTask task, int soFarBytes, int totalBytes) {

                    }

                    @Override
                    protected void blockComplete(BaseDownloadTask task) {
//                        ToastUtils.getUtils(PickUpCaseActivity.this).show("下载已完222222222222成");
                    }

                    @Override
                    protected void retry(final BaseDownloadTask task, final Throwable ex, final int retryingTimes, final int soFarBytes) {
                    }

                    @Override
                    protected void completed(BaseDownloadTask task) {
                        startActivity(openFile(savePath, type));

                    }

                    @Override
                    protected void paused(BaseDownloadTask task, int soFarBytes, int totalBytes) {
                    }

                    @Override
                    protected void error(BaseDownloadTask task, Throwable e) {
                    }

                    @Override
                    protected void warn(BaseDownloadTask task) {
                    }
                }).start();
    }


    private void entrust(String id) {
        RequestParams params = new RequestParams();
        params.addHeader("Authorization", PrefsUtils.getString(PickUpCaseActivity.this, PrefsUtils.AUTHORIZATION));
        params.addQueryStringParameter("id", id);

        httpDataUtils.sendPost(MyData.EMPLOYLAYER_CHOOSE_LAYER, params);
        httpDataUtils.setSucessBack(new HttpDataUtils.SucessBack() {
            @Override
            public void sucess(ResponseInfo<String> arg0) {

                try {
                    JSONObject object = new JSONObject(arg0.result);
                    String code = JSONTool.getString(object, "code");
                    String msg = JSONTool.getString(object, "message");
                    if (code.equals("0")) { // 成功
                        ToastUtils.getUtils(PickUpCaseActivity.this).show("委托成功");

                        JSONObject data = JSONTool.getJsonObject(object, "data");
                        String order_no = JSONTool.getString(data, "order_no");


                        linear_coll_publish_select_lawyer.setVisibility(View.GONE);
                        refresh = true;
                        startActivityForResult(new Intent(PickUpCaseActivity.this, NewPayActivity.class).putExtra("order_no", order_no).putExtra("money", amount_price), 252);


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
//                ToastUtils.getUtils(PickUpCaseActivity.this).show("接口报错");
            }
        });
    }

    //获取问卷信息

    private void getdata() {

        RequestParams params = new RequestParams();
        params.addHeader("Authorization", PrefsUtils.getString(PickUpCaseActivity.this, PrefsUtils.AUTHORIZATION));

        httpDataUtils.sendGet(MyData.ORDER_GET_QUESTION + getIntent().getStringExtra("order_no"), params);
        httpDataUtils.setSucessBack(new HttpDataUtils.SucessBack() {
            @Override
            public void sucess(ResponseInfo<String> arg0) {

                try {
                    JSONObject object = new JSONObject(arg0.result);
                    String code = JSONTool.getString(object, "code");
                    String msg = JSONTool.getString(object, "message");
                    String data = JSONTool.getString(object, "data");
                    if (code.equals("0")) { // 成功

                        Gson gson = new Gson();
                        Questionnaire questionnaire = gson.fromJson(arg0.result, Questionnaire.class);
                        list.clear();
                        list.addAll(questionnaire.getData());
                        questionnaireAdapter.notifyDataSetChanged();

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

    String category;

    private static final int CALL_PHONE_REQUEST_CODE = 111;

    @OnClick({R.id.ic_back, R.id.tv_o_c, R.id.tv_o_c_1, R.id.tv_o_c_2,
            R.id.btn_apply, R.id.tv_sure_complete, R.id.tv_tele, R.id.tv_connect,
            R.id.tv_upload_1, R.id.tv_upload_2, R.id.tv_order, R.id.tv_call_service,
            R.id.tv_modify_price, R.id.tv_sure_trusteeship, R.id.tv_go_to_evaluation,
            R.id.tv_sure_complete_user, R.id.btn_go_to_pay,
            R.id.btn_connect, R.id.tv_refuse, R.id.tv_connect_trusteeship, R.id.iv_tip_web})
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.iv_tip_web:

                startActivity(new Intent().putExtra("title", "帮助")
                        .putExtra("url", MyData.MARRYHINT)
                        .setClass(PickUpCaseActivity.this, WebviewActivity.class));


                break;

            case R.id.tv_refuse:

                final DialogCooperateApplyCollaboration collaboration = new DialogCooperateApplyCollaboration();
                collaboration.showDialog(this, new Connect() {
                    @Override
                    public void text(String text) {

                        if (text.length() < 10) {
                            ToastUtils.getUtils(PickUpCaseActivity.this).show("请输入原因10-240个字");
                            return;
                        } else {
                            collaboration.dismissDialog();
                            refuse(text);

                        }

                    }
                });

                break;

            case R.id.btn_connect:

                Intent intent = new Intent();
                intent.putExtra(Extras.EXTRA_ACCOUNT, publisher_cloud_ID);
                intent.putExtra(Extras.EXTRA_CUSTOMIZATION, SessionHelper.getP2pCustomization());
                intent.setClass(PickUpCaseActivity.this, MyNewMessageActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);

                break;

            case R.id.tv_connect_trusteeship:

                Intent i = new Intent();
                i.putExtra(Extras.EXTRA_ACCOUNT, lawyer_ID);
                i.putExtra(Extras.EXTRA_CUSTOMIZATION, SessionHelper.getP2pCustomization());
                i.setClass(PickUpCaseActivity.this, MyNewMessageActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(i);

                break;

            case R.id.btn_go_to_pay:
                refresh = true;
                startActivityForResult(new Intent(PickUpCaseActivity.this, NewPayActivity.class).putExtra("order_no", order_no), 252);

                break;
            case R.id.tv_sure_complete_user:


                DialogCooperateSureComplete sure1 = new DialogCooperateSureComplete();
                sure1.showDialog(this, new DialogCooperateAgreeGiveup.Click() {
                    @Override
                    public void click() {
                        // TODO Auto-generated method stub
                        userSureComplete();
                    }
                });


                break;

            case R.id.tv_go_to_evaluation:

                startActivity(new Intent(PickUpCaseActivity.this, EvaluationActivity.class).putExtra("application_id", application_id));

                break;

            case R.id.tv_sure_trusteeship:

//                DialogCooperateSurePay dialogCooperateSurePay = new DialogCooperateSurePay();
//                dialogCooperateSurePay.showDialog(this, new DialogCooperateAgreeGiveup.Click() {
//                    @Override
//                    public void click() {
//                        // TODO Auto-generated method stub
                refresh = true;
                startActivityForResult(new Intent(PickUpCaseActivity.this, NewPayActivity.class).putExtra("order_no", private_order_no).putExtra("money", amount_price), 252);
//                    }
//                });


                break;

            case R.id.tv_modify_price:

                DialogCooperateAddmoney addmoney = new DialogCooperateAddmoney();
                addmoney.showDialog(this);
                addmoney.setCont(new DialogCooperateAddmoney.Cont() {
                    @Override
                    public void Money(String money) {

                        if (money.length() != 0) {
//                            int a = Integer.parseInt(money);
                            modifyPrice(money);
                        }

                    }
                });


                break;

            case R.id.tv_call_service:


//                if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

                    if (ContextCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE)
                            != PackageManager.PERMISSION_GRANTED) {

                        ActivityCompat.requestPermissions(PickUpCaseActivity.this, new String[]{Manifest.permission.CALL_PHONE},
                                CALL_PHONE_REQUEST_CODE);
                        return;
                    } else {
                        Intent intent2 = new Intent(Intent.ACTION_CALL);
                        Uri data = Uri.parse("tel:" + MyData.CONSUMER_HOTLINE);
                        intent2.setData(data);
                        startActivity(intent2);

                    }
//                } else {
//                    Intent intent2 = new Intent(Intent.ACTION_CALL);
//                    Uri data = Uri.parse("tel:" + MyData.CONSUMER_HOTLINE);
//                    intent2.setData(data);
//                   startActivity(intent2);
//                }

                break;
            case R.id.tv_order:

                refresh = true;

                startActivity(new Intent(PickUpCaseActivity.this, CollaborativeOrderDetailsActivity.class).putExtra("private_order_no", private_order_no));

                break;
            case R.id.tv_upload_1:
                category = "written";
                UploadPopupWindow uploadPopupWindow = new UploadPopupWindow(PickUpCaseActivity.this, 5);
                uploadPopupWindow.showPopupWindow(view);
                break;
            case R.id.tv_upload_2:
                category = "legal";
                UploadPopupWindow uploadPopupWindow1 = new UploadPopupWindow(PickUpCaseActivity.this, 1);
                uploadPopupWindow1.showPopupWindow(view);
                break;

            case R.id.tv_connect:

                if (can_connect) {
                    Intent intent1 = new Intent();
                    intent1.putExtra(Extras.EXTRA_ACCOUNT, publisher_cloud_ID);
                    intent1.putExtra(Extras.EXTRA_CUSTOMIZATION, SessionHelper.getP2pCustomization());
                    intent1.setClass(PickUpCaseActivity.this, MyNewMessageActivity.class);
                    intent1.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    startActivity(intent1);
                } else {

                    ToastUtils.getUtils(PickUpCaseActivity.this).show("订单已完成，不能联系");
                }


                break;

            case R.id.tv_tele:

                if (ContextCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE)
                        != PackageManager.PERMISSION_GRANTED) {
                    //申请获取联系人权限
                    ActivityCompat.requestPermissions(PickUpCaseActivity.this, new String[]{Manifest.permission.CALL_PHONE},
                            CALL_PHONE_REQUEST_CODE);
                    return;
                } else {

                    Intent intent2 = new Intent(Intent.ACTION_CALL);
                    Uri data = Uri.parse("tel:" + to);
                    intent2.setData(data);
                    startActivity(intent2);
                }

                break;


            case R.id.tv_sure_complete:

                DialogCooperateSureComplete sure2 = new DialogCooperateSureComplete();
                sure2.showDialog(this, new DialogCooperateAgreeGiveup.Click() {
                    @Override
                    public void click() {

                        submitCompleted();
                    }
                });


                break;

            case R.id.btn_apply:


                if (case_type.equals("婚姻保") || case_type.equals("债权保")) {
                    if (tv_advantage.getText().toString().length() < 10) {
                        ToastUtils.getUtils(PickUpCaseActivity.this).show("请用10-200个字描述你的优势，供用户参考。");
                        return;
                    }

                    goToApply();

                } else {

                    if (et_advantage_1.getText().toString().length() < 10) {
                        ToastUtils.getUtils(PickUpCaseActivity.this).show("请用10-200个字描述你的优势，供用户参考。");
                        return;
                    }
                    if (et_intention_price.getText().toString().length() == 0) {
                        ToastUtils.getUtils(PickUpCaseActivity.this).show("请填写您的报价。");
                        return;
                    }
                    goToApplyPQ();
                }

                break;

            case R.id.ic_back:
                finish();
                break;
            case R.id.tv_o_c:

                if (open) {

                    Drawable rightDrawable = context.getResources().getDrawable(R.drawable.icon_down);
                    rightDrawable.setBounds(0, 0, rightDrawable.getMinimumWidth(), rightDrawable.getMinimumHeight());
                    tv_o_c.setCompoundDrawables(null, null, rightDrawable, null);

                    lv_question.setVisibility(View.GONE);
                    tv_o_c.setText("展开");

                } else {

                    Drawable rightDrawable = context.getResources().getDrawable(R.drawable.icon_up);
                    rightDrawable.setBounds(0, 0, rightDrawable.getMinimumWidth(), rightDrawable.getMinimumHeight());
                    tv_o_c.setCompoundDrawables(null, null, rightDrawable, null);

                    lv_question.setVisibility(View.VISIBLE);
                    tv_o_c.setText("收起");
                }
                open = !open;
                break;


            case R.id.tv_o_c_1:

                if (apply_open) {


                    lv_question.setVisibility(View.GONE);
                    tv_o_c_1.setText("展开");

                    Drawable rightDrawable = context.getResources().getDrawable(R.drawable.sort_down);
                    rightDrawable.setBounds(0, 0, rightDrawable.getMinimumWidth(), rightDrawable.getMinimumHeight());
                    tv_o_c_1.setCompoundDrawables(null, null, rightDrawable, null);


                } else {

                    lv_question.setVisibility(View.VISIBLE);
                    tv_o_c_1.setText("收起");

                    Drawable rightDrawable = context.getResources().getDrawable(R.drawable.icon_arrow_up);
                    rightDrawable.setBounds(0, 0, rightDrawable.getMinimumWidth(), rightDrawable.getMinimumHeight());
                    tv_o_c_1.setCompoundDrawables(null, null, rightDrawable, null);


                }
                apply_open = !apply_open;
                break;

            case R.id.tv_o_c_2:

                if (apply_open) {


                    Drawable rightDrawable = context.getResources().getDrawable(R.drawable.sort_down);
                    rightDrawable.setBounds(0, 0, rightDrawable.getMinimumWidth(), rightDrawable.getMinimumHeight());
                    tv_o_c_2.setCompoundDrawables(null, null, rightDrawable, null);

                    linear_my_apply_1.setVisibility(View.GONE);
                    tv_o_c_2.setText("展开");

                } else {

                    Drawable rightDrawable = context.getResources().getDrawable(R.drawable.icon_arrow_up);
                    rightDrawable.setBounds(0, 0, rightDrawable.getMinimumWidth(), rightDrawable.getMinimumHeight());
                    tv_o_c_2.setCompoundDrawables(null, null, rightDrawable, null);

                    linear_my_apply_1.setVisibility(View.VISIBLE);
                    tv_o_c_2.setText("收起");
                }
                apply_open = !apply_open;
                break;
        }
    }

    private void refuse(String text) {
        RequestParams params = new RequestParams();
        params.addHeader("Authorization", PrefsUtils.getString(PickUpCaseActivity.this, PrefsUtils.AUTHORIZATION));
        params.addQueryStringParameter("reason", text);
        params.addQueryStringParameter("order_no", order_no);
        httpDataUtils.sendProgressPost(MyData.EMPLOYLAYER_LAYER_REFUSE, params);
        httpDataUtils.setSucessBack(new HttpDataUtils.SucessBack() {
            @Override
            public void sucess(ResponseInfo<String> arg0) {

                try {
                    JSONObject object = new JSONObject(arg0.result);
                    String code = JSONTool.getString(object, "code");
                    String msg = JSONTool.getString(object, "message");
                    if (code.equals("0")) { // 成功
                        ToastUtils.getUtils(PickUpCaseActivity.this).show("已拒绝");


                        view_apply.setVisibility(View.GONE);
                        ptrfs.setRefreshing();
                        getDetail();
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

    //确认聘请律师完成  聘请律师用户确认完成订单

    private void userSureComplete() {
        RequestParams params = new RequestParams();
        params.addHeader("Authorization", PrefsUtils.getString(PickUpCaseActivity.this, PrefsUtils.AUTHORIZATION));
        params.addQueryStringParameter("employ_layer_id", employ_layer_id + "");
//        params.addQueryStringParameter("amount", money);
        httpDataUtils.sendProgressPost(MyData.EMPLOYLAYER_CONFIRM_SUCCESS, params);
        httpDataUtils.setSucessBack(new HttpDataUtils.SucessBack() {
            @Override
            public void sucess(ResponseInfo<String> arg0) {

                try {
                    JSONObject object = new JSONObject(arg0.result);
                    String code = JSONTool.getString(object, "code");
                    String msg = JSONTool.getString(object, "message");
                    if (code.equals("0")) { // 成功
                        ToastUtils.getUtils(PickUpCaseActivity.this).show("修改成功");
                        ptrfs.setRefreshing();
                        getDetail();
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


    //修改报价
    private void modifyPrice(String money) {

        RequestParams params = new RequestParams();
        params.addHeader("Authorization", PrefsUtils.getString(PickUpCaseActivity.this, PrefsUtils.AUTHORIZATION));
        params.addQueryStringParameter("order_no", getIntent().getStringExtra("order_no"));
        params.addQueryStringParameter("amount", money);
        httpDataUtils.sendProgressPost(MyData.EMPLOYLAYER_UPDATE_AMOUNT, params);
        httpDataUtils.setSucessBack(new HttpDataUtils.SucessBack() {
            @Override
            public void sucess(ResponseInfo<String> arg0) {

                try {
                    JSONObject object = new JSONObject(arg0.result);
                    String code = JSONTool.getString(object, "code");
                    String msg = JSONTool.getString(object, "message");
                    if (code.equals("0")) { // 成功
                        ToastUtils.getUtils(PickUpCaseActivity.this).show("修改成功");
                        ptrfs.setRefreshing();
                        getDetail();
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

    //律师端确认完成

    private void submitCompleted() {

        RequestParams params = new RequestParams();
        params.addHeader("Authorization", PrefsUtils.getString(PickUpCaseActivity.this, PrefsUtils.AUTHORIZATION));
        params.addQueryStringParameter("order_no", getIntent().getStringExtra("order_no"));
//        params.addQueryStringParameter("advantage",tv_advantage.getText().toString());
        httpDataUtils.sendProgressPut(MyData.ORDER_LAWYER_CONFIRMATION, params);
        httpDataUtils.setSucessBack(new HttpDataUtils.SucessBack() {
            @Override
            public void sucess(ResponseInfo<String> arg0) {

                try {
                    JSONObject object = new JSONObject(arg0.result);
                    String code = JSONTool.getString(object, "code");
                    String msg = JSONTool.getString(object, "message");
                    if (code.equals("0")) { // 成功
                        ToastUtils.getUtils(PickUpCaseActivity.this).show("提交成功");
                        ptrfs.setRefreshing();
                        first = false;
                        getDetail();
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

    private void goToApply() {
        RequestParams params = new RequestParams();
        params.addHeader("Authorization", PrefsUtils.getString(PickUpCaseActivity.this, PrefsUtils.AUTHORIZATION));
        params.addQueryStringParameter("order_no", getIntent().getStringExtra("order_no"));
        params.addQueryStringParameter("advantage", tv_advantage.getText().toString());
        httpDataUtils.sendPost(MyData.ORDER_LAWYER_APPLY, params);
        httpDataUtils.setSucessBack(new HttpDataUtils.SucessBack() {
            @Override
            public void sucess(ResponseInfo<String> arg0) {

                try {
                    JSONObject object = new JSONObject(arg0.result);
                    String code = JSONTool.getString(object, "code");
                    String msg = JSONTool.getString(object, "message");
                    if (code.equals("0")) { // 成功
                        ToastUtils.getUtils(PickUpCaseActivity.this).show("申请成功");
                        ptrfs.setRefreshing();
                        first = false;
                        getDetail();
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

    private void goToApplyPQ() {


        RequestParams params = new RequestParams();
        params.addHeader("Authorization", PrefsUtils.getString(PickUpCaseActivity.this, PrefsUtils.AUTHORIZATION));
        params.addQueryStringParameter("order_no", getIntent().getStringExtra("order_no"));
        params.addQueryStringParameter("advantage", et_advantage_1.getText().toString());
        params.addQueryStringParameter("amount", et_intention_price.getText().toString());
        httpDataUtils.sendPost(MyData.EMPLOYLAYER_LAYER_APPLY, params);
        httpDataUtils.setSucessBack(new HttpDataUtils.SucessBack() {
            @Override
            public void sucess(ResponseInfo<String> arg0) {

                try {
                    JSONObject object = new JSONObject(arg0.result);
                    String code = JSONTool.getString(object, "code");
                    String msg = JSONTool.getString(object, "message");
                    if (code.equals("0")) { // 成功
                        ToastUtils.getUtils(PickUpCaseActivity.this).show("申请成功");
                        ptrfs.setRefreshing();
                        getDetail();
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 250 && data != null) {
            String path = data.getStringExtra(FileBrowserActivity.EXTRA_DATA_PATH);
            LogUtils.logE(path);
//            ToastUtils.getUtils(PickUpCaseActivity.this).show("已选择文件" + path);
            uploadLocalFile(path);
        }

        if (requestCode == 251 && data != null) {
            String path = data.getStringExtra("ids");
            LogUtils.logE(path);
//            ToastUtils.getUtils(PickUpCaseActivity.this).show("已选择文件" + path);
            uploadCloudFile(path);
        }

//        if (requestCode == 252 && data != null) {
//            linear_waiting_to_pay_the_deposit.setVisibility(View.GONE);
//            ptrfs.setRefreshing();
//            getDetail();
//        }

        if (requestCode == 253 && data != null) {
            String path = data.getStringArrayListExtra(MultiImageSelector.EXTRA_RESULT).get(0);
            LogUtils.logE(path);
            uploadLocalFile(path);
        }

    }


    //上传本地文件
    private void uploadLocalFile(String path) {

        RequestParams params = new RequestParams();
        params.addHeader("Authorization", PrefsUtils.getString(PickUpCaseActivity.this, PrefsUtils.AUTHORIZATION));
        params.addBodyParameter("id", file_id + "");

        if (category.equals("written")) {
            params.addBodyParameter("written_documents[]", new File(path));
        } else {
            params.addBodyParameter("legal_opinion", new File(path));
        }

        params.addQueryStringParameter("category", category);
        httpDataUtils.sendProgressPost(MyData.COLL_UPLOAD_ATTACHMENT, params);
        httpDataUtils.setSucessBack(new HttpDataUtils.SucessBack() {
            @Override
            public void sucess(ResponseInfo<String> arg0) {

                try {
                    JSONObject object = new JSONObject(arg0.result);
                    String code = JSONTool.getString(object, "code");
                    String msg = JSONTool.getString(object, "message");
                    if (code.equals("0")) { // 成功
//                        ToastUtils.getUtils(getApplicationContext()).show("成功1216");
//                        Gson gson =new Gson();
//                        Questionnaire questionnaire = gson.fromJson(object.toString(),Questionnaire.class);
//                        list.clear();
//                        list.addAll(questionnaire.getData());
//                        questionnaireAdapter.notifyDataSetChanged();
                        ptrfs.setRefreshing();
                        getDetail();

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

    //上传云盘文件
    private void uploadCloudFile(String path) {

        RequestParams params = new RequestParams();
        params.addHeader("Authorization", PrefsUtils.getString(PickUpCaseActivity.this, PrefsUtils.AUTHORIZATION));
        params.addBodyParameter("id", path);
        params.addBodyParameter("application_id", file_id + "");
        params.addQueryStringParameter("category", category);
        httpDataUtils.sendProgressPost(MyData.COLL_UPLOAD_ATTACHMENT_FROM_DISK, params);
        httpDataUtils.setSucessBack(new HttpDataUtils.SucessBack() {
            @Override
            public void sucess(ResponseInfo<String> arg0) {

                try {
                    JSONObject object = new JSONObject(arg0.result);
                    String code = JSONTool.getString(object, "code");
                    String msg = JSONTool.getString(object, "message");

                    if (code.equals("0")) { // 成功
//                        ToastUtils.getUtils(getApplicationContext()).show("成功1216");
//                        Gson gson =new Gson();
//                        Questionnaire questionnaire = gson.fromJson(object.toString(),Questionnaire.class);
//                        list.clear();
//                        list.addAll(questionnaire.getData());
//                        questionnaireAdapter.notifyDataSetChanged();
                        ptrfs.setRefreshing();
                        getDetail();

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

    //加载附件

    private void attachmentData(List<ServiceDetailsEntity.DataBean.FileDetailInfoBean> list) {
        ll_album.removeAllViews();
        if (list != null && list.size() != 0) {

            ll_attachment.setVisibility(View.VISIBLE);

            final ArrayList<String> urlList = new ArrayList<String>();
            for (int i = 0; i < list.size(); i++) {
                urlList.add(list.get(i).getFileurl_full());
            }
            for (int i = 0; i < list.size(); i++) {
                View view = getLayoutInflater().inflate(R.layout.item_iamgeview, null);
                ImageView iv_image = (ImageView) view.findViewById(R.id.iv_image);
                ImageLoaderUtils.initUtils().display(list.get(i).getFileurl_full(), iv_image);
                final int position = i;
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(PickUpCaseActivity.this, ViewPagerExampleActivity.class);
                        intent.putStringArrayListExtra(ViewPagerExampleActivity.PHOTO_URL_LIST_INTENT, urlList).putExtra("position", position);
                        startActivity(intent);
                    }
                });
                ll_album.addView(view);
            }
        } else {
            ll_attachment.setVisibility(View.GONE);
        }
    }


    @Override
    protected void onResume() {

        if (refresh) {
            getDetail();
            refresh = false;
        } else {
            refresh = false;
        }
        super.onResume();
    }

    boolean refresh = false;

}
