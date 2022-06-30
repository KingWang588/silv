package com.yhy.hzzll.home;

import java.util.List;

/**
 * Created by chengying on 2017/7/12.
 */

public class H {


    /**
     * code : 0
     * message : 查询成功
     * data : {"order_info":{"consult_id":413,"order_no":"41918003210249975","amount":"50.00","private_order_no":"41918003210249975","order_status":"申请中","status_id":10,"nickname":"刘强武","mobile":"15623562356","type_name":"聘请律师","data_time":"2018-10-24 09:38:07","employ_type_name":"律师函","question":"也战略战略17页清者自清微热91岁3月1日3页是1页91岁","budget_amount":"1234.00000","is_public":1,"accid":"test_silv_32","base_region_id":{"simple_name":"","area_name":"","whole_name":""},"number":0,"is_my":1},"file_detail_info":[{"id":"1343","fileurl_full":"http://hzzll-test-private.oss-cn-shanghai.aliyuncs.com/UploadRoot/32/20181024/be6f744936ab44d6a45edf5bfe4febfd.png"},{"id":"1344","fileurl_full":"http://hzzll-test-private.oss-cn-shanghai.aliyuncs.com/UploadRoot/32/20181024/3f7278ab303929d2925bf3ab904936b9.png"},{"id":"1345","fileurl_full":"http://hzzll-test-private.oss-cn-shanghai.aliyuncs.com/UploadRoot/32/20181024/ea9f423ea3461fe307ba52b3e5fa984c.png"},{"id":"1346","fileurl_full":"http://hzzll-test-private.oss-cn-shanghai.aliyuncs.com/UploadRoot/32/20181024/8c3ade9dd7f560226284de6d401873b9.png"},{"id":"1347","fileurl_full":"http://hzzll-test-private.oss-cn-shanghai.aliyuncs.com/UploadRoot/32/20181024/11ee99f8c6612c466e3bb14319e8c3ab.png"}],"service_info":{"service_state":"2","application_info":null,"complaint":null,"evaluate":null}}
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
         * order_info : {"consult_id":413,"order_no":"41918003210249975","amount":"50.00","private_order_no":"41918003210249975","order_status":"申请中","status_id":10,"nickname":"刘强武","mobile":"15623562356","type_name":"聘请律师","data_time":"2018-10-24 09:38:07","employ_type_name":"律师函","question":"也战略战略17页清者自清微热91岁3月1日3页是1页91岁","budget_amount":"1234.00000","is_public":1,"accid":"test_silv_32","base_region_id":{"simple_name":"","area_name":"","whole_name":""},"number":0,"is_my":1}
         * file_detail_info : [{"id":"1343","fileurl_full":"http://hzzll-test-private.oss-cn-shanghai.aliyuncs.com/UploadRoot/32/20181024/be6f744936ab44d6a45edf5bfe4febfd.png"},{"id":"1344","fileurl_full":"http://hzzll-test-private.oss-cn-shanghai.aliyuncs.com/UploadRoot/32/20181024/3f7278ab303929d2925bf3ab904936b9.png"},{"id":"1345","fileurl_full":"http://hzzll-test-private.oss-cn-shanghai.aliyuncs.com/UploadRoot/32/20181024/ea9f423ea3461fe307ba52b3e5fa984c.png"},{"id":"1346","fileurl_full":"http://hzzll-test-private.oss-cn-shanghai.aliyuncs.com/UploadRoot/32/20181024/8c3ade9dd7f560226284de6d401873b9.png"},{"id":"1347","fileurl_full":"http://hzzll-test-private.oss-cn-shanghai.aliyuncs.com/UploadRoot/32/20181024/11ee99f8c6612c466e3bb14319e8c3ab.png"}]
         * service_info : {"service_state":"2","application_info":null,"complaint":null,"evaluate":null}
         */

        private OrderInfoBean order_info;
        private ServiceInfoBean service_info;
        private List<FileDetailInfoBean> file_detail_info;

        public OrderInfoBean getOrder_info() {
            return order_info;
        }

        public void setOrder_info(OrderInfoBean order_info) {
            this.order_info = order_info;
        }

        public ServiceInfoBean getService_info() {
            return service_info;
        }

        public void setService_info(ServiceInfoBean service_info) {
            this.service_info = service_info;
        }

        public List<FileDetailInfoBean> getFile_detail_info() {
            return file_detail_info;
        }

        public void setFile_detail_info(List<FileDetailInfoBean> file_detail_info) {
            this.file_detail_info = file_detail_info;
        }

        public static class OrderInfoBean {
            /**
             * consult_id : 413
             * order_no : 41918003210249975
             * amount : 50.00
             * private_order_no : 41918003210249975
             * order_status : 申请中
             * status_id : 10
             * nickname : 刘强武
             * mobile : 15623562356
             * type_name : 聘请律师
             * data_time : 2018-10-24 09:38:07
             * employ_type_name : 律师函
             * question : 也战略战略17页清者自清微热91岁3月1日3页是1页91岁
             * budget_amount : 1234.00000
             * is_public : 1
             * accid : test_silv_32
             * base_region_id : {"simple_name":"","area_name":"","whole_name":""}
             * number : 0
             * is_my : 1
             */

            private int consult_id;
            private String order_no;
            private String amount;
            private String private_order_no;
            private String order_status;
            private int status_id;
            private String nickname;
            private String mobile;
            private String type_name;
            private String data_time;
            private String employ_type_name;
            private String question;
            private String budget_amount;
            private int is_public;
            private String accid;
            private BaseRegionIdBean base_region_id;
            private int number;
            private int is_my;

            public int getConsult_id() {
                return consult_id;
            }

            public void setConsult_id(int consult_id) {
                this.consult_id = consult_id;
            }

            public String getOrder_no() {
                return order_no;
            }

            public void setOrder_no(String order_no) {
                this.order_no = order_no;
            }

            public String getAmount() {
                return amount;
            }

            public void setAmount(String amount) {
                this.amount = amount;
            }

            public String getPrivate_order_no() {
                return private_order_no;
            }

            public void setPrivate_order_no(String private_order_no) {
                this.private_order_no = private_order_no;
            }

            public String getOrder_status() {
                return order_status;
            }

            public void setOrder_status(String order_status) {
                this.order_status = order_status;
            }

            public int getStatus_id() {
                return status_id;
            }

            public void setStatus_id(int status_id) {
                this.status_id = status_id;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public String getType_name() {
                return type_name;
            }

            public void setType_name(String type_name) {
                this.type_name = type_name;
            }

            public String getData_time() {
                return data_time;
            }

            public void setData_time(String data_time) {
                this.data_time = data_time;
            }

            public String getEmploy_type_name() {
                return employ_type_name;
            }

            public void setEmploy_type_name(String employ_type_name) {
                this.employ_type_name = employ_type_name;
            }

            public String getQuestion() {
                return question;
            }

            public void setQuestion(String question) {
                this.question = question;
            }

            public String getBudget_amount() {
                return budget_amount;
            }

            public void setBudget_amount(String budget_amount) {
                this.budget_amount = budget_amount;
            }

            public int getIs_public() {
                return is_public;
            }

            public void setIs_public(int is_public) {
                this.is_public = is_public;
            }

            public String getAccid() {
                return accid;
            }

            public void setAccid(String accid) {
                this.accid = accid;
            }

            public BaseRegionIdBean getBase_region_id() {
                return base_region_id;
            }

            public void setBase_region_id(BaseRegionIdBean base_region_id) {
                this.base_region_id = base_region_id;
            }

            public int getNumber() {
                return number;
            }

            public void setNumber(int number) {
                this.number = number;
            }

            public int getIs_my() {
                return is_my;
            }

            public void setIs_my(int is_my) {
                this.is_my = is_my;
            }

            public static class BaseRegionIdBean {
                /**
                 * simple_name :
                 * area_name :
                 * whole_name :
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
        }

        public static class ServiceInfoBean {
            /**
             * service_state : 2
             * application_info : null
             * complaint : null
             * evaluate : null
             */

            private String service_state;
            private Object application_info;
            private Object complaint;
            private Object evaluate;

            public String getService_state() {
                return service_state;
            }

            public void setService_state(String service_state) {
                this.service_state = service_state;
            }

            public Object getApplication_info() {
                return application_info;
            }

            public void setApplication_info(Object application_info) {
                this.application_info = application_info;
            }

            public Object getComplaint() {
                return complaint;
            }

            public void setComplaint(Object complaint) {
                this.complaint = complaint;
            }

            public Object getEvaluate() {
                return evaluate;
            }

            public void setEvaluate(Object evaluate) {
                this.evaluate = evaluate;
            }
        }

        public static class FileDetailInfoBean {
            /**
             * id : 1343
             * fileurl_full : http://hzzll-test-private.oss-cn-shanghai.aliyuncs.com/UploadRoot/32/20181024/be6f744936ab44d6a45edf5bfe4febfd.png
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
