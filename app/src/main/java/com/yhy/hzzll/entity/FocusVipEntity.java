package com.yhy.hzzll.entity;

import android.graphics.Bitmap;

import java.io.Serializable;

/**
 * 我的关注--会员数据
 *
 * @author wangyang
 */
public class FocusVipEntity  {

    private Bitmap headImg;

    private String name = "";

    private String tag = "";

    public Bitmap getHeadImg() {
        return headImg;
    }

    public void setHeadImg(Bitmap headImg) {
        this.headImg = headImg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

}
