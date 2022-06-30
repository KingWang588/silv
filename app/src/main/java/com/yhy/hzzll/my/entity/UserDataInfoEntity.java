package com.yhy.hzzll.my.entity;

import java.util.List;

/**
 * Created by chengying on 2017/7/19.
 */

public class UserDataInfoEntity {

    /**
     * code : 0
     * message : 查询成功
     * data : {"avatar":"http://hzzll-test-private.oss-cn-shanghai.aliyuncs.com/UploadRoot/434/20170904/b4dbc9a2e29d8e65f04ff2571f295f64.jpg","gender":"2","truename":"何虎","base_region_id":"山西省,阳泉市,郊区","base_region_number":"140311000000","address":"万达国际B座1108","auth_status":"已认证","occupation":"lawyer","nickname":"本来就这样","lawyer_intro":"先后毕业于辽宁师范大学、中国政法大学，获理学士、法学士的双学士学位，并在当年荣获\u201c中国政法大学优秀毕业生\u201d称号。白素华律师视野开阔、思维活跃、逻辑严密、作风稳健，擅长民商法（含合同法、公司法、房地产法、婚姻家庭、劳动争议等）、刑事辩护、行政诉讼与非诉讼业务。从业以来成功代理多起争议标的额大，法律关系复杂、具有较大社会影响的诉讼案件，有的案件曾被《民主与法制》等报刊报导。\n\n   扎实的理论功底、求实的工作作风、周到的服务意识，使白素华律师在当事人中间形成了良好的口碑，其工作业绩连年突出。目前，白素华律师在电信、印刷、服饰、酒店业等行业领域，担任多家大型国有\n","lawyer_secpical":"1,2,4,6","lawyer_type":"执业律师","lawyer_title":"主任","lawyer_license_no":"12345678979999999","lawyer_license_photo":[{"id":"203","fileurl_full":"http://hzzll-test-private.oss-cn-shanghai.aliyuncs.com/UploadRoot/429/20170823/2bddf14b930acf1ad72bc44f8e57be44.jpg"},{"id":"608","fileurl_full":"http://hzzll-test-private.oss-cn-shanghai.aliyuncs.com/UploadRoot/13087/20170904/bef5c7267430299151727427c5c02f80.png"}],"law_firm":"华债网","user_type":"0","id":434,"mobile":"15629037629","email":"","old_user_info":{"old_user_id":13073,"password":"e10adc3949ba59abbe56e057f20f883e","hash":"72052b7e43cb5ea55739603c967aa8f1","username":"15629037629"}}
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
         * avatar : http://hzzll-test-private.oss-cn-shanghai.aliyuncs.com/UploadRoot/434/20170904/b4dbc9a2e29d8e65f04ff2571f295f64.jpg
         * gender : 2
         * truename : 何虎
         * base_region_id : 山西省,阳泉市,郊区
         * base_region_number : 140311000000
         * address : 万达国际B座1108
         * auth_status : 已认证
         * occupation : lawyer
         * nickname : 本来就这样
         * lawyer_intro : 先后毕业于辽宁师范大学、中国政法大学，获理学士、法学士的双学士学位，并在当年荣获“中国政法大学优秀毕业生”称号。白素华律师视野开阔、思维活跃、逻辑严密、作风稳健，擅长民商法（含合同法、公司法、房地产法、婚姻家庭、劳动争议等）、刑事辩护、行政诉讼与非诉讼业务。从业以来成功代理多起争议标的额大，法律关系复杂、具有较大社会影响的诉讼案件，有的案件曾被《民主与法制》等报刊报导。

            扎实的理论功底、求实的工作作风、周到的服务意识，使白素华律师在当事人中间形成了良好的口碑，其工作业绩连年突出。目前，白素华律师在电信、印刷、服饰、酒店业等行业领域，担任多家大型国有

         * lawyer_secpical : 1,2,4,6
         * lawyer_type : 执业律师
         * lawyer_title : 主任
         * lawyer_license_no : 12345678979999999
         * lawyer_license_photo : [{"id":"203","fileurl_full":"http://hzzll-test-private.oss-cn-shanghai.aliyuncs.com/UploadRoot/429/20170823/2bddf14b930acf1ad72bc44f8e57be44.jpg"},{"id":"608","fileurl_full":"http://hzzll-test-private.oss-cn-shanghai.aliyuncs.com/UploadRoot/13087/20170904/bef5c7267430299151727427c5c02f80.png"}]
         * law_firm : 华债网
         * user_type : 0
         * id : 434
         * mobile : 15629037629
         * email :
         * old_user_info : {"old_user_id":13073,"password":"e10adc3949ba59abbe56e057f20f883e","hash":"72052b7e43cb5ea55739603c967aa8f1","username":"15629037629"}
         */

        private String avatar;
        private String gender;
        private String truename;
        private String base_region_id;
        private String base_region_number;
        private String address;
        private String auth_status;
        private String occupation;
        private String nickname;
        private String lawyer_intro;
        private String lawyer_secpical;
        private String lawyer_type;
        private String lawyer_title;
        private String lawyer_license_no;
        private String law_firm;
        private String user_type;
        private int id;
        private String mobile;
        private String email;
        private OldUserInfoBean old_user_info;
        private List<LawyerLicensePhotoBean> lawyer_license_photo;

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getTruename() {
            return truename;
        }

        public void setTruename(String truename) {
            this.truename = truename;
        }

        public String getBase_region_id() {
            return base_region_id;
        }

        public void setBase_region_id(String base_region_id) {
            this.base_region_id = base_region_id;
        }

        public String getBase_region_number() {
            return base_region_number;
        }

        public void setBase_region_number(String base_region_number) {
            this.base_region_number = base_region_number;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getAuth_status() {
            return auth_status;
        }

        public void setAuth_status(String auth_status) {
            this.auth_status = auth_status;
        }

        public String getOccupation() {
            return occupation;
        }

        public void setOccupation(String occupation) {
            this.occupation = occupation;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getLawyer_intro() {
            return lawyer_intro;
        }

        public void setLawyer_intro(String lawyer_intro) {
            this.lawyer_intro = lawyer_intro;
        }

        public String getLawyer_secpical() {
            return lawyer_secpical;
        }

        public void setLawyer_secpical(String lawyer_secpical) {
            this.lawyer_secpical = lawyer_secpical;
        }

        public String getLawyer_type() {
            return lawyer_type;
        }

        public void setLawyer_type(String lawyer_type) {
            this.lawyer_type = lawyer_type;
        }

        public String getLawyer_title() {
            return lawyer_title;
        }

        public void setLawyer_title(String lawyer_title) {
            this.lawyer_title = lawyer_title;
        }

        public String getLawyer_license_no() {
            return lawyer_license_no;
        }

        public void setLawyer_license_no(String lawyer_license_no) {
            this.lawyer_license_no = lawyer_license_no;
        }

        public String getLaw_firm() {
            return law_firm;
        }

        public void setLaw_firm(String law_firm) {
            this.law_firm = law_firm;
        }

        public String getUser_type() {
            return user_type;
        }

        public void setUser_type(String user_type) {
            this.user_type = user_type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public OldUserInfoBean getOld_user_info() {
            return old_user_info;
        }

        public void setOld_user_info(OldUserInfoBean old_user_info) {
            this.old_user_info = old_user_info;
        }

        public List<LawyerLicensePhotoBean> getLawyer_license_photo() {
            return lawyer_license_photo;
        }

        public void setLawyer_license_photo(List<LawyerLicensePhotoBean> lawyer_license_photo) {
            this.lawyer_license_photo = lawyer_license_photo;
        }

        public static class OldUserInfoBean {
            /**
             * old_user_id : 13073
             * password : e10adc3949ba59abbe56e057f20f883e
             * hash : 72052b7e43cb5ea55739603c967aa8f1
             * username : 15629037629
             */

            private int old_user_id;
            private String password;
            private String hash;
            private String username;

            public int getOld_user_id() {
                return old_user_id;
            }

            public void setOld_user_id(int old_user_id) {
                this.old_user_id = old_user_id;
            }

            public String getPassword() {
                return password;
            }

            public void setPassword(String password) {
                this.password = password;
            }

            public String getHash() {
                return hash;
            }

            public void setHash(String hash) {
                this.hash = hash;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }
        }

        public static class LawyerLicensePhotoBean {
            /**
             * id : 203
             * fileurl_full : http://hzzll-test-private.oss-cn-shanghai.aliyuncs.com/UploadRoot/429/20170823/2bddf14b930acf1ad72bc44f8e57be44.jpg
             */

            private String id;
            private String fileurl_full;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getFileurl_full() {
                return fileurl_full;
            }

            public void setFileurl_full(String fileurl_full) {
                this.fileurl_full = fileurl_full;
            }
        }
    }
}
