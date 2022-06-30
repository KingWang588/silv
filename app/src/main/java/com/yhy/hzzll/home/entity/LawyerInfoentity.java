package com.yhy.hzzll.home.entity;

import java.util.List;

/**
 * Created by chengying on 2017/7/27.
 */

public class LawyerInfoentity {
    /**
     * code : 0
     * message : 查询成功！
     * data : {"users_id":434,"lawyer_intro":"    腌先后毕业于辽宁师范大学、中国政法大学，获理学士、法学士的双学士学位，并在当年荣获\u201c中国政法大学优秀毕业生\u201d称号。白素华律师视野开阔、思维活跃、逻辑严密、作风稳健，擅长民商法（含合同法、公司法、房地产法、婚姻家庭、劳动争议等）、刑事辩护、行政诉讼与非诉讼业务。从业以来成功代理多起争议标的额大，法律关系复杂、具有较大社会影响的诉讼案件，有的案件曾被《民主与法制》等报刊报导。\n\n   扎实的理论功底、求实的工作作风、周到的服务意识，使白素华律师在当事人中间形成了良好的口碑，其工作业绩连年突出。目前，白素华律师在电信、印刷、服饰、酒店业等行业领域臜，","lawyer_type":"执业律师","date_of_issue":"","law_firm":"华债网","lawyer_license_photo":["http://hzzll-test-private.oss-cn-shanghai.aliyuncs.com/UploadRoot/13087/20170904/bef5c7267430299151727427c5c02f80.png","http://hzzll-test-private.oss-cn-shanghai.aliyuncs.com/UploadRoot/429/20170823/2bddf14b930acf1ad72bc44f8e57be44.jpg"],"lawyer_secpical":"3,4,2","page_view":1045,"legal_advice_rate":"8.0","handle_case_rate":"0.0","reply_consult_num":266,"handle_case_num":0,"follow":4,"exclusive_consult_isprice":1,"exclusive_consult_num":0,"quoted_consult_num":0,"avatar":1403,"truename":"何虎","base_region_id":{"simple_name":"北京","area_name":"北京市","whole_name":"北京,北京市,东城区"},"email":"123456@163.com","mobile":"15629037629","user_type":0,"user_status":1,"nickname":"本来就这样","address":"给我","id_number_front":"","id_number":"","id_number_opposite":"","id_number_handheld":"","id_number_expiry":"","authentication_status_id":3,"gender":2,"updated_at":"2017-09-16 12:13:03","is_buy_consult":false,"lawyer_offer_price":"69.00","headimg":"http://hzzll-test-private.oss-cn-shanghai.aliyuncs.com/UploadRoot/434/20170912/41181237720f81a95a268a3d77875d25.jpg","special":["投资证券","并购重组","建筑房产"],"im_token":{"users_id":434,"accid":"15629037629","token":"d6b7a51305fad8bedaadbce3fdebab92","is_delete":0},"is_follow":1}
     */

    private int code;
    private String message;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * users_id : 434
         * lawyer_intro :     腌先后毕业于辽宁师范大学、中国政法大学，获理学士、法学士的双学士学位，并在当年荣获“中国政法大学优秀毕业生”称号。白素华律师视野开阔、思维活跃、逻辑严密、作风稳健，擅长民商法（含合同法、公司法、房地产法、婚姻家庭、劳动争议等）、刑事辩护、行政诉讼与非诉讼业务。从业以来成功代理多起争议标的额大，法律关系复杂、具有较大社会影响的诉讼案件，有的案件曾被《民主与法制》等报刊报导。

            扎实的理论功底、求实的工作作风、周到的服务意识，使白素华律师在当事人中间形成了良好的口碑，其工作业绩连年突出。目前，白素华律师在电信、印刷、服饰、酒店业等行业领域臜，
         * lawyer_type : 执业律师
         * date_of_issue :
         * law_firm : 华债网
         * lawyer_license_photo : ["http://hzzll-test-private.oss-cn-shanghai.aliyuncs.com/UploadRoot/13087/20170904/bef5c7267430299151727427c5c02f80.png","http://hzzll-test-private.oss-cn-shanghai.aliyuncs.com/UploadRoot/429/20170823/2bddf14b930acf1ad72bc44f8e57be44.jpg"]
         * lawyer_secpical : 3,4,2
         * page_view : 1045
         * legal_advice_rate : 8.0
         * handle_case_rate : 0.0
         * reply_consult_num : 266
         * handle_case_num : 0
         * follow : 4
         * exclusive_consult_isprice : 1
         * exclusive_consult_num : 0
         * quoted_consult_num : 0
         * avatar : 1403
         * truename : 何虎
         * base_region_id : {"simple_name":"北京","area_name":"北京市","whole_name":"北京,北京市,东城区"}
         * email : 123456@163.com
         * mobile : 15629037629
         * user_type : 0
         * user_status : 1
         * nickname : 本来就这样
         * address : 给我
         * id_number_front :
         * id_number :
         * id_number_opposite :
         * id_number_handheld :
         * id_number_expiry :
         * authentication_status_id : 3
         * gender : 2
         * updated_at : 2017-09-16 12:13:03
         * is_buy_consult : false
         * lawyer_offer_price : 69.00
         * headimg : http://hzzll-test-private.oss-cn-shanghai.aliyuncs.com/UploadRoot/434/20170912/41181237720f81a95a268a3d77875d25.jpg
         * special : ["投资证券","并购重组","建筑房产"]
         * im_token : {"users_id":434,"accid":"15629037629","token":"d6b7a51305fad8bedaadbce3fdebab92","is_delete":0}
         * is_follow : 1
         */

        private int users_id;
        private String lawyer_intro;
        private String lawyer_type;
        private String date_of_issue;
        private String law_firm;
        private String lawyer_secpical;
        private int page_view;
        private String legal_advice_rate;
        private String handle_case_rate;
        private int reply_consult_num;
        private int handle_case_num;
        private int follow;
        private int exclusive_consult_isprice;
        private int exclusive_consult_num;
        private int quoted_consult_num;
        private int avatar;
        private String truename;
        private String lawyer_title;
        private BaseRegionIdBean base_region_id;
        private String email;
        private String mobile;
        private int user_type;
        private int user_status;
        private String nickname;
        private String address;
        private String id_number_front;
        private String id_number;
        private String id_number_opposite;
        private String id_number_handheld;
        private String id_number_expiry;
        private int authentication_status_id;
        private int gender;
        private String updated_at;
        private boolean is_buy_consult;
        private String lawyer_offer_price;
        private String headimg;
        private ImTokenBean im_token;
        private int is_follow;
        private List<String> lawyer_license_photo;
        private List<String> special;


        public String getLawyer_title() {
            return lawyer_title;
        }

        public int getUsers_id() {
            return users_id;
        }

        public void setUsers_id(int users_id) {
            this.users_id = users_id;
        }

        public String getLawyer_intro() {
            return lawyer_intro;
        }

        public void setLawyer_intro(String lawyer_intro) {
            this.lawyer_intro = lawyer_intro;
        }

        public String getLawyer_type() {
            return lawyer_type;
        }

        public void setLawyer_type(String lawyer_type) {
            this.lawyer_type = lawyer_type;
        }

        public String getDate_of_issue() {
            return date_of_issue;
        }

        public void setDate_of_issue(String date_of_issue) {
            this.date_of_issue = date_of_issue;
        }

        public String getLaw_firm() {
            return law_firm;
        }

        public void setLaw_firm(String law_firm) {
            this.law_firm = law_firm;
        }

        public String getLawyer_secpical() {
            return lawyer_secpical;
        }

        public void setLawyer_secpical(String lawyer_secpical) {
            this.lawyer_secpical = lawyer_secpical;
        }

        public int getPage_view() {
            return page_view;
        }

        public void setPage_view(int page_view) {
            this.page_view = page_view;
        }

        public String getLegal_advice_rate() {
            return legal_advice_rate;
        }

        public void setLegal_advice_rate(String legal_advice_rate) {
            this.legal_advice_rate = legal_advice_rate;
        }

        public String getHandle_case_rate() {
            return handle_case_rate;
        }

        public void setHandle_case_rate(String handle_case_rate) {
            this.handle_case_rate = handle_case_rate;
        }

        public int getReply_consult_num() {
            return reply_consult_num;
        }

        public void setReply_consult_num(int reply_consult_num) {
            this.reply_consult_num = reply_consult_num;
        }

        public int getHandle_case_num() {
            return handle_case_num;
        }

        public void setHandle_case_num(int handle_case_num) {
            this.handle_case_num = handle_case_num;
        }

        public int getFollow() {
            return follow;
        }

        public void setFollow(int follow) {
            this.follow = follow;
        }

        public int getExclusive_consult_isprice() {
            return exclusive_consult_isprice;
        }

        public void setExclusive_consult_isprice(int exclusive_consult_isprice) {
            this.exclusive_consult_isprice = exclusive_consult_isprice;
        }

        public int getExclusive_consult_num() {
            return exclusive_consult_num;
        }

        public void setExclusive_consult_num(int exclusive_consult_num) {
            this.exclusive_consult_num = exclusive_consult_num;
        }

        public int getQuoted_consult_num() {
            return quoted_consult_num;
        }

        public void setQuoted_consult_num(int quoted_consult_num) {
            this.quoted_consult_num = quoted_consult_num;
        }

        public int getAvatar() {
            return avatar;
        }

        public void setAvatar(int avatar) {
            this.avatar = avatar;
        }

        public String getTruename() {
            return truename;
        }

        public void setTruename(String truename) {
            this.truename = truename;
        }

        public BaseRegionIdBean getBase_region_id() {
            return base_region_id;
        }

        public void setBase_region_id(BaseRegionIdBean base_region_id) {
            this.base_region_id = base_region_id;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public int getUser_type() {
            return user_type;
        }

        public void setUser_type(int user_type) {
            this.user_type = user_type;
        }

        public int getUser_status() {
            return user_status;
        }

        public void setUser_status(int user_status) {
            this.user_status = user_status;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getId_number_front() {
            return id_number_front;
        }

        public void setId_number_front(String id_number_front) {
            this.id_number_front = id_number_front;
        }

        public String getId_number() {
            return id_number;
        }

        public void setId_number(String id_number) {
            this.id_number = id_number;
        }

        public String getId_number_opposite() {
            return id_number_opposite;
        }

        public void setId_number_opposite(String id_number_opposite) {
            this.id_number_opposite = id_number_opposite;
        }

        public String getId_number_handheld() {
            return id_number_handheld;
        }

        public void setId_number_handheld(String id_number_handheld) {
            this.id_number_handheld = id_number_handheld;
        }

        public String getId_number_expiry() {
            return id_number_expiry;
        }

        public void setId_number_expiry(String id_number_expiry) {
            this.id_number_expiry = id_number_expiry;
        }

        public int getAuthentication_status_id() {
            return authentication_status_id;
        }

        public void setAuthentication_status_id(int authentication_status_id) {
            this.authentication_status_id = authentication_status_id;
        }

        public int getGender() {
            return gender;
        }

        public void setGender(int gender) {
            this.gender = gender;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
        }

        public boolean isIs_buy_consult() {
            return is_buy_consult;
        }

        public void setIs_buy_consult(boolean is_buy_consult) {
            this.is_buy_consult = is_buy_consult;
        }

        public String getLawyer_offer_price() {
            return lawyer_offer_price;
        }

        public void setLawyer_offer_price(String lawyer_offer_price) {
            this.lawyer_offer_price = lawyer_offer_price;
        }

        public String getHeadimg() {
            return headimg;
        }

        public void setHeadimg(String headimg) {
            this.headimg = headimg;
        }

        public ImTokenBean getIm_token() {
            return im_token;
        }

        public void setIm_token(ImTokenBean im_token) {
            this.im_token = im_token;
        }

        public int getIs_follow() {
            return is_follow;
        }

        public void setIs_follow(int is_follow) {
            this.is_follow = is_follow;
        }

        public List<String> getLawyer_license_photo() {
            return lawyer_license_photo;
        }

        public void setLawyer_license_photo(List<String> lawyer_license_photo) {
            this.lawyer_license_photo = lawyer_license_photo;
        }

        public List<String> getSpecial() {
            return special;
        }

        public void setSpecial(List<String> special) {
            this.special = special;
        }

        public static class BaseRegionIdBean {
            /**
             * simple_name : 北京
             * area_name : 北京市
             * whole_name : 北京,北京市,东城区
             */

            private String simple_name;
            private String area_name;
            private String whole_name;

            public String getSimple_name() {
                return simple_name;
            }

            public void setSimple_name(String simple_name) {
                this.simple_name = simple_name;
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

        public static class ImTokenBean {
            /**
             * users_id : 434
             * accid : 15629037629
             * token : d6b7a51305fad8bedaadbce3fdebab92
             * is_delete : 0
             */
            private int users_id;
            private String accid;
            private String token;
            private int is_delete;

            public int getUsers_id() {
                return users_id;
            }

            public void setUsers_id(int users_id) {
                this.users_id = users_id;
            }

            public String getAccid() {
                return accid;
            }

            public void setAccid(String accid) {
                this.accid = accid;
            }

            public String getToken() {
                return token;
            }

            public void setToken(String token) {
                this.token = token;
            }

            public int getIs_delete() {
                return is_delete;
            }

            public void setIs_delete(int is_delete) {
                this.is_delete = is_delete;
            }
        }
    }
}
