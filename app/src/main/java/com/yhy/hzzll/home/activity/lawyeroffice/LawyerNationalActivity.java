package com.yhy.hzzll.home.activity.lawyeroffice;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.umeng.socialize.Config;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.utils.Log;
import com.yhy.hzzll.R;
import com.yhy.hzzll.common.entity.CaseTypeEntity;
import com.yhy.hzzll.entity.LawyerNationalEntity;
import com.yhy.hzzll.entity.SortEntity;
import com.yhy.hzzll.framework.MyData;
import com.yhy.hzzll.home.activity.collaborate.LawyerNationalCooperationActivity;
import com.yhy.hzzll.home.activity.collaborate.PublishCollaborationActivity;
import com.yhy.hzzll.home.activity.consult.ConsultActivity;
import com.yhy.hzzll.home.adapter.LawyerListAdapter;
import com.yhy.hzzll.mian.activity.BaseActivity;
import com.yhy.hzzll.mian.activity.LoginActivity;
import com.yhy.hzzll.utils.HttpDataUtils;
import com.yhy.hzzll.utils.LogUtils;
import com.yhy.hzzll.utils.PrefsUtils;
import com.yhy.hzzll.utils.ToastUtils;
import com.yhy.hzzll.view.DialogLoading;
import com.yhy.hzzll.view.PopupWindowAddress;
import com.yhy.hzzll.view.SharePopupWindow;
import com.yhy.hzzll.view.SortView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * 首页-- 全国律师
 */
public class LawyerNationalActivity extends BaseActivity implements OnRefreshListener2<ListView> {

    @ViewInject(R.id.tv_sort)
    private TextView tv_sort;
    @ViewInject(R.id.tv_comment)
    private TextView tv_comment;
    @ViewInject(R.id.tv_classify)
    private TextView tv_classify;

    @ViewInject(R.id.lv_lawyer)
    private PullToRefreshListView lv_lawyer;

    @ViewInject(R.id.ll_office_table)
    private LinearLayout ll_office_table;
    @ViewInject(R.id.et_search)
    EditText et_search;

    @ViewInject(R.id.iv_btn_to_top)
    private ImageView iv_btn_to_top;

    List<SortEntity> sortList = new ArrayList<>();

    private boolean isClose = false;

    private int index = 1;

    private int maxpage;

    private LawyerNationalEntity entity;
    private LawyerListAdapter adapter;
    private DialogLoading loading;

    private boolean isPullRefresh;

    private List<LawyerNationalEntity.DataBean.ListBean> mList;

    private int value = 0;

    private String skey = "";

    private String sort = "desc";

    private String item = "";

    private String province = "-1";

    private String address = "";

    @Override
    protected void onCreate(Bundle arg0) {
        setContentView(R.layout.activity_lawyer_national);
        super.onCreate(arg0);
        ViewUtils.inject(this);
        mList = new ArrayList<>();
        adapter = new LawyerListAdapter(context, mList);
        lv_lawyer.setAdapter(adapter);

        viewListInit();
    }

    private void dataInit() {
        RequestParams params = new RequestParams();

        if (!isPullRefresh) {
            if (index > maxpage) {
                ToastUtils.getUtils(LawyerNationalActivity.this).show("已到底部！");
                return;
            } else {
                params.addQueryStringParameter("page", index + "");
                LogUtils.logE(index + "index》》》》》");
            }
        } else {
            params.addQueryStringParameter("page", index + "");
            LogUtils.logE(index + "index》》》》》");
        }

        skey = et_search.getText().toString();
        if (skey.length() != 0) {
            params.addQueryStringParameter("truename", skey);
        }

        if (!item.isEmpty()) {
            params.addQueryStringParameter("start_rate", item);
        }

        if (value != 0) {
            params.addQueryStringParameter("special", value + "");// 律师专长
        }
        params.addQueryStringParameter("city", address);
        LogUtils.logE(address + "city>>>>>>");

        params.addQueryStringParameter("page_num", "10");

        httpDataUtils.sendGet(MyData.LAWYERCERTIFIEDLISTS, params);
        httpDataUtils.setSucessBack(new HttpDataUtils.SucessBack() {
            @Override
            public void sucess(ResponseInfo<String> arg0) {
                LogUtils.logE(arg0.result);
                if (loading != null) {
                    loading.dismissDialog();
                }
                JSONObject object = null;
                try {
                    object = new JSONObject(arg0.result);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                if (httpDataUtils.code(arg0.result)) {
                    entity = gson.fromJson(arg0.result, LawyerNationalEntity.class);
                    if (isPullRefresh) {
                        mList.clear();
                        maxpage = entity.getData().getPages();
                    }

                    if (entity.getData().getTotal()==0){
                        ToastUtils.getUtils(LawyerNationalActivity.this).show("没有查询到相应数据。");
                    }



                    mList.addAll(entity.getData().getList());
                    adapter.notifyDataSetChanged();
                } else {
                    httpDataUtils.showMsgNew(arg0.result);
                }
                // 加载完成后停止刷新
                lv_lawyer.onRefreshComplete();
            }
        });
        httpDataUtils.setFailBack(new HttpDataUtils.FailBack() {
            @Override
            public void fail(String msg) {
                if (loading != null) {
                    loading.dismissDialog();
                }
                // 通知数据改变了
                adapter.notifyDataSetChanged();
                // 加载完成后停止刷新
                lv_lawyer.onRefreshComplete();
            }
        });
    }

    @OnClick({R.id.tv_table, R.id.tv_tool, R.id.tv_toolnavig, R.id.tv_sort, R.id.tv_comment,
            R.id.tv_classify, R.id.iv_search_btn, R.id.tv_share, R.id.iv_btn_to_top,R.id.tv_publish,R.id.tv_reset})
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.tv_reset:

                item = "";
                tv_sort.setText("综合排序");
                address = "";
                tv_comment.setText("地区");
                value = 0;
                tv_classify.setText("专长分类");

                et_search.setText("");

                resetPageIndex();
                lv_lawyer.setRefreshing();
                isPullRefresh = true;
                dataInit();

                break;


            case R.id.iv_btn_to_top:
                lv_lawyer.getRefreshableView().setSelection(0);
                iv_btn_to_top.setVisibility(View.GONE);
                break;
            case R.id.tv_table:// 附加功能
                if (isClose) {
                    Animation animation = new TranslateAnimation(0, 400, 0, 0);
                    animation.setDuration(400);
                    animation.setFillAfter(true);
                    ll_office_table.startAnimation(animation);
                    isClose = false;
                } else {
                    if (ll_office_table.getVisibility() == View.GONE)
                        ll_office_table.setVisibility(View.VISIBLE);
                    Animation animation = new TranslateAnimation(100, 0, 0, 0);
                    animation.setDuration(400);
                    animation.setFillAfter(true);
                    ll_office_table.startAnimation(animation);
                    isClose = true;
                }
                break;
            case R.id.tv_tool:// 聘律师
                startActivity(new Intent().putExtra("tab", 1).setClass(context, PublishCollaborationActivity.class));
                break;
            case R.id.tv_toolnavig:// 问答咨询
                startActivity(new Intent().setClass(context, ConsultActivity.class));
                break;
            case R.id.tv_sort:// 排序
                sortViewInit(tv_sort);
                break;
            case R.id.tv_comment:// 地区

                tv_sort.setSelected(false);
                tv_comment.setSelected(true);
                tv_classify.setSelected(false);

                final PopupWindowAddress popupWindowAddress = new PopupWindowAddress(LawyerNationalActivity.this,"2",false);
                popupWindowAddress.setAddresskListener(new PopupWindowAddress.OnAddressCListener() {
                    @Override
                    public void onClick(String province, String city, String area, String provincesid, String cityid, String areaid) {
                        if (province.equals("全国")) {
                            address = "";
                            handler.sendEmptyMessage(1);
                            popupWindowAddress.dismiss();
                            tv_comment.setText(province);
                            return;
                        }
                        if (city.equals("不限")){
                            address = provincesid;
                            address = address.substring(0,2);
                            handler.sendEmptyMessage(1);
                            tv_comment.setText(province);
                            popupWindowAddress.dismiss();
                            return;
                        }else{
                            address = cityid;
                            address = address.substring(0,4);
                            handler.sendEmptyMessage(1);
                            tv_comment.setText(city);
                            popupWindowAddress.dismiss();
                            return;
                        }
                    }
                });
//                popupWindowAddress.showAtLocation();
                popupWindowAddress.showAsDropDown(tv_comment);

                break;
            case R.id.tv_classify:// 专长分类
                getLawyerExpertise(tv_classify);
                break;
            case R.id.iv_search_btn:
                skey = et_search.getText() + "";
                if (TextUtils.isEmpty(et_search.getText())) {
                    lv_lawyer.setRefreshing(true);
                } else {
                    resetPageIndex();
                    isPullRefresh = true;
                    dataInit();
                }
                break;
            case R.id.tv_share:
                SharePopupWindow popupWindow = new SharePopupWindow(this,
                        new SharePopupWindow.Click() {
                            @Override
                            public void Select(SHARE_MEDIA shareStatus) {
                                UMImage image = new UMImage(context, R.drawable.app2);
                                switch (shareStatus) {
                                    case WEIXIN:
                                        Config.OpenEditor = true;
                                        Config.dialog = new DialogLoading().showDialog(LawyerNationalActivity.this);
                                        new ShareAction(LawyerNationalActivity.this)
                                                .setPlatform(SHARE_MEDIA.WEIXIN)
                                                .setCallback(umShareListener)
                                                .withText("全国律师")
                                                .withText("助力实现中国法律服务最后一公里！使年轻律师财富经验双积累！")
                                                .withMedia(image)
                                                .withTargetUrl(MyData.LAWYERS)
                                                .share();
                                        break;
                                    case WEIXIN_CIRCLE:
                                        // UMImage image = new UMImage(context,
                                        // "http://www.umeng.com/images/pic/social/integrated_3.png");
                                        Config.OpenEditor = true;
                                        Config.dialog = new DialogLoading().showDialog(LawyerNationalActivity.this);
                                        new ShareAction(LawyerNationalActivity.this)
                                                .setPlatform(SHARE_MEDIA.WEIXIN_CIRCLE)
                                                .setCallback(umShareListener)
                                                .withTitle("私律")
                                                .withText("助力实现中国法律服务最后一公里！使年轻律师财富经验双积累！")
                                                .withMedia(image)
                                                .withTargetUrl(MyData.LAWYERS)
                                                .share();
                                        break;
                                    case QQ:
                                        // UMImage image = new UMImage(context,
                                        // "http://www.umeng.com/images/pic/social/integrated_3.png");
                                        Config.OpenEditor = true;
                                        Config.dialog = new DialogLoading()
                                                .showDialog(LawyerNationalActivity.this);
                                        new ShareAction(LawyerNationalActivity.this)
                                                .setPlatform(SHARE_MEDIA.QQ)
                                                .setCallback(umShareListener)
                                                .withTitle("私律全国律师")
                                                .withText(" 助力实现中国法律服务最后一公里！使年轻律师财富经验双积累！")
                                                .withMedia(image)
                                                .withTargetUrl(MyData.LAWYERS)
                                                .share();
                                        break;
                                    case SINA:
                                        // UMImage image = new UMImage(context,
                                        // "http://www.umeng.com/images/pic/social/integrated_3.png");
                                        if (Build.VERSION.SDK_INT >= 23) {
                                            String[] mPermissionList = new String[]{
                                                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                                    Manifest.permission.ACCESS_FINE_LOCATION,
                                                    Manifest.permission.CALL_PHONE,
                                                    Manifest.permission.READ_LOGS,
                                                    Manifest.permission.READ_PHONE_STATE,
                                                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                                    Manifest.permission.SET_DEBUG_APP,
                                                    Manifest.permission.SYSTEM_ALERT_WINDOW,
                                                    Manifest.permission.GET_ACCOUNTS,
                                                    Manifest.permission.WRITE_APN_SETTINGS};
                                            ActivityCompat.requestPermissions(
                                                    LawyerNationalActivity.this,
                                                    mPermissionList, 123);
                                        }
                                        //武汉
                                        Config.OpenEditor = true;
                                        Config.dialog = new DialogLoading()
                                                .showDialog(LawyerNationalActivity.this);
                                        new ShareAction(LawyerNationalActivity.this)
                                                .setPlatform(SHARE_MEDIA.SINA)
                                                .setCallback(umShareListener)
                                                .withText(MyData.MOBILE_URL + "/lawyers")
                                                .share();
                                        break;
                                    default:
                                        break;
                                }
                            }
                        });
                popupWindow.showPopupWindow(view);
                break;

            case R.id.tv_publish:// 发布律师协作

                if (PrefsUtils.getString(LawyerNationalActivity.this, PrefsUtils.AUTHORIZATION) == null || PrefsUtils.getString(LawyerNationalActivity.this, PrefsUtils.AUTHORIZATION).length() == 0) {
                    startActivity(new Intent().putExtra("activity", LawyerNationalCooperationActivity.class.toString())
                            .setClass(context, LoginActivity.class));
                    return;
                } else {
                    if (PrefsUtils.getString(LawyerNationalActivity.this, PrefsUtils.ACCESSKEY) == null || PrefsUtils.getString(LawyerNationalActivity.this, PrefsUtils.ACCESSKEY).length() == 0) {
                        ToastUtils.getUtils(LawyerNationalActivity.this).show("十分抱歉，您不是使用账号密码登录，暂时无法访问本页信息。请切换使用密码登录才可以访问。");
                        return;
                    }
                }
                startActivity(new Intent().setClass(context, PublishCollaborationActivity.class));
                break;
        }
    }

    private void viewListInit() {
        loading = new DialogLoading();
        lv_lawyer.getRefreshableView().setOnItemClickListener(
                new OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                        try {
                            LogUtils.logE("user_id"+mList.get(arg2 - 1).getUsers_id() + "");
                            LogUtils.logE("title"+mList.get(arg2 - 1));
                            startActivity(new Intent().putExtra("user_id", mList.get(arg2 - 1).getUsers_id() + "").putExtra("title", mList.get(arg2 - 1).getTruename()).putExtra("insert",true).setClass(context, LawyerOfficeActivity.class));
                        } catch (Exception e) {
                            // TODO: handle exception
                        }
                    }
                });

        lv_lawyer.setOnScrollListener(new OnScrollListener() {

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

                iv_btn_to_top.setVisibility(View.VISIBLE);

                // 判断滚动到顶部
                if (lv_lawyer.getRefreshableView().getFirstVisiblePosition() == 0) {
                    iv_btn_to_top.setVisibility(View.GONE);
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });

        lv_lawyer.setMode(Mode.BOTH);
        // 刷新
        lv_lawyer.setOnRefreshListener(this);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                lv_lawyer.setRefreshing(true);
            }
        }, 500);
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            isPullRefresh = true;
            LogUtils.logE(index + "重置前");
            resetPageIndex();
            LogUtils.logE(index + "重置后的");
            if (!loading.isShow()) {
                loading.showDialog(LawyerNationalActivity.this);
            }
            dataInit();
            super.handleMessage(msg);
        }
    };

    private UMShareListener umShareListener = new UMShareListener() {
        @Override
        public void onResult(SHARE_MEDIA platform) {
//            Log.d("plat", "platform" + platform);
            if (platform.name().equals("WEIXIN_FAVORITE")) {
                Toast.makeText(context, platform + " 收藏成功啦", Toast.LENGTH_SHORT)
                        .show();
            } else {
                Toast.makeText(context, platform + " 分享成功啦", Toast.LENGTH_SHORT)
                        .show();
            }
        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(context, platform + " 分享失败啦", Toast.LENGTH_SHORT)
                    .show();
            if (t != null) {
                Log.d("throw", "throw:" + t.getMessage());
            }
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(context, platform + " 分享取消了", Toast.LENGTH_SHORT)
                    .show();
        }
    };

    /**
     * 综合排序
     */
    private void sortViewInit(TextView view) {
        tv_sort.setSelected(true);
        tv_comment.setSelected(false);
        tv_classify.setSelected(false);

        List<SortEntity> sortList = new ArrayList<SortEntity>();
        sortList.clear();
        sortList.add(new SortEntity("综合排序", "1", "a", false));
        sortList.add(new SortEntity("回复咨询好评率", "2", "c", false));
//        sortList.add(new SortEntity("办理案件好评率", "3", "e", false));

        SortView sortView = new SortView();
        sortView.setSortViews(this, view, sortList);

        //法律咨询好评率legal_advice_rate 办理案件好评率 handle_case_rate

        sortView.setSelects(new SortView.Select() {
            @Override
            public void index(int index) {
                if (index == 0) {
                    item = "";
                } else if (index == 1) {
                    item = "legal_advice_rate";
                } else {
                    item = "handle_case_rate";
                }
                handler.sendEmptyMessage(1);
            }
        });

    }


    private void getLawyerExpertise(TextView view) {
        RequestParams params = new RequestParams();
        httpDataUtils.sendGet(MyData.CASETYPE, params);
        httpDataUtils.setSucessBack(new HttpDataUtils.SucessBack() {
            @Override
            public void sucess(ResponseInfo<String> arg0) {
                if (httpDataUtils.code(arg0.result)) {
                    CaseTypeEntity entity = gson.fromJson(arg0.result, CaseTypeEntity.class);
                    sortList = new ArrayList<SortEntity>();
                    sortList.clear();
                    for (int i = 0; i < entity.getData().size(); i++) {
                        sortList.add(new SortEntity(entity.getData().get(i).getName(), entity.getData().get(i).getId() + "", false));
                    }
                    sortList.remove(sortList.size() - 1);
                    sortList.add(0, new SortEntity("全部", "0", false));
                    classifyViewInit(tv_classify);
                }
            }
        });
    }


    /**
     * 专长分类
     */
    private void classifyViewInit(TextView view) {
        tv_sort.setSelected(false);
        tv_comment.setSelected(false);
        tv_classify.setSelected(true);

        SortView sortView = new SortView();
        sortView.setSortViews(this, view, sortList);
        sortView.setSelects(new SortView.Select() {
            @Override
            public void index(int index) {
                if (index == 0) {
                    value = 0;
                } else {
                    value = index;
                }
                handler.sendEmptyMessage(1);
            }
        });
    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
        resetPageIndex();
        isPullRefresh = true;
        dataInit();
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
        addPageIndex();
        isPullRefresh = false;
        dataInit();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // 加载完成后停止刷新
                lv_lawyer.onRefreshComplete();
            }
        }, 1000);
    }

    private void resetPageIndex() {
        index = 1;
    }

    private void addPageIndex() {
        ++index;
    }

}
