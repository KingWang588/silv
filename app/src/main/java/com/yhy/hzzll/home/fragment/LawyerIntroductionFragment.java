package com.yhy.hzzll.home.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.google.gson.Gson;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.yhy.hzzll.R;
import com.yhy.hzzll.framework.MyData;
import com.yhy.hzzll.home.entity.LawyerInfoentity;
import com.yhy.hzzll.mian.fragment.BaseFragment;
import com.yhy.hzzll.utils.HttpDataUtils;
import com.yhy.hzzll.utils.ToastUtils;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 
 * @author 律师简介
 * 
 */
public class LawyerIntroductionFragment extends BaseFragment {

	@ViewInject(R.id.tv_phone_number)
	TextView tv_phone_number;
	@ViewInject(R.id.tv_email)
	TextView tv_email;
	@ViewInject(R.id.tv_law_firm)
	TextView tv_law_firm;
	@ViewInject(R.id.tv_lawyer_expertise)
	TextView tv_lawyer_expertise;
	@ViewInject(R.id.tv_address_detail)
	TextView tv_address_detail;
	@ViewInject(R.id.tv_lawyer_introduction)
	TextView tv_lawyer_introduction;

	private Activity mActivity;
	HttpDataUtils httpDataUtils;

	public static final String USER_ID_INTENT = "USER_ID_INTENT";
	private String uid;

	public static LawyerIntroductionFragment newInstance(String uid) {
		LawyerIntroductionFragment fragment = new LawyerIntroductionFragment();
		Bundle args = new Bundle();
		args.putString(USER_ID_INTENT, uid);
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (getArguments() != null) {
			uid = getArguments().getString(USER_ID_INTENT);
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_lawyer_introduction, null);
		ViewUtils.inject(this, view);
		httpDataUtils = new HttpDataUtils(mActivity);
		getUserInfoById();
		return view;
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		mActivity = activity;
	}

	/** 获取用户信息 */
	private void getUserInfoById() {
		RequestParams params = new RequestParams();
		params.addQueryStringParameter("lawyerid", uid);
		httpDataUtils.sendGet(MyData.GET_LAWYERINFO_BY_ID, params);
		httpDataUtils.setSucessBack(new HttpDataUtils.SucessBack() {

			@Override
			public void sucess(ResponseInfo<String> arg0) {
				try {
					JSONObject object = new JSONObject(arg0.result);
					String code = object.optString("code");
					String msg = object.optString("message");
					if (code.equals("0")) {
						try {
							Gson gson = new Gson();
							LawyerInfoentity entity = gson.fromJson(arg0.result, LawyerInfoentity.class);
							tv_email.setText("***********");
							tv_law_firm.setText(entity.getData().getLaw_firm());
							tv_address_detail.setText(entity.getData().getAddress());
							tv_phone_number.setText("***********");
//                            tv_lawyer_introduction.setText(entity.getData().getLawyer_intro());
							tv_lawyer_introduction.setText(Html.fromHtml(entity.getData().getLawyer_intro()));
                            String secpical= "";

                            for (int i = 0; i < entity.getData().getSpecial().size(); i++) {
                                secpical = secpical + entity.getData().getSpecial().get(i)+"  ";
                            }

							tv_lawyer_expertise.setText(secpical);

						} catch (Exception e) {
							// TODO: handle exception
						}
					} else {
						ToastUtils.getUtils(mActivity).show(msg);
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		});
	}
}
