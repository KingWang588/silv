package com.yhy.hzzll.my.activity;

import android.Manifest;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;

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
import com.yhy.hzzll.entity.BannerEntity;
import com.yhy.hzzll.framework.MyData;
import com.yhy.hzzll.mian.activity.BaseActivity;
import com.yhy.hzzll.utils.HttpDataUtils;
import com.yhy.hzzll.utils.JSONTool;
import com.yhy.hzzll.utils.LogUtils;
import com.yhy.hzzll.utils.PrefsUtils;
import com.yhy.hzzll.view.DialogLoading;
import com.yhy.hzzll.view.SharePopupWindow;

import org.json.JSONException;
import org.json.JSONObject;

public class NewInviteActivity extends BaseActivity {

    @ViewInject(R.id.tv_invite_code)
    private TextView tv_invite_code;

    @ViewInject(R.id.iv_banner)
    private ImageView iv_banner;


    String url;
    String invition_code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_invite);

        ViewUtils.inject(this);

        getBanner();
    }

    private void getBanner() {
        httpDataUtils.sendGet(MyData.GET_HOME_BANNER + "19", null);
        httpDataUtils.setSucessBack(new HttpDataUtils.SucessBack() {
            @Override
            public void sucess(ResponseInfo<String> arg0) {
                try {
                    JSONObject object = new JSONObject(arg0.result);
                    String code = JSONTool.getString(object, "code");
                    String msg = JSONTool.getString(object, "msg");
                    if (code.equals("0")) {

                        BannerEntity bannerEntity = gson.fromJson(object.toString(), BannerEntity.class);
                        Glide.with(NewInviteActivity.this).load(bannerEntity.getData().get(0).getImgurl()).into(iv_banner);
                        getMyInvitionCode();

                    } else {
                        httpDataUtils.showMsg(arg0.result);
                    }
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
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



    @OnClick({R.id.tv_look,R.id.tv_copy_invitation_code,R.id.ic_back,R.id.linear_one,R.id.linear_download})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.linear_download:
                startActivity(new Intent(NewInviteActivity.this,InvitationImageActivity.class).putExtra("url",url));
                break;
            case R.id.ic_back:
                finish();
                break;
            case R.id.tv_look:
                startActivity(new Intent(NewInviteActivity.this,CheckIncomeActivity.class));
                break;
            case R.id.tv_copy_invitation_code:
                onClickCopy();
                break;
            case R.id.linear_one:

                SharePopupWindow popupWindow = new SharePopupWindow(this,
                        new SharePopupWindow.Click() {
                            @Override
                            public void Select(SHARE_MEDIA shareStatus) {
                                UMImage image = new UMImage(context, R.drawable.app2);
                                switch (shareStatus) {

                                    case WEIXIN:
                                        Config.OpenEditor = true;
                                        Config.dialog = new DialogLoading().showDialog(NewInviteActivity.this);
                                        new ShareAction(NewInviteActivity.this)
                                                .setPlatform(SHARE_MEDIA.WEIXIN)
                                                .setCallback(umShareListener)
                                                .withTitle("邀请注册")
                                                .withText("法律问题找私律，注册即送10元咨询券")
                                                .withMedia(image)
                                                .withTargetUrl(MyData.MOBILE_URL+"invitation?invite_code="+invition_code+"&users_id="+PrefsUtils.getString(NewInviteActivity.this,PrefsUtils.UID))
                                                .share();
                                        break;

                                    case WEIXIN_CIRCLE:
                                        // UMImage image = new UMImage(context,
                                        // "http://www.umeng.com/images/pic/social/integrated_3.png");
                                        Config.OpenEditor = true;
                                        Config.dialog = new DialogLoading().showDialog(NewInviteActivity.this);
                                        new ShareAction(NewInviteActivity.this)
                                                .setPlatform(SHARE_MEDIA.WEIXIN_CIRCLE)
                                                .setCallback(umShareListener)
                                                .withTitle("邀请注册")
                                                .withText("法律问题找私律，注册即送10元咨询券")
                                                .withMedia(image)
                                                .withTargetUrl(MyData.MOBILE_URL+"invitation?invite_code="+invition_code+"&users_id="+PrefsUtils.getString(NewInviteActivity.this,PrefsUtils.UID))
                                                .share();
                                        break;
                                    case QQ:
                                        // UMImage image = new UMImage(context,
                                        // "http://www.umeng.com/images/pic/social/integrated_3.png");
                                        Config.OpenEditor = true;
                                        Config.dialog = new DialogLoading()
                                                .showDialog(NewInviteActivity.this);
                                        new ShareAction(NewInviteActivity.this)
                                                .setPlatform(SHARE_MEDIA.QQ)
                                                .setCallback(umShareListener)
                                                .withTitle("邀请注册")
                                                .withText(" 法律问题找私律，注册即送10元咨询券")
                                                .withMedia(image)
                                                .withTargetUrl(MyData.MOBILE_URL+"invitation?invite_code="+invition_code+"&users_id="+PrefsUtils.getString(NewInviteActivity.this,PrefsUtils.UID))
                                                .share();
                                        break;
                                    case SINA:
                                        // UMImage image = new UMImage(context,
                                        // "http://www.umeng.com/images/pic/social/integrated_3.png");
                                        if (Build.VERSION.SDK_INT >= 23) {
                                            String[] mPermissionList = new String[]{
                                                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                                    Manifest.permission.ACCESS_FINE_LOCATION,
                                                    Manifest.permission.CALL_PHONE,
                                                    Manifest.permission.READ_LOGS,
                                                    Manifest.permission.READ_PHONE_STATE,
                                                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                                    Manifest.permission.SET_DEBUG_APP,
                                                    Manifest.permission.SYSTEM_ALERT_WINDOW,
                                                    Manifest.permission.GET_ACCOUNTS,
                                                    Manifest.permission.WRITE_APN_SETTINGS};
                                            ActivityCompat.requestPermissions(
                                                    NewInviteActivity.this,
                                                    mPermissionList, 123);
                                        }
                                        //武汉
                                        Config.OpenEditor = true;
                                        Config.dialog = new DialogLoading()
                                                .showDialog(NewInviteActivity.this);
                                        new ShareAction(NewInviteActivity.this)
                                                .setPlatform(SHARE_MEDIA.SINA)
                                                .setCallback(umShareListener)
                                                .withText(MyData.MOBILE_URL + "/lawyers")
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

                        getInvitationImage();

                    } else {

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void getInvitationImage() {
        RequestParams params = new RequestParams();
        params.addHeader("Authorization", PrefsUtils.getString(getApplicationContext(), PrefsUtils.AUTHORIZATION));

        httpDataUtils.sendGet(MyData.GET_QRCODE+PrefsUtils.getString(getApplicationContext(), PrefsUtils.UID), params);
        httpDataUtils.setSucessBack(new HttpDataUtils.SucessBack() {
            @Override
            public void sucess(ResponseInfo<String> arg0) {
                try {
                    JSONObject object = new JSONObject(arg0.result);
                    String code = object.optString("code");
                    String msg = object.optString("msg");

                    LogUtils.logE(arg0.result);
                    if (code.equals("0")) {
//                        JSONObject data = JSONTool.getJsonObject(object, "data");
                        url = JSONTool.getString(object, "data");
//
//                        getInvitationImage();
//
                    } else {
//
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }


}
