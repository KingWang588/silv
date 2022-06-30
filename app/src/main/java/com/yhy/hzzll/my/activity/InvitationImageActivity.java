package com.yhy.hzzll.my.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.yhy.hzzll.R;
import com.yhy.hzzll.mian.activity.BaseActivity;
import com.yhy.hzzll.my.utils.album.AlbumManager;
import com.yhy.hzzll.my.utils.album.DownloadCallback;

public class InvitationImageActivity extends BaseActivity {

    @ViewInject(R.id.iv_invitation_image)
     ImageView iv_invitation_image;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invitation_image);

        ViewUtils.inject(this);

        Glide.with(InvitationImageActivity.this).load(getIntent().getStringExtra("url")).into(iv_invitation_image);

    }

    @OnClick({R.id.tv_download,R.id.tv_cancle})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_download:
                downloadImage();
                break;
            case R.id.tv_cancle:
                finish();
                break;
        }}


    private void downloadImage() {
        AlbumManager.download(getIntent().getStringExtra("url"), new DownloadCallback() {
            public void onDownloadCompleted(boolean downloadResult) {

//                if(InvitationImageActivity.this == null || !isAdded()) return;
//                dismiss();
                Toast.makeText(InvitationImageActivity.this, downloadResult ? "图片保持成功" : "图片保持失败", Toast.LENGTH_SHORT).show();
            }
        });
    }




}
