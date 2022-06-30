package com.yhy.hzzll.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.yhy.hzzll.R;
import com.yhy.hzzll.entity.CooperateInfoEntity;
import com.yhy.hzzll.entity.CooperateInfoEntity.collaboration.list;
import com.yhy.hzzll.framework.MyData;
import com.yhy.hzzll.utils.ImageLoaderUtils;

import java.util.List;

/**
 * 全国律师协作 ==详情 适配器
 * 
 * @author Yang
 * 
 */
public class LawyerCollaboraDetailsAdapter extends BaseAdapter {

	private Context context;

	private List<list> list;

	private int type;

	private OnClick click;

	public LawyerCollaboraDetailsAdapter(Context context,
			List<CooperateInfoEntity.collaboration.list> list, int type) {
		this.context = context;
		this.list = list;
		this.type = type;
	}

	public interface OnClick {
		void select(String lawyerid, String lawyerName);

		void connect(String lawyerid);
	}

	public void setOnclick(OnClick click) {
		this.click = click;

	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
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
	public View getView(final int position, View view, ViewGroup arg2) {
		// TODO Auto-generated method stub
		ViewHolder holder = null;
		if (view == null) {
			holder = new ViewHolder();
			view = LayoutInflater.from(context).inflate(
					R.layout.item_lawyer_collabor_details, null);
			holder.iv_head = (ImageView) view.findViewById(R.id.iv_head);
			holder.tv_name = (TextView) view.findViewById(R.id.tv_name);
			holder.iv_phone = (ImageView) view.findViewById(R.id.iv_phone);
			holder.iv_email = (ImageView) view.findViewById(R.id.iv_email);
			holder.iv_card = (ImageView) view.findViewById(R.id.iv_card);
			holder.tv_phoneno = (TextView) view.findViewById(R.id.tv_phoneno);
			holder.tv_address = (TextView) view.findViewById(R.id.tv_address);
			holder.tv_ls = (TextView) view.findViewById(R.id.tv_ls);
			holder.tv_connect = (TextView) view.findViewById(R.id.tv_connect);
			holder.iv_select = (ImageView) view.findViewById(R.id.iv_select);
			view.setTag(holder);
		} else {
			holder = (ViewHolder) view.getTag();
		}
		ImageLoaderUtils.initUtils().display(
				MyData.IP + list.get(position).getImgurl(), holder.iv_head);
		holder.tv_name.setText(list.get(position).getTruename() + "");
		if (!list.get(position).getVemail().equals(1)) {
			holder.iv_email.setVisibility(View.GONE);
		}
		if (!list.get(position).getVmobile().equals(1)) {
			holder.iv_phone.setVisibility(View.GONE);
		}
		if (!list.get(position).getVtruename().equals(1)) {
			holder.iv_card.setVisibility(View.GONE);
		}
		holder.tv_phoneno.setText(list.get(position).getMobile() + "");
		holder.tv_address.setText(list.get(position).getPlaces() + "");
		holder.tv_ls.setText(list.get(position).getLawfirm() + "");
		if (list.get(position).getStatus().equals("1")) {
			holder.iv_select.setVisibility(View.VISIBLE);
		} else {
			holder.iv_select.setVisibility(View.GONE);
		}

		if (type == 0) {
			holder.tv_connect.setText("选TA协作");
		} else {
			holder.tv_connect.setVisibility(View.GONE);
			holder.tv_connect.setText("联系TA");
		}

		holder.tv_connect.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				if (type == 0) {
					if (click != null)
						click.select(list.get(position).getUserid(),list.get(position).getTruename());
				} else {
					if (click != null)
						click.connect(list.get(position).getUserid());
				}
			}
		});
		return view;
	}

	private class ViewHolder {
		private ImageView iv_head;
		private TextView tv_name;
		private ImageView iv_phone;
		private ImageView iv_email;
		private ImageView iv_card;
		private TextView tv_phoneno;
		private TextView tv_address;
		private TextView tv_ls;
		private TextView tv_connect;
		private ImageView iv_select;
	}
}
