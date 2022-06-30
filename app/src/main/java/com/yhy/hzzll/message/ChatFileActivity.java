package com.yhy.hzzll.message;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.netease.nim.uikit.business.session.constant.Extras;
import com.yhy.hzzll.R;
import com.yhy.hzzll.framework.MyData;
import com.yhy.hzzll.home.activity.ViewPagerExampleActivity;
import com.yhy.hzzll.message.entity.FileEntity;
import com.yhy.hzzll.mian.activity.BaseActivity;
import com.yhy.hzzll.utils.DownloadUtils;
import com.yhy.hzzll.utils.HttpDataUtils;
import com.yhy.hzzll.utils.JSONTool;
import com.yhy.hzzll.utils.LogUtils;
import com.yhy.hzzll.utils.PrefsUtils;
import com.yhy.hzzll.utils.ToastUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ChatFileActivity extends BaseActivity{


    @ViewInject(R.id.ic_back)
    private ImageView ic_back;

    @ViewInject(R.id.gv_file)
    private GridView gv_file;

    @ViewInject(R.id.tv_no_data)
    private TextView tv_no_data;

    List<FileEntity.DataBean.ListBean> listBeanList;
    String account;
    private DownloadUtils downloadUtils;
    GvAdapter gvAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_file);
        ViewUtils.inject(this);
        account = getIntent().getStringExtra(Extras.EXTRA_ACCOUNT);

        listBeanList = new ArrayList<>();
        gvAdapter = new GvAdapter(listBeanList);

        gv_file.setEmptyView(tv_no_data);
        gv_file.setAdapter(gvAdapter);

        gv_file.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                ArrayList<String> list = new ArrayList<String>();

                FileEntity.DataBean.ListBean listBean2 = listBeanList.get(position);

                if(listBean2.getMessage_type_id()!=1){

                    String url = "";
                    String name = "";
                    String type = "";
                    try {
                        JSONObject object =new JSONObject(listBean2.getContent());
                        url = JSONTool.getString(object,"url");
                        name = JSONTool.getString(object,"name");
                        type = JSONTool.getString(object,"ext");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    LogUtils.logE(name);

//                    downloadUtils =   new DownloadUtils(ChatFileActivity.this);
//                    downloadUtils.downloadAPK
                    downloadApk(ChatFileActivity.this,url,"聊天文件",name);
                    ToastUtils.getUtils(ChatFileActivity.this).show("文件下载中......");

                }else{
                    for (int i = 0; i <listBeanList.size() ; i++) {
                        FileEntity.DataBean.ListBean listBean = listBeanList.get(i);

                        if (listBean.getMessage_type_id() == 1){
                            String url = "";
                            try {
                                JSONObject object =new JSONObject(listBean.getContent());
                                url = JSONTool.getString(object,"url");
                                list.add(url);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                    }

                    Intent intent = new Intent(ChatFileActivity.this, ViewPagerExampleActivity.class);
                    intent.putStringArrayListExtra(ViewPagerExampleActivity.PHOTO_URL_LIST_INTENT, list);
                    startActivity(intent);
                }



            }
        });



        getFile();
    }

    private void getFile() {
        RequestParams params = new RequestParams();
        params.addHeader("Authorization",PrefsUtils.getString(ChatFileActivity.this,PrefsUtils.AUTHORIZATION));
        params.addQueryStringParameter("from_id", PrefsUtils.getString(ChatFileActivity.this,PrefsUtils.ACCID));
        params.addQueryStringParameter("to_id",account);
        httpDataUtils.sendGet(MyData.CHAT_FILE, params);
        httpDataUtils.setSucessBack(new HttpDataUtils.SucessBack() {
            @Override
            public void sucess(ResponseInfo<String> arg0) {
                try {
                    JSONObject object = new JSONObject(arg0.result);
                    if (object.optString("code").equals("0")) {

                        FileEntity fileEntity = gson.fromJson(arg0.result, FileEntity.class);

                        listBeanList.addAll(fileEntity.getData().getList());
                        gvAdapter.notifyDataSetChanged();

                    } else {

                    }
                } catch (Exception e) {

                }
            }

        });

        httpDataUtils.setFailBack(new HttpDataUtils.FailBack() {
            @Override
            public void fail(String msg) {

            }
        });
    }

    @OnClick({R.id.ic_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ic_back:
                finish();
                break;
        }
    }


    class GvAdapter extends BaseAdapter{

        List<FileEntity.DataBean.ListBean> listBeanList;

        public GvAdapter(List<FileEntity.DataBean.ListBean> listBeanList) {
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

            convertView = LayoutInflater.from(ChatFileActivity.this).inflate(R.layout.chat_file_item, null);

            ImageView imageView = (ImageView) convertView.findViewById(R.id.iv_img);
            TextView tv_name = (TextView) convertView.findViewById(R.id.tv_name);

            FileEntity.DataBean.ListBean listBean = listBeanList.get(position);

            try {
                JSONObject object =new JSONObject(listBean.getContent());

                String url = JSONTool.getString(object,"url");

                if (listBean.getMessage_type_id() == 1){
                    Glide.with(ChatFileActivity.this).load(url).into(imageView);
                }

                if (listBean.getMessage_type_id()==6){
                    tv_name.setText("文件");
                }else{
                    tv_name.setText("图片");
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

            return convertView;
        }
    }

    public static void downloadApk(
            Context context,
            String downLoadUrl,
            String description,
            String infoName) {

        DownloadManager.Request request;
        try {
            request = new DownloadManager.Request(Uri.parse(downLoadUrl));
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        request.setTitle(infoName);
        request.setDescription(description);

        //在通知栏显示下载进度
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            request.allowScanningByMediaScanner();
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        }

        //设置保存下载apk保存路径
        request.setDestinationInExternalPublicDir(Environment.getExternalStorageDirectory().getAbsolutePath() , infoName);

        Context appContext = context.getApplicationContext();
        DownloadManager manager = (DownloadManager)
                appContext.getSystemService(Context.DOWNLOAD_SERVICE);
        //进入下载队列
        manager.enqueue(request);

    }



}
