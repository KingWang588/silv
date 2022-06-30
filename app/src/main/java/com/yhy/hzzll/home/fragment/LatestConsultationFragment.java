package com.yhy.hzzll.home.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.google.gson.Gson;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.yhy.hzzll.R;
import com.yhy.hzzll.entity.ConsultEntity;
import com.yhy.hzzll.framework.MyData;
import com.yhy.hzzll.home.activity.consult.ConsultDetailsActivity;
import com.yhy.hzzll.home.adapter.ConsultAdapter;
import com.yhy.hzzll.utils.HttpDataUtils;
import com.yhy.hzzll.utils.PrefsUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link LatestConsultationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LatestConsultationFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    //    private List<ConsultEntity.list> allConsultlisList= new ArrayList<>();
    public LatestConsultationFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LatestConsultationFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LatestConsultationFragment newInstance(String param1, String param2) {
        LatestConsultationFragment fragment = new LatestConsultationFragment();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_latest_consultation, container, false);
        ViewUtils.inject(this, view);
        initView();
        return view;
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

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    @ViewInject(R.id.lv_latest)
    private ListView lv_latest;
    private ConsultAdapter consultAdapter;
    //数据分页
    private int index = 1;
    //总页数
    private int maxpage;

    HttpDataUtils httpDataUtils;
    List<ConsultEntity.DataBean.ListBean> mList;

    private void initView() {
        httpDataUtils = new HttpDataUtils(getActivity());
        mList = new ArrayList<>();
        consultAdapter = new ConsultAdapter(getActivity(), mList);
        lv_latest.setAdapter(consultAdapter);

        lv_latest.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(getActivity(), ConsultDetailsActivity.class)
                        .putExtra("id",mList.get(position).getId()+"")
                        .putExtra("title",mList.get(position).getContent()).putExtra("insert",true));
            }
        });

       lv_latest.setOnScrollListener(new AbsListView.OnScrollListener() {
           @Override
           public void onScrollStateChanged(AbsListView view, int scrollState) {

           }

           @Override
           public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

           }
       });

        dataInit();
    }

    private void dataInit() {
        RequestParams params = new RequestParams();
        params.addHeader("Authorization", PrefsUtils.getString(getActivity(), PrefsUtils.AUTHORIZATION));
        params.addQueryStringParameter("consult_type", "3");
        params.addQueryStringParameter("client", "2");
        params.addQueryStringParameter("page", index + "");
//        params.addQueryStringParameter("user_id", PrefsUtils.getString(getActivity(), PrefsUtils.UID));
        httpDataUtils.sendGet(MyData.CONSULT_LIST, params);
        httpDataUtils.setSucessBack(new HttpDataUtils.SucessBack() {
            @Override
            public void sucess(ResponseInfo<String> arg0) {
                if (httpDataUtils.code(arg0.result)) {
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


}
