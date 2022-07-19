package com.yhy.hzzll.framework;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;

import androidx.multidex.MultiDex;

import com.alibaba.sdk.android.ams.common.global.AmsGlobalHolder;
import com.alibaba.sdk.android.man.MANService;
import com.alibaba.sdk.android.man.MANServiceProvider;
import com.alibaba.sdk.android.push.CloudPushService;
import com.alibaba.sdk.android.push.CommonCallback;
import com.alibaba.sdk.android.push.noonesdk.PushServiceFactory;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.liulishuo.filedownloader.FileDownloader;
import com.liulishuo.filedownloader.connection.FileDownloadUrlConnection;
import com.netease.nim.uikit.api.NimUIKit;
import com.netease.nim.uikit.api.UIKitOptions;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.SDKOptions;
import com.netease.nimlib.sdk.StatusBarNotificationConfig;
import com.netease.nimlib.sdk.auth.LoginInfo;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.UsingFreqLimitedMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.umeng.socialize.PlatformConfig;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;
import com.yhy.hzzll.R;
import com.yhy.hzzll.config.preference.Preferences;
import com.yhy.hzzll.contact.ContactHelper;
import com.yhy.hzzll.event.DemoOnlineStateContentProvider;
import com.yhy.hzzll.gen.DaoMaster;
import com.yhy.hzzll.gen.DaoSession;
import com.yhy.hzzll.message.MyNewMessageActivity;
import com.yhy.hzzll.mian.activity.MainActivity;
import com.yhy.hzzll.session.SessionHelper;
import com.yhy.hzzll.utils.DataCache;
import com.yhy.hzzll.utils.HttpDataUtils;
import com.yhy.hzzll.utils.LogUtils;
import com.yhy.hzzll.utils.PrefsUtils;
import com.yhy.hzzll.utils.sys.SystemUtil;
import java.io.File;
import java.util.UUID;

import static com.yhy.hzzll.config.NimSDKOptionConfig.getAppCacheDir;


/**
 * @author
 * @version V1.0
 * @Description: 类描述
 * @date 2015-10-6
 */
public class HzApplication extends Application {
    private static String HZACTION="HzApplication.receiver";
    public static DataCache userEntityCache;
    public static String cachePath = null;
    CloudPushService pushService;
    private static DaoSession daoSession;
    public CloudPushService getPushService() {
        return pushService;
    }

    int index = 1;

    public void setIndex(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    @Override
    public void onCreate() {
        PushServiceFactory.init(this);
        Log.e("kunkun","用户未同意隐私政策，PushServiceFactory.init()");
        initAllSDK();

        super.onCreate();
        IntentFilter filter = new IntentFilter(HZACTION);
        registerReceiver(receiver, filter);
    }

    private void initAllSDK(){
        ApplicationProvider.IMPL.init(this);
        DemoCache.setContext(this);
        setupDatabase();
        imageLoaderInit();
        init();


        boolean isAgreePrivacy= PrefsUtils.getIsAgreePrivacyBoolean(this);

        if (!isAgreePrivacy){
            NIMClient.config(this, getLoginInfo(), options());
            Log.e("kunkun","用户未同意隐私政策，NIMClient.config()");
        }else{
            NIMClient.init(getApplicationContext(), loginInfo(), options());
            if (inMainProcess(getApplicationContext())) {
                initUiKit();
            }
            Log.e("kunkun","用户已经同意隐私政策：初始化：NIMClient.init()");
            initCloudChannel(this);
            Log.e("kunkun","用户已经同意隐私政策：初始化：initCloudChannel()");
        }

        DemoCache.setContext(this);

        ZXingLibrary.initDisplayOpinion(this);
        FileDownloader.setupOnApplicationOnCreate(this)
                .connectionCreator(new FileDownloadUrlConnection
                        .Creator(new FileDownloadUrlConnection.Configuration()
                        .connectTimeout(15_000) // set connection timeout.
                        .readTimeout(15_000) // set read timeout.
                ))
                .commit();

    }


    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(HZACTION)){
                initCloudChannel(getApplicationContext());
                Log.e("kunkun","用户已经同意隐私政策：初始化：initCloudChannel()");
            }
        }
    };


    private void initUiKit() {

        // 初始化
        NimUIKit.init(this);

        // 可选定制项
        // 注册定位信息提供者类（可选）,如果需要发送地理位置消息，必须提供。
        // demo中使用高德地图实现了该提供者，开发者可以根据自身需求，选用高德，百度，google等任意第三方地图和定位SDK。
//        NimUIKit.setLocationProvider(new NimDemoLocationProvider());

        // 会话窗口的定制: 示例代码可详见demo源码中的SessionHelper类。
        // 1.注册自定义消息附件解析器（可选）
        // 2.注册各种扩展消息类型的显示ViewHolder（可选）
        // 3.设置会话中点击事件响应处理（一般需要）
        SessionHelper.init();

        // 通讯录列表定制：示例代码可详见demo源码中的ContactHelper类。
        // 1.定制通讯录列表中点击事响应处理（一般需要，UIKit 提供默认实现为点击进入聊天界面)
        ContactHelper.init();

        // 在线状态定制初始化。
        NimUIKit.setOnlineStateContentProvider(new DemoOnlineStateContentProvider());
        //设置是否听筒模式
        NimUIKit.setEarPhoneModeEnable(false);
    }




    // 如果返回值为 null，则全部使用默认参数。
    private SDKOptions options() {
        SDKOptions options = new SDKOptions();

        // 如果将新消息通知提醒托管给 SDK 完成，需要添加以下配置。否则无需设置。
        StatusBarNotificationConfig config = new StatusBarNotificationConfig();
        config.notificationEntrance = MyNewMessageActivity.class; // 点击通知栏跳转到该Activity
        config.notificationSmallIconId = R.drawable.app;
        // 呼吸灯配置
        config.ledARGB = Color.GREEN;
        config.ledOnMs = 1000;
        config.ledOffMs = 1500;
        // 通知铃声的uri字符串
        config.notificationSound = "android.resource://com.netease.nim.demo/raw/msg";
        options.statusBarNotificationConfig = config;

        // 配置保存图片，文件，log 等数据的目录
        // 如果 options 中没有设置这个值，SDK 会使用采用默认路径作为 SDK 的数据目录。
        // 该目录目前包含 log, file, image, audio, video, thumb 这6个目录。
        String sdkPath = getAppCacheDir(this) + "/nim"; // 可以不设置，那么将采用默认路径
        // 如果第三方 APP 需要缓存清理功能， 清理这个目录下面个子目录的内容即可。
        options.sdkStorageRootPath = sdkPath;

        // 配置是否需要预下载附件缩略图，默认为 true
        options.preloadAttach = true;

        // 配置附件缩略图的尺寸大小。表示向服务器请求缩略图文件的大小
        // 该值一般应根据屏幕尺寸来确定， 默认值为 Screen.width / 2
        options.thumbnailSize = 480 / 2;
        return options;
    }



    private UIKitOptions buildUIKitOptions() {
        UIKitOptions options = new UIKitOptions();
        // 设置app图片/音频/日志等缓存目录
        options.appCacheDir = getAppCacheDir(this) + "/app";
        return options;
    }



    /**
     * 配置数据库
     */
    private void setupDatabase() {
        //创建数据库shop.db"
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "shop.db", null);
        //获取可写数据库
        SQLiteDatabase db = helper.getWritableDatabase();
        //获取数据库对象
        DaoMaster daoMaster = new DaoMaster(db);
        //获取Dao对象管理者
        daoSession = daoMaster.newSession();
    }

    public static DaoSession getDaoInstant() {
        return daoSession;
    }


    private LoginInfo getLoginInfo() {
        String account = Preferences.getUserAccount();
        String token = Preferences.getUserToken();

        if (!TextUtils.isEmpty(account) && !TextUtils.isEmpty(token)) {
            DemoCache.setAccount(account.toLowerCase());
            return new LoginInfo(account, token);
        } else {
            return null;
        }
    }


    public static boolean inMainProcess(Context context) {
        String packageName = context.getPackageName();
        String processName = SystemUtil.getProcessName(context);
        return packageName.equals(processName);
    }


    /**
     * 获取当前进程名
     *
     * @param context
     * @return 进程名
     */


    public static final String getProcessName(Context context) {
        String processName = null;

        // ActivityManager
        ActivityManager am = ((ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE));

        while (true) {
            for (ActivityManager.RunningAppProcessInfo info : am.getRunningAppProcesses()) {
                if (info.pid == android.os.Process.myPid()) {
                    processName = info.processName;
                    break;
                }
            }

            // go home
            if (!TextUtils.isEmpty(processName)) {
                return processName;
            }

            // take a rest and again
            try {
                Thread.sleep(100L);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }


    // 如果已经存在用户登录信息，返回LoginInfo，否则返回null即可
    private LoginInfo loginInfo() {

        String account = PrefsUtils.getString(HzApplication.this, PrefsUtils.ACCID);
        String token = PrefsUtils.getString(HzApplication.this, PrefsUtils.USERTOKEN);

        if (!TextUtils.isEmpty(account) && !TextUtils.isEmpty(token)) {
            DemoCache.setAccount(account.toLowerCase());
            NimUIKit.setAccount(account);
            return new LoginInfo(account, token);
        } else {
            return null;
        }

//        return null;
    }

    private void init() {

        cachePath = getCacheDir().getPath();
        if (null == cachePath) {
            String cachePaths = getApplicationContext().getFilesDir()
                    .getAbsolutePath() + getPackageName() + "cache";
            new File(cachePaths).mkdirs();
            cachePath = cachePaths;
        }
        // 获取缓存本地存储的路径
        userEntityCache = DataCache.get(new File(cachePath
                + Constans.LOGIN_ENTITY_CACHE_PATH));

    }

    // 各个平台的配置，建议放在全局Application或者程序入口
    {
        // 微信 wx12342956d1cab4f9,a5ae111de7d9ea137e88a5e02c07c94d
        PlatformConfig.setWeixin("wx54906d22793e6dce", "e06cfa69325dac05047c4c4cec788b99");
        // 新浪微博
        PlatformConfig.setSinaWeibo("526247608", "99fa6b9c71eda8064cd4aeef68db9b0d");
        PlatformConfig.setQQZone("1105689489", "MuzhCwSbpW06r9fh");

    }

    /**
     * 初始化 数据分析
     */


    private void initDataSys() {

	/* 【注意】建议您在Application中初始化MAN，以保证正常获取MANService*/
        // 获取MAN服务
        MANService manService = MANServiceProvider.getService();
        // 打开调试日志，线上版本建议关闭
         manService.getMANAnalytics().turnOnDebug();
        // 设置渠道（用以标记该app的分发渠道名称），如果不关心可以不设置即不调用该接口，渠道设置将影响控制台【渠道分析】栏目的报表展现。如果文档3.3章节更能满足您渠道配置的需求，就不要调用此方法，按照3.3进行配置即可；1.1.6版本及之后的版本，请在init方法之前调用此方法设置channel.
//        manService.getMANAnalytics().setChannel("某渠道");
        // MAN初始化方法之一，从AndroidManifest.xml中获取appKey和appSecret初始化
        manService.getMANAnalytics().init(this, this);
//         MAN另一初始化方法，手动指定appKey和appSecret
//         String appKey = "********";
//         String appSecret = "*************";
//         manService.getMANAnalytics().init(this, getApplicationContext(), appKey, appSecret);
        // 若需要关闭 SDK 的自动异常捕获功能可进行如下操作,详见文档5.4
//        manService.getMANAnalytics().turnOffCrashReporter();
        // 通过此接口关闭页面自动打点功能，详见文档4.2
//        manService.getMANAnalytics().turnOffAutoPageTrack();
        // 若AndroidManifest.xml 中的 android:versionName 不能满足需求，可在此指定
        // 若在上述两个地方均没有设置appversion，上报的字段默认为null
        manService.getMANAnalytics().setAppVersion("2.0.4");
        LogUtils.logE("初始化成功！");
    }


    /**
     * 初始化云推送通道
     *
     * @param applicationContext
     */
    private void initCloudChannel(final Context applicationContext) {
        pushService = PushServiceFactory.getCloudPushService();
        pushService.register(applicationContext, new CommonCallback() {
            @Override
            public void onSuccess(String response) {

//                Log.e("123546789794564", "init cloudchannel success");
//                Log.e("========>>>>>>", "init CloudPushService success, device id: " + pushService.getDeviceId() +", Appkey: " + AmsGlobalHolder.getAppMetaData("com.alibaba.app.appkey"));

                if (PrefsUtils.getString(applicationContext, PrefsUtils.UID) != null) {
                    pushService.bindAccount(MyData.SILVZONE+PrefsUtils.getString(applicationContext, PrefsUtils.UID), new CommonCallback() {
                        @Override
                        public void onSuccess(String s) {
                            bind(pushService.getDeviceId());
                        }

                        @Override
                        public void onFailed(String s, String s1) {
                            bind(getDeviceId());
                        }
                    });
                }

                if (PrefsUtils.getString(applicationContext, PrefsUtils.AUTHORIZATION) == null ||
                        PrefsUtils.getString(applicationContext, PrefsUtils.AUTHORIZATION).isEmpty()||
                        TextUtils.equals("0",PrefsUtils.getString(applicationContext, PrefsUtils.OFF_PUSH))){

                    pushService.turnOffPushChannel(new CommonCallback() {
                        @Override
                        public void onSuccess(String s) {
                            LogUtils.logE("开启推送成功！");
                        }

                        @Override
                        public void onFailed(String s, String s1) {
                            bind(getDeviceId());
                        }
                    });



                }else {
                    pushService.turnOnPushChannel(new CommonCallback() {
                        @Override
                        public void onSuccess(String s) {
                            LogUtils.logE("没有登录状态，退送关闭！");
                        }

                        @Override
                        public void onFailed(String s, String s1) {

                        }
                    });
                }





                initDataSys();
            }

            @Override
            public void onFailed(String errorCode, String errorMessage) {
                bind(getDeviceId());
                LogUtils.logE("init cloudchannel failed -- errorcode:" + errorCode + " -- errorMessage:" + errorMessage);
            }
        });
    }

    private void bind(String id) {
        RequestParams params = new RequestParams();
        params.addHeader("Authorization", PrefsUtils.getString(getApplicationContext(), PrefsUtils.AUTHORIZATION));
        params.addQueryStringParameter("device_id",id);
        params.addQueryStringParameter("device_type","android");
        HttpDataUtils httpDataUtils = new HttpDataUtils(this);
        httpDataUtils.sendPost(MyData.DEVICE, params);
        httpDataUtils.setSucessBack(new HttpDataUtils.SucessBack() {
            @Override
            public void sucess(ResponseInfo<String> arg0) {

            }
        });
        httpDataUtils.setFailBack(new HttpDataUtils.FailBack() {

            @Override
            public void fail(String msg) {
            }
        });
    }


    /**
     * imageloader 初始化
     */
    private void imageLoaderInit() {
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                getApplicationContext())
                // .memoryCacheExtraOptions(480, 800)
                // max width, max height，即保存的每个缓存文件的最大长宽
                // .discCacheExtraOptions(480, 800, CompressFormat.JPEG, 75,
                // null)
                // Can slow ImageLoader, use it carefully (Better don't use
                // it)/设置缓存的详细信息，最好不要设置这个
                .threadPoolSize(2)
                // 线程池内加载的数量
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .memoryCache(new UsingFreqLimitedMemoryCache(2 * 1024 * 1024))
                // You can pass your own memory cache
                // implementation/你可以通过自己的内存缓存实现
                .memoryCacheSize(2 * 1024 * 1024)
                .diskCacheSize(50 * 1024 * 1024)
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                // 将保存的时候的URI名称用MD5 加密
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .diskCacheFileCount(100)
                // 缓存的文件数量
                // .discCache(new UnlimitedDiscCache(cacheDir))
                // 自定义缓存路径
                .defaultDisplayImageOptions(DisplayImageOptions.createSimple())
                .imageDownloader(
                        new BaseImageDownloader(getApplicationContext(),
                                5 * 1000, 30 * 1000)) // connectTimeout
                // (5
                // s),
                // readTimeout
                // (30
                // s)超时时间
                .writeDebugLogs() // Remove for release app
                .build();// 开始构建
        ImageLoader.getInstance().init(config);
    }

    private Application parentApp;
    private Activity curActivity;

    public Application getAppContext() {
        return parentApp;
    }

    private static class LazyHolder {
        static HzApplication instance = new HzApplication();
    }

    public static HzApplication getInstance() {
        return LazyHolder.instance;
    }

    public void setCurrentActivity(Activity curActivity) {
        this.curActivity = curActivity;
    }

    public Activity getCurrentActivity() {
        return this.curActivity;
    }

    public DataCache getUserEntityCache() {
        return userEntityCache;
    }

    public static void setUserEntityCache(DataCache userEntityCache) {
        HzApplication.userEntityCache = userEntityCache;
    }

    public boolean isInMainActivity() {
        boolean isInMainActivity = false;
        if (null != curActivity) {
            if (curActivity.equals(MainActivity.class)) {
                isInMainActivity = true;
            }
        }
        return isInMainActivity;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }




    /**
     * 获取设备唯一ID
     * @return
     */
    public static String getDeviceId() {
        String m_szDevIDShort = "35" + (Build.BOARD.length() % 10) + (Build.BRAND.length() % 10) + (Build.CPU_ABI.length() % 10) + (Build.DEVICE.length() % 10) + (Build.MANUFACTURER.length() % 10) + (Build.MODEL.length() % 10) + (Build.PRODUCT.length() % 10);
        String serial = null;
        try {
            serial = Build.class.getField("SERIAL").get(null).toString();
            return new UUID(m_szDevIDShort.hashCode(), serial.hashCode()).toString();
        } catch (Exception exception) {
            serial = "serial";
        }
        return new UUID(m_szDevIDShort.hashCode(), serial.hashCode()).toString();
    }

}
