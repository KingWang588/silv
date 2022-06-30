package com.yhy.hzzll.session.extension;

import com.alibaba.fastjson.JSONObject;

import java.security.KeyException;

public class CloudFileAttachment extends CustomAttachment {

    private final String KEY_NAME = "name";
    private final String KEY_URL = "url";
    private final String KEY_SIZE = "size";


    public CloudFileAttachment() {
        super(CustomAttachmentType.CloudFile);
    }

    String url;
    String name;
    String size;

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public CloudFileAttachment(int type, String url, String name,String size) {
        super(type);
        this.url = url;
        this.name = name;
        this.size = size;
    }

    @Override
    protected void parseData(JSONObject data) {
        this.name = data.getString(KEY_NAME);
        this.url = data.getString(KEY_URL);
        this.size = data.getString(KEY_SIZE);

    }

    @Override
    protected JSONObject packData() {

        JSONObject data = new JSONObject();
        data.put(KEY_NAME, name);
        data.put(KEY_URL, url);
        data.put(KEY_SIZE, size);

        return data;
    }
}
