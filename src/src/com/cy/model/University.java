package com.cy.model;

import java.io.Serializable;
import org.apache.ibatis.type.Alias;

/**
 * 实体类 - 表：t_university
 * @since 2015-08-04 14:17:10
 */
@Alias("University")
public class University implements Serializable {
	/** university_id -- 学校ID->学校表主键 */
	private String universityId;

	/** university_name -- 学校名称 */
	private String universityName;

	/** university_logo -- 大学图标 */
	private String universityLogo;

	/** classify -- 学校类别 */
	private String classify;

	/** website -- 学校网址 */
	private String website;

	/** phone -- 电话 */
	private String phone;

	/** fax -- 传真 */
	private String fax;

	/** email -- 电邮 */
	private String email;

	/** address -- 地址 */
	private String address;

	/** introduction -- 简介 */
	private String introduction;

	private static final long serialVersionUID = 1L;

	/** 获取学校ID->学校表主键 */
	public String getUniversityId() {
		return universityId;
	}

	/** 设置学校ID->学校表主键 */
	public void setUniversityId(String universityId) {
		this.universityId = universityId == null ? null : universityId.trim();
	}

	/** 获取学校名称 */
	public String getUniversityName() {
		return universityName;
	}

	/** 设置学校名称 */
	public void setUniversityName(String universityName) {
		this.universityName = universityName == null ? null : universityName.trim();
	}

	/** 获取大学图标 */
	public String getUniversityLogo() {
		return universityLogo;
	}

	/** 设置大学图标 */
	public void setUniversityLogo(String universityLogo) {
		this.universityLogo = universityLogo == null ? null : universityLogo.trim();
	}

	/** 获取学校类别 */
	public String getClassify() {
		return classify;
	}

	/** 设置学校类别 */
	public void setClassify(String classify) {
		this.classify = classify == null ? null : classify.trim();
	}

	/** 获取学校网址 */
	public String getWebsite() {
		return website;
	}

	/** 设置学校网址 */
	public void setWebsite(String website) {
		this.website = website == null ? null : website.trim();
	}

	/** 获取电话 */
	public String getPhone() {
		return phone;
	}

	/** 设置电话 */
	public void setPhone(String phone) {
		this.phone = phone == null ? null : phone.trim();
	}

	/** 获取传真 */
	public String getFax() {
		return fax;
	}

	/** 设置传真 */
	public void setFax(String fax) {
		this.fax = fax == null ? null : fax.trim();
	}

	/** 获取电邮 */
	public String getEmail() {
		return email;
	}

	/** 设置电邮 */
	public void setEmail(String email) {
		this.email = email == null ? null : email.trim();
	}

	/** 获取地址 */
	public String getAddress() {
		return address;
	}

	/** 设置地址 */
	public void setAddress(String address) {
		this.address = address == null ? null : address.trim();
	}

	/** 获取简介 */
	public String getIntroduction() {
		return introduction;
	}

	/** 设置简介 */
	public void setIntroduction(String introduction) {
		this.introduction = introduction == null ? null : introduction.trim();
	}

	/**
	
	 * @since 2015-08-04 14:17:10
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName());
		sb.append(" [");
		sb.append("Hash = ").append(hashCode());
		sb.append(", universityId=").append(universityId);
		sb.append(", universityName=").append(universityName);
		sb.append(", universityLogo=").append(universityLogo);
		sb.append(", classify=").append(classify);
		sb.append(", website=").append(website);
		sb.append(", phone=").append(phone);
		sb.append(", fax=").append(fax);
		sb.append(", email=").append(email);
		sb.append(", address=").append(address);
		sb.append(", introduction=").append(introduction);
		sb.append(", serialVersionUID=").append(serialVersionUID);
		sb.append("]");
		return sb.toString();
	}
}