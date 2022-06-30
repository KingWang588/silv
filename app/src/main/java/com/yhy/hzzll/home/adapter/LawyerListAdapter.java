package com.yhy.hzzll.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.makeramen.roundedimageview.RoundedImageView;
import com.yhy.hzzll.R;
import com.yhy.hzzll.entity.LawyerNationalEntity;
import com.yhy.hzzll.utils.Tools;

import java.util.List;

import static com.yhy.hzzll.R.id.tv_profession;

/**
 * 全国律师 - 列表 适配器
 * 
 * @author Yang
 * 
 */
public class LawyerListAdapter extends BaseAdapter {

	private Context context;
	private List<LawyerNationalEntity.DataBean.ListBean> list;

	public LawyerListAdapter(Context context, List<LawyerNationalEntity.DataBean.ListBean> list) {
		this.context = context;
		// TODO Auto-generated constructor stub
		this.list = list;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list != null ? list.size() : 0;
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
			view = LayoutInflater.from(context).inflate(R.layout.item_lawyer_list, null);
			holder.iv_img = (RoundedImageView) view.findViewById(R.id.iv_img);
			holder.iv_tag = (ImageView) view.findViewById(R.id.iv_tag);
			holder.tv_name = (TextView) view.findViewById(R.id.tv_name);
			holder.tv_profession = (TextView) view.findViewById(tv_profession);
			holder.tv_profess = (TextView) view.findViewById(R.id.tv_profess);
			holder.tv_address = (TextView) view.findViewById(R.id.tv_address);
			holder.rb_star = (RatingBar) view.findViewById(R.id.rb_star);
			holder.rb_star2 = (RatingBar) view.findViewById(R.id.rb_star2);
			holder.tv_help_num = (TextView) view.findViewById(R.id.tv_help_num);
			holder.tv_case_num = (TextView) view.findViewById(R.id.tv_case_num);
			holder.tv_special = (TextView) view.findViewById(R.id.tv_special);

			view.setTag(holder);
		} else {
			holder = (ViewHolder) view.getTag();
		}
		LawyerNationalEntity.DataBean.ListBean e = list.get(position);
		holder.tv_name.setText(e.getTruename());
		holder.tv_profession.setText(e.getLawyer_title());

//		if (e.getLawyer_title().equals("13")){
//			holder.tv_profession.setText("主任");
//		}else if (e.getLawyer_title().equals("14")){
//			holder.tv_profession.setText("副主任");
//		}else if (e.getLawyer_title().equals("15")){
//			holder.tv_profession.setText("合伙人");
//		}else{
//			holder.tv_profession.setText("律师");
//		}

		Glide.with(context).load(e.getHeadimg()).into(holder.iv_img);
		holder.tv_address.setText(e.getBase_region_id().getSimple_name());
		String special = "";
		for (int i = 0; i < e.getSpecial().size(); i++) {
			special = special+e.getSpecial().get(i).toString()+" ";
		}
		holder.tv_special.setText(special);

		if(e.getLawyer_type().equals("11")){
			holder.iv_tag.setImageResource(R.drawable.consult_vip_practice);
		}else{
			holder.iv_tag.setImageResource(R.drawable.consult_vip);
		}


		holder.tv_help_num.setText(context.getString(R.string.label_help_number_txt, e.getReply_consult_num()+""));
		holder.tv_case_num.setText(context.getString(R.string.label_case_number_txt, e.getHandle_case_num()+""));
		holder.rb_star.setRating(Tools.stringToFloat(e.getLegal_advice_rate())/2);
//		holder.rb_star2.setRating(Tools.stringToFloat(e.getHandle_case_rate()));

		return view;
	}

	private class ViewHolder {
		private ImageView iv_img;
		private ImageView iv_tag;
		private TextView tv_name;
		private TextView tv_profession;
		private TextView tv_profess;
		private TextView tv_address;
		private RatingBar rb_star;
		private RatingBar rb_star2;

		private TextView tv_help_num;
		private TextView tv_case_num;
		private TextView tv_special;


	}

}
