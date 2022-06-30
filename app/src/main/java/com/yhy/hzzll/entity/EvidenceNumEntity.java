package com.yhy.hzzll.entity;

public class EvidenceNumEntity {

    /**
     * code : 000000
     * msg : success
     * data : {"pic":0,"rec":35919,"vcr":4109,"web":0,"file":0}
     */

    private String code;
    private String msg;
    /**
     * pic : 0
     * rec : 35919
     * vcr : 4109
     * web : 0
     * file : 0
     */

    /**
     *  pic => 所剩图片存证次数,
         vcr  => 所剩录像存证时长(s),
         rec=> 所剩录音存证时长(s),
         web => 所剩网页存证次数,
         file => 所剩文件存证大小(kb),
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
        private int pic;
        private int rec;
        private int vcr;
        private int web;
        private int file;

        public int getPic() {
            return pic;
        }

        public void setPic(int pic) {
            this.pic = pic;
        }

        public int getRec() {
            return rec;
        }

        public void setRec(int rec) {
            this.rec = rec;
        }

        public int getVcr() {
            return vcr;
        }

        public void setVcr(int vcr) {
            this.vcr = vcr;
        }

        public int getWeb() {
            return web;
        }

        public void setWeb(int web) {
            this.web = web;
        }

        public int getFile() {
            return file;
        }

        public void setFile(int file) {
            this.file = file;
        }
    }
	
}
