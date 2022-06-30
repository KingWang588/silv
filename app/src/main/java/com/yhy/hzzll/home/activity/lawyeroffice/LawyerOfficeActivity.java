package com.yhy.hzzll.home.activity.lawyeroffice;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.netease.nim.uikit.business.session.constant.Extras;
import com.umeng.socialize.Config;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.utils.Log;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionListener;
import com.yhy.hzzll.R;
import com.yhy.hzzll.entity.ArticleEntity;
import com.yhy.hzzll.entity.Footprint;
import com.yhy.hzzll.entity.OtherUserEntity;
import com.yhy.hzzll.entity.ServiceAndQuoteEntity;
import com.yhy.hzzll.framework.HzApplication;
import com.yhy.hzzll.framework.MyData;
import com.yhy.hzzll.home.entity.LawyerInfoentity;
import com.yhy.hzzll.home.fragment.LawyerIntroductionFragment;
import com.yhy.hzzll.message.MyNewMessageActivity;
import com.yhy.hzzll.mian.activity.BaseActivity;
import com.yhy.hzzll.mian.activity.BigImageActivity;
import com.yhy.hzzll.mian.activity.LoginActivity;
import com.yhy.hzzll.my.fragment.MembersCommentFragment;
import com.yhy.hzzll.my.fragment.PublishedArticlesFragment;
import com.yhy.hzzll.session.SessionHelper;
import com.yhy.hzzll.utils.DateUtils;
import com.yhy.hzzll.utils.HttpDataUtils;
import com.yhy.hzzll.utils.JSONTool;
import com.yhy.hzzll.utils.LogUtils;
import com.yhy.hzzll.utils.PrefsUtils;
import com.yhy.hzzll.utils.ToastUtils;
import com.yhy.hzzll.view.DialogLoading;
import com.yhy.hzzll.view.PullToZoomScrollViewEx;
import com.yhy.hzzll.view.SharePopupWindow;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * 律师办公室
 *
 * @author Yang
 */
public class LawyerOfficeActivity extends BaseActivity {

    @ViewInject(R.id.scroll_view)
    private PullToZoomScrollViewEx scrollView;


    @ViewInject(R.id.tv_lawyer)
    private TextView tv_lawyer;

    @ViewInject(R.id.iv_user_avatar)
    ImageView iv_user_avatar;

    @ViewInject(R.id.iv_vip)
    ImageView iv_vip;

    @ViewInject(R.id.tv_user_name)
    TextView tv_user_name;

    @ViewInject(R.id.iv_certified_phone)
    ImageView iv_certified_phone;
    @ViewInject(R.id.iv_certified_email)
    ImageView iv_certified_email;
    @ViewInject(R.id.iv_certified_user)
    ImageView iv_certified_user;

    @ViewInject(R.id.ratingbar_reply)
    RatingBar ratingbar_reply;
    @ViewInject(R.id.ratingbar_case)
    RatingBar ratingbar_case;

    @ViewInject(R.id.tv_browse_count)
    TextView tv_browse_count;
    @ViewInject(R.id.tv_collect_count)
    TextView tv_collect_count;

    @ViewInject(R.id.tv_count_reply)
    TextView tv_count_reply;
    @ViewInject(R.id.tv_count_case)
    TextView tv_count_case;
    @ViewInject(R.id.tv_follow)
    TextView tv_follow;

    @ViewInject(R.id.v_lawyer_introduction)
    View v_lawyer_introduction;
    @ViewInject(R.id.v_lawyer)
    View v_lawyer;
    @ViewInject(R.id.v_succe)
    View v_succe;
    @ViewInject(R.id.v_text)
    View v_text;
    @ViewInject(R.id.v_comment)
    View v_comment;
    @ViewInject(R.id.tv_action_focus)
    TextView tv_action_focus;
    @ViewInject(R.id.linear_server_and_quote_content)
    LinearLayout linear_server_and_quote_content;

    @ViewInject(R.id.tv_price)
    TextView tv_price;

    @ViewInject(R.id.ll_bottom_layout)
    private LinearLayout ll_bottom_layout;

    LawyerInfoentity lawyerInfoentity;

    private int ScreenW;
    private int moveX;
    private int currentX;
    private int nextX;

    String iamgePath = "";

    /**
     * 记录选项
     */
    private int index = 0;

    /**
     * 服务商个人中心--发表文章列表
     */
    private ArticleEntity entity;

    private Fragment mTab01;
    private Fragment mTab02;
    private Fragment mTab03;
    private Fragment mTab04;
    private Fragment mTab05;
    public static final String USER_ID_INTENT = "USER_ID_INTENT";
    private String uid;
    private String accessKey;

    private String account ;
    private boolean isFocus;
    /**
     * 是否显示更多
     */
    private boolean isViewMore;

    private List<ServiceAndQuoteEntity> list;

    private OtherUserEntity otherUserEntity;

    @Override
    protected void onCreate(Bundle arg0) {
        setContentView(R.layout.activity_lawyer_office);
        super.onCreate(arg0);
        ViewUtils.inject(this);
        loadViewForCode();
        viewInit();
    }


    private void viewInit() {

        uid = getIntent().getStringExtra("user_id");
        LogUtils.logE(uid+"");

        if (getIntent().getBooleanExtra("insert",true)){
            Footprint footprint = new Footprint(uid, DateUtils.getCurrentDate(),getIntent().getStringExtra("title"),"律师办公室");
            HzApplication.getDaoInstant().getFootprintDao().insert(footprint);
        }

        // 是自己就隐藏掉关注 和联系ta 按钮
        if (PrefsUtils.getString(context, PrefsUtils.UID).equals(uid)) {
            ll_bottom_layout.setVisibility(View.GONE);
        } else {
            ll_bottom_layout.setVisibility(View.VISIBLE);
        }
        getUserInfoById();

    }


    private void loadViewForCode() {
        View headView = LayoutInflater.from(context).inflate(R.layout.activity_lawyer_office_head, null, false);
        View contentView = LayoutInflater.from(context).inflate(R.layout.activity_lawyer_office_content, null, false);

        ViewUtils.inject(this, headView);
        ViewUtils.inject(this, contentView);

        scrollView.setZoomEnabled(false);
        scrollView.setHeaderView(headView);
        scrollView.setScrollContentView(contentView);
    }



    @OnClick({R.id.tv_lawyer, R.id.tv_succe, R.id.tv_text, R.id.tv_comment, R.id.tv_lawyer_introduction,
            R.id.tv_action_focus, R.id.tv_contact_him, R.id.tv_contact_me, R.id.tv_share,R.id.tv_follow})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_follow:
               LogUtils.logE("点击关注！");
                follow();
                break;
            case R.id.tv_lawyer_introduction: // 律师简介
                setSelect(0);
                break;
            case R.id.tv_lawyer:// 律师风采
//			setSelect(1);
                break;
            case R.id.tv_succe:// 成功案例
                setSelect(2);
                break;
            case R.id.tv_text:// 文章发表
                setSelect(3);
                break;
            case R.id.tv_comment:// 会员评论
                setSelect(4);
                break;
            case R.id.tv_action_focus:
//			getCollectOrCollect();
                break;
            case R.id.tv_contact_him: // 联系他


                if (PrefsUtils.getString(context, PrefsUtils.AUTHORIZATION) == null || PrefsUtils.getString(context, PrefsUtils.AUTHORIZATION).isEmpty()){
                    startActivity(new Intent().putExtra("activity", LawyerOfficeActivity.class.toString()).setClass(context, LoginActivity.class));
                }else{
                    if (!account.equals("")){

                        PermissionListener permissionListener = new PermissionListener() {
                            @Override
                            public void onSucceed(int requestCode, @NonNull List<String> grantPermissions) {
                                Intent intent = new Intent();
                                intent.putExtra(Extras.EXTRA_ACCOUNT, account);
                                intent.putExtra("name", tv_user_name.getText().toString());
                                intent.putExtra(Extras.EXTRA_CUSTOMIZATION, SessionHelper.getP2pCustomization());
                                intent.setClass(LawyerOfficeActivity.this, MyNewMessageActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                                startActivity(intent);
                            }

                            @Override
                            public void onFailed(int requestCode, @NonNull List<String> deniedPermissions) {
                            }
                        };

                        AndPermission.with(LawyerOfficeActivity.this)
                                .requestCode(102)
                                .permission(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.RECORD_AUDIO)
                                .callback(permissionListener)
                                .start();


                    }else{
                        Toast.makeText(LawyerOfficeActivity.this,"暂未开通即时聊天",Toast.LENGTH_SHORT).show();
                    }
                }


                break;
            case R.id.tv_contact_me: // 让他联系我

                break;

            case R.id.tv_share:
                final String text = tv_user_name.getText().toString() + "    律师 ";
                SharePopupWindow popupWindow = new SharePopupWindow(this, new SharePopupWindow.Click() {
                    @Override
                    public void Select(SHARE_MEDIA shareStatus) {
//                        UMImage image = new UMImage(context, R.drawable.app2);
                        UMImage image = new UMImage(context, iamgePath);
                        switch (shareStatus) {
                            case WEIXIN:
                                Config.OpenEditor = true;
                                Config.dialog = new DialogLoading().showDialog(LawyerOfficeActivity.this);
                                new ShareAction(LawyerOfficeActivity.this).setPlatform(SHARE_MEDIA.WEIXIN)
                                        .setCallback(umShareListener).withText(text).withMedia(image)
                                        // .withMedia(new
                                        // UMEmoji(context,"http://img.newyx.net/news_img/201306/20/1371714170_1812223777.gif"))
//                                        .withTargetUrl(MyData.MOBILE_URL + "/lawyers/" + uid)
                                        .withTargetUrl(MyData.LAWYERS_DETIAL + uid)
                                        .share();
                                break;
                            case WEIXIN_CIRCLE:
                                // UMImage image = new UMImage(context,
                                // "http://www.umeng.com/images/pic/social/integrated_3.png");
                                Config.OpenEditor = true;
                                Config.dialog = new DialogLoading().showDialog(LawyerOfficeActivity.this);
                                new ShareAction(LawyerOfficeActivity.this).setPlatform(SHARE_MEDIA.WEIXIN_CIRCLE)
                                        .setCallback(umShareListener).withTitle("私律全国律师").withText(text).withMedia(image)
                                        .withTargetUrl(MyData.LAWYERS_DETIAL + uid).share();
                                break;
                            case QQ:
                                // UMImage image = new UMImage(context,
                                // "http://www.umeng.com/images/pic/social/integrated_3.png");
                                Config.OpenEditor = true;
                                Config.dialog = new DialogLoading().showDialog(LawyerOfficeActivity.this);
                                new ShareAction(LawyerOfficeActivity.this).setPlatform(SHARE_MEDIA.QQ)
                                        .setCallback(umShareListener).withTitle("私律全国律师").withText(text).withMedia(image)
                                        // .withMedia(music)
                                        .withTargetUrl(MyData.LAWYERS_DETIAL + uid).share();
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
                                    ActivityCompat.requestPermissions(LawyerOfficeActivity.this, mPermissionList, 123);
                                }
                                Config.OpenEditor = true;
                                Config.dialog = new DialogLoading().showDialog(LawyerOfficeActivity.this);
                                new ShareAction(LawyerOfficeActivity.this).setPlatform(SHARE_MEDIA.SINA)
                                        .setCallback(umShareListener).withText(MyData.MOBILE_URL + "/lawyers/detail/" + uid)
                                        .share();
                                break;
                            default:
                                break;
                        }
                    }
                });
                popupWindow.showPopupWindow(view);
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

    private void setSelect(int i) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        hideFragment(transaction);
        switch (i) {
            case 0:
                if (mTab01 == null) {
                    mTab01 = LawyerIntroductionFragment.newInstance(uid);
                    transaction.add(R.id.id_content, mTab01);
                } else {
                    transaction.show(mTab01);
                }
                v_lawyer_introduction.setVisibility(View.VISIBLE);
                v_lawyer.setVisibility(View.INVISIBLE);
                v_succe.setVisibility(View.INVISIBLE);
                v_text.setVisibility(View.INVISIBLE);
                v_comment.setVisibility(View.INVISIBLE);
                break;
//
//            case 2:
//                if (mTab03 == null) { // 成功案例
//                    mTab03 = SuccessfulCaseFragment.newInstance(uid, true);
//                    transaction.add(R.id.id_content, mTab03);
//                } else {
//                    transaction.show(mTab03);
//                }
//                v_lawyer_introduction.setVisibility(View.INVISIBLE);
//                v_lawyer.setVisibility(View.INVISIBLE);
//                v_succe.setVisibility(View.VISIBLE);
//                v_text.setVisibility(View.INVISIBLE);
//                v_comment.setVisibility(View.INVISIBLE);
//                break;
            case 3:
                if (mTab04 == null) { // 文章发布
                    mTab04 = PublishedArticlesFragment.newInstance(uid, true);
                    transaction.add(R.id.id_content, mTab04);
                } else {
                    transaction.show(mTab04);
                }
                v_lawyer_introduction.setVisibility(View.INVISIBLE);
                v_lawyer.setVisibility(View.INVISIBLE);
                v_succe.setVisibility(View.INVISIBLE);
                v_text.setVisibility(View.VISIBLE);
                v_comment.setVisibility(View.INVISIBLE);
                break;
            case 4:
                if (mTab05 == null) { // 会员评论
                    mTab05 = MembersCommentFragment.newInstance(uid, false);
                    transaction.add(R.id.id_content, mTab05);
                } else {
                    transaction.show(mTab05);
                }
                v_lawyer_introduction.setVisibility(View.INVISIBLE);
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
        if (mTab01 != null) {
            transaction.hide(mTab01);
        }
        if (mTab02 != null) {
            transaction.hide(mTab02);
        }
        if (mTab03 != null) {
            transaction.hide(mTab03);
        }
        if (mTab04 != null) {
            transaction.hide(mTab04);
        }
        if (mTab05 != null) {
            transaction.hide(mTab05);
        }
    }

    /**
     * 关注
     */
	private void follow() {
		RequestParams params = new RequestParams();
        if (PrefsUtils.getString(LawyerOfficeActivity.this, PrefsUtils.AUTHORIZATION)!=null&&!PrefsUtils.getString(LawyerOfficeActivity.this, PrefsUtils.AUTHORIZATION).equals("")){
            params.addHeader("Authorization", PrefsUtils.getString(LawyerOfficeActivity.this, PrefsUtils.AUTHORIZATION));
        }else{
            ToastUtils.getUtils(LawyerOfficeActivity.this).show("您还未登录！");
            return;
        }
		params.addQueryStringParameter("luser_id", uid);
//		params.addQueryStringParameter("puser_id", PrefsUtils.getString(LawyerOfficeActivity.this,PrefsUtils.UID));

        if (tv_follow.getText().equals("关注")){
            params.addQueryStringParameter("action","true");
        }else{
            params.addQueryStringParameter("action","flase");
        }

		httpDataUtils.sendProgressPost(MyData.FOLLOW, params);
		httpDataUtils.setSucessBack(new HttpDataUtils.SucessBack() {
			@Override
			public void sucess(ResponseInfo<String> arg0) {
				try {
					JSONObject object = new JSONObject(arg0.result);
					String code = JSONTool.getString(object, "code");
					String msg = JSONTool.getString(object, "message");
					if (code.equals("0")) { // 成功

                        if (tv_follow.getText().equals("关注")){
                            tv_follow.setText("已关注");
                            tv_collect_count.setText((lawyerInfoentity.getData().getFollow()+1)+"");
                        }else{
                            tv_follow.setText("关注");


                            if(lawyerInfoentity.getData().getFollow()== 0){
                                tv_collect_count.setText((lawyerInfoentity.getData().getFollow())+"");
                            }else{
                                tv_collect_count.setText((lawyerInfoentity.getData().getFollow())+"");
                            }

                        }

					} else {
//
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		});
	}


    /**
     * 获取用户信息
     */
    private void getUserInfoById() {
        RequestParams params = new RequestParams();
        params.addQueryStringParameter("userid", PrefsUtils.getString(LawyerOfficeActivity.this,PrefsUtils.UID));
        LogUtils.logE( PrefsUtils.getString(LawyerOfficeActivity.this,PrefsUtils.UID)+"");
        params.addQueryStringParameter("lawyerid", uid);

        httpDataUtils.sendProgressGet(MyData.GET_LAWYERINFO_BY_ID, params);
        httpDataUtils.setSucessBack(new HttpDataUtils.SucessBack() {
            @Override
            public void sucess(ResponseInfo<String> arg0) {
                try {

                    LogUtils.logE(arg0.result);
                    JSONObject object = new JSONObject(arg0.result);
                    String code = object.optString("code");
                    String msg = object.optString("msg");
                    if (code.equals("0")) {
                        Gson gson = new Gson();
                        lawyerInfoentity = gson.fromJson(arg0.result, LawyerInfoentity.class);
                        tv_user_name.setText(lawyerInfoentity.getData().getTruename() + "  "+lawyerInfoentity.getData().getLawyer_title());
                        iamgePath = lawyerInfoentity.getData().getHeadimg();
                        Glide.with(LawyerOfficeActivity.this).load(lawyerInfoentity.getData().getHeadimg()).into(iv_user_avatar);

                        iv_user_avatar.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(LawyerOfficeActivity.this, BigImageActivity.class);
                                intent.putExtra("url", lawyerInfoentity.getData().getHeadimg());
                                startActivity(intent);
                            }
                        });

                        if (lawyerInfoentity.getData().getAuthentication_status_id()==3){

                            iv_certified_user.setImageResource(R.drawable.iv_certified_user);

                            if(lawyerInfoentity.getData().getLawyer_type().equals("12")){
                                iv_vip.setImageResource(R.drawable.consult_vip);
                            }else{
                                iv_vip.setImageResource(R.drawable.consult_vip_practice);
                            }

                        }else{
                            iv_certified_user.setImageResource(R.drawable.user_infotag);
                        }

                        if (!lawyerInfoentity.getData().getEmail().equals("")){
                            iv_certified_email.setImageResource(R.drawable.user_smstag);
                        }else{
                            iv_certified_email.setImageResource(R.drawable.iv_certified_email);
                        }


                        tv_browse_count.setText(lawyerInfoentity.getData().getPage_view()+"");
                        tv_collect_count.setText(lawyerInfoentity.getData().getFollow()+"");

                        ratingbar_reply.setRating(Float.parseFloat(lawyerInfoentity.getData().getLegal_advice_rate())/2);
                        tv_count_reply.setText("("+lawyerInfoentity.getData().getReply_consult_num()+")");
                        ratingbar_case.setRating(Float.parseFloat(lawyerInfoentity.getData().getHandle_case_rate())/2);
                        tv_count_case.setText("("+lawyerInfoentity.getData().getHandle_case_num()+")");

                        if (lawyerInfoentity.getData().getExclusive_consult_isprice()== 1){
                            tv_price.setText(lawyerInfoentity.getData().getLawyer_offer_price()+"元");
                        }else{
                            tv_price.setText("暂无报价");
                        }

                        if (lawyerInfoentity.getData().getIs_follow() == 1){
                            tv_follow.setText("关注");
                        }else {
                            tv_follow.setText("已关注");
                        }

                        account = lawyerInfoentity.getData().getIm_token().getAccid();

                       setSelect(0);

                    } else {

                    }
                } catch (JSONException e) {
                   LogUtils.logE(e.toString());
                }
            }
        });

    }

	/**
     * 增加浏览量
	 */
	private void addLawyerViewNum() {
		RequestParams params = new RequestParams();
        params.addQueryStringParameter("user_id", uid);
		httpDataUtils.sendGet(MyData.ADD_LAWYER_VIEW_num, params);
		httpDataUtils.setSucessBack(new HttpDataUtils.SucessBack() {

			@Override
			public void sucess(ResponseInfo<String> arg0) {

			}
		});
	}




}
