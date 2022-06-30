package com.yhy.hzzll.home.activity.collaborate;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.umeng.socialize.Config;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.yhy.hzzll.R;
import com.yhy.hzzll.adapter.LawyerCollaboraDetailsAdapter;
import com.yhy.hzzll.entity.AllStatusEntity;
import com.yhy.hzzll.entity.CooperateInfoEntity;
import com.yhy.hzzll.entity.CooperateInfoEntity.collaboration.list;
import com.yhy.hzzll.entity.Footprint;
import com.yhy.hzzll.entity.MessageListEntity;
import com.yhy.hzzll.entity.OtherUserEntity;
import com.yhy.hzzll.framework.HzApplication;
import com.yhy.hzzll.framework.MyData;
import com.yhy.hzzll.home.activity.ViewPagerExampleActivity;
import com.yhy.hzzll.home.activity.lawyeroffice.LawyerOfficeActivity;
import com.yhy.hzzll.interfacelib.Connect;
import com.yhy.hzzll.message.MessageDetailChatActivity;
import com.yhy.hzzll.mian.activity.BaseActivity;
import com.yhy.hzzll.mian.activity.LoginActivity;
import com.yhy.hzzll.my.activity.AuthenticatRealLawActivity;
import com.yhy.hzzll.utils.DateUtils;
import com.yhy.hzzll.utils.HttpDataUtils;
import com.yhy.hzzll.utils.HttpDataUtils.FailBack;
import com.yhy.hzzll.utils.ImageLoaderUtils;
import com.yhy.hzzll.utils.PrefsUtils;
import com.yhy.hzzll.utils.StringUtil;
import com.yhy.hzzll.utils.ToastUtils;
import com.yhy.hzzll.view.DialogCooperateAddmoney;
import com.yhy.hzzll.view.DialogCooperateAddtime;
import com.yhy.hzzll.view.DialogCooperateAgreeGiveup;
import com.yhy.hzzll.view.DialogCooperateAnonymous;
import com.yhy.hzzll.view.DialogCooperateApplyCollaboration;
import com.yhy.hzzll.view.DialogCooperateApplyServiceIn;
import com.yhy.hzzll.view.DialogCooperateContinueCollaborate;
import com.yhy.hzzll.view.DialogCooperateEnter;
import com.yhy.hzzll.view.DialogCooperateReload;
import com.yhy.hzzll.view.DialogLoading;
import com.yhy.hzzll.view.SharePopupWindow;
import com.yhy.hzzll.widget.CustomListview;
import com.yhy.hzzll.widget.TimeTextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 律师协作 // 我要聘律师 ----详情
 *
 * @author WangYang
 */
@SuppressLint("ResourceAsColor")
public class LawyerCollaboratDetailsActivity extends BaseActivity {

    public static LawyerCollaboratDetailsActivity lawyerCollaboratDetailsActivity = null;

    @ViewInject(R.id.lv_lawyer)
    private CustomListview lv_lawyer;

    private List<AllStatusEntity> publishStatusList;
    /**
     * 协作者的状态文字
     */
    private List<AllStatusEntity> collaboratorStatusList;
    /**
     * 其他人员状态文字
     */
    private List<AllStatusEntity> otherStatusList;

    private List<AllStatusEntity> publishList = new ArrayList<AllStatusEntity>();
    /**
     * 详情ID
     */
    private String id = "";

    /**
     * 剩余时间
     */
    @ViewInject(R.id.tv_date)
    private TimeTextView tv_date;

    /**
     * 协作title文字
     */
    @ViewInject(R.id.tv_text)
    private TextView tv_text;

    /**
     * 底部按钮的布局
     */
    @ViewInject(R.id.ll_btn_layout)
    private LinearLayout ll_btn_layout;

    /**
     * 修改
     */
    @ViewInject(R.id.tv_modificat)
    private TextView tv_modificat;

    /**
     * 协作标题
     */
    @ViewInject(R.id.tv_name)
    private TextView tv_name;
    /**
     * 创建日期
     */
    @ViewInject(R.id.tv_regdate)
    private TextView tv_regdate;
    /**
     * 订单号
     */
    @ViewInject(R.id.tv_orderno)
    private TextView tv_orderno;
    /**
     * 酬金
     */
    @ViewInject(R.id.tv_money)
    private TextView tv_money;
    /**
     * 协作类型
     */
    @ViewInject(R.id.tv_type)
    private TextView tv_type;

    /**
     * 协作地区
     */
    @ViewInject(R.id.tv_address)
    private TextView tv_address;

    /**
     * 完成日期
     */
    @ViewInject(R.id.tv_compledate)
    private TextView tv_compledate;

    /**
     * 协作详情
     */
    @ViewInject(R.id.tv_details)
    private TextView tv_details;
    /**
     * 查看全部按钮
     */
    @ViewInject(R.id.head_tv_checkall)
    private TextView head_tv_checkall;

    /**
     * 查看全部开关
     */
    private boolean checkALL;

    /**
     * 附件图片加载容器
     */
    @ViewInject(R.id.ll_album)
    private LinearLayout ll_album;

    /**
     * 申请协作的人布局
     */
    @ViewInject(R.id.ll_applicat)
    private LinearLayout ll_applicat;

    /**
     * 收藏浏览人数
     */
    @ViewInject(R.id.tv_collect)
    private TextView tv_collect;

    /**
     * 申请人数
     */
    @ViewInject(R.id.tv_applicat)
    private TextView tv_applicat;

    /**
     * 客服处理结果
     */
    @ViewInject(R.id.tv_service_result)
    private TextView tv_service_result;

    /**
     * 发布方姓名
     */
    @ViewInject(R.id.tv_publisher)
    private TextView tv_publisher;

    /**
     * 发布方电话
     */
    @ViewInject(R.id.tv_publish_phone)
    private TextView tv_publish_phone;

    /**
     * 附件的布局
     */
    @ViewInject(R.id.ll_attachment)
    private LinearLayout ll_attachment;

    /**
     * 申请客服介入
     */
    @ViewInject(R.id.tv_servicein)
    private TextView tv_servicein;
    /**
     * 托管
     */
    @ViewInject(R.id.tv_hosting)
    private TextView tv_hosting;

    /**
     * 数据实体
     */
    private CooperateInfoEntity entity;

    @ViewInject(R.id.psc_lawyer)
    private PullToRefreshScrollView psc_lawyer;

    /**
     * 收藏
     */
    @ViewInject(R.id.tv_collects)
    private TextView tv_collects;

    /**
     * 律师是否申请了协作
     */
    private int userType = 0;
    private int giveUp = 0;
    private int isService = 0;
    private int hanlderid = 0;
    /**
     * 自己是否被选中为协作律师
     */
    private int cooperate = 0;

    private DialogLoading loading;

    private OtherUserEntity otherUserEntity;

    @Override
    protected void onCreate(Bundle arg0) {
        // TODO Auto-generated method stub
        setContentView(R.layout.activity_lawyer_collaborationdetails);
        super.onCreate(arg0);
        lawyerCollaboratDetailsActivity = this;
        loading = new DialogLoading();
        loading.showDialog(this);
        getAllStatus();

        if (getIntent() != null) {
            if (getIntent().getBooleanExtra("insert",true)) {
                Footprint footprint = new Footprint(getIntent().getStringExtra("id"), DateUtils.getCurrentDate(),getIntent().getStringExtra("title"),"律师协作");
                HzApplication.getDaoInstant().getFootprintDao().insert(footprint);
            }
        }

    }

    @Override
    protected void onRestart() {
        dataInit();
        super.onRestart();
    }

    /**
     * 收藏or 取消收藏
     */
    private void setCollect() {
        RequestParams params = new RequestParams();
        params.addHeader("Authorization", PrefsUtils.getString(context, PrefsUtils.ACCESSKEY));
        params.addQueryStringParameter("cate", "Lawyercoop");
        params.addQueryStringParameter("nid", id);
        params.addQueryStringParameter("url", "/Lawyercoop/detail/id/" + id + ".html");

        httpDataUtils.sendProgressGet(MyData.COLLECT_OR_UNCOLLECT, params);
        httpDataUtils.setSucessBack(new HttpDataUtils.SucessBack() {
            @Override
            public void sucess(ResponseInfo<String> arg0) {
                try {
                    JSONObject object = new JSONObject(arg0.result);
                    String code = object.optString("code");
                    String msg = object.optString("msg");
                    if (code.equals("000000")) {
                        if (msg.equals("添加收藏成功！")) {
                            tv_collects.setText("已收藏");
                        } else {
                            tv_collects.setText("收藏");
                        }
                    } else {
                        httpDataUtils.showMsg(arg0.result);
                    }
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });

        httpDataUtils.setFailBack(new FailBack() {

            @Override
            public void fail(String msg) {
                ToastUtils.getUtils(context).show(msg);
            }
        });
    }

    private void getAllStatus() {
        publishStatusList = new ArrayList<AllStatusEntity>();
        collaboratorStatusList = new ArrayList<AllStatusEntity>();
        otherStatusList = new ArrayList<AllStatusEntity>();
        publishStatusList.clear();
        collaboratorStatusList.clear();
        otherStatusList.clear();
        RequestParams params = new RequestParams();
        httpDataUtils.sendGet(MyData.GETALLSTATUS, params);
        httpDataUtils.setSucessBack(new HttpDataUtils.SucessBack() {
            @Override
            public void sucess(ResponseInfo<String> arg0) {
                if (httpDataUtils.code(arg0.result)) {
                    publishList.clear();
                    try {
                        JSONArray array = new JSONArray(httpDataUtils.data(arg0.result));
                        for (int i = 0; i < array.length(); i++) {
                            JSONObject object = array.optJSONObject(i);
                            AllStatusEntity entity = gson.fromJson(object.toString(), AllStatusEntity.class);
                            publishList.add(entity);

                            if (object.optString("remark").equals("律师协作中发布者的状态文字")) {
                                publishStatusList.add(entity);
                            }
                            if (object.optString("remark").equals("律师协作中律师的状态文字")) {
                                collaboratorStatusList.add(entity);
                            }
                            if (object.optString("remark").equals("律师协作中其他人员的状态文字")) {
                                otherStatusList.add(entity);
                            }
                        }
                        dataInit();
                    } catch (JSONException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                } else {
                    httpDataUtils.showMsg(arg0.result);
                }
            }
        });
    }

    private void setReLoadData() {
        psc_lawyer.setOnRefreshListener(new OnRefreshListener<ScrollView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ScrollView> refreshView) {
                /** 律师是否申请了协作 */
                userType = 0;
                giveUp = 0;
                isService = 0;
                hanlderid = 0;
                /** 自己是否被选中为协作律师 */
                cooperate = 0;
                getAllStatus();
            }
        });




    }

    private void dataInit() {
        /** 律师是否申请了协作 */
        userType = 0;
        giveUp = 0;
        isService = 0;
        hanlderid = 0;
        /** 自己是否被选中为协作律师 */
        cooperate = 0;
        entity = null;

        if (null != getIntent()) {
            id = getIntent().getStringExtra("id");

            RequestParams params = new RequestParams();
            params.addHeader("Authorization", PrefsUtils.getString(getApplicationContext(), PrefsUtils.ACCESSKEY));
            params.addHeader("Accept", "application/json, text/javascript, */*;");
            params.addQueryStringParameter("id", id);
            HttpUtils utils = new HttpUtils();
            utils.send(HttpMethod.GET, MyData.LAWERCOOPINFO, params, new RequestCallBack<String>() {

                @Override
                public void onFailure(HttpException arg0, String arg1) {
                    if (loading != null) {
                        loading.dismissDialog();
                    }
                }

                @Override
                public void onSuccess(ResponseInfo<String> arg0) {
                    if (loading != null)
                        loading.dismissDialog();
                    if (httpDataUtils.code(arg0.result)) {
                        Log.e("===============》》》》", arg0.result);
                        entity = gson.fromJson(httpDataUtils.data(arg0.result), CooperateInfoEntity.class);
                        // 先验证登录是否失效
                        try {
                            JSONObject object = new JSONObject(arg0.result);
                            String code = object.optString("code");
                            if (code.equals("999999")) {
                                unloginView();
                                return;
                            }
//                            DataUserEntity userEntity = (DataUserEntity) HzApplication.getInstance()
//                                    .getUserEntityCache().getAsObject(Constans.USER_INFO);
                            if (PrefsUtils.getString(LawyerCollaboratDetailsActivity.this,PrefsUtils.ACCESSKEY) == null||PrefsUtils.getString(context, PrefsUtils.AUTHORIZATION) == null || PrefsUtils.getString(context, PrefsUtils.AUTHORIZATION).isEmpty()) {
                                unloginView();
                                return;
                            }
                        } catch (JSONException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }


                        if (PrefsUtils.getString(context, PrefsUtils.OLD_UID).equals(entity.getUid())) {// =========================================发布方
                            if (entity.getStatus().equals("0")) {// 邀请中
                                publisher3View();
                            } else if (entity.getStatus().equals("1")) {// 协作中
                                if (entity.getIs_giveup().equals("0") && entity.getIs_regiveup().equals("0")
                                        && entity.getIs_service().equals("0")) {// 协作中
                                    publisher6View();
                                } else if (entity.getIs_giveup().equals("1") && entity.getIs_regiveup().equals("1")
                                        && entity.getIs_service().equals("0")) {// 第二次申请放弃协作
                                    // 申请放弃
                                    giveUp = 1;
                                    publisher4View();
                                } else if (entity.getIs_giveup().equals("1") && entity.getIs_regiveup().equals("0")
                                        && entity.getIs_service().equals("0")) {// 协作方申请放弃
                                    // 申请放弃
                                    giveUp = 1;
                                    publisher4View();
                                } else if (entity.getIs_giveup().equals("0") && entity.getIs_regiveup().equals("1")
                                        && entity.getIs_service().equals("0")) {// 第一次申请被拒绝，显示可以再次申请放弃状态
                                    publisher6View();
                                } else if (entity.getIs_giveup().equals("0") && entity.getIs_regiveup().equals("0")
                                        && entity.getIs_service().equals("1")) {// 客服介入
                                    if (entity.getWorkorderInfo().getHandler_id() == null) {
                                        isService = 1;
                                        publisher2View();
                                    } else {// 客服处理结果
                                        hanlderid = 1;
                                        publisher8View();
                                    }
                                }
                            } else if (entity.getStatus().equals("2")) {// 待确认
                                publisher1View();
                            } else if (entity.getStatus().equals("3")) {// 完成
                                publisher5View();
                            } else if (entity.getStatus().equals("4")) {// 关闭
                                publisher7View();// 只有发布方才能重新开启
                            }
                        } else {// ============================================协作方
                            if (entity.getStatus().equals("0")) {// 邀请中
                                if (entity.getIs_giveup().equals("0") && entity.getIs_giveup().equals("0")
                                        && entity.getIs_service().equals("0")) {// 未申请协作
                                    userType = 1;
                                    unPublisherView();
                                }
                                if (entity.getCollaboration().getList() != null
                                        && entity.getCollaboration().getList().size() != 0) {
                                    for (list list : entity.getCollaboration().getList()) {
                                        if (list.getUserid().equals(PrefsUtils.getString(context, PrefsUtils.OLD_UID))) {// 已申请协作
                                            applicant5View();
                                        }
                                    }
                                }
                            } else if (entity.getStatus().equals("1")) {
                                if (entity.getColl_user() != null && entity.getColl_user().getUserid()
                                        .equals(PrefsUtils.getString(context, PrefsUtils.OLD_UID))) {// 被选中为协作律师
                                    userType = 0;
                                    cooperate = 1;
                                    applicant3View();
                                } else {// 没有被选作协作律师
                                    userType = 1;
                                    cooperate = 0;
                                    unPublisher2View();
                                    return;
                                }
                                if (entity.getIs_giveup().equals("0") && entity.getIs_regiveup().equals("0")
                                        && entity.getIs_service().equals("0")) {// 协作中
                                    userType = 0;
                                    cooperate = 1;
                                    applicant3View();
                                } else if (entity.getIs_giveup().equals("1") && entity.getIs_regiveup().equals("1")
                                        && entity.getIs_service().equals("0")) {// 第二次申请放弃协作
                                    giveUp = 1;
                                    // 申请放弃
                                    applicant7View();
                                } else if (entity.getIs_giveup().equals("1") && entity.getIs_regiveup().equals("0")
                                        && entity.getIs_service().equals("0")) {// 协作方申请放弃
                                    giveUp = 1;
                                    // 申请放弃
                                    applicant7View();
                                } else if (entity.getIs_giveup().equals("0") && entity.getIs_regiveup().equals("1")
                                        && entity.getIs_service().equals("0")) {// 第一次申请被拒绝，显示可以再次申请放弃状态
                                    applicant1View();
                                } else if (entity.getIs_giveup().equals("0") && entity.getIs_regiveup().equals("0")
                                        && entity.getIs_service().equals("1")) {// 客服介入
                                    if (entity.getWorkorderInfo().getHandler_id() == null) {
                                        isService = 1;
                                        applicant2View();
                                    } else {// 客服处理结果
                                        hanlderid = 1;
                                        applicant6View();
                                    }
                                }
                            } else if (entity.getStatus().equals("2")) {// 待确认
                                if (entity.getColl_user() != null && entity.getColl_user().getUserid()
                                        .equals(PrefsUtils.getString(context, PrefsUtils.OLD_UID))) {// 被选中为协作律师
                                    applicant8View();
                                } else {// 没有被选作协作律师
                                    userType = 1;
                                    cooperate = 0;
                                    unPublisher2View();
                                    return;
                                }
                            } else if (entity.getStatus().equals("3")) {// 完成
                                if (entity.getColl_user() != null && entity.getColl_user().getUserid()
                                        .equals(PrefsUtils.getString(context, PrefsUtils.OLD_UID))) {// 被选中为协作律师
                                    publisher5View();
                                } else {// 没有被选作协作律师
                                    userType = 1;
                                    cooperate = 0;
                                    unPublisher2View();
                                    return;
                                }
                            } else if (entity.getStatus().equals("4")) {// 关闭
                                if (entity.getColl_user() != null && entity.getColl_user().getUserid().equals(PrefsUtils.getString(context, PrefsUtils.OLD_UID))) {// 被选中为协作律师
                                    applicant9View();
                                } else {// 没有被选作协作律师
                                    userType = 1;
                                    cooperate = 0;
                                    unPublisher2View();
                                    return;
                                }
                            } else {// 未申请协作
//                                if (CacheUtils.dataUserEntity.getVtruename().equals("-1")) {// 未认证律师
//                                    domesticView();
//                                } else {// 已认证律师
//                                    unPublisherView();
//                                }
                            }
                        }
                        if (loading != null)
                            loading.dismissDialog();

                    }

                }
            });
        }
    }

    /**
     * 发布方(待确认)
     */
    private void publisher1View() {
        View view = LayoutInflater.from(context).inflate(R.layout.cooperate_publisher1_layout, null);
        setContentView(view);
        ViewUtils.inject(this, view);
        handler.sendEmptyMessage(1);
        collaboration(1);
    }

    /**
     * 发布方(客服介入)
     */
    private void publisher2View() {
        View view = LayoutInflater.from(context).inflate(R.layout.cooperate_publisher2_layout, null);
        setContentView(view);
        ViewUtils.inject(this, view);
        handler.sendEmptyMessage(1);
        collaboration(1);
    }

    /**
     * 发布方（客服处理结果，是否同意裁定）
     */
    private void publisher8View() {
        View view = LayoutInflater.from(context).inflate(R.layout.cooperate_applicant6_layout, null);
        setContentView(view);
        ViewUtils.inject(this, view);
        handler.sendEmptyMessage(1);
        collaboration(1);
    }

    /**
     * 发布方(未选择协作方)
     */
    private void publisher3View() {
        View view = LayoutInflater.from(context).inflate(R.layout.cooperate_publisher3_layout, null);
        setContentView(view);
        ViewUtils.inject(this, view);
        handler.sendEmptyMessage(1);
        collaboration(0);
    }

    /**
     * 发布方(协作方申请放弃)
     */
    private void publisher4View() {
        View view = LayoutInflater.from(context).inflate(R.layout.cooperate_publisher4_layout, null);
        setContentView(view);
        ViewUtils.inject(this, view);
        handler.sendEmptyMessage(1);
        collaboration(1);
    }

    /**
     * 发布方(已完成)
     */
    private void publisher5View() {
        View view = LayoutInflater.from(context).inflate(R.layout.cooperate_publisher5_layout, null);
        setContentView(view);
        ViewUtils.inject(this, view);
        handler.sendEmptyMessage(1);
        collaboration(1);
    }

    /**
     * 发布方(已选择协作方)
     */
    private void publisher6View() {
        View view = LayoutInflater.from(context).inflate(R.layout.cooperate_publisher6_layout, null);
        setContentView(view);
        ViewUtils.inject(this, view);
        handler.sendEmptyMessage(1);
        collaboration(1);
    }

    /**
     * 关闭 、重新开启
     */
    private void publisher7View() {
        View view = LayoutInflater.from(context).inflate(R.layout.cooperate_publisher7_layout, null);
        setContentView(view);
        ViewUtils.inject(this, view);
        handler.sendEmptyMessage(1);
    }

    /**
     * 普通用户（针对未认证的律师）
     */
    private void domesticView() {
        View view = LayoutInflater.from(context).inflate(R.layout.cooperate_domestic_layout, null);
        setContentView(view);
        ViewUtils.inject(this, view);
        handler.sendEmptyMessage(1);
    }

    /**
     * 申请人（被拒后第二次申请放弃协作）
     */
    private void applicant1View() {
        View view = LayoutInflater.from(context).inflate(R.layout.cooperate_applicant1_layout, null);
        setContentView(view);
        ViewUtils.inject(this, view);
        handler.sendEmptyMessage(1);
    }

    /**
     * 申请人（客服介入）
     */
    private void applicant2View() {
        View view = LayoutInflater.from(context).inflate(R.layout.cooperate_applicant2_layout, null);
        setContentView(view);
        ViewUtils.inject(this, view);
        handler.sendEmptyMessage(1);
    }

    /**
     * 申请人（协作中）
     */
    private void applicant3View() {
        View view = LayoutInflater.from(context).inflate(R.layout.cooperate_applicant3_layout, null);
        setContentView(view);
        ViewUtils.inject(this, view);
        handler.sendEmptyMessage(1);
    }

    /**
     * 申请人（申请放弃中 ，等待状态）
     */
    private void applicant7View() {
        View view = LayoutInflater.from(context).inflate(R.layout.cooperate_applicant7_layout, null);
        setContentView(view);
        ViewUtils.inject(this, view);
        handler.sendEmptyMessage(1);
    }

    /**
     * 申请人（已申请）
     */
    private void applicant5View() {
        View view = LayoutInflater.from(context).inflate(R.layout.cooperate_applicant5_layout, null);
        setContentView(view);
        ViewUtils.inject(this, view);
        handler.sendEmptyMessage(1);
    }

    /**
     * 申请人（客服处理结果，是否同意裁定）
     */
    private void applicant6View() {
        View view = LayoutInflater.from(context).inflate(R.layout.cooperate_applicant6_layout, null);
        setContentView(view);
        ViewUtils.inject(this, view);
        handler.sendEmptyMessage(1);
//        collaboration(0);
    }

    /**
     * 申请人（提交确认，待确认）
     */
    private void applicant8View() {
        View view = LayoutInflater.from(context).inflate(R.layout.cooperate_applicant8_layout, null);
        setContentView(view);
        ViewUtils.inject(this, view);
        handler.sendEmptyMessage(1);
    }

    /**
     * 关闭
     */
    private void applicant9View() {
        View view = LayoutInflater.from(context).inflate(R.layout.cooperate_applicant9_layout, null);
        setContentView(view);
        ViewUtils.inject(this, view);
        handler.sendEmptyMessage(1);
    }

    /**
     * 未登录状态
     */
    private void unloginView() {
        View view = LayoutInflater.from(context).inflate(R.layout.cooperate_unlogin_layout, null);
        setContentView(view);
        ViewUtils.inject(this, view);
        handler.sendEmptyMessage(1);
    }

    /**
     * 未申请.(针对已认证的律师)
     */
    private void unPublisherView() {
        View view = LayoutInflater.from(context).inflate(R.layout.cooperate_unpublisher_layout, null);
        setContentView(view);
        ViewUtils.inject(this, view);
        handler.sendEmptyMessage(1);
    }

    /**
     * 未被选作为协作律师.(针对已认证的律师)
     */
    private void unPublisher2View() {
        View view = LayoutInflater.from(context).inflate(R.layout.cooperate_unpublisher2_layout, null);
        setContentView(view);
        ViewUtils.inject(this, view);
        handler.sendEmptyMessage(1);
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    // 加载数据
                    loadData();
                    break;
                default:
                    break;
            }
        }

    };

    /**
     * 加载数据到页面
     */
    private void loadData() {
        if (entity == null) {
            return;
        }
        String status = null;
        status = entity.getStatus();
        tv_text.setFocusableInTouchMode(true);
        tv_text.setFocusable(true);
        tv_text.requestFocus();

        // 是否收藏
        if (tv_collects != null) {
            if (this.entity.getIs_collected().equals("1")) {
                tv_collects.setText("已收藏");
            } else {
                tv_collects.setText("收藏");
            }
        }

        if (entity.getUid().equals(PrefsUtils.getString(context, PrefsUtils.OLD_UID))) {// 发布者
            for (AllStatusEntity entity : publishStatusList) {
                if (entity.getStatus_value().equals(status)) {
                    if (tv_text != null)
                        tv_text.setText(entity.getStatus_name() != null ? entity.getStatus_name() : "");
                    if (entity.getStatus_name().contains("%s")
                            && this.entity.getCollaboration().getList().get(0).getStatus().equals("1")) {
                        String status2 = entity.getStatus_name().replace("%s",
                                this.entity.getCollaboration().getList().get(0).getTruename() + "");
                        tv_text.setText(status2);
                    }
                }
                if (this.entity.getStatus().equals("1") && this.entity.getIs_giveup().equals("0")
                        && this.entity.getIs_regiveup().equals("0") && this.entity.getIs_service().equals("1")) {
                    tv_text.setText("客服介入处理中，请您耐心等待");
                }

                if (giveUp == 1) {
                    String info = "协作方已申请放弃: 原因：" + "【 " + this.entity.getAbandon_reason()
                            + "】 , 正在等待您的确认, 如有异议请与协作方联系。";
                    if (tv_text != null)
                        tv_text.setText(StringUtil.ToDBC(info));
                } else if (hanlderid == 0 && isService == 1) {
                    // tv_text.setText("申请客服介入中，请您耐心等待");
                } else if (hanlderid == 1) {
                    int u_finally = Integer.valueOf(this.entity.getWorkorderInfo().getU_finally());
                    int o_finally = Integer.valueOf(this.entity.getWorkorderInfo().getO_finally());
                    if (u_finally == 0 && o_finally == 0) {// 双方都没有选择裁定结果是否同意
                        if (tv_text != null)
                            tv_text.setText("客服处理完成，裁定结果是否同意？发布方未选择，协作还未选择");
                        if (ll_btn_layout != null)
                            ll_btn_layout.setVisibility(View.VISIBLE);
                    } else if (u_finally == 1 && o_finally == 0) {// 发布方不同意，协作方没有选择
                        if (tv_text != null)
                            tv_text.setText(StringUtil.ToDBC("客服处理完成，裁定结果是否同意？发布方【不同意】，协作方还未选择"));
                        if (ll_btn_layout != null)
                            ll_btn_layout.setVisibility(View.GONE);
                    } else if (u_finally == 2 && o_finally == 0) {// 发布方同意，协作方没有选择
                        if (tv_text != null)
                            tv_text.setText(StringUtil.ToDBC("客服处理完成，裁定结果是否同意？发布方【同意】，协作方还未选择"));
                        if (ll_btn_layout != null)
                            ll_btn_layout.setVisibility(View.GONE);
                    } else if (u_finally == 0 && o_finally == 1) {// 发布方没有选择，协作方不同意
                        if (tv_text != null)
                            tv_text.setText(StringUtil.ToDBC("客服处理完成，裁定结果是否同意？发布方未选择，协作方【不同意】"));
                        if (ll_btn_layout != null)
                            ll_btn_layout.setVisibility(View.VISIBLE);
                    } else if (u_finally == 0 && o_finally == 2) {// 发布方没有选择，协作方同意
                        if (tv_text != null)
                            tv_text.setText(StringUtil.ToDBC("客服处理完成，裁定结果是否同意？发布方未选择，协作方【同意】"));
                        if (ll_btn_layout != null)
                            ll_btn_layout.setVisibility(View.VISIBLE);
                    } else if (u_finally == 1 && o_finally == 1) {
                        if (tv_text != null)
                            tv_text.setText(StringUtil.ToDBC("客服处理完成，裁定结果是否同意？发布方【不同意】，协作方【不同意】"));
                        if (ll_btn_layout != null)
                            ll_btn_layout.setVisibility(View.GONE);
                    } else if (u_finally == 2 && o_finally == 1) {
                        if (tv_text != null)
                            tv_text.setText(StringUtil.ToDBC("客服处理完成，裁定结果是否同意？发布方【同意】，协作方【不同意】"));
                        if (ll_btn_layout != null)
                            ll_btn_layout.setVisibility(View.GONE);
                    } else if (u_finally == 1 && o_finally == 2) {
                        if (tv_text != null)
                            tv_text.setText(StringUtil.ToDBC("客服处理完成，裁定结果是否同意？发布方【不同意】，协作方【同意】"));
                        if (ll_btn_layout != null)
                            ll_btn_layout.setVisibility(View.GONE);
                    } else if (u_finally == 2 && o_finally == 2) {
                        if (tv_text != null)
                            tv_text.setText(StringUtil.ToDBC("客服处理完成，裁定结果是否同意？发布方【同意】，协作方【同意】"));
                        if (ll_btn_layout != null)
                            ll_btn_layout.setVisibility(View.GONE);
                    }
                    tv_service_result.setText(this.entity.getWorkorderInfo().getRemark());
                }
            }
        } else if (userType == 0) {// 协作者
            for (AllStatusEntity entity : collaboratorStatusList) {
                if (entity.getStatus_value().equals(status)) {
                    if (tv_text != null)
                        tv_text.setText(entity.getStatus_name() != null ? entity.getStatus_name() : "");
                }
            }
            if (giveUp == 1) {
                String info = "协作方已申请放弃: 原因: 【 " + entity.getAbandon_reason() + "】 , 正在等待发布方的确认, 如有异议请与协作方联系。";
                if (tv_text != null)
                    tv_text.setText(StringUtil.stringFilter(info));
            } else if (hanlderid == 0 && isService == 1) {
                // tv_text.setText("申请客服介入中，请您耐心等待");
            } else if (hanlderid == 1) {
                int u_finally = Integer.valueOf(entity.getWorkorderInfo().getU_finally());
                int o_finally = Integer.valueOf(entity.getWorkorderInfo().getO_finally());
                if (u_finally == 0 && o_finally == 0) {// 双方都没有选择裁定结果是否同意
                    if (tv_text != null)
                        tv_text.setText("客服处理完成，裁定结果是否同意？发布方未选择，协作还未选择");
                    if (ll_btn_layout != null)
                        ll_btn_layout.setVisibility(View.VISIBLE);
                } else if (u_finally == 1 && o_finally == 0) {// 发布方不同意，协作方没有选择
                    if (tv_text != null)
                        tv_text.setText(StringUtil.ToDBC("客服处理完成，裁定结果是否同意？发布方【不同意】，协作方还未选择"));
                    if (ll_btn_layout != null)
                        ll_btn_layout.setVisibility(View.VISIBLE);
                } else if (u_finally == 2 && o_finally == 0) {// 发布方同意，协作方没有选择
                    if (tv_text != null)
                        tv_text.setText(StringUtil.ToDBC("客服处理完成，裁定结果是否同意？发布方【同意】，协作方还未选择"));
                    if (ll_btn_layout != null)
                        ll_btn_layout.setVisibility(View.VISIBLE);
                } else if (u_finally == 0 && o_finally == 1) {// 发布方没有选择，协作方不同意
                    if (tv_text != null)
                        tv_text.setText(StringUtil.ToDBC("客服处理完成，裁定结果是否同意？发布方未选择，协作方【不同意】"));
                    if (ll_btn_layout != null)
                        ll_btn_layout.setVisibility(View.GONE);
                } else if (u_finally == 0 && o_finally == 2) {// 发布方没有选择，协作方同意
                    if (tv_text != null)
                        tv_text.setText(StringUtil.ToDBC("客服处理完成，裁定结果是否同意？发布方未选择，协作方【同意】"));
                    if (ll_btn_layout != null)
                        ll_btn_layout.setVisibility(View.GONE);
                } else if (u_finally == 1 && o_finally == 1) {
                    if (tv_text != null)
                        tv_text.setText(StringUtil.ToDBC("客服处理完成，裁定结果是否同意？发布方【不同意】，协作方【不同意】"));
                    if (ll_btn_layout != null)
                        ll_btn_layout.setVisibility(View.GONE);
                } else if (u_finally == 2 && o_finally == 1) {
                    if (tv_text != null)
                        tv_text.setText(StringUtil.ToDBC("客服处理完成，裁定结果是否同意？发布方【同意】，协作方【不同意】"));
                    if (ll_btn_layout != null)
                        ll_btn_layout.setVisibility(View.GONE);
                } else if (u_finally == 1 && o_finally == 2) {
                    if (tv_text != null)
                        tv_text.setText(StringUtil.ToDBC("客服处理完成，裁定结果是否同意？发布方【不同意】，协作方【同意】"));
                    if (ll_btn_layout != null)
                        ll_btn_layout.setVisibility(View.GONE);
                } else if (u_finally == 2 && o_finally == 2) {
                    if (tv_text != null)
                        tv_text.setText(StringUtil.ToDBC("客服处理完成，裁定结果是否同意？发布方【同意】，协作方【同意】"));
                    if (ll_btn_layout != null)
                        ll_btn_layout.setVisibility(View.GONE);
                }

                tv_service_result.setText(entity.getWorkorderInfo().getRemark());
            }
        } else {
            if (entity.getStatus().equals("0")) {
                for (AllStatusEntity entity : otherStatusList) {
                    if (entity.getStatus_value().equals(status)) {
                        if (tv_text != null)
                            tv_text.setText(entity.getStatus_name() + "");
                    }
                }
            } else if (entity.getStatus().equals("4")) {
                if (tv_text != null)
                    tv_text.setText("该协作已经关闭！");
            } else {
                if (tv_text != null)
                    tv_text.setText("发布方已选择协作律师");
            }

        }

        if (tv_publisher != null) {
            tv_publisher.setText("发布方：" + entity.getName());
        }
        if (tv_publish_phone != null) {
            tv_publish_phone.setText("联系电话：" + entity.getMobile());
        }

        test(entity.getHopedate());
        if (PrefsUtils.getString(context, PrefsUtils.OLD_UID).equals(entity.getUid())) {
            if (tv_servicein != null)
                tv_servicein.setVisibility(View.VISIBLE);
        } else {
            if (tv_servicein != null) {
                if (entity.getStatus().equals("1") && Integer.valueOf(entity.getIs_reload()) > 1) {// 重新开始协作次数大于一次就可以申请协作
                    tv_servicein.setVisibility(View.VISIBLE);
                } else {
                    tv_servicein.setVisibility(View.GONE);
                }
            }
        }

        if (entity.getIs_service().equals("1") || entity.getStatus().equals("4")) {
            tv_date.setVisibility(View.GONE);
        } else {
            tv_date.setVisibility(View.VISIBLE);
        }

        if (entity.getIs_pay().equals("1")) {
            if (tv_hosting != null)
                tv_hosting.setVisibility(View.GONE);
        } else {
            if (tv_hosting != null)
                tv_hosting.setVisibility(View.GONE);
        }

        if (tv_modificat != null)
            tv_modificat.setVisibility(View.GONE);

        if (tv_applicat != null)
            tv_applicat.setText("已有" + entity.getCollaboration().getCount() + "人申请");
        tv_name.setText(entity.getTitle() + "");
        tv_regdate.setText(entity.getCtime() + "");
        tv_orderno.setText("订单编号：" + entity.getLawcoop_number());
        tv_money.setText(entity.getZqprice() + "");
        tv_type.setText("协作类型：" + entity.getCatalog_text());
        tv_address.setText("地点：" + entity.getPlaces());
        tv_compledate.setText("希望完成日期：" + entity.getHopedate());
//      tv_details.setText(entity.getZqcontent().toString());
        tv_details.setText(Html.fromHtml(entity.getZqcontent().toString()));
        tv_details.setTextColor(0xff000000);
        tv_collect.setText(entity.getHits() + "人浏览，" + entity.getCollect_num() + "人收藏");
        attachmentData();// 附件图片加载
        collaration();
        setReLoadData();
    }

    /**
     * （协作方）申请律师协作的人
     */
    private void collaration() {
        if (ll_applicat != null) {
            ll_applicat.removeAllViews();
            if (entity.getCollaboration().getList() != null && entity.getCollaboration().getList().size() != 0) {
                for (int i = 0; i < entity.getCollaboration().getList().size(); i++) {
                    View view = getLayoutInflater().inflate(R.layout.item_iamgeview2, null);
                    final ImageView iv_image = (ImageView) view.findViewById(R.id.iv_image);

                    iv_image.setTag(i);

                    if (entity.getCollaboration() != null && entity.getCollaboration().getList() != null
                            && entity.getCollaboration().getList().size() != 0 && entity.getCollaboration().getList()
                            .get((Integer) (iv_image.getTag())).getIs_anonymity().equals("0")) {// 没有匿名的人
                        ImageLoaderUtils.initUtils().display(
                                MyData.IP + entity.getCollaboration().getList().get(i).getImgurl(), iv_image,
                                ImageLoaderUtils.initUtils().option2());
                    }

                    iv_image.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // TODO Auto-generated method stub
                            if (entity.getCollaboration() != null && entity.getCollaboration().getList() != null
                                    && entity.getCollaboration().getList().size() != 0
                                    && entity.getCollaboration().getList().get((Integer) (iv_image.getTag()))
                                    .getIs_anonymity().equals("0")) {// 没有匿名的人
//                                startActivity(new Intent().putExtra(LawyerOfficeActivity.USER_ID_INTENT,
//                                                entity.getCollaboration().getList().get((Integer) (iv_image.getTag()))
//                                                        .getUserid())
//                                        .putExtra("title", "hahaha").putExtra("from", "footprint")
//                                        .setClass(context, LawyerOfficeActivity.class));
                                getNewId(entity.getCollaboration().getList().get((Integer) (iv_image.getTag())).getUserid()+"");

                            }
                        }
                    });
                    ll_applicat.addView(view);
                }
            }
        }
    }

    private void getNewId(String s) {

        RequestParams params = new RequestParams();
        httpDataUtils.sendProgressGet(MyData.GET_NEWID_BY_OLDID + s, params);
        httpDataUtils.setSucessBack(new HttpDataUtils.SucessBack() {
            @Override
            public void sucess(ResponseInfo<String> arg0) {
                if (httpDataUtils.code(arg0.result)){
                    try {
                        JSONObject object = new JSONObject(arg0.result);
                        JSONObject data = object.getJSONObject("data");
                        int users_id = data.optInt("users_id");
                        startActivity(new Intent().putExtra("user_id", users_id + "").putExtra("title", "00").putExtra("insert", false).setClass(context, LawyerOfficeActivity.class));
                    }catch (Exception e){

                    }
                }
            }
        });


    }

    private int day;
    private int hour;
    private int minute;
    private int second;

    /**
     * 当前时间离完成时间还剩多长时间
     *
     * @throws ParseException
     */
    public void test(String time) {

        if (entity.getStatus().equals("2")) {
            SimpleDateFormat date2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 格式化时间
            String nowtime2 = date2.format(new Date());// 按以上格式 将当前时间转换成字符串
            String time2 = entity.getSuretime();
            daysBetween(nowtime2, time2);
            int[] times = {day, hour, minute, second};
            tv_date.setTimes(times);
            if (!tv_date.isRun()) {
                tv_date.run();
            }
        } else {
            SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 格式化时间
            String nowtime = date.format(new Date());// 按以上格式 将当前时间转换成字符串
            time = time + " 23:59:59";

            daysBetween(nowtime, time);
            int[] times = {day, hour, minute, second};
            tv_date.setTimes(times);
            if (!tv_date.isRun()) {
                tv_date.run();
            }
        }

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

    /**
     * 申请人的列表
     */
    private void collaboration(int type) {
        if (entity.getCollaboration().getList() != null && lv_lawyer != null) {
            LawyerCollaboraDetailsAdapter adapter = new LawyerCollaboraDetailsAdapter(context,
                    entity.getCollaboration().getList(), type);
            lv_lawyer.setAdapter(adapter);
            lv_lawyer.setFocusable(false);
            lv_lawyer.setFocusableInTouchMode(false);

            adapter.setOnclick(new LawyerCollaboraDetailsAdapter.OnClick() {
                @Override
                public void select(String lawyerid, String name) {
                    // TODO Auto-generated method stub
                    showDiolog(lawyerid, name);
                }

                @Override
                public void connect(String lawyerid) {
                    // TODO Auto-generated method stub
                    Logininfo(lawyerid);// 获取个人信息并联系他

                }
            });

            lv_lawyer.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

//                    startActivity(new Intent()
//                            .putExtra(LawyerOfficeActivity.USER_ID_INTENT,
//                                    entity.getCollaboration().getList().get(arg2).getUserid())
//                            .putExtra("title", "00000").putExtra("from", "footprint")
//                            .setClass(context, LawyerOfficeActivity.class));

                    getNewId(entity.getCollaboration().getList().get(arg2).getUserid()+"");

                }
            });
        }
    }

    private void showDiolog(final String id, String name) {
        AlertDialog.Builder builder = new AlertDialog.Builder(LawyerCollaboratDetailsActivity.this);
        View view2 = getLayoutInflater().inflate(R.layout.view_dialog_delece_consult_reply, null);
        view2.setBackgroundResource(R.drawable.background_view_dialog);
        builder.setView(view2);
        final Dialog dialog = builder.create();

        TextView tv_title = (TextView) view2.findViewById(R.id.tv_1);
        TextView tv_content = (TextView) view2.findViewById(R.id.tv_2);

        tv_title.setText("选择协作律师");
        tv_content.setText("是否确定选择 " + name + " 律师为协作律师？");

        TextView tv_yes = (TextView) view2.findViewById(R.id.tv_yes);
        TextView tv_no = (TextView) view2.findViewById(R.id.tv_no);
        tv_yes.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {

                areeCollaboration(id);
                dialog.cancel();

            }
        });

        tv_no.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        dialog.show();
    }


    /**
     * 获取用户信息
     */
    public void Logininfo(String uid) {
        RequestParams params = new RequestParams();
        params.addQueryStringParameter("uid", uid);
        httpDataUtils.sendGet(MyData.GET_INFOBYID, params);
        httpDataUtils.setSucessBack(new HttpDataUtils.SucessBack() {

            @Override
            public void sucess(ResponseInfo<String> arg0) {
                loading.dismissDialog();
                try {
                    JSONObject object = new JSONObject(arg0.result);
                    String code = object.optString("code");
                    String msg = object.optString("msg");
                    if (code.equals("000000")) {
                        Gson gson = new Gson();
                        otherUserEntity = gson.fromJson(httpDataUtils.data(arg0.result), OtherUserEntity.class);
                        MessageListEntity listEntity = new MessageListEntity();
                        listEntity.setUserid(otherUserEntity.getUserid());
                        listEntity.setNickname(otherUserEntity.getTruename());
                        listEntity.setImgurl(otherUserEntity.getImgurl());
                        listEntity.setNewmsg(new ArrayList<MessageListEntity.NewmsgBean>());
                        Intent intent = new Intent(context, MessageDetailChatActivity.class);
                        intent.putExtra(MessageDetailChatActivity.MESSAGE_INTENT, listEntity);
                        startActivity(intent);
                    } else {
                        ToastUtils.getUtils(context).show(msg);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        httpDataUtils.setFailBack(new FailBack() {
            @Override
            public void fail(String msg) {
                loading.dismissDialog();
            }
        });
    }

    /**
     *
     * 图片附件加载
     *
     */


    private void attachmentData() {
        ll_album.removeAllViews();
        if (entity.getImgurlList() != null && entity.getImgurlList().size() != 0) {
            ll_attachment.setVisibility(View.VISIBLE);

            final ArrayList<String> urlList = new ArrayList<String>();
            for (int i = 0; i < entity.getImgurlList().size(); i++) {
                urlList.add(MyData.IP + entity.getImgurlList().get(i).getA_url());
            }
            for (int i = 0; i < entity.getImgurlList().size(); i++) {
                View view = getLayoutInflater().inflate(R.layout.item_iamgeview, null);
                ImageView iv_image = (ImageView) view.findViewById(R.id.iv_image);
                ImageLoaderUtils.initUtils().display(MyData.IP + entity.getImgurlList().get(i).getUrl(), iv_image);
                final int position = i;
                view.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(LawyerCollaboratDetailsActivity.this, ViewPagerExampleActivity.class);
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

    @OnClick({R.id.ic_back, R.id.tv_hosting, R.id.tv_addmoney, R.id.tv_addtime, R.id.tv_considerapplication,
            R.id.tv_enter, R.id.tv_agreeruling, R.id.tv_unagreeruling, R.id.tv_auth, R.id.tv_servicein, R.id.tv_reload,
            R.id.tv_close, R.id.tv_restart, R.id.tv_continue, R.id.tv_agreegiveup, R.id.tv_login,
            R.id.tv_apply_collaboration, R.id.tv_connect, R.id.tv_share, R.id.tv_collects,R.id.tv_evaluate})
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.tv_evaluate:

                break;

            case R.id.tv_collects://

                setCollect();
                break;
            case R.id.tv_share:// 分享

                final String text = tv_name.getText().toString();
                SharePopupWindow popupWindow = new SharePopupWindow(this, new SharePopupWindow.Click() {
                    @Override
                    public void Select(SHARE_MEDIA shareStatus) {
                        UMImage image = new UMImage(context, R.drawable.app2);
                        switch (shareStatus) {
                            case WEIXIN:
                                Config.OpenEditor = true;
                                Config.dialog = new DialogLoading().showDialog(LawyerCollaboratDetailsActivity.this);
                                new ShareAction(LawyerCollaboratDetailsActivity.this).setPlatform(SHARE_MEDIA.WEIXIN)
                                        .setCallback(umShareListener).withText(text).withMedia(image)
                                        // .withMedia(new
                                        // UMEmoji(context,"http://img.newyx.net/news_img/201306/20/1371714170_1812223777.gif"))
                                        .withTargetUrl(MyData.MOBILE_URL + "/cooperation/detail/" + id).share();
                                break;
                            case WEIXIN_CIRCLE:
                                // UMImage image = new UMImage(context,
                                // "http://www.umeng.com/images/pic/social/integrated_3.png");
                                Config.OpenEditor = true;
                                Config.dialog = new DialogLoading().showDialog(LawyerCollaboratDetailsActivity.this);
                                new ShareAction(LawyerCollaboratDetailsActivity.this).setPlatform(SHARE_MEDIA.WEIXIN_CIRCLE)
                                        .setCallback(umShareListener).withTitle("私律").withText(text).withMedia(image)
                                        .withTargetUrl(MyData.MOBILE_URL + "/cooperation/detail/" + id).share();
                                break;
                            case QQ:
                                // UMImage image = new UMImage(context,
                                // "http://www.umeng.com/images/pic/social/integrated_3.png");
                                Config.OpenEditor = true;
                                Config.dialog = new DialogLoading().showDialog(LawyerCollaboratDetailsActivity.this);
                                new ShareAction(LawyerCollaboratDetailsActivity.this).setPlatform(SHARE_MEDIA.QQ)
                                        .setCallback(umShareListener).withTitle("私律").withText(text).withMedia(image)
                                        // .withMedia(music)
                                        .withTargetUrl(MyData.MOBILE_URL + "/cooperation/detail/" + id).share();
                                break;
                            case SINA:
                                // UMImage image = new UMImage(context,
                                // "http://www.umeng.com/images/pic/social/integrated_3.png");
                                if (Build.VERSION.SDK_INT >= 23) {
                                    String[] mPermissionList = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                            Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.CALL_PHONE,
                                            Manifest.permission.READ_LOGS, Manifest.permission.READ_PHONE_STATE,
                                            Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.SET_DEBUG_APP,
                                            Manifest.permission.SYSTEM_ALERT_WINDOW, Manifest.permission.GET_ACCOUNTS,
                                            Manifest.permission.WRITE_APN_SETTINGS};
                                    ActivityCompat.requestPermissions(LawyerCollaboratDetailsActivity.this, mPermissionList,
                                            123);
                                }
                                Config.OpenEditor = true;
                                Config.dialog = new DialogLoading().showDialog(LawyerCollaboratDetailsActivity.this);
                                new ShareAction(LawyerCollaboratDetailsActivity.this).setPlatform(SHARE_MEDIA.SINA)
                                        .setCallback(umShareListener).withText(MyData.MOBILE_URL + "/cooperation/detail/" + id)
                                        // .withTitle("私律")
                                        // .withMedia(image)
                                        // .withExtra(new
                                        // UMImage(LawyerCollaboratDetailsActivity.this,R.drawable.user_head)
                                        // .withTargetUrl(MyData.MOBILE_URL +
                                        // "/cooperation/detail/" + id)
                                        .share();
                                break;
                            default:
                                break;
                        }
                    }
                });
                popupWindow.showPopupWindow(view);
                break;
            case R.id.tv_connect:// 联系TA
                Logininfo(entity.getUid());// 获取个人信息并联系他
                break;
            case R.id.tv_hosting:// 去托管
                assure();
                break;
            case R.id.tv_addmoney:// 增加酬金（）

                if (PrefsUtils.getString(LawyerCollaboratDetailsActivity.this, PrefsUtils.ACCESSKEY) == null || PrefsUtils.getString(LawyerCollaboratDetailsActivity.this, PrefsUtils.ACCESSKEY).length() == 0) {
                    ToastUtils.getUtils(LawyerCollaboratDetailsActivity.this).show("十分抱歉，您不是使用账号密码登录，暂时无法访问本页信息。请切换使用密码登录才可以访问。");
                    return;
                }

                DialogCooperateAddmoney addmoney = new DialogCooperateAddmoney();
                addmoney.showDialog(this);
                addmoney.setCont(new DialogCooperateAddmoney.Cont() {
                    @Override
                    public void Money(String money) {

                        if (money.length() != 0) {
                            int a = Integer.parseInt(money);
                            String price = entity.getZqprice();
                            price = price.replace(",", "");
                            price = price.substring(0, price.length() - 3);

                            int b = Integer.parseInt(price);
                            int c = a + b;
                            if ((a + b) > 1000000) {
                                ToastUtils.getUtils(lawyerCollaboratDetailsActivity).show("总酬金数不能高于1,000,000元。");
                            } else {
                                addMoney(money, c);
                            }
                        } else {
                            ToastUtils.getUtils(LawyerCollaboratDetailsActivity.this).show("请填写金额");
                        }

                    }
                });

                break;
            case R.id.tv_addtime:// 延长任务所需时间

                if (PrefsUtils.getString(LawyerCollaboratDetailsActivity.this, PrefsUtils.ACCESSKEY) == null || PrefsUtils.getString(LawyerCollaboratDetailsActivity.this, PrefsUtils.ACCESSKEY).length() == 0) {
                    ToastUtils.getUtils(LawyerCollaboratDetailsActivity.this).show("十分抱歉，您不是使用账号密码登录，暂时无法访问本页信息。请切换使用密码登录才可以访问。");
                    return;
                }

                DialogCooperateAddtime addtime = new DialogCooperateAddtime();
                addtime.showDialog(this, new Connect() {
                    @Override
                    public void text(String text) {
                        // TODO Auto-generated method stub
                        addTaskTime(text);
                    }
                });

                break;
            case R.id.tv_considerapplication:// 申请放弃协作/再次申请放弃协作

                if (PrefsUtils.getString(LawyerCollaboratDetailsActivity.this, PrefsUtils.ACCESSKEY) == null || PrefsUtils.getString(LawyerCollaboratDetailsActivity.this, PrefsUtils.ACCESSKEY).length() == 0) {
                    ToastUtils.getUtils(LawyerCollaboratDetailsActivity.this).show("十分抱歉，您不是使用账号密码登录，暂时无法访问本页信息。请切换使用密码登录才可以访问。");
                    return;
                }

                DialogCooperateApplyCollaboration collaboration = new DialogCooperateApplyCollaboration();
                collaboration.showDialog(this, new Connect() {
                    @Override
                    public void text(String text) {

                        if (text.length() < 10) {
                            ToastUtils.getUtils(lawyerCollaboratDetailsActivity).show("请输入原因10-240个字");
                            return;
                        } else {
                            applyGiveup(text);
                        }

                    }
                });

                break;
            case R.id.tv_enter:// 确认完成/提交确认

                if (PrefsUtils.getString(LawyerCollaboratDetailsActivity.this, PrefsUtils.ACCESSKEY) == null || PrefsUtils.getString(LawyerCollaboratDetailsActivity.this, PrefsUtils.ACCESSKEY).length() == 0) {
                    ToastUtils.getUtils(LawyerCollaboratDetailsActivity.this).show("十分抱歉，您不是使用账号密码登录，暂时无法访问本页信息。请切换使用密码登录才可以访问。");
                    return;
                }


                if (PrefsUtils.getString(context, PrefsUtils.OLD_UID).equals(entity.getUid())) {// 发布方
                    DialogCooperateEnter enter = new DialogCooperateEnter();
                    enter.showDialog(this, new DialogCooperateEnter.Click() {
                        @Override
                        public void click() {
                            completion();
                        }
                    });

                } else {// 协作方
                    DialogCooperateEnter enter = new DialogCooperateEnter();
                    enter.showDialog(this, new DialogCooperateEnter.Click() {
                        @Override
                        public void click() {
                            // TODO Auto-generated method stub
                            sureTask();
                        }
                    });
                }
                break;
            case R.id.tv_agreeruling:// 同意裁定

                if (PrefsUtils.getString(LawyerCollaboratDetailsActivity.this, PrefsUtils.ACCESSKEY) == null || PrefsUtils.getString(LawyerCollaboratDetailsActivity.this, PrefsUtils.ACCESSKEY).length() == 0) {
                    ToastUtils.getUtils(LawyerCollaboratDetailsActivity.this).show("十分抱歉，您不是使用账号密码登录，暂时无法访问本页信息。请切换使用密码登录才可以访问。");
                    return;
                }

                agreeService();
                break;
            case R.id.tv_unagreeruling:// 不同意裁定

                if (PrefsUtils.getString(LawyerCollaboratDetailsActivity.this, PrefsUtils.ACCESSKEY) == null || PrefsUtils.getString(LawyerCollaboratDetailsActivity.this, PrefsUtils.ACCESSKEY).length() == 0) {
                    ToastUtils.getUtils(LawyerCollaboratDetailsActivity.this).show("十分抱歉，您不是使用账号密码登录，暂时无法访问本页信息。请切换使用密码登录才可以访问。");
                    return;
                }

                disAgreeService();
                break;
            case R.id.tv_auth:// 实名认证
                startActivity(new Intent().setClass(context, AuthenticatRealLawActivity.class));
                break;
            case R.id.tv_servicein:// 申请客服介入

                if (PrefsUtils.getString(LawyerCollaboratDetailsActivity.this, PrefsUtils.ACCESSKEY) == null || PrefsUtils.getString(LawyerCollaboratDetailsActivity.this, PrefsUtils.ACCESSKEY).length() == 0) {
                    ToastUtils.getUtils(LawyerCollaboratDetailsActivity.this).show("十分抱歉，您不是使用账号密码登录，暂时无法访问本页信息。请切换使用密码登录才可以访问。");
                    return;
                }

                DialogCooperateApplyServiceIn serviceIn = new DialogCooperateApplyServiceIn();
                serviceIn.showDialog(this, new Connect() {
                    @Override
                    public void text(String text) {
                        serviceIn(text);
                    }
                });
                break;
            case R.id.tv_reload:// 申请重新执行协作

                if (PrefsUtils.getString(LawyerCollaboratDetailsActivity.this, PrefsUtils.ACCESSKEY) == null || PrefsUtils.getString(LawyerCollaboratDetailsActivity.this, PrefsUtils.ACCESSKEY).length() == 0) {
                    ToastUtils.getUtils(LawyerCollaboratDetailsActivity.this).show("十分抱歉，您不是使用账号密码登录，暂时无法访问本页信息。请切换使用密码登录才可以访问。");
                    return;
                }

                DialogCooperateReload reload = new DialogCooperateReload();
                reload.showDialog(this, new DialogCooperateReload.Click() {
                    @Override
                    public void click() {
                        // TODO Auto-generated method stub
                        applyReload();
                    }
                });
                break;
            case R.id.tv_close:// 关闭

                if (PrefsUtils.getString(LawyerCollaboratDetailsActivity.this, PrefsUtils.ACCESSKEY) == null || PrefsUtils.getString(LawyerCollaboratDetailsActivity.this, PrefsUtils.ACCESSKEY).length() == 0) {
                    ToastUtils.getUtils(LawyerCollaboratDetailsActivity.this).show("十分抱歉，您不是使用账号密码登录，暂时无法访问本页信息。请切换使用密码登录才可以访问。");
                    return;
                }

                closeTask();
                break;
            case R.id.tv_restart:// 重新开启

                if (PrefsUtils.getString(LawyerCollaboratDetailsActivity.this, PrefsUtils.ACCESSKEY) == null || PrefsUtils.getString(LawyerCollaboratDetailsActivity.this, PrefsUtils.ACCESSKEY).length() == 0) {
                    ToastUtils.getUtils(LawyerCollaboratDetailsActivity.this).show("十分抱歉，您不是使用账号密码登录，暂时无法访问本页信息。请切换使用密码登录才可以访问。");
                    return;
                }

                SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 格式化时间
                String nowtime = date.format(new Date());// 按以上格式 将当前时间转换成字符串
                final String time = entity.getHopedate() + " 23:59:59";
                if (daysBetween(nowtime, time)) {
                    DialogCooperateAddtime cooperateAddtime = new DialogCooperateAddtime();
                    cooperateAddtime.showDialog(this, new Connect() {
                        @Override
                        public void text(String text) {
                            duerestart(text);
                        }
                    });
                } else {
                    DialogCooperateAddtime cooperateAddtime = new DialogCooperateAddtime();
                    cooperateAddtime.showDialog(this, new Connect() {
                        @Override
                        public void text(String text) {
                            closeStart(text);
                        }
                    });
                }
                break;
            case R.id.tv_continue:// 不同意放弃协作

                if (PrefsUtils.getString(LawyerCollaboratDetailsActivity.this, PrefsUtils.ACCESSKEY) == null || PrefsUtils.getString(LawyerCollaboratDetailsActivity.this, PrefsUtils.ACCESSKEY).length() == 0) {
                    ToastUtils.getUtils(LawyerCollaboratDetailsActivity.this).show("十分抱歉，您不是使用账号密码登录，暂时无法访问本页信息。请切换使用密码登录才可以访问。");
                    return;
                }

                DialogCooperateContinueCollaborate collaborate = new DialogCooperateContinueCollaborate();
                collaborate.showDialog(this, new DialogCooperateContinueCollaborate.Click() {
                    @Override
                    public void click() {
                        // TODO Auto-generated method stub
                        notAllowedGiveup();
                    }
                });
                break;
            case R.id.tv_agreegiveup:// 同意放弃协作

                if (PrefsUtils.getString(LawyerCollaboratDetailsActivity.this, PrefsUtils.ACCESSKEY) == null || PrefsUtils.getString(LawyerCollaboratDetailsActivity.this, PrefsUtils.ACCESSKEY).length() == 0) {
                    ToastUtils.getUtils(LawyerCollaboratDetailsActivity.this).show("十分抱歉，您不是使用账号密码登录，暂时无法访问本页信息。请切换使用密码登录才可以访问。");
                    return;
                }

                DialogCooperateAgreeGiveup agreeGiveup = new DialogCooperateAgreeGiveup();
                agreeGiveup.showDialog(this, new DialogCooperateAgreeGiveup.Click() {
                    @Override
                    public void click() {
                        // TODO Auto-generated method stub
                        allowedGiveup();
                    }
                });
                break;
            case R.id.tv_apply_collaboration:// 申请协作

                if (PrefsUtils.getString(LawyerCollaboratDetailsActivity.this, PrefsUtils.ACCESSKEY) == null || PrefsUtils.getString(LawyerCollaboratDetailsActivity.this, PrefsUtils.ACCESSKEY).length() == 0) {
                    ToastUtils.getUtils(LawyerCollaboratDetailsActivity.this).show("十分抱歉，您不是使用账号密码登录，暂时无法访问本页信息。请切换使用密码登录才可以访问。");
                    return;
                }

                DialogCooperateAnonymous anonymous = new DialogCooperateAnonymous();
                anonymous.showDialog(this, new DialogCooperateAnonymous.Click() {
                    @Override
                    public void click(int type) {
                        // if (type==0) {//匿名
                        applyCollaboration(type);
                        // }else {//不匿名
                        //
                        // }
                    }
                });
                break;
            case R.id.tv_login:// 登录
                startActivity(new Intent().putExtra("id", id)
                        .putExtra("activity", LawyerCollaboratDetailsActivity.class.toString())
                        .setClass(context, LoginActivity.class));
                break;
            case R.id.ic_back:// 返回
                finish();
                break;
        }
    }


    private UMShareListener umShareListener = new UMShareListener() {
        @Override
        public void onResult(SHARE_MEDIA platform) {
            Log.d("plat", "platform" + platform);
            if (platform.name().equals("WEIXIN_FAVORITE")) {
                Toast.makeText(context, platform + " 收藏成功啦", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, platform + " 分享成功啦", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(context, platform + " 分享失败啦", Toast.LENGTH_SHORT).show();
            if (t != null) {
                Log.d("throw", "throw:" + t.getMessage());
            }
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(context, platform + " 分享取消了", Toast.LENGTH_SHORT).show();
        }
    };

    /**
     * 发布方----确认完成
     */
    private void completion() {
        RequestParams params = new RequestParams();
        params.addBodyParameter("id", entity.getId());
        params.addBodyParameter("uid", PrefsUtils.getString(context, PrefsUtils.OLD_UID));
        params.addBodyParameter("lawid", entity.getColl_user().getUserid());
        httpDataUtils.sendProgressPost(MyData.COMPLETION, params);
        httpDataUtils.setSucessBack(new HttpDataUtils.SucessBack() {
            @Override
            public void sucess(ResponseInfo<String> arg0) {
                if (httpDataUtils.code(arg0.result)) {
//                    finish();
//                    startActivity(new Intent().putExtra("id", id).setClass(LawyerCollaboratDetailsActivity.this,
//                            LawyerCollaboratDetailsActivity.class));
                    getAllStatus();
                } else {
                    httpDataUtils.showMsg(arg0.result);
                }
            }
        });
    }

    /**
     * 托管
     */
    private void assure() {
        startActivity(new Intent().putExtra("activity", LawyerCollaboratDetailsActivity.class.toString())
                .putExtra("money", entity.getZqprice()).putExtra("id", entity.getId())
                .setClass(context, LawyerCollaboratHostingChangeActivity.class));
        finish();
    }

    /**
     * 增加酬金
     */
    private void addMoney(String money, int c) {

//        if (entity.getIs_pay().equals("1")) {
//            startActivity(new Intent().putExtra("activity", LawyerCollaboratDetailsActivity.class.toString())
//                    .putExtra("id", entity.getId()).putExtra("price", money).putExtra("totalmoney", c).setClass(context, PayActivity.class));
//            return;
//        }
        RequestParams params = new RequestParams();
        params.addBodyParameter("id", entity.getId());
        params.addBodyParameter("uid", PrefsUtils.getString(context, PrefsUtils.OLD_UID));
        params.addBodyParameter("amount", money);
        httpDataUtils.sendProgressPost(MyData.ADDREWARD, params);
        httpDataUtils.setSucessBack(new HttpDataUtils.SucessBack() {
            @Override
            public void sucess(ResponseInfo<String> arg0) {
                if (httpDataUtils.code(arg0.result)) {
                    finish();
                    startActivity(new Intent().putExtra("id", id).putExtra("title", "00000")
                            .putExtra("from", "footprint")
                            .setClass(LawyerCollaboratDetailsActivity.this, LawyerCollaboratDetailsActivity.class));
                } else {
                    httpDataUtils.showMsg(arg0.result);
                }
            }
        });
    }

    /**
     * 发布方申请重新执行
     */
    private void applyReload() {
        RequestParams params = new RequestParams();
        params.addHeader("Authorization", PrefsUtils.getString(context, PrefsUtils.ACCESSKEY));
        params.addBodyParameter("id", entity.getId());
        httpDataUtils.sendProgressPost(MyData.APPLYRELOAD, params);
        httpDataUtils.setSucessBack(new HttpDataUtils.SucessBack() {
            @Override
            public void sucess(ResponseInfo<String> arg0) {
                if (httpDataUtils.code(arg0.result)) {
                    finish();
                    startActivity(new Intent().putExtra("id", id).putExtra("title", "00000")
                            .putExtra("from", "footprint")
                            .setClass(LawyerCollaboratDetailsActivity.this, LawyerCollaboratDetailsActivity.class));
                } else {
                    httpDataUtils.showMsg(arg0.result);
                }
            }
        });
    }

    /**
     * 延长协作时间
     */
    private void addTaskTime(String time) {
        RequestParams params = new RequestParams();
        params.addHeader("Authorization", PrefsUtils.getString(context, PrefsUtils.ACCESSKEY));
        params.addBodyParameter("id", entity.getId());
        params.addBodyParameter("hopedate", time);
        httpDataUtils.sendProgressPost(MyData.ADDTASKTIME, params);
        httpDataUtils.setSucessBack(new HttpDataUtils.SucessBack() {
            @Override
            public void sucess(ResponseInfo<String> arg0) {
                if (httpDataUtils.code(arg0.result)) {
                    finish();
                    startActivity(new Intent().putExtra("id", id).putExtra("title", "00000")
                            .putExtra("from", "footprint")
                            .setClass(LawyerCollaboratDetailsActivity.this, LawyerCollaboratDetailsActivity.class));
                } else {
                    httpDataUtils.showMsg(arg0.result);
                }
            }
        });
    }

    /**
     * 到期状态下重新开启
     */
    private void duerestart(String hopedate) {
        RequestParams params = new RequestParams();
        params.addHeader("Authorization", PrefsUtils.getString(context, PrefsUtils.ACCESSKEY));
        params.addBodyParameter("id", entity.getId());
        params.addBodyParameter("hopedate", hopedate);
        httpDataUtils.sendProgressPost(MyData.DUERESTART, params);
        httpDataUtils.setSucessBack(new HttpDataUtils.SucessBack() {
            @Override
            public void sucess(ResponseInfo<String> arg0) {
                if (httpDataUtils.code(arg0.result)) {
                    finish();
                    startActivity(new Intent().putExtra("id", id).putExtra("title", "00000")
                            .putExtra("from", "footprint")
                            .setClass(LawyerCollaboratDetailsActivity.this, LawyerCollaboratDetailsActivity.class));
                } else {
                    httpDataUtils.showMsg(arg0.result);
                }
            }
        });
    }

    /**
     * 关闭后重新开启
     */
    private void closeStart(String hopedate) {
        RequestParams params = new RequestParams();
        params.addHeader("Authorization", PrefsUtils.getString(context, PrefsUtils.ACCESSKEY));
        params.addBodyParameter("id", entity.getId());
        params.addBodyParameter("hopedate", hopedate);
        httpDataUtils.sendProgressPost(MyData.CLOSERESTART, params);
        httpDataUtils.setSucessBack(new HttpDataUtils.SucessBack() {
            @Override
            public void sucess(ResponseInfo<String> arg0) {
                if (httpDataUtils.code(arg0.result)) {
                    finish();
                    startActivity(new Intent().putExtra("id", id).putExtra("title", "00000")
                            .putExtra("from", "footprint")
                            .setClass(LawyerCollaboratDetailsActivity.this, LawyerCollaboratDetailsActivity.class));
                } else {
                    httpDataUtils.showMsg(arg0.result);
                }
            }
        });
    }

    /**
     * 关闭协作
     */
    private void closeTask() {
        RequestParams params = new RequestParams();
        params.addHeader("Authorization", PrefsUtils.getString(context, PrefsUtils.ACCESSKEY));
        params.addBodyParameter("id", entity.getId());
        httpDataUtils.sendProgressPost(MyData.CLOSETASK, params);
        httpDataUtils.setSucessBack(new HttpDataUtils.SucessBack() {
            @Override
            public void sucess(ResponseInfo<String> arg0) {
                if (httpDataUtils.code(arg0.result)) {
                    finish();
                    startActivity(new Intent().putExtra("id", id).putExtra("title", "00000")
                            .putExtra("from", "footprint")
                            .setClass(LawyerCollaboratDetailsActivity.this, LawyerCollaboratDetailsActivity.class));
                } else {
                    httpDataUtils.showMsg(arg0.result);
                }
            }
        });
    }

    /**
     * 协作方提交确认
     */
    private void sureTask() {
        RequestParams params = new RequestParams();
        params.addHeader("Authorization", PrefsUtils.getString(context, PrefsUtils.ACCESSKEY));
        params.addBodyParameter("id", entity.getId());
        httpDataUtils.sendProgressPost(MyData.SURETASK, params);
        httpDataUtils.setSucessBack(new HttpDataUtils.SucessBack() {
            @Override
            public void sucess(ResponseInfo<String> arg0) {
                if (httpDataUtils.code(arg0.result)) {
                    finish();
                    startActivity(new Intent().putExtra("id", id).putExtra("title", "00000")
                            .putExtra("from", "footprint")
                            .setClass(LawyerCollaboratDetailsActivity.this, LawyerCollaboratDetailsActivity.class));
                } else {
                    httpDataUtils.showMsg(arg0.result);
                }
            }
        });
    }

    /**
     * 同意客服裁定结果
     */
    private void agreeService() {
        RequestParams params = new RequestParams();
        params.addHeader("Authorization", PrefsUtils.getString(context, PrefsUtils.ACCESSKEY));
        params.addBodyParameter("wid", entity.getWorkorderInfo().getId());
        params.addBodyParameter("eid", entity.getId());
        httpDataUtils.sendProgressPost(MyData.AGREESERVICE, params);
        httpDataUtils.setSucessBack(new HttpDataUtils.SucessBack() {
            @Override
            public void sucess(ResponseInfo<String> arg0) {
                if (httpDataUtils.code(arg0.result)) {
                    finish();
                    startActivity(new Intent().putExtra("id", id).putExtra("title", "00000")
                            .putExtra("from", "footprint")
                            .setClass(LawyerCollaboratDetailsActivity.this, LawyerCollaboratDetailsActivity.class));
                } else {
                    httpDataUtils.showMsg(arg0.result);
                }
            }
        });
    }

    /**
     * 不同意客服裁定结果
     */
    private void disAgreeService() {
        RequestParams params = new RequestParams();
        params.addHeader("Authorization", PrefsUtils.getString(context, PrefsUtils.ACCESSKEY));
        params.addBodyParameter("wid", entity.getWorkorderInfo().getId());
        params.addBodyParameter("eid", entity.getId());
        httpDataUtils.sendProgressPost(MyData.DISAGREESERVICE, params);
        httpDataUtils.setSucessBack(new HttpDataUtils.SucessBack() {
            @Override
            public void sucess(ResponseInfo<String> arg0) {
                if (httpDataUtils.code(arg0.result)) {
                    finish();
                    startActivity(new Intent().putExtra("id", id).putExtra("title", "00000")
                            .putExtra("from", "footprint")
                            .setClass(LawyerCollaboratDetailsActivity.this, LawyerCollaboratDetailsActivity.class));
                } else {
                    httpDataUtils.showMsg(arg0.result);
                }
            }
        });
    }

    /**
     * 允许协作方放弃协作
     */
    private void allowedGiveup() {
        RequestParams params = new RequestParams();
        params.addHeader("Authorization", PrefsUtils.getString(context, PrefsUtils.ACCESSKEY));
        params.addBodyParameter("id", entity.getId());
        params.addBodyParameter("lawid", entity.getColl_user().getUserid());
        httpDataUtils.sendProgressPost(MyData.ALLOWEDGIVEUP, params);
        httpDataUtils.setSucessBack(new HttpDataUtils.SucessBack() {
            @Override
            public void sucess(ResponseInfo<String> arg0) {
                if (httpDataUtils.code(arg0.result)) {
                    finish();
                    startActivity(new Intent().putExtra("id", id).putExtra("title", "00000")
                            .putExtra("from", "footprint")
                            .setClass(LawyerCollaboratDetailsActivity.this, LawyerCollaboratDetailsActivity.class));
                } else {
                    httpDataUtils.showMsg(arg0.result);
                }
            }
        });
    }

    /**
     * 不允许协作方放弃协作
     */
    private void notAllowedGiveup() {
        RequestParams params = new RequestParams();
        params.addHeader("Authorization", PrefsUtils.getString(context, PrefsUtils.ACCESSKEY));
        params.addBodyParameter("id", entity.getId());
        params.addBodyParameter("lawid", entity.getColl_user().getUserid());
        httpDataUtils.sendProgressPost(MyData.NOTALLOWEDGIVEUP, params);
        httpDataUtils.setSucessBack(new HttpDataUtils.SucessBack() {
            @Override
            public void sucess(ResponseInfo<String> arg0) {
                if (httpDataUtils.code(arg0.result)) {
//					finish();
//					startActivity(new Intent().putExtra("id", id).putExtra("title", "00000")
//							.putExtra("from", "footprint")
//							.setClass(LawyerCollaboratDetailsActivity.this, LawyerCollaboratDetailsActivity.class));

                    getAllStatus();

                } else {
                    httpDataUtils.showMsg(arg0.result);
                }
            }
        });
    }

    /**
     * 选择某个律师作为协作方
     */
    private void areeCollaboration(String lawyer_id) {
        RequestParams params = new RequestParams();
        params.addHeader("Authorization", PrefsUtils.getString(context, PrefsUtils.ACCESSKEY));
        params.addBodyParameter("id", entity.getId());
        params.addBodyParameter("lawyer_id", lawyer_id);
        httpDataUtils.sendProgressPost(MyData.AGREECOLLABORATION, params);
        httpDataUtils.setSucessBack(new HttpDataUtils.SucessBack() {
            @Override
            public void sucess(ResponseInfo<String> arg0) {
                if (httpDataUtils.code(arg0.result)) {
                    startActivity(new Intent().putExtra("id", id).putExtra("title", "00000")
                            .putExtra("from", "footprint")
                            .setClass(LawyerCollaboratDetailsActivity.this, LawyerCollaboratDetailsActivity.class));
                    finish();
                } else {
                    httpDataUtils.showMsg(arg0.result);
                }
            }
        });
    }

    /**
     * 申请放弃协作
     *
     * @param remark 放弃协作的理由
     */
    private void applyGiveup(String remark) {
        RequestParams params = new RequestParams();
        params.addHeader("Authorization", PrefsUtils.getString(context, PrefsUtils.ACCESSKEY));
        params.addBodyParameter("id", entity.getId());
        params.addBodyParameter("abandon_reason", remark);
        httpDataUtils.sendProgressPost(MyData.APPLYGIVEUP, params);
        httpDataUtils.setSucessBack(new HttpDataUtils.SucessBack() {
            @Override
            public void sucess(ResponseInfo<String> arg0) {
                if (httpDataUtils.code(arg0.result)) {
                    finish();
                    startActivity(new Intent().putExtra("id", id).putExtra("title", "00000")
                            .putExtra("from", "footprint")
                            .setClass(LawyerCollaboratDetailsActivity.this, LawyerCollaboratDetailsActivity.class));
                } else {


                    httpDataUtils.showMsg(arg0.result);
                }
            }
        });
    }

    /**
     * 申请协作
     */
    private void applyCollaboration(int type) {
        RequestParams params = new RequestParams();
        params.addHeader("Authorization", PrefsUtils.getString(context, PrefsUtils.ACCESSKEY));
        params.addBodyParameter("coopid", entity.getId());
        params.addBodyParameter("publisher", entity.getUid());
        params.addBodyParameter("is_anonymity", type + "");

        httpDataUtils.sendProgressPost(MyData.APPLYCOLLABORATION, params);
        httpDataUtils.setSucessBack(new HttpDataUtils.SucessBack() {
            @Override
            public void sucess(ResponseInfo<String> arg0) {
                if (httpDataUtils.code(arg0.result)) {
                    finish();
                    startActivity(new Intent().putExtra("id", id).putExtra("title", "00000")
                            .putExtra("insert", false)
                            .setClass(LawyerCollaboratDetailsActivity.this, LawyerCollaboratDetailsActivity.class));

                    getAllStatus();

                } else {

                    try {
                        JSONObject object = new JSONObject(arg0.result);
                        String msg = object.optString("msg");
                        ToastUtils.getUtils(LawyerCollaboratDetailsActivity.this).show(msg);
                    } catch (JSONException e) {

                        e.printStackTrace();
                    }
                }
            }
        });
    }










    /**
     * 申请客服介入
     *
     * @param remark 客服介入的理由
     */
    private void serviceIn(String remark) {
        RequestParams params = new RequestParams();
        params.addHeader("Authorization", PrefsUtils.getString(context, PrefsUtils.ACCESSKEY));
        params.addBodyParameter("id", entity.getId());
        params.addBodyParameter("description", remark);
        if (PrefsUtils.getString(context, PrefsUtils.OLD_UID).equals(entity.getUid())) {// 发布方
            params.addBodyParameter("o_id", entity.getColl_user().getUserid());
        } else {// 协作方
            params.addBodyParameter("o_id", entity.getUid());
        }

        httpDataUtils.sendProgressPost(MyData.SERVICEIN, params);
        httpDataUtils.setSucessBack(new HttpDataUtils.SucessBack() {
            @Override
            public void sucess(ResponseInfo<String> arg0) {
                if (httpDataUtils.code(arg0.result)) {
                    finish();
                    startActivity(new Intent().putExtra("id", id).putExtra("title", "00000")
                            .putExtra("from", "footprint")
                            .setClass(LawyerCollaboratDetailsActivity.this, LawyerCollaboratDetailsActivity.class));
                } else {
                    httpDataUtils.showMsg(arg0.result);

                }
            }
        });
    }

}
