package com.springboot.mutidatasource.admin.test.service;

import com.springboot.mutidatasource.admin.test.entity.Test;
import com.springboot.mutidatasource.base.service.mysql.BaseService;

/**
* 用户管理服务
* @author jiasx
* @create 2017/11/22 18:08
**/
public interface TestService extends BaseService<Test,String> {

    Test findOneByAccount(String account);

}
