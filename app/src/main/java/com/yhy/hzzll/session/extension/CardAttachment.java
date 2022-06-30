package com.yhy.hzzll.session.extension;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by chengying on 2017/8/24.
 */

public class CardAttachment extends CustomAttachment {

    private final String KEY_ADDRESS = "address";
    private final String KEY_SPECIAL = "special";
    private final String KEY_ID = "id";
    private final String KEY_NAME = "name";
    private final String KEY_AVATAR = "avatar";

   public CardAttachment() {
        super(CustomAttachmentType.Card);
    }

    String avatar;
    String name;
    String address;
    String special;
    String id;

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSpecial() {
        return special;
    }

    public void setSpecial(String special) {
        this.special = special;
    }

    public CardAttachment(int type, String name, String address, String special, String id,String avatar) {
        super(type);
        this.name = name;
        this.address = address;
        this.special = special;
        this.id = id;
        this.avatar = avatar;
    }

    @Override
    protected void parseData(JSONObject data) {

        this.address = data.getString(KEY_ADDRESS);
        this.special = data.getString(KEY_SPECIAL);
        this.id = data.getString(KEY_ID);
        this.name = data.getString(KEY_NAME);
        this.avatar = data.getString(KEY_AVATAR);
    }


    @Override
    protected JSONObject packData() {

        JSONObject data = new JSONObject();

        data.put(KEY_ADDRESS, address);
        data.put(KEY_SPECIAL, special);
        data.put(KEY_ID, id);
        data.put(KEY_NAME,name);
        data.put(KEY_AVATAR, avatar);

        return data;
    }

}
