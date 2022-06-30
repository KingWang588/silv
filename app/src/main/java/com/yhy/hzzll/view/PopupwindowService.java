package com.yhy.hzzll.view;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.wpd.address.wheel.widget.views.AbstractWheelTextAdapter;
import com.wpd.address.wheel.widget.views.OnWheelChangedListener;
import com.wpd.address.wheel.widget.views.OnWheelScrollListener;
import com.wpd.address.wheel.widget.views.WheelView;
import com.yhy.hzzll.R;
import com.yhy.hzzll.entity.ServiceAndQuoteStatusEntity;

/**
 * 律师协作筛选类型1
 * 
 * @author Yang
 * 
 */
public class PopupwindowService extends PopupWindow implements OnClickListener,
		OnWheelChangedListener {

	private View conentView;

	private WheelView lawyer_job;

	private ArrayList<String> mList = new ArrayList<String>();

	private AddressTextAdapter adapter;

	private TextView btnSure;// 确定按钮

	private TextView btn_myinfo_cancel;// 取消按钮
	// 回调方法
	private OnAddressCListener onAddressCListener;

	private Activity context;

	private List<ServiceAndQuoteStatusEntity> casetypeList;

	public PopupwindowService(final Activity context,
			List<ServiceAndQuoteStatusEntity> casetypeList) {
		this.context = context;
		this.casetypeList = casetypeList;
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		conentView = inflater.inflate(R.layout.popupwindow_service, null);
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

		setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

		getType1Status();
	}

	public interface OnAddressCListener {
		void onClick(String job);
	}

	public void setTextviewSize(String curriteItemText,
			AddressTextAdapter adapter) {
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

	/** 获取分类 */
	private void getType1Status() {
		for (int i = 0; i < casetypeList.size(); i++) {
			mList.add(casetypeList.get(i).getName());
		}

		lawyer_job = (WheelView) conentView.findViewById(R.id.lawyer_job);
		adapter = new AddressTextAdapter(context, mList, 0, 24, 14);
		lawyer_job.setVisibleItems(5);
		lawyer_job.setViewAdapter(adapter);
		lawyer_job.setCurrentItem(0);

		lawyer_job.addScrollingListener(new OnWheelScrollListener() {
			@Override
			public void onScrollingStarted(WheelView wheel) {
			}

			@Override
			public void onScrollingFinished(WheelView wheel) {
				String currentText = (String) adapter.getItemText(wheel
						.getCurrentItem());
				setTextviewSize(currentText, adapter);
			}
		});
		lawyer_job.addChangingListener(PopupwindowService.this);

		btnSure = (TextView) conentView.findViewById(R.id.btn_myinfo_sure);
		btn_myinfo_cancel = (TextView) conentView
				.findViewById(R.id.btn_myinfo_cancel);

		btnSure.setOnClickListener(PopupwindowService.this);
		btn_myinfo_cancel.setOnClickListener(PopupwindowService.this);

	}

	private class AddressTextAdapter extends AbstractWheelTextAdapter {
		ArrayList<String> list;

		protected AddressTextAdapter(Context context, ArrayList<String> list,
				int currentItem, int maxsize, int minsize) {
			super(context, R.layout.item_birth_year, NO_RESOURCE, currentItem,
					maxsize, minsize);
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
				onAddressCListener.onClick(mList.get(lawyer_job
						.getCurrentItem()));
			}
		case R.id.btn_myinfo_cancel:
			dismiss();
			break;
		}
	}

	@Override
	public void onChanged(WheelView wheel, int oldValue, int newValue) {
		String currentText = (String) adapter.getItemText(wheel
				.getCurrentItem());
		setTextviewSize(currentText, adapter);
	}

}
