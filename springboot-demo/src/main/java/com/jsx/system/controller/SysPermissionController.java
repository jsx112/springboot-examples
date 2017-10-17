/******************************************************************************
 * Copyright (C) 2017 ShenZhen hhyy Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳华海乐盈开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
 * 复制、修改或发布本软件.
 ******************************************************************************/
package com.jsx.system.controller;

import java.util.*;
import java.sql.Timestamp;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jsx.base.BaseController;
import com.jsx.entry.CODE;
import com.jsx.exception.BizException;
import com.jsx.util.StringUtil;
import com.jsx.util.Utils;
import com.jsx.system.entity.SysPermission;
import com.jsx.system.service.SysPermissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * 系统权限表 rest服务
 * @author jiasx
 * @since 1.0
 * @version 2017-07-06 jiasx
 */
@RestController
@RequestMapping("/permission")
@Api(tags = {"系统权限表"},description="系统权限表服务")
public class SysPermissionController extends BaseController {
	
	/**
	 * 日志记录
	 */
	private Logger logger = LoggerFactory.getLogger(SysPermissionController.class);
	

    @Autowired
    private SysPermissionService sysPermissionService;
    
    /**
     * 查询系统权限表,带分页
     * @param sysPermission 系统权限表对象
     * @return
     */
    @RequestMapping("/count")
    @ApiOperation(value="查询系统权限表数量", notes="根据条件查询系统权限表数量",httpMethod = "POST", response = Long.class)
    public long getTotalCount(SysPermission sysPermission){
    	return sysPermissionService.getTotalCount(sysPermission);
    }
    
    /**
     * 查询系统权限表,带分页
     * @param sysPermission 系统权限表对象
     * @return
     */
    @RequestMapping("/list")
    @ApiOperation(value="查询系统权限表列表", notes="根据条件查询系统权限表列表",httpMethod = "POST", response = PageInfo.class)
    public PageInfo<SysPermission> getList(SysPermission sysPermission){
    	PageHelper.startPage(sysPermission.getPageNum(), sysPermission.getPageSize(), true);
        List<SysPermission> list = sysPermissionService.getList(sysPermission);
        PageInfo<SysPermission> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }
    

	/**
	 * 查询系统权限表，不带分页
	 * @param sysPermission 系统权限表对象
	 * @return
	 */
	@RequestMapping(value="/nopagelist")
	@ApiOperation(value="查询系统权限表列表", notes="根据条件查询系统权限表列表，不分页",httpMethod = "POST", response = List.class)
	public List<SysPermission> getNoPageList(SysPermission sysPermission){
		PageHelper.startPage(1,0);
		return sysPermissionService.getList(sysPermission);
	}

    /**
     * 新增系统权限表
     * @param sysPermission 系统权限表对象
     */
    @RequestMapping("/insert")
    @ApiOperation(value="新增系统权限表", notes="新增系统权限表，必填项不能为空",httpMethod = "POST", response = String.class)
    public String insert(SysPermission sysPermission) {
		sysPermission.setId(Utils.generateId());
		sysPermission.setCreateUid(getUserId());
		sysPermission.setCreateTime(new Date());
		sysPermission.setUpdateUid(getUserId());
		sysPermission.setUpdateTime(new Date());
        sysPermissionService.insert(sysPermission);
        //新增缓存
        
        return ok();
    }

    /**
     * 修改系统权限表
     * @param sysPermission 系统权限表对象
     */
    @RequestMapping("/update")
    @ApiOperation(value="更新系统权限表", notes="更新系统权限表，需要主键Id，必填项不能为空",httpMethod = "POST", response = String.class)
    public String update(SysPermission sysPermission) {
    	SysPermission dbSysPermission = sysPermissionService.getById(sysPermission.getId());
    	if(dbSysPermission==null || StringUtil.hasNull(dbSysPermission.getId())){
    		 throw new BizException(CODE.partner_data_not_exists);
        }
    	dbSysPermission.setState(sysPermission.getState());
    	dbSysPermission.setPermissionName(sysPermission.getPermissionName());
    	dbSysPermission.setPermissionCode(sysPermission.getPermissionCode());
    	dbSysPermission.setParentId(sysPermission.getParentId());
    	dbSysPermission.setResourceType(sysPermission.getResourceType());
    	dbSysPermission.setUrl(sysPermission.getUrl());
		dbSysPermission.setUpdateUid(getUserId());
		dbSysPermission.setUpdateTime(new Date());
    	sysPermissionService.update(dbSysPermission);
    	//更新缓存
    	return ok();
    }

    /**
     * 根据Id查询系统权限表
     * @param id 主键id
     */
    @RequestMapping("/get")
    @ApiOperation(value="获取系统权限表对象", notes="根据主键Id获取系统权限表对象",httpMethod = "POST", response = String.class)
    public SysPermission getById(Long id){
		if(id == null){
			throw new BizException(CODE.checkNotNull);
		}
    	//先从缓存中查询，查询不到，再从数据库中查询
    	SysPermission sysPermission = sysPermissionService.getById(id);
        if(sysPermission==null){
            throw new BizException(CODE.has_no_data);
        }
    	return sysPermission;
    }

    /**
     * 删除系统权限表
     * @param id 主键id
     */
    @RequestMapping("/delete")
    @ApiOperation(value="删除系统权限表对象", notes="根据主键Id删除系统权限表对象",httpMethod = "POST", response = String.class)
    public String deleteById(Long id) {
    	if(id == null){
            throw new BizException(CODE.checkNotNull);
        }
    	sysPermissionService.deleteById(id);
    	//需要加上删除redis的语句
        return  ok();
    }

}
