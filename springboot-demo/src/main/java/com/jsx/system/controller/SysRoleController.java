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
import com.jsx.system.entity.SysRole;
import com.jsx.system.service.SysRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * 系统角色 rest服务
 * @author jiasx
 * @since 1.0
 * @version 2017-07-06 jiasx
 */
@RestController
@RequestMapping("/role")
@Api(tags = {"系统角色"},description="系统角色服务")
public class SysRoleController extends BaseController {
	
	/**
	 * 日志记录
	 */
	private Logger logger = LoggerFactory.getLogger(SysRoleController.class);
	

    @Autowired
    private SysRoleService sysRoleService;
    
    /**
     * 查询系统角色,带分页
     * @param sysRole 系统角色对象
     * @return
     */
    @RequestMapping("/count")
    @ApiOperation(value="查询系统角色数量", notes="根据条件查询系统角色数量",httpMethod = "POST", response = Long.class)
    public long getTotalCount(SysRole sysRole){
    	return sysRoleService.getTotalCount(sysRole);
    }
    
    /**
     * 查询系统角色,带分页
     * @param sysRole 系统角色对象
     * @return
     */
    @RequestMapping("/list")
    @ApiOperation(value="查询系统角色列表", notes="根据条件查询系统角色列表",httpMethod = "POST", response = PageInfo.class)
    public PageInfo<SysRole> getList(SysRole sysRole){
    	PageHelper.startPage(sysRole.getPageNum(), sysRole.getPageSize(), true);
        List<SysRole> list = sysRoleService.getList(sysRole);
        PageInfo<SysRole> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }
    

	/**
	 * 查询系统角色，不带分页
	 * @param sysRole 系统角色对象
	 * @return
	 */
	@RequestMapping(value="/nopagelist")
	@ApiOperation(value="查询系统角色列表", notes="根据条件查询系统角色列表，不分页",httpMethod = "POST", response = List.class)
	public List<SysRole> getNoPageList(SysRole sysRole){
		PageHelper.startPage(1,0);
		return sysRoleService.getList(sysRole);
	}

    /**
     * 新增系统角色
     * @param sysRole 系统角色对象
     */
    @RequestMapping("/insert")
    @ApiOperation(value="新增系统角色", notes="新增系统角色，必填项不能为空",httpMethod = "POST", response = String.class)
    public String insert(SysRole sysRole) {
		sysRole.setCreateUid(getUserId());
		sysRole.setCreateTime(new Date());
		sysRole.setUpdateUid(getUserId());
		sysRole.setUpdateTime(new Date());
        sysRoleService.insert(sysRole);
        //新增缓存
        
        return ok();
    }

    /**
     * 修改系统角色
     * @param sysRole 系统角色对象
     */
    @RequestMapping("/update")
    @ApiOperation(value="更新系统角色", notes="更新系统角色，需要主键Id，必填项不能为空",httpMethod = "POST", response = String.class)
    public String update(SysRole sysRole) {
    	SysRole dbSysRole = sysRoleService.getById(sysRole.getId());
    	if(dbSysRole==null || StringUtil.hasNull(dbSysRole.getId())){
    		 throw new BizException(CODE.partner_data_not_exists);
        }
    	dbSysRole.setState(sysRole.getState());
    	dbSysRole.setRoleCode(sysRole.getRoleCode());
    	dbSysRole.setRoleName(sysRole.getRoleName());
    	dbSysRole.setDescription(sysRole.getDescription());
		dbSysRole.setUpdateUid(getUserId());
		dbSysRole.setUpdateTime(new Date());
    	sysRoleService.update(dbSysRole);
    	//更新缓存
    	return ok();
    }

    /**
     * 根据Id查询系统角色
     * @param id 主键id
     */
    @RequestMapping("/get")
    @ApiOperation(value="获取系统角色对象", notes="根据主键Id获取系统角色对象",httpMethod = "POST", response = String.class)
    public SysRole getById(Long id){
		if(id == null){
			throw new BizException(CODE.checkNotNull);
		}
    	//先从缓存中查询，查询不到，再从数据库中查询
    	SysRole sysRole = sysRoleService.getById(id);
        if(sysRole==null){
            throw new BizException(CODE.has_no_data);
        }
    	return sysRole;
    }

    /**
     * 删除系统角色
     * @param id 主键id
     */
    @RequestMapping("/delete")
    @ApiOperation(value="删除系统角色对象", notes="根据主键Id删除系统角色对象",httpMethod = "POST", response = String.class)
    public String deleteById(Long id) {
    	if(id == null){
            throw new BizException(CODE.checkNotNull);
        }
    	sysRoleService.deleteById(id);
    	//需要加上删除redis的语句
        return  ok();
    }

}
