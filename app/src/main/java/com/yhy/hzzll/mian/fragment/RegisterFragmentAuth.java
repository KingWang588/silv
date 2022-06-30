package com.yhy.hzzll.mian.fragment;

/**
 * 律师注册--实名认证
 *
 * @author Yang
 */
public class RegisterFragmentAuth extends BaseFragment {

//    private View view;
//    private Handler handler;
//
//    @ViewInject(R.id.tv_next)
//    private TextView tv_next;
//
//    @ViewInject(R.id.tv_lawyer_expertise)
//    private TextView tv_lawyer_expertise;
//
//    @ViewInject(R.id.linear_main)
//    private LinearLayout linear_mian;
//
//    private DialogLoading loading;
//
//    @ViewInject(radioGroup)
//    private RadioGroup rg_gender;
//
//    @ViewInject(R.id.radioGroup2)
//    private RadioGroup rg_lawyer_type;
//
//    private HttpDataUtils dataUtils;
//
//
//
//    /**
//     * 真实姓名
//     */
//    @ViewInject(R.id.et_truename)
//    private CustomEdittext et_truename;
//    /**
//     * 性别
//     */
//    private String gender = "";
//    //律师类型
//    private String lawyer_type = "";
//    /**
//     * 律所
//     */
//    @ViewInject(R.id.et_law_office_name)
//    private CustomEdittext et_law_office_name;
//
//    /**
//     * 职业证号
//     */
//    @ViewInject(R.id.et_lawyer_license_number)
//    private CustomEdittext et_lawyer_license_number;
//
//    private ArrayList<String> arrProvinces = new ArrayList<String>();
//
//
//
//    @ViewInject(R.id.tv_address)
//    private TextView tv_address;
//
//    private String addressID;
//
//    private List<SortEntity> sortList; // 专长集合
//    String specialitiesid = ""; //专长id
//
//    public RegisterFragmentAuth() {
//
//    }
//
//    public void setHandler(Handler handler) {
//        this.handler = handler;
//        // TODO Auto-generated constructor stub
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        dataUtils = new HttpDataUtils(getActivity());
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        view = inflater.inflate(R.layout.fragment_register_auth, null);
//        ViewUtils.inject(this, view);
//        viewInit();
//        return view;
//    }
//
//    private void getLawyerExpertise() {
//        RequestParams params = new RequestParams();
//        dataUtils.sendGet(MyData.CASETYPE, params);
//        dataUtils.setSucessBack(new HttpDataUtils.SucessBack() {
//            @Override
//            public void sucess(ResponseInfo<String> arg0) {
//                if (dataUtils.code(arg0.result)) {
//                    CaseTypeEntity entity = gson.fromJson(arg0.result, CaseTypeEntity.class);
//                    sortList = new ArrayList<SortEntity>();
//                    sortList.clear();
//                    for (int i = 0; i < entity.getData().size(); i++) {
//                        sortList.add(new SortEntity(entity.getData().get(i).getName(), entity.getData().get(i).getId() + "", false));
//                    }
//                }
//            }
//        });
//    }
//
//    @OnClick({R.id.tv_next, R.id.linear_main, R.id.linear_address})
//    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.linear_address:
//
//                final PopupWindowAddress popupWindowAddress = new PopupWindowAddress(getActivity(),"3",true);
//                popupWindowAddress.setAddresskListener(new PopupWindowAddress.OnAddressCListener() {
//                    @Override
//                    public void onClick(String province, String city, String area, String provincesid, String cityid, String areaid) {
//                        tv_address.setText(province+"-"+city+"-"+ area);
//                        addressID = areaid;
//                        popupWindowAddress.dismiss();
//                    }
//                });
//
//                popupWindowAddress.showAtLocation(view, Gravity.CENTER,0,0);
//                break;
//            case R.id.linear_main:
//                publishViewInit(tv_lawyer_expertise);
//                break;
//            case R.id.tv_next:
//                if (et_truename.getText().toString().isEmpty()) {
//                    ToastUtils.getUtils(getActivity()).show("请填写真实姓名！");
//                    return;
//                }
//                if (gender.equals("")) {
//                    ToastUtils.getUtils(getActivity()).show("请选择性别！");
//                    return;
//                }
//                if (et_law_office_name.getText().toString().isEmpty()) {
//                    ToastUtils.getUtils(getActivity()).show("请填写律所名称！");
//                    return;
//                }
//
//                if(lawyer_type.equals("11")){
//
//                    if (et_lawyer_license_number.getText().toString().length()!=15) {
//                        ToastUtils.getUtils(getActivity()).show("请填写15位的实习律师证号！");
//                        return;
//                    }
//
//                }else{
//
//                    if (et_lawyer_license_number.getText().toString().length()!=17) {
//                        ToastUtils.getUtils(getActivity()).show("请填写17位的律师执业证号！");
//                        return;
//                    }
//
//                }
//
//
//                if (tv_lawyer_expertise.getText().toString().equals("请选择专长")) {
//                    ToastUtils.getUtils(getActivity()).show("请选择专长!");
//                    return;
//                }
//
//                authentication();
//
//                break;
//
//        }
//    }
//
//    @Override
//    public void viewInit() {
//        getLawyerExpertise();
//        rg_gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                switch (checkedId) {
//                    case R.id.radio0:
//                        gender = "2";
//                        break;
//                    case R.id.radio1:
//                        gender = "3";
//                        break;
//                }
//            }
//        });
//
//        rg_lawyer_type.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                switch (checkedId) {
//                    case R.id.radio3:
//                        lawyer_type = "11";
//                        break;
//                    case R.id.radio4:
//                        lawyer_type = "12";
//                        break;
//                }
//            }
//        });
//
//        loading = new DialogLoading();
//        super.viewInit();
//    }
//
//    /**
//     * 提交认证第一步
//     */
//    private void authentication() {
//        RequestParams params = new RequestParams();
//
//        params.addHeader("Authorization", PrefsUtils.getString(getActivity(),PrefsUtils.AUTHORIZATION));
//        params.addBodyParameter("truename", et_truename.getText().toString());
//        params.addBodyParameter("gender", gender);
//        params.addBodyParameter("lawyer_type", lawyer_type);
//        params.addBodyParameter("lawyer_secpical", specialitiesid);
//        params.addBodyParameter("base_region_id", addressID);
//        LogUtils.logE(addressID+"/////");
//        params.addBodyParameter("lawyer_license_no", et_lawyer_license_number.getText().toString());
//        params.addBodyParameter("law_firm", et_law_office_name.getText().toString());
//
//
//        dataUtils.sendProgressPost(MyData.REALNAME, params);
//        dataUtils.setSucessBack(new HttpDataUtils.SucessBack() {
//            @Override
//            public void sucess(ResponseInfo<String> arg0) {
//
//                    if (dataUtils.code(arg0.result)) {
//                        handler.sendEmptyMessage(2);
//                    } else {
//                        dataUtils.showMsg(arg0.result);
//                    }
//            }
//        });
//
//    }
//
//    /**
//     * 专长
//     *
//     * @param
//     */
//    private void publishViewInit(final TextView tv) {
//
//        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//        View selectview = LayoutInflater.from(getActivity()).inflate(R.layout.selectview_dialog, null);
//        ListView lv_select = (ListView) selectview.findViewById(R.id.lv_select);
//        LvAdapter lvAdapter = new LvAdapter(sortList);
//        lv_select.setAdapter(lvAdapter);
//        builder.setView(selectview);
//        builder.setPositiveButton("确定", null);
//        builder.setNegativeButton("返回", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {// 响应事件
//                // TODO Auto-generated method stub
//            }
//        });
//        final AlertDialog dialog = builder.create();
//        dialog.show();// 在按键响应事件中显示此对话框
//        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                String specialities = "";
//                int num = 0;
//                for (int i = 0; i < sortList.size(); i++) {
//                    if (sortList.get(i).isSelect()) {
//                        num++;
//                    }
//                }
//
//                if (num > 5) {
//                    ToastUtils.getUtils(getActivity()).show("专长最多只能设置五条！");
//                } else {
//                    for (int i = 0; i < sortList.size(); i++) {
//                        if (sortList.get(i).isSelect()) {
//                            specialities = specialities + sortList.get(i).getTitle() + ",";
//                            specialitiesid = specialitiesid + sortList.get(i).getId() + ",";
//                        }
//                    }
//
//                    if (specialities.length()!= 0){
//                        specialitiesid = specialitiesid.substring(0, specialitiesid.length() - 1);
//                        specialities = specialities.substring(0, specialities.length() - 1);
//                    }
//
//                    tv.setText(specialities);
//                    dialog.dismiss();
//                }
//            }
//        });
//    }
//
//    /**
//     * 弹出框listView的适配器
//     *
//     * @author 一合鱼
//     */
//
//    public class LvAdapter extends BaseAdapter {
//
//        private List<SortEntity> list;
//
//        public LvAdapter(List<SortEntity> list) {
//            super();
//            this.list = list;
//        }
//
//        @Override
//        public int getCount() {
//            return list != null ? list.size() : 0;
//        }
//
//        @Override
//        public Object getItem(int position) {
//            return list.get(position);
//        }
//
//        @Override
//        public long getItemId(int position) {
//            return position;
//        }
//
//        @Override
//        public View getView(final int position, View convertView, ViewGroup parent) {
//            // TODO Auto-generated method stub
//            ViewHolder holder = null;
//            if (convertView == null) {
//                holder = new ViewHolder();
//                convertView = LayoutInflater.from(getActivity()).inflate(R.layout.item_lv_cause_of_action, null);
//                holder.checkbox = (CheckBox) convertView.findViewById(R.id.cb_cause_of_action);
//                // holder.checkbox.setTag(position);
//                convertView.setTag(holder);
//            } else {
//                holder = (ViewHolder) convertView.getTag();
//            }
//
//            holder.checkbox.setText(list.get(position).getTitle());
//
//            int i = 0;
//
//            holder.checkbox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
//                @Override
//                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//
//                    if (isChecked) {
//                        list.get(position).setSelect(true);
//                    } else {
//                        list.get(position).setSelect(false);
//                    }
//                }
//            });
//
//            if (list.get(position).isSelect()) {
//                holder.checkbox.setChecked(true);
//            } else {
//                holder.checkbox.setChecked(false);
//            }
//
//            return convertView;
//        }
//
//        private class ViewHolder {
//            CheckBox checkbox;
//        }
//
//    }

}
