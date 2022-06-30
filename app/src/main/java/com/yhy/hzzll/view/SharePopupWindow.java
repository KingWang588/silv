package com.yhy.hzzll.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.umeng.socialize.bean.SHARE_MEDIA;
import com.yhy.hzzll.R;

public class SharePopupWindow extends PopupWindow {
	private View conentView;
	private Activity context;
	private Click click;

	public interface Click {
		void Select(SHARE_MEDIA status);
	}

	public SharePopupWindow(final Activity context, Click click) {
		this.context = context;
		this.click = click;
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		conentView = inflater.inflate(R.layout.interacation_pop_layout, null);
		// int height = context.getResources().getDisplayMetrics().heightPixels;
		int width = context.getResources().getDisplayMetrics().widthPixels;
		// 设置SelectPicPopupWindow的View
		this.setContentView(conentView);
		// 设置SelectPicPopupWindow弹出窗体的宽
		this.setWidth(width);
		// 设置SelectPicPopupWindow弹出窗体的高
		this.setHeight(LayoutParams.WRAP_CONTENT);
		// 设置SelectPicPopupWindow弹出窗体可点击
		this.setFocusable(true);
		this.setOutsideTouchable(true);
		// 刷新状态
		this.update();
		// 实例化一个ColorDrawable颜色为半透明
		ColorDrawable dw = new ColorDrawable(0000000000);
		// 点back键和其他地方使其消失,设置了这个才能触发OnDismisslistener ，设置其他控件变化等操作
		this.setBackgroundDrawable(dw);
		// mPopupWindow.setAnimationStyle(android.R.style.Animation_Dialog);
		// 设置SelectPicPopupWindow弹出窗体动画效果
		this.setAnimationStyle(R.style.AnimationPreview);
		// 微信
		conentView.findViewById(R.id.share_weixin).setOnClickListener(listener);
		// 朋友圈
		conentView.findViewById(R.id.share_friend).setOnClickListener(listener);
		// qq
		conentView.findViewById(R.id.share_qq).setOnClickListener(listener);
		// 新浪
		conentView.findViewById(R.id.share_sina).setOnClickListener(listener);
		// 取消
		conentView.findViewById(R.id.tv_cancel).setOnClickListener(listener);

		// 设置背景颜色变暗
		WindowManager.LayoutParams lp = context.getWindow().getAttributes();
		lp.alpha = 0.7f;
		context.getWindow().setAttributes(lp);
		this.setOnDismissListener(new OnDismissListener() {
			@Override
			public void onDismiss() {
				WindowManager.LayoutParams lp = context.getWindow()
						.getAttributes();
				lp.alpha = 1f;
				context.getWindow().setAttributes(lp);
			}
		});
	}

	private OnClickListener listener = new OnClickListener() {
		@Override
		public void onClick(View view) {
			switch (view.getId()) {
			case R.id.share_weixin:
				if (click != null)
					click.Select(SHARE_MEDIA.WEIXIN);
				// UMImage image = new UMImage(context,
				// "http://www.umeng.com/images/pic/social/integrated_3.png");
				// Config.OpenEditor = true;
				// Config.dialog = new DialogLoading().showDialog(context);
				// new ShareAction(context).setPlatform(SHARE_MEDIA.WEIXIN)
				// .setCallback(umShareListener).withText("私律")
				// // .withMedia(image)
				// // .withMedia(new
				// //
				// UMEmoji(context,"http://img.newyx.net/news_img/201306/20/1371714170_1812223777.gif"))
				// // .withText("hello umeng")
				// .withTargetUrl("http://silv.hzzll.com/").share();
				break;
			case R.id.share_friend:
				// UMImage image = new UMImage(context,
				// "http://www.umeng.com/images/pic/social/integrated_3.png");
				// Config.OpenEditor = true;
				// Config.dialog = new DialogLoading().showDialog(context);
				// new
				// ShareAction(context).setPlatform(SHARE_MEDIA.WEIXIN_CIRCLE)
				// .setCallback(umShareListener).withTitle("私律")
				// // .withText("私律")
				// // .withMedia(image)
				// .withTargetUrl("http://silv.hzzll.com/").share();
				if (click != null)
					click.Select(SHARE_MEDIA.WEIXIN_CIRCLE);
				break;
			case R.id.share_qq:
				// UMImage image = new UMImage(context,
				// "http://www.umeng.com/images/pic/social/integrated_3.png");
				// Config.OpenEditor = true;
				// Config.dialog = new DialogLoading().showDialog(context);
				// new ShareAction(context).setPlatform(SHARE_MEDIA.QQ)
				// .setCallback(umShareListener).withTitle("私律")
				// // .withText("www.hzzll.com").withMedia(image)
				// // .withMedia(music)
				// .withTargetUrl("http://silv.hzzll.com/").share();

				if (click != null)
					click.Select(SHARE_MEDIA.QQ);
				break;
			case R.id.share_sina:
				// UMImage image = new UMImage(context,
				// "http://www.umeng.com/images/pic/social/integrated_3.png");
				// Config.OpenEditor = true;
				// Config.dialog = new DialogLoading().showDialog(context);
				// new ShareAction(context).setPlatform(SHARE_MEDIA.SINA)
				// .setCallback(umShareListener)
				// .withText("http://silv.hzzll.com/")
				// // .withTitle("私律")
				// // .withMedia(image)
				// // .withExtra(new
				// // UMImage(ShareActivity.this,R.drawable.ic_launcher))
				// // .withTargetUrl("http://silv.hzzll.com/")
				// .share();
				if (click != null)
					click.Select(SHARE_MEDIA.SINA);
				break;
			case R.id.tv_cancel:
				dismiss();
				break;
			}
		}
	};

	/**
	 * 显示popupWindow
	 * 
	 * @param parent
	 */
	public void showPopupWindow(View parent) {
		if (!this.isShowing()) {
			// 以下拉方式显示popupwindow
			this.showAtLocation(parent, Gravity.BOTTOM, 0, 0);
		} else {
			this.dismiss();
		}
	}
}
