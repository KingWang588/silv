package com.yhy.hzzll.mian.view;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.wpd.address.wheel.widget.views.AbstractWheelTextAdapter;
import com.wpd.address.wheel.widget.views.OnWheelChangedListener;
import com.wpd.address.wheel.widget.views.OnWheelScrollListener;
import com.wpd.address.wheel.widget.views.WheelView;
import com.yhy.hzzll.R;
import com.yhy.hzzll.mian.entity.AddressEntity;
import com.yhy.hzzll.framework.MyData;
import com.yhy.hzzll.utils.HttpDataUtils;
import com.yhy.hzzll.utils.JSONTool;
import com.yhy.hzzll.utils.LogUtils;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdressSelect extends PopupWindow implements View.OnClickListener, OnWheelChangedListener {
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

    private String provincesid;
    private String cityid;
    private String areaid;

    /**
     * 省市区
     */
//    private List<ProvincesEntity> provincesList;
//    private List<CityEntity> cityList;
//    private List<AreaEntity> areaList;

    private List<AddressEntity.AddressBean> provincesList;
    private List<AddressEntity.AddressBean> cityList;
    private List<AddressEntity.AddressBean> areaList;

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
    private Gson gson;

    public AdressSelect(final Activity context,ArrayList<String>  arrProvinces,  boolean isLimit) {
        this.arrProvinces = arrProvinces;
        this.isLimit = isLimit;
        provincesList = new ArrayList<>();
        cityList = new ArrayList<>();
        areaList = new ArrayList<>();
        this.context = context;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        conentView = inflater.inflate(R.layout.dialog_myinfo_changeaddress, null);
        int width = context.getResources().getDisplayMetrics().widthPixels;
        this.setContentView(conentView);
        this.setWidth(width);
        this.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        this.update();
        this.setAnimationStyle(R.style.AnimationPreview);

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

        inteProvinces();
        setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
    }


//    /**
//     * 初始化省会
//     */
//    public void initProvinces() {
//        int length = mProvinceDatas.length;
//        for (int i = 0; i < length; i++) {
//            arrProvinces.add(mProvinceDatas[i]);
//        }
//    }
//
//    /**
//     * 根据省会，生成该省会的所有城市
//     *
//     * @param citys
//     */
//    public void initCitys(String[] citys) {
//        if (citys != null) {
//            arrCitys.clear();
//            if (isLimit) {
//                arrCitys.add("不限");
//            }
//            int length = citys.length;
//            for (int i = 0; i < length; i++) {
//                arrCitys.add(citys[i]);
//            }
//        } else {
//            String[] city = mCitisDatasMap.get("北京市");
//            arrCitys.clear();
//            if (isLimit) {
//                arrCitys.add("不限");
//            }
//            int length = city.length;
//            for (int i = 0; i < length; i++) {
//                arrCitys.add(city[i]);
//            }
//        }
//        if (arrCitys != null && arrCitys.size() > 0 && !arrCitys.contains(strCity)) {
//            strCity = arrCitys.get(0);
//        }
//    }
//
//    /**
//     * 根据城市，生成该城市的所有地区
//     *
//     * @param
//     */
//    public void initAreas(String[] areas) {
//        if (areas != null) {
//            arrAreas.clear();
//            if (isLimit) {
//                arrAreas.add("不限");
//            }
//
//            int length = areas.length;
//            for (int i = 0; i < length; i++) {
//                arrAreas.add(areas[i]);
//            }
//        } else {
//            String[] city = mCitisDatasMap.get("北京市");
//            arrCitys.clear();
//            if (isLimit) {
//                arrCitys.add("不限");
//            }
//            int length = city.length;
//            for (int i = 0; i < length; i++) {
//                arrCitys.add(city[i]);
//            }
//        }
//        if (arrAreas != null && arrAreas.size() > 0 && !arrAreas.contains(strArea)) {
//            strArea = arrAreas.get(0);
//        }
//    }
//
//    /**
//     * 返回省会索引
//     */
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

        return provinceIndex;
    }


    /**
     * 得到城市
     * */
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

        return cityIndex;
    }


//     得到地区
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

        return cityIndex;
    }

    // 根据省来更新wheel的状态
    private void updateCities(String id) {

        RequestParams params = new RequestParams();
        utils = new HttpDataUtils(context);
        utils.sendGet(MyData.GETADDRESS + "/" + id, params);
        utils.setSucessBack(new HttpDataUtils.SucessBack() {
            @Override
            public void sucess(ResponseInfo<String> arg0) {
                try {
                    JSONObject object = new JSONObject(arg0.result);
                    String code = JSONTool.getString(object, "code");
                    String msg = JSONTool.getString(object, "msg");
                    if (code.equals("0")) {
                        gson = new Gson();
                        cityList.clear();
                        AddressEntity entity = gson.fromJson(object.toString(), AddressEntity.class);
                        cityList.addAll(entity.getData());


                        strCity = cityList.get(0).getArea_name();
                        cityid = cityList.get(0).getId();


                        cityAdapter = new AddressTextAdapter(context, cityList, 0, maxsize, minsize);
                        wvCitys.setViewAdapter(cityAdapter);
                        wvCitys.setCurrentItem(0);

                        updateAreas(cityList.get(0).getId());

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }


//     根据城市来更新wheel的状态
    private void updateAreas(String id) {

        RequestParams params = new RequestParams();
        utils = new HttpDataUtils(context);
        utils.sendGet(MyData.GETADDRESS + "/" + id, params);
        utils.setSucessBack(new HttpDataUtils.SucessBack() {
            @Override
            public void sucess(ResponseInfo<String> arg0) {
                try {
                    JSONObject object = new JSONObject(arg0.result);
                    String code = JSONTool.getString(object, "code");
                    String msg = JSONTool.getString(object, "msg");
                    if (code.equals("0")) {
                        gson = new Gson();
                        areaList.clear();
                        AddressEntity entity = gson.fromJson(object.toString(), AddressEntity.class);
                        areaList.addAll(entity.getData());

                        areaAdapter = new AddressTextAdapter(context, areaList, 0, maxsize, minsize);
                        wvArea.setViewAdapter(areaAdapter);
                        wvArea.setCurrentItem(0);

                        strArea = areaList.get(0).getArea_name();
                        areaid = areaList.get(0).getId();

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

    @Override
    public void onChanged(WheelView wheel, int oldValue, int newValue) {
        if (wheel == wvProvince) {
            // 切换省份的操作
//            updateCities();
        } else if (wheel == wvCitys) {
//            updateAreas();
        } else if (wheel == wvArea) {
//            String currentText = (String) areaAdapter.getItemText(wheel.getCurrentItem());
//            strArea = currentText;
//            Log.e(strArea);
////				strArea = mAreaDatasMap.get(strCity)[newValue];
//            strArea = arrAreas.get(newValue);
//            setTextviewSize(currentText, areaAdapter);

        }
    }

    private void inteProvinces() {
        RequestParams params = new RequestParams();
        utils = new HttpDataUtils(context);
        utils.sendGet(MyData.GETADDRESS, params);
        utils.setSucessBack(new HttpDataUtils.SucessBack() {
            @Override
            public void sucess(ResponseInfo<String> arg0) {
                try {
                    LogUtils.logE("+++++++++++++++>>>>>>>>>>>>1");
                    JSONObject object = new JSONObject(arg0.result);
                    String code = JSONTool.getString(object, "code");
                    String msg = JSONTool.getString(object, "msg");
                    if (code.equals("0")) {
                        gson = new Gson();
                        AddressEntity entity = gson.fromJson(object.toString(), AddressEntity.class);
                        provincesList.addAll(entity.getData());

                        inteCities(provincesList.get(0).getId());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void inteCities(String id) {
        RequestParams params = new RequestParams();
        utils = new HttpDataUtils(context);
        utils.sendGet(MyData.GETADDRESS + "/" + id, params);
        utils.setSucessBack(new HttpDataUtils.SucessBack() {
            @Override
            public void sucess(ResponseInfo<String> arg0) {
                try {
                    LogUtils.logE("+++++++++++++++>>>>>>>>>>>>2");
                    JSONObject object = new JSONObject(arg0.result);
                    String code = JSONTool.getString(object, "code");
                    String msg = JSONTool.getString(object, "msg");
                    if (code.equals("0")) {
                        gson = new Gson();
                        AddressEntity entity = gson.fromJson(object.toString(), AddressEntity.class);
                        cityList.addAll(entity.getData());

                        inteAreas(cityList.get(0).getId());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void inteAreas(String id) {
        RequestParams params = new RequestParams();
        utils = new HttpDataUtils(context);
        utils.sendGet(MyData.GETADDRESS + "/" + id, params);
        utils.setSucessBack(new HttpDataUtils.SucessBack() {
            @Override
            public void sucess(ResponseInfo<String> arg0) {
                try {
                    LogUtils.logE("+++++++++++++++>>>>>>>>>>>>3");
                    JSONObject object = new JSONObject(arg0.result);
                    String code = JSONTool.getString(object, "code");
                    String msg = JSONTool.getString(object, "msg");
                    if (code.equals("0")) {
                        gson = new Gson();
                        AddressEntity entity = gson.fromJson(object.toString(), AddressEntity.class);
                        areaList.addAll(entity.getData());
                        initDatas();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


    private void initDatas() {

        provinceAdapter = new AddressTextAdapter(context, provincesList, getProvinceItem(strProvince), maxsize, minsize);
        wvProvince.setVisibleItems(4);
        wvProvince.setViewAdapter(provinceAdapter);
        wvProvince.setCurrentItem(getProvinceItem(strProvince));

        cityAdapter = new AddressTextAdapter(context, cityList, getCityItem(strCity), maxsize, minsize);
        wvProvince.setVisibleItems(4);
        wvCitys.setViewAdapter(cityAdapter);
        wvCitys.setCurrentItem(getCityItem(strCity));

        areaAdapter = new AddressTextAdapter(context, areaList, getCityItem(strArea), maxsize, minsize);
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
                provincesid = provincesList.get(wheel.getCurrentItem()).getId();
                updateCities(provincesList.get(wheel.getCurrentItem()).getId());
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
                cityid = cityList.get(wheel.getCurrentItem()).getId();
                updateAreas(cityList.get(wheel.getCurrentItem()).getId());
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
                areaid = areaList.get(wheel.getCurrentItem()).getId();
            }
        });

        LogUtils.logE("+++++++++++++++>>>>>>>>>>>>5");
    }

    private class AddressTextAdapter extends AbstractWheelTextAdapter {

        List<AddressEntity.AddressBean> list;

        protected AddressTextAdapter(Context context, List<AddressEntity.AddressBean> list, int currentItem, int maxsize, int minsize) {
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
            return list.get(index).getArea_name();
        }
    }


    public interface OnAddressCListener {

        void onClick(String province, String city, String area, String provincesid, String cityid, String areaid);

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

                if (onAddressCListener != null) {
                    onAddressCListener.onClick(strProvince, strCity, strArea, provincesid, cityid, areaid);
                }
                break;
            case R.id.btn_myinfo_cancel:
                dismiss();
                break;
        }
    }
}
