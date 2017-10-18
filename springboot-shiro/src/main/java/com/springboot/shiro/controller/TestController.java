package com.springboot.shiro.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
* 测试权限，在Relam中配置的
* @author jiasx
* @create 2017/10/18 14:24
**/
@RestController
public class TestController {
    /**
     * 此方法执行的时候，会抛出异常：
     * Session creation has been disabled for the current subject.
     * @return
     */
    @RequestMapping("/test1")
    public String test1(){
        Subject currentUser = SecurityUtils.getSubject();
        Session session = currentUser.getSession();
        System.out.println(session);
        return"test1,宝贝";
    }

    @RequestMapping("/test2")
    @RequiresRoles("admin")
    public String test2(){
        return "test2,宝贝";
    }

    @RequestMapping("/test3")
    @RequiresPermissions("user:add")//权限管理;
    public String test3(){
        return "test3,宝贝";
    }
}