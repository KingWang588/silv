package com.yhy.hzzll.home.fragment;

import android.Manifest;
import android.app.Activity;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.FileDownloadListener;
import com.liulishuo.filedownloader.FileDownloader;
import com.liulishuo.filedownloader.util.FileDownloadUtils;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionListener;
import com.yhy.hzzll.R;
import com.yhy.hzzll.entity.BannerEntity;
import com.yhy.hzzll.entity.LawyerCoopEntity;
import com.yhy.hzzll.entity.VersionEntity;
import com.yhy.hzzll.framework.Constans;
import com.yhy.hzzll.framework.HzApplication;
import com.yhy.hzzll.framework.MyData;
import com.yhy.hzzll.home.activity.ADActivity;
import com.yhy.hzzll.home.activity.LegalToolActivity;
import com.yhy.hzzll.home.activity.ProfessionalServicesActivity;
import com.yhy.hzzll.home.activity.collaborate.LawyerNationalCooperationActivity;
import com.yhy.hzzll.home.activity.consult.ConsultActivity;
import com.yhy.hzzll.home.activity.lawyeroffice.LawyerNationalActivity;
import com.yhy.hzzll.home.holder.CBViewHolderCreator;
import com.yhy.hzzll.listener.OnItemCListener;
import com.yhy.hzzll.message.WebviewActivity;
import com.yhy.hzzll.mian.activity.MainActivity;
import com.yhy.hzzll.mian.fragment.BaseFragment;
import com.yhy.hzzll.my.activity.OrderManagementActivity;
import com.yhy.hzzll.utils.HttpDataUtils;
import com.yhy.hzzll.utils.JSONTool;
import com.yhy.hzzll.utils.MsgEvent;
import com.yhy.hzzll.utils.PrefsUtils;
import com.yhy.hzzll.utils.ToastUtils;
import com.yhy.hzzll.view.ConvenientBanner;
import com.yhy.hzzll.view.ImageBannerView;
import com.yhy.hzzll.view.UpdataVersionDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import de.greenrobot.event.EventBus;

import static com.yhy.hzzll.utils.OpenFileUtil.openFile;

/**
 *
 * 已废弃
 *
 * 新版见  NewHomeFragment
 *
 */



public class HomeFragment extends BaseFragment implements OnItemCListener {

    private View view;

    private List<BannerEntity.DataBean> photolist = new ArrayList<>();
    @ViewInject(R.id.convenientBanner)
    private ConvenientBanner<String> convenientBanner;// 顶部广告栏控件


    @ViewInject(R.id.tv_latest_user_consultation)
    private TextView tv_latest_user_consultation;

    @ViewInject(R.id.tv_my_exclusive_consulting)
    private TextView tv_my_exclusive_consulting;


    @ViewInject(R.id.line_zixun)
    private View line_zixun;

    @ViewInject(R.id.line_jieda)
    private View line_jieda;

    @ViewInject(R.id.tv_notify_couselt)
    private ImageView tv_notify_couselt;

    @ViewInject(R.id.tv_notify_lawcoop)

    private ImageView tv_notify_lawcoop;


    private HttpDataUtils dataUtils;

    int type;
    String downUrl = "";
    String apk_version = "";

//    AllConsultingFragment allConsultingFragment;
    LatestConsultationFragment latestConsultationFragment;
    ExclusiveconsultingFragment exclusiveconsultingFragment;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 2) {
                if (msg.obj.equals("showconsult")) {
                    tv_notify_couselt.setVisibility(View.VISIBLE);
                } else if (msg.obj.equals("showlawcoop")) {
                    tv_notify_lawcoop.setVisibility(View.VISIBLE);
                }
            } else if (msg.what == 3) {
                tv_notify_lawcoop.setVisibility(View.INVISIBLE);
            }
            super.handleMessage(msg);
        }
    };

    private Activity mActivity;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = activity;
        ((MainActivity) mActivity).setHandler(handler);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataUtils = new HttpDataUtils(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, null);
        ViewUtils.inject(this, view);
        tv_latest_user_consultation.setSelected(true);

//        if (NetUtills.isMobileConnected()){
            getBanner();
//        }else {
//            ToastUtils.getUtils(getActivity()).show("网络不可用！");
//        }

        setDefaultFragment();


        return view;
    }

    @Override
    public void onResume() {
        setDefaultFragment();
        super.onResume();
    }

    @Override
    public void viewInit() {
        super.viewInit();
    }

    @OnClick({R.id.tv_consulting, R.id.tv_lawcoop, R.id.tv_legal_tool,R.id.tv_lawyers,
            R.id.tv_professional_services,R.id.tv_latest_user_consultation, R.id.tv_my_exclusive_consulting,
            R.id.rl_consulting,R.id.rl_lawcoop,R.id.tv_more,R.id.iv_scan})
    public void onClick(View view) {

        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();

        switch (view.getId()) {


            case R.id.tv_legal_tool:
                startActivity(new Intent().setClass(getActivity(), LegalToolActivity.class));
                break;
            case R.id.tv_lawyers:
                startActivity(new Intent().setClass(getActivity(), LawyerNationalActivity.class));
                break;
            case R.id.tv_professional_services:
                startActivity(new Intent().setClass(getActivity(), ProfessionalServicesActivity.class));
                break;
            case R.id.tv_consulting:// 解答疑
            case R.id.rl_consulting:

                EventBus.getDefault().post(new MsgEvent("云办公"));

                break;

            case R.id.tv_lawcoop:// 接办案
            case R.id.rl_lawcoop:
                Date dt2 = new Date();
                Long time2 = dt2.getTime();
                PrefsUtils.saveString(getActivity(), PrefsUtils.CLICKLAWCOOP, String.valueOf(time2));
                tv_notify_lawcoop.setVisibility(View.INVISIBLE);
                startActivity(new Intent().putExtra("tab", 0).setClass(getActivity(), LawyerNationalCooperationActivity.class));
                break;
            case R.id.tv_latest_user_consultation:
                type = 1;
                line_zixun.setVisibility(View.VISIBLE);
                line_jieda.setVisibility(View.INVISIBLE);
                tv_latest_user_consultation.setSelected(true);
                tv_my_exclusive_consulting.setSelected(false);
                if (latestConsultationFragment == null) {
                    latestConsultationFragment = new LatestConsultationFragment();
                }
                transaction.replace(R.id.tv_fragment, latestConsultationFragment);
                transaction.commit();

                break;
            case R.id.tv_my_exclusive_consulting:

                type = 2;
                line_zixun.setVisibility(View.INVISIBLE);
                line_jieda.setVisibility(View.VISIBLE);
                tv_latest_user_consultation.setSelected(false);
                tv_my_exclusive_consulting.setSelected(true);
                if (exclusiveconsultingFragment == null) {
                    exclusiveconsultingFragment = new ExclusiveconsultingFragment();
                }
                transaction.replace(R.id.tv_fragment, exclusiveconsultingFragment);
                transaction.commit();
                break;

            case R.id.tv_more:

                if (type == 1){
                    startActivity(new Intent().setClass((Activity)getActivity(), ConsultActivity.class));
                }else{
                    startActivity(new Intent().setClass(mActivity, OrderManagementActivity.class));
                }

                break;
        }
    }

    private void setDefaultFragment(){
        line_zixun.setVisibility(View.VISIBLE);
        line_jieda.setVisibility(View.INVISIBLE);
        type = 1;
        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        latestConsultationFragment =  new LatestConsultationFragment();
        transaction.replace(R.id.tv_fragment, latestConsultationFragment);
        transaction.commit();
    }

    private void checkVision() {
        RequestParams params = new RequestParams();
        dataUtils.sendGet(MyData.CHECK_VERSION, params);
        dataUtils.setSucessBack(new HttpDataUtils.SucessBack() {
            @Override
            public void sucess(ResponseInfo<String> arg0) {
                try {
                    JSONObject object = new JSONObject(arg0.result);
                    if (object.optString("code").equals("0")) {
                        JSONObject data = JSONTool.getJsonObject(object, "data");
                        final VersionEntity entity = gson.fromJson(object.toString(), VersionEntity.class);
                        String version = getVersion(getActivity());
                        if (entity != null) {
                            String newVersion = entity.getData().getVersion();
                            int a = Integer.parseInt(version.charAt(0) + "");
                            int a1 = Integer.parseInt(newVersion.charAt(0) + "");

                            int b = Integer.parseInt(version.charAt(2) + "");
                            int b1 = Integer.parseInt(newVersion.charAt(2) + "");

                            int c = Integer.parseInt(version.charAt(4) + "");
                            int c1 = Integer.parseInt(newVersion.charAt(4) + "");

                            downUrl = entity.getData().getDownurl();
                            apk_version = entity.getData().getVersion();

                            if (a1 > a) {
                                UpdataVersionDialog enter = new UpdataVersionDialog();
                                enter.showDialog(getActivity(), new UpdataVersionDialog.Click() {
                                    @Override
                                    public void click() {
                                        jurisdiction();
//                                        downloadApk(getActivity(), entity.getData().getDownurl(), "私律律师端", "更新");
                                    }
                                }, true, entity.getData().getVersion());
                            } else if (a1 == a) {
                                if (b1 > b) {
                                    UpdataVersionDialog enter = new UpdataVersionDialog();
                                    enter.showDialog(getActivity(), new UpdataVersionDialog.Click() {
                                        @Override
                                        public void click() {
                                            jurisdiction();
//                                            downloadApk(getActivity(), entity.getData().getDownurl(), "私律律师端", "更新");
                                        }
                                    }, true, entity.getData().getVersion());
                                } else if (b1 == b) {
                                    if (c1 > c) {
                                        if (!PrefsUtils.getString(getActivity(), PrefsUtils.UPDATAORNOT).equals(entity.getData().getVersion())) {
                                            UpdataVersionDialog enter = new UpdataVersionDialog();
                                            enter.showDialog(getActivity(), new UpdataVersionDialog.Click() {
                                                @Override
                                                public void click() {

                                                    jurisdiction();
//                                                    downloadApk(getActivity(), entity.getData().getDownurl(), "私律律师端", "更新");
                                                }
                                            }, false, entity.getData().getVersion());
                                        }
                                    }
                                }
                            }
                        }
                        coopListData();
                    } else {

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        dataUtils.setFailBack(new HttpDataUtils.FailBack() {
            @Override
            public void fail(String msg) {

            }
        });
    }

    private void jurisdiction() {

        AndPermission.with(getActivity())
                .requestCode(102)
                .permission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .callback(permissionListener)
                .start();

    }

    private static final int REQUEST_CODE_SETTING = 300;

    private PermissionListener permissionListener = new PermissionListener() {
        @Override
        public void onSucceed(int requestCode, List<String> grantPermissions) {
            switch (requestCode) {
                case 102: {
                    if (AndPermission.hasPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
//                        downloadApk(getActivity(), downUrl, "私律律师端", "更新");
                        final String savePath = FileDownloadUtils.getDefaultSaveRootPath() + File.separator + "document" + File.separator + "silv"+apk_version+".apk";
                        downloadFile(savePath, downUrl, "apk");
                    } else {
                        Toast.makeText(getActivity(), "我们需要的一些权限被您拒绝或者系统发生错误申请失败，请您到设置页面手动授权，否则功能无法正常使用！", Toast.LENGTH_SHORT).show();
                    }
                    break;
                }
            }
        }

        @Override
        public void onFailed(int requestCode, List<String> deniedPermissions) {
            switch (requestCode) {
                case 102: {
                    Toast.makeText(getActivity(), "获取权限失败", Toast.LENGTH_SHORT).show();
                    break;
                }
            }
        }
    };


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
                        ToastUtils.getUtils(getActivity()).show("下载已完成");
                        startActivity(openFile(savePath, type));

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



    private void getAD() {

        final HzApplication hzApplication = (HzApplication) getActivity().getApplication();
        hzApplication.setIndex(2);

        dataUtils.sendGet(MyData.GET_HOME_BANNER+"9", null);
        dataUtils.setSucessBack(new HttpDataUtils.SucessBack() {
            @Override
            public void sucess(ResponseInfo<String> arg0) {
                try {
                    JSONObject object = new JSONObject(arg0.result);
                    String code = JSONTool.getString(object, "code");
                    String msg = JSONTool.getString(object, "msg");
                    if (code.equals("0")) {
                        BannerEntity bannerEntity = gson.fromJson(object.toString(), BannerEntity.class);

                        if (bannerEntity.getData().size()!=0){
                            startActivity(new Intent(getActivity(), ADActivity.class)
                                    .putExtra("imgurl",bannerEntity.getData().get(0).getImgurl())
                            .putExtra("target",bannerEntity.getData().get(0).getTarget())
                            .putExtra("title",bannerEntity.getData().get(0).getTitle()));
                        }

                    } else {
//                        dataUtils.showMsg(arg0.result);
                    }
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });

        dataUtils.setFailBack(new HttpDataUtils.FailBack() {
            @Override
            public void fail(String msg) {
                checkVision();
            }
        });
    }


    private void getBanner() {
        dataUtils.sendGet(MyData.GET_HOME_BANNER+"1", null);
        dataUtils.setSucessBack(new HttpDataUtils.SucessBack() {
            @Override
            public void sucess(ResponseInfo<String> arg0) {
                try {
                JSONObject object = new JSONObject(arg0.result);
                String code = JSONTool.getString(object, "code");
                String msg = JSONTool.getString(object, "msg");
                if (code.equals("0")) {
                    BannerEntity bannerEntity = gson.fromJson(object.toString(), BannerEntity.class);
                    photolist.addAll(bannerEntity.getData());
                    bannerInit();
                    checkVision();
                } else {
                    dataUtils.showMsg(arg0.result);
                }
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });

        dataUtils.setFailBack(new HttpDataUtils.FailBack() {
            @Override
            public void fail(String msg) {
                checkVision();
            }
        });
    }

    /**
     * 广告栏加载
     */
    private void bannerInit() {
        convenientBanner.setPages(new CBViewHolderCreator<ImageBannerView>() {
            @Override
            public ImageBannerView createHolder() {
                return new ImageBannerView();}
        }, photolist);
        convenientBanner.setPageIndicator(new int[]{R.drawable.dot_tab_grey, R.drawable.dot_tab_yellow})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                .setOnItemClickListener(this);

        convenientBanner.setFocusable(true);
        convenientBanner.setFocusableInTouchMode(true);
        convenientBanner.requestFocus();
//        accesskey = PrefsUtils.getString(getActivity(), PrefsUtils.ACCESSKEY);
    }

    @Override
    public void onItemClick(int position) {
        if (!"".equals(photolist.get(position).getTarget().toString())) {
            startActivity(new Intent().putExtra("title", photolist.get(position).getTitle()).putExtra("url", photolist.get(position).getTarget()).setClass(getActivity(), WebviewActivity.class));
        }
    }

    @Override
    public void onDestroy() {

//        getActivity().unregisterReceiver(receiver);

        super.onDestroy();
    }

//    /**
//     * 下载Apk, 并设置Apk地址,
//     * 默认位置: /storage/sdcard0/Download
//     *
//     * @param context     上下文
//     * @param downLoadUrl 下载地址
//     * @param infoName    通知名称
//     * @param description 通知描述
//     */
//    @SuppressWarnings("unused")
    public static void downloadApk(
            Context context,
            String downLoadUrl,
            String description,
            String infoName) {

        DownloadManager.Request request;
        try {
            request = new DownloadManager.Request(Uri.parse(downLoadUrl));
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        request.setTitle(infoName);
        request.setDescription(description);

        //在通知栏显示下载进度
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            request.allowScanningByMediaScanner();
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        }

        //设置保存下载apk保存路径
        request.setDestinationInExternalPublicDir(Constans.SAVE_APP_LOCATION, Constans.SAVE_APP_NAME);

        Context appContext = context.getApplicationContext();
        DownloadManager manager = (DownloadManager)
                appContext.getSystemService(Context.DOWNLOAD_SERVICE);
        //进入下载队列
        manager.enqueue(request);

    }






    /**
     * 获取版本号
     *
     * @return 当前应用的版本号
     */
    public String getVersion(Context context) {
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            String version = info.versionName;
            return version;
        } catch (Exception e) {
            e.printStackTrace();
            return "1.1.7";
        }
    }

    private void coopListData() {
        RequestParams params = new RequestParams();
        params.addQueryStringParameter("page", String.valueOf(1));
        dataUtils.sendGet(MyData.LAWERCOOPLIST, params);
        dataUtils.setSucessBack(new HttpDataUtils.SucessBack() {
            @Override
            public void sucess(ResponseInfo<String> arg0) {
                if (dataUtils.code(arg0.result)) {
                    LawyerCoopEntity coopEntity = gson.fromJson(dataUtils.data(arg0.result), LawyerCoopEntity.class);
                    long newtime = 0;
//                    if (coopEntity.getList() != null) {
//                        if (coopEntity.getList().get(0).getCtime_num() != null) {
//                            newtime = Long.parseLong(coopEntity.getList().get(0).getCtime_num());
//                        }
//                    }
                    String time = PrefsUtils.getString(getActivity(), PrefsUtils.CLICKLAWCOOP);
                    long millionSeconds = 0;
                    if (time.equals("")) {
                        millionSeconds = 0;
                    } else {
                        millionSeconds = Long.parseLong(time);
                    }
                    if (newtime > millionSeconds / 1000) {
                        tv_notify_lawcoop.setVisibility(View.VISIBLE);
                    } else {
                        tv_notify_lawcoop.setVisibility(View.GONE);
                    }


                    getAD();

                }
            }
        });
    }
}
