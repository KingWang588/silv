package com.yhy.hzzll.message;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.yhy.hzzll.R;
import com.yhy.hzzll.entity.FocusVipEntity;
import com.yhy.hzzll.framework.Constans;
import com.yhy.hzzll.framework.MyData;
import com.yhy.hzzll.mian.activity.BaseActivity;
import com.yhy.hzzll.utils.HttpDataUtils;
import com.yhy.hzzll.utils.JSONTool;
import com.yhy.hzzll.utils.PrefsUtils;
import com.yhy.hzzll.utils.ToastUtils;
import com.yhy.hzzll.view.DialogLoading;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class SeacherLinkmanActivity extends BaseActivity {

    private static final int CALL_PHONE_REQUEST_CODE = 1212;
    @ViewInject(R.id.et_seacher)
    private EditText et_seacher;

    @ViewInject(R.id.lv_seacher)
    ListView lv_seacher;

    private DialogLoading loading;
    List<FocusVipEntity> list = new ArrayList<>();
    List<FocusVipEntity> getList = new ArrayList<>(); //搜索后的

    /**
     * 获取库Phon表字段
     **/
    private static final String[] PHONES_PROJECTION = new String[]{
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER, ContactsContract.Contacts.Photo.PHOTO_ID, ContactsContract.CommonDataKinds.Phone.CONTACT_ID};
    /**
     * 联系人显示名称
     **/
    private static final int PHONES_DISPLAY_NAME_INDEX = 0;

    /**
     * 电话号码
     **/
    private static final int PHONES_NUMBER_INDEX = 1;

    /**
     * 头像ID
     **/
    private static final int PHONES_PHOTO_ID_INDEX = 2;

    /**
     * 联系人的ID
     **/
    private static final int PHONES_CONTACT_ID_INDEX = 3;

    /**
     * 联系人名称
     **/
    private ArrayList<String> mContactsName = new ArrayList<String>();
    /**
     * 联系人头像
     **/
    private ArrayList<String> mContactsNumber = new ArrayList<String>();
    /**
     * 联系人头像
     **/
    private ArrayList<Bitmap> mContactsPhonto = new ArrayList<Bitmap>();

    /**
     * 电话列表
     */
    private String[] phones;
    /**
     * 联系人列表
     */
    private String[] names;


    SeacherAdapter seacherAdapter ;



    @OnClick({R.id.ic_back,R.id.iv_search})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ic_back:
                finish();
                break;
            case R.id.iv_search:
                search();
                break;


        }
    }

    private void search() {
        getList.clear();
        String searchText = et_seacher.getText().toString();

        if(searchText.length()!= 0){

            for (int i = 0; i <list.size() ; i++) {

                if(list.get(i).getName().contains(searchText)){
                    getList.add(list.get(i));
                }
            }


//            String name = "";
//
//            for (int i = 0; i <getList.size() ; i++) {
//                name =name+ getList.get(i).getName()+",";
//            }
//            ToastUtils.getUtils(SeacherLinkmanActivity.this).show(name);

            seacherAdapter.notifyDataSetInvalidated();

        }




    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seacher_linkman);
        ViewUtils.inject(this);

        getPhoneContacts();

    }



    /**
     * 得到手机通讯录联系人信息
     **/
    private void getPhoneContacts() {
        ContentResolver resolver = context.getContentResolver();
        // 获取手机联系人
        Cursor phoneCursor = resolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                PHONES_PROJECTION, null, null, null);
        if (phoneCursor != null) {
            while (phoneCursor.moveToNext()) {
                // 得到手机号码
                String phoneNumber = phoneCursor.getString(PHONES_NUMBER_INDEX);
                // 当手机号码为空的或者为空字段 跳过当前循环
                if (TextUtils.isEmpty(phoneNumber))
                    continue;
                // 得到联系人名称
                String contactName = phoneCursor
                        .getString(PHONES_DISPLAY_NAME_INDEX);
                if (TextUtils.isEmpty(contactName))
                    contactName = "未知";

                // 得到联系人ID
                Long contactid = phoneCursor.getLong(PHONES_CONTACT_ID_INDEX);
                // 得到联系人头像ID
                Long photoid = phoneCursor.getLong(PHONES_PHOTO_ID_INDEX);
                // 得到联系人头像Bitamp
                Bitmap contactPhoto = null;
                // photoid 大于0 表示联系人有头像 如果没有给此人设置头像则给他一个默认的
                if (photoid > 0) {
                    Uri uri = ContentUris.withAppendedId(
                            ContactsContract.Contacts.CONTENT_URI, contactid);
                    InputStream input = ContactsContract.Contacts
                            .openContactPhotoInputStream(resolver, uri);
                    contactPhoto = BitmapFactory.decodeStream(input);
                } else {
                    contactPhoto = BitmapFactory.decodeResource(getResources(),
                            R.drawable.icon_contact);
                }
                mContactsName.add(contactName);
                mContactsNumber.add(phoneNumber);
                mContactsPhonto.add(contactPhoto);

//                Log.e("lianxiren ", contactName + phoneNumber);
            }
            phoneCursor.close();

            dataInit();
        }
    }


    private void dataInit() {

        Bitmap[] photo = mContactsPhonto.toArray(new Bitmap[mContactsPhonto
                .size()]);
        for (int i = 0; i < mContactsName.size(); i++) {
            FocusVipEntity entity = new FocusVipEntity();
            entity.setName(mContactsName.get(i));
            entity.setTag(mContactsNumber.get(i));
            entity.setHeadImg(photo[i]);
            list.add(entity);
        }


        seacherAdapter = new SeacherAdapter();
        lv_seacher.setAdapter(seacherAdapter);


        lv_seacher.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {

                String name = getList.get(arg2).getName();
                String phone = new String(getList.get(arg2).getTag())
                        .replaceAll("[a-zA-Z -]", "");
                callingRequest(name,phone);
//				userOpen(phone);
            }
        });

        // et_serach.addTextChangedListener(new TextWatcher() {
        // @Override
        // public void onTextChanged(CharSequence s, int start, int before,
        // int count) {
        // }
        //
        // @Override
        // public void beforeTextChanged(CharSequence s, int start, int count,
        // int after) {
        // }
        //
        // @Override
        // public void afterTextChanged(Editable s) {
        // for (FocusVipEntity entity : focusVipList) {
        // dataList.add(entity);
        // if (entity.getTag().replaceAll("[a-zA-Z -]", "")
        // .contains(s.toString())) {
        // dataList.add(entity);
        // setData(dataList);
        // } else {
        // dataList.remove(entity);
        // }
        // }
        // }
        // });
    }

    private void callingRequest(final String name, final String oppo) {
        if (oppo.isEmpty()) {
            return;
        }

        RequestParams params = new RequestParams();
//        HttpUtils dataUtils = new HttpUtils();
        params.addBodyParameter("uid",
                PrefsUtils.getString(context, PrefsUtils.UID));
        params.addBodyParameter("phone",
                PrefsUtils.getString(context, PrefsUtils.UPHONE));
        params.addBodyParameter("oppo", oppo);
        params.addBodyParameter("time", "1");


        httpDataUtils.sendPost(MyData.CALLING,params);
        httpDataUtils.setSucessBack(new HttpDataUtils.SucessBack() {
            @Override
            public void sucess(ResponseInfo<String> arg0) {
                try {
                    JSONObject object = new JSONObject(arg0.result);
                    String code = JSONTool.getString(object, "code");
                    String msg = JSONTool.getString(object, "msg");
                    if (code.equals(Constans.SUCCESS_CODE)) { // 成功

                        showDiolog(name,oppo);

                    } else {
                        ToastUtils.getUtils(getApplicationContext()).show(msg);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });


//        dataUtils.send(HttpRequest.HttpMethod.POST, MyData.CALLING, params,
//                new RequestCallBack<String>() {
//                    @Override
//                    public void onFailure(HttpException arg0, String arg1) {
//                        loading.dismissDialog();
//                        ToastUtils.getUtils(context).show(arg1);
//                    }
//
//                    @Override
//                    public void onSuccess(ResponseInfo<String> arg0) {
////                        Log.e("arg0222222222", arg0.result);
//                        loading.dismissDialog();
//                        if (httpDataUtils.code(arg0.result)) {
//                            // 0571-95105856
//                            // 0571-56383200
////							String to = "0571-95105856" ;
//////							+ "%23" + oppo;
////							Intent intent = new Intent(Intent.ACTION_DIAL, Uri
////									.parse("tel:" + to));
////							intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
////							startActivity(intent);
////							ToastUtils.getUtils(EvidenceTelephoneActivity.this).show("正在为您调取第三方服务");
//
//
//                            showDiolog(name,oppo);
//
//
//                        } else {
//                            httpDataUtils.showMsg((String) arg0.result);
//                        }
//                    }
//                });
    }


//
    private void showDiolog(String name,String phone) {
        AlertDialog.Builder builder = new AlertDialog.Builder(SeacherLinkmanActivity.this);
        View view2 = getLayoutInflater().inflate(R.layout.view_dialog_delece_consult_reply, null);
        view2.setBackgroundResource(R.drawable.background_view_dialog);
        builder.setView(view2);
        final Dialog dialog = builder.create();

        TextView tv_title = (TextView) view2.findViewById(R.id.tv_1);
        TextView tv_content = (TextView) view2.findViewById(R.id.tv_2);

        tv_title.setText("通话存证");
        tv_content.setText("你要对"+name+"（"+phone+"）拨号吗？私律会对通话内容进行录音并存证。 ");

        TextView tv_yes = (TextView) view2.findViewById(R.id.tv_yes);
        TextView tv_no = (TextView) view2.findViewById(R.id.tv_no);
        tv_yes.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                if (ContextCompat.checkSelfPermission(SeacherLinkmanActivity.this, Manifest.permission.CALL_PHONE)
                        != PackageManager.PERMISSION_GRANTED) {
                    //申请获取联系人权限
                    ActivityCompat.requestPermissions(SeacherLinkmanActivity.this,new String[]{Manifest.permission.CALL_PHONE},
                            CALL_PHONE_REQUEST_CODE);
                    return;
                }
                else{
                    String to = "0571-95105856";
////							+ "%23" + oppo;
                    Intent intent = new Intent(Intent.ACTION_CALL);
                    Uri data = Uri.parse("tel:" + to);
                    intent.setData(data);
                    startActivity(intent);
                    dialog.cancel();
                }

            }
        });

        tv_no.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        dialog.show();
    }


    class SeacherAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return getList.size();
        }

        @Override
        public Object getItem(int position) {
            return getList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {


            convertView = getLayoutInflater().inflate(R.layout.item_search_linkman, null);

            TextView tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            TextView tv_phone = (TextView) convertView.findViewById(R.id.tv_phone);

            String phone = new String(getList.get(position).getTag()).replaceAll("[a-zA-Z -]", "");
            tv_phone.setText(phone);
            tv_name.setText(getList.get(position).getName());

            return convertView;
        }
    }


}
