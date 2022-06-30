package com.yhy.hzzll.home.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.yhy.hzzll.R;
import com.yhy.hzzll.message.WebviewActivity;
import com.yhy.hzzll.mian.activity.BaseActivity;

public class ADActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad);

        LinearLayout linear = (LinearLayout) findViewById(R.id.linear);
        ImageView imageView = (ImageView) findViewById(R.id.image_ad);
        Glide.with(ADActivity.this).load(getIntent().getStringExtra("imgurl")).into(imageView);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getIntent().getStringExtra("target")!=null&&getIntent().getStringExtra("target").length()!=0){
                    startActivity(new Intent().putExtra("title", getIntent().getStringExtra("title")).putExtra("url",getIntent().getStringExtra("target") ).setClass(ADActivity.this, WebviewActivity.class));
                }
            }
        });

        linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
