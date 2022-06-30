package com.yhy.hzzll.entity;

/**
 * Created by chengying on 2017/5/11.
 *
 *
 *
 */

public class PhotoEntity extends BaseEntity {

    String url;
    String imgpath;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImgpath() {
        return imgpath;
    }

    public void setImgpath(String imgpath) {
        this.imgpath = imgpath;
    }

    public PhotoEntity(String url, String imgpath) {
        this.url = url;
        this.imgpath = imgpath;
    }

}
