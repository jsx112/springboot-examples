/******************************************************************************
 * Copyright (C) 2017 ShenZhen hhyy Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳华海乐盈开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
 * 复制、修改或发布本软件.
 ******************************************************************************/
package com.jsx.system.dao.mybatis;

import java.util.*;
import com.jsx.system.entity.SysUser;
import org.springframework.stereotype.Repository;

/**
 * 
 * 系统用户数据库操作类
 * @author jiasx
 * @since 1.0
 * @version 2017-07-06 jiasx
 */
@Repository
public interface SysUserDao {
    
    /**
     * 查询系统用户,带分页
     * @param entry
     * @return
     */
	long getTotalCount(SysUser entry);
    
    /**
     * 查询系统用户,带分页
     * @param entry
     * @return
     */
    List<SysUser> getList(SysUser entry);

    /**
     * 新增系统用户
     * @param entry
     */
    void insert(SysUser entry);

    /**
     * 修改系统用户
     * @param entry
     */
    void update(SysUser entry);

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
