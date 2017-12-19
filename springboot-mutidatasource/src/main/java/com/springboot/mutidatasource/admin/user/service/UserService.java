package com.springboot.mutidatasource.admin.user.service;

import com.springboot.mutidatasource.admin.user.entity.User;
import com.springboot.mutidatasource.base.service.mongo.BaseService;

/**
* 用户管理服务
* @author jiasx
* @create 2017/11/22 18:08
**/
public interface UserService extends BaseService<User,String> {

    User  findOneByAccount(String account);

}
