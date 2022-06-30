package com.yhy.hzzll.session.activity;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.FileProvider;

import com.netease.nim.uikit.common.activity.UI;
import com.netease.nim.uikit.common.ui.dialog.DialogMaker;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.Observer;
import com.netease.nimlib.sdk.msg.MsgService;
import com.netease.nimlib.sdk.msg.MsgServiceObserve;
import com.netease.nimlib.sdk.msg.attachment.FileAttachment;
import com.netease.nimlib.sdk.msg.constant.AttachStatusEnum;
import com.netease.nimlib.sdk.msg.model.IMMessage;
import com.yhy.hzzll.R;
import com.yhy.hzzll.utils.LogUtils;

import java.io.File;

import static com.yhy.hzzll.utils.OpenFileUtil.openFile;

/**
 * Created by hzxuwen on 2016/12/14.
 */

public class FileDownloadActivity extends UI {
    private static final String INTENT_EXTRA_DATA = "INTENT_EXTRA_DATA";

    private TextView fileNameText;
    private Button fileDownloadBtn;
    private TextView text_location;
    private ImageView ic_back;

    private IMMessage message;

    private String url="";
    private String name="";
    private String path="";
    private String type="";

    public static void start(Context context, IMMessage message) {
        Intent intent = new Intent();
        intent.putExtra(INTENT_EXTRA_DATA, message);
        intent.setClass(context, FileDownloadActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.file_download_activity);

        onParseIntent();
        findViews();

        updateUI();
        registerObservers(true);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        registerObservers(false);
    }

    private void onParseIntent() {
        this.message = (IMMessage) getIntent().getSerializableExtra(INTENT_EXTRA_DATA);
    }

    private void findViews() {

        fileNameText = findView(R.id.file_name);
        fileDownloadBtn = findView(R.id.download_btn);
        text_location = findView(R.id.text_location);
        ic_back = (ImageView) findViewById(R.id.ic_back);
        ic_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        fileDownloadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isOriginDataHasDownloaded(message)) {
                    return;
                }

                downloadFile();

                downloadApk(FileDownloadActivity.this,url,"文件下载",name);

            }
        });

        text_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(openFile(path,type));
            }
        });

    }

     private void updateUI() {
        FileAttachment attachment = (FileAttachment) message.getAttachment();
         LogUtils.logE("getDisplayName()"+attachment.getDisplayName()+"    getPath()"+attachment.getPath()+"    getUrl()"+attachment.getUrl()
         +"  getExtension()  "+attachment.getExtension()+"");

        if (attachment != null) {
            fileNameText.setText(attachment.getDisplayName());
            name = attachment.getDisplayName();
            url= attachment.getUrl();
            path = attachment.getPath();
            type = attachment.getExtension();
        }

        if (isOriginDataHasDownloaded(message)) {
            onDownloadSuccess();
            text_location.setVisibility(View.VISIBLE);
        } else {
            onDownloadFailed();
        }
    }

    private boolean isOriginDataHasDownloaded(final IMMessage message) {
        if (!TextUtils.isEmpty(((FileAttachment) message.getAttachment()).getPath())) {
            return true;
        }

        return false;
    }

    private void downloadFile() {
        DialogMaker.showProgressDialog(this, "loading");
        NIMClient.getService(MsgService.class).downloadAttachment(message, false);
    }

    /**
     * ********************************* 下载 ****************************************
     */

    private void registerObservers(boolean register) {
        NIMClient.getService(MsgServiceObserve.class).observeMsgStatus(statusObserver, register);
    }

    private Observer<IMMessage> statusObserver = new Observer<IMMessage>() {
        @Override
        public void onEvent(IMMessage msg) {
            if (!msg.isTheSame(message) || isDestroyedCompatible()) {
                return;
            }

            if (msg.getAttachStatus() == AttachStatusEnum.transferred && isOriginDataHasDownloaded(msg)) {
                DialogMaker.dismissProgressDialog();
                onDownloadSuccess();
            } else if (msg.getAttachStatus() == AttachStatusEnum.fail) {
                DialogMaker.dismissProgressDialog();
                Toast.makeText(FileDownloadActivity.this, "download failed", Toast.LENGTH_SHORT).show();
                onDownloadFailed();
            }
        }
    };

    private void onDownloadSuccess() {

        FileAttachment attachment = (FileAttachment) message.getAttachment();
        if (attachment != null) {
            fileNameText.setText(attachment.getDisplayName());
            name = attachment.getDisplayName();
            url= attachment.getUrl();
            path = attachment.getPath();
            type = attachment.getExtension();
        }


        fileDownloadBtn.setText("已下载");
        fileDownloadBtn.setEnabled(false);
        fileDownloadBtn.setBackgroundResource(R.drawable.g_white_btn_pressed);

        text_location.setVisibility(View.VISIBLE);
    }

    private void onDownloadFailed() {
        fileDownloadBtn.setText("下载");
        fileDownloadBtn.setEnabled(true);
        fileDownloadBtn.setBackgroundResource(R.drawable.nim_team_create_btn_selector);
    }


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
        request.setDestinationInExternalPublicDir(Environment.getExternalStorageDirectory().getAbsolutePath() , infoName);

        Context appContext = context.getApplicationContext();
        DownloadManager manager = (DownloadManager)
                appContext.getSystemService(Context.DOWNLOAD_SERVICE);
        //进入下载队列
        manager.enqueue(request);

    }


//    public static void openFile(Context context,File file) {
//        //Uri uri = Uri.parse("file://"+file.getAbsolutePath());
//        Intent intent = new Intent();
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        //设置intent的Action属性
//        intent.setAction(Intent.ACTION_VIEW);
//        //获取文件file的MIME类型
////        String type = getMIMEType(file);
//        //设置intent的data和Type属性。
//        intent.setDataAndType(/*uri*/FileProvider.getUriForFile(context, context.getPackageName() + ".provider", file), ".pdf");
//        //跳转
//        context.startActivity(intent);
//    }
//
//    public static void openFileByWps(Context context,File file){
//        Intent intent = new Intent();
//
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        intent.setAction(android.content.Intent.ACTION_VIEW);
//        intent.setClassName("cn.wps.moffice", "cn.wps.moffice.documentmanager.PreStartActivity");
//        Uri uri = Uri.fromFile(file);
//        intent.setData(uri);
//        context.startActivity(intent);
//    }


    private static Uri getUri(Context context, Intent intent, File file) {
        Uri uri = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            //判断版本是否在7.0以上
            uri =
                    FileProvider.getUriForFile(context,
                            context.getPackageName() + ".fileprovider",
                            file);
            //添加这一句表示对目标应用临时授权该Uri所代表的文件
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        } else {
            uri = Uri.fromFile(file);
        }
        return uri;
    }

}
