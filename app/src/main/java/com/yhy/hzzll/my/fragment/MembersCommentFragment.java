package com.yhy.hzzll.my.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.google.gson.Gson;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.yhy.hzzll.R;
import com.yhy.hzzll.adapter.MemberCommentAdapter;
import com.yhy.hzzll.entity.MemberCommentEntity;
import com.yhy.hzzll.entity.SortEntity;
import com.yhy.hzzll.framework.MyData;
import com.yhy.hzzll.mian.fragment.BaseFragment;
import com.yhy.hzzll.utils.HttpDataUtils;
import com.yhy.hzzll.utils.HttpDataUtils.FailBack;
import com.yhy.hzzll.utils.JSONTool;
import com.yhy.hzzll.view.SortView;
import com.yhy.hzzll.widget.CustomListview;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 会员评论
 */
public class MembersCommentFragment extends BaseFragment {

    @ViewInject(R.id.lv_office)
    CustomListview listview;
    @ViewInject(R.id.ll_publish)
    LinearLayout ll_publish;
    @ViewInject(R.id.tv_no_data)
    TextView tv_no_data;
    @ViewInject(R.id.tv_applause_rate)
    TextView tv_applause_rate;
    @ViewInject(R.id.tv_category)
    TextView tv_category;
    @ViewInject(R.id.tv_evaluation_num)
    TextView tv_evaluation_num;


    private Activity mActivity;
    private MemberCommentAdapter mAdapter;
    private ArrayList<MemberCommentEntity.DataBean.ListBean> mList;
    private HttpDataUtils httpDataUtils;

    public static final String IS_LOOK_OTHER_OFFICE_INTENT = "IS_LOOK_OTHER_OFFICE_INTENT";
    private boolean isLookOtherOffice;
    public static final String USER_ID_INTENT = "USER_ID_INTENT";
    private String uid;
    private String star = "";
    private String type_id = "";

    public static MembersCommentFragment newInstance(String uid, boolean isLookOtherOffice) {
        MembersCommentFragment fragment = new MembersCommentFragment();
        Bundle args = new Bundle();
        args.putBoolean(IS_LOOK_OTHER_OFFICE_INTENT, isLookOtherOffice);
        args.putString(USER_ID_INTENT, uid);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            isLookOtherOffice = getArguments().getBoolean(IS_LOOK_OTHER_OFFICE_INTENT);
            uid = getArguments().getString(USER_ID_INTENT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_member_comment, null);
        ViewUtils.inject(this, view);
        inits();
        return view;
    }

    private void inits() {
        mList = new ArrayList<>();
        mAdapter = new MemberCommentAdapter(mActivity, mList);
        listview.setEmptyView(tv_no_data);
        listview.setAdapter(mAdapter);
        httpDataUtils = new HttpDataUtils(mActivity);
//        if (isLookOtherOffice) {
//            ll_publish.setVisibility(View.GONE);
//        } else {
//            ll_publish.setVisibility(View.VISIBLE);
//        }
        getList();
    }

    /**
     * 会员评论列表
     */

    private void getList() {
        RequestParams params = new RequestParams();
        params.addQueryStringParameter("user_id", uid);
        params.addQueryStringParameter("type_id",type_id);
        params.addQueryStringParameter("star",star);
        params.addQueryStringParameter("page_num","1000");
        httpDataUtils.sendGet(MyData.GET_EVALUATE_LIST, params);
        httpDataUtils.setSucessBack(new HttpDataUtils.SucessBack() {

            @Override
            public void sucess(ResponseInfo<String> arg0) {
                mList.clear();
                try {
                    JSONObject object = new JSONObject(arg0.result);
                    String code = JSONTool.getString(object, "code");
                    String msg = JSONTool.getString(object, "msg");
                    if (code.equals("0")) { // 成功
                        Gson gson = new Gson();
                        MemberCommentEntity memberCommentEntity = gson.fromJson(object.toString(), MemberCommentEntity.class);
                        if (memberCommentEntity.getData().getList() != null) {
                            mList.addAll(memberCommentEntity.getData().getList());
                        }

                        mAdapter.notifyDataSetChanged();
                        tv_evaluation_num.setText(memberCommentEntity.getData().getTotal() + "");

                    } else {
//						ToastUtils.getUtils(mActivity).show(msg);
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

    @OnClick({R.id.tv_applause_rate, R.id.tv_category})
    public void onclick(View view) {
        switch (view.getId()) {
//            case R.id.tv_publish_comment:
//                postComment();
//                break;
            case R.id.tv_applause_rate:
                sortViewInit(tv_applause_rate);
                break;
            case R.id.tv_category:
                sortViewInit2(tv_category);
                break;
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = activity;
    }


    /**
     * 好评率
     */
    private void sortViewInit(TextView view) {

        List<SortEntity> sortList = new ArrayList<SortEntity>();
        sortList.clear();
        sortList.add(new SortEntity("全部", "1", "a", false));
        sortList.add(new SortEntity("好评", "2", "c", false));
        sortList.add(new SortEntity("中评", "3", "e", false));
        sortList.add(new SortEntity("差评", "4", "e", false));

        SortView sortView = new SortView();
        sortView.setSortViews(getActivity(), view, sortList);
        sortView.setSelects(new SortView.Select() {
            @Override
            public void index(int index) {
                if (index == 0) {
                    star = "";
                } else if (index == 1) {
                    star = "3";
                }  else if (index == 2) {
                    star = "2";
                } else if (index == 3) {
                    star = "1";
                }
                handler.sendEmptyMessage(1);
            }
        });
    }

    /**
     * 好评率
     */
    private void sortViewInit2(TextView view) {

        List<SortEntity> sortList = new ArrayList<SortEntity>();
        sortList.clear();
        sortList.add(new SortEntity("全部", "0", "a", false));
        sortList.add(new SortEntity("快速咨询", "1", "e", false));
        sortList.add(new SortEntity("专属咨询", "2", "e", false));
//        sortList.add(new SortEntity("律师协作", "3", "e", false));

        SortView sortView = new SortView();
        sortView.setSortViews(getActivity(), view, sortList);
        sortView.setSelects(new SortView.Select() {
            @Override
            public void index(int index) {
                if (index == 0) {
                    type_id = "";
                } else if (index == 1) {
                    type_id = "1";
                } else if (index == 2) {
                  type_id = "2";
                } else if (index == 3){
                 type_id = "3";
                }
                handler.sendEmptyMessage(1);
            }
        });
    }

    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            getList();
            super.handleMessage(msg);

        }
    };


    private void filterList(String type_id) {
        RequestParams params = new RequestParams();
        params.addQueryStringParameter("user_id", uid);
        params.addQueryStringParameter("type_id", type_id);
        httpDataUtils.sendGet(MyData.GET_EVALUATE_LIST, params);
        httpDataUtils.setSucessBack(new HttpDataUtils.SucessBack() {

            @Override
            public void sucess(ResponseInfo<String> arg0) {
                mList.clear();
                try {
                    JSONObject object = new JSONObject(arg0.result);
                    String code = JSONTool.getString(object, "code");
                    String msg = JSONTool.getString(object, "msg");
                    if (code.equals("0")) { // 成功
                        Gson gson = new Gson();
                        MemberCommentEntity memberCommentEntity = gson.fromJson(object.toString(), MemberCommentEntity.class);
                        if (memberCommentEntity.getData().getList() != null) {
                            mList.addAll(memberCommentEntity.getData().getList());
                        }

                        mAdapter.notifyDataSetChanged();
                        tv_evaluation_num.setText(memberCommentEntity.getData().getTotal() + "");
                    } else {
//						ToastUtils.getUtils(mActivity).show(msg);
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


}
