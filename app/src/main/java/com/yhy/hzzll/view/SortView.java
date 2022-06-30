package com.yhy.hzzll.view;

import java.util.List;

import android.app.Activity;
import android.widget.TextView;

import com.yhy.hzzll.entity.SortEntity;

/**
 * 筛选 title 弹出的框帮助类
 * 
 * @author Yang
 * 
 */
public class SortView {

	private static PopupWindowSort windowSort;

	private static Select select;

	private PopupWindowSort windowSorts;

	private Select selects;

	/**
	 * 设置 弹出的内容框
	 * 
	 * @param view
	 *            pop依赖的view
	 * @param sortview
	 *            点击的view
	 * @param sortList
	 *            弹出内容的集合
	 */
	public static void setSortView(Activity context, final TextView sortview,
			final List<SortEntity> sortList) {

		for (int i = 0; i < sortList.size(); i++) {
			if (sortview.getText().toString()
					.equals(sortList.get(i).getTitle())) {
				sortList.get(i).setSelect(true);
			} else {
				sortList.get(i).setSelect(false);
			}
		}

		windowSort = new PopupWindowSort(context, sortList);
		windowSort.showAsDropDown(sortview, 0, 25);
		windowSort.setOnclick(new PopupWindowSort.OnClick() {
			@Override
			public void select(String title, int index) {
				sortview.setText(title);
				windowSort.dismiss();
				if (select != null)
					select.index(index);
			}

			@Override
			public void dissmiss() {
				sortview.setSelected(false);
			}
		});
	}

	public static void setSelect(Select select) {
		SortView.select = select;
	}

	public interface Select {
		void index(int index);
	}

	/**
	 * 设置 弹出的内容框
	 * 
	 * @param view
	 *            pop依赖的view
	 * @param sortview
	 *            点击的view
	 * @param sortList
	 *            弹出内容的集合
	 */
	public void setSortViews(Activity context, final TextView sortview,
			final List<SortEntity> sortList) {

		for (int i = 0; i < sortList.size(); i++) {
			if (sortview.getText().toString()
					.equals(sortList.get(i).getTitle())) {
				sortList.get(i).setSelect(true);
			} else {
				sortList.get(i).setSelect(false);
			}
		}

		windowSorts = new PopupWindowSort(context, sortList);
		windowSorts.showAsDropDown(sortview, 0, 25);
		windowSorts.setOnclick(new PopupWindowSort.OnClick() {
			@Override
			public void select(String title, int index) {
				sortview.setText(title);
				windowSorts.dismiss();
				if (selects != null)
					selects.index(index);
			}

			@Override
			public void dissmiss() {
				sortview.setSelected(false);
			}
		});
	}
	
	/**
	 * 设置 弹出的内容框
	 * 
	 * @param view
	 *            pop依赖的view
	 * @param sortview
	 *            点击的view
	 * @param sortList
	 *            弹出内容的集合
	 */
	public void setSortViews(Activity context, final TextView sortview,
			final List<SortEntity> sortList,int width,int height) {

		for (int i = 0; i < sortList.size(); i++) {
			if (sortview.getText().toString()
					.equals(sortList.get(i).getTitle())) {
				sortList.get(i).setSelect(true);
			} else {
				sortList.get(i).setSelect(false);
			}
		}

		windowSorts = new PopupWindowSort(context, sortList,width,height);
		windowSorts.showAsDropDown(sortview, 0, 5);
		windowSorts.setOnclick(new PopupWindowSort.OnClick() {
			@Override
			public void select(String title, int index) {
				sortview.setText(title);
				windowSorts.dismiss();
				if (selects != null)
					selects.index(index);
			}

			@Override
			public void dissmiss() {
				sortview.setSelected(false);
			}
		});
	}

	public void setSelects(Select select) {
		this.selects = select;
	}

}
