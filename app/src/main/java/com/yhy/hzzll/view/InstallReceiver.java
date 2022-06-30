package com.yhy.hzzll.view;

import com.umeng.socialize.utils.Log;
import com.yhy.hzzll.framework.Constans;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * 安装下载接收器
 * Created by maimingliang on 2016/8/11.
 */

public class InstallReceiver extends BroadcastReceiver {

    private static final String TAG = "InstallReceiver";

    // 安装下载接收器
    @Override public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(DownloadManager.ACTION_DOWNLOAD_COMPLETE)) {
            installApk(context);
        }
    }

    // 安装Apk
    private void installApk(Context context) {

        try {
            Intent i = new Intent(Intent.ACTION_VIEW);
            String filePath =Constans.SAVE_APP_LOCATION;
            i.setDataAndType(Uri.parse("file://" + filePath), "application/vnd.android.package-archive");
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        }catch (Exception e){
            Log.e(TAG,"安装失败");
            e.printStackTrace();
        }
    }
}
