package com.yhy.hzzll.my.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
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
import com.yhy.hzzll.my.utils.album.AlbumManager;
import com.yhy.hzzll.my.utils.album.DownloadCallback;
import com.yhy.hzzll.utils.HttpDataUtils;
import com.yhy.hzzll.utils.JSONTool;
import com.yhy.hzzll.utils.LogUtils;
import com.yhy.hzzll.utils.PrefsUtils;
import com.yhy.hzzll.view.DialogLoading;

import org.json.JSONException;
import org.json.JSONObject;

public class ExclusiveCodeActivity extends BaseActivity {

    @ViewInject(R.id.iv_invitation_image)
    ImageView iv_invitation_image;

    String url;
    String iamgePath = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exclusive_code);

        ViewUtils.inject(this);

        getInvitationImage();
    }

    private void getInvitationImage() {
        RequestParams params = new RequestParams();
        params.addHeader("Authorization", PrefsUtils.getString(getApplicationContext(), PrefsUtils.AUTHORIZATION));

        httpDataUtils.sendGet(MyData.GET_CONSULT_CODE+PrefsUtils.getString(getApplicationContext(), PrefsUtils.UID), params);
        httpDataUtils.setSucessBack(new HttpDataUtils.SucessBack() {
            @Override
            public void sucess(ResponseInfo<String> arg0) {
                try {
                    JSONObject object = new JSONObject(arg0.result);
                    String code = object.optString("code");
                    String msg = object.optString("msg");

                    LogUtils.logE(arg0.result);
                    if (code.equals("0")) {
                        url = JSONTool.getString(object, "data");
                        iamgePath = url;
                        Glide.with(ExclusiveCodeActivity.this).load(url).into(iv_invitation_image);

                    } else {

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    @OnClick({R.id.tv_download,R.id.tv_cancle,R.id.tv_pengyouquan,R.id.tv_weixin,R.id.linear_3,R.id.linear_2,R.id.linear_1})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_download:
            case R.id.linear_3:
                downloadImage();
                break;

            case R.id.tv_cancle:
                finish();
                break;

            case   R.id.tv_pengyouquan:
            case   R.id.linear_2:

                share(SHARE_MEDIA.WEIXIN_CIRCLE);
                break;

            case  R.id.tv_weixin :
            case  R.id.linear_1 :
                share(SHARE_MEDIA.WEIXIN);

                break;

        }}


    private void downloadImage() {
        AlbumManager.download(url, new DownloadCallback() {
            public void onDownloadCompleted(boolean downloadResult) {
                Toast.makeText(ExclusiveCodeActivity.this, downloadResult ? "图片保持成功" : "图片保持失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void share(SHARE_MEDIA var1) {
//        UMImage image = new UMImage(context, R.drawable.app2);
        UMImage image = new UMImage(context, PrefsUtils.getString(ExclusiveCodeActivity.this,PrefsUtils.AVATAR));
        Config.OpenEditor = true;
        Config.dialog = new DialogLoading().showDialog(ExclusiveCodeActivity.this);
        new ShareAction(ExclusiveCodeActivity.this)
                .setPlatform(var1)
                .setCallback(umShareListener)
                .withTitle(PrefsUtils.getString(ExclusiveCodeActivity.this,PrefsUtils.UNAME)+"律师")
                .withText("您的专属律师，点击立即向我咨询。")
                .withMedia(image)
                .withTargetUrl(MyData.LAWYERS_DETIAL +PrefsUtils.getString(ExclusiveCodeActivity.this,PrefsUtils.UID) )
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






}
