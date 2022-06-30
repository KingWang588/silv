package com.yhy.hzzll.home.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.yhy.hzzll.R;
import com.yhy.hzzll.home.activity.consult.ConsultActivity;
import com.yhy.hzzll.home.view.DialogTipRule;
import com.yhy.hzzll.mian.activity.LoginActivity;
import com.yhy.hzzll.utils.PrefsUtils;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ConsultFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ConsultFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ConsultFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ConsultFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ConsultFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ConsultFragment newInstance(String param1, String param2) {
        ConsultFragment fragment = new ConsultFragment();
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

    @ViewInject(R.id.tv_all)
    private TextView tv_all;

    @ViewInject(R.id.tv_ask)
    private TextView tv_ask;

    @ViewInject(R.id.linear_rule)
    LinearLayout linear_rule;

//    @ViewInject(R.id.tv_money)
//    MarqueeView tv_money;

    View pview;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_consult, container, false);
        ViewUtils.inject(this,view);
        tv_all.setSelected(true);
        setDefaultFragment();
//        tv_money.setText("每时每刻28元咨询红包等您来拿，8亿普通百姓都在等您说说话。");
        return view;
    }

    AllConsultingFragment allConsultingFragment;
    IAnsweredFragment iAnsweredFragment;

    private void setDefaultFragment() {
        FragmentManager fm = getChildFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        allConsultingFragment = new AllConsultingFragment();
        transaction.replace(R.id.tv_fragment, allConsultingFragment);
        transaction.commit();
    }

    @OnClick({R.id.tv_all, R.id.tv_ask, R.id.ic_back, R.id.tv_rule, R.id.iv_close,R.id.tv_money})
    public void onClick(View view) {

        FragmentManager fm = getChildFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();

        switch (view.getId()) {
            case R.id.tv_rule:
            case R.id.tv_money:

                showRule();

                break;
            case R.id.iv_close:

//                tv_money.setVisibility(View.GONE);
                linear_rule.setVisibility(View.GONE);

                break;
            case R.id.ic_back:
//                finish();
                break;
            case R.id.tv_all:

                tv_all.setSelected(true);
                tv_ask.setSelected(false);
                if (allConsultingFragment == null) {
                    allConsultingFragment = new AllConsultingFragment();
                }
                transaction.replace(R.id.tv_fragment, allConsultingFragment);
                transaction.commit();
                break;

            case R.id.tv_ask:

                if (PrefsUtils.getString(getActivity(),PrefsUtils.AUTHORIZATION).equals("")){

                    startActivity(new Intent(getActivity(), LoginActivity.class).putExtra("activity",ConsultActivity.class.toString()));

                    return;
                }

                tv_ask.setSelected(true);
                tv_all.setSelected(false);
                if (iAnsweredFragment == null) {
                    iAnsweredFragment = new IAnsweredFragment();
                }
                transaction.replace(R.id.tv_fragment, iAnsweredFragment);
                transaction.commit();

                break;
        }
    }

    private void showRule() {

        DialogTipRule dialogTipRule = new DialogTipRule();
        dialogTipRule.showDialog(getActivity());

    }

    @Override
    public void onResume() {
//        tv_money.setVisibility(View.VISIBLE);
//        linear_rule.setVisibility(View.VISIBLE);
//        tv_money.startScroll();
        super.onResume();
    }

    @Override
    public void onStop() {
//        tv_money.stopScroll();
//        tv_money.setVisibility(View.GONE);
        super.onStop();
    }

    @Override
    public void onPause() {
//        tv_money.stopScroll();
//        tv_money.setVisibility(View.GONE);
        super.onPause();
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
        void onFragmentInteraction(Uri uri);
    }
}
