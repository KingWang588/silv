package com.yhy.hzzll.file.browser;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.netease.nim.uikit.common.activity.ToolBarOptions;
import com.netease.nim.uikit.common.activity.UI;
import com.netease.nim.uikit.common.adapter.TAdapterDelegate;
import com.netease.nim.uikit.common.adapter.TViewHolder;

import com.yhy.hzzll.R;
import com.yhy.hzzll.utils.LogUtils;
import com.yhy.hzzll.utils.ToastUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 文件管理器
 * Created by hzxuwen on 2015/4/17.
 */
public class FileBrowserActivity extends UI implements TAdapterDelegate {
    // constant
    private static final String ROOT_PATH = Environment.getExternalStorageDirectory().getPath() + "/";
    public static final String EXTRA_DATA_PATH = "EXTRA_DATA_PATH";

    // data
    private ArrayList<String> names = null; //存储文件名称
    private ArrayList<String> paths = null; //存储文件路径

    // view
    private ListView fileListView;
    private ImageView iv_back;
    private List<FileBrowserAdapter.FileManagerItem> fileListItems = new ArrayList<>();

    public static void startActivityForResult(Activity activity, int reqCode) {
        Intent intent = new Intent();
        intent.setClass(activity, FileBrowserActivity.class);
        activity.startActivityForResult(intent, reqCode);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.file_browser_activity);

        ToolBarOptions options = new ToolBarOptions();
        setToolBar(R.id.toolbar, options);

        findViews();
        showFileDir(ROOT_PATH);
    }

    private void findViews() {
        fileListView = (ListView) findViewById(R.id.file_list);
        iv_back = (ImageView) findView(R.id.iv_back);

        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    /**
     * 显示文件列表
     * @param path 根路径
     */
    private void showFileDir(String path){
        names = new ArrayList<>();
        paths = new ArrayList<>();
        File file = new File(path);
        File[] files = file.listFiles();

        //如果当前目录不是根目录
        if (ROOT_PATH.equals(path)) {
//            if (path.endsWith("txt")||path.endsWith("doc")||path.endsWith("xls")||path.endsWith("mp3")||path.endsWith("amr")||path.endsWith("wav")
//                    ||path.endsWith("docx")||path.endsWith("ppt")||path.endsWith("pptx")||path.endsWith("xlsx")||path.endsWith("pot")
//                    ||path.endsWith("wps")||path.endsWith("et")||path.endsWith("dps")||path.endsWith("pdf")||path.endsWith("zip")||path.endsWith("rar")||path.endsWith("jpg")||path.endsWith("png")){

                names.add("@1");
                paths.add(ROOT_PATH);
//            }


        } else {

            names.add("@2");
            paths.add(file.getParent());
        }
        //添加所有文件
        for (File f : files) {
            names.add(f.getName());
            paths.add(f.getPath());
        }

        fileListItems.clear();
        for (int i = 0; i < names.size(); i++) {
            fileListItems.add(new FileBrowserAdapter.FileManagerItem(names.get(i), paths.get(i)));
        }


        fileListView.setItemsCanFocus(true);
        fileListView.setAdapter(new FileBrowserAdapter(this, fileListItems, this));
        fileListView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String path = fileListItems.get(position).getPath();

                File file = new File(path);
                // 文件存在并可读
                if (file.exists() && file.canRead()) {
                    if (file.isDirectory()) {
                        //显示子目录及文件
                        showFileDir(path);
                    } else {
                        //处理文件
                        selectFile(path);
                    }
                } else {
                    //没有权限
                    Toast.makeText(FileBrowserActivity.this, R.string.no_permission, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * （只能发送文件后缀为: doc、xls、mp3、amr、wav、docx、ppt、pptx、xlsx、 pot、wps、txt、et、dps、pdf、zip、rar的文件）
     * @param path
     */

    private void selectFile(String path) {
        LogUtils.logE(path);
        if (path.endsWith("txt")||path.endsWith("doc")||path.endsWith("xls")||path.endsWith("mp3")||path.endsWith("amr")||path.endsWith("wav")
                ||path.endsWith("docx")||path.endsWith("ppt")||path.endsWith("pptx")||path.endsWith("xlsx")||path.endsWith("pot")
                ||path.endsWith("wps")||path.endsWith("et")||path.endsWith("dps")||path.endsWith("pdf")||path.endsWith("zip")||path.endsWith("rar")||path.endsWith("jpg")||path.endsWith("png")){
            Intent intent = new Intent();
            intent.putExtra(EXTRA_DATA_PATH, path);
            setResult(Activity.RESULT_OK, intent);
            finish();
        }else{
            ToastUtils.getUtils(FileBrowserActivity.this).show("不支持的文件格式！");
        }
    }

    /**
     * *************** implements TAdapterDelegate ***************
     */
    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public Class<? extends TViewHolder> viewHolderAtPosition(int position) {
        return FileBrowserViewHolder.class;
    }

    @Override
    public boolean enabled(int position) {
        return true;
    }
}
