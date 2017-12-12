package com.springboot.convert.controller;/**
 * Created by dell on 2017/11/13.
 */

import com.springboot.convert.base.controller.BaseController;
import com.springboot.convert.base.entity.UserInfo;
import com.springboot.convert.base.util.UserReqContextUtil;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * 用户服务控制类
 *
 * @author jiasx
 * @create 2017-11-13 16:13
 **/
@RestController
@RequestMapping("/json")
public class JsonController extends BaseController<UserInfo> {



    @PostMapping(value = "/get")
    public UserInfo get(@RequestBody String json) {
        UserInfo userInfo = new UserInfo();
        userInfo.setAccount("111");
        userInfo.setPhone(json);
        userInfo.setCreateTime(new Date());
        return userInfo;
    }


    @PostMapping(value = "/get1")
    public UserInfo get1(@RequestParam String user) {
        UserInfo userInfo = new UserInfo();
        userInfo.setAccount("111");
        userInfo.setPhone(UserReqContextUtil.getRequestBody() +user);
        userInfo.setCreateTime(new Date());
        return userInfo;
    }

}
