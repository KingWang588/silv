package com.yhy.hzzll.home.entity;

import com.yhy.hzzll.home.H;

import java.util.List;

public class ServiceDetailsEntity {


    /**
     * code : 0
     * message : 查询成功
     * data : {"order_info":{"order_no":"71818007008247759","amount":"38.00","order_status":"进行中","status_id":4,"nickname":"私律用户","mobile":"13000000004","type_name":"债权保","data_time":"2018-08-24 15:42:10","base_region_id":{"simple_name":"","area_name":"","whole_name":""},"number":0},"service_info":{"service_state":"9","select_other_status":"用户已选择其它律师，服务正在进行中","application_info":null,"complaint":null,"evaluate":null}}
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
         * order_info : {"order_no":"71818007008247759","amount":"38.00","order_status":"进行中","status_id":4,"nickname":"私律用户","mobile":"13000000004","type_name":"债权保","data_time":"2018-08-24 15:42:10","base_region_id":{"simple_name":"","area_name":"","whole_name":""},"number":0}
         * service_info : {"service_state":"9","select_other_status":"用户已选择其它律师，服务正在进行中","application_info":null,"complaint":null,"evaluate":null}
         */

        private OrderInfoBean order_info;
        private ServiceInfoBean service_info;
        private List<FileDetailInfoBean> file_detail_info;

        public List<FileDetailInfoBean> getFile_detail_info() {
            return file_detail_info;
        }

        public void setFile_detail_info(List<FileDetailInfoBean> file_detail_info) {
            this.file_detail_info = file_detail_info;
        }

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

        public static class OrderInfoBean {
            /**
             * order_no : 71818007008247759
             * amount : 38.00
             * order_status : 进行中
             * status_id : 4
             * nickname : 私律用户
             * mobile : 13000000004
             * type_name : 债权保
             * data_time : 2018-08-24 15:42:10
             * base_region_id : {"simple_name":"","area_name":"","whole_name":""}
             * number : 0
             */

            private String order_no;
            private String private_order_no;
            private String amount;
            private String order_status;
            private int status_id;
            private String nickname;
            private String mobile;
            private String question;
            private String type_name;
            private String data_time;
            private BaseRegionIdBean base_region_id;
            private int number;
            private String is_my;
            private String budget_amount;
            private String accid;
            private String employ_type_name;
            private String is_public;

            public String getBudget_amount() {
                return budget_amount;
            }

            public void setBudget_amount(String budget_amount) {
                this.budget_amount = budget_amount;
            }

            public String getIs_public() {
                return is_public;
            }

            public void setIs_public(String is_public) {
                this.is_public = is_public;
            }

            public String getAccid() {
                return accid;
            }

            public void setAccid(String accid) {
                this.accid = accid;
            }

            public String getEmploy_type_name() {
                return employ_type_name;
            }

            public void setEmploy_type_name(String employ_type_name) {
                this.employ_type_name = employ_type_name;
            }

            public String getIs_my() {
                return is_my;
            }

            public void setIs_my(String is_my) {
                this.is_my = is_my;
            }

            public String getPrivate_order_no() {
                return private_order_no;
            }

            public void setPrivate_order_no(String private_order_no) {
                this.private_order_no = private_order_no;
            }

            public String getQuestion() {
                return question;
            }

            public void setQuestion(String question) {
                this.question = question;
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
             * select_other_status :
             * application_info : {"id":5,"layer_describe":"算我求你了搜过舒服舒服七对，特色他，地，福特，谷底，忒对，户的他却社区独缺特区师父父佩服佩服确实肤色土特却热天却","amount":"5000.00000","created_at":"-0001-11-30 00:00:00","truename":"刘强武","base_region_id":{"simple_name":"高邮","area_name":"高邮市","whole_name":"江苏省-扬州市"},"avatar":"https://tapi.silvzone.com/img/avatar.jpg","data_time":"/11/3"}
             * complaint : null
             * evaluate : null
             */

            private String service_state;
            private String reason;
            private String select_other_status;
            private List<ApplicationInfoBean> application_info;
            private ComplaintBean complaint;
            private ApplicationInfoBean.EvaluateBean evaluate;

            public String getService_state() {
                return service_state;
            }

            public void setService_state(String service_state) {
                this.service_state = service_state;
            }

            public String getReason() {
                return reason;
            }

            public void setReason(String reason) {
                this.reason = reason;
            }

            public String getSelect_other_status() {
                return select_other_status;
            }

            public void setSelect_other_status(String select_other_status) {
                this.select_other_status = select_other_status;
            }

            public List<ApplicationInfoBean> getApplication_info() {
                return application_info;
            }

            public void setApplication_info(List<ApplicationInfoBean> application_info) {
                this.application_info = application_info;
            }

            public ComplaintBean getComplaint() {
                return complaint;
            }

            public void setComplaint(ComplaintBean complaint) {
                this.complaint = complaint;
            }

            public ApplicationInfoBean.EvaluateBean getEvaluate() {
                return evaluate;
            }

            public void setEvaluate(ApplicationInfoBean.EvaluateBean evaluate) {
                this.evaluate = evaluate;
            }



            public static class ComplaintBean {

                /**
                 * status_id : 2
                 * handle_result : 1
                 * handle_opinion : 爱我的搬家啊问代表
                 * created_at : 2018-09-03 14:34:59
                 * updated_at : 2018-09-03 14:47:01
                 */

                private int status_id;
                private int handle_result;
                private String handle_opinion;
                private String created_at;
                private String updated_at;

                public int getStatus_id() {
                    return status_id;
                }

                public void setStatus_id(int status_id) {
                    this.status_id = status_id;
                }

                public int getHandle_result() {
                    return handle_result;
                }

                public void setHandle_result(int handle_result) {
                    this.handle_result = handle_result;
                }

                public String getHandle_opinion() {
                    return handle_opinion;
                }

                public void setHandle_opinion(String handle_opinion) {
                    this.handle_opinion = handle_opinion;
                }

                public String getCreated_at() {
                    return created_at;
                }

                public void setCreated_at(String created_at) {
                    this.created_at = created_at;
                }

                public String getUpdated_at() {
                    return updated_at;
                }

                public void setUpdated_at(String updated_at) {
                    this.updated_at = updated_at;
                }
            }





            public static class ApplicationInfoBean{

                /**
                 * id : 19
                 * advantage : 我是虎哥虎哥就是牛逼
                 * written_documents : [{"id":"1208","filename":"个人数据.xlsx","fileurl_full":"http://hzzll-test-private.oss-cn-shanghai.aliyuncs.com/UploadRoot/28/20180830/82ddcc7953f32b9c3b0817325bfb1c1f.xlsx","created_time":"2018-08-30 14:38:11"},{"id":"1209","filename":"其它类型用户列表.xls","fileurl_full":"http://hzzll-test-private.oss-cn-shanghai.aliyuncs.com/UploadRoot/28/20180830/cfa1273ed4bd1bbc232a06fdf719cd27.xls","created_time":"2018-08-30 14:38:12"},{"id":"1223","filename":"1523935055818.png","fileurl_full":"http://hzzll-test-private.oss-cn-shanghai.aliyuncs.com/UploadRoot/32/20180831/4f1efda2441911360f71ceec1a2af630.png","created_time":"2018-08-31 14:57:03"},{"id":"1224","filename":"1524119935716.png","fileurl_full":"http://hzzll-test-private.oss-cn-shanghai.aliyuncs.com/UploadRoot/32/20180831/b829b3499a23a9cbdd0e7bc6251ab1ef.png","created_time":"2018-08-31 14:58:14"}]
                 * legal_opinion : [{"id":"1226","filename":"我的接办案-指定聘请我的.jpg","fileurl_full":"http://hzzll-test-private.oss-cn-shanghai.aliyuncs.com/UploadRoot/32/20180831/65bb227b53cd72930ab46812ea9cff11.jpg","created_time":"2018-08-31 15:01:01"}]
                 * is_select : 0
                 * created_at : 2018-08-29 09:24:23
                 * avatar : http://hzzll-test-private.oss-cn-shanghai.aliyuncs.com/UploadRoot/32/20180611/bc6ee2c4895be0594fc51abc33103aef.jpg
                 * truename : 刘强武律师
                 * base_region_id : {"simple_name":"高邮","area_name":"高邮市","whole_name":"江苏省-扬州市"}
                 * lawyer_secpical : 婚姻家庭,债权债务,合同纠纷
                 * mobile : 15629037629
                 * email :
                 */

                private int id;
                private String advantage;
                private int is_select;
                private String created_at;
                private String amount;
                private String avatar;
                private String truename;
                private BaseRegionIdBean base_region_id;
                private String lawyer_secpical;
                private String mobile;
                private String email;
                private String lawyer_id;
                private String accid;
                private List<WrittenDocumentsBean> written_documents;
                private List<LegalOpinionBean> legal_opinion;

                public String getAccid() {
                    return accid;
                }

                public String getLawyer_id() {
                    return lawyer_id;
                }

                public void setLawyer_id(String lawyer_id) {
                    this.lawyer_id = lawyer_id;
                }

                public void setAccid(String accid) {
                    this.accid = accid;
                }

                public String getAmount() {
                    return amount;
                }

                public void setAmount(String amount) {
                    this.amount = amount;
                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getAdvantage() {
                    return advantage;
                }

                public void setAdvantage(String advantage) {
                    this.advantage = advantage;
                }

                public int getIs_select() {
                    return is_select;
                }

                public void setIs_select(int is_select) {
                    this.is_select = is_select;
                }

                public String getCreated_at() {
                    return created_at;
                }

                public void setCreated_at(String created_at) {
                    this.created_at = created_at;
                }

                public String getAvatar() {
                    return avatar;
                }

                public void setAvatar(String avatar) {
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

                public String getLawyer_secpical() {
                    return lawyer_secpical;
                }

                public void setLawyer_secpical(String lawyer_secpical) {
                    this.lawyer_secpical = lawyer_secpical;
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

                public List<WrittenDocumentsBean> getWritten_documents() {
                    return written_documents;
                }

                public void setWritten_documents(List<WrittenDocumentsBean> written_documents) {
                    this.written_documents = written_documents;
                }

                public List<LegalOpinionBean> getLegal_opinion() {
                    return legal_opinion;
                }

                public void setLegal_opinion(List<LegalOpinionBean> legal_opinion) {
                    this.legal_opinion = legal_opinion;
                }

                public static class BaseRegionIdBean {
                    /**
                     * simple_name : 高邮
                     * area_name : 高邮市
                     * whole_name : 江苏省-扬州市
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

                public static class WrittenDocumentsBean {
                    /**
                     * id : 1208
                     * filename : 个人数据.xlsx
                     * fileurl_full : http://hzzll-test-private.oss-cn-shanghai.aliyuncs.com/UploadRoot/28/20180830/82ddcc7953f32b9c3b0817325bfb1c1f.xlsx
                     * created_time : 2018-08-30 14:38:11
                     */

                    private String id;
                    private String filename;
                    private String fileurl_full;
                    private String created_time;
                    private String extension;

                    public String getExtension() {
                        return extension;
                    }

                    public void setExtension(String extension) {
                        this.extension = extension;
                    }

                    public String getId() {
                        return id;
                    }

                    public void setId(String id) {
                        this.id = id;
                    }

                    public String getFilename() {
                        return filename;
                    }

                    public void setFilename(String filename) {
                        this.filename = filename;
                    }

                    public String getFileurl_full() {
                        return fileurl_full;
                    }

                    public void setFileurl_full(String fileurl_full) {
                        this.fileurl_full = fileurl_full;
                    }

                    public String getCreated_time() {
                        return created_time;
                    }

                    public void setCreated_time(String created_time) {
                        this.created_time = created_time;
                    }
                }


                public static class EvaluateBean{

                    /**
                     * id : 8
                     * reply_id : 33
                     * users_id : 32
                     * evaluated_id : 68
                     * content : 就可以现在怎么弄弄某些图默默陆续突突突
                     * start_rate : 10
                     * type_id : 6
                     * is_delete : 0
                     * created_at : 2018-09-12 09:40:36
                     * updated_at : 2018-09-12 10:34:17
                     */

                    private int id;
                    private int reply_id;
                    private int users_id;
                    private int evaluated_id;
                    private String content;
                    private int start_rate;
                    private int type_id;
                    private int is_delete;
                    private String created_at;
                    private String updated_at;

                    public int getId() {
                        return id;
                    }

                    public void setId(int id) {
                        this.id = id;
                    }

                    public int getReply_id() {
                        return reply_id;
                    }

                    public void setReply_id(int reply_id) {
                        this.reply_id = reply_id;
                    }

                    public int getUsers_id() {
                        return users_id;
                    }

                    public void setUsers_id(int users_id) {
                        this.users_id = users_id;
                    }

                    public int getEvaluated_id() {
                        return evaluated_id;
                    }

                    public void setEvaluated_id(int evaluated_id) {
                        this.evaluated_id = evaluated_id;
                    }

                    public String getContent() {
                        return content;
                    }

                    public void setContent(String content) {
                        this.content = content;
                    }

                    public int getStart_rate() {
                        return start_rate;
                    }

                    public void setStart_rate(int start_rate) {
                        this.start_rate = start_rate;
                    }

                    public int getType_id() {
                        return type_id;
                    }

                    public void setType_id(int type_id) {
                        this.type_id = type_id;
                    }

                    public int getIs_delete() {
                        return is_delete;
                    }

                    public void setIs_delete(int is_delete) {
                        this.is_delete = is_delete;
                    }

                    public String getCreated_at() {
                        return created_at;
                    }

                    public void setCreated_at(String created_at) {
                        this.created_at = created_at;
                    }

                    public String getUpdated_at() {
                        return updated_at;
                    }

                    public void setUpdated_at(String updated_at) {
                        this.updated_at = updated_at;
                    }
                }


                public static class LegalOpinionBean {
                    /**
                     * id : 1226
                     * filename : 我的接办案-指定聘请我的.jpg
                     * fileurl_full : http://hzzll-test-private.oss-cn-shanghai.aliyuncs.com/UploadRoot/32/20180831/65bb227b53cd72930ab46812ea9cff11.jpg
                     * created_time : 2018-08-31 15:01:01
                     */

                    private String id;
                    private String filename;
                    private String fileurl_full;
                    private String created_time;
                    private String extension;

                    public String getExtension() {
                        return extension;
                    }

                    public void setExtension(String extension) {
                        this.extension = extension;
                    }

                    public String getId() {
                        return id;
                    }

                    public void setId(String id) {
                        this.id = id;
                    }

                    public String getFilename() {
                        return filename;
                    }

                    public void setFilename(String filename) {
                        this.filename = filename;
                    }

                    public String getFileurl_full() {
                        return fileurl_full;
                    }

                    public void setFileurl_full(String fileurl_full) {
                        this.fileurl_full = fileurl_full;
                    }

                    public String getCreated_time() {
                        return created_time;
                    }

                    public void setCreated_time(String created_time) {
                        this.created_time = created_time;
                    }
                }
            }
        }
    }
}
