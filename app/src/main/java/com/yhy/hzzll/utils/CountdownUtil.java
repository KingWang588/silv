package com.yhy.hzzll.utils;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

import android.os.CountDownTimer;
import android.widget.TextView;

/**
 * 
 * 倒计时
 * @author 一合鱼
 *
 */

public class CountdownUtil {

	private long time;
	TextView counetdownView;
	CountdownThread thread;
	SimpleDateFormat formatter;
	String hms;
	/**
	 * @time:时间差(指定的一段时间长),时间戳
	 * @counetdownView：TextView显示倒计时
	 * */
	public CountdownUtil(long time, TextView counetdownView) {
		this.time = time;
		this.counetdownView = counetdownView;
	}
	/**
	 * 倒计时
	 * */
	public void countdown(){
		formatter = new SimpleDateFormat("HH:mm:ss");// 初始化Formatter的转换格式。
		formatter.setTimeZone(TimeZone.getTimeZone("GMT +8:00"));//设置时区(北京),如果你不设置这个,你会发现你的时间总会多出来8个小时

		thread = new CountdownThread(time, 1000);// 构造CountDownTimer对象
		thread.start();
	}
	class CountdownThread extends CountDownTimer {
		public CountdownThread(long millisInFuture, long countDownInterval) {
			super(millisInFuture, countDownInterval);
			// TODO Auto-generated constructor stub
		}
		@Override
		public void onTick(long millisUntilFinished) {
			hms = formatter.format(millisUntilFinished);//转化成  "00:00:00"的格式
			counetdownView.setText(hms);
		}

		@Override
		public void onFinish() {
			// TODO Auto-generated method stub
			//倒计时结束时触发
			counetdownView.setText("00:00:00");
		}
	}
	/**
	 * 终止线程
	 * */
	public void stopThread(){
		thread.cancel();
	}
	
}
