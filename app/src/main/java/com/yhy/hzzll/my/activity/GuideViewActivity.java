package com.yhy.hzzll.my.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.yhy.hzzll.R;
import com.yhy.hzzll.mian.activity.BaseActivity;

public class GuideViewActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide_view);

      ImageView iv_img = (ImageView) findViewById(R.id.iv_img);

        iv_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
}
