package com.yhy.hzzll.entity;

import java.util.List;

/**
 * 获取服务报价类别
 * 
 * @author 一合鱼
 *
 */
public class ProjectsServiceTypeEntity {

	private List<String> title;
	/**
	 * id : 0 name :
	 */

	private List<TypeBean> type;
	/**
	 * id : 0 name :
	 */

	private List<CaseTypeBean> case_type;
	/**
	 * id : 0 name :
	 */

	private List<ChargeTypeBean> charge_type;
	/**
	 * id : 0 name :
	 */

	private List<PriceTypeBean> price_type;
	/**
	 * id : 0 name : 起
	 */

	private List<TimeUnitBean> time_unit;
	/**
	 * id : 0 name :
	 */

	private List<PriceUnitBean> price_unit;

	public List<String> getTitle() {
		return title;
	}

	public void setTitle(List<String> title) {
		this.title = title;
	}

	public List<TypeBean> getType() {
		return type;
	}

	public void setType(List<TypeBean> type) {
		this.type = type;
	}

	public List<CaseTypeBean> getCase_type() {
		return case_type;
	}

	public void setCase_type(List<CaseTypeBean> case_type) {
		this.case_type = case_type;
	}

	public List<ChargeTypeBean> getCharge_type() {
		return charge_type;
	}

	public void setCharge_type(List<ChargeTypeBean> charge_type) {
		this.charge_type = charge_type;
	}

	public List<PriceTypeBean> getPrice_type() {
		return price_type;
	}

	public void setPrice_type(List<PriceTypeBean> price_type) {
		this.price_type = price_type;
	}

	public List<TimeUnitBean> getTime_unit() {
		return time_unit;
	}

	public void setTime_unit(List<TimeUnitBean> time_unit) {
		this.time_unit = time_unit;
	}

	public List<PriceUnitBean> getPrice_unit() {
		return price_unit;
	}

	public void setPrice_unit(List<PriceUnitBean> price_unit) {
		this.price_unit = price_unit;
	}

	public static class TypeBean {
		private int id;
		private String name;

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
	}

	public static class CaseTypeBean {
		private int id;
		private String name;

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
	}

	public static class ChargeTypeBean {
		private int id;
		private String name;

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
	}

	public static class PriceTypeBean {
		private int id;
		private String name;

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
	}

	public static class TimeUnitBean {
		private int id;
		private String name;

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
	}

	public static class PriceUnitBean {
		private int id;
		private String name;

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
	}

}
