/******************************************************************************
 * Copyright (C) 2017 ShenZhen hhyy Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳华海乐盈开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
 * 复制、修改或发布本软件.
 ******************************************************************************/
package com.springboot.jpa.system.controller;

import com.springboot.jpa.system.entity.SysUser;
import com.springboot.jpa.system.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
public class SysUserController {
	
	/**
	 * 日志记录
	 */
	private Logger logger = LoggerFactory.getLogger(SysUserController.class);
	

    @Autowired
    private SysUserService sysUserService;

	/**
	 * 查询系统用户，不带分页
	 * @param sysUser 系统用户对象
	 * @return
	 */
	@RequestMapping(value="/list")
	@ApiOperation(value="查询系统用户列表", notes="根据条件查询系统用户列表，不分页",httpMethod = "POST", response = List.class)
	public List<SysUser> list(SysUser sysUser){
		return sysUserService.getList(sysUser);
	}



}
