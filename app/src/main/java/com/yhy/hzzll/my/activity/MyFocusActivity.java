package com.yhy.hzzll.my.activity;

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
import com.yhy.hzzll.my.fragment.FocusInfoFragment;
import com.yhy.hzzll.my.fragment.FocusMemberFragment;

import java.util.List;

/**
 * 我的关注
 * 
 * @author wangyang
 * 
 */
public class MyFocusActivity extends BaseActivity {

//	@ViewInject(R.id.pg_focus2)
//	private CustomViewPager pg_focus;

	@ViewInject(R.id.tv_info)
	private TextView tv_info;

	@ViewInject(R.id.tv_vip)
	private TextView tv_vip;

	@ViewInject(R.id.tv_service)
	private TextView tv_service;

	private List<Fragment> list;
	FocusInfoFragment focusInfoFragment;
	FocusMemberFragment focusMemberFragment;

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_focus);
		super.onCreate(arg0);
		ViewUtils.inject(this);
		setDefaultFragment();
	}

	private void setDefaultFragment() {
		tv_info.setSelected(true);
		FragmentManager fm = getSupportFragmentManager();
		FragmentTransaction transaction = fm.beginTransaction();
		focusInfoFragment = new FocusInfoFragment();
		transaction.replace(R.id.id_content, focusInfoFragment);
		transaction.commit();
	}

	// private void viewInit() {
	// list = new ArrayList<Fragment>();
	// list.add(new FocusInfoFragment());
	// list.add(new FocusMemberFragment());
	//// list.add(new FocusServiceFragment());
	//// list.add(new FocusNewsFragment());
	//
	//// FragmentAdapter adapter = new FragmentAdapter(
	//// getSupportFragmentManager(), list);
	//// pg_focus.setAdapter(adapter);
	//// infoDataInit();
	//// pg_focus.setCurrentItem(0, false);
	// }

	// /** 我的关注--信息数据 */
	 private void infoDataInit() {
	 tv_info.setSelected(true);
	 tv_vip.setSelected(false);
//	 tv_service.setSelected(false);
//	 pg_focus.setCurrentItem(0, false);
	 }

	 /** 我的关注--会员数据 */
	 private void vipDataInit() {
	 tv_info.setSelected(false);
	 tv_vip.setSelected(true);
//	 tv_service.setSelected(false);
//	 pg_focus.setCurrentItem(1, false);
	 }

	/** 我的关注--服务数据 */
	private void serviceDataInit() {
		// tv_info.setSelected(false);
		// tv_vip.setSelected(false);
		// tv_service.setSelected(true);
		// pg_focus.setCurrentItem(2, false);
	}

	// /** 我的关注--文章数据 */
	// private void newsDataInit() {
	// tv_info.setSelected(false);
	// tv_vip.setSelected(false);
	// tv_service.setSelected(false);
	// tv_news.setSelected(true);
	// pg_focus.setCurrentItem(3, false);
	//
	// }

	@OnClick({ R.id.tv_info, R.id.tv_vip, R.id.tv_service
			// , R.id.tv_news
	})
	public void onClick(View view) {
		FragmentManager fm = getSupportFragmentManager();
		// 开启Fragment事务
		FragmentTransaction transaction = fm.beginTransaction();
		switch (view.getId()) {
		case R.id.tv_info:
			 infoDataInit();
			if (focusInfoFragment == null) {
				focusInfoFragment = new FocusInfoFragment();
			}
			// 使用当前Fragment的布局替代id_content的控件   白律师
			transaction.replace(R.id.id_content, focusInfoFragment);
			break;
		case R.id.tv_vip:
			 vipDataInit();
			if (focusMemberFragment == null) {
				focusMemberFragment = new FocusMemberFragment();
			}
			transaction.replace(R.id.id_content, focusMemberFragment);
			break;
		case R.id.tv_service:
			// serviceDataInit();
			break;
		// case R.id.tv_news:
		// newsDataInit();
		// break;
		}
		// 事务提交
		transaction.commit();
	}
}
