package com.yhy.hzzll.mian.entity;

import java.util.List;

/**
 * Created by chengying on 2017/7/7.
 */

public class AddressEntity {


    /**
     * code : 0
     * message : 查询成功
     * data : [{"id":"0","level":"0","area_name":"中国","whole_name":"中国"},{"id":"110000000000","level":"1","area_name":"北京","whole_name":"北京"},{"id":"120000000000","level":"1","area_name":"天津","whole_name":"天津"},{"id":"130000000000","level":"1","area_name":"河北省","whole_name":"河北省"},{"id":"140000000000","level":"1","area_name":"山西省","whole_name":"山西省"},{"id":"150000000000","level":"1","area_name":"内蒙古自治区","whole_name":"内蒙古自治区"},{"id":"210000000000","level":"1","area_name":"辽宁省","whole_name":"辽宁省"},{"id":"220000000000","level":"1","area_name":"吉林省","whole_name":"吉林省"},{"id":"230000000000","level":"1","area_name":"黑龙江省","whole_name":"黑龙江省"},{"id":"310000000000","level":"1","area_name":"上海","whole_name":"上海"},{"id":"320000000000","level":"1","area_name":"江苏省","whole_name":"江苏省"},{"id":"330000000000","level":"1","area_name":"浙江省","whole_name":"浙江省"},{"id":"340000000000","level":"1","area_name":"安徽省","whole_name":"安徽省"},{"id":"350000000000","level":"1","area_name":"福建省","whole_name":"福建省"},{"id":"360000000000","level":"1","area_name":"江西省","whole_name":"江西省"},{"id":"370000000000","level":"1","area_name":"山东省","whole_name":"山东省"},{"id":"410000000000","level":"1","area_name":"河南省","whole_name":"河南省"},{"id":"420000000000","level":"1","area_name":"湖北省","whole_name":"湖北省"},{"id":"430000000000","level":"1","area_name":"湖南省","whole_name":"湖南省"},{"id":"440000000000","level":"1","area_name":"广东省","whole_name":"广东省"},{"id":"450000000000","level":"1","area_name":"广西壮族自治区","whole_name":"广西壮族自治区"},{"id":"460000000000","level":"1","area_name":"海南省","whole_name":"海南省"},{"id":"500000000000","level":"1","area_name":"重庆","whole_name":"重庆"},{"id":"510000000000","level":"1","area_name":"四川省","whole_name":"四川省"},{"id":"520000000000","level":"1","area_name":"贵州省","whole_name":"贵州省"},{"id":"530000000000","level":"1","area_name":"云南省","whole_name":"云南省"},{"id":"540000000000","level":"1","area_name":"西藏自治区","whole_name":"西藏自治区"},{"id":"610000000000","level":"1","area_name":"陕西省","whole_name":"陕西省"},{"id":"620000000000","level":"1","area_name":"甘肃省","whole_name":"甘肃省"},{"id":"630000000000","level":"1","area_name":"青海省","whole_name":"青海省"},{"id":"640000000000","level":"1","area_name":"宁夏回族自治区","whole_name":"宁夏回族自治区"},{"id":"650000000000","level":"1","area_name":"新疆维吾尔自治区","whole_name":"新疆维吾尔自治区"},{"id":"710000000000","level":"1","area_name":"台湾","whole_name":"台湾"},{"id":"810000000000","level":"1","area_name":"香港特别行政区","whole_name":"香港特别行政区"},{"id":"820000000000","level":"1","area_name":"澳门特别行政区","whole_name":"澳门特别行政区"},{"id":"900000000000","level":"1","area_name":"钓鱼岛","whole_name":"钓鱼岛"}]
     */

    private int code;
    private String message;
    private List<AddressBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<AddressBean> getData() {
        return data;
    }

    public void setData(List<AddressBean> data) {
        this.data = data;
    }

    public static class AddressBean {

        public AddressBean(String id, String level, String area_name, String whole_name) {
            this.id = id;
            this.level = level;
            this.area_name = area_name;
            this.whole_name = whole_name;
        }

        /**
         * id : 0
         * level : 0
         * area_name : 中国
         * whole_name : 中国
         */


        private String id;
        private String level;
        private String area_name;
        private String whole_name;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public String getArea_name() {
            return area_name;
        }

        public void setArea_name(String area_name) {
            this.area_name = area_name;
        }

        public String getWhole_name() {
            return whole_name;
        }

        public void setWhole_name(String whole_name) {
            this.whole_name = whole_name;
        }
    }
}
