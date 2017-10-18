/******************************************************************************
 * Copyright (C) 2017 ShenZhen hhyy Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳华海乐盈开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
 * 复制、修改或发布本软件.
 ******************************************************************************/
package com.springboot.quartz.system.dao.mybatis;

import java.util.*;
import com.springboot.quartz.system.entity.SysPermission;
import org.springframework.stereotype.Repository;

/**
 * 
 * 系统权限表数据库操作类
 * @author jiasx
 * @since 1.0
 * @version 2017-07-06 jiasx
 */
@Repository
public interface SysPermissionDao {
    
    /**
     * 查询系统权限表,带分页
     * @param entry
     * @return
     */
	long getTotalCount(SysPermission entry);
    
    /**
     * 查询系统权限表,带分页
     * @param entry
     * @return
     */
    List<SysPermission> getList(SysPermission entry);

    /**
     * 新增系统权限表
     * @param entry
     */
    void insert(SysPermission entry);

    /**
     * 修改系统权限表
     * @param entry
     */
    void update(SysPermission entry);

    /**
     * 根据Id查询系统权限表
     * @param id
     */
    SysPermission getById(Long id);

    /**
     * 删除系统权限表
     * @param id
     */
    void deleteById(Long id);

}
