package com.yhy.hzzll.home.activity.collaborate;

import android.os.Bundle;

import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.yhy.hzzll.R;
import com.yhy.hzzll.adapter.MyPublishLawyerAdapter;
import com.yhy.hzzll.mian.activity.BaseActivity;

/**
 * 我的办公室--我的协作
 * 
 * @author Yang
 * 
 */
public class LawyerCollaboratActivity extends BaseActivity {


	@ViewInject(R.id.lv_lawyer)
	private PullToRefreshListView lv_lawyer;

	private MyPublishLawyerAdapter lawyerAdapter;

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_my_plawyer);
		super.onCreate(arg0);
		ViewUtils.inject(this);
		viewInit();
	}

	private void viewInit() {
		//lawyerAdapter = new MyPublishLawyerAdapter(context);
		//lv_lawyer.setAdapter(lawyerAdapter);
	}

}
