package com.yhy.hzzll.my.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionListener;
import com.yhy.hzzll.R;
import com.yhy.hzzll.common.entity.CaseTypeEntity;
import com.yhy.hzzll.entity.SortEntity;
import com.yhy.hzzll.framework.MyData;
import com.yhy.hzzll.mian.activity.BaseActivity;
import com.yhy.hzzll.mian.entity.DataUserEntity;
import com.yhy.hzzll.mian.view.AdressSelect;
import com.yhy.hzzll.my.entity.UserDataInfoEntity;
import com.yhy.hzzll.utils.HttpDataUtils;
import com.yhy.hzzll.utils.PrefsUtils;
import com.yhy.hzzll.utils.ToastUtils;
import com.yhy.hzzll.view.DialogLoading;
import com.yhy.hzzll.view.LawyerJobPopupWindow;
import com.yhy.hzzll.view.PopupWindowAddress;
import com.yhy.ooslibrary.util.PutObjectSamples;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import me.nereo.multi_image_selector.MultiImageSelector;

/**
 * 设置--律师个人资料
 *
 * @author Yang
 */
public class PersonDataLawyerActivity extends BaseActivity {

    @ViewInject(R.id.rl_avatar_content)
    RelativeLayout rl_avatar_content;
    @ViewInject(R.id.iv_avatar)
    ImageView iv_avatar;
    @ViewInject(R.id.tv_username)
    TextView tv_username;
    @ViewInject(R.id.tv_nickname)
    TextView tv_nickname;
    @ViewInject(R.id.tv_sex)
    TextView tv_sex;
    @ViewInject(R.id.tv_license_no)
    TextView tv_license_no;
    @ViewInject(R.id.tv_lawyer_name)
    TextView tv_lawyer_name;
    @ViewInject(R.id.tv_duty_type)
    TextView tv_duty_type;
    @ViewInject(R.id.tv_address)
    TextView tv_address;
    @ViewInject(R.id.tv_address_detail)
    TextView tv_address_detail;
    @ViewInject(R.id.tv_main)
    TextView tv_specialty;
    @ViewInject(R.id.et_description)
    TextView et_description;
    @ViewInject(R.id.iv_avatar_icon)
    ImageView iv_avatar_icon;
    @ViewInject(R.id.iv_nickname)
    ImageView iv_nickname;
    @ViewInject(R.id.rl_sex)
    RelativeLayout rl_sex;
    @ViewInject(R.id.rl_duty_type_content)
    RelativeLayout rl_duty_type_content;
    @ViewInject(R.id.rl_address_content)
    RelativeLayout rl_address_content;
    @ViewInject(R.id.iv_address)
    ImageView iv_address;
    @ViewInject(R.id.iv_address_detail)
    ImageView iv_address_detail;
    @ViewInject(R.id.rl_specialty_content)
    LinearLayout rl_specialty_content;
    @ViewInject(R.id.iv_specialty)
    ImageView iv_specialty;
    @ViewInject(R.id.tv_add)
    TextView tv_add;
    @ViewInject(R.id.tv_state)
    TextView tv_state;
    private View view;
    private DataUserEntity userEntity;
    /**
     * 图片集合
     */
    public static List<String> photolist = new ArrayList<String>();
    public static final int PHOTOALBUMCODE = 902;
    /**
     * 文件的服务器回调路径
     */
    private String filepath;
    private PutObjectSamples samples;
    private File file;
    private AdressSelect popupWindow = null;
    private ArrayList<String> arrProvinces = new ArrayList<String>();
    /**
     * 省市区ID
     */
    private String provincesId, cityId, areaId;
    /**
     * 是否是编辑状态,默认是不是编辑状态
     */
//	private boolean isEditState = true;

    /**
     * 已经选择的本地照片路径
     */
    // public static ArrayList<String> photoList = new ArrayList<String>();
    private static final int REQUEST_CODE_SETTING = 300;

    private ArrayList<String> urlPathList;
    /**
     * 记录上传到哪一张图片
     */
    private int index = 0;
    private DialogLoading loading;
    private String hashcode;
    public static final int FEEDBACK_PHOTO_ALBUM_CODE = 901;
    private String photoName;
    private int authenticateUserState;

    private int setAvaut;
    @ViewInject(R.id.srl_listview)
    SwipeRefreshLayout srl_listview;

    @Override
    protected void onCreate(Bundle arg0) {
        view = getLayoutInflater().inflate(R.layout.activity_lawyer_persondata, null);
        createCameraTempFile(arg0);
        setContentView(view);
        super.onCreate(arg0);
        ViewUtils.inject(this);
        init();
    }

    private void init() {
        loading = new DialogLoading();
        httpDataUtils = new HttpDataUtils(this);
        urlPathList = new ArrayList<>();

        initViews();

        srl_listview.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                checkoutUserInfo();
            }
        });

    }

    private void checkoutUserInfo() {

//        srl_listview.setRefreshing(true);

        RequestParams params = new RequestParams();
        params.addHeader("Authorization", PrefsUtils.getString(PersonDataLawyerActivity.this, PrefsUtils.AUTHORIZATION));
        httpDataUtils.sendGet(MyData.CM_USER_DATA_INFO, params);
        httpDataUtils.setSucessBack(new HttpDataUtils.SucessBack() {
            @Override
            public void sucess(ResponseInfo<String> arg0) {
                try {
                    JSONObject object = new JSONObject(arg0.result);
                    String code = object.optString("code");
                    String msg = object.optString("msg");
                    if (code.equals("0")) {
                        Gson gson = new Gson();
                        try {
                            UserDataInfoEntity userDataInfoEntity = gson.fromJson(arg0.result, UserDataInfoEntity.class);
                            if (userDataInfoEntity.getData().getAvatar()!=null&userDataInfoEntity.getData().getAvatar().length()!=0)
                                Glide.with(PersonDataLawyerActivity.this).load(userDataInfoEntity.getData().getAvatar()).into(iv_avatar);

                            tv_username.setText(userDataInfoEntity.getData().getTruename() + "");
                            tv_nickname.setText(userDataInfoEntity.getData().getNickname());
                            String gender = userDataInfoEntity.getData().getGender();

                            if (gender.equals("3")) {
                                tv_sex.setText("女");
                            } else if (gender.equals("2")) {
                                tv_sex.setText("男");
                            }
                            tv_license_no.setText(userDataInfoEntity.getData().getLawyer_license_no());
                            tv_lawyer_name.setText(userDataInfoEntity.getData().getLaw_firm());
                            tv_duty_type.setText(userDataInfoEntity.getData().getLawyer_title());
                            tv_address.setText(userDataInfoEntity.getData().getBase_region_id());
                            tv_address_detail.setText(userDataInfoEntity.getData().getAddress());

                            et_description.setText(userDataInfoEntity.getData().getLawyer_intro());

                            tv_specialty.setText(userDataInfoEntity.getData().getLawyer_secpical());

                            tv_state.setText(userDataInfoEntity.getData().getAuth_status());

                            srl_listview.setRefreshing(false);

                        } catch (Exception e) {
//                            LogUtil.e("fd方法对方的方法","ddddddddddddddddd");
                        }
                    } else {
                        // ToastUtils.getUtils(getActivity()).show(msg);
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
                    checkoutUserInfo();
                }
            }
        });
    }

    private void initViews() {
        getLawyerExpertise();
    }

    private static final int HEADREQUESTCODE = 10058;
    private static final int REQUESTCODE = 10059;
    public static final int CARD_PHOTO_ALBUM_CODE = 111;

    private static final int REQUEST_CAPTURE = 100;
    // 请求相册
    private static final int REQUEST_PICK = 101;
    // 请求截图
    private static final int REQUEST_CROP_PHOTO = 102;
    private File tempFile;

    @OnClick({R.id.rl_avatar_content, R.id.et_description, R.id.rl_address_content, R.id.rl_specialty_content,
            R.id.rl_address, R.id.rl_nickname, R.id.rl_description, R.id.rl_duty_type_content})
    public void Onclick(View view) {
        switch (view.getId()) {
            case R.id.et_description:
            case R.id.rl_description:
                startActivity(new Intent(PersonDataLawyerActivity.this, EditDescriptionActivity.class).putExtra("description", et_description.getText().toString()));
                break;
            case R.id.rl_nickname:
                startActivity(new Intent(PersonDataLawyerActivity.this, EditNickNameActivity.class));
                break;
            case R.id.rl_address:
                startActivity(new Intent(PersonDataLawyerActivity.this, EditAddressActivity.class).putExtra("address",tv_address_detail.getText().toString()));
                break;
            case R.id.rl_avatar_content: // 设置图像

                AndPermission.with(PersonDataLawyerActivity.this)
                        .requestCode(101)
                        .permission(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        .callback(permissionListener)
                        .start();

                break;

            case R.id.rl_address_content:// 设置地址

                final PopupWindowAddress popupWindowAddress = new PopupWindowAddress(PersonDataLawyerActivity.this, "3", true);
                popupWindowAddress.setAddresskListener(new PopupWindowAddress.OnAddressCListener() {
                    @Override
                    public void onClick(String province, String city, String area, String provincesid, String cityid, String areaid) {
                        tv_address.setText(province + "-" + city + "-" + area);
                        updataAddress(areaid);
                        popupWindowAddress.dismiss();
                    }
                });
                popupWindowAddress.showAsDropDown(tv_address);

                break;
            case R.id.rl_specialty_content: // 专长
                publishViewInit(tv_specialty);
                break;

            case R.id.rl_duty_type_content:
                LawyerJobPopupWindow lawyerJobPopupWindow = new LawyerJobPopupWindow(PersonDataLawyerActivity.this);
                lawyerJobPopupWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);
                lawyerJobPopupWindow.setAddresskListener(new LawyerJobPopupWindow.OnAddressCListener() {
                    @Override
                    public void onClick(String job) {
                        tv_duty_type.setText(job);

                        String id = "";

                        if (job.equals("合伙人")) {
                            id = "15";
                        } else if (job.equals("主任")) {
                            id = "13";
                        } else if (job.equals("副主任")) {
                            id = "14";
                        }

                        updataLawyerTitle(id);
                    }
                });
                break;
        }
    }

    private PermissionListener permissionListener = new PermissionListener() {
        @Override
        public void onSucceed(int requestCode, List<String> grantPermissions) {
            switch (requestCode) {
                case 101: {
                    if (AndPermission.hasPermission(PersonDataLawyerActivity.this, Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                        pickImage();
                    } else {
                        //专为小米 设置的
                        Toast.makeText(PersonDataLawyerActivity.this, "我们需要的一些权限被您拒绝或者系统发生错误申请失败，请您到设置页面手动授权，否则功能无法正常使用！", Toast.LENGTH_SHORT).show();
                    }

                    break;
                }
            }
        }

        @Override
        public void onFailed(int requestCode, List<String> deniedPermissions) {
            switch (requestCode) {
                case 101: {
                    Toast.makeText(PersonDataLawyerActivity.this, "获取权限失败", Toast.LENGTH_SHORT).show();
                    break;
                }
                case 102: {
                    Toast.makeText(PersonDataLawyerActivity.this, "获取权限失败", Toast.LENGTH_SHORT).show();
                    break;
                }
            }

            // 用户否勾选了不再提示并且拒绝了权限，那么提示用户到设置中授权。
            if (AndPermission.hasAlwaysDeniedPermission(PersonDataLawyerActivity.this, deniedPermissions)) {
                // 第一种：用默认的提示语。
                AndPermission.defaultSettingDialog(PersonDataLawyerActivity.this, REQUEST_CODE_SETTING).show();

            }
        }
    };

    private void pickImage() {
        MultiImageSelector selector = MultiImageSelector.create();
        selector.showCamera(true);
        selector.single();
        selector.start(PersonDataLawyerActivity.this, 1);
    }

    protected void updataAddress(String areaId) {
        RequestParams params = new RequestParams();
        HttpDataUtils httpDataUtils = new HttpDataUtils(PersonDataLawyerActivity.this);
        params.addHeader("Authorization", PrefsUtils.getString(getApplicationContext(), PrefsUtils.AUTHORIZATION));
        params.addBodyParameter("base_region_id", areaId + "");
        httpDataUtils.sendProgressPost(MyData.CM_USER_DATA_INFO, params);
        httpDataUtils.setSucessBack(new HttpDataUtils.SucessBack() {

            @Override
            public void sucess(ResponseInfo<String> arg0) {
                try {
                    JSONObject object = new JSONObject(arg0.result);
                    String code = object.optString("code");
                    String msg = object.optString("msg");
                    if (code.equals("0")) {
                        ToastUtils.getUtils(PersonDataLawyerActivity.this).show("地址设置成功！");
                    } else {

                    }
                    // ToastUtils.getUtils(getApplicationContext()).show(msg);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    private Uri getUri() {
        return Uri.fromFile(getFile());
    }

    /**
     * 该方法用于获取指定路径 和 名字 的file
     *
     * @return
     */
    private File getFile() {
        File filePath = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM), "Hzzll");
        if (!filePath.exists()) {
            filePath.mkdirs();
        }
        // 将图片保存的名字设置为当前拍照的时间
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        String name = format.format(new Date());
        File file = new File(filePath.getPath() + name + ".jpg");
        photoName = filePath.getPath() + name + ".jpg";
        return file;
    }

    private String setSpecialty() {
        String id = "";
        for (int i = 0; i < sortList.size(); i++) {
            if (sortList.get(i).isSelect()) {
                id = id + sortList.get(i).getId() + ",";
            }
        }
        if (id.equals("")) {
            return id;
        } else {
            return id = id.substring(0, id.length() - 1);
        }

    }

    /**
     * 专长
     *
     * @param
     */
    private void publishViewInit(final TextView tv) {
        AlertDialog.Builder builder = new AlertDialog.Builder(PersonDataLawyerActivity.this);
        View selectview = getLayoutInflater().inflate(R.layout.selectview_dialog, null);
        ListView lv_select = (ListView) selectview.findViewById(R.id.lv_select);
        String sp = tv.getText().toString();
        for (int i = 0; i < sortList.size(); i++) {
            if (sp.contains(sortList.get(i).getTitle())) {
                sortList.get(i).setSelect(true);
            }
        }
        LvAdapter lvAdapter = new LvAdapter(sortList);
        lv_select.setAdapter(lvAdapter);
        builder.setView(selectview);
        builder.setPositiveButton("确定", null);
        builder.setNegativeButton("返回", new DialogInterface.OnClickListener() {// 添加返回按钮
            @Override
            public void onClick(DialogInterface dialog, int which) {// 响应事件
                // TODO Auto-generated method stub
            }
        });
        final AlertDialog dialog = builder.create();
        dialog.show();// 在按键响应事件中显示此对话框

        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                String specialities = "";
                String id = "";
                int num = 0;
                for (int i = 0; i < sortList.size(); i++) {
                    if (sortList.get(i).isSelect()) {
                        num++;
                    }
                }
                if (num == 0) {
                    ToastUtils.getUtils(PersonDataLawyerActivity.this).show("请选择专长！");
                } else if (num > 5) {
                    ToastUtils.getUtils(PersonDataLawyerActivity.this).show("专长最多只能设置五条！");
                } else {
                    for (int i = 0; i < sortList.size(); i++) {
                        if (sortList.get(i).isSelect()) {
                            specialities = specialities + sortList.get(i).getTitle() + ",";
                            id = id + sortList.get(i).getId() + ",";
                        }
                    }

                    id = id.substring(0, id.length() - 1);
                    specialities = specialities.substring(0, specialities.length() - 1);
                    tv.setText(specialities);

                    updataSpeciality(id, dialog);

                }
            }
        });
    }

    protected void updataSpeciality(final String spec, final AlertDialog dialog) {
        RequestParams params = new RequestParams();
        HttpDataUtils httpDataUtils = new HttpDataUtils(PersonDataLawyerActivity.this);
        params.addHeader("Authorization", PrefsUtils.getString(getApplicationContext(), PrefsUtils.AUTHORIZATION));
        params.addBodyParameter("lawyer_secpical", spec);
        httpDataUtils.sendProgressPost(MyData.CM_USER_DATA_INFO, params);
        httpDataUtils.setSucessBack(new HttpDataUtils.SucessBack() {

            @Override
            public void sucess(ResponseInfo<String> arg0) {
                try {
                    JSONObject object = new JSONObject(arg0.result);
                    String code = object.optString("code");
                    String msg = object.optString("msg");
                    if (code.equals("0")) {
                        ToastUtils.getUtils(PersonDataLawyerActivity.this).show("专长设置成功！");
                        dialog.dismiss();
                    } else {

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    protected void updataLawyerTitle(final String spec) {
        RequestParams params = new RequestParams();
        HttpDataUtils httpDataUtils = new HttpDataUtils(PersonDataLawyerActivity.this);
        params.addHeader("Authorization", PrefsUtils.getString(getApplicationContext(), PrefsUtils.AUTHORIZATION));
        params.addBodyParameter("lawyer_title", spec);
        httpDataUtils.sendProgressPost(MyData.CM_USER_DATA_INFO, params);
        httpDataUtils.setSucessBack(new HttpDataUtils.SucessBack() {

            @Override
            public void sucess(ResponseInfo<String> arg0) {
                try {
                    JSONObject object = new JSONObject(arg0.result);
                    String code = object.optString("code");
                    String msg = object.optString("message");
                    if (code.equals("0")) {
                        ToastUtils.getUtils(PersonDataLawyerActivity.this).show("职务设置成功。");
                    } else {
                        tv_duty_type.setText("");
                        ToastUtils.getUtils(PersonDataLawyerActivity.this).show(msg);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    /**
     * 弹出框listView的适配器
     *
     * @author
     */

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
            // TODO Auto-generated method stub
            ViewHolder holder = null;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = LayoutInflater.from(context).inflate(R.layout.item_lv_cause_of_action, null);
                holder.checkbox = (CheckBox) convertView.findViewById(R.id.cb_cause_of_action);
                // holder.checkbox.setTag(position);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.checkbox.setText(list.get(position).getTitle());
            int i = 0;

            holder.checkbox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
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

    private List<SortEntity> sortList;

    protected void updataHeadAvatar(final String url) {
        RequestParams params = new RequestParams();
        HttpDataUtils httpDataUtils = new HttpDataUtils(PersonDataLawyerActivity.this);
        params.addHeader("Authorization", PrefsUtils.getString(getApplicationContext(), PrefsUtils.AUTHORIZATION));
        params.addBodyParameter("avatar", new File(url));
        httpDataUtils.sendProgressPost(MyData.CM_USER_DATA_INFO, params);
        httpDataUtils.setSucessBack(new HttpDataUtils.SucessBack() {

            @Override
            public void sucess(ResponseInfo<String> arg0) {
                try {
                    JSONObject object = new JSONObject(arg0.result);
                    String code = object.optString("code");
                    String msg = object.optString("msg");
                    if (code.equals("0")) {

                        Bitmap bitMap = BitmapFactory.decodeFile(url);
                        iv_avatar.setImageBitmap(bitMap);
                        ToastUtils.getUtils(PersonDataLawyerActivity.this).show("头像设置成功！");

                        checkoutUserInfo();

                    } else {

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (data != null) {

                String photo = data.getStringArrayListExtra(MultiImageSelector.EXTRA_RESULT).get(0);
                Uri uri = Uri.fromFile(new File(photo));
                gotoClipActivity(uri);
            }
        } else if (requestCode == REQUEST_CROP_PHOTO) {
            final Uri uri = data.getData();
            if (uri == null) {
                return;
            }
            String cropImagePath = getRealFilePathFromUri(getApplicationContext(), uri);

            updataHeadAvatar(cropImagePath);

        }

        super.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    protected void onResume() {
        getLawyerExpertise();
        super.onResume();
    }

    public void gotoClipActivity(Uri uri) {
        if (uri == null) {
            return;
        }
        Intent intent = new Intent();
        intent.setClass(PersonDataLawyerActivity.this, ClipImageActivity.class);
        intent.putExtra("type", 2);
        intent.setData(uri);
        startActivityForResult(intent, REQUEST_CROP_PHOTO);
    }

    /**
     * 创建调用系统照相机待存储的临时文件
     *
     * @param savedInstanceState
     */
    private void createCameraTempFile(Bundle savedInstanceState) {
        if (savedInstanceState != null && savedInstanceState.containsKey("tempFile")) {
            tempFile = (File) savedInstanceState.getSerializable("tempFile");
        } else {
            tempFile = new File(checkDirPath(Environment.getExternalStorageDirectory().getPath() + "/image/"),
                    System.currentTimeMillis() + ".jpg");
        }
    }

    /**
     * 检查文件是否存在
     */
    private static String checkDirPath(String dirPath) {
        if (TextUtils.isEmpty(dirPath)) {
            return "";
        }
        File dir = new File(dirPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        return dirPath;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("tempFile", tempFile);
    }

    /**
     * Try to return the absolute file path from the given Uri 兼容了file:///开头的 和
     * content://开头的情况
     *
     * @param context
     * @param uri
     * @return the file path or null
     */
    public static String getRealFilePathFromUri(final Context context, final Uri uri) {
        if (null == uri)
            return null;
        final String scheme = uri.getScheme();
        String data = null;
        if (scheme == null)
            data = uri.getPath();
        else if (ContentResolver.SCHEME_FILE.equals(scheme)) {
            data = uri.getPath();
        } else if (ContentResolver.SCHEME_CONTENT.equals(scheme)) {
            Cursor cursor = context.getContentResolver().query(uri,
                    new String[]{MediaStore.Images.ImageColumns.DATA}, null, null, null);
            if (null != cursor) {
                if (cursor.moveToFirst()) {
                    int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                    if (index > -1) {
                        data = cursor.getString(index);
                    }
                }
                cursor.close();
            }
        }
        return data;
    }
}
