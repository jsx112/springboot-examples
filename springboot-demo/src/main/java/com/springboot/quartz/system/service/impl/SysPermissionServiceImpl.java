/******************************************************************************
 * Copyright (C) 2017 ShenZhen hhyy Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳华海乐盈开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
 * 复制、修改或发布本软件.
 ******************************************************************************/
package com.springboot.quartz.system.service.impl;

import java.util.*;

import com.springboot.quartz.system.dao.jpa.SysPermissionRepository;
import com.springboot.quartz.system.dao.mybatis.SysPermissionDao;
import com.springboot.quartz.system.entity.SysPermission;
import com.springboot.quartz.system.service.SysPermissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 * 系统权限表 服务实现
 * @author jiasx
 * @since 1.0
 * @version 2017-07-06 jiasx
 */
@Service
public class SysPermissionServiceImpl implements SysPermissionService {
	
	/**
	 * 日志记录
	 */
	private Logger logger = LoggerFactory.getLogger(SysPermissionServiceImpl.class);
	
	@Autowired
    private SysPermissionRepository sysPermissionRepository;
    
    @Autowired
    private SysPermissionDao sysPermissionDao;
    
    /**
     * 查询系统权限表,带分页
     * @param sysPermission
     * @return
     */
    public long getTotalCount(SysPermission sysPermission){
    	return sysPermissionDao.getTotalCount(sysPermission);
    }
    
    /**
     * 查询系统权限表,带分页
     * @param sysPermission
     * @return
     */
    public List<SysPermission> getList(SysPermission sysPermission){
    	return sysPermissionDao.getList(sysPermission);
    }

    /**
     * 新增系统权限表
     * @param sysPermission
     */
    public void insert(SysPermission sysPermission) {
    	sysPermissionRepository.save(sysPermission);
    }

    /**
     * 修改系统权限表
     * @param sysPermission
     */
    public void update(SysPermission sysPermission) {
    	sysPermissionRepository.save(sysPermission);
    }

    /**
     * 根据Id查询系统权限表
     * @param id
     */
    public SysPermission getById(Long id){
    	return sysPermissionDao.getById(id);
    }

    /**
     * 删除系统权限表
     * @param id
     */
    public void deleteById(Long id) {
    	sysPermissionRepository.delete(id);
    }

}
