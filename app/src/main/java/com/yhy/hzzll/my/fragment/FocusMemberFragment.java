package com.yhy.hzzll.my.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.google.gson.Gson;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.yhy.hzzll.R;
import com.yhy.hzzll.entity.FocusListEntity;
import com.yhy.hzzll.framework.MyData;
import com.yhy.hzzll.home.activity.lawyeroffice.LawyerOfficeActivity;
import com.yhy.hzzll.mian.fragment.BaseFragment;
import com.yhy.hzzll.my.adapter.FansListAdapter;
import com.yhy.hzzll.utils.HttpDataUtils;
import com.yhy.hzzll.utils.PrefsUtils;
import com.yhy.hzzll.utils.ToastUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 我的关注 -- 会员
 *
 * @author Yang
 */
public class FocusMemberFragment extends BaseFragment {

    //	/** 我的关注--会员 适配器 */
//	private FocusVipAdapter vipAdapter;
//
//	/** 会员列表字母标题 */
//	private char[] chaTitle = null;
//	/** 会员列表名称 */
//	private List<String> strData = new ArrayList<String>();
//	/** 会员列表职称 */
//	private List<String> cataData = new ArrayList<String>();
//	/** 会员列表头像 */
//	private List<String> urlData = new ArrayList<String>();
//	private String[] data = null;
//	/** 职称 */
//	private String[] cata = null;
//	/** 头像 */
//	private String[] url = null;
    public HttpDataUtils httpDataUtils;

//    @ViewInject(R.id.view_right)
//    private SeekBarRight view_right;
//
//    @ViewInject(R.id.text_tag)
//    private TextView text_tag;
    @ViewInject(R.id.tv_no_data)
    TextView tv_no_data;
    private int index = 1;

    private View view;

    @ViewInject(R.id.lv_myfocus)
    private ListView lv_focus;

    private static List<FocusListEntity.DataBean.ListBean> focusVipList;
    private FansListAdapter fansAdapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        httpDataUtils = new HttpDataUtils(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_focus_member_list, null);
        ViewUtils.inject(this, view);
        viewInit();
        return view;
    }

    @Override
    public void viewInit() {
        focusVipList = new ArrayList<>();
        fansAdapter = new FansListAdapter(getActivity(), focusVipList);
        lv_focus.setAdapter(fansAdapter);
        lv_focus.setEmptyView(tv_no_data);
        lv_focus.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if ("1".equals(focusVipList.get(position).getUser_type())) {
//					Intent intent = new Intent(getActivity(), LawyerOfficeActivity.class);
//					intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//					intent.putExtra(LawyerOfficeActivity.USER_ID_INTENT, focusVipList.get(position).getUser_id()+"");
//					startActivity(intent);

                    startActivity(new Intent().putExtra("user_id", focusVipList.get(position).getFollowed_id()+"").putExtra("title", focusVipList.get(position).getNickname()+"").putExtra("from", "laynation").setClass(getActivity(), LawyerOfficeActivity.class));


                }
            }
        });

        getLawyer();
    }

    /**
     * 关注会员数据
     */
    private void getLawyer() {

        RequestParams params = new RequestParams();
        params.addHeader("Authorization", PrefsUtils.getString(getActivity(), PrefsUtils.AUTHORIZATION));
        params.addQueryStringParameter("type", "1");
        httpDataUtils.sendProgressGet(MyData.MY_FOLLOW, params);
        httpDataUtils.setSucessBack(new HttpDataUtils.SucessBack() {
            @Override
            public void sucess(ResponseInfo<String> arg0) {
                if (httpDataUtils.code(arg0.result)) {
                    try {
                        JSONObject object = new JSONObject(arg0.result);
                        Gson gson = new Gson();
                        FocusListEntity entity = gson.fromJson(object.toString(), FocusListEntity.class);

                        focusVipList.addAll(entity.getData().getList());

                        fansAdapter.notifyDataSetChanged();
                    } catch (JSONException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        });

        httpDataUtils.setFailBack(new HttpDataUtils.FailBack() {

            @Override
            public void fail(String msg) {
                ToastUtils.getUtils(getActivity()).show(msg);
            }
        });
    }

}
