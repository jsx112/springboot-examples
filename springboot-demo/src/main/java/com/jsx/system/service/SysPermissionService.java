/******************************************************************************
 * Copyright (C) 2017 ShenZhen hhyy Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳华海乐盈开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
 * 复制、修改或发布本软件.
 ******************************************************************************/
package com.jsx.system.service;

import java.util.*;
import com.jsx.system.entity.SysPermission;


/**
 * 
 * 系统权限表 服务接口
 * @author jiasx
 * @since 1.0
 * @version 2017-07-06 jiasx
 */
public interface SysPermissionService {
    
    /**
     * 查询系统权限表,带分页
     * @param sysPermission
     * @return
     */
	long getTotalCount(SysPermission sysPermission);
    
    /**
     * 查询系统权限表,带分页
     * @param sysPermission
     * @return
     */
    List<SysPermission> getList(SysPermission sysPermission);

    /**
     * 新增系统权限表
     * @param sysPermission
     */
    void insert(SysPermission sysPermission);

    /**
     * 修改系统权限表
     * @param sysPermission
     */
    void update(SysPermission sysPermission);

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
