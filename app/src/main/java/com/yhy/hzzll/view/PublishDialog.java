package com.yhy.hzzll.view;

import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.yhy.hzzll.R;
import com.yhy.hzzll.entity.SortEntity;

/**
 * 快速发布 --dialog
 * 
 * @author Yang
 * 
 */
public class PublishDialog {

	private Activity activity;

	private AlertDialog alertDialog;

	private ListView lv_publish;

	private List<SortEntity> sortList;

	private TextView textview;

	public PublishDialog(Activity activity, List<SortEntity> sortList, TextView view) {
		// TODO Auto-generated constructor stub
		this.activity = activity;
		this.sortList = sortList;
		this.textview = view;
		showDialog();
	}

	public void showDialog() {
		alertDialog = new AlertDialog.Builder(activity).create();
		alertDialog.show();
		Window window = alertDialog.getWindow();
		View view = activity.getLayoutInflater().inflate(R.layout.dialog_publish, null);

		lv_publish = (ListView) view.findViewById(R.id.lv_publish);
		lv_publish.setAdapter(adapter);
		lv_publish.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
				sortList.get(position).setSelect(true);
				textview.setText(sortList.get(position).getTitle());
				textview.setTag(sortList.get(position).getId());
				dismiss();
			}
		});
		window.setContentView(view);
	}

	private BaseAdapter adapter = new BaseAdapter() {
		@Override
		public View getView(int position, View view, ViewGroup arg2) {
			view = LayoutInflater.from(activity).inflate(R.layout.item_popup_sort, null);
			TextView tv_content = (TextView) view.findViewById(R.id.tv_content);
			tv_content.setText(sortList.get(position).getTitle());
			return view;
		}

		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return arg0;
		}

		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return sortList.get(arg0);
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return sortList.size();
		}
	};

	public void dismiss() {
		if (alertDialog.isShowing())
			alertDialog.dismiss();
	}
}
