package com.yhy.hzzll.session.action;

import android.content.Intent;

import com.netease.nim.uikit.business.session.actions.BaseAction;
import com.netease.nim.uikit.business.session.constant.RequestCode;
import com.netease.nimlib.sdk.msg.MessageBuilder;
import com.netease.nimlib.sdk.msg.model.CustomMessageConfig;
import com.netease.nimlib.sdk.msg.model.IMMessage;
import com.yhy.hzzll.R;
import com.yhy.hzzll.home.activity.newcollaborate.CloudDiskActivity;
import com.yhy.hzzll.session.extension.CardAttachment;
import com.yhy.hzzll.session.extension.CloudFileAttachment;
import com.yhy.hzzll.utils.LogUtils;
import com.yhy.hzzll.utils.PrefsUtils;

public class CloudFileAction extends BaseAction {

    public CloudFileAction() {
        super(R.drawable.messageview_icon_cloudfile_2, R.string.input_panel_file_cloud);
    }


    @Override
    public void onClick() {
        chooseFile();
    }

    private void chooseFile() {
        CloudDiskActivity.startActivityForResult(getActivity(), makeRequestCode(RequestCode.GET_CLOUD_FILE));
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        LogUtils.logE("===============2");
        if (requestCode == RequestCode.GET_CLOUD_FILE) {
            String path = data.getStringExtra("paths");
            String name = data.getStringExtra("name");
            String size = data.getStringExtra("size");

            CloudFileAttachment cloudFileAttachment = new CloudFileAttachment();
            cloudFileAttachment.setName(name);
            cloudFileAttachment.setUrl(path);
            cloudFileAttachment.setSize(size);


            CustomMessageConfig config = new CustomMessageConfig();
            config.enableHistory = true;
            config.enableRoaming = false;
            config.enableSelfSync = false;
            IMMessage cloudFileMessage = MessageBuilder.createCustomMessage(getAccount(), getSessionType(), "", cloudFileAttachment, config);
            sendMessage(cloudFileMessage);

////            File file = new File(path);
//            IMMessage message = MessageBuilder.createTextMessage(getAccount(), getSessionType(), path+" （文件来自私律云盘）");
////            IMMessage message = MessageBuilder.createFileMessage(getAccount(), getSessionType(), file, file.getName());
//            sendMessage(message);
        }

//        super.onActivityResult(requestCode, resultCode, data);

    }
}
