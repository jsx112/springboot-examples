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
import com.jsx.system.entity.SysUser;
import com.jsx.system.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * 系统用户 rest服务
 * @author jiasx
 * @since 1.0
 * @version 2017-07-06 jiasx
 */
@RestController
@RequestMapping("/user")
@Api(tags = {"系统用户"},description="系统用户服务")
public class SysUserController extends BaseController {
	
	/**
	 * 日志记录
	 */
	private Logger logger = LoggerFactory.getLogger(SysUserController.class);
	

    @Autowired
    private SysUserService sysUserService;
    
    /**
     * 查询系统用户,带分页
     * @param sysUser 系统用户对象
     * @return
     */
    @RequestMapping("/count")
    @ApiOperation(value="查询系统用户数量", notes="根据条件查询系统用户数量",httpMethod = "POST", response = Long.class)
    public long getTotalCount(SysUser sysUser){
    	return sysUserService.getTotalCount(sysUser);
    }
    
    /**
     * 查询系统用户,带分页
     * @param sysUser 系统用户对象
     * @return
     */
    @RequestMapping("/list")
    @ApiOperation(value="查询系统用户列表", notes="根据条件查询系统用户列表",httpMethod = "POST", response = PageInfo.class)
    public PageInfo<SysUser> getList(SysUser sysUser){
    	PageHelper.startPage(sysUser.getPageNum(), sysUser.getPageSize(), true);
        List<SysUser> list = sysUserService.getList(sysUser);
        PageInfo<SysUser> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }
    

	/**
	 * 查询系统用户，不带分页
	 * @param sysUser 系统用户对象
	 * @return
	 */
	@RequestMapping(value="/nopagelist")
	@ApiOperation(value="查询系统用户列表", notes="根据条件查询系统用户列表，不分页",httpMethod = "POST", response = List.class)
	public List<SysUser> getNoPageList(SysUser sysUser){
		PageHelper.startPage(1,0);
		return sysUserService.getList(sysUser);
	}

    /**
     * 新增系统用户
     * @param sysUser 系统用户对象
     */
    @RequestMapping("/insert")
    @ApiOperation(value="新增系统用户", notes="新增系统用户，必填项不能为空",httpMethod = "POST", response = String.class)
    public String insert(SysUser sysUser) {
//		sysUser.setId(Utils.generateId());
		sysUser.setCreateUid(getUserId());
		sysUser.setCreateTime(new Date());
		sysUser.setUpdateUid(getUserId());
		sysUser.setUpdateTime(new Date());
        sysUserService.insert(sysUser);
        //新增缓存
        
        return ok();
    }

    /**
     * 修改系统用户
     * @param sysUser 系统用户对象
     */
    @RequestMapping("/update")
    @ApiOperation(value="更新系统用户", notes="更新系统用户，需要主键Id，必填项不能为空",httpMethod = "POST", response = String.class)
    public String update(SysUser sysUser) {
    	SysUser dbSysUser = sysUserService.getById(sysUser.getId());
    	if(dbSysUser==null || StringUtil.hasNull(dbSysUser.getId())){
    		 throw new BizException(CODE.partner_data_not_exists);
        }
    	dbSysUser.setNickName(sysUser.getNickName());
    	dbSysUser.setAccount(sysUser.getAccount());
    	dbSysUser.setPassword(sysUser.getPassword());
    	dbSysUser.setSalt(sysUser.getSalt());
    	dbSysUser.setState(sysUser.getState());
    	dbSysUser.setType(sysUser.getType());
		dbSysUser.setUpdateUid(getUserId());
		dbSysUser.setUpdateTime(new Date());
    	sysUserService.update(dbSysUser);
    	//更新缓存
    	return ok();
    }

    /**
     * 根据Id查询系统用户
     * @param id 主键id
     */
    @RequestMapping("/get")
    @ApiOperation(value="获取系统用户对象", notes="根据主键Id获取系统用户对象",httpMethod = "POST", response = String.class)
    public SysUser getById(Long id){
		if(id == null){
			throw new BizException(CODE.checkNotNull);
		}
    	//先从缓存中查询，查询不到，再从数据库中查询
    	SysUser sysUser = sysUserService.getById(id);
        if(sysUser==null){
            throw new BizException(CODE.has_no_data);
        }
    	return sysUser;
    }

    /**
     * 删除系统用户
     * @param id 主键id
     */
    @RequestMapping("/delete")
    @ApiOperation(value="删除系统用户对象", notes="根据主键Id删除系统用户对象",httpMethod = "POST", response = String.class)
    public String deleteById(Long id) {
    	if(id == null){
            throw new BizException(CODE.checkNotNull);
        }
    	sysUserService.deleteById(id);
    	//需要加上删除redis的语句
        return  ok();
    }

}
