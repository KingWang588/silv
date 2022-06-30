package com.yhy.hzzll.adapter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.yhy.hzzll.R;
import com.yhy.hzzll.entity.PhotoEntity;
import com.yhy.hzzll.framework.MyData;

import java.util.List;



public class GridViewAdapter extends BaseAdapter {
    private View.OnClickListener delete;// 删除
    private LayoutInflater ml;
    private Context context;
//    private ArrayList<Map<String,Object>> imgs;
    List<PhotoEntity> photoEntities;
//    private ImageView mimageview;
//    int position = -1;

    public GridViewAdapter(Context context, List<PhotoEntity> photoEntities,View.OnClickListener delete) {
        this.context = context;
        this.delete = delete;
        this.photoEntities = photoEntities;
        ml= LayoutInflater.from(context);
    }

    @Override
    public int getCount(){
        return photoEntities==null ? 0 : photoEntities.size()+1;
    }

    @Override
    public Object getItem(int i) {
        return photoEntities==null ? null : photoEntities.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder=null;
        if(view==null){
            view = ml.inflate(R.layout.item_gridview_pic, null);
            holder=new ViewHolder();
            holder.img=(ImageView) view.findViewById(R.id.img);
            holder.iv_del = (ImageView) view.findViewById(R.id.iv_delete);
            view.setTag(holder);
        }else{
            holder=(ViewHolder) view.getTag();
        }
        if(i < photoEntities.size()){
            PhotoEntity photoEntity=photoEntities.get(i);
            String url = photoEntity.getImgpath();
            if (url.contains("http")){
                Glide.with(context).load(MyData.IP+url).into(holder.img);
            }else {
                holder.img.setImageBitmap(BitmapFactory.decodeFile(photoEntity.getImgpath()));
            }

            holder.iv_del.setTag(i);
            holder.iv_del.setOnClickListener(delete);
            holder.iv_del.setVisibility(View.VISIBLE);
        }else{
            holder.img.setImageResource(R.drawable.add);
            holder.iv_del.setVisibility(View.GONE);
        }

        return view;

    }

    class ViewHolder{
        ImageView img,iv_del;
    }

}
