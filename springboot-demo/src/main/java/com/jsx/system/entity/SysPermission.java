/******************************************************************************
 * Copyright (C) 2017 ShenZhen hhyy Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳华海乐盈开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
 * 复制、修改或发布本软件.
 ******************************************************************************/
package com.jsx.system.entity;

import java.util.*;
import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.jsx.base.BaseEntry;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * 系统权限表实体类
 * @author jiasx
 * @since 1.0
 * @version 2017-07-06 jiasx
 */
@Entity
@Table(name = "t_sys_permission")
@ApiModel(value="系统权限表对象")
public class SysPermission extends BaseEntry{
    
	/** 1、启用 0、禁用*/
	@Column(name = "state")
	@ApiModelProperty(value="1、启用 0、禁用" ,dataType="Boolean")
	private Boolean state;
	
	
	/** 权限名称*/
	@Column(name = "permission_name")
	@ApiModelProperty(value="权限名称" ,dataType="String")
	private String permissionName;


	/** permission*/
	@Column(name = "permission_code")
	@ApiModelProperty(value="权限编码" ,dataType="String")
	private String permissionCode;
	
	
	/** 权限父ID*/
	@Column(name = "parent_id")
	@ApiModelProperty(value="权限父ID" ,dataType="java.lang.Long")
	private Long parentId;

	
	
	/** resourceType*/
	@Column(name = "resourceType")
	@ApiModelProperty(value="资源类型" ,dataType="String")
	private String resourceType;
	
	
	/** url*/
	@Column(name = "url")
	@ApiModelProperty(value="url地址" ,dataType="String")
	private String url;
	



	
	
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
			
	
	/**
     * 权限父ID get方法
     * @return 获取权限父ID
     */
	public Long getParentId() {
		return this.parentId;
	}
	
	/**
     * 权限父ID set方法
     * @param parentId 权限父ID
     */
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}


	public String getPermissionName() {
		return permissionName;
	}

	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}

	public String getPermissionCode() {
		return permissionCode;
	}

	public void setPermissionCode(String permissionCode) {
		this.permissionCode = permissionCode;
	}

	/**
     * resourceType get方法
     * @return 获取resourceType
     */
	public String getResourceType() {
		return this.resourceType;
	}
	
	/**
     * resourceType set方法
     * @param resourceType resourceType
     */
	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}
			
	
	/**
     * url get方法
     * @return 获取url
     */
	public String getUrl() {
		return this.url;
	}
	
	/**
     * url set方法
     * @param url url
     */
	public void setUrl(String url) {
		this.url = url;
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
			.append("PermissionName",getPermissionName())
			.append("ParentId",getParentId())
			.append("PermissionCode",getPermissionCode())
			.append("ResourceType",getResourceType())
			.append("Url",getUrl())
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
		if(obj instanceof SysPermission == false) return false;
		if(this == obj) return true;
		SysPermission other = (SysPermission)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}
