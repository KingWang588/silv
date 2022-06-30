package com.yhy.hzzll.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.yhy.hzzll.R;
import com.yhy.hzzll.entity.SortEntity;

import java.util.List;

public class PopupWindowSort extends PopupWindow {

	private View conentView;
	private ListView listView;
	private OnClick click;
	private List<SortEntity> sortList;

	public interface OnClick {
		void select(String title, int index);

		void dissmiss();
	}

	public void setOnclick(OnClick click) {
		this.click = click;
	}

	public PopupWindowSort(final Activity context, List<SortEntity> sortLists) {
		this.sortList = sortLists;
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		conentView = inflater.inflate(R.layout.popup_sort, null);
		int height = context.getResources().getDisplayMetrics().heightPixels;
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

		listView = (ListView) conentView.findViewById(R.id.lv_sort);
		listView.setAdapter(new BaseAdapter() {
			@Override
			public View getView(int position, View view, ViewGroup arg2) {
				view = LayoutInflater.from(context).inflate(
						R.layout.item_popup_sort, null);
				TextView tv_content = (TextView) view
						.findViewById(R.id.tv_content);
				ImageView iv_tick = (ImageView) view.findViewById(R.id.iv_tick);
				tv_content.setText(sortList.get(position).getTitle());

				if (sortList.get(position).isSelect()) {
					iv_tick.setVisibility(View.VISIBLE);
				} else {
					iv_tick.setVisibility(View.GONE);
				}

				return view;
			}

			@Override
			public long getItemId(int arg0) {
				return arg0;
			}

			@Override
			public Object getItem(int arg0) {
				return sortList.get(arg0);
			}

			@Override
			public int getCount() {
				return sortList.size();
			}
		});

		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				if (click != null)
					click.select(sortList.get(arg2).getTitle().toString(), arg2);
			}
		});

		// 设置背景颜色变暗
		WindowManager.LayoutParams lp = context.getWindow().getAttributes();
		lp.alpha = 0.9f;
		context.getWindow().setAttributes(lp);
		this.setOnDismissListener(new OnDismissListener() {
			@Override
			public void onDismiss() {
				WindowManager.LayoutParams lp = context.getWindow()
						.getAttributes();
				lp.alpha = 1f;
				context.getWindow().setAttributes(lp);
				if (click != null)
					click.dissmiss();
			}
		});
	}
	
	public PopupWindowSort(final Activity context, List<SortEntity> sortLists,int width,int height) {
		this.sortList = sortLists;
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		conentView = inflater.inflate(R.layout.popup_sort, null);
//		int height = context.getResources().getDisplayMetrics().heightPixels;
		// 设置SelectPicPopupWindow的View
		this.setContentView(conentView);
		// 设置SelectPicPopupWindow弹出窗体的宽
		this.setWidth(width);
		// 设置SelectPicPopupWindow弹出窗体的高
		this.setHeight(height);
		// 设置SelectPicPopupWindow弹出窗体可点击
		this.setFocusable(true);
		this.setOutsideTouchable(true);
		// 刷新状态
		this.update();
		// 实例化一个ColorDrawable颜色为半透明
		ColorDrawable dw = new ColorDrawable(0000000000);

		this.setBackgroundDrawable(dw);

		listView = (ListView) conentView.findViewById(R.id.lv_sort);
		listView.setAdapter(new BaseAdapter() {
			@Override
			public View getView(int position, View view, ViewGroup arg2) {
				view = LayoutInflater.from(context).inflate(R.layout.item_popup_sort, null);
				TextView tv_content = (TextView) view
						.findViewById(R.id.tv_content);
				ImageView iv_tick = (ImageView) view.findViewById(R.id.iv_tick);
				tv_content.setText(sortList.get(position).getTitle());

				if (sortList.get(position).isSelect()) {
					iv_tick.setVisibility(View.VISIBLE);
				} else {
					iv_tick.setVisibility(View.GONE);
				}


				return view;
			}

			@Override
			public long getItemId(int arg0) {

				return arg0;
			}

			@Override
			public Object getItem(int arg0) {

				return sortList.get(arg0);
			}

			@Override
			public int getCount() {

				return sortList.size();
			}
		});

		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				if (click != null)
					click.select(sortList.get(arg2).getTitle().toString(), arg2);
			}
		});

		// 设置背景颜色变暗
		WindowManager.LayoutParams lp = context.getWindow().getAttributes();
		lp.alpha = 0.8f;
		context.getWindow().setAttributes(lp);
		this.setOnDismissListener(new OnDismissListener() {
			@Override
			public void onDismiss() {
				WindowManager.LayoutParams lp = context.getWindow()
						.getAttributes();
				lp.alpha = 1f;
				context.getWindow().setAttributes(lp);
				if (click != null)
					click.dissmiss();
			}
		});
	}

	/**
	 * 显示popupWindow
	 * @param parent
	 */
	public void showPopupWindow(View parent) {
		if (!this.isShowing()) {
			this.showAtLocation(parent, Gravity.BOTTOM, 0, 0);
		} else {
			this.dismiss();
		}
	}
}