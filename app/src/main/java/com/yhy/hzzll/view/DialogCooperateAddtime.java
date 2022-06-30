package com.yhy.hzzll.view;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;
import android.widget.TextView;
import android.widget.TimePicker;

import com.yhy.hzzll.R;
import com.yhy.hzzll.interfacelib.Connect;

/**
 * 律师协作详情--延长协作时间
 * 
 * @author Yang
 * 
 */
public class DialogCooperateAddtime implements OnDateChangedListener {

	private AlertDialog alertDialog;

	private View view;
	private String dateTime;

	private DatePicker datepicker;

	public AlertDialog showDialog(final Context context, final Connect connect) {
		alertDialog = new AlertDialog.Builder(context).create();
		alertDialog.show();
		alertDialog.setCancelable(true);
		alertDialog.setCanceledOnTouchOutside(true);
		// 关键在下面的两行,使用window.setContentView,替换整个对话框窗口的布局
		Window window = alertDialog.getWindow();
		view = LayoutInflater.from(context).inflate(
				R.layout.dialog_cooperate_addtime, null);
		TextView tv_left = (TextView) view.findViewById(R.id.tv_left);
		TextView tv_right = (TextView) view.findViewById(R.id.tv_right);
		datepicker = (DatePicker) view.findViewById(R.id.datepicker);
		init(datepicker);
		tv_left.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				alertDialog.dismiss();
			}
		});

		tv_right.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (connect != null)
					connect.text(dateTime);
				alertDialog.dismiss();
			}
		});
		onDateChanged(null, 0, 0, 0);
		window.setContentView(view);
		return alertDialog;
	}

	private void init(DatePicker datePicker) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeZone(TimeZone.getTimeZone("GMT+8"));
		// if (!(null == initDateTime || "".equals(initDateTime))) {
		// calendar = this.getCalendarByInintData(initDateTime);
		// } else {
		// initDateTime = calendar.get(Calendar.YEAR) + "-"
		// + calendar.get(Calendar.MONTH) + "-"
		// + calendar.get(Calendar.DAY_OF_MONTH) + "- ";
		// }

		datePicker.init(calendar.get(Calendar.YEAR),
				calendar.get(Calendar.MONTH),
				calendar.get(Calendar.DAY_OF_MONTH), this);
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
