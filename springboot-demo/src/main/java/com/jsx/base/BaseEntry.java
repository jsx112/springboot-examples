package com.jsx.base;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 实体类
 * @author jiasx
 * @since 1.0
 * @version 2017-07-06 jiasx
 */
@MappedSuperclass
public class BaseEntry implements Serializable{
    
	/** FXM */
    private static final long serialVersionUID = 5454155825314635343L;

	/** 主键*/
    @Id
	@Column(name = "id")
	@GeneratedValue(generator = "globalIdGenerator")
	@GenericGenerator(name = "globalIdGenerator", strategy = "com.jsx.base.GlobalIdGenerator")
	@ApiModelProperty(value="主键Id" ,dataType="String")
	protected Long id;
	

	/** 创建用户*/
	@Column(name = "create_uid")
	@ApiModelProperty(value="创建用户" ,dataType="Integer")
	protected Long createUid;



	/** 创建时间开始时间*/
	@Transient
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value="创建时间开始，仅用在查询中" ,dataType="Date")
	protected Date createTimeBegin;

	/** 创建时间结束时间*/
	@Transient
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value="创建时间结束，仅用在查询中" ,dataType="Date")
	protected Date createTimeEnd;
	
	/** 创建时间*/
	@Column(name = "create_time")
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value="创建时间" ,dataType="Date")
	protected Date createTime;
	
	
	
	/** 更新用户*/
	@Column(name = "update_uid")
	@ApiModelProperty(value="更新用户" ,dataType="String")
	protected Long updateUid;



	/** 更新时间开始时间*/
	@Transient
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value="更新时间开始，仅用在查询中" ,required=false,dataType="Date")
	protected Date updateTimeBegin;

	/** 更新时间结束时间*/
	@Transient
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value="更新时间结束，仅用在查询中" ,required=false,dataType="Date")
	protected Date updateTimeEnd;
	
	/** 更新时间*/
	@Column(name = "update_time")
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value="更新时间" ,dataType="Date")
	protected Date updateTime;


	/** 页码*/
	@Transient
	protected int pageNum = 1;

	/** 每页大小*/
	@Transient
	protected int pageSize = 20;



    /**
     * 主键 set方法
     * @param id 主键
     */
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
     * 主键 get方法
     * @return 获取主键
     */
	public Long getId() {
		return this.id;
	}


	/**
     * 创建用户 get方法
     * @return 获取创建用户
     */
	public Long getCreateUid() {
		return this.createUid;
	}
	
	/**
     * 创建用户 set方法
     * @param createUid 创建用户
     */
	public void setCreateUid(Long createUid) {
		this.createUid = createUid;
	}



    /**
     * 创建时间开始时间 get方法
     * @return 获取创建时间
     */
	public Date getCreateTimeBegin() {
		return this.createTimeBegin;
	}

	/**
	 * 创建时间创建时间开始时间 set方法
	 * @param createTimeBegin 创建时间开始时间
	 */
	public void setCreateTimeBegin(Date createTimeBegin) {
		this.createTimeBegin = createTimeBegin;
	}

	/**
	 * 创建时间开始时间 get方法
	 * @return 获取创建时间
	 */
	public Date getCreateTimeEnd() {
		return this.createTimeEnd;
	}

	/**
	 * 创建时间结束时间 set方法
	 * @param createTimeEnd 创建时间结束时间
	 */
	public void setCreateTimeEnd(Date createTimeEnd) {
		this.createTimeEnd = createTimeEnd;
	}

	/**
     * 创建时间 get方法
     * @return 获取创建时间
     */
	public Date getCreateTime() {
		return this.createTime;
	}
	
	/**
     * 创建时间 set方法
     * @param createTime 创建时间
     */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
			
	
	/**
     * 更新用户 get方法
     * @return 获取更新用户
     */
	public Long getUpdateUid() {
		return this.updateUid;
	}
	
	/**
     * 更新用户 set方法
     * @param updateUid 更新用户
     */
	public void setUpdateUid(Long updateUid) {
		this.updateUid = updateUid;
	}



    /**
     * 更新时间开始时间 get方法
     * @return 获取更新时间
     */
	public Date getUpdateTimeBegin() {
		return this.updateTimeBegin;
	}

	/**
	 * 更新时间开始时间 set方法
	 * @param updateTimeBegin 更新时间开始时间
	 */
	public void setUpdateTimeBegin(Date updateTimeBegin) {
		this.updateTimeBegin = updateTimeBegin;
	}


	/**
	 * 更新时间开始时间 get方法
	 * @return 获取更新时间
	 */
	public Date getUpdateTimeEnd() {
		return this.updateTimeEnd;
	}

	/**
	 * 更新时间结束时间 set方法
	 * @param updateTimeEnd 更新时间结束时间
	 */
	public void setUpdateTimeEnd(Date updateTimeEnd) {
		this.updateTimeEnd = updateTimeEnd;
	}

	/**
     * 更新时间 get方法
     * @return 获取更新时间
     */
	public Date getUpdateTime() {
		return this.updateTime;
	}
	
	/**
     * 更新时间 set方法
     * @param updateTime 更新时间
     */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
}
