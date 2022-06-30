package com.yhy.hzzll.utils;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.yhy.hzzll.R;

/**
 * 
 * @Description: imageLoaderUtils 图片加载工具类
 * 
 * @author wangyang
 * 
 * @date 2015-10-6
 * 
 * @version V1.0
 * 
 */
public class ImageLoaderUtils {

    private static ImageLoaderUtils imageLoaderUtils;

    public static ImageLoaderUtils initUtils() {
	if (imageLoaderUtils == null)
	    imageLoaderUtils = new ImageLoaderUtils();
	return imageLoaderUtils;
    }

    private DisplayImageOptions option() {
	DisplayImageOptions options = new DisplayImageOptions.Builder()
	// .showImageOnLoading(R.drawable.ic_launcher) // 设置图片在下载期间显示的图片
	// .showImageForEmptyUri(R.drawable.ic_launcher)// 设置图片Uri为空或是错误的时候显示的图片
	// .showImageOnFail(R.drawable.ic_launcher) // 设置图片加载/解码过程中错误时候显示的图片
		.cacheInMemory(true)// 设置下载的图片是否缓存在内存中
		.cacheOnDisk(true)// 设置下载的图片是否缓存在SD卡中
		.considerExifParams(true) // 是否考虑JPEG图像EXIF参数（旋转，翻转）
		// .imageScaleType(ImageScaleType.NONE)// 设置图片以如何的编码方式显示
		.bitmapConfig(Bitmap.Config.RGB_565)// 设置图片的解码类型//
		// .delayBeforeLoading(int delayInMillis)//int
		// delayInMillis为你设置的下载前的延迟时间
		// 设置图片加入缓存前，对bitmap进行设置
		// .preProcessor(BitmapProcessor preProcessor)
		.resetViewBeforeLoading(true)// 设置图片在下载前是否重置，复位
		// .displayer(new RoundedBitmapDisplayer(20))// 是否设置为圆角，弧度为多少
		.displayer(new FadeInBitmapDisplayer(1000))// 是否图片加载好后渐入的动画时间
		.build();// 构建完成

	return options;
    }
    
    public DisplayImageOptions option2() {
	DisplayImageOptions options = new DisplayImageOptions.Builder()
	 .showImageOnLoading(R.drawable.icon_login_user) // 设置图片在下载期间显示的图片
	 .showImageForEmptyUri(R.drawable.icon_login_user)// 设置图片Uri为空或是错误的时候显示的图片
	 .showImageOnFail(R.drawable.icon_login_user) // 设置图片加载/解码过程中错误时候显示的图片
		.cacheInMemory(true)// 设置下载的图片是否缓存在内存中
		.cacheOnDisk(true)// 设置下载的图片是否缓存在SD卡中
		.considerExifParams(true) // 是否考虑JPEG图像EXIF参数（旋转，翻转）
		// .imageScaleType(ImageScaleType.NONE)// 设置图片以如何的编码方式显示
		.bitmapConfig(Bitmap.Config.RGB_565)// 设置图片的解码类型//
		// .delayBeforeLoading(int delayInMillis)//int
		// delayInMillis为你设置的下载前的延迟时间
		// 设置图片加入缓存前，对bitmap进行设置
		// .preProcessor(BitmapProcessor preProcessor)
		.resetViewBeforeLoading(true)// 设置图片在下载前是否重置，复位
		// .displayer(new RoundedBitmapDisplayer(20))// 是否设置为圆角，弧度为多少
		.displayer(new FadeInBitmapDisplayer(1000))// 是否图片加载好后渐入的动画时间
		.build();// 构建完成

	return options;
    }

    public void display(String uri, ImageView imageView) {
	ImageLoader.getInstance().displayImage(uri, imageView, option());
    }
    
    public void display(String uri, ImageView imageView,DisplayImageOptions options) {
	ImageLoader.getInstance().displayImage(uri, imageView, option());
    }

}
