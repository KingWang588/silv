package com.yhy.hzzll.utils;

import java.io.IOException;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.util.Log;

import com.yhy.hzzll.interfacelib.IVoiceManager;

public class UPlayer implements IVoiceManager {

	private final String TAG = UPlayer.class.getName();
	private String path;

	private MediaPlayer mPlayer;
	private OnCompletionListener listener;

	public UPlayer(String path) {
		this.path = path;
		mPlayer = new MediaPlayer();
	}

	public void setOnCompletionListener(OnCompletionListener listener) {
		this.listener = listener;
	}

	@Override
	public boolean start() {
		try {
			// 设置要播放的文件
			mPlayer.setDataSource(path);
			try {
				// 在播放之前先判断playerMusic是否被占用，这样就不会报错了
				if (mPlayer != null) {
					mPlayer.stop();
				}
				mPlayer.prepare();
				mPlayer.start();
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			mPlayer.setOnCompletionListener(listener);
		} catch (Exception e) {
			Log.e(TAG, "prepare() failed");
		}

		return false;
	}

	@Override
	public boolean stop() {
		if (mPlayer != null && mPlayer.isPlaying()) {
			mPlayer.stop();
			mPlayer.release();
			mPlayer = null;
		}
		return false;
	}

}
