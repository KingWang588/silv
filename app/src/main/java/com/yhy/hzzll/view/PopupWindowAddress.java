package com.yhy.hzzll.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.yhy.hzzll.R;
import com.yhy.hzzll.framework.MyData;
import com.yhy.hzzll.mian.entity.AddressEntity;
import com.yhy.hzzll.utils.HttpDataUtils;
import com.yhy.hzzll.utils.JSONTool;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chengying on 2017/8/4.
 */

public class PopupWindowAddress extends PopupWindow {

    Context context;

    TextView tv_province;
    TextView tv_city;
    TextView tv_area;

    View line_province;
    View line_city;
    View line_area;

    ListView lv_province;
    ListView lv_city;
    ListView lv_area;


    private HttpDataUtils utils;
    private Gson gson;
    private List<AddressEntity.AddressBean> paddressList;
    private List<AddressEntity.AddressBean> caddressList;
    private List<AddressEntity.AddressBean> aaddressList;

    LvAdapter plvAdapter;
    LvAdapter clvAdapter;
    LvAdapter alvAdapter;


    private String provincesid = "";
    private String cityid = "";
    private String areaid = "";

    // 选中的省市区信息
    private String strProvince = "";
    private String strCity = "";
    private String strArea = "";

    String level = "3";
    boolean isLimit = false;

    // 回调方法
    private OnAddressCListener onAddressCListener;


    public PopupWindowAddress(Context context, String level, boolean isLimit) {
        this.context = context;
        this.level = level;
        this.isLimit = isLimit;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View conentView = inflater.inflate(R.layout.address_selector, null);
        int width = context.getResources().getDisplayMetrics().widthPixels;
        int height = context.getResources().getDisplayMetrics().heightPixels/3*2;
        this.setContentView(conentView);
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        this.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        this.setFocusable(true);
        this.setOutsideTouchable(true);
//        // 实例化一个ColorDrawable颜色为半透明
//         ColorDrawable dw = new ColorDrawable(0000000000);
//        ColorDrawable dw = (ColorDrawable) context.getResources().getDrawable(R.color.red);
//        this.setBackgroundDrawable(dw);
        this.update();
        this.setAnimationStyle(R.style.AnimationPreview);
        this.setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss() {

            }
        });

        setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        tv_province = (TextView) conentView.findViewById(R.id.tv_province);
        tv_city = (TextView) conentView.findViewById(R.id.tv_city);
        tv_area = (TextView) conentView.findViewById(R.id.tv_area);

        line_province = (View) conentView.findViewById(R.id.line_province);
        line_city = (View) conentView.findViewById(R.id.line_city);
        line_area = (View) conentView.findViewById(R.id.line_area);

        lv_province = (ListView) conentView.findViewById(R.id.lv_province);
        lv_city = (ListView) conentView.findViewById(R.id.lv_city);
        lv_area = (ListView) conentView.findViewById(R.id.lv_area);

        init();
    }


    private void init() {

        if (level.equals("2")) {
            tv_area.setVisibility(View.GONE);
            line_area.setVisibility(View.GONE);
            lv_area.setVisibility(View.GONE);
        }


        tv_province.setText("请选择");

        tv_province.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lv_province.setVisibility(View.VISIBLE);
                lv_city.setVisibility(View.GONE);
                lv_area.setVisibility(View.GONE);

                tv_city.setText("");
                tv_area.setText("");

                line_province.setVisibility(View.VISIBLE);
                line_city.setVisibility(View.GONE);
                line_area.setVisibility(View.GONE);

            }
        });


        tv_city.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lv_city.setVisibility(View.VISIBLE);
                lv_province.setVisibility(View.GONE);
                lv_area.setVisibility(View.GONE);

                tv_area.setText("");

                line_area.setVisibility(View.GONE);
                line_province.setVisibility(View.GONE);
                line_city.setVisibility(View.VISIBLE);


            }
        });

//        tv_province.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                lv_province.setVisibility(View.VISIBLE);
//                lv_city.setVisibility(View.GONE);
//                lv_area.setVisibility(View.GONE);
//            }
//        });


        paddressList = new ArrayList<>();
        caddressList = new ArrayList<>();
        aaddressList = new ArrayList<>();

        plvAdapter = new LvAdapter(paddressList);
        lv_province.setAdapter(plvAdapter);

        clvAdapter = new LvAdapter(caddressList);
        lv_city.setAdapter(clvAdapter);

        alvAdapter = new LvAdapter(aaddressList);
        lv_area.setAdapter(alvAdapter);

        lv_province.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                tv_province.setText(paddressList.get(position).getArea_name());


                line_province.setVisibility(View.GONE);
                line_city.setVisibility(View.VISIBLE);
                line_area.setVisibility(View.GONE);

                tv_city.setText("请选择");

                provincesid = paddressList.get(position).getId();
                strProvince = paddressList.get(position).getArea_name();

                if (strProvince.equals("全国")) {
                    if (onAddressCListener != null) {
                        onAddressCListener.onClick(strProvince, strCity, strArea, provincesid, cityid, areaid);
                    }
                }else{
                    updatacity(paddressList.get(position).getId());
                }

            }
        });

        lv_city.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                tv_city.setText(caddressList.get(position).getArea_name());


                line_province.setVisibility(View.GONE);
                line_city.setVisibility(View.GONE);

                tv_area.setText("请选择");

                cityid = caddressList.get(position).getId();
                strCity = caddressList.get(position).getArea_name();


                if (strCity.equals("不限") || level.equals("2")) {
                    if (onAddressCListener != null) {
                        onAddressCListener.onClick(strProvince, strCity, strArea, provincesid, cityid, areaid);
                    }
                } else {
                    updataArea(caddressList.get(position).getId());
                    line_area.setVisibility(View.VISIBLE);
                }

//                if (level.equals("2")){
//                    if (onAddressCListener != null) {
//                        onAddressCListener.onClick(strProvince, strCity, strArea, provincesid, cityid, areaid);
//                    }
//                }

            }
        });

        lv_area.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                tv_area.setText(aaddressList.get(position).getArea_name());

                strArea = aaddressList.get(position).getArea_name();
                areaid = aaddressList.get(position).getId();

                if (onAddressCListener != null) {
                    onAddressCListener.onClick(strProvince, strCity, strArea, provincesid, cityid, areaid);
                }

            }
        });

        loadProvince();
    }


    private void updataArea(String id) {
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
                        AddressEntity entity = gson.fromJson(object.toString(), AddressEntity.class);

                        lv_province.setVisibility(View.GONE);
                        lv_city.setVisibility(View.GONE);
                        lv_area.setVisibility(View.VISIBLE);
                        aaddressList.clear();
                        if (!isLimit) {
                            aaddressList.add(new AddressEntity.AddressBean("0", "0", "不限", "不限"));
                        }
                        aaddressList.addAll(entity.getData());
                        alvAdapter.notifyDataSetChanged();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void updatacity(String id) {

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
                        AddressEntity entity = gson.fromJson(object.toString(), AddressEntity.class);

                        lv_province.setVisibility(View.GONE);
                        lv_city.setVisibility(View.VISIBLE);
                        lv_area.setVisibility(View.GONE);

                        caddressList.clear();

                        if (!isLimit) {
                            caddressList.add(new AddressEntity.AddressBean("0", "0", "不限", "不限"));
                        }

                        caddressList.addAll(entity.getData());
                        clvAdapter.notifyDataSetChanged();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }


    public interface OnAddressCListener {

        void onClick(String province, String city, String area, String provincesid, String cityid, String areaid);

    }

    public void setAddresskListener(OnAddressCListener onAddressCListener) {
        this.onAddressCListener = onAddressCListener;
    }

    private void loadProvince() {
        RequestParams params = new RequestParams();
        utils = new HttpDataUtils(context);
        utils.sendGet(MyData.GETADDRESS, params);
        utils.setSucessBack(new HttpDataUtils.SucessBack() {
            @Override
            public void sucess(ResponseInfo<String> arg0) {
                try {
                    JSONObject object = new JSONObject(arg0.result);
                    String code = JSONTool.getString(object, "code");
                    if (code.equals("0")) {
                        gson = new Gson();
                        AddressEntity entity = gson.fromJson(object.toString(), AddressEntity.class);

                        if (!isLimit) {
                            paddressList.add(new AddressEntity.AddressBean("0", "0", "全国", "全国"));
                        }

                        paddressList.addAll(entity.getData());
                        plvAdapter.notifyDataSetChanged();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


    class LvAdapter extends BaseAdapter {

        List<AddressEntity.AddressBean> list;

        public LvAdapter(List<AddressEntity.AddressBean> list) {
            this.list = list;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            convertView = View.inflate(context, R.layout.item_address_selector, null);
            TextView tv_address = (TextView) convertView.findViewById(R.id.tv_address);
            tv_address.setText(list.get(position).getArea_name());

            return convertView;
        }
    }


}
