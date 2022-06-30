package com.yhy.hzzll.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

/**
 * 缓存类
 * 
 * @author Yang
 * 
 */
public class PrefsUtils {


	public static String DATE_TIME = "date_time";


	public static String CONSULT_PRICE = "price";
	public static String CONSULT_OPEN = "open";
	public static String ACCID = "accid";
	/** 是不是第一次进应用 */
	public static String USERTOKEN = "token";

//第一 first--1st，第二 second--2nd；第三 third--3rd，第四 fourth 4th。

	public static String FIRST_REPLY = "first_reply";
	public static String SECOND_REPLY = "second_reply";
	public static String THIRD_REPLY = "third_reply";


	/** 是不是第一次进应用 */
	public static String First = "first";
	/** 是不是第一次进应用我的界面 */
	public static String FirstMY = "firstmy";

	//是否同意隐私协议
	public static String Agree = "agree";


	/** 全局Authorization */
	public static String AUTHORIZATION= "Authorization";
	public static String OFF_PUSH= "offPush";
	public static String AUTHORIZATION_NO_B= "AUTHORIZATION_NO_B";
	public static String REFRESH_TOKEN= "refresh_token";
	public static String AUTH_CODE= "auth_code";
	public static String AUTH_STATUS= "auth_status";

	public static String ORDER_NO= "order_no";
	public static String IS_HAS_REPLY= "is_has_reply";
	public static String IS_COMPLAINTING= "is_complainting";


	/** 全局key */
	public static String ACCESSKEY = "AccessKey";

	/** 全局hashCode */
	public static String HASHCODE = "hashCode";


	public static String ESCIT_PAYPSW = "escit_paypsw";
	public static String  EMAIl= "";

	/** 注册用手机号临时存 */
	public static String PHONE = "phone";
	/** 注册用密码临时存 */
	public static String PASSWORD = "password";
	/** 注册用验证码临时存 */
	public static String VERRIFY = "verrify";
	/** 注册用省临时存 */
	public static String PROVINCES = "provinces";
	/** 注册用市临时存 */
	public static String CITY = "city";
	/** 注册用区临时存 */
	public static String AREA = "area";
	/** 注册用职务临时存 */
	public static String DUTY = "duty";
	/** 是不是第一次进入问答咨询 */
	public static String ENTRY = "entry";


	// 用户uid
	public static String UID = "uid";
	public static String OLD_UID = "old_uid";
	public static String ADDRESS = "address";
	public static String SPECIAL = "special";
	public static String AVATAR = "avatar";



	// 用户姓名
	public static String LAWYER_TYPE = "lawyer_type";

	// 用户姓名
	public static String UNAME = "uname";
	// 用户证件号
	public static String UCODE = "ucode";
	// 用户手机号
	public static String UPHONE = "uphone";
	// 用户支付密码
	public static String PAYWORD = "payword";
	/** 用户登陆名 */
	public static String USERNAME = "username";
	/** 用户登陆密码 */
	public static String USERPASSROWD = "password";
	/** 用户是否记住密码 */
	public static String ISREMEMBER = "remember";

	/** 全国所有地区的json */
	public static String ALLAREA = "allarea";
	
	/** 记录通过日程管理进入案件管理界面的次数 */
	public static String REMAINDTOCASE  = "remindtocase";

	
	/** 记录最近一次点击解答疑的时间*/
	public static String CLICKCONSULT  = "clickconsult";
	
	/** 记录最近一次点击解接办案的时间*/
	public static String CLICKLAWCOOP  = "clicklawcoop";


	/** 记录刷新时间*/
	public static String REFRESHTIME  = "refreshtime";

	/** 标记不再更新 */
	public static String UPDATAORNOT = "updata";
	
	
	private static SharedPreferences mInstance = null;

	// double check and lock
	private static SharedPreferences getInstance(Context ctx) {
		if (mInstance == null) {
			synchronized (PrefsUtils.class) {
				if (mInstance == null) {
					mInstance = PreferenceManager
							.getDefaultSharedPreferences(ctx);
				}
			}
		}
		return mInstance;
	}

	public static SharedPreferences get(Context ctx) {
		return getInstance(ctx);
	}

	/**
	 * 保存数据
	 * 
	 * @param context
	 * @param key
	 * @param object
	 */
	public static void put(Context context, String key, Object object) {

		SharedPreferences sp = get(context);
		Editor editor = sp.edit();

		if (object instanceof String) {
			editor.putString(key, (String) object);
		} else if (object instanceof Integer) {
			editor.putInt(key, (Integer) object);
		} else if (object instanceof Boolean) {
			editor.putBoolean(key, (Boolean) object);
		} else if (object instanceof Float) {
			editor.putFloat(key, (Float) object);
		} else if (object instanceof Long) {
			editor.putLong(key, (Long) object);
		} else {
			editor.putString(key, object.toString());
		}
	}

	public static void saveString(Context context, String name, String text) {
		SharedPreferences sp = get(context);
		Editor editor = sp.edit();
		editor.putString(name, text);
		editor.commit();
	}

	public static String getString(Context context, String name) {
		SharedPreferences sp = get(context);
		String str = sp.getString(name, "");
		return str;
	}

	public static void saveInt(Context context, String name, int position) {
		SharedPreferences sp = get(context);
		Editor editor = sp.edit();
		editor.putInt(name, position);
		editor.commit();
	}

	public static void saveLong(Context context, String name, long position) {
		SharedPreferences sp = get(context);
		Editor editor = sp.edit();
		editor.putLong(name, position);
		editor.commit();
	}


	public static int getInt(Context context, String name) {
		SharedPreferences sp = get(context);
		int position = sp.getInt(name, 0);
		return position;
	}

	public static void clear(Context context) {
		SharedPreferences sp = get(context);
		sp.edit().clear().commit();
	}
}
