package com.yhy.hzzll.entity;

public class DateEntity {

	// 记录实时点击的年月日
	private int cyear=0;
	private int cmonth=0;
	private int cday=0;

	public int getCyear() {
		return cyear;
	}

	public void setCyear(int cyear) {
		this.cyear = cyear;
	}

	public int getCmonth() {
		return cmonth;
	}

	public void setCmonth(int cmonth) {
		this.cmonth = cmonth;
	}

	public int getCday() {
		return cday;
	}

	public synchronized void setCday(int cday) {
		this.cday = cday;
	}

}
