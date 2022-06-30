package com.yhy.hzzll.session.extension;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by chengying on 2017/8/24.
 */

public class EvaluateAttachment extends CustomAttachment {


    public EvaluateAttachment() {
        super(CustomAttachmentType.Evaluate);
    }


    public static final String KEY_ORDER_NO = "order_no";
//    public static final String KEY_ID = "id";

    String order_no;
//    String id;

    public String getOrder_no() {
        return order_no;
    }

    public void setOrder_no(String order_no) {
        this.order_no = order_no;
    }

//    public String getId() {
////        return id;
//    }

//    public void setId(String id) {
////        this.id = id;
//    }

    @Override
    protected void parseData(JSONObject data) {
        this.order_no = data.getString(KEY_ORDER_NO);
//        this.id = data.getString(KEY_ID);
    }

    @Override
    protected JSONObject packData() {
        JSONObject data = new JSONObject();

        data.put(KEY_ORDER_NO, order_no);
//        data.put(KEY_ID, id);

        return data;
    }
}
