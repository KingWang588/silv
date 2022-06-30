package com.yhy.hzzll.home.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.yhy.hzzll.R;
import com.yhy.hzzll.common.entity.CaseTypeEntity;
import com.yhy.hzzll.entity.ConsultEntity;
import com.yhy.hzzll.entity.SortEntity;
import com.yhy.hzzll.framework.MyData;
import com.yhy.hzzll.home.activity.consult.ConsultDetailsActivity;
import com.yhy.hzzll.home.adapter.ConsultAdapter;
import com.yhy.hzzll.utils.HttpDataUtils;
import com.yhy.hzzll.utils.KeyboardUtils;
import com.yhy.hzzll.utils.PrefsUtils;
import com.yhy.hzzll.utils.ToastUtils;
import com.yhy.hzzll.view.PopupWindowAddress;
import com.yhy.hzzll.view.SortView;

import java.util.ArrayList;
import java.util.List;

public class AllConsultingFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";

    boolean only_the_latest = false;

    public AllConsultingFragment() {
        // Required empty public constructor
    }

    public static AllConsultingFragment newInstance(boolean only_the_latest) {
        AllConsultingFragment fragment = new AllConsultingFragment();
        Bundle args = new Bundle();
        args.putBoolean(ARG_PARAM1, only_the_latest);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            only_the_latest = getArguments().getBoolean(ARG_PARAM1, false);
        }
    }

    @ViewInject(R.id.tv_problem_types)
    private TextView tv_problem_types;
    @ViewInject(R.id.tv_consultation)
    private TextView tv_consultation;
    @ViewInject(R.id.tv_address)
    private TextView tv_address;

    @ViewInject(R.id.tv_no_data)
    private TextView tv_no_data;

    @ViewInject(R.id.lv_consult)
    private PullToRefreshListView lv_consult;

    @ViewInject(R.id.et_search)
    EditText et_search;

    @ViewInject(R.id.tv_reset)
    TextView tv_reset;
    @ViewInject(R.id.iv_btn_to_top)
    private ImageView iv_btn_to_top;

    @ViewInject(R.id.iv_search_btn)
    private ImageView iv_search_btn;


    @ViewInject(R.id.linear_only)
    private LinearLayout linear_only;

    HttpDataUtils httpDataUtils;
    ConsultAdapter consultAdapter;

    List<ConsultEntity.DataBean.ListBean> mList;

    /**
     * 标记是刷新还是加载更多
     */
    private boolean isPullRefresh = true;

    //数据分页
    private int index = 1;
    //总页数
    private int maxpage;
    //问题类型
    private String consult_type = "3"; // 3是律师最新咨询，4是律师我的未回复的咨询
    private String quick_consult_type = "";
    private String address = "";
    private String keyword = "";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_all_consulting, container, false);
        ViewUtils.inject(this, view);
        httpDataUtils = new HttpDataUtils(getActivity());
        viewInit();
        return view;
    }

    private void viewInit() {

        if (only_the_latest) {
            linear_only.setVisibility(View.GONE);
        }

        mList = new ArrayList<>();
        consultAdapter = new ConsultAdapter(getActivity(), mList);

        View headerview1 = View.inflate(getActivity(), R.layout.fragment_all_consult_header, null);
        ViewUtils.inject(this, headerview1);
        lv_consult.getRefreshableView().addHeaderView(headerview1);

        lv_consult.setAdapter(consultAdapter);
//        lv_consult.setEmptyView(tv_no_data);
        lv_consult.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                startActivity(new Intent(getActivity(), ConsultDetailsActivity.class).putExtra("id", mList.get(position - 2).getId() + "")
                        .putExtra("title", mList.get(position - 2).getContent()).putExtra("insert", true));
            }
        });

        lv_consult.setOnScrollListener(new AbsListView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                iv_btn_to_top.setVisibility(View.VISIBLE);
                // 判断滚动到顶部
                if (lv_consult.getRefreshableView().getFirstVisiblePosition() == 0) {
                    iv_btn_to_top.setVisibility(View.GONE);
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (firstVisibleItem != 0) {
//                    linear_only.setVisibility(View.GONE);
                } else {
//                    linear_only.setVisibility(View.VISIBLE);
                }
            }
        });

        lv_consult.setMode(PullToRefreshBase.Mode.BOTH);
        // 刷新
        lv_consult.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
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
                        lv_consult.onRefreshComplete();
                    }
                }, 1000);
            }
        });

        dataInit();
    }

    private void resetPageIndex() {
        index = 1;
    }

    private void addPageIndex() {
        ++index;
    }

    private void dataInit() {
        RequestParams params = new RequestParams();
        params.addHeader("Authorization", PrefsUtils.getString(getActivity(), PrefsUtils.AUTHORIZATION));

        params.addQueryStringParameter("consult_type", consult_type);
        params.addQueryStringParameter("client", "2");
        if (!isPullRefresh) {
            if (index > maxpage) {
                ToastUtils.getUtils(getActivity()).show("已到底部！");
                return;
            } else {
                params.addQueryStringParameter("page", index + "");
            }
        } else {
            params.addQueryStringParameter("page", index + "");
        }
        params.addQueryStringParameter("legal_type", quick_consult_type);
        params.addQueryStringParameter("city", address);

        Log.e("gsd", address);

        keyword = et_search.getText().toString();
        if (keyword.length() != 0) {
            params.addQueryStringParameter("keyword", keyword);
        }

        httpDataUtils.sendGet(MyData.CONSULT_LIST, params);
        httpDataUtils.setSucessBack(new HttpDataUtils.SucessBack() {
            @Override
            public void sucess(ResponseInfo<String> arg0) {
                if (httpDataUtils.code(arg0.result)) {
                    Gson gson = new Gson();
                    ConsultEntity entity = gson.fromJson(arg0.result, ConsultEntity.class);

                    if (isPullRefresh) {
                        mList.clear();
                        maxpage = entity.getData().getPages();
                    }

                    if (entity.getData().getTotal() == 0) {
                        ToastUtils.getUtils(getActivity()).show("没有查询到相应数据。");
                    }

                    mList.addAll(entity.getData().getList());
                    consultAdapter.notifyDataSetChanged();
                    lv_consult.onRefreshComplete();
                }
            }
        });
        httpDataUtils.setFailBack(new HttpDataUtils.FailBack() {
            @Override
            public void fail(String msg) {
                lv_consult.onRefreshComplete();
            }
        });
    }

    @OnClick({R.id.iv_search_btn, R.id.tv_problem_types, R.id.tv_consultation, R.id.tv_address, R.id.iv_btn_to_top, R.id.tv_reset})
    public void onClick(View view) {
        switch (view.getId()) {


            case R.id.tv_reset:

                quick_consult_type = "";
                tv_problem_types.setText("问题类型");
                consult_type = "3";
                tv_consultation.setText("最新咨询");
                address="";
                tv_address.setText("地区选择");

                et_search.setText("");

                resetPageIndex();
                lv_consult.setRefreshing();
                isPullRefresh = true;
                KeyboardUtils.hideKeyboard(view);
                dataInit();

                break;
            case R.id.iv_btn_to_top:

                lv_consult.getRefreshableView().setSelection(0);
                iv_btn_to_top.setVisibility(View.GONE);

                break;
            case R.id.tv_problem_types:
                getLawyerExpertise(tv_problem_types);
                break;
            case R.id.tv_consultation:
                sortViewInit(tv_consultation);
                break;
            case R.id.tv_address:

                final PopupWindowAddress popupWindowAddress = new PopupWindowAddress(getActivity(), "2", false);
                popupWindowAddress.setAddresskListener(new PopupWindowAddress.OnAddressCListener() {
                    @Override
                    public void onClick(String province, String city, String area, String provincesid, String cityid, String areaid) {
//                        ToastUtils.getUtils(getActivity()).show(province+"province"+city+"city"+area+"area"+provincesid+"provincesid"+cityid+"cityid"+areaid+"areaid");
                        if (province.equals("全国")) {
                            address = "";
                            handler.sendEmptyMessage(1);
                            popupWindowAddress.dismiss();
                            tv_address.setText(province);
                            return;
                        }
                        if (city.equals("不限")) {
                            address = provincesid;
//                            address = address.substring(0, 2);
                            handler.sendEmptyMessage(1);
                            tv_address.setText(province);
                            popupWindowAddress.dismiss();
                            return;
                        } else {
                            address = cityid;
//                            address = address.substring(0, 4);
                            handler.sendEmptyMessage(1);
                            tv_address.setText(city);
                            popupWindowAddress.dismiss();
                            return;
                        }
                    }
                });
                popupWindowAddress.showAsDropDown(tv_address);

                break;
            case R.id.iv_search_btn:
                resetPageIndex();
                isPullRefresh = true;
                KeyboardUtils.hideKeyboard(view);
                dataInit();
                break;
        }
    }

    /**
     * 最新咨询
     */

    private void sortViewInit(TextView view) {
        tv_consultation.setSelected(true);
        tv_problem_types.setSelected(false);
        tv_address.setSelected(false);

        List<SortEntity> sortList = new ArrayList<SortEntity>();
        sortList.clear();

        sortList.add(new SortEntity("最新咨询", "1", "a", false));
        sortList.add(new SortEntity("我未回复的咨询", "2", "c", false));


        SortView sortView = new SortView();
        sortView.setSortViews(getActivity(), view, sortList);

        sortView.setSelects(new SortView.Select() {
            @Override
            public void index(int index) {
                if (index == 0) {
                    consult_type = "3";
                    handler.sendEmptyMessage(1);
                } else if (index == 1) {
                    consult_type = "4";

                }
                handler.sendEmptyMessage(1);
            }
        });

    }

    /**
     * 问题类型
     *
     * @param view
     */

    List<SortEntity> sortList_type;

    private void getLawyerExpertise(final TextView view) {
        RequestParams params = new RequestParams();
        httpDataUtils.sendGet(MyData.CASETYPE, params);
        httpDataUtils.setSucessBack(new HttpDataUtils.SucessBack() {
            @Override
            public void sucess(ResponseInfo<String> arg0) {
                if (httpDataUtils.code(arg0.result)) {
                    Gson gson = new Gson();
                    CaseTypeEntity entity = gson.fromJson(arg0.result, CaseTypeEntity.class);
                    sortList_type = new ArrayList<>();
                    sortList_type.clear();
                    for (int i = 0; i < entity.getData().size(); i++) {
                        sortList_type.add(new SortEntity(entity.getData().get(i).getName(), entity.getData().get(i).getId() + "", false));
                    }
                    sortList_type.remove(sortList_type.size() - 1);
                    sortList_type.add(0, new SortEntity("全部", "0", true));
                    classifyViewInit(view);
                }
            }
        });
    }

    /**
     * 问题类型
     */
    private void classifyViewInit(TextView view) {
        tv_problem_types.setSelected(true);
        tv_address.setSelected(false);
        tv_consultation.setSelected(false);

        SortView sortView = new SortView();
        sortView.setSortViews(getActivity(), view, sortList_type, getResources().getDisplayMetrics().widthPixels, getResources().getDisplayMetrics().heightPixels / 2);
        sortView.setSelects(new SortView.Select() {
            @Override
            public void index(int index) {

                if (index == 0) {
                    quick_consult_type = "";
                } else {
                    quick_consult_type = index + "";
                }

                handler.sendEmptyMessage(1);
            }
        });
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            isPullRefresh = true;
            resetPageIndex();
            dataInit();
            super.handleMessage(msg);
        }
    };

}
