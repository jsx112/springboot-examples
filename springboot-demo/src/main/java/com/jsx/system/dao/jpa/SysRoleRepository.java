/******************************************************************************
 * Copyright (C) 2017 ShenZhen hhyy Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳华海乐盈开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
 * 复制、修改或发布本软件.
 ******************************************************************************/
package com.jsx.system.dao.jpa;

import java.util.*;
import com.jsx.system.entity.SysRole;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * 
 * 系统角色数据库操作类
 * @author jiasx
 * @since 1.0
 * @version 2017-07-06 jiasx
 */
public interface SysRoleRepository  extends JpaRepository<SysRole,Long> {
    

}
