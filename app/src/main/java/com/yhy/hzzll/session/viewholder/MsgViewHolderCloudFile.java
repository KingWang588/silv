package com.yhy.hzzll.session.viewholder;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.FileDownloadListener;
import com.liulishuo.filedownloader.FileDownloader;
import com.liulishuo.filedownloader.util.FileDownloadUtils;
import com.netease.nim.uikit.business.session.viewholder.MsgViewHolderBase;
import com.netease.nim.uikit.common.ui.recyclerview.adapter.BaseMultiItemFetchLoadAdapter;
import com.yhy.hzzll.R;
import com.yhy.hzzll.home.activity.newcollaborate.PickUpCaseActivity;
import com.yhy.hzzll.session.extension.CloudFileAttachment;
import com.yhy.hzzll.utils.ToastUtils;

import java.io.File;

import static com.yhy.hzzll.utils.OpenFileUtil.openFile;

public class MsgViewHolderCloudFile extends MsgViewHolderBase {

    private ImageView fileIcon;
    private TextView fileNameLabel;
    private TextView fileSizeLabel;

    private CloudFileAttachment msgAttachment;

    public MsgViewHolderCloudFile(BaseMultiItemFetchLoadAdapter adapter) {
        super(adapter);
    }

    @Override
    protected int getContentResId() {
        return  R.layout.nin_message_item_cloudfile;
    }

    @Override
    protected void inflateContentView() {
        fileIcon = (ImageView) view.findViewById(R.id.iv_file);
        fileNameLabel = (TextView) view.findViewById(R.id.tv_file_name);
        fileSizeLabel = (TextView) view.findViewById(R.id.tv_file_size);

    }

    @Override
    protected void bindContentView() {
        msgAttachment = (CloudFileAttachment) message.getAttachment();

        fileIcon.setImageResource(R.drawable.copy_1);
        fileNameLabel.setText(msgAttachment.getName());
        fileSizeLabel.setText(msgAttachment.getSize());
    }


    @Override
    protected void onItemClick() {

        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        Uri content_url = Uri.parse(msgAttachment.getUrl());
        intent.setData(content_url);
        try {
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(context, "路径错误", Toast.LENGTH_SHORT).show();
        }

//        String savePath = FileDownloadUtils.getDefaultSaveRootPath() + File.separator + "document" + File.separator + msgAttachment.getName();
//        String url = msgAttachment.getUrl();
//        String type =  msgAttachment.getUrl();
//
//        downloadFile(savePath, url, type);


//        context.startActivity(new Intent().putExtra("user_id", msgAttachment.getUrl()).putExtra("title", msgAttachment.getName()).putExtra("from", "IM").setClass(context, LawyerOfficeActivity.class));
        super.onItemClick();
    }

    private void downloadFile(final String savePath, String url, final String type) {

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
                        ToastUtils.getUtils(context).show("下载已完成");
                        context.startActivity(openFile(savePath, type));

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


}
