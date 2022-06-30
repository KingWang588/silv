package com.yhy.hzzll.view;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.yhy.hzzll.R;
import com.yhy.hzzll.home.holder.Holder;
import com.yhy.hzzll.utils.ImageLoaderUtils;

/**
 * Created by Sai on 15/8/4. 网络图片加载
 */
public class ImageHolderView implements Holder<String> {
	private ImageView imageView;

	@Override
	public View createView(Context context) {
		// 你可以通过layout文件来创建，也可以像我一样用代码创建，不一定是Image，任何控件都可以进行翻页
		imageView = new ImageView(context);
		imageView.setImageResource(R.drawable.banner);
		imageView.setScaleType(ImageView.ScaleType.FIT_XY);
		return imageView;
	}

	@Override
	public void UpdateUI(Context context, int position, String data) {
		try {
			ImageLoaderUtils.initUtils().display(data, imageView);
		} catch (Exception e) {
			imageView.setImageResource(R.drawable.banner);
		}
	}
}
