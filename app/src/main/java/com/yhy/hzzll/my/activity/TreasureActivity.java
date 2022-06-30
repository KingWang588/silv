package com.yhy.hzzll.my.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.yhy.hzzll.R;
import com.yhy.hzzll.framework.MyData;
import com.yhy.hzzll.interfacelib.Connect;
import com.yhy.hzzll.mian.activity.BaseActivity;
import com.yhy.hzzll.mian.entity.DataUserEntity;
import com.yhy.hzzll.utils.HttpDataUtils;
import com.yhy.hzzll.utils.PrefsUtils;
import com.yhy.hzzll.view.PopupWindowTime;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 我的财富
 * 
 * @author wangyang
 * 
 */
public class TreasureActivity extends BaseActivity {

	private String initStartDateTime; // 初始化时间

	@ViewInject(R.id.tv_startdata)
	private TextView tv_startdata;

	@ViewInject(R.id.tv_enddata)
	private TextView tv_enddata;

	@ViewInject(R.id.tv_one)
	private TextView tv_one;

	@ViewInject(R.id.tv_three)
	private TextView tv_three;

	@ViewInject(R.id.tv_six)
	private TextView tv_six;

	@ViewInject(R.id.tv_doc)
	TextView tv_doc;
	@ViewInject(R.id.tv_working_income)
	TextView tv_working_income;
	@ViewInject(R.id.tv_frozen_money)
	TextView tv_frozen_money;
	@ViewInject(R.id.tv_balance)
	TextView tv_balance;

    DataUserEntity	userEntity;
    String m4;


	DecimalFormat df;

	@Override
	protected void onCreate(Bundle arg0) {
		setContentView(R.layout.activity_treasure);
		super.onCreate(arg0);
		ViewUtils.inject(this);
		init();
	}

	private void init() {
		df = new DecimalFormat("00");
		Calendar ca = Calendar.getInstance();
		int year = ca.get(Calendar.YEAR);// 获取年份
		int month = ca.get(Calendar.MONTH);// 获取月份
		int day = ca.get(Calendar.DATE);// 获取日
		initStartDateTime = year + "年" + (month + 1) + "月" + day + "日";

		tv_one.setSelected(true);
		tv_three.setSelected(false);
		tv_six.setSelected(false);


		Calendar c = Calendar.getInstance();
		int currYear = c.get(Calendar.YEAR);
		int currMonth = c.get(Calendar.MONTH) + 1;
		int currDay = c.get(Calendar.DAY_OF_MONTH);
		int previousYear = 0;

		String ys = currYear + "";
		String ms = currMonth + "";
		String ds = currDay + "";

		if (currMonth < 10) {
			ms = "0" + currMonth;
		}
		if (currDay < 10) {
			ds = "0" + currDay;
		}
		tv_enddata.setText(ys + "-" + ms + "-" + ds);
		String months = df.format(currMonth - 1);
		if ((currMonth - 1) <= 0) {
			previousYear = currYear - 1;
			String ys2 = previousYear + "";
			String ms2 = months + "";
			String ds2 = currDay + "";

			if (currMonth < 10) {
				ms2 = "0" + currMonth;
			}
			if (currDay < 10) {
				ds2 = "0" + currDay;
			}
			tv_startdata.setText(ys2 + "-" + ms2 + "-" + ds2);

			// tv_startdata.setText(previousYear + "-" + months + "-" +
			// currDay);
		} else {
			String ys2 = currYear + "";
			String ms2 = months + "";
			String ds2 = currDay + "";

			if (currMonth < 10) {
				ms2 = "0" + currMonth;
			}
			if (currDay < 10) {
				ds2 = "0" + currDay;
			}
			tv_startdata.setText(ys2 + "-" + ms2 + "-" + ds2);

			// tv_startdata.setText(currYear + "-" + months + "-" + currDay);
		}
		tv_one.setSelected(true);
		tv_three.setSelected(false);
		tv_six.setSelected(false);
	}

	@OnClick({ R.id.tv_startdata, R.id.tv_enddata, R.id.rl_withdraw, R.id.rl_recharge, R.id.tv_one, R.id.tv_three,
			R.id.tv_six, R.id.rl_voucher, R.id.tv_search_record })
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.tv_startdata:// 开始日期

			PopupWindowTime windowTime = new PopupWindowTime(this, new Connect() {
				@Override
				public void text(String text) {
					tv_startdata.setText(text);
				}
			}, false);
			windowTime.showAtLocation(view, Gravity.BOTTOM, 0, 0);

			break;
		case R.id.tv_enddata:// 结束日期

			PopupWindowTime windowTime2 = new PopupWindowTime(this, new Connect() {
				@Override
				public void text(String text) {
					tv_enddata.setText(text);
				}
			}, false);
			windowTime2.showAtLocation(view, Gravity.BOTTOM, 0, 0);

			break;
		case R.id.rl_withdraw:// 提现


			startActivity(new Intent().setClass(context, TreasureWithdrawActivity.class).putExtra("money",m4));
			break;

		case R.id.tv_one:
			tv_one.setSelected(true);
			tv_three.setSelected(false);
			tv_six.setSelected(false);

			Calendar c = Calendar.getInstance();
			int currYear = c.get(Calendar.YEAR);
			int currMonth = c.get(Calendar.MONTH) + 1;
			int currDay = c.get(Calendar.DAY_OF_MONTH);

			String ys = currYear + "";
			String ms = currMonth + "";
			String ds = currDay + "";

			if (currMonth < 10) {
				ms = "0" + currMonth;
			}
			if (currDay < 10) {
				ds = "0" + currDay;
			}
			tv_enddata.setText(ys + "-" + ms + "-" + ds);

			String months = df.format(currMonth - 1);
			if (currMonth > 2) {
				currMonth = currMonth - 1;
			} else {
				currYear = currYear - 1;
				currMonth = 12;
			}

			String ys2 = currYear + "";
			String ms2 = currMonth + "";
			String ds2 = currDay + "";

			if (currMonth < 10) {
				ms2 = "0" + currMonth;
			}
			if (currDay < 10) {
				ds2 = "0" + currDay;
			}
			tv_startdata.setText(ys2 + "-" + ms2 + "-" + ds2);
			break;
		case R.id.tv_three:
			tv_one.setSelected(false);
			tv_three.setSelected(true);
			tv_six.setSelected(false);
			Calendar c2 = Calendar.getInstance();
			int currYear2 = c2.get(Calendar.YEAR);
			int currMonth2 = c2.get(Calendar.MONTH) + 1;
			int currDay2 = c2.get(Calendar.DAY_OF_MONTH);

			String y = currYear2 + "";
			String m = currMonth2 + "";
			String d = currDay2 + "";

			if (currMonth2 < 10) {
				m = "0" + currMonth2;
			}
			if (currDay2 < 10) {
				d = "0" + currDay2;
			}
			tv_enddata.setText(y + "-" + m + "-" + d);

			if (currMonth2 > 4) {
				currMonth2 = currMonth2 - 3;
			} else {
				currYear2 = currYear2 - 1;
				if (currMonth2 == 3) {
					currMonth2 = 12;
				} else if (currMonth2 == 2) {
					currMonth2 = 11;
				} else if (currMonth2 == 1) {
					currMonth2 = 10;
				}
			}

			String y2 = currYear2 + "";
			String m2 = currMonth2 + "";
			String d2 = currDay2 + "";

			if (currMonth2 < 10) {
				m2 = "0" + currMonth2;
			}
			if (currDay2 < 10) {
				d2 = "0" + currDay2;
			}
			tv_startdata.setText(y2 + "-" + m2 + "-" + d2);
			break;
		case R.id.tv_six:
			tv_one.setSelected(false);
			tv_three.setSelected(false);
			tv_six.setSelected(true);

			Calendar c3 = Calendar.getInstance();
			c3.setTime(new Date());
			c3.add(Calendar.MONTH, -6);
//			System.out.println(DateUtils.date2String("yyyy-MM-dd", c3.getTime()));

			int currYear3 = c3.get(Calendar.YEAR);
			int currMonth3 = c3.get(Calendar.MONTH) + 1;
			int currDay3 = c3.get(Calendar.DAY_OF_MONTH);
//
			String yy = currYear3 + "";
			String mm = currMonth3 + "";
			String dd = currDay3 + "";

			tv_startdata.setText(yy + "-" + mm + "-" + dd);

			break;
		case R.id.tv_search_record:
			String startDate = tv_startdata.getText().toString();
			String endDate = tv_enddata.getText().toString();

			if (startDate.equals(endDate)) {
				getOneMonth();
				startDate = tv_startdata.getText().toString();
			}

			Intent intent = new Intent().setClass(context, TreasureDetailsActivity.class);
			intent.putExtra(TreasureDetailsActivity.START_DATE_INTENT, startDate);
			intent.putExtra(TreasureDetailsActivity.END_DATE_INTENT, endDate);
			startActivity(intent);
			break;
		}
	}

	private void getOneMonth() {

		Calendar c = Calendar.getInstance();
		int currYear = c.get(Calendar.YEAR);
		int currMonth = c.get(Calendar.MONTH) + 1;
		int currDay = c.get(Calendar.DAY_OF_MONTH);

		String ys = currYear + "";
		String ms = currMonth + "";
		String ds = currDay + "";

		if (currMonth < 10) {
			ms = "0" + currMonth;
		}
		if (currDay < 10) {
			ds = "0" + currDay;
		}
		tv_enddata.setText(ys + "-" + ms + "-" + ds);

		String months = df.format(currMonth - 1);
		if (currMonth > 2) {
			currMonth = currMonth - 1;
		} else {
			currYear = currYear - 1;
			currMonth = 12;
		}

		String ys2 = currYear + "";
		String ms2 = currMonth + "";
		String ds2 = currDay + "";

		if (currMonth < 10) {
			ms2 = "0" + currMonth;
		}
		if (currDay < 10) {
			ds2 = "0" + currDay;
		}

		tv_startdata.setText(ys2 + "-" + ms2 + "-" + ds2);

	}

	@Override
	protected void onResume() {
		getMoney();
		super.onResume();
	}

	private void getMoney() {

		RequestParams params = new RequestParams();
		params.addHeader("Authorization", PrefsUtils.getString(TreasureActivity.this, PrefsUtils.AUTHORIZATION));
		httpDataUtils.sendGet(MyData.CHECK_OUT_USER_INFO, params);
		httpDataUtils.setSucessBack(new HttpDataUtils.SucessBack() {
			@Override
			public void sucess(ResponseInfo<String> arg0) {
				try {
					JSONObject object = new JSONObject(arg0.result);
					String code = object.optString("code");
					String msg = object.optString("msg");
					if (code.equals("0")) {
						Gson gson = new Gson();
						try {

                            userEntity = gson.fromJson(arg0.result, DataUserEntity.class);
							String m1 = userEntity.getData().getFinance().getAmt_consult();
							m1 = m1.substring(0,m1.length()-3);

							String m2 = userEntity.getData().getFinance().getAmt_case();
							m2 = m2.substring(0,m2.length()-3);

							String m3 = userEntity.getData().getFinance().getAmt_freeze();
							m3 = m3.substring(0,m3.length()-3);

							m4 = userEntity.getData().getFinance().getAmt_balance();
							m4 = m4.substring(0,m4.length()-3);

							tv_doc.setText(m1+"元");
							tv_working_income.setText(m2+"元");
							tv_frozen_money.setText(m3+"元");
							tv_balance.setText(m4+"元");


						} catch (Exception e) {

						}
					} else {
						// ToastUtils.getUtils(getActivity()).show(msg);
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		});
	}
}
