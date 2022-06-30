package com.yhy.hzzll.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.google.gson.Gson;
import com.wpd.address.wheel.widget.views.AbstractWheelTextAdapter;
import com.wpd.address.wheel.widget.views.OnWheelChangedListener;
import com.wpd.address.wheel.widget.views.OnWheelScrollListener;
import com.wpd.address.wheel.widget.views.WheelView;
import com.yhy.hzzll.R;
import com.yhy.hzzll.entity.AllStatusEntity;
import com.yhy.hzzll.entity.SortEntity;
import com.yhy.hzzll.utils.HttpDataUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 发布咨询的标题
 * 
 * @author Yang
 * 
 */
public class PopupWindowConsultTitle extends PopupWindow implements OnClickListener, OnWheelChangedListener {

	private View conentView;

	private WheelView lawyer_job;

	private ArrayList<String> jobList = new ArrayList<String>();

	private AddressTextAdapter adapter;

	private TextView btnSure;// 确定按钮

	private TextView btn_myinfo_cancel;// 取消按钮
	// 回调方法
	private OnAddressCListener onAddressCListener;

	private HttpDataUtils httpDataUtils;

	private Gson gson;

	private Activity context;

	private List<AllStatusEntity> publishTypeList;

	public PopupWindowConsultTitle(final Activity context, List<AllStatusEntity> publishTypeList) {
		this.context = context;
		this.publishTypeList = publishTypeList;
		httpDataUtils = new HttpDataUtils(context);
		gson = new Gson();
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		conentView = inflater.inflate(R.layout.popupwindow_cooperate_price, null);
		int width = context.getResources().getDisplayMetrics().widthPixels;
		// 设置SelectPicPopupWindow的View
		this.setContentView(conentView);
		// 设置SelectPicPopupWindow弹出窗体的宽
		this.setWidth(width);
		// 设置SelectPicPopupWindow弹出窗体的高
		this.setHeight(LayoutParams.MATCH_PARENT);
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

		// // 设置背景颜色变暗
		// WindowManager.LayoutParams lp = context.getWindow().getAttributes();
		// lp.alpha = 0.9f;
		// context.getWindow().setAttributes(lp);
		// this.setOnDismissListener(new OnDismissListener() {
		// @Override
		// public void onDismiss() {
		// WindowManager.LayoutParams lp = context.getWindow()
		// .getAttributes();
		// lp.alpha = 1f;
		// context.getWindow().setAttributes(lp);
		// }
		// });

		TextView tv_share_title = (TextView) conentView.findViewById(R.id.tv_share_title);
		tv_share_title.setText("选择类型");
		setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

		getStatus();
	}

	public interface OnAddressCListener {
		void onClick(String job, String index);
	}

	public void setTextviewSize(String curriteItemText, AddressTextAdapter adapter) {
		ArrayList<View> arrayList = adapter.getTestViews();
		int size = arrayList.size();
		String currentText;
		for (int i = 0; i < size; i++) {
			TextView textvew = (TextView) arrayList.get(i);
			currentText = textvew.getText().toString();
			if (curriteItemText.equals(currentText)) {
				textvew.setTextSize(24);
			} else {
				textvew.setTextSize(14);
			}
		}
	}

	private void getStatus() {
		List<SortEntity> sortList = new ArrayList<SortEntity>();
//		sortList.add(new SortEntity("金融借贷", 0, false));
//		sortList.add(new SortEntity("建筑房产", 1, false));
//		sortList.add(new SortEntity("投资证券", 2, false));
//		sortList.add(new SortEntity("并购重组", 3, false));
//		sortList.add(new SortEntity("公司事务", 4, false));
//		sortList.add(new SortEntity("婚姻家庭", 5, false));
//		sortList.add(new SortEntity("债权债务", 6, false));
//		sortList.add(new SortEntity("合同纠纷", 7, false));
//		sortList.add(new SortEntity("人身损害", 8, false));
//		sortList.add(new SortEntity("交通保险", 9, false));
//		sortList.add(new SortEntity("医疗事故", 10, false));
//		sortList.add(new SortEntity("劳动纠纷", 11, false));
//		sortList.add(new SortEntity("环境保护", 12, false));
//		sortList.add(new SortEntity("海事海商", 13, false));
//		sortList.add(new SortEntity("知识产权", 14, false));
//		sortList.add(new SortEntity("征地拆产", 15, false));
//		sortList.add(new SortEntity("涉外事务", 16, false));
//		sortList.add(new SortEntity("行政诉讼", 17, false));
//		sortList.add(new SortEntity("行政复议", 18, false));
//		sortList.add(new SortEntity("刑事诉讼", 19, false));
//		sortList.add(new SortEntity("法律顾问", 20, false));
//		sortList.add(new SortEntity("国家赔偿", 21, false));
//		sortList.add(new SortEntity("公司上市", 22, false));
//		sortList.add(new SortEntity("消费权益", 23, false));
		
		
		for (int j = 0; j < sortList.size(); j++) {
			jobList.add(sortList.get(j).getTitle());
		}
		handler.sendEmptyMessage(1);
//		RequestParams params = new RequestParams();
//		httpDataUtils.sendGet(MyData.GETALLSTATUS, params);
//		httpDataUtils.setSucessBack(new HttpDataUtils.SucessBack() {
//			@Override
//			public void sucess(ResponseInfo<String> arg0) {
//				if (httpDataUtils.code(arg0.result)) {
//					try {
//						JSONArray array = new JSONArray(httpDataUtils.data(arg0.result));
//						for (int i = 0; i < array.length(); i++) {
//							JSONObject object = array.optJSONObject(i);
//							AllStatusEntity entity = gson.fromJson(object.toString(), AllStatusEntity.class);
//
//							if (object.optString("remark").equals("咨询类型")) {
//								publishTypeList.add(entity);
//							}
//						}
//
//						for (int j = 0; j < publishTypeList.size(); j++) {
//							jobList.add(publishTypeList.get(j).getStatus_name());
//						}
//						handler.sendEmptyMessage(1);
//					} catch (JSONException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//
//				} else {
//					httpDataUtils.showMsg(arg0.result);
//				}
//			}
//		});
	}

	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			lawyer_job = (WheelView) conentView.findViewById(R.id.lawyer_job);
			adapter = new AddressTextAdapter(context, jobList, 0, 24, 14);
			lawyer_job.setVisibleItems(5);
			lawyer_job.setViewAdapter(adapter);
			// for (int i = 0; i < publishTypeList.size(); i++) {
			// if (PrefsUtils.getString(context,
			// ScreeningActivity.SCREENTYPE2ID).equals(
			// publishTypeList.get(i).getId())) {
			String currentText = (String) adapter.getItemText(lawyer_job.getCurrentItem());
			setTextviewSize(currentText, adapter);
			// }
			// }

			lawyer_job.addScrollingListener(new OnWheelScrollListener() {
				@Override
				public void onScrollingStarted(WheelView wheel) {
				}

				@Override
				public void onScrollingFinished(WheelView wheel) {
					String currentText = (String) adapter.getItemText(wheel.getCurrentItem());
					setTextviewSize(currentText, adapter);
				}
			});
			lawyer_job.addChangingListener(PopupWindowConsultTitle.this);

			btnSure = (TextView) conentView.findViewById(R.id.btn_myinfo_sure);
			btn_myinfo_cancel = (TextView) conentView.findViewById(R.id.btn_myinfo_cancel);

			btnSure.setOnClickListener(PopupWindowConsultTitle.this);
			btn_myinfo_cancel.setOnClickListener(PopupWindowConsultTitle.this);
			super.handleMessage(msg);
		}

	};

	private class AddressTextAdapter extends AbstractWheelTextAdapter {
		ArrayList<String> list;

		protected AddressTextAdapter(Context context, ArrayList<String> list, int currentItem, int maxsize,
				int minsize) {
			super(context, R.layout.item_birth_year, NO_RESOURCE, currentItem, maxsize, minsize);
			this.list = list;
			setItemTextResource(R.id.tempValue);
		}

		@Override
		public View getItem(int index, View cachedView, ViewGroup parent) {
			View view = super.getItem(index, cachedView, parent);
			return view;
		}

		@Override
		public int getItemsCount() {
			return list.size();
		}

		@Override
		protected CharSequence getItemText(int index) {
			return list.get(index) + "";
		}
	}

	public void setAddresskListener(OnAddressCListener onAddressCListener) {
		this.onAddressCListener = onAddressCListener;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_myinfo_sure:
			
			if (onAddressCListener != null) {
				onAddressCListener.onClick(jobList.get(lawyer_job.getCurrentItem()),"空");
			}
			
		case R.id.btn_myinfo_cancel:
			dismiss();
			break;
		}
		
	}

	@Override
	public void onChanged(WheelView wheel, int oldValue, int newValue) {
		String currentText = (String) adapter.getItemText(wheel.getCurrentItem());
		setTextviewSize(currentText, adapter);
	}
}