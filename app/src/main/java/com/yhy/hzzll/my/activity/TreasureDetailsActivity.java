package com.yhy.hzzll.my.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.yhy.hzzll.R;
import com.yhy.hzzll.mian.activity.BaseActivity;
import com.yhy.hzzll.mian.activity.MainActivity;
import com.yhy.hzzll.my.fragment.AllIncomeAndExpensesFragment;
import com.yhy.hzzll.my.fragment.ExpensesFragment;
import com.yhy.hzzll.my.fragment.IncomeFragment;

/**
 * 财富明细
 * 
 * @author Yang
 * 
 */
public class TreasureDetailsActivity extends BaseActivity {

	@ViewInject(R.id.tv_all)
	private TextView tv_all;
	@ViewInject(R.id.tv_inside)
	private TextView tv_inside;
	@ViewInject(R.id.tv_outside)
	private TextView tv_outside;

	private Fragment mTab01;
	private Fragment mTab02;
	private Fragment mTab03;

	public static final String START_DATE_INTENT = "START_DATE_INTENT";
	public static final String END_DATE_INTENT = "END_DATE_INTENT";

	private String startDate;
	private String endDate;

	@Override
	protected void onCreate(Bundle arg0) {
		setContentView(R.layout.activity_treasure_details);
		super.onCreate(arg0);
		ViewUtils.inject(this);
		init();
	}

	private void init() {
		startDate = getIntent().getStringExtra(START_DATE_INTENT);
		endDate = getIntent().getStringExtra(END_DATE_INTENT);
		setSelect(0);

	}

	private void setSelect(int i) {
		FragmentManager fm = getSupportFragmentManager();
		FragmentTransaction transaction = fm.beginTransaction();
		hideFragment(transaction);
		// 把图片设置为亮的
		// 设置内容区域
		switch (i) {
		case 0:
			if (mTab01 == null) {
				mTab01 = AllIncomeAndExpensesFragment.newInstance(startDate, endDate);
				transaction.add(R.id.id_content, mTab01);
			} else {
				transaction.show(mTab01);
			}
			tv_all.setBackgroundColor(getResources().getColor(R.color.white));
			break;
		case 1:
			if (mTab02 == null) {
				mTab02 = IncomeFragment.newInstance(startDate, endDate);
				transaction.add(R.id.id_content, mTab02);
			} else {
				transaction.show(mTab02);

			}
			tv_inside.setBackgroundColor(getResources().getColor(R.color.white));
			break;
		case 2:
			if (mTab03 == null) {
				mTab03 =  ExpensesFragment.newInstance(startDate, endDate);
				transaction.add(R.id.id_content, mTab03);
			} else {
				transaction.show(mTab03);
			}
			tv_outside.setBackgroundColor(getResources().getColor(R.color.white));
			break;
		default:
			break;
		}

		transaction.commit();
	}
	
	/**
	 * 切换图片至暗色
	 */
	private void resetImgs()
	{
		tv_all.setBackgroundColor(getResources().getColor(R.color.ccgrey));
		tv_inside.setBackgroundColor(getResources().getColor(R.color.ccgrey));
		tv_outside.setBackgroundColor(getResources().getColor(R.color.ccgrey));
	}

	private void hideFragment(FragmentTransaction transaction) {
		if (mTab01 != null) {
			transaction.hide(mTab01);
		}
		if (mTab02 != null) {
			transaction.hide(mTab02);
		}
		if (mTab03 != null) {
			transaction.hide(mTab03);
		}
	}

	@OnClick({ R.id.tv_all, R.id.tv_inside, R.id.tv_outside,R.id.tv_home })
	public void onClick(View view) {
		resetImgs();
		switch (view.getId()) {

			case R.id.tv_home:

				startActivity(new Intent().putExtra("tab", 0).setClass(getApplicationContext(), MainActivity.class));

				break;


		case R.id.tv_all:
			setSelect(0);
			break;
		case R.id.tv_inside:
			setSelect(1);
			break;
		case R.id.tv_outside:
			setSelect(2);
			break;
		}
	}
}
