package com.yhy.hzzll.entity;

import java.io.Serializable;

/**
 * <p>日历实体类/p>
 * @author  wy
 * 
 */
public class CalendarEntity implements Serializable {

	private static final long serialVersionUID = -6053739977785155088L;
	public int year;
	public int month;
	public int day;
	/** 星期 */
	public String weekDay;
	/** 是否为当前日*/
	public boolean isNowDate;
	/**是否为本月日*/
	public boolean isSelfMonthDate;
}
