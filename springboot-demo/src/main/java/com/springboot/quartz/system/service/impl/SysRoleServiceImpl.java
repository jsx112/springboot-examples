/******************************************************************************
 * Copyright (C) 2017 ShenZhen hhyy Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳华海乐盈开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
 * 复制、修改或发布本软件.
 ******************************************************************************/
package com.springboot.quartz.system.service.impl;

import java.util.*;

import com.springboot.quartz.system.dao.jpa.SysRoleRepository;
import com.springboot.quartz.system.dao.mybatis.SysRoleDao;
import com.springboot.quartz.system.entity.SysRole;
import com.springboot.quartz.system.service.SysRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 * 系统角色 服务实现
 * @author jiasx
 * @since 1.0
 * @version 2017-07-06 jiasx
 */
@Service
public class SysRoleServiceImpl implements SysRoleService {
	
	/**
	 * 日志记录
	 */
	private Logger logger = LoggerFactory.getLogger(SysRoleServiceImpl.class);
	
	@Autowired
    private SysRoleRepository sysRoleRepository;
    
    @Autowired
    private SysRoleDao sysRoleDao;
    
    /**
     * 查询系统角色,带分页
     * @param sysRole
     * @return
     */
    public long getTotalCount(SysRole sysRole){
    	return sysRoleDao.getTotalCount(sysRole);
    }
    
    /**
     * 查询系统角色,带分页
     * @param sysRole
     * @return
     */
    public List<SysRole> getList(SysRole sysRole){
    	return sysRoleDao.getList(sysRole);
    }

    /**
     * 新增系统角色
     * @param sysRole
     */
    public void insert(SysRole sysRole) {
        sysRoleDao.insert(sysRole);
    }

    /**
     * 修改系统角色
     * @param sysRole
     */
    public void update(SysRole sysRole) {
    	sysRoleRepository.save(sysRole);
    }

    /**
     * 根据Id查询系统角色
     * @param id
     */
    public SysRole getById(Long id){
    	return sysRoleDao.getById(id);
    }

    /**
     * 删除系统角色
     * @param id
     */
    public void deleteById(Long id) {
    	sysRoleRepository.delete(id);
    }

}
