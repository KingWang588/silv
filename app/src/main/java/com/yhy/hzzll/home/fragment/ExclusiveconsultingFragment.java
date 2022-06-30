package com.yhy.hzzll.home.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.google.gson.Gson;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.yhy.hzzll.R;
import com.yhy.hzzll.entity.OrderListEntity;
import com.yhy.hzzll.framework.MyData;
import com.yhy.hzzll.home.activity.consult.PursueAskActivity;
import com.yhy.hzzll.my.activity.QuickConsultingOrderDetailsActivity;
import com.yhy.hzzll.my.adapter.OrderListsAdapter;
import com.yhy.hzzll.utils.HttpDataUtils;
import com.yhy.hzzll.utils.PrefsUtils;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ExclusiveconsultingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ExclusiveconsultingFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ExclusiveconsultingFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ExclusiveconsultingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ExclusiveconsultingFragment newInstance(String param1, String param2) {
        ExclusiveconsultingFragment fragment = new ExclusiveconsultingFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_exclusiveconsulting, container, false);
        ViewUtils.inject(this, inflate);
        initView();
        return inflate;
    }


    @ViewInject(R.id.lv_order)
    private ListView lv_order;
    private OrderListsAdapter mAdapter;
    //数据分页
    private int index = 1;
    //总页数
    private int maxpage;


    @ViewInject(R.id.tv_no_data)
    private TextView tv_no_data;

    HttpDataUtils httpDataUtils;
    ArrayList<OrderListEntity.DataBean.ListBean> mList;

    private void initView() {

        httpDataUtils = new HttpDataUtils(getActivity());
        mList = new ArrayList<>();
        mAdapter = new OrderListsAdapter(getActivity(), mList,pursue);
        lv_order.setAdapter(mAdapter);

        lv_order.setEmptyView(tv_no_data);

        lv_order.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(getActivity(), QuickConsultingOrderDetailsActivity.class).putExtra("order_no", mList.get(position).getOrder_no()));
            }
        });

        dataInit();

    }


    View.OnClickListener pursue = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int position = (int) v.getTag();
            startActivity(new Intent(getActivity(), PursueAskActivity.class)
                    .putExtra("reply_id",mList.get(position).getReply_id()+"")
                    .putExtra("id",mList.get(position).getConsult_id()+""+"")
            .putExtra("message_id",mList.get(position).getMessage_id()+""));
        }
    };


    private void dataInit() {

            String url = MyData.ORDER_LIST + "-" + "0"+ "-" + "month" + "-" + "0" + "-" + "1" + "-" + "10";
            RequestParams params = new RequestParams();
            params.addHeader("Authorization", PrefsUtils.getString(getActivity(), PrefsUtils.AUTHORIZATION));
            httpDataUtils.sendGet(url, params);
            httpDataUtils.setSucessBack(new HttpDataUtils.SucessBack() {

                @Override
                public void sucess(ResponseInfo<String> arg0) {

                    if (httpDataUtils.code(arg0.result)) {

                        Gson gson = new Gson();
                        OrderListEntity entity = gson.fromJson(arg0.result, OrderListEntity.class);
                        mList.addAll(entity.getData().getList());
                        mAdapter.notifyDataSetChanged();
                    }

                }

            });
            httpDataUtils.setFailBack(new HttpDataUtils.FailBack() {

                @Override
                public void fail(String msg) {

                }
            });
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
}
