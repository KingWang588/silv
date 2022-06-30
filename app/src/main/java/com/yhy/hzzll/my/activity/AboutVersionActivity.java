package com.yhy.hzzll.my.activity;

import android.Manifest;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.FileDownloadListener;
import com.liulishuo.filedownloader.FileDownloader;
import com.liulishuo.filedownloader.util.FileDownloadUtils;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionListener;
import com.yhy.hzzll.R;
import com.yhy.hzzll.entity.VersionEntity;
import com.yhy.hzzll.framework.Constans;
import com.yhy.hzzll.framework.MyData;
import com.yhy.hzzll.mian.activity.BaseActivity;
import com.yhy.hzzll.utils.HttpDataUtils;
import com.yhy.hzzll.utils.JSONTool;
import com.yhy.hzzll.utils.ToastUtils;
import com.yhy.hzzll.view.UpdataVersionDialog;

import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import static com.yhy.hzzll.utils.OpenFileUtil.openFile;

public class AboutVersionActivity extends BaseActivity {


    @ViewInject(R.id.tv_version_end)
    TextView tv_version_end;
    @ViewInject(R.id.tv_version_code)
    TextView tv_version_code;

    @ViewInject(R.id.iv_weixin)
    ImageView iv_weixin;
    VersionEntity entity;
    String downUrl = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_version);
        ViewUtils.inject(this);
        checkVersion();

//        iv_weixin.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                savePicForSystem(AboutVersionActivity.this,"https://image.baidu.com/search/detail?ct=503316480&z=0&tn=baiduimagedetail&ipn=d&cl=2&cm=1&sc=0&lm=-1&ie=gbk&pn=5&rn=1&di=55538017370&ln=30&word=%C3%C0%C5%AE&os=2693601731,178782203&cs=998893491,2679530940&objurl=http%3A%2F%2Fimgs.qingqulu.com%2F2016%2F12%2F20161206100857-69.jpg&bdtype=0&simid=3477571785,410067502&pi=0&adpicid=0&timingneed=0&spn=0&is=0,0&fr=ala&ala=1&alatpl=cover&pos=1&hs=2&xthttps=111111");
//                return true;
//            }
//        });

        tv_version_code.setText("版本号  "+getVersion(this));
    }


    @OnClick({R.id.rl_about, R.id.tv_version_end, R.id.ic_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ic_back:
                finish();
                break;
            case R.id.rl_about:
                startActivity(new Intent().setClass(AboutVersionActivity.this, AboutActivity.class));
                break;
            case R.id.tv_version_end:
                if(entity!=null){
                    if(tv_version_end.getText().toString().contains("有新版本")){
                        UpdataVersionDialog enter = new UpdataVersionDialog();
                        enter.showDialog(AboutVersionActivity.this, new UpdataVersionDialog.Click() {
                            @Override
                            public void click() {
                                jurisdiction();
//                                downloadApk(AboutVersionActivity.this, entity.getData().getDownurl(), "私律律师端", "更新");
                            }
                        }, false, entity.getData().getVersion());
                    }
                }

                break;
        }
    }


    private void checkVersion() {
        RequestParams params = new RequestParams();
//        params.addBodyParameter("type", "android");
        httpDataUtils.sendGet(MyData.CHECK_VERSION, params);
        httpDataUtils.setSucessBack(new HttpDataUtils.SucessBack() {
            @Override
            public void sucess(ResponseInfo<String> arg0) {
                try {
                    JSONObject object = new JSONObject(arg0.result);
                    String msg = JSONTool.getString(object, "msg");
                    if (object.optString("code").equals("0")) {
                        JSONObject data = JSONTool.getJsonObject(object, "data");
                        entity = gson.fromJson(object.toString(), VersionEntity.class);
                        String version = getVersion(AboutVersionActivity.this);
                        if (entity != null) {
                            String newVersion = entity.getData().getVersion();
                            int a = Integer.parseInt(version.charAt(0) + "");
                            int a1 = Integer.parseInt(newVersion.charAt(0) + "");

                            int b = Integer.parseInt(version.charAt(2) + "");
                            int b1 = Integer.parseInt(newVersion.charAt(2) + "");

                            int c = Integer.parseInt(version.charAt(4) + "");
                            int c1 = Integer.parseInt(newVersion.charAt(4) + "");

                            downUrl =  entity.getData().getDownurl();
                            apk_version = entity.getData().getVersion();

                            if (a1 > a) {
                                tv_version_end.setText("有新版本"+entity.getData().getVersion() );
                                UpdataVersionDialog enter = new UpdataVersionDialog();
                                enter.showDialog(AboutVersionActivity.this, new UpdataVersionDialog.Click() {
                                    @Override
                                    public void click() {
                                        jurisdiction();
//                                        downloadApk(AboutVersionActivity.this, entity.getData().getDownurl(), "私律律师端", "更新");
                                    }
                                }, true, entity.getData().getVersion());
                            } else if (a1 == a) {
                                if (b1 > b) {
                                    tv_version_end.setText("有新版本"+entity.getData().getVersion() );
                                    UpdataVersionDialog enter = new UpdataVersionDialog();
                                    enter.showDialog(AboutVersionActivity.this, new UpdataVersionDialog.Click() {
                                        @Override
                                        public void click() {
                                            jurisdiction();
//                                            downloadApk(AboutVersionActivity.this, entity.getData().getDownurl(), "私律律师端", "更新");
                                        }
                                    }, true, entity.getData().getVersion());
                                } else if (b1 == b) {
                                    if (c1 > c) {
                                        tv_version_end.setText("有新版本"+entity.getData().getVersion() );
                                        UpdataVersionDialog enter = new UpdataVersionDialog();
                                        enter.showDialog(AboutVersionActivity.this, new UpdataVersionDialog.Click() {
                                            @Override
                                            public void click() {
                                                jurisdiction();
//                                                downloadApk(AboutVersionActivity.this, entity.getData().getDownurl(), "私律律师端", "更新");
                                            }
                                        }, false, entity.getData().getVersion());
                                    } else {
//                                        ToastUtils.getUtils(AboutVersionActivity.this).show("已是最新版本");
                                        tv_version_end.setText("当前已是最新版本");
                                    }
                                } else {
//                                    ToastUtils.getUtils(AboutVersionActivity.this).show("已是最新版本");
                                    tv_version_end.setText("当前已是最新版本");
                                }
                            } else {
                                tv_version_end.setText("当前已是最新版本");
//                                ToastUtils.getUtils(AboutVersionActivity.this).show("已是最新版本");
                            }
                        }

                    } else {
                        tv_version_end.setText("当前已是最新版本");
//                        ToastUtils.getUtils(AboutVersionActivity.this).show("已是最新版本");
                    }
                } catch (Exception e) {
                    // TODO Auto-generated catch block
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



    private void jurisdiction() {

        AndPermission.with(AboutVersionActivity.this)
                .requestCode(102)
                .permission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .callback(permissionListener)
                .start();

    }

    private static final int REQUEST_CODE_SETTING = 300;
    String apk_version = "";

    private PermissionListener permissionListener = new PermissionListener() {
        @Override
        public void onSucceed(int requestCode, List<String> grantPermissions) {
            switch (requestCode) {
                case 102: {
                    if (AndPermission.hasPermission(AboutVersionActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
//                        downloadApk(AboutVersionActivity.this, downUrl, "私律律师端", "更新");

                        final String savePath = FileDownloadUtils.getDefaultSaveRootPath() + File.separator + "document" + File.separator + "silv"+apk_version+".apk";
                        downloadFile(savePath, downUrl, "apk");

                    } else {
                        Toast.makeText(AboutVersionActivity.this, "我们需要的一些权限被您拒绝或者系统发生错误申请失败，请您到设置页面手动授权，否则功能无法正常使用！", Toast.LENGTH_SHORT).show();
                    }
                    break;
                }
            }
        }

        @Override
        public void onFailed(int requestCode, List<String> deniedPermissions) {
            switch (requestCode) {
                case 102: {
                    Toast.makeText(AboutVersionActivity.this, "获取权限失败", Toast.LENGTH_SHORT).show();
                    break;
                }
            }
        }
    };


    private void downloadFile(final String savePath, String url, final String type) {

        ToastUtils.getUtils(AboutVersionActivity.this).show("正在下载......");

        FileDownloader.getImpl().create(url)
                .setPath(savePath)
                .setListener(new FileDownloadListener() {
                    @Override
                    protected void pending(BaseDownloadTask task, int soFarBytes, int totalBytes) {

                    }

                    @Override
                    protected void connected(BaseDownloadTask task, String etag, boolean isContinue, int soFarBytes, int totalBytes) {

                    }

                    @Override
                    protected void progress(BaseDownloadTask task, int soFarBytes, int totalBytes) {

                    }

                    @Override
                    protected void blockComplete(BaseDownloadTask task) {
                    }

                    @Override
                    protected void retry(final BaseDownloadTask task, final Throwable ex, final int retryingTimes, final int soFarBytes) {
                    }

                    @Override
                    protected void completed(BaseDownloadTask task) {
                        ToastUtils.getUtils(AboutVersionActivity.this).show("下载已完成");
                        startActivity(openFile(savePath, type));
                    }

                    @Override
                    protected void paused(BaseDownloadTask task, int soFarBytes, int totalBytes) {
                    }

                    @Override
                    protected void error(BaseDownloadTask task, Throwable e) {
                    }

                    @Override
                    protected void warn(BaseDownloadTask task) {

                    }
                }).start();
    }


    public void savePicForSystem(Context context,String url){
        File saveFile = new File(url);
        // 其次把文件插入到系统图库
        try {
            MediaStore.Images.Media.insertImage(getContentResolver(),
                    saveFile.getAbsolutePath(), saveFile.getName(), null);
        } catch (FileNotFoundException e) {
            Toast.makeText(context, "图片保存成功！", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
        // 最后通知图库更新
        sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(saveFile)));
        Toast.makeText(context,"图片保存成功！", Toast.LENGTH_SHORT).show();
    }



    /**
     * 下载Apk, 并设置Apk地址,
     * 默认位置: /storage/sdcard0/Download
     *
     * @param context     上下文
     * @param downLoadUrl 下载地址
     * @param infoName    通知名称
     * @param description 通知描述
     */
    @SuppressWarnings("unused")
    public static void downloadApk(
            Context context,
            String downLoadUrl,
            String description,
            String infoName) {

        DownloadManager.Request request;
        try {
            request = new DownloadManager.Request(Uri.parse(downLoadUrl));
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        request.setTitle(infoName);
        request.setDescription(description);

        //在通知栏显示下载进度
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            request.allowScanningByMediaScanner();
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        }

        //设置保存下载apk保存路径
        request.setDestinationInExternalPublicDir(Constans.SAVE_APP_LOCATION, Constans.SAVE_APP_NAME);

        Context appContext = context.getApplicationContext();
        DownloadManager manager = (DownloadManager)
                appContext.getSystemService(Context.DOWNLOAD_SERVICE);
        //进入下载队列
        manager.enqueue(request);

    }


    /**
     * 获取版本号
     *
     * @return 当前应用的版本号
     */
    public String getVersion(Context context) {
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            String version = info.versionName;

//			Log.e("132465789",version+"******");
            return version;
        } catch (Exception e) {
            e.printStackTrace();
            return "1.0.0";
        }
    }


}
