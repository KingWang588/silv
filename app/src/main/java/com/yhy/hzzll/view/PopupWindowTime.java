package com.yhy.hzzll.view;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.TimePicker;

import com.yhy.hzzll.R;
import com.yhy.hzzll.interfacelib.Connect;
import com.yhy.hzzll.utils.DateUtils;

public class PopupWindowTime extends PopupWindow implements
		OnDateChangedListener {

	
	
	private View conentView;
	
	private boolean isSetMindata;
	
	private String dateTime;
	  long mindata ;
	private DatePicker datepicker;

	public PopupWindowTime(final Activity context, final Connect connect ,boolean isSetMindata) {
		this.isSetMindata = isSetMindata;
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		conentView = inflater.inflate(R.layout.popupwindow_time, null);
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

		TextView tv_left = (TextView) conentView.findViewById(R.id.tv_left);
		TextView tv_right = (TextView) conentView.findViewById(R.id.tv_right);
		datepicker = (DatePicker) conentView.findViewById(R.id.datepicker);
		
		if(isSetMindata){
			datepicker.setMinDate(   DateUtils.getStringToDate(DateUtils.getCurrentDate()));
		}else{
//			datepicker.setMaxDate(   DateUtils.getStringToDate(DateUtils.getCurrentDate()));
		}
		
		init(datepicker);
		tv_left.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (connect != null)
					connect.text(dateTime);
				dismiss();
			}
		});

		tv_right.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

				dismiss();
			}
		});
		onDateChanged(null, 0, 0, 0);

		setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
	}

	private void init(DatePicker datePicker) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeZone(TimeZone.getTimeZone("GMT+8"));
		datePicker.init(calendar.get(Calendar.YEAR),
				calendar.get(Calendar.MONTH),
				calendar.get(Calendar.DAY_OF_MONTH), this);
	}

	@Override
	public void onDateChanged(DatePicker arg0, int arg1, int arg2, int arg3) {
		// 获得日历实例
		Calendar calendar = Calendar.getInstance();
		calendar.set(datepicker.getYear(), datepicker.getMonth(),
				datepicker.getDayOfMonth());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		dateTime = sdf.format(calendar.getTime());
	   
		
	}

	public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
		onDateChanged(null, 0, 0, 0);
	}

}
