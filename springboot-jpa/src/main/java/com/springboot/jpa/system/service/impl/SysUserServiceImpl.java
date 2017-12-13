/******************************************************************************
 * Copyright (C) 2017 ShenZhen hhyy Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳华海乐盈开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
 * 复制、修改或发布本软件.
 ******************************************************************************/
package com.springboot.jpa.system.service.impl;

import com.springboot.jpa.system.dao.jpa.SysUserRepository;
import com.springboot.jpa.system.dao.session.SysUserDao;
import com.springboot.jpa.system.entity.SysUser;
import com.springboot.jpa.system.service.SysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 
 * 系统用户 服务实现
 * @author jiasx
 * @since 1.0
 * @version 2017-07-06 jiasx
 */
@Service
public class SysUserServiceImpl implements SysUserService {
	
	/**
	 * 日志记录
	 */
	private Logger logger = LoggerFactory.getLogger(SysUserServiceImpl.class);
	
	@Autowired
    private SysUserRepository sysUserRepository;

	@Autowired
    private SysUserDao sysUserDao;

    /**
     * 查询系统用户,带分页
     * @param sysUser
     * @return
     */
    @Override
    public List<SysUser> getList(SysUser sysUser){
    	return sysUserDao.getUsers();
//    	return sysUserRepository.findAll();
    }


}
