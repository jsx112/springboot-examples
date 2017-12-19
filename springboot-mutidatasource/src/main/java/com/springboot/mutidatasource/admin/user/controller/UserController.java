package com.springboot.mutidatasource.admin.user.controller;/**
 * Created by dell on 2017/11/22.
 */

import com.springboot.mutidatasource.admin.user.entity.User;
import com.springboot.mutidatasource.admin.user.service.UserService;
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
@RequestMapping("/user")
public class UserController extends BaseController {


    @Autowired
    private UserService userService;


    /**
     * 管理员用户是内置的，这里只能新增使用短链接的用户
     * @param user
     * @return
     */
    @RequestMapping("/insert")
    public User insert(User user) {
        return userService.insert(user);
    }

    /**
     * 管理员用户是内置的，这里只能修改使用短链接的用户
     * @param user
     * @return
     */
    @RequestMapping("/update")
    public User update(User user) {
        if(StringUtils.isEmpty(user.getId())){
            throw new BizException(CodeMsg.id_param_blank);
        }
        User dbUser = userService.find(user.getId());
        if(dbUser==null){
            throw new BizException(CodeMsg.record_not_exist);
        }
        dbUser.setAccount(user.getAccount());
        return userService.update(dbUser);
    }


    /**
     * 管理员用户是内置的，这里只能获取使用短链接的用户
     * @param id
     * @return
     */
    @RequestMapping("/get/{id}")
    public User get(@PathVariable("id") String id) {
        return userService.find(id);
    }

}
