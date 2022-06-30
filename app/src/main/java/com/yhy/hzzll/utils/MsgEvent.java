package com.yhy.hzzll.utils;

import java.util.List;

import com.yhy.hzzll.entity.LawyerCoopEntity;

public class MsgEvent {

	private String msg;

	private int msg1;

	private Object object;

//	private List<LawyerCoopEntity.list> list;

	
	public MsgEvent() {
		// TODO Auto-generated constructor stub
	}
	
//	public List<LawyerCoopEntity.list> getListMsg() {
//		return list;
//	}

//	public MsgEvent(List<LawyerCoopEntity.list> coopList) {
//		super();
//		this.list = coopList;
//	}

	public Object getObjectMsg() {
		return object;
	}

	public MsgEvent(Object object) {
		super();
		this.object = object;
	}

	public MsgEvent(String msg) {
		super();
		this.msg = msg;
	}

	public String getMsg() {
		return msg;
	}

	public MsgEvent(int msg) {
		super();
		this.msg1 = msg;
	}

	public int getIntMsg() {
		return msg1;
	}
}
