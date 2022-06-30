package com.yhy.hzzll.session.fragment;

import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.netease.nim.uikit.api.NimUIKit;
import com.netease.nim.uikit.api.model.main.CustomPushContentProvider;
import com.netease.nim.uikit.api.model.session.SessionCustomization;
import com.netease.nim.uikit.business.session.constant.Extras;
import com.netease.nim.uikit.business.session.fragment.MessageFragment;
import com.netease.nim.uikit.business.session.module.Container;
import com.netease.nim.uikit.business.session.module.input.InputPanel;
import com.netease.nim.uikit.business.session.module.list.MessageListPanelEx;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.Observer;
import com.netease.nimlib.sdk.msg.MsgService;
import com.netease.nimlib.sdk.msg.MsgServiceObserve;
import com.netease.nimlib.sdk.msg.constant.SessionTypeEnum;
import com.netease.nimlib.sdk.msg.model.IMMessage;
import com.netease.nimlib.sdk.msg.model.MessageReceipt;
import com.yhy.hzzll.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by chengying on 2017/8/29.
 */

public class MyMessageFragment extends MessageFragment {

//    private View rootView;
//    private TextView tv_date_time;
//
//    private SessionCustomization customization;
//
//    protected static final String TAG = "MessageActivity";
//
//    // 聊天对象
//    protected String sessionId; // p2p对方Account或者群id
//
//    protected SessionTypeEnum sessionType;
//
//    // modules
//    protected InputPanel inputPanel;
//    protected MessageListPanelEx messageListPanel;
//
//    @Override
//    public void onActivityCreated(Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        parseIntent();
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        rootView = inflater.inflate(R.layout.nim_message_fragment, container, false);
//        tv_date_time = (TextView) rootView.findViewById(R.id.tv_date_time);
//
//        tv_date_time.setText("212313212");
//
//        return rootView;
//
//    }
//
//    /**
//     *
//     * ***************************** life cycle *******************************
//     *
//     */
//
//    @Override
//    public void onPause() {
//        super.onPause();
//
//        NIMClient.getService(MsgService.class).setChattingAccount(MsgService.MSG_CHATTING_ACCOUNT_NONE, SessionTypeEnum.None);
//        inputPanel.onPause();
//        messageListPanel.onPause();
//
//    }
//
//    @Override
//    public void onResume() {
//        super.onResume();
//        messageListPanel.onResume();
//        NIMClient.getService(MsgService.class).setChattingAccount(sessionId, sessionType);
//        getActivity().setVolumeControlStream(AudioManager.STREAM_VOICE_CALL); // 默认使用听筒播放
//    }
//
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        messageListPanel.onDestroy();
//        registerObservers(false);
//        if (inputPanel != null) {
//            inputPanel.onDestroy();
//        }
//    }
//
//    public boolean onBackPressed() {
//        if (inputPanel.collapse(true)) {
//            return true;
//        }
//
//        if (messageListPanel.onBackPressed()) {
//            return true;
//        }
//        return false;
//    }
//
//    public void refreshMessageList() {
//        messageListPanel.refreshMessageList();
//    }
//
//    private void parseIntent() {
//        sessionId = getArguments().getString(Extras.EXTRA_ACCOUNT);
//        sessionType = (SessionTypeEnum) getArguments().getSerializable(Extras.EXTRA_TYPE);
//        IMMessage anchor = (IMMessage) getArguments().getSerializable(Extras.EXTRA_ANCHOR);
//
//        customization = (SessionCustomization) getArguments().getSerializable(Extras.EXTRA_CUSTOMIZATION);
//        Container container = new Container(getActivity(), sessionId, sessionType, this);
//
//        if (messageListPanel == null) {
//            messageListPanel = new MessageListPanelEx(container, rootView, anchor, false, false);
//        } else {
//            messageListPanel.reload(container, anchor);
//        }
//
//        if (inputPanel == null) {
//            inputPanel = new InputPanel(container, rootView, getActionList());
//            inputPanel.setCustomization(customization);
//        } else {
//            inputPanel.reload(container, customization);
//        }
//
//        registerObservers(true);
//
//        if (customization != null) {
//            messageListPanel.setChattingBackground(customization.backgroundUri, customization.backgroundColor);
//        }
//    }
//
//    /**
//     * ************************* 消息收发 **********************************
//     */
//    // 是否允许发送消息
//    protected boolean isAllowSendMessage(final IMMessage message) {
//        return true;
//    }
//
//    /**
//     * ****************** 观察者 **********************
//     */
//
//    private void registerObservers(boolean register) {
//        MsgServiceObserve service = NIMClient.getService(MsgServiceObserve.class);
//        service.observeReceiveMessage(incomingMessageObserver, register);
//        service.observeMessageReceipt(messageReceiptObserver, register);
//    }
//
//    /**
//     * 消息接收观察者
//     */
//    Observer<List<IMMessage>> incomingMessageObserver = new Observer<List<IMMessage>>() {
//        @Override
//        public void onEvent(List<IMMessage> messages) {
//            if (messages == null || messages.isEmpty()) {
//                return;
//            }
//
//            messageListPanel.onIncomingMessage(messages);
//            sendMsgReceipt(); // 发送已读回执
//        }
//    };
//
//    private Observer<List<MessageReceipt>> messageReceiptObserver = new Observer<List<MessageReceipt>>() {
//        @Override
//        public void onEvent(List<MessageReceipt> messageReceipts) {
//            receiveReceipt();
//        }
//    };
//
//
//    /**
//     * ********************** implements ModuleProxy *********************
//     */
//    @Override
//    public boolean sendMessage(IMMessage message) {
//        if (!isAllowSendMessage(message)) {
//            return false;
//        }
//        appendPushConfig(message);
//        // send message to server and save to db
//        NIMClient.getService(MsgService.class).sendMessage(message, false);
//        messageListPanel.onMsgSend(message);
//        return true;
//    }
//
//    private void appendPushConfig(IMMessage message) {
//        CustomPushContentProvider customConfig = NimUIKit.getCustomPushContentProvider();
//        if (customConfig != null) {
//            String content = customConfig.getPushContent(message);
//            Map<String, Object> payload = customConfig.getPushPayload(message);
//            message.setPushContent(content);
//            message.setPushPayload(payload);
//        }
//    }
//
//    @Override
//    public void onInputPanelExpand() {
//        messageListPanel.scrollToBottom();
//    }
//
//    @Override
//    public void shouldCollapseInputPanel() {
//        inputPanel.collapse(false);
//    }
//
//    @Override
//    public boolean isLongClickEnabled() {
//        return !inputPanel.isRecording();
//    }
//
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        inputPanel.onActivityResult(requestCode, resultCode, data);
//        messageListPanel.onActivityResult(requestCode, resultCode, data);
//    }
//
//    // 操作面板集合
//    protected List<BaseAction> getActionList() {
//        List<BaseAction> actions = new ArrayList<>();
//        actions.add(new ImageAction());
////        actions.add(new VideoAction());
//        actions.add(new LocationAction());
//
//        if (customization != null && customization.actions != null) {
//            actions.addAll(customization.actions);
//        }
//        return actions;
//    }
//
//    /**
//     * 发送已读回执
//     */
//    private void sendMsgReceipt() {
//        messageListPanel.sendReceipt();
//    }
//
//    /**
//     * 收到已读回执
//     */
//    public void receiveReceipt() {
//        messageListPanel.receiveReceipt();
//    }

}
