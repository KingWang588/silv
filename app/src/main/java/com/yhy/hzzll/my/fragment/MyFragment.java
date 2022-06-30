package com.yhy.hzzll.my.fragment;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.auth.AuthService;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionListener;
import com.yhy.hzzll.R;
import com.yhy.hzzll.adapter.MemberCommentAdapter;
import com.yhy.hzzll.adapter.PublishedArtclesAdapter;
import com.yhy.hzzll.entity.MemberCommentEntity;
import com.yhy.hzzll.entity.SortEntity;
import com.yhy.hzzll.entity.SuccessfulCaseEntity;
import com.yhy.hzzll.framework.Constans;
import com.yhy.hzzll.framework.HzApplication;
import com.yhy.hzzll.framework.MyData;
import com.yhy.hzzll.mian.activity.BigImageActivity;
import com.yhy.hzzll.mian.activity.LoginActivity;
import com.yhy.hzzll.mian.activity.MainActivity;
import com.yhy.hzzll.mian.activity.PCLoginActivity;
import com.yhy.hzzll.mian.activity.RegisterSecondActivity;
import com.yhy.hzzll.mian.activity.RegisterThirdActivity;
import com.yhy.hzzll.mian.entity.DataUserEntity;
import com.yhy.hzzll.mian.fragment.BaseFragment;
import com.yhy.hzzll.my.activity.ArticleDetailsActivity;
import com.yhy.hzzll.my.activity.ExclusiveCodeActivity;
import com.yhy.hzzll.my.activity.ExclusiveLawyerActivity;
import com.yhy.hzzll.my.activity.FansActivity;
import com.yhy.hzzll.my.activity.GuideViewActivity;
import com.yhy.hzzll.my.activity.MyFocusActivity;
import com.yhy.hzzll.my.activity.MyPLawyerActivity;
import com.yhy.hzzll.my.activity.NewInviteActivity;
import com.yhy.hzzll.my.activity.OrderManagementActivity;
import com.yhy.hzzll.my.activity.PersonDataLawyerActivity;
import com.yhy.hzzll.my.activity.PublishNewsActivity;
import com.yhy.hzzll.my.activity.RechargeActivity;
import com.yhy.hzzll.my.activity.ServiceAndQuoteActivity;
import com.yhy.hzzll.my.activity.SettingActivity;
import com.yhy.hzzll.my.activity.TreasureActivity;
import com.yhy.hzzll.my.view.DialogRealname;
import com.yhy.hzzll.my.view.DialogTipRealname;
import com.yhy.hzzll.utils.CacheUtils;
import com.yhy.hzzll.utils.HttpDataUtils;
import com.yhy.hzzll.utils.HttpDataUtils.FailBack;
import com.yhy.hzzll.utils.JSONTool;
import com.yhy.hzzll.utils.MyActivityManager;
import com.yhy.hzzll.utils.PrefsUtils;
import com.yhy.hzzll.utils.ToastUtils;
import com.yhy.hzzll.view.SortView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MyFragment extends BaseFragment implements
        PublishedArtclesAdapter.OnActionListenter {

    private View view;

//    @ViewInject(R.id.scroll_view)
//    private PullToZoomScrollViewEx scrollView;

    @ViewInject(R.id.ptr_lv)
    private PullToRefreshListView ptr_lv;

    @ViewInject(R.id.linear_no_login)
    private LinearLayout linear_no_login;

    @ViewInject(R.id.linear_login)
    private LinearLayout linear_login;

//    @ViewInject(R.id.ll_publish)
//    private LinearLayout ll_publish;

    @ViewInject(R.id.tv_publish_btn)
    private TextView tv_publish_btn;

    @ViewInject(R.id.tv_lawyer)
    private TextView tv_lawyer;

    @ViewInject(R.id.tv_line)
    private TextView tv_line;
    @ViewInject(R.id.iv_user_avatar)
    ImageView iv_user_avatar;
    @ViewInject(R.id.tv_user_name)
    TextView tv_user_name;
    @ViewInject(R.id.iv_certified_phone)
    ImageView iv_certified_phone;
    @ViewInject(R.id.iv_certified_email)
    ImageView iv_certified_email;
    @ViewInject(R.id.iv_certified_user)
    ImageView iv_certified_user;

    @ViewInject(R.id.tv_doc)
    TextView tv_doc;
    @ViewInject(R.id.tv_working_income)
    TextView tv_working_income;
    @ViewInject(R.id.tv_frozen_money)
    TextView tv_frozen_money;
    @ViewInject(R.id.tv_balance)
    TextView tv_balance;

    @ViewInject(R.id.tv_consulting)
    TextView tv_consulting;

    @ViewInject(R.id.tv_browse_count)
    TextView tv_browse_count;
    @ViewInject(R.id.tv_collect_count)
    TextView tv_collect_count;
    @ViewInject(R.id.tv_count_reply)
    TextView tv_count_reply;

    @ViewInject(R.id.tv_action_pay)
    TextView tv_action_pay;
    @ViewInject(R.id.ratingbar)
    RatingBar ratingbar;
    @ViewInject(R.id.ratingbar_reply)
    RatingBar ratingbar_reply;

    @ViewInject(R.id.iv_vip)
    ImageView iv_vip;

    @ViewInject(R.id.iv_no_login)
    ImageView iv_no_login;

    @ViewInject(R.id.v_lawyer)
    View v_lawyer;
    @ViewInject(R.id.v_succe)
    View v_succe;
    @ViewInject(R.id.v_text)
    View v_text;
    @ViewInject(R.id.v_comment)
    View v_comment;

    private int ScreenW;
    private int moveX;
    private int currentX;
    private int nextX;

    @ViewInject(R.id.ll_office_table)
    private LinearLayout ll_office_table;

    private HttpDataUtils httpDataUtils;
    private Activity mActivity;
    private Fragment mTab01;
    private Fragment mTab02;
    private Fragment mTab03;
    private Fragment mTab04;

    private boolean exist_paypassword;
    DataUserEntity userEntity;


    @ViewInject(R.id.tv_evaluation_num)
    TextView tv_evaluation_num;

    @ViewInject(R.id.tv_applause_rate)
    TextView tv_applause_rate;

    @ViewInject(R.id.tv_category)
    TextView tv_category;


//    @ViewInject(R.id.tv_no_data)
//    TextView tv_no_data;

    @ViewInject(R.id.linear_eva)
    LinearLayout linear_eva;

    @ViewInject(R.id.ll_publish)
    LinearLayout ll_publish;

    @ViewInject(R.id.linear_zs)
    LinearLayout linear_zs;

    @ViewInject(R.id.iv_marriage)
    ImageView iv_marriage;

    @ViewInject(R.id.iv_creditor)
    ImageView iv_creditor;

    int type = 1;

    boolean y = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        httpDataUtils = new HttpDataUtils(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_my, null);
        ViewUtils.inject(this, view);
        viewInit();
        return view;
    }

    @Override
    public void viewInit() {

        View headView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_my_head, null, false);
        View contentView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_my_content, null, false);

        ViewUtils.inject(this, headView);
        ViewUtils.inject(this, contentView);

        ptr_lv.getRefreshableView().addHeaderView(headView);
        ptr_lv.getRefreshableView().addHeaderView(contentView);

        mAdapter = new MemberCommentAdapter(getActivity(), mList);
//        ptr_lv.setEmptyView(tv_no_data);
        ptr_lv.setAdapter(mAdapter);
        ptr_lv.setMode(PullToRefreshBase.Mode.PULL_FROM_START);

        ptr_lv.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                logininfo();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {

            }
        });


        if (PrefsUtils.getString(getActivity(), PrefsUtils.AUTHORIZATION) == null || PrefsUtils.getString(getActivity(), PrefsUtils.AUTHORIZATION).isEmpty()) {
            linear_no_login.setVisibility(View.VISIBLE);
            linear_login.setVisibility(View.GONE);
        } else {
            linear_no_login.setVisibility(View.GONE);
            linear_login.setVisibility(View.VISIBLE);
            logininfo();
        }

        super.viewInit();
    }


    @Override
    public void onResume() {

        if (PrefsUtils.getString(getActivity(), PrefsUtils.AUTHORIZATION) == null || PrefsUtils.getString(getActivity(), PrefsUtils.AUTHORIZATION).isEmpty()) {
            linear_no_login.setVisibility(View.VISIBLE);
            linear_login.setVisibility(View.GONE);
        } else {
            linear_no_login.setVisibility(View.GONE);
            linear_login.setVisibility(View.VISIBLE);
            logininfo();
        }
        super.onResume();

    }

    private void checkAuthInfo() {
        RequestParams params = new RequestParams();
        params.addHeader("Authorization", PrefsUtils.getString(getActivity(), PrefsUtils.AUTHORIZATION));
        httpDataUtils.sendGet(MyData.CHECK_USER_AUTH_INFO, params);
        httpDataUtils.setSucessBack(new HttpDataUtils.SucessBack() {
            @Override
            public void sucess(ResponseInfo<String> arg0) {

                try {
                    JSONObject object = new JSONObject(arg0.result);
                    String code = JSONTool.getString(object, "code");
                    String msg = JSONTool.getString(object, "msg");
                    if (code.equals("0")) { // 成功
                        JSONObject data = JSONTool.getJsonObject(object, "data");
                        boolean status = JSONTool.getBoolean(data, "status");
                        String step = JSONTool.getString(data, "step");
                        String authcode = JSONTool.getString(data, "code");
                        String reason = JSONTool.getString(data, "reason");

                        PrefsUtils.saveString(getActivity(), PrefsUtils.AUTH_CODE, authcode);

                        if (authcode.equals("NOT_CERTIFIED")) {
                            if (step.equals("1")) { //没有填写认证资料

                                DialogTipRealname dialogTips = new DialogTipRealname();
                                dialogTips.showDialog(getActivity(), new DialogTipRealname.Click() {
                                    @Override
                                    public void buy() {
                                        startActivity(new Intent(getActivity(), RegisterSecondActivity.class));
                                    }
                                });

                            } else if (step.equals("2")) {//没有上传用户资料

                                DialogTipRealname dialogTips = new DialogTipRealname();
                                dialogTips.showDialog(getActivity(), new DialogTipRealname.Click() {
                                    @Override
                                    public void buy() {
                                        startActivity(new Intent(getActivity(), RegisterThirdActivity.class));
                                    }
                                });

                            }
                        } else if (authcode.equals("AUDIT_FAILED")) {

                            DialogRealname dialogTips = new DialogRealname();
                            dialogTips.showDialog(getActivity(), new DialogRealname.Click() {
                                @Override
                                public void buy() {
                                    startActivity(new Intent(getActivity(), RegisterSecondActivity.class));
                                }
                            }, reason);

                        }

                        if (type == 1) {
                            getEvaluations();
                        } else {
                            getArt();
                        }


                    } else {

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
    }


    int index = 1;
    private String uid;
    private String star = "";
    private String type_id = "";

    private void getEvaluations() {
        RequestParams params = new RequestParams();
        params.addQueryStringParameter("user_id", PrefsUtils.getString(mActivity, PrefsUtils.UID));
        params.addQueryStringParameter("type_id", type_id);
        params.addQueryStringParameter("star", star);
        params.addQueryStringParameter("page_num", "1000");
        httpDataUtils.sendGet(MyData.GET_EVALUATE_LIST, params);
        httpDataUtils.setSucessBack(new HttpDataUtils.SucessBack() {

            @Override
            public void sucess(ResponseInfo<String> arg0) {
                mList.clear();
                try {
                    JSONObject object = new JSONObject(arg0.result);
                    String code = JSONTool.getString(object, "code");
                    String msg = JSONTool.getString(object, "msg");
                    if (code.equals("0")) { // 成功
                        Gson gson = new Gson();
                        MemberCommentEntity memberCommentEntity = gson.fromJson(object.toString(), MemberCommentEntity.class);
                        if (memberCommentEntity.getData().getList() != null) {
                            mList.addAll(memberCommentEntity.getData().getList());
                        }

                        mAdapter.notifyDataSetChanged();
                        tv_evaluation_num.setText(memberCommentEntity.getData().getTotal() + "");

                    } else {
//						ToastUtils.getUtils(mActivity).show(msg);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        });
        httpDataUtils.setFailBack(new FailBack() {

            @Override
            public void fail(String msg) {
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    /**
     * 获取登录信息
     */
    private void logininfo() {

        RequestParams params = new RequestParams();
        params.addHeader("Authorization", PrefsUtils.getString(getActivity(), PrefsUtils.AUTHORIZATION));
        httpDataUtils.sendGet(MyData.CHECK_OUT_USER_INFO, params);
        httpDataUtils.setSucessBack(new HttpDataUtils.SucessBack() {
            @Override
            public void sucess(ResponseInfo<String> arg0) {
                ptr_lv.onRefreshComplete();
                try {
                    JSONObject object = new JSONObject(arg0.result);
                    String code = object.optString("code");
                    String msg = object.optString("msg");
                    if (code.equals("0")) {
                        Gson gson = new Gson();
                        try {
                        CacheUtils.dataUserEntity = gson.fromJson(arg0.result, DataUserEntity.class);
                        HzApplication.getInstance().getUserEntityCache().put(Constans.USER_INFO, CacheUtils.dataUserEntity);
                        userEntity = gson.fromJson(arg0.result, DataUserEntity.class);

                        if (userEntity.getData().getInfo().getAvatar() != null & userEntity.getData().getInfo().getAvatar().length() != 0) {
                            Glide.with(getActivity()).load(userEntity.getData().getInfo().getAvatar()).into(iv_user_avatar);
                        }


                        PrefsUtils.saveString(getActivity(), PrefsUtils.OLD_UID, userEntity.getData().getInfo().getOld_user_info().getOld_user_id() + "");

                        PrefsUtils.saveString(getActivity(), PrefsUtils.UPHONE, userEntity.getData().getInfo().getMobile());
                        PrefsUtils.saveString(getActivity(), PrefsUtils.UNAME, userEntity.getData().getInfo().getTruename());
                        PrefsUtils.saveString(getActivity(), PrefsUtils.ADDRESS, userEntity.getData().getInfo().getBase_region());
                        PrefsUtils.saveString(getActivity(), PrefsUtils.SPECIAL, userEntity.getData().getInfo().getLawyer_secpical());
                        PrefsUtils.saveString(getActivity(), PrefsUtils.AVATAR, userEntity.getData().getInfo().getAvatar());

                        PrefsUtils.saveString(getActivity(), PrefsUtils.UID, userEntity.getData().getInfo().getId() + "");
                        PrefsUtils.saveString(getActivity(), PrefsUtils.ACCID, userEntity.getData().getInfo().getIm_token().getAccid());
                        PrefsUtils.saveString(getActivity(), PrefsUtils.USERTOKEN, userEntity.getData().getInfo().getIm_token().getToken());
                        PrefsUtils.saveString(getActivity(), PrefsUtils.CONSULT_PRICE, userEntity.getData().getInfo().getOffer_info().getPrice());

                        PrefsUtils.saveString(getActivity(), PrefsUtils.CONSULT_OPEN, userEntity.getData().getInfo().getOffer_info().getExclusive_consult_isprice());
                        PrefsUtils.saveString(getActivity(), PrefsUtils.AUTH_STATUS, userEntity.getData().getInfo().getAuth_status());

                        final String url = userEntity.getData().getInfo().getAvatar();
                        final String name = userEntity.getData().getInfo().getTruename();

                        iv_user_avatar.setOnClickListener(new View.OnClickListener() {

                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(getActivity(), BigImageActivity.class);
                                intent.putExtra("url", url);
                                startActivity(intent);
                            }
                        });

//                            Map<UserInfoFieldEnum, Object> fields = new HashMap<>(1);
//                            fields.put(UserInfoFieldEnum.AVATAR, url);
//
//                            NIMClient.getService(UserService.class).updateUserInfo(fields)
//                                    .setCallback(new RequestCallbackWrapper<Void>() {
//                                        @Override
//                                        public void onResult(int i, Void aVoid, Throwable throwable) {
//
//                                        }
//                                    });
//
//                            Map<UserInfoFieldEnum, Object> field2 = new HashMap<>(1);
//                            field2.put(UserInfoFieldEnum.Name, name);
//                            NIMClient.getService(UserService.class).updateUserInfo(field2)
//                                    .setCallback(new RequestCallbackWrapper<Void>() {
//                                        @Override
//                                        public void onResult(int i, Void aVoid, Throwable throwable) {
//
//                                        }
//                                    });

                        tv_user_name.setText(userEntity.getData().getInfo().getTruename());
                        tv_browse_count.setText("浏览  " + userEntity.getData().getInfo().getPage_view());
                        tv_collect_count.setText("关注  " + userEntity.getData().getInfo().getCount_befollowd());
//                            tv_count_case.setText("(" + userEntity.getData().getInfo().getCount_case() + ")");
                        tv_count_reply.setText("(" + userEntity.getData().getInfo().getCount_reply() + ")");

                        String m1 = userEntity.getData().getFinance().getAmt_consult();
                        m1 = m1.substring(0, m1.length() - 3);

                        String m2 = userEntity.getData().getFinance().getAmt_case();
                        m2 = m2.substring(0, m2.length() - 3);

//                        String m3 = userEntity.getData().getFinance().getAmt_freeze();
//                        m3 = m3.substring(0, m3.length() - 3);

                        String m4 = userEntity.getData().getFinance().getAmt_balance();
                        m4 = m4.substring(0, m4.length() - 3);

                        tv_doc.setText(m1 + "元");
                        tv_working_income.setText(m2 + "元");
//                        tv_frozen_money.setText(m3 + "元");
                        tv_balance.setText(m4 + "元");

                        ratingbar_reply.setRating(Float.parseFloat(userEntity.getData().getInfo().getStart_reply()) / 2);

                        exist_paypassword = userEntity.getData().getInfo().isExist_paypassword();
                        if (exist_paypassword) {
                            PrefsUtils.saveString(getActivity(), PrefsUtils.ESCIT_PAYPSW, "1");
                        } else {
                            PrefsUtils.saveString(getActivity(), PrefsUtils.ESCIT_PAYPSW, "0");
                        }

                        PrefsUtils.saveString(getActivity(), PrefsUtils.EMAIl, userEntity.getData().getInfo().getEmail());
                        PrefsUtils.saveString(getActivity(), PrefsUtils.LAWYER_TYPE, userEntity.getData().getInfo().getLawyer_type());

                        if (userEntity.getData().getInfo().getEmail().length() != 0) {
                            iv_certified_email.setImageResource(R.drawable.iv_certified_email);
                        }

                        if (userEntity.getData().getInfo().getAuth_status().equals("已认证")) {
                            iv_certified_user.setImageResource(R.drawable.iv_certified_user);

                            if (userEntity.getData().getInfo().getLawyer_type().equals("执业律师")) {
                                iv_vip.setImageResource(R.drawable.consult_vip);
                                iv_vip.setVisibility(View.VISIBLE);
                            } else {
                                iv_vip.setImageResource(R.drawable.consult_vip_practice);
                                iv_vip.setVisibility(View.VISIBLE);
                            }
                        } else {
                            iv_vip.setVisibility(View.GONE);
                            iv_certified_user.setImageResource(R.drawable.user_infotag);
                        }

                        Log.e("PrefsUtils.FirstMY", "sucess: " + PrefsUtils.getString(getActivity(), PrefsUtils.FirstMY));

                        if (!PrefsUtils.getString(getActivity(), PrefsUtils.FirstMY).equals("4")) {
                            Log.e("PrefsUtils.FirstMY", "sucess: PrefsUtils.FirstMY != 4" );
                            PrefsUtils.saveString(getActivity(), PrefsUtils.FirstMY, "4");
                            startActivity(new Intent(getActivity(), GuideViewActivity.class));
                        } else {
                            Log.e("PrefsUtils.FirstMY", "sucess: PrefsUtils.FirstMY == 4 && index == "+ index );
                            Log.e("PrefsUtils.FirstMY", "sucess: PrefsUtils.CONSULT_OPEN == " + PrefsUtils.getString(getActivity(), PrefsUtils.CONSULT_OPEN) );
                            if (index == 1) {
                                if (PrefsUtils.getString(getActivity(), PrefsUtils.CONSULT_OPEN).equals("0")) {
//                                    startActivity(new Intent(getActivity(), GuideViewActivity.class));
                                    index = 2;
                                }

                            }
                        }

                        if (userEntity.getData().getInfo().getExclusive_lawyer_application().equals("0")) {
                            linear_zs.setVisibility(View.GONE);
                        } else if (userEntity.getData().getInfo().getExclusive_lawyer_application().equals("1")) {
                            linear_zs.setVisibility(View.VISIBLE);
                            iv_marriage.setVisibility(View.VISIBLE);
                        } else if (userEntity.getData().getInfo().getExclusive_lawyer_application().equals("2")) {
                            linear_zs.setVisibility(View.VISIBLE);
                            iv_creditor.setVisibility(View.VISIBLE);
                        } else if (userEntity.getData().getInfo().getExclusive_lawyer_application().equals("1,2")) {
                            linear_zs.setVisibility(View.VISIBLE);
                            iv_marriage.setVisibility(View.VISIBLE);
                            iv_creditor.setVisibility(View.VISIBLE);
                        }


                        checkAuthInfo();

                        } catch (Exception e) {

                        }
                    } else {

                        NIMClient.getService(AuthService.class).logout();

                        PrefsUtils.saveString(getActivity(), PrefsUtils.AUTHORIZATION, "");
                        PrefsUtils.saveString(getActivity(), PrefsUtils.REFRESH_TOKEN, "");
                        PrefsUtils.saveString(getActivity(), PrefsUtils.ACCESSKEY, "");
                        MyActivityManager.getInstance().finishAllActivity();
                        Intent intent = new Intent();
                        intent.setClass(getActivity(), LoginActivity.class);
                        startActivity(intent);

//                        getActivity().finish();

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        httpDataUtils.setFailBack(new FailBack() {

            @Override
            public void fail(String msg) {
            }
        });
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = activity;
    }

    MemberCommentAdapter mAdapter;
    ArrayList<MemberCommentEntity.DataBean.ListBean> mList = new ArrayList<>();


    private PublishedArtclesAdapter mArtAdapter;
    private ArrayList<SuccessfulCaseEntity.DataBean.ListBean> mArtList = new ArrayList<>();

    private void loadViewForCode() {

//        setSelect(3);
    }

    @OnClick({R.id.tv_treasure, R.id.tv_focus, R.id.tv_publish, R.id.tv_case, R.id.tv_track,
            R.id.tv_consulting, R.id.tv_order, R.id.tv_fans, R.id.tv_service, R.id.tv_remind,
            R.id.iv_setting, R.id.rl_person, R.id.rl_person, R.id.rl_treasure, R.id.ll_publish, R.id.tv_case,
            R.id.tv_succe, R.id.tv_text, R.id.tv_comment, R.id.tv_action_pay, R.id.tv_invitation, R.id.iv_no_login,
            R.id.tv_applause_rate, R.id.tv_category,R.id.iv_scan})
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.iv_scan:

                if (PrefsUtils.getString(getActivity(), PrefsUtils.AUTHORIZATION) == null || PrefsUtils.getString(getActivity(), PrefsUtils.AUTHORIZATION).isEmpty()) {
                    startActivity(new Intent().putExtra("tab", 4).putExtra("activity", MainActivity.class.toString()).setClass(getActivity(), LoginActivity.class));
                    return;
                }

                PermissionListener permissionListener = new PermissionListener() {
                    @Override
                    public void onSucceed(int requestCode, @NonNull List<String> grantPermissions) {
                        Intent intent_1 = new Intent(getActivity(), CaptureActivity.class);
                        startActivityForResult(intent_1, 299);
                    }
                    @Override
                    public void onFailed(int requestCode, @NonNull List<String> deniedPermissions) {

                    }
                };

                AndPermission.with(getActivity())
                        .requestCode(102)
                        .permission(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        .callback(permissionListener)
                        .start();

                break;

            case R.id.tv_action_pay:
                startActivity(new Intent(getActivity(),RechargeActivity.class));
                break;
            case R.id.tv_applause_rate:
                sortViewInit(tv_applause_rate);
                break;
            case R.id.tv_category:
                sortViewInit2(tv_category);
                break;

            case R.id.iv_no_login:
                startActivity(new Intent().putExtra("tab", 4).putExtra("activity", MainActivity.class.toString()).setClass(getActivity(), LoginActivity.class));
                break;

            case R.id.tv_invitation:

                if (PrefsUtils.getString(getActivity(), PrefsUtils.AUTHORIZATION) == null || PrefsUtils.getString(getActivity(), PrefsUtils.AUTHORIZATION).isEmpty()) {
                    startActivity(new Intent().putExtra("tab", 4).putExtra("activity", MainActivity.class.toString()).setClass(getActivity(), LoginActivity.class));
                    return;
                }

                startActivity(new Intent().setClass(getActivity(), NewInviteActivity.class));
                break;

            case R.id.tv_treasure:// 我的财富

                if (PrefsUtils.getString(getActivity(), PrefsUtils.AUTHORIZATION) == null || PrefsUtils.getString(getActivity(), PrefsUtils.AUTHORIZATION).isEmpty()) {
                    startActivity(new Intent().putExtra("tab", 4).putExtra("activity", MainActivity.class.toString()).setClass(getActivity(), LoginActivity.class));
                    return;
                }

                startActivity(new Intent().setClass(getActivity(), TreasureActivity.class));
                break;

            case R.id.tv_focus:// 我的关注

                if (PrefsUtils.getString(getActivity(), PrefsUtils.AUTHORIZATION) == null || PrefsUtils.getString(getActivity(), PrefsUtils.AUTHORIZATION).isEmpty()) {
                    startActivity(new Intent().putExtra("tab", 4).putExtra("activity", MainActivity.class.toString()).setClass(getActivity(), LoginActivity.class));
                    return;
                }


                if (!PrefsUtils.getString(getActivity(), PrefsUtils.AUTH_STATUS).equals("已认证")) {
                    ToastUtils.getUtils(getActivity()).show("您未完成认证！请先进行实名认证。");
                    return;
                }


                startActivity(new Intent().setClass(getActivity(), ExclusiveLawyerActivity.class));
                break;

            case R.id.tv_publish:// 我的协作

                if (PrefsUtils.getString(getActivity(), PrefsUtils.AUTHORIZATION) == null || PrefsUtils.getString(getActivity(), PrefsUtils.AUTHORIZATION).isEmpty()) {
                    startActivity(new Intent().putExtra("tab", 4).putExtra("activity", MainActivity.class.toString()).setClass(getActivity(), LoginActivity.class));
                    return;
                }
//
//                if (PrefsUtils.getString(getActivity(), PrefsUtils.ACCESSKEY) == null || PrefsUtils.getString(getActivity(), PrefsUtils.ACCESSKEY).equals("")) {
//                    ToastUtils.getUtils(getActivity()).show("十分抱歉，您不是使用账号密码登录，暂时无法访问本页信息。请切换使用密码登录才可以访问。");
//                    return;
//                }

                startActivity(new Intent().setClass(getActivity(), MyPLawyerActivity.class));
                break;

            case R.id.tv_track://我的关注
                if (PrefsUtils.getString(getActivity(), PrefsUtils.AUTHORIZATION) == null || PrefsUtils.getString(getActivity(), PrefsUtils.AUTHORIZATION).isEmpty()) {
                    startActivity(new Intent().putExtra("tab", 4).putExtra("activity", MainActivity.class.toString()).setClass(getActivity(), LoginActivity.class));
                    return;
                }


//                if (!PrefsUtils.getString(getActivity(), PrefsUtils.AUTH_STATUS).equals("已认证")) {
//                    ToastUtils.getUtils(getActivity()).show("您未完成认证！请先进行实名认证。");
//                    return;
//                }


                startActivity(new Intent().setClass(getActivity(), MyFocusActivity.class));
//                startActivity(new Intent().setClass(getActivity(), MyFootmarkActivity.class));
                break;


            case R.id.tv_fans://

                if (PrefsUtils.getString(getActivity(), PrefsUtils.AUTHORIZATION) == null || PrefsUtils.getString(getActivity(), PrefsUtils.AUTHORIZATION).isEmpty()) {
                    startActivity(new Intent().putExtra("tab", 4).putExtra("activity", MainActivity.class.toString()).setClass(getActivity(), LoginActivity.class));
                    return;
                }

                startActivity(new Intent().setClass(getActivity(), FansActivity.class));
                break;

            case R.id.tv_remind:// 我的提醒
//                startActivity(new Intent().setClass(getActivity(), MyRemindActivity.class));
                break;

            case R.id.tv_case:

                if (PrefsUtils.getString(getActivity(), PrefsUtils.AUTHORIZATION) == null || PrefsUtils.getString(getActivity(), PrefsUtils.AUTHORIZATION).isEmpty()) {
                    startActivity(new Intent().putExtra("tab", 4).putExtra("activity", MainActivity.class.toString()).setClass(getActivity(), LoginActivity.class));
                    return;
                }

                if (!PrefsUtils.getString(getActivity(), PrefsUtils.AUTH_STATUS).equals("已认证")) {
                    ToastUtils.getUtils(getActivity()).show("您未完成认证！请先进行实名认证。");
                    return;
                }


                if (PrefsUtils.getString(getActivity(), PrefsUtils.CONSULT_OPEN).equals("0")) {
                    ToastUtils.getUtils(getActivity()).show("您还未报价，请先进行服务报价！");
                    return;
                }


                startActivity(new Intent(getActivity(), ExclusiveCodeActivity.class));
                break;

            case R.id.tv_consulting: //服务报价

                if (PrefsUtils.getString(getActivity(), PrefsUtils.AUTHORIZATION) == null || PrefsUtils.getString(getActivity(), PrefsUtils.AUTHORIZATION).isEmpty()) {
                    startActivity(new Intent().putExtra("tab", 4).putExtra("activity", MainActivity.class.toString()).setClass(getActivity(), LoginActivity.class));
                    return;
                }

                startActivity(new Intent().setClass(getActivity(), ServiceAndQuoteActivity.class)
                        .putExtra("office", userEntity.getData().getInfo().getOffer_info().getExclusive_consult_isprice() + "")
                        .putExtra("price", userEntity.getData().getInfo().getOffer_info().getPrice()));
                break;

            case R.id.tv_order:// 我的订单

                if (PrefsUtils.getString(getActivity(), PrefsUtils.AUTHORIZATION) == null || PrefsUtils.getString(getActivity(), PrefsUtils.AUTHORIZATION).isEmpty()) {
                    startActivity(new Intent().putExtra("tab", 4).putExtra("activity", MainActivity.class.toString()).setClass(getActivity(), LoginActivity.class));
                    return;
                }

                startActivity(new Intent().setClass(mActivity, OrderManagementActivity.class));
                break;

            case R.id.iv_setting:// 设置
                startActivity(new Intent().setClass(getActivity(), SettingActivity.class));
                break;

            case R.id.rl_person:// 打开个人资料
                Intent intent = new Intent(mActivity, PersonDataLawyerActivity.class);
                startActivity(intent);
                break;

            case R.id.rl_treasure:// 打开我的财富

                if (PrefsUtils.getString(getActivity(), PrefsUtils.AUTHORIZATION) == null || PrefsUtils.getString(getActivity(), PrefsUtils.AUTHORIZATION).isEmpty()) {
                    startActivity(new Intent().putExtra("tab", 4).putExtra("activity", MainActivity.class.toString()).setClass(getActivity(), LoginActivity.class));
                    return;
                }

                startActivity(new Intent().setClass(getActivity(), TreasureActivity.class));
                break;

            case R.id.tv_text:// 文章发表

                if (PrefsUtils.getString(getActivity(), PrefsUtils.AUTHORIZATION) == null || PrefsUtils.getString(getActivity(), PrefsUtils.AUTHORIZATION).isEmpty()) {
                    startActivity(new Intent().putExtra("tab", 4).putExtra("activity", MainActivity.class.toString()).setClass(getActivity(), LoginActivity.class));
                    return;
                }
                type = 2;
//                setSelect(2);
                ll_publish.setVisibility(View.VISIBLE);
                linear_eva.setVisibility(View.GONE);
                mArtAdapter = new PublishedArtclesAdapter(getActivity(), mArtList, false);
//                ptr_lv.setEmptyView(tv_no_data);
                ptr_lv.setAdapter(mArtAdapter);
                ptr_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        if (i < 3) {

                        } else {
                            startActivity(new Intent().putExtra("id", mArtList.get(i - 3).getId() + "").putExtra("type", "article").setClass(getActivity(), ArticleDetailsActivity.class));
                        }
                    }
                });

                v_comment.setVisibility(View.INVISIBLE);
                v_text.setVisibility(View.VISIBLE);
                getArt();

                break;
            case R.id.tv_comment:// 会员评论

                type = 1;
                if (PrefsUtils.getString(getActivity(), PrefsUtils.AUTHORIZATION) == null || PrefsUtils.getString(getActivity(), PrefsUtils.AUTHORIZATION).isEmpty()) {
                    startActivity(new Intent().putExtra("tab", 4).putExtra("activity", MainActivity.class.toString()).setClass(getActivity(), LoginActivity.class));
                    return;
                }

                v_comment.setVisibility(View.VISIBLE);
                v_text.setVisibility(View.INVISIBLE);
                ll_publish.setVisibility(View.GONE);
                linear_eva.setVisibility(View.VISIBLE);

                mAdapter = new MemberCommentAdapter(getActivity(), mList);
//                ptr_lv.setEmptyView(tv_no_data);
                ptr_lv.setAdapter(mAdapter);
                ptr_lv.setOnItemClickListener(null);
                getEvaluations();
                break;

            case R.id.ll_publish:
                Intent intent2 = new Intent(mActivity, PublishNewsActivity.class);
                startActivity(intent2);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 299) {
            //处理扫描结果（在界面上显示）
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);

                    if (result.contains("silv_qlogin")){

                        try{
                            JSONObject object = new JSONObject(result);
                            String sid = object.optString("sid");


                            startActivity(new Intent(getActivity(),PCLoginActivity.class).putExtra("sid",sid));

                        }catch (Exception e){

                        }
                    }else {
                        ToastUtils.getUtils(getActivity()).show("请扫描云盘二维码");
                    }

                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    Toast.makeText(getActivity(), "解析二维码失败", Toast.LENGTH_LONG).show();
                }
            }
        }



        super.onActivityResult(requestCode, resultCode, data);
    }

    private void getArt() {
        RequestParams params = new RequestParams();
//        params.addHeader("Authorization", PrefsUtils.getString(mActivity, PrefsUtils.ACCESSKEY));
        params.addQueryStringParameter("cur_page", "1");
        params.addQueryStringParameter("author_id", PrefsUtils.getString(mActivity, PrefsUtils.UID));
        httpDataUtils.sendGet(MyData.OFFICEHOMEARTICLE, params);
        httpDataUtils.setSucessBack(new HttpDataUtils.SucessBack() {

            @Override
            public void sucess(ResponseInfo<String> arg0) {

                try {

                    mArtList.clear();
                    JSONObject object = new JSONObject(arg0.result);
                    String code = JSONTool.getString(object, "code");
                    String msg = JSONTool.getString(object, "msg");
                    if (code.equals(Constans.SUCCESS_CODE)) { // 成功
                        SuccessfulCaseEntity successfulCaseEntity = gson.fromJson(object.toString(), SuccessfulCaseEntity.class);

                        mArtList.addAll(successfulCaseEntity.getData().getList());
                        mArtAdapter.notifyDataSetChanged();

                    } else {
                        // ToastUtils.getUtils(mActivity).show(msg);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        });
        httpDataUtils.setFailBack(new FailBack() {

            @Override
            public void fail(String msg) {
            }
        });


    }


    private void showDiolog(final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view2 = LayoutInflater.from(getActivity()).inflate(R.layout.view_dialog_delece_consult_reply, null);
        view2.setBackgroundResource(R.drawable.background_view_dialog);
        builder.setView(view2);
        final Dialog dialog = builder.create();

        TextView tv_title = (TextView) view2.findViewById(R.id.tv_1);
        TextView tv_content = (TextView) view2.findViewById(R.id.tv_2);

        tv_title.setText("删除文章");
        tv_content.setText("删除文章后不可恢复，是否确认删除");

        TextView tv_yes = (TextView) view2.findViewById(R.id.tv_yes);
        TextView tv_no = (TextView) view2.findViewById(R.id.tv_no);
        tv_yes.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                deleteData(position);
                dialog.cancel();
            }
        });

        tv_no.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        dialog.show();
    }


    /**
     * 删除
     */
    private void deleteData(final int position) {
        RequestParams params = new RequestParams();
        params.addHeader("Authorization", PrefsUtils.getString(mActivity, PrefsUtils.AUTHORIZATION));
        params.addBodyParameter("id", mList.get(position).getId() + "");
        httpDataUtils.sendProgressPost(MyData.DELETE_ARTICLE, params);
        httpDataUtils.setSucessBack(new HttpDataUtils.SucessBack() {

            @Override
            public void sucess(ResponseInfo<String> arg0) {
                try {
                    JSONObject object = new JSONObject(arg0.result);
                    String code = object.optString("code");
                    String msg = object.optString("message");
                    if (code.equals("0")) {
                        getArt();
                    }
                    ToastUtils.getUtils(mActivity).show(msg);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    @Override
    public void onDeleteListener(View v, int position) {
        showDiolog(position);
    }


    private void setSelect(int i) {
        FragmentManager fm = getChildFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        hideFragment(transaction);
        switch (i) {

            case 2:
                if (mTab03 == null) { // 文章发布
                    mTab03 = PublishedArticlesFragment.newInstance(PrefsUtils.getString(mActivity, PrefsUtils.UID), false);
                    transaction.add(R.id.id_content, mTab03);
                } else {
                    transaction.show(mTab03);
                }
                v_lawyer.setVisibility(View.INVISIBLE);
                v_succe.setVisibility(View.INVISIBLE);
                v_text.setVisibility(View.VISIBLE);
                v_comment.setVisibility(View.INVISIBLE);
                break;
            case 3:
                if (mTab04 == null) { // 会员评论
                    mTab04 = MembersCommentFragment.newInstance(PrefsUtils.getString(mActivity, PrefsUtils.UID), true);
                    transaction.add(R.id.id_content, mTab04);
                } else {
                    transaction.show(mTab04);
                }
                v_lawyer.setVisibility(View.INVISIBLE);
                v_succe.setVisibility(View.INVISIBLE);
                v_text.setVisibility(View.INVISIBLE);
                v_comment.setVisibility(View.VISIBLE);
                break;
            default:
                break;
        }
        transaction.commit();
    }

    private void hideFragment(FragmentTransaction transaction) {

        if (mTab02 != null) {
            transaction.hide(mTab02);
        }
        if (mTab03 != null) {
            transaction.hide(mTab03);
        }
        if (mTab04 != null) {
            transaction.hide(mTab04);
        }
    }

    private void sortViewInit(TextView view) {

        List<SortEntity> sortList = new ArrayList<SortEntity>();
        sortList.clear();
        sortList.add(new SortEntity("全部", "1", "a", false));
        sortList.add(new SortEntity("好评", "2", "c", false));
        sortList.add(new SortEntity("中评", "3", "e", false));
        sortList.add(new SortEntity("差评", "4", "e", false));

        SortView sortView = new SortView();
        sortView.setSortViews(getActivity(), view, sortList);
        sortView.setSelects(new SortView.Select() {
            @Override
            public void index(int index) {
                if (index == 0) {
                    star = "";
                } else if (index == 1) {
                    star = "3";
                } else if (index == 2) {
                    star = "2";
                } else if (index == 3) {
                    star = "1";
                }
                handler.sendEmptyMessage(1);
            }
        });
    }

    /**
     * 好评率
     */
    private void sortViewInit2(TextView view) {

        List<SortEntity> sortList = new ArrayList<SortEntity>();
        sortList.clear();
        sortList.add(new SortEntity("全部", "0", "a", false));
        sortList.add(new SortEntity("快速咨询", "1", "e", false));
        sortList.add(new SortEntity("专属咨询", "2", "e", false));
//        sortList.add(new SortEntity("律师协作", "3", "e", false));

        SortView sortView = new SortView();
        sortView.setSortViews(getActivity(), view, sortList);
        sortView.setSelects(new SortView.Select() {
            @Override
            public void index(int index) {
                if (index == 0) {
                    type_id = "";
                } else if (index == 1) {
                    type_id = "1";
                } else if (index == 2) {
                    type_id = "2";
                } else if (index == 3) {
                    type_id = "3";
                }
                handler.sendEmptyMessage(1);
            }
        });
    }

    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            getEvaluations();
            super.handleMessage(msg);

        }
    };

}
