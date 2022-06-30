package com.yhy.hzzll.home.activity.newcollaborate;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.yhy.hzzll.R;
import com.yhy.hzzll.file.browser.FileBrowserActivity;
import com.yhy.hzzll.framework.MyData;
import com.yhy.hzzll.home.entity.ConsultDetial;
import com.yhy.hzzll.mian.activity.BaseActivity;
import com.yhy.hzzll.utils.HttpDataUtils;
import com.yhy.hzzll.utils.KeyboardUtils;
import com.yhy.hzzll.utils.PrefsUtils;
import com.yhy.hzzll.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

public class CloudDiskActivity extends BaseActivity {

    @ViewInject(R.id.lv_file)
    PullToRefreshListView lv_file;

    @ViewInject(R.id.tv_no_data)
    TextView tv_no_data;

    @ViewInject(R.id.tv_pan_address)
    TextView tv_pan_address;

    @ViewInject(R.id.et_search)
    EditText et_search;

    CloudDiskAdapter cloudDiskAdapter;

    List<CloudDiskEntity.DataBean.ListBean> list = new ArrayList<>();
    List<CloudDiskEntity.DataBean.ListBean> selectList = new ArrayList<>();


    int num = 1;

    View rootView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        rootView = LayoutInflater.from(this).inflate(R.layout.activity_cloud_disk, null);
        setContentView(rootView);

        ViewUtils.inject(this);

//        lv_file.setEmptyView(tv_no_data);
        cloudDiskAdapter = new CloudDiskAdapter();
        lv_file.setAdapter(cloudDiskAdapter);
        tv_pan_address.setText(MyData.PAN_ADDRESS);

        num = getIntent().getIntExtra("num", 1);

        View headerview1 = View.inflate(CloudDiskActivity.this, R.layout.serrch_layout, null);
        ViewUtils.inject(this, headerview1);
        lv_file.getRefreshableView().addHeaderView(headerview1);
        lv_file.setMode(PullToRefreshBase.Mode.BOTH);
        lv_file.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                selectList.clear();
                resetPageIndex();
                isPullRefresh = true;
                getList();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // 加载完成后停止刷新
                        lv_file.onRefreshComplete();
                    }
                }, 1000);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                addPageIndex();
                isPullRefresh = false;
                getList();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // 加载完成后停止刷新
                        lv_file.onRefreshComplete();
                    }
                }, 1000);
            }
        });

        getList();

    }

    private boolean isPullRefresh = true;
    private int index = 1;

    private void resetPageIndex() {
        index = 1;
    }

    private void addPageIndex() {
        ++index;
    }


    private void getList() {

        RequestParams params = new RequestParams();
        params.addHeader("Authorization", PrefsUtils.getString(context, PrefsUtils.AUTHORIZATION));
        params.addQueryStringParameter("page", index + "");

        String keyword = et_search.getText().toString();
        if (keyword.length() != 0) {
            params.addQueryStringParameter("filename", et_search.getText().toString());
        }

        httpDataUtils.sendGet(MyData.GET_CLOUD_DISK_LIST, params);
        httpDataUtils.setSucessBack(new HttpDataUtils.SucessBack() {
            @Override
            public void sucess(ResponseInfo<String> arg0) {
                if (httpDataUtils.code(arg0.result)) {
                    CloudDiskEntity entity = gson.fromJson(arg0.result, CloudDiskEntity.class);
                    if (isPullRefresh) {
                        list.clear();
                    }

                    List<CloudDiskEntity.DataBean.ListBean> listBeanList = entity.getData().getList();

                    for (int i = 0; i < listBeanList.size(); i++) {
                        listBeanList.get(i).setSelected(false);
                    }

                    if (entity.getData().getTotal() == 0) {
                        ToastUtils.getUtils(CloudDiskActivity.this).show("没有查询到相应数据。");
                    }

                    list.addAll(listBeanList);
                    cloudDiskAdapter.notifyDataSetChanged();
                } else {
                    httpDataUtils.showMsg(arg0.result);
                }

            }
        });
        httpDataUtils.setFailBack(new HttpDataUtils.FailBack() {
            @Override
            public void fail(String msg) {
                Log.e("45487+++++++", msg);
            }
        });

    }


    @OnClick({R.id.ic_back, R.id.btn_binding, R.id.et_search, R.id.iv_search_btn, R.id.tv_reset})
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.tv_reset:
                et_search.setText("");
                resetPageIndex();
                lv_file.setRefreshing();
                isPullRefresh = true;
                KeyboardUtils.hideKeyboard(rootView);
                getList();

                break;

            case R.id.et_search:
                et_search.setFocusable(true);
                break;

            case R.id.ic_back:
                finish();
                break;
            case R.id.btn_binding:
//                ToastUtils.getUtils(CloudDiskActivity.this).show("请选择文件。");
                if (selectList.size() == 0) {
                    ToastUtils.getUtils(CloudDiskActivity.this).show("请选择文件。");
                    return;
                }
//
                if (selectList.size() > num) {
                    ToastUtils.getUtils(CloudDiskActivity.this).show("只能选择单个文件。");
                    return;
                }

                String ids = "";
                String paths = "";
                String name = "";
                String size = "";

                for (int i = 0; i < selectList.size(); i++) {
                    ids = ids + selectList.get(i).getFile_id() + ",";
                    paths = paths + selectList.get(i).getFileurl_original() + ",";
                    name = name + selectList.get(i).getFilename() + ",";
                    size = size + selectList.get(i).getFile_size() + ",";
                }

                ids = ids.substring(0, ids.length() - 1);
                paths = paths.substring(0, paths.length() - 1);
                name = name.substring(0, name.length() - 1);
                size = size.substring(0, size.length() - 1);

                Intent intent = new Intent();
                intent.putExtra("ids", ids);
                intent.putExtra("paths", paths);
                intent.putExtra("name", name);
                intent.putExtra("size", size);
                setResult(Activity.RESULT_OK, intent);
                finish();

                break;

            case R.id.iv_search_btn:
                KeyboardUtils.hideKeyboard(rootView);
                resetPageIndex();
                isPullRefresh = true;
                getList();
                break;

        }
    }


    public static void startActivityForResult(Activity activity, int reqCode) {
        Intent intent = new Intent();
        intent.setClass(activity, CloudDiskActivity.class);
        activity.startActivityForResult(intent, reqCode);
    }


    class CloudDiskAdapter extends BaseAdapter {


        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int i) {
            return list.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(final int i, View view, ViewGroup viewGroup) {

            view = LayoutInflater.from(CloudDiskActivity.this).inflate(R.layout.item_clouddisk_file,
                    null);

            TextView tv_file_name = view.findViewById(R.id.tv_file_name);
            TextView tv_file_size = view.findViewById(R.id.tv_file_size);
            TextView tv_file_time = view.findViewById(R.id.tv_file_time);
            final CheckBox cb_select = view.findViewById(R.id.cb_select);


            tv_file_name.setText(list.get(i).getFilename());
            tv_file_size.setText(list.get(i).getFile_size());
            tv_file_time.setText(list.get(i).getCreated_at());

            if (list.get(i).isSelected()) {
                cb_select.setChecked(true);
            } else {
                cb_select.setChecked(false);
            }

            cb_select.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if (b) {
                        list.get(i).setSelected(true);
                        selectList.add(list.get(i));
//                        ToastUtils.getUtils(context).show("选择了" + list.get(i).getFilename() + list.get(i).getFile_id());
                    } else {
                        list.get(i).setSelected(false);
                        selectList.remove(list.get(i));
//                        ToastUtils.getUtils(context).show("取消选择" + list.get(i).getFilename());
                    }

                }
            });


            return view;
        }
    }


    class CloudDiskEntity {


        /**
         * code : 0
         * message : 查询结果！
         * data : {"pages":1,"total":2,"list":[{"id":2,"users_id":32,"filename":"bc6ee2c4895be0594fc51abc33103aef.jpg","file_id":1071,"file_size":"27496","created_at":"2018-08-13 11:22:47","updated_at":"2018-08-13 11:22:50"},{"id":1,"users_id":32,"filename":"60819ca5fb5197e73ba8a3982e0d6287.png","file_id":1073,"file_size":"248027","created_at":"2018-08-13 11:21:46","updated_at":"2018-08-13 11:21:49"}]}
         */

        private int code;
        private String message;
        private DataBean data;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public DataBean getData() {
            return data;
        }

        public void setData(DataBean data) {
            this.data = data;
        }

        public class DataBean {
            /**
             * pages : 1
             * total : 2
             * list : [{"id":2,"users_id":32,"filename":"bc6ee2c4895be0594fc51abc33103aef.jpg","file_id":1071,"file_size":"27496","created_at":"2018-08-13 11:22:47","updated_at":"2018-08-13 11:22:50"},{"id":1,"users_id":32,"filename":"60819ca5fb5197e73ba8a3982e0d6287.png","file_id":1073,"file_size":"248027","created_at":"2018-08-13 11:21:46","updated_at":"2018-08-13 11:21:49"}]
             */

            private int pages;
            private int total;
            private List<ListBean> list;

            public int getPages() {
                return pages;
            }

            public void setPages(int pages) {
                this.pages = pages;
            }

            public int getTotal() {
                return total;
            }

            public void setTotal(int total) {
                this.total = total;
            }

            public List<ListBean> getList() {
                return list;
            }

            public void setList(List<ListBean> list) {
                this.list = list;
            }

            public class ListBean {
                /**
                 * id : 2
                 * users_id : 32
                 * filename : bc6ee2c4895be0594fc51abc33103aef.jpg
                 * file_id : 1071
                 * file_size : 27496
                 * created_at : 2018-08-13 11:22:47
                 * updated_at : 2018-08-13 11:22:50
                 */

                private int id;
                private int users_id;
                private String filename;
                private int file_id;
                private String file_size;
                private String created_at;
                private String updated_at;
                private String fileurl_original;
                private boolean selected;

                public boolean isSelected() {
                    return selected;
                }

                public void setSelected(boolean selected) {
                    this.selected = selected;
                }

                //                private boolean select;
//
//                public boolean isSelect() {
//                    return select;
//                }
//
//                public void setSelect(boolean select) {
//                    this.select = select;
//                }


                public String getFileurl_original() {
                    return fileurl_original;
                }

                public void setFileurl_original(String fileurl_original) {
                    this.fileurl_original = fileurl_original;
                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public int getUsers_id() {
                    return users_id;
                }

                public void setUsers_id(int users_id) {
                    this.users_id = users_id;
                }

                public String getFilename() {
                    return filename;
                }

                public void setFilename(String filename) {
                    this.filename = filename;
                }

                public int getFile_id() {
                    return file_id;
                }

                public void setFile_id(int file_id) {
                    this.file_id = file_id;
                }

                public String getFile_size() {
                    return file_size;
                }

                public void setFile_size(String file_size) {
                    this.file_size = file_size;
                }

                public String getCreated_at() {
                    return created_at;
                }

                public void setCreated_at(String created_at) {
                    this.created_at = created_at;
                }

                public String getUpdated_at() {
                    return updated_at;
                }

                public void setUpdated_at(String updated_at) {
                    this.updated_at = updated_at;
                }
            }
        }
    }


}
