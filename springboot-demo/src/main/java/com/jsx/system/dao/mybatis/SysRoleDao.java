/******************************************************************************
 * Copyright (C) 2017 ShenZhen hhyy Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳华海乐盈开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
 * 复制、修改或发布本软件.
 ******************************************************************************/
package com.jsx.system.dao.mybatis;

import java.util.*;
import com.jsx.system.entity.SysRole;
import org.springframework.stereotype.Repository;

/**
 * 
 * 系统角色数据库操作类
 * @author jiasx
 * @since 1.0
 * @version 2017-07-06 jiasx
 */
@Repository
public interface SysRoleDao {
    
    /**
     * 查询系统角色,带分页
     * @param entry
     * @return
     */
	long getTotalCount(SysRole entry);
    
    /**
     * 查询系统角色,带分页
     * @param entry
     * @return
     */
    List<SysRole> getList(SysRole entry);

    /**
     * 新增系统角色
     * @param entry
     */
    void insert(SysRole entry);

    /**
     * 修改系统角色
     * @param entry
     */
    void update(SysRole entry);

    /**
     * 根据Id查询系统角色
     * @param id
     */
    SysRole getById(Long id);

    /**
     * 删除系统角色
     * @param id
     */
    void deleteById(Long id);

}
