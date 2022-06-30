package com.yhy.hzzll.my.activity;

import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.umeng.socialize.Config;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.utils.Log;
import com.yhy.hzzll.R;
import com.yhy.hzzll.framework.MyData;
import com.yhy.hzzll.mian.activity.BaseActivity;
import com.yhy.hzzll.my.entity.InvitetionEntity;
import com.yhy.hzzll.utils.HttpDataUtils;
import com.yhy.hzzll.utils.JSONTool;
import com.yhy.hzzll.utils.PrefsUtils;
import com.yhy.hzzll.view.DialogLoading;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MyInvitationActivity extends BaseActivity {

    @ViewInject(R.id.tv_invite_code)
    private TextView tv_invite_code;
    @ViewInject(R.id.tv_member_num)
    private TextView tv_member_num;
    @ViewInject(R.id.tv_lawyer_num)
    private TextView tv_lawyer_num;

    @ViewInject(R.id.share_weixin)
    private TextView share_weixin;

    @ViewInject(R.id.share_friend)
    private TextView share_friend;

    @ViewInject(R.id.share_qq)
    private TextView share_qq;

    @ViewInject(R.id.tv_copy_invitation_code)
    private TextView tv_copy_invitation_code;


    @ViewInject(R.id.linear_lawyer_head_img)
    private LinearLayout linear_lawyer_head_img;

    @ViewInject(R.id.linear_head_img)
    private LinearLayout linear_head_img;
    String invition_code;
    List<InvitetionEntity.DataBean.GeneralBean.ListsBean> mListsBean;
    List<InvitetionEntity.DataBean.LawyerBean.ListsBeanX> lListsBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_invitation);
        ViewUtils.inject(this);
        init();
    }

    private void init() {

        getMyInvitionCode();
    }

    private void getMyInvitionCode() {
        RequestParams params = new RequestParams();
        params.addHeader("Authorization", PrefsUtils.getString(getApplicationContext(), PrefsUtils.AUTHORIZATION));

        httpDataUtils.sendProgressGet(MyData.GET_INVITION_CODE, params);
        httpDataUtils.setSucessBack(new HttpDataUtils.SucessBack() {
            @Override
            public void sucess(ResponseInfo<String> arg0) {
                try {
                    JSONObject object = new JSONObject(arg0.result);
                    String code = object.optString("code");
                    String msg = object.optString("msg");

                    if (code.equals("0")) {
                        JSONObject data = JSONTool.getJsonObject(object, "data");
                        invition_code = JSONTool.getString(data, "name");
                        tv_invite_code.setText(invition_code);

                        getInviteed();

                    } else {

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void getInviteed() {
        RequestParams params = new RequestParams();
        params.addHeader("Authorization", PrefsUtils.getString(getApplicationContext(), PrefsUtils.AUTHORIZATION));

        httpDataUtils.sendProgressGet(MyData.GET_INVITED_PERSON, params);
        httpDataUtils.setSucessBack(new HttpDataUtils.SucessBack() {
            @Override
            public void sucess(ResponseInfo<String> arg0) {
                try {
                    JSONObject object = new JSONObject(arg0.result);
                    String code = object.optString("code");//何虎
                    if (code.equals("0")) {

                        InvitetionEntity invitetionEntity = gson.fromJson(object.toString(), InvitetionEntity.class);
                        tv_member_num.setText("(" + invitetionEntity.getData().getGeneral().getNum() + ")");
                        tv_lawyer_num.setText("(" + invitetionEntity.getData().getLawyer().getNum() + ")");
                        if (invitetionEntity.getData().getGeneral().getLists() != null) {

                            mListsBean = new ArrayList<>();
                            mListsBean.addAll(invitetionEntity.getData().getGeneral().getLists());

                            if (mListsBean.size()!=0){
                                linear_head_img.removeAllViews();
                            }

                            for (int i = 0; i <mListsBean.size(); i++) {

                                View view = View.inflate(MyInvitationActivity.this,R.layout.item_invated_pic,null);
                                ImageView imageView = (ImageView) view.findViewById(R.id.iv_head);
                                TextView tv_name = (TextView) view.findViewById(R.id.tv_name);
                                tv_name.setText(mListsBean.get(i).getNickname());

                               Glide.with(MyInvitationActivity.this).load(mListsBean.get(i).getAvatar()).into(imageView);
                                linear_head_img.addView(view);
                            }

                        }
                        if (invitetionEntity.getData().getLawyer().getLists() != null) {

                            lListsBean = new ArrayList<>();
                            lListsBean.addAll(invitetionEntity.getData().getLawyer().getLists());

                            if (mListsBean.size()!=0){
                                linear_lawyer_head_img.removeAllViews();
                            }

                            for (int i = 0; i <lListsBean.size(); i++) {

                                View view = View.inflate(MyInvitationActivity.this,R.layout.item_invated_pic,null);
                                ImageView imageView = (ImageView) view.findViewById(R.id.iv_head);
                                TextView tv_name = (TextView) view.findViewById(R.id.tv_name);
                                tv_name.setText(lListsBean.get(i).getTruename());

                                Glide.with(MyInvitationActivity.this).load(lListsBean.get(i).getAvatar()).into(imageView);
                                linear_lawyer_head_img.addView(view);

                            }
                        }
                    } else {

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    @OnClick({R.id.ic_back, R.id.tv_copy_invitation_code,R.id.share_weixin,R.id.share_friend,R.id.share_qq})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ic_back:
                finish();
                break;

            case R.id.tv_copy_invitation_code:
                onClickCopy();
                break;

            case R.id.share_weixin:
                share(SHARE_MEDIA.WEIXIN);
                break;

            case R.id.share_friend:
                share(SHARE_MEDIA.WEIXIN_CIRCLE);
                break;

            case R.id.share_qq:
                share(SHARE_MEDIA.QQ);
                break;
        }
    }

    private void share(SHARE_MEDIA var1) {
        UMImage image = new UMImage(context, R.drawable.app2);
        Config.OpenEditor = true;
        Config.dialog = new DialogLoading().showDialog(MyInvitationActivity.this);
        new ShareAction(MyInvitationActivity.this)
                .setPlatform(var1)
                .setCallback(umShareListener)
                .withTitle("私律")
                .withText("邀请注册")
                .withMedia(image)
                .withTargetUrl(MyData.INVITATIONLAWYER+invition_code)
                .share();

    }

    private UMShareListener umShareListener = new UMShareListener() {
        @Override
        public void onResult(SHARE_MEDIA platform) {
//            Log.d("plat", "platform" + platform);
            if (platform.name().equals("WEIXIN_FAVORITE")) {
                Toast.makeText(context, platform + " 收藏成功啦", Toast.LENGTH_SHORT)
                        .show();
            } else {
                Toast.makeText(context, platform + " 分享成功啦", Toast.LENGTH_SHORT)
                        .show();
            }
        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(context, platform + " 分享失败啦", Toast.LENGTH_SHORT)
                    .show();
            if (t != null) {
                Log.d("throw", "throw:" + t.getMessage());
            }
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(context, platform + " 分享取消了", Toast.LENGTH_SHORT)
                    .show();
        }
    };

    public void onClickCopy() {
        // 从API11开始android推荐使用android.content.ClipboardManager
        // 为了兼容低版本我们这里使用旧版的android.text.ClipboardManager，虽然提示deprecated，但不影响使用。
        ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        // 将文本内容放到系统剪贴板里。
        cm.setText(tv_invite_code.getText());
        Toast.makeText(this, "复制成功，可以发给朋友们了。", Toast.LENGTH_LONG).show();
    }

}
