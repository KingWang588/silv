package com.yhy.hzzll.my.activity;

import android.os.Bundle;

import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.yhy.hzzll.R;

public class MyCaptureActivity extends CaptureActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_capture);
    }
}
