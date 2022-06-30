package com.yhy.hzzll.my.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.yhy.hzzll.R;
import com.yhy.hzzll.framework.MyData;
import com.yhy.hzzll.home.activity.ViewPagerExampleActivity;
import com.yhy.hzzll.mian.activity.BaseActivity;
import com.yhy.hzzll.mian.activity.RegisterSecondActivity;
import com.yhy.hzzll.my.entity.UserDataInfoEntity;
import com.yhy.hzzll.utils.HttpDataUtils;
import com.yhy.hzzll.utils.PrefsUtils;
import com.yhy.hzzll.view.DialogRiseRank;
import com.yhy.hzzll.view.GridViewForScrollView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 认证信息--律师实名认证
 *
 * @author Yang
 */
public class AuthenticatRealLawActivity extends BaseActivity {

    @ViewInject(R.id.iv_headimg)
    ImageView iv_headimg;

    @ViewInject(R.id.et_username)
    EditText et_username;
    @ViewInject(R.id.tv_gender)
    TextView tv_gender;

    @ViewInject(R.id.et_lawyer_name)
    EditText et_lawyer_name;

    @ViewInject(R.id.et_lawyer_no)
    EditText et_lawyer_no;

    @ViewInject(R.id.linear_authentication_mark)
    LinearLayout linear_authentication_mark;

    @ViewInject(R.id.iv_state_icon)
    ImageView iv_state_icon;

    @ViewInject(R.id.iv_enble_select_img)
    ImageView iv_enble_select_img;

    @ViewInject(R.id.tv_state_title)
    TextView tv_state_title;

    @ViewInject(R.id.tv_state_content)
    TextView tv_state_content;

    @ViewInject(R.id.rl_image)
    RelativeLayout rl_image;

    @ViewInject(R.id.linear_practice)
    LinearLayout linear_practice;

    public static final String AUTHENTICATE_USER_STATE_INTENT = "AUTHENTICATE_USER_STATE_INTENT";

    String  authCodeauthCode ;

    @ViewInject(R.id.gv_certificate)
    GridViewForScrollView gv_certificate;

    ArrayList<String> list;
    GvAdapter gvAdapter;

    @ViewInject(R.id.tv_rise_in_rank)
    TextView tv_rise_in_rank;

    @Override
    protected void onCreate(Bundle arg0) {
        setContentView(R.layout.activity_authenticat_law);
        super.onCreate(arg0);
        ViewUtils.inject(this);
        list = new ArrayList<>();
        authCodeauthCode = getIntent().getStringExtra("authCodeauthCode");
        gvAdapter = new GvAdapter(list);
        gv_certificate.setAdapter(gvAdapter);

        gv_certificate.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(AuthenticatRealLawActivity.this, ViewPagerExampleActivity.class);
                intent.putStringArrayListExtra(ViewPagerExampleActivity.PHOTO_URL_LIST_INTENT, list);
                startActivity(intent);
            }
        });

        tv_rise_in_rank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogRiseRank dialogTips = new DialogRiseRank();
                dialogTips.showDialog(AuthenticatRealLawActivity.this, new DialogRiseRank.Click() {
                    @Override
                    public void buy() {
                        goToRiseRank();
                    }
                });
            }
        });

        checkoutUserInfo();
    }

    private void goToRiseRank(){
        RequestParams params = new RequestParams();
        params.addHeader("Authorization", PrefsUtils.getString(AuthenticatRealLawActivity.this, PrefsUtils.AUTHORIZATION));
        httpDataUtils.sendProgressPut(MyData.LAWYER_PROMOTE, params);
        httpDataUtils.setSucessBack(new HttpDataUtils.SucessBack() {
            @Override
            public void sucess(ResponseInfo<String> arg0) {
                try {
                    JSONObject object = new JSONObject(arg0.result);
                    String code = object.optString("code");
                    String msg = object.optString("message");
                    if (code.equals("0")) {

                        if (msg.equals("申请成功")){
                            startActivity(new Intent(AuthenticatRealLawActivity.this, RegisterSecondActivity.class));
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

    //认证结果标识，返回值：NOT_CERTIFIED-未认证，PENDING_AUDIT-待审核，AUTHENTICATED-已认证


    private void checkoutUserInfo() {
        RequestParams params = new RequestParams();
        params.addHeader("Authorization", PrefsUtils.getString(AuthenticatRealLawActivity.this, PrefsUtils.AUTHORIZATION));
        httpDataUtils.sendProgressGet(MyData.CM_USER_DATA_INFO, params);
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
                            UserDataInfoEntity userDataInfoEntity = gson.fromJson(arg0.result, UserDataInfoEntity.class);
                            Glide.with(AuthenticatRealLawActivity.this).load(userDataInfoEntity.getData().getAvatar()).into(iv_headimg);

                            if (authCodeauthCode.equals("AUTHENTICATED")) {
                                rl_image.setEnabled(false);
                                iv_enble_select_img.setVisibility(View.INVISIBLE);
                                linear_authentication_mark.setVisibility(View.VISIBLE);
                                iv_state_icon.setImageResource(R.drawable.register_success);
                                tv_state_title.setText(R.string.title_authentication_state_txt);


                                if (PrefsUtils.getString(AuthenticatRealLawActivity.this,PrefsUtils.LAWYER_TYPE).equals("实习律师")){
                                    tv_state_content.setVisibility(View.GONE);
                                    linear_practice.setVisibility(View.VISIBLE);
                                }else{
                                    linear_practice.setVisibility(View.GONE);
                                    tv_state_content.setVisibility(View.VISIBLE);
                                    tv_state_content.setText(R.string.title_authentication_warn_txt);
                                }

                            } else {
                                iv_enble_select_img.setVisibility(View.INVISIBLE);
                                linear_authentication_mark.setVisibility(View.VISIBLE);
                                iv_state_icon.setImageResource(R.drawable.icon_interaction_n);
                                tv_state_title.setText(R.string.title_checking_authentication_state_txt);
                                tv_state_content.setText(R.string.title_checking_authentication_warn_txt);
                            }

                            et_username.setText(userDataInfoEntity.getData().getTruename());

                            String gender = userDataInfoEntity.getData().getGender();
                            if (gender.equals("3")) {
                                tv_gender.setText("女");
                            } else if (gender.equals("2")) {
                                tv_gender.setText("男");
                            }else {
                                tv_gender.setText("未知");
                            }

                            et_lawyer_name.setText(userDataInfoEntity.getData().getLaw_firm());
                            et_lawyer_no.setText(userDataInfoEntity.getData().getLawyer_license_no());

                            et_username.setEnabled(false);
                            tv_gender.setEnabled(false);
                            et_lawyer_name.setEnabled(false);
                            et_lawyer_no.setEnabled(false);

                            for (int i = 0; i <userDataInfoEntity.getData().getLawyer_license_photo().size(); i++) {
                                list.add(userDataInfoEntity.getData().getLawyer_license_photo().get(i).getFileurl_full());
                            }

                            gvAdapter.notifyDataSetChanged();

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

    class GvAdapter extends BaseAdapter {

        List<String> listBeanList;

        public GvAdapter(List<String> listBeanList) {
            this.listBeanList = listBeanList;
        }

        @Override
        public int getCount() {
            return listBeanList.size();
        }

        @Override
        public Object getItem(int position) {
            return listBeanList.get(position) ;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            convertView = LayoutInflater.from(AuthenticatRealLawActivity.this).inflate(R.layout.chat_file_item, null);

            ImageView imageView = (ImageView) convertView.findViewById(R.id.iv_img);
            TextView tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            tv_name.setVisibility(View.GONE);
            Glide.with(AuthenticatRealLawActivity.this).load(listBeanList.get(position)).into(imageView);

            return convertView;
        }
    }







}
