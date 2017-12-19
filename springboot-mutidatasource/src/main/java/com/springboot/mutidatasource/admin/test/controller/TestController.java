package com.springboot.mutidatasource.admin.test.controller;/**
 * Created by dell on 2017/11/22.
 */

import com.springboot.mutidatasource.admin.test.entity.Test;
import com.springboot.mutidatasource.admin.test.service.TestService;
import com.springboot.mutidatasource.base.controller.BaseController;
import com.springboot.mutidatasource.base.entity.CodeMsg;
import com.springboot.mutidatasource.base.exception.BizException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户管理服务
 *
 * @author jiasx
 * @create 2017-11-22 18:05
 **/
@RestController
@RequestMapping("/test")
public class TestController extends BaseController {


    @Autowired
    private TestService testService;


    /**
     * 管理员用户是内置的，这里只能新增使用短链接的用户
     * @param user
     * @return
     */
    @RequestMapping("/insert")
    public Test insert(Test user) {
        return testService.insert(user);
    }

    /**
     * 管理员用户是内置的，这里只能修改使用短链接的用户
     * @param user
     * @return
     */
    @RequestMapping("/update")
    public Test update(Test user) {
        if(StringUtils.isEmpty(user.getId())){
            throw new BizException(CodeMsg.id_param_blank);
        }
        Test dbUser = testService.find(user.getId());
        if(dbUser==null){
            throw new BizException(CodeMsg.record_not_exist);
        }
        dbUser.setAccount(user.getAccount());
        return testService.update(dbUser);
    }


    /**
     * 管理员用户是内置的，这里只能获取使用短链接的用户
     * @param id
     * @return
     */
    @RequestMapping("/get/{id}")
    public Test get(@PathVariable("id") String id) {
        return testService.find(id);
    }


}
