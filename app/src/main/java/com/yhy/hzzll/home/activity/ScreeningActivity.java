package com.yhy.hzzll.home.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.yhy.hzzll.R;
import com.yhy.hzzll.entity.AllStatusEntity;
import com.yhy.hzzll.entity.TypeEntity;
import com.yhy.hzzll.mian.activity.BaseActivity;
import com.yhy.hzzll.utils.MsgEvent;
import com.yhy.hzzll.utils.PrefsUtils;
import com.yhy.hzzll.view.AdressPopupWindow;
import com.yhy.hzzll.view.PopupWindowCooperatePrice;
import com.yhy.hzzll.view.PopupwindowcooperateType1;
import com.yhy.hzzll.view.PopupwindowcooperateType2;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;


/**
 * 律师协作筛选的activity
 * 
 * @author Yang
 * 
 */
public class ScreeningActivity extends BaseActivity {

	@ViewInject(R.id.tv_adress)
	private TextView tv_adress;

	@ViewInject(R.id.tv_sortmoney)
	private TextView tv_sortmoney;

	@ViewInject(R.id.tv_type1)
	private TextView tv_type1;

	@ViewInject(R.id.tv_type2)
	private TextView tv_type2;
	/** 省市区ID */
	public final static String SCREENPROVINCEID = "screenprovinceId";
	public final static String SCREENCITYID = "screencityId";
	public final static String SCREENAREAID = "screenareaId ";
	/** 省市区name */
	public final static String SCREENAREANAME = "screenareaname ";

	/** 酬金ID */
	public final static String SCREENPRICEID = "screenpriceid";
	/** 一级列表ID */
	public final static String SCREENTYPE1ID = "screentype1id";
	/** 二级列表ID */
	public final static String SCREENTYPE2ID = "screentype2id";

	/** 一级列表ID临时 */
	public static String Type1ID = "";

	/** 酬金name */
	public final static String SCREENPRICENAME = "screenpricename";
	/** 一级列表name */
	public final static String SCREENTYPE1NAME = "screentype1name";
	/** 二级列表name */
	public final static String SCREENTYPE2NAME = "screentype2name";

	/** 临时存省市区ID */
	private String screenprovinceId1 = "";
	private String screencityId1 = "";
	private String screenareaId1 = " ";
	/** 临时存省市区name */
	private String screenareaname1 = " ";

	/** 临时存酬金ID */
	private String screenpriceid1 = "";
	/** 临时存一级列表ID */
	private String screentype1id1 = "";
	/** 临时存二级列表ID */
	private String screentype2id1 = "";

	/** 临时存酬金name */
	private String screenpricename1 = "";
	/** 临时存一级列表name */
	private String screentype1name1 = "";
	/** 临时存二级列表name */
	private String screentype2name1 = "";

	/** 协作酬金区间列表 */
	private List<AllStatusEntity> publishPriceList = new ArrayList<AllStatusEntity>();
	/** 协作类型1级列表 */
	private List<TypeEntity> publishType1List = new ArrayList<TypeEntity>();
	/** 协作类型2级列表 */
	private List<TypeEntity> publishType2List = new ArrayList<TypeEntity>();

	private AdressPopupWindow popupWindow;
	private ArrayList<String> arrProvinces = new ArrayList<String>();

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		setContentView(R.layout.popupwindow_screening);
		super.onCreate(arg0);
		ViewUtils.inject(this);
		EventBus.getDefault().register(this);
		viewInit();
	}

	private void viewInit() {
		screenareaname1 = PrefsUtils.getString(context, SCREENAREANAME);
		screenpricename1 = PrefsUtils.getString(context, SCREENPRICENAME);
		screentype1name1 = PrefsUtils.getString(context, SCREENTYPE1NAME);
		screentype2name1 = PrefsUtils.getString(context, SCREENTYPE2NAME);
		screenprovinceId1 = PrefsUtils.getString(context, SCREENPROVINCEID);
		screencityId1 = PrefsUtils.getString(context, SCREENCITYID);
		screenareaId1 = PrefsUtils.getString(context, SCREENAREAID);
		screenpriceid1 = PrefsUtils.getString(context, SCREENPRICEID);
		screentype1id1 = PrefsUtils.getString(context, SCREENTYPE1ID);
		screentype2id1 = PrefsUtils.getString(context, SCREENTYPE2ID);
		Type1ID = screentype1id1;

		if (PrefsUtils.getString(context, SCREENAREANAME).isEmpty()
				|| PrefsUtils.getString(context, SCREENAREANAME).equals("")) {
			tv_adress.setText("全国");
		} else {
			tv_adress.setText(PrefsUtils.getString(context, SCREENAREANAME));
		}
		if (PrefsUtils.getString(context, SCREENPRICENAME).isEmpty()) {
			tv_sortmoney.setText("全部");
		} else {
			tv_sortmoney
					.setText(PrefsUtils.getString(context, SCREENPRICENAME));
		}
		if (PrefsUtils.getString(context, SCREENTYPE1NAME).isEmpty()) {
			tv_type1.setText("全部");
		} else {
			tv_type1.setText(PrefsUtils.getString(context, SCREENTYPE1NAME));
		}
		if (PrefsUtils.getString(context, SCREENTYPE2NAME).isEmpty()) {
			tv_type2.setText("全部");
		} else {
			tv_type2.setText(PrefsUtils.getString(context, SCREENTYPE2NAME));
		}
	}

	@OnClick({ R.id.tv_adress, R.id.tv_sortmoney, R.id.tv_type1, R.id.tv_type2,
			R.id.tv_enter, R.id.ll_content_layout, R.id.ll_layout,R.id.tv_re_enter})
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.tv_adress:// 选择地区
			arrProvinces.add("全国");
			popupWindow = new AdressPopupWindow(this, arrProvinces,true);
			popupWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);
			popupWindow
					.setAddresskListener(new AdressPopupWindow.OnAddressCListener() {
						@Override
						public void onClick(String province, String city,
								String area, int provinceid, int cityid,
								int areaid) {
							if (province.equals("全国")) {
								tv_adress.setText("全国");
								screenprovinceId1 = "";
								screencityId1 = "";
								screenareaId1 = "";
								screenareaname1 = "";
							} else {
								tv_adress.setText(province + "-" + city + "-"
										+ area);
								screenprovinceId1 = provinceid + "";
								screencityId1 = cityid + "";
								screenareaId1 = areaid + "";
								screenareaname1 = province + "-" + city + "-"
										+ area;
							}
							Log.e("SCREENPROVINCEID", screenprovinceId1);
							Log.e("SCREENCITYID", screencityId1);
							Log.e("SCREENAREAID", screenareaId1);
							Log.e("SCREENAREANAME", screenareaname1);
							popupWindow.dismiss();
						}
					});
			break;
		case R.id.ll_content_layout:// 有内容的区域
			break;
		case R.id.ll_layout:// 没有内容的区域
			if (popupWindow != null && popupWindow.isShowing()) {
				popupWindow.dismiss();
			} else {
				finish();
			}
			break;
		case R.id.tv_sortmoney:// 选择酬金
			publishPriceList.clear();
			PopupWindowCooperatePrice lawyerJobPopupWindow = new PopupWindowCooperatePrice(
					this, publishPriceList);
			lawyerJobPopupWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);
			lawyerJobPopupWindow
					.setAddresskListener(new PopupWindowCooperatePrice.OnAddressCListener() {
						@Override
						public void onClick(String job) {
							tv_sortmoney.setText(job);
							if (job.equals("全部")) {
								screenpriceid1 = "";
								screenpricename1 = "";
							} else {
								for (int i = 0; i < publishPriceList.size(); i++) {
									if (publishPriceList.get(i)
											.getStatus_name().equals(job)) {
										screenpriceid1 = publishPriceList
												.get(i).getStatus_value();
										screenpricename1 = publishPriceList
												.get(i).getStatus_name();
									}
								}
							}
							Log.e("SCREENPRICEID", screenpriceid1);
							Log.e("SCREENPRICENAME", screenpricename1);
						}
					});
			break;
		case R.id.tv_type1:// 选择类型1
			publishType1List.clear();
			TypeEntity entity = new TypeEntity();
			entity.setId("99999");
			entity.setName("全部");
			entity.setType("coop_type");
			publishType1List.add(entity);
			PopupwindowcooperateType1 type1 = new PopupwindowcooperateType1(
					this, publishType1List);
			type1.showAtLocation(view, Gravity.BOTTOM, 0, 0);
			type1.setAddresskListener(new PopupwindowcooperateType1.OnAddressCListener() {
				@Override
				public void onClick(String job) {
					tv_type1.setText(job);
					if (!job.equals(PrefsUtils.getString(context,
							SCREENTYPE1NAME))) {
						tv_type2.setText("全部");
						screentype2id1 = "";
						screentype2name1 = "";
					}

					if (job.equals("全部")) {
						screentype1id1 = "";
						screentype1name1 = "";
					} else {
						for (int i = 0; i < publishType1List.size(); i++) {
							if (publishType1List.get(i).getName().equals(job)) {
								screentype1id1 = publishType1List.get(i)
										.getId();
								screentype1name1 = publishType1List.get(i)
										.getName();
							}
						}
					}

					Type1ID = screentype1id1;
					Log.e("SCREENTYPE1ID", Type1ID);

					Log.e("SCREENTYPE1NAME", screentype1name1);
				}
			});
			break;
		case R.id.tv_type2:// 选择类型2
			publishType2List.clear();
			PopupwindowcooperateType2 type2 = new PopupwindowcooperateType2(
					this, publishType2List);
			type2.showAtLocation(view, Gravity.BOTTOM, 0, 0);
			type2.setAddresskListener(new PopupwindowcooperateType2.OnAddressCListener() {
				@Override
				public void onClick(String job) {
					tv_type2.setText(job);
					if (job.equals("全部")) {
						screentype2id1 = "";
						screentype2name1 = "";
					} else {
						for (int i = 0; i < publishType2List.size(); i++) {
							if (publishType2List.get(i).getName().equals(job)) {
								screentype2id1 = publishType2List.get(i)
										.getId();
								screentype2name1 = publishType2List.get(i)
										.getName();
							}
						}
					}
					Log.e("SCREENTYPE2ID", screentype2id1);
					Log.e("SCREENTYPE2NAME", screentype2name1);
				}
			});
			break;
		case R.id.tv_enter:// 确定
			PrefsUtils.saveString(context, ScreeningActivity.SCREENPROVINCEID,
					screenprovinceId1);
			PrefsUtils.saveString(context, ScreeningActivity.SCREENCITYID,
					screencityId1);
			PrefsUtils.saveString(context, ScreeningActivity.SCREENAREAID,
					screenareaId1);
			PrefsUtils.saveString(context, ScreeningActivity.SCREENAREANAME,
					screenareaname1);
			PrefsUtils.saveString(context, ScreeningActivity.SCREENPRICENAME,
					screenpricename1);
			PrefsUtils.saveString(context, ScreeningActivity.SCREENPRICEID,
					screenpriceid1);
			PrefsUtils.saveString(context, ScreeningActivity.SCREENTYPE1NAME,
					screentype1name1);
			PrefsUtils.saveString(context, ScreeningActivity.SCREENTYPE1ID,
					screentype1id1);
			PrefsUtils.saveString(context, ScreeningActivity.SCREENTYPE2NAME,
					screentype2name1);
			PrefsUtils.saveString(context, ScreeningActivity.SCREENTYPE2ID,
					screentype2id1);
			setResult(1901);
			finish();
			break;
		case R.id.tv_re_enter:
			tv_adress.setText("全部");
			tv_sortmoney.setText("全部");
			tv_type1.setText("全部");
			tv_type2.setText("全部");
			break;
		}
	}

	/**
	 * @param msg
	 *            事件
	 */
	public void onEventMainThread(MsgEvent msg) {
		switch (msg.getIntMsg()) {
		case 1992:
			finish();
			break;
		}
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		// 界面销毁时，取消订阅
		EventBus.getDefault().unregister(this);
	}

}
