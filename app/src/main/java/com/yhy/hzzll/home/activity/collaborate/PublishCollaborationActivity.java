package com.yhy.hzzll.home.activity.collaborate;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.Gravity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionListener;
import com.yhy.hzzll.R;
import com.yhy.hzzll.entity.TypeEntity;
import com.yhy.hzzll.framework.MyData;
import com.yhy.hzzll.home.activity.ReadAgreementActivity;
import com.yhy.hzzll.interfacelib.Connect;
import com.yhy.hzzll.message.WebviewActivity;
import com.yhy.hzzll.mian.activity.BaseActivity;
import com.yhy.hzzll.utils.CodeUtils;
import com.yhy.hzzll.utils.HttpDataUtils;
import com.yhy.hzzll.utils.JSONTool;
import com.yhy.hzzll.utils.PrefsUtils;
import com.yhy.hzzll.utils.StringUtil;
import com.yhy.hzzll.utils.ToastUtils;
import com.yhy.hzzll.view.AdressPopupWindow;
import com.yhy.hzzll.view.DialogLoading;
import com.yhy.hzzll.view.PickPhotoView;
import com.yhy.hzzll.view.PickPhotoView.OnItemClickedLisener;
import com.yhy.hzzll.view.PopupWindowTime;
import com.yhy.hzzll.view.PopupwindowcooperateType1;
import com.yhy.hzzll.view.PopupwindowpublishType2;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import am.widget.multiactiontextview.MultiActionClickableSpan;
import am.widget.multiactiontextview.MultiActionTextView;
import me.nereo.multi_image_selector.MultiImageSelector;

/**
 * 发布 律师协作
 *
 * @author wangyang
 */
public class PublishCollaborationActivity extends BaseActivity implements OnItemClickedLisener {

    /**
     * 标题
     */
    @ViewInject(R.id.et_title)
    private EditText et_title;
    /**
     * 酬金
     */
    @ViewInject(R.id.et_price)
    private EditText et_price;
    /**
     * 协作类型
     */
    @ViewInject(R.id.tv_xzlx)
    private TextView tv_xzlx;
    /**
     * 协作类型2
     */
    @ViewInject(R.id.tv_xzlx2)
    private TextView tv_xzlx2;
    /**
     * 协作完成地
     */
    @ViewInject(R.id.tv_xzwcd)
    private TextView tv_xzwcd;
    /**
     * 协作完成日期
     */
    @ViewInject(R.id.tv_xwwcrq)
    private TextView tv_xwwcrq;
    /**
     * 详请
     */
    @ViewInject(R.id.et_details)
    private EditText et_details;
    /**
     * 添加图片
     */
    @ViewInject(R.id.ll_photo)
    private PickPhotoView ll_photo;
    /**
     * 发布方姓名
     */
    @ViewInject(R.id.et_fbf)
    private TextView et_fbf;
    /**
     * 发布方电话
     */
    @ViewInject(R.id.et_mobile)
    private EditText et_mobile;
    /**
     * 验证码
     */
    @ViewInject(R.id.et_validCode)
    private EditText et_validCode;
    /**
     * 是否同意协议。
     */
    @ViewInject(R.id.rb_btn)
    private CheckBox rb_btn;
    /**
     * 发布按钮
     */
    @ViewInject(R.id.tv_publish)
    private TextView tv_publish;

//	// 使用协议
//	@ViewInject(R.id.tv_use_agreement)
//	private TextView tv_use_agreement;
//	// 隐私声明
//	@ViewInject(R.id.tv_privacy)
//	private TextView tv_privacy;
//	// 法律声明
//	@ViewInject(R.id.tv_law)
//	private TextView tv_law;

    /**
     * 验证码
     */
    @ViewInject(R.id.iv_validCode)
    private ImageView iv_validCode;

    /**
     * 协作类型1级列表
     */
    private List<TypeEntity> publishType1List = new ArrayList<TypeEntity>();
    /**
     * 协作类型2级列表
     */
    private List<TypeEntity> publishType2List = new ArrayList<TypeEntity>();

    /**
     * 一级列表name
     */
    public final static String PUBLISHTYPE1NAME = "publishtype1name";
    /**
     * 二级列表name
     */
    public final static String PUBLISHTYPE2NAME = "publishtype2name";

    /**
     * 一级列表ID
     */
    public static String screentype1id1 = "";
    /**
     * 二级列表ID
     */
    private String screentype2id1 = "";

    /**
     * 省市区名称
     */
    private String provinceName = "";
    private String cityName = "";
    private String areaName = "";

    /**
     * 一级列表名称
     */
    private String type1Name = "";
    /**
     * 二级列表名称
     */
    private String type2Name = "";

    /**
     * 图片resultcode
     */
    public static final int CARD_PHOTO_ALBUM_CODE = 1090;

    /**
     * 图片集合
     */
    public static ArrayList<String> photolist = new ArrayList<String>();

    /**
     * 图片组
     */
    private ArrayList<String> cardImgUrls = new ArrayList<String>();
    /**
     * 图片
     */
    private String pathArr = "";

    private DialogLoading loading;

    /**
     * 记录上传到哪一张图片
     */
    private int index = 0;

    // 省
    private String province;
    // 市
    private String city;
    // 区
    private String area;
    // 协作一级类型
    private String zqlx;
    // 协作二级类型
    private String zqdb;
    // hash值(通过API生成)
    private String hash;
    // 协作标题
    private String title;
    // 协作金额
    private String zqprice;
    // 协作内容
    private String zqcontent;
    // 期望完成时间
    private String hopedate;
    // 协作联系人名称
    private String name;
    // 协作联系人电话
    private String mobile;
    private String photoName;
    private ArrayList<String> arrProvinces = new ArrayList<String>();
    private View view;

    @ViewInject(R.id.tv_read_action)
    MultiActionTextView tv_read_action;


    public static PublishCollaborationActivity instance = null;
    private static final int REQUEST_CODE_SETTING = 300;

    @Override
    protected void onCreate(Bundle arg0) {
        // TODO Auto-generated method stub
        view = getLayoutInflater().inflate(R.layout.activity_publish_collaboration, null);
        setContentView(view);
        super.onCreate(arg0);
        loading = new DialogLoading();
        ViewUtils.inject(this);
        getHashcode();
        viewInit();
        instance = this;
//		et_title.setText("测试");
    }

    /**
     * 获取hash
     */
    private void getHashcode() {
        RequestParams params = new RequestParams();
        httpDataUtils.sendProgressGet(MyData.INDEXCREATEHASHCODE, params);
        httpDataUtils.setSucessBack(new HttpDataUtils.SucessBack() {
            @Override
            public void sucess(ResponseInfo<String> arg0) {
                if (httpDataUtils.code(arg0.result)) {
                    PrefsUtils.saveString(context, PrefsUtils.HASHCODE, httpDataUtils.data(arg0.result));
                }
            }
        });
    }

    MultiActionClickableSpan.OnTextClickedListener listener1 = new MultiActionClickableSpan.OnTextClickedListener() {
        @Override
        public void onTextClicked(View view, MultiActionClickableSpan span) {
            startActivity(new Intent().putExtra("title", "使用协议")
//					.putExtra("url", "http://mobile.hzzll.com/n/0914/m.html")
                    .putExtra("url", MyData.AGREEMENT_OF_USAGE)
                    .setClass(PublishCollaborationActivity.this, WebviewActivity.class));
        }
    };

    MultiActionClickableSpan.OnTextClickedListener listener2 = new MultiActionClickableSpan.OnTextClickedListener() {
        @Override
        public void onTextClicked(View view, MultiActionClickableSpan span) {
            startActivity(new Intent().putExtra("title", "隐私声明")
                    .putExtra("url", MyData.PRIVACY_STATEMENT)
                    .setClass(PublishCollaborationActivity.this, WebviewActivity.class));
        }
    };

    MultiActionClickableSpan.OnTextClickedListener listener3 = new MultiActionClickableSpan.OnTextClickedListener() {
        @Override
        public void onTextClicked(View view, MultiActionClickableSpan span) {
            startActivity(new Intent().putExtra("title", "法律声明")
                    .putExtra("url", MyData.LEGAL_DISCLAIMER)
                    .setClass(PublishCollaborationActivity.this, WebviewActivity.class));
        }
    };

    private void viewInit() {

        String text = "阅读并接受《私律使用协议》、《隐私声明》及《法律声明》";

        MultiActionClickableSpan action1 = new MultiActionClickableSpan(6, 12, R.color.textbule, true, false, listener1);
        MultiActionClickableSpan action2 = new MultiActionClickableSpan(15, 20, R.color.textbule, false, true, listener2);
        MultiActionClickableSpan action3 = new MultiActionClickableSpan(22, 26, R.color.textbule, false, true, listener3);
        tv_read_action.setText(text, action1, action2, action3);

        et_fbf.setText(PrefsUtils.getString(PublishCollaborationActivity.this, PrefsUtils.UNAME));

        et_mobile.setText(PrefsUtils.getString(PublishCollaborationActivity.this, PrefsUtils.UPHONE));

        rb_btn.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton arg0, boolean check) {
                if (check) {
                    tv_publish.setEnabled(true);
                } else {
                    tv_publish.setEnabled(false);
                }
            }
        });
        photolist.clear();
        ll_photo.addViewed(cardImgUrls);
        ll_photo.setOnItemClickedLisener(this);
        /** 获取验证码图片 */
        iv_validCode.setImageBitmap(CodeUtils.getInstance().createBitmap());
    }

    @OnClick({R.id.tv_xzlx, R.id.tv_xzlx2, R.id.tv_xzwcd, R.id.tv_xwwcrq, R.id.ll_photo, R.id.iv_validCode,
            R.id.tv_publish, R.id.tv_read})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_read:
                startActivity(new Intent().setClass(PublishCollaborationActivity.this, ReadAgreementActivity.class));
                break;
//		case R.id.tv_privacy:
//			startActivity(new Intent(PublishCollaborationActivity.this,WebviewActivity.class).putExtra("title", "隐私声明").putExtra("url", MyData.PRIVACY_STATEMENT));
//			break;
//		case R.id.tv_law:
//			startActivity(new Intent(PublishCollaborationActivity.this,WebviewActivity.class).putExtra("title", "法律声明").putExtra("url", MyData.LEGAL_DISCLAIMER));
//			break;
            case R.id.tv_xzlx:// 协作类型1
                publishType1List.clear();
                PopupwindowcooperateType1 type1 = new PopupwindowcooperateType1(this, publishType1List);
                type1.showAtLocation(view, Gravity.BOTTOM, 0, 0);
                type1.setAddresskListener(new PopupwindowcooperateType1.OnAddressCListener() {
                    @Override
                    public void onClick(String job) {
                        type1Name = job;
                        tv_xzlx.setText(job);
                        if (!job.equals(PrefsUtils.getString(context, PUBLISHTYPE1NAME))) {
                            tv_xzlx2.setText("");
                        }

                        if (job.equals("全部")) {
                            screentype1id1 = "";
                        } else {
                            for (int i = 0; i < publishType1List.size(); i++) {
                                if (publishType1List.get(i).getName().equals(job)) {
                                    screentype1id1 = publishType1List.get(i).getId();
                                }
                            }
                        }
                    }
                });
                break;
            case R.id.tv_xzlx2:// 协作类型2

                if (tv_xzlx.getText().toString().equals("")) {
                    ToastUtils.getUtils(context).show("请选择协作类型");
                    return;
                }

                publishType2List.clear();
                PopupwindowpublishType2 type2 = new PopupwindowpublishType2(this, publishType2List);
                type2.showAtLocation(view, Gravity.BOTTOM, 0, 0);
                type2.setAddresskListener(new PopupwindowpublishType2.OnAddressCListener() {
                    @Override
                    public void onClick(String job) {
                        tv_xzlx2.setText(job);
                        type2Name = job;
                        if (job.equals("全部")) {
                            screentype2id1 = "";
                        } else {
                            for (int i = 0; i < publishType2List.size(); i++) {
                                if (publishType2List.get(i).getName().equals(job)) {
                                    screentype2id1 = publishType2List.get(i).getId();
                                }
                            }
                        }
                    }
                });
                break;
            case R.id.tv_xzwcd:// 协作完成地
                arrProvinces.add("全国");
                final AdressPopupWindow popupWindow = new AdressPopupWindow(this, arrProvinces, true);
                popupWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);
                popupWindow.setAddresskListener(new AdressPopupWindow.OnAddressCListener() {
                    @Override
                    public void onClick(String province, String city, String area, int provinceid, int cityid, int areaid) {
                        provinceName = province;
                        cityName = city;
                        areaName = area;
                        if (province.equals("全国")) {
                            tv_xzwcd.setText("全国");
                            PublishCollaborationActivity.this.province = "-1";
                            PublishCollaborationActivity.this.city = "";
                            PublishCollaborationActivity.this.area = "";
                        } else if (city.equals("不限")) {
                            tv_xzwcd.setText(province);
                            PublishCollaborationActivity.this.province = provinceid + "";
                            PublishCollaborationActivity.this.city = "";
                            PublishCollaborationActivity.this.area = "";
                        } else if (area.equals("不限")) {
                            tv_xzwcd.setText(province + "-" + city);
                            PublishCollaborationActivity.this.province = provinceid + "";
                            PublishCollaborationActivity.this.city = cityid + "";
                            PublishCollaborationActivity.this.area = "";
                        } else {
                            tv_xzwcd.setText(province + "-" + city + "-" + area);
                            PublishCollaborationActivity.this.province = provinceid + "";
                            PublishCollaborationActivity.this.city = cityid + "";
                            PublishCollaborationActivity.this.area = areaid + "";
                        }
                        popupWindow.dismiss();
                    }
                });

                break;
            case R.id.tv_xwwcrq:// 协作完成日期
                PopupWindowTime windowTime = new PopupWindowTime(this, new Connect() {
                    @Override
                    public void text(String text) {
                        tv_xwwcrq.setText(text);
                    }
                }, true);
                windowTime.showAtLocation(view, Gravity.BOTTOM, 0, 0);
                break;
            case R.id.iv_validCode:// 验证码
                /** 获取验证码图片 */
                iv_validCode.setImageBitmap(CodeUtils.getInstance().createBitmap());
                break;
            case R.id.tv_publish:// 发布
                if (et_validCode.getText().toString().trim().isEmpty()) {
                    ToastUtils.getUtils(context).show("请填写验证码");
                    return;
                }
                if (CodeUtils.getInstance().equesCode(et_validCode.getText().toString())) {
                    publish();// 发布预览
                } else {
                    ToastUtils.getUtils(context).show("验证码错误！");
                }
                break;
        }
    }

    /**
     * 存储信息跳转到发布预览
     */
    private void publish() {
        hash = PrefsUtils.getString(context, PrefsUtils.HASHCODE);
        zqlx = screentype1id1;
        zqdb = screentype2id1;
        title = et_title.getText().toString().trim();
        zqprice = et_price.getText().toString().trim();
        zqcontent = et_details.getText().toString().trim();
        hopedate = tv_xwwcrq.getText().toString().trim();
        name = et_fbf.getText().toString().trim();
        mobile = et_mobile.getText().toString().trim();

        if (title.isEmpty()) {
            ToastUtils.getUtils(context).show("协作标题不能为空");
            return;
        }

        if (!StringUtil.isContainChinese(title)) {
            ToastUtils.getUtils(context).show("标题中必须包含中文");
            return;
        }

        if (zqprice.isEmpty()) {
            ToastUtils.getUtils(context).show("协作酬金不能为空");
            return;
        }

        if (zqlx.isEmpty()) {
            ToastUtils.getUtils(context).show("协作类型不能为空");
            return;
        }

        if (zqdb.isEmpty()) {
            ToastUtils.getUtils(context).show("协作子类型不能为空");
            return;
        }

        if (zqcontent.isEmpty()) {
            ToastUtils.getUtils(context).show("协作内容不能为空");
            return;
        }

        if (tv_xzwcd.getText().toString().equals("")) {
            ToastUtils.getUtils(context).show("请选择协作地区");
            return;
        }

        if (hopedate.equals("")) {
            ToastUtils.getUtils(context).show("选择期望完成时间");
            return;
        }

        Intent intent = new Intent();
        intent.putExtra("hash", hash);// hash
        intent.putExtra("province", province);// 省ID
        intent.putExtra("city", city);// 市ID
        intent.putExtra("area", area);// 区ID
        intent.putExtra("title", title);// 协作标题
        intent.putExtra("zqlx", zqlx);// 协作一级类型
        intent.putExtra("zqdb", zqdb);// 协作二级类型
        intent.putExtra("zqprice", zqprice);// 协作金额
        intent.putExtra("zqcontent", zqcontent);// 协作内容
        intent.putExtra("hopedate", hopedate);// 期望完成时间
        intent.putExtra("name", name);// 发布人
        intent.putExtra("mobile", mobile);// 发布人联系方式
        /** 省市区名称 */
        intent.putExtra("provinceName", provinceName);
        intent.putExtra("cityName", cityName);
        intent.putExtra("areaName", areaName);
        /** 一级列表名称 */
        intent.putExtra("type1Name", type1Name);
        /** 二级列表名称 */
        intent.putExtra("type2Name", type2Name);
        startActivity(intent.setClass(context, PublishCollaboratePreviewActivity.class));
//		finish();
    }

    @Override
    public void onItemClick(int position, View view) {
        // TODO Auto-generated method stub
    }

    private static final int HEADREQUESTCODE = 10058;

    int temp = 0;

    @Override
    public void onAddClick(int position, View view) {
        if (cardImgUrls != null) {
            cardImgUrls.clear();
        }
        if (cardImgUrls != null && cardImgUrls.size() != 0) {
            cardImgUrls.clear();
        }
//		PopupwindowPickPhoto pickPhoto = new PopupwindowPickPhoto(this, new PopupwindowPickPhoto.Click() {
//			@Override
//			public void index(int index) {
//				switch (index) {
//				case 1:// 相册
//						// urlPathList.clear();
//					startActivityForResult(
//							new Intent().putExtra("code", CARD_PHOTO_ALBUM_CODE).putExtra("single", 1)
//									.setClass(PublishCollaborationActivity.this, PhotoAlbumActivity.class),
//							CARD_PHOTO_ALBUM_CODE);
//					break;
//				case 2:// 相机
//					try {
//						Intent intent = new Intent();
//						intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
//						intent.putExtra(MediaStore.EXTRA_OUTPUT, getUri());
//						startActivityForResult(intent, HEADREQUESTCODE);
//					} catch (Exception e) {
//						// TODO: handle exception
//					}
//					break;
//				}
//			}
//		});
//		pickPhoto.showAtLocation(this.view, Gravity.BOTTOM, 0, 0);

        AndPermission.with(PublishCollaborationActivity.this)
                .requestCode(102)
                .permission(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .callback(permissionListener)
                .start();


    }

    /**
     * 回调监听。
     */
    private PermissionListener permissionListener = new PermissionListener() {
        @Override
        public void onSucceed(int requestCode, List<String> grantPermissions) {
            switch (requestCode) {

                case 102: {
                    if (AndPermission.hasPermission(PublishCollaborationActivity.this, Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                        pickImage2();
                    } else {
                        //专为小米 设置的
                        Toast.makeText(PublishCollaborationActivity.this, "我们需要的一些权限被您拒绝或者系统发生错误申请失败，请您到设置页面手动授权，否则功能无法正常使用！", Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(PublishCollaborationActivity.this, "获取权限失败", Toast.LENGTH_SHORT).show();
                    break;
                }
                case 102: {
                    Toast.makeText(PublishCollaborationActivity.this, "获取权限失败", Toast.LENGTH_SHORT).show();
                    break;
                }
            }

            // 用户否勾选了不再提示并且拒绝了权限，那么提示用户到设置中授权。
            if (AndPermission.hasAlwaysDeniedPermission(PublishCollaborationActivity.this, deniedPermissions)) {
                // 第一种：用默认的提示语。
                AndPermission.defaultSettingDialog(PublishCollaborationActivity.this, REQUEST_CODE_SETTING).show();

            }
        }
    };

    private void pickImage2() {
        MultiImageSelector selector = MultiImageSelector.create();
        selector.count(5);
        selector.showCamera(true);
        selector.multi();
        selector.origin(photolist);
        selector.start(PublishCollaborationActivity.this, 2);
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

    // @Override
    // public void onAddClick(int position, View view) {
    // if (cardImgUrls != null) {
    // cardImgUrls.clear();
    // }
    // if (cardImgUrls != null && cardImgUrls.size() != 0) {
    // cardImgUrls.clear();
    // }
    // startActivityForResult(
    // new Intent().putExtra("code", CARD_PHOTO_ALBUM_CODE)
    // .putExtra("single", 1)
    // .setClass(this, PhotoAlbumActivity.class),
    // CARD_PHOTO_ALBUM_CODE);
    // }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 2) {
            if (data != null) {
                photolist.clear();
                photolist.addAll(data.getStringArrayListExtra(MultiImageSelector.EXTRA_RESULT));
                ll_photo.addViewed(photolist);

            }
        }


//		if (requestCode == HEADREQUESTCODE) {
//			if (photoName != null) {
//				long l = FileUtils.getAutoFilesSize(photoName);
//				if (l != 0) {
//					photolist.add(photoName);
//					upLoadFile(photoName, true);
//					ll_photo.addViewed(photolist);
//				}
//			}
//		} else if (resultCode == CARD_PHOTO_ALBUM_CODE) {
//			// if (photolist.size() != 0) {
//			ll_photo.addViewed(photolist);
//			for (int i = 0; i < photolist.size(); i++) {
//				index++;// 记录当前传到哪一张
//				upLoadFile(photolist.get(i), false);
//			}
//			// }
//		}
        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * 上传文件
     */
    private void upLoadFile(String filePath, final boolean ispicktrue) {
        String times = System.currentTimeMillis() + "";
        RequestParams params = new RequestParams();
        params.addHeader("Authorization", PrefsUtils.getString(context, PrefsUtils.ACCESSKEY));
        params.addBodyParameter("name", times);
        params.addBodyParameter("hash", PrefsUtils.getString(context, PrefsUtils.HASHCODE));
        params.addBodyParameter("reType", "json");
        params.addBodyParameter(times, new File(filePath));
        httpDataUtils.sendProgressPost(MyData.FILEUPLOAD, params);
//        if (!loading.isShow()) {
//            loading.showDialog(this);
//        }
        httpDataUtils.setSucessBack(new HttpDataUtils.SucessBack() {
            @Override
            public void sucess(ResponseInfo<String> arg0) {
                JSONObject objet = JSONTool.getJsonObjectOfStr(arg0.result);
                if (httpDataUtils.code(arg0.result)) {
//					JSONArray array = JSONTool.getJsonArray(objet, "data");
//					for (int i = 0; i < array.length(); i++) {
                    try {
//							String url = array.getString(i);
//							if (url.contains("file_b")) {

                        JSONObject data = objet.getJSONObject("data");
                        String url = data.getString("imgurl");
//								url = url.substring(1, url.length());
                        cardImgUrls.add(url);
                        pathArr = pathArr + url + "-";
                        pathArr.substring(0, pathArr.length() - 1);
                        if (ispicktrue) {
//                            loading.dismissDialog();
                        } else {
                            if (index == photolist.size()) {
                                loading.dismissDialog();
                                index = 0;
                            }
                        }
//							}
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
//					}
                } else {
                    httpDataUtils.showMsg(arg0.result);
                }
            }
        });

        httpDataUtils.setFailBack(new HttpDataUtils.FailBack() {
            @Override
            public void fail(String msg) {
                loading.dismissDialog();
            }
        });
    }
}
