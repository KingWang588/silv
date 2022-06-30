package com.yhy.hzzll.home.activity;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.viewpager.widget.PagerAdapter;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.yhy.hzzll.R;
import com.yhy.hzzll.adapter.ExtendedViewPager;
import com.yhy.hzzll.mian.activity.BaseActivity;
import com.yhy.hzzll.widget.TouchImageView;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerExampleActivity extends BaseActivity {

	/**
	 * Step 1: Download and set up v4 support library:
	 * http://developer.android.com/tools/support-library/setup.html Step 2:
	 * Create ExtendedViewPager wrapper which calls
	 * TouchImageView.canScrollHorizontallyFroyo Step 3: ExtendedViewPager is a
	 * custom view and must be referred to by its full package name in XML Step
	 * 4: Write TouchImageAdapter, located below Step 5. The ViewPager in the
	 * XML should be ExtendedViewPager
	 */

	public static final String PHOTO_URL_LIST_INTENT = "PHOTO_URL_LIST_INTENT";
	private static List<String> urlList;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_viewpager_preview);
		ViewUtils.inject(this);
		init();
	}

	private void init() {
		urlList = new ArrayList<String>();
		urlList.addAll(getIntent().getStringArrayListExtra(PHOTO_URL_LIST_INTENT));


		for (int i = 0; i < urlList.size(); i++) {
			Log.e("=======",urlList.get(i));
		}


		ExtendedViewPager mViewPager = (ExtendedViewPager) findViewById(R.id.view_pager);
		mViewPager.setAdapter(new TouchImageAdapter(ViewPagerExampleActivity.this));
		mViewPager.setCurrentItem(getIntent().getIntExtra("position", 0));
	}

	@OnClick({ R.id.ic_back })
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.ic_back:
			finish();
			break;
		}
	}

	static class TouchImageAdapter extends PagerAdapter {
		// ImageLoader.getInstance().displayImage(MyData.IP +
		// userEntity.getImgtrue(), iv_avatar);
		// private static int[] images = {};

		Context context;

		public TouchImageAdapter(Context context) {
			this.context = context;
		}

		@Override
		public int getCount() {
			return urlList.size();
		}

		@Override
		public View instantiateItem(ViewGroup container, int position) {

			TouchImageView img = new TouchImageView(container.getContext());
//			ImageLoader.getInstance().displayImage(urlList.get(position), img);

			if (urlList.get(position).contains("http")){

				ImageLoader.getInstance().displayImage(urlList.get(position), img);


//				Glide.with(context).load(urlList.get(position)).into(img);
			}else {
				img.setImageBitmap(BitmapFactory.decodeFile(urlList.get(position)));
			}


			// img.setImageURI(Uri.parse(urlList.get(position)));
			container.addView(img, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
			return img;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View) object);
		}

		@Override
		public boolean isViewFromObject(View view, Object object) {
			return view == object;
		}
	}
}
