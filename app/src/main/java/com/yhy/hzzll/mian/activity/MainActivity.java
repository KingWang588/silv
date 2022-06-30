package com.yhy.hzzll.mian.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

import androidx.fragment.app.FragmentTransaction;

import com.alibaba.sdk.android.man.MANService;
import com.alibaba.sdk.android.man.MANServiceProvider;
import com.alibaba.sdk.android.push.CommonCallback;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.Observer;
import com.netease.nimlib.sdk.StatusCode;
import com.netease.nimlib.sdk.auth.AuthService;
import com.netease.nimlib.sdk.auth.AuthServiceObserver;
import com.netease.nimlib.sdk.msg.MsgService;
import com.yhy.hzzll.R;
import com.yhy.hzzll.entity.MessageListEntity;
import com.yhy.hzzll.entity.NotificationMsgEntity;
import com.yhy.hzzll.framework.Constans;
import com.yhy.hzzll.framework.HzApplication;
import com.yhy.hzzll.framework.MyData;
import com.yhy.hzzll.home.fragment.ConsultFragment;
import com.yhy.hzzll.home.fragment.NewHomeFragment;
import com.yhy.hzzll.listener.BackHandlerInterface;
import com.yhy.hzzll.message.MessageFragment;
import com.yhy.hzzll.mian.fragment.BackHandlerFragment;
import com.yhy.hzzll.my.fragment.MyFragment;
import com.yhy.hzzll.utils.CacheUtils;
import com.yhy.hzzll.utils.ClickFilter;
import com.yhy.hzzll.utils.HttpDataUtils;
import com.yhy.hzzll.utils.HttpDataUtils.FailBack;
import com.yhy.hzzll.utils.JSONTool;
import com.yhy.hzzll.utils.LogUtils;
import com.yhy.hzzll.utils.MsgEvent;
import com.yhy.hzzll.utils.PrefsUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

import de.greenrobot.event.EventBus;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class MainActivity extends BaseActivity implements BackHandlerInterface {

    private BackHandlerFragment mBackHandedFragment;
    private ArrayList<MessageListEntity> mList = new ArrayList<MessageListEntity>();
    private ArrayList<NotificationMsgEntity> mNotificationList = new ArrayList<>();
    public static MainActivity mainactivity = null;
    private int time = 0;
    private static final String EXTRA_APP_QUIT = "APP_QUIT";

    // 注销
    public static void logout(Context context, boolean quit) {
        Intent extra = new Intent();
        extra.putExtra(EXTRA_APP_QUIT, quit);
        start(context, extra);
    }

    public static void start(Context context, Intent extras) {
        Intent intent = new Intent();
        intent.setClass(context, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        if (extras != null) {
            intent.putExtras(extras);
        }
        context.startActivity(intent);
    }

    /**
     * 首页
     */
    @ViewInject(R.id.tv_hzhome)
    private TextView tv_hzhome;
    /**
     * 律查查
     */
    @ViewInject(R.id.tv_ask_list)
    private TextView tv_ask_list;

    /**
     * 消息
     */
    @ViewInject(R.id.tv_messages)
    private TextView tv_messages;
    /**
     * 我的
     */
    @ViewInject(R.id.tv_userinfo)
    private TextView tv_userinfo;

    /**
     * 消息数量
     */
    @ViewInject(R.id.tv_msg_number)
    private TextView tv_msg_number;
    private int tab = 0;
    private NewHomeFragment homeFragment;
    private MessageFragment messageFragment;
    private ConsultFragment consultFragment;
    private MyFragment myFragment;
    private Handler mHandler;

    public void setHandler(Handler handler) {
        mHandler = handler;
    }

    /**
     *
     * @param msg 事件
     *
     */
    public void onEventMainThread(MsgEvent msg) {
        if (msg !=null && msg.getMsg() !=null && msg.getMsg().equals("用户咨询")) {
            setTabSelection(1);
//            .setClickable(true);
        }
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                if (Integer.valueOf(msg.obj + "") != 0) {
                    tv_msg_number.setVisibility(View.VISIBLE);
                    if (Integer.valueOf(msg.obj + "") < 99) {
                        tv_msg_number.setText(msg.obj + "");
                    } else {
                        tv_msg_number.setText("99");
                    }
                } else {
                    tv_msg_number.setVisibility(View.GONE);
                }
            } else if (msg.what == 2) {
                if (time <= 0) {
                    tv_ask_list.setClickable(true);
                }
            }
            super.handleMessage(msg);
        }
    };


    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_main2);
        ViewUtils.inject(this);
        mainactivity = this;
        EventBus.getDefault().register(this);
        viewInit();

        int unreadNum = NIMClient.getService(MsgService.class).getTotalUnreadCount();
        Message message = new Message();
        message.what = 1;
        message.obj = unreadNum;
        handler.sendMessage(message);

        if (PrefsUtils.getString(MainActivity.this, PrefsUtils.REFRESH_TOKEN) != null && PrefsUtils.getString(MainActivity.this, PrefsUtils.REFRESH_TOKEN).length() != 0) {
            if (ClickFilter.isRefresh()) {
                tv_hzhome.setClickable(false);
                tv_ask_list.setClickable(false);
                tv_messages.setClickable(false);
                tv_userinfo.setClickable(false);
                refreshToken();
            }
        }

    }


    public void viewInit() {
        tv_msg_number.setVisibility(View.GONE);
        if (null != getIntent()) {
            tab = getIntent().getIntExtra("tab", 0);
            setTabSelection(tab);
        } else {
            setTabSelection(0);
        }
    }


    @OnClick({R.id.tv_hzhome, R.id.tv_ask_list, R.id.tv_messages, R.id.tv_userinfo})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.tv_hzhome:
                if (!((PrefsUtils.getString(context, PrefsUtils.ACCESSKEY) == null || PrefsUtils.getString(context, PrefsUtils.ACCESSKEY).isEmpty()))) {
                    getChatList();
                }
                setTabSelection(0);
                break;
            case R.id.tv_ask_list:
                setTabSelection(1);
                break;

            case R.id.tv_messages:
                if (PrefsUtils.getString(context, PrefsUtils.AUTHORIZATION) == null || PrefsUtils.getString(context, PrefsUtils.AUTHORIZATION).isEmpty()) {
                    startActivity(new Intent().putExtra("tab", 3).putExtra("activity", MainActivity.class.toString()).setClass(context, LoginActivity.class));
                    return;
                }

                setTabSelection(3);
                break;
            case R.id.tv_userinfo:

                setTabSelection(4);
                break;
        }
    }

    @Override
    public void onDestroy() {

        super.onDestroy();
    }

    @Override
    protected void onResume() {

        int unreadNum = NIMClient.getService(MsgService.class).getTotalUnreadCount();
        Message message = new Message();
        message.what = 1;
        message.obj = unreadNum;
        handler.sendMessage(message);

        NIMClient.getService(AuthServiceObserver.class).observeOnlineStatus(
                new Observer<StatusCode>() {
                    public void onEvent(StatusCode status) {
                        if (status.wontAutoLogin()) {

                            PrefsUtils.saveString(context, PrefsUtils.AUTHORIZATION, "");
                            PrefsUtils.saveString(context, PrefsUtils.REFRESH_TOKEN, "");
                            NIMClient.getService(AuthService.class).logout();
                            startActivity(new Intent(MainActivity.this, LoginActivity.class).putExtra("show",true));
                            finish();

//                            final HzApplication hzApplication = (HzApplication) getApplication();
//
//                            if (hzApplication.getIndex()==2){
//                                unbind(hzApplication.getPushService().getDeviceId());
//                            }

                        }
                    }
                }, true);

        super.onResume();
    }

    private void loginOut() {

        NIMClient.getService(AuthService.class).logout();

        String username = PrefsUtils.getString(context, PrefsUtils.USERNAME) + "";
        String userpassword = PrefsUtils.getString(context, PrefsUtils.USERPASSROWD) + "";
        String isremember = PrefsUtils.getString(context, PrefsUtils.ISREMEMBER) + "";

        MANService manService = MANServiceProvider.getService();
        manService.getMANAnalytics().updateUserAccount(PrefsUtils.getString(MainActivity.this, PrefsUtils.UNAME), PrefsUtils.getString(MainActivity.this, PrefsUtils.UID));

        PrefsUtils.saveString(context, PrefsUtils.USERNAME, username);
        PrefsUtils.saveString(context, PrefsUtils.USERPASSROWD, userpassword);
        PrefsUtils.saveString(context, PrefsUtils.ISREMEMBER, isremember);
        PrefsUtils.saveString(context, PrefsUtils.UID, "");
        PrefsUtils.saveString(context, PrefsUtils.OLD_UID, "");

        CacheUtils.dataUserEntity = null;
        HzApplication.getInstance().getUserEntityCache().put(Constans.USER_INFO, CacheUtils.dataUserEntity, 86400);
        final HzApplication hzApplication = (HzApplication) getApplication();
        hzApplication.getPushService().unbindAccount(new CommonCallback() {
            @Override
            public void onSuccess(String s) {
                unbind(hzApplication.getPushService().getDeviceId());
            }

            @Override
            public void onFailed(String s, String s1) {
                PrefsUtils.saveString(context, PrefsUtils.AUTHORIZATION, "");
                PrefsUtils.saveString(context, PrefsUtils.REFRESH_TOKEN, "");
            }
        });
    }

    private void unbind(String id) {
        RequestParams params = new RequestParams();
        params.addHeader("Authorization", PrefsUtils.getString(getApplicationContext(), PrefsUtils.AUTHORIZATION));
        params.addHeader("Content-Type", "application/x-www-form-urlencoded");
        params.addQueryStringParameter("device_id", id);
        HttpUtils httpUtils = new HttpUtils();
        httpUtils.send(HttpMethod.DELETE, MyData.DEVICE, params, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                PrefsUtils.saveString(context, PrefsUtils.AUTHORIZATION, "");
                PrefsUtils.saveString(context, PrefsUtils.REFRESH_TOKEN, "");
                NIMClient.getService(AuthService.class).logout();
                startActivity(new Intent(MainActivity.this, LoginActivity.class).putExtra("show",true));
                finish();
            }

            @Override
            public void onFailure(HttpException e, String s) {

            }
        });
    }

    /**
     * 根据传入的index参数来设置选中的tab页。
     *
     * @param index 每个tab页对应的下标。0表示消息，1表示联系人，2表示动态，3表示设置。
     */
    private void setTabSelection(int index) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        hideFragments(transaction);
        switch (index) {
            case 0:
                clearSelection();
                tv_hzhome.setSelected(true);
                if (homeFragment == null) {
                    homeFragment = new NewHomeFragment();
                    transaction.add(R.id.content, homeFragment);
                } else {
                    transaction.show(homeFragment);
                }
                transaction.commit();
                break;
            case 1:

                clearSelection();
                tv_ask_list.setSelected(true);
                if (consultFragment == null) {
                    consultFragment = new ConsultFragment();
                    transaction.add(R.id.content, consultFragment);
                } else {
                    transaction.show(consultFragment);
                }
                transaction.commit();


//                startActivity(new Intent().setClass(MainActivity.this, ConsultActivity.class));
//                Date dt = new Date();
//                Long time = dt.getTime();
//                PrefsUtils.saveString(MainActivity.this, PrefsUtils.CLICKCONSULT, String.valueOf(time));

                break;

            case 3:
                clearSelection();
                tv_messages.setSelected(true);
                if (messageFragment == null) {
                    messageFragment = new MessageFragment();
                    transaction.add(R.id.content, messageFragment);
                } else {
                    transaction.show(messageFragment);
                }
                transaction.commit();
                EventBus.getDefault().post(new MsgEvent(2333));
                break;
            case 4:
                clearSelection();
                tv_userinfo.setSelected(true);
                if (myFragment == null) {
                    myFragment = new MyFragment();
                    transaction.add(R.id.content, myFragment);
                } else {
                    transaction.show(myFragment);
                }
                transaction.commit();
                EventBus.getDefault().post(new MsgEvent(2444));
                break;
        }
    }

    /**
     * 将所有的Fragment都置为隐藏状态。
     *
     * @param transaction 用于对Fragment执行操作的事务
     */
    private void hideFragments(FragmentTransaction transaction) {

        if (homeFragment != null) {
            transaction.hide(homeFragment);
        }

        if (consultFragment!=null){
            transaction.hide(consultFragment);
        }

        if (messageFragment != null) {
            transaction.hide(messageFragment);
        }
        if (myFragment != null) {
            transaction.hide(myFragment);
        }
    }

    /**
     * 清除掉所有的选中状态。
     */
    private void clearSelection() {
        tv_hzhome.setSelected(false);
        tv_ask_list.setSelected(false);
        tv_messages.setSelected(false);
        tv_userinfo.setSelected(false);
    }

    @Override
    public void setSelectedFragment(BackHandlerFragment selectedFragment) {
        this.mBackHandedFragment = selectedFragment;
    }

    @Override
    public void onBackPressed() {
        if (mBackHandedFragment == null || !mBackHandedFragment.onBackPressed()) {
            if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
                super.onBackPressed();
            } else {
                getSupportFragmentManager().popBackStack();
            }
        }
    }

    private void getChatList() {
        mNotificationList.clear();
        mList.clear();
        RequestParams params = new RequestParams();
        params.addHeader("Authorization", PrefsUtils.getString(MainActivity.this, PrefsUtils.ACCESSKEY));
        params.addQueryStringParameter("page", 1 + "");
        httpDataUtils.sendGet(MyData.CHAT_LIST, params);
        httpDataUtils.setSucessBack(new HttpDataUtils.SucessBack() {

            @Override
            public void sucess(ResponseInfo<String> arg0) {

                try {
                    JSONObject object = new JSONObject(arg0.result);
                    String code = JSONTool.getString(object, "code");
                    String msg = JSONTool.getString(object, "msg");
                    if (code.equals(Constans.SUCCESS_CODE)) { // 成功
                        JSONObject data = JSONTool.getJsonObject(object, "data");
                        String pages = JSONTool.getString(data, "pages");
                        JSONArray list = JSONTool.getJsonArray(data, "lawerchat");
                        JSONArray list2 = JSONTool.getJsonArray(data, "notification");
                        Type type2 = new TypeToken<ArrayList<NotificationMsgEntity>>() {
                        }.getType();
                        mNotificationList
                                .addAll((ArrayList<NotificationMsgEntity>) gson.fromJson(list2.toString(), type2));
                        Type type = new TypeToken<ArrayList<MessageListEntity>>() {
                        }.getType();
                        mList.addAll((ArrayList<MessageListEntity>) gson.fromJson(list.toString(), type));

                        int notifitymsgcount = mNotificationList.get(0).getNewmsg().getCount();

                        int msgcount = 0;

                        for (int i = 0; i < mList.size(); i++) {
                            msgcount = msgcount + mList.get(i).getNewmsg().size();
                        }

                        Message message = new Message();
                        message.what = 1;
                        message.obj = msgcount + notifitymsgcount;
                        handler.sendMessage(message);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        httpDataUtils.setFailBack(new FailBack() {
            @Override
            public void fail(String msg) {

            }
        });
    }


    //计时线程
    class TimeThread extends Thread {
        @Override
        public void run() {

            while (time >= 0) {
                handler.sendEmptyMessage(2);
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                time -= 1;
            }

        }
    }

    private final int BASIC_PERMISSION_REQUEST_CODE = 100;

    /**
     * 基本权限管理
     */
//    private final String[] BASIC_PERMISSIONS = new String[]{
//            Manifest.permission.WRITE_EXTERNAL_STORAGE,
//            Manifest.permission.READ_EXTERNAL_STORAGE,
//            Manifest.permission.CAMERA,
//            Manifest.permission.RECORD_AUDIO,
//    };

//    private void requestBasicPermission() {
//        MPermission.printMPermissionResult(true, MainActivity.this, BASIC_PERMISSIONS);
//        MPermission.with(MainActivity.this)
//                .setRequestCode(BASIC_PERMISSION_REQUEST_CODE)
//                .permissions(BASIC_PERMISSIONS)
//                .request();
//    }

//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        MPermission.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
//    }

//    @OnMPermissionGranted(BASIC_PERMISSION_REQUEST_CODE)
//    public void onBasicPermissionSuccess() {
//        MPermission.printMPermissionResult(false, MainActivity.this, BASIC_PERMISSIONS);
//    }

//    @OnMPermissionDenied(BASIC_PERMISSION_REQUEST_CODE)
//    public void onBasicPermissionFailed() {
//        Toast.makeText(MainActivity.this, "未全部授权，部分功能可能无法正常运行！", Toast.LENGTH_SHORT).show();
//        MPermission.printMPermissionResult(false, MainActivity.this, BASIC_PERMISSIONS);
//    }

    private void refreshToken() {

        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody formBody = new FormBody.Builder()
                .add("grant_type", "refresh_token")
                .add("client_id", "4")
                .add("client_secret", "kkRwNTASYx001U2yn8PIYOZxGYO422YQ9mk2NbXg")
                .add("refresh_token", PrefsUtils.getString(MainActivity.this, PrefsUtils.REFRESH_TOKEN))
                .add("scope", "")
                .build();
        Request request = new Request.Builder()
                .url(MyData.NEW_LOGIN)
                .post(formBody)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                final String res = response.body().string();
                LogUtils.logE(res);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {

                            JSONObject object = new JSONObject(res);
                            String message = object.optString("message");
                            String token_type = object.optString("token_type");
                            String access_token = object.optString("access_token");
                            String refresh_token = object.optString("refresh_token");

                            if (message != null & !message.equals("")) {
                                PrefsUtils.saveString(MainActivity.this, PrefsUtils.AUTHORIZATION, "");
                                PrefsUtils.saveString(MainActivity.this, PrefsUtils.REFRESH_TOKEN, "");

                                tv_hzhome.setClickable(true);
                                tv_ask_list.setClickable(true);
                                tv_messages.setClickable(true);
                                tv_userinfo.setClickable(true);

                            } else {
                                LogUtils.logE(token_type + "/////" + access_token);
                                PrefsUtils.saveString(MainActivity.this, PrefsUtils.AUTHORIZATION, token_type + " " + access_token);
                                PrefsUtils.saveString(MainActivity.this, PrefsUtils.REFRESH_TOKEN, refresh_token);

                                tv_hzhome.setClickable(true);
                                tv_ask_list.setClickable(true);
                                tv_messages.setClickable(true);
                                tv_userinfo.setClickable(true);
                            }

                        } catch (Exception e) {

                        }

                    }
                });
            }
        });

    }
}
