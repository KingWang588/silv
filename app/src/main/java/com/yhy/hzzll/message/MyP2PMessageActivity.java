package com.yhy.hzzll.message;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import com.netease.nim.uikit.api.NimUIKit;
import com.netease.nim.uikit.api.model.session.SessionCustomization;
import com.netease.nim.uikit.api.model.user.UserInfoObservable;
import com.netease.nim.uikit.business.session.activity.BaseMessageActivity;
import com.netease.nim.uikit.business.session.constant.Extras;
import com.netease.nim.uikit.business.session.fragment.MessageFragment;
import com.netease.nim.uikit.business.uinfo.UserInfoHelper;
import com.netease.nim.uikit.common.activity.ToolBarOptions;
import com.netease.nim.uikit.impl.cache.FriendDataCache;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.Observer;
import com.netease.nimlib.sdk.msg.MsgServiceObserve;
import com.netease.nimlib.sdk.msg.constant.SessionTypeEnum;
import com.netease.nimlib.sdk.msg.model.CustomNotification;
import com.netease.nimlib.sdk.msg.model.IMMessage;
import com.yhy.hzzll.R;
import com.yhy.hzzll.session.fragment.MyMessageFragment;

import java.util.List;
import java.util.Set;


public class MyP2PMessageActivity {
//        extends BaseMessageActivity {

//    private static String contactID;
//    private boolean isResume = false;
//
//    public static void start(Context context, String contactId, SessionCustomization customization, IMMessage anchor) {
//        Intent intent = new Intent();
//        contactID = contactId;
//        intent.putExtra(Extras.EXTRA_ACCOUNT, contactId);
//        intent.putExtra(Extras.EXTRA_CUSTOMIZATION, customization);
//        if (anchor != null) {
//            intent.putExtra(Extras.EXTRA_ANCHOR, anchor);
//        }
//        intent.setClass(context, MyP2PMessageActivity.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
//
//        context.startActivity(intent);
//    }
//
//    @Override
//    protected MessageFragment fragment() {
//        return null;
//    }
//
//    @Override
//    protected int getContentViewId() {
//        return 0;
//    }
//
//    @Override
//    protected void initToolBar() {
//
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        // 单聊特例话数据，包括个人信息，
//        requestBuddyInfo();
//        displayOnlineState();
//        registerObservers(true);
//        registerOnlineStateChangeListener(true);
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        registerObservers(false);
//        registerOnlineStateChangeListener(false);
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        isResume = true;
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//        isResume = false;
//    }
//
//    private void requestBuddyInfo() {
//        setTitle(UserInfoHelper.getUserTitleName(sessionId, SessionTypeEnum.P2P));
//    }
//
//    private void registerObservers(boolean register) {
//        if (register) {
////            registerUserInfoObserver();
//        } else {
////            unregisterUserInfoObserver();
//        }
////        NIMClient.getService(MsgServiceObserve.class).observeCustomNotification(commandObserver, register);
////        FriendDataCache.getInstance().registerFriendDataChangedObserver(friendDataChangedObserver, register);
//    }
//
////    FriendDataCache.FriendDataChangedObserver friendDataChangedObserver = new FriendDataCache.FriendDataChangedObserver() {
//        @Override
//        public void onAddedOrUpdatedFriends(List<String> accounts) {
//            setTitle(UserInfoHelper.getUserTitleName(sessionId, SessionTypeEnum.P2P));
//        }
//
//        @Override
//        public void onDeletedFriends(List<String> accounts) {
//            setTitle(UserInfoHelper.getUserTitleName(sessionId, SessionTypeEnum.P2P));
//        }
//
//        @Override
//        public void onAddUserToBlackList(List<String> account) {
//            setTitle(UserInfoHelper.getUserTitleName(sessionId, SessionTypeEnum.P2P));
//        }
//
//        @Override
//        public void onRemoveUserFromBlackList(List<String> account) {
//            setTitle(UserInfoHelper.getUserTitleName(sessionId, SessionTypeEnum.P2P));
//        }
//    };
//
//    private UserInfoObservable.UserInfoObserver uinfoObserver;
//
//    OnlineStateChangeListener onlineStateChangeListener = new OnlineStateChangeListener() {
//        @Override
//        public void onlineStateChange(Set<String> accounts) {
//            // 更新 toolbar
//            if (accounts.contains(sessionId)) {
//                // 按照交互来展示
//                displayOnlineState();
//            }
//        }
//    };
//
//    private void registerOnlineStateChangeListener(boolean register) {
//        if (!NimUIKit.enableOnlineState()) {
//            return;
//        }
//        if (register) {
//            NimUIKit.addOnlineStateChangeListeners(onlineStateChangeListener);
//        } else {
//            NimUIKit.removeOnlineStateChangeListeners(onlineStateChangeListener);
//        }
//    }
//
//    private void displayOnlineState() {
//        if (!NimUIKit.enableOnlineState()) {
//            return;
//        }
//        String detailContent = NimUIKit.getOnlineStateContentProvider().getDetailDisplay(sessionId);
//        setSubTitle(detailContent);
//    }
//
//    private void registerUserInfoObserver() {
//        if (uinfoObserver == null) {
//            uinfoObserver = new UserInfoObservable.UserInfoObserver() {
//                @Override
//                public void onUserInfoChanged(List<String> accounts) {
//                    if (accounts.contains(sessionId)) {
//                        requestBuddyInfo();
//                    }
//                }
//            };
//        }
//
//        UserInfoHelper.registerObserver(uinfoObserver);
//    }
//
//    private void unregisterUserInfoObserver() {
//        if (uinfoObserver != null) {
//            UserInfoHelper.unregisterObserver(uinfoObserver);
//        }
//    }
//
//    /**
//     * 命令消息接收观察者
//     */
//    Observer<CustomNotification> commandObserver = new Observer<CustomNotification>() {
//        @Override
//        public void onEvent(CustomNotification message) {
//            if (!sessionId.equals(message.getSessionId()) || message.getSessionType() != SessionTypeEnum.P2P) {
//                return;
//            }
//            showCommandMessage(message);
//        }
//    };
//
//    protected void showCommandMessage(CustomNotification message) {
//        if (!isResume) {
//            return;
//        }
//
//        String content = message.getContent();
//        try {
//            JSONObject json = JSON.parseObject(content);
//            int id = json.getIntValue("id");
//            if (id == 1) {
//                // 正在输入
//                Toast.makeText(MyP2PMessageActivity.this, "对方正在输入...", Toast.LENGTH_LONG).show();
//            } else {
//                Toast.makeText(MyP2PMessageActivity.this, "command: " + content, Toast.LENGTH_SHORT).show();
//            }
//
//        } catch (Exception e) {
//
//        }
//    }

//    @Override
////    protected MessageFragment fragment() {
////        Bundle arguments = getIntent().getExtras();
////        arguments.putSerializable(Extras.EXTRA_TYPE, SessionTypeEnum.P2P);
////        MyMessageFragment fragment = new MyMessageFragment();
////        fragment.setArguments(arguments);
////        fragment.setContainerId(R.id.message_fragment_container);
////        return fragment;
////    }
//
//    @Override
//    protected int getContentViewId() {
//        return R.layout.nim_message_activity;
//    }
//
//    @Override
//    protected void initToolBar() {
//        ToolBarOptions options = new ToolBarOptions();
//        setToolBar(R.id.toolbar, options);
//    }
}
