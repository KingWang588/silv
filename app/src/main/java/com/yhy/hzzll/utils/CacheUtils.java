package com.yhy.hzzll.utils;

import com.yhy.hzzll.mian.entity.DataUserEntity;
import com.yhy.hzzll.entity.PersonEntity;

/**
 * 临时存储一些常用数据
 * 
 * @author Yang
 * 
 */
public class CacheUtils {

	/** 个人信息 */
	public static DataUserEntity dataUserEntity;

	/** 用户基本信息 */
	public static PersonEntity personEntity;

	public static void cacheClear() {
		dataUserEntity = null;
		personEntity = null;
	}

}
