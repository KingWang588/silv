package com.yhy.hzzll.entity;

import java.util.List;

/**
 * 律师风采
 * 
 * @author 一合鱼
 *
 */
public class LawyerStyleEntity {
	  /**
     * id : 114
     * uid : 10058
     * summary : 阿萨达速度
     * hash : 187fd84208e62620dc604a99f491262e
     * ctime : 2016年05月07日
     * utime : 2016-05-07 14:32:11
     * deleted : 0
     * praise : 2
     * comment : {"code":"000000","msg":"获取律师风采成功","data":{"list":[{"id":"18","uid":"10058","postuid":"12900","postname":"华债网用户","nickname":"华债网用户","imgurl":"/Uploads/temp/20160513/f3cb17851826d3b4107c541fb43f0729/file_c.jpg","nid":"114","message":"好吃","addtime":"2016年05月13日","utype":"1"},{"id":"17","uid":"10058","postuid":"10101","postname":"肖筱","nickname":"肖筱","imgurl":"/stc/upload/10101/avatar/photo.jpg","nid":"114","message":"不错","addtime":"2016年05月07日","utype":"1"}],"count":"2","pages":1}}
     * imgurl : [{"id":"7453","uid":"10058","ctime":"1462602731","hash":"187fd84208e62620dc604a99f491262e","filename":"p7818.jpg","filetype":".jpg","filesize":"6072","url":"/Uploads/users/10058/20160507/aa7b83360403f82c70f86948a923eb17/file_c.jpg","b_url":"/Uploads/users/10058/20160507/aa7b83360403f82c70f86948a923eb17/file_a.jpg","a_url":"","isimage":"1","thumb":"0","remote":"0","aid":"0"}]
     * imgcount : 1
     */

    private String id;
    private String uid;
    private String summary;
    private String hash;
    private String ctime;
    private String utime;
    private String deleted;
    private String praise;
    /**
     * code : 000000
     * msg : 获取律师风采成功
     * data : {"list":[{"id":"18","uid":"10058","postuid":"12900","postname":"华债网用户","nickname":"华债网用户","imgurl":"/Uploads/temp/20160513/f3cb17851826d3b4107c541fb43f0729/file_c.jpg","nid":"114","message":"好吃","addtime":"2016年05月13日","utype":"1"},{"id":"17","uid":"10058","postuid":"10101","postname":"肖筱","nickname":"肖筱","imgurl":"/stc/upload/10101/avatar/photo.jpg","nid":"114","message":"不错","addtime":"2016年05月07日","utype":"1"}],"count":"2","pages":1}
     */

    private CommentBean comment;
    private int imgcount;
    /**
     * id : 7453
     * uid : 10058
     * ctime : 1462602731
     * hash : 187fd84208e62620dc604a99f491262e
     * filename : p7818.jpg
     * filetype : .jpg
     * filesize : 6072
     * url : /Uploads/users/10058/20160507/aa7b83360403f82c70f86948a923eb17/file_c.jpg
     * b_url : /Uploads/users/10058/20160507/aa7b83360403f82c70f86948a923eb17/file_a.jpg
     * a_url : 
     * isimage : 1
     * thumb : 0
     * remote : 0
     * aid : 0
     */

    private List<ImgurlBean> imgurl;

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

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }

    public String getUtime() {
        return utime;
    }

    public void setUtime(String utime) {
        this.utime = utime;
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }

    public String getPraise() {
        return praise;
    }

    public void setPraise(String praise) {
        this.praise = praise;
    }

    public CommentBean getComment() {
        return comment;
    }

    public void setComment(CommentBean comment) {
        this.comment = comment;
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

    public static class CommentBean {
        private String code;
        private String msg;
        /**
         * list : [{"id":"18","uid":"10058","postuid":"12900","postname":"华债网用户","nickname":"华债网用户","imgurl":"/Uploads/temp/20160513/f3cb17851826d3b4107c541fb43f0729/file_c.jpg","nid":"114","message":"好吃","addtime":"2016年05月13日","utype":"1"},{"id":"17","uid":"10058","postuid":"10101","postname":"肖筱","nickname":"肖筱","imgurl":"/stc/upload/10101/avatar/photo.jpg","nid":"114","message":"不错","addtime":"2016年05月07日","utype":"1"}]
         * count : 2
         * pages : 1
         */

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
            private String count;
            private int pages;
            /**
             * id : 18
             * uid : 10058
             * postuid : 12900
             * postname : 华债网用户
             * nickname : 华债网用户
             * imgurl : /Uploads/temp/20160513/f3cb17851826d3b4107c541fb43f0729/file_c.jpg
             * nid : 114
             * message : 好吃
             * addtime : 2016年05月13日
             * utype : 1
             */

            private List<ListBean> list;

            public String getCount() {
                return count;
            }

            public void setCount(String count) {
                this.count = count;
            }

            public int getPages() {
                return pages;
            }

            public void setPages(int pages) {
                this.pages = pages;
            }

            public List<ListBean> getList() {
                return list;
            }

            public void setList(List<ListBean> list) {
                this.list = list;
            }

            public static class ListBean {
                private String id;
                private String uid;
                private String postuid;
                private String postname;
                private String nickname;
                private String imgurl;
                private String nid;
                private String message;
                private String addtime;
                private String utype;

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

                public String getPostuid() {
                    return postuid;
                }

                public void setPostuid(String postuid) {
                    this.postuid = postuid;
                }

                public String getPostname() {
                    return postname;
                }

                public void setPostname(String postname) {
                    this.postname = postname;
                }

                public String getNickname() {
                    return nickname;
                }

                public void setNickname(String nickname) {
                    this.nickname = nickname;
                }

                public String getImgurl() {
                    return imgurl;
                }

                public void setImgurl(String imgurl) {
                    this.imgurl = imgurl;
                }

                public String getNid() {
                    return nid;
                }

                public void setNid(String nid) {
                    this.nid = nid;
                }

                public String getMessage() {
                    return message;
                }

                public void setMessage(String message) {
                    this.message = message;
                }

                public String getAddtime() {
                    return addtime;
                }

                public void setAddtime(String addtime) {
                    this.addtime = addtime;
                }

                public String getUtype() {
                    return utype;
                }

                public void setUtype(String utype) {
                    this.utype = utype;
                }
            }
        }
    }

    public static class ImgurlBean {
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
