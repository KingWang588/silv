package com.yhy.hzzll.message;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.uinfo.UserService;
import com.netease.nimlib.sdk.uinfo.model.NimUserInfo;
import com.yhy.hzzll.R;
import com.yhy.hzzll.mian.activity.BaseActivity;

public class CheckRedPacketActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_red_packet);

        TextView tv_price = (TextView) findViewById(R.id.tv_price);
        tv_price.setText(getIntent().getStringExtra("money") + "元");

        ImageView iv_cancle = (ImageView) findViewById(R.id.iv_close);

        ImageView iv_avatar = (ImageView) findViewById(R.id.iv_avatar);

        String account = getIntent().getStringExtra("account");
        NimUserInfo user = NIMClient.getService(UserService.class).getUserInfo(account);

        if (user.getAvatar()!=null&&user.getAvatar().length()!=0){
            Glide.with(CheckRedPacketActivity.this).load(user.getAvatar()).into(iv_avatar);
        }

        iv_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
