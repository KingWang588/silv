package com.yhy.hzzll.view;

import java.io.IOException;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yhy.hzzll.R;
import com.yhy.hzzll.utils.ToastUtils;

/**
 * 解答疑--语音回复的预览
 * 
 * @author Yang
 * 
 */
public class DialogVoiceReview {

	private AlertDialog alertDialog;

	private TextView tv_cancel, tv_ok;

	private LinearLayout ll_voice_layout;

	private TextView tv_content_lenth;

	private ImageView iv_voice;

	private MediaPlayer mp;

	private AnimationDrawable animationDrawable;

	public interface Click {
		void cancel();

		void buy();
	}

	public AlertDialog showDialog(final Context context, final Click click,
			final String mSoundData, String mTime) {
		alertDialog = new AlertDialog.Builder(context).create();
		alertDialog.show();
		alertDialog.setCancelable(true);
		alertDialog.setCanceledOnTouchOutside(true);
		// 关键在下面的两行,使用window.setContentView,替换整个对话框窗口的布局
		Window window = alertDialog.getWindow();
		View view = LayoutInflater.from(context).inflate(
				R.layout.dialog_voice_review, null);
		tv_cancel = (TextView) view.findViewById(R.id.tv_reload);
		tv_ok = (TextView) view.findViewById(R.id.tv_ok);
		ll_voice_layout = (LinearLayout) view
				.findViewById(R.id.ll_voice_layout);
		tv_content_lenth = (TextView) view.findViewById(R.id.tv_content_lenth);
		iv_voice = (ImageView) view.findViewById(R.id.iv_voice);
		tv_content_lenth.setText(mTime + "秒");
		window.setContentView(view);

		ll_voice_layout.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// ToastUtils.getUtils(context).show("播放语音");
				try {
					if (null != mp) {
						mp.release();
					}
					mp = new MediaPlayer();
					mp.setDataSource(mSoundData);
					mp.prepare();
					mp.start();
					iv_voice.setBackgroundResource(R.drawable.voice_animation);
					animationDrawable = (AnimationDrawable) iv_voice
							.getBackground();
					// 动画是否正在运行
					if (animationDrawable.isRunning()) {
						// 停止动画播放
						animationDrawable.stop();
					} else {
						// 开始或者继续动画播放
						animationDrawable.start();
					}
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalStateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}

				mp.setOnErrorListener(new MediaPlayer.OnErrorListener() {
					@Override
					public boolean onError(MediaPlayer mp, int what, int extra) {
						ToastUtils.getUtils(context).show("播放失败！");
						return true;
					}
				});

				mp.setOnCompletionListener(new OnCompletionListener() {
					@Override
					public void onCompletion(MediaPlayer mp) {
						if (animationDrawable != null
								&& animationDrawable.isRunning()) {
							animationDrawable.stop();
							iv_voice.setBackgroundResource(R.drawable.voice4);

						}
					}
				});

			}
		});

		tv_cancel.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (click != null) {
					click.cancel();
				}
				dismissDialog();
			}
		});
		tv_ok.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (click != null) {
					click.buy();
				}
				dismissDialog();
			}
		});
		return alertDialog;
	}

	public boolean isShow() {
		if (alertDialog != null) {
			if (alertDialog.isShowing()) {
				return true;
			}
		}
		return false;
	}

	public void dismissDialog() {
		if (alertDialog != null)
			alertDialog.dismiss();
	}
}
