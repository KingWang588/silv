package com.yhy.hzzll.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yhy.hzzll.R;
import com.yhy.hzzll.home.entity.ServiceDetailsEntity;

import java.util.List;

public class FileAdapter extends BaseAdapter {

    Context context;
    List<ServiceDetailsEntity.DataBean.ServiceInfoBean.ApplicationInfoBean.WrittenDocumentsBean> list;

    public FileAdapter(Context context, List<ServiceDetailsEntity.DataBean.ServiceInfoBean.ApplicationInfoBean.WrittenDocumentsBean> list) {
        this.context = context;
        this.list = list;
    }

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
    public View getView(int i, View view, ViewGroup viewGroup) {
        view =  LayoutInflater.from(context).inflate(R.layout.item_file_display, null);

        TextView tv_name = view.findViewById(R.id.tv_name);
        TextView tv_time = view.findViewById(R.id.tv_time);

        tv_name.setText(list.get(i).getFilename());
        tv_time.setText(list.get(i).getCreated_time());

        return view;
    }
}
