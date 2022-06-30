package com.yhy.hzzll.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.umeng.socialize.utils.Log;
import com.wpd.address.wheel.widget.views.AbstractWheelTextAdapter;
import com.wpd.address.wheel.widget.views.OnWheelChangedListener;
import com.wpd.address.wheel.widget.views.OnWheelScrollListener;
import com.wpd.address.wheel.widget.views.WheelView;
import com.yhy.hzzll.R;
import com.yhy.hzzll.entity.AreaEntity;
import com.yhy.hzzll.entity.CityEntity;
import com.yhy.hzzll.entity.ProvincesEntity;
import com.yhy.hzzll.framework.MyData;
import com.yhy.hzzll.utils.HttpDataUtils;
import com.yhy.hzzll.utils.PrefsUtils;

public class AdressPopupWindow extends PopupWindow implements OnClickListener, OnWheelChangedListener {
	private View conentView;
	// 省市区控件
	private WheelView wvProvince;
	private WheelView wvCitys;
	private WheelView wvArea;

	private TextView btnSure;// 确定按钮

	private TextView btn_myinfo_cancel;// 取消按钮

	private JSONObject mJsonObj;// 存放地址信息的json对象

	private String[] mProvinceDatas;
	private Map<String, String[]> mCitisDatasMap = new HashMap<String, String[]>();
	private Map<String, String[]> mAreaDatasMap = new HashMap<String, String[]>();

	private ArrayList<String> arrCitys = new ArrayList<String>();
	private ArrayList<String> arrAreas = new ArrayList<String>();

	private int provincesid;
	private int cityid;
	private int areaid;

	/** 省市区 */
	private List<ProvincesEntity> provincesList;
	private List<CityEntity> cityList;
	private List<AreaEntity> areaList;

	private AddressTextAdapter provinceAdapter;
	private AddressTextAdapter cityAdapter;
	private AddressTextAdapter areaAdapter;

	// 选中的省市区信息
	private String strProvince = "";
	private String strCity = "";
	private String strArea = "";

	// 回调方法
	private OnAddressCListener onAddressCListener;

	// 显示文字的字体大小
	private int maxsize = 24;
	private int minsize = 14;

	private Activity context;
	private HttpDataUtils utils;
	private ArrayList<String> arrProvinces;
	private boolean isLimit;

	public AdressPopupWindow(final Activity context, ArrayList<String> arrProvinces ,boolean isLimit ) {
		this.arrProvinces = arrProvinces;
		this.isLimit = isLimit;
		provincesList = new ArrayList<ProvincesEntity>();
		cityList = new ArrayList<CityEntity>();
		areaList = new ArrayList<AreaEntity>();
		this.context = context;
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		conentView = inflater.inflate(R.layout.dialog_myinfo_changeaddress, null);
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
		// ColorDrawable dw = new ColorDrawable(0000000000);
		// 点back键和其他地方使其消失,设置了这个才能触发OnDismisslistener ，设置其他控件变化等操作
		// this.setBackgroundDrawable(dw);
		// mPopupWindow.setAnimationStyle(android.R.style.Animation_Dialog);
		// 设置SelectPicPopupWindow弹出窗体动画效果
		this.setAnimationStyle(R.style.AnimationPreview);

		// // 设置背景颜色变暗
		// WindowManager.LayoutParams lp = context.getWindow().getAttributes();
		// lp.alpha = 0.9f;
		// context.getWindow().setAttributes(lp);
		this.setOnDismissListener(new OnDismissListener() {
			@Override
			public void onDismiss() {
				// WindowManager.LayoutParams lp = context.getWindow()
				// .getAttributes();
				// lp.alpha = 1f;
				// context.getWindow().setAttributes(lp);
				// EventBus.getDefault().post(new MsgEvent(1992));
			}
		});

		wvProvince = (WheelView) conentView.findViewById(R.id.wv_address_province);
		wvCitys = (WheelView) conentView.findViewById(R.id.wv_address_city);
		wvArea = (WheelView) conentView.findViewById(R.id.wv_address_area);
		btnSure = (TextView) conentView.findViewById(R.id.btn_myinfo_sure);
		btn_myinfo_cancel = (TextView) conentView.findViewById(R.id.btn_myinfo_cancel);

		btnSure.setOnClickListener(this);
		btn_myinfo_cancel.setOnClickListener(this);
		wvProvince.addChangingListener(this);
		wvCitys.addChangingListener(this);
		wvArea.addChangingListener(this);

		initJsonData();
		setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
	}

	/**
	 * 初始化省会
	 */
	public void initProvinces() {
		int length = mProvinceDatas.length;
		for (int i = 0; i < length; i++) {
			arrProvinces.add(mProvinceDatas[i]);
		}
	}

	/**
	 * 根据省会，生成该省会的所有城市
	 * 
	 * @param citys
	 */
	public void initCitys(String[] citys) {
		if (citys != null) {
			arrCitys.clear();
			if(isLimit){
				arrCitys.add("不限");
			}
			int length = citys.length;
			for (int i = 0; i < length; i++) {
				arrCitys.add(citys[i]);
			}
		} else {
			String[] city = mCitisDatasMap.get("北京市");
			arrCitys.clear();
			if(isLimit){
				arrCitys.add("不限");
			}
			int length = city.length;
			for (int i = 0; i < length; i++) {
				arrCitys.add(city[i]);
			}
		}
		if (arrCitys != null && arrCitys.size() > 0 && !arrCitys.contains(strCity)) {
			strCity = arrCitys.get(0);
		}
	}

	/**
	 * 根据城市，生成该城市的所有地区
	 * 
	 * @param citys
	 */
	public void initAreas(String[] areas) {
		if (areas != null) {
			arrAreas.clear();
			if(isLimit){
				 arrAreas.add("不限");
			}
			
			int length = areas.length;
			for (int i = 0; i < length; i++) {
				arrAreas.add(areas[i]);
			}
		} else {
			String[] city = mCitisDatasMap.get("北京市");
			arrCitys.clear();
			if(isLimit){
				arrCitys.add("不限");
			}			 
			int length = city.length;
			for (int i = 0; i < length; i++) {
				arrCitys.add(city[i]);
			}
		}
		if (arrAreas != null && arrAreas.size() > 0 && !arrAreas.contains(strArea)) {
			strArea = arrAreas.get(0);
		}
	}

	/**
	 * 返回省会索引
	 */
	public int getProvinceItem(String province) {
		int size = arrProvinces.size();
		int provinceIndex = 0;
		boolean noprovince = true;
		for (int i = 0; i < size; i++) {
			if (province.equals(arrProvinces.get(i))) {
				noprovince = false;
				return provinceIndex;
			} else {
				provinceIndex++;
			}
		}
		if (noprovince) {
			strProvince = "北京市";
			return 12;
		}
		return provinceIndex;
	}

	/**
	 * 得到城市索引
	 */
	public int getCityItem(String city) {
		int size = arrCitys.size();
		int cityIndex = 0;
		// boolean nocity = true;
		for (int i = 0; i < size; i++) {
			if (city.equals(arrCitys.get(i))) {
				// nocity = false;
				return cityIndex;
			} else {
				cityIndex++;
			}
		}
		// if (nocity) {
		// strCity = "广州市";
		// return 0;
		// }
		return cityIndex;
	}

	// 得到地区
	public int getAreaItem(String area) {
		int size = arrAreas.size();
		int cityIndex = 0;
		// boolean nocity1 = true;
		for (int i = 0; i < size; i++) {
			if (area.equals(arrAreas.get(i))) {
				// nocity1 = false;
				return cityIndex;
			} else {
				cityIndex++;
			}
		}
		// if (nocity1) {
		// strArea = "你玩去";
		// return 0;
		// }
		return cityIndex;
	}

	// 根据省来更新wheel的状态
	private void updateCities() {
		String currentText = (String) provinceAdapter.getItemText(wvProvince.getCurrentItem());
		strProvince = currentText;
		setTextviewSize(currentText, provinceAdapter);
		String[] citys = mCitisDatasMap.get(currentText);
		if (citys == null) {
			citys = new String[] { "" };
		}
		initCitys(citys);
		cityAdapter = new AddressTextAdapter(context, arrCitys, 0, maxsize, minsize);
		wvCitys.setViewAdapter(cityAdapter);
		wvCitys.setCurrentItem(0);
		updateAreas();
	}

	// 根据城市来更新wheel的状态
	private void updateAreas() {
		String currentText = (String) cityAdapter.getItemText(wvCitys.getCurrentItem());
		strCity = currentText;
		setTextviewSize(currentText, cityAdapter);
		String[] areas = mAreaDatasMap.get(currentText);
	
		if (areas == null) {
			areas = new String[] { "" };
		}
		List<String> areaslist = new ArrayList<>();
		if(isLimit){
			areaslist.add("不限");
		}
		
		for(int i= 0;i<areas.length;i++){
			areaslist.add(areas[i]);
		}
		strArea = areaslist.get(0);
		Log.e("123544+6+"+strArea);
		initAreas(areas);
		areaAdapter = new AddressTextAdapter(context, arrAreas, 0, maxsize, minsize);
		wvArea.setViewAdapter(areaAdapter);
		wvArea.setCurrentItem(0);
	}

	@Override
	public void onChanged(WheelView wheel, int oldValue, int newValue) {
		if (wheel == wvProvince) {
			// 切换省份的操作
			updateCities();
		} else if (wheel == wvCitys) {
			updateAreas();
		} else if (wheel == wvArea) {
			String currentText = (String) areaAdapter.getItemText(wheel.getCurrentItem());
			strArea = currentText;
			Log.e(strArea);
//				strArea = mAreaDatasMap.get(strCity)[newValue];
				strArea = arrAreas.get(newValue);
				setTextviewSize(currentText, areaAdapter);
			
		}
	}

	// //////////////////////////////////////////////////华丽的分界线
	private void initJsonData() {
		if (PrefsUtils.getString(context, PrefsUtils.ALLAREA).isEmpty()) {
			RequestParams params = new RequestParams();
			utils = new HttpDataUtils(context);
			utils.sendGet(MyData.GETALLAREA, params);
			utils.setSucessBack(new HttpDataUtils.SucessBack() {
				@Override
				public void sucess(ResponseInfo<String> arg0) {
					if (utils.code(arg0.result)) {
						try {
							PrefsUtils.saveString(context, PrefsUtils.ALLAREA, arg0.result);
							mJsonObj = new JSONObject(arg0.result);
							initDatas();
						} catch (JSONException e) {
							e.printStackTrace();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			});
		} else {
			try {
				mJsonObj = new JSONObject(PrefsUtils.getString(context, PrefsUtils.ALLAREA));
				initDatas();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void initDatas() {
		try {
			JSONArray jsonArray = mJsonObj.getJSONArray("data");
			mProvinceDatas = new String[jsonArray.length()];
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonP = jsonArray.getJSONObject(i);// 每个省的json对象
				String province = jsonP.getString("name");// 省名字
				ProvincesEntity entity = new ProvincesEntity();
				entity.setId(jsonP.optString("id"));
				entity.setName(jsonP.optString("name"));
				provincesList.add(entity);
				mProvinceDatas[i] = province;
				JSONArray jsonCs = null;
				try {
					/**
					 * Throws JSONException if the mapping doesn't exist or is
					 * not a JSONArray.
					 */
					jsonCs = jsonP.getJSONArray("city");
				} catch (Exception e1) {
					continue;
				}
				String[] mCitiesDatas = new String[jsonCs.length()];
				for (int j = 0; j < jsonCs.length(); j++) {
					JSONObject jsonCity = jsonCs.getJSONObject(j);
					String city = jsonCity.getString("name");// 市名字
					CityEntity cityEntity = new CityEntity();
					cityEntity.setId(jsonCity.getString("id"));
					cityEntity.setName(jsonCity.getString("name"));
					cityList.add(cityEntity);
					mCitiesDatas[j] = city;
					JSONArray jsonAreas = null;
					try {
						/**
						 * Throws JSONException if the mapping doesn't exist or
						 * is not a JSONArray.
						 */
						jsonAreas = jsonCity.getJSONArray("area");
					} catch (Exception e) {
						continue;
					}
					String[] mAreasDatas = new String[jsonAreas.length()];// 当前市的所有区
					for (int k = 0; k < jsonAreas.length(); k++) {
						String area = jsonAreas.getJSONObject(k).getString("name");// 区域的名称
						AreaEntity areaEntity = new AreaEntity();
						areaEntity.setId(jsonAreas.getJSONObject(k).getString("id"));
						areaEntity.setName(jsonAreas.getJSONObject(k).getString("name"));
						areaList.add(areaEntity);
						mAreasDatas[k] = area;
					}
					mAreaDatasMap.put(city, mAreasDatas);
				}
				mCitisDatasMap.put(province, mCitiesDatas);
			}

			initProvinces();
			provinceAdapter = new AddressTextAdapter(context, arrProvinces, getProvinceItem(strProvince), maxsize,
					minsize);
			wvProvince.setVisibleItems(4);
			wvProvince.setViewAdapter(provinceAdapter);
			// wvProvince.setCyclic(true);//设置内容循环
			wvProvince.setCurrentItem(getProvinceItem(strProvince));

			initCitys(mCitisDatasMap.get(strProvince));
			cityAdapter = new AddressTextAdapter(context, arrCitys, getCityItem(strCity), maxsize, minsize);
			wvProvince.setVisibleItems(4);
			wvCitys.setViewAdapter(cityAdapter);
			wvCitys.setCurrentItem(getCityItem(strCity));

			initAreas(mAreaDatasMap.get(strCity));
			areaAdapter = new AddressTextAdapter(context, arrAreas, getCityItem(strArea), maxsize, minsize);
			wvProvince.setVisibleItems(4);
			wvArea.setViewAdapter(areaAdapter);
			wvArea.setCurrentItem(getAreaItem(strArea));
			wvProvince.addScrollingListener(new OnWheelScrollListener() {
				@Override
				public void onScrollingStarted(WheelView wheel) {
				}

				@Override
				public void onScrollingFinished(WheelView wheel) {
					String currentText = (String) provinceAdapter.getItemText(wheel.getCurrentItem());
					setTextviewSize(currentText, provinceAdapter);
					strProvince = currentText;
				}
			});
			wvCitys.addScrollingListener(new OnWheelScrollListener() {
				@Override
				public void onScrollingStarted(WheelView wheel) {
				}

				@Override
				public void onScrollingFinished(WheelView wheel) {
					// TODO Auto-generated method stub
					String currentText = (String) cityAdapter.getItemText(wheel.getCurrentItem());
					setTextviewSize(currentText, cityAdapter);
					strCity = currentText;
				}
			});
			wvArea.addScrollingListener(new OnWheelScrollListener() {
				@Override
				public void onScrollingStarted(WheelView wheel) {
				}

				@Override
				public void onScrollingFinished(WheelView wheel) {
					String currentText = (String) areaAdapter.getItemText(wheel.getCurrentItem());
					setTextviewSize(currentText, areaAdapter);
					strArea = currentText;
				}
			});

		} catch (JSONException e) {
			e.printStackTrace();
		}
		mJsonObj = null;
	}

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

	public interface OnAddressCListener {
		void onClick(String province, String city, String area, int provincesid, int cityid, int areaid);
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

	public void setAddresskListener(OnAddressCListener onAddressCListener) {
		this.onAddressCListener = onAddressCListener;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_myinfo_sure:
			for (int i = 0; i < provincesList.size(); i++) {
				if (provincesList.get(i).getName().equals(strProvince)) {
					provincesid = Integer.valueOf(provincesList.get(i).getId());
				}
			}

			for (int i = 0; i < cityList.size(); i++) {
				if (cityList.get(i).getName().equals(strCity)) {
					cityid = Integer.valueOf(cityList.get(i).getId());
				}
			}

			for (int i = 0; i < areaList.size(); i++) {
				if (areaList.get(i).getName().equals(strArea)) {
					areaid = Integer.valueOf(areaList.get(i).getId());
				}
			}

			if (onAddressCListener != null) {
				onAddressCListener.onClick(strProvince, strCity, strArea, provincesid, cityid, areaid);
				Log.e("+6+6+6", strProvince + strCity + strArea + provincesid + cityid + areaid);
			}
			break;
		case R.id.btn_myinfo_cancel:
			dismiss();
			break;
		}
	}
}