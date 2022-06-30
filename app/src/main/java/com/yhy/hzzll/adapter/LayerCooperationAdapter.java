package com.yhy.hzzll.adapter;

import java.util.List;

import com.yhy.hzzll.R;
import com.yhy.hzzll.entity.LawyerCoopEntity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * 首页--全国律师协作
 * 
 * @author Yang
 * 
 */
public class LayerCooperationAdapter extends BaseAdapter {

	private Context context;
	private List<LawyerCoopEntity.DataBean.ListBean> list;

	public LayerCooperationAdapter(Context context,
			List<LawyerCoopEntity.DataBean.ListBean> list) {
		this.context = context;
		this.list = list;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list == null ? 0 : list.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return list.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int position, View view, ViewGroup arg2) {
		ViewHolder holder = null;
		if (view == null) {
			holder = new ViewHolder();
			view = LayoutInflater.from(context).inflate(R.layout.item_layer_zp,
					null);
			holder.tv_title = (TextView) view.findViewById(R.id.tv_title);
			holder.tv_tag = (TextView) view.findViewById(R.id.tv_tag);
			holder.tv_status = (TextView) view.findViewById(R.id.tv_status);
			holder.tv_people = (TextView) view.findViewById(R.id.tv_people);
			holder.tv_date = (TextView) view.findViewById(R.id.tv_date);
			holder.tv_type = (TextView) view.findViewById(R.id.tv_type);
			holder.tv_tgmoney = (TextView) view.findViewById(R.id.tv_tgmoney);
			holder.tv_add = (TextView) view.findViewById(R.id.tv_add);
			holder.tv_paymoney = (TextView) view.findViewById(R.id.tv_paymoney);
			holder.ll_prize = (LinearLayout) view.findViewById(R.id.ll_prize);
			view.setTag(holder);
		} else {
			holder = (ViewHolder) view.getTag();
		}

		if (list.get(position).getTitle()!=null){
			holder.tv_title.setText(list.get(position).getTitle());
		}else {
			holder.tv_title.setText(list.get(position).getType());
		}


//		if (list.get(position).getIs_pay().equals("1")) {
//			holder.tv_tag.setText("已托管");
//			holder.tv_tag.setTextColor(context.getResources().getColor(
//					R.color.textbule));
//			holder.ll_prize.setVisibility(View.VISIBLE);
//		} else {
//			holder.tv_tag.setText("未托管");
//			holder.tv_tag.setTextColor(context.getResources().getColor(
//					R.color.red));
//			holder.ll_prize.setVisibility(View.GONE);
//		}
		holder.tv_status.setText(list.get(position).getStatus() + "");
		holder.tv_people.setText("(" + list.get(position).getNumber()
				+ "人申请)");
		holder.tv_date.setText(list.get(position).getCreate_time());
		holder.tv_type.setText(list.get(position).getType());
//		holder.tv_tgmoney.setText(list.get(position).getAmount());
		holder.tv_add.setText(list.get(position).getBase_region_id().getWhole_name());
		holder.tv_paymoney.setText(list.get(position).getAmount());

		return view;
	}

	private class ViewHolder {
		private TextView tv_title;
		private TextView tv_tag;
		private TextView tv_status;
		private TextView tv_people;
		private TextView tv_date;
		private TextView tv_type;
		private TextView tv_tgmoney;
		private TextView tv_add;
		private TextView tv_paymoney;
		private LinearLayout ll_prize;

	}

}
