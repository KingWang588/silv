package com.yhy.hzzll.entity;

import java.util.List;

/**
 * 当事人详细信息
 * @author zsfzbc
 *
 */
public class PartyDetailEntity {
    /**
     * code : 000000
     * msg : 查询成功
     * data : {"customer_id":"206","lawyer_id":"13073","customer_type":"个人","customer_name":"何虎2","customer_contact":"18453525653","customer_gender":"男","other_contact":"","customer_address":"","customer_birthday":"2017-05-11","customer_idcard_type":"身份证","customer_idcard":"","customer_remark":"","hash":"f8e3492defc02c28dc53156bccb3a463","is_member":"非华债网会员","is_share":"0","is_deleted":"0","ctime":"1494495501","is_member_value":"0","imgurl":[{"id":"9701","uid":"13073","ctime":"1494495435","hash":"f8e3492defc02c28dc53156bccb3a463","filename":"Screenshot_2017-05-09-22-34-24-943_com.tencent.mm.png","filetype":".png","filesize":"210041","url":"/Uploads/users/13073/20170511/b086bb690187158f384976ecf2687e86/file_c.png","b_url":"/Uploads/users/13073/20170511/b086bb690187158f384976ecf2687e86/file_b.png","a_url":"/Uploads/users/13073/20170511/b086bb690187158f384976ecf2687e86/file_a.png","isimage":"1","thumb":"0","remote":"0","aid":"0"},{"id":"9703","uid":"13073","ctime":"1494495451","hash":"f8e3492defc02c28dc53156bccb3a463","filename":"Screenshot_2017-05-09-22-10-41-710_com.tencent.mm.png","filetype":".png","filesize":"1058407","url":"/Uploads/users/13073/20170511/afe783e0ee7318a8a5235ed2804de792/file_c.png","b_url":"/Uploads/users/13073/20170511/afe783e0ee7318a8a5235ed2804de792/file_b.png","a_url":"/Uploads/users/13073/20170511/afe783e0ee7318a8a5235ed2804de792/file_a.png","isimage":"1","thumb":"0","remote":"0","aid":"0"},{"id":"9704","uid":"13073","ctime":"1494495483","hash":"f8e3492defc02c28dc53156bccb3a463","filename":"Screenshot_2017-05-10-07-43-55-337_com.tencent.mm.png","filetype":".png","filesize":"1497240","url":"/Uploads/users/13073/20170511/3f0900913ad9f7f27d93d605b43c36d8/file_c.png","b_url":"/Uploads/users/13073/20170511/3f0900913ad9f7f27d93d605b43c36d8/file_b.png","a_url":"/Uploads/users/13073/20170511/3f0900913ad9f7f27d93d605b43c36d8/file_a.png","isimage":"1","thumb":"0","remote":"0","aid":"0"}],"imgcount":3}
     */

    private String code;
    private String msg;
    private DataBean data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * customer_id : 206
         * lawyer_id : 13073
         * customer_type : 个人
         * customer_name : 何虎2
         * customer_contact : 18453525653
         * customer_gender : 男
         * other_contact :
         * customer_address :
         * customer_birthday : 2017-05-11
         * customer_idcard_type : 身份证
         * customer_idcard :
         * customer_remark :
         * hash : f8e3492defc02c28dc53156bccb3a463
         * is_member : 非华债网会员
         * is_share : 0
         * is_deleted : 0
         * ctime : 1494495501
         * is_member_value : 0
         * imgurl : [{"id":"9701","uid":"13073","ctime":"1494495435","hash":"f8e3492defc02c28dc53156bccb3a463","filename":"Screenshot_2017-05-09-22-34-24-943_com.tencent.mm.png","filetype":".png","filesize":"210041","url":"/Uploads/users/13073/20170511/b086bb690187158f384976ecf2687e86/file_c.png","b_url":"/Uploads/users/13073/20170511/b086bb690187158f384976ecf2687e86/file_b.png","a_url":"/Uploads/users/13073/20170511/b086bb690187158f384976ecf2687e86/file_a.png","isimage":"1","thumb":"0","remote":"0","aid":"0"},{"id":"9703","uid":"13073","ctime":"1494495451","hash":"f8e3492defc02c28dc53156bccb3a463","filename":"Screenshot_2017-05-09-22-10-41-710_com.tencent.mm.png","filetype":".png","filesize":"1058407","url":"/Uploads/users/13073/20170511/afe783e0ee7318a8a5235ed2804de792/file_c.png","b_url":"/Uploads/users/13073/20170511/afe783e0ee7318a8a5235ed2804de792/file_b.png","a_url":"/Uploads/users/13073/20170511/afe783e0ee7318a8a5235ed2804de792/file_a.png","isimage":"1","thumb":"0","remote":"0","aid":"0"},{"id":"9704","uid":"13073","ctime":"1494495483","hash":"f8e3492defc02c28dc53156bccb3a463","filename":"Screenshot_2017-05-10-07-43-55-337_com.tencent.mm.png","filetype":".png","filesize":"1497240","url":"/Uploads/users/13073/20170511/3f0900913ad9f7f27d93d605b43c36d8/file_c.png","b_url":"/Uploads/users/13073/20170511/3f0900913ad9f7f27d93d605b43c36d8/file_b.png","a_url":"/Uploads/users/13073/20170511/3f0900913ad9f7f27d93d605b43c36d8/file_a.png","isimage":"1","thumb":"0","remote":"0","aid":"0"}]
         * imgcount : 3
         */

        private String customer_id;
        private String lawyer_id;
        private String customer_type;
        private String customer_name;
        private String customer_contact;
        private String customer_gender;
        private String other_contact;
        private String customer_address;
        private String customer_birthday;
        private String customer_idcard_type;
        private String customer_idcard;
        private String customer_remark;
        private String hash;
        private String is_member;
        private String is_share;
        private String is_deleted;
        private String ctime;
        private String is_member_value;
        private int imgcount;
        private List<ImgurlBean> imgurl;

        public String getCustomer_id() {
            return customer_id;
        }

        public void setCustomer_id(String customer_id) {
            this.customer_id = customer_id;
        }

        public String getLawyer_id() {
            return lawyer_id;
        }

        public void setLawyer_id(String lawyer_id) {
            this.lawyer_id = lawyer_id;
        }

        public String getCustomer_type() {
            return customer_type;
        }

        public void setCustomer_type(String customer_type) {
            this.customer_type = customer_type;
        }

        public String getCustomer_name() {
            return customer_name;
        }

        public void setCustomer_name(String customer_name) {
            this.customer_name = customer_name;
        }

        public String getCustomer_contact() {
            return customer_contact;
        }

        public void setCustomer_contact(String customer_contact) {
            this.customer_contact = customer_contact;
        }

        public String getCustomer_gender() {
            return customer_gender;
        }

        public void setCustomer_gender(String customer_gender) {
            this.customer_gender = customer_gender;
        }

        public String getOther_contact() {
            return other_contact;
        }

        public void setOther_contact(String other_contact) {
            this.other_contact = other_contact;
        }

        public String getCustomer_address() {
            return customer_address;
        }

        public void setCustomer_address(String customer_address) {
            this.customer_address = customer_address;
        }

        public String getCustomer_birthday() {
            return customer_birthday;
        }

        public void setCustomer_birthday(String customer_birthday) {
            this.customer_birthday = customer_birthday;
        }

        public String getCustomer_idcard_type() {
            return customer_idcard_type;
        }

        public void setCustomer_idcard_type(String customer_idcard_type) {
            this.customer_idcard_type = customer_idcard_type;
        }

        public String getCustomer_idcard() {
            return customer_idcard;
        }

        public void setCustomer_idcard(String customer_idcard) {
            this.customer_idcard = customer_idcard;
        }

        public String getCustomer_remark() {
            return customer_remark;
        }

        public void setCustomer_remark(String customer_remark) {
            this.customer_remark = customer_remark;
        }

        public String getHash() {
            return hash;
        }

        public void setHash(String hash) {
            this.hash = hash;
        }

        public String getIs_member() {
            return is_member;
        }

        public void setIs_member(String is_member) {
            this.is_member = is_member;
        }

        public String getIs_share() {
            return is_share;
        }

        public void setIs_share(String is_share) {
            this.is_share = is_share;
        }

        public String getIs_deleted() {
            return is_deleted;
        }

        public void setIs_deleted(String is_deleted) {
            this.is_deleted = is_deleted;
        }

        public String getCtime() {
            return ctime;
        }

        public void setCtime(String ctime) {
            this.ctime = ctime;
        }

        public String getIs_member_value() {
            return is_member_value;
        }

        public void setIs_member_value(String is_member_value) {
            this.is_member_value = is_member_value;
        }

        public int getImgcount() {
            return imgcount;
        }

        public void setImgcount(int imgcount) {
            this.imgcount = imgcount;
        }

        public List<ImgurlBean> getImgurl() {
            return imgurl;
        }

        public void setImgurl(List<ImgurlBean> imgurl) {
            this.imgurl = imgurl;
        }

        public static class ImgurlBean {
            /**
             * id : 9701
             * uid : 13073
             * ctime : 1494495435
             * hash : f8e3492defc02c28dc53156bccb3a463
             * filename : Screenshot_2017-05-09-22-34-24-943_com.tencent.mm.png
             * filetype : .png
             * filesize : 210041
             * url : /Uploads/users/13073/20170511/b086bb690187158f384976ecf2687e86/file_c.png
             * b_url : /Uploads/users/13073/20170511/b086bb690187158f384976ecf2687e86/file_b.png
             * a_url : /Uploads/users/13073/20170511/b086bb690187158f384976ecf2687e86/file_a.png
             * isimage : 1
             * thumb : 0
             * remote : 0
             * aid : 0
             */

            private String id;
            private String uid;
            private String ctime;
            private String hash;
            private String filename;
            private String filetype;
            private String filesize;
            private String url;
            private String b_url;
            private String a_url;
            private String isimage;
            private String thumb;
            private String remote;
            private String aid;
            private String relative_url;

            public String getRelative_url() {
                return relative_url;
            }

            public void setRelative_url(String relative_url) {
                this.relative_url = relative_url;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getUid() {
                return uid;
            }

            public void setUid(String uid) {
                this.uid = uid;
            }

            public String getCtime() {
                return ctime;
            }

            public void setCtime(String ctime) {
                this.ctime = ctime;
            }

            public String getHash() {
                return hash;
            }

            public void setHash(String hash) {
                this.hash = hash;
            }

            public String getFilename() {
                return filename;
            }

            public void setFilename(String filename) {
                this.filename = filename;
            }

            public String getFiletype() {
                return filetype;
            }

            public void setFiletype(String filetype) {
                this.filetype = filetype;
            }

            public String getFilesize() {
                return filesize;
            }

            public void setFilesize(String filesize) {
                this.filesize = filesize;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getB_url() {
                return b_url;
            }

            public void setB_url(String b_url) {
                this.b_url = b_url;
            }

            public String getA_url() {
                return a_url;
            }

            public void setA_url(String a_url) {
                this.a_url = a_url;
            }

            public String getIsimage() {
                return isimage;
            }

            public void setIsimage(String isimage) {
                this.isimage = isimage;
            }

            public String getThumb() {
                return thumb;
            }

            public void setThumb(String thumb) {
                this.thumb = thumb;
            }

            public String getRemote() {
                return remote;
            }

            public void setRemote(String remote) {
                this.remote = remote;
            }

            public String getAid() {
                return aid;
            }

            public void setAid(String aid) {
                this.aid = aid;
            }
        }
    }


//    /**
//     * customer_id : 34
//     * lawyer_id : 10058
//     * customer_type : 一般单位
//     * customer_name : a路路通
//     * customer_contact : 15802712922
//     * customer_gender : 男
//     * other_contact : 2588888855
//     * customer_address : 558888看
//     * customer_birthday : 2016-09-03
//     * customer_idcard_type : 身份证
//     * customer_idcard : 255888888
//     * customer_remark :
//     * hash : 59fe1bb5c554acececaa22f76075f6fd
//     * is_member : 非华债网会员
//     * is_share : 0
//     * is_deleted : 0
//     * ctime : 1472893441
//     * is_member_value : 0
//     * imgcount : 0
//     */
//
//    private String customer_id;
//    private String lawyer_id;
//    private String customer_type;
//    private String customer_name;
//    private String customer_contact;
//    private String customer_gender;
//    private String other_contact;
//    private String customer_address;
//    private String customer_birthday;
//    private String customer_idcard_type;
//    private String customer_idcard;
//    private String customer_remark;
//    private String hash;
//    private String is_member;
//    private String is_share;
//    private String is_deleted;
//    private String ctime;
//    private String is_member_value;
//    private int imgcount;
//
//    public String getCustomer_id() {
//        return customer_id;
//    }
//
//    public void setCustomer_id(String customer_id) {
//        this.customer_id = customer_id;
//    }
//
//    public String getLawyer_id() {
//        return lawyer_id;
//    }
//
//    public void setLawyer_id(String lawyer_id) {
//        this.lawyer_id = lawyer_id;
//    }
//
//    public String getCustomer_type() {
//        return customer_type;
//    }
//
//    public void setCustomer_type(String customer_type) {
//        this.customer_type = customer_type;
//    }
//
//    public String getCustomer_name() {
//        return customer_name;
//    }
//
//    public void setCustomer_name(String customer_name) {
//        this.customer_name = customer_name;
//    }
//
//    public String getCustomer_contact() {
//        return customer_contact;
//    }
//
//    public void setCustomer_contact(String customer_contact) {
//        this.customer_contact = customer_contact;
//    }
//
//    public String getCustomer_gender() {
//        return customer_gender;
//    }
//
//    public void setCustomer_gender(String customer_gender) {
//        this.customer_gender = customer_gender;
//    }
//
//    public String getOther_contact() {
//        return other_contact;
//    }
//
//    public void setOther_contact(String other_contact) {
//        this.other_contact = other_contact;
//    }
//
//    public String getCustomer_address() {
//        return customer_address;
//    }
//
//    public void setCustomer_address(String customer_address) {
//        this.customer_address = customer_address;
//    }
//
//    public String getCustomer_birthday() {
//        return customer_birthday;
//    }
//
//    public void setCustomer_birthday(String customer_birthday) {
//        this.customer_birthday = customer_birthday;
//    }
//
//    public String getCustomer_idcard_type() {
//        return customer_idcard_type;
//    }
//
//    public void setCustomer_idcard_type(String customer_idcard_type) {
//        this.customer_idcard_type = customer_idcard_type;
//    }
//
//    public String getCustomer_idcard() {
//        return customer_idcard;
//    }
//
//    public void setCustomer_idcard(String customer_idcard) {
//        this.customer_idcard = customer_idcard;
//    }
//
//    public String getCustomer_remark() {
//        return customer_remark;
//    }
//
//    public void setCustomer_remark(String customer_remark) {
//        this.customer_remark = customer_remark;
//    }
//
//    public String getHash() {
//        return hash;
//    }
//
//    public void setHash(String hash) {
//        this.hash = hash;
//    }
//
//    public String getIs_member() {
//        return is_member;
//    }
//
//    public void setIs_member(String is_member) {
//        this.is_member = is_member;
//    }
//
//    public String getIs_share() {
//        return is_share;
//    }
//
//    public void setIs_share(String is_share) {
//        this.is_share = is_share;
//    }
//
//    public String getIs_deleted() {
//        return is_deleted;
//    }
//
//    public void setIs_deleted(String is_deleted) {
//        this.is_deleted = is_deleted;
//    }
//
//    public String getCtime() {
//        return ctime;
//    }
//
//    public void setCtime(String ctime) {
//        this.ctime = ctime;
//    }
//
//    public String getIs_member_value() {
//        return is_member_value;
//    }
//
//    public void setIs_member_value(String is_member_value) {
//        this.is_member_value = is_member_value;
//    }
//
//    public int getImgcount() {
//        return imgcount;
//    }
//
//    public void setImgcount(int imgcount) {
//        this.imgcount = imgcount;
//    }

}
