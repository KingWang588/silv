package com.yhy.hzzll.receiver;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.alibaba.sdk.android.push.CloudPushService;
import com.alibaba.sdk.android.push.MessageReceiver;
import com.alibaba.sdk.android.push.noonesdk.PushServiceFactory;
import com.alibaba.sdk.android.push.notification.CPushMessage;

import com.netease.nim.uikit.business.session.constant.Extras;
import com.yhy.hzzll.home.activity.consult.ConsultDetailsActivity;
import com.yhy.hzzll.home.activity.consult.PursueAskActivity;
import com.yhy.hzzll.message.MessageDetailHuaZhaiActivity;
import com.yhy.hzzll.message.MyNewMessageActivity;
import com.yhy.hzzll.my.activity.AboutActivity;
import com.yhy.hzzll.session.SessionHelper;
import com.yhy.hzzll.utils.ToastUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

/**
 * @author: 正纬
 * @since: 15/4/9
 * @version: 1.1
 * @feature: 用于接收推送的通知和消息
 */
public class MyMessageReceiver extends MessageReceiver {

    // 消息接收部分的LOG_TAG
    public static final String REC_TAG = "receiver";

    /**
     * 推送通知的回调方法
     *
     * @param context
     * @param title
     * @param summary
     * @param extraMap
     */
    @Override
    public void onNotification(Context context, String title, String summary, Map<String, String> extraMap) {
        // TODO 处理推送通知
        if (null != extraMap) {
            for (Map.Entry<String, String> entry : extraMap.entrySet()) {
                Log.e(REC_TAG, "@Get diy param : Key=" + entry.getKey() + " , Value=" + entry.getValue());
            }
        } else {
            Log.e(REC_TAG, "@收到通知 && 自定义消息为空");
        }
        Log.e(REC_TAG, "收到一条推送通知 ： " + title);
    }

    @Override
    protected void onNotificationReceivedInApp(Context context, String title, String summary, Map<String, String> extraMap, int openType, String openActivity, String openUrl) {
        Log.e(REC_TAG, "onNotificationReceivedInApp ： " + " : " + title + " : " + summary + "  " + extraMap + " : " + openType + " : " + openActivity + " : " + openUrl);
    }

    /**
     * 推送消息的回调方法    *
     *
     * @param context
     * @param cPushMessage
     */
    @Override
    public void onMessage(final Context context, CPushMessage cPushMessage) {
        try {

            Log.e(REC_TAG, "收到一条推送消息 ： " + cPushMessage.getTitle() + cPushMessage.getContent());

            if (cPushMessage.getContent().equals("版本更新")) {

                ToastUtils.getUtils(context).show("版本更新拉");
//                UpdataVersionDialog enter = new UpdataVersionDialog();
//                enter.showDialog(context, new UpdataVersionDialog.Click() {
//                    @Override
//                    public void click() {
//                        downloadApk(context,"","私律律师端","更新");
//                    }
//                },true,"");

                context.startActivity(new Intent(context, AboutActivity.class));

            }


            // 持久化推送的消息到数据库
//            new MessageDao(context).add(new MessageEntity(cPushMessage.getMessageId().substring(6, 16), Integer.valueOf(cPushMessage.getAppId()), cPushMessage.getTitle(), cPushMessage.getContent(), new SimpleDateFormat("HH:mm:ss").format(new Date())));
//
//            // 刷新下消息列表
//            ActivityBox.CPDMainActivity.initMessageView();
        } catch (Exception e) {
            Log.e(REC_TAG, e.toString());
        }
    }

    /**
     * 从通知栏打开通知的扩展处理
     *
     * @param context
     * @param title
     * @param summary
     * @param extraMap
     */
    @Override
    public void onNotificationOpened(Context context, String title, String summary, String extraMap) {
        CloudPushService cloudPushService = PushServiceFactory.getCloudPushService();
        Log.e(REC_TAG, "onNotificationOpened ： " + " : " + title + " : " + summary + " : " + extraMap);

        try {
            JSONObject object = new JSONObject(extraMap);
            String message_code = object.getString("message_code");


            if (message_code.equals("CONSULT_QUICK")||message_code.equals("ATTEND")){

                String id = object.getString("params_id");

                Intent intent = new Intent(context, ConsultDetailsActivity.class);
                intent.putExtra("id",id);
                intent.putExtra("title",summary);
                intent.putExtra("insert",false);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);

            }else  if (message_code.equals("REPLY_PURSUE")){

                String id = object.getString("params_id");

                String reply_id = object.getString("reply_id");
                String message_id = object.getString("message_id");

                Intent intent = new Intent(context, PursueAskActivity.class);
                intent.putExtra("reply_id",reply_id);
                intent.putExtra("id",id);
                intent.putExtra("message_id",message_id);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);

            }else  if (message_code.equals("CONSULT_EXCLUSIVE")){

                Log.e("============>>>........","执行了。。。。。。。。");
                String accid_id = object.getString("accid_id");

                Intent intent = new Intent();
                intent.putExtra(Extras.EXTRA_ACCOUNT, accid_id);
                intent.putExtra(Extras.EXTRA_CUSTOMIZATION, SessionHelper.getP2pCustomization());
                intent.setClass(context, MyNewMessageActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                context.startActivity(intent);

            }else{
                Intent intent = new Intent(context, MessageDetailHuaZhaiActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }

        } catch (JSONException e) {
            Log.e("+++++++++++","出现问题");
            e.printStackTrace();
        }

    }


    @Override
    public void onNotificationRemoved(Context context, String messageId) {
        Log.e(REC_TAG, "onNotificationRemoved ： " + messageId);
    }


    @Override
    protected void onNotificationClickedWithNoAction(Context context, String title, String summary, String extraMap) {
        Log.e(REC_TAG, "onNotificationClickedWithNoAction ： " + " : " + title + " : " + summary + " : " + extraMap);
    }
}