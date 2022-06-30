package com.yhy.hzzll.framework;

import java.io.File;

/**
 * 存放常量
 */
public class Constans {

	
	public final static String SAVE_APP_NAME = "download.apk";

    public final static String SAVE_APP_LOCATION = "/download";
	
	public final static String LOGIN_ENTITY_CACHE_PATH = "/hzzll/loginCache/";// 保存着LoginEntity缓存的路径
	/**
	 * 用户信息
	 */
	public static final String USER_INFO = "USER_INFO";
	/**
	 * 律师职位
	 */
	public static final String DUTY_TYPE_ONE = "执业律师";
	public static final String DUTY_TYPE_TWO = "主任";
	public static final String DUTY_TYPE_THREE = "副主任";
	public static final String DUTY_TYPE_FOUR = "合伙人";

	/**
	 * 服务器返回码定义
	 */
	public static final String SUCCESS_CODE = "0";

	/**
	 * 认证状态--待审核
	 */
	public static final String CHECK_PENDING = "-1";
	/**
	 * 认证状态--未认证
	 */
	public static final String UNAUTHERIZED = "0";
	/**
	 * 认证状态--已认证
	 */
	public static final String authenticated = "1";

	/**
	 * 支付方式
	 * 余额支付
	 */
	public static final String MONEY = "money";
	/**
	 * 支付宝PC支付
	 */
	public static final String ALIPAY_PC_DIRECT = "alipay_pc_direct";
	/**
	 * 支付宝手机支付
	 */
	public static final String alipay_wap = "alipay_wap";
	/**
	 * 微信扫码支付
	 */
	public static final String WX_PUB_QR = "wx_pub_qr";
	/**
	 * 银联PC支付
	 */
	public static final String UPACP_PC = "upacp_pc";
}
