package com.yhy.hzzll.mian.activity;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionListener;
import com.yhy.hzzll.R;
import com.yhy.hzzll.adapter.GridViewAdapter;
import com.yhy.hzzll.entity.PhotoEntity;
import com.yhy.hzzll.framework.MyData;
import com.yhy.hzzll.my.activity.ClipImageActivity;
import com.yhy.hzzll.utils.HttpDataUtils;
import com.yhy.hzzll.utils.LogUtils;
import com.yhy.hzzll.utils.PrefsUtils;
import com.yhy.hzzll.utils.ToastUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import me.nereo.multi_image_selector.MultiImageSelector;


public class RegisterThirdActivity extends BaseActivity {

    public static List<String> photolist = new ArrayList<>();
    // 请求截图
    private static final int REQUEST_CROP_PHOTO = 102;
    private static final int REQUEST_CODE_SETTING = 300;

    @ViewInject(R.id.iv_headimg)
    private ImageView iv_headimg;

    @ViewInject(R.id.gridview)
    GridView gridview;
    private GridViewAdapter gridViewAdapter;

    List<PhotoEntity> photoEntities = new ArrayList<>();
    ArrayList<String> imgList = new ArrayList<String>();

    String cropImagePath;



    @OnClick({R.id.tv_next, R.id.iv_headimg,R.id.tv_home})
    public void onclick(View view) {
        switch (view.getId()) {
            case R.id.tv_home:
                startActivity(new Intent().putExtra("tab", 0).setClass(getApplicationContext(), MainActivity.class));
                break;

            case R.id.tv_next:

                if (cropImagePath == null) {
                    ToastUtils.getUtils(RegisterThirdActivity.this).show("请设置头像");
                    return;
                }

                if (imgList.size() == 0) {
                    ToastUtils.getUtils(RegisterThirdActivity.this).show("请上传执业证附件");
                    return;
                }

                uploadAuthFile();

                break;

            case R.id.iv_headimg:

                AndPermission.with(RegisterThirdActivity.this)
                        .requestCode(101)
                        .permission(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        .callback(permissionListener)
                        .start();

                break;
        }
    }


    /**
     * 回调监听。
     */
    private PermissionListener permissionListener = new PermissionListener() {
        @Override
        public void onSucceed(int requestCode, List<String> grantPermissions) {
            switch (requestCode) {
                case 101: {
                    if (AndPermission.hasPermission(RegisterThirdActivity.this, Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                        pickImage();
                    } else {
                        Toast.makeText(RegisterThirdActivity.this, "我们需要的一些权限被您拒绝或者系统发生错误申请失败，请您到设置页面手动授权，否则功能无法正常使用！", Toast.LENGTH_SHORT).show();
                    }

                    break;
                }
                case 102: {
                    if (AndPermission.hasPermission(RegisterThirdActivity.this, Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                        pickImage2();
                    } else {
                        Toast.makeText(RegisterThirdActivity.this, "我们需要的一些权限被您拒绝或者系统发生错误申请失败，请您到设置页面手动授权，否则功能无法正常使用！", Toast.LENGTH_SHORT).show();
                    }
                    break;
                }
            }
        }

        @Override
        public void onFailed(int requestCode, List<String> deniedPermissions) {
            switch (requestCode) {
                case 101: {
                    Toast.makeText(RegisterThirdActivity.this, "获取权限失败", Toast.LENGTH_SHORT).show();
                    break;
                }
                case 102: {
                    Toast.makeText(RegisterThirdActivity.this, "获取权限失败", Toast.LENGTH_SHORT).show();
                    break;
                }
            }

        }
    };



    private void uploadAuthFile() {

        RequestParams params = new RequestParams();

        params.addHeader("Authorization", PrefsUtils.getString(RegisterThirdActivity.this, PrefsUtils.AUTHORIZATION));
        LogUtils.logE(cropImagePath);
        params.addBodyParameter("avatar", new File(cropImagePath));

        for (int i = 0; i < imgList.size(); i++) {
            LogUtils.logE(imgList.get(i));
            params.addBodyParameter("lawyer_license_photo["+i+"]", new File(imgList.get(i)));
        }

        httpDataUtils.sendProgressPost( MyData.REALNAME, params);
        httpDataUtils.setSucessBack(new HttpDataUtils.SucessBack() {
            @Override
            public void sucess(ResponseInfo<String> arg0) {
                if (httpDataUtils.code(arg0.result)) {
                    startActivity(new Intent(RegisterThirdActivity.this,RegisterfourthActivity.class));
                } else {
                    httpDataUtils.showMsgNew(arg0.result);
                }
            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_third);
        ViewUtils.inject(this);
        init2();
    }
    private void init2() {

        gridViewAdapter = new GridViewAdapter(RegisterThirdActivity.this, photoEntities, delete);
        gridview.setAdapter(gridViewAdapter);
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == parent.getChildCount() - 1) {
                    AndPermission.with(RegisterThirdActivity.this)
                            .requestCode(102)
                            .permission(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                            .callback(permissionListener)
                            .start();
                } else {

                }
            }
        });

    }

    private View.OnClickListener delete = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            final int position = (int) v.getTag();
            imgList.remove(position);
            String url = photoEntities.get(position).getImgpath();
                photoEntities.remove(position);
                gridViewAdapter = new GridViewAdapter(RegisterThirdActivity.this, photoEntities, delete);
                gridview.setAdapter(gridViewAdapter);
        }
    };

    private void pickImage2() {
        MultiImageSelector selector = MultiImageSelector.create();
        selector.count(5);
        selector.showCamera(true);
        selector.multi();
        selector.origin(imgList);
        selector.start(RegisterThirdActivity.this,2);
    }


    private void pickImage() {
        MultiImageSelector selector = MultiImageSelector.create();
        selector.showCamera(true);
        selector.single();
        selector.start(RegisterThirdActivity.this, 1);
    }


    public void gotoClipActivity(Uri uri) {
        if (uri == null) {
            return;
        }
        Intent intent = new Intent();
        intent.setClass(RegisterThirdActivity.this, ClipImageActivity.class);
        intent.putExtra("type", 2);
        intent.setData(uri);
        startActivityForResult(intent, REQUEST_CROP_PHOTO);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if (data != null) {
                photolist.clear();
                photolist.addAll(data.getStringArrayListExtra(MultiImageSelector.EXTRA_RESULT));
                Uri uri = Uri.fromFile(new File(photolist.get(0)));
                gotoClipActivity(uri);
            }
        } else if (requestCode == REQUEST_CROP_PHOTO) {

            final Uri uri = data.getData();
            if (uri == null) {
                return;
            }
            cropImagePath = getRealFilePathFromUri(RegisterThirdActivity.this, uri);

            Bitmap bitMap = BitmapFactory.decodeFile(cropImagePath);
            iv_headimg.setImageBitmap(bitMap);
        } else if (requestCode == 2) {
            if (data != null) {
                imgList.clear();
                imgList = data.getStringArrayListExtra(MultiImageSelector.EXTRA_RESULT);
                photoEntities.clear();

                for (int i = 0; i < imgList.size(); i++) {
                    String imgPath = imgList.get(i);
                    PhotoEntity photoEntity = new PhotoEntity("", imgPath);
                    photoEntities.add(photoEntity);
                }
                gridViewAdapter = new GridViewAdapter(RegisterThirdActivity.this, photoEntities, delete);
                gridview.setAdapter(gridViewAdapter);
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
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


    @Override
    public void onBackPressed() {

        return;
    }

}
