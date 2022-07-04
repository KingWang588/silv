package com.yhy.hzzll.home.fragment;

import android.Manifest;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
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
import com.yhy.hzzll.entity.ConsultEntity;
import com.yhy.hzzll.entity.LawyerCoopEntity;
import com.yhy.hzzll.entity.OrderListEntity;
import com.yhy.hzzll.entity.VersionEntity;
import com.yhy.hzzll.framework.Constans;
import com.yhy.hzzll.framework.HzApplication;
import com.yhy.hzzll.framework.MyData;
import com.yhy.hzzll.home.activity.ADActivity;
import com.yhy.hzzll.home.activity.LegalToolActivity;
import com.yhy.hzzll.home.activity.ProfessionalServicesActivity;
import com.yhy.hzzll.home.activity.collaborate.LawyerNationalCooperationActivity;
import com.yhy.hzzll.home.activity.consult.ConsultActivity;
import com.yhy.hzzll.home.activity.consult.ConsultDetailsActivity;
import com.yhy.hzzll.home.activity.consult.PursueAskActivity;
import com.yhy.hzzll.home.activity.lawyeroffice.LawyerNationalActivity;
import com.yhy.hzzll.home.adapter.ConsultAdapter;
import com.yhy.hzzll.home.holder.CBViewHolderCreator;
import com.yhy.hzzll.listener.OnItemCListener;
import com.yhy.hzzll.message.WebviewActivity;
import com.yhy.hzzll.mian.activity.LoginActivity;
import com.yhy.hzzll.mian.activity.MainActivity;
import com.yhy.hzzll.mian.fragment.BaseFragment;
import com.yhy.hzzll.my.activity.OrderManagementActivity;
import com.yhy.hzzll.my.activity.QuickConsultingOrderDetailsActivity;
import com.yhy.hzzll.my.adapter.OrderListsAdapter;
import com.yhy.hzzll.utils.ClickFilter;
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
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import de.greenrobot.event.EventBus;

import static com.yhy.hzzll.utils.OpenFileUtil.openFile;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link NewHomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewHomeFragment extends BaseFragment implements OnItemCListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public NewHomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NewHomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NewHomeFragment newInstance(String param1, String param2) {
        NewHomeFragment fragment = new NewHomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @ViewInject(R.id.ptrflv)
    private PullToRefreshListView ptrflv;

    @ViewInject(R.id.layout_tab_2)
    private ConstraintLayout layout_tab_2;

    private ConsultAdapter consultAdapter;

    HttpDataUtils httpDataUtils;
    List<ConsultEntity.DataBean.ListBean> mList;


    private List<BannerEntity.DataBean> photolist = new ArrayList<>();
    @ViewInject(R.id.convenientBanner)
    private ConvenientBanner<String> convenientBanner;// 顶部广告栏控件


    @ViewInject(R.id.tv_latest_user_consultation)
    private TextView tv_latest_user_consultation;

    @ViewInject(R.id.tv_my_exclusive_consulting)
    private TextView tv_my_exclusive_consulting;


    @ViewInject(R.id.tv_latest_user_consultation_top)
    private TextView tv_latest_user_consultation_top;

    @ViewInject(R.id.tv_my_exclusive_consulting_top)
    private TextView tv_my_exclusive_consulting_top;


    @ViewInject(R.id.line_zixun)
    private View line_zixun;

    @ViewInject(R.id.line_jieda)
    private View line_jieda;

    @ViewInject(R.id.line_zixun_top)
    private View line_zixun_top;

    @ViewInject(R.id.line_jieda_top)
    private View line_jieda_top;


    @ViewInject(R.id.tv_notify_couselt)
    private ImageView tv_notify_couselt;

    @ViewInject(R.id.tv_notify_lawcoop)

    private ImageView tv_notify_lawcoop;

    int type = 1;
    String downUrl = "";
    private HttpDataUtils dataUtils;

    private OrderListsAdapter orderListsAdapter;

    ArrayList<OrderListEntity.DataBean.ListBean> orderList;


    int w_cur_pos;
    int w_top;
    boolean isfirst;
    View headerview2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_home, container, false);
        ViewUtils.inject(this, view);
        dataUtils = new HttpDataUtils(getActivity());
        initView();
        return view;
    }

    private void initView() {

        View headerview1 = View.inflate(getActivity(), R.layout.home_first_part, null);
        ViewUtils.inject(this, headerview1);
        headerview2 = View.inflate(getActivity(), R.layout.home_second_part_tab, null);
        ViewUtils.inject(this, headerview2);

        getBanner();

        ptrflv.getRefreshableView().addHeaderView(headerview1);
        ptrflv.getRefreshableView().addHeaderView(headerview2);

        ptrflv.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {

            }

            @Override
            public void onScroll(AbsListView absListView, int i, int i1, int i2) {

            }
        });

        ptrflv.setOnLastItemVisibleListener(new PullToRefreshBase.OnLastItemVisibleListener() {
            @Override
            public void onLastItemVisible() {
//                Toast.makeText(getActivity(),"到底了",Toast.LENGTH_SHORT).show();
            }
        });

        httpDataUtils = new HttpDataUtils(getActivity());
        mList = new ArrayList<>();
        orderList = new ArrayList<>();
        consultAdapter = new ConsultAdapter(getActivity(), mList);
        ptrflv.setAdapter(consultAdapter);

        ptrflv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (type == 1) {
                    startActivity(new Intent(getActivity(), ConsultDetailsActivity.class)
                            .putExtra("id", mList.get(position - 3).getId() + "")
                            .putExtra("title", mList.get(position - 3).getContent()).putExtra("insert", true));
                } else {
                    startActivity(new Intent(getActivity(), QuickConsultingOrderDetailsActivity.class).putExtra("order_no", orderList.get(position - 3).getOrder_no()));
                }

            }
        });


        ptrflv.setMode(PullToRefreshBase.Mode.BOTH);

        ptrflv.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
               resetPageIndex();
                isPullRefresh = true;
                dataInit2();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // 加载完成后停止刷新
                        ptrflv.onRefreshComplete();
                    }
                }, 1000);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                addPageIndex();
                isPullRefresh = false;
                dataInit2();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // 加载完成后停止刷新
                        ptrflv.onRefreshComplete();
                    }
                }, 1000);
            }
        });
    }

    private boolean isPullRefresh = true;
    private int index = 1;

    private void resetPageIndex() {
        index = 1;
    }

    private void addPageIndex() {
        ++index;
    }

    @OnClick({R.id.tv_consulting, R.id.tv_lawcoop, R.id.tv_legal_tool, R.id.tv_lawyers,
            R.id.tv_professional_services, R.id.tv_latest_user_consultation, R.id.tv_my_exclusive_consulting,
            R.id.rl_consulting, R.id.rl_lawcoop, R.id.tv_more, R.id.tv_latest_user_consultation_top,
            R.id.tv_my_exclusive_consulting_top, R.id.tv_more_top})
    public void onClick(View view) {

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
//                startActivity(new Intent().setClass(getActivity(), ConsultActivity.class));
//                Date dt = new Date();
//                Long time = dt.getTime();
//                PrefsUtils.saveString(getActivity(), PrefsUtils.CLICKCONSULT, String.valueOf(time));
//                tv_notify_couselt.setVisibility(View.INVISIBLE);
//                break;

                EventBus.getDefault().post(new MsgEvent("用户咨询"));
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

                if (ClickFilter.isFastClick()){
                    type = 1;
                    line_zixun.setVisibility(View.VISIBLE);
                    line_zixun_top.setVisibility(View.VISIBLE);
                    line_jieda.setVisibility(View.INVISIBLE);
                    line_jieda_top.setVisibility(View.INVISIBLE);
                    tv_latest_user_consultation.setSelected(true);
                    tv_latest_user_consultation_top.setSelected(true);
                    tv_my_exclusive_consulting.setSelected(false);
                    tv_my_exclusive_consulting_top.setSelected(false);

                    mList.clear();
                    resetPageIndex();
                    consultAdapter = new ConsultAdapter(getActivity(), mList);
                    ptrflv.setAdapter(consultAdapter);

                    dataInit2();
                }



                break;
            case R.id.tv_my_exclusive_consulting:

                if (ClickFilter.isFastClick()){
                    if (PrefsUtils.getString(getActivity(), PrefsUtils.AUTHORIZATION) == null || PrefsUtils.getString(getActivity(), PrefsUtils.AUTHORIZATION).isEmpty()) {
                        startActivity(new Intent().putExtra("tab", 1).putExtra("activity", MainActivity.class.toString())
                                .setClass(getActivity(), LoginActivity.class));
                        return;
                    }

                    type = 2;
                    line_zixun.setVisibility(View.INVISIBLE);
                    line_zixun_top.setVisibility(View.INVISIBLE);
                    line_jieda.setVisibility(View.VISIBLE);
                    line_jieda_top.setVisibility(View.VISIBLE);
                    tv_latest_user_consultation.setSelected(false);
                    tv_latest_user_consultation_top.setSelected(false);
                    tv_my_exclusive_consulting.setSelected(true);
                    tv_my_exclusive_consulting_top.setSelected(true);

                    orderList.clear();
                    resetPageIndex();
                    orderListsAdapter = new OrderListsAdapter(getActivity(), orderList, pursue);
                    ptrflv.setAdapter(orderListsAdapter);

                    dataInit2();
                }else{

                }



                break;

//            case R.id.tv_latest_user_consultation_top:
//
//                type = 1;
//                line_zixun.setVisibility(View.VISIBLE);
//                line_zixun_top.setVisibility(View.VISIBLE);
//                line_jieda.setVisibility(View.INVISIBLE);
//                line_jieda_top.setVisibility(View.INVISIBLE);
//                tv_latest_user_consultation.setSelected(true);
//                tv_latest_user_consultation_top.setSelected(true);
//                tv_my_exclusive_consulting.setSelected(false);
//                tv_my_exclusive_consulting_top.setSelected(false);
//
//                dataInit2();
//
//                break;

//            case R.id.tv_my_exclusive_consulting_top:
//
//                type = 2;
//                line_zixun.setVisibility(View.INVISIBLE);
//                line_zixun_top.setVisibility(View.INVISIBLE);
//                line_jieda.setVisibility(View.VISIBLE);
//                line_jieda_top.setVisibility(View.VISIBLE);
//                tv_latest_user_consultation.setSelected(false);
//                tv_latest_user_consultation_top.setSelected(false);
//                tv_my_exclusive_consulting.setSelected(true);
//                tv_my_exclusive_consulting_top.setSelected(true);
//
//                dataInit2();
//
//                break;

            case R.id.tv_more:

                if (type == 1) {
                    startActivity(new Intent().setClass(getActivity(), ConsultActivity.class));
                } else {
                    startActivity(new Intent().setClass(getActivity(), OrderManagementActivity.class));
                }

                break;

//            case R.id.tv_more_top:
//
//                if (type == 1) {
//                    startActivity(new Intent().setClass(getActivity(), ConsultActivity.class));
//                } else {
//                    startActivity(new Intent().setClass(getActivity(), OrderManagementActivity.class));
//                }
//
//                break;
        }
    }


    private void dataInit2() {

        switch (type) {
            case 1:
                getQuestions();
                break;
            case 2:
                getOrders();
                break;
        }

    }

    private void getOrders() {
        String url = MyData.ORDER_LIST + "-" + "0" + "-" + "month" + "-" + "0" + "-" + index + "-" + "10";
        RequestParams params = new RequestParams();
        params.addHeader("Authorization", PrefsUtils.getString(getActivity(), PrefsUtils.AUTHORIZATION));
        httpDataUtils.sendGet(url, params);
        httpDataUtils.setSucessBack(new HttpDataUtils.SucessBack() {

            @Override
            public void sucess(ResponseInfo<String> arg0) {

                if (httpDataUtils.code(arg0.result)) {

                    if (isPullRefresh){
                        orderList.clear();
                    }

                    Gson gson = new Gson();
                    OrderListEntity entity = gson.fromJson(arg0.result, OrderListEntity.class);
                    orderList.addAll(entity.getData().getList());
                    orderListsAdapter.notifyDataSetChanged();

                }

            }

        });
        httpDataUtils.setFailBack(new HttpDataUtils.FailBack() {

            @Override
            public void fail(String msg) {
                orderListsAdapter.notifyDataSetChanged();
            }
        });
    }


    View.OnClickListener pursue = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int position = (int) v.getTag();
            startActivity(new Intent(getActivity(), PursueAskActivity.class)
                    .putExtra("reply_id", orderList.get(position).getReply_id() + "")
                    .putExtra("id", orderList.get(position).getConsult_id() + "" + "")
                    .putExtra("message_id", orderList.get(position).getMessage_id() + ""));
        }
    };


    private void getQuestions() {
        RequestParams params = new RequestParams();
        params.addHeader("Authorization", PrefsUtils.getString(getActivity(), PrefsUtils.AUTHORIZATION));
        params.addQueryStringParameter("consult_type", "3");
        params.addQueryStringParameter("client", "2");
        params.addQueryStringParameter("page", index + "");
        httpDataUtils.sendGet(MyData.CONSULT_LIST, params);
        httpDataUtils.setSucessBack(new HttpDataUtils.SucessBack() {
            @Override
            public void sucess(ResponseInfo<String> arg0) {
                if (httpDataUtils.code(arg0.result)) {

                    if (isPullRefresh){
                        mList.clear();
                    }

                    Gson gson = new Gson();
                    ConsultEntity entity = gson.fromJson(arg0.result, ConsultEntity.class);
                    mList.addAll(entity.getData().getList());
                    consultAdapter.notifyDataSetChanged();

                }
            }
        });

        httpDataUtils.setFailBack(new HttpDataUtils.FailBack() {
            @Override
            public void fail(String msg) {
//                lv_latest.onRefreshComplete();
            }
        });

    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private void getBanner() {
        dataUtils.sendGet(MyData.GET_HOME_BANNER + "1", null);
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

    String apk_version = "";

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

        ToastUtils.getUtils(getActivity()).show("正在下载......");

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

    /**
     * 广告栏加载
     */
    private void bannerInit() {
        convenientBanner.setPages(new CBViewHolderCreator<ImageBannerView>() {
            @Override
            public ImageBannerView createHolder() {
                return new ImageBannerView();
            }
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

    /**
     * 下载Apk, 并设置Apk地址,
     * 默认位置: /storage/sdcard0/Download
     *
     * @param context     上下文
     * @param downLoadUrl 下载地址
     * @param infoName    通知名称
     * @param description 通知描述
     */
    @SuppressWarnings("unused")
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


    @Override
    public void onResume() {

        dataInit2();

        super.onResume();
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
            return "2.0.6";
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

    private void getAD() {

        Calendar calendar = Calendar.getInstance();
        String time = calendar.get(Calendar.YEAR)+"-"+calendar.get(Calendar.MONTH)+"-"+calendar.get(Calendar.DAY_OF_MONTH);

        if (time.equals(PrefsUtils.getString(getActivity(),PrefsUtils.DATE_TIME))) {
//            ToastUtils.getUtils(getActivity()).show("今天已经显示了。。。。");
            return;
        }else {
            PrefsUtils.saveString(getActivity(), PrefsUtils.DATE_TIME,time);
        }

        final HzApplication hzApplication = (HzApplication) getActivity().getApplication();
        hzApplication.setIndex(2);

        dataUtils.sendGet(MyData.GET_HOME_BANNER + "9", null);
        dataUtils.setSucessBack(new HttpDataUtils.SucessBack() {
            @Override
            public void sucess(ResponseInfo<String> arg0) {
                try {
                    JSONObject object = new JSONObject(arg0.result);
                    String code = JSONTool.getString(object, "code");
                    String msg = JSONTool.getString(object, "msg");
                    if (code.equals("0")) {
                        BannerEntity bannerEntity = gson.fromJson(object.toString(), BannerEntity.class);

                        if (bannerEntity.getData().size() != 0) {
                            startActivity(new Intent(getActivity(), ADActivity.class)
                                    .putExtra("imgurl", bannerEntity.getData().get(0).getImgurl())
                                    .putExtra("target", bannerEntity.getData().get(0).getTarget())
                                    .putExtra("title", bannerEntity.getData().get(0).getTitle()));
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

}
