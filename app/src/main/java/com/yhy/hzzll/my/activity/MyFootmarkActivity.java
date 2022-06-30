package com.yhy.hzzll.my.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.yhy.hzzll.R;
import com.yhy.hzzll.my.adapter.MyFootmarkAdapter;
import com.yhy.hzzll.entity.Footprint;
import com.yhy.hzzll.framework.HzApplication;
import com.yhy.hzzll.home.activity.lawyeroffice.LawyerOfficeActivity;
import com.yhy.hzzll.home.activity.collaborate.LawyerCollaboratDetailsActivity;
import com.yhy.hzzll.home.activity.consult.ConsultDetailsActivity;
import com.yhy.hzzll.mian.activity.BaseActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 我的足迹
 * 
 * @author Yang
 * 
 */
public class MyFootmarkActivity extends BaseActivity {

	@ViewInject(R.id.lv_footmark)
	private ListView lv_footmark;
	@ViewInject(R.id.tv_no_data)
	TextView tv_no_data;
	@ViewInject(R.id.tv_clear)
	TextView tv_clear;
	private MyFootmarkAdapter footmarkAdapter;

	List<Footprint> allFootptint ;

	@OnClick({ R.id.tv_clear })
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.tv_clear:
			HzApplication.getDaoInstant().getFootprintDao().deleteInTx(allFootptint);

			allFootptint.clear();
			footmarkAdapter.notifyDataSetChanged();

			break;
		}
	}

	@Override
	protected void onCreate(Bundle arg0) {
		setContentView(R.layout.activity_footmark);
		super.onCreate(arg0);
		ViewUtils.inject(this);

		allFootptint = new ArrayList<>();
		allFootptint = HzApplication.getDaoInstant().getFootprintDao().loadAll();

		Collections.reverse(allFootptint);

		if (!(allFootptint.size() == 0)) {
			tv_clear.setVisibility(View.VISIBLE);
		}

		footmarkAdapter = new MyFootmarkAdapter(context, allFootptint);

		lv_footmark.setEmptyView(tv_no_data);
		lv_footmark.setAdapter(footmarkAdapter);
		lv_footmark.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				if (allFootptint.get(arg2).getCate_name().equals("律师协作")) {
					startActivity(new Intent().putExtra("id", allFootptint.get(arg2).getNid())
							.putExtra("title", allFootptint.get(arg2).getTitle()).putExtra("insert",false)
							.setClass(context, LawyerCollaboratDetailsActivity.class));

				} else if (allFootptint.get(arg2).getCate_name().equals("律师办公室")) {
					startActivity(
							new Intent().putExtra("user_id", allFootptint.get(arg2).getNid())
									.putExtra("title", allFootptint.get(arg2).getTitle()).putExtra("insert",false)
									.setClass(context, LawyerOfficeActivity.class));
				} else {
					startActivity(new Intent().putExtra("id", allFootptint.get(arg2).getNid())
							.putExtra("title", allFootptint.get(arg2).getTitle()).putExtra("insert",false)
							.setClass(context, ConsultDetailsActivity.class));
				}

			}
		});

	}

}
