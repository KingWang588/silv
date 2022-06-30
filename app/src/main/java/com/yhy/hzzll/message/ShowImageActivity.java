package com.yhy.hzzll.message;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

import com.yhy.hzzll.R;
import com.yhy.hzzll.utils.ImageLoaderUtils;

public class ShowImageActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_image);
		ImageView imageView1 = (ImageView) findViewById(R.id.imageView1);

		ImageLoaderUtils.initUtils().display(getIntent().getStringExtra("url"), imageView1);

	}

}
