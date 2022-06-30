package com.yhy.hzzll.mian.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.viewpager.widget.ViewPager;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.yhy.hzzll.R;
import com.yhy.hzzll.adapter.MyViewPagerAdapter;
import com.yhy.hzzll.utils.PrefsUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 引导页
 * 
 * @author Yang
 * 
 */
public class WelcomeActivity extends CheckPermissionsActivity {

	private List<View> viewlist = new ArrayList<View>();

	@ViewInject(R.id.pg_welcome)
	private ViewPager pg_welcome;

	private MyViewPagerAdapter MyViewPagerAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);
		ViewUtils.inject(this);
		PrefsUtils.saveString(getApplicationContext(), PrefsUtils.First, "1");
		bannerInit();
	}

	/** 广告栏加载 */
	private void bannerInit() {
		viewlist.add(getView(1, R.drawable.guide_3_1));
		viewlist.add(getView(2, R.drawable.guide_3_2));
		viewlist.add(getView(3, R.drawable.guide_3_3));
//		viewlist.add(getView(4, R.drawable.new_guide4));
		MyViewPagerAdapter = new MyViewPagerAdapter(viewlist);
		pg_welcome.setAdapter(MyViewPagerAdapter);
	}

	private View getView(int position, int imageId) {
		View view = LayoutInflater.from(this).inflate(
				R.layout.welcome_pager_layout, null);
		ImageView iv_page = (ImageView) view.findViewById(R.id.iv_page);
		TextView tv_start = (TextView) view.findViewById(R.id.tv_start);
		TextView tv_next = (TextView) view.findViewById(R.id.tv_next);
		iv_page.setImageResource(imageId);
		switch (position) {
		case 1:
			tv_next.setOnClickListener(new OnClickListener() {// 跳过
				@Override
				public void onClick(View v) {
					startActivity(new Intent().setClass(getApplicationContext(),
							MainActivity.class));
					finish();
				}
			});
			break;
		case 2:
			tv_next.setOnClickListener(new OnClickListener() {// 跳过

				@Override
				public void onClick(View v) {
					startActivity(new Intent().setClass(getApplicationContext(),
							MainActivity.class));
					finish();
				}
			});
			break;
//		case 3:
//			tv_next.setOnClickListener(new OnClickListener() {// 跳过
//				@Override
//				public void onClick(View v) {
//					startActivity(new Intent().setClass(getApplicationContext(),
//							MainActivity.class));
//					finish();
//				}
//			});
//			break;
		case 3:
			tv_start.setOnClickListener(listener);
			break;
		}
		return view;
	}

	private OnClickListener listener = new OnClickListener() {// 开启

		@Override
		public void onClick(View v) {

			startActivity(new Intent().setClass(getApplicationContext(),
					MainActivity.class));
			finish();
		}
	};

}
