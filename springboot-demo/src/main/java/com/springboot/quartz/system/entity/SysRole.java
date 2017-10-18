/******************************************************************************
 * Copyright (C) 2017 ShenZhen hhyy Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳华海乐盈开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
 * 复制、修改或发布本软件.
 ******************************************************************************/
package com.springboot.quartz.system.entity;

import java.util.*;
import javax.persistence.*;

import com.springboot.quartz.base.BaseEntry;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 
 * 系统角色实体类
 * @author jiasx
 * @since 1.0
 * @version 2017-07-06 jiasx
 */
@Entity
@Table(name = "t_sys_role")
@ApiModel(value="系统角色对象")
public class SysRole extends BaseEntry{
    

	/** 1、启用 0、禁用*/
	@Column(name = "state")
	@ApiModelProperty(value="1、启用 0、禁用" ,dataType="Boolean")
	private Boolean state;
	
	
	/** 角色名称*/
	@Column(name = "role_code")
	@ApiModelProperty(value="角色编码" ,dataType="String")
	private String roleCode;

	/** 角色名称*/
	@Column(name = "role_name")
	@ApiModelProperty(value="角色名称" ,dataType="String")
	private String roleName;

	
	/** 角色描述*/
	@Column(name = "description")
	@ApiModelProperty(value="角色描述" ,dataType="String")
	private String description;


	//菜单/权限列表
	@Transient
	private List<SysPermission> permissions;
	



	
	
	/**
     * 1、启用 0、禁用 get方法
     * @return 获取1、启用 0、禁用
     */
	public Boolean getState() {
		return this.state;
	}
	
	/**
     * 1、启用 0、禁用 set方法
     * @param state 1、启用 0、禁用
     */
	public void setState(Boolean state) {
		this.state = state;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	/**
     * 角色描述 get方法
     * @return 获取角色描述
     */
	public String getDescription() {
		return this.description;
	}
	
	/**
     * 角色描述 set方法
     * @param description 角色描述
     */
	public void setDescription(String description) {
		this.description = description;
	}

	public List<SysPermission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<SysPermission> permissions) {
		this.permissions = permissions;
	}

	/**
	 * 重写String方法
	 *
	 * @return 字符串
	 */
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("State",getState())
			.append("RoleCode",getRoleCode())
			.append("RoleName",getRoleName())
			.append("Description",getDescription())
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
		if(obj instanceof SysRole == false) return false;
		if(this == obj) return true;
		SysRole other = (SysRole)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}
