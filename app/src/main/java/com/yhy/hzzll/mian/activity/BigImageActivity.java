package com.yhy.hzzll.mian.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.yhy.hzzll.R;
import com.yhy.hzzll.utils.ImageLoaderUtils;

public class BigImageActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bigimage);
		
	ImageView bigImage = (ImageView) findViewById(R.id.big_image);
	ImageLoaderUtils.initUtils().display(getIntent().getStringExtra("url"),bigImage);
	
	bigImage.setOnClickListener(new OnClickListener() {
		@Override
		public void onClick(View v) {
			finish();
		}
	});
	}
}
