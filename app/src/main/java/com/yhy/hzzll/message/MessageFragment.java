package com.yhy.hzzll.message;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.netease.nim.uikit.common.ui.drop.DropFake;
import com.netease.nim.uikit.common.ui.imageview.HeadImageView;
import com.netease.nim.uikit.common.util.log.LogUtil;
import com.netease.nim.uikit.common.util.sys.TimeUtil;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.msg.model.RecentContact;
import com.netease.nimlib.sdk.uinfo.UserService;
import com.netease.nimlib.sdk.uinfo.model.NimUserInfo;
import com.yhy.hzzll.R;
import com.yhy.hzzll.entity.LawyerCoopEntity;
import com.yhy.hzzll.entity.MessageOfSystemEntity;
import com.yhy.hzzll.framework.Constans;
import com.yhy.hzzll.framework.MyData;
import com.yhy.hzzll.home.activity.collaborate.LawyerNationalCooperationActivity;
import com.yhy.hzzll.home.activity.consult.PursueAskActivity;
import com.yhy.hzzll.message.entity.PursueEntity;
import com.yhy.hzzll.mian.fragment.BaseFragment;
import com.yhy.hzzll.utils.HttpDataUtils;
import com.yhy.hzzll.utils.PrefsUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MessageFragment extends BaseFragment {

    private View view;

    @ViewInject(R.id.tv_quick)
    private TextView tv_quick;

    @ViewInject(R.id.tv_not_quick)
    private TextView tv_not_quick;

    @ViewInject(R.id.tv_contect)
    private TextView tv_contect;


    @ViewInject(R.id.lv_quick)
    private ListView lv_quick;

    @ViewInject(R.id.lv_rectnt_connect)
    private ListView lv_rectnt_connect;


    @ViewInject(R.id.linear_fragment)
    private LinearLayout linear_fragment;

    @ViewInject(R.id.linear_cooperation)
    private LinearLayout linear_cooperation;

    @ViewInject(R.id.linear_system)
    private LinearLayout linear_system;

    @ViewInject(R.id.tv_second_msg_content)
    private TextView tv_second_msg_content;


    @ViewInject(R.id.tv_msg_content)
    private TextView tv_msg_content;

    CustomPursueContactAdapter customPursueContactAdapter;
    CustomContactAdapter customContactAdapter;

    List<PursueEntity.DataBean.ListBean> pursueContacts;

    ArrayList<RecentContact> recentContacts;


    public MessageFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_message, null);
        ViewUtils.inject(this, view);
        viewInit();

        return view;
    }




    @OnClick({R.id.tv_quick, R.id.tv_not_quick, R.id.linear_cooperation,R.id.linear_system,R.id.tv_contect})
    public void Onclick(View view) {
        switch (view.getId()) {

            case R.id.linear_system:
				startActivity(new Intent(getActivity(), MessageDetailHuaZhaiActivity.class));
                break;

            case R.id.linear_cooperation:

                startActivity(new Intent().putExtra("tab", 0).setClass(getActivity(),
                        LawyerNationalCooperationActivity.class));

                break;

            case R.id.tv_quick:

                if (quickchatListIsOpen) {
                    quickchatListIsOpen = false;
                    Drawable drawable = getActivity().getResources().getDrawable(R.drawable.jiantou2);
                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                    tv_quick.setCompoundDrawables(drawable, null, null, null);
                    lv_quick.setVisibility(View.GONE);

                } else {
                    quickchatListIsOpen = true;
                    Drawable drawable = getActivity().getResources().getDrawable(R.drawable.jiantou1);
                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                    tv_quick.setCompoundDrawables(drawable, null, null, null);

                    lv_quick.setVisibility(View.VISIBLE);

                    chatListIsOpen = false;
                    Drawable drawable1 = getActivity().getResources().getDrawable(R.drawable.jiantou2);
                    drawable1.setBounds(0, 0, drawable1.getMinimumWidth(), drawable1.getMinimumHeight());
                    tv_not_quick.setCompoundDrawables(drawable1, null, null, null);
//                    recent_contacts_fragment.setUserVisibleHint();
                    linear_fragment.setVisibility(View.GONE);

                    recentListIsOpen = false;
                    Drawable drawable3 = getActivity().getResources().getDrawable(R.drawable.jiantou2);
                    drawable3.setBounds(0, 0, drawable3.getMinimumWidth(), drawable3.getMinimumHeight());
                    tv_contect.setCompoundDrawables(drawable3, null, null, null);
                    lv_rectnt_connect.setVisibility(View.GONE);


                }

                break;

            case R.id.tv_not_quick:

                if (chatListIsOpen) {
                    chatListIsOpen = false;
                    Drawable drawable = getActivity().getResources().getDrawable(R.drawable.jiantou2);
                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                    tv_not_quick.setCompoundDrawables(drawable, null, null, null);
//                    recent_contacts_fragment.setUserVisibleHint();
                    linear_fragment.setVisibility(View.GONE);
                } else {
                    chatListIsOpen = true;
                    Drawable drawable = getActivity().getResources().getDrawable(R.drawable.jiantou1);
                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                    tv_not_quick.setCompoundDrawables(drawable, null, null, null);
                    linear_fragment.setVisibility(View.VISIBLE);

                    quickchatListIsOpen = true;
                    Drawable drawable1 = getActivity().getResources().getDrawable(R.drawable.jiantou2);
                    drawable1.setBounds(0, 0, drawable1.getMinimumWidth(), drawable1.getMinimumHeight());
                    tv_quick.setCompoundDrawables(drawable1, null, null, null);
                    lv_quick.setVisibility(View.GONE);

                    recentListIsOpen = false;
                    Drawable drawable3 = getActivity().getResources().getDrawable(R.drawable.jiantou2);
                    drawable3.setBounds(0, 0, drawable3.getMinimumWidth(), drawable3.getMinimumHeight());
                    tv_contect.setCompoundDrawables(drawable3, null, null, null);
                    lv_rectnt_connect.setVisibility(View.GONE);
                }

                break;

            case R.id.tv_contect:

                if (recentListIsOpen) {
                    recentListIsOpen = false;
                    Drawable drawable = getActivity().getResources().getDrawable(R.drawable.jiantou2);
                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                    tv_contect.setCompoundDrawables(drawable, null, null, null);
                    lv_rectnt_connect.setVisibility(View.GONE);
                } else {
                    recentListIsOpen = true;
                    Drawable drawable = getActivity().getResources().getDrawable(R.drawable.jiantou1);
                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                    tv_contect.setCompoundDrawables(drawable, null, null, null);
                    lv_rectnt_connect.setVisibility(View.VISIBLE);


                    quickchatListIsOpen = true;
                    Drawable drawable2 = getActivity().getResources().getDrawable(R.drawable.jiantou2);
                    drawable2.setBounds(0, 0, drawable2.getMinimumWidth(), drawable2.getMinimumHeight());
                    tv_quick.setCompoundDrawables(drawable2, null, null, null);

                    lv_quick.setVisibility(View.GONE);

                    chatListIsOpen = false;
                    Drawable drawable1 = getActivity().getResources().getDrawable(R.drawable.jiantou2);
                    drawable1.setBounds(0, 0, drawable1.getMinimumWidth(), drawable1.getMinimumHeight());
                    tv_not_quick.setCompoundDrawables(drawable1, null, null, null);
//                    recent_contacts_fragment.setUserVisibleHint();
                    linear_fragment.setVisibility(View.GONE);

                }
                break;

        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }


    private boolean quickchatListIsOpen = true;
    private boolean chatListIsOpen = true;
    private boolean recentListIsOpen = true;

    @Override
    public void viewInit() {
        super.viewInit();
        recentContacts = new ArrayList<>();
        customContactAdapter = new CustomContactAdapter(recentContacts);
        lv_rectnt_connect.setAdapter(customContactAdapter);

//        lv_rectnt_connect.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent intent = new Intent();
//                intent.putExtra(Extras.EXTRA_ACCOUNT, recentContacts.get(position).getContactId());
//                intent.putExtra(Extras.EXTRA_CUSTOMIZATION, SessionHelper.getP2pCustomization());
//                intent.setClass(getActivity(), MyNewMessageActivity.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
//                startActivity(intent);
//            }
//        });

        pursueContacts = new ArrayList<>();
        customPursueContactAdapter = new CustomPursueContactAdapter(pursueContacts);
        lv_quick.setAdapter(customPursueContactAdapter);

        lv_quick.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                LogUtil.e("789465456", pursueContacts.get(position).getReply_id() + "Reply_id()" + pursueContacts.get(position).getConsult_id() + "Consult_id()");
                startActivity(new Intent(getActivity(), PursueAskActivity.class)
                        .putExtra("reply_id", pursueContacts.get(position).getReply_id() + "").putExtra("id", pursueContacts.get(position).getConsult_id() + "")
                        .putExtra("message_id",pursueContacts.get(position).getMessage_id()+""));
            }
        });

        getPursueList();


//        NIMClient.getService(MsgService.class).queryRecentContacts()
//                .setCallback(new RequestCallbackWrapper<List<RecentContact>>() {
//                    @Override
//                    public void onResult(int code, List<RecentContact> recents, Throwable e) {
//
//                        if (recents!=null){
//                        for (int i = 0; i <recents.size() ; i++) {
//                            final RecentContact loadedRecent = recents.get(i);
//                            List<String> list = new ArrayList<>();
//                            list.add(loadedRecent.getContactId());
//                            NIMClient.getService(UserService.class).fetchUserInfo(list)
//                                    .setCallback(new RequestCallback<List<NimUserInfo>>() {
//                                        @Override
//                                        public void onSuccess(List<NimUserInfo> nimUserInfos) {
//                                            if (nimUserInfos.get(0).getExtensionMap()!=null){
//                                                if (nimUserInfos.get(0).getExtensionMap().toString().equals("{users_type=lawyer}")) {
//                                                    recentContacts.add(loadedRecent);
//                                                }
//                                            }
//                                            customContactAdapter.notifyDataSetChanged();
//                                        }
//
//                                        @Override
//                                        public void onFailed(int i) {
//
//                                        }
//
//                                        @Override
//                                        public void onException(Throwable throwable) {
//
//                                        }
//                                    });
//
//                        }
//                    }
//                    }
//
//                });

    }

    private void getPursueList() {
         RequestParams params = new RequestParams();
        params.addHeader("Authorization", PrefsUtils.getString(getActivity(), PrefsUtils.AUTHORIZATION));
        HttpDataUtils httpDataUtils = new HttpDataUtils(getActivity());
        String url = MyData.MESSAGE_QUICK;
        httpDataUtils.sendGet(url, params);
        httpDataUtils.setSucessBack(new HttpDataUtils.SucessBack() {
            @Override
            public void sucess(ResponseInfo<String> arg0) {
                try {
                    JSONObject object = new JSONObject(arg0.result);
                    String code = object.optString("code");
                    String msg = object.optString("msg");
                    if (code.equals("0")) {
                        Gson gson = new Gson();
                        try {
                            pursueContacts.clear();

                            PursueEntity pursueEntity = gson.fromJson(object.toString(), PursueEntity.class);
                            pursueContacts.addAll(pursueEntity.getData().getList());
                            customPursueContactAdapter.notifyDataSetChanged();

                            coopListData();

                        } catch (Exception e) {

                        }
                    } else {

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

    private class CustomPursueContactAdapter extends BaseAdapter {

        List<PursueEntity.DataBean.ListBean> pursueContacts;

        public CustomPursueContactAdapter(List<PursueEntity.DataBean.ListBean> pursueContacts) {
            this.pursueContacts = pursueContacts;
        }

        @Override
        public int getCount() {
            return pursueContacts.size();
        }

        @Override
        public Object getItem(int position) {
            return pursueContacts.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            convertView = LayoutInflater.from(getActivity()).inflate(R.layout.nim_recent_contact_list_item, null);

            HeadImageView img_head = (HeadImageView) convertView.findViewById(R.id.img_head);
            TextView tv_nickname = (TextView) convertView.findViewById(R.id.tv_nickname);
            TextView tv_online_state = (TextView) convertView.findViewById(R.id.tv_online_state);
            ImageView img_msg_status = (ImageView) convertView.findViewById(R.id.img_msg_status);
            TextView tv_message = (TextView) convertView.findViewById(R.id.tv_message);
            TextView tv_date_time = (TextView) convertView.findViewById(R.id.tv_date_time);
            DropFake unread_number_tip = (DropFake) convertView.findViewById(R.id.unread_number_tip);
            PursueEntity.DataBean.ListBean listBean = pursueContacts.get(position);

            if (listBean.getAvatar() != null && listBean.getAvatar().length() != 0) {
                Glide.with(getActivity()).load(listBean.getAvatar()).into(img_head);
            }

            tv_nickname.setText(listBean.getNickname());
            tv_message.setText(listBean.getContent());
            tv_date_time.setText(listBean.getSend_time());

            if (listBean.getCount()!=0){
                unread_number_tip.setVisibility(View.VISIBLE);
                unread_number_tip.setText(listBean.getCount()+"");
            }else {
                unread_number_tip.setVisibility(View.GONE);
            }
            return convertView;
        }
    }

    class CustomContactAdapter extends BaseAdapter{

        List<RecentContact> recents;

        public void setList(ArrayList<RecentContact> list) {
            if (list != null) {
                recents = (ArrayList<RecentContact>) list.clone();
                notifyDataSetChanged();
            }
        }

        public CustomContactAdapter(List<RecentContact> recents) {
            this.recents = recents;
        }

        @Override
        public int getCount() {
            return recents.size();
        }

        @Override
        public Object getItem(int position) {
            return recents.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            convertView = LayoutInflater.from(getActivity()).inflate(R.layout.nim_recent_contact_list_item, null);
            HeadImageView img_head = (HeadImageView) convertView.findViewById(R.id.img_head);
            TextView tv_nickname = (TextView) convertView.findViewById(R.id.tv_nickname);
            TextView tv_online_state = (TextView) convertView.findViewById(R.id.tv_online_state);
            ImageView img_msg_status = (ImageView) convertView.findViewById(R.id.img_msg_status);
            TextView tv_message = (TextView) convertView.findViewById(R.id.tv_message);
            TextView tv_date_time = (TextView) convertView.findViewById(R.id.tv_date_time);
            RecentContact recent = recents.get(position);
            NimUserInfo user = NIMClient.getService(UserService.class).getUserInfo(  recent.getContactId());

            if (user.getAvatar() != null && user.getAvatar().length() != 0) {
                Glide.with(getActivity()).load(user.getAvatar()).into(img_head);
            }

            tv_nickname.setText(user.getName());
            tv_message.setText(recent.getContent());

            String timeString = TimeUtil.getTimeShowString(recent.getTime(), true);
            tv_date_time.setText(timeString);

            return convertView;
        }
    }


    @Override
    public void onPause() {
        getPursueList();
        super.onPause();
    }

    @Override
    public void onResume() {

        super.onResume();
    }

    @Override
    public void onStop() {

        super.onStop();
    }

    private void coopListData() {
        RequestParams params = new RequestParams();
        params.addQueryStringParameter("page", String.valueOf(1));
        final HttpDataUtils httpDataUtils = new HttpDataUtils(getActivity());
        httpDataUtils.sendGet(MyData.LAWERCOOPLIST, params);
        httpDataUtils.setSucessBack(new HttpDataUtils.SucessBack() {
            @Override
            public void sucess(ResponseInfo<String> arg0) {
                if (httpDataUtils.code(arg0.result)) {
                    LawyerCoopEntity coopEntity = gson.fromJson(httpDataUtils.data(arg0.result),
                            LawyerCoopEntity.class);
                    long newtime = 0;
//                    if (coopEntity.getList() != null) {
////                        if (coopEntity.getList().get(0).getCtime_num() != null) {
////                            newtime = Long.parseLong(coopEntity.getList().get(0).getCtime_num());
////                        }
////                    }
                    String time = PrefsUtils.getString(getActivity(), PrefsUtils.CLICKLAWCOOP);
                    long millionSeconds = 0;
                    if (time.equals("")) {
                        millionSeconds = 0;
                    } else {
                        millionSeconds = Long.parseLong(time);
                    }

                    if (newtime > millionSeconds / 1000) {
                        tv_second_msg_content.setText("有新的律师协作消息,请注意查看");
                    } else {
                        tv_second_msg_content.setText("");
                    }
                }

                refreshMsg();

            }
        });
    }

    private void refreshMsg() {
        RequestParams params = new RequestParams();
        final HttpDataUtils httpDataUtils = new HttpDataUtils(getActivity());
        String url = MyData.MESSAGE_SYSTEM+1+"-30";
        params.addHeader("Authorization", PrefsUtils.getString(getActivity(), PrefsUtils.AUTHORIZATION));
        httpDataUtils.sendGet(url, params);
        httpDataUtils.setSucessBack(new HttpDataUtils.SucessBack() {

            @Override
            public void sucess(ResponseInfo<String> arg0) {

                try {
                    JSONObject object = new JSONObject(arg0.result);
                    String code = object.optString("code");
                    String msg = object.optString("message");
                    if (code.equals(Constans.SUCCESS_CODE)) {

                        MessageOfSystemEntity messageOfSystemEntity = gson.fromJson(arg0.result,MessageOfSystemEntity.class);

                        if (messageOfSystemEntity.getData().getList().size()!=0){
                            tv_msg_content.setText( messageOfSystemEntity.getData().getList().get(0).getContent());
                        }else{
                            tv_msg_content.setText("暂无系统消息");
                        }

                    } else {
//                        ToastUtils.getUtils(MessageDetailHuaZhaiActivity.this).show(msg);
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






}
