package com.yhy.hzzll.view;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.yhy.hzzll.R;
import com.yhy.hzzll.entity.BannerEntity;
import com.yhy.hzzll.home.holder.Holder;
import com.yhy.hzzll.my.activity.MyInvitationActivity;

/**
 * Created by Yang . 网络图片加载
 */
public class ImageBannerView implements Holder<BannerEntity.DataBean> {
	private ImageView imageView;

	@Override
	public View createView(Context context) {
		// 你可以通过layout文件来创建，也可以像我一样用代码创建，不一定是Image，任何控件都可以进行翻页
		imageView = new ImageView(context);
		imageView.setImageResource(R.drawable.banner);
		imageView.setScaleType(ImageView.ScaleType.FIT_XY);
		return imageView;
	}

//	@Override
//	public void UpdateUI(Context context, int position, List<BannerEntity> data) {
//		// TODO Auto-generated method stub
//		for (int i = 0; i < data.size(); i++) {
//			ImageLoaderUtils.initUtils().display(data.get(i).getImg(),
//					imageView);
//		}
//	}

	@Override
	public void UpdateUI(Context context, int position, BannerEntity.DataBean data) {

		if(data.getImgurl()!=null&&data.getImgurl().length()!=0){
			Glide.with(context).load(data.getImgurl()).into(imageView);
		}
	}
}
