package com.yhy.hzzll.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by chengying on 2017/9/13.
 */
@Entity
public class Footprint  {
    @Id(autoincrement = true)
    private Long id;
    private String nid;
    private String addtime;
    private String title;
    private String cate_name;

    public Footprint(String nid, String addtime, String title, String cate_name) {
        this.nid = nid;
        this.addtime = addtime;
        this.title = title;
        this.cate_name = cate_name;
    }



    @Generated(hash = 1258550886)
    public Footprint(Long id, String nid, String addtime, String title,
            String cate_name) {
        this.id = id;
        this.nid = nid;
        this.addtime = addtime;
        this.title = title;
        this.cate_name = cate_name;
    }



    @Generated(hash = 23830807)
    public Footprint() {
    }



    public String getNid() {
        return nid;
    }

    public void setNid(String nid) {
        this.nid = nid;
    }

    public String getAddtime() {
        return addtime;
    }

    public void setAddtime(String addtime) {
        this.addtime = addtime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCate_name() {
        return cate_name;
    }

    public void setCate_name(String cate_name) {
        this.cate_name = cate_name;
    }



    public Long getId() {
        return this.id;
    }



    public void setId(Long id) {
        this.id = id;
    }
}
