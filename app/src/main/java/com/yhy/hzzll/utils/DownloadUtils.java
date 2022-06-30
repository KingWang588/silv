package com.yhy.hzzll.utils;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Environment;

/**
 * Created by chengying on 2017/9/29.
 */

public class DownloadUtils {
    //下载器
    private DownloadManager downloadManager;
    //上下文
    private Context mContext;
    //下载的ID
    private long downloadId;

    Uri downloadFileUri;
    String name; String type;

    public  DownloadUtils(Context context){
        this.mContext = context;
    }

    //下载apk
    public void downloadAPK(String url, String name,String type) {
        this.name = name;
        this.type = type;
        //创建下载任务
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
        //移动网络情况下是否允许漫游
        request.setAllowedOverRoaming(false);

        //在通知栏中显示，默认就是显示的
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE);
        request.setTitle("私律"+name);
        request.setDescription("聊天文件");
        request.setVisibleInDownloadsUi(true);

        //设置下载的路径
        request.setDestinationInExternalPublicDir(Environment.getExternalStorageDirectory().getAbsolutePath() , name);

        //获取DownloadManager
        Context appContext = mContext.getApplicationContext();
        downloadManager = (DownloadManager) appContext.getSystemService(Context.DOWNLOAD_SERVICE);
        //将下载请求加入下载队列，加入下载队列后会给该任务返回一个long型的id，通过该id可以取消任务，重启任务、获取下载的文件等等
         downloadManager.enqueue(request);

//        //注册广播接收者，监听下载状态
//        mContext.registerReceiver(receiver,
//                new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
    }

//    //广播监听下载的各个状态
//    private BroadcastReceiver receiver = new BroadcastReceiver() {
//        @Override
//        public void onReceive(Context context, Intent intent) {
////            checkStatus();
//        }
//    };


    //检查下载状态
//    private void checkStatus() {
//        DownloadManager.Query query = new DownloadManager.Query();
//        //通过下载的id查找
//        query.setFilterById(downloadId);
//        Cursor c = downloadManager.query(query);
//        if (c.moveToFirst()) {
//            int status = c.getInt(c.getColumnIndex(DownloadManager.COLUMN_STATUS));
//            switch (status) {
//                //下载暂停
//                case DownloadManager.STATUS_PAUSED:
//                    break;
//                //下载延迟
//                case DownloadManager.STATUS_PENDING:
//                    break;
//                //正在下载
//                case DownloadManager.STATUS_RUNNING:
//                    break;
//                //下载完成
//                case DownloadManager.STATUS_SUCCESSFUL:
//                    //下载完成安装APK
////                    downloadFileUri = downloadManager.getUriForDownloadedFile(downloadId);
////                    openFile(name);
//                    break;
//                //下载失败
//                case DownloadManager.STATUS_FAILED:
//                    Toast.makeText(mContext, "下载失败", Toast.LENGTH_SHORT).show();
//                    break;
//            }
//        }
//        c.close();
//    }

//    //下载到本地后执行安装
//    private void installAPK() {
//        //获取下载文件的Uri
//        Uri downloadFileUri = downloadManager.getUriForDownloadedFile(downloadId);
//        if (downloadFileUri != null) {
//            Intent intent= new Intent(Intent.ACTION_VIEW);
//            intent.setDataAndType(downloadFileUri, "application/vnd.android.package-archive");
//            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            mContext.startActivity(intent);
//        }
//    }
//
//
//    public  Intent openFile(String filePath){
//
////        File file = new File(filePath);
////        if(!file.exists()) return null;
//        /* 取得扩展名 */
//        String end=type;
//        /* 依扩展名的类型决定MimeType */
//        if(end.equals("m4a")||end.equals("mp3")||end.equals("mid")||
//                end.equals("xmf")||end.equals("ogg")||end.equals("wav")){
//            return getAudioFileIntent(downloadFileUri);
//        }else if(end.equals("3gp")||end.equals("mp4")){
//            return getAudioFileIntent(downloadFileUri);
//        }else if(end.equals("jpg")||end.equals("gif")||end.equals("png")||
//                end.equals("jpeg")||end.equals("bmp")){
//            return getImageFileIntent(downloadFileUri);
//        }else if(end.equals("apk")){
//            return getApkFileIntent(filePath);
//        }else if(end.equals("ppt")){
//            return getPptFileIntent(downloadFileUri);
//        }else if(end.equals("xls")){
//            return getExcelFileIntent(downloadFileUri);
//        }else if(end.equals("doc")){
//            return getWordFileIntent(downloadFileUri);
//        }else if(end.equals("pdf")){
//            return getPdfFileIntent(downloadFileUri);
//        }else if(end.equals("chm")){
//            return getChmFileIntent(downloadFileUri);
//        }else if(end.equals("txt")){
//            return getTextFileIntent(filePath,false);
//        }else{
//            return getAllIntent(filePath);
//        }
//    }
//
//    //Android获取一个用于打开APK文件的intent
//    public static Intent getAllIntent( String param ) {
//
//        Intent intent = new Intent();
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        intent.setAction(android.content.Intent.ACTION_VIEW);
//        Uri uri = Uri.fromFile(new File(param ));
//        intent.setDataAndType(uri,"*/*");
//        return intent;
//    }
//    //Android获取一个用于打开APK文件的intent
//    public static Intent getApkFileIntent( String param ) {
//
//        Intent intent = new Intent();
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        intent.setAction(android.content.Intent.ACTION_VIEW);
//        Uri uri = Uri.fromFile(new File(param ));
//        intent.setDataAndType(uri,"application/vnd.android.package-archive");
//        return intent;
//    }
//
//    //Android获取一个用于打开VIDEO文件的intent
//    public static Intent getVideoFileIntent( String param ) {
//
//        Intent intent = new Intent("android.intent.action.VIEW");
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        intent.putExtra("oneshot", 0);
//        intent.putExtra("configchange", 0);
//        Uri uri = Uri.fromFile(new File(param ));
//        intent.setDataAndType(uri, "video/*");
//        return intent;
//    }
//
//    //Android获取一个用于打开AUDIO文件的intent
//    public static Intent getAudioFileIntent( Uri uri ){
//
//        Intent intent = new Intent("android.intent.action.VIEW");
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        intent.putExtra("oneshot", 0);
//        intent.putExtra("configchange", 0);
////        Uri uri = Uri.fromFile(new File(param ));
//        intent.setDataAndType(uri, "audio/*");
//        return intent;
//    }
//
//    //Android获取一个用于打开Html文件的intent
//    public static Intent getHtmlFileIntent(  Uri uri ){
//
////        Uri uri = Uri.parse(param ).buildUpon().encodedAuthority("com.android.htmlfileprovider").scheme("content").encodedPath(param ).build();
//        Intent intent = new Intent("android.intent.action.VIEW");
//        intent.setDataAndType(uri, "text/html");
//        return intent;
//    }
//
//    //Android获取一个用于打开图片文件的intent
//    public static Intent getImageFileIntent(  Uri uri ) {
//
//        Intent intent = new Intent("android.intent.action.VIEW");
//        intent.addCategory("android.intent.category.DEFAULT");
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
////        Uri uri = Uri.fromFile(new File(param ));
//        intent.setDataAndType(uri, "image/*");
//        return intent;
//    }
//
//    //Android获取一个用于打开PPT文件的intent
//    public static Intent getPptFileIntent( Uri uri ){
//
//        Intent intent = new Intent("android.intent.action.VIEW");
//        intent.addCategory("android.intent.category.DEFAULT");
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
////        Uri uri = Uri.fromFile(new File(param ));
//        intent.setDataAndType(uri, "application/vnd.ms-powerpoint");
//        return intent;
//    }
//
//    //Android获取一个用于打开Excel文件的intent
//    public static Intent getExcelFileIntent( Uri uri){
//
//        Intent intent = new Intent("android.intent.action.VIEW");
//        intent.addCategory("android.intent.category.DEFAULT");
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
////        Uri uri = Uri.fromFile(new File(param ));
//        intent.setDataAndType(uri, "application/vnd.ms-excel");
//        return intent;
//    }
//
//    //Android获取一个用于打开Word文件的intent
//    public static Intent getWordFileIntent(  Uri uri ){
//
//        Intent intent = new Intent("android.intent.action.VIEW");
//        intent.addCategory("android.intent.category.DEFAULT");
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
////        Uri uri = Uri.fromFile(new File(param ));
//        intent.setDataAndType(uri, "application/msword");
//        return intent;
//    }
//
//    //Android获取一个用于打开CHM文件的intent
//    public static Intent getChmFileIntent(  Uri uri ){
//
//        Intent intent = new Intent("android.intent.action.VIEW");
//        intent.addCategory("android.intent.category.DEFAULT");
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
////        Uri uri = Uri.fromFile(new File(param ));
//        intent.setDataAndType(uri, "application/x-chm");
//        return intent;
//    }
//
//    //Android获取一个用于打开文本文件的intent
//    public static Intent getTextFileIntent( String param, boolean paramBoolean){
//
//        Intent intent = new Intent("android.intent.action.VIEW");
//        intent.addCategory("android.intent.category.DEFAULT");
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        if (paramBoolean){
//            Uri uri1 = Uri.parse(param );
//            intent.setDataAndType(uri1, "text/plain");
//        }else{
//            Uri uri2 = Uri.fromFile(new File(param ));
//            intent.setDataAndType(uri2, "text/plain");
//        }
//        return intent;
//    }
//    //Android获取一个用于打开PDF文件的intent
//    public static Intent getPdfFileIntent(  Uri uri){
//
//        Intent intent = new Intent("android.intent.action.VIEW");
//        intent.addCategory("android.intent.category.DEFAULT");
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
////        Uri uri = Uri.fromFile(new File(param ));
//        intent.setDataAndType(uri, "application/pdf");
//        return intent;
//    }

}
