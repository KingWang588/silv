package com.yhy.hzzll.home.activity.collaborate;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
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

import com.google.gson.Gson;
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
import com.yhy.hzzll.adapter.LayerCooperationAdapter;
import com.yhy.hzzll.entity.FocusListEntity;
import com.yhy.hzzll.entity.LawyerCoopEntity;
import com.yhy.hzzll.entity.SortEntity;
import com.yhy.hzzll.framework.MyData;
import com.yhy.hzzll.home.activity.ScreeningActivity;
import com.yhy.hzzll.home.activity.consult.ConsultActivity;
import com.yhy.hzzll.home.activity.newcollaborate.CollaborationPublishActivity;
import com.yhy.hzzll.home.activity.newcollaborate.PickUpCaseActivity;
import com.yhy.hzzll.home.entity.SelectTypeEntity;
import com.yhy.hzzll.mian.activity.BaseActivity;
import com.yhy.hzzll.mian.activity.LoginActivity;
import com.yhy.hzzll.my.activity.ExclusiveLawyerActivity;
import com.yhy.hzzll.utils.HttpDataUtils;
import com.yhy.hzzll.utils.JSONTool;
import com.yhy.hzzll.utils.KeyboardUtils;
import com.yhy.hzzll.utils.MsgEvent;
import com.yhy.hzzll.utils.PrefsUtils;
import com.yhy.hzzll.utils.ToastUtils;
import com.yhy.hzzll.view.DialogLoading;
import com.yhy.hzzll.view.PopupWindowAddress;
import com.yhy.hzzll.view.SharePopupWindow;
import com.yhy.hzzll.view.SortView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import de.greenrobot.event.EventBus;


/**
 * 首页--全国律师协作
 *
 * @author Yang
 */
public class LawyerNationalCooperationActivity extends BaseActivity implements OnRefreshListener2<ListView> {
    @ViewInject(R.id.ll_office_table)
    private LinearLayout ll_office_table;

    /**
     * 是否打开功能按钮
     */
    private boolean isClose = false;

    private LayerCooperationAdapter cooperationAdapter;

    @ViewInject(R.id.lv_lawyer)
    private PullToRefreshListView lv_lawyer;

    @ViewInject(R.id.tv_lsxz)
    private TextView tv_lsxz;

    @ViewInject(R.id.tv_pls)
    private TextView tv_pls;

    @ViewInject(R.id.tv_tabline)
    private TextView tv_tabline;


    private int moveX;
    private int currentX;
    private int nextX;

    @ViewInject(R.id.tv_money)
    private TextView tv_money;
    @ViewInject(R.id.tv_deal)
    private TextView tv_deal;
    @ViewInject(R.id.tv_bolt)
    private TextView tv_bolt;

    @ViewInject(R.id.iv_btn_to_top)
    private ImageView iv_btn_to_top;

    /**
     * 搜索的关键字
     */
    @ViewInject(R.id.et_keyword)
    private EditText et_keyword;

    /**
     * 数据分页
     */
    private int index = 1;
    /**
     * 总页数
     */
    private int maxpage;

    /**
     * 是否只查看邀请律师中的条目选项开关
     */
    private boolean isStatus;

    /**
     * 是否只查看邀请律师中的条目选项开关
     */
    private boolean isStatus_1;

    private DialogLoading loading;

    /**
     * 酬金排序 降序 asc 升序 desc 全部“”
     */
    private String value = "";

    /**
     * 托管 1 未托管 2 全部“”
     */
    private String assure = "";

    /**
     * 只看邀请中的 0 / 全部 ""
     */
    private String status = "";

    /**
     * 排序的分类列表
     */
    private List<SortEntity> sortList = new ArrayList<SortEntity>();

    // 全部协作
    private List<LawyerCoopEntity> allList;
    // 当前显示的list
    private List<LawyerCoopEntity.DataBean.ListBean> currentList = new ArrayList<>();

    @ViewInject(R.id.rb_check)
    private TextView rb_check;

    @ViewInject(R.id.rb_check_1)
    private TextView rb_check_1;


    String time = "";
    String address = "";
    String look = "";
    String keyword = "";
    String delegate_type = "";

    @Override
    protected void onCreate(Bundle arg0) {
        // TODO Auto-generated method stub
        setContentView(R.layout.activity_lawyernational_cooperation);
        super.onCreate(arg0);
        loading = new DialogLoading();
        ViewUtils.inject(this);
        viewInit();
        screeningClear();

        // 更改进入时间
        Date dt2 = new Date();
        Long time2 = dt2.getTime();
        PrefsUtils.saveString(LawyerNationalCooperationActivity.this, PrefsUtils.CLICKLAWCOOP, String.valueOf(time2));

        coopListData();

    }

    private void viewInit() {
        lv_lawyer.getRefreshableView().setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

                startActivity(new Intent(LawyerNationalCooperationActivity.this, PickUpCaseActivity.class)
                        .putExtra("order_no", currentList.get(arg2 - 1).getOrder_no()).putExtra("type", currentList.get(arg2 - 1).getType()));

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

        cooperationAdapter = new LayerCooperationAdapter(context, currentList);
        lv_lawyer.setAdapter(cooperationAdapter);
        lv_lawyer.setMode(Mode.BOTH);
        lv_lawyer.setOnRefreshListener(this);

    }

    /**
     * 清楚筛选条件
     */
    private void screeningClear() {
        PrefsUtils.saveString(context, ScreeningActivity.SCREENPROVINCEID, "");
        PrefsUtils.saveString(context, ScreeningActivity.SCREENCITYID, "");
        PrefsUtils.saveString(context, ScreeningActivity.SCREENAREAID, "");
        PrefsUtils.saveString(context, ScreeningActivity.SCREENAREANAME, "");
        PrefsUtils.saveString(context, ScreeningActivity.SCREENPRICENAME, "");
        PrefsUtils.saveString(context, ScreeningActivity.SCREENPRICEID, "");
        PrefsUtils.saveString(context, ScreeningActivity.SCREENTYPE1NAME, "");
        PrefsUtils.saveString(context, ScreeningActivity.SCREENTYPE1ID, "");
        PrefsUtils.saveString(context, ScreeningActivity.SCREENTYPE2NAME, "");
        PrefsUtils.saveString(context, ScreeningActivity.SCREENTYPE2ID, "");
    }


    private void coopListData() {

        RequestParams params = new RequestParams();
        params.addHeader("Authorization", PrefsUtils.getString(LawyerNationalCooperationActivity.this, PrefsUtils.AUTHORIZATION));
        params.addQueryStringParameter("status", "");
        params.addQueryStringParameter("time", time);
        params.addQueryStringParameter("type", assure);
        params.addQueryStringParameter("keyword", keyword);
        params.addQueryStringParameter("look", look);
        params.addQueryStringParameter("page", String.valueOf(index));
        params.addQueryStringParameter("base_region_id", address);
        params.addQueryStringParameter("delegate_type", delegate_type);
//        params.addBodyParameter("limit", "10");

        httpDataUtils.sendGet(MyData.LAWYER_LIST, params);
        httpDataUtils.setSucessBack(new HttpDataUtils.SucessBack() {
            @Override
            public void sucess(ResponseInfo<String> arg0) {

                lv_lawyer.onRefreshComplete();

                try {
                    JSONObject object = new JSONObject(arg0.result);
                    String code = JSONTool.getString(object, "code");
                    String msg = JSONTool.getString(object, "message");
                    if (code.equals("0")) { // 成功
                        Gson gson = new Gson();
                        LawyerCoopEntity entity = gson.fromJson(object.toString(), LawyerCoopEntity.class);

                        if (isPullRefresh) {
                            currentList.clear();
                        }

                        if (entity.getData().getList().size() == 0) {
                            ToastUtils.getUtils(LawyerNationalCooperationActivity.this).show("没有查询到相应数据");
                        }

                        currentList.addAll(entity.getData().getList());
                        cooperationAdapter.notifyDataSetChanged();

                    } else {
                        ToastUtils.getUtils(getApplicationContext()).show(msg);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

//                if (loading != null)
//                    loading.dismissDialog();
            }
        });
        httpDataUtils.setFailBack(new HttpDataUtils.FailBack() {
            @Override
            public void fail(String msg) {
//                if (loading != null)
//                    loading.dismissDialog();
            }
        });
    }


    @OnClick({R.id.rb_check, R.id.tv_table, R.id.tv_lsxz, R.id.tv_pls, R.id.tv_tool, R.id.tv_toolnav, R.id.tv_money,
            R.id.tv_deal, R.id.tv_bolt, R.id.tv_publish, R.id.iv_serach, R.id.tv_share, R.id.iv_btn_to_top, R.id.rb_check_1, R.id.tv_reset})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_reset:

                et_keyword.setText("");
                time = "";
                address = "";
                look = "";
                keyword = "";
                assure = "";
                rb_check.setSelected(false);
                rb_check_1.setSelected(false);
                delegate_type = "";

                tv_money.setText("最新发布");
                tv_deal.setText("地区");
                tv_bolt.setText("选择类型");

                handler.sendEmptyMessage(1);
                KeyboardUtils.hideKeyboard(view);

                break;


            case R.id.iv_btn_to_top:
                lv_lawyer.getRefreshableView().setSelection(0);
                iv_btn_to_top.setVisibility(View.GONE);
                break;

            case R.id.iv_serach:// 搜索
                if (!et_keyword.getText().toString().trim().isEmpty()) {
                    keyword = et_keyword.getText().toString().trim();
                    handler.sendEmptyMessage(1);
                    KeyboardUtils.hideKeyboard(view);
                } else {
                    ToastUtils.getUtils(context).show("请输入有效内容进行搜索！");
                }
                break;
            case R.id.rb_check://未申请 2
                if (isStatus) {
                    rb_check.setSelected(false);
                    look = "";
                } else {

                    if (isStatus_1) {
                        rb_check_1.setSelected(false);
                        isStatus_1 = !isStatus_1;
                    }

                    look = "2";
                    rb_check.setSelected(true);
                }
                isStatus = !isStatus;
                handler.sendEmptyMessage(1);
                break;

            case R.id.rb_check_1://已申请
                if (isStatus_1) {
                    rb_check_1.setSelected(false);
                    look = "";
                } else {

                    if (isStatus) {
                        isStatus = !isStatus;
                        rb_check.setSelected(false);
                    }

                    look = "1";
                    rb_check_1.setSelected(true);
                }
                isStatus_1 = !isStatus_1;

                handler.sendEmptyMessage(1);
                break;

            case R.id.tv_money://最新、酬金排序
                moneyViewInit(tv_money);
                break;
            case R.id.tv_deal:// 地区选择
                final PopupWindowAddress popupWindowAddress = new PopupWindowAddress(LawyerNationalCooperationActivity.this, "2", false);
                popupWindowAddress.setAddresskListener(new PopupWindowAddress.OnAddressCListener() {
                    @Override
                    public void onClick(String province, String city, String area, String provincesid, String cityid, String areaid) {
//                        ToastUtils.getUtils(getActivity()).show(province+"province"+city+"city"+area+"area"+provincesid+"provincesid"+cityid+"cityid"+areaid+"areaid");
                        if (province.equals("全国")) {
                            address = "";
                            handler.sendEmptyMessage(1);
                            popupWindowAddress.dismiss();
                            tv_deal.setText(province);
                            return;
                        }
                        if (city.equals("不限")) {
                            address = provincesid;
//                            address = address.substring(0, 2);
                            handler.sendEmptyMessage(1);
                            tv_deal.setText(province);
                            popupWindowAddress.dismiss();
                            return;
                        } else {
                            address = cityid;
//                            address = address.substring(0, 4);
                            handler.sendEmptyMessage(1);
                            tv_deal.setText(city);
                            popupWindowAddress.dismiss();
                            return;
                        }
                    }
                });
                popupWindowAddress.showAsDropDown(tv_deal);
                break;
            case R.id.tv_bolt:// 类型

                getSelectType();

                break;
            case R.id.tv_publish:// 发布律师协作


                if (PrefsUtils.getString(LawyerNationalCooperationActivity.this, PrefsUtils.AUTHORIZATION) == null || PrefsUtils.getString(LawyerNationalCooperationActivity.this, PrefsUtils.AUTHORIZATION).length() == 0) {
                    startActivity(new Intent().putExtra("activity", LawyerNationalCooperationActivity.class.toString())
                            .setClass(context, LoginActivity.class));
                    return;
                }

                startActivity(new Intent().setClass(context, CollaborationPublishActivity.class));
                break;
        }
    }

    SelectTypeEntity selectTypeEntity;

    private void getSelectType() {

        RequestParams params = new RequestParams();
        httpDataUtils.sendGet(MyData.SELEET_TYPE, params);
        httpDataUtils.setSucessBack(new HttpDataUtils.SucessBack() {
            @Override
            public void sucess(ResponseInfo<String> arg0) {

                try {
                    JSONObject object = new JSONObject(arg0.result);
                    String code = JSONTool.getString(object, "code");
                    String msg = JSONTool.getString(object, "message");
                    if (code.equals("0")) { // 成功
                        Gson gson = new Gson();
                        selectTypeEntity = gson.fromJson(arg0.result, SelectTypeEntity.class);

                        dealViewInit(tv_bolt);

                        sortList.clear();
                        sortList.add(new SortEntity("不限", "1", false));
                        for (int i = 0; i < selectTypeEntity.getData().size(); i++) {
                            sortList.add(new SortEntity(selectTypeEntity.getData().get(i).getName(), "1", false));
                        }

                    } else {
                        ToastUtils.getUtils(getApplicationContext()).show(msg);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
        httpDataUtils.setFailBack(new HttpDataUtils.FailBack() {
            @Override
            public void fail(String msg) {
            }
        });
    }


    private UMShareListener umShareListener = new UMShareListener() {
        @Override
        public void onResult(SHARE_MEDIA platform) {
            Log.d("plat", "platform" + platform);
            if (platform.name().equals("WEIXIN_FAVORITE")) {
                Toast.makeText(context, platform + " 收藏成功啦", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, platform + " 分享成功啦", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(context, platform + " 分享失败啦", Toast.LENGTH_SHORT).show();
            if (t != null) {
                Log.d("throw", "throw:" + t.getMessage());
            }
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(context, platform + " 分享取消了", Toast.LENGTH_SHORT).show();
        }
    };

    private void moneyViewInit(TextView view) {
        tv_money.setSelected(true);
        tv_deal.setSelected(false);
        tv_bolt.setSelected(false);
        sortList.clear();
        sortList.add(new SortEntity("最新发布", "1", false));
        sortList.add(new SortEntity("酬金从高到低", "2", false));
//		sortList.add(new SortEntity("酬金从低到高", 0, false));
        SortView sortView = new SortView();
        sortView.setSortViews(this, view, sortList);
        sortView.setSelects(new SortView.Select() {
            @Override
            public void index(int index) {
                switch (index) {
                    case 0:
                        time = "";
                        break;
                    case 1:
                        time = "desc";
                        break;
                }
                handler.sendEmptyMessage(1);
            }
        });
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
//            loading.showDialog(LawyerNationalCooperationActivity.this);
            resetPageIndex();
            isPullRefresh = true;
            coopListData();
            lv_lawyer.setRefreshing();
            super.handleMessage(msg);
        }

    };


    private void dealViewInit(final TextView view) {
        tv_money.setSelected(false);
        tv_deal.setSelected(false);
        tv_bolt.setSelected(true);

        final SortView sortView = new SortView();
        sortView.setSortViews(this, view, sortList);
        sortView.setSelects(new SortView.Select() {
            @Override
            public void index(int index) {
                switch (index) {
                    case 0:
                        assure = "";
                        handler.sendEmptyMessage(1);
                        break;
                    case 3:
                        assure = "6";

                        dealViewInit2(view);

                        sortList.clear();

                        for (int i = 0; i < selectTypeEntity.getData().get(2).getDelegate_type().size(); i++) {
                            sortList.add(new SortEntity(selectTypeEntity.getData().get(2).getDelegate_type().get(i).getName(), "1", false));
                        }

                        break;
                    case 1:
                        assure = "4";
                        handler.sendEmptyMessage(1);
                        break;
                    case 2:
                        assure = "5";
                        handler.sendEmptyMessage(1);
                        break;
                }

            }
        });
    }

    private void dealViewInit2(TextView view) {
        tv_money.setSelected(false);
        tv_deal.setSelected(false);
        tv_bolt.setSelected(true);

        final SortView sortView = new SortView();
        sortView.setSortViews(this, view, sortList);
        sortView.setSelects(new SortView.Select() {
            @Override
            public void index(int index) {

                delegate_type = selectTypeEntity.getData().get(2).getDelegate_type().get(index).getId() + "";
                ToastUtils.getUtils(LawyerNationalCooperationActivity.this).show("您选择了" + selectTypeEntity.getData().get(2).getDelegate_type().get(index).getName() + "      " + selectTypeEntity.getData().get(2).getDelegate_type().get(index).getId());
                handler.sendEmptyMessage(1);

            }
        });


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (resultCode == 1901) {
        }
        super.onActivityResult(requestCode, resultCode, intent);
    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
        isPullRefresh = true;
        resetPageIndex();
        coopListData();
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
        isPullRefresh = false;
        addPageIndex();
        coopListData();
    }

    private void resetPageIndex() {
        index = 1;
    }

    private void addPageIndex() {
        ++index;
    }


    boolean isPullRefresh;
}
