package com.yhy.hzzll.framework;

import android.os.Environment;

public class MyData {


    /**
     * 正式环境
     */

    public static String IP = "";

    public static String MOBILE_URL = "https://m.silvzone.com/";
    public static String IP1 = "https://oldapi.silvzone.com";
    public static String IPs = "https://prepare.silvzone.com";
    public static String SILVZONE = "https://api.silvzone.com/";
    public static String PAN_ADDRESS = "https://pan.silvzone.com/#/user/login";

//    public static String SILVZONE = "http://silvapi.hzzll.com/";
//    public static String MOBILE_URL = "http://testmobile.hzzll.com/";
//    public static String IP1 = "http://oldapi.hzzll.com";
//    public static String IPs = "http://prepare.hzzll.com";

    /**
     * 测试站
     */

//    public static String SILVZONE = "https://tapi.silvzone.com/";
//    public static String MOBILE_URL = "https://tm.silvzone.com/";
//    public static String IP1 = "https://tthink.silvzone.com/";
//    public static String IPs = "https://tlarav.silvzone.com/";
//    public static String PAN_ADDRESS = "https://tpan.silvzone.com/#/user/login";


    public static String URL = IP1 + "/api.php/Open";
    /**
     * banner
     */
    public static String BANNERHOME = IPs + "/imgs/banner_list/home";
    /**
     * banner
     */
    public static String BANNEROFFICE = IPs + "/imgs/banner_list/office";
    /**
     * banner evidence
     */
    public static String BANNEREVIDENCE = IPs + "/imgs/banner_list/evidence";


    // /** 录音文件网络路径 */
    // public static String RECORDURL =
    // "http://hzzll-api-hangzhou.oss-cn-hangzhou.aliyuncs.com";

    // /** 录音文件网络路径--测试 */
    // public static String RECORDURL =
    // "http://hzzll-test-private.oss-cn-shanghai.aliyuncs.com";

    // 阿里OSS - 上传音频文件
//	public static String ALIOSSREC = "http://prepare.hzzll.com/alioss/rec";
    public static String ALIOSSREC = IPs + "/alioss/rec";


    // 阿里OSS - 上传文件
//	public static String ALIOSSUPFILE = "http://prepare.hzzll.com/alioss/upFile";
    public static String ALIOSSUPFILE = IPs + "/alioss/upFile";
    /**
     * 获取所有地区
     */
    public static String GETALLAREA = IPs + "/places/get_all";

    // 图片验证码===================================================
    public static String CREATEVALIDATECODE = URL + "/Index/createValidateCode";
    /**
     * 检测图片验证码是否正确
     */
    public static String CHECKVALIDATECODE = URL + "/Index/checkValidateCode";

    // 注册 =============================================
//	public static String REGISTER = URL + "/Member/register";
    /** 注册时验证码 */
//	public static String GETREGISTERSMSCODE = URL + "/Msg/getRegisterSmsCode";
    /**
     * 检测注册验证码是否通过
     */
    public static String CHECKVALIDATA = URL + "/Msg/checkValidate";
    /**
     * 全局状态
     */
    public static String GETALLSTATUS = URL + "/Status/getAllStatusType";

    // 一级分类=======================================================
    public static String PARENTCATALOG = IPs + "/common/parent_catalog";
    // 二级分类=======================================================
    public static String CHILDRENCATALOG = IPs + "/common/children_catalog";
    // 根据一级分类ID获取二级分类=======================================================
    public static String CHILDRENCATALOGBYID = IPs + "/common/get_catalog_children_by_pid";

    // 登录 =============================================
    public static String LOGIN = URL + "/Login/lin";
    /**
     * 创建注册用hash值
     */
    public static String INDEXCREATEHASHCODE = URL + "/Index/createHashCode";
    /**
     * 获取当前登录用户的信息
     */
    public static String LOGININFO = URL + "/Member/loginInfo";
    /**
     * 获取登录错误次数
     */
    public static String LOGINERROR = URL + "/Login/login_error";
    /**
     * 退出登录
     */
    public static String LOGINOUT = URL + "/Login/lout";
    /**
     * 根据ID获取用户基本信息
     */
    public static String BASEINFO = URL + "/Member/getBaseInfoById";

    // 个人中心类=============================================
    /**
     * 学术列表
     */
    public static String ARTICLELISTS = URL + "/Article/lists";
    /**
     * 学术风采和成功案例
     */
    public static String ARTICLEWORK = URL + "/ArticleWork/top";

    /**
     * 学术风采和成功案例
     */
    public static String CASEDETIAL = URL + "/ArticleWork/detail";


    // 华债互动=============================================
    /**
     * 互动列表
     */
    public static String INTERACTLIST = URL + "/InteractApply/lists";
    /**
     * 获取省
     */
    public static String PLACESPROVINCE = IPs + "/places/province";
    /**
     * 根据省 获取市
     */
    public static String PLACESGETCITYBYPROVINCE = IPs + "/places/get_city_by_pid";
    /**
     * 获取区
     */
    public static String PLACESGETAREABYCITY = URL + "/Places/getAreaByCity";
    /**
     * 获取所有地域
     */
    public static String PLACESALL = URL + "/Places/allPlaces_nosecret";


    // 律师=============================================


    // 上传=============================================
    /**
     * 文件上传
     */
    public static String FILEUPLOAD = URL + "/File/upload";

    /**
     * 文件删除  图片删除
     */
    public static String FILEDELETE = URL + "/File/delFile";


    // 服务商店===============================================
    // public static String COMPANYSHOP = URL + "/Company/shops";
    public static String COMPANYSHOP = MOBILE_URL + "/n/0914/c.html#/";
    // 律师协作===============================================
    /**
     * 律师协作列表
     */
    public static String LAWERCOOPLIST = URL + "/Lawercoop/lists";
    /**
     * 律师协作详情
     */
    public static String LAWERCOOPINFO = URL + "/Lawercoop/infos";
    /**
     * 申请律师协作(需登录)
     */
    public static String APPLYCOLLABORATION = URL + "/Collaboration/applyCollaboration";
    /**
     * 申请放弃协作(需登录)
     */
    public static String APPLYGIVEUP = URL + "/Collaboration/applyGiveUp";
    /**
     * 选择某个律师协作(需登录)
     */
    public static String AGREECOLLABORATION = URL + "/Collaboration/agreeCollaboration";
    /**
     * 申请客服介入
     */
    public static String SERVICEIN = URL + "/Collaboration/serviceJoin";
    /**
     * 允许放弃协作
     */
    public static String ALLOWEDGIVEUP = URL + "/Collaboration/allowedGiveUp";
    /**
     * 不允许放弃协作
     */
    public static String NOTALLOWEDGIVEUP = URL + "/Collaboration/notAllowedGiveUp";
    /**
     * 同意客服裁定结果
     */
    public static String AGREESERVICE = URL + "/Collaboration/agreeService";
    /**
     * 不同意客服裁定结果
     */
    public static String DISAGREESERVICE = URL + "/Collaboration/disagreeService";
    /**
     * 协作方提交确认
     */
    public static String SURETASK = URL + "/Collaboration/sureTask";
    /**
     * 关闭协作
     */
    public static String CLOSETASK = URL + "/Collaboration/closeTask";
    /**
     * 关闭后重新开启
     */
    public static String CLOSERESTART = URL + "/Collaboration/closeReStart";
    /**
     * 延长协作时间
     */
    public static String ADDTASKTIME = URL + "/Collaboration/addTaskTime";
    /**
     * 发布者申请重新执行
     */
    public static String APPLYRELOAD = URL + "/Collaboration/applyReload";
    /**
     * 到期状态下重新开启
     */
    public static String DUERESTART = URL + "/Collaboration/dueReStart";
    /**
     * 发布律师协作
     */
    public static String ADDLAWYERCOOP = URL + "/Lawercoop/addLawyercoop";
    /**
     * 增加酬金
     */
    public static String ADDREWARD = IPs + "/collaboration/add_reward";
    /**
     * 托管
     */
    public static String ASSURE = IPs + "/collaboration/assure";
    /**
     * 确认完成
     */
    public static String COMPLETION = IPs + "/collaboration/completion";

    // 支付---创建charge对象
    public final static String CHARGE = IPs + "/pay/charge";

    // 提问详情=====================================================
    /**
     * 所有提问列表
     */
    public static String ASKLIST = URL + "/Ask/lists";
    /**
     * 提问列表筛选
     */
    public static String ASKSCREEN = URL + "/Ask/screen";
    /**
     * 我回答的提问列表
     */
    public static String ALIST = URL + "/Ask/alist";
    /**
     * 我旁听/旁观的提问列表
     */
    public static String VLIST = URL + "/Ask/vlist";
    /**
     * 提问详情
     */
    public static String ASKDETAIL = URL + "/Ask/detail";
    /**
     * 根据ID查询回复信息
     */
    public static String ANSWERBYID = URL + "/Answer/answerbyid";

    /**
     * 解答疑一元查看
     */
    public static String CREATE = URL + "/AskReward/create";
    /**
     * 解答疑回复
     */
    public static String ADD = URL + "/Answer/add";
    /**
     * 解答疑采纳
     */
    public static String ISHELP = URL + "/Answer/ishelp";
    /**
     * 解答疑删除回复
     */
    public static String ANSWERDEL = URL + "/Answer/del";
    /**
     * 解答疑发表提问
     */
    public static String ASKCREATE = URL + "/Ask/create";
    /**
     * 解答疑发表评价
     */
    public static String ASKCOMMENTCREATE = URL + "/Askcomment/create";
    /**
     * 解答疑评价列表
     */
    public static String ASKCOMMENTLISTS = URL + "/Askcomment/lists";
    /**
     * 解答疑打赏
     */
    public static String ASKREWARDCREATE = URL + "/AskReward/create";
    /**
     * 解答疑点赞列表
     */
    public static String ASKRERUSERPRAISE = URL + "/Answer/userPraise";
    /**
     * 解答疑点赞
     */
    public static String ADDPRAISE = URL + "/Answer/addpraise";
    /**
     * 解答疑删除提问
     */
    public static String ASKDEL = URL + "/Ask/del";

    // 根据hash 获取图片
    /**
     * 附件获取
     */
    public static String GETFILEBYHASH = URL + "/File/getFileByHash";

    /**
     * oss 访客（未登录、注册）存储的路径
     */
    public static String OSSTEMP = "temp/";
    /**
     * oss 用户存储的路径
     */
    public static String OSSUSER = "users/";

    /**
     * 图片存储的路径
     */
    public static String PROFILE = "/profile";

    // /** 阿里云服务器域名--正式 */
    // public static String OSSURL =
    // "http://hzzll-api-private.oss-cn-shanghai.aliyuncs.com/";

    /**
     * 阿里云服务器域名--测试
     */
    public static String OSSURL = "http://hzzll-test-private.oss-cn-shanghai.aliyuncs.com/";

    /**
     * 债权转让
     */
    // public static String TRANSFER_LIST = URL + "/Transfer/mineList";
    public static String TRANSFER_LIST = URL + "/n/0914/a.html#/";
    /**
     * 债权融资
     */
    public static String FINANCING_LIST = MOBILE_URL + "/Financing/mineList";
    /**
     * 债权易物
     */
    public static String EXCHANGE_LIST = URL + "/Exchange/mineList";
    /**
     * 我的咨询
     */
    public static String ASK_LIST = URL + "/Ask/list";
    /**
     * 律师协作 - 我发布的律师协作(需登录)
     */
    public static String LAWERCOOP_LIST = URL + "/Lawercoop/minepublishlists";
    /**
     * 律师协作 - 我参与的律师协作(需登录)
     */
    public static String JOINLISTS = URL + "/Lawercoop/minejoinlists";
    /**
     * 债务危机求助 - 我的列表
     */
    public static String HELP_LIST = URL + "/Help/mineList";
    /**
     * 资产线索悬赏 - 我的列表
     */
    public static String REWARD_LIST = URL + "/Reward/mineList";
    /**
     * 28元服务律师
     */
    public static String SERVICELAWYER_LIST = URL + "/TelephoneLawyer/getServiceLawyerList";

    /**
     * 优惠券 - 获取 当前 登录用户的优惠券
     */
    public static String COUPON_LIST = URL + "/Coupon/getCouponByUid";

    /**
     * 律师业务 - 律师用户认证(需登录)
     */
    public static String LAWER_CERTIFICATION = URL + "/Lawer/lawerCertification";
    /**
     * 律师业务 - 认证律师手机号码(需登录)
     */
    public static String LAWYER_MOBILE_CERTIFICATION = URL + "/Lawer/lawyerMobileCertification";
    /**
     * 律师业务 - 认证律师邮箱(需登录)
     */
    public static String LAWYER_EMAIL_CERTIFICATION = URL + "/Lawer/lawyerEmailCertification";
    /**
     * 律师业务 - 修改律师基本信息(需登录)
     */
//	public static String LAWER_EDIT = URL + "/Lawer/lawerEdit";
    public static String LAWER_EDIT = URL + "/Member/personEditInfo";
    /**
     * 律师业务 - 修改律师手机号码(需登录)
     */
    public static String LAWYER_CHANGE_MOBILE = URL + "/Lawer/lawyerChangeMobile";
    /**
     * 律师业务 - 修改律师邮箱地址(需登录)
     */
    public static String LAWYER_CHANGE_EMAIL = URL + "/Lawer/lawyerChangeEmail";
    /**
     * 消息类 - 获取认证信息邮件验证码
     */
    public static String GET_CERTIFICATION_EMAIL_CODE = URL + "/Msg/getCertificationEmailCode";
    /**
     * 消息类 - 获取找回密码验证码
     */
    public static String GET_REPWD_MOBILE_CODE = URL + "/Msg/getRePswdMobileCode";
    /**
     * 用户中心 - 设置安全密码
     */
    public static String SETPAYWORD = URL + "/Member/setPayword";
    /**
     * 用户中心 - 设置密码
     */
    public static String SETPASSWORD = URL + "/Member/setPassword";
    /**
     * 我的足迹
     */
    public static String FOOTPRINT_LISTS = URL + "/Footprint/lists";
    /**
     * 收藏关注 - 获取收藏
     */
    public static String COLLECTIONG_COUNT = URL + "/Collectiong/query";
    /**
     * 收藏关注 - 获取点击量
     */
    public static String BROWSE_COUNT = URL + "/Collectiong/gethits";
    /**
     * 收藏关注 - 是否已收藏
     */
    public static String IS_COLLECT = URL + "/Collectiong/hasCollected";
    /**
     * 收藏关注 - 收藏/取消收藏
     */
    public static String COLLECT_OR_UNCOLLECT = URL + "/Collectiong/trigger";
    /**
     * 律师业务 - 办公室首页数据
     */
    public static String OFFICE_HOME_PUSH = URL + "/Lawer/officeHomePush";
    /**
     * 获取咨询记录列表
     */
    public static String MY_CONSULT_LIST = URL + "/Telephone/getTelephoneList";
    /**
     * 案件管理 - 案件列表
     */
    public static String LAWCASE_LIST = URL + "/Lawcase/list";
    /**
     * 网站反馈 - 提交反馈信息
     */
    public static String FEEDBACK = URL + "/Feedback/send";
    /**
     * 收藏关注 - 谁关注了我
     */
    public static String COLLECTME = URL + "/Collectiong/collectMe";
    /**
     * 案件管理 - 获取案件详情
     */
    public static String LAWCASE_DETAIL = URL + "/Lawcase/info";
    /**
     * 系统分类 - 获取所有分类
     */
    public static String ALL_CATALOG = IPs + "/common/all_catalog/";
    /**
     * 当事人管理 - 客户列表
     */
    public static String LAWCUSTOMER_LIST = URL + "/Lawcustomer/list";
    /**
     * 当事人管理 - 创建关联当事人
     */
    public static String CREATE_RELEVANCE = URL + "/Lawcustomer/createRelevance";
    /**
     * 当事人管理 - 取消关联当事人
     */
    public static String DEL_RELEVANCE = URL + "/Lawcustomer/delRelevance";
    /**
     * 当事人管理 - 创建客户
     */
    public static String CREATE_LAWCUSTOMER = URL + "/Lawcustomer/create";
    /**
     * 当事人管理 - 创建客户
     */
    public static String CHECK_EXISTS = URL + "/Member/checkExists";
    /**
     * 当事人管理 - 创建客户
     */
    public static String DEL_LAWCASE = URL + "/Lawcase/del";
    /**
     * 案件管理 - 获取根据案件信息返回案件进度下拉列表
     */
    public static String GETCATALOG = URL + "/Lawcase/getCatalog";
    /**
     * 消息类 - 邀请注册
     */
    public static String INVITATION_REGISTER = URL + "/Msg/invitation_register";
    /**
     * 系统分类 - 根据父Id获取二级分类
     */
    public static String GET_CATALOG_CHILDREN_BY_PID = IPs + "/common/get_catalog_children_by_pid/";
    /**
     * 案件管理 - 创建案件
     */
    public static String CREATE_LAWCASE = URL + "/Lawcase/create";
    /**
     * 案件编辑
     */
    public static String EDIT_LAWCASE = URL + "/Lawcase/edit";
    /**
     * 案件进度管理 - 创建案件进度
     */
    public static String LAWCASE_PROCESS = URL + "/LawCaseProcess/create";
    /**
     * 案件进度管理 - 获取案件详情以及进度列表
     */
    public static String LAWCASE_PROCESS_LIST = URL + "/LawCaseProcess/info_list";
    /**
     * 案件进度管理 - 删除案件进度
     */
    public static String DELETE_LAWCASEPROCESS = URL + "/LawCaseProcess/del";
    /**
     * 充值 - 发起充值请求
     */
    public static String RECHARGE = IPs + "/pay/recharge";
    /*
     * 当事人管理 - 删除客户
     */
    public static String DELETE_LAWCUSTOMER = URL + "/Lawcustomer/del";
    /**
     * 当事人管理 - 获取客户信息
     */
    public static String GET_LAWCUSTOMER = URL + "/Lawcustomer/info";

    /**
     * 当事人管理 - 修改客户信息
     */
    public static String EDIT_LAWCUSTOMER = URL + "/Lawcustomer/edit";
    /**
     * 服务报价管理 - 报价列表
     */
    public static String LAWSERVICES_LIST = URL + "/Lawservices/list";
    /**
     * 案件进度管理 - 案件进度修改 更新案件状态相关字段
     */
    public static String LAWCASEPROCESS_CHANGESTATUS = URL + "/LawCaseProcess/changeStatus";
    /**
     * 服务报价 - 服务报价列表
     */
    public static String SERVICES_LIST = IPs + "/comunicate/services_list/";
    /**
     * 服务报价 - 创建服务报价
     */
    public static String SERVICES_STORE = IPs + "/comunicate/services_store";
    /**
     * 创建服务报价类别
     */
    public static String SERVICES_TYPE_LIST = IPs + "/comunicate/services_type_list";
    /**
     * 修改服务报价类别
     */
    public static String SERVICESUPDATE = IPs + "/comunicate/services_update";
    /**
     * 删除服务报价
     */
    public static String SERVICESUPDELETE = IPs + "/comunicate/services_destroy";
    /**
     * 用户中心 - 根据用户ID获取用户信息--部分
     */
    public static String GET_INFOBYID = URL + "/Member/getInfoById";
    /**
     * 律师业务 - 律师风采
     */
    public static String OFFICEHOMEALBUMS = URL + "/Lawer/officeHomealbums";
    /**
     * 用户中心 - 点赞
     */
    public static String MEMBER_ADDPRAISE = URL + "/Member/addPraise";
    /**
     * 律师风采评论 - 创建律师风采评论
     */
    public static String MEMBERALBUMSCOMMENT = URL + "/MemberAlbumsComment/create";
    /**
     * 律师风采 - 删除律师风采
     */
    public static String DELETE_MEMBERALBUMS = URL + "/MemberAlbums/del";

    /**
     * 我的学术 - 删除我的学术
     */
    public static String dddd = URL + "/Article/del";
    /**
     * 用户中心 - 检测支付密码是否正确
     */
    public static String CHECK_PAYWORD = URL + "/Member/checkPayword";
    /**
     * IM即时通讯-日志记录 - 与律师对话
     */
    public static String IMLOG = URL + "/Imlog/madd";
    /**
     * 用户中心 - 忘记密码,检测用户名,手机号,邮箱是否存在
     */
    public static String CHECK_USERNAME = URL + "/Member/checkUsername";
    /**
     * 用户中心 - 检测验证码是否正确并设置验证码
     */
    public static String CHECK_VALIDATE_SETPASSWORD = URL + "/Member/checkValidateSetPassword";
    /**
     * 当事人管理 - 获取关联案件
     */
    public static String GET_CASE = URL + "/Lawcustomer/getCase";
    /**
     * IM即时通讯-日志记录 - 发给我的所有新信息列表(消息列表)
     */
    public static String CHAT_LIST = URL + "/Imlog/chatlist";
    /**
     * IM即时通讯-日志记录 - 与律师对话
     */
    public static String MSG_ADD = URL + "/Imlog/madd";
    /**
     * IM即时通讯-日志记录 - 获取聊天历史记录
     */
    public static String GET_MSG_LIST = URL + "/Imlog/getList";
    /**
     * IM即时通讯-日志记录 - 更新消息时间戳
     */
    public static String MSGUPDATE = URL + "/Imlog/update";

    // /** 关注的律师 *//** 订单评论 - 获取咨询订单列表 */
    public static String GET_ORDER_DETAIL = IPs + "/orders/show_by_no/";
    // public static String COLLECTINGLAWYER = URL + "/Collectiong/lawers";
    // /** 收藏的信息 */
    // public static String COLLECTINGINFO = URL + "/Collectiong/infos";
    // /** 收藏的服务 */
    // public static String COLLECTINGCGOODS = URL + "/Collectiong/cgoods";
    // /** 收藏的服务商家 */
    // public static String COLLECTINGCSHOPS = URL + "/Collectiong/cshops";
    // /**收藏的文章*/
    // public static String COLLECTINGLAWYER =URL+ "/Collectiong/lawers";
    /**
     * 删除消息
     */
    public static String MYLOGDEL = URL + "/Imlog/del";
    /**
     * 根据用户id获取相关金额
     */
    public static String GETMONEY = IPs + "/user/get_money/";

    /**
     * 发表律师风采
     */
    public static String MEMBERALBUMCREATE = URL + "/MemberAlbums/create";
    /**
     * 发表成功案例
     */
    public static String WORKCREATE = URL + "/Work/create";


    // /** 律师风采评论 - 创建律师风采评论 */
    // public static String MEMBERALBUMSCOMMENT = URL
    // + "/MemberAlbumsComment/create";

    /**
     * 增加点击量/浏览量
     */
    public static String ADDHITS = URL + "/Member/addHis";
    /**
     * 关注的律师
     */
    public static String COLLECTINGLAWYER = URL + "/Collectiong/lawers";
    /**
     * 收藏的信息
     */
    public static String COLLECTINGINFO = URL + "/Collectiong/infos";
    /**
     * 收藏的服务
     */
    public static String COLLECTINGCGOODS = URL + "/Collectiong/cgoods";
    /**
     * 收藏的服务商家
     */
    public static String COLLECTINGCSHOPS = URL + "/Collectiong/cshops";
    // /**收藏的文章*/
    // public static String COLLECTINGLAWYER =URL+ "/Collectiong/lawers";

    /**
     * 律师业务 - 案例分享
     */
    public static String OFFICEHOMEARTICLEWORK = URL + "/Lawer/officeHomearticlework";
    /**
     * 律师业务 - 会员评论
     */
    public static String OFFICEHOMECOMMENT = URL + "/Lawer/officeHomecomment";
    /**
     * 用户评论 - 发表评论
     */
    public static String CREATE_TELEPHONECOMMENT = URL + "/TelephoneComment/create";
    /**
     * 用户评论 - 发表评论
     */
    public static String CREATE_MEMBERCOMMENT = URL + "/MemberComment/create";

    /**
     * 在线咨询录音文件存储路径
     */
    public static final String Sounds = Environment.getExternalStorageDirectory().getAbsolutePath()
            + "/Hzzll/Recrod/Sounds/";

    /**
     * 问答咨询录音文件存储路径
     */
    public static final String Audio = Environment.getExternalStorageDirectory().getAbsolutePath()
            + "/Hzzll/Recrod/Audio/";

    /**
     * 存证--通话文件存储路径
     */
    public static final String Call = Environment.getExternalStorageDirectory().getAbsolutePath()
            + "/Hzzll/EvidenceCall/";

    /**
     * 存证--拍照文件存储路径
     */
    public static final String Pictures = Environment.getExternalStorageDirectory().getAbsolutePath()
            + "/Hzzll/EvidencePictures/";

    /**
     * 存证--录音文件存储路径
     */
    public static final String Record = Environment.getExternalStorageDirectory().getAbsolutePath()
            + "/Hzzll/EvidenceRecord/";

    /**
     * 存证--录像文件存储路径
     */
    public static final String Video = Environment.getExternalStorageDirectory().getAbsolutePath()
            + "/Hzzll/EvidenceVideo/";

    /**
     * 消息类 - 获取认证验证码
     */
    public static String GET_APPROVE_SMSCODE = URL + "/Msg/getApproveSmsCode";

    // ==================================================安存
    /**
     * 安存存证购买
     */
    public final static String ORDEREVIDENCE = IPs + "/ancun/order_evidence";
    /**
     * 图片存储
     */
    public final static String ANCUNPIC = IPs + "/ancun/pic";
    /**
     * 录音存储
     */
    public final static String ANCUNREC = IPs + "/ancun/rec";
    /**
     * 录像存储
     */
    public final static String ANCUNVCR = IPs + "/ancun/vcr";
    /**
     * 网页存证
     */
    public final static String ANCUNWEB = IPs + "/ancun/web";
    /**
     * 通话--开通
     */
    public final static String USEROPEN = IPs + "/ancun/user_open";
    /**
     * 通话--呼叫请求
     */
    public final static String CALLING = IPs + "/ancun/calling_request";
    /**
     * 通话-- 呼叫回调
     */
    public final static String CALLBACK = IPs + "/ancun/callback";

    /**
     * 通话套餐显示
     */
    public final static String COMBO = IPs + "/ancun/combo/";
    /**
     * 购买通话套餐
     */
    public final static String ORDERCOMBO = IPs + "/ancun/order_combo";
    /**
     * 根据业务类型查询价格
     */
    public final static String ORDERPRICE = IPs + "/ancun/order_eprice/";

    /**
     * 律师创建通话
     */
    public final static String CREATECALL = IPs + "/comunicate/calling_client";
    // 律师扫描二维码
    public final static String SCAN = IPs + "/comunicate/scan";
    //
    public final static String LAWCASE = IPs + "/lawcase/caselist_by_date/";

    /**
     * 登陆banner
     */
    public final static String LOGINRE = IPs + "/imgs/banner_list/login";
    /**
     * 注册banner
     */
//	public final static String REGISTERRE = "http://files.hzzll.com/banner/regist/1.jpg ";

    public final static String REGISTERRE = IPs + "/imgs/banner_list/regist";

    /**
     * 获取用户安全存证的存储剩余数目
     */
    public final static String GET_EVIDENCENUM_BY_ID = IPs + "/ancun/get_evidence/";

    /**
     * 仲裁服务
     */
    public final static String ARBITRATION_SERVICE = MOBILE_URL + "n/introhomeapp/index.html#/arbitrament?mobile";

    /**
     * 私律微课
     */
    public final static String PRIVATE_LAW_CLASS = MOBILE_URL + "/n/micclass/index.html";

    /**
     * 保全担保
     */
    public final static String ARBITRATION_AWARD = MOBILE_URL + "n/introhomeapp/index.html#/security";
    /**
     * 律查查
     */
    public final static String LAW_CHECK = MOBILE_URL + "chacha";

    /**
     * 债权转让
     */
//    public final static String ASSIGNMENT_OF_DEBT = MOBILE_URL + "/n/0914/a.html#/";
    public final static String ASSIGNMENT_OF_DEBT = "http://m.hzzll.com/webApp/";
    /**
     * 资产线索悬赏
     */
    public final static String ASSET_CLUES_FOR = "http://m.hzzll.com/webApp/Zhaiquan/reward.html";

    /**
     * 专业服务
     */
    public final static String PROFESSIONAL_SERVICES = "http://m.hzzll.com/webApp/Zhaiquan/fwsd.html";

    /**
     * 律师办公室 收藏数
     */
    public final static String LAWEROFFIC_CLLOCTION = URL + "/Lawer/officeHomelawyerinfo";
    /**
     * 注册活动
     */
    public final static String REGISTERACTIVITY = "https://www.baidu.com/";

    /**
     * 创建案件 获取案由  审理程序  诉讼角色
     */
    public final static String CREATECASE = URL + "/Catalog/getCaseCateLog/.json";


    /**
     * 免费收听查看收听数量增加
     */
    public final static String ADDVCOUNT = URL + "/Answer/addvcount";

    /**
     * 版本更新
     */
    public final static String VERSION = IPs + "/appversion/check";


    /******************************
     *
     * 新版接口
     *
     * http://silvapi.hzzll.com/v1/.....
     *
     * **********************************/


    public static String VERSIONCODE = "v1/";
    public static String VERSIONCODE_2 = "v2/";


    // user/device  post  delect
    public static String DEVICE = SILVZONE + VERSIONCODE + "user/device";


    /*获取地区列表*/
    public static String GETADDRESS = SILVZONE + VERSIONCODE + "base/region";
    // 获取验证码
    public static String GET_AUTH_CODE = SILVZONE + VERSIONCODE + "message/sms-send";
    // 获取首页banner   "id":"1",APP首页banner"/"id":"2","name":"APP律查查banner"/"id":"3","name":"APP云办公室banner"/"id":"4","name":"APP证据保全"
    public static String GET_HOME_BANNER = SILVZONE + VERSIONCODE + "banner/lists/";
    public static String GET_OFFICE_BANNER = SILVZONE + VERSIONCODE + "banner/lists/3";
    public static String GET_EVIDENCE_BANNER = SILVZONE + VERSIONCODE + "banner/lists/4";
    /**
     * 版本更新
     */
    public final static String CHECK_VERSION = SILVZONE + VERSIONCODE + "app/version-check/android";
    /**
     * 注册时验证码
     */
    public static String GETREGISTERSMSCODE = SILVZONE + VERSIONCODE + "message/sms-send";

    // 注册 =============================================
    public static String REGISTER = SILVZONE + VERSIONCODE + "user";
    // 登录 =============================================
    public static String NEW_LOGIN = SILVZONE + "oauth/token";
    //  个人用户实名认证=============================================
    public static String REALNAME = SILVZONE + VERSIONCODE + "user/auth/realname";
    //  案件类型 （律师专长）=============================================
    public static String CASETYPE = SILVZONE + VERSIONCODE + "base/legal-type";

    // 查询用户认证信息
    public static String CHECK_USER_AUTH_INFO = SILVZONE + VERSIONCODE + "user/auth";

    // 查询用户信息
    public static String CHECK_OUT_USER_INFO = SILVZONE + VERSIONCODE + "user";


    // 查询用户个人资料信息（check）   修改(modify) 用户资料信息
    public static String CM_USER_DATA_INFO = SILVZONE + VERSIONCODE + "user/info";


    // 用户忘记密码
    public static String FORGET_PSW = SILVZONE + VERSIONCODE + "forgot-password";

    //修改用户登录密码
    public static String MODIFY_PSW = SILVZONE + VERSIONCODE + "user/security/password";
    //修改用户支付密码
    public static String MODIFY_PAY_PSW = SILVZONE + VERSIONCODE + "user/security/paypassword";
    //修改用户认证手机号
    public static String MODIFY_PHONE = SILVZONE + VERSIONCODE + "user/auth/mobile";
    //发送邮件
    public static String SEND_EMAIL_CODE = SILVZONE + VERSIONCODE + "message/email-send";
    //修改用户认证邮箱
    public static String MODIFY_EMAIL = SILVZONE + VERSIONCODE + "user/auth/email";

    // 创建意见反馈接口http://silvapi.hzzll.com/v1/feedback/create-feedback
    public static String CREATE_FEEDBACK = SILVZONE + VERSIONCODE + "feedback";
    //查询我的邀请码  http://silvapi.hzzll.com/v1/invite/invite
    public static String GET_INVITION_CODE = SILVZONE + VERSIONCODE + "invite/invite";
    //查询邀请过的用户接口http://silvapi.hzzll.com/v1/invite/isinvited
    public static String GET_INVITED_PERSON = SILVZONE + VERSIONCODE + "invite/isinvited";
    //律师办公室评价列表evaluate
    public static String GET_EVALUATE_LIST = SILVZONE + VERSIONCODE + "evaluate";
    //  律师办公室用户关注   http://silvapi.hzzll.com/v1/lawyer/follow
    public static String FOLLOW = SILVZONE + VERSIONCODE + "lawyer/follow";


    //全国律师
    public static String LAWYERCERTIFIEDLISTS = SILVZONE + VERSIONCODE + "lawyer/wholecountry";
    //Lawyer - 查找某个律师详细信息 http://silvapi.hzzll.com/v1/lawyer/someone
    public static String GET_LAWYERINFO_BY_ID = SILVZONE + VERSIONCODE + "lawyer/someone";
    //http://silvapi.hzzll.com/v1/lawyer/eye 律师浏览量
    public static String ADD_LAWYER_VIEW_num = SILVZONE + VERSIONCODE + "lawyer/eye";


    //律师发布报价http://silvapi.hzzll.com/v1/releasePrice
    public static String RELEASE_PRICE = SILVZONE + VERSIONCODE + "lawyer/release-price";


    //查询快速咨询列
    public static String CONSULT_LIST = SILVZONE + VERSIONCODE + "consult/list";
    //我的回答列表http://silvapi.hzzll.com/v1/consult/answer
    public static String CONSULT_ANSWER = SILVZONE + VERSIONCODE + "consult/answer";
    //快速咨询详细信息http://silvapi.hzzll.com/v1/consult/details/参数
    public static String CONSULT_DETAILS = SILVZONE + VERSIONCODE + "consult/details/";
    //某个咨询问题的律师回复列表http://silvapi.hzzll.com/v1/consult/reply-curissue
    public static String ANSWERLIST = SILVZONE + VERSIONCODE + "consult/reply-curissue";

    // 律师回复快速咨询问题_发布页面(律师端)http://silvapi.hzzll.com/v1/consult/quick-publish
    public static String PUBLISHANSWER = SILVZONE + VERSIONCODE + "consult/quick-publish";

    //当前咨询问题律师回复的列表查看具体用户点赞和评价信息http://silvapi.hzzll.com/v1/consult/reply-issue-evaluate
    public static String COMMENTS_AND_LIKES = SILVZONE + VERSIONCODE + "consult/reply-issue-evaluate";

    //    用户快速咨询关注与取消 http://silvapi.hzzll.com/v1/consult/follow
    public static String CONSULT_FOLLOW = SILVZONE + VERSIONCODE + "consult/follow";
    //- 详情咨询问题 律师回复点赞与取消http://silvapi.hzzll.com/v1/consult/thumbs-up
    public static String CONSULT_LIKE = SILVZONE + VERSIONCODE + "sns/like";
    //   查询追问信息列表 http://silvapi.hzzll.com/v1/reply/reply-list/参数
    public static String REPLY_REPLY_LIST = SILVZONE + VERSIONCODE + "reply/reply-list/";
    //创建追问 http://silvapi.hzzll.com/v1/reply
    public static String REPL = SILVZONE + VERSIONCODE + "reply";

    // 律师|个人中心我的私粉http://silvapi.hzzll.com/v1/lawyer/my-follower
    public static String MY_FOLLOWER = SILVZONE + VERSIONCODE + "lawyer/my-follower";
    //   律师|个人中心我的关注 http://silvapi.hzzll.com/v1/lawyer/my-follow
    public static String MY_FOLLOW = SILVZONE + VERSIONCODE + "lawyer/my-follow";

    /**
     * 提现 - 发起提现请求  http://silvapi.hzzll.com/v1/balance/withdraw
     */
    public static String WITHDRAW = SILVZONE + VERSIONCODE + "balance/withdraw";
    ;
    /**
     * /**
     * 收支明细 - 收支记录 http://silvapi.hzzll.com/v1/balance/consult-case-income
     */

    public static String CASHLOG_LIST = SILVZONE + VERSIONCODE + "balance/consult-case-income";

    /**
     * 获取咨询订单列表
     * <p>
     * http://silvapi.hzzll.com/v1/order/order-list
     */
    public static String ORDER_LIST = SILVZONE + VERSIONCODE + "order/lists";

    // Order - 订单数据统计接口http://silvapi.hzzll.com/v1/order/count
    public static String ORDER_COUNT = SILVZONE + VERSIONCODE + "order/count";

    //  订单详情查询http://silvapi.hzzll.com/v1/order/detail/
    public static String ORDER_DETAIL = SILVZONE + VERSIONCODE + "order/detail/";


    //  http://silvapi.hzzll.com/v1/lawyer/promote
    public static String LAWYER_PROMOTE = SILVZONE + VERSIONCODE + "lawyer/promote";

    /**
     * 律师业务 - 学术发表   http://silvapi.hzzll.com/v1/article
     */
    public static String OFFICEHOMEARTICLE = SILVZONE + VERSIONCODE + "article";

    /**
     * 发表文章学术      http://silvapi.hzzll.com/v1/article/publish
     */
    public static String ARTICLECREATE = SILVZONE + VERSIONCODE + "article/publish";

    public static String DELETE_ARTICLE = SILVZONE + VERSIONCODE + "article/delete";
    public static String DELETE_WORK = SILVZONE + VERSIONCODE + "a";
    /**
     * 我的案例 - 删除案例(需登录)  http://silvapi.hzzll.com/v1/article/delete
     * /
     * <p>
     * /**
     * 文章详情  http://silvapi.hzzll.com/v1/article/detail
     */
    public static String MEMBERARTICLE = SILVZONE + VERSIONCODE + "article/detail";

    // ExclusiveConsult - 查询专属咨询倒计时情况http://silvapi.hzzll.com/v1/consult/exclusive/countdown/{from_accid}/{to_accid}
    public static String COUNT_DOWN = SILVZONE + VERSIONCODE_2 + "consult/exclusive/countdown/";

    // http://silvapi.hzzll.com/v1/yunxin/account/localmsg
    public static String CHAT_FILE = SILVZONE + VERSIONCODE + "yunxin/account/localmsg";

    //http://silvapi.hzzll.com/v1/message/list-APP-week-14-434-0-10
    public static String MESSAGE_QUICK = SILVZONE + VERSIONCODE + "message/list-14-month-1-30";

    //http://silvapi.hzzll.com/v1/message/list-APP-week-14-434-0-10
    public static String MESSAGE_SYSTEM = SILVZONE + VERSIONCODE + "message/system-0-";

    // 回答问题 费用分配  http://silvapi.hzzll.com/v1/base/dict/consult_ranking
    public static String CONSULT_RANKING = SILVZONE + VERSIONCODE + "base/dict/consult_ranking";
    //邀请购买专属咨询
    public static String INVITE_BUY = SILVZONE + VERSIONCODE + "message/exclusive-messages-";

    //Message - 是否已读未读http://silvapi.hzzll.com/v1/message/is-read
    public static String MESSAGE_IS_READ = SILVZONE + VERSIONCODE + "message/is-read";

    // 用户转律师
    public static String ROLE_REVERSAL = SILVZONE + VERSIONCODE + "user/user-switch-lawyer";


    /**
     * 所有使用到的外部链接地址
     */

//    public static String HEAD ="http://testmobile.hzzll.com/";

    //用户端地址
    public static String USER_SIDE = MOBILE_URL;
    //使用协议 "https://m.silvzone.com/
    public static String AGREEMENT_OF_USAGE = MOBILE_URL + "n/0914/m.html#/";
    //隐私声明
    public static String PRIVACY_STATEMENT = MOBILE_URL + "n/0914/p.html#/";
    //法律声明
    public final static String LEGAL_DISCLAIMER = MOBILE_URL + "n/0914/q.html#/";
    //专属咨询协议
    public static String EXCLUSIVE_CONSULTATION_AGREEMENT = "http://mobile.hzzll.com";
    //关于私律
    public static String ABOUT_SILV = MOBILE_URL + "n/0914/k.html#/";

    //法律法规库
    public static String LAW_AND_REGULATION_DATABASE = MOBILE_URL + "n/chacha/index.html#/lawlist";

    //诉讼费计算器
    public static String LITIGATION_COST_CALCULATOR = MOBILE_URL + "n/0914/calculator.html";

    //仲裁费计算器
    public static String THE_ARBITRATION_FEE_CALCULATOR = MOBILE_URL + "arbitrate";

    //婚姻保  债权保  聘请律师
    public static String MARRYHINT = MOBILE_URL + "MarryHint";


    // 根据旧用户ID获取新用户IDhttp://silvapi.hzzll.com/v1/user/getid/{old_user_id}

    public static String GET_NEWID_BY_OLDID = SILVZONE + VERSIONCODE + "user/getid/";

    //邀请收益明细
    public static String GET_INVITE_INCOME = SILVZONE + VERSIONCODE_2 + "invite/get-invite-income";
    public static String GET_INVITE_USER = SILVZONE + VERSIONCODE_2 + "invite/get-invite-user";


    public static String GET_QRCODE = SILVZONE + VERSIONCODE_2 + "channel/get-qrcode/";
    public static String GET_CONSULT_CODE = SILVZONE + VERSIONCODE_2 + "channel/get-consult-code/";


    //专属律师

    //查询

    public static String GET_EXCLUSIVELAWYER_STATUES = SILVZONE + VERSIONCODE_2 + "user/get-status";

    //申请

    public static String GET_EXCLUSIVELAWYER_APPLICATION = SILVZONE + VERSIONCODE_2 + "user/lawyer-application";


//云盘   私律云盘用户文件列表

    public static String GET_CLOUD_DISK_LIST = SILVZONE + VERSIONCODE + "cloud/cloudlist";


    //协作列表  tapi.silvzone.com/v2/order/lawyer-lists?
    public static String LAWYER_LIST = SILVZONE + VERSIONCODE_2 + "order/lawyer-lists";


    //  获取咨询类别   tapi.silvzone.com/v2/marry/get-type
    public static String SELEET_TYPE = SILVZONE + VERSIONCODE_2 + "marry/get-type";

    //  获取咨询类别   tapi.silvzone.com/v2/marry/get-type tapi.silvzone.com/v1/employlayer/legal-type
    //GET
    //聘请律师代理类型

    public static String SELEET_EMPLOYLAYER_TYPE = SILVZONE + VERSIONCODE + "employlayer/legal-type";


    //tapi.silvzone.com/v2/pay/lawyer-pay
    //POST
    //律师端支付
    public static String LAWYER_PAY = SILVZONE + VERSIONCODE_2 + "pay/lawyer-pay";


    //
//    HTTP
//    tapi.silvzone.com/v1/employlayer/release-public
//    POST
//            发布公开聘请律师

    public static String EMPLOYLAYER_RELEASE_PUBLIC = SILVZONE + VERSIONCODE + "employlayer/release-public";


    // tapi.silvzone.com/v2/order/service-details/71718004908082964
    //GET
    //律师端，用户端查询服务详情
    public static String ORDER_SERVICE_DETAILS = SILVZONE + VERSIONCODE_2 + "order/service-details/";

    //tapi.silvzone.com/v2/order/get-question?order_no=71718004908082964
//GET
//订单详情查询调查问卷
    public static String ORDER_GET_QUESTION = SILVZONE + VERSIONCODE_2 + "order/get-question?order_no=";

    //tapi.silvzone.com/v2/marry/lawyer-apply-marry
//POST
//律师申请婚姻保，债权保
    public static String ORDER_LAWYER_APPLY = SILVZONE + VERSIONCODE_2 + "marry/lawyer-apply-marry";

    //tapi.silvzone.com/v2/order/lawyer-confirmation
    //PUT
    //律师端确认完成
    public static String ORDER_LAWYER_CONFIRMATION = SILVZONE + VERSIONCODE_2 + "order/lawyer-confirmation";

    //tapi.silvzone.com/v1/employlayer/update-amount
//POST
//律师修改报价
    public static String EMPLOYLAYER_UPDATE_AMOUNT = SILVZONE + VERSIONCODE + "employlayer/update-amount";


    //tapi.silvzone.com/v1/employlayer/layer-application
    //POST
    //律师申请用户的聘请
    public static String EMPLOYLAYER_LAYER_APPLY = SILVZONE + VERSIONCODE + "employlayer/layer-application";

    //    tapi.silvzone.com/v2/order/lawyer-my-lists?my_operation=0
//    GET
//            律师端我的接办案
    public static String ORDER_LAWYER_CASE_LIST = SILVZONE + VERSIONCODE_2 + "order/lawyer-my-lists";


    //    tapi.silvzone.com/v2/order/order-details/71718004908082964
//    GET
//    律师端，用户端查询订单详情
    public static String COLL_ORDER_DETAILS = SILVZONE + VERSIONCODE_2 + "order/order-details/";


    //  tapi.silvzone.com/v2/marry/upload-attachment
    //          POST
    // 律师申请上传附件

    public static String COLL_UPLOAD_ATTACHMENT = SILVZONE + VERSIONCODE_2 + "marry/upload-attachment";


    //api.silvzone.com/v2/marry/cloud-disk-attachment
    //POST
    //律师申请从云盘上传附件

    public static String COLL_UPLOAD_ATTACHMENT_FROM_DISK = SILVZONE + VERSIONCODE_2 + "marry/cloud-disk-attachment";

    //tapi.silvzone.com/v2/
    //POST
    //充值接口
    public static String PAY_RECHARGE = SILVZONE + VERSIONCODE_2 + "pay/recharge";

    //tapi.silvzone.com/v2/order
    //POST
    //更新订单
    public static String ORDER_UPDATE = SILVZONE + VERSIONCODE_2 + "order";



    //tapi.silvzone.com/v1/employlayer/choose-layer
    //POST
    //用户确认委托

    public static String EMPLOYLAYER_CHOOSE_LAYER = SILVZONE + VERSIONCODE+ "employlayer/choose-layer";


    //tapi.silvzone.com/v2/evaluate/order-evaluate
    //POST
    //订单里面的评价
    public static String EVALUATE_ORDER_EVALUATE = SILVZONE + VERSIONCODE_2+ "evaluate/order-evaluate";

    //tapi.silvzone.com/v2/marry/dele-order

    public static String DELE_ORDER = SILVZONE + VERSIONCODE_2+ "marry/dele-order";


    //tapi.silvzone.com/v2/complaint
    //POST
    //投诉接口
    public static String COMPLAINT = SILVZONE + VERSIONCODE_2+ "complaint";

//tapi.silvzone.com/v1/employlayer/confirm-success
//POST
//聘请律师用户确认完成订单

    public static String EMPLOYLAYER_CONFIRM_SUCCESS = SILVZONE + VERSIONCODE+ "employlayer/confirm-success";


    //tapi.silvzone.com/v1/employlayer/layer-refuse
//POST
//律师拒绝
    public static String EMPLOYLAYER_LAYER_REFUSE = SILVZONE + VERSIONCODE+ "employlayer/layer-refuse";


    //tapi.silvzone.com/v1/qcode-return
//GET
//APP扫码确认
    public static String APP_QCODE_RETURN = SILVZONE + VERSIONCODE+ "qcode-return";

    /**
     * 分享的外链
     */

    //邀请注册
    public static String INVITATIONLAWYER = MyData.MOBILE_URL + "invitationlawyer/";
    //律师办公室
    public static final String LAWYERS_DETIAL = MyData.MOBILE_URL + "lawyers/";
    //律师列表
    public static final String LAWYERS = MyData.MOBILE_URL + "lawyers";




    //客服电话 consumer hotline
    public static final String CONSUMER_HOTLINE = "4001618528";

    //云信SDK隐私协议
    public static final String PRIVACY_AGREEMENT = "https://yunxin.163.com/clauses?serviceType=3";
    public static final String HZAPPLICATION_RECEIVER = "HzApplication.receiver";


}
