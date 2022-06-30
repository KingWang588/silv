package com.yhy.hzzll.view;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.netease.nim.uikit.common.media.picker.PickImageHelper;
import com.netease.nim.uikit.common.util.storage.StorageType;
import com.netease.nim.uikit.common.util.storage.StorageUtil;
import com.netease.nim.uikit.common.util.string.StringUtil;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionListener;
import com.yhy.hzzll.R;
import com.yhy.hzzll.file.browser.FileBrowserActivity;
import com.yhy.hzzll.home.activity.newcollaborate.CloudDiskActivity;
import com.yhy.hzzll.mian.activity.RegisterThirdActivity;
import com.yhy.hzzll.utils.ToastUtils;

import java.util.List;

import me.nereo.multi_image_selector.MultiImageSelector;

public class UploadPopupWindow extends PopupWindow {
    private View conentView;
    private Activity context;
    private int num;

    public UploadPopupWindow(final Activity context,int num) {
        this.num = num;
        this.context = context;
//        this.click = click;
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        conentView = inflater.inflate(R.layout.upload_pop_layout, null);
        // int height = context.getResources().getDisplayMetrics().heightPixels;
        int width = context.getResources().getDisplayMetrics().widthPixels;
        // 设置SelectPicPopupWindow的View
        this.setContentView(conentView);
        // 设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(width);
        // 设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        // 设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        // 刷新状态
        this.update();
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0000000000);
        // 点back键和其他地方使其消失,设置了这个才能触发OnDismisslistener ，设置其他控件变化等操作
        this.setBackgroundDrawable(dw);
        // mPopupWindow.setAnimationStyle(android.R.style.Animation_Dialog);
        // 设置SelectPicPopupWindow弹出窗体动画效果
        this.setAnimationStyle(R.style.AnimationPreview);

        conentView.findViewById(R.id.upload_local).setOnClickListener(listener);
        conentView.findViewById(R.id.upload_cloud_disk).setOnClickListener(listener);
        conentView.findViewById(R.id.tv_cancel).setOnClickListener(listener);
        conentView.findViewById(R.id.upload_pic).setOnClickListener(listener);

        // 设置背景颜色变暗
        WindowManager.LayoutParams lp = context.getWindow().getAttributes();
        lp.alpha = 0.7f;
        context.getWindow().setAttributes(lp);
        this.setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = context.getWindow()
                        .getAttributes();
                lp.alpha = 1f;
                context.getWindow().setAttributes(lp);
            }
        });
    }

    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {

                case R.id.upload_cloud_disk:
//                    ToastUtils.getUtils(context).show("打开云盘");
                    dismiss();
                    context.startActivityForResult(new Intent(context,CloudDiskActivity.class).putExtra("num",num),251);

                    break;

                    case R.id.upload_local:
                        dismiss();
                        FileBrowserActivity.startActivityForResult(context,250);
//                        ToastUtils.getUtils(context).show("本地");
                    break;

                case R.id.upload_pic:

                    dismiss();

                    AndPermission.with(context)
                            .requestCode(102)
                            .permission(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                            .callback(permissionListener)
                            .start();
                    
//                    FileBrowserActivity.startActivityForResult(context,250);
//                    showSelector(12, 256, false, tempFile());
                    break;
                    
                case R.id.tv_cancel:
                    dismiss();
                    break;
            }
        }
    };


    /**
     * 回调监听。
     */
    private PermissionListener permissionListener = new PermissionListener() {
        @Override
        public void onSucceed(int requestCode, List<String> grantPermissions) {
            switch (requestCode) {
//                case 101: {
//                    if (AndPermission.hasPermission(context, Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
//                        pickImage();
//                    } else {
//                        Toast.makeText(context, "我们需要的一些权限被您拒绝或者系统发生错误申请失败，请您到设置页面手动授权，否则功能无法正常使用！", Toast.LENGTH_SHORT).show();
//                    }
//
//                    break;
//                }
                case 102: {
                    if (AndPermission.hasPermission(context, Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                        pickImage2();
                    } else {
                        Toast.makeText(context, "我们需要的一些权限被您拒绝或者系统发生错误申请失败，请您到设置页面手动授权，否则功能无法正常使用！", Toast.LENGTH_SHORT).show();
                    }
                    break;
                }
            }
        }

        @Override
        public void onFailed(int requestCode, List<String> deniedPermissions) {
            switch (requestCode) {
                case 101: {
                    Toast.makeText(context, "获取权限失败", Toast.LENGTH_SHORT).show();
                    break;
                }
                case 102: {
                    Toast.makeText(context, "获取权限失败", Toast.LENGTH_SHORT).show();
                    break;
                }
            }

        }
    };


    private void pickImage2() {
        MultiImageSelector selector = MultiImageSelector.create();
        selector.showCamera(true);
        selector.multi();
        selector.single();
        selector.start(context,253);
    }

    /**
     * 显示popupWindow
     *
     * @param parent
     */
    public void showPopupWindow(View parent) {
        if (!this.isShowing()) {
            // 以下拉方式显示popupwindow
            this.showAtLocation(parent, Gravity.BOTTOM, 0, 0);
        } else {
            this.dismiss();
        }
    }
}
