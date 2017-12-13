/******************************************************************************
 * Copyright (C) 2017 ShenZhen hhyy Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳华海乐盈开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
 * 复制、修改或发布本软件.
 ******************************************************************************/
package com.springboot.jpa.system.entity;

import com.springboot.jpa.base.BaseEntry;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;

/**
 * 
 * 系统用户实体类
 * @author jiasx
 * @since 1.0
 * @version 2017-07-06 jiasx
 */
@Entity
@Table(name = "t_sys_user")
@ApiModel(value="系统用户对象")
public class SysUser extends BaseEntry {
    

	/** 用户昵称*/
	@Column(name = "nick_name")
	@ApiModelProperty(value="用户昵称" ,dataType="String")
	private String nickName;
	
	
	/** 登录账号*/
	@Column(name = "account")
	@ApiModelProperty(value="登录账号" ,dataType="String")
	private String account;
	
	
	/** 登录密码*/
	@Column(name = "password")
	@ApiModelProperty(value="登录密码" ,dataType="String")
	private String password;
	
	
	/** 加密的盐*/
	@Column(name = "salt")
	@ApiModelProperty(value="加密的盐" ,dataType="String")
	private String salt;
	
	
	/** 1启用 0禁用*/
	@Column(name = "state")
	@ApiModelProperty(value="1启用 0禁用" ,dataType="Boolean")
	private Boolean state;
	
	/** 1、平台用户 2、企业用户 3、代理用户*/
	@Column(name = "type")
	@ApiModelProperty(value="1、平台用户 2、企业用户 3、代理用户" ,dataType="Integer")
	private Integer type;
	/**
     * 用户昵称 get方法
     * @return 获取用户昵称
     */
	public String getNickName() {
		return this.nickName;
	}
	
	/**
     * 用户昵称 set方法
     * @param nickName 用户昵称
     */
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
			
	
	/**
     * 登录账号 get方法
     * @return 获取登录账号
     */
	public String getAccount() {
		return this.account;
	}
	
	/**
     * 登录账号 set方法
     * @param account 登录账号
     */
	public void setAccount(String account) {
		this.account = account;
	}
			
	
	/**
     * 登录密码 get方法
     * @return 获取登录密码
     */
	public String getPassword() {
		return this.password;
	}
	
	/**
     * 登录密码 set方法
     * @param password 登录密码
     */
	public void setPassword(String password) {
		this.password = password;
	}
			
	
	/**
     * 加密的盐 get方法
     * @return 获取加密的盐
     */
	public String getSalt() {
		return this.salt;
	}
	
	/**
     * 加密的盐 set方法
     * @param salt 加密的盐
     */
	public void setSalt(String salt) {
		this.salt = salt;
	}
			
	
	/**
     * 1启用 0禁用 get方法
     * @return 获取1启用 0禁用
     */
	public Boolean getState() {
		return this.state;
	}
	
	/**
     * 1启用 0禁用 set方法
     * @param state 1启用 0禁用
     */
	public void setState(Boolean state) {
		this.state = state;
	}
			
	/**
     * 1、平台用户 2、企业用户 3、代理用户 get方法
     * @return 获取1、平台用户 2、企业用户 3、代理用户
     */
	public Integer getType() {
		return this.type;
	}
	
	/**
     * 1、平台用户 2、企业用户 3、代理用户 set方法
     * @param type 1、平台用户 2、企业用户 3、代理用户
     */
	public void setType(Integer type) {
		this.type = type;
	}


	/**
	 * 重写String方法
	 *
	 * @return 字符串
	 */
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("NickName",getNickName())
			.append("Account",getAccount())
			.append("Password",getPassword())
			.append("Salt",getSalt())
			.append("State",getState())
			.append("Type",getType())
			.append("CreateTime",getCreateTime())
			.append("CreateUid",getCreateUid())
			.append("UpdateTime",getUpdateTime())
			.append("UpdateUid",getUpdateUid())
			.toString();
	}
	
	/**
	 * 重写hashCode方法
	 *
	 * @return hashCode
	 */
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	/**
	 * 重写比较方法
	 *
	 * @param obj 对象
	 * @return true/false
	 */
	public boolean equals(Object obj) {
		if(obj instanceof SysUser == false) return false;
		if(this == obj) return true;
		SysUser other = (SysUser)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}
