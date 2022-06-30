package com.yhy.hzzll.home.activity.newcollaborate;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EdgeEffect;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionListener;
import com.yhy.hzzll.R;
import com.yhy.hzzll.entity.SortEntity;
import com.yhy.hzzll.framework.MyData;
import com.yhy.hzzll.home.activity.collaborate.LawyerNationalCooperationActivity;
import com.yhy.hzzll.home.entity.SelectTypeEntity;
import com.yhy.hzzll.mian.activity.BaseActivity;
import com.yhy.hzzll.mian.activity.NewPayActivity;
import com.yhy.hzzll.utils.HttpDataUtils;
import com.yhy.hzzll.utils.JSONTool;
import com.yhy.hzzll.utils.PrefsUtils;
import com.yhy.hzzll.utils.ToastUtils;
import com.yhy.hzzll.view.PickPhotoView;
import com.yhy.hzzll.view.PopupWindowAddress;
import com.yhy.hzzll.view.SortView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import me.nereo.multi_image_selector.MultiImageSelector;

public class CollaborationPublishActivity extends BaseActivity implements PickPhotoView.OnItemClickedLisener {

    @ViewInject(R.id.ll_photo)
    private PickPhotoView ll_photo;


    @ViewInject(R.id.tv_select_type)
    TextView tv_select_type;

    @ViewInject(R.id.tv_address)
    TextView tv_address;
    @ViewInject(R.id.tv_phone_auot)
    TextView tv_phone_auot;

    @ViewInject(R.id.et_details)
    EditText et_details;
    @ViewInject(R.id.et_money)
    EditText et_money;
    @ViewInject(R.id.et_name)
    EditText et_name;
    @ViewInject(R.id.et_mobile)
    EditText et_mobile;

    SelectTypeEntity selectTypeEntity;
    private ArrayList<String> cardImgUrls = new ArrayList<String>();

    String type;
    String address;
    View view_root;

    private CharSequence temp;

    /**
     * 图片集合
     */
    public static ArrayList<String> photolist = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        cardImgUrls.clear();

        super.onCreate(savedInstanceState);

        view_root = LayoutInflater.from(this).inflate(R.layout.activity_collaboration_publish, null);
        setContentView(view_root);

        ViewUtils.inject(this);

        photolist.clear();
        ll_photo.addViewed(cardImgUrls);
        ll_photo.setOnItemClickedLisener(this);


        et_details.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                temp = charSequence;
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (temp.length() >= 300){
                    ToastUtils.getUtils(CollaborationPublishActivity.this).show("请用不超过300字描述您的需求。");
                }
            }
        });

    }


    @Override
    public void onItemClick(int position, View view) {

    }

    @Override
    public void onAddClick(int position, View view) {
        if (cardImgUrls != null) {
            cardImgUrls.clear();
        }
        if (cardImgUrls != null && cardImgUrls.size() != 0) {
            cardImgUrls.clear();
        }

        AndPermission.with(CollaborationPublishActivity.this)
                .requestCode(102)
                .permission(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .callback(permissionListener)
                .start();
    }

    private static final int REQUEST_CODE_SETTING = 300;

    /**
     * 回调监听。
     */
    private PermissionListener permissionListener = new PermissionListener() {
        @Override
        public void onSucceed(int requestCode, List<String> grantPermissions) {
            switch (requestCode) {

                case 102: {
                    if (AndPermission.hasPermission(CollaborationPublishActivity.this, Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                        pickImage2();
                    } else {
                        //专为小米 设置的
                        Toast.makeText(CollaborationPublishActivity.this, "我们需要的一些权限被您拒绝或者系统发生错误申请失败，请您到设置页面手动授权，否则功能无法正常使用！", Toast.LENGTH_SHORT).show();
//                        AndPermission.defaultSettingDialog(EvidenceSavaActivity.this,101);
                    }
                    break;
                }
            }
        }

        @Override
        public void onFailed(int requestCode, List<String> deniedPermissions) {
            switch (requestCode) {
                case 101: {
                    Toast.makeText(CollaborationPublishActivity.this, "获取权限失败", Toast.LENGTH_SHORT).show();
                    break;
                }
                case 102: {
                    Toast.makeText(CollaborationPublishActivity.this, "获取权限失败", Toast.LENGTH_SHORT).show();
                    break;
                }
            }

            if (AndPermission.hasAlwaysDeniedPermission(CollaborationPublishActivity.this, deniedPermissions)) {

                AndPermission.defaultSettingDialog(CollaborationPublishActivity.this, REQUEST_CODE_SETTING).show();
            }
        }
    };

    private void pickImage2() {
        MultiImageSelector selector = MultiImageSelector.create();
        selector.count(5);
        selector.showCamera(true);
        selector.multi();
        selector.origin(photolist);
        selector.start(CollaborationPublishActivity.this, 2);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 2) {
            if (data != null) {
                photolist.clear();
                photolist.addAll(data.getStringArrayListExtra(MultiImageSelector.EXTRA_RESULT));
                ll_photo.addViewed(photolist);

            }
        } else if (requestCode == 252) {
            finish();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @OnClick({R.id.ic_back, R.id.tv_select_type, R.id.tv_address, R.id.tv_publish, R.id.tv_phone_auot})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_phone_auot:

                et_mobile.setText(PrefsUtils.getString(CollaborationPublishActivity.this, PrefsUtils.UPHONE));

                break;

            case R.id.tv_publish:

                submit();

                break;
            case R.id.ic_back:
                finish();
                break;
            case R.id.tv_select_type:
                getSelectType();
                break;

            case R.id.tv_address:
                final PopupWindowAddress popupWindowAddress = new PopupWindowAddress(CollaborationPublishActivity.this, "2", true);
                popupWindowAddress.setAddresskListener(new PopupWindowAddress.OnAddressCListener() {
                    @Override
                    public void onClick(String province, String city, String area, String provincesid, String cityid, String areaid) {
//                        ToastUtils.getUtils(getActivity()).show(province+"province"+city+"city"+area+"area"+provincesid+"provincesid"+cityid+"cityid"+areaid+"areaid");
                        if (province.equals("全国")) {
                            address = "";
//                            handler.sendEmptyMessage(1);
                            popupWindowAddress.dismiss();
                            tv_address.setText(province);
                            return;
                        }
                        if (city.equals("不限")) {
                            address = provincesid;
////                            address = address.substring(0, 2);
//                            handler.sendEmptyMessage(1);
                            tv_address.setText(province);
                            popupWindowAddress.dismiss();
                            return;
                        } else {
                            address = cityid;
////                            address = address.substring(0, 4);
//                            handler.sendEmptyMessage(1);
                            tv_address.setText(province+"-"+city);
                            popupWindowAddress.dismiss();
                            return;
                        }
                    }
                });
                popupWindowAddress.showAsDropDown(view_root);
                break;
        }
    }

    private void submit() {
        RequestParams params = new RequestParams();
        params.addHeader("Authorization", PrefsUtils.getString(CollaborationPublishActivity.this, PrefsUtils.AUTHORIZATION));
        params.addQueryStringParameter("employ_type_id", type);
        params.addQueryStringParameter("question", et_details.getText().toString());
        params.addQueryStringParameter("budget_amount", et_money.getText().toString());
        params.addQueryStringParameter("nickname", et_name.getText().toString());
        params.addQueryStringParameter("base_region_id", address);
        params.addQueryStringParameter("mobile", et_mobile.getText().toString());

        for (int i = 0; i < photolist.size(); i++) {
            params.addBodyParameter("file_attachment_id[" + i + "]", new File(photolist.get(i)));
        }

        httpDataUtils.sendProgressPost(MyData.EMPLOYLAYER_RELEASE_PUBLIC, params);
        httpDataUtils.setSucessBack(new HttpDataUtils.SucessBack() {
            @Override
            public void sucess(ResponseInfo<String> arg0) {

                try {
                    JSONObject object = new JSONObject(arg0.result);
                    String code = JSONTool.getString(object, "code");
                    String msg = JSONTool.getString(object, "message");
                    if (code.equals("0")) {

                        JSONObject data = object.getJSONObject("data");
                        String employ_id = JSONTool.getString(data, "employ_id");
                        String order_no = JSONTool.getString(data, "order_no");


                        startActivityForResult(new Intent(CollaborationPublishActivity.this, NewPayActivity.class).putExtra("order_no", order_no).putExtra("money", 50).putExtra("success", true), 252);

//                        goToPay(order_no);

                    } else {
                        ToastUtils.getUtils(getApplicationContext()).show(msg);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
        httpDataUtils.setFailBack(new HttpDataUtils.FailBack() {
            @Override
            public void fail(String msg) {
            }
        });


    }

    private void goToPay(String order_no) {

        RequestParams params = new RequestParams();
        params.addHeader("Authorization", PrefsUtils.getString(CollaborationPublishActivity.this, PrefsUtils.AUTHORIZATION));
        params.addQueryStringParameter("order_no", order_no);
        httpDataUtils.sendGet(MyData.LAWYER_PAY, params);
        httpDataUtils.setSucessBack(new HttpDataUtils.SucessBack() {
            @Override
            public void sucess(ResponseInfo<String> arg0) {

                try {
                    JSONObject object = new JSONObject(arg0.result);
                    String code = JSONTool.getString(object, "code");
                    String msg = JSONTool.getString(object, "message");
                    if (code.equals("0")) {
                        Gson gson = new Gson();
                        selectTypeEntity = gson.fromJson(arg0.result, SelectTypeEntity.class);


                        sortList.clear();

                        for (int i = 0; i < selectTypeEntity.getData().size(); i++) {
                            sortList.add(new SortEntity(selectTypeEntity.getData().get(i).getName(), "1", false));
                        }

                        dealViewInit(tv_select_type);

                    } else {
                        ToastUtils.getUtils(getApplicationContext()).show(msg);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
        httpDataUtils.setFailBack(new HttpDataUtils.FailBack() {
            @Override
            public void fail(String msg) {
            }
        });


    }

    List<SortEntity> sortList = new ArrayList<>();

    private void getSelectType() {

        RequestParams params = new RequestParams();
        httpDataUtils.sendGet(MyData.SELEET_EMPLOYLAYER_TYPE, params);
        httpDataUtils.setSucessBack(new HttpDataUtils.SucessBack() {
            @Override
            public void sucess(ResponseInfo<String> arg0) {

                try {
                    JSONObject object = new JSONObject(arg0.result);
                    String code = JSONTool.getString(object, "code");
                    String msg = JSONTool.getString(object, "message");
                    if (code.equals("0")) {
                        Gson gson = new Gson();
                        selectTypeEntity = gson.fromJson(arg0.result, SelectTypeEntity.class);


                        sortList.clear();

                        for (int i = 0; i < selectTypeEntity.getData().size(); i++) {
                            sortList.add(new SortEntity(selectTypeEntity.getData().get(i).getName(), "1", false));
                        }

                        dealViewInit(tv_select_type);

                    } else {
                        ToastUtils.getUtils(getApplicationContext()).show(msg);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
        httpDataUtils.setFailBack(new HttpDataUtils.FailBack() {
            @Override
            public void fail(String msg) {
            }
        });
    }

    private void dealViewInit(final TextView view) {

        final SortView sortView = new SortView();
        sortView.setSortViews(this, view, sortList);
        sortView.setSelects(new SortView.Select() {
            @Override
            public void index(int index) {

                type = selectTypeEntity.getData().get(index).getId() + "";
//                ToastUtils.getUtils(CollaborationPublishActivity.this).show(type);

            }
        });
    }


    @Override
    public void onDestroy() {
        cardImgUrls.clear();
        cardImgUrls = null;

        super.onDestroy();
    }
}
