package com.yhy.hzzll.home.activity.collaborate;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.yhy.hzzll.R;

import com.yhy.hzzll.mian.activity.BaseActivity;

/** 协作详情支付完成 */
public class LawyerCollaboratPaysucessActivity extends BaseActivity {

	// private String activity;

	private String lawyerCollid;

	private int totalmoney;

	@ViewInject(R.id.tv_feedback)
	private TextView tv_feedback;


	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_lawyer_collaborat_paysucess);
		super.onCreate(arg0);

		ViewUtils.inject(this);

		if (null != getIntent()) {
			// activity = getIntent().getStringExtra("activity");
			lawyerCollid = getIntent().getStringExtra("lawyerCollid");
			totalmoney = getIntent().getIntExtra("totalmoney",0);
		}

		if (totalmoney == 0){
			tv_feedback.setText("支付成功 您已成功发布律师协作，请耐心等待律师申请。");
		}else {
			tv_feedback.setText("您已成功支付酬金 您的此项协作酬金已增到"+ totalmoney +"元");
		}


	}

	@OnClick(R.id.tv_check)
	public void onClick(View view) {
		// try {
		// if (activity.equals(LawyerCollaboratDetailsActivity.class
		// .toString())) {
		if (LawyerCollaboratDetailsActivity.lawyerCollaboratDetailsActivity != null)
			LawyerCollaboratDetailsActivity.lawyerCollaboratDetailsActivity
					.finish();
		if (LawyerCollaboratHostingChangeActivity.lawyerCollaboratHostingChangeActivity != null)
			LawyerCollaboratHostingChangeActivity.lawyerCollaboratHostingChangeActivity
					.finish();
//		if (EvidenceTelephoneMealActivity.evidenceTelephoneMealActivity != null)
//			EvidenceTelephoneMealActivity.evidenceTelephoneMealActivity
//					.finish();
		if (LawyerCollaboratHosting2ChangeActivity.lawyerCollaboratHostingChangeActivity != null) {
			LawyerCollaboratHosting2ChangeActivity.lawyerCollaboratHostingChangeActivity
					.finish();
		}

//		if (PayActivity.payActivity != null)
//			PayActivity.payActivity.finish();
		finish();
		startActivity(new Intent().putExtra("id", lawyerCollid).putExtra("title", "00000").putExtra("from", "footprint").setClass(
				context, LawyerCollaboratDetailsActivity.class));

		// } else if (activity
		// .equals(LawyerCollaboratHostingChangeActivity.class
		// .toString())) {
		// startActivity(new Intent().setClass(context,
		// LawyerCollaboratDetailsActivity.class));
		// } else if (activity.equals(EvidenceTelephoneMealActivity.class
		// .toString())) {
		// startActivity(new Intent().setClass(context,
		// EvidenceTelephoneMealActivity.class));
		// }
		// } catch (Exception e) {
		// // TODO: handle exception
		// }
	}
	
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {

		return true;
	}
	
	
}
