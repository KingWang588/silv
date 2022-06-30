package com.yhy.hzzll.entity;

import java.io.Serializable;

/**
 * 代金券
 * 
 * @author 一合鱼
 *
 */
public class MyTreasureVoucherEntity implements Serializable{
	



    /**
     * id : 41
     * name : 律师协作
     * price : 50.00
     * start_time : 2016-04-01
     * expire_time : 2016-12-01
     * intro : 只能用于律师协作的托管交易
     * type : lawercoop
     */

    private String id;
    private String name;
    private String price;
    private String start_time;
    private String expire_time;
    private String intro;
    private String type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getExpire_time() {
        return expire_time;
    }

    public void setExpire_time(String expire_time) {
        this.expire_time = expire_time;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
