package com.yhy.hzzll.home.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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
import com.yhy.hzzll.utils.PrefsUtils;
import com.yhy.hzzll.view.SortView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link IAnsweredFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class IAnsweredFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public IAnsweredFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment IAnsweredFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static IAnsweredFragment newInstance(String param1, String param2) {
        IAnsweredFragment fragment = new IAnsweredFragment();
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


    // TODO: Rename method, update argument and hook method into UI event
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

    @ViewInject(R.id.tv_no_data)
    private TextView tv_no_data;

    @ViewInject(R.id.tv_show_num)
    private TextView tv_show_num;

    @ViewInject(R.id.tv_type)
    private TextView tv_type;

    @ViewInject(R.id.lv_consult)
    private PullToRefreshListView lv_consult;


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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_ianswered, container, false);
        ViewUtils.inject(this, view);
        httpDataUtils = new HttpDataUtils(getActivity());
        viewInit();
        return view;
    }

    private void viewInit() {

        mList = new ArrayList<>();
        consultAdapter = new ConsultAdapter(getActivity(), mList);
        lv_consult.setAdapter(consultAdapter);

        if (quick_consult_type.equals("")){
            tv_no_data.setText("您还未回复任何咨询，赶快语音回复抢红包。");
        }else{
            tv_no_data.setText("您还未回复过该类型的问题");
        }

        lv_consult.setEmptyView(tv_no_data);
        lv_consult.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(getActivity(), ConsultDetailsActivity.class)
                        .putExtra("id", mList.get(position - 1).getId() + "")
                        .putExtra("title", mList.get(position - 1).getContent()).putExtra("insert", true));
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
        params.addQueryStringParameter("page", index + "");
        params.addQueryStringParameter("lawyer_type", quick_consult_type);
        httpDataUtils.sendGet(MyData.CONSULT_ANSWER, params);
        httpDataUtils.setSucessBack(new HttpDataUtils.SucessBack() {
            @Override
            public void sucess(ResponseInfo<String> arg0) {

                if (httpDataUtils.code(arg0.result)) {
                    Gson gson = new Gson();

                    if (isPullRefresh) {
                        mList.clear();
                        isPullRefresh = false;
                    }

                    ConsultEntity entity = gson.fromJson(arg0.result, ConsultEntity.class);
                    SpannableString spannableString = new SpannableString("您已回答 " + entity.getData().getTotal() + " 条问题");
                    ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.parseColor("#FF9C00"));
                    RelativeSizeSpan sizeSpan02 = new RelativeSizeSpan(1.4f);
                    spannableString.setSpan(sizeSpan02, 4, spannableString.length() - 3, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                    spannableString.setSpan(colorSpan, 4, spannableString.length() - 3, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                    tv_show_num.setText(spannableString);

                    mList.addAll(entity.getData().getList());

                    for (int i = 0; i < mList.size(); i++) {
                        mList.get(i).setCount(1);
                    }

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


    @OnClick({R.id.tv_type})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_type:
                getLawyerExpertise(tv_type);
                break;
        }
    }

    /**
     * 问题类型
     *
     * @param view
     */
    private void getLawyerExpertise(final TextView view) {
        RequestParams params = new RequestParams();
        httpDataUtils.sendGet(MyData.CASETYPE, params);
        httpDataUtils.setSucessBack(new HttpDataUtils.SucessBack() {
            @Override
            public void sucess(ResponseInfo<String> arg0) {
                if (httpDataUtils.code(arg0.result)) {
                    Gson gson = new Gson();
                    CaseTypeEntity entity = gson.fromJson(arg0.result, CaseTypeEntity.class);
                    List<SortEntity> sortList = new ArrayList<>();
                    sortList.clear();
                    for (int i = 0; i < entity.getData().size(); i++) {
                        sortList.add(new SortEntity(entity.getData().get(i).getName(), entity.getData().get(i).getId() + "", false));
                    }
                    sortList.remove(sortList.size() - 1);
                    sortList.add(0, new SortEntity("全部", "0", false));
                    classifyViewInit(sortList, view);
                }
            }
        });
    }
    private String quick_consult_type = "";
    /**
     * 问题类型
     */
    private void classifyViewInit(List<SortEntity> sortList, TextView view) {

        SortView sortView = new SortView();
        sortView.setSortViews(getActivity(), view, sortList,getResources().getDisplayMetrics().widthPixels, getResources().getDisplayMetrics().heightPixels / 2);
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
            viewInit();
            super.handleMessage(msg);
        }
    };

}
