package com.yhy.hzzll.mian.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.yhy.hzzll.R;
import com.yhy.hzzll.common.entity.CaseTypeEntity;
import com.yhy.hzzll.entity.SortEntity;
import com.yhy.hzzll.framework.MyData;
import com.yhy.hzzll.utils.HttpDataUtils;
import com.yhy.hzzll.utils.PrefsUtils;
import com.yhy.hzzll.utils.ToastUtils;
import com.yhy.hzzll.view.CustomEdittext;
import com.yhy.hzzll.view.PopupWindowAddress;

import java.util.ArrayList;
import java.util.List;

public class RegisterSecondActivity extends BaseActivity {


    @ViewInject(R.id.tv_next)
    private TextView tv_next;

    @ViewInject(R.id.tv_lawyer_expertise)
    private TextView tv_lawyer_expertise;

    @ViewInject(R.id.linear_main)
    private LinearLayout linear_mian;


    @ViewInject(R.id.radioGroup)
    private RadioGroup rg_gender;

    @ViewInject(R.id.radioGroup2)
    private RadioGroup rg_lawyer_type;

    @ViewInject(R.id.et_truename)
    private CustomEdittext et_truename;

    private String gender = "";
    //律师类型
    private String lawyer_type = "";

    @ViewInject(R.id.et_law_office_name)
    private CustomEdittext et_law_office_name;

    @ViewInject(R.id.et_lawyer_license_number)
    private CustomEdittext et_lawyer_license_number;

    private ArrayList<String> arrProvinces = new ArrayList<String>();

    @ViewInject(R.id.tv_address)
    private TextView tv_address;

    private String addressID;

    private List<SortEntity> sortList; // 专长集合
    String specialitiesid = ""; //专长id

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_second);
        ViewUtils.inject(this);
        viewInit();
    }

    public void viewInit() {
        getLawyerExpertise();
        rg_gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radio0:
                        gender = "2";
                        break;
                    case R.id.radio1:
                        gender = "3";
                        break;
                }
            }
        });

        rg_lawyer_type.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radio3:
                        lawyer_type = "11";
                        break;
                    case R.id.radio4:
                        lawyer_type = "12";
                        break;
                }
            }
        });
    }

    private void getLawyerExpertise() {
        RequestParams params = new RequestParams();
        httpDataUtils.sendGet(MyData.CASETYPE, params);
        httpDataUtils.setSucessBack(new HttpDataUtils.SucessBack() {
            @Override
            public void sucess(ResponseInfo<String> arg0) {
                if (httpDataUtils.code(arg0.result)) {
                    CaseTypeEntity entity = gson.fromJson(arg0.result, CaseTypeEntity.class);
                    sortList = new ArrayList<SortEntity>();
                    sortList.clear();
                    for (int i = 0; i < entity.getData().size(); i++) {
                        sortList.add(new SortEntity(entity.getData().get(i).getName(), entity.getData().get(i).getId() + "", false));
                    }
                }
            }
        });
    }

    @OnClick({R.id.tv_next, R.id.linear_main, R.id.linear_address,R.id.tv_home})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_home:

                startActivity(new Intent().putExtra("tab", 0).setClass(getApplicationContext(), MainActivity.class));

                break;

            case R.id.linear_address:

                final PopupWindowAddress popupWindowAddress = new PopupWindowAddress(RegisterSecondActivity.this,"3",true);
                popupWindowAddress.setAddresskListener(new PopupWindowAddress.OnAddressCListener() {
                    @Override
                    public void onClick(String province, String city, String area, String provincesid, String cityid, String areaid) {
                        tv_address.setText(province+"-"+city+"-"+ area);
                        addressID = areaid;
                        popupWindowAddress.dismiss();
                    }
                });

                popupWindowAddress.showAtLocation(view, Gravity.CENTER,0,0);
                break;
            case R.id.linear_main:
                publishViewInit(tv_lawyer_expertise);
                break;
            case R.id.tv_next:
                if (et_truename.getText().toString().isEmpty()) {
                    ToastUtils.getUtils(RegisterSecondActivity.this).show("请填写真实姓名！");
                    return;
                }
                if (gender.equals("")) {
                    ToastUtils.getUtils(RegisterSecondActivity.this).show("请选择性别！");
                    return;
                }


                if(lawyer_type.equals("11")){

                    if (et_lawyer_license_number.getText().toString().length()!=14) {
                        ToastUtils.getUtils(RegisterSecondActivity.this).show("请填写15位的实习律师证号！");
                        return;
                    }

                }else{

                    if (et_lawyer_license_number.getText().toString().length()!=17) {
                        ToastUtils.getUtils(RegisterSecondActivity.this).show("请填写17位的律师执业证号！");
                        return;
                    }

                }


                if (et_law_office_name.getText().toString().isEmpty()) {
                    ToastUtils.getUtils(RegisterSecondActivity.this).show("请填写律所名称！");
                    return;
                }

                if (tv_lawyer_expertise.getText().toString().equals("")) {
                    ToastUtils.getUtils(RegisterSecondActivity.this).show("请选择专长!");
                    return;
                }

                if (tv_address.getText().toString().equals("")) {
                    ToastUtils.getUtils(RegisterSecondActivity.this).show("请选择地区!");
                    return;
                }


                authentication();

                break;

        }
    }

    private void authentication() {
        RequestParams params = new RequestParams();

        params.addHeader("Authorization", PrefsUtils.getString(RegisterSecondActivity.this,PrefsUtils.AUTHORIZATION));
        params.addBodyParameter("truename", et_truename.getText().toString());
        params.addBodyParameter("gender", gender);
        params.addBodyParameter("lawyer_type", lawyer_type);
        params.addBodyParameter("lawyer_secpical", specialitiesid);
        params.addBodyParameter("base_region_id", addressID);
        params.addBodyParameter("lawyer_license_no", et_lawyer_license_number.getText().toString());
        params.addBodyParameter("law_firm", et_law_office_name.getText().toString());

        httpDataUtils.sendProgressPost(MyData.REALNAME, params);
        httpDataUtils.setSucessBack(new HttpDataUtils.SucessBack() {
            @Override
            public void sucess(ResponseInfo<String> arg0) {

                if (httpDataUtils.code(arg0.result)) {
                    startActivity(new Intent(RegisterSecondActivity.this,RegisterThirdActivity.class));
                } else {
                    httpDataUtils.showMsgNew(arg0.result);
                }
            }
        });
    }

    private void publishViewInit(final TextView tv) {

        AlertDialog.Builder builder = new AlertDialog.Builder(RegisterSecondActivity.this);
        View selectview = LayoutInflater.from(RegisterSecondActivity.this).inflate(R.layout.selectview_dialog, null);
        ListView lv_select = (ListView) selectview.findViewById(R.id.lv_select);
        LvAdapter lvAdapter = new LvAdapter(sortList);
        lv_select.setAdapter(lvAdapter);
        builder.setView(selectview);
        builder.setPositiveButton("确定", null);
        builder.setNegativeButton("返回", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {// 响应事件
                // TODO Auto-generated method stub
            }
        });
        final AlertDialog dialog = builder.create();
        dialog.show();// 在按键响应事件中显示此对话框
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String specialities = "";
                int num = 0;
                for (int i = 0; i < sortList.size(); i++) {
                    if (sortList.get(i).isSelect()) {
                        num++;
                    }
                }

                if (num > 5) {
                    ToastUtils.getUtils(RegisterSecondActivity.this).show("专长最多只能设置五条！");
                } else {
                    for (int i = 0; i < sortList.size(); i++) {
                        if (sortList.get(i).isSelect()) {
                            specialities = specialities + sortList.get(i).getTitle() + ",";
                            specialitiesid = specialitiesid + sortList.get(i).getId() + ",";
                        }
                    }

                    if (specialities.length()!= 0){
                        specialitiesid = specialitiesid.substring(0, specialitiesid.length() - 1);
                        specialities = specialities.substring(0, specialities.length() - 1);
                    }

                    tv.setText(specialities);
                    dialog.dismiss();
                }
            }
        });
    }


    public class LvAdapter extends BaseAdapter {

        private List<SortEntity> list;

        public LvAdapter(List<SortEntity> list) {
            super();
            this.list = list;
        }

        @Override
        public int getCount() {
            return list != null ? list.size() : 0;
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
        public View getView(final int position, View convertView, ViewGroup parent) {

            ViewHolder holder = null;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = LayoutInflater.from(RegisterSecondActivity.this).inflate(R.layout.item_lv_cause_of_action, null);
                holder.checkbox = (CheckBox) convertView.findViewById(R.id.cb_cause_of_action);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.checkbox.setText(list.get(position).getTitle());

            int i = 0;
            holder.checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                    if (isChecked) {
                        list.get(position).setSelect(true);
                    } else {
                        list.get(position).setSelect(false);
                    }
                }
            });

            if (list.get(position).isSelect()) {
                holder.checkbox.setChecked(true);
            } else {
                holder.checkbox.setChecked(false);
            }

            return convertView;
        }

        private class ViewHolder {
            CheckBox checkbox;
        }

    }

    @Override
    public void onBackPressed() {

        return;
    }

}
