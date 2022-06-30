package com.yhy.hzzll.entity;

import java.util.List;

/**
 * 服务商店 实体
 * 
 * @author Yang
 * 
 */
public class ServiceEntity extends BaseEntity {

	private String data;
	private String page;
	private String count;
	private List<LIst> list;

	public List<LIst> getList() {
		return list;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public class LIst {
		private String num;
		private String nid;
		private String starnum;
		private String userid;
		private String username;
		private String nickname;
		private String passport;
		private String password;
		private String payword;
		private String email;
		private String mobile;
		private String message;
		private String utype;
		private String top;
		private String hidden;
		private String online;
		private String avatar;

		private String avatarurl;
		private String gender;
		private String truename;
		private String idcard;
		private String msn;
		private String qq;
		private String ali;
		private String skype;
		private String imgurl;
		private String career;
		private String admin;
		private String role;
		private String aid;
		private String grouptype;
		private String regid;
		private String areaid;
		private String sms;
		private String credit;

		private String money;
		private String locking;
		private String bank;
		private String account;
		private String edittime;
		private String regip;
		private String regtime;
		private String loginip;
		private String logintime;
		private String logintimes;
		private String black;
		private String send;
		private String auth;
		private String authvalue;
		private String authtime;
		private String vemail;
		private String vmobile;
		private String vtruename;

		private String vsummary;
		private String vbank;
		private String vcompany;
		private String vtrade;
		private String trade;
		private String support;
		private String inviter;
		private String source;
		private String province;
		private String city;
		private String area;
		private String company;
		private String companypicurl;
		private String companytype;
		private String lawfirm;
		private String lawcode;
		private String lawcode2;
		private String lawzc;

		private String telphone;
		private String fronturl;
		private String reverseurl;
		private String bthumb;
		private String cthumb;
		private String hot;
		private String tplid;
		private String deleted;
		private String intr;
		private String contact;
		private String utime;
		private String hits;
		private String reasons;
		private String frozen_money;
		private String mtplid;
		private String withdraw;

		private String fwshop_type;
		private String fwshop_keyword;
		private String be_door;
		private String thumb_list;
		private String ucenter_bg;
		private String ucenter_menu;
		private String duty;
		private String is_safe;
		private String collect_num;
		private String place;
		private String fwshop_type_name;

		public String getNum() {
			return num;
		}

		public void setNum(String num) {
			this.num = num;
		}

		public String getNid() {
			return nid;
		}

		public void setNid(String nid) {
			this.nid = nid;
		}

		public String getStarnum() {
			return starnum;
		}

		public void setStarnum(String starnum) {
			this.starnum = starnum;
		}

		public String getUserid() {
			return userid;
		}

		public void setUserid(String userid) {
			this.userid = userid;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getNickname() {
			return nickname;
		}

		public void setNickname(String nickname) {
			this.nickname = nickname;
		}

		public String getPassport() {
			return passport;
		}

		public void setPassport(String passport) {
			this.passport = passport;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getPayword() {
			return payword;
		}

		public void setPayword(String payword) {
			this.payword = payword;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getMobile() {
			return mobile;
		}

		public void setMobile(String mobile) {
			this.mobile = mobile;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

		public String getUtype() {
			return utype;
		}

		public void setUtype(String utype) {
			this.utype = utype;
		}

		public String getTop() {
			return top;
		}

		public void setTop(String top) {
			this.top = top;
		}

		public String getHidden() {
			return hidden;
		}

		public void setHidden(String hidden) {
			this.hidden = hidden;
		}

		public String getOnline() {
			return online;
		}

		public void setOnline(String online) {
			this.online = online;
		}

		public String getAvatar() {
			return avatar;
		}

		public void setAvatar(String avatar) {
			this.avatar = avatar;
		}

		public String getAvatarurl() {
			return avatarurl;
		}

		public void setAvatarurl(String avatarurl) {
			this.avatarurl = avatarurl;
		}

		public String getGender() {
			return gender;
		}

		public void setGender(String gender) {
			this.gender = gender;
		}

		public String getTruename() {
			return truename;
		}

		public void setTruename(String truename) {
			this.truename = truename;
		}

		public String getIdcard() {
			return idcard;
		}

		public void setIdcard(String idcard) {
			this.idcard = idcard;
		}

		public String getMsn() {
			return msn;
		}

		public void setMsn(String msn) {
			this.msn = msn;
		}

		public String getQq() {
			return qq;
		}

		public void setQq(String qq) {
			this.qq = qq;
		}

		public String getAli() {
			return ali;
		}

		public void setAli(String ali) {
			this.ali = ali;
		}

		public String getSkype() {
			return skype;
		}

		public void setSkype(String skype) {
			this.skype = skype;
		}

		public String getImgurl() {
			return imgurl;
		}

		public void setImgurl(String imgurl) {
			this.imgurl = imgurl;
		}

		public String getCareer() {
			return career;
		}

		public void setCareer(String career) {
			this.career = career;
		}

		public String getAdmin() {
			return admin;
		}

		public void setAdmin(String admin) {
			this.admin = admin;
		}

		public String getRole() {
			return role;
		}

		public void setRole(String role) {
			this.role = role;
		}

		public String getAid() {
			return aid;
		}

		public void setAid(String aid) {
			this.aid = aid;
		}

		public String getGrouptype() {
			return grouptype;
		}

		public void setGrouptype(String grouptype) {
			this.grouptype = grouptype;
		}

		public String getRegid() {
			return regid;
		}

		public void setRegid(String regid) {
			this.regid = regid;
		}

		public String getAreaid() {
			return areaid;
		}

		public void setAreaid(String areaid) {
			this.areaid = areaid;
		}

		public String getSms() {
			return sms;
		}

		public void setSms(String sms) {
			this.sms = sms;
		}

		public String getCredit() {
			return credit;
		}

		public void setCredit(String credit) {
			this.credit = credit;
		}

		public String getMoney() {
			return money;
		}

		public void setMoney(String money) {
			this.money = money;
		}

		public String getLocking() {
			return locking;
		}

		public void setLocking(String locking) {
			this.locking = locking;
		}

		public String getBank() {
			return bank;
		}

		public void setBank(String bank) {
			this.bank = bank;
		}

		public String getAccount() {
			return account;
		}

		public void setAccount(String account) {
			this.account = account;
		}

		public String getEdittime() {
			return edittime;
		}

		public void setEdittime(String edittime) {
			this.edittime = edittime;
		}

		public String getRegip() {
			return regip;
		}

		public void setRegip(String regip) {
			this.regip = regip;
		}

		public String getRegtime() {
			return regtime;
		}

		public void setRegtime(String regtime) {
			this.regtime = regtime;
		}

		public String getLoginip() {
			return loginip;
		}

		public void setLoginip(String loginip) {
			this.loginip = loginip;
		}

		public String getLogintime() {
			return logintime;
		}

		public void setLogintime(String logintime) {
			this.logintime = logintime;
		}

		public String getLogintimes() {
			return logintimes;
		}

		public void setLogintimes(String logintimes) {
			this.logintimes = logintimes;
		}

		public String getBlack() {
			return black;
		}

		public void setBlack(String black) {
			this.black = black;
		}

		public String getSend() {
			return send;
		}

		public void setSend(String send) {
			this.send = send;
		}

		public String getAuth() {
			return auth;
		}

		public void setAuth(String auth) {
			this.auth = auth;
		}

		public String getAuthvalue() {
			return authvalue;
		}

		public void setAuthvalue(String authvalue) {
			this.authvalue = authvalue;
		}

		public String getAuthtime() {
			return authtime;
		}

		public void setAuthtime(String authtime) {
			this.authtime = authtime;
		}

		public String getVemail() {
			return vemail;
		}

		public void setVemail(String vemail) {
			this.vemail = vemail;
		}

		public String getVmobile() {
			return vmobile;
		}

		public void setVmobile(String vmobile) {
			this.vmobile = vmobile;
		}

		public String getVtruename() {
			return vtruename;
		}

		public void setVtruename(String vtruename) {
			this.vtruename = vtruename;
		}

		public String getVsummary() {
			return vsummary;
		}

		public void setVsummary(String vsummary) {
			this.vsummary = vsummary;
		}

		public String getVbank() {
			return vbank;
		}

		public void setVbank(String vbank) {
			this.vbank = vbank;
		}

		public String getVcompany() {
			return vcompany;
		}

		public void setVcompany(String vcompany) {
			this.vcompany = vcompany;
		}

		public String getVtrade() {
			return vtrade;
		}

		public void setVtrade(String vtrade) {
			this.vtrade = vtrade;
		}

		public String getTrade() {
			return trade;
		}

		public void setTrade(String trade) {
			this.trade = trade;
		}

		public String getSupport() {
			return support;
		}

		public void setSupport(String support) {
			this.support = support;
		}

		public String getInviter() {
			return inviter;
		}

		public void setInviter(String inviter) {
			this.inviter = inviter;
		}

		public String getSource() {
			return source;
		}

		public void setSource(String source) {
			this.source = source;
		}

		public String getProvince() {
			return province;
		}

		public void setProvince(String province) {
			this.province = province;
		}

		public String getCity() {
			return city;
		}

		public void setCity(String city) {
			this.city = city;
		}

		public String getArea() {
			return area;
		}

		public void setArea(String area) {
			this.area = area;
		}

		public String getCompany() {
			return company;
		}

		public void setCompany(String company) {
			this.company = company;
		}

		public String getCompanypicurl() {
			return companypicurl;
		}

		public void setCompanypicurl(String companypicurl) {
			this.companypicurl = companypicurl;
		}

		public String getCompanytype() {
			return companytype;
		}

		public void setCompanytype(String companytype) {
			this.companytype = companytype;
		}

		public String getLawfirm() {
			return lawfirm;
		}

		public void setLawfirm(String lawfirm) {
			this.lawfirm = lawfirm;
		}

		public String getLawcode() {
			return lawcode;
		}

		public void setLawcode(String lawcode) {
			this.lawcode = lawcode;
		}

		public String getLawcode2() {
			return lawcode2;
		}

		public void setLawcode2(String lawcode2) {
			this.lawcode2 = lawcode2;
		}

		public String getLawzc() {
			return lawzc;
		}

		public void setLawzc(String lawzc) {
			this.lawzc = lawzc;
		}

		public String getTelphone() {
			return telphone;
		}

		public void setTelphone(String telphone) {
			this.telphone = telphone;
		}

		public String getFronturl() {
			return fronturl;
		}

		public void setFronturl(String fronturl) {
			this.fronturl = fronturl;
		}

		public String getReverseurl() {
			return reverseurl;
		}

		public void setReverseurl(String reverseurl) {
			this.reverseurl = reverseurl;
		}

		public String getBthumb() {
			return bthumb;
		}

		public void setBthumb(String bthumb) {
			this.bthumb = bthumb;
		}

		public String getCthumb() {
			return cthumb;
		}

		public void setCthumb(String cthumb) {
			this.cthumb = cthumb;
		}

		public String getHot() {
			return hot;
		}

		public void setHot(String hot) {
			this.hot = hot;
		}

		public String getTplid() {
			return tplid;
		}

		public void setTplid(String tplid) {
			this.tplid = tplid;
		}

		public String getDeleted() {
			return deleted;
		}

		public void setDeleted(String deleted) {
			this.deleted = deleted;
		}

		public String getIntr() {
			return intr;
		}

		public void setIntr(String intr) {
			this.intr = intr;
		}

		public String getContact() {
			return contact;
		}

		public void setContact(String contact) {
			this.contact = contact;
		}

		public String getUtime() {
			return utime;
		}

		public void setUtime(String utime) {
			this.utime = utime;
		}

		public String getHits() {
			return hits;
		}

		public void setHits(String hits) {
			this.hits = hits;
		}

		public String getReasons() {
			return reasons;
		}

		public void setReasons(String reasons) {
			this.reasons = reasons;
		}

		public String getFrozen_money() {
			return frozen_money;
		}

		public void setFrozen_money(String frozen_money) {
			this.frozen_money = frozen_money;
		}

		public String getMtplid() {
			return mtplid;
		}

		public void setMtplid(String mtplid) {
			this.mtplid = mtplid;
		}

		public String getWithdraw() {
			return withdraw;
		}

		public void setWithdraw(String withdraw) {
			this.withdraw = withdraw;
		}

		public String getFwshop_type() {
			return fwshop_type;
		}

		public void setFwshop_type(String fwshop_type) {
			this.fwshop_type = fwshop_type;
		}

		public String getFwshop_keyword() {
			return fwshop_keyword;
		}

		public void setFwshop_keyword(String fwshop_keyword) {
			this.fwshop_keyword = fwshop_keyword;
		}

		public String getBe_door() {
			return be_door;
		}

		public void setBe_door(String be_door) {
			this.be_door = be_door;
		}

		public String getThumb_list() {
			return thumb_list;
		}

		public void setThumb_list(String thumb_list) {
			this.thumb_list = thumb_list;
		}

		public String getUcenter_bg() {
			return ucenter_bg;
		}

		public void setUcenter_bg(String ucenter_bg) {
			this.ucenter_bg = ucenter_bg;
		}

		public String getUcenter_menu() {
			return ucenter_menu;
		}

		public void setUcenter_menu(String ucenter_menu) {
			this.ucenter_menu = ucenter_menu;
		}

		public String getDuty() {
			return duty;
		}

		public void setDuty(String duty) {
			this.duty = duty;
		}

		public String getIs_safe() {
			return is_safe;
		}

		public void setIs_safe(String is_safe) {
			this.is_safe = is_safe;
		}

		public String getCollect_num() {
			return collect_num;
		}

		public void setCollect_num(String collect_num) {
			this.collect_num = collect_num;
		}

		public String getPlace() {
			return place;
		}

		public void setPlace(String place) {
			this.place = place;
		}

		public String getFwshop_type_name() {
			return fwshop_type_name;
		}

		public void setFwshop_type_name(String fwshop_type_name) {
			this.fwshop_type_name = fwshop_type_name;
		}
	}
}
