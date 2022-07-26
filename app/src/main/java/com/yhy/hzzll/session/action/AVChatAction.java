package com.yhy.hzzll.session.action;

import android.widget.Toast;

import com.netease.nim.uikit.business.session.actions.BaseAction;
import com.netease.nim.uikit.common.util.sys.NetworkUtil;

import com.netease.nimlib.sdk.avchat.constant.AVChatType;
import com.yhy.hzzll.R;


/**
 * Created by hzxuwen on 2015/6/12.
 */
public class AVChatAction extends BaseAction {
    private AVChatType avChatType;

    public AVChatAction(AVChatType avChatType) {
        super(avChatType == AVChatType.AUDIO ? R.drawable.messageview_icon_mic : R.drawable.messageview_icon_mic,
                avChatType == AVChatType.AUDIO ? R.string.input_panel_audio_call : R.string.input_panel_video_call);
        this.avChatType = avChatType;
    }

    @Override
    public void onClick() {
        if (NetworkUtil.isNetAvailable(getActivity())) {
            startAudioVideoCall(avChatType);
        } else {
            Toast.makeText(getActivity(), R.string.network_is_not_available, Toast.LENGTH_SHORT).show();
        }
    }

    /************************ 音视频通话 ***********************/

    public void startAudioVideoCall(AVChatType avChatType) {
//        AVChatActivity.launch(getActivity(), getAccount(), avChatType.getValue(), AVChatActivity.FROM_INTERNAL);
    }
}
