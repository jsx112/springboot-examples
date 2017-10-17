/******************************************************************************
 * Copyright (C) 2017 ShenZhen hhyy Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳华海乐盈开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
 * 复制、修改或发布本软件.
 ******************************************************************************/
package com.jsx.system.service;

import java.util.*;
import com.jsx.system.entity.SysUser;


/**
 * 
 * 系统用户 服务接口
 * @author jiasx
 * @since 1.0
 * @version 2017-07-06 jiasx
 */
public interface SysUserService {
    
    /**
     * 查询系统用户,带分页
     * @param sysUser
     * @return
     */
	long getTotalCount(SysUser sysUser);
    
    /**
     * 查询系统用户,带分页
     * @param sysUser
     * @return
     */
    List<SysUser> getList(SysUser sysUser);

    /**
     * 新增系统用户
     * @param sysUser
     */
    void insert(SysUser sysUser);

    /**
     * 修改系统用户
     * @param sysUser
     */
    void update(SysUser sysUser);

    /**
     * 根据Id查询系统用户
     * @param id
     */
    SysUser getById(Long id);

    /**
     * 删除系统用户
     * @param id
     */
    void deleteById(Long id);

}
